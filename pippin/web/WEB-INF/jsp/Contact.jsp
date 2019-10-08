<%-- 
    Document   : Contact
    Created on : 2019-6-13, 8:27:31
    Author     : zhaoxuyang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="Contact - PIPPIN" />
        </jsp:include>
    </head>
    <body >

        <jsp:include page="_nav.jsp" />

        <div class="container set-pad sec" id="contact-sec">
            <div class="row text-center">
                <div class="col-lg-12  col-md-12  col-sm-12">
                    <h1 class="header-line">Contact Us</h1>
                    <p>
                        You can use our software through this help document.
                    </p>
                </div>
            </div>
            <div class="row set-row-pad" >
                <div class="col-lg-12 col-md-12 col-sm-12" >
                    <p>If you have any comments or query about <code>PIPPIN</code>, please contact:</p>

                    <div class="col-md-6">
                        <p><strong>Mail Addresses:</strong></p>
                        <ul class="list-group">
                            <li class="list-group-item">Song Group</li>
                            <li class="list-group-item">Biochemistry & Molecular Biology </li>
                            <li class="list-group-item">Biomedicine Discovery Institute </li>
                            <li class="list-group-item">Faculty of Medicine, Nursing and Health Sciences</li>
                            <li class="list-group-item">Monash University</li>
                            <li class="list-group-item">Melbourne, VIC 3800, Australia</li>
                        </ul>
                    </div> 
                    <div class="col-md-6">
                        <p>You can send your queries and suggestions to the <strong>developers:</strong></p>
                        <ul class="list-group">
                            <li class="list-group-item"><a href="mailto:lpy0927@gmail.com" target="_blank">lpy0927@gmail.com</a></li>
                            <li class="list-group-item"><a href="mailto:He.Zhang@monash.edu" target="_blank">He.Zhang@monash.edu </a> </li>
                            <li class="list-group-item"><a href="mailto:zhaoxuyang19971015@foxmail.com"  target="_blank">zhaoxuyang19971015@foxmail.com</a></li>
                        </ul>
                    </div>


                </div>
            </div>
        </div>
        <jsp:include page="_footer.jsp" />


    </body>
</html>
