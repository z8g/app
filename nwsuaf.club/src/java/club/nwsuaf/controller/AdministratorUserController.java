package club.nwsuaf.controller;

import club.nwsuaf.controller.util.NotificationUtil;
import club.nwsuaf.model.Notification;
import club.nwsuaf.model.User;
import club.nwsuaf.service.UserService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/administrator/user")
public class AdministratorUserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        List<User> administratorUserList = userService.AdministratorListAll();
        mv.addObject("administratorUserList", administratorUserList);
        mv.setViewName("AdministratorUserList");
        return mv;
    }

    @RequestMapping(value = "/delete.action")
    public String delete(String username, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        boolean success = userService.AdministratorDelete(username);
        if (success) {
            NotificationUtil.add(session, String.format("删除账号为 [%s] 的用户成功", username),Notification.TYPE_WARNING);
        } else {
            NotificationUtil.add(session, String.format("删除账号为 [%s] 的用户失败", username),Notification.TYPE_DANGER);
        }
        return "redirect:/administrator/user/list";
    }


    @RequestMapping(value = "/updateToUser.action")
    public String updateToUser(String username, HttpSession session) {
        boolean success = userService.AdministratorUpdateToUser(username);
        if (success) {
            NotificationUtil.add(session, String.format("修改账号为 [%s] 的用户的身份为用户成功", username), Notification.TYPE_WARNING);
        } else {
            NotificationUtil.add(session, String.format("修改账号为 [%s] 的用户的身份为用户失败", username), Notification.TYPE_DANGER);
        }
        return "redirect:/administrator/user/list";
    }

    @RequestMapping(value = "/updateToOperator.action")
    public String updateToOperator(String username, HttpSession session) {
        boolean success = userService.AdministratorUpdateToUser(username);
        if (success) {
            NotificationUtil.add(session, String.format("修改账号为 [%s] 的用户的身份为操作员成功", username),Notification.TYPE_SUCCESS);
        } else {
            NotificationUtil.add(session, String.format("修改账号为 [%s] 的用户的身份为操作员失败", username), Notification.TYPE_DANGER);
        }
        return "redirect:/administrator/user/list";
    }

}
