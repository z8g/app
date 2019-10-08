/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 

package net.zhaoxuyang.blog.mapper;

import net.zhaoxuyang.blog.model.UserVO;

/**
 *
 * @author zhaoxuyang
 */
public interface OneToMoreMapper {
    public UserVO getUserVO(Integer userId);
}
