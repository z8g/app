/*
 * 注册控制器的工具类
 */
package club.nwsuaf.controller.util;

/**
 *
 * @时间 2019-02-05 03:10
 * @author 赵栩旸
 */
public class RegisterUtil {

    public static boolean checkUsername(String username) {
        return checkReservedWord(username);
    }

    private static boolean checkReservedWord(String username) {
        if (username == null || username.endsWith("")) {
            return false;
        }
        /**
         * 不能包含的字符
         */
        String[] containsStrs = {
            "/", "@", "#", "-", "'", "\"", "."
        };
        for (String str : containsStrs) {
            if (username.contains(str)) {
                return false;
            }
        }
        /**
         * 开头不能为
         */
        String[] startsWithStrs = {
            "user",
            "project",
            "notice",
            "article",
            "administrator",
            "operator"
        };
        for (String str : startsWithStrs) {
            if (username.contains(str)) {
                return false;
            }
        }

        /**
         * 结尾不能为
         */
        String[] endsWithStrs = {
            ".form",
            ".action",
            ".do",
            ".jsp",
            ".html",
            ".css",
            ".js",
            ".json"
        };
        for (String str : endsWithStrs) {
            if (username.contains(str)) {
                return false;
            }
        }
        return true;
    }
}
