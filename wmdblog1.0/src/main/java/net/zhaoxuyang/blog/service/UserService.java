/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.service;

import net.zhaoxuyang.blog.model.User;
import java.util.List;

/**
 *
 * @author zhaoxuyang
 */
public interface UserService {

    /**
     * 登录
     *
     * @param usernameOrEmail 用户名或邮箱
     * @param password 
     * @return
     */
    public User login(String usernameOrEmail, String password);
    /**
     * 
     * @param user
     * @return 
     */
    public boolean register(User user);
    
    /**
     * 
     * @param id
     * @return 
     */
    public User getById(Integer id);
    public User getByUsername(String username);
    public User getByEmail(String email);
    public List<User> list();
    public List<User> list(Integer currPage, Integer pageSize);
    public boolean update(User user);
}
