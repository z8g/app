package pan.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import pan.mapper.UserMapper;
import pan.model.User;
import pan.util.DateUtil;
import pan.util.MyBatisUtil;

/**
 * @名称 用户服务类，为控制类提供与用户有关的数据库操作
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public interface UserService {
    public User userLogin(User user);
    public void userRegister(User user) ;
    public String userRegCheck(String username);
    public List<User> listUsers() ;
    public User getUserById(Integer id);
    public User getUserByName(String username);
}
