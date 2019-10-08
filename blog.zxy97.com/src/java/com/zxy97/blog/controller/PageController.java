package com.zxy97.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PageController {
    
    @RequestMapping(value="/index")
    public String index(){
        return "Index";
    }
    
    @RequestMapping(value="/login")
    public String login(){
        return "LoginForm";
    }
    
    @RequestMapping(value="/register")
    public String register(){
        return "RegisterForm";
    }
    
    @RequestMapping(value="/author")
    public String author(){
        return "AuthorSearchForm";
    }
        
    
}
