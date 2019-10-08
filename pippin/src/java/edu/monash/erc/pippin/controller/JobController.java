package edu.monash.erc.pippin.controller;

import edu.monash.erc.pippin.model.Job;
import edu.monash.erc.pippin.model.Protein;
import edu.monash.erc.pippin.service.FileService;
import edu.monash.erc.pippin.service.PythonService;
import edu.monash.erc.pippin.service.SessionService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    SessionService sessionService;

    @Autowired
    PythonService pythonService;

    @Autowired
    FileService fileService;

    static final String JOB_RESULT_PAGE = "JobResult";//一次Job结果的视图名称
    static final String JOB_LOCK_PAGE = "JobLock";//多次提交的视图名称

    /**
     * 获取JobID，由前端Ajax获取
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public String getJobId(HttpSession session) {
        return sessionService.getJobId(session);
    }

    /**
     * 获取Job List ,由前端Ajax获取
     *
     * @return
     */
    @RequestMapping(value = "/list.json", method = RequestMethod.GET)
    @ResponseBody
    public List<Job> jobList() {
        return fileService.getJobList();
    }

    /**
     * (txt格式)
     *
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/{jobId}.txt", method = RequestMethod.GET)
    @ResponseBody
    public String formatProteinListToTxt(@PathVariable String jobId) {
        return formatProteinListToString(jobId, "\t", "\n");
    }

    /**
     * (cvs格式)
     *
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/{jobId}.cvs", method = RequestMethod.GET)
    @ResponseBody
    public String formatProteinListToCvs(@PathVariable String jobId) {
        return formatProteinListToString(jobId, ",", "<br>");
    }

    /**
     * (json格式)
     *
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/{jobId}.json", method = RequestMethod.GET)
    @ResponseBody
    public List<Protein> formatProteinListToJson(@PathVariable String jobId) {
        return fileService.getProteinListFromOutputFile(jobId);
    }

    /**
     * (xlsx格式)
     *
     * @param jobId
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/{jobId}.xlsx", method = RequestMethod.GET)
    @ResponseBody
    public String formatProteinListToExcel(
            @PathVariable String jobId,
            HttpServletResponse response
    ) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(jobId + ".xlsx", "UTF-8"));
        return formatProteinListToString(jobId, "\t", "\n");
    }

    private String formatProteinListToString(String jobId, String split, String n) {
        List<Protein> proteinList = fileService.getProteinListFromOutputFile(jobId);
        StringBuilder buffer = new StringBuilder();
        buffer.append("id").append(split)
                .append("pre").append(split)
                .append("post").append(split)
                .append("class").append(n);
        proteinList.forEach((protein) -> {
            buffer.append(protein.getId()).append(split)
                    .append(protein.getPre()).append(split)
                    .append(protein.getPost()).append(split)
                    .append(protein.getClazz()).append(n);
        });
        return buffer.toString();
    }

    /**
     * 根据JobID获取结果
     *
     * @param jobId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{jobId}", method = RequestMethod.GET)
    public String jobItem(@PathVariable String jobId, Model model) {
        Job job = fileService.getJobById(jobId);
        model.addAttribute("job", job);
        System.out.println(job);
        return JOB_RESULT_PAGE;
    }

    /**
     * 上传文件事件
     *
     * @param file fasta文件
     * @param session 会话
     * @return JSON格式Protein对象列表
     */
    @RequestMapping(value = "/upload")
    public String upload(MultipartFile file, HttpSession session) {
        return processInput(file, session);
    }

    /**
     * 键入序列事件
     *
     * @param sequence 序列
     * @param session 会话
     * @return JSON格式Protein对象列表
     */
    @RequestMapping(value = "/fill")
    public String fillAction(String sequence, HttpSession session) {
        return processInput(sequence, session);
    }

    /**
     * 处理输入
     *
     * <ul>
     * <li>1. 根据会话ID获取JobID</li>
     * <li>2. 根据JobID创建目录，及其子目录calculatedFeatures</li>
     * <li>3. 将文件保存（JobId/input.fasta）</li>
     * <li>4. 调用Python计算，返回计算结果</li>
     * <li>5. 计算结果为true时，根据JobID取出输出文件</li>
     * <li>6. 将输出文件存入SpringMVC的Model</li>
     * <li>7. 返回视图名称（web/WEB-INF/jsp/xxx.jsp）</li>
     * </ul>
     *
     * @param inputData MultipartFile/String
     * @param session
     * @param model
     * @return
     */
    private String processInput(Object inputData, HttpSession session) {
        String jobId = sessionService.getJobId(session);

        if (sessionService.isLock(session)) {
            return JOB_LOCK_PAGE;//如果正在运行，则返回被锁页面
        }

        sessionService.lock(session);//加锁

        fileService.createFolder(jobId);//创建目录
        String saveFilePath;//文件保存路径
        if (inputData instanceof MultipartFile) {
            saveFilePath = fileService.saveFile(jobId, (MultipartFile) inputData);
        } else {
            saveFilePath = fileService.saveStringToFile(jobId, (String) inputData);
        }
        System.out.println("saveFilePath:" + saveFilePath);

        boolean pythonRunResult = pythonService.run(jobId);//Python运行结果
        System.out.println(pythonRunResult);

        sessionService.unlock(session);//解锁
        return "redirect:/job/" + jobId;
    }

//    private static final String[] strs = {
//        "<", "\\", "/", ":", "*", "#", "?",
//        "\"", "'", ">", "|", ",", "（", "）", "+"
//    }
}
