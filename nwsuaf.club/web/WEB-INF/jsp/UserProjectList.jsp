<%-- 
    Document   : projectTimeline
    Created on : 2019-1-18, 17:32:11
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="${user.realname ne null ? user.realname : user.username }的项目" />
        </jsp:include>
    </head>
    <body class="sticky-header">
        <jsp:include page="_leftSide.jsp" />
        <div class="main-content" >

            <jsp:include page="_header.jsp" />

            <div class="wrapper">

                <div class="row">
                    <div class="col-md-12">
                        <div id="timeline" class="container">
                            <c:forEach var="project" items="${userProjectList}">
                                <div class="timeline-block">
                                    <div class="timeline-img bg-primary">
                                        <i class="fa fa-file-text"></i>
                                    </div>
                                    <div class="timeline-content">
                                        <h3><strong>${project.name}</strong></h3>
                                        <hr />
                                        <span class="date">
                                            项目创建时间：${project.gmtCreate} <hr />
                                            最后修改时间：${project.gmtUpdate} <hr />
                                            申请截止日期：${project.gmtEnd} <hr />
                                            <span class="label label-danger" title="项目状态">${project.state}</span>
                                            <span class="label label-warning" title="具体类型">${project.type}</span>
                                            <hr/>
                                            <blockquote style="text-indent: 2em;" title="[队员要求] ${project.required}">[项目简介] ${project.content}</blockquote>
                                        </span>
                                        
                                        <img src="${project.logo}" alt="${project.name}" width="100%" /><br />
                                        <a href="project/${user.username}/details/${project.id}" target="_blank" class="btn btn-primary">预览</a>
                                        <a href="user/project/delete.action?id=${project.id}" class="btn btn-danger item-delete">删除</a>
                                        
                                        <c:choose>
                                            <c:when test="${project.state eq '草稿'}">
                                                <a href="user/project/update?id=${project.id}"  class="btn btn-warning">修改</a>
                                                <a href="user/project/publish.action?id=${project.id}"  class="btn btn-success item-publish">发布</a>
                                            </c:when>
                                            <c:when test="${project.state eq '已发布'}">
                                                <a href="user/project/update?id=${project.id}"  class="btn btn-warning">修改</a>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <jsp:include page="_footer.jsp" />

            </div>

            <jsp:include page="_script.jsp" />
            <script>
                $('.item-delete').click(function() {
                    if (!confirm('是否删除？')) {
                        window.event.returnValue = false;
                    }
                });
                $('.item-publish').click(function() {
                    if (!confirm('是否发布？')) {
                        window.event.returnValue = false;
                    }
                });

            </script>
    </body>

</html>
