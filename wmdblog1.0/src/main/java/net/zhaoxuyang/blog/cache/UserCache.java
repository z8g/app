/*
 __        ____  __ ____    ____  _     ___   ____ 
 \ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
 \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
 \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
package net.zhaoxuyang.blog.cache;

import com.github.pagehelper.PageInfo;
import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.model.UserInfo;
import net.zhaoxuyang.blog.service.ArticleService;
import net.zhaoxuyang.blog.service.NoticeService;
import net.zhaoxuyang.blog.service.UserService;
import net.zhaoxuyang.blog.service.UserinfoService;
import net.zhaoxuyang.blog.service.VisitService;
import net.zhaoxuyang.util.DatetimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 - 缓存类
 *
 * @author zhaoxuyang
 */
@Service
@CacheConfig(cacheNames = "UserHome")
public class UserCache {

    final static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    VisitService visitService;

    @Autowired
    NoticeService noticeService;

    
    @Autowired UserinfoService userinfoService;
    public Map<String, Object> getUserHomeMap(String username) {
        Map<String, Object> result = new HashMap<>();
        /**
         * 作者信息
         */
        User author = userService.getByUsername(username);
        result.put("author", author);
        UserInfo userInfo = userinfoService.selectByUserId(author.getId());
        if(userInfo!=null)
        {
                   result.put("nickimg", userInfo.getNickimg());
        }
  

        
        

        List<Article> articleList = listAllArticle(author.getId());//该用户所有文章

        /**
         * 统计文章总数
         */
        result.put("articleListSize", articleList.size());

        /**
         * 用户访问量
         */
        int countUserHome = visitService.countUserHome(author.getId());
        result.put("visitorListSize", countUserHome);

        /**
         * 用户关注量
         */
        int countNotice = noticeService.countNotice(author.getId());
        result.put("noticeListSize", countNotice);

        /**
         * 用户粉丝量
         */
        int countFans = noticeService.countFan(author.getId());
        result.put("fansListSize", countFans);

        /**
         * 标签 - 不要转成labmda表达式
         */
        Set<String> tagSet = getTagSet(articleList);
        result.put("tagSet", tagSet);

        /**
         * 分类 - 不要转成labmda表达式
         */
        Map<String, Integer> categoryMap = getCategoryMap(articleList);
        result.put("categoryMap", categoryMap);

        /**
         * 归档 - 不要转成labmda表达式
         */
        Map<String, Integer> yearMonthMap = getYearMonthMap(articleList);
        result.put("yearMonthMap", yearMonthMap);

        /**
         * 评论数
         */
        /**
         * 关注/粉丝
         */
        /**
         * 文章列表，分页信息 - 已在AuthorArticleController中实现
         */
        /**
         * 最近发表
         */
        PageInfo lastPostPageInfo = articleService.list(username, 0, LAST_POST_DEFAULT_CURR, LAST_POST_DEFAULT_SIZE);
        result.put("lastPostPageInfo", lastPostPageInfo);

        return result;
    }

    @CachePut
    public List<Article> listAllArticle(Integer id) {
        return articleService.list(id);
    }

    @CachePut
    public Set<String> getTagSet(List<Article> articleList) {
        Set<String> tagSet = new TreeSet<>();
        for (Article article : articleList) {
            String tags = article.getTags();
            if (tags != null) {
                String[] tagArray = tags.split("\\|");
                for (String tag : tagArray) {
                    String trimTag = tag.trim();
                    if (trimTag.length() > 0) {
                        tagSet.add(tag);
                    }
                }
            }
        }
        return tagSet;
    }

    @CachePut
    public Set<String> getCategories(Integer userId) {
        List<Article> list = listAllArticle(userId);
        Map<String, Integer> categoryMap = getCategoryMap(list);
        Set<String> result = categoryMap.keySet();
        return result;
    }

    @CachePut
    public Map<String, Integer> getCategoryMap(List<Article> articleList) {
        Map<String, Integer> categoryMap = new HashMap<>();
        for (Article article : articleList) {
            String key = article.getCategory();
            if (categoryMap.containsKey(key)) {
                Integer value = categoryMap.get(key);
                categoryMap.put(key, value + 1);
            } else {
                categoryMap.put(key, 1);
            }
        }
        return categoryMap;
    }

    @CachePut
    public Map<String, Integer> getYearMonthMap(List<Article> articleList) {
        Map<String, Integer> yearMonthMap = new HashMap<>();
        for (Article article : articleList) {
            Date gmtUpdate = article.getGmtUpdate();
            String key = DatetimeUtil.formatDate(gmtUpdate);
            if (yearMonthMap.containsKey(key)) {
                Integer value = yearMonthMap.get(key);
                yearMonthMap.put(key, value + 1);
            } else {
                yearMonthMap.put(key, 1);
            }
        }
        return yearMonthMap;
    }

    private static final int LAST_POST_DEFAULT_CURR = 1;//最忌发表，初始页码
    private static final int LAST_POST_DEFAULT_SIZE = 3;//最近发表，每页几篇

}
