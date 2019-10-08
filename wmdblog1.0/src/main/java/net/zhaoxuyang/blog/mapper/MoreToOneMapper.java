    /*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 

package net.zhaoxuyang.blog.mapper;

import java.util.List;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.ArticleListItem;

/**
 *
 * @author zhaoxuyang
 */
public interface MoreToOneMapper {
    public ArticleListItem getArticleListItem(Integer articleId);
    public List<ArticleListItem> listArticleItem();
    public List<ArticleListItem> listIndexTypeItem(Article article);
    public List<ArticleListItem> listLikeTitleOrContent(Article article);
    
}
