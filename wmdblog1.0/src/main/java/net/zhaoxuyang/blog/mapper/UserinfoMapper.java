/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
package net.zhaoxuyang.blog.mapper;

import java.util.List;
import net.zhaoxuyang.blog.model.UserInfo;
import org.springframework.stereotype.Repository;

/**
 *
 * @author maoyufeng
 */
@Repository
public interface UserinfoMapper {

    public List<UserInfo> list();

    public int insert(UserInfo user);

    public int update(UserInfo user);

    public int delete(UserInfo user);

    public UserInfo selectByUserId(Integer id);

}
