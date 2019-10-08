///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.zhaoxuyang.util;
//
//import net.zhaoxuyang.blog.model.MailBean;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// *
// * @author zhaoxuyang
// */
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class MailUtilTest {
//    
//    public MailUtilTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of sendSimpleMail method, of class MailUtil.
//     */
//    @Test
//    public void testSendSimpleMail() {
//        System.out.println("sendSimpleMail");
//        MailBean mailBean = new MailBean();
//        mailBean.setContent("内容");
//        mailBean.setSubject("主题");
//        mailBean.setRecipient("1395359719@qq.com");
//        MailUtil instance = new MailUtil();
//        instance.sendSimpleMail(mailBean);
//    }
//
//    /**
//     * Test of sendHTMLMail method, of class MailUtil.
//     */
//    @Test
//    public void testSendHTMLMail() {
//        System.out.println("sendHTMLMail");
//        MailBean mailBean = null;
//        MailUtil instance = new MailUtil();
//        instance.sendHTMLMail(mailBean);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of sendAttachmentMail method, of class MailUtil.
//     */
//    @Test
//    public void testSendAttachmentMail() {
//        System.out.println("sendAttachmentMail");
//        MailBean mailBean = null;
//        MailUtil instance = new MailUtil();
//        instance.sendAttachmentMail(mailBean);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of sendInlineMail method, of class MailUtil.
//     */
//    @Test
//    public void testSendInlineMail() {
//        System.out.println("sendInlineMail");
//        MailBean mailBean = null;
//        MailUtil instance = new MailUtil();
//        instance.sendInlineMail(mailBean);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
