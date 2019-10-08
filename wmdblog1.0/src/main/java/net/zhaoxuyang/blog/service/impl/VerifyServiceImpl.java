/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
*/
package net.zhaoxuyang.blog.service.impl;

import java.lang.invoke.MethodHandles;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.zhaoxuyang.blog.service.VerifyService;
import net.zhaoxuyang.util.RandomValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VerifyServiceImpl implements VerifyService {

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void createVerify(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
        LOG.info("public void createVerify");
        LOG.info("request=" + request);
        LOG.info("response=" + response);
        LOG.info("sessionKey=" + sessionKey);
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response, sessionKey);//输出验证码图片方法
        } catch (Exception e) {
            LOG.error("获取验证码失败>>>>   ", e);
        }
    }

    /**
     * 检查验证码，比较session中的key
     *
     * @param verifyCode 客户端传来的验证码
     * @param session HTTP 会话
     * @param sessionKey key
     * @return
     */
    @Override
    public boolean checkVerify(String verifyCode, HttpSession session, String sessionKey) {
        try {
            String random = (String) session.getAttribute(sessionKey);
            LOG.info("验证码:" + random);
            return (random == null) ? false : random.equals(verifyCode.toUpperCase());
        } catch (Exception e) {
            LOG.error("验证码校验失败", e);
            return false;
        }
    }
}
