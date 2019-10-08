/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.exception;

/**
 * 博客 - 用户 - 无权限 - 异常
 *
 * @author zhaoxuyang
 */
public class BlogUserNoPermissionException extends BlogUserException{

    public BlogUserNoPermissionException(String message) {
        super(message);
    }
}
