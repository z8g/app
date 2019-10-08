<%-- 
    Document   : userUpdate
    Created on : 2019-1-18, 17:32:22
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <h2 class="header-title">修改 <span class="label label-info">${user.username}</span> 的用户信息</h2>
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/user/update.action">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">账号：</label>
                                    <div class="col-sm-10">
                                        <p class="form-control-static">${sessionScope.user.username}</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">注册时间：</label>
                                    <div class="col-sm-10">
                                        <p class="form-control-static">${sessionScope.user.gmtCreate}</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">用户类型：</label>
                                    <div class="col-sm-10">
                                        <p class="form-control-static">${sessionScope.user.auth}</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">密码：</label>
                                    <div class="col-md-10">
                                        <input class="form-control" name="password" value="${sessionScope.user.password}" type="password" placeholder="请输入密码" required />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">姓名：</label>
                                    <div class="col-md-10">
                                        <input class="form-control" name="realname" value="${sessionScope.user.realname}" type="text" placeholder="请输入姓名" required />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">邮箱：</label>
                                    <div class="col-md-10">
                                        <input class="form-control" name="email" type="email" value="${sessionScope.user.email}" placeholder="请输入邮箱" required />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">性别：</label>
                                    <div class="col-md-10">
                                        <select name="gender" class="form-control" required="required">
                                            <option value="男" ${sessionScope.user.gender eq '男' ? 'selected' : ''}>男</option>
                                            <option value="女" ${sessionScope.user.gender eq '女' ? 'selected' : ''}>女</option>
                                            <option value="保密" ${sessionScope.user.gender eq '保密' ? 'selected' : ''}>保密</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">出生日期：</label>
                                    <div class="col-md-10">
                                        <input name="birth" class="form-control" type="date" placeholder="[选填] 出生日期" value="${sessionScope.user.birth}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">手机号码：</label>
                                    <div class="col-md-10">
                                        <input name="tel" class="form-control" type="tel" placeholder="[选填] 手机号码" value="${sessionScope.user.tel}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">简介：</label>
                                    <div class="col-md-10">
                                        <textarea name="info" class="form-control" placeholder="[选填] 一段话介绍自己，1000字以内" rows="4">${sessionScope.user.info}</textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">
                                        <a href="http://file.zxy97.com/" target="_blank"><span class="label label-primary">[ 上传头像 ]</span></a>
                                        头像地址
                                    </label>
                                    <div class="col-md-10">
                                        <input name="logo" class="form-control" type="url" placeholder="[选填] 输入头像的URL地址" value="${sessionScope.user.logo}">
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-2">
                                        <input class="form-control" type="reset" value="恢复到修改前">
                                    </div>
                                    <div class="col-md-2">
                                        <input class="form-control btn btn-primary" type="submit" value="提交修改">
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
