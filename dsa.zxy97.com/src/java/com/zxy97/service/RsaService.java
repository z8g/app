/*
 * Rsa服务接口
 */

package com.zxy97.service;

import com.zxy97.model.Model;

/**
 *
 * @author zhaoxuyang
 */
public interface RsaService {
    
    /**
     * RSA加密
     * 即用公钥加密
     * @param message 消息
     * @return 密文
     */
    public String encrypt(String message);
    
    /**
     * RSA解密
     * 即用私钥解密
     * @param ciphertext 密文
     * @return 明文
     */
    public String decrypt(String ciphertext);
    
    /**
     * 将消息进行签名
     * 即用私钥进行加密
     * @param message
     * @return 
     */
    public Model sign(String message);
    
    /**
     * 将消息进行签名验证
     * 即用公钥进行解密
     * @param signModel
     * @return 
     */
    public boolean signVerify(Model signModel);
    
    /**
     * 消息的md5
     * @param message
     * @return 
     */
    public String md5(String message);
    
    /**
     * 消息的sha1
     * @param message
     * @return 
     */
    public String sha1(String message);
    
    /**
     * 消息的md5的签名
     * @param message
     * @return 
     */
    public Model Md5Sign(String message);
    
    /**
     * 消息的sha1的签名
     * @param message
     * @return 
     */
    public Model Sha1Sign(String message);
    
}
