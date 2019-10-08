/*
 __        ____  __ ____    ____  _     ___   ____ 
 \ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
 \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
 \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
package net.zhaoxuyang.blog.service.impl;

import com.github.pagehelper.PageHelper;
import java.util.Date;
import net.zhaoxuyang.blog.mapper.UserMapper;
import net.zhaoxuyang.blog.model.User;
import java.util.List;
import net.zhaoxuyang.blog.service.UserService;
import net.zhaoxuyang.util.CryptographicUtil;
import net.zhaoxuyang.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zhaoxuyang
 */
@Service
public class UserServiceImpl implements UserService {

    Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String usernameOrEmail, String password) {
        LOG.info("public User login(String usernameOrEmail, String password)");
        LOG.info("usernameOrEmail=" + usernameOrEmail);
        LOG.info("password=" + password);
        User result = null;

        User input = new User();

        //判断是否为邮箱
        if (StringUtil.isEmail(usernameOrEmail)) {
            input.setEmail(usernameOrEmail);
        } else {
            input.setUsername(usernameOrEmail);
        }
        User aUser = userMapper.getAnd(input);
        String aSalt = aUser.getSalt();//从数据库中取出的盐
        String aPassword = aUser.getPassword();//从数据库中取出的密码
        String passwordSaltSha = getPasswordSaltSha(password, aSalt);//加盐

        if (aPassword.equals(passwordSaltSha)) {
            result = aUser;
        }
        return result;
    }

    @Override
    public List<User> list() {
        LOG.info(" public List<User> list()");
        List<User> result;
        result = userMapper.list();

//        PageHelper.startPage(1, 10);
//        List<Country> list = countryMapper.selectIf(1);
        return result;
    }

    @Override
    public User getByUsername(String username) {

        User result;

        User input = new User();
        input.setUsername(username);
        System.out.println("hello");
        result = userMapper.getAnd(input);

        return result;
    }

    @Override
    public User getById(Integer id) {
        LOG.info("public User getById(Integer id)");
        LOG.info("id=" + id);

        User result;

        User input = new User();
        input.setId(id);
        result = userMapper.getAnd(input);

        return result;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("public User getByEmail(String email)");
        LOG.info("email=" + email);
        return userMapper.getByEmail(email);
    }

    @Override
    public boolean register(User user) {
        boolean result;

        //密码加盐
        String salt = StringUtil.createSalt();
        String password = user.getPassword();//明文密码
        String passwordSaltSha = getPasswordSaltSha(password, salt);//加盐

        user.setSalt(salt);//存在数据表中的盐
        user.setPassword(passwordSaltSha);//存在数据表中的密码
        user.setGmtCreate(new Date());

        int n = userMapper.insert(user);
        result = n > 0;

        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result;

        String password = user.getPassword();//明文密码
        LOG.info("显示原密码： "+password);
        String username = user.getUsername();
        String email = user.getEmail();
        Integer id = user.getId();
        Integer auth = user.getAuth();

        User oldUser = getById(id);//数据库中的用户信息

        if (password != "") {
            //密码加盐
            String salt = StringUtil.createSalt();
            String passwordSaltSha = getPasswordSaltSha(password, salt);//加盐
            oldUser.setSalt(salt);
            oldUser.setPassword(passwordSaltSha);

            if (username != null) {
                oldUser.setUsername(username);
            }

            if (email != null) {
                oldUser.setEmail(email);
            }

            if (auth != null) {
                oldUser.setAuth(auth);
            }

            LOG.info("password为空修改用户：" + oldUser.toString());
            int n = userMapper.update(oldUser);
            result = n > 0;

            return result;
        } else {
            if (username != null) {
                oldUser.setUsername(username);
            }

            if (email != null) {
                oldUser.setEmail(email);
            }

            if (auth != null) {
                oldUser.setAuth(auth);
            }

            if ((username == null) && (auth == null) && (email == null)) {
                return true;
            }
            
            LOG.info("修改用户：" + oldUser.toString());
            
            int n = userMapper.update(oldUser);
            result = n > 0;
            return result;
        }
    }

    /**
     * sha(password+salt)
     *
     * @param password
     * @return sha(password+salt)
     */
    private String getPasswordSaltSha(String password, String salt) {
        String message = String.format("%s%s", password, salt);
        String result = CryptographicUtil.sha(message);
        return result;
    }

    @Override
    public List<User> list(Integer currPage, Integer pageSize) {
        LOG.info(" public List<User> list(Integer currPage, Integer pageSize)");
        LOG.info("currPage=" + currPage);
        LOG.info("pageSize=" + pageSize);

        PageHelper.startPage(currPage, pageSize);
        List<User> result;
        result = userMapper.list();
        return result;
    }

}
