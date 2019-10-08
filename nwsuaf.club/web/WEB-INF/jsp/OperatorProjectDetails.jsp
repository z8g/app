<%-- 
    Document   : projectDetails
    Created on : 2019-1-18, 17:27:10
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
                        <blockquote>
                            <a href="operator/project/auditSuccess.action?id=${projectDetails.id}" class="btn btn-primary item-success">审核通过</a>
                            <a href="operator/project/auditFail.action?id=${projectDetails.id}" class="btn btn-danger item-fail">审核不通过</a>
                        </blockquote>
                    </div>

                    <div class="col-md-12">
                        <blockquote>项目名称：${projectDetails.name} [<a href="${projectDetails.username}" target="_blank" title="作者">${projectDetails.username}</a>]</blockquote>
                    </div>

                    <div class="col-md-4">
                        <blockquote>项目类型： ${projectDetails.type} </blockquote>
                    </div>

                    <div class="col-md-4">
                        <blockquote>最后修改时间：${projectDetails.gmtUpdate}</blockquote>
                    </div>
                    <div class="col-md-4">
                        <blockquote>申请截止日期：${projectDetails.gmtEnd}</blockquote>
                    </div>

                    <div class="col-md-6">
                        <blockquote>
                            <p>项目LOGO：</p>
                            <p><img src="${projectDetails.logo}" width="100%" alt="项目LOGO"/></p>
                        </blockquote>
                    </div>
                    <div class="col-md-6">
                        <blockquote>
                            <p>项目简介：</p>
                            <pre>${projectDetails.content}</pre>
                        </blockquote>
                        <blockquote>
                            <p>队友要求：</p>
                            <pre>${projectDetails.required}</pre>
                        </blockquote>
                        
                    </div>

                </div>
            </div>
            <jsp:include page="_footer.jsp" />
        </div>

        <jsp:include page="_script.jsp" />

        <script>
            $('.item-success').click(function() {
                if (!confirm('确定审核通过？')) {
                    window.event.returnValue = false;
                }
            });
            $('.item-fail').click(function() {
                if (!confirm('确定审核不通过？')) {
                    window.event.returnValue = false;
                }
            });
        </script>

    </body>

</html>
