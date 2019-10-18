function searchUser() {
    var keyword =  $.trim($("#keyword").val());
    newPagePager.currentPage = 1;
    listHelmetlogPage.keyword = keyword;
    reloadListData();
}

function reloadListData(){
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/helmetlog/list/"+page;
    if(listHelmetlogPage.keyword != ''){
        url += "?keyword="+listHelmetlogPage.keyword;
    }
    loadMainContent(url);
}
