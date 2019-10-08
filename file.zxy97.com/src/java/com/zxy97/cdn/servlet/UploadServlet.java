package com.zxy97.cdn.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

    final static GetPath getPath = new GetPath();
    static final String saveFolder = getPath.getValueByKey("saveFolder");//默认的存放文件的根文件夹

    /**
     * @描述 上传数据及保存文件
     * @param request
     * @param response
     * @return 保存路径
     */
    public static String upload(HttpServletRequest request, HttpServletResponse response) {
        //设置上传的文件大小
        int memoryThreshold = Integer.valueOf(getPath.getValueByKey("memoryThreshold"));
        int maxFileSize = Integer.valueOf(getPath.getValueByKey("maxFileSize"));
        int maxRequestSize = Integer.valueOf(getPath.getValueByKey("maxRequestSize"));
        try {

            String characterEncoding = getPath.getValueByKey("characterEncoding");
            request.setCharacterEncoding(characterEncoding);
            response.setCharacterEncoding(characterEncoding);

            if (!ServletFileUpload.isMultipartContent(request)) {
                //检测是否为多媒体上传,如果不是则停止
                PrintWriter writer = response.getWriter();
                writer.println("错误：表单必须包含 enctype=multipart/form-data");
                writer.flush();
                return null;
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();// 实例化，开始配置上传参数
            factory.setSizeThreshold(memoryThreshold);// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));// 设置临时存储目录
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(maxFileSize);// 设置最大文件上传值
            upload.setSizeMax(maxRequestSize);// 设置最大请求值 (包含文件和表单数据)
            upload.setHeaderEncoding(characterEncoding);// 防止中文乱码

            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String itemName = item.getName();//获取文件名，进行验证
                        if (checkFileName(itemName)) {//验证通过
                            java.util.Date date = new java.util.Date();//保存当时的系统时间，也可以用别的命名方式
                            String fileName = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(date);
                            String path = String.format("/%s/%s%s", saveFolder, fileName, getFileSuffix(itemName));
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
            message = "允许上传的文件最大为" + getFileSize(maxFileSize);
            return null;
        }
    }

    /**
     * @描述 将文件长度转换成人性化的文件大小字符串
     */
    private static String getFileSize(long length) {
        if (length < 0) {
            return "";
        } else if (length < 1024) {
            return new DecimalFormat("#0.00").format(length) + "B";
        } else if (length < 1024 * 1024) {
            return new DecimalFormat("#0.00").format(length / 1024.0) + "KB";
        } else if (length < 1024 * 1024 * 1024) {
            return new DecimalFormat("#0.00").format(length / 1024.0 / 1024) + "MB";
        } else {
            return new DecimalFormat("#0.00").format(length / 1024.0 / 1024 / 1024) + "GB";
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
    private static boolean checkFileName(final String fileName) throws IOException {
        String name = fileName.toLowerCase();//转小写
        String[] arrSuffix = new GetPath().getSuffixArray();

        for (String suffix : arrSuffix) {
            suffix = "." + suffix;
            if (name.endsWith(suffix)) {
                return true;
            }
        }
        success = 0;
        message = "允许上传的文件类型为：" + Arrays.toString(arrSuffix);
        return false;
    }

    /**
     * @描述 去掉Servlet带来的冗余路径
     */
    private static String getURL(String requestURL) {
        int index = requestURL.lastIndexOf("/");
        return requestURL.substring(0, index);
    }

    private String toJson(int success, String message, String url) {
        return String.format("{\n\t\"success\":%d,\n\t\"message\":\"%s\",\n\t\"url\":\"%s\"\n}", success, message, url);
    }

    static int success;
    String url;
    static String message;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = upload(request, response);//上传
        if (path == null) {
            success = 0;
            url = "";
        } else {
            success = 1;
            url = getURL(request.getRequestURL().toString()) + path;
        }
        String json = toJson(success, message, url);
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.print(json);
        }
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
