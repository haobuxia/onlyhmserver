<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
    <link href="/static/materialize/css/icon.css" rel="stylesheet">
    <link href="/static/materialize/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="/static/materialize/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="/static/materialize/css/ghpages-materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
    <script src="/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="/static/js/utils.js?v=${version}"></script>
    <script src="/static/js/common.js?v=${version}"></script>
    <script src="/static/materialize/js/init.js"></script>
</head>
<body>
<header>
    <div class="fixed-announcement">
        <div class="announcement">
            <nav>
                <div class="nav-wrapper">
                    <div class="col s12">
                        <a href="javascript:void(0)" class="breadcrumb" >
                            <i class="material-icons dp48" id='opeFullScreenBtn'onclick="requestFullScreen()" title="点击全屏">fullscreen</i>
                            <i class="material-icons dp48" id='closeFullScreenBtn'onclick="exitFullscreen()" style="display: none" title="点击关闭全屏">fullscreen_exit</i>
                        </a>
                        <a href="javascript:void(0)" class="breadcrumb"> </a>
                        <a href="javascript:void(0)" class="breadcrumb"> </a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="container"><a href="#" data-activates="nav-mobile"
                              class="button-collapse top-nav full hide-on-large-only"><i class="material-icons black-text">menu</i></a>
    </div>
    <jsp:include page="console-leftmenu.jsp"/>
</header>
<main>
    <!--  工作区  -->
</main>
<div class="progress hide">
    <div class="indeterminate"></div>
</div>
<!--  Scripts-->
<script src="/static/materialize/js/materialize.min.js"></script>
<!--  tymapapi -->
<script src="/static/js/consoleIndex.js?v=${version}"></script>
<%--<script src="${tianYuanMapApiUrl}TYMapAPI?key=${tianYuanMapApiKey}"></script>--%>
<script src="/static/tymap/TYMapAPI.js"></script>
<script src="/static/js/tymap.js?v=${version}"></script>
</body>
</html>