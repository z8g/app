/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.mapper;

import java.util.Map;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wyx
 */
@Repository
public interface VisitMapper {
    public int insertVisitUserHome(Map map);
    public int insertVisitArticle(Map map);
    public int countVisitUserHome(User user);
    public int countVisitArticle(Article article);
}
