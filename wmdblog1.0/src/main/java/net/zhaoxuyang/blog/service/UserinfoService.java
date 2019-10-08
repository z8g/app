/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.service;

import net.zhaoxuyang.blog.model.UserInfo;

/**
 *
 * @author maoyufeng
 */
public interface UserinfoService {

    /**
     * 向用户信息表插入新的信息
     * 
     * @param info 用户信息类
     * @return 
     */
    public boolean insertInfo(UserInfo info);
    
    /**
     * 对用户信息表进行数据更新操作
     * 
     * @param info 用户信息类
     * @return 
     */
    
    public boolean update(UserInfo info);
    
    /**
     * 根据用户id来查询用户信息表中的信息
     * 
     * @param id 用户id
     * @return 
     */
    public UserInfo selectByUserId(Integer id);
    
    public boolean ifExist(String username);

    


}
