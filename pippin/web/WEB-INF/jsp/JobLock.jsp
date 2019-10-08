<%-- 
    Document   : JobLock
    Created on : 2019-6-13, 13:17:00
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="Job Lock - PIPPIN" />
        </jsp:include>
    </head>
    <body >

        <jsp:include page="_nav.jsp" />

        <div id="server-sec" class="container sec">
            <div class="container set-pad">
                <div class="row text-center">
                    <div class="col-lg-12  col-md-12  col-sm-12">
                        <h1 class="header-line">Job Lock</h1>
                    </div>
                </div>
                <div class="col-lg-12  col-md-12  col-sm-12">
                    <blockquote>
                        You Job is running...
                    </blockquote>
                </div>

            </div>
        </div>

        <jsp:include page="_footer.jsp" />
        <jsp:include page="_script.jsp" />

    </body>
</html>