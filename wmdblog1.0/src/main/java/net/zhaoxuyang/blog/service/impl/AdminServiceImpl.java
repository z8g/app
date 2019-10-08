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
import java.lang.invoke.MethodHandles;
import java.util.List;
import net.zhaoxuyang.blog.mapper.ArticleMapper;
import net.zhaoxuyang.blog.mapper.UserMapper;
import net.zhaoxuyang.blog.mapper.UserinfoMapper;
import net.zhaoxuyang.blog.mapper.AdminMapper;
import net.zhaoxuyang.blog.model.AdminArticleComment;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.model.UserInfo;
import net.zhaoxuyang.blog.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * login()通过验证用户输入的用户名和密码，与配置文件中的adminUsername和adminPassword进行对比，进行登录
 * deleteUserById()把获取到的id传入函数，进行数据库操作
 * PageInfo listUser(Integer pageCurr, Integer pageSize)采用pagehelper进行分页
 * listArticle(Integer pageCurr, Integer pageSize)采用pagehelper进行分页
 * @author wjy
 */
@Service
public class AdminServiceImpl implements AdminService{

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    @Value("${blog.admin.username}") String adminUsername;
    @Value("${blog.admin.password}") String adminPassword;
   
    @Autowired ArticleMapper articleMapper;
    @Autowired UserMapper userMapper;
    @Autowired UserinfoMapper userinfoMapper;
    @Autowired AdminMapper adminMapper;
    
    @Override
    public boolean login(String username, String password) {
        LOG.info("public boolean login(String username, String password)");
        LOG.info("username="+ username);
        LOG.info("password="+ password);
        
        if(username==null || password==null){
            return false;
        }
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    @Override
    public boolean deleteUserById(Integer id) {
        LOG.info("public boolean delete(Integer id)");
        LOG.info("id=" + id);
        
        UserInfo userinfo = new UserInfo();
        userinfo.setUser_id(id);
        int n = userinfoMapper.delete(userinfo);
        LOG.info("22"+userinfo.toString());
        User user = new User();
        user.setId(id);
        LOG.info("33"+userinfo.toString());
        int n2 = userMapper.delete(user);
        return (n > 0 &&n2 > 0);
    }


    @Override
    public boolean deleteArticleById(Integer id) {

        Article article = new Article();
        article.setId(id);
        int n = articleMapper.delete(article);
        
        
        AdminArticleComment adminArticleComment = new AdminArticleComment();
        adminArticleComment.setArticle_id(id);
        int n2 = adminMapper.deleteArticleComment(adminArticleComment);

//        AdminArticleCommentReply adminArticleCommentReply = new AdminArticleCommentReply();
//        adminArticleCommentReply.setId(id);
//        int n3 = adminMapper.deleteArticleCommentReply(adminArticleCommentReply);
  
//        AdminVisitArticle adminVisitArticle = new AdminVisitArticle();
//        adminVisitArticle.setArticle_id(id);
//        int n4 = adminMapper.deleteVisitArticle(adminVisitArticle);
        
        return (n*n2)>0;
    }
    @Override
    public List<User> listUser() {
        return userMapper.list();
    }

    @Override
    public List<Article> listArticle() {
        return articleMapper.listAnd(null);
    }

    @Override
    public PageInfo listUser(Integer pageCurr, Integer pageSize) {
        PageHelper.startPage(pageCurr, pageSize);
        List<User> userList =userMapper.list();
        PageInfo pageInfo = new PageInfo(userList);
        return pageInfo;
    }
    
    @Override
    public PageInfo listArticle(Integer pageCurr, Integer pageSize) {
        PageHelper.startPage(pageCurr, pageSize);
        List<Article> articleList;
        articleList = articleMapper.listAnd(null);
        PageInfo pageInfo = new PageInfo(articleList);
        return pageInfo;
    }
}
