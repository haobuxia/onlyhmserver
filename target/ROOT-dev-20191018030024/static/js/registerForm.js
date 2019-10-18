function isPhoneAvailable(mobileVal) {
    var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
    if (!myreg.test(mobileVal)) {
        return false;
    } else {
        return true;
    }
}

var initBtnEvent = function () {
    registerFormPage.verifyCode = VerifyCode.create("userReg","#captchaImg","verifyCode","#sendSmsBtn");

    // $('input').characterCounter();

    //生成验证码
    registerFormPage.verifyCode.showCaptcha();

    //发送短信
    $('#sendSmsBtn').off('click').click(function () {
        registerFormPage.verifyCode.sendSms("#mobile","#sendSmsBtn","/common/registerSms");
    });

    //注册提交
    $('#userRegisterBtn').off('click').click(function () {
        var mobile = $.trim($("#mobile").val());
        var code = $.trim($("#msgCode").val());
        var password = $.trim($("#password").val());
        if(!isPhoneAvailable(mobile)){
            $("#mobile").addClass("invalid");
            $(".tel_error").text("号码无效").show();
            $("label[for=mobile]").attr("data-error","号码无效").addClass("active");
            return false;
        }
        $(".tel_error").hide();

        if(password.length == 0 ){
            $("#password").addClass("invalid");
            $(".rpwd_error").text("密码无效").show();
            $("label[for=password]").attr("data-error","不能为空").addClass("active");
            return ;
        }
        $(".rpwd_error").hide();
        
        if(code.length != 4 && !$.isNumeric(code)){
            $("#msgCode").addClass("invalid");
            $(".msg_error").text("短信码无效").show();
            $("label[for=msgCode]").attr("data-error","短信码无效").addClass("active");
            return false;
        }
        $(".msg_error").hide();

        

        $.post("/common/register",{
            "mobile":mobile,
            "code":code,
            "password":password
        },function (resp) {
            if(resp.success){
                alert("注册成功");
                location.href="/console/index";
            }else{
                registerFormPage.verifyCode.showCaptcha();
                showAlert(resp.message);
            }
        },"json");

        return false;
    });
}

$(function () {
    initBtnEvent();
});
