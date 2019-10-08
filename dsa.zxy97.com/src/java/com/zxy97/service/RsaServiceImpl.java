/*
 * RsaService的实现类
 */
package com.zxy97.service;

import com.zxy97.model.Model;
import com.zxy97.rsa.RSAUtil;
import com.zxy97.util.MD5;
import com.zxy97.util.SHA;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhaoxuyang
 */
public class RsaServiceImpl implements RsaService {

    public static String publicKey;
    public static String privateKey;
    
    /**
     * 初始化，获取公钥和私钥
     */
    static {
        try {
            Map<String, Object> keyMap = RSAUtil.genKeyPair();
            publicKey = RSAUtil.getPublicKey(keyMap);
            privateKey = RSAUtil.getPrivateKey(keyMap);
        } catch (Exception e) {
        }
    }

    /**
     * 私钥签名
     * @param message 消息
     * @return 签名后的model
     */
    @Override
    public Model sign(String message) {
        Model result = new Model();
        try {
            byte[] encodedData = RSAUtil.encryptByPrivateKey(message.getBytes(), privateKey);//加密后的数据
            String sign = RSAUtil.sign(encodedData, privateKey);//签名
        
            result.setMessage(message);
            result.setEncodedData(new String(encodedData));
            result.setSign(sign);
            System.out.println(result);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 公钥验证签名
     * @param signModel 私钥签名后的model
     * @return 
     */
    @Override
    public boolean signVerify(Model signModel) {
        boolean result = false;
        try {
            String message = signModel.getMessage();
            byte[] encodedData = RSAUtil.encryptByPrivateKey(message.getBytes(), privateKey);//加密后的数据
            String sign = signModel.getSign();
            result = RSAUtil.verify(encodedData, publicKey, sign);
            signModel.setVerify(result);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public String md5(String message) {
       return MD5.md5(message);
    }

    @Override
    public String sha1(String message) {
        return SHA.sha1(message);
    }

    @Override
    public Model Md5Sign(String message) {
        return sign(md5(message));
    }

    @Override
    public Model Sha1Sign(String message) {
        return sign(sha1(message));
    }

    @Override
    public String encrypt(String message) {
        String result = null;
        try {
            byte[] encodedData = RSAUtil.encryptByPublicKey(message.getBytes(), publicKey);
            result = new String(encodedData);
        } catch (Exception ex) {
            Logger.getLogger(RsaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public String decrypt(String ciphertext) {
        String result = null;
        try {
            byte[] encodedData = ciphertext.getBytes();
            byte[] decodedData = RSAUtil.decryptByPrivateKey(encodedData, privateKey);
            result = new String(decodedData);
        } catch (Exception ex) {
            Logger.getLogger(RsaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
