/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
package net.zhaoxuyang.blog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.model.UserInfo;
import net.zhaoxuyang.blog.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 更新用户信息类 1. 前端发送AJAX请求 2. 后端输出URL 3. 前端接收URL
 *
 * @author maoyufeng
 */
@Controller
public class UserUpdateController {

    Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UserinfoService userinfoService;

    /**
     * 读取/img/{userId}虚拟路径对应的文件，并返回二进制数据
     * @param request
     * @param response
     * @param userId
     * @return 
     */
    @RequestMapping(value = "/img/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    public byte[] userHeadImage(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer userId) {
        try {

            File file = new File("upload",userId+"");
            
            LOG.info("img:"+file.getAbsolutePath());
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        } catch (final IOException e) {
            return null;
        }
    }

    /**
     * 修改用户信息
     *
     * @param info 用户信息类
     * @param session HTTP会话
     * @param file
     * @param request HTTP请求
     * @param model
     * @return 用户主页
     */
    @PostMapping("/update")
    public String updateAction(
            UserInfo info,
            HttpSession session,
            MultipartFile file,
            HttpServletRequest request,
            Model model
    ) {
        System.out.println(session.getAttribute("user"));

        User user = (User) session.getAttribute("user");
        //  System.out.println(user.getUsername());
        if (!file.isEmpty()) {
            try {
                String saveFolder = "upload";
                new File(saveFolder).mkdirs();

                File saveFile = new File(saveFolder, "" + user.getId()); // 上传文件名
                file.transferTo(saveFile.toPath());

                info.setNickimg("img/" + user.getId());//http://localhost:8080/img/1
                model.addAttribute("imgPath", saveFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            //  System.out.println(user.getId());
            /**
             * 试着查询用户信息表中是否存在该用户信息
             */
            boolean result = (userinfoService.selectByUserId(user.getId()) != null);
            info.setUser_id(user.getId());

            /**
             * - 如果存在用户信息，就更新该信息 - 否则插入新的用户信息 -
             * 如果出现修改异常，则重定向到错误提示，修改成功后也重定向到用户主页
             *
             */
            if (result) {
                userinfoService.update(info);
            } else {
                userinfoService.insertInfo(info);
            }
        } catch (Exception e) {
            // System.out.println("修改失败！");
            return "redirect:/" + user.getUsername() + "/article/list";
        }
        //System.out.println(userinfoService.selectByUserId(user.getId()));

        session.setAttribute(
                "userinfo", userinfoService.selectByUserId(user.getId()));
        //System.out.println("修改成功！");
        return "redirect:/" + user.getUsername()
                + "/article/list";

    }

}
