package jalaxy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zhaoxuyang
 */
public class DatetimeUtil {

    
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(sdf.format(new Date()));
    }
    
}
