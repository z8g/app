<%-- 
    Document   : userUpdate
    Created on : 2019-1-18, 17:32:22
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="创建项目" />
        </jsp:include>
    </head>
    <body class="sticky-header">
        <jsp:include page="_leftSide.jsp" />
        <div class="main-content" >

            <jsp:include page="_header.jsp" />

            <div class="wrapper">

                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h2 class="header-title"> 创建项目 </h2>
                            <form class="form-horizontal" action="user/project/create.action">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">项目名称：</label>
                                    <div class="col-md-10">
                                        <input class="form-control" name="name" type="text" placeholder="请输入项目名称" required />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">具体类型：</label>
                                    <div class="col-md-10">
                                        <select name="type" class="form-control" required="required">
                                            <c:forEach var="item" items="${types}">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">项目简介：</label>
                                    <div class="col-md-10">
                                        <textarea name="content" class="form-control" placeholder="请用一段话介绍该项目，1000字以内" rows="4" maxlength="1000"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">队员要求：</label>
                                    <div class="col-md-10">
                                        <textarea name="required" class="form-control" placeholder="填写申请者需要具备什么，200字以内" rows="4" maxlength="200"></textarea>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-2 control-label">申请截止日期：</label>
                                    <div class="col-md-10">
                                        <input name="gmtEnd" class="form-control" type="date" placeholder="请输入申请截止日期">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">
                                        <a href="http://file.zxy97.com/" target="_blank"><span class="label label-primary">[ 上传LOGO ]</span></a>
                                        LOGO：
                                    </label>
                                    <div class="col-md-10">
                                        <input name="logo" class="form-control" type="url" placeholder="请输入项目LOGO的URL地址，要求图片宽高比为3:2">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-2">
                                        <input class="form-control btn btn-primary" type="submit" value="完成创建">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <jsp:include page="_footer.jsp" />

            </div>
            <jsp:include page="_script.jsp" />

    </body>

</html>
