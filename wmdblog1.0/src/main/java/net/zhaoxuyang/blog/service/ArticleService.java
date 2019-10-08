/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.service;

import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.List;
import java.util.Set;
import net.zhaoxuyang.blog.model.Article;

/**
 *
 * @author zhaoxuyang
 */
public interface ArticleService {

    /**
     * 插入文章
     * 
     * @param article 文章信息
     * @return 是否插入成功
     */
    public boolean insert(Article article);//插入

    public boolean delete(Integer id);//删除

    public boolean update(Article article);//修改

    public List<Article> list();//列出所有用户的所有文章

    public List<Article> list(Integer userId);//列出一个用户的所有文章

    public Article get(Integer id, Integer auth);//根据文章ID获取一个用户的一篇文章

    public PageInfo list(String username, Integer auth, Integer pageCurr, Integer pageSize);//列出一个用户的所有文章

    public PageInfo listAll(Integer pageCurr, Integer pageSize);//列出所有用户的所有文章

    //public List<Article> listLikeTitle(String title);//全局搜索，根据标题查找所有用户的所有文章
    //public List<Article> listLikeTitleOrContent(String content);//全局搜索，根据内容查找所有用户的所有文章
    public PageInfo listLikeTitleOrContent(String keyword, Integer auth, Integer pageCurr, Integer pageSize);//全局搜索，根据标题或内容查找所有用户的所有文章

    public PageInfo listByCategory(Integer userId, String category, Integer auth,Integer pageCurr, Integer pageSize);//根据用户分类查找一个用户的所有文章

    public PageInfo listLikeTag(Integer userId, String tag, Integer auth, Integer pageCurr, Integer pageSize);//根据标签查找一个用户的所有文章
    
    public PageInfo listByType(String type,Integer pageCurr, Integer pageSize);//根据系统分类查找所有公开文章

    public PageInfo listByCreateYearMonth(Integer userId, Date date, Integer auth, Integer pageCurr, Integer pageSize);//根据月份列出一个用户所有的文章

    public Set<String> listAllCategories(String username);

}
