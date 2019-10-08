<%-- 
    Document   : login
    Created on : 2019-1-18, 17:24:52
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="登录页面" />
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
                            <h2 class="header-title">填写登录信息 | 看看大家在讨论些什么</h2>

                            <form class="form-horizontal" action="login.action" method="post">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">账号：</label>
                                    <div class="col-md-10">
                                        <input value="" name="username" class="form-control" value="" type="text" placeholder="请输入账号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">密码：</label>
                                    <div class="col-md-10">
                                        <input value="" name="password" class="form-control" value="" type="password" placeholder="请输入密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-2">
                                        <input class="form-control btn btn-primary" type="submit" value="完成登录">
                                    </div>
                                    <div class="col-md-2">
                                        <a href="register" class="form-control btn btn-default">注册页面</a>
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

