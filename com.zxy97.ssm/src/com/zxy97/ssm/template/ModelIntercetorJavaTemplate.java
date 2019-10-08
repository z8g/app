package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;
import java.util.List;

public class ModelIntercetorJavaTemplate extends Template {

    private String getContent(Config config, String model, String uri) {

        StringBuilder result = new StringBuilder();
        String upperModel = StringUtil.toUpperCaseFirstOne(model);
        String upperUri = StringUtil.toUpperCaseFirstOne(uri);
        String packageRoot = config.getPackageRoot();

        result.append(format("package %s.interceptor;\n\n", packageRoot))
                .append(format("import %s.model.%s;\n", packageRoot, upperModel))
                .append("import java.io.IOException;\n")
                .append("import java.lang.invoke.MethodHandles;\n")
                .append("import java.util.logging.Level;\n")
                .append("import java.util.logging.Logger;\n")
                .append("import javax.servlet.http.HttpServletRequest;\n")
                .append("import javax.servlet.http.HttpServletResponse;\n")
                .append("import javax.servlet.http.HttpSession;\n")
                .append("import org.springframework.web.servlet.HandlerInterceptor;\n")
                .append("import org.springframework.web.servlet.ModelAndView;\n\n")
                .append(format("public class %s%sInterceptor implements HandlerInterceptor {\n\n", upperModel, upperUri))
                .append("    @Override\n")
                .append("    public boolean preHandle(\n")
                .append("            HttpServletRequest request,\n")
                .append("            HttpServletResponse response,\n")
                .append("            Object handler\n")
                .append("    ) {\n")
                .append("        boolean result = false;\n")
                .append("        HttpSession session = request.getSession(false);\n")
                .append("        if (session != null) {\n")
                .append(format("            %s %s = (%s) session.getAttribute(\"%s\");\n", upperModel, uri, upperModel, uri))
                .append(format("            if (%s != null) {\n", uri))
                .append("                result = true;\n")
                .append("            }\n")
                .append("        }\n")
                .append("        if(!result){\n")
                .append("            try {\n")
                .append("                response.sendError(403);//有人用401，请在web.xml中配置对应的页面\n")
                .append("            } catch (IOException ex) {\n")
                .append("                Logger.getLogger(MethodHandles.lookup().lookupClass().getName()).log(Level.SEVERE, null, ex);\n")
                .append("            }\n")
                .append("        }\n")
                .append("        return result;\n")
                .append("    }\n\n")
                .append("    @Override\n")
                .append("    public void postHandle(\n")
                .append("            HttpServletRequest request,\n")
                .append("            HttpServletResponse response,\n")
                .append("            Object handler,\n")
                .append("            ModelAndView modelAndView\n")
                .append("    ) {\n\n")
                .append("    }\n\n")
                .append("    @Override\n")
                .append("    public void afterCompletion(\n")
                .append("            HttpServletRequest request,\n")
                .append("            HttpServletResponse response,\n")
                .append("            Object handler,\n")
                .append("            Exception ex\n")
                .append("    ) {\n\n")
                .append("    }\n\n")
                .append("}");
        return result.toString();
    }

    private String getFilePath(Config config, String model, String uri) {
        String src = config.getSrc();
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        String upperModel = StringUtil.toUpperCaseFirstOne(model);
        String upperUri = StringUtil.toUpperCaseFirstOne(uri);
        return format("%s/%s/interceptor/%s%sInterceptor.java", src, packagePath, upperModel, upperUri);
    }

    @Override
    public void createFile(Config config) {
        List<String> list = config.getInterceptorList();
        if (list == null || list.isEmpty()) {
            return;
        }
        list.stream().forEach((intercetor) -> {
            String[] strs = intercetor.split(":");
            write(getFilePath(config, strs[0], strs[1]), getContent(config, strs[0], strs[1]));
        });
    }

}
