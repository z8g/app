/*
 * 文件管理-控制器
 */
package club.nwsuaf.controller;

import club.nwsuaf.model.FileVO;
import club.nwsuaf.service.FileService;
import club.nwsuaf.util.DatetimeUtil;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 赵栩旸
 */
@Controller
@RequestMapping(value = "/operator/file")

public class OperatorFileController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/list")
    public String listAction(HttpSession session) {
        List<FileVO> fileVOList = fileService.listFiles();
        session.setAttribute("fileVOList", fileVOList);
        return "OperatorFileList";
    }
    
    private static final String[]strs = {
        "<","\\","/",":","*","#","?","\"","'",">","|",",","（","）","+"
    };
    private static String filterFileName(String fileName){
        for(String str:strs){
            if(fileName.contains(str)){
                return DatetimeUtil.formatFileName(fileName);
            }
        }
        return fileName;
    }

    @RequestMapping(value = "/upload.action")
    @ResponseBody
    public String upload(
            MultipartFile file,
            HttpSession session
    ) {
        try {
            String fileName = filterFileName(file.getOriginalFilename());
            String path = fileService.getUploadFolder();
            File folder = new File(path);
            if(!folder.exists()){
                folder.mkdirs();
            }
            File saveFile = new File(path, fileName);
            file.transferTo(saveFile);
        } catch (IOException | IllegalStateException ex) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping(value = "/delete.action")
    public String delete(
            String name,
            HttpSession session
    ) {
        boolean result;
        try {
            result = fileService.deleteFile(name);
        } catch (Exception ex) {
            
        }
        return "redirect:/operator/file/list";
    }
    
    @RequestMapping(value = "/rename.action")
    public String rename(
            String name,
            String newName,
            HttpSession session
    ) {
        boolean result;
        try {
            result = fileService.rename(name,newName);
        } catch (Exception ex) {
            
        }
        return "redirect:/operator/file/list";
    }

}
