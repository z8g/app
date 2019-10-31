package club.nwsuaf.envi.controller;

import club.nwsuaf.envi.service.TiffService;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author zhaoxuyang
 */
@Controller
public class GuestController {

    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    TiffService tiffService;

    @GetMapping(value = {"manage"})
    String manage(Model model) {
        return "manage.html";
    }
    @GetMapping(value = {"index", "index.html",""})
    String index(Model model) {
        return "index.html";
    }

    @GetMapping(value = {"upload"})
    String upload(Model model) {
        return "upload.html";
    }

//    @PostMapping("upload/image")
//    String uploadImage(MultipartFile file, HttpSession session) {
//        String msg = tiffService.uploadImage(file);
//        session.setAttribute("msg", msg);
//        return "redirect:/manage";
//    }
//
//    @PostMapping("upload/txt")
//    String uploadTxt(MultipartFile file, HttpSession session) {
//        String msg = tiffService.uploadTxt(file);
//        session.setAttribute("msg", msg);
//        return "redirect:/manage";
//    }
    
}
