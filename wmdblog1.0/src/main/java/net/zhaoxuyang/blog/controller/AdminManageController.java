/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
package net.zhaoxuyang.blog.controller;

import com.github.pagehelper.PageInfo;
import java.lang.invoke.MethodHandles;
import net.zhaoxuyang.blog.exception.BlogUserNotFoundException;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.service.AdminService;
import net.zhaoxuyang.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理员管理用户和文章控制层 通过userForm()获得用户的所有信息，并显示，包含分页 userDeleteAction()负责删除用户控制
 * userUpdateForm()进行修改用户信息操作 articleForm()实现获得文章信息，显示，包含分页
 *
 *
 * @author wjy
 */
@Controller
@RequestMapping("/admin/manage")
public class AdminManageController {

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String form() {
        return "AdminManage.html";
    }

    @GetMapping(value = {"user", "user/list"})
    public String userForm(Model model) {
//        List<User> userList = adminService.listUser();
//        LOG.info(userList.toString());
//        model.addAttribute("userList", userList);
//        return "AdminManageUser.html";
        return "redirect:/admin/manage/user/list/1";
    }

    @GetMapping("user/list/{pageCurr}")
    public String userForm(
            Model model,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "8") Integer pageSize
    ) {
        PageInfo pageInfo = adminService.listUser(pageCurr, pageSize);
        LOG.info(pageInfo.toString());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageUri", "admin/manage/user/list");
        return "AdminManageUser.html";
    }

    @GetMapping(value = {"user/delete/{userId}"})
    public String userDeleteAction(@PathVariable Integer userId, Model model) {
        LOG.info("删除用户：" + userId);
        adminService.deleteUserById(userId);

        return "redirect:/admin/manage/user/list/1";
    }

    @GetMapping(value = {"article/delete/{articleId}"})
    public String articleDeleteAction(@PathVariable Integer articleId, Model model) {
        LOG.info("删除文章：" + articleId);
        adminService.deleteArticleById(articleId);

        return "redirect:/admin/manage/article/list/1";
    }

    @GetMapping(value = {"article", "article/list"})
    public String articleForm(Model model) {

        return "redirect:/admin/manage/article/list/1";
    }

    @GetMapping("article/list/{pageCurr}")
    public String articleForm(
            Model model,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "8") Integer pageSize
    ) {
        PageInfo pageInfo = adminService.listArticle(pageCurr, pageSize);
        LOG.info(pageInfo.toString());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageUri", "admin/manage/article/list");
        return "AdminManageArticle.html";
    }

    @GetMapping(value = {"user/update/{userId}"})
    public String userUpdateForm(
            @PathVariable Integer userId,
            Model model
    ) throws BlogUserNotFoundException {
        User editUser = userService.getById(userId);
        if (editUser == null) {
            throw new BlogUserNotFoundException(String.format("ID为%d的用户不存在！", userId));
        }

        model.addAttribute("editUser", editUser);
        return "AdminManageUserUpdate.html";
    }

    @PostMapping(value = {"user/update/{userId}"})
    public ModelAndView userUpdateAction(@PathVariable Integer userId, User user) {
        ModelAndView mv = new ModelAndView();

        boolean isUpdate = userService.update(user);
        if (isUpdate) {
            mv.setViewName(String.format("redirect:/admin/manage/user"));
        } else {
            mv.addObject("editUser", user);
            mv.setViewName("AdminManageUserUpdate.html");
        }

        return mv;
    }

}
