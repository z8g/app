package com.zxy97.servlet;

import com.zxy97.caesar.CaesarEncryption05;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CaesarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String content = request.getParameter("content");
        String key = request.getParameter("key");
        String res = request.getParameter("res");
        String isDecode = request.getParameter("isDecode");
        
        String result = CaesarEncryption05.caesarEncode(content,Integer.valueOf(key), res, Boolean.valueOf(isDecode));
        HttpSession session = request.getSession(true);
        session.setAttribute("content", content);
        session.setAttribute("key", key);
        session.setAttribute("res", res);
        session.setAttribute("isDecode", isDecode);
        session.setAttribute("result", result);
        
        request.getRequestDispatcher("CaesarEncryption.jsp").forward(request, response);
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
