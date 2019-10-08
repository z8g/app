/*
 * 访客 - 控制器
 */
package club.nwsuaf.controller;

import club.nwsuaf.controller.util.NotificationUtil;
import club.nwsuaf.model.Apply;
import club.nwsuaf.model.Article;
import club.nwsuaf.model.FileVO;
import club.nwsuaf.model.Notification;
import club.nwsuaf.model.Project;
import club.nwsuaf.model.User;
import club.nwsuaf.model.constants.Constants;
import club.nwsuaf.service.ApplyService;
import club.nwsuaf.service.ArticleService;
import club.nwsuaf.service.FileService;
import club.nwsuaf.service.ProjectService;
import club.nwsuaf.service.UserService;
import club.nwsuaf.util.EmailUtil;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @时间 2019-02-04 21:37
 * @作者 赵栩旸
 */
@Controller
public class VisitorController {

    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    ProjectService projectService;
    @Autowired
    FileService fileService;
    @Autowired
    ApplyService applyService;

    /**
     * 用户主页
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/{username}")
    public ModelAndView usrProfile(@PathVariable String username) {
        ModelAndView mv = new ModelAndView();
        User user = userService.get(username);
        List<Apply> applyList = applyService.listForUser(username);
        if (user != null) {
            List<Project> userPojectList = projectService.visitorListPublishedByUser(username);
            mv.addObject("userPojectList", userPojectList);
            mv.addObject("profile", user);
            mv.addObject("applyList", applyList);
            mv.setViewName("UserProfile");
        }
        return mv;
    }

    /**
     * 所有人-项目列表事件（状态为“已发表”）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/project/list")
    public String projectListAction(HttpSession session) {
        List<Project> projectList = projectService.visitorListPublished();
        session.setAttribute("projectList", projectList);
        return "VisitorProjectList";
    }
//    
//    /**
//     * 所有人-用户列表事件（身份为“用户”）
//     *
//     * @param session
//     * @return
//     */
//    @RequestMapping(value = "/userList.action")
//    public String userListAction(HttpSession session) {
//        List<User> userList = userService.listUsers();
//        session.setAttribute("userList", userList);
//        return "VisitorUserList";
//    }
//

    /**
     * 所有人-文章列表事件
     *
     * @param type
     * @param session
     * @return
     */
    @RequestMapping(value = "/article/list")
    public String articleListAction(String type, HttpSession session) {
        List<Article> articleList = articleService.listByType(type);
        session.setAttribute("articleList", articleList);
        return "VisitorArticleList";
    }

    /**
     * 获取项目详情事件
     *
     * @param username
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/project/{username}/details/{id}")
    public String projectDetailsAction(
            @PathVariable String username,
            @PathVariable Integer id,
            HttpSession session
    ) {
        Project projectDetails = projectService.get(id);
        if (projectDetails == null) {
            NotificationUtil.add(session, String.format("查询不到编号为[%d]的项目信息", id), Notification.TYPE_WARNING);
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");//获取用户登录状态

        if ((user != null && user.getUsername().equals(username)) || (Constants.PROJECT_STATE_YFB.equals(projectDetails.getState()))) {
            //是作者或者项目已发布
            session.setAttribute("projectDetails", projectDetails);
            return "ProjectDetails";
        }

        NotificationUtil.add(session, String.format("查询不到编号为[%d]的项目信息", id), Notification.TYPE_WARNING);
        return "redirect:/";
    }

    /**
     * 获取通知详情事件
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/article/details/{id}")
    public String articleDetailsAction(@PathVariable Integer id, HttpSession session) {
        Article article = articleService.get(id);
        if (article != null) {
            session.setAttribute("article", article);
            NotificationUtil.add(session, String.format("查看编号为[%d]的信息", id));
            return "VisitorArticleDetails";
        }
        NotificationUtil.add(session, String.format("查询不到编号为[%d]的信息", id), Notification.TYPE_WARNING);
        return "redirect:/";
    }

    /**
     * 注册页面
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/register")
    public String registerForm(HttpSession session) {
        return "VisitorRegister";
    }

    /**
     * 注册事件
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/register.action")
    public String registerAction(User user, HttpSession session) {
        boolean success = userService.visitorRegister(user);
        if (success) {
            User loginUser = userService.visitorLogin(user);
            session.setAttribute("user", loginUser);
            NotificationUtil.add(session, "注册成功！");
            return "redirect:/" + loginUser.getUsername();
        } else {
            NotificationUtil.add(session, "注册失败，可能是账号已被注册。");
            return "VisitorRegister";
        }
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String loginForm() {
        return "VisitorLogin";
    }

    /**
     * 登录事件
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.action")
    public String loginAction(User user, HttpSession session) {
        User loginUser = userService.visitorLogin(user);
        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            String auth = loginUser.getAuth();
            if (null != auth) {
                switch (auth) {
                    case Constants.USER_AUTH_USER:
                        return "redirect:/user/project/list";
                    case Constants.USER_AUTH_OPERATOR:
                        return "redirect:/operator/project/list";//项目审核
                }
            }
            return "redirect:/" + loginUser.getUsername();
        } else {
            NotificationUtil.add(session, "登录失败，可能是账号密码不匹配。", Notification.TYPE_DANGER);
            return "redirect:/login";
        }
    }

    /**
     * 找回密码页面
     *
     * @return
     */
    @RequestMapping(value = "/findPassword")
    public String findPasswordForm() {
        return "VisitorFindPassword";
    }

