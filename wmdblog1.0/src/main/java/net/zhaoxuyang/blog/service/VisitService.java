/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
*/
package net.zhaoxuyang.blog.service;

import javax.servlet.http.HttpSession;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.User;

/**
 *
 * @author wyx
 */
public interface VisitService {
    public boolean visitArticle(HttpSession session,Article article);
    public boolean visitUserHome(HttpSession session,User user);
    /**
     * 用户访问量
     */
    public int countUserHome(Integer user_id);
    /**
     * 文章访问量
     */
    public int countArticle(Integer article_id);
}
