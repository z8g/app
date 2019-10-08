<%-- 
    Document   : index
    Created on : 2018-12-31, 15:17:17
    Author     : zhaoxuyang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + path;
    application.setAttribute("webRoot", basePath);//应用
%>

<!DOCTYPE html>
<html>
<head>
    <link rel="shortcut icon" href="${webRoot}/static/images/favicon.ico" />        
    <title><spring:message code="website.index.title"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <link href="${webRoot}/static/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
    <iframe src="${webRoot}/header.html"  name="header" class="header"></iframe>
    <iframe src="${webRoot}/userList.html" name="list" class="center list"></iframe>
    <iframe src="${webRoot}/main.html" name="main" class="center main" id="main"></iframe>
    <iframe src="${webRoot}/footer.html" name="footer" class="footer"></iframe>
</body>
</html>
