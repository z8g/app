package pan.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import pan.mapper.UserMapper;
import pan.model.User;
import pan.service.UserService;
import pan.util.DateUtil;
import pan.util.MyBatisUtil;

/**
 * @名称 用户服务类，为控制类提供与用户有关的数据库操作
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
@Service
public class UserServiceImpl implements UserService{

    public static void main(String[] args) {

//显示用户列表测试
//        UserMapper userService = new UserServiceImpl();
//        List<User> userList = userService.listUsers();
//        for(User user: userList){
//            System.out.println(user);
//        }
//根据ID获取用户测试
//        UserMapper userService = new UserServiceImpl();
//        User user = userService.getUserById(8);
//       
//            System.out.println(user);
        //2019-01-01 15:12测试失败，
        //### Error querying database.  Cause: java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        //将UserMapper.xml中id为getUserById的标签的
        //parameterType="java.lang.String"
        //改为parameterType="java.lang.Integer"后测试成功，输出：
        //User{id=8, username=zhaoxuyang, password=zhaoxuyang, email=zhaoxuyang@zxy97.com, gmtCreate=2019-01-01 15:04:57}
//
//注册查重测试
//        
//        UserMapper userService = new UserServiceImpl();
//        String username = userService.userRegCheck("asas");
//        System.out.println(username);
//
//注册测试
//        UserMapper userService = new UserServiceImpl();
//        User user = new User();
//        user.setEmail("1395359719@qq.com");
//        user.setUsername("zhaoxuyang");
//        user.setPassword("zhaoxuyang");
//        userService.userRegister(user);
//
//登陆测试        
//        UserMapper userService = new UserServiceImpl();
//        User user = new User();
//        user.setUsername("zhaoxuyang");
//        user.setPassword("zhaoxuyang");
//        User loginUser = userService.userLogin(user);
//        System.out.println(loginUser);
    }

    public User userLogin(User user) {
        User result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            result = mapper.userLogin(user);
            sqlSession.commit();
        }
        return result;
    }

    public void userRegister(User user) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user.setGmtCreate(DateUtil.formatNow());
            mapper.userRegister(user);
            sqlSession.commit();
        }
    }

    public String userRegCheck(String username) {
        String result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            result = mapper.userRegCheck(username);
            sqlSession.commit();
        }
        return result;
    }

    public List<User> listUsers() {
        List<User> result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            result = mapper.listUsers();
            sqlSession.commit();
        }
        return result;
    }

    public User getUserById(Integer id) {
        User result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            result = mapper.getUserById(id);
            sqlSession.commit();
        }
        return result;
    }

    public User getUserByName(String username) {
        User result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            result = mapper.getUserByName(username);
            sqlSession.commit();
        }
        return result;
    }

}
