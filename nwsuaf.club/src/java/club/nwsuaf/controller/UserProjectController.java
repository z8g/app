package club.nwsuaf.controller;

import club.nwsuaf.controller.util.ArticleUtil;
import club.nwsuaf.controller.util.NotificationUtil;
import club.nwsuaf.model.Notification;
import club.nwsuaf.model.Project;
import club.nwsuaf.model.User;
import club.nwsuaf.service.ProjectService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/user/project")
public class UserProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * 用户 - 项目 - 创建页面
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/create")
    public String createForm(HttpSession session) {
        ArticleUtil.projectFormInit(session);
        return "UserProjectCreate";
    }

    /**
     * 用户 - 项目 - 创建事件
     *
     * @param project
     * @param session
     * @return
     */
    @RequestMapping(value = "/create.action")
    public String createAction(Project project, HttpSession session) {
        User user = (User) session.getAttribute("user");
        project.setUsername(user.getUsername());
        boolean success = projectService.userInsert(project);
        if (success) {
            NotificationUtil.add(session, String.format("项目创建成功！"));
            return "redirect:/user/project/list";
        } else {
            NotificationUtil.add(session, String.format("项目创建失败！"), Notification.TYPE_DARK);
            return "ProjectCreate";
        }

    }

    /**
     * 项目修改页面
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/update")
    public String updateForm(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");//进行操作的用户
        Project project = projectService.get(id);
        if (project == null || !project.getUsername().equals(user.getUsername())) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目不属于当前用户！", id), Notification.TYPE_DARK);
            return "redirect:/login";
        }
        ArticleUtil.projectFormInit(session);
        session.setAttribute("updateProject", project);
        NotificationUtil.add(session, String.format("开始修改项目[%d]", id));
        return "UserProjectUpdate";

    }

    /**
     * 每个用户的项目列表
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/list")
    public String projectList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        List<Project> userProjectList = projectService.userList(username);
        session.setAttribute("userProjectList", userProjectList);
        return "UserProjectList";
    }

    /**
     * 项目修改事件
     *
     * @param updateProject
     * @param session
     * @return
     */
    @RequestMapping(value = "/update.action")
    public String updateAction(Project updateProject, HttpSession session) {
        User user = (User) session.getAttribute("user");//进行操作的用户
        Project project = projectService.get(updateProject.getId());
        Integer id = project.getId();
        if (user == null || !user.getUsername().equals(project.getUsername())) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目不属于当前用户！", id), Notification.TYPE_DARK);
            return "redirect:/login";
        }
        project.setRequired(updateProject.getRequired());
        project.setContent(updateProject.getContent());
        project.setName(updateProject.getName());
        project.setType(updateProject.getType());
        project.setLogo(updateProject.getLogo());
        project.setGmtEnd(updateProject.getGmtEnd());
        boolean success = projectService.userUpdate(project);
        if (success) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目信息修改成功！", id));
             return "redirect:/user/project/list";
        } else {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目信息修改失败！", id), Notification.TYPE_DANGER);
            return String.format("redirect:/update?id=%d", id);
        }

    }

    /**
     * 用户-项目删除事件
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/delete.action")
    public String deleteAction(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");//进行操作的用户
        Project project = projectService.get(id);//项目
         if (user == null || !user.getUsername().equals(project.getUsername())) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目不属于当前用户！", id), Notification.TYPE_DARK);
            return "redirect:/login";
        }
        boolean success = projectService.userDelete(project);
        if (success) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目信息删除成功！", id));
        } else {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目信息删除失败！", id), Notification.TYPE_DANGER);
        }
        return "redirect:/user/project/list";
    }

    /**
     * 用户-项目发布事件
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/publish.action")
    public String publishAction(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Project project = projectService.get(id);
        if (user == null || !user.getUsername().equals(project.getUsername())) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目不属于当前用户！", id), Notification.TYPE_DARK);
            return "redirect:/login";
        }
        
        boolean success = projectService.userPublish(project);
        if (success) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目发布成功，请等待操作员审核……", id));
        } else {
            NotificationUtil.add(session, String.format("编号为 [%s] 的项目发布失败，请向系统管理员反馈……", id), Notification.TYPE_DARK);
        }
        return "redirect:/user/project/list";
    }
}
