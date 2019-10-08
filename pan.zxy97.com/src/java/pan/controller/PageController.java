package pan.controller;

import pan.util.PageNameUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @名称 控制器-页面映射
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
@Controller
public class PageController {

    @RequestMapping(value=PageNameUtil.INDEX)
    public String index(){
        return "index";
    }

    @RequestMapping(value=PageNameUtil.HEADER)
    public String header(){
        return "header";
    }
    @RequestMapping(value=PageNameUtil.FOOTER)
    public String footer(){
        return "footer";
    }

    @RequestMapping(value=PageNameUtil.MAIN)
    public String main(){
        return "main";
    }

    @RequestMapping(value=PageNameUtil.LOGIN_FORM)
    public String loginForm(){
        return "loginForm";
    }

    @RequestMapping(value=PageNameUtil.REGISTER_FORM)
    public String registerForm(){
        return "registerForm";
    }

    @RequestMapping(value=PageNameUtil.ERROR)
    public String error(){
        return "error";
    }

    @RequestMapping(value=PageNameUtil.DATABSE_ERROR)
    public String databaseError(){
        return "databaseError";
    }

    @RequestMapping(value=PageNameUtil.WELCOME)
    public String welcome(){
        return "welcome";
    }



}
