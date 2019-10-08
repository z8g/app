package edu.monash.erc.pippin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @名称 工具 - 日期时间
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class DatetimeUtil {

    private DatetimeUtil() {
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * @描述 获取当前系统时间并格式化
     * @return yyyy-MM-dd HH:mm:ss 形式的当前系统时间
     */
    public static String formatNow() {
        return sdf.format(new Date());
    }

    public static String formatDateTime(long length) {
        return sdf.format(new Date(length));
    }

    public static String formatFileName(String fileName, String suffix) {
        int lastIndex = fileName.lastIndexOf(".");
        String e = lastIndex < 0 ? "" : fileName.substring(lastIndex);
        return String.format("%s-%s%s", sdfFileName.format(new Date()),suffix, e);
    }

}
