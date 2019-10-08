<%--
    Document   : Profile
    Created on : 2019-2-5, 3:20:03
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="userPojectNum">${fn:length(userPojectList)}</c:set>
<c:set var="applyNum">${fn:length(applyList)}</c:set>

    <!DOCTYPE html>
    <html>
        <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="${profile.realname}的主页" />
        </jsp:include>
    </head>
    <body class="sticky-header">
        <jsp:include page="_leftSide.jsp" />
        <div class="main-content" >

            <jsp:include page="_header.jsp" />

            <div class="wrapper">

                <div class="row">
                    <div class="col-md-12">

                        <div class="profile-cover">
                            <div class="overlay-profile"></div>
                            <div class="profile-inner">
                                <div class="profile-info">

                                    <div class="profile-media">
                                        <img src="${profile.logo}" alt="" />
                                    </div>

                                    <div class="profile-intro">
                                        <h4>${profile.realname}</h4>
                                        <ul>
                                            <li>账号：${profile.username}</li>
                                            <li>邮箱：${profile.email}</li>
                                        </ul>
                                    </div>

                                </div> <!--/.profile-info-->

                                <div class="profile-stats">
                                    <ul>
                                        <li>
                                            <h4>${userPojectNum}</h4>
                                            <p>已发布项目</p>
                                        </li>

                                        <li>
                                            <h4>${applyNum}</h4>
                                            <p>留言数</p>
                                        </li>

                                        <li>
                                            <h4>-</h4>
                                            <p>访问量</p>
                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </div>
                        <!-- End cover-->

                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div class="white-box">
                            <form action="apply.action" method="post">
                                <div class="form-group">
                                    <input type="hidden" name="projectId" value="0" />
                                    <input type="hidden" name="username" value="${profile.username}" />
                                    <input type="hidden" name="realname" value="游客" />
                                    <input type="hidden" name="concat" value="无" />
                                    <textarea name="content" rows="2" class="form-control" placeholder="想对TA说些什么……"></textarea>
                                </div>

                                <input type="submit" value="留言" class="btn btn-primary pull-right">

                                <ul class="nav nav-pills m-t-20 post-list">
                                    <li>
                                        <a href="#"><i class="fa fa-user"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-location-arrow"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class=" fa fa-camera"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-smile-o"></i></a>
                                    </li>
                                </ul>
                            </form>
                        </div>

                        <c:forEach var="item" items="${applyList}">
                            <div class="white-box">

                                <div class="timeline-comment">
                                    <span>
                                        <strong><i class="fa fa-user"></i> ${item.realname}</strong>
                                        <small class="text-light">
                                            ${item.gmtCreate} - 来自 

                                            <c:choose>
                                                <c:when test="${item.projectId eq 0}">
                                                    留言
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="project/${profile.username}/details/${item.projectId}" target="_blank"> <i class="fa fa-book"></i> 项目[${item.projectId}]</a>
                                                </c:otherwise>
                                            </c:choose>

                                            <c:if test="${user ne null && user.username eq profile.username}">
                                                | <a href="applyDelete.action?id=${item.id}&username=${user.username}"><i class="fa fa-close"></i> 删除</a>
                                            </c:if>
                                        </small>
                                    </span>
                                    <div class="comment-text">
                                        <blockquote>${item.content}</blockquote>
                                        <c:if test="${user ne null && user.username eq profile.username}">
                                            <p>联系方式： ${item.concat}</p> 
                                        </c:if>
                                    </div>
                                </div>   

                            </div>
                        </c:forEach>
                    </div>


                    <div class="col-md-4">
                        <div class="white-box">
                            <h2 class="header-title">个人信息（${profile.auth}）</h2>
                            <p>姓名：${profile.realname}</p>
                            <p>性别：${profile.gender}</p>
                            <p>生日：${profile.birth}</p>
                            <pre>${profile.info}</pre>
                        </div>

                        <c:if test="${userPojectNum > 0}">
                            <div class="white-box">
                                <h2 class="header-title">所有发布的项目</h2>
                                <ul class="list-group">
                                    <c:forEach var="item" items="${userPojectList}">
                                        <li class="list-group-item">
                                            <a href="project/${item.username}/details/${item.id}" 
                                               target="_blank" title="${item.type}">
                                                ${item.name}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>

                </div>

                <jsp:include page="_footer.jsp" />

            </div>
            <jsp:include page="_script.jsp" />
    </body>

</html>



