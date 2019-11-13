package cn.edu.nwsuaf.deep4mcpred.controller;

import cn.edu.nwsuaf.deep4mcpred.service.SequenceService;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 访客控制器
 *
 * @author zhaoxuyang
 */
@Controller
public class VisitorController {
    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * 依赖注入
     *
     * @see cn.edu.nwsuaf.deep4mcpred.service.impl.sequenceServiceImpl;
     */
    @Autowired
    SequenceService sequenceService;

    /**
     * 根据类别ID获取样例数据，select标签的change事件
     *
     * @see http://localhost:8080/example?id=1
     * @param id 类别ID
     * @return
     */
    @RequestMapping(value = "/example")
    @ResponseBody
    public String selectTagChange(Integer id) {
        return sequenceService.getExample(id);
    }    
    
    @RequestMapping(value = "/query")
    public String queryJob(String jobId) {
        return "redirect:/job/"+jobId.trim();
    }

    /**
     * 进入主页
     *
     * @see http://localhost:8080/
     * @return resources/templates/home.html
     */
    @GetMapping(value = {"", "home"})
    public String home() {
        return "home.html";
    }

    @GetMapping("contact")
    public String contact() {
        return "contact.html";
    }

    @GetMapping("server")
    public String server() {
        return "server.html";
    }

    @GetMapping("help")
    public String about() {
        return "help.html";
    }

    /**
     * 输入命令
     *
     * @see http://localhost:8080/cmd?cmd=ls -l
     * @param cmd
     * @return
     */
//    @RequestMapping("/cmd")
//    @ResponseBody
//    public String index(String cmd) {
//        try {
//            String result = CommandUtil.run(cmd);
//            return result.replaceAll("\n", "<br />");
//        } catch (IOException | InterruptedException ex) {
//            return "error";
//        }
//    }
}
