<%-- 
    Document   : OperatorProjectList
    Created on : 2019-2-5, 22:43:34
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="项目审核页面" />
        </jsp:include>
        <!--插件列表-->
        <link href="assets/plugins/datatables/css/jquery.datatables.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/datatables/css/jquery.datatables-custom.css" rel="stylesheet" type="text/css"/>
        <!--插件列表-->
    </head>

    <body class="sticky-header">
        <jsp:include page="_leftSide.jsp">
            <jsp:param name="active_xmsh" value="active" />
            <jsp:param name="active_xmgl" value="nav-active" />
        </jsp:include>
        <div class="main-content" >
            <jsp:include page="_header.jsp"/>
            <div class="wrapper">

                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h2 class="header-title">未审核的项目</h2>
                            <div class="table-responsive">
                                <table id="example" class="display table">
                                    <thead>
                                        <tr>
                                            <th>项目标题</th>
                                            <th>修改时间</th>
                                            <th>具体类型</th>
                                            <th>作者</th>
                                            <th>审核通过</th>
                                            <th>审核不通过</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="project" items="${operatorProjectList}">
                                            <tr>
                                                <td><a href="operator/project/details/${project.id}" target="_blank">${project.name}</a></td>
                                                <td>${project.gmtUpdate}</td>
                                                <td>${project.type}</td>
                                                <td><a href="${project.username}">${project.username}</a></td>
                                                <td><a href="operator/project/auditSuccess.action?id=${project.id}" class="item-success">通过</a></td>
                                                <td><a href="operator/project/auditFail.action?id=${project.id}" class="item-fail">不通过</a></td>
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
         <script>
            $('.item-success').click(function() {
                if (!confirm('确定审核通过？')) {
                    window.event.returnValue = false;
                }
            });
            $('.item-fail').click(function() {
                if (!confirm('确定审核不通过？')) {
                    window.event.returnValue = false;
                }
            });
        </script>
    </body>

</html>