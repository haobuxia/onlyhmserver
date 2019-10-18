function searchUser() {
    var keyword =  $.trim($("#keyword").val());
    newPagePager.currentPage = 1;
    listUserlogPage.keyword = keyword;
    reloadListData();
}

function reloadListData(){
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url;
    if(listUserlogPage.personal)
        url = "/personal/loglist/"+page;
    else
        url = "/userlog/list/"+page;
    if(listUserlogPage.keyword != ''){
        url += "?keyword="+listUserlogPage.keyword;
    }
    loadMainContent(url);
}
