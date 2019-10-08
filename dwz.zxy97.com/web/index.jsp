<%-- 
     _                                 ___ _____                    
  __| |_      ______  ______  ___   _ / _ \___  |___ ___  _ __ ___  
 / _` \ \ /\ / /_  / |_  /\ \/ / | | | (_) | / // __/ _ \| '_ ` _ \ 
| (_| |\ V  V / / / _ / /  >  <| |_| |\__, |/ /| (_| (_) | | | | | |
 \__,_| \_/\_/ /___(_)___|/_/\_\\__, |  /_//_/(_)___\___/|_| |_| |_|
                                |___/                               

    Document   : index.jsp
    Created on : 2018-8-3, 3:45:27
    Author     : Administrator
--%>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base target="_blank" />
        <title>zxy97短网址 - 在线生成短网址，提供网址压缩服务</title>
        <link rel="stylesheet" media="screen" href="css/styles.css" >
        <style>
            body{
                width:960px;
                margin:20px 40px;
            }
            #logo{
                vertical-align: middle;
            }

        </style>
    </head>

    <body>
        <form class="contact_form" action="create.jsp" method="post" name="contact_form">
            <ul>
                <li>
                    <h2>在线生成短网址</h2>
                    <span class="required_notification">* 表示必填项</span>
                </li>
                <li>
                    <label for="website">您要缩短的长网址:</label>
                    <input type="url" name="url_long" placeholder="请输入长网址" required pattern="(http|https)://.+" title="请填写正确的网址格式"/>
                    <span class="form_hint">正确格式为：https://www.xxx.com/xxx/yyy/www/sss.html</span>
                </li>
                <li>
                    <label for="website">自定义短网址后缀：</label>
                    <input type="text" name="url_short" placeholder="填写字母或者数字[选填]" pattern="[A-z0-9]*" title="请填写字母或者数字"/>
                    <span class="form_hint">正确格式为：字母或数字</span>
                </li>
                <li>
                    <button class="submit" type="submit">生成短网址</button>
                </li>

                <%
                    String msg = (String) request.getAttribute("msg");
                    if (msg != null) {
                        out.println("<li><p>" + msg + "</p></li>");
                    }
                %>

                <%
                    String msg_url_short = (String) request.getAttribute("msg_url_short");
                    if (msg_url_short != null) {
                %>
                <li>
                    <div id="qrcode"></div>
                    <p><a href="<%=msg_url_short%>"><%=msg_url_short%></a></p>
                    <script src="js/jquery.min.js"></script>
                    <script src="js/qrcode.min.js"></script>
                    <script>
                        jQuery(function() {
                            var text = "<%=msg_url_short%>";
                            if (text !== "") {
                                jQuery('#qrcode').qrcode(text);
                            }
                        });
                    </script>
                </li> 
                <% }%>
                <li>
                    <p>[提示] 本站生成的短网址长期有效，请放心使用。</p> 
                </li>  
            </ul>
        </form>

    </body>
</html>
