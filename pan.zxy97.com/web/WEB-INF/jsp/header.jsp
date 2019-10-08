<%-- 
    Document   : header
    Created on : 2018-12-31, 15:15:45
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html>
    <head>
        <title><spring:message code="website.header.login"/></title>
        <link href="${webRoot}/static/css/a.css" type="text/css" rel="stylesheet">
        <style>
            li{
               float: right;
               font-weight: bold;
               list-style-type: none;
               margin:6px 12px 6px 0px;
            }
            .logo{
               float:left;
               font-weight: bolder;
               margin:6px 12px 6px 0px;
            }
            .logo a{
                font-size: 24px;
            }
            .logo span{
                margin-left:20px;
                font:18px grey;
            }
        </style>
    </head>
    <body>
        <div class="logo">
            <a href="http://zxy97.com/"target="_blank"
               title='<spring:message code="website.logo.title"/>'>
                <spring:message code="website.header.logo.title"/>
            </a>
            <span><spring:message code="website.header.logo.body"/></span>
        </div>
        <ul>
            <li><a href="index.html?lang=en_US" target="_top">English</a></li>
            <li><a href="index.html?lang=zh_CN" target="_top">中文</a></li>
            <li><a href="loginForm.html" target="main"><spring:message code="website.header.login"/></a></li>
            <li><a href="registerForm.html" target="main"><spring:message code="website.header.register"/></a></li>
        </ul>
    </body>
</html>
