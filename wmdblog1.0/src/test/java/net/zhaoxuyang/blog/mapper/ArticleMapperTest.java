///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.zhaoxuyang.blog.mapper;
//
//import java.util.List;
//import net.zhaoxuyang.blog.model.Article;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author zhaoxuyang
// */
//public class ArticleMapperTest {
//    
//    public ArticleMapperTest() {
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
//      @Autowired
//    private UserMapper userMapper;
//      @Autowired
//    private ArticleMapper articleMapper;
//    
//    /**
//     * Test of getById method, of class ArticleMapper.
//     */
//    @Test
//    public void testGetById() {
//        System.out.println("getById");
//        Integer id = 1;
//        
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listAnd method, of class ArticleMapper.
//     */
//    @Test
//    public void testListAnd() {
//        System.out.println("listAnd");
//        Article article = null;
//        ArticleMapper instance = new ArticleMapperImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.listAnd(article);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listOr method, of class ArticleMapper.
//     */
//    @Test
//    public void testListOr() {
//        System.out.println("listOr");
//        Article article = null;
//        ArticleMapper instance = new ArticleMapperImpl();
//        List<Article> expResult = null;
//        List<Article> result = instance.listOr(article);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of insert method, of class ArticleMapper.
//     */
//    @Test
//    public void testInsert() {
//        System.out.println("insert");
//        Article article = null;
//        ArticleMapper instance = new ArticleMapperImpl();
//        int expResult = 0;
//        int result = instance.insert(article);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update method, of class ArticleMapper.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        Article article = null;
//        ArticleMapper instance = new ArticleMapperImpl();
//        int expResult = 0;
//        int result = instance.update(article);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete method, of class ArticleMapper.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        Article article = null;
//        ArticleMapper instance = new ArticleMapperImpl();
//        int expResult = 0;
//        int result = instance.delete(article);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    public class ArticleMapperImpl implements ArticleMapper {
//
//        public Article getById(Integer id) {
//            return null;
//        }
//
//        public List<Article> listAnd(Article article) {
//            return null;
//        }
//
//        public List<Article> listOr(Article article) {
//            return null;
//        }
//
//        public int insert(Article article) {
//            return 0;
//        }
//
//        public int update(Article article) {
//            return 0;
//        }
//
//        public int delete(Article article) {
//            return 0;
//        }
//
//        @Override
//        public Article getByUsername(String username) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//    }
//    
//}
