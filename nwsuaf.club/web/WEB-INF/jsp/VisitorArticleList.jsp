<%-- 
    Document   : ArticleList
    Created on : 2019-2-5, 22:17
    Author     : 赵栩旸
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="${articleList ne null ? articleList[0].type : '无'}" />
        </jsp:include>
        <!--插件列表-->
        <link href="assets/plugins/datatables/css/jquery.datatables.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/css/jquery.datatables-custom.css" rel="stylesheet" type="text/css"/>
        <!--插件列表-->
    </head>

    <body class="sticky-header">
        <jsp:include page="_leftSide.jsp"/>
        <div class="main-content" >
            <jsp:include page="_header.jsp"/>
            <div class="wrapper">

                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h2 class="header-title">${articleList ne null ? articleList[0].type : '无数据'}</h2>
                            <div class="table-responsive">
                                <table id="example" class="display table">
                                    <thead>
                                        <tr>
                                            <th>标题</th>
                                            <th>发布时间</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="article" items="${articleList}">
                                            <tr>
                                                <td><a href="article/details/${article.id}" target="_blank">${article.title}</a></td>
                                                <td>${article.gmtUpdate}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>  
                                </table>  
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <jsp:include page="_footer.jsp" />

        </div>

        <jsp:include page="_script.jsp" />

        <!--插件列表-->
        <script src="assets/plugins/datatables/js/jquery.datatables.min.js"></script>
        <script src="assets/pages/table-data.js"></script>

    </body>

</html>