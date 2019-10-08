/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
*/
package net.zhaoxuyang.blog.service.impl;

import net.zhaoxuyang.blog.mapper.UserinfoMapper;
import net.zhaoxuyang.blog.model.UserInfo;
import net.zhaoxuyang.blog.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maoyufeng
 */
@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    UserinfoMapper userinfoMapper;
   

    @Override
    public boolean insertInfo(UserInfo info) {

        int n = userinfoMapper.insert(info);

        boolean result = n > 0;

        return result;
    }

    @Override
    public boolean ifExist(String username) {
        return false;

    }

    @Override
    public UserInfo selectByUserId(Integer id) {
        UserInfo usr = userinfoMapper.selectByUserId(id);
        return usr;
    }

    @Override
    public boolean update(UserInfo info) {
        int n = userinfoMapper.update(info);
        boolean result = n > 0;

        return result;
    }

 
}
