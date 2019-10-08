package jalaxy.controller;

/**
 *
 * @author zhaoxuyang
 */
import jalaxy.annotation.Controller;
import jalaxy.annotation.RequestMapping;
import jalaxy.annotation.RequestParam;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/doTest")
    public void test1(
            HttpServletRequest request, 
            HttpServletResponse response,
            @RequestParam("param") String param
    ) {
        System.out.println(param);
        try {
            response.getWriter().write("参数是" + param);
        } catch (IOException e) {
        }
    }

    @RequestMapping("/doTest2")
    public void test2(
            HttpServletRequest request, 
            HttpServletResponse response
    ) {
        try {
            response.getWriter().println("Test2");
        } catch (IOException e) {
        }
    }
}
