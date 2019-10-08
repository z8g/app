<%--
    Document   : index
    Created on : 2018-12-22, 16:44:01
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<fmt:setBundle basename="message" var="message"/>
<fmt:setLocale value="${pageContext.request.locale}"/>

<fmt:setBundle basename="config" var="config"/>
<fmt:message key="sql.driver" var="sql_driver" bundle="${config}" />
<fmt:message key="sql.url" var="sql_url" bundle="${config}" />
<fmt:message key="sql.user" var="sql_user" bundle="${config}" />
<fmt:message key="sql.password" var="sql_password" bundle="${config}" />
<fmt:message key="sql.driver" var="sql_driver" bundle="${config}" />
<fmt:message key="bg.music.src" var="bg_music_src" bundle="${config}" />

<sql:setDataSource var="zxy97"
                   scope="session"
                   driver="${sql_driver}"
                   url="${sql_url}"
                   user="${sql_user}"
                   password="${sql_password}"/>

<sql:query dataSource="${zxy97}" var="articles" sql="select * from article order by gmt_update desc" />
<sql:query dataSource="${zxy97}" var="links" sql="select * from link order by id desc"  />
<sql:query dataSource="${zxy97}" var="navs" sql="select * from nav"  />

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="UTF-8">
        <meta name="HandheldFriendly" content="True">
        <meta name="MobileOptimized" content="320">
        <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1">
        <link href="assets/css/fonts.css" rel="stylesheet" type="text/css">
        <title><fmt:message key="website.title" bundle="${message}" /></title>
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="assets/images/favicon.png" rel="icon">
        <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
        <meta name="description" content="zxy97主页">
        <meta name="keywords" content="<fmt:message key="website.meta.keywords" bundle="${message}" />">
        <meta name="author" content="<fmt:message key="website.meta.author" bundle="${message}" />">
        <base target="_blank">
    </head>
    <body>
        <header role="banner">
            <hgroup>
                <h1><a href="http://zxy97.com/">zxy97.com</a></h1>
                <h2><i class="fa fa-home"></i>&nbsp;<fmt:message key="website.header.home" bundle="${message}" /></h2>
            </hgroup>
        </header>

        <nav role="navigation">
            <fieldset class="mobile-nav">
                <select onchange="location = this.value;">
                    <option value=""><fmt:message key="website.nav.home" bundle="${message}" /></option>
                    <c:forEach var="row" items="${navs.rows}">
                        <option value="${row.link}">${row.content}</option>
                    </c:forEach>
                </select>
            </fieldset>

            <ul class="main-navigation">
                <c:forEach var="row" items="${navs.rows}">
                    <li><a href="${row.link}" title="${row.title}">${row.content}</a></li>
                    </c:forEach>
            </ul>

            <ul class="subscription">
                <li><a href="rss/rss.xml" title="RSS"><i class="fa fa-rss-square"></i></a></li>
            </ul>

            <form action="https://baidu.com/s" method="get" target="_blank">
                <fieldset role="search">
                    <input class="search" type="text" name="wd" placeholder=""/>
                </fieldset>
            </form>
        </nav>

        <div id="main">
            <div id="content">
                <div class="blog-index">

                    <c:forEach var="row" items="${articles.rows}">

                    <!-- id:${row.id} gmtCreate:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${row.gmt_create}" />  start-->
                        <article id="article_${row.id}">
                            <header>
                                <p class="meta">${row.gmt_update}</p>
                                <h1 class="entry-title">
                                    <a href="${row.title_link}">${row.title}</a>
                                </h1>
                            </header>

                            ${row.content}

                            <footer>
                                <a href="${row.full_link}" rel="full-article"><fmt:message key="website.full.article" bundle="${message}" /></a>
                            </footer>
                        </article>
                        <!-- id:${row.id} gmtCreate:${row.gmt_create}  end-->

                    </c:forEach>

                    <div class="pagination">
                        <a href="" class="label-pagination"></a>
                    </div>

                </div>



                <aside class="sidebar thirds">
                    <section class="first odd">
                        <p></p>
                    </section>
                    <ul class="sidebar-nav">
                        <li class="sidebar-nav-item">
                            <audio controls="controls" preload="none">
                                <source src="${bg_music_src}" type="audio/mpeg">
                            </audio>
                        </li>
                    </ul>
                    <section class="even">
                        <h1><fmt:message key="website.recent.posts" bundle="${message}" /></h1>
                        <ul id="recent_posts">
                            <c:forEach var="row" items="${links.rows}">
                                <li class="post">
                                    <a target="_blank" href="${row.href}" title="${row.title}">${row.content}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </section>
                </aside>

            </div>
        </div>

        <footer role="contentinfo">
            <p><span class="credit"><fmt:message key="website.footer.copyright" bundle="${message}" /></span></p>
        </footer>

    </body>
</html>