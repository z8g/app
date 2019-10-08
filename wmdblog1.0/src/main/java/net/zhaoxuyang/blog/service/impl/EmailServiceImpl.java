/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
*/
package net.zhaoxuyang.blog.service.impl;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.service.EmailService;
import net.zhaoxuyang.blog.service.UserService;
import net.zhaoxuyang.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author zhaoxuyang
 */
@Service
public class EmailServiceImpl implements EmailService {

    Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Value("${email.user}")
    private String FORM = "zhaoxuyang19971015@foxmail.com";

    @Value("${email.password}")
    private String FORM_KEYWORD = "gomyhlqetjxsbabb";

    @Override
    public boolean findPassword(String email, HttpSession session) {
        LOG.info("发送找回密码的邮件给：" + email);

        String subject = "【blog.zhaoxuyang.net】 找回密码";
        String content = UUID.randomUUID().toString();
        session.setAttribute("findPasswordCode", content);
        User user = userService.getByEmail(email);
        if (user == null) {
            return false;
        }
        return EmailUtil.send(FORM, FORM_KEYWORD, email, subject, content);
    }

    static final String REGISTER_ACTIVE_URI = "guest/register/active";

    @Override
    public boolean registerActive(String email, HttpServletRequest request) {
        LOG.info("发送注册激活的邮件给=" + email);
        LOG.info("public boolean registerActive(String email, HttpServletRequest request)");
        LOG.info("email="+ email);
        LOG.info("request="+ request);

        String subject = "【blog.zhaoxuyang.net】 注册激活";//邮件主题

        String registerActiveCode = UUID.randomUUID().toString();//注册激活码
        String webSiteUrl = String.format("%s://%s:%d%s/%s?code=%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                request.getContextPath(),
                REGISTER_ACTIVE_URI,
                registerActiveCode
        );//注册激活请求地址
        String content = String.format("账号激活地址：<a href='%s' target='_blank'>%s</a>",
                webSiteUrl, webSiteUrl);//邮件内容

        HttpSession session = request.getSession();
        session.setAttribute("registerActiveCode", registerActiveCode);//添加到session中，等待请求

        return EmailUtil.send(FORM, FORM_KEYWORD, email, subject, content);
    }

}
