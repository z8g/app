
package pan.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pan.util.PathUtil;

/**
 * @名称 文件上传服务类，为控制类提供文件上传
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class UploadService {
    /**
     * 上传数据及保存文件
     *
     * @param saveFolder 保存在哪个文件夹
     * @param request
     * @param response
     * @return 上传结果
     */
    public static boolean upload(
            final String saveFolder, 
            HttpServletRequest request, 
            HttpServletResponse response) {
        try {
            //设置上传的文件大小
            int MEMORY_THRESHOLD;
            int MAX_FILE_SIZE ;
            int MAX_REQUEST_SIZE;
            
            //默认上传的文件大小
            final int DEFAULT_MEMORY_THRESHOLD =1024 * 1024 * 512;
            final int DEFAULT_MAX_FILE_SIZE = 1024 * 1024 * 1024;
            final int DEFAULT_MAX_REQUEST_SIZE = 1024 * 1024 * 1024;
            try{
                MEMORY_THRESHOLD = Integer.valueOf(PathUtil.getValueByKey("MEMORY_THRESHOLD"));
                MAX_FILE_SIZE = Integer.valueOf(PathUtil.getValueByKey("MAX_FILE_SIZE"));
                MAX_REQUEST_SIZE = Integer.valueOf(PathUtil.getValueByKey("MAX_REQUEST_SIZE"));
            } catch(NumberFormatException e){
                MEMORY_THRESHOLD = DEFAULT_MEMORY_THRESHOLD;
                MAX_FILE_SIZE = DEFAULT_MAX_FILE_SIZE;
                MAX_REQUEST_SIZE = DEFAULT_MAX_REQUEST_SIZE;
            }
            
            
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            if (!ServletFileUpload.isMultipartContent(request)) {
                //检测是否为多媒体上传,如果不是则停止
                PrintWriter writer = response.getWriter();
                writer.println("错误：表单必须包含 enctype=multipart/form-data");
                writer.flush();
                return false;
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();// 实例化，开始配置上传参数
            factory.setSizeThreshold(MEMORY_THRESHOLD);// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));// 设置临时存储目录
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);// 设置最大文件上传值
            upload.setSizeMax(MAX_REQUEST_SIZE);// 设置最大请求值 (包含文件和表单数据)

            upload.setHeaderEncoding("UTF-8");// 防止中文乱码

            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String itemName = item.getName();
                        String filePath = saveFolder + "/" + itemName;
                        File file = new File(filePath);//要保存的文件
                        file.getParentFile().mkdirs();//为要保存的文件创建文件夹，否则无法保存
                        item.write(file);
                    }
                }
            }
            return true;
        }catch (Exception ex) {
            return false;
        }
    }
}
