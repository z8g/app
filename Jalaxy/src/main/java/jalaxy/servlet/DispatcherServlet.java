package jalaxy.servlet;

import jalaxy.annotation.Controller;
import jalaxy.annotation.RequestMapping;
import jalaxy.util.LoggerUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

    static final Logger LOG = LoggerUtil.getDefaultLogger();

    private final Properties properties = new Properties();

    private final List<String> classNames = new ArrayList<>();

    private final Map<String, Object> ioc = new HashMap<>();

    private final Map<String, Method> handlerMapping = new HashMap<>();

    private final Map<String, Object> controllerMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        /**
         * 1.加载配置文件
         */
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        /**
         * 2.初始化所有相关联的类,扫描用户设定的包下面所有的类
         */
        doScanner(properties.getProperty("scanPackage"));

        /**
         * 3.拿到扫描到的类,通过反射机制实例化, 并且放到ioc容器中(k-v 即 beanName-bean) beanName默认是首字母小写
         */
        doInstance();

        /**
         * 4.初始化HandlerMapping(将url和method对应上)
         */
        initHandlerMapping();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            doDispatch(req, resp);//处理请求
        } catch (Exception e) {
            resp.getWriter().write("500：服务器异常");
        }

    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse resp)
            throws Exception {
        if (handlerMapping.isEmpty()) {
            return;
        }

        String url = request.getRequestURI();
        String contextPath = request.getContextPath();

        url = url.replace(contextPath, "").replaceAll("/+", "/");

        if (!this.handlerMapping.containsKey(url)) {
            resp.getWriter().write("404：路径不存在");
            return;
        }

        Method method = this.handlerMapping.get(url);

        //获取方法的参数列表
        Class<?>[] parameterTypes = method.getParameterTypes();

        //获取请求的参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        //保存参数值
        Object[] paramValues = new Object[parameterTypes.length];

        //方法的参数列表
        for (int i = 0; i < parameterTypes.length; i++) {
            //根据参数名称，做某些处理  
            String requestParam = parameterTypes[i].getSimpleName();
            LOG.info(requestParam);
            switch (requestParam) {
                case "HttpServletRequest":
                    paramValues[i] = request;
                    break;
                case "HttpServletResponse":
                    paramValues[i] = resp;
                    break;
                case "String":
                    for (Entry<String, String[]> param : parameterMap.entrySet()) {
                        String value = Arrays.toString(param.getValue())
                                .replaceAll("\\[|\\]", "")
                                .replaceAll(",\\s", ",");
                        paramValues[i] = value;
                    }
                    break;
                default:
                    break;
            }
        }

        /**
         * 利用反射机制来调用 第一个参数是method所对应的实例(在ioc容器中)
         */
        try {
            method.invoke(this.controllerMap.get(url), paramValues);
        } catch (IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException e) {
        }
    }

    private void doLoadConfig(String location) {
        try (InputStream iStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(location)) {
            properties.load(iStream);
        } catch (IOException e) {
        }

    }

    private void doScanner(String packageName) {
        // 把包名的所有的.替换成/
        String packagePath = packageName.replaceAll("\\.", "/");

        URL url = this.getClass()
                .getClassLoader()
                .getResource(String.format("/%s", packagePath));

        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                String fileName = file.getName();
                String name = String.format("%s.%s", packageName, fileName);
                doScanner(name);
            } else {
                String fileName = file.getName().replace(".class", "");
                String className = String.format("%s.%s", packageName, fileName);
                classNames.add(className);
            }
        }
    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        classNames.forEach((className) -> {
            try {
                Class<?> c = Class.forName(className);
                if (c.isAnnotationPresent(Controller.class)) {
                    String key = toLowerFirstWord(c.getSimpleName());
                    ioc.put(key, c.newInstance());
                }
            } catch (ClassNotFoundException
                    | IllegalAccessException
                    | InstantiationException e) {
            }
        });
    }

    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        try {
            for (Entry<String, Object> entry : ioc.entrySet()) {
                Class<? extends Object> clazz = entry.getValue().getClass();
                if (!clazz.isAnnotationPresent(Controller.class)) {
                    continue;
                }

                // url = controller上的url + 方法上的url
                String baseUrl = "";
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
                    baseUrl = annotation.value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(RequestMapping.class)) {
                        continue;
                    }
                    RequestMapping annotation
                            = method.getAnnotation(RequestMapping.class);
                    String url = annotation.value();

                    url = String.format("%s/%s", baseUrl, url)
                            .replaceAll("/+", "/");
                    handlerMapping.put(url, method);
                    controllerMap.put(url, clazz.newInstance());
                }
            }

        } catch (IllegalAccessException
                | InstantiationException
                | SecurityException e) {
        }

    }

    /**
     * 把字符串的首字母小写
     *
     * @param name
     * @return
     */
    private static String toLowerFirstWord(String name) {
        if (name == null) {
            return null;
        }
        int len = name.length();
        if (len == 0) {
            return name;
        }
        String first = name.substring(0, 1).toLowerCase();
        String end = name.substring(1);
        return first.concat(end);
    }

}
