package com.zxy97.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload {

    /**
     * 上传数据及保存文件
     *
     * @param saveFolder 保存在哪个文件夹
     * @param request
     * @param response
     * @return 上传的文件路径或者文件夹根路径
     */
    public static String upload(final String saveFolder, HttpServletRequest request, HttpServletResponse response) {
        File saveFolderFile = new File(saveFolder);
        saveFolderFile.mkdirs();
        
        String path = null;
        try {
            //设置上传的文件大小
            final int MEMORY_THRESHOLD = 1024 * 1024 * 512;
            final int MAX_FILE_SIZE = 1024 * 1024 * 1024;
            final int MAX_REQUEST_SIZE = 1024 * 1024 * 1024;

            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            if (!ServletFileUpload.isMultipartContent(request)) {
                //检测是否为多媒体上传,如果不是则停止
                PrintWriter writer = response.getWriter();
                writer.println("错误：表单必须包含 enctype=multipart/form-data");
                writer.flush();
                return path;
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
                        path = filePath;//记录文件上传到磁盘的位置
                    }
                }
            }
            return path;
        }catch (Exception ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            return path;
        }
    }
    
//    
//    private static String findMinLenString(String rootPath,String path){
//        int index = path.indexOf("/");
//        String str1 = path.substring(index);
//        if(rootPath.equals(str1)){//上传的是文件
//            return path;
//        }
//        return str1;
//    }
    
    public static void main(String []args){
        String root = "C:/test";
        
    }
    
}
