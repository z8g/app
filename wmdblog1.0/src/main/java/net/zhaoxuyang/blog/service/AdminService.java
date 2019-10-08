/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.User;

/**
 * login()负责获取登录，验证登录信息
 * deleteUserById()根据用户id进行数据删除
 * listUser()获取用户列表全部信息
 * deleteArticleById()通过文章id删除文章
 * listArticle()获取文章列表
 * PageInfo listUser()分页操作
 * PageInfo listArticle()文章列表更新
 * @author wjy
 */
public interface AdminService {
    
    public static final String ADMIN_SESSION_KEY = "admin";
    public boolean login(String username,String password);
    public boolean deleteUserById(Integer id);
    public List<User> listUser();
    public boolean deleteArticleById(Integer id);
    public List<Article> listArticle();
    public PageInfo listUser(Integer pageCurr, Integer pageSize);//列出一个用户的所有文章
    public PageInfo listArticle(Integer pageCurr, Integer pageSize);

    
}
