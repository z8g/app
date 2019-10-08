package edu.monash.erc.pippin.controller;

import edu.monash.erc.pippin.model.Job;
import edu.monash.erc.pippin.model.Protein;
import edu.monash.erc.pippin.service.FileService;
import edu.monash.erc.pippin.service.PythonService;
import edu.monash.erc.pippin.service.SessionService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

//    private static final String[] strs = {
//        "<", "\\", "/", ":", "*", "#", "?",
//        "\"", "'", ">", "|", ",", "（", "）", "+"
//    }
    
    @RequestMapping(value = "/list.json", method = RequestMethod.GET)
    @ResponseBody
    public List<Job> jobList() {
        return fileService.getJobList();
    }

    @RequestMapping(value = "/get.json", method = RequestMethod.GET)
    @ResponseBody
    public Job getJob(String jobId) {
        return fileService.getJobById(jobId);
    }

    @RequestMapping(value = "/test.json", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "test";
    }

    /**
     * 上传文件事件
     * <ul>
     * <li>1. 根据会话ID获取JobID</li>
     * <li>2. 根据JobID创建目录，及其子目录calculatedFeatures</li>
     * <li>3. 将文件保存（JobId/input.fasta）</li>
     * <li>4. 调用Python计算，返回计算结果</li>
     * <li>5. 计算结果为true时，根据JobID取出输出文件</li>
     * <li>6. 将输出文件转换成JSON</li>
     * </ul>
     *
     * @param file fasta文件
     * @param session 会话
     * @return JSON格式Protein对象列表
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public List<Protein> upload(
            MultipartFile file,
            HttpSession session
    ) {
        List<Protein> result;
        String jobId = sessionService.getJobId(session);
        
        boolean createFolderResult = fileService.createFolder(jobId);
        assert createFolderResult;

        boolean saveFileSuccess = fileService.saveFile(jobId, file);
        assert saveFileSuccess;
        
        boolean pythonRunResult = pythonService.run(jobId);
        if (!pythonRunResult) {
            //fileService.removeFolder(jobId);
            return null;
        }

        result = fileService.getProteinListFromOutputFile(jobId);
        
        return result;
    }

    /**
     * 键入序列事件
     *
     * <ul>
     * <li>1. 根据会话ID获取JobID</li>
     * <li>2. 根据JobID创建目录，及其子目录calculatedFeatures</li>
     * <li>3. 将序列保存成（JobId/input.fasta）</li>
     * <li>4. 调用Python计算，返回计算结果</li>
     * <li>5. 计算结果为true时，根据JobID取出输出文件</li>
     * <li>6. 将输出文件转换成JSON</li>
     * </ul>
     *
     * @param sequence 序列
     * @param session 会话
     * @return JSON格式Protein对象列表
     */
    @RequestMapping(value = "/fill")
    @ResponseBody
    public List<Protein> fillAction(String sequence, HttpSession session) {
        List<Protein> result;
        String jobId = sessionService.getJobId(session);
        
        boolean createFolderResult = fileService.createFolder(jobId);
        assert createFolderResult;

        boolean saveFileSuccess = fileService.saveStringToFile(jobId, sequence);
        assert saveFileSuccess;
        
        boolean pythonRunResult = pythonService.run(jobId);
        if (!pythonRunResult) {
            //fileService.removeFolder(jobId);
            return null;
        }

        result = fileService.getProteinListFromOutputFile(jobId);

        return result;
    }

}
