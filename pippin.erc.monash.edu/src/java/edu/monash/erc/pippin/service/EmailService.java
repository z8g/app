package edu.monash.erc.pippin.service;

public interface EmailService {
    
    /**
     * 
     * @param email 用户填写的邮箱
     * @param name 用户填写的称呼
     * @param message 用户填写的消息
     * 
     * @return 
     */
    public boolean processSubmit(String email,String name,String message);
}
