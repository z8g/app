package club.nwsuaf.envi.service;

import club.nwsuaf.envi.util.CommandUtil;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author zhaoxuyang
 */
@Service
public class TiffService {

    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    CommandUtil commandUtil;

    @Value("${tiff.cmd.BatchLonLatToImgXY}")
    String cmdBatchLonLatToImgXY;

    @Value("${tiff.cmd.ImgXYToLonLat}")
    String cmdImgXYToLonLat;

    @Value("${tiff.root}")
    String tiffRoot;

    final String uploadFolderName = "upload";
    final String logFolderName = "logs";
    File uploadFolder;
    File logFolder;

    @PostConstruct
    void init() {
        uploadFolder = new File(tiffRoot, uploadFolderName);
        logFolder = new File(tiffRoot, logFolderName);
        uploadFolder.mkdirs();
        logFolder.mkdirs();
    }

    public String imgXYToLonLat(@NotNull String imageName, int x, int y) {
        LOG.info("[imgXYToLonLat] image={}, x={}, y={}", imageName, x, y);
        File imageFile = new File(uploadFolder, imageName);
        String imagePath = imageFile.getAbsolutePath();
        String cmd = String.format(cmdImgXYToLonLat, imagePath, x, y);
        try {
            return commandUtil.exec(cmd);
        } catch (IOException | InterruptedException ex) {
            LOG.error(ex.toString());
            return ex.toString();
        }
    }

    public List<String> fileSearch(String keyword) {
        return Arrays.stream(uploadFolder.listFiles())
                .map(File::getName)
                .filter(name -> name.contains(keyword))
                .sorted()
                .collect(toList());
    }

    public List<String> fileList() {
        return Arrays.stream(uploadFolder.listFiles())
                .map(File::getName)
                .sorted()
                .collect(toList());
    }

    public List<String> logList() {
        return Arrays.stream(logFolder.listFiles())
                .map(File::getName)
                .sorted()
                .collect(toList());
    }

    public boolean fileRename(@NotNull String source, @NotNull String target) {
        File sourceFile = new File(uploadFolder, source);
        if (!sourceFile.exists()) {
            LOG.error("文件不存在！");
            return false;
        }

        File targetFile = new File(uploadFolder, target);
        boolean result = sourceFile.renameTo(targetFile);

        LOG.info("[rename] {} to {}, result={}", source, target, result);
        return result;
    }

    public boolean fileDelete(@NotNull String imageName) {
        File file = new File(uploadFolder, imageName);
        boolean result = file.delete();
        LOG.info("[delete] {}, result={}", imageName, result);
        return result;
    }

//    /**
//     * 只能上传以下格式的文件
//     */
//    String[] suffixArray = {
//        ".tif", ".tiff", ".txt", ".input", ".output"
//    };
//
//    private boolean checkFileType(String fileName) {
//        if (fileName != null) {
//            for (String suffix : suffixArray) {
//                if (fileName.endsWith(suffix)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    public File getFile(String imageName) {
        File result = new File(uploadFolder, imageName);
        if (!result.exists()) {
            LOG.error("[getFile] 文件不存在:{}", result.getAbsolutePath());
        }
        return result;
    }

    public File getLog(String logName) {
        File result = new File(logFolder, logName);
        if (!result.exists()) {
            LOG.error("[getLog] 日志文件不存在:{}", result.getAbsolutePath());
        }
        return result;
    }

    public String batchLonLatToImgXY(String image, String input) {
        File imageFile = new File(uploadFolder, image);
        if (!imageFile.exists()) {
            LOG.error("文件不存在: {}", imageFile.getAbsolutePath());
            return "图像文件不存在" + imageFile.getAbsolutePath();
        }

        File inputFile = new File(uploadFolder, input);
        if (!inputFile.exists()) {
            LOG.error("文件不存在: {}", inputFile.getAbsolutePath());
            return "采样点文件不存在" + inputFile.getAbsolutePath();
        }

        String imageFilePath = imageFile.getAbsolutePath();
        String inputFilePath = inputFile.getAbsolutePath();
        String outputFilePath = createOutputPath(imageFile, inputFile);
        String cmd = String.format(cmdBatchLonLatToImgXY,
                imageFilePath, inputFilePath, outputFilePath);
        try {
            commandUtil.exec(cmd);
        } catch (IOException | InterruptedException ex) {
            LOG.error(ex.toString());
            return "执行命令异常:" + ex.toString();
        }

        try {
            byte[] outputContent = Files.readAllBytes(Paths.get(outputFilePath));
            String result = new String(outputContent);
            LOG.info("读取输出文件: {}\n{}", outputFilePath, result);
            return result;
        } catch (IOException ex) {
            LOG.error("读取输出文件[{}]异常: {}", outputFilePath, ex.toString());
            return "读取输出文件异常：" + outputFilePath;
        }
    }

    public Map uploadImage(MultipartFile uploadFile) {
        String uploadFileName = uploadFile.getOriginalFilename();
        String saveFileName = getFileName(uploadFileName) + ".tif";
        boolean success = saveUploadFile(uploadFile, saveFileName);
        Map result = new HashMap<>();
        result.put("success", success);
        result.put("name", saveFileName);
        return result;
    }

    public Map uploadTxt(MultipartFile uploadFile) {
        String uploadFileName = uploadFile.getOriginalFilename();
        String saveFileName = getFileName(uploadFileName) + ".input";
        boolean success =  saveUploadFile(uploadFile, saveFileName);
        Map result = new HashMap<>();
        result.put("success", success);
        result.put("name", saveFileName);
        return result;
    }
    

    private String createOutputPath(File imageFile, File inputFile) {
        String imageFileName = getFileName(imageFile.getName());
        String inputFileName = getFileName(inputFile.getName());
        return imageFileName + "-" + inputFileName;
    }

    private String getFileName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return lastIndex < 1 ? name : name.substring(0, lastIndex);
    }

    private Boolean saveUploadFile(MultipartFile uploadFile, String fileName) {
        LOG.info("[save file] {}",fileName);
        File file = new File(uploadFolder, fileName);
        try {
            uploadFile.transferTo(file);
            LOG.info("[upload][success] {}", file.getAbsolutePath());
            return true;
        } catch (IOException | IllegalStateException ex) {
            LOG.error("[upload][exception] {}", ex);
            return false;
        }
    }
}
