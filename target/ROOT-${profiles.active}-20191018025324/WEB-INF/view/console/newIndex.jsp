<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    
    <meta name="msapplication-tap-highlight" content="no">
    <title>田一智能头盔控制台</title>
    <!-- Favicons-->
    <link rel="apple-touch-icon-precomposed" href="/static/images/favicon/apple-touch-icon-152x152.png">
    <meta name="msapplication-TileColor" content="#FFFFFF">
    <meta name="msapplication-TileImage" content="/static/images/favicon/mstile-144x144.png">
    <link rel="icon" href="/favicon.ico" sizes="32x32">
    <!--  Android 5 Chrome Color-->
    <meta name="theme-color" content="#EE6E73">
    <!-- CSS-->

    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="/static/assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/assets/vendor/linearicons/style.css">
    <link rel="stylesheet" href="/static/assets/vendor/toastr/toastr.min.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="/static/assets/css/main.css?v=${version}">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="/static/assets/css/demo.css?v=${version}">


    <script src="/static/jquery/jquery-2.1.1.min.js"></script>

    <script src="/static/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
   
</head>
<body class oncontextmenu=self.event.returnValue=false> <%--oncontextmenu=self.event.returnValue=false  禁用右键--%>
<!-- WRAPPER -->
<div id="wrapper">

    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="/console/index" class="logo"><img src="/static/images/tianyikeji.png" alt="田一科技" style="height: 16px;"></a>
        </div>
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
            </div>

            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">
                   <!--  <li class="dropdown">
                        <a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
                            <i class="lnr lnr-alarm"></i>
                            <span class="badge bg-danger">5</span>
                        </a>
                        <ul class="dropdown-menu notifications">
                            <li><a href="#" class="notification-item"><span class="dot bg-warning"></span>特殊消息:XXXX</a></li>
                            <li><a href="#" class="notification-item"><span class="dot bg-danger"></span>紧急消息:XXXX</a></li>
                            <li><a href="#" class="notification-item"><span class="dot bg-success"></span>一般通知:XXXX</a></li>
                            <li><a href="#" class="more">查看全部</a></li>
                        </ul>
                    </li> -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="uname">${userInfo.username}</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="/"><i class="lnr lnr-home"></i> <span>回到首页</span></a></li>
                            <li><a href="#/personal/index"><i class="lnr lnr-user"></i> <span>个人资料</span></a><li>
                            <li><a href="#/personal/qrcode"><i class="fa fa-barcode"></i> <span>个人二维码</span></a><li>
                            <!-- <li><a href="#"><i class="lnr lnr-cog"></i> <span>设置</span></a></li> -->
                            <li><a data-toggle="modal" data-target="#logout"><i class="lnr lnr-exit"></i> <span>退出登录</span></a></li>
                        </ul>
                    </li>
                    
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- 侧边栏 -->
    <jsp:include page="newconsole-leftmenu.jsp"/>
    
    <main class="main">
        <!--  工作区  -->

        <jsp:include page="newIndexContent.jsp"></jsp:include>

    </main>
    <div class="progress hide">
        <div class="indeterminate"></div>
    </div>
</div>

<!-- 模态框（Modal）  注销登录 -->
<div class="modal fade" id="logout" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                你要注销登录吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <a href="/logout" class="btn btn-warning">
                    注销
                </a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 载入新页面进度条 -->
<div class="progress hide">
    <div class="loading">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
    </div>
</div>
<!-- END WRAPPER -->
<!--  Scripts-->
<script src="/static/js/utils.js?v=${version}"></script>
<script src="/static/js/common.js?v=${version}"></script>

<script src="/static/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- showalert    -->
<script src="/static/assets/vendor/toastr/toastr.min.js"></script>
<script src="/static/assets/scripts/klorofil-common.js"></script>
<!--  tymapapi -->
<script src="/static/js/consoleIndex.js?v=${version}"></script>
<%--<script src="${tianYuanMapApiUrl}TYMapAPI?key=${tianYuanMapApiKey}"></script>--%>
<script src="/static/tymap/TYMapAPI.js"></script>
<script src="/static/js/tymap.js?v=${version}"></script>

<script>
    var userId = '${userInfo.id}';
    var userOrganisation = '${userInfo.organisation}';
</script>
</body>
</html>