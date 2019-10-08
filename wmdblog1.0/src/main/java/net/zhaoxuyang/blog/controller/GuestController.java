/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
/**
 * 转发页面：
 * - 注册、登录、找回密码（GET，由前端实现在同一个视图中）
 *
 * 注册模块：
 * - 获取验证码（GET，前端Ajax获取）
 * - 检查验证码（POST，前端Ajax请求）
 * - 用户名查重（GET，前端Ajax请求）
 * - 注册事件（POST，提交事件，前端Ajax）
 * - 注册激活事件（GET，通过用户登录邮箱后点击链接来请求，完成后进行登录）
 *
 * 登录模块：
 * - 获取验证码（GET，前端Ajax）
 * - 检查验证码（POST，前端Ajax）
 * - 登录事件（GET，重定向）
 *
 * 找回密码模块：
 * - 获取验证码（GET，前端Ajax）
 * - 检查验证码（POST，前端Ajax）登录几
 * - 找回密码事件（POST，前端Ajax）
 * - 找回密码激活事件（GET，通过用户登录邮箱后点击链接来请求）
 *
 * 修改密码模块：
 * - 上传需要修改的密码(Post)
 * - 修改密码
 */
package net.zhaoxuyang.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.zhaoxuyang.blog.cache.UserCache;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.model.UserInfo;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 来宾（未登录者） - 控制器类
 *
 * @author zhaoxuyang
 */
@Controller
@RequestMapping("/guest")
public class GuestController {

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

    /**
     * 返回 【登录、注册、找回密码】 的页面
     *
     * @return
     */
    @GetMapping("/sign")
    public String signForm() {
        return "GuestSign.html";
    }

    
//                        _         _                 
//  _ __    ___    __ _  (_)  ___  | |_    ___   _ __ 
// | '__|  / _ \  / _` | | | / __| | __|  / _ \ | '__|
// | |    |  __/ | (_| | | | \__ \ | |_  |  __/ | |   
// |_|     \___|  \__, | |_| |___/  \__|  \___| |_|   
//                |___/                               

    /**
     * 游客注册事件 将激活链接发送至用户邮箱
     *
     * @param user 用户名，密码，邮箱
     * @param verifyCode
     * @param session
     * @param request HTTP请求
     * @return "true" | "false"
     */
    @PostMapping("/register")
    @ResponseBody
    public boolean registerAction(
            User user,
            String verifyCode,
            HttpSession session,
            HttpServletRequest request
    ) {
        boolean verifyCodeCheck = verifyService.checkVerify(
                verifyCode, session, VerifyService.SESSION_KEY_VERIFY_REGISTER);
        LOG.info("verifyCodeCheck:" + verifyCodeCheck);

        if (verifyCodeCheck) {
            LOG.info("register User:" + user);
            String email = user.getEmail();
            session.setAttribute("registerUser", user);

            //测试
            return userService.register(user);
            //测试

//            boolean isSend = emailService.registerActive(email, request);
//            return isSend;
        } else {
            return false;
        }
    }

    /**
     * 修改密码事件
     *
     * @param session HTTP会话
     * @param user 用户类
     * @return "true" | "false"
     */
    @PostMapping("/changePassword")
    @ResponseBody
    public boolean changePassAction(
            HttpSession session,
            User user
    ) {
//        LOG.info("public boolean rpublicegisterAction");
//        LOG.info("user=" + user);
//        LOG.info("verifyCode=" + verifyCode); 
//        LOG.info("session=" + session); 
//        LOG.info("request=" + request);

//            LOG.info("register User:" + user);
        User usr1 = (User) session.getAttribute("user");

        user.setId(usr1.getId());
        //     System.out.println("new Password:  " + user.getPassword());
        //测试

        return userService.update(user);
        //测试

    }

    /**
     * 注册查重事件
     *
     * @param username 用户名
     * @return "true" | "false"
     */
    @RequestMapping("/register/check")
    @ResponseBody
    public boolean registerCheck(String username) {
        User user = userService.getByUsername(username);
        return (user == null);
    }

    /**
     *
     * @param email
     * @return
     */
    @RequestMapping("/register/checkEmail")
    @ResponseBody
    public boolean registerCheckEmail(String email) {
        User user = userService.getByEmail(email);
        return (user == null);
    }

    /**
     * 游客注册激活事件
     *
     * @param code 注册激活码
     * @param session HTTP请求的会话
     * @return "true" | "false"
     */
    @GetMapping("/register/active")
    @ResponseBody
    public boolean registerActiveAction(String code, HttpSession session) {
        if (code == null) {
            return false;
        }
        String registerActiveCode = (String) session.getAttribute("registerActiveCode");
        boolean isActive = code.equals(registerActiveCode);
        if (isActive) {
            User registerUser = (User) session.getAttribute("registerUser");
            isActive = userService.register(registerUser);
        }
        return isActive;
    }

    /**
     * 注册页面生成验证码
     *
     * @param request HTTP请求
     * @param response 响应
     */
    @GetMapping("/register/verify")
    public void createRegisterVerify(HttpServletRequest request, HttpServletResponse response) {
        verifyService.createVerify(request, response, VerifyService.SESSION_KEY_VERIFY_REGISTER);
    }