    /**
     * 找回密码事件
     *
     * @param email
     * @param session
     * @return
     */
    @RequestMapping(value = "/findPassword.action")
    public String findPasswordAction(String email, HttpSession session) {
        List<User> userList = userService.visitorFindPassword(email);
        boolean success = EmailUtil.sendEmail(userList);
        if (success) {
            NotificationUtil.add(session, "账号信息已发送至邮箱，请注意查收");
        } else {
            NotificationUtil.add(session, "找回密码失败，可能是邮箱错误，或者要找回密码的人数太多。");
        }
        return "redirect:/login";
    }
//
//    /**
//     * 通知 - 全部清除
//     *
//     * @param session
//     * @return
//     */
//    @RequestMapping(value = "/deleteNotification.action")
//    @ResponseBody
//    public String deleteNotificationAction(HttpSession session) {
//        NotificationUtil.deleteAll(session);
//        return "success";
//    }
//

    /**
     * 资源列表页面
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/downloads")
    public String downloadForm(HttpSession session) {
        List<FileVO> fileVOList = fileService.listFiles();
        session.setAttribute("fileVOList", fileVOList);
        return "VisitorFileList";
    }
//

    /**
     * 文件下载
     *
     * @param file
     * @param request
     * @param response
     * @param session
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String downloadAction(
            String file,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) throws UnsupportedEncodingException {
        String pathUri = file;
        String folderPath = fileService.getUploadFolder();
        File downloadFile = new File(folderPath, pathUri);
        session.setAttribute("filepath", downloadFile.getAbsolutePath());
        request.setAttribute("callback", "file/list");
        return "Download";
    }

    /**
     * 项目申请
     *
     * @param apply
     * @param projectId
     * @param session
     * @return
     */
    @RequestMapping(value = "/apply.action")
    public String applyAction(Apply apply, Integer projectId, HttpSession session) {
        apply.setProjectId(projectId);
        boolean success = applyService.insert(apply);
        if (success) {
            NotificationUtil.add(session, "申请成功！");
        } else {
            NotificationUtil.add(session, "申请失败，可能是申请的人数太多。");
        }
        return String.format("redirect:/%s", apply.getUsername());
    } 
    
    @RequestMapping(value = "/applyDelete.action")
    public String applyDeleteAction(Integer id, String username, HttpSession session) {
        boolean success = applyService.delete(id, username);
        if (success) {
            NotificationUtil.add(session, "删除成功！");
        } else {
            NotificationUtil.add(session, "删除失败，请稍后再删除。");
        }
        return String.format("redirect:/%s",username);
    }

}
