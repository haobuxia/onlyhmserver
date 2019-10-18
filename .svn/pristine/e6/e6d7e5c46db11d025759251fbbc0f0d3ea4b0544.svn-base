var searchClick = function () {
    var keyword = $("#keyword").val();
    listApkFilePage.keyword = keyword;
    reloadListData();
}

var reloadListData = function () {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/apk/file/list/"+page;
    if(listApkFilePage.keyword != "")
        url += "?keyword="+listApkFilePage.keyword;
    loadMainContent(url);
}

var addClick = function () {
    loadMainContent("/apk/file/upload");
}

var deleteFile = function (id) {
    if(confirm("确定要删除这个文件吗？")){
        $.post("/apk/file/delete/"+id,function (resp) {
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