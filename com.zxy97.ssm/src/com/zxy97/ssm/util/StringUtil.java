package com.zxy97.ssm.util;

/**
 *
 * @author Administrator
 */
public class StringUtil {

    /**
     * 首字母转小写
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 提取类名中的Model,对其首字母小写，如将com.pojo.User转换成user
     *
     * @param className 类名
     * @return
     */
    public static String toModelLower(String className) {
        return toLowerCaseFirstOne(toModel(className));
    }

    /**
     * 提取类名中的Model,对其首字母大写，如将com.pojo.User转换成User
     *
     * @param className 类名
     * @return
     */
    public static String toModelUpper(String className) {
        return toUpperCaseFirstOne(toModel(className));
    }

    /**
     * 从全类名中提取出类名，如将com.pojo.User转换成User
     *
     * @param className
     * @return
     */
    public static String toModel(String className) {
        return className.substring(className.lastIndexOf(".") + 1);
    }

    /**
     * 将包路径转换成文件路径，如将com.pojo转换成com/pojo
     *
     * @param packageRoot
     * @return
     */
    public static String toPackagePath(String packageRoot) {
        return packageRoot.replaceAll("\\.", "/");
    }
    
    
}
