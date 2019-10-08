/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
package net.zhaoxuyang.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.zhaoxuyang.blog.mapper.ArticleMapper;
import net.zhaoxuyang.blog.mapper.MoreToOneMapper;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.ArticleListItem;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author zhaoxuyang
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    MoreToOneMapper moreToOneMapper;

    @Override
    public boolean insert(Article article) {
        article.setId(0);
        Date now = new Date();
        article.setGmtCreate(now);
        article.setGmtUpdate(now);
        article.createSummary();
        if (article.getAuth() == null) {
            article.setAuth(0);
        }
        int n = articleMapper.insert(article);
        return n > 0;
    }

    @Override
    public boolean delete(Integer id) {

        Article article = new Article();
        article.setId(id);
        int n = articleMapper.delete(article);
        return n > 0;
    }

    @Override
    public boolean update(Article article) {
        Date now = new Date();
        article.setGmtUpdate(now);
        article.createSummary();
        if (article.getAuth() == null) {
            article.setAuth(0);
        }
        int n = articleMapper.update(article);
        return n > 0;
    }

    @Override
    public Article get(Integer id, Integer auth) {
        Article input = new Article();
        input.setId(id);
        input.setAuth(auth);
        return articleMapper.getById(input);
    }

    @Override
    public List<Article> list(Integer userId) {
        Article article = new Article();
        article.setUserId(userId);
        return articleMapper.listAnd(article);
    }

    @Override
    public List<Article> list() {
        return articleMapper.listAnd(null);
    }

    @Override
    public PageInfo listLikeTitleOrContent(
            String keyword,
            Integer auth,
            Integer pageCurr,
            Integer pageSize) {

        Article article = new Article();
        article.setTitle(keyword);
        article.setMarkdown(keyword);
        article.setAuth(auth);

        PageHelper.startPage(pageCurr, pageSize);
        List<ArticleListItem> list = moreToOneMapper.listLikeTitleOrContent(article);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        //System.out.println(pageInfo);
        return pageInfo;
    }

    @Override
    public PageInfo listByCategory(
            Integer userId,
            String category,
            Integer auth,
            Integer pageCurr,
            Integer pageSize) {
        PageHelper.startPage(pageCurr, pageSize);
        Article article = new Article();
        article.setUserId(userId);
        article.setCategory(category);
        List<Article> list = articleMapper.listAnd(article);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override

    public PageInfo listLikeTag(
            Integer userId,
            String tag,
            Integer auth,
            Integer pageCurr,
            Integer pageSize) {
        PageHelper.startPage(pageCurr, pageSize);
        Article article = new Article();
        article.setUserId(userId);
        article.setTags(tag);
        List<Article> list = articleMapper.listAnd(article);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo listByCreateYearMonth(
            Integer userId,
            Date date,
            Integer auth,
            Integer pageCurr,
            Integer pageSize) {
        PageHelper.startPage(pageCurr, pageSize);
        Article article = new Article();
        article.setUserId(userId);
        article.setGmtCreate(date);
        List<Article> list = articleMapper.listAnd(article);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo list(
            String username,
            Integer auth,
            Integer pageCurr,
            Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("auth", auth);
        PageHelper.startPage(pageCurr, pageSize);
        List<Article> list = articleMapper.listByUsername(map);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     *
     * @param pageCurr
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo listAll(Integer pageCurr, Integer pageSize) {
        PageHelper.startPage(pageCurr, pageSize);
        List<ArticleListItem> list = moreToOneMapper.listArticleItem();
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        //System.out.println(pageInfo);
        return pageInfo;
    }

    @Override
    public PageInfo listByType(
            String type,
            Integer pageCurr,
            Integer pageSize) {

        PageHelper.startPage(pageCurr, pageSize);
        Article article = new Article();
        article.setType(type);
        article.setAuth(0);
        List<ArticleListItem> list = moreToOneMapper.listIndexTypeItem(article);
        //用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;

    }

    @Override
    public Set<String> listAllCategories(String username) {
        User select = new User();
        select.setUsername(username);
        Set<String> set = articleMapper.listAllCategories(select);
        return set;

    }

}
