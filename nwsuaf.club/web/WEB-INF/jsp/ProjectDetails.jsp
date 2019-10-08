<%--
    Document   : projectDetails
    Created on : 2019-1-18, 17:27:10
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="" />
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
                            <h2 class="header-title">
                                ${projectDetails.name} 
                             </h2>
                             <p><a href="${projectDetails.username}">${projectDetails.username}</a>  发布于 ${projectDetails.gmtUpdate}</p>
                             <blockquote style="text-indent: 2em">${projectDetails.content}</blockquote>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="white-box">
                            <h2 class="header-title">项目LOGO</h2>
                            <img src="${projectDetails.logo}" width="100%" alt="LOGO">
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="white-box">
                                    <h2 class="header-title">项目类型</h2>
                                    <p>${projectDetails.type}</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="white-box">
                                    <h2 class="header-title">申请截止日期</h2>
                                    <p>${projectDetails.gmtEnd}</p>
                                </div>
                            </div>
                        </div>
                        <div class="white-box">
                            <h2 class="header-title">队员要求</h2>
                            <blockquote style="text-indent: 2em">${projectDetails.required}</blockquote>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h2 class="header-title">申请加入 [${projectDetails.name}]</h2>
                            <form class="form-horizontal" action="apply.action" method="post">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">姓名</label>
                                    <div class="col-md-10">
                                        <input type="hidden" name="username" value="${projectDetails.username}" />
                                        <input type="hidden" name="projectId" value="${projectDetails.id}" />
                                        <input name="realname" class="form-control" required="required" value="" type="text" placeholder="[必填] 请输姓名">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">联系方式</label>
                                    <div class="col-md-10">
                                        <input name="concat" class="form-control" required="required" value="" type="text" placeholder="[必填] 请输入联系方式">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">简介</label>
                                    <div class="col-md-10">
                                        <textarea name="content" class="form-control" placeholder="[必填] 一段话介绍自己，500字以内" rows="4" required="required"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-2">
                                        <button class="form-control btn btn-primary" type="submit">申请加入</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>


            <jsp:include page="_footer.jsp" />
        </div>

        <jsp:include page="_script.jsp" />
    </body>

</html>
