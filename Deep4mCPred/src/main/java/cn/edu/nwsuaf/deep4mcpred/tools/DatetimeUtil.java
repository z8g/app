package cn.edu.nwsuaf.deep4mcpred.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间转字符串-工具类
 *
 * @author zhaoxuyang
 */
public class DatetimeUtil {

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * yyyyMMddHHmmss
     *
     * @return
     */
    public static String formatFileName() {
        return sdf1.format(new Date());
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param date new Date()
     * @return
     */
    public static String formatFileName(Date date) {
        return sdf1.format(date);
    }

    public static String formatFileName(long date) {
        return sdf1.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatNow() {
        return sdf2.format(new Date());
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param now new Date()
     * @return
     */
    public static String formatNow(Date now) {
        return sdf2.format(now);
    }

    public static String formatDate(long date) {
        return sdf2.format(date);
    }

    /**
     * yyyy-MM-dd
     *
     * @return
     */
    public static String formatToday() {
        return sdf3.format(new Date());
    }

    /**
     * yyyy-MM-dd
     *
     * @param today new Date()
     * @return
     */
    public static String formatToday(Date today) {
        return sdf3.format(today);
    }

}
