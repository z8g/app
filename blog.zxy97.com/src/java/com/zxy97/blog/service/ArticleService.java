package com.zxy97.blog.service;

import com.zxy97.blog.database.MyBatisUtil;
import com.zxy97.blog.mapper.ArticleMapper;
import com.zxy97.blog.model.Article;
import com.zxy97.blog.model.EnumArticleAuth;
import com.zxy97.blog.util.string.GetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class ArticleService {

    public static void main(String[] args) {
//        Article article = new Article(1,"biaoti", "aas", EnumArticleAuth.PUBLIC, "ass", "asas", "asas");
//        System.out.println(articleUpdate(article));

        Article articleInsert = new Article("biaoti", "aas", EnumArticleAuth.PUBLIC, "ass", "asas", "zhaoxuyang");
        System.out.println(articleInsert(articleInsert));

//        Article articleDelete = new Article(2,"biaoti", "aas", EnumArticleAuth.PUBLIC, "ass", "asas", "asas");
//        System.out.println(articleRecycle(articleDelete));
        
        //System.out.println(ListAllArticlesByAuthorUsername("asas"));
        
        //System.out.println(ArticleService.getArticleById(4));
//        String str ="abc";
//        Integer id = Integer.valueOf(str);
//        System.out.println(id);
        
    }

    /**
     * 写新文章
     *
     * @param article
     * @return 写新文章
     */
    public static boolean articleInsert(Article article) {
        article.setGmtCreate(GetDateTime.getNow());//创建时间
        article.setNumComment(0);//评论数
        article.setNumRead(0);//阅读数

        boolean isSuccess;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
                mapper.articleInsert(article);
                sqlSession.commit();
                isSuccess = true;
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 修改文章
     *
     * @param article
     * @return
     */
    public static boolean articleUpdate(Article article) {
        article.setGmtUpdate(GetDateTime.getNow());//修改时间

        boolean isSuccess;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
                mapper.articleUpdate(article);
                sqlSession.commit();
                isSuccess = true;
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();

                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 通过文章ID将文章彻底删除
     *
     * @param id
     * @return
     */
    public static boolean articleDeleteById(Integer id) {
        boolean isSuccess;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
                mapper.articleDeleteById(id);
                sqlSession.commit();
                isSuccess = true;
            } catch (Exception e) {
                sqlSession.rollback();
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 将文章放到回收站
     *
     * @param article
     * @return
     */
    public static boolean articleRecycle(Article article) {
        article.setGmtDelete(GetDateTime.getNow());

        boolean isSuccess;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
                mapper.articleRecycle(article);
                sqlSession.commit();
                isSuccess = true;
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();

                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 将文章从回收站取出
     *
     * @param id
     * @return
     */
    public static boolean articleRecover(Integer id) {
        boolean isSuccess;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
                mapper.articleRecover(id);
                sqlSession.commit();
                isSuccess = true;
            } catch (Exception e) {
                sqlSession.rollback();
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 根据作者的用户名列出所有不在回收站中的文章
     *
     * @param authorUsername
     * @return
     */
    public static List<Article> ListAllArticlesByAuthorUsername(String authorUsername) {
        List<Article> list = new ArrayList<>();
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
                list = mapper.ListArticlesByAuthorUsername(authorUsername);
                sqlSession.commit();
            } catch (Exception e) {
                e.printStackTrace();
                sqlSession.rollback();
            }
        }
        return list;
    }
    
    public static Article getArticleById(Integer id){
        Article article = new Article();
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
                article = mapper.getArticleById(id);
                sqlSession.commit();
            } catch (Exception e) {
                e.printStackTrace();
                sqlSession.rollback();
            }
        }
        return article;
    }

}
