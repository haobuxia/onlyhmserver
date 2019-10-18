
function reloadListData() {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {

    var url = "/device/tybox/list";

    ajaxPost(url,
        {
            page: page
        },
        function (resp) {
            if (resp.success) {
                var list = resp.data.list;
                newPagePager.page = resp.data.page;
                newPagePager.pageCount = resp.data.pageCount;
                newPagePager.total = resp.data.total;
                var _inner = '';
                var deviceListContent = $("#deviceListContent");
                for (var i = 0; i < list.length; i++) {
                    var _item = list[i];
                    if (_item.createTime == null) {
                        _item.createTime = '';
                    }
                    _inner += "<tr>" +
                        '<td>' + (i + 1) + '</td>' +
                        '<td  style="overflow: hidden;text-overflow:ellipsis;white-space:nowrap;">' + _item.dataPart + '</td>'
                        + '<td><a class="label label-info" onclick="editOpen(\'' + _item.dataPart + '\',\'' + _item.dataParts + '\');">查看</a>';

                    _inner += '</tr>';

                }
                deviceListContent.html("");
                deviceListContent.append(_inner);

            } else {
                showAlert(resp.message);
            }
            makePage(newPagePager.page, newPagePager.pageCount);
        });

}

var doSearch = function () {


    loadData(1);
}

var initBtnEvent = function () {
    //搜
    $("#search-button").off('click').click(doSearch);
    //页签切换
    $("#saleStateList>li").click(function (event) {
        //此事件触发时，active的还没变，此时执行搜索会导致搜索旧的tab数据
        //所以延迟一下再执行
        setTimeout(doSearch, 300);
    });
}

function makePage(page, pageCount) {
    newPagePager.currentPage = 1;
    if (page > pageCount) {
        newPagePager.currentPage = pageCount;
    } else {
        newPagePager.currentPage = page;
    }

    $("#new-page-pager").attr("data-page", newPagePager.currentPage);

    if (newPagePager.currentPage <= 1) {
        $("#prePageButton").addClass("disabled").prop("disabled", true);
    } else {
        $("#prePageButton").removeClass("disabled").prop("disabled", false);
    }

    if (newPagePager.currentPage >= pageCount) {
        $("#nextPageButton").addClass("disabled").prop("disabled", true);
    } else {
        $("#nextPageButton").removeClass("disabled").prop("disabled", false);
    }

    $("#toPageButton").off('click').click(function () {
        var toPage = $.trim($("#toPage").val());
        if (isNaN(toPage)) {
            return;
        }
        loadData(toPage, "");
    });
    $("#prePageButton").off('click').click(function () {
        loadData(newPagePager.currentPage - 1, "");
    });
    $("#nextPageButton").off('click').click(function () {
        loadData(newPagePager.currentPage + 1, "");
    });

    $("#pageInfo").text("共 " + newPagePager.total + " 条数据 , 第 " + newPagePager.currentPage + " 页 , 共 " + newPagePager.pageCount + " 页");
    return newPagePager.currentPage;
}

var initDatePicker = function(){
    $("#startTime").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
    $("#endTime").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
}
var editOpen= function(datapart, dataparts){
    $("#edit_orgname").val(datapart);
    $("#edit_name").val(dataparts);
    $("#editUserModal").modal("show");
}
$(function () {
    initBtnEvent();
    doSearch();
});