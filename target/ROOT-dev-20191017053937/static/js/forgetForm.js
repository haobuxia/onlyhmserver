function isPhoneAvailable(mobileVal) {
    var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
    if (!myreg.test(mobileVal)) {
        return false;
    } else {
        return true;
    }
}

var initBtnEvent = function () {
    forgetFormPage.verifyCode = VerifyCode.create("userForget","#captchaImg","verifyCode","#sendSmsBtn");

    // $('input').characterCounter();

    //生成验证码
    forgetFormPage.verifyCode.showCaptcha();

    //发送短信
    $('#sendSmsBtn').off('click').click(function () {
        forgetFormPage.verifyCode.sendSms("#mobile","#sendSmsBtn","/common/forgetSms");
    });

    //注册提交
    $('#userForgetBtn').off('click').click(function () {
        var mobile = $.trim($("#mobile").val());
        var code = $.trim($("#msgCode").val());
        var password = $.trim($("#password").val());
        if(!isPhoneAvailable(mobile)){
            $("#mobile").addClass("invalid");
            $("label[for=mobile]").attr("data-error","手机号码无效").addClass("active");
            return ;
        }
        if(code.length != 4 && !$.isNumeric(code)){
            $("#msgCode").addClass("invalid");
            $("label[for=msgCode]").attr("data-error","短信验证码无效").addClass("active");
            return ;
        }
        if(password.length == 0 ){
            $("#password").addClass("invalid");
            $("label[for=password]").attr("data-error","密码不能为空").addClass("active");
            return ;
        }
        $.post("/common/forget",{
            "mobile":mobile,
            "code":code,
            "password":password
        },function (resp) {
            if(resp.success){
                showAlert("重置密码成功");
                location.href="/console/index";
            }else{
                forgetFormPage.verifyCode.showCaptcha();
                showAlert(resp.message);
            }
        },"json");

        return false;
    });

    $("#userRegisterBtn").off('click').click(function () {
        loadRegisterForm();
    });
}

$(function () {
    initBtnEvent();
});
