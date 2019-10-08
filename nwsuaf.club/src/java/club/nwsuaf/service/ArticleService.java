package club.nwsuaf.service;

import club.nwsuaf.model.Article;
import java.util.List;

/**
 * @名称 逻辑服务 - 文章
 * @作者 赵栩旸
 */
public interface ArticleService {
    
    /**
     * 系统管理员或操作员 - 获取所有文章
     * @return 文章列表
     */
    public List<Article> list();
    
    /**
     * 系统管理员 - 根据文章id删除文章
     * @param id 文章id
     * @return 删除成功为true
     */
    public boolean administratorDelete(Integer id);
    
    /**
     * 游客 - 根据类型列出所有文章
     * @param type
     * @return 文章列表
     */
    public List<Article> listByType(String type);
    
    /**
     * 游客 - 根据文章id获取文章信息
     * @param id 文章id
     * @return 文章信息
     */
    public Article get(Integer id);
    
    /**
     * 操作员 - 发布文章
     * @param article 提交的表单（不含文章id）
     * @return 发布成功为true
     */
    public boolean operatorInsert(Article article);
    
    /**
     * 操作员 - 修改文章
     * @param article 提交的表单（含文章id）
     * @return 修改成功为true
     */
    public boolean operatorUpdate(Article article);
    
    /**
     * 操作员 - 删除文章
     * @param article 文章id和操作员用户名
     * @return 修改成功为true
     */
    public boolean operatorDelete(Article article);
    
}
