
package pan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @名称 日期工具类
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class DateUtil {
    
    public static void main(String[] args){
        //获取当前系统时间的测试
        //System.out.println(getNow());
    }
    
    public static SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 获取当前系统时间并格式化
     * @return 
     */
    public static String formatNow(){
        Date date = new Date();
        return sdf.format(date);
    }
}
