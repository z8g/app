/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zhaoxuyang.blog.exception;

import net.zhaoxuyang.util.DatetimeUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常 controller 增强器
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * 绑定后请在后面注释作者
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("now2",DatetimeUtil.formatNow());//zhaoxuyang
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BlogException.class)
    public ModelAndView errorHandler(BlogException ex) {
        ModelAndView result = new ModelAndView();
        result.addObject("exceptionTitle", formatExceptionTitle(ex));
        result.addObject("exceptionMessage", ex.getMessage());
        result.setViewName("Exception.html");
        return result;
    }

    /**
     * 根据异常类型返回异常页面的标题
     *
     * @param ex
     * @return
     */
    private String formatExceptionTitle(BlogException ex) {
        if (ex instanceof BlogArticleNoPermissionException) {
            return "文章无权限";
        }

        if (ex instanceof BlogArticleNotFoundException) {
            return "文章不存在";
        }

        if (ex instanceof BlogUserNoPermissionException) {
            return "用户无权限";
        }

        if (ex instanceof BlogUserNotFoundException) {
            return "用户不存在";
        }

        /**
         * 父类异常需要写在下方
         */
        if (ex instanceof BlogUserException) {
            return "用户异常";
        }

        if (ex instanceof BlogArticleException) {
            return "文章异常";
        }

        return "博客异常";
    }

}
