/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.exception;

/**
 * 博客 - 文章 - 无权限 - 异常
 * @author zhaoxuyang
 */
public class BlogArticleNoPermissionException extends BlogArticleException {

    public BlogArticleNoPermissionException(String message) {
        super(message);
    }
}
