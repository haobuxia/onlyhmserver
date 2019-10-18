<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <!-- CSS  -->
    <link href="/static/materialize/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="/static/materialize//css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

    <title>田一智能头盔-设置用户</title>

    <script type="text/javascript" src="/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="/static/materialize/js/materialize.min.js"></script>
    <script type="text/javascript" src="/static/js/common.js?v=${version}"></script>

</head>
<body>
    <div class="section no-pad-bot">
        <div class="container">
            <div class="row">
                <div class="col">
                    <span class="black-text">请填写您的用户信息</span>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input type="hidden" name="helmetId" id="helmetId" value="${id}" readonly/>
                    <input type="text" name="userName" id="userName" value=""/>
                    <label for="userName" class="active">您的姓名</label>
                </div>
                <div class="input-field col s12">
                    <input type="text" name="userPhone" id="userPhone" value=""/>
                    <label for="userPhone" class="active">手机号</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <img id="captchaImg" style="width:120px;height:40px;" title="点击更换验证码"/>
                    <input id="verifyCode" placeholder="输入上册图形验证码中的字符" type="text" class="validate" required data-length="4" maxlength="4">
                    <label for="verifyCode" class="active">图形验证码</label>
                </div>
                <div class="input-field col s12">
                    <a class="waves-effect waves-light btn disabled" id="sendSmsBtn">发送短信码</a>
                    <input id="msgCode" type="text" name="msgCode" placeholder="短信验证码" value="" data-length="4" required maxlength="4">
                    <label for="msgCode" class="active">短信验证码</label>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <button class="btn waves-effect waves-light" id="bindButton" style="cursor: pointer;">提交</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script src="/static/js/verifycode.js?v=${version}"></script>
<script src="/static/js/bind.js?v=${version}"></script>