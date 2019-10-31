package club.nwsuaf.envi.config;

import club.nwsuaf.envi.service.TiffService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 异常 controller 增强器
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    
    @Autowired TiffService tiffService;
    
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值 绑定后请在后面注释作者
     *
     * @param model
     * @param session
     */
    @ModelAttribute
    public void addAttributes(Model model, HttpSession session) {
        //文件列表
        List<String> tiffFileList = tiffService.fileList();

        //日志列表
        List<String> tiffLogList = tiffService.logList();
        
        model.addAttribute("tiffLogList", tiffLogList);
        model.addAttribute("tiffFileList", tiffFileList);
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
//    @ExceptionHandler(value = BlogException.class)
//    public ModelAndView errorHandler(BlogException ex) {
//        ModelAndView result = new ModelAndView();
//        result.addObject("exceptionTitle", ex.getType().getTitle());
//        result.addObject("exceptionMessage", ex.getMessage());
//        result.setViewName("Exception.html");
//        return result;
//    }

}
