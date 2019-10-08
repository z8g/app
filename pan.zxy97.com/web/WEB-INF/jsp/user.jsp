<%-- 
    Document   : user
    Created on : 2018-12-31, 15:20:12
    Author     : zhaoxuyang
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="userProcess" value="${webRoot}/process.html"></c:set>

    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8">
            <title><spring:message code="website.user.title"/></title>
            <link rel="stylesheet" href="${webRoot}/static/css/table.css" >
            <link rel="stylesheet" href="${webRoot}/static/css/a.css" >
            <script src="${webRoot}/static/js/user.js"></script>
            <script>
                
                function deleteCheck() {
                    if (!confirm('<spring:message code="website.user.deletecheck"/>')) {
                        window.event.returnValue = false;
                    }
                }
                function renameCheck(oldName) {
                    var newName = prompt('<spring:message code="website.user.newfolder.inputfilename"/>', oldName);
                    if (newName !== null) {
                        window.location.href = "${userProcess}?op=rename&name=" + oldName + "&newName=" + newName;
                    } else {
                        window.event.returnValue = false;
                    }
                }
            </script>
    </head>
    <body>
        <p>
            <a href="${userProcess}?op=upper"><spring:message code="website.user.upper"/></a>
            <span class="tip-warning tip-margin" title='<spring:message code="website.user.currpath"/>'><small>${path_uri}</small></span>
        </p>

        <table class="style" id="tableSort">
            <tr>
                <th onclick="sortTable('tableSort', 0)" title='<spring:message code="website.user.files.clicksortr"/>'> <spring:message code="website.user.files.type"/> </th>
                <th onclick="sortTable('tableSort', 1)" title='<spring:message code="website.user.files.clicksortr"/>'> <spring:message code="website.user.files.name"/> </th>
                <th onclick="sortTable('tableSort', 2)" title='<spring:message code="website.user.files.clicksortr"/>'> <spring:message code="website.user.files.size"/> </th>
                <th onclick="sortTable('tableSort', 3)" title='<spring:message code="website.user.files.clicksortr"/>'> <spring:message code="website.user.files.lasttime"/> </th>
                    <c:if test="${isUserSelf==true}">
                    <th onclick="sortTable('tableSort', 4)" title='<spring:message code="website.user.files.clicksortr"/>'> <spring:message code="website.user.files.delete"/> </th>
                    <th onclick="sortTable('tableSort', 5)" title='<spring:message code="website.user.files.clicksortr"/>'> <spring:message code="website.user.files.rename"/> </th>
                    <th onclick="sortTable('tableSort', 6)" title='<spring:message code="website.user.files.clicksortr"/>'> <spring:message code="website.user.files.sharp"/> </th>
                    <th onclick="sortTable('tableSort', 7)" title='<spring:message code="website.user.files.clicksortr"/>'> <spring:message code="website.user.files.edit"/> </th>
                    </c:if>
            </tr>

            <c:forEach var="sf" items="${fileVOList}">
                <tr>
                    <td>${sf.type}</td>
                    <td><a href="${userProcess}?op=child&name=${sf.name}">${sf.name}</a></td>
                    <td>${sf.size}</td>
                    <td>${sf.lastTime}</td>
                    <c:if test="${sessionScope.isUserSelf==true}">
                        <td><a href="${userProcess}?op=delete&name=${sf.name}" onclick="deleteCheck()"><spring:message code="website.user.files.delete"/></a></td>
                        <td><a href="#" onclick="renameCheck('${sf.name}')"><spring:message code="website.user.files.rename"/></a></td>

                        <c:choose>
                            <c:when test="${sf.type=='文件'}">
                                <td><a href="${webRoot}/download.html?file=${path_uri}/${sf.name}" title="${webRoot}/download.html?file=${path_uri}/${sf.name}"><spring:message code="website.user.files.rightclickcopy"/></a></td>
                            </c:when>
                            <c:otherwise>
                                <td>&nbsp;</td>
                            </c:otherwise>
                        </c:choose>

                        <td><a href="${userProcess}?op=edit&name=${sf.name}"><spring:message code="website.user.files.edit"/></a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${sessionScope.isUserSelf==true}">

            <table class="style">
                <tr>
                    <th><spring:message code="website.user.files.op"/></th>
                    <th>&nbsp;</th>
                </tr>
                <tr>
                    <td>
                        <form action="${userProcess}?op=upload" method="post" enctype="multipart/form-data">
                            <input type="file" name="uploadfile" title='<spring:message code="website.user.upload.selectfolder"/> ' webkitdirectory>
                            <button type="submit" class="btn"><spring:message code="website.user.upload.uploadfolder"/></button>
                        </form>
                    </td>
                    <td>
                        <form  action="${userProcess}?op=upload" method="post" enctype="multipart/form-data">
                            <input type="file" name="uploadfile" title='<spring:message code="website.user.upload.selectfile"/> '>
                            <button type="submit" class="btn"><spring:message code="website.user.upload.uploadfile"/> </button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="${userProcess}?op=newFolder" method="post">
                            <input type="text"  name="name" placeholder='<spring:message code="website.user.new.foldername"/>'>
                            <button type="submit" class="btn"><spring:message code="website.user.new.newfolder"/></button>
                        </form>
                    </td>
                    <td>
                        <form action="${userProcess}?op=newFile" method="post">
                            <input type="text"  name="name" placeholder='<spring:message code="website.user.new.filename"/>'>
                            <button type="submit" class="btn"><spring:message code="website.user.new.newfile"/></button>
                        </form>
                    </td>
                </tr>
            </table>
            <table class="style">
                <tr>
                    <th><spring:message code="website.user.set.title"/></th>
                </tr>
                <tr>
                    <td>
                        <a href=""><strong>${user.username}</strong></a>
                        <span id="welcome_info"><spring:message code="website.user.set.welcome"/></span>
                        <a href="${webRoot}/logout.action"><span class="tip-warning"><spring:message code="website.user.set.logout"/></span></a>
                    </td></tr>
                <tr>
                    <td><spring:message code="website.user.set.info.uploadfolder"/><span class="tip-right">Chrome</span></td>
               	</tr>
               	<tr>
                    <td><spring:message code="website.user.set.info.privatefolder"/><span class="tip-right">_pass</span></td>
                </tr>
            </table>
        </c:if>
    </body>
</html>
