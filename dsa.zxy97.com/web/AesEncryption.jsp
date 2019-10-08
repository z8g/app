<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    AES加密 - 桌面版 <span class="label label-info">附带压缩功能</span>
                </div>
                <div class="panel-body" id="exp01">
                    <p class="well">下载<a href="data/AESEncryption.jar">AESEncryption.jar</a></p>
                </div>
            </div>
        </div>
    </section>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    AES加密 - Web版 <span class="label label-info">2018-10-29更新</span>
                </div>
                <div class="panel-body">                
                    <div class="form-group">
                        <label class="control-label" for="AesKey">密钥：</label>
                        <textarea class="form-control" placeholder="请输入密钥" id="AesKey" name="AesKey" rows="2" required="required">${content}</textarea>
                </div>
                <div class="form-group">
                    <p><button class="form-control" onclick="AesSetKey(true)">设置密钥并选择加密</button></p>
                    <p><button class="form-control" onclick="AesSetKey(false)">设置密钥并选择解密</button></p>
                    <p class="well" id="AesSetKeyResponse"></p>
                </div>
                
                <form action="AesServlet" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label" for="uploadfile">选择文件：</label>
                        <input type="file" class="form-control" name="uploadfile" id="uploadfile"/>
                    </div>
                    <div class="form-group">
                        <p><button class="form-control">上传文件</button></p>
                    </div>
                </form>
                
                <!--webkitdirectory-->
            </div>
        </div>
    </div>
</section>
<jsp:include page="bottom.jsp"></jsp:include>