    /**
     * 注册页面校验验证码
     *
     * @param verifyCode 验证码
     * @param session HTTP会话
     * @return
     */
    @PostMapping("/register/verify")
    @ResponseBody
    public boolean checkRegisterVerify(String verifyCode, HttpSession session) {
        return verifyService.checkVerify(verifyCode, session, VerifyService.SESSION_KEY_VERIFY_REGISTER);
    }

//  
//
//  _                   _         
// | |   ___     __ _  (_)  _ __  
// | |  / _ \   / _` | | | | '_ \ 
// | | | (_) | | (_| | | | | | | |
// |_|  \___/   \__, | |_| |_| |_|
//              |___/             
    /**
     * 登录事件
     */
    /**
     * 游客登录事件 将激活链接发送至用户邮箱
     *
     * @param usernameOrEmail
     * @param password
     * @param isRememberPassword
     * @param verifyCode
     * @param model
     * @param session
     * @param request HTTP请求
     * @return "true" | "false"
     */
    @PostMapping("/login")
    public String loginAction(
            String usernameOrEmail,
            String password,
            String verifyCode,
            boolean isRememberPassword,
            HttpSession session,
            Model model,
            HttpServletRequest request
    ) {
        LOG.info("public String loginAction");
        LOG.info("usernameOrEmail=" + usernameOrEmail);
        LOG.info("password=" + password);
        LOG.info("verifyCode=" + verifyCode);
        LOG.info("isRememberPassword=" + isRememberPassword);

        boolean verifyCodeCheck = verifyService.checkVerify(verifyCode, session, VerifyService.SESSION_KEY_VERIFY_LOGIN);
        LOG.info("verifyCodeCheck:" + verifyCodeCheck);

        if (!verifyCodeCheck) {
            return "redirect:/guest/sign";
        }
        

        User loginUser = userService.login(usernameOrEmail, password);
        LOG.info("loginUserId:" + loginUser.getId());
        UserInfo info = infoService.selectByUserId(loginUser.getId());

        LOG.info("loginUser:" + loginUser);

        if (loginUser != null) {
            LOG.info("loginUser:" + loginUser);
            session.setAttribute("user", loginUser);

            session.setAttribute("userinfo", info);
            //session.setAttribute("userinfo", infoService.selectByUserId(loginUser.getId()));
            String username = loginUser.getUsername();
            //  model.addAllAttributes(userCache.getUserHomeMap(username));//默认模型

            /**
             * 记住密码
             */
            return "redirect:/" + username + "/article/list";
        } else {
            return "redirect:/guest/sign";
        }
    }
    @Autowired
    UserCache userCache;
    private final String GUEST_LOGIN_FAIL = "GuestLoginFail.html";
    private final String USER_HOME = "UserHome.html";

    /**
     * 登录页面生成验证码
     *
     * @param request HTTP请求
     * @param response 响应
     */
    @GetMapping("/login/verify")
    public void createLoginVerify(HttpServletRequest request, HttpServletResponse response) {
        verifyService.createVerify(request, response, VerifyService.SESSION_KEY_VERIFY_LOGIN);
    }

    /**
     * 登录页面校验验证码
     *
     * @param verifyCode 验证码
     * @param session HTTP会话
     * @return
     */
    @PostMapping("/login/verify")
    @ResponseBody
    public boolean checkLoginVerify(String verifyCode, HttpSession session) {
        return verifyService.checkVerify(verifyCode, session, VerifyService.SESSION_KEY_VERIFY_LOGIN);
    }

//  
//    
//  _____   _               _   ____                   _ 
// |  ___| (_)  _ __     __| | |  _ \  __      __   __| |
// | |_    | | | '_ \   / _` | | |_) | \ \ /\ / /  / _` |
// |  _|   | | | | | | | (_| | |  __/   \ V  V /  | (_| |
// |_|     |_| |_| |_|  \__,_| |_|       \_/\_/    \__,_|
//                                                       
    /**
     * 找回密码事件 将激活链接发送至用户邮箱
     *
     * @param email
     * @param session
     * @return
     */
    @RequestMapping("/findPassword")
    @ResponseBody
    public boolean findPasswordAction(String email, HttpSession session) {
        System.out.println(email);
        return emailService.findPassword(email, session);
    }

    /**
     * 找回密码页面生成验证码
     *
     * @param request HTTP请求
     * @param response 响应
     */
    @GetMapping("/findPassword/verify")
    public void createFindPasswordVerify(HttpServletRequest request, HttpServletResponse response) {
        verifyService.createVerify(request, response, VerifyService.SESSION_KEY_VERIFY_FIND_PASSWORD);
    }

    /**
     * 找回密码页面校验验证码
     *
     * @param verifyCode 验证码
     * @param session HTTP会话
     * @return
     */
    @PostMapping("/findPassword/verify")
    @ResponseBody
    public boolean checkFindPasswordVerify(String verifyCode, HttpSession session) {
        return verifyService.checkVerify(verifyCode, session, VerifyService.SESSION_KEY_VERIFY_FIND_PASSWORD);
    }
    
    
    @RequestMapping("/logout")
    public String logoutAction(HttpSession session) {
        session.invalidate();
        return "redirect:/guest/sign";
    }

}
