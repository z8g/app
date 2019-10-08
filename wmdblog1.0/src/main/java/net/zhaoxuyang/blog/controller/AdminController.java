/*
 __        ____  __ ____    ____  _     ___   ____ 
 \ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
 \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
 \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
package net.zhaoxuyang.blog.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.zhaoxuyang.blog.service.AdminService;
import static net.zhaoxuyang.blog.service.AdminService.ADMIN_SESSION_KEY;
import net.zhaoxuyang.blog.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 管理员登录控制层 info()为了进行测试数值的传递 login()返回跳转到管理员首页 logout()负责管理员登出，清除session信息
 * login()的post提交负责验证管理员信息 login/verify路径请求验证码
 *
 * @author wjy
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${blog.admin.username}")
    String adminUsername;
    @Value("${blog.admin.password}")
    String adminPassword;

    @Autowired
    VerifyService verifyService;

    @GetMapping("/info")
    @ResponseBody
    public String info() {
        return String.format("username=%s,password=%s", adminUsername, adminPassword);
    }

    @GetMapping("/login")
    public String signForm() {
        return "AdminSign.html";
    }

    @RequestMapping("/logout")
    public String logoutAction(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @Autowired
    AdminService adminService;

    @Transactional
    @PostMapping("/login")
//    @ResponseBody
    public String loginAction(
            String adminUsername,
            String adminPassword,
            String verifyCode,
            HttpSession session,
            Model model,
            HttpServletRequest request
    ) {
        LOG.info("public String loginAction");
        LOG.info("adminUsername=" + adminUsername);
        LOG.info("adminPassword=" + adminPassword);
        LOG.info("verifyCode=" + verifyCode);

        boolean verifyCodeCheck = verifyService.checkVerify(verifyCode, session,
                VerifyService.SESSION_KEY_VERIFY_ADMIN_LOGIN);
        LOG.info("verifyCodeCheck:" + verifyCodeCheck);

        if (verifyCodeCheck) {
            boolean isLogin = adminService.login(adminUsername, adminPassword);
            if (isLogin) {
                session.setAttribute(ADMIN_SESSION_KEY, adminUsername);
                return "redirect:/admin/manage";
            }
        }
        return "redirect:/admin/login";
    }

    /**
     * 管理员登录页面生成验证码
     *
     * @param request HTTP请求
     * @param response 响应
     */
    @GetMapping("/login/verify")
    public void createVerify(HttpServletRequest request, HttpServletResponse response) {
        verifyService.createVerify(request, response, VerifyService.SESSION_KEY_VERIFY_ADMIN_LOGIN);
    }

    /**
     * 管理员登录页面校验验证码
     *
     * @param verifyCode 验证码
     * @param session HTTP会话
     * @return
     */
    @PostMapping("/login/verify")
    @ResponseBody
    public boolean checkVerify(String verifyCode, HttpSession session) {
        return verifyService.checkVerify(verifyCode, session, VerifyService.SESSION_KEY_VERIFY_ADMIN_LOGIN);
    }

    @PostMapping(value = "/databasebackup")
    @ResponseBody
    public String databasebackup(HttpServletResponse response) throws Exception {
        String filePath = "D:\\WMD管理系统\\";
//        mysqldump -h 118.24.178.170 -uzhaoxuyang -pzhaoxuyang test > my.sql
        String dbName = "test";
        java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
        String s = format2.format(new Date());
        String str;
        str = "cmd /c mysqldump -h 118.24.178.170 -uzhaoxuyang -pzhaoxuyang " + dbName + " > "
                + filePath + s
                + ".sql";
        try {
            Process process = Runtime.getRuntime().exec(
                    str
            );
            //备份的数据库名字为teacher，数据库连接和密码均为root
            System.out.println(str);
            System.out.println("success!!!");
            return "success";
        } catch (IOException e) {
            LOG.info(e.toString());
            return "error";
        }
    }
}
