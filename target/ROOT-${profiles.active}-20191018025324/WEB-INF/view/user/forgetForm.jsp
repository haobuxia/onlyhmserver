<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="/static/css/login.css?v=${version}">
<div class="content">
    <div class="con_left">
        <img src="/static/images/scene5.png" alt="">
    </div>
    <div class="con_right">
        <div class="con_r_top">
            <a href="javascript:;" class="iright" style="color: rgb(51, 51, 51); border-bottom: 2px solid rgb(46, 85, 142);">找回密码</a>
        </div>
        <ul>
            <li class="con_r_left con" style="display: block;">

                <form  onsubmit="return false;" autocomplete="off">
                    <div class="user">
                        <div>
                            <label  for="mobile" class="fa fa-mobile " ></label>
                            <input type="tel" id="mobile"  placeholder="请输入手机号" name="phone" value="" data-length="11" maxlength="11">
                            <em class="tel_error"></em>
                        </div>
                        <div>
                            <span for="verifyCode" class="fa fa-key"></span>
                            <input id="verifyCode" type="text" name="validate" placeholder="验证码" value="" style=" width:150px;" data-length="4" maxlength="4">　　
                            <!--   这里是验证码的相关路径  -->                            
                            <img id="captchaImg"  title="点击更换验证码" style="cursor: pointer; margin-top:8px;" alt="看不清？点击更换">
                        </div>   
                        <div>
                            <label for="password" class="fa fa-lock"></label>
                            <input id="password" type="password" name="password" placeholder="新的密码" value="" data-length="15" maxlength="15">
                            <em class="rpwd_error"></em>
                        </div>

                        <div>
                            <label for="msgCode" class="fa fa-envelope-open"></label>
                            <input id="msgCode" type="text" name="msgCode" placeholder="短信验证码" value="" data-length="4" maxlength="4" style="width:150px">
                            <em class="msg_error"></em>
                            <span class="btn waves-effect waves-light" id="sendSmsBtn">发送验证码</span>
                        </div>                                          
                                                    
                     </div>
                     <div class="btn_grp">
                         <button id="userForgetBtn"  class="btn waves-effect waves-light">提交</button>
                    
                </form>
            </li>
            
        </ul>
    </div>
</div>
<script>
    var forgetFormPage = {};
</script>
<script src="/static/js/verifycode.js?v=${version}"></script>
<script src="/static/js/forgetForm.js?v=${version}"></script>
