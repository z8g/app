package com.zxy97.make.servlet;

import com.zxy97.make.util.Util;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String softname = request.getParameter("softname");
        String softlogourl = request.getParameter("softlogourl");
        String softinfotitle = request.getParameter("softinfotitle");
        String softinfocontent = request.getParameter("softinfocontent");
        String softdownloadurl = request.getParameter("softdownloadurl");
        
        Util util = new Util();
        String webrootPath = util.getWebRootPath();
        
        String tempFilePath = webrootPath + "WEB-INF/temp.html";
        String tempFileContent = Util.readToString(tempFilePath);
        HashMap<String,String> map = new HashMap();
        map.put("softname", softname);
        map.put("softlogourl", softlogourl);
        map.put("softinfotitle", softinfotitle);
        map.put("softinfocontent", softinfocontent);
        map.put("softdownloadurl", softdownloadurl);
        String saveContent = Util.replace(tempFileContent, map);
        String saveFilePath = Util.getSaveFilePath();
        
        Util.writeFile(webrootPath + saveFilePath,saveContent);
        
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getContextPath() + "/";
        String urlPath =  basePath + saveFilePath;
        request.getSession(true).setAttribute("urlPath", urlPath);
        request.getRequestDispatcher("make.jsp").forward(request, response);
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
