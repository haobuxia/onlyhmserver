var searchClick = function () {
    var keyword = $.trim($("#keyword").val());
    listTagGroupPage.keyword = keyword;
    reloadListData();
}

var reloadListData = function () {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/tag/grouplist/"+page;
    if(listTagGroupPage.keyword != "")
        url += "?keyword="+listTagGroupPage.keyword;
    loadMainContent(url);
}

var addClick = function () {
    var groupName = $.trim($("#keyword").val());
    if(groupName == ""){
        showAlert("请在左侧填写新标签组名称");
        return;
    }

    $.post("/tag/addgroup",{"groupName":groupName},function (resp) {
        if(resp.success){
            $("#keyword").val("");
            reloadListData();
        }else{
            showAlert(resp.message);
        }
    });
}

var deleteTagGroup = function (id,groupName) {
    if(confirm("确定要删除标签组["+groupName+"]吗？该标签对应的资源将丢失该标签.")){
        $.post("/tag/deletegroup/"+id,function (resp) {
            if(resp.success){
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