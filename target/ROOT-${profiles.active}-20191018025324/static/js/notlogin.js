var loadRegisterForm = function () {
    $("main").load("/common/register",function(html){
        $("#nav-pc li").removeClass("active").first().addClass("active");
    });
};
var loadLoginForm = function () {
    $("main").load("/common/login",function(html){
        $("#nav-pc li").removeClass("active").last().addClass("active");
    });
};
var loadForgetForm = function () {
    $("main").load("/common/forget",function(html){
        $("#nav-pc li").removeClass("active");
    });
};


$(function () {
    $("#nav-mobile").html($("#nav-pc").html());
    // $(".button-collapse").sideNav();
})