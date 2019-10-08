/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.exception;

/**
 * 博客 - 文章 - 异常
 *
 * @author zhaoxuyang
 */
public class BlogArticleException extends BlogException {

    public BlogArticleException(String message) {
        super(message);
    }
}
