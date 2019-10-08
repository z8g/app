<%-- 
    Document   : index
    Created on : 2018-11-10, 13:36:07
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>zxy97博客 - 记录美好回忆</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editormd.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
        <link rel="shortcut icon" href="http://zxy97.com/images/favicon.png" type="image/x-icon" />
    </head>
    <body>
        <a name="top"></a>
        <div id="layout">
            <div id="header">
                <h1><i class="editormd-logo editormd-logo-3x"></i></h1>
<!--                <h1><img src="https://p.ssl.qhimg.com/bdr/180__/t018ef55a37c8cb1391.jpg" alt="${author.nickname}"/></h1>-->
                <h1><c:choose>
                        <c:when test="${author.nickname!=null}">${author.nickname}的博客</c:when>
                        <c:when test="${author.username!=null}">${author.username}的博客</c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                </h1>
                
                <div class="menu">
                    <a href="author"><i class="fa fa-search"></i>查找作者</a>
                    <c:choose>
                        <c:when test="${user!=null}">
                            <a href="${pageContext.request.contextPath}/${author.username}/article/new"><i class="fa fa-file-o fa-lg"></i> 写文章</a>
                            <a href="${pageContext.request.contextPath}/author/settings"><i class="fa fa-cogs fa-lg"></i> 设置</a>
                            <a href="${pageContext.request.contextPath}/author/message"><i class="fa fa-comments-o fa-lg"></i> 消息</a>
                            <a href="${pageContext.request.contextPath}/author/logout"><i class="fa fa-sign-out fa-lg"></i> 退出</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/register"><i class="fa fa-user-plus fa-lg"></i> 注册</a>
                            <a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-in fa-lg"></i> 登录</a>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="menu">
                    <c:forEach var="articlePackageVO" items="${articlePackageVOList}">
                        <a href="${pageContext.request.contextPath}/index#c${articlePackageVO.typeId}"><i class="fa fa-bars"></i> ${articlePackageVO.type}</a>
                    </c:forEach>
                </div>
                
                
            </div>
            <c:forEach var="articlePackageVO" items="${articlePackageVOList}">
                
                <h1 class="category">
                    <a href="#top"><i class="fa fa-arrow-circle-up"></i> 返回顶部</a>
                    <i name="c${articlePackageVO.typeId}" class="fa fa-bars"></i> ${articlePackageVO.type}
                </h1>
                <ul>
                    <c:forEach var="articleVO" items="${articlePackageVO.articleVOList}"> 
                        <li>
                            <a href="${pageContext.request.contextPath}/${author.username}/article/details/${articleVO.id}">
                                <c:choose>
                                    <c:when test="${isSelf}">
                                        <i class="fa fa-edit"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-file-text-o"></i>
                                    </c:otherwise>
                                </c:choose>
                                ${articleVO.title}
                                <span>${articleVO.gmtUpdate}</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:forEach>

            <div class="clear"></div>
            <div class="footer">                  
                &copy; 2018  - <a href="zhaoxuyang" title="开发者的博客">zhaoxuyang</a> @ <a href="http://zxy97.com/" target="_blank" title="zxy97.com">zxy97.com</a>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
    </body>
</html>