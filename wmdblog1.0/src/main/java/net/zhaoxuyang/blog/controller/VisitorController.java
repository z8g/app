/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.controller;

import java.util.List;
import net.zhaoxuyang.blog.exception.BlogException;
import net.zhaoxuyang.blog.exception.BlogUserNoPermissionException;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.service.ArticleService;
import net.zhaoxuyang.blog.service.EmailService;
import net.zhaoxuyang.blog.service.UserService;
import net.zhaoxuyang.blog.service.UserinfoService;
import net.zhaoxuyang.blog.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 访客 - 控制器类
 *
 * @author 赵栩旸
 */
@Controller
public class VisitorController {
   Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    VerifyService verifyService;

    @Autowired
    UserinfoService infoService;
    
    @Autowired
    ArticleService articleService;


     @RequestMapping("")
    public String index(){
        LOG.info("public String index");
        return "redirect:/index";
    }
    
    @RequestMapping("error.form")
    public ModelAndView errorForm() throws BlogException{
        throw new BlogUserNoPermissionException("我抛了一个用户找不到的异常，这是返回到客户端的消息<i class=\"fa fa-bell\"></i>");
    }
    
    @RequestMapping("/{username}")
    public String userHome(
            @PathVariable String username,
            Model model
    ) {
        LOG.info("public String userHome");
        LOG.info("username=" + username);
        return String.format("redirect:/%s/article/list", username);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Article> listByUsername(
            @RequestParam Integer userId
    ) {
        LOG.info("public List<Article> listByUsername");
        LOG.info("userId=" + userId);
        return articleService.list(userId);
    }

    /**
     * 下面会跳转到 classpath:/templates/home.html
     *
     * @param name
     * @return
     */
    @RequestMapping("/html/{name}")
    public String goHome(@PathVariable String name) {
        LOG.info("public String goHome");
        LOG.info("name=" + name);
        return name + ".html";
    }

    @RequestMapping("/{username}/rss.xml")
    @ResponseBody
    public String userHomeRss(@PathVariable String username) {
        LOG.info("public String userHomeRss");
        LOG.info("username=" + username);
        /**
         * 生成RSS，显示前20条信息
         */
        return username + "RSS";
    }

}
