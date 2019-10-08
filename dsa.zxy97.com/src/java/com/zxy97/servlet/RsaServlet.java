/*
 * 处理Servlet类，使用策略模式，根据请求的参数op进行不同的处理
 */

package com.zxy97.servlet;

import com.zxy97.model.Model;
import com.zxy97.service.RsaServiceImpl;
import com.zxy97.util.JsonUtil;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RsaServlet extends HttpServlet {

    static RsaServiceImpl service = new RsaServiceImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");  
        String op = request.getParameter("op");//操作
        
        if(op==null || "".equals(op)){
            
        } else if("sign".equals(op)){
            String message = request.getParameter("message");
            Model model =  service.sign(message);
            HashMap<String, Object> result = new HashMap<>();
            result.put("sign", model.getSign());
            result.put("encoded", model.getEncodedData());
            result.put("publicKey", RsaServiceImpl.publicKey);
            response.getWriter().print(JsonUtil.toJson(result));
        } else if("signVerify".equals(op)){
            String message = request.getParameter("message");
            String publicKey = request.getParameter("publicKey");
            String sign = request.getParameter("sign");
            Model signModel = new Model();
            signModel.setMessage(message);
            signModel.setPublicKey(publicKey);
            signModel.setSign(sign);
            boolean verify =  service.signVerify(signModel);
            HashMap<String, Object> result = new HashMap<>();
            result.put("verify", verify);
            response.getWriter().print(JsonUtil.toJson(result));
        } else if("hash".equals(op)){
            String message = request.getParameter("message");
            String md5 =  service.md5(message);
            String sha1 =  service.sha1(message);
            HashMap<String, Object> result = new HashMap<>();
            result.put("md5", md5);
            result.put("sha1", sha1);
            response.getWriter().print(JsonUtil.toJson(result));
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
        return "RsaServlet";
    }
}
