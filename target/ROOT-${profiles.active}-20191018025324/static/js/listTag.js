var searchClick = function () {
    var keyword = $.trim($("#keyword").val());
    listTagPage.keyword = keyword;
    reloadListData();
}

var reloadListData = function () {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/tag/list/"+page;
    if(listTagPage.keyword != "")
        url += "?keyword="+listTagPage.keyword;
    loadMainContent(url);
}

var deleteTag = function (id,tagName) {
    if(confirm("确定要删除标签["+tagName+"]吗？该标签对应的资源将丢失该标签.")){
        $.post("/tag/delete/"+id,function (resp) {
            if(resp.success){
                reloadListData();
            }else{
                showAlert(resp.message);
            }
        });
    }
}

var initBtnEvent = function (){
    // $('select').material_select();
    $("#search-button").off('click').click(searchClick);
    $("#add-button").off('click').click(function () {
        $("#tagModalTitle").text("添加新的标签");
        $("#tagedit_name").val("");
        $('#tagModal').modal('show');
        return false;
    });

    $("#tagdit-button").off('click').click(function () {
        var tagName = $.trim($("#tagedit_name").val());
        var groupId = $("#tagedit_group").val();
        if(tagName == ""){
            showAlert("标签名称未填写");
            return;
        }
        $.post("/tag/add",{"tagName":tagName,"groupId":groupId},function (resp) {
            if(resp.success){
                $('#tagModal').modal('hide');
                reloadListData();
            }else{
                showAlert(resp.message);
            }
        });
    });
}

$(function () {
    initBtnEvent();
});