<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="utf-8"%>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String filepath = (String) session.getAttribute("downloadPath");

    try {
        File file = new File(filepath);
        if (!file.exists()) {
            out.println("<p>文件不存在或者已删除-下载失败！</p>");
        } else {
            java.io.InputStream fis = new java.io.FileInputStream(file);
            java.io.OutputStream os = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(file.getName(), "utf-8"));
            response.addHeader("Content-Length", file.length() + "");
            response.setContentType("application/octet-stream");
            int data = 0;
            while ((data = fis.read()) != -1) {
                os.write(data);
            }
            out.clear();
            out = pageContext.popBody();
            os.close();
            fis.close();
        }
    } catch (Exception e) {
        out.printf("<h3>下载失败，可能是文件不存在！</h3>");
        out.println("<a href=\"index.jsp\">返回</a>");
    }

%>
</body>
</html>
