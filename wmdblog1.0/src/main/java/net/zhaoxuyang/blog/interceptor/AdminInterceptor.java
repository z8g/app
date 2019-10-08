/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.interceptor;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static net.zhaoxuyang.blog.service.AdminService.ADMIN_SESSION_KEY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 项目事件 - 拦截器 拦截以下事件： 1. 新建项目 2. 修改项目 3. 删除项目 4. 查看个人所有项目（树） 5. 发布项目
 */
public class AdminInterceptor implements HandlerInterceptor {

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${blog.admin.username}")
    String adminUsername;

    /**
     * 未登录（session对象中没有"user"对象）则拦截（/user/**）
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws java.io.IOException
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws IOException {
        boolean isAdmin = false;

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(ADMIN_SESSION_KEY);
        if (username != null) {
            isAdmin = username.equals(adminUsername);
        }

        if (!isAdmin) {
//            response.sendError(403);
            response.sendRedirect("http://"+request.getServerName()+":"+request.getServerPort()+"/admin/login");
        }

        return isAdmin;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) {
      
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) {
        
    }

}
