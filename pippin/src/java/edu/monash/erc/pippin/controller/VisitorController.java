/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.monash.erc.pippin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author zhaoxuyang
 */
@Controller
public class VisitorController {
    
    @RequestMapping("home")
    public String home(){
        return "Home";
    }    
    @RequestMapping("contact")
    public String contact(){
        return "Contact";
    }    
    @RequestMapping("server")
    public String server(){
        return "Server";
    }    
    @RequestMapping("help")
    public String help(){
        return "Help";
    }
    @RequestMapping("job")
    public String jobList(){
        return "JobList";
    }
}
