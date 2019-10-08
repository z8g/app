<%-- 
    Document   : error404
    Created on : 2019-2-4, 20:59:30
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="404" />
</jsp:include>
<h3>资源不存在</h3>
<p>您请求的资源不存在</p>
<jsp:include page="_footer.jsp" />