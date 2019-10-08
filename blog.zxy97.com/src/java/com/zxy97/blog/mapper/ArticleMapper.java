package com.zxy97.blog.mapper;

import com.zxy97.blog.model.Article;
import java.util.List;

public interface ArticleMapper {
    public void articleInsert(Article article);//新文章
    public void articleUpdate(Article article);//修改文章
    public void articleRecycle(Article article);//放回收站
    public void articleRecover(Integer id);//从回收站中恢复
    public void articleDeleteById(Integer id);//彻底删除
    public List<Article> ListArticlesByAuthorUsername(String authorUsername);//根据作者用户名列出所有不在回收站中的文章
    public void articleAddReadNumber();//阅读数+1
    public Article getArticleById(Integer id);
}
