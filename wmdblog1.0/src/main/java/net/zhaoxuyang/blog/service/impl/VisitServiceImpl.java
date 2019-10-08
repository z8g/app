/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
*/
package net.zhaoxuyang.blog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import net.zhaoxuyang.blog.mapper.VisitMapper;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    VisitMapper visitMapper;

    private static final String ARTICLE_KEY = "articleVisitSet";
    private static final String USER_HOME_KEY = "userHomeVisitSet";

    @Override
    public boolean visitArticle(HttpSession session, Article article) {
        Integer articleId = article.getId();

        Set<Integer> articleSet = (HashSet) session.getAttribute(ARTICLE_KEY);
        System.out.println("articleSet:"+articleSet);
        
        if (articleSet != null) {
            if (articleSet.contains(articleId)) {
                return false;//不增加
            }
        } else {
            articleSet = new HashSet<>();
        }
        
        //增加数据库记录
        articleSet.add(articleId);
        session.setAttribute(ARTICLE_KEY, articleSet);
        System.out.println("article--set" + articleSet);
        
        //获取用户ID
        User loginUser = (User) session.getAttribute("user");
        Integer loginUserId = (loginUser == null) ? -1 : loginUser.getId();

        //根据loginUserId 和articleId增加数据库记录
        Map<String, Object> map = new HashMap<>();
        map.put("visitorId", loginUserId);
        map.put("articleId", articleId);
        map.put("gmtCreate", new Date());

        int n = visitMapper.insertVisitArticle(map);
        return n > 0;

    }

    @Override
    public boolean visitUserHome(HttpSession session, User author) {
        //System.out.println("author" + author);
        Integer authorId = author.getId();
        //System.out.println(authorId);

        Set<Integer> authorSet = (HashSet) session.getAttribute(USER_HOME_KEY);
        //System.out.println(authorSet);
        if (authorSet != null) {
            if (authorSet.contains(authorId)) {
                return false;//不增加
            }
        } else {
            authorSet = new HashSet<>();
        }
        authorSet.add(authorId);
        session.setAttribute(USER_HOME_KEY, authorSet);
        System.out.println("author--set" + authorSet);

        //获取用户ID
        User loginUser = (User) session.getAttribute("user");
        Integer userId = (loginUser == null) ? -1 : loginUser.getId();

        //根据userId 和articleId增加数据库记录
        Map<String, Object> map = new HashMap<>();
        map.put("visitorId", userId);
        map.put("authorId", authorId);
        map.put("gmtCreate", new Date());

        int n = visitMapper.insertVisitUserHome(map);
        return n > 0;
    }

    @Override
    public int countUserHome(Integer user_id) {
        //Integer userId = user.getId();
        User user=new User();
        user.setId(user_id);    
        int result = visitMapper.countVisitUserHome(user);
        System.out.println("用户主页访问量："+result);
        return result;   
    }

    @Override
    public int countArticle(Integer article_id) {
        Article article = new Article();
        article.setId(article_id);
        int result = visitMapper.countVisitArticle(article);
        System.out.println("文章浏览量："+result);
        return result;   
    }

}
