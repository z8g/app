<%-- 
    Document   : Server
    Created on : 2019-6-13, 8:26:33
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="Server - PIPPIN" />
        </jsp:include>
    </head>
    <body >

        <jsp:include page="_nav.jsp" />

        <div id="server-sec" class="container sec">
            <div class="container set-pad">
                <div class="row text-center">
                    <div class="col-lg-12  col-md-12  col-sm-12">
                        <h1 class="header-line">Use Server</h1>
                    </div>
                </div>
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab1" data-toggle="tab">
                            Enter Sequence String
                        </a>
                    </li>
                    <li>
                        <a href="#tab2" data-toggle="tab">
                            Upload File
                        </a>
                    </li>
                </ul>

                <div id="myTabContent" class="tab-content set-row-pad">
                    <div class="tab-pane fade in active" id="tab1">
                        <form target="_blank" id="input-fill-form" class="col-lg-12  col-md-12  col-sm-12" method="POST" action="${pageContext.request.contextPath}/job/fill">
                            <div class="form-group">
                                <label>Paste protein sequences in fasta format (<a id="enterExample" href="javascript:void(0);">example</a>): </label>
                                <textarea id="sequenceText" name="sequence" required="required" class="form-control" rows="10" placeholder="Paste protein sequences in fasta format"></textarea>
                            </div>
                            <div class="form-group">
                                <button id="input-fill-submit" type="submit" class="btn btn-info btn-block btn-lg">Submit Job</button>
                            </div>
                            <hr />
                        </form>
                    </div>
                    <div class="tab-pane fade in" id="tab2">
                        <form target="_blank" id="input-upload-form" class="col-lg-12  col-md-12  col-sm-12" enctype="multipart/form-data" action="${pageContext.request.contextPath}/job/upload" method="post">
                            <div class="form-group">
                                <label>Or upload a fasta file: </label>
                                <div class="input-group">
                                    <input type="text" class="form-control" onkeydown="return false;" placeholder="Select input file..." id="textFileName" />
                                    <label class="input-group-btn">
                                        <span class="btn btn-primary" >
                                            <i class="fa fa-folder-open"></i> Browse...
                                            <input type="file" name="file" style="display:none;" onchange="$('#textFileName').val(this.value)" />
                                        </span>
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <button class="btn btn-success btn-block btn-lg" type="submit">Submit Job</button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="col-lg-12  col-md-12  col-sm-12">
                    <blockquote>
                        To help you better understand the result, you can refer to the <a href="${pageContext.request.contextPath}/help">help documentation</a>.
                    </blockquote>
                </div>

            </div>
        </div>

        <jsp:include page="_footer.jsp" />
        <jsp:include page="_script.jsp" />

        <script>
            $(function () {
                $('#enterExample').click(function () {
                    var text =
                            ">Q8R4H7\n" +
                            "MATAWVATALRSAAAARRLRSPGGPGGSRRLSGSARRRGAKSASPGRRLSTARAHAEDAE\n" +
                            "GAKGRVQSPAVEEPSWTPLPTPLESPAPPAGRSLVQRDIQAFLNQCGASPGEARHWLTQF\n" +
                            "QTCYHSVDKPFAVMEVDEEVIRCPQAVSRLAFALAFLQRMDMKPLVVLGLPTPTAPSGCL\n" +
                            "SFWEAKAQLAQSCKVLVDELRHNAATAVPFFGGGSVLSAAEPAPHASYGGIVAVETDLLQ\n" +
                            "WCLESNSIPILCPIGETAARRSVLLDSLEVTASLAKALQPTKIIFLNNSGGLRNNSQKIL\n" +
                            "SNVNLPADLDLVTNAEWLSIKERQQIRLIVDVLSRLPHYSSAVITAASTLLTELFSNKGC\n" +
                            "GTLFKNAERMLRVRNLDSLDQGRLVNLVNASFGKKLREDYLESLRPRLHSIYVSEGYNAA\n" +
                            "AILTVEPVLGGTPYLDKFVVSSSRQGQGSGQMLWECLRRDLQTLFWRSRVTNPINPWYFK\n" +
                            "HSDGSFSNKQWIFFWFGLADIRDSYELVNHAKGLPDSFCKPASDPGS";
                    $('#sequenceText').val(text);
                });
            });
        </script>

    </body>
</html>
