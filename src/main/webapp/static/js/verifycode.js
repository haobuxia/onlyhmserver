function isPhoneAvailable(mobileVal) {
    var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
    if (!myreg.test(mobileVal)) {
        return false;
    } else {
        return true;
    }
}

var VerifyCode = {
    what:'',
    imgSelector:'',
    inputId:'',
    sendSmsBtnSelector:'',
    token:'',
    verifySuccess:false,
};
VerifyCode.getToken = function () {
    return this.token;
}
VerifyCode.getOperate = function () {
    return this.what;
}
VerifyCode.create = function (what,imgSelector,inputId,sendSmsBtnSelector) {
    this.what = what;
    this.imgSelector = imgSelector;
    this.inputId = inputId;
    this.sendSmsBtnSelector = sendSmsBtnSelector;
    return this;
}

VerifyCode.gencode = function () {
    var that = this;
    $.get("/common/captchaGen/"+that.what,function (resp) {
        that.verifySuccess = false;
        if(that.sendSmsBtnSelector){
            $(that.sendSmsBtnSelector).addClass("disabled");
        }
        that.refreshImg(resp.data);
    });
}
//隐藏图形验证码
VerifyCode.hideCaptcha = function () {
    $(this.imgSelector).addClass("hide");
    $("#"+this.inputId).addClass("hide");
}
//图形验证码不可再刷新
VerifyCode.disableCaptcha = function () {
    $(this.imgSelector).off('click').attr("title","验证码已校验通过");
    $("#"+this.inputId).addClass("disabled").prop("readonly",true).prop("disabled",true);
}
//显示验证码功能
VerifyCode.showCaptcha = function () {
    $(this.imgSelector).removeClass("hide").attr("title","点击刷新验证码");
    $("#"+this.inputId).removeClass("hide disabled").prop("readonly",false).prop("disabled",false).attr("placeholder","验证码");
    var _verifyCode = this;
    $(this.imgSelector).off('click').click(function () {
        _verifyCode.gencode();
    });
    $("#"+this.inputId).off('change').change(function () {
        var val = $.trim($(this).val());
        if(val.length >= 4){
            _verifyCode.verify(val.substring(0,4));
        }
    });
    this.gencode();
}

VerifyCode.verify = function (inputCode) {
    if(inputCode == "" || $.trim(inputCode).length == 0){
        return;
    }
    var that = this;
    $.get("/common/captchaVerify/"+that.what+"/"+that.token,{"code":inputCode},function (resp) {
        if(resp.success){
            that.verifySuccess = true;
            if(that.sendSmsBtnSelector){
                $(that.sendSmsBtnSelector).removeClass("disabled");
            }
            console.debug('验证码正确');
            that.disableCaptcha();
        }else {
            that.verifySuccess = false;
            if(that.sendSmsBtnSelector){
                $(that.sendSmsBtnSelector).addClass("disabled");
            }
            console.debug('验证码错误');
            that.refreshImg(resp.data);
            $("label[for="+that.inputId+"]").attr("data-error","验证码错误").addClass("active");
        }
    });
}
VerifyCode.refreshImg = function (token) {
    this.token = token;
    $(this.imgSelector).attr("src","/common/captchaimg/"+this.what+"/"+this.token);
    $("#"+this.inputId).val("").focus();
}
VerifyCode.sendSms = function (mobileInputSelector,sendSmsBtnSelector,sendSmsUrl) {
    //先校验图形码是否验证通过了
    if(!this.verifySuccess){
        if(that.sendSmsBtnSelector){
            $(that.sendSmsBtnSelector).addClass("disabled");
        }
        showAlert('请先输入正确的图形验证码');
        return;
    }

    var mobile = $.trim($(mobileInputSelector).val());
    if(!isPhoneAvailable(mobile)){
        showAlert('请输入有效的手机号');
        $(mobileInputSelector).focus();
        return;
    }
    var sendSmsBtn = $(sendSmsBtnSelector);
    sendSmsBtn.addClass("disabled");
    var that = this;
    $.post(sendSmsUrl,{
        operate:that.what,
        token:that.token,
        mobile:mobile
    },function (resp) {
        if(resp.success){
            that.sendSmsCounter = 60;
            that.sendSmsInterval = window.setInterval(function(){
                that.sendSmsCounter --;
                sendSmsBtn.text("发送中("+that.sendSmsCounter+"s)");
                if(that.sendSmsCounter == 0){
                    window.clearInterval(that.sendSmsInterval);
                    sendSmsBtn.removeClass("disabled");
                    sendSmsBtn.text("发送短信码");
                }
            },1000);
        }else{
            sendSmsBtn.removeClass("disabled");
            //验证码重新生成
            that.showCaptcha();
            showAlert("短信发送失败."+resp.message);
        }
    });
}