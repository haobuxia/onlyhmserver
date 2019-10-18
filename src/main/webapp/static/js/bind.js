function isPhoneAvailable(mobileVal) {
    var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
    if (!myreg.test(mobileVal)) {
        return false;
    } else {
        return true;
    }
}

var bindFormPage = {};
var initBtnEvent = function () {
    bindFormPage.verifyCode = VerifyCode.create("userBindHelmet","#captchaImg","verifyCode","#sendSmsBtn");

    //生成验证码
    bindFormPage.verifyCode.showCaptcha();

    //发送短信
    $('#sendSmsBtn').off('click').click(function () {
        bindFormPage.verifyCode.sendSms("#userPhone","#sendSmsBtn","/common/bindSms");
    });

    $('#bindButton').off('click').click(doBind);
}

var doBind = function () {
    var userName = $("#userName").val();
    var userPhone = $("#userPhone").val();
    var msgCode = $("#msgCode").val();
    if(userName == "" || userPhone == "" || msgCode == ""){
        alert('姓名、手机号、短信验证码都不能为空');
        return false;
    }
    if(!isPhoneAvailable(userPhone)){
        alert('手机号无效');
        return false;
    }
    var helmetId = $("#helmetId").val();
    $.post("/helmetqr/binduser",{
        "helmetId":helmetId,
        "userName":userName,
        "userPhone":userPhone,
        "msgCode":msgCode
    },function(resp){
        if(resp.success){
            showAlert('设置成功');
            location.href="/helmetqr/code/"+helmetId;
        }else{
            showAlert(resp.message);
        }
    },"json");
    return false;
}


$(function () {
    initBtnEvent();
});
