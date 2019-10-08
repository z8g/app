/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.mapper;

import net.zhaoxuyang.blog.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhaoxuyang
 */
@Repository
public interface UserMapper {
    public User getAnd(User user);
    public User getByEmail(String email);

    public List<User> list();
    public int insert(User user);
    public int update(User user);
    public int delete(User user);
    public int updatePass(User user);
}
