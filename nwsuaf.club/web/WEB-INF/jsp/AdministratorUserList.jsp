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
            <jsp:param name="title" value="用户列表" />
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
                            <h2 class="header-title">用户列表</h2>
                            <div class="table-responsive">
                                <table id="example" class="display table">
                                    <thead>
                                        <tr>
                                            <th>身份</th>
                                            <th>账号</th>
                                            <th>密码</th>
                                            <th>姓名</th>
                                            <th>性别</th>
                                            <th>生日</th>
                                            <th>注册时间</th>
                                            <th>删除</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" items="${administratorUserList}">
                                            <c:set var="itemAuth">${item.auth}</c:set>
                                            <c:if test="${ itemAuth ne '系统管理员'}">
                                                <tr>
                                                    <c:choose>
                                                        <c:when test="${itemAuth eq '用户'}">
                                                            <td><a href="administrator/user/updateToOperator.action?username=${item.username}" class="item-updateToOperator">${item.auth}</a></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td><a href="administrator/user/updateToUser.action?username=${item.username}" class="item-updateToUser">${item.auth}</a></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td><a href="${item.username}" target="_blank">${item.username}</a></td>
                                                    <td>${item.password}</td>
                                                    <td>${item.realname}</td>
                                                    <td>${item.gender}</td>
                                                    <td>${item.birth}</td>
                                                    <td>${item.gmtCreate}</td>
                                                    <td><a href="administrator/user/updateToOperator.action?username=${item.username}" class="item-delete" btn btn-danger>删除</a></td>
                                                </tr>   
                                            </c:if>
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
            $('.item-updateToUser').click(function() {
                if (!confirm('是否将其身份更改为普通用户？')) {
                    window.event.returnValue = false;
                }
            });
            $('.item-updateToOperator').click(function() {
                if (!confirm('是否将其身份更改为操作员？')) {
                    window.event.returnValue = false;
                }
            });
        </script>
    </body>

</html>