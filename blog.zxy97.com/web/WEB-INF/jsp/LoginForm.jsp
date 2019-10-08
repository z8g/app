<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage="../"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/login.css" >
        <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/a.css" >
    </head>

    <body>
        <form class="login_form" action="${pageContext.request.contextPath}/author/login" method="post" name="form_login">
            <ul>
                <li>
                    <h2>登录</h2>
                    <span class="required_notification">${msg}</span>
                </li>
                <li>
                    <label for="login_name">账号：</label>
                    <input id="login_name" name="login_name" placeholder="请填写您的账号" required title="请填写您的账号"/>
                    <span class="form_hint">请填写您的账号</span>
                </li>
                <li>
                    <label for="login_password">密码：</label>
                    <input id="login_password" name="login_password" required="required" type="password" placeholder="请填写您的密码" />
                    <span class="form_hint">请填写您的密码</span>
                </li>
                <li>                         
                    <button class="submit" type="submit">登录</button>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/index">首页</a> | <a href="register">注册</a>
                </li>
            </ul>
        </form>
    </body>
</html>

