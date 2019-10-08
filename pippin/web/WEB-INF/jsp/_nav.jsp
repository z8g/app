<%-- 
    Document   : _nav
    Created on : 2019-6-13, 8:28:31
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- data-scroll-reveal="enter from the bottom after 0.4s"-->
<div class="navbar navbar-inverse navbar-fixed-top " id="menu">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><img class="logo-custom" src="${pageContext.request.contextPath}/assets/img/logo180-50.png" alt=""  /></a>
        </div>
        <div class="navbar-collapse collapse move-me">
            <ul class="nav navbar-nav navbar-right">
                <li ><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/server"> Server</a></li>
                <li><a href="${pageContext.request.contextPath}/job">Job List</a></li>
                <li><a href="${pageContext.request.contextPath}/help">Help</a></li>
                <li><a href="${pageContext.request.contextPath}/contact">Contact Us</a></li>
            </ul>
        </div>
    </div>
</div>