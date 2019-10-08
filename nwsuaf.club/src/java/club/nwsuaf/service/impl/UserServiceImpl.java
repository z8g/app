package club.nwsuaf.service.impl;

import club.nwsuaf.mapper.UserMapper;
import club.nwsuaf.model.constants.Constants;
import club.nwsuaf.model.User;
import club.nwsuaf.service.UserService;
import club.nwsuaf.util.DatetimeUtil;
import club.nwsuaf.util.MyBatisUtil;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @名称 逻辑服务实现类 - 用户
 * @作者 赵栩旸
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean visitorRegister(User user) {
        boolean result;
        //注册前的初始化
        user.setAuth(Constants.USER_AUTH_USER);//身份为普通用户
        user.setGmtCreate(DatetimeUtil.formatNow());//设置创建时间

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int n = userMapper.insert(user);
            sqlSession.commit();
            result = n > 0;
        } catch (Exception e){
            result = false;
        }
        return result;
    }

    @Override
    public User visitorLogin(User user) {
        User result = null;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.list(user);
            sqlSession.commit();
            //结果是一个用户列表，如果不为空则是登录的用户信息
            if (userList != null && !userList.isEmpty()) {
                result = userList.get(0);
            }
        }catch (Exception e){
            
        }
        return result;
    }

    @Override
    public List<User> visitorFindPassword(String email) {
        List<User> result = null;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setEmail(email);
            
            result = userMapper.list(user);
            sqlSession.commit();
            System.err.println(result);
        }
        return result;
    }

    @Override
    public User visitorGet(String username) {
        User result = null;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setUsername(username);//根据用户名查询
            user.setAuth(Constants.USER_AUTH_USER);//只能查询身份为用户的信息

            List<User> userList = userMapper.list(user);
            sqlSession.commit();
            //结果是一个用户列表，如果不为空则是登录的用户信息
            if (userList != null) {
                result = userList.get(0);
            }
        }
        return result;
    }

    @Override
    public List<User> AdministratorListAll() {
        List<User> result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            result = userMapper.list(null);
            sqlSession.commit();
        }
        return result;
    }

    @Override
    public boolean AdministratorUpdateToOperator(String username) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setUsername(username);
            user.setAuth(Constants.USER_AUTH_OPERATOR);

            int n = userMapper.update(user);
            sqlSession.commit();

            result = n > 0;
        }
        return result;
    }

    @Override
    public boolean AdministratorUpdateToUser(String username) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setUsername(username);
            user.setAuth(Constants.USER_AUTH_USER);

            int n = userMapper.update(user);
            sqlSession.commit();

            result = n > 0;
        }
        return result;
    }

    @Override
    public boolean AdministratorDelete(String username) {
        boolean result;
            User user = new User();
            user.setUsername(username);
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            int n = userMapper.update(user);
            sqlSession.commit();

            result = n > 0;
        }
        return result;
    }

    @Override
    public boolean userUpdate(User user) {
   boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            int n = userMapper.update(user);
            sqlSession.commit();

            result = n > 0;
        }
        return result;
    }

    @Override
    public User get(String username) {
        User result = null;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setUsername(username);//根据用户名查询

            List<User> userList = userMapper.list(user);
            sqlSession.commit();
            //结果是一个用户列表，如果不为空则是登录的用户信息
            if (userList != null) {
                result = userList.get(0);
            }
        }
        return result;
    }


}
