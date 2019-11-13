package cn.edu.nwsuaf.deep4mcpred.controller;

import cn.edu.nwsuaf.deep4mcpred.model.Job;
import cn.edu.nwsuaf.deep4mcpred.model.OutputData;
import cn.edu.nwsuaf.deep4mcpred.service.FileService;
import cn.edu.nwsuaf.deep4mcpred.service.JobService;
import cn.edu.nwsuaf.deep4mcpred.service.SequenceService;
import cn.edu.nwsuaf.deep4mcpred.tools.ExcelUtil;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/job")
public class JobController {

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    FileService fileService;

    @Autowired
    JobService jobService;

    @Autowired
    SequenceService sequenceService;

    @RequestMapping("/folder")
    @ResponseBody
    public String jobFolder() {
        return sequenceService.test();
    }

    @GetMapping("")
    public String jobList(Model model, HttpSession session) {
        String jobId = jobService.getJobId(session);
        List<Job> jobList = fileService.getJobList();
        model.addAttribute("jobList", jobList);
        model.addAttribute("jobId", jobId);
        return "job.html";
    }

    @GetMapping(value = {"/{jobId}"})
    public String jobItem(@PathVariable String jobId, Model model, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        model.addAttribute("jobId", jobId);

        //避免重复提交
        boolean isLock = jobService.isLock(session);//是否加锁
        if (isLock) {
            result.put("message", "Your job is in progress. Please wait a moment.");
            return LOCK_PAGE;
        } else {

            Set<OutputData> outputSet = fileService.getOutputDataSet(jobId);
            boolean runResult = outputSet != null && !outputSet.isEmpty();
            model.addAttribute("runResult", runResult);
            model.addAttribute("outputSet", outputSet);
            return JOB_ITEM_PAGE;//如果已加锁，则返回等待信息
        }
    }

    @GetMapping(value = {"{jobId}.txt"})
    @ResponseBody
    public String readOutputTxt(@PathVariable String jobId) {
        return fileService.readOutputFile(jobId).replaceAll("\n", "<br />");
    }

    @GetMapping("{jobId}.json")
    @ResponseBody
    public Set<OutputData> readOutputJson(@PathVariable String jobId) {
        return fileService.getOutputDataSet(jobId);
    }

    /**
     * @url http://localhost:8080/job/4B339089.xls
     * @param jobId
     * @param response
     */
    @GetMapping("{jobId}.xls")
    @ResponseBody
    public void readOutputXls(
            @PathVariable String jobId,
            HttpServletResponse response) {
        Set<OutputData> outputDataSet = fileService.getOutputDataSet(jobId);//自己实现
        List<OutputData> list = new ArrayList<>(outputDataSet);//自己实现
        ExcelUtil.exportExcel(list, jobId, jobId, OutputData.class, jobId + ".xlsx", response);
    }

//python /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/Deep4mcPred.py /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/test_data.txt /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/2022.txt A,C,D fixed /home/zhaoxuyang/code/2019/20190521/test_webserver-0519
    /**
     * 上传文件 1.保存文件 2.运行python程序 3.获取输出文件 4.解析输出文件 5.处理显示数据
     *
     * @param categories 多选
     * @param file 文件框
     * @param session 用户访问网站即进入一个会话
     * @param inputType
     * @param model
     * @return
     */
    @RequestMapping("/upload")
    public String upload(
            MultipartFile file,
            @RequestParam(defaultValue = "A,C,D") String categories,
            @RequestParam(defaultValue = "fixed") String inputType,
            HttpSession session,
            Model model
    ) {
        return processInput(file, categories, inputType, session, model);
    }

    private final String LOCK_PAGE = "LockPage.html";
    private final String JOB_RESULT_PAGE = "JobResult.html";
    private final String JOB_ITEM_PAGE = "JobItem.html";

    /**
     * 填写序列 1.将序列保存成文件 2.运行python程序 3.获取输出文件 4.解析输出文件 5.处理显示数据
     *
     * @param categories
     * @param sequence 字符串序列，存储成文件
     * @param session
     * @param inputType
     * @param model
     * @return
     */
    @PostMapping("/sequence")
    public String sequence(
            String sequence,
            @RequestParam(defaultValue = "A,C,D") String categories,
            @RequestParam(defaultValue = "file") String inputType,
            HttpSession session,
            Model model
    ) {
        return processInput(sequence, categories, inputType, session, model);
    }

    /**
     * 处理输入
     *
     * @param inputData 文件或字符串
     * @param categories 类别，按英文逗号分割
     * @param inputType file或fixed
     * @param session HTTp会话
     * @param model 返回到视图的数据
     * @return 视图名称
     */
    private String processInput(
            Object inputData,
            @RequestParam(defaultValue = "A,C,D") String categories,
            @RequestParam(defaultValue = "file") String inputType,
            HttpSession session,
            Model model
    ) {
        Map<String, Object> result = new HashMap<>();
        String jobId = jobService.getJobId(session);//获取JobId

        //避免重复提交
        boolean isLock = jobService.isLock(session);//是否加锁
        if (isLock) {
            result.put("jobId", jobId);
            result.put("message", "Your job is in progress. Please wait a moment.");
            return LOCK_PAGE;
        } else {
            //result.put("userSubmitTime", DatetimeUtil.formatNow());
            jobService.lock(session);//加锁

            boolean isSave;
            if (inputData instanceof MultipartFile) {
                isSave = fileService.saveFile((MultipartFile) inputData, jobId);//保存文件
            } else {
                isSave = fileService.saveFile((String) inputData, jobId);//保存文件
            }
            boolean runResult = jobService.run(jobId, categories, inputType);//运行
            Set<OutputData> outputSet = fileService.getOutputDataSet(jobId);

            jobService.unlock(session);//解锁
            model.addAttribute("jobId", jobId);
            model.addAttribute("categories", categories);
            model.addAttribute("inputType", inputType);
            model.addAttribute("isSave", isSave);
            model.addAttribute("runResult", runResult);
            model.addAttribute("outputSet", outputSet);
            return JOB_RESULT_PAGE;//如果已加锁，则返回等待信息
        }
    }

}
