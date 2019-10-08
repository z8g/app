package com.zxy97.blog.controller;

import com.zxy97.blog.model.ArticlePackageVO;
import com.zxy97.blog.model.Author;
import com.zxy97.blog.service.AuthorService;
import com.zxy97.blog.service.LoginService;
import com.zxy97.blog.service.RegisterService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorController {
    
    @RequestMapping(value="{username}")
    public ModelAndView authorHome(@PathVariable String username,HttpSession session){
        ModelAndView mv = new ModelAndView();
        Author author = AuthorService.getAuthor(username);
        Author user = (Author)session.getAttribute("user");
        boolean isLogin = (user != null);//是否登录
        boolean isSelf = false;//被访问主页的作者是否为已登录用户
        if(author == null){
            mv.setViewName("redirect:/author");
            return mv;//找不到被访问主页的作者则直接返回
        }
        if(isLogin){//不加判断，如果user为null会引起异常
            if(user.getUsername()!=null){
                if(user.getUsername().equals(author.getUsername())){
                    isSelf = true;
                }
            }
        }
        List<ArticlePackageVO> articlePackageVOList = AuthorService.listArticlePackageVO(username,user);
        session.setAttribute("author", author);
        session.setAttribute("user", user);
        session.setAttribute("isSelf", isSelf);
        session.setAttribute("articlePackageVOList", articlePackageVOList);
        mv.setViewName("Index");
        
        return mv;
    }
    
    @RequestMapping(value="author/login")
    public ModelAndView login(@RequestParam String login_name,@RequestParam String login_password, HttpSession session){
        ModelAndView mv = new ModelAndView();
        Author user = LoginService.login(login_name, login_password);
        boolean isLogin = (user != null);
        if(isLogin){
            session.setAttribute("user", user);
            mv.setViewName("redirect:/"+login_name);
        } else {
            session.setAttribute("msg", "登录失败");
            mv.setViewName("LoginForm");
        }
        return mv;
    }    
    
    @RequestMapping(value="author/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView mv = new ModelAndView();
        session.invalidate();
        mv.setViewName("Index");
        return mv;
    }
    
    @RequestMapping(value="author/register")
    public ModelAndView register(@RequestParam String reg_name,@RequestParam String reg_password,HttpSession session){
        ModelAndView mv = new ModelAndView();
        boolean isReg = RegisterService.register(reg_name, reg_password);
        if(isReg){
            Author user = new Author();
            user.setUsername(reg_name);
            user.setPassword(reg_password);
            session.setAttribute("user", user);
            mv.setViewName("redirect:/"+reg_name);
        } else {
            session.setAttribute("msg", "注册失败");
            mv.setViewName("RegisterForm");
        }
        return mv;
    }    
    
    @RequestMapping(value="author/settings")
    public String settings(){
        return "SettingsForm";
    }    
    @RequestMapping(value="author/message")
    public String message(){
        return "MessageForm";
    } 
    
    @RequestMapping(value="author/search")
    public ModelAndView search(@RequestParam String username){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/" + username);
        return mv;
    }

}
