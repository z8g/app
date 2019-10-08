///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.zhaoxuyang.blog.service.impl;
//
//import java.util.Date;
//import java.util.List;
//import java.util.TreeMap;
//import net.zhaoxuyang.blog.model.Article;
//import net.zhaoxuyang.blog.model.ArticleAuth;
//import net.zhaoxuyang.blog.service.ArticleService;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// *
// * @author zhaoxuyang
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ArticleServiceImplTest {
//    
//    public ArticleServiceImplTest() {
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
//    
//    @Autowired
//    ArticleService articleService;
//    /**
//     * Test of insert method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testInsert() {
//        System.out.println("insert");
//        
//        boolean result = true;
//        for(int i = 111110 ;i < 1000000; i++){
//            Integer userId = i % 1000;
//            String category = "category" + (i % 200);
//            StringBuilder sb = new StringBuilder();
//            for(int j = 0; j < (i % 6); j++){
//                sb.append("tag").append(j).append("|");
//            }
//            sb.append("tag").append(i % 1000);
//            String tags = sb.toString();
//            String markdown = "md" + i;
//            Integer auth = i % 3;
//            String title = "title" + (i % 5000);
//            
//            Article article = new Article();
//            article.setAuth(auth);
//            article.setCategory(category);
//            article.setTags(tags);
//            article.setUserId(userId);
//            article.setMarkdown(markdown);
//            
//            Date now = new Date();
//            Date date = new Date(now.getTime() + 10000 * (i % 1000));
//            article.setGmtCreate(date);
//            article.setGmtUpdate(date);
//            article.setTitle(title);
//            result &= articleService.insert(article);
//            System.out.println(i);
//        }
//        
//        assertEquals(true, result);
//    }
//
//    /**
//     * Test of delete method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Integer id = null;
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        boolean expResult = false;
//        boolean result = instance.delete(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Article article = null;
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        boolean expResult = false;
//        boolean result = instance.update(article);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testGet() {
//        System.out.println("get");
//        Integer id = null;
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        Article expResult = null;
//        Article result = instance.get(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of list method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testList_Integer() {
//        System.out.println("list");
//        Integer userId = null;
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.list(userId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of list method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testList_0args() {
//        System.out.println("list");
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.list();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listLikeTitleOrContent method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testListLikeTitleOrContent() {
//        System.out.println("listLikeTitleOrContent");
//        String keyword = "";
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.listLikeTitleOrContent(keyword);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listByCategory method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testListByCategory() {
//        System.out.println("listByCategory");
//        Integer userId = null;
//        String category = "";
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.listByCategory(userId, category);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listLikeTag method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testListLikeTag() {
//        System.out.println("listLikeTag");
//        Integer userId = null;
//        String tag = "";
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.listLikeTag(userId, tag);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listByCreateYearMonth method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testListByCreateYearMonth() {
//        System.out.println("listByCreateYearMonth");
//        Integer userId = null;
//        Date date = null;
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.listByCreateYearMonth(userId, date);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of sortByUpdate method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testSortByUpdate() {
//        System.out.println("sortByUpdate");
//        List<Article> source = null;
//        boolean isDesc = false;
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.sortByUpdate(source, isDesc);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of screenByAuth method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testScreenByAuth() {
//        System.out.println("screenByAuth");
//        List<Article> source = null;
//        ArticleAuth articleAuth = null;
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.screenByAuth(source, articleAuth);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of categorizedByMonth method, of class ArticleServiceImpl.
//     */
//    @Test
//    public void testCategorizedByMonth() {
//        System.out.println("categorizedByMonth");
//        List<Article> source = null;
//        ArticleServiceImpl instance = new ArticleServiceImpl();
//        TreeMap<Date, List<Article>> expResult = null;
//        TreeMap<Date, List<Article>> result = instance.categorizedByMonth(source);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
