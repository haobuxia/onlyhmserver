var searchClick = function () {
    var clientId = $("#keyword").val();
    listApkUpdatePage.imei = clientId;
    reloadListData();
}

var reloadListData = function () {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/apk/update/list/"+page;
    if(listApkUpdatePage.clientId != "")
        url += "?imei="+listApkUpdatePage.imei;
    loadMainContent(url);
}

var addClick = function () {
    loadMainContent("/apk/update/set");
}

var delUpdate = function (id) {
    if(confirm("确定要删除这个更新设置吗？")){
        $.post("/apk/update/delete/"+id,function (resp) {
            if(resp.success){
                showAlert('删除成功');
                reloadListData();
            }else{
                showAlert(resp.message);
            }
        });
    }
}

var initBtnEvent = function () {
    $("#search-button").off('click').click(searchClick);
    $("#add-button").off('click').click(addClick);
}

$(function () {
    initBtnEvent();
});