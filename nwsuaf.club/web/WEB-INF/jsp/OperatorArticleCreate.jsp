<%-- 
    Document   : projectCreate
    Created on : 2019-1-18, 17:26:20
    Author     : Administrator
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="文章创建页面" />
        </jsp:include>
        <link href="assets/plugins/summernote-master/summernote.css" rel="stylesheet" type="text/css" />
        <link href="assets/plugins/summernote-master/summernote-bs2.css" rel="stylesheet" type="text/css" />
    </head>

    <body class="sticky-header">

        <jsp:include page="_leftSide.jsp" />
        <div class="main-content" >

            <jsp:include page="_header.jsp" />

            <div class="wrapper">
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h2 class="header-title">文章创建页面</h2>

                            <form class="form-horizontal" action="operator/article/create.action" method="post">
                                <div class="form-group">

                                    <div class="col-sm-4">
                                        <label>文章标题：</label>
                                        <input class="form-control" type="text" name="title" placeholder="请输入文章的标题，40个字以内" required="required" maxlength="40" />
                                    </div>

                                    <div class="col-sm-2">
                                        <label>具体类型：</label>
                                        <select class="form-control" name="type">
                                            <c:forEach var="type" items="${types}">
                                                <option value="${type}">${type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                </div>
                                <div class="form-group col-md-12">
                                    <textarea id="summernote" name="content"></textarea>
                                </div>
                                <button class="btn btn-primary" type="submit"> 保存 </button>
                            </form>
                        </div>
                    </div>
                </div>

            </div>

            <jsp:include page="_footer.jsp"></jsp:include>
            </div>

        <jsp:include page="_script.jsp" />

        <script type="text/javascript" src="assets/plugins/summernote-master/summernote.js"></script>
        <script>
            $(document).ready(function() {
                $('#summernote').summernote();
            });
        </script>


    </body>

</html>
