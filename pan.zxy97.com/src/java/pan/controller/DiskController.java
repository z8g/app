/**
 * @名称 控制器-网盘操作
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */

package pan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pan.service.FileService;


@Controller
public class DiskController {


    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @param session
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping(value = "/download.html", produces = "text/html;charset=UTF-8")
    public String download(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) throws UnsupportedEncodingException {
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String pathUri = request.getParameter("file");
        String fileAbsolutePath = FileService.getAbsolutePath(pathUri);
        
        session.setAttribute("filepath", fileAbsolutePath);
        try {
            request.setAttribute("callback", "visit.html?tid="+session.getAttribute("tid"));
            request.getRequestDispatcher("WEB-INF/jsp/download.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {

        }
        return "user";
    }
    
    /**
     * 根据用户ID访问网盘
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/visit.html")
    public String visit(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) {

        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String tid = request.getParameter("tid");//请求传来的参数，被访问的用户ID
            session.setAttribute("tid", tid);

            String initPath = null;//初始路径为空
            session.setAttribute("path", initPath);
            
            return "redirect:process.html?op=upper";
        } catch (UnsupportedEncodingException | NumberFormatException e) {
            session.setAttribute("msg", "访问失败");
            return "loginForm";
        }
    }
//    
//    /**
//     * 编码测试
//     *
//     * @param username
//     * @return
//     */
//    @RequestMapping(value = "/test/{username}", produces = "text/html;charset=UTF-8")
//    @ResponseBody
//    public String test(@PathVariable String username) {
//        return username;
//    }
}
