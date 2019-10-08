/*
     _                                                                _   
 ___| |__   __ _  _____  ___   _ _   _  __ _ _ __   __ _   _ __   ___| |_ 
|_  / '_ \ / _` |/ _ \ \/ / | | | | | |/ _` | '_ \ / _` | | '_ \ / _ \ __|
 / /| | | | (_| | (_) >  <| |_| | |_| | (_| | | | | (_| |_| | | |  __/ |_ 
/___|_| |_|\__,_|\___/_/\_\\__,_|\__, |\__,_|_| |_|\__, (_)_| |_|\___|\__|
                                 |___/             |___/                  
*/
package net.zhaoxuyang.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

/**
 * 密码学 -工具类
 * @author zhaoxuyang
 */
public class CryptographicUtil {

    public static void main(String[] args){
        String password = "123zcczxxxxxxxxxx6";
        String salt = StringUtil.createSalt();
        String message = String.format("%s%s", password,salt);
        String passwordSaltSha = CryptographicUtil.sha(message);
        System.out.println(passwordSaltSha);
        System.out.println(passwordSaltSha.length());
    }
    
//    public static String jdkBase64Encode(String plaintext) {
//        BASE64Encoder encoder = new BASE64Encoder();
//        String result = encoder.encode(plaintext.getBytes());
//        return result;
//    }
//
//    public static String jdkBase64Decode(String ciphertext) throws IOException {
//        BASE64Decoder decoder = new BASE64Decoder();
//        String result = new String(decoder.decodeBuffer(ciphertext));
//        return result;
//    }

    public static String md5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(message.getBytes("utf-8"));
            return toHex(bytes);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sha(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] bytes = md.digest(message.getBytes("utf-8"));
            return toHex(bytes);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
