<%-- 
    Document   : _head
    Created on : 2019-2-5, 21:51:30
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <base href="${pageContext.request.contextPath}/" />
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="assets/images/favicon.png" type="image/png">
        <title>${param.title}</title>
        <link href="assets/css/icons.css" rel="stylesheet">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet">
        <link href="assets/css/responsive.css" rel="stylesheet">

        <!--[if lt IE 9]>
              <script src="assets/js/html5shiv.min.js"></script>
              <script src="assets/js/respond.min.js"></script>
        <![endif]-->

    </head>

    <body class="error-bg">

        <section>
            <div class="container">
                <div class="row">
                    <div class="error-inner">
                        <h1>${param.title}</h1>