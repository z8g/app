<%-- 
    Document   : userList
    Created on : 2018-12-31, 15:15:12
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="404.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title><spring:message code="website.userlist.title"/></title>
        <link type="text/css" rel="stylesheet" href="${webRoot}/static/css/a.css" >
        <link type="text/css" rel="stylesheet" href="${webRoot}/static/css/table.css" >
    </head>
<body>
    <hr>
    <table class="style">
        <tr><th><spring:message code="website.userlist.title"/></th></tr>
        <c:forEach var="user" items="${userList}">
            <tr><td><a href="visit.html?tid=${user.id}" target="main" title="<spring:message code="website.userlist.visit"/>">${user.username}</a></td></tr>
        </c:forEach>      
    </table>
</body>
</html>