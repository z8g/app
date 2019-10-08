<%@page contentType="text/html" pageEncoding="UTF-8"%>
<pre>package com.zxy97.caesar;

public class CaesarEncryption01 {

    /**
     * 加密
     * @param content 铭文
     * @param key 移位数
     * @return 返回密文
     */
    public static String caesarEncode(String content, int key) {
        String es = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c += key % 26;
                if (c < 'a') {
                    c += 26;
                }
                if (c > 'z') {
                    c -= 26;
                }
            } else if (c >= 'A' && c <= 'Z') {
                c += key % 26;
                if (c < 'A') {
                    c += 26;
                }
                if (c > 'Z') {
                    c -= 26;
                }
            }
            es += c;
        }
        return es;
    }

    /**
     * 解密
     * @param content 密文
     * @param key 移位数
     * @return 明文
     */
    public static String caesarDecode(String content, int key) {
        String es = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c -= key % 26;
                if (c < 'a') {
                    c += 26;
                }
                if (c > 'z') {
                    c -= 26;
                }
            } else if (c >= 'A' && c <= 'Z') {
                c -= key % 26;
                if (c < 'A') {
                    c += 26;
                }
                if (c > 'Z') {
                    c -= 26;
                }
            }
            es += c;
        }
        return es;
    }

    /**
     * 按照一定格式打印
     * @param content 明文/密文
     * @param key 秘钥
     * @param isDecode 是否解密，如果不是，则是加密
     * @return 
     */
    public static String print(String content, int key, boolean isDecode) {
        String str = isDecode?caesarDecode(content, key):caesarEncode(content, key);
        String type = isDecode?"decode":"encode";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("content=")
                .append(content)
                .append("\n")
                .append("key=")
                .append(key)
                .append("\n")
                .append(type).append("=")
                .append(str)
                .append("\n");
        return stringBuffer.toString();
    }

    /**
     * 测试
     * @param args 
     */
    public static void main(String[] args) {
        final String[] contents = {
            "test",
            "多字符测试,./;'[]1234567890abcdefghijklmnopqrstuvwxyz"
        };
        final int[] keys = {
            3,26,27,-1
        };

        for (String content : contents) {
            for(int key:keys){
               System.out.println(print(content, key,false));
            }
        }
    }
}
    
/****输出结果****
content=test
key=3
encode=whvw

content=test
key=26
encode=test

content=test
key=27
encode=uftu

content=test
key=-1
encode=sdrs

content=多字符测试,./;'[]1234567890abcdefghijklmnopqrstuvwxyz
key=3
encode=多字符测试,./;'[]1234567890defghijklmnopqrstuvwxyzabc

content=多字符测试,./;'[]1234567890abcdefghijklmnopqrstuvwxyz
key=26
encode=多字符测试,./;'[]1234567890abcdefghijklmnopqrstuvwxyz

content=多字符测试,./;'[]1234567890abcdefghijklmnopqrstuvwxyz
key=27
encode=多字符测试,./;'[]1234567890bcdefghijklmnopqrstuvwxyza

content=多字符测试,./;'[]1234567890abcdefghijklmnopqrstuvwxyz
key=-1
encode=多字符测试,./;'[]1234567890zabcdefghijklmnopqrstuvwxy

****输出结果****/
</pre>