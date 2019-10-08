<%-- 
    Document   : JobResult
    Created on : 2019-6-13, 8:26:47
    Author     : zhaoxuyang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="Job Result - PIPPIN" />
        </jsp:include>
    </head>
    <body >

        <jsp:include page="_nav.jsp" />

        <div id="job-list-sec" class="container set-pad sec">
            <div class="row text-center">
                <div class="col-lg-12  col-md-12  col-sm-12">
                    <h1 class="header-line">Job Result</h1>
                    <p class="well-sm">
                        Here is a job result of <code id="jobId">${job.id}</code> 
                        <span class="btn-group">
                            <a target="_blank" href="${pageContext.request.contextPath}/job/${job.id}.txt" class="btn btn-primary">txt</a>
                            <a target="_blank" href="${pageContext.request.contextPath}/job/${job.id}.json" class="btn btn-info">json</a>
                            <a target="_blank" href="${pageContext.request.contextPath}/job/${job.id}.cvs" class="btn btn-warning">cvs</a>
                            <a target="_blank" href="${pageContext.request.contextPath}/job/${job.id}.xlsx" class="btn btn-success">xlsx</a>
                        </span>
                    </p>
                    <p class="well-sm">
                        <strong>Job ID: </strong>${job.id} | 
                        <strong>Submit Time: </strong>${job.time} | 
                        <strong>Status: </strong>${job.status}
                    </p>
                    <hr />

                    <div class="col-md-12 col-sm-12">
                        <table id="job-result-table" class="table table-hover table-responsive">
                            <thead>
                                <tr>
                                    <th class="text-center">id</th>
                                    <th class="text-right">pre</th>
                                    <th class="text-right">post</th>
                                    <th class="text-center">class</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="protein" items="${job.proteinList}">
                                    <tr>
                                        <td class="text-center">${protein.id}</td>
                                        <td class="text-right">
                                            <fmt:formatNumber value="${protein.pre}" pattern="#0.000#"/>
                                        </td>
                                        <td class="text-right">
                                            <fmt:formatNumber value="${protein.post}" pattern="#0.000#"/>
                                        </td>
                                        <td class="text-center">${protein.clazz}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>  
                    </div>

                </div>
            </div>
        </div>

        <jsp:include page="_footer.jsp" />
        <jsp:include page="_script.jsp" />

        <script>
            $('#job-result-table').dataTable({
                "sPaginationType": "full_numbers"
            });
        </script>
    </body>
</html>
