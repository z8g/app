<%--
    Document   : _leftSide
    Created on : 2019-1-12, 13:34:51
    Author     : zhaoxuyang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="left-side sticky-left-side">

    <div class="logo">
        <a href=""><img src="assets/images/logo.png" alt=""></a>

    </div>

    <div class="logo-icon text-center">
        <a href=""><img src="assets/images/logo-icon.png" alt=""></a>
    </div>

    <div class="left-side-inner">
        <ul class="nav nav-pills nav-stacked custom-nav">
            <li class=""><a href=""><i class="fa fa-home"></i>&nbsp;<span>网站首页</span></a></li>
            <li class=""><a href="article/list?type=关于我们"><i class="fa fa-share"></i>&nbsp;<span>关于我们</span></a></li>
            <li class="menu-list ${param.active_zxdt}"><a href="news"><i class="fa fa-newspaper-o"></i>&nbsp;<span>最新动态</span></a>
                <ul class="sub-menu-list">
                    <li class="${param.active_bstz}"><a href="article/list?type=比赛通知">比赛通知 </a></li>
                    <li class="${param.active_pxbg}"><a href="article/list?type=培训报告">培训报告 </a></li>
                    <li class="${param.active_xxzl}"><a href="article/list?type=学习资料">学习资料 </a></li>
                </ul>
            </li>
            <li class="menu-list ${param.active_xmzs}"><a href="project"><i class="fa fa-bookmark"></i>&nbsp;<span>项目展示</span></a>
                <ul class="sub-menu-list">
                    <li class="${param.active_jsxm}"><a href="article/list?type=竞赛项目">竞赛项目 </a></li>
                    <li class="${param.active_zfxm}"><a href="article/list?type=在孵项目">在孵项目 </a></li>
                </ul>
            </li>
            <li class="${param.active_xzhb}"><a href="project/list"><i class="fa fa-share"></i>&nbsp;<span>寻找伙伴</span></a></li>
           <li class="menu-list ${param.active_wzbz}"><a href="#"><i class="fa fa-question-circle"></i>&nbsp;<span>学习提升</span></a>
                <ul class="sub-menu-list">
                    <li  class="${param.active_cjwt}"><a href="downloads"> 靠自学 </a></li>
                    <li  class="${param.active_cjwt}"><a href="article/list?type=导师信息"> 找导师 </a></li>
                </ul>
            </li>
            <li class="menu-list ${param.active_wzbz}"><a href="#"><i class="fa fa-question-circle"></i>&nbsp;<span>网站帮助</span></a>
                <ul class="sub-menu-list">
                    <li  class="${param.active_cjwt}"><a href="article/list?type=常见问题"> 常见问题 </a></li>
                </ul>
            </li>

            <c:if test="${sessionScope.user ne null}" >
                <c:choose>
                    <c:when test="${'用户' eq sessionScope.user.auth}">
                        <li class="menu-list"><a href="#"><i class="fa fa-cogs"></i>&nbsp;<span>${user.realname ne null ? user.realname : user.username}</span></a>
                            <ul class="sub-menu-list">
                                <li class="${param.active_wdxm}"><a href="user/project/list"> 我的项目 </a></li>
                                <li class="${param.active_cjxm}"><a href="user/project/create"> 创建项目 </a></li>
                            </ul>
                        </li>
                    </c:when>

                    <c:when test="${'操作员' eq sessionScope.user.auth}">
                        <li class="menu-list"><a href="#"><i class="fa fa-cogs"></i>&nbsp;<span>${user.realname ne null ? user.realname : user.username}</span></a>
                            <ul class="sub-menu-list">
                                <li class="${param.active_wdtz}"><a href="operator/article/list"> 文章管理 </a></li>
                                <li class="${param.active_fbtz}"><a href="operator/article/create"> 文章发布 </a></li>
                                <li class="${param.active_xmsh}"><a href="operator/project/list"> 项目审核 </a></li>
                                <li class="${param.active_wjgl}"><a href="operator/file/list"> 文件上传 </a></li>
                            </ul>
                        </li>
                    </c:when>

                    <c:when test="${'系统管理员' eq sessionScope.user.auth}">
                        <li class="menu-list"><a href="#"><i class="fa fa-cogs"></i>&nbsp;<span>${user.realname ne null ? user.realname : user.username}</span></a>
                            <ul class="sub-menu-list">
                                <li class="${param.active_yhgl}"><a href="administrator/user/list"> 用户管理 </a></li>
<!--                                <li class="${param.active_wzgl}"><a href="administrator/article/list"> 文章管理 </a></li>
                                <li class="${param.active_wzgl}"><a href="administrator/project/list"> 项目管理 </a></li>-->
                            </ul>
                        </li> 
                    </c:when>
                </c:choose>
            </c:if>


        </ul>

    </div>
</div>
