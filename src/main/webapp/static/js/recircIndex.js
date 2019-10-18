var initBtnEvent = function () {
    $("#search-button").off('click').click(reloadListData);
}

var initUiData = function () {
    var todayTime = new Date().getTime();
    var dayBefore = new Date();
    var tomorrow = new Date();
    dayBefore.setTime(todayTime - 7 * 24 * 60 * 60 * 1000);
    tomorrow.setTime(todayTime + 1 * 24 * 60 * 60 * 1000);

    $("#recirc_date1").val(dayBefore.Format("yyyy-MM-dd"));
    $("#recirc_date2").val(tomorrow.Format("yyyy-MM-dd"));
    reloadListData();
}

function reloadListData() {
    loadData(1);
}

var loadData = function (page,type) {
    var machineCode = $.trim($("#recirc_machineCode").val());
    var date1 = $("#recirc_date1").val();
    var date2 = $("#recirc_date2").val();
    if("video"==type || type == null){
        var url1 = "/recirculate/videolist/" + page + "?machineCode=" + machineCode + "&date1=" + date1 + "&date2=" + date2;
        loadContent("#videoContentSection", url1,function (html) {
            var eleCount = $(html).find("div.row>div.col").length;
            var section = $("#videoContentSection");
            if(eleCount > 0 ){
                section.before('<div class="divider"></div>');
            }else{
                section.remove();
            }
        });
    }
    if("image"==type || type == null){
        var url2 = "/recirculate/imagelist/" + page + "?machineCode=" + machineCode + "&date1=" + date1 + "&date2=" + date2;
        loadContent("#imageContentSection", url2,function (html) {
            var eleCount = $(html).find("div.row>div.col").length;
            var section = $("#imageContentSection");
            if(eleCount > 0 ){
                section.before('<div class="divider"></div>');
            }else{
                section.remove();
            }
        });
    }
}

var checkNoData = function(html){


}

$(function () {
    initBtnEvent();
    initUiData();
})