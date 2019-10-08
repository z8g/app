package com.zxy97.servlet;

import com.zxy97.util.MD5;
import com.zxy97.util.SHA;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HashServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");  
        String content = request.getParameter("content");        
        request.setAttribute("content", content);
        request.setAttribute("result_md5", MD5.md5(content));
        request.setAttribute("result_sha", SHA.sha1(content));
        request.getRequestDispatcher("Hash.jsp").forward(request, response);
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
        return "CaesarServlet";
    }
}
