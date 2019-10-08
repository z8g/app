<%-- 
    Document   : Job
    Created on : 2019-6-13, 8:26:39
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp">
            <jsp:param name="title" value="Job List - PIPPIN" />
        </jsp:include>
    </head>
    <body >

        <jsp:include page="_nav.jsp" />


        <div id="job-list-sec" class="container set-pad sec">
            <div class="row text-center">
                <div class="col-lg-12  col-md-12  col-sm-12">
                    <h1 class="header-line">Job List</h1>
                    <p>
                        Here is a list of jobs submitted by all users, your Job ID is <code id="jobId">123</code>
                    </p>
                    <table id="job-list-table"></table>
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

                $.get("job/id", function (jsonData) {
                    $('#jobId').text(jsonData);
                });
                $.getJSON("job/list.json", function (jsonData) {
                    var result = '<thead>' + '<tr>' +
                            '<th>Job ID</th>' +
                            '<th>Submit Time</th>' +
                            '<th>Status</th>' +
                            '<th>Details</th>' +
                            '</tr></thead>' +
                            '<tbody>';
                    $.each(jsonData, function (k, v) {
                        var row = '<tr>' +
                                '<td class="center">' + v.id + '</td>' +
                                '<td class="center">' + v.time + '</td>' +
                                '<td class="center">' + v.status + '</td>' +
                                '<td class="center"><a id="' + v.id + '"  href="${pageContext.request.contextPath}/job/' + v.id + '">Details</a></td>' +
                                '</tr>';
                        result += row;
                    });
                    result += '</tbody>';
                    $('#job-list-table').append(result); //填充数据表的内容

                    //创建数据表，带工具栏，全数字分页
                    $('#job-list-table').dataTable({
                        "sPaginationType": "full_numbers"
                    });
                });
            });

        </script>
    </body>
</html>
