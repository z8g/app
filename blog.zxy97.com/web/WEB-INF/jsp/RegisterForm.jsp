<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage=""%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/login.css" >
        <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/a.css" >

    </head>

    <body>
        <form action="${pageContext.request.contextPath}/author/register" method="post" name="form_reg"  class="login_form">
            <ul>
                <li>
                    <h2>注册</h2>
                    <span class="required_notification" id="checkDiv">${msg}</span>
                </li>
                <li>
                    <label for="reg_name">账号：</label>
                    <input id="reg_name" onchange="regCheck();" name="reg_name" required="required" type="text" pattern="[A-z0-9]{1,20}" placeholder="请填写注册用户名，1至20位英文字母或数字"/>
                    <span class="form_hint">用户名格式：1-20位英文字母或数字</span>
                </li>
                <li>
                    <label for="reg_password">密码：</label>
                    <input id="reg_password" name="reg_password" required="required" type="text" pattern="[A-z0-9]{6,20}" placeholder="请填写注册密码，6至20位英文字母或数字"/>
                    <span class="form_hint">密码格式：6至20位英文字母或数字</span>
                </li>
                <li>
                    <button type="submit" class="submit">注册</button>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/index">首页</a> | <a href="login">登录</a>
                </li>
            </ul>
        </form>
        <script>
            function regCheck() {
                var checkDiv = document.getElementById("checkDiv");
                var reg_name = document.getElementById('reg_name').value;
                var url = "${pageContext.request.contextPath}/regCheck?reg_name=" + reg_name;
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
                } else {
                    checkDiv.innerHTML = "";
                }
            }
        </script>
    </body>
</html>

