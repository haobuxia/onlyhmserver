<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MAIN CONTENT -->
<div>
    <div class="main-content">
        <div class="container-fluid" id="infoPerson">
            
           <h3 class="page-title">个人中心</h3>
           <p class="subtitle">在此查看自己的个人信息、操作日志、进行资源管理和安全设置</p>  

            <div class="panel panel-headline" id="personOperateDiv">
                <div class="panel-body">
                    <c:if test="${!userInfo.tianyuan}">
                        <p>姓名：${user.name}</p>
                        <p>手机：${user.mobile}</p>
                        <p>账号：${userInfo.username}</p>
                        <p>身份：
                            <c:if test="${userInfo.admin}"><span class="label label-danger">管理员</span></c:if>
                            <c:if test="${userInfo.demo}"><span class="label label-warning">演示员</span></c:if>
                            <c:if test="${userInfo.sales}"><span class="label label-success">销售员</span></c:if>
                            <c:if test="${userInfo.customer}"><span class="label label-primary">客户</span></c:if>
                            <c:if test="${userInfo.driver}"><span class="label label-info">机手</span></c:if>
                        </p>
                    </c:if>
                    
                    <%--<c:if test="${userInfo.tianyuan}">
                    <p>账号ID：${tianYuanUser.accountId}</p>
                    <p>人员ID：${tianYuanUser.oprtId}</p>
                    <p>姓名：${tianYuanUser.oprtName}</p>
                    <p>身份：<span class="label label-default">天远服务人员</span></p>
                    </c:if>--%>
                </div>
                <div class="panel-body">
                    <c:if test="${!userInfo.tianyuan}">
                        <a href="javascript:chgPwd()">修改密码</a>
                        <a href="javascript:chgInfo()">修改资料</a>
                        <a href="javascript:chgMobile()">修改手机</a>
                    </c:if>
                </div>

            </div>
        </div>

    <!-- 修改密码 start  -->
        <div class="container-fluid hidden" id="chgPwdModal">
            
            <h3 class="page-title">个人中心-修改密码</h3>
            <div class="panel panel-headline col-md-6">
                <div class="panel-body" id="pwdForm">
                    <p class="demo-button">
                        <input type="password" id="userpwd_password1" class="form-control" data-length="15" maxlength="15" placeholder="请输入旧密码">
                    </p>
                    <p class="demo-button">
                        <input type="password" id="userpwd_password2" class="form-control" data-length="15" maxlength="15" placeholder="请输入新密码">
                    </p>
                    <p class="demo-button">
                        <input type="password" id="userpwd_password3" class="form-control" data-length="15" maxlength="15" placeholder="请再次输入新密码">
                    </p>
                    <p class="demo-button">
                        <a href="javascript:void(0)" class="btn btn-primary" id="userpwd-button">确认修改</a>
                        <a href="javascript:cls('chgPwdModal')" class="btn btn-default cls-cancel">取消</a>
                    </p>
                </div>
            </div>

       </div>
    <!-- 修改密码 end    -->

    <!-- 修改手机 start  -->
        <div class="container-fluid hidden" id="chgMobileModal">
            
            <h3 class="page-title">个人中心-修改手机</h3>
            <div class="panel panel-headline col-md-6">
                <div class="panel-body" id="mobileForm">
                    
                    <p class="input-group">
                       <input type="text" id="verifyCode" placeholder="请输入验证码" data-length="4" maxlength="4" class="form-control" value="" style="height: 40px;"> 
                       <span class="input-group-addon" style="padding: 0;border: 0;"><img style="width:120px;height:40px;display: inline-block;" src="" id="captchaImg" title="获取验证码" alt="获取验证码" /></span>
                    </p>
                    <p class="demo-button">
                        <input type="tel" id="usermobile_mobile" class="form-control"  maxlength="15" placeholder="请输入新手机号">
                    </p>
                    <p class="input-group">
                       <input type="text" id="usermobile_smscode" placeholder="请输入短信码" class="form-control" value=""> <a class="input-group-addon btn btn-primary" id="sendSmsBtn" style="color: #fff;">获取短信码</a>
                    </p>
                    <p class="demo-button">
                       <input type="password" id="usermobile_password" placeholder="请输入当前账号密码" class="form-control" value=""  data-length="15" maxlength="15"> 
                    </p>
                    <p class="demo-button">
                        <a href="javascript:void(0)" class="btn btn-primary" id="usermobile-button">确认修改</a>
                        <a href="javascript:cls('chgMobileModal')" class="btn btn-default cls-cancel">取消</a>
                    </p>
                </div>                
            </div>     

        </div>
    <!-- 修改手机 end    -->

    <!-- 修改信息 start  -->
        <div class="container-fluid hidden" id="chgInfoModal">
            
            <h3 class="page-title">个人中心-修改信息</h3>
            <div class="panel panel-headline col-md-6" id="infoForm">
                <div class="panel-body">
                    <p class="input-group">
                        <span class="input-group-addon">真实姓名</span>
                        <input type="text" id="useredit_name" class="form-control" placeholder="真实姓名">
                    </p>
                    <p class="input-group">
                        <span class="input-group-addon">性别</span>
                        <select class="form-control">
                            <option value="2">请选择性别</option>
                            <option value="1">男</option>
                            <option value="0">女</option>                            
                        </select>
                    </p>
                    <!-- <p class="input-group">
                        <span class="input-group-addon">公司名称</span>
                        <input type="text" id="useredit_company" class="form-control"  placeholder="公司名称">
                    </p> -->
                    <p class="demo-button">
                        <a href="javascript:void(0)" class="btn btn-primary" id="useredit-button">确认修改</a>
                        <a href="javascript:cls('chgInfoModal')" class="btn btn-default cls-cancel">取消</a>
                    </p>
                </div>
            </div>

       </div>
    <!-- 修改信息 end    -->



    </div>
</div>



<script>
    var personIndexPage = {};
</script>
<script src="/static/js/verifycode.js?v=${version}"></script>
<script src="/static/js/personIndex.js?v=${version}"></script>