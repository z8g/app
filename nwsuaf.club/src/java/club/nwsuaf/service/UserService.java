package club.nwsuaf.service;

import club.nwsuaf.model.User;
import java.util.List;

/**
 * @名称 逻辑服务 - 用户
 * @作者 赵栩旸
 */
public interface UserService {
    /**
     * 游客 - 用户注册
     * @param user 注册填写的表单信息
     * @return 注册成功为true
     */
    public boolean visitorRegister(User user);
    
    /**
     * 游客 - 用户登录
     * @param user 用户名和密码
     * @return 查询结果
     */
    public User visitorLogin(User user);
    
    /**
     * 游客 - 找回密码
     * 提供给EmailUtil使用，发送邮件时记得在session中设置时间间隔
     * @param email
     * @return 
     */
    public List<User> visitorFindPassword(String email);
    
    /**
     * 游客 - 访问用户主页
     * @param username
     * @return 
     */
    public User visitorGet(String username);
    
    /**
     * 系统管理员 - 列出所有用户信息
     * @return 用户列表
     */
    public List<User> AdministratorListAll();
    
    /**
     * 系统管理员 - 将用户身份变为操作员
     * @param username 用户名
     * @return 修改成功为true
     */
    public boolean AdministratorUpdateToOperator(String username); 
    
    /**
     * 系统管理员 - 将用户身份变为用户
     * @param username 用户名
     * @return 修改成功为true
     */
    public boolean AdministratorUpdateToUser(String username);
    /**
     * 系统管理员 - 根据用户名删除用户
     * @param username
     * @return 
     */
    public boolean AdministratorDelete(String username);
    
    /**
     * 用户 - 修改自己的信息
     * @param user
     * @return 
     */
    public boolean userUpdate(User user);
    
    
    /**
     * 任何人 - 获取用户信息
     * @param username
     * @return 
     */
    public User get(String username);
}
