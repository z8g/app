package com.zxy97.aes;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 *
 * @author Administrator
 */
public class ExplorerUtil {
    /**
     * 打开资源管理器
     * @param path
     * @throws java.io.IOException
     */
    public static void openExplorer(String path) throws IOException{
        Runtime.getRuntime().exec("cmd /c start explorer " + path);
    }
    
    /**
     * 获取文件大小
     * @param length
     * @return 
     */
    public static String getFileSize(long length){
        DecimalFormat df = new DecimalFormat("#.00");
        String str;
        if(length<=0){
           str = "";
        }else if(length<1024){
           str = df.format(length)+"B";
        }else if(length<1024*1024){
            str = df.format((length/1024.0)) + "KB"; 
        }else if(length<1024*1024*1024){
            str = df.format((length/1024.0/1024)) + "MB"; 
        }else{
            str = df.format((length/1024.0/1024/1024)) + "GB"; 
        }
        return str;
    }
 
}
