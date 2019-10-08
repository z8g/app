package pan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pan.model.FileVO;
import pan.model.User;
import pan.service.FileService;
import pan.service.UploadService;
import pan.service.UserService;
import pan.util.PageNameUtil;

/**
 * @名称 控制器-用户操作
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
/**
     * 核心处理
     *
     * @param request
     * @param response
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/process.html", produces = "text/html;charset=UTF-8")
    public String process(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String path = (String) session.getAttribute("path");//当前所在的绝对路径
        String tid = (String) session.getAttribute("tid");//当前所在目录的所有者的id
        User user = (User) session.getAttribute("user");//当前登陆的用户
        String op = request.getParameter("op");//操作类型
        String name = request.getParameter("name");//文件/目录名

        User tUser = userService.getUserById(Integer.valueOf(tid));//被访问的用户
        session.setAttribute("tUser", tUser);
        boolean isUserSelf;//当前目录是否属于已登录的用户
        if (user == null) {
            isUserSelf = false;//当前用户未登录
        } else {
            isUserSelf = tUser.getId().equals(user.getId());
        }
        session.setAttribute("isUserSelf", isUserSelf);

        if (op == null || "".equals(op)) {

        } else if ("upper".equals(op)) {
            path = FileService.upper(path, tUser.getId() + "");//返回上级目录
        } else if ("child".equals(op)) {
            //进入所点击的目录或者单击下载文件
            path = FileService.child(path, name, request, response);
        } else if ("rename".equals(op)) {
            if (isUserSelf) {//权限控制
                String newName = request.getParameter("newName");
                FileService.rename(path, name, newName);//重命名
            }
        } else if ("delete".equals(op)) {
            if (isUserSelf) {//权限控制
                FileService.deleteFile(path, name);//删除文件或文件夹
            }
        } else if ("newFile".equals(op)) {
            if (isUserSelf) {//权限控制
                FileService.newFile(path, name);//新建文件
            }
        } else if ("newFolder".equals(op)) {
            if (isUserSelf) {//权限控制
                FileService.newFolder(path, name);//新建文件夹
            }
        } else if ("upload".equals(op)) {
            if (isUserSelf) {//权限控制
                UploadService.upload(path, request, response);//新建文件夹
            }
        }

        String path_uri = FileService.getPathUri(path);
        List<FileVO> fileVOList = FileService.getFileVOList(path, isUserSelf);//文件信息列表
        session.setAttribute("path_uri", path_uri);
        session.setAttribute("fileVOList", fileVOList);
        session.setAttribute("path", path);
        session.setAttribute("tid", tid);
        session.setAttribute("user", user);
        session.setAttribute("isUserSelf", isUserSelf);
        return "user";

    }
    /**
     * 登陆
     *
     * @param request
     * @param response
     * @param session
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping(value = PageNameUtil.LOGIN_ACTION)
    public String login(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        User loginUser = new User();
        loginUser.setUsername(request.getParameter("login_name"));
        loginUser.setPassword(request.getParameter("login_password"));

        User user = userService.userLogin(loginUser);
        if (user != null) {
            session.setAttribute("tid", user.getId());
            session.setAttribute("user", user);
            return "redirect:visit.html?tid="+user.getId();
        } else {
            session.setAttribute("msg", "登录失败!");
            return "loginForm";
        }
    }
    
    /**
     * 退出登陆
     *
     * @param session
     * @return
     */
    @RequestMapping(value = PageNameUtil.LOGOUT_ACTION)
    public String logout(
            HttpSession session) {
        
        User user = (User)session.getAttribute("user");
        Integer tid = user.getId();
        user = null;
        session.setAttribute("user", user);
        session.invalidate();
        return "redirect:visit.html?tid="+tid;
    }

    /**
     * 注册
     *
     * @param request
     * @param response
     * @param session
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping(value = PageNameUtil.REGISTER_ACTION)
    public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        User regUser = new User();
        regUser.setUsername(request.getParameter("reg_name"));
        regUser.setPassword(request.getParameter("reg_password"));
        regUser.setEmail(request.getParameter("reg_email"));
        try {
            userService.userRegister(regUser);//将注册信息插入数据库
            User user = userService.getUserByName(regUser.getUsername());
            FileService.userRegister(user.getId());//根据ID创建文件夹
            session.setAttribute("msg", user.getUsername() + "注册成功！");
            session.setAttribute("user", user);
            return "loginForm";
        } catch (Exception ex) {
            session.setAttribute("msg", regUser.getUsername() + "注册失败！");
            return "registerForm";
        }

    }

    @RequestMapping(value = PageNameUtil.REG_CHECK)
    public void regCheck(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String regName = request.getParameter("reg_name");
        String username = userService.userRegCheck(regName);
        String resultText;
        if (username == null) {
            resultText = regName + "可以注册！";
        } else {
            resultText = regName + "已注册！";
        }
        PrintWriter printWriter;
        try {
            printWriter = response.getWriter();
            printWriter.print(resultText);
            printWriter.close();
        } catch (IOException ex) {

        }

    }

    @RequestMapping(value = PageNameUtil.USER_LIST)
    public String userList(HttpSession session) {
        List<User> userList = userService.listUsers();
        session.setAttribute("userList", userList);
        return "userList";
    }
}
