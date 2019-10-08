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
                    散列 <span class="label label-info">支持中文字符</span>
                </div>
                <div class="panel-body">
                <form action="HashServlet" method="post">
                    <div class="form-group">
                        <label class="control-label" for="content">明文：</label>
                        <textarea class="form-control" placeholder="请输入明文，即要加密的内容" id="content" name="content" rows="2" required="required">${content}</textarea>
                    </div>
                    <div class="form-group">
                        <p><button class="form-control">提交</button></p>
                    </div>     
                    <div class="form-group">
                        <label class="control-label"><span class="label label-success">MD5</span></label>
                        <pre id="result">${result_md5}</pre>
                    </div>   
                    <div class="form-group">
                        <label class="control-label"><span class="label label-success">SHA</span></label>
                        <pre id="result">${result_sha}</pre>
                    </div>
                   
                </form>
                </div>
            </div>
        </div>
    </section>

<jsp:include page="bottom.jsp"></jsp:include>

                    

