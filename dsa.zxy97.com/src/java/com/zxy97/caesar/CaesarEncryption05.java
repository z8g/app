package com.zxy97.caesar;

public class CaesarEncryption05 {

    /**
     * 加密
     *
     * @param content 明文/密文
     * @param key 移位数
     * @param res 字符集
     * @param isDecode 是不是解密，如果不是则是加密
     * @return 返回密文/明文
     */
    public static String caesarEncode(String content, int key, String res, boolean isDecode) {
        int resLen = res.length();
        StringBuffer es = new StringBuffer();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            int index = res.indexOf(c);
            if (index >= 0) {
                if (isDecode) {
                    index = (index - key + resLen*9999) % resLen;
                } else {
                    index = (index + key + resLen*9999) % resLen;
                }
                es.append(res.charAt(index));
            }else{
                es.append(c);
            }  
        }
        return es.toString();
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(caesarEncode("4227512", -3, "12345",false));
        System.out.println(caesarEncode("1234", 3, "12345",true));
    }

}
