<%-- 
    Document   : error500
    Created on : 2019-2-4, 20:59:43
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<jsp:include page="_header.jsp">
    <jsp:param name="title" value="500" />
</jsp:include>
<h3>服务器内部错误</h3>
<p>这个错误来自服务器内部，说明程序员要加班了。</p>
<jsp:include page="_footer.jsp" />
