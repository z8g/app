<%-- 
     _                                 ___ _____                    
  __| |_      ______  ______  ___   _ / _ \___  |___ ___  _ __ ___  
 / _` \ \ /\ / /_  / |_  /\ \/ / | | | (_) | / // __/ _ \| '_ ` _ \ 
| (_| |\ V  V / / / _ / /  >  <| |_| |\__, |/ /| (_| (_) | | | | | |
 \__,_| \_/\_/ /___(_)___|/_/\_\\__, |  /_//_/(_)___\___/|_| |_| |_|
                                |___/                               
    Document   : index.jsp
    Created on : 2018-8-3, 3:59:27
    Author     : Administrator
--%>

<%@ page language="java" import="java.util.*,com.zxy97.dwz.*" pageEncoding="utf-8"%>

<%
    
    GetPath getPath = new GetPath();
    String url = getPath.getValueByKey("url");
    boolean isSelfLink = Boolean.valueOf(getPath.getValueByKey("isSelfLink"));

    String url_long = request.getParameter("url_long");
    String url_short = request.getParameter("url_short");
    if (url_short == null || "".equals(url_short)) {
        url_short = FileSystem.long2HexStr(new Date().getTime());
    }
    
    
    String showShortUrl;
    if (isSelfLink) {
        String urlSaveRoot = getPath.getValueByKey("urlSaveRoot");
        showShortUrl = url + urlSaveRoot + "/" + url_short;
    } else {
        showShortUrl = url + url_short;
    }
    String msg = null;
    if (FileSystem.create(url_short, url_long)) {
        msg = "长网址：" + url_long + "<br>短网址：" + showShortUrl;
        request.setAttribute("msg_url_short", showShortUrl);
    } else {
        msg = showShortUrl + " 已存在";
    }
    request.setAttribute("msg", msg);
%>

<jsp:forward page="index.jsp"></jsp:forward>