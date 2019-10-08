/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.nwsuaf.controller;

import club.nwsuaf.controller.util.NotificationUtil;
import club.nwsuaf.model.Article;
import club.nwsuaf.model.Notification;
import club.nwsuaf.model.Project;
import club.nwsuaf.model.User;
import club.nwsuaf.service.ArticleService;
import club.nwsuaf.service.ProjectService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/operator/project")
public class OperatorProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * 操作员-显示项目列表事件
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/list")
    public String listAction(HttpSession session) {
        List<Project> operatorProjectList = projectService.operatorList();
        session.setAttribute("operatorProjectList", operatorProjectList);
        return "OperatorProjectList";
    }

    
    
    /**
     * 操作员-查看项目详情
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/details/{id}")
    public String details(@PathVariable Integer id, HttpSession session) {
        Project project = projectService.get(id);
        session.setAttribute("projectDetails", project);
        return "OperatorProjectDetails";
    }
    
    /**
     * 操作员-审核成功
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/auditSuccess.action")
    public String auditSuccessAction(Integer id, HttpSession session) {
        Project project = projectService.get(id);
        boolean success =  projectService.operatorAuditSuccess(project);
        if (success) {
            NotificationUtil.add(session, String.format("编号为 [%d] 的项目审核[通过]！", id));
        } else {
            NotificationUtil.add(session, String.format("审核编号为 [%d] 的项目时出现异常！", id), Notification.TYPE_DANGER);
        }
        return "redirect:/operator/project/list";
    }

    /**
     * 操作员-审核失败
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/auditFail.action")
    public String auditFailAction(Integer id, HttpSession session) {
        Project project = projectService.get(id);
        boolean success =  projectService.operatorAuditFail(project);
        if (success) {
            NotificationUtil.add(session, String.format("编号为 [%d] 的项目审核[未通过]！", id));
        } else {
            NotificationUtil.add(session, String.format("审核编号为 [%d] 的项目时出现异常！", id), Notification.TYPE_DANGER);
        }
        return "redirect:/operator/project/list";
    }
}
