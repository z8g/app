<%-- 
    Document   : Help
    Created on : 2019-6-13, 9:14:55
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="Help - PIPPIN" />
        </jsp:include>
    </head>
    <body >

        <jsp:include page="_nav.jsp" />

        <div id="help-sec" class="container set-pad sec">
            <div class="row text-center">
                <div class="col-lg-12  col-md-12  col-sm-12">
                    <h1 class="header-line">Help</h1>
                    <p>
                        You can use our software through this help document.
                    </p>
                </div>
            </div>

            <div class="row set-row-pad" >
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="panel-group" id="accordion">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">
                                        1. Submit data by filling in sequences or selecting files
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse1" class="panel-collapse collapse"  style="height: 0px;">
                                <div class="panel-body">
                                    <blockquote>
                                        <strong>1) Submit data by filling in a sequence</strong>
                                    </blockquote>
                                    <ol class="list-group">
                                        <li class="list-group-item">Paste protein sequences in <code>FASTA</code> format and submit it:</li>
                                        <li class="list-group-item"><img src="${pageContext.request.contextPath}/assets/img/help/1.png" alt="Filling in a sequence" width="100%" /></li>
                                    </ol>

                                    <blockquote>
                                        <strong>2) Submit data by upload a fasta file</strong>
                                    </blockquote>
                                    <ol class="list-group">
                                        <li class="list-group-item">Select a <code>FASTA</code> file and submit it:</li>
                                        <li class="list-group-item"><img src="${pageContext.request.contextPath}/assets/img/help/2.png" alt="Upload a fasta file" width="100%" /></li>
                                    </ol>
                                    
                                    <p class="text-right">
                                            <a class="btn btn-danger" data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">
                                                Hide
                                            </a>
                                        </p>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" class="collapsed">
                                        2. Query your job
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse2" class="panel-collapse collapse"  style="height: 0px;">
                                <div class="panel-body">
                                    <blockquote>
                                        <strong>1) Waiting for Job to finish.</strong>
                                    </blockquote>
                                    <img src="${pageContext.request.contextPath}/assets/img/help/3.png"  alt="Waiting for Job to finish" width="100%" />
                                    
                                    <blockquote>
                                        <strong>2) Click <code>Details</code> on Job List</strong>
                                    </blockquote>
                                    <img src="${pageContext.request.contextPath}/assets/img/help/4.png" alt="Click Details on Job List" width="100%"  />
                                    <p class="text-right">
                                            <a class="btn btn-danger" data-toggle="collapse" data-parent="#accordion" href="#collapse2" class="collapsed">
                                                Hide
                                            </a>
                                        </p>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="_footer.jsp" />
        <jsp:include page="_script.jsp" />
        
    </body>
</html>
