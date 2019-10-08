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
            <jsp:param name="title" value="文章列表" />
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
                            <h2 class="header-title">文章列表</h2>
                            <div class="table-responsive">
                                <table id="example" class="display table">
                                    <thead>
                                        <tr>
                                            <th>文章标题</th>
                                            <th>具体类型</th>
                                            <th>修改时间</th>
                                            <th>修改</th>
                                            <th>删除</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="article" items="${articleList}">
                                            <tr>
                                                <td><a href="article/details/${article.id}" target="_blank">${article.title}</a></td>
                                                <td>${article.type}</td>
                                                <td>${article.gmtUpdate}</td>
                                                <td><a href="operator/article/update?id=${article.id}" target="_blank" class="item-update">修改</a></td>
                                                <td><a href="operator/article/delete.action?id=${article.id}" target="_blank" class="item-delete">删除</a></td>
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
        <!--插件列表-->
                <!--插件列表-->
        <script>
            $('.item-delete').click(function() {
                if (!confirm('是否删除？')) {
                    window.event.returnValue = false;
                }
            });
            $('.item-update').click(function() {
                if (!confirm('是否修改？')) {
                    window.event.returnValue = false;
                }
            });
        </script>
    </body>

</html>