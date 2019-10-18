/**
 * 操作日志页面按钮的功能
 * Created by wenxinyan on 2018/8/16.
 */
function searchUser() {
    var keyword =  $.trim($("#keyword").val());
    var logflow =  $.trim($("#logflow").val());
    newPagePager.currentPage = 1;
    helmet_log.keyword = keyword;
    helmet_log.logflow = logflow;
    reloadListData();
}

function reloadListData(){
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var deviceType = $("#deviceType").val();
    var logType = $("#logType").val();
    var logNature = $("#logNature").val();
    var time1 = formatToTime($("#time1").val()).toString();
    var time2 = formatToTime($("#time2").val()).toString();
    var url = "/operalog/list/"+page + "?deviceType=" + deviceType + "&logType=" + logType + "&logNature=" + logNature + "&time1=" + time1 + "&time2=" + time2;
    if(helmet_log.keyword != ''){
        url += "&keyword="+helmet_log.keyword;
    }
    if(helmet_log.logflow != ''){
        url += "&logflow="+helmet_log.logflow;
    }
    loadMainContent(url);
}

var initBtnEvent = function () {

    $("#search-button").off('click').click(function () {
        searchUser();
        return false;
    });

}

var formatToTime = function (yyyyMMddHHmmss) {
    yyyyMMddHHmmss = yyyyMMddHHmmss.replaceAll("-","").replaceAll(":","").replaceAll(" ","");
    var d = new Date();
    d.setYear(yyyyMMddHHmmss.substring(0,4)*1);
    d.setMonth(yyyyMMddHHmmss.substring(4,6)*1-1);
    d.setDate(yyyyMMddHHmmss.substring(6,8)*1);
    d.setHours(yyyyMMddHHmmss.substring(8,10)*1);
    d.setMinutes(yyyyMMddHHmmss.substring(10,12)*1);
    d.setSeconds(yyyyMMddHHmmss.substring(12,14)*1);
    d.setMilliseconds(0);
    return d.getTime();
}

var initUiData = function () {
    var todayTime = new Date().getTime();
    // var time1 = new Date();
    var time2 = new Date();
    // time1.setTime(todayTime - 1 * 24 * 60 * 60 * 1000);
    time2.setTime(todayTime + 1 * 60 * 60 * 1000);

    // $("#time1").val(time1.Format("yyyy-MM-dd hh:mm:ss"));
    $("#time1").val("2018-01-01 00:00:00");
    $("#time2").val(time2.Format("yyyy-MM-dd hh:mm:ss"));
}

function devChange() {
    if ($("#deviceType").val() != '')
    {
        $("#keyword").attr("readOnly",false);
    }
    else
    {
        $("#keyword").val("");
        $("#keyword").attr("readOnly",true);
    }
}

function natChange() {
    if ($("#logNature").val() == "0") {
        $("#logflow").attr("readOnly",false);
    }
    else {
        $("#logflow").val("");
        $("#logflow").attr("readOnly",true);
    }
}

$(function () {
    initBtnEvent();
    initUiData()
    devChange()
});