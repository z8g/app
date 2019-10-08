<%-- 
    Document   : dsa
    Created on : 2019-1-3, 11:20:43
    Author     : zhaoxuyang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#sign" data-toggle="collapse" title="展开/关闭">私钥签名  </a><span class="label label-info">服务端处理</span>
                </div>
                <div class="panel-body collapse" id="sign">                
                    <div class="form-group">
                        <label class="control-label" for="sign_message">请输入要签名的消息</label>
                        <textarea class="form-control" placeholder="请输入要签名的消息" id="sign_message" name="sign_message" rows="3" required="required"></textarea>
                    </div>
                    <div class="form-group">
                        <p><button class="form-control" id="sign-btn">提交</button></p>
                    </div>
                    <p>服务端返回结果：<span class="label label-info">服务端私钥签名</span></p>
                    <pre id="sign-result"></pre>
                </div>
            </div>
        </div>
    </section>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#signVerify" data-toggle="collapse" title="展开/关闭">公钥验证签名  </a><span class="label label-info">服务端处理</span>
                </div>
                <div class="panel-body collapse" id="signVerify">
                    <div class="form-group">
                        <label class="control-label" for="signVerify_message">请输入消息</label>
                        <textarea class="form-control" placeholder="请输入消息" id="signVerify_message" name="signVerify_message" rows="3" required="required"></textarea>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="signVerify_publicKey">请输入公钥</label>
                        <textarea class="form-control" placeholder="请输入公钥" id="signVerify_publicKey" name="signVerify_publicKey" rows="3" required="required"></textarea>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="signVerify_sign">请输入签名</label>
                        <textarea class="form-control" placeholder="请输入签名" id="signVerify_sign" name="signVerify_sign" rows="3" required="required"></textarea>
                    </div>
                    <div class="form-group">
                        <p><button class="form-control" id="signVerify-btn">提交</button></p>
                    </div>
                    <p>服务端返回结果：<span class="label label-info">服务端私钥签名</span></p>
                    <pre id="signVerify-result"></pre>
                </div>
            </div>
        </div>
    </section>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#hash" data-toggle="collapse" title="展开/关闭">MD5和SHA1散列  </a><span class="label label-info">服务端处理</span>
                </div>
                <div class="panel-body collapse" id="hash">
                    <div class="form-group">
                        <label class="control-label" for="hash_message">请输入消息</label>
                        <textarea class="form-control" placeholder="请输入消息" id="hash_message" name="hash_message" rows="3" required="required"></textarea>
                    </div>
                    <div class="form-group">
                        <p><button class="form-control" id="hash-btn">提交</button></p>
                    </div>
                    <p>服务端返回MD5结果：<span class="label label-info">MD5</span></p>
                    <pre id="hash-result-md5"></pre>
                    <p>服务端返回SHA1结果：<span class="label label-info">SHA1</span></p>
                    <pre id="hash-result-sha1"></pre>
                </div>
            </div>
        </div>
    </section>


    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a href="#show1" data-toggle="collapse" title="展开/关闭">客户端公钥加密，服务端私钥解密  </a><span class="label label-info">双端处理</span>  
                </div>
                <div class="panel-body collapse" id="show1">                
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
                    <a href="#show2" data-toggle="collapse" title="展开/关闭">服务端私钥签名，客户端公钥验证  </a><span class="label label-info">服务端处理</span>  
                </div>
                <div class="panel-body collapse" id="show2">
                    <div class="form-group">
                        <label class="control-label" for="rsa_sign_input">数据：</label>
                        <textarea class="form-control" placeholder="请输入数据" id="rsa_sign_input" name="rsa_sign_input" rows="3" required="required"></textarea>
                    </div>
                    <div class="form-group">
                        <p><button class="form-control" id="rsa-sign-btn">提交</button></p>
                    </div>
                    <p>演示：<span class="label label-info">验证过程</span></p>
                    <pre id="rsa-sign-result"></pre>

                    <p><code>zxy97.com</code>的签名：</p>
                    <pre>ZEPxYBM50hmtK/jvmz5O8kmdgZF249xi0LKZMCaQ+kXBoUJtgX+UxDAVCNVTtp1wW83qkfL9Ooaxq7x2g2C1uQVocABR5rpP0bN//I8H8KVjhV5rOfK1IEoSIqRDBJzdm6JyHIjeWIovOPF8HaCwjgWi0XVtR0s/h8mNRsXVewg=</pre>
                </div>
            </div>
        </div>
    </section>

    <script type="text/javascript">
        $(function() {

            //私钥签名
            $("#sign-btn").click(function() {
                var sign_message = $("#sign_message").val();
                var jsonData = {
                    "op": "sign",
                    "message": sign_message
                };
                $.post("process", jsonData, function(result) {
                    var text = "验证签名的公钥：\n" + result.publicKey + "\n\n签名：\n" + result.sign + "\n\n加密后的数据：\n" + result.encoded;
                    $("#sign-result").text(text);
                }, 'json');
            });

            //公钥验证签名
            $("#signVerify-btn").click(function() {
                var signVerify_message = $("#signVerify_message").val();
                var signVerify_publicKey = $("#signVerify_publicKey").val();
                var signVerify_sign = $("#signVerify_sign").val();
                var jsonData = {
                    "op": "signVerify",
                    "message": signVerify_message,
                    "publicKey": signVerify_publicKey,
                    "sign": signVerify_sign
                };
                $.post("process", jsonData, function(result) {
                    var text = "验证结果：\n" + result.verify;
                    $("#signVerify-result").text(text);
                }, 'json');
            });


            //MD5和SHA1散列
            $("#hash-btn").click(function() {
                var hash_message = $("#hash_message").val();
                var jsonData = {
                    "op": "hash",
                    "message": hash_message
                };
                $.post("process", jsonData, function(result) {
                    $("#hash-result-md5").text(result.md5);
                    $("#hash-result-sha1").text(result.sha1);
                }, 'json');
            });


            //公钥验证签名
            $("#signVerify-btn").click(function() {
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

            //客户端公钥加密，服务端私钥解密
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
            
            //私钥签名，公钥验证的过程
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