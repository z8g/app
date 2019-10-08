<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="UTF-8" /><!--设置编码-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge" /><!--强制Chrome内核-->
        <meta name="viewport" content="width=device-width, initial-scale=1" /><!--移动端正确显示页面并正确缩放-->
        <title>数字签名与认证</title>
        <link rel="shortcut icon" href="http://zxy97.com/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script src="js/ajax.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jsencrypt.min.js"></script>
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
                        <li><a href="dsa.jsp">数字签名与认证</a></li>
                        
<!--                        <li><a href="index.jsp">首页</a></li>-->
                        <li><a href="exp.jsp">实习*</a></li>
                        <li><a href="CaesarEncryption.jsp">古典加密*</a></li>
                        <li><a href="AesEncryption.jsp">AES加密*</a></li>
                        <li><a href="RsaEncryption.jsp">RSA加密*</a></li>
                        <li><a href="Hash.jsp">散列*</a></li>

                    </ul>
                    <form class="navbar-form navbar-right" action="https://www.baidu.com/s" target="_blank">
                        <div class="form-group">
                            <input class="form-control" type="search" name="wd" placeholder="输入关键词……">
                            <button class="form-control" type="submit">搜索</button>
                        </div>
                    </form>
                </div>
            </nav>