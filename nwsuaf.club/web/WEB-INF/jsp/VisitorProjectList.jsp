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
            <jsp:param name="title" value="创新创业服务平台" />
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

                <div class="panel-wrap">

                    <c:forEach var="item" items="${projectList}">
                        <div class="col-md-4">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <a href="project/${item.username}/details/${item.id}" target="_blank" title="${item.type}">${item.name}</a>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <img width="100%" src="${item.logo}" />
                                    <hr/>
                                    <p>${item.content}</p>
                                    <p class="text-center small">版权所有：<a href="${item.username}" target="_blank" title="作者">${item.username}</a> | ${item.gmtUpdate}</p>
                                </div>
                            </div>
                        </div>           
                    </c:forEach>

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