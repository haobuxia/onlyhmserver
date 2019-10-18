var tianYiLogin = function () {
    doLogin("1");
}
var tianYuanLogin = function () {
    doLogin("2");
}
var doLogin = function (loginType) {
    var userId = $.trim($("#loginUserId").val());
    var userPwd = $("#loginUserPwd").val();
    if(userId == ""){
        $(".user_error").text("请填写用户名").show();
        return false;
    }
    $(".user_error").hide();
    if(userPwd == ""){
        $(".pwd_error").text("请填写密码").show();
        return false;
    }
    $(".pwd_error").hide();

    $("#userLoginBtn").addClass("disabled");
    $("#tyUserLoginBtn").addClass("disabled");
    $.post("/common/login",{
        username:userId,
        password:userPwd,
        loginType:loginType
    },function (resp) {
        $("#userLoginBtn").removeClass("disabled");
        $("#tyUserLoginBtn").removeClass("disabled");
        var code =resp.code;
        if(code == null){
            if(resp.success){
                location.href="/console/index";
            }else{
                alert("登录失败."+resp.message);
            }
        }else{
            if(resp.code == "200"){
                location.href="/console/index";
            }else if(resp.code== "600"){
                $("#loginUserId").addClass("invalid");
                $("label[for=loginUserId]").attr("data-error",resp.msg).addClass("active");
                $(".user_error").text(resp.msg).show();
            }else if(resp.code== "601"){
                $("#loginUserPwd").addClass("invalid");
                $("label[for=loginUserPwd]").attr("data-error",resp.msg).addClass("active");
                 $(".pwd_error").text(resp.msg).show();
            }else{
                alert("登录失败."+resp.msg);
            }
        }
    },'json');
    return false;
}

var initBtnEvent = function () {
    //登录
    $("#userLoginBtn").off('click').on('click',tianYiLogin);
    $("#tyUserLoginBtn").off('click').on('click',tianYuanLogin);

    //忘记密码
    $("#userForgetBtn").off('click').click(function () {
        loadForgetForm();
    });

    $.ajaxSetup({
        error:function (xMLHttpRequest, textStatus, errorThrown) {
            alert("发生错误."+textStatus+" "+errorThrown);
        }
    });
}

$(function () {
    initBtnEvent();
    // tabs切换
     $(".content .con_right .ileft").click(function (e) {
        $(this).css({ "color": "#333333", "border-bottom": "2px solid #2e558e" });
        $(".content .con_right .iright").css({ "color": "#999999", "border-bottom": "2px solid #dedede" });
        $(".content .con_right ul .con_r_left").css("display", "block");
        $(".content .con_right ul .con_r_right").css("display", "none");
    });
    $(".content .con_right .iright").click(function (e) {
        $(this).css({ "color": "#333333", "border-bottom": "2px solid #2e558e" });
        $(".content .con_right .ileft").css({ "color": "#999999", "border-bottom": "2px solid #dedede" });
        $(".content .con_right ul .con_r_right").css("display", "block");
        $(".content .con_right ul .con_r_left").css("display", "none");
    });
});