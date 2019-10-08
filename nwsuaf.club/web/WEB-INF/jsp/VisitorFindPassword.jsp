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
            <jsp:param name="title" value="找回密码" />
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
                            <h2 class="header-title">填写邮箱找回密码</h2>

                            <form class="form-horizontal" action="findPassword.action" method="post">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">邮箱：</label>
                                    <div class="col-md-10">
                                        <input name="email" class="form-control" type="email" placeholder="请输入注册时填写的邮箱">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">&nbsp;</label>
                                    <div class="col-md-2">
                                        <input class="form-control btn btn-primary" type="submit" value="找回密码">
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

