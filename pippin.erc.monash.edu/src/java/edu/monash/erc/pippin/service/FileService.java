package edu.monash.erc.pippin.service;

import edu.monash.erc.pippin.model.Job;
import edu.monash.erc.pippin.model.Protein;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * @名称 逻辑服务 - 文件
 * @作者 赵栩旸
 */
public interface FileService {

    /**
     * 将内容存成fasta文件
     *
     * @param jobId
     * @param sequence
     * @return
     */
    public boolean saveStringToFile(String jobId, String sequence);

    public boolean saveFile(String jobId, MultipartFile uploadFile);

    public boolean createFolder(String jobId);

    public boolean removeFolder(String jobId);

    public List<Protein> getProteinListFromOutputFile(String jobId);

    public List<Job> getJobList();

    public Job getJobById(String jobId);

}
