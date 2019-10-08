package pan.mapper;

import java.util.List;
import pan.model.User;

/**
 * @名称 数据库操作接口-用户操作
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public interface UserMapper {
    public User userLogin(User user);//登录
    public void userRegister(User user);//注册
    public String userRegCheck(String username);//注册查重
    public List<User> listUsers();//显示所有用户信息
    public User getUserById(Integer id);//查找用户信息
    public User getUserByName(String username);//查找用户信息
}
