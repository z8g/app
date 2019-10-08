package com.zxy97.blog.servlet;

import com.zxy97.blog.util.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImageUploadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        JSONObject json = new JSONObject();

        int success;
        String message = "上传图片";
        String url = upload(request, response);
        if (url == null) {
            success = 0;
            url = "";
        } else {
            success =1;
            url = getURL(request.getRequestURL().toString()) + url;
        }
        json.put("success", success);
        json.put("message", message);
        json.put("url", url);
        try (PrintWriter out = response.getWriter()) {
            out.println(json.toString());
        }
    }
    
    //设置上传的文件大小
    static final int MEMORY_THRESHOLD = 1024 * 1024 * 512;
    static final int MAX_FILE_SIZE = 1024 * 1024 * 1024;
    static final int MAX_REQUEST_SIZE = 1024 * 1024 * 1024;
    static final String IMG_FOLDER = "img";//默认的存放img的根文件夹

    /**
     * @描述 上传数据及保存文件
     * @param request
     * @param response
     * @return 上传结果
     */
    public static String upload(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!ServletFileUpload.isMultipartContent(request)) {
                //检测是否为多媒体上传,如果不是则停止
                PrintWriter writer = response.getWriter();
                writer.println("错误：表单必须包含 enctype=multipart/form-data");
                writer.flush();
                return null;
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
                    if (!item.isFormField()) {
                        String itemName = item.getName();//获取文件名，进行验证
                        if (checkFileName(itemName)) {//验证通过
                            java.util.Date date = new java.util.Date();//保存当时的系统时间，也可以用别的命名方式
                            String path = "/" + IMG_FOLDER + new java.text.SimpleDateFormat("/yyyyMMddHHmmss").format(date) + getFileSuffix(itemName);//相对路径
                            String filePath = request.getSession().getServletContext().getRealPath("/") + path;
                            File file = new File(filePath);//要保存的文件
                            file.getParentFile().mkdirs();//为要保存的文件创建文件夹，否则无法保存
                            item.write(file);
                            return path;
                        }
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * @描述 获取文件后缀名
     */
    private static String getFileSuffix(final String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index >= 0) {
            return fileName.substring(index);
        }
        return fileName;
    }

    /**
     * @描述 根据允许上传的文件后缀，进行文件判断
     */
    private static boolean checkFileName(final String fileName) {
        String name = fileName.toLowerCase();//转小写
        for (String suffix : arrSuffix) {
            if (name.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @描述 允许上传的文件后缀
     */
    private static final String[] arrSuffix = {
        ".jpg", ".png", ".bmp", ".ico", ".jpge", ".gif"
    };

    /**
     * @param requestURL
     * @return
     * @描述 去掉Servlet带来的冗余路径
     */
    private static String getURL(String requestURL) {
        int index = requestURL.lastIndexOf("/");
        return requestURL.substring(0, index);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
