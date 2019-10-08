/**
 * 项目事件 - 拦截器
 * 拦截以下事件：
 * 1. 新建项目
 * 2. 修改项目
 * 3. 删除项目
 * 4. 查看个人所有项目（树）
 * 5. 发布项目
 */
package club.nwsuaf.interceptor;

import club.nwsuaf.model.User;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        boolean result = false;
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                result = true;
            }
        }
        if(!result){
            try {
                response.sendError(403);
            } catch (IOException ex) {
                Logger.getLogger(MethodHandles.lookup().lookupClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
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
