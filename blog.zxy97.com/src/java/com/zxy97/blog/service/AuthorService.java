package com.zxy97.blog.service;

import com.zxy97.blog.database.MyBatisUtil;
import com.zxy97.blog.mapper.AuthorMapper;
import com.zxy97.blog.model.Article;
import com.zxy97.blog.model.ArticlePackageVO;
import com.zxy97.blog.model.Author;
import com.zxy97.blog.model.EnumArticleAuth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.session.SqlSession;

public class AuthorService {

    public static void main(String[]args){
        Author user = new Author();
        user.setUsername("www");
        System.out.println(listArticlePackageVO("2",null));
    }
    
    public static List<ArticlePackageVO> listArticlePackageVO(String authorUsername, Author user){
        List<ArticlePackageVO> articlePackageVOList = new ArrayList<>();//最终要返回的结果
                
        boolean isLogin = (user != null);//是否登录
        boolean isSelf = false;//被访问主页的作者是否为已登录用户
        
        Author author = AuthorService.getAuthor(authorUsername);//被访问主页的作者
        if(author == null){
            return null;//找不到被访问主页的作者则直接返回
        }
        if(isLogin){//不加判断，如果user为null会引起异常
            if(user.getUsername().equals(author.getUsername())){
                isSelf = true;
            }
        }
        List<Article> initArticleList = ArticleService.ListAllArticlesByAuthorUsername(authorUsername);//未处理的文章列表
          
        //权限控制部分
        List<Article> articleList = new ArrayList<>();
        for(Article article : initArticleList){
            if(isSelf){//主页是自己的，直接添加
                articleList.add(article);
            } else {//主页不是自己的
                int auth = article.getAuth();
                if(isLogin){//用户已登录
                    if(auth!=EnumArticleAuth.PRIVATE){//文章“仅自己可见”，也添加
                        articleList.add(article);
                    }
                }else if(auth == EnumArticleAuth.PUBLIC){//用户未登录
                    //文章的“公开可见”，也添加
                    articleList.add(article);
                }
            }
        }
        
        
        Set<String> typeList = new HashSet<>();//文章类别列表，会添加成不重复的
        for(Article article : articleList) {
            typeList.add(article.getType());
        }
        
        //按照类别打包
        int i = 1;//类别ID
        for (String type : typeList) {
            
            ArticlePackageVO articlePackageVO = new ArticlePackageVO();
            List<Article> articleSameTypeList = new ArrayList<>();//每个类别下有一系列文章
            
            for (Article article : articleList) {
                if(type.equals(article.getType())){
                    articleSameTypeList.add(article);
                }
            }
            
            articlePackageVO.setTypeId(i++);
            articlePackageVO.setType(type);
            articlePackageVO.setArticleVOList(articleSameTypeList);
            
            articlePackageVOList.add(articlePackageVO);
        }

        return articlePackageVOList;
    }
    
    public static Author getAuthor(String username) {
        Author a = new Author();
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                AuthorMapper mapper = sqlSession.getMapper(AuthorMapper.class);
                a = mapper.getAuthor(username);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
        return a;
    }
}
