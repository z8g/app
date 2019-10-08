package club.nwsuaf.controller;

import club.nwsuaf.controller.util.NotificationUtil;
import club.nwsuaf.model.Notification;
import club.nwsuaf.model.User;
import club.nwsuaf.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 退出登录事件
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout.action")
    public String logoutAction(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * 用户信息修改页面
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/update")
    public String userUpdateForm(HttpSession session) {
        return "UserUpdate";
    }

    /**
     * 用户信息修改事件
     *
     * @param formUser
     * @param session
     * @return
     */
    @RequestMapping(value = "/update.action")
    public String updateAction(HttpSession session, User formUser) {
        User user = (User) session.getAttribute("user");
        
        user.setPassword(formUser.getPassword());
        user.setRealname(formUser.getRealname());
        user.setInfo(formUser.getInfo());
        user.setEmail(formUser.getEmail());
        user.setLogo(formUser.getLogo());
        user.setTel(formUser.getTel());
        user.setGender(formUser.getGender());
        user.setBirth(formUser.getBirth());
        boolean success = userService.userUpdate(user);
        if (success) {
            NotificationUtil.add(session, String.format("修改个人信息成功"));
            return "redirect:/" + user.getUsername();
        } else {
            NotificationUtil.add(session, String.format("修改个人信息失败！"), Notification.TYPE_DANGER);
            return "redirect:/user/update";
        }
    }

}
