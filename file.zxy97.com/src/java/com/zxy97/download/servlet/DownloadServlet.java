package com.zxy97.download.servlet;

import static com.zxy97.download.util.Download.download;
import static com.zxy97.download.util.Download.getFileName;
import com.zxy97.download.util.GetPath;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DownloadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();
        String url = request.getParameter("url");
        String webRootPath = new GetPath().getWebRootPath();

        String folderName = webRootPath + "WEB-INF/downloads/" + sessionId + "/";
        File folder = new File(folderName);
        String folderPath = folder.getAbsolutePath();
        String fileName = sessionId + getFileName(url);
        boolean isDownload = download(folderPath, fileName, url);
        if(!isDownload){
            try (PrintWriter printWriter = response.getWriter()) {
                printWriter.println(url + "下载失败!");
            }
            return;
        }
        String saveFilePath = folderPath + File.separator + fileName;
        File file = new File(saveFilePath);
        String downloadPath = file.getAbsolutePath();
        session.setAttribute("downloadPath", downloadPath);
        response.sendRedirect("download.jsp");
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
        return "根据指定url下载，然后转发给用户。";
    }
}
