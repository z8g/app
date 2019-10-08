<%-- 
    Document   : OperatorFileList
    Created on : 2019-2-17, 3:37:19
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="资源下载" />
        </jsp:include>
        <!--插件列表-->
        <link href="assets/plugins/datatables/css/jquery.datatables.min.css" rel="stylesheet" type="text/css"/>
        <!--插件列表-->
    </head>

    <body class="sticky-header">
        <jsp:include page="_leftSide.jsp" />

        <div class="main-content" >
            <jsp:include page="_header.jsp"/>
            <div class="wrapper">
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h2 class="header-title">文件列表</h2>
                            <div class="table-responsive">
                                <table id="example" class="display table">
                                    <thead>
                                        <tr>
                                            <th>文件名称</th>
                                            <th>文件大小</th>
                                            <th>上传时间</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="fileVO" items="${fileVOList}">
                                            <tr>
                                                <td><a href="download?file=${fileVO.name}" target="_blank">${fileVO.name}</a></td>
                                                <td>${fileVO.size}</td>
                                                <td>${fileVO.lastTime}</td>
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
    </body>

</html>

