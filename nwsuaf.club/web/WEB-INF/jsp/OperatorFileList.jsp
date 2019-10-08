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
            <jsp:param name="title" value="操作员-文件管理-文件列表" />
        </jsp:include>
        <!--插件列表-->
        <link href="assets/plugins/datatables/css/jquery.datatables.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/dropzone/dropzone.css" rel="stylesheet" type="text/css" />
        <!--插件列表-->
    </head>

    <body class="sticky-header">
        <jsp:include page="_leftSide.jsp" />

        <div class="main-content" >
            <jsp:include page="_header.jsp"/>
            <div class="wrapper">
                <div class="row">
                    <div class="col-md-12">
                        <div class="dropzone" id="dropzone">
                            <div class="fallback">
                                <input name="uploadFile" type="file" />
                            </div>
                        </div>
                    </div>
                </div>
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
                                            <th>重命名</th>
                                            <th>删除</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="fileVO" items="${fileVOList}">
                                            <tr>
                                                <td><a href="download?file=${fileVO.name}" target="_blank">${fileVO.name}</a></td>
                                                <td>${fileVO.size}</td>
                                                <td>${fileVO.lastTime}</td>
                                                <td><button onclick="renameCheck('${fileVO.name}');" class="btn btn-primary" >重命名</button></td>
                                                <td><a href="operator/file/delete.action?name=${fileVO.name}" class="btn btn-danger item-delete">删除</a></td>
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
        <script src="assets/plugins/dropzone/dropzone.js"></script>
        <script>
               $('#example').dataTable();
                $("#dropzone").dropzone({
                    url: "${pageContext.request.contextPath}/operator/file/upload.action",
                    dictDefaultMessage: '<i class="fa fa-upload"></i> 点击或直接将文件(夹)拖到这里<hr /><span class="text-danger smail">警告：文件名称不要包含特殊符号！</span> <span class="text-primary smail">建议只包含：中文、英文、数字、下划线。</span>',
                    success: function() {
                        location.reload();
                    }
                });
                $('.item-delete').click(function() {
                    if (!confirm('是否删除？')) {
                        window.event.returnValue = false;
                    }
                });
                function renameCheck(oldName) {
                    var newName = prompt('请输入新名称', oldName);
                    if (newName !== null) {
                        window.location.href = "${pageContext.request.contextPath }/operator/file/rename.action?name=" + oldName + "&newName=" + newName;
                    } else {
                        window.event.returnValue = false;
                    }
                }
        </script>
        <!--插件列表-->
    </body>

</html>

