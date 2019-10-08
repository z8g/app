<%-- 
    Document   : Index
    Created on : 2019-6-13, 8:26:21
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="Welcome to PIPPIN" />
        </jsp:include>
    </head>
    <body >

        <jsp:include page="_nav.jsp" />

        <div class="home-sec" id="home" >
            <div class="overlay">
                <div class="container">
                    <div class="row text-center " >

                        <div class="col-lg-12  col-md-12 col-sm-12">

                            <div class="flexslider set-flexi" id="main-section" >
                                <ul class="slides move-me">
                                    <li>
                                        <h1>
                                            PresynaptIc Postsynaptic PredIctioN
                                        </h1>
                                        <h4 class="text-left">
                                            PIPPIN: An accurate XGBoost-based web server for the prediction of presynaptic and postsynaptic neurotoxins.
                                        </h4>
                                        <h4 class="text-left">
                                            Accurate prediction between presynaptic neurotoxins and postsynaptic neurotoxins is critical for helping neuroscientist and pharmacologist making better decisions. Although there have been some breakthroughs for this prediction task in recent years, none of them provide easy-to-use web tools. Here, we developed PIPPIN, which is a powerful predictor for the presynaptic and postsynaptic neurotoxins with more data available, more features calculated, and more powerful algorithms (XGBoost) used.
                                        </h4>

                                        <h4 class="text-right">
                                            <a  href="help" class="btn btn-info btn-lg" >
                                                Help
                                            </a>
                                            <a href="server" class="btn btn-primary btn-lg" >
                                                Go To Use It
                                            </a>
                                            <a  href="job" class="btn btn-success btn-lg" >
                                                Query Your Job
                                            </a>
                                        </h4>
                                        <h4 class="text-left">
                                            If you find PIPPIN useful, please kindly cite the following paper:
                                        </h4>
                                    </li>
                                </ul>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="//rf.revolvermaps.com/0/0/8.js?i=5y7hfza4osk&amp;m=0&amp;c=ff0000&amp;cr1=ffffff&amp;f=arial&amp;l=33" async="async"></script>
        <jsp:include page="_footer.jsp" />
        <jsp:include page="_script.jsp" />
        
    </body>
</html>
