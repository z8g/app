package cn.edu.nwsuaf.deep4mcpred.service;

import cn.edu.nwsuaf.deep4mcpred.model.Job;
import cn.edu.nwsuaf.deep4mcpred.model.OutputData;
import cn.edu.nwsuaf.deep4mcpred.tools.DatetimeUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @date 2019-05-22 01:09
 * @author zhaoxuyang
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileComponent fileComponent;

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        //System.out.println(new FileServiceImpl().saveSequenceToFile("123","123"));
        FileServiceImpl fileServiceImpl = new FileServiceImpl();
        //System.out.println(fileServiceImpl.saveSequenceToFile("12345\n\n1224", "1232"));
        long start = System.currentTimeMillis();
        //System.out.println(fileServiceImpl.getOutputDataList("/home/zhaoxuyang/code/2019/20190516/test_webserver-0519/job/1/output.txt"));
        System.out.println(fileServiceImpl.formatPath("job", "11212", "input.txt"));

        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Job> getJobList() {
        List<Job> result = new ArrayList<>();

        File[] files = fileComponent.getJobRoot().listFiles();

        for (File folder : files) {
            result.add(getJobFromFile(folder));
        }

        return result;
    }

    private Job getJobFromFile(File file) {
        Job result = new Job();

        String time = DatetimeUtil.formatDate(file.lastModified());
        String jobId = file.getName();
        String status = "success";
        Set<OutputData> outputSet = getOutputDataSet(jobId);

        if (outputSet.isEmpty()) {
            status = "fail";
        }

        result.setTime(time);//修改时间
        result.setJobId(jobId);//Job ID
        result.setOutputDataSet(outputSet);//要输出的数据集合
        result.setStatus(status);//状态
        return result;
    }

    @Override
    public boolean saveFile(String sequence, String jobId) {
        boolean result = false;

        File saveFile = fileComponent.getSaveFile(jobId);
        PrintStream ps;
        try {
            ps = new PrintStream(saveFile);
            ps.print(sequence);
            logger.info("save string to file successed:" + saveFile.getAbsolutePath());
            result = true;
        } catch (FileNotFoundException ex) {
            logger.error("save string to file fail:" + ex);
        }
        return result;
    }

    @Override
    public boolean saveFile(MultipartFile uploadFile, String jobId) {
        boolean result = false;
        File saveFile = fileComponent.getSaveFile(jobId);
        try {
            uploadFile.transferTo(saveFile);
            logger.info("save upload file successed:" + saveFile.getAbsolutePath());
            result = true;
        } catch (IOException | IllegalStateException ex) {
            logger.error("save upload file  fail:" + saveFile.getAbsolutePath());
        }
        return result;
    }

    @Override
    public String readOutputFile(String jobId) {
        String result;
        Path path = fileComponent.getOutputFile(jobId).toPath();
        try {
            byte[] bytes = Files.readAllBytes(path);
            result = new String(bytes);
        } catch (IOException ex) {
            logger.error("readOutputFile Exception:" + ex);
            result = null;
        }
        return result;
    }

    @Override
    public Set<OutputData> getOutputDataSet(String jobId) {
        Set<OutputData> result = new TreeSet<>();
        Path path = fileComponent.getOutputFile(jobId).toPath();
        try {
            List<String> lineList = Files.readAllLines(path);
            for (String line : lineList) {
                LOG.info(line);
                OutputData outputData = new OutputData(line);
                LOG.info(outputData.toString());
                result.add(outputData);
            }
// 只测了小数据量，lambda表达式700，foreach300
//            lineList.stream()
//                    .map((line) -> new OutputData(line))
//                    .forEachOrdered((output) -> {
//                        result.add(output);
//                    });
            logger.info("getOutputDataList success:" + path.toAbsolutePath().toString());
        } catch (IOException ex) {
            logger.error("getOutputDataList Exception:" + ex);
        }
        return result;
    }

    @Override
    public String formatPath(String jobFolder, String... more) {
        Path jobFolderPath = Paths.get(jobFolder, more);
        return jobFolderPath.toAbsolutePath().toString();
    }

}
