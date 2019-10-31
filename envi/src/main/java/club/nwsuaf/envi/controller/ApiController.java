package club.nwsuaf.envi.controller;

import club.nwsuaf.envi.service.TiffService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author zhaoxuyang
 */
@RestController
@RequestMapping("api")
public class ApiController {

    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    TiffService tiffService;

    /**
     * 返回文件列表
     *
     * @return
     */
    @RequestMapping("file/list")
    List<String> fileList() {
        return tiffService.fileList();
    }

    /**
     * 文件重命名
     *
     * @param source 源文件名
     * @param target 新文件名
     * @return 是否重命名成功
     */
    @RequestMapping("file/rename")
    boolean fileRename(String source, String target) {
        return tiffService.fileRename(source, target);
    }

    /**
     * 删除文件
     *
     * @param name 文件名称
     * @return 是否删除成功
     */
    @RequestMapping("file/delete")
    boolean fileDelete(String name) {
        return tiffService.fileDelete(name);
    }

    /**
     * 按关键词查找文件（文件名包含该关键词）
     *
     * @param keyword 关键词
     * @return 匹配的文件列表
     */
    @RequestMapping("file/search")
    List<String> fileSearch(String keyword) {
        return tiffService.fileSearch(keyword);
    }

    @PostMapping("upload/image")
    Map uploadImage(MultipartFile file) {
        return tiffService.uploadImage(file);
    }

    @PostMapping("upload/txt")
    Map uploadTxt(MultipartFile file, HttpSession session) {
        return tiffService.uploadTxt(file);
    }

    /**
     * 根据图像名称，返回图像的二进制序列
     *
     * @param name
     * @param request
     * @param response
     */
    @PostMapping("file/image")
    protected void fileImage(
            String name,
            HttpServletRequest request,
            HttpServletResponse response) {

        File file = tiffService.getFile(name);
        try (ServletOutputStream out = response.getOutputStream();
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream buf = new BufferedInputStream(fis)) {
            byte[] buffer = new byte[1024];
            while (buf.read(buffer) != -1) {
                out.write(buffer);
            }
            out.flush();
        } catch (IOException e) {
            LOG.error("读取图像[{}]异常:", file.getAbsolutePath(), e.toString());
        }
    }

    @RequestMapping("file/content")
    protected String fileContent(String name) {
        File file = tiffService.getFile(name);
        String result;
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            result = new String(bytes);
        } catch (IOException ex) {
            result = "读取文件异常: " + ex.toString();
            LOG.error(result);
        }
        return result;
    }

    @RequestMapping("log/list")
    List<String> logList() {
        return tiffService.logList();
    }

    /**
     * 根据日志名称，返回日志内容
     *
     * @param name 日志文件名称
     * @return 日志文件的内容或异常信息
     */
    @RequestMapping("log/content")
    protected String logContent(String name) {
        File file = tiffService.getLog(name);
        String result;
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            result = new String(bytes);
        } catch (IOException ex) {
            result = "读取日志异常: " + ex.toString();
            LOG.error(result);
        }
        return result;
    }

    /**
     * 根据文件名、XY坐标计算出该像素点的地理位置坐标 python3 tiff.py -i test.tif -x 1 -y 2
     * (675753.785, 4542562.99)->(107.0902001517882, 41.01532810342937)
     *
     * @param image 文件名
     * @param x x像素
     * @param y y像素
     * @return 地理位置坐标
     */
    @RequestMapping("query/ll")
    String imgXYToLonLat(String image, int x, int y) {
        return tiffService.imgXYToLonLat(image, x, y);
    }

    /**
     * 根据地理位置坐标（字符串）计算返回对应的图像的XY像素列表
     *
     * @param image 图像文件名
     * @param input 采样点的文件名
     * @步骤 将字符串保存成文件（文件名为当前时间）、执行计算、返回结果
     * @return 图像的XY像素列表
     */
    @RequestMapping("query/xy/batch")
    String batchLonLatToImgXY(String image, String input) {
        return tiffService.batchLonLatToImgXY(image, input);
    }

}
