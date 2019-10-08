package pan.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.apache.ibatis.io.Resources;

/**
 * @名称 路径工具类，提供一些路径的获取方法
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class PathUtil {

    public static void main(String[] args) {

//测试获取WebRoot路径
//System.out.println(new PathUtil().getWebRootPath());
//测试从配置文件中根据键获取值
//        try {
//            String str = PathUtil.getValueByKey("str");
//            System.out.println(str);
//        } catch (FileNotFoundException ex) {
//
//        }
//
    }
    /**
     * 根据配置文件的键获取值
     *
     * @param key
     * @return
     */
    public static String getValueByKey(String key) {

        /**
         * 如果没有加载，则进行加载
         */
        if (!isLoad) {
            /**
             * 如果加载成功，则将isLoad设置为true，否则设置为false
             */
            if (load()) {
                isLoad = true;
                System.out.println("配置文件加载成功");
            } else {
                isLoad = false;
                System.out.println("配置文件加载失败");
            }
        }
        return prop.getProperty(key);
    }

    /**
     * 加载配置文件
     */
    private static boolean load() {
        try {
            InputStream in = Resources.getResourceAsStream(DEFAULT_FILE_NAME);
            prop.load(in);
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    private static final Properties prop = new Properties();//配置文件
    private static boolean isLoad = false;//是否已经加载配置文件
    private static final String DEFAULT_FILE_NAME = "config.properties";
//加载配置文件的代码，以前写成这样子，改进后如上所示
//改进后不用每次根据键获取值时都加载一次配置文件
//    private String getValueByKey(String configFilePath, String key)
//      throws FileNotFoundException {
//        String value;
//        Properties prop = new Properties();
//        try {
//            InputStream in = new BufferedInputStream(
//                new FileInputStream(configFilePath));
//            prop.load(in);
//            value = prop.getProperty(key);
//            return value;
//        } catch (IOException e) {
//            System.out.println(e);
//            return null;
//        }
//    }
    
    /**
     * @return WebRoot的绝对路径
     */
    public String getWebRootPath() {

        final String strClassName = getClass().getName();

        String strPackageName = "";
        if (getClass().getPackage() != null) {
            strPackageName = getClass().getPackage().getName();
        }

        String strClassFileName;
        if ("".equals(strPackageName)) {
            strClassFileName = strClassName;
        } else {
            strClassFileName = strClassName.substring(strPackageName.length() + 1, strClassName.length());
        }

        URL url = getClass().getResource(strClassFileName + ".class");

        String strURL = url.toString();
        strURL = strURL.substring(strURL.indexOf("/") + 1, strURL.lastIndexOf("WEB-INF"));
        return strURL;
    }
}
