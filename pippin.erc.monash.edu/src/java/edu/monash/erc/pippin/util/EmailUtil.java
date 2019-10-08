package edu.monash.erc.pippin.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailUtil {
    private static final String FORM = "zhaoxuyang19971015@foxmail.com";
    private static final String FORM_KEYWORD = "aysnkwkfbhbbjgbg";
    
    /**
     * @param from 发件人QQ邮箱地址
     * @param keyword 发件人授权码
     * @param to 收件人邮箱地址
     * @param subject 邮件主题
     * @param text 邮件内容
     * @return 发送成功状态
     */
    public static boolean send(final String from, final String keyword, String to, String subject, String text) {

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, keyword); //发件人邮件地址、授权码
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);// 创建默认的 MimeMessage 对象
            message.setFrom(new InternetAddress(from));// Set From: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// Set To: 头部头字段
            message.setSubject(subject);// Set Subject: 头部头字段
            message.setText(text);// 设置消息体
            Transport.send(message);// 发送消息
            return true;
        } catch (MessagingException mex) {
            return false;
        }
    }

    public static void main(String[] args) {
        String to = "1395359719@qq.com";// 收件人电子邮箱
        String from = "zhaoxuyang19971015@foxmail.com";// 发件人电子邮箱
        String from_keyword = "aysnkwkfbhbbjgbg";//QQ邮箱申请的授权码，申请步骤：QQ邮箱-设置-账户-POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务-开启服务
        String subject =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        String text = "zxy97网盘欢迎您的注册！点击链接访问激活注册信息 http://zxy97.com";
        if(send(from,from_keyword,to,subject,text)){
            System.out.println("邮件已发送！");
        }else{
            System.out.println("邮件发送失败！");
        }
    }
    
}
