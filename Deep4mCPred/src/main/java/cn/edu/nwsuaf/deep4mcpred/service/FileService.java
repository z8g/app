/*
 * Job服务接口，定义了提供给控制器的相关服务逻辑
 */
package cn.edu.nwsuaf.deep4mcpred.service;

import cn.edu.nwsuaf.deep4mcpred.model.Job;
import cn.edu.nwsuaf.deep4mcpred.model.OutputData;
import java.util.List;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

/**
 * @date 2019-05-22 01:09
 * @author zhaoxuyang
 */
public interface FileService {

    /**
     * 格式化文件路径
     *
     * @see formatPath("job","11212","input.txt")
     * @param jobFolder
     * @param more
     * @return 文件的绝对路径
     */
    public String formatPath(String jobFolder, String... more);

    /**
     * 将序列存储成文件
     *
     * @param sequence 序列
     * @param jobId
     * @return 是否存储成功
     */
    public boolean saveFile(String sequence, String jobId);

    /**
     * 将表单中的文件保存
     *
     * @param jobId
     * @param uploadFile 表单中的文件
     * @return
     */
    public boolean saveFile(MultipartFile uploadFile, String jobId);

    /**
     * 根据jobId读取输出文件的内容
     *
     * @param filePath
     * @return
     */
    public String readOutputFile(String filePath);

    /**
     * 根据jobId获取输出数据的列表
     *
     * @param jobId
     * @return
     */
    public Set<OutputData> getOutputDataSet(String jobId);
    
    /**
     * 从文件系统中获取JobList
     * @return 
     */
    public List<Job> getJobList();
}
