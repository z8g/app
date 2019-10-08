/*
     _                                                                _   
 ___| |__   __ _  _____  ___   _ _   _  __ _ _ __   __ _   _ __   ___| |_ 
|_  / '_ \ / _` |/ _ \ \/ / | | | | | |/ _` | '_ \ / _` | | '_ \ / _ \ __|
 / /| | | | (_| | (_) >  <| |_| | |_| | (_| | | | | (_| |_| | | |  __/ |_ 
/___|_| |_|\__,_|\___/_/\_\\__,_|\__, |\__,_|_| |_|\__, (_)_| |_|\___|\__|
                                 |___/             |___/                  
*/
package net.zhaoxuyang.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @名称 路径 - 工具类，提供一些路径的获取方法
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 * 
 * @作者 zhaoxuyang
 * @版本　v1.1
 * @更新　2019-06-02
 */
//支持Spring依赖注入
@org.springframework.stereotype.Component
public class PathUtil {
    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        String test1 = "file:///home/zhaoxuyang/code/2019/20190601/blog.zhaoxuyang.net/build/web/WEB-INF/classes/net/zhaoxuyang/blog/util/PathUtil.class";
        String test2 = "/home/zhaoxuyang/code/2019/20190601/blog.zhaoxuyang.net/build/web/WEB-INF/classes/net/zhaoxuyang/blog/util/PathUtil.class";
        String test3 = "file:/home/zhaoxuyang/code/2019/20190601/blog.zhaoxuyang.net/build/web/WEB-INF/classes/net/zhaoxuyang/blog/util/PathUtil.class";
        String test4 = "file:///C:/home/zhaoxuyang/code/2019/20190601/blog.zhaoxuyang.net/build/web/WEB-INF/classes/net/zhaoxuyang/blog/util/PathUtil.class";
        System.out.println(sub(test1));
        System.out.println(sub(test2));
        System.out.println(sub(test3));
        System.out.println(sub(test4));

        //测试获取WebRoot路径
        System.out.println(new PathUtil().getWebRootPath());

        //测试从配置文件中根据键获取值
        String str = new PathUtil().getValueByKey("dwz.url.saveFolder");
        System.out.println(str);

        String str2 = new PathUtil().getValueByKey("dwz.url.domain.isSelf");
        System.out.println(str2);

        new PathUtil().save("out1", "aaaa", "注释");

        String str3 = new PathUtil().getValueByKey("out1");
        System.out.println(str3);

    }

    /**
     * 保存到配置文件
     *
     * @param key　键
     * @param value　值
     * @param comment 注释（会覆盖配置文件的其他注释）
     */
    public void save(String key, String value, String comment) {
        LOG.info(" public void save(String key, String value, String comment)");
        LOG.info("key=" + key);
        LOG.info("value=" + value);
        LOG.info("comment=" + comment);
        try (FileOutputStream fos = new FileOutputStream(getConfigFile())) {
            PROPERTIES.setProperty(key, value);
            PROPERTIES.store(fos, comment);
            isLoad = false;
        } catch (FileNotFoundException ex) {
            System.out.println(getClass().getName() + ex);
        } catch (IOException ex) {
            System.out.println(getClass().getName() + ex);
        }
    }

    /**
     * 根据配置文件的键获取值
     *
     * @param key
     * @return
     */
    public String getValueByKey(String key) {

        /**
         * 如果没有加载，则进行加载
         */
        if (!isLoad) {
            //如果加载成功，则将isLoad设置为true，否则设置为false
            if (load()) {
                isLoad = true;
                System.out.println("> config.properties配置文件加载成功");
            } else {
                isLoad = false;
                System.out.println("> config.properties配置文件加载失败");
            }
        }
        return PROPERTIES.getProperty(key);
    }

    /**
     * 加载配置文件
     */
    private boolean load() {
        try (InputStream in = new FileInputStream(getConfigFile())) {
            PROPERTIES.load(in);
            return true;
        } catch (IOException ex) {
            System.out.println(getClass().getName() + ex);
            return false;
        }
    }

    private File getConfigFile() {
        return new File(getWebRootPath(), FOLDER_NAME + FILE_NAME);
    }
    private static final Properties PROPERTIES = new Properties();//配置
    private static boolean isLoad = false;//是否已经加载配置文件

    private static final String FOLDER_NAME = "WEB-INF/classes/";//配置目录
    private static final String FILE_NAME = "config.properties";//配置文件名

    /**
     * @return WebRoot的绝对路径（后面有斜杠）
     * 例如：/home/zhaoxuyang/code/2019/20190601/blog.zhaoxuyang.net/build/web/
     */
    public String getWebRootPath() {
        String classPath = getClassPath();
        String str = sub(classPath);
        int start = str.indexOf("/");
        int end = str.lastIndexOf("WEB-INF");
        return str.substring(start, end);
    }

    /**
     * 去除冗余路径如：　file: /// //
     *
     * @param classPath
     * @return
     */
    private static String sub(String classPath) {
        return classPath.replaceAll("file:", "")
                .replaceAll("///", "")
                .replaceAll("//", "");
    }

    /**
     * 获取当前类的文件路径
     *
     * @return
     */
    private String getClassPath() {
        Package thePackage = getClass().getPackage();//包: package xxx.xxx
        String packageName = thePackage != null ? thePackage.getName() : "";//包名

        //获取类名
        final String fullyClassName = getClass().getName();//类全限定路径
        int start = packageName.length() + 1;
        int end = fullyClassName.length();
        String className = "".equals(packageName) ? fullyClassName
                : fullyClassName.substring(start, end);//类名

        return getClass().getResource(className + ".class").toString();
    }

}
