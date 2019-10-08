/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
/**
 * 用户管理文章：
 *
 */
package net.zhaoxuyang.blog.controller;

import java.lang.invoke.MethodHandles;
import javax.servlet.http.HttpSession;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.service.ArticleService;
import net.zhaoxuyang.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author zhaoxuyang
 */
//@RequestPermission(value = {Permission.AUTHOR, Permission.ADMINISTRATOR})
@Controller
@CacheConfig(cacheNames = "UserHome")
@RequestMapping("/user/article")

public class UserArticleController {

    final static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    /**
     * 新文章发表页面
     *
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String insertForm(
            Model model
    ) {
        String[] categoies = {"BigData", "Java", "Linux", "C#", "C++"};
        String[] types = {"程序人生", "Python", "Java", "C", "C++", "C#", "前端", "架构", "区块链", "数据库", "游戏开发", "移动开发", "运维", "人工智能", "云计算", "大数据", "研发管理", "物联网", "计算机基础", "其他"};
        Article article = new Article();
        model.addAttribute("categories", categoies);
        model.addAttribute("types", types);
        model.addAttribute("article", article);

        return "ArticleNew.html";
    }

    /**
     * 新文章发表事件
     *
     * @param article
     * @param session
     * @return
     */
    @PostMapping("/new")
    @ResponseBody
    public Object insertAction(
            Article article,
            HttpSession session
    ) {
        ///插入文章
        System.out.println("insert");

        User user = (User) session.getAttribute("user");
        article.setUserId(user.getId());

        boolean isInsert = articleService.insert(article);

        return isInsert;
    }

    @GetMapping("/{articleId}")
    public String updateForm(
            @PathVariable Integer articleId,
            HttpSession session,
            Model model
    ) {
        String[] categoies = {"BigData", "Java", "Linux", "C#", "C++"};
        String[] types = {"程序人生", "Python", "Java", "C", "C++", "C#", "前端", "架构", "区块链", "数据库", "游戏开发", "移动开发", "运维", "人工智能", "云计算/大数据", "研发管理", "物联网", "计算机基础", "其他"};
        Article article = articleService.get(articleId, 2);
        model.addAttribute("categories", categoies);
        model.addAttribute("types", types);
        model.addAttribute("article", article);
        return "ArticleEdit.html";
    }

    @PostMapping("/{articleId}")
    @ResponseBody
    public boolean updateAction(
            @PathVariable Integer articleId,
            Article article,
            HttpSession session
    ) {
        boolean result = false;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return result;
        }
        article.setUserId(user.getId());
        result = articleService.update(article);
        return result;
    }

    /**
     * 1. 前端上传图片（判断图片格式、过滤） 2. 后端接收文件，响应图片URL 3. 前端获取响应，将URL填入表单 4. 用户提交表单 5.
     * 后端将表单信息填入数据库
     *
     * @param authorUsername
     * @param articleId
     * @param session
     * @return
     */
    @GetMapping("/delete/{articleId}")
    @ResponseBody
    public boolean delete(
            @PathVariable Integer articleId,
            HttpSession session
    ) {
        boolean result = false;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return result;
        } 
        Article article = articleService.get(articleId, 2);
        System.out.println(article);
        if(article==null){
            return result;
        }
        if(article.getUserId().equals(user.getId())){
            result = articleService.delete(articleId);
        }
        return result;
    }

}
