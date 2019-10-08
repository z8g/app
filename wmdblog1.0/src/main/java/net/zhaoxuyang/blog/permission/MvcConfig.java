///*
//__        ____  __ ____    ____  _     ___   ____ 
//\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
// \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
//  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
//   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
// */ 
//package net.zhaoxuyang.blog.config;
//
//import net.zhaoxuyang.blog.permission.PermissionInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// *
// * @author zhaoxuyang
// */
//@Configuration
//public class MvcConfig extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public PermissionInterceptor permissionInterceptor() {
//        return new PermissionInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(permissionInterceptor())
//                .excludePathPatterns("/static/*")//不拦截
//                .excludePathPatterns("/error")//不拦截
//                .addPathPatterns("/**");//拦截
//    }
//
//}
