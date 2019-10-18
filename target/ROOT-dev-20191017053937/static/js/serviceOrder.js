var initTableData = function () {
   var tds = $("td[data-key]");
   $.each(tds,function (idx,td) {
       var _td = $(td);
       var key = _td.attr("data-key");
       var val = serviceOrderPage.orderDetail[key] || serviceOrderPage.orderForm[key];
       _td.text(val);
   });
}

var initVideoImageData =function () {
    var list = serviceOrderPage.resList;
    serviceOrderPage.imageCount=0;
    serviceOrderPage.videoCount=0;
    var imageResContent = $("#imageResContent");
    var videoResContent = $("#videoResContent");
    imageResContent.empty();
    videoResContent.empty();
    $.each(list,function(idx,res){
        if(res.resType==1) {
            serviceOrderPage.videoCount++;
            videoResContent.append("<div class='col s12 m6 l3'><img class='responsive-img' src='"+res.thumbnailUrl+"' data-url='"+res.fullPlayUrl+"'/></div>");
        }else if(res.resType==2) {
            serviceOrderPage.imageCount++;
            imageResContent.append("<div class='col s12 m6 l3'><img class='responsive-img' src='"+res.thumbnailUrl+"' data-url='"+res.fullPlayUrl+"'/></div>");
        }
    });

    $("td[data-key=videoCount]").text(serviceOrderPage.videoCount);
    $("td[data-key=imageCount]").text(serviceOrderPage.imageCount);
    imageResContent.find("img").css("cursor","pointer").attr("title","点击播放").click(function(){
        var url= $(this).attr("data-url");
        playImage(url);
    });
    videoResContent.find("img").css("cursor","pointer").attr("title","点击查看").click(function(){
        var url= $(this).attr("data-url");
        playVideo(url);
    });
}

var playVideo = function (url) {
    $("#playVideo").attr("src",url);
    $('#playVideoModal').modal('show');
}

function closePlayVideo(){
    $("#playVideo")[0].pause();
    $("#playVideo").removeAttr("src");
    $('#playVideoModal').modal('hide');
}

var playImage = function (url) {
    $("#playImage").attr("src",url);
    $('#playVideoModal').modal('show');
}

function closePlayImage(){
    $("#playImage").removeAttr("src");
    $('#playIMageModal').modal('hide');
}

var initData = function () {
    $("td:even").wrapInner("<b></b>");
    initTableData();
    initVideoImageData();
}

var initBtnEvent = function () {
    $("#playVideoCloseBtn").off('click').on('click',closePlayVideo);
    $("#playImageCloseBtn").off('click').on('click',closePlayImage);
}
$(function () {
    initData();
    initBtnEvent();
});