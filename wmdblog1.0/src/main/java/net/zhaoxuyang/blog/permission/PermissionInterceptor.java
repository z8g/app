///*
//__        ____  __ ____    ____  _     ___   ____ 
//\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
// \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
//  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
//   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
// */ 
//
//package net.zhaoxuyang.blog.permission;
//
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import net.zhaoxuyang.blog.model.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// *
// * @author zhaoxuyang
// */
///**
// * @author blueriver
// * @description 权限拦截器
// * @date 2017/11/17
// * @since 1.0
// */
//public class PermissionInterceptor implements HandlerInterceptor {
//            Logger LOG = LoggerFactory.getLogger(getClass());
////    @Autowired
////    private AdminUserService adminUserService;
//// 
//    @Override
//    public boolean preHandle(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Object handler
//    ) throws Exception {
//        LOG.info("public void afterCompletion");
//        LOG.info("request=" + request);
//        LOG.info("response=" + response);
//        LOG.info("handler=" + handler); 
////        if(session.getAttribute("user")!=null){
////            return true;
////        }
//
//        boolean result = true;
//
//        try {
//            request.setCharacterEncoding("UTF-8");
//        } catch (UnsupportedEncodingException ex) {
//            System.out.println(ex);
//            result = false;
//        }
//
//        // 验证权限
////        if (this.hasPermission(request, handler)) {
////            return true;
////        }
//        //  null == request.getHeader("x-requested-with") TODO 暂时用这个来判断是否为ajax请求
//        // 如果没有权限 则抛403异常 springboot会处理，跳转到 /error/403 页面
////        response.sendError(HttpStatus.FORBIDDEN.value(), "无权限");
//        return result;
//    }
//
//    /**
//     * 是否有权限
//     *
//     * @param handler
//     * @return
//     */
//    private boolean hasPermission(HttpServletRequest request, Object handler) {
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            
//            // 获取方法上的注解
//            Method method = handlerMethod.getMethod();//方法
//            RequestPermission permission = method.getAnnotation(RequestPermission.class);
//            // 如果方法上的注解为空 则获取类的注解
//            if (permission == null) {
//                permission = method.getDeclaringClass().getAnnotation(RequestPermission.class);
//            }
//
//            // 如果标记了注解，则判断权限
//            Permission [] permissions = permission.value();
//            System.out.println(Arrays.toString(permissions));
//
//            HttpSession session = request.getSession();
//            User user = (User) session.getAttribute("user");
//            if (user != null) {
//                return permissions.equals(Permission.ADMINISTRATOR);
//            }
//
////            if (requiredPermission != null && StringUtils.isNotBlank(requiredPermission.value())) {
////                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
////                Set<String> permissionSet = adminUserService.getPermissionSet();
////                if (CollectionUtils.isEmpty(permissionSet) ){
////                    return false;
////                }
////                return permissionSet.contains(requiredPermission.value());
////            }
//        }
//        return false;
//    }
//
//    @Override
//    public void postHandle(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Object handler, 
//            ModelAndView modelAndView) throws Exception {
//        LOG.info("public void postHandle");
//        LOG.info("request=" + request);
//        LOG.info("response=" + response);
//        LOG.info("handler=" + handler); 
//    }
//
//    @Override
//    public void afterCompletion(
//            HttpServletRequest request,
//            HttpServletResponse response, 
//            Object handler,
//            
//            Exception ex) throws Exception {
//          LOG.info("public void afterCompletion");
//          LOG.info("request=" + request);
//          LOG.info("response=" + response);
//          LOG.info("handler=" + handler); 
//    }
//}
