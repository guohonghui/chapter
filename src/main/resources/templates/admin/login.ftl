<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登陆</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="${request.contextPath}/favicon.ico">
    <link href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
    <style>
        .form-control:focus,
        .single-line:focus {
            border-color: #0e9aef !important;
        }
        .middle-box {
            padding-top: 60px;
            margin: 8% auto;
        }
        .loginscreen.middle-box {
            width: 360px;
        }
        .box {
            background: #fff;
            padding: 20px;
        }
    </style>
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div class="box">
        <div>
        </div>
        <h3>权限管理</h3>
        <form class="m-t" role="form" action="/admin/mylogin" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="username" value="test" placeholder="用户名" >
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" value="admin" placeholder="密码" >
            </div>
            <#if msg??>
                <div class="form-group">
                    <span style="color: #cc6571;">${msg}</span>
                </div>
            </#if>
            <button type="submit" class="btn btn-success block full-width m-b">登 录</button>


            <p class="text-muted text-center"> <a href="login.html"><small>忘记密码了？</small></a> | <a href="/admin/register.html">注册一个新账号</a>
            </p>

        </form>
    </div>
</div>
<!-- 全局js -->
<script src="${request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>