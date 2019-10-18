<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="section no-pad-bot">
    <div class="container">
        <div class="row center">
            <h4>用户注册</h4><br><br>
            <div class="row">
                <form class="col s12">
                    <div class="row">
                        <div class="input-field col s12 m6 l3 ">
                            <input id="mobile" type="text" name="phone" placeholder="phone" value="" data-length=11 required maxlength="11">
                            <label for="mobile" class="active">手机号</label>
                        </div>
                        <div class="input-field col s12 m6 l3">
                            <img id="captchaImg" style="width:120px;height:40px;" title="点击更换验证码"/>
                        </div>
                        <div class="input-field col s12 m6 l3">
                            <input id="verifyCode" placeholder="输入左侧图形验证码中的字符" type="text" class="validate" required data-length="4" maxlength="4">
                            <label for="verifyCode" class="active">图形验证码</label>
                        </div>
                        <div class="input-field col s12 m6 l3 left-align">
                            <a class="waves-effect waves-light btn disabled" id="sendSmsBtn">发送短信码</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input id="password" type="password" name="password" placeholder="账号密码" value="" data-length="15" required maxlength="15">
                            <label for="password" class="active">账号密码</label>
                        </div>
                        <div class="input-field col s12 m6">
                            <input id="msgCode" type="text" name="msgCode" placeholder="短信验证码" value="" data-length="4" required maxlength="4">
                            <label for="msgCode" class="active">短信验证码</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <button class="btn waves-effect waves-light" id="userRegisterBtn">注册
                                <i class="material-icons right">send</i>
                            </button>
                            <a href="#" id="userLoginBtn" class="blue-text text-darken-2">登录已有账号</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    var registerFormPage = {};
</script>
<script src="/static/js/verifycode.js?v=${version}"></script>
<script src="/static/js/registerForm.js?v=${version}"></script>
