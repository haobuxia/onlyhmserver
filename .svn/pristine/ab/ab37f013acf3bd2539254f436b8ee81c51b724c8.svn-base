
var cls = function (e) {
     $('#'+e).addClass('hidden');
}
var chgPwd = function () {
    $('#chgMobileModal,#chgInfoModal').addClass('hidden');
    $('#chgPwdModal').removeClass('hidden');
}
var chgMobile = function () {
    $('#chgPwdModal,#chgInfoModal').addClass('hidden');
    $('#chgMobileModal').removeClass('hidden');
    personIndexPage.verifyCode.showCaptcha();    
}
var chgInfo = function () {
    $('#chgPwdModal,#chgMobileModal').addClass('hidden');
    $.post("/personal/info",{},function (resp) {
        if(resp.success){
            $('#chgInfoModal').removeClass('hidden');
        }else{
            showAlert(resp.message);
        }
    });
}

var reloadData = function () {
    loadMainContent("/personal/index");
}

var initBtnEvent = function () {
    // $('input').characterCounter();

    personIndexPage.verifyCode = VerifyCode.create("changeMobile","#captchaImg","verifyCode","#sendSmsBtn");

    //修改密码提交
    $("#userpwd-button").off('click').click(function () {
        var oldpwd = $.trim($("#userpwd_password1").val());
        var newpwd = $.trim($("#userpwd_password2").val());
        if(oldpwd == "" || newpwd == "") return;
        $.post("/personal/updatePwd",{"oldpwd":oldpwd,"pwd":newpwd},function (resp) {
            if(resp.success){
                $("#userpwd_password1").val("");
                $("#userpwd_password2").val("");
                $('#chgPwdModal').addClass('hidden');
                showAlert("修改成功");
            }else{
                showAlert(resp.message);
            }
        },"json");
    });

    //修改手机号提交
    $("#usermobile-button").off('click').click(function () {
        var mobile = $.trim($("#usermobile_mobile").val());
        if(mobile == "") return;
        var smscode = $.trim($("#usermobile_smscode").val());
        if(smscode == "") return;
        var password = $.trim($("#usermobile_password").val());
        if(password == "") return;

        if(isPhoneAvailable(mobile)){
            showAlert("手机号无效");
            return;
        }

        $.post("/personal/changeMobile",{
            "mobile":mobile,
            "code":smscode,
            "password":password
        },function (resp) {
            if(resp.success){
                $("#verifyCode").val("");
                $("#usermobile_mobile").val("");
                $("#usermobile_smscode").val("");
                $("#usermobile_password").val("");
                $('#chgMobileModal').addClass("hidden");
                $("#userMobile").text(mobile);
                // alert("修改成功");
            }else{
                personIndexPage.verifyCode.showCaptcha();
                showAlert(resp.message);
            }
        },"json");
    });

    //修改个人信息提交
   $("#useredit-button").off('click').click(function () {
       var info = {};
       info.name = $.trim($("#useredit_name").val());
       // info.userSex = $.trim($("#useredit_userSex").val());
       // info.company = $.trim($("#useredit_company").val());
       if(info.name == ""){
           $("#useredit_name").addClass("invalid");
           $("label[for=useredit_name]").attr("data-error","姓名不能为空").addClass("active");
           return;
       }

       $.post("/personal/updateInfo",info,function (resp) {
           if(resp.success){
               $('#chgInfoModal').addClass("hidden");
               // alert("修改成功");
               reloadData();
           }else{
               showAlert(resp.message);
           }
       },"json");
   });

   //发送短信
   $("#sendSmsBtn").off('click').click(function () {
       personIndexPage.verifyCode.sendSms("#usermobile_mobile","#sendSmsBtn","/personal/changeMobileSms");
   });
}


$(function () {
    initBtnEvent();
});