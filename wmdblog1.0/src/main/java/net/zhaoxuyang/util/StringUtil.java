/*
     _                                                                _   
 ___| |__   __ _  _____  ___   _ _   _  __ _ _ __   __ _   _ __   ___| |_ 
|_  / '_ \ / _` |/ _ \ \/ / | | | | | |/ _` | '_ \ / _` | | '_ \ / _ \ __|
 / /| | | | (_| | (_) >  <| |_| | |_| | (_| | | | | (_| |_| | | |  __/ |_ 
/___|_| |_|\__,_|\___/_/\_\\__,_|\__, |\__,_|_| |_|\__, (_)_| |_|\___|\__|
                                 |___/             |___/                  
*/
package net.zhaoxuyang.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串 - 工具类
 * @author zhaoxuyang
 */
public class StringUtil {

    public static void main(String[] args){
        System.out.println(createSalt());
        System.out.println(createSalt());
    }
    public static boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regEx1);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    
    public static String createSalt() {
        return UUID.randomUUID().toString().substring(0,4);
    }
}
