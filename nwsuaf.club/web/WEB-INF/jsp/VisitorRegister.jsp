<%-- 
    Document   : register
    Created on : 2019-1-18, 17:25:48
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
                            <h2 class="header-title">信息越真实，项目越好做 | 请您填写注册信息</h2>
                            <form class="form-horizontal" action="register.action" method="post">

                                <div class="form-group">
                                    <label class="col-md-2 control-label">账号：</label>
                                    <div class="col-md-10">
                                        <input name="username" class="form-control" required="required" type="text" placeholder="[必填] 请输入账号,长度在6-16之间,只能包含字母、数字" pattern="[a-z1-9A-Z]{6,16}$">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">密码：</label>
                                    <div class="col-md-10">
                                        <input name="password" class="form-control" required="required" type="text" placeholder="[必填] 请输入密码,长度在8-20之间,只能包含字母、数字" pattern="[a-z1-9A-Z]{8,20}$">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">姓名：</label>
                                    <div class="col-md-10">
                                        <input name="realname" class="form-control" required="required" type="text" placeholder="[必填] 请输入真实姓名">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">邮箱：</label>
                                    <div class="col-md-10">
                                        <input name="email" class="form-control" type="email" placeholder="[必填] 请输入电子邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">性别：</label>
                                    <div class="col-md-10">
                                        <select name="gender" class="form-control" required="required">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                            <option value="保密" selected>保密</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-10">
                                        <label class="label label-warning">以下是选填信息</label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">出生日期：</label>
                                    <div class="col-md-10">
                                        <input name="birth" class="form-control" type="date" placeholder="[选填] 出生日期">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">手机号码：</label>
                                    <div class="col-md-10">
                                        <input name="tel" class="form-control" type="tel" placeholder="[选填] 手机号码">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">简介：</label>
                                    <div class="col-md-10">
                                        <textarea name="info" class="form-control" placeholder="[选填] 一段话介绍自己，1000字以内" rows="4" maxlength="1000"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">
                                        <a href="http://file.zxy97.com/" target="_blank"><span class="label label-primary">[ 上传头像 ]</span></a>
                                    </label>
                                    <div class="col-md-10">
                                        <input name="logo" class="form-control" type="url" placeholder="[选填] 输入头像的URL地址" maxlength="200" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-10">
                                        <input type="checkbox" required="required" id="acceptTerms" >
                                        <label for="acceptTerms">我同意</label> <a href="#">注册协议</a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-2">
                                        <input class="form-control btn btn-primary" type="submit" value="完成注册并登陆">
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
