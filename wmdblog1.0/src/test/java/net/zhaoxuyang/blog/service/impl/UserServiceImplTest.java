//
//package net.zhaoxuyang.blog.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import java.util.List;
//import net.zhaoxuyang.blog.mapper.UserMapper;
//import net.zhaoxuyang.blog.model.User;
//import net.zhaoxuyang.blog.service.UserService;
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
//public class UserServiceImplTest {
//
//    public UserServiceImplTest() {
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
//    @Autowired(required = true)
//    UserService userService;
//
//    /**
//     * Test of login method, of class UserServiceImpl.
//     */
//    @Test
//    public void testLogin() {
//        System.out.println("login");
//        String usernameOrEmail = "zhaoxuyang";
//        String password = "zhaoxuyang";
//
//        User result = userService.login(usernameOrEmail, password);
//        System.out.println(result);
//        assertNotNull(result);
//    }
//
//    @Autowired
//    UserMapper userMapper;
//
//    /**
//     * Test of list method, of class UserServiceImpl.
//     */
//    @Test
//    public void testList() {
////        System.out.println("list");
////        List<User> result = userService.list(1,10);
////        System.out.println(result);
////        
////        assertNotNull(result);
////        System.out.println(result.size());
////        assertEquals(result.size(),10);
//        PageHelper.startPage(1, 10);
//        List<User> list = userMapper.list();
//        
//        System.out.println(list);
//        System.out.println(list.size());
////用PageInfo对结果进行包装
//        PageInfo page = new PageInfo(list);
////测试PageInfo全部属性
////PageInfo包含了非常全面的分页属性
////        assertEquals(1, page.getPageNum());
////        assertEquals(10, page.getPageSize());
////        assertEquals(1, page.getStartRow());
////        assertEquals(10, page.getEndRow());
////        assertEquals(183, page.getTotal());
////        assertEquals(19, page.getPages());
////        assertEquals(1, page.getNavigateFirstPage());
////        assertEquals(8, page.getNavigateLastPage());
////        assertEquals(true, page.isIsFirstPage());
////        assertEquals(false, page.isIsLastPage());
////        assertEquals(false, page.isHasPreviousPage());
////        assertEquals(true, page.isHasNextPage());
//    }
//
//    /**
//     * Test of getByUsername method, of class UserServiceImpl.
//     */
//    @Test
//    public void testGetByUsername() {
//        System.out.println("getByUsername");
//        String username = "";
//        UserServiceImpl instance = new UserServiceImpl();
//        User expResult = null;
//        User result = instance.getByUsername(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getById method, of class UserServiceImpl.
//     */
//    @Test
//    public void testGetById() {
//        System.out.println("getById");
//        Integer id = null;
//        UserServiceImpl instance = new UserServiceImpl();
//        User expResult = null;
//        User result = instance.getById(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getByEmail method, of class UserServiceImpl.
//     */
//    @Test
//    public void testGetByEmail() {
//        System.out.println("getByEmail");
//        String email = "";
//        UserServiceImpl instance = new UserServiceImpl();
//        User expResult = null;
//        User result = instance.getByEmail(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of register method, of class UserServiceImpl.
//     */
//    @Test
//    public void testRegister() {
//        System.out.println("register");
//
//        int i;
//        for (i = 222; i < 10000; i++) {
//            User user = new User();
//            user.setEmail(i + "@qq.com");
//            user.setUsername("name" + i);
//            user.setPassword("password" + i);
//            userService.register(user);
//        }
//
//        assertEquals(1000, i);
//    }
//
//    /**
//     * Test of list method, of class UserServiceImpl.
//     */
//    @Test
//    public void testList_0args() {
//        System.out.println("list");
//        UserServiceImpl instance = new UserServiceImpl();
//        List<User> expResult = null;
//        List<User> result = instance.list();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of list method, of class UserServiceImpl.
//     */
//    @Test
//    public void testList_Integer_Integer() {
//        System.out.println("list");
//        Integer currPage = null;
//        Integer pageSize = null;
//        UserServiceImpl instance = new UserServiceImpl();
//        List<User> expResult = null;
//        List<User> result = instance.list(currPage, pageSize);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//}
