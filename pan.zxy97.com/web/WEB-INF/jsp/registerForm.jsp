<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage="../error/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" media="screen" href="${webRoot}/static/css/login.css" >
        <link rel="stylesheet" media="screen" href="${webRoot}/static/css/a.css" >
    </head>

    <body>
        <form action="${webRoot}/register.action" method="post" name="form_reg"  class="login_form">
            <ul>
                <li>
                    <h2><spring:message code="website.registerform.title"/></h2>
                    <span class="required_notification" id="checkDiv">${msg}</span>
                </li>
                <li>
                    <label for="reg_name"><spring:message code="website.registerform.username"/></label>
                    <input id="reg_name" name="reg_name" type="text"
                           required="required"  maxlength="20" onchange="regCheck();"
                           title="<spring:message code="website.registerform.username.input.title"/>"
                           placeholder='<spring:message code="website.registerform.username.input.placeholder"/>' />
                    <span class="form_hint"><spring:message code="website.registerform.username.hint"/></span>
                </li>
                
                <li>
                    <label for="reg_password"><spring:message code="website.registerform.password"/></label>
                    <input id="reg_name" name="reg_password" type="text" 
                           required="required" onchange="regCheck();" pattern="[A-z0-9]{6,20}"
                           title="<spring:message code="website.registerform.password.input.title"/>"
                           placeholder='<spring:message code="website.registerform.password.input.placeholder"/>' />
                    <span class="form_hint"><spring:message code="website.registerform.password.hint"/></span>
                </li>
                
                <li>
                    <label for="reg_email"><spring:message code="website.registerform.email"/></label>
                    <input id="reg_email" name="reg_email" required="required" type="email" 
                           title='<spring:message code="website.registerform.email.input.title"/>'
                           placeholder='<spring:message code="website.registerform.email.input.placeholder"/>'/>
                    <span class="form_hint"><spring:message code="website.registerform.email.hint"/></span>
                </li>
                <li>
                    <button type="submit" class="submit"><spring:message code="website.registerform.register.button"/></button>
                </li>
                <li>
                     <a href="${webRoot}/loginForm.html"><spring:message code="website.header.login"/></a>
                </li>
            </ul>
        </form>

 <script>
function regCheck() {
    var checkDiv = document.getElementById("checkDiv");
    var reg_name = document.form_reg.reg_name.value;
    var url = "regCheck.txt?reg_name=" + reg_name;
    //var xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");

    if (reg_name !== "") {
        var xmlHttp;
        if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        } else {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlHttp.open("POST", url, true);
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState === 4) {
                checkDiv.innerHTML = xmlHttp.responseText;
            } else {
                checkDiv.innerHTML = "...";
            }
        };
        xmlHttp.send();
    }else{
        checkDiv.innerHTML = "";   
    }
}
            
        </script>
    </body>
</html>

