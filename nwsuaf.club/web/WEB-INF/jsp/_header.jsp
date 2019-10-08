<%-- 
    Document   : _header
    Created on : 2019-1-12, 13:50:24
    Author     : zhaoxuyang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header-section">
    <a class="toggle-btn"><i class="fa fa-bars"></i></a>
    <form class="searchform" method="get" action="https://www.baidu.com/s" target="_blank">
        <input type="text" class="form-control" name="wd" placeholder="输入关键词搜索……" />
    </form>
    <div class="menu-right">
        <ul class="notification-menu">

            <!-- 用户操作反馈 -->
            <c:set var="notificationListLength" value="${fn:length(sessionScope.notificationList)}" />
            <c:if test="${sessionScope.notificationList ne null}">
                <li>
                    <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="badge">${notificationListLength}</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-head pull-right" id="notification">
                        <h5 class="title">您有 ${notificationListLength} 条系统消息</h5>
                        <ul class="dropdown-list">
                            <li class="notification-scroll-list notification-list ">

                                <c:set var="startIndex" value="${notificationListLength - 1}" />
                                <c:forEach items="${sessionScope.notificationList}" varStatus="status">
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <div class="media">
                                            <div class="pull-left p-r-10">
                                                <em class="fa fa-bell-o ${sessionScope.notificationList[startIndex - status.index].type}"></em>
                                            </div>
                                            <div class="media-body">
                                                <h5 class="media-heading">${sessionScope.notificationList[startIndex - status.index].content}</h5>
                                                <p class="m-0">
                                                    <small>${sessionScope.notificationList[startIndex - status.index].gmtCreate}</small>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>

                            </li>
                            <li class="last">关闭</li>
                        </ul>
                    </div>
                </li>
            </c:if>

            <c:choose>
                <c:when test="${sessionScope.user ne null}">
                    <!--                    <li>
                                            <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                                                <i class="fa fa-user"></i>
                                                <span class="badge">2</span>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-head pull-right">
                                                <h5 class="title">消息</h5>
                                                <ul class="dropdown-list normal-list">
                                                    <li class="message-list message-scroll-list">
                                                        <a href="#">
                                                            <span class="photo"> <img src="assets/images/users/avatar-6.jpg" class="img-circle" alt="img"></span>
                                                            <span class="subject">李四</span>
                                                            <span class="message"> 下午去图书馆吗？</span>
                                                            <span class="time">2019-01-01 12:28:52</span>
                                                        </a>
                    
                                                        <a href="#">
                                                            <span class="photo"> <img src="assets/images/users/avatar-6.jpg" class="img-circle" alt="img"></span>
                                                            <span class="subject">张三</span>
                                                            <span class="message"> 什么时候去吃饭？</span>
                                                            <span class="time">2019-01-01 12:30:52</span>
                                                        </a>
                                                    </li>
                                                    <li class="last"> <a  href="#">所有消息</a> </li>
                                                </ul>
                                            </div>
                                        </li>    -->

                    <li>
                        <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-user"></i> ${sessionScope.user.realname}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                            <li> <a href="${user.username}"> <i class="fa fa-cog"></i> 个人主页 </a> </li>
                            <li> <a href="user/update"> <i class="fa fa-cog"></i> 修改信息 </a> </li>
                            <li> <a href="user/logout.action"> <i class="fa fa-sign-out"></i> 退出登录 </a> </li>
                            <!--                            <li> <a href="lockscreen.form"> <i class="fa fa-sign-out"></i> 锁住屏幕 </a> </li>-->
                        </ul>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-sign-in"></i> 登录
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                            <li> <a href="login"> <i class="fa fa-sign-in"></i> 登录 </a> </li>
                            <li> <a href="register"> <i class="fa fa-user-plus"></i> 注册 </a> </li>
                            <li> <a href="findPassword"> <i class="fa fa-wrench"></i> 找回密码 </a> </li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</div>
