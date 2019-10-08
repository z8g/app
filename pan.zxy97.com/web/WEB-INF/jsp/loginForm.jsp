<%-- 
    Document   : loginForm
    Created on : 2018-12-31, 15:17:17
    Author     : zhaoxuyang
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage="${webRoot}/error"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="${webRoot}/static/css/login.css" >
        <link rel="stylesheet" href="${webRoot}/static/css/a.css" >
    </head>

    <body>
        <form class="login_form" action="${webRoot}/login.action" method="post" name="form_login">
            <ul>
                <li>
                    <h2><spring:message code="website.loginform.title"/></h2>
                    <span class="required_notification">${msg}</span>
                </li>
                <li>
                    <label for="login_name"><spring:message code="website.loginform.username"/></label>
                    <input id="login_name" 
                           name="login_name" 
                           placeholder='<spring:message code="website.loginform.username.input.placeholder"/>' 
                           required 
                           title="<spring:message code="website.loginform.username.input.title"/>" 
                           value="${user.username}"/>
                    <span class="form_hint"><spring:message code="website.loginform.username.hint"/></span>
                </li>
                
                <li>
                    <label for="login_password"><spring:message code="website.loginform.password"/></label>
                    <input id="login_password" 
                           name="login_password" 
                           placeholder='<spring:message code="website.loginform.password.input.placeholder"/>' 
                           required 
                           title="<spring:message code="website.loginform.password.input.title"/>" 
                           value="${user.password}"
                           type="password" />
                    <span class="form_hint"><spring:message code="website.loginform.password.hint"/></span>
                </li>
                <li>                         
                    <button class="submit" type="submit"><spring:message code="website.loginform.login.button"/></button>
                </li>
                <li>
                    <a href="${webRoot}/registerForm.html"><spring:message code="website.header.register"/></a>
                </li>
            </ul>
        </form>
    </body>
</html>

