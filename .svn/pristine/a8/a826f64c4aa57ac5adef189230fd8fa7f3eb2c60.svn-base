var loadData = function (page) {
    var url = "/list/video/"+listGroupVideoPage.groupBy+"/"+page;
    loadMainContent(url);
}

var loadOneKeyVideo = function (val) {
    var url = "/list/video/searchlist/"+listGroupVideoPage.groupBy+"/"+val+"/1";//根据clientId搜索第1页
    loadMainContent(url);
}

var initBtnEvent = function () {
    $("#search-button").off('click').click(function () {
        var oneKey= $.trim($("#oneKey").val());
        loadOneKeyVideo(oneKey);
    });
    $("#videoCountSection a").click(function () {
        var key = $(this).attr('data-key');
        loadOneKeyVideo(key);
    });
}

$(function () {
    initBtnEvent();
});