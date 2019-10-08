<%-- 
    Document   : CaesarEncryption
    Created on : 2018-10-22, 15:57:06
    Author     : zhaoxuyang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    古典加密 - Web版 <span class="label label-info">支持中文字符</span>
                </div>
                <div class="panel-body">
                    <form action="CaesarServlet" method="post">
                    <div class="form-group">
                        <label class="control-label" for="res">字符集：</label>
                        <textarea class="form-control" placeholder="请输入字符集" id="res" name="res" rows="4" required="required">${res}</textarea>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="content">明文：</label>
                        <textarea class="form-control" placeholder="请输入明文，即要加密的内容" id="content" name="content" rows="4" required="required">${content}</textarea>
                    </div>
                    <div class="form-group">
                        <select name="isDecode" class="form-control">
                            <option value="false">加密</option>
                            <option value="true">解密</option>
                        </select>     
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="key">秘钥：</label>
                        <input name="key" id="key" class="form-control" type="number" required="required" placeholder="请输入秘钥，填写大于0的一个整数" min="0" value="${key}" />
                    </div>
                    <div class="form-group">
                        <p><button class="form-control">提交</button></p>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label">结果：</label>
                        <pre id="result">${result}</pre>
                    </div>
                </form>
                </div>
            </div>
        </div>
    </section>

<jsp:include page="bottom.jsp"></jsp:include>

                    

