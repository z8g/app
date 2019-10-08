<%-- 
    Document   : RsaEncryption
    Created on : 2018-11-18, 15:02:31
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    RSA - 客户端公钥加密，服务端私钥解密 <span class="label label-info">2018-11-18更新</span>
                </div>
                <div class="panel-body">                
                    <div class="form-group">
                        <label class="control-label" for="publickey">服务端提供的公钥：</label>
                        <textarea class="form-control" placeholder="请输入服务端提供的公钥" id="publickey" name="publickey" rows="3" required="required">MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ9s1qlOyv9qpuaTqauW6fUftzE50rVk3yVPZwv1aO1Ch/XSEz76xCwkyvqpaqceRXrPpdBmO5+ruJ+I8osOHo7L5GWEOcMOO+8izp9hXKBBrmRMD4Egpn00k9DhVIEKp/vyddZPS/doxB8onhN6poTJDLdFLFVEicMf52caN9GQIDAQAB</textarea>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="uname">要加密的数据-例如账号：</label>
                        <input name="uname" id="uname" class="form-control" type="text" required="required" placeholder="请输入账号" min="0"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="password">要加密的数据-例如密码：</label>
                        <input name="password" id="password" class="form-control" type="text" required="required" placeholder="请输入密码" min="0"/>
                    </div>

                    <div class="form-group">
                        <p><button class="form-control" id="rsa-btn">提交</button></p>
                    </div>

                    <p>账号传到服务端前使用公钥加密：<span class="label label-info">客户端公钥加密（JS实现）</span></p>
                    <pre id="jmName"></pre>
                    <p>密码传到服务端前使用公钥加密：<span class="label label-info">客户端公钥加密（JS实现）</span></p>
                    <pre id="jmPasswrod"></pre>

                    <p>服务端返回结果：<span class="label label-info">服务端私钥解密</span></p>
                    <pre id="rsa-result"></pre>
                    <!--webkitdirectory-->
                </div>
            </div>
        </div>
    </section>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    RSA - 服务端私钥签名，客户端公钥验证演示 <span class="label label-info">2018-11-18更新</span>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="control-label" for="rsa_sign_input">数据：</label>
                        <textarea class="form-control" placeholder="请输入数据" id="rsa_sign_input" name="rsa_sign_input" rows="3" required="required"></textarea>
                    </div>
                    <div class="form-group">
                        <p><button class="form-control" id="rsa-sign-btn">提交</button></p>
                    </div>
                    <p>演示：<span class="label label-info">验证过程</span></p>
                    <pre id="rsa-sign-result"></pre>
                    <!--webkitdirectory-->
                    
                    <p><code>zxy97.com</code>的签名：</p>
                    <pre>ZEPxYBM50hmtK/jvmz5O8kmdgZF249xi0LKZMCaQ+kXBoUJtgX+UxDAVCNVTtp1wW83qkfL9Ooaxq7x2g2C1uQVocABR5rpP0bN//I8H8KVjhV5rOfK1IEoSIqRDBJzdm6JyHIjeWIovOPF8HaCwjgWi0XVtR0s/h8mNRsXVewg=</pre>
                </div>
            </div>
        </div>
    </section>

    <script type="text/javascript">
        $(function() {
            $("#rsa-btn").click(function() {
                var encrypt = new JSEncrypt();
                encrypt.setPublicKey($("#publickey").val());
                //encrypt.setPrivateKey($("#privatekey").val());
                var password = $("#password").val();
                var uname = $("#uname").val();
                uname = encrypt.encrypt(uname);
                password = encrypt.encrypt(password);
                $("#jmName").text(uname);
                $("#jmPasswrod").text(password);
                var jsonData = {
                    "password": password,
                    "uname": uname
                };
                $.post("RsaServlet", jsonData, function(result) {
                    $("#rsa-result").text("服务端解密的用户名：" + result.uname + "\n" + "服务端解密的密码：" + result.password);
                }, 'json');
            });
            
            $("#rsa-sign-btn").click(function() {
                var rsa_sign_input = $("#rsa_sign_input").val();
                var jsonData = {
                    "source": rsa_sign_input
                };
                $.post("RsaSignServlet", jsonData, function(result) {
                    $("#rsa-sign-result").text(result);
                });
            });
        });
    </script>


<jsp:include page="bottom.jsp"></jsp:include>