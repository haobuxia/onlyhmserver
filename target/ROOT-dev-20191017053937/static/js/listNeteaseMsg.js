var reloadListData = function () {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/neteasevideo/faillist/"+page;
    loadMainContent(url);
}

var ignoreFailMsg = function (id) {
    if(confirm("确定忽略掉这个失败数据吗？")){
        $.post("/neteasevideo/ignore/"+id,function (resp) {
            if(resp.success){
                reloadListData();
            }else{
                showAlert(resp.message);
            }
        },"json");
    }
}

var initBtnEvent = function () {
    $("#retry-button").off('click').click(function () {
        $.post("/neteasevideo/failedRetry",{},function(resp){
            if(resp.success){
                showAlert("成功添加"+resp.data+"项数据进入重试队列");
                loadData(1);
            }else{
                showAlert(resp.message);
            }
        },"json")
    });
}

$(function () {
    initBtnEvent();
});