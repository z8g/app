<%-- 
    Document   : findAuthor
    Created on : 2018-11-16, 20:46:12
    Author     : Administrator
--%>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage=""%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/login.css" >
        <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/a.css" >
    </head>

    <body>
        <form class="login_form" action="${pageContext.request.contextPath}/author/search" method="post">
            <ul>
                <li>
                    <h2>查找作者</h2>
                    <span class="required_notification">${msg}</span>
                </li>
                <li>
                    <label for="username">账号：</label>
                    <input id="username" name="username" placeholder="请填写作者的账号" required title="请填写作者的账号"/>
                    <span class="form_hint">请填写作者的账号</span>
                </li>
                <li>                         
                    <button class="submit" type="submit">查找</button>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/index">首页</a>
                </li>
            </ul>
        </form>
    </body>
</html>

