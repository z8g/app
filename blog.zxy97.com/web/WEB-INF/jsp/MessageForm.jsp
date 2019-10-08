<%-- 
    Document   : index
    Created on : 2018-11-10, 13:36:07
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>zxy97博客 - 记录美好回忆</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editormd.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
        <link rel="shortcut icon" href="http://zxy97.com/images/favicon.png" type="image/x-icon" />
    </head>
    <body>
        <a name="top"></a>
        <div id="layout">
            <div id="header">
                <h1><i class="editormd-logo editormd-logo-3x"></i></h1>
                <h1>敬请期待……</h1>
                
                <div class="menu">
                    <a href="${pageContext.request.contextPath}/index"><i class="fa fa-search"></i>返回首页</a>
                </div>
            </div>        
            <div class="clear"></div>
            <div class="footer">                  
                &copy; 2018  - <a href="zhaoxuyang" title="开发者的博客">zhaoxuyang</a> @ <a href="http://zxy97.com/" target="_blank" title="zxy97.com">zxy97.com</a>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
    </body>
</html>