<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="UTF-8" /><!--设置编码-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge" /><!--强制Chrome内核-->
        <meta name="viewport" content="width=device-width, initial-scale=1" /><!--移动端正确显示页面并正确缩放-->
        <meta name="description" content="zxy97在线制作,在线制作软件下载页面" />
        <meta name="keywords" content="在线制作网页,在线制作软件下载页面" />
        <title>ogdp.zxy97.com - 在线制作软件下载页面</title>
        <link rel="shortcut icon" href="http://zxy97.com/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        
        <!--[if lt IE 9]>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.1/html5shiv-printshiv.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>
        <![end if]-->

    </head>

    <body>

        <div class="container">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#myNav">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <img src="http://zxy97.com/images/logo.png" alt="zxy97.com" id="logo">
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="myNav">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">返回首页</a></li>
                        <li><a href="make.jsp">刷新本页</a></li>
                    </ul>
                    <form class="navbar-form navbar-right" action="https://www.baidu.com/s" target="_blank">
                        <div class="form-group">
                            <input class="form-control" type="search" name="wd" placeholder="输入关键词……">
                            <button class="form-control" type="submit">搜索</button>
                        </div>
                    </form>
                </div>
            </nav>

    <section class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    在线制作下载页面 <span class="label label-info">ogdp.zxy97.com</span> (Online generated download page)
                </div>
                <div class="panel-body">
                    <form action="make" method="post">
                    <div class="form-group">
                        <label class="control-label" for="softname">软件的名称：</label>
                        <input value="" type="text" class="form-control" placeholder="请输入软件的名称" id="softname" name="softname" required="required">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="softlogourl">软件的LOGO地址：<span><a href="http://file.zxy97.com/" target="_blank" title="http://file.zxy97.com/">图片上传</a></span></label>
                        <input value="" type="url" class="form-control" placeholder="请输入软件的LOGO地址" id="softlogourl" name="softlogourl" required="required">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="softinfotitle">软件简介的标题：</label>
                        <input value="" type="text" class="form-control" placeholder="请输入软件简介的标题" id="softinfotitle" name="softinfotitle" required="required">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="softinfocontent">软件简介的内容：</label>
                        <textarea type="text" class="form-control" placeholder="请输入软件简介的内容" id="softinfocontent" name="softinfocontent" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="softdownloadurl">软件的下载地址：<span><a href="http://file.zxy97.com/" target="_blank" title="http://file.zxy97.com/">资源上传</a></span></label>
                        <input value="" type="url" class="form-control" placeholder="请输入软件的下载地址" id="softdownloadurl" name="softdownloadurl" required="required">
                    </div>
                    <!--
                    <div class="form-group">
                        <label class="control-label" for="softbgurl">软件下载界面背景图的地址：<a href="http://img.zxy97.com/" target="_blank" title="http://img.zxy97.com/">图片上传网站</a></span></label>
                        <input type="url" class="form-control" placeholder="请输入软件下载界面背景图的地址（选填）" id="softbgurl" name="softbgurl">
                    </div>
                        -->
                    <div class="form-group">
                        <p><button class="form-control">提交</button></p>
                    </div>

                    <div class="form-group">
                        <label class="control-label">获取结果：<a href="http://dwz.zxy97.com" target="_blank" title="http://dwz.zxy97.com/">在线生成短网址</a></label>
                        <pre id="result">${urlPath}</pre>
                    </div>
                </form>
                </div>
            </div>
        </div>
    </section>


<section class="row">
    <p class="text-center">&copy; 2018 <a href="http://zxy97.com" target="_blank">zxy97.com</a> 版权所有 | <a href="http://www.miibeian.gov.cn/" target="_blank">陕ICP备18005813号</a></p> 
</section>
 
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>