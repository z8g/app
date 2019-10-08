package edu.monash.erc.pippin.service.impl;

import edu.monash.erc.pippin.model.Job;
import edu.monash.erc.pippin.model.Protein;
import edu.monash.erc.pippin.service.FileService;
import edu.monash.erc.pippin.util.DatetimeUtil;
import edu.monash.erc.pippin.util.PathUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private String getJobFolderPath(String jobId) {
        return new File(getJobRoot(), jobId).getAbsolutePath();
    }

    private String getJobRoot() {
        return new PathUtil().getWebRootPath() + "/WEB-INF/python/job/";
    }

    @Override
    public boolean saveStringToFile(String jobId, String content) {
        String folderPath = getJobFolderPath(jobId);
        File saveFile = new File(folderPath, "input.fasta");
        String filePath = saveFile.getAbsolutePath();
        System.out.format("> create file：%s\n", filePath);
        Path targetPath = Paths.get(filePath);
        try (BufferedWriter writer = java.nio.file.Files.newBufferedWriter(targetPath,
                StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(content);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean createFolder(String jobId) {
        File folder = new File(getJobRoot(), jobId + "/calculatedFeatures");
        System.out.println(String.format("> create folder:[%s]", folder.getAbsolutePath()));
        return folder.mkdirs();
    }

    @Override
    public boolean saveFile(String jobId, MultipartFile uploadFile) {
        boolean result = true;
        try {
            String folderPath = getJobFolderPath(jobId);
            File saveFile = new File(folderPath, "input.fasta");
            uploadFile.transferTo(saveFile);
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(FileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    @Override
    public boolean removeFolder(String jobId) {
        File folder = new File(getJobRoot(), jobId);
        System.out.println(String.format("> remove folder:[%s]", folder.getAbsolutePath()));
        return folder.delete();
    }

    @Override
    public List<Protein> getProteinListFromOutputFile(String jobId) {
        File outputFile = new File(getJobFolderPath(jobId), "predict_output.csv");
        List<Protein> result = parserFromFile(outputFile);
        return result;
    }

    private List<Protein> parserFromFile(File file) {
        List<Protein> result = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            lines.stream().forEach((line) -> {
                Protein protein = parser(line);
                if (protein != null) {
                    result.add(protein);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(MethodHandles.lookup().lookupClass().getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * 对每一行解析成Protein对象
     *
     * @param line
     * @return
     */
    private static Protein parser(String line) {
        Protein result = new Protein();
        if (line.endsWith("Class")) {
            return null;
        }

        String[] strs = line.split("\\s+");
        System.out.println(Arrays.toString(strs));
        result.setId(strs[1]);
        result.setPre(new BigDecimal(strs[2]));
        result.setPost(new BigDecimal(strs[3]));
        result.setClazz(strs[4]);

        return result;
    }

    @Override
    public List<Job> getJobList() {
        List<Job> result = new ArrayList<>();
        File jobRootFolder = new File(getJobRoot());
        File[] files = jobRootFolder.listFiles();

        for (File folder : files) {
            result.add(getJobFromFile(folder));
        }

        return result;

    }

    @Override
    public Job getJobById(String jobId) {
        return getJobFromFile(new File(getJobFolderPath(jobId)));
    }

    private Job getJobFromFile(File file) {
        Job result = new Job();
        
        String time = DatetimeUtil.formatDateTime(file.lastModified());
        String fileName = file.getName();
        String status = "success";
        List<Protein> proteinList = getProteinListFromOutputFile(fileName);
        
        if( proteinList.isEmpty()){
            status = "fail";
        }
        
        result.setTime(time);
        result.setJobId(fileName);
        result.setProteinList(proteinList);
        result.setStatus(status);
        return result;

    }
}
