/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */                                      
package net.zhaoxuyang.blog.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wangjingyu
 */


public interface VerifyService {

    public final String SESSION_KEY_VERIFY_REGISTER = "registerVerify";
    public final String SESSION_KEY_VERIFY_LOGIN = "loginVerify";
    public final String SESSION_KEY_VERIFY_FIND_PASSWORD = "findPasswordVerify";
    public final String SESSION_KEY_VERIFY_ADMIN_LOGIN = "adminLoginVerify";

//    
//                        _    __                                      _        
// __   __   ___   _ __  (_)  / _|  _   _            ___    ___     __| |   ___ 
// \ \ / /  / _ \ | '__| | | | |_  | | | |  _____   / __|  / _ \   / _` |  / _ \
//  \ V /  |  __/ | |    | | |  _| | |_| | |_____| | (__  | (_) | | (_| | |  __/
//   \_/    \___| |_|    |_| |_|    \__, |          \___|  \___/   \__,_|  \___|
// 
    /**
     * 生成验证码，根据key存入session，再将其渲染成图片并响应，
     *
     * @param request
     * @param response
     * @param sessionKey
     */
    public void createVerify(HttpServletRequest request, HttpServletResponse response, String sessionKey);

    /**
     * 检查验证码，比较session中的key
     *
     * @param verifyCode 客户端传来的验证码
     * @param session HTTP 会话
     * @param sessionKey key
     * @return
     */
    public boolean checkVerify(String verifyCode, HttpSession session, String sessionKey);

}
