var initBtnEvent = function () {
    $("#search-button").off('click').click(reloadListData);
}

var initUiData = function () {
    if (queryIndexPage.isTianYuanUser) {//天远用户只能查自己的工单
        $("#alipay_oprtId").prop("disabled", true).prop("readonly", true);
    } else {//能查所有人的
        $("#alipay_oprtId").prop("disabled", false).prop("readonly", false);
    }

    var todayTime = new Date().getTime();
    var dayBefore10 = new Date();
    var tomorrow = new Date();
    dayBefore10.setTime(todayTime - 90 * 24 * 60 * 60 * 1000);
    tomorrow.setTime(todayTime + 4 * 24 * 60 * 60 * 1000);

    $("#alipay_date1").val(dayBefore10.Format("yyyy-MM-dd"));
    $("#alipay_date2").val(tomorrow.Format("yyyy-MM-dd"));

    reloadListData();
}

function reloadListData() {
    loadData(1);
}

var loadData = function (page) {
    var oprtId = $.trim($("#alipay_oprtId").val());
    var date1 = $("#alipay_date1").val();
    var date2 = $("#alipay_date2").val();
    var url = "/alipayquery/queryList/" + page + "?&date1=" + date1 + "&date2=" + date2 + "&oprtId=" + decodeURIComponent(oprtId);
    loadContent("#contentSection", url);
}

var transTyTradeType = function (txt) {
    if ("partBuyOrder" == txt) return "零配件采购";
    if ("partSellOrder" == txt) return "零配件销售";
    return txt;
}

$(function () {
    initBtnEvent();
    initUiData();
})