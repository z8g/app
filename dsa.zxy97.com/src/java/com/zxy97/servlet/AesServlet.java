/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zxy97.servlet;

import com.zxy97.aes.ZipCipherUtil;
import com.zxy97.util.GetPath;
import com.zxy97.util.Upload;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class AesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        GetPath getPath = new GetPath();
        String webRootPath = getPath.getWebRootPath();
        HttpSession session = request.getSession(true);
        String uId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        
        //获取上传文件的保存路径
        String srcFile = Upload.upload(webRootPath + "WEB-INF/" + uId, request, response);
        String fne = ZipCipherUtil.FILE_NAME_EXTENSION;//扩展名
        

        String keyStr = (String)session.getAttribute("AesKey");
        boolean isEncode = Boolean.valueOf((String)session.getAttribute("isEncode"));
        if(keyStr == null){
            keyStr = "zxy97";
        }
        
//        PrintWriter out = response.getWriter();
//        try {
//            out.println(srcFile);  
//                        out.println("<br>");  
//
//            out.println(webRootPath + "WEB-INF/" + sessionId);     out.println("<br>");  
//            out.println(keyStr);     out.println("<br>");  
//            out.println(destFile);     out.println("<br>");  
//        } finally {
//            out.close();
//        }
        try {
            if(isEncode){
                String destFile = srcFile + "." + fne;//压缩后的路径
                ZipCipherUtil.encryptZip(srcFile, destFile, keyStr);//进行加密压缩
                session.setAttribute("downloadPath", destFile);
            } else {
                File file = new File(srcFile);
                File []files = file.getParentFile().listFiles();
                String destFolder = files[0].getAbsolutePath();
                ZipCipherUtil.decryptUnzip(srcFile, destFolder, keyStr);//进行加密压缩
                session.setAttribute("downloadPath", destFolder);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(AesServlet.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        response.sendRedirect("download.jsp");
        
        
      
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
