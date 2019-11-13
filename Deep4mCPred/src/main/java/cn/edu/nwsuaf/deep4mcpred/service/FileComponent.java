/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.nwsuaf.deep4mcpred.service;

import java.io.File;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @date 2019-05-22 01:09
 * @author zhaoxuyang
 */
@Component
public class FileComponent {

    @Value("${python.folder}")
    private String pythonFolder;

    @Value("${job.input.filename}")
    private String jobInputFileName;

    @Value("${job.output.filename}")
    private String jobOutputFileName;
    
    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public File getJobRoot() {
        return new File(pythonFolder, "job");
    }    
    
    public String getJobFolderPath(String jobId) {
        return getJobFolder(jobId).getAbsolutePath();
    }

    public File getJobFolder(String jobId) {
        return new File(getJobRoot(), jobId);
    }

    public File getSaveFile(String jobId) {
        File jobFolder = getJobFolder(jobId);

        jobFolder.mkdirs();//联级创建文件夹，不需要父文件夹存在
        LOG.info("Create jobFolder:" + jobFolder.getAbsolutePath());

        File saveFile = new File(jobFolder, jobInputFileName);
        return saveFile;
    }

    public File getOutputFile(String jobId) {
        File jobFolder = getJobFolder(jobId);
        File outputFile = new File(jobFolder, jobOutputFileName);
        return outputFile;
    }

}
