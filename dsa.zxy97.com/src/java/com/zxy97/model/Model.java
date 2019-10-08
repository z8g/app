/*
 * 验证后产生的数据对象
 */
package com.zxy97.model;

import java.io.Serializable;

/**
 *
 * @author zhaoxuyang
 */
public class Model implements Serializable{

    private String message;//消息
    private String encodedData;//加密后产生的密文
    private String decodedData;//解密后的消息
    private String sign;//私钥加密后产生的签名
    private boolean verify;//签名的验证结果
    private String publicKey;

    @Override
    public String toString() {
        return "Model{" + "message=" + message + ", encodedData=" + encodedData + ", decodedData=" + decodedData + ", sign=" + sign + ", verify=" + verify + ", publicKey=" + publicKey + '}';
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEncodedData() {
        return encodedData;
    }

    public void setEncodedData(String encodedData) {
        this.encodedData = encodedData;
    }

    public String getDecodedData() {
        return decodedData;
    }

    public void setDecodedData(String decodedData) {
        this.decodedData = decodedData;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
