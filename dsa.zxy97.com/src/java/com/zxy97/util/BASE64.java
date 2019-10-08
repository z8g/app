/*
Base64用于网络中传输的数据进行编码，
严格意义上属于编码的格式，
有64个字符的对应的编码，
Base64就是将内容按照该格式进行编码。
可以对数据编码和解码，
是可逆的，安全度较低，
不过，
也可以作为最基础最简单的加密算法用于加密要求较弱的情况
 */
package com.zxy97.util;

import java.io.IOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64 {

    public static void jdkBase64(String src){
        try {
            BASE64Encoder encoder=new BASE64Encoder();
            String encode = encoder.encode(src.getBytes());
            System.out.println("encode: "+encode);
            
            BASE64Decoder decoder=new BASE64Decoder();
            String decode=new String(decoder.decodeBuffer(encode));
            System.out.println("decode: "+decode);
        } catch (IOException e) {
        }
    }

    public static void main(String[] args){
        jdkBase64("123456");
    }
}
