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
            <jsp:param name="title" value="${article.title}" />
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
                            <h2 class="header-title">${article.title}</h2>
                            <div class="form-group col-md-12">
                                ${article.content}
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="_footer.jsp" />
        </div>

        <jsp:include page="_script.jsp" />
    </body>

</html>
