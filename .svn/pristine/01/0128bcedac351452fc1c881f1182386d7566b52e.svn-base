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
            <%--<a href="javascript:;" class="ileft" style="color: rgb(153, 153, 153); border-bottom: 2px solid rgb(222, 222, 222);">注册</a>--%> <a href="javascript:;" class="iright" style="color: rgb(51, 51, 51); border-bottom: 2px solid rgb(46, 85, 142);">登录</a>
        </div>
        <ul>
            <%--<li class="con_r_left con" style="display: none;">--%>

                <%--<form  onsubmit="return false;" autocomplete="off">--%>
                    <%--<div class="user">--%>
                        <%--<div>--%>
                            <%--<label  for="mobile" class="fa fa-mobile" ></label>--%>
                            <%--<input type="tel" id="mobile"  placeholder="请输入手机号" name="phone" value="" data-length="11" maxlength="11">--%>
                            <%--<em class="tel_error"></em>--%>
                        <%--</div>--%>
                        <%--<div>--%>
                            <%--<span for="verifyCode" class="fa fa-key"></span>--%>
                            <%--<input id="verifyCode" type="text" name="validate" placeholder="验证码" value="" style=" width:150px;" data-length="4" maxlength="4">　　--%>
                            <%--<!--   这里是验证码的相关路径  -->                            --%>
                            <%--<img id="captchaImg"  title="点击更换验证码" style="cursor: pointer; margin-top:8px;" alt="看不清？点击更换">--%>
                        <%--</div>   --%>
                        <%--<div>--%>
                            <%--<label for="password" class="fa fa-lock"></label>--%>
                            <%--<input id="password" type="password" name="password" placeholder="账号密码" value="" data-length="15" maxlength="15">--%>
                            <%--<em class="rpwd_error"></em>--%>
                        <%--</div>--%>

                        <%--<div>--%>
                            <%--<label for="msgCode" class="fa fa-envelope-open "></label>--%>
                            <%--<input id="msgCode" type="text" name="msgCode" placeholder="短信验证码" value="" data-length="4" maxlength="4" style="width:150px">--%>
                            <%--<em class="msg_error"></em>--%>
                            <%--<span class="btn waves-effect waves-light" id="sendSmsBtn">发送验证码</span>--%>
                        <%--</div>                                          --%>
                                                    <%----%>
                    <%--</div>--%>
                    <%--<div class="btn_grp">--%>
                        <%--<button id="userRegisterBtn"  class="btn waves-effect waves-light">注册</button>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</li>--%>
            
            
            <li class="con_r_right con" style="display: block;">
                <form  onsubmit="return false;" autocomplete="off">
                    <div class="user">
                        <div>
                            <label  for="loginUserId" class="fa fa-user-o" data-error="用户名不能为空" data-success=""></label>
                            <input type="text" id="loginUserId"  placeholder="请输入登录账号" value="">
                            <em class="user_error"></em>
                        </div>

                        <div>
                            <label for="loginUserPwd" class="fa fa-lock"  data-error="密码不能为空" data-success=""></label>
                            <input id="loginUserPwd" type="password"  placeholder="请输入密码"  data-length="15"  maxlength="15" value="">
                            <em class="pwd_error"></em>
                        </div>
                        
                        <!--  <div>
                            <span class="yzmz-icon"></span>
                            <input id="vdcode" type="text" name="validate" placeholder="　验证码" value="" style=" width:150px;">　 -->　
                            <!--   这里是验证码的相关路径  -->                            
                           <!--  <img style="cursor: pointer; margin-top:8px;" alt="看不清？点击更换" src="img/yanzhengma.jpg">
                        </div>    -->                   
                                                    
                     </div>
                     <div class="btn_grp">
                         <button id="userLoginBtn"  class="btn waves-effect waves-light">登 录</button><!--<button  id="tyUserLoginBtn" class="btn waves-effect waves-light">天远账号登录</button>-->
                     </div>
                    <div id="userForgetBtn" class="blue-text text-darken-2">
                        <span>忘记密码?</span>
                    </div>
                    
                </form>
            </li>
        </ul>
    </div>
</div>

    <script>
        var loginFormPage = {};
        var registerFormPage = {};
    </script>
    <script src="/static/js/loginForm.js?v=${version}"></script>
    <script src="/static/js/verifycode.js?v=${version}"></script>
    <script src="/static/js/registerForm.js?v=${version}"></script>
