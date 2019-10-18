var showMore = function () {
    if ($("#show-more").css("display") == 'block') {
        $("#model").val('');
        $("#batchSelect").val('');
        $("#startTime").val('');
        $("#endTime").val('');
        $("#versionSelect").val('');
        $("#categorySelect").val('');
        $("#userName").val('');
        $("#afflSelect").val('');
    }
    $("#show-more").toggle();
}

function reloadListData() {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/device/ledger/get/history";
    var status = newPagePager.status;
    if (status == '正常') {
        status = 1;
    } else if (status == '维修') {
        status = 2;
    } else if (status == '丢失') {
        status = 3;
    } else if (status == '报废') {
        status = 4;
    } else if (status == '删除') {
        status = 5;
    } else if (status == '注销') {
        status = 6;
    }
    if(newPagePager.startTime != ''){
        if(newPagePager.startTime.replaceAll("-","").replaceAll(":","").replaceAll(" ","").length == 8){
            newPagePager.startTime += ' 00:00:00';
        }
    }
    if(newPagePager.endTime != ''){
        if(newPagePager.endTime.replaceAll("-","").replaceAll(":","").replaceAll(" ","").length == 8){
            newPagePager.endTime += ' 23:59:59';
        }
    }
    ajaxPost(url,
        {
            page: page,
            affiliationId: newPagePager.affiliationId,
            userName: newPagePager.userName,
            categoryId: newPagePager.categoryId,
            versionId: newPagePager.versionId,
            model: newPagePager.model,
            batch: newPagePager.batch,
            startTime: newPagePager.startTime,
            endTime: newPagePager.endTime,
            status: status,
            deviceNumber: newPagePager.deviceNumber
        },
        function (resp) {
            if (resp.success) {
                var list = resp.data.list;
                newPagePager.page = resp.data.currentPage || 1;
                newPagePager.pageCount = resp.data.pageTotal || 1;
                newPagePager.total = resp.data.count || 0;
                newPagePager.pageSize = resp.data.pageSize || 10;
                var _inner = '';
                var historyListContent = $("#historyListContent");
                for (var i = 0; i < list.length; i++) {
                    var _item = list[i];
                    if (_item.status == 1) {
                        _item.status = '正常';
                    } else if (_item.status == 2) {
                        _item.status = '维修';
                    } else if (_item.status == 3) {
                        _item.status = '丢失';
                    } else if (_item.status == 4) {
                        _item.status = '报废';
                    } else if (_item.status == 5) {
                        _item.status = '删除';
                    } else if (_item.status == 6) {
                        _item.status = '注销';
                    }
                    if (_item.endTime == null) {
                        _item.endTime = '';
                    }
                    if (_item.startTime == null) {
                        _item.startTime = '';
                    }
                    if (_item.createTime == null) {
                        _item.createTime = '';
                    }
                    if(_item.remark == null){
                        _item.remark = '';
                    }
                    _inner += "<tr>" +
                        '<td>' + (i + 1) + '</td>' +
                        '<td>' + _item.deviceNumber + '</td>' +
                        '<td>' + _item.category + '</td>' +
                        '<td>' + _item.model + '</td>' +
                        '<td>' + _item.batch + '</td>' +
                        '<td>' + _item.createTime + '</td>' +
                        '<td>' + _item.status + '</td>' +
                        '<td>' + _item.userName + '</td>' +
                        '<td>' + _item.startTime + '</td>' +
                        '<td>' + _item.endTime + '</td>' +
                        '<td>' + _item.affiliationName + '</td>' +
                        '<td>' + _item.version + '</td>' +
                        '<td>' + _item.remark + '</td>' +
                        '</tr>';
                }
                historyListContent.html("");
                historyListContent.append(_inner);
            } else {
                showAlert(resp.message);
            }
            makePage(newPagePager.page, newPagePager.pageCount);
        });
}

var doSearch = function () {
    var affiliationId = $("#afflSelect").val();
    var userName = $("#userName").val();
    var categoryId = $("#categorySelect").val();
    var versionId = $("#versionSelect").val();
    var model = $("#model").val();
    var batch = $("#batchSelect option:selected").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var status = $("#statusSelect").val();
    var deviceNumber = $("#deviceNumber").val();
    newPagePager.affiliationId = affiliationId;
    newPagePager.userName = userName;
    newPagePager.categoryId = categoryId;
    newPagePager.versionId = versionId;
    newPagePager.model = model;
    newPagePager.batch = batch;
    newPagePager.startTime = startTime;
    newPagePager.endTime = endTime;
    newPagePager.status = status;
    newPagePager.deviceNumber = deviceNumber;
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

function afflReg() {
    console.log("afflReg Start");
    var selectObj = $("#afflSelect");
    $.post("/company/listnopage", {}, function (resp) {
        console.log("afflReg post Start");
        if (resp.success) {
            $.each(resp.data, function (idx, affl) {
                if (userOrganisation == 1) {
                    selectObj.append('<option value="' + affl.id + '">' + affl.companyName + '</option>');
                } else {
                    if (userOrganisation == affl.id) {
                        $("#sl").html('');
                        $("#sl").append('<input type="text" id="afflSelect" value="' + affl.id + '" style="display: none" /><input type="text" class="form-control" value="' + affl.companyName + '" readonly />');
                    }
                }
            });
            selectObj.attr("data-init", "true");

        } else {
            showAlert('载入单位列表失败');
        }

    });
}

function cateReg() {
    console.log("cateReg Start");
    var selectObj = $("#categorySelect");
    $.post("/dictionary/device/category", {}, function (resp) {
        console.log("cateReg post Start");
        if (resp.success) {
            $.each(resp.data, function (idx, category) {
                selectObj.append('<option value="' + category.categoryId + '">' + category.category + '</option>');
            });
            selectObj.attr("data-init", "true");
        } else {
            showAlert('载入品类列表失败');
        }

    });
}

function verReg() {
    console.log("verReg Start");
    var selectObj = $("#versionSelect");
    $.post("/dictionary/device/version", {}, function (resp) {
        console.log("verReg post Start");
        if (resp.success) {
            $.each(resp.data, function (idx, ver) {
                selectObj.append('<option value="' + ver.versionId + '">' + ver.version + '</option>');
            });
            selectObj.attr("data-init", "true");
        } else {
            showAlert('载入品类列表失败');
        }

    });
}

function batchReg() {
    console.log("batchReg Start");
    var selectObj = $("#batchSelect");
    $.post("/dictionary/device/batch", {}, function (resp) {
        console.log("batchReg post Start");
        if (resp.success) {
            $.each(resp.data, function (idx, b) {
                selectObj.append('<option value="' + b.batch + '">' + b.batch + '</option>');
            });
            selectObj.attr("data-init", "true");
        } else {
            showAlert('载入批次列表失败');
        }

    });
}

function modelReg() {
    console.log("modelReg Start");
    var selectObj1 = $("#model");
    $.post("/dictionary/device/model", {}, function (resp) {
        console.log("batchReg post Start");
        if (resp.success) {
            $.each(resp.data, function (idx, m) {
                selectObj1.append('<option value="' + m.model + '">' + m.model + '</option>');
            });
        } else {
            showAlert('载入批次列表失败');
        }

    });
}
function initStatus() {
    console.log("initStatus Start");
    var selectObj = $("#statusSelect");
    $.post("/dictionary/device/status", {}, function (resp) {
        console.log("initStatus post Start");
        if (resp.success) {
            $.each(resp.data, function (idx, sta) {
                // if(sta.statusId != 6){
                selectObj.append('<option value="' + sta.status + '">' + sta.status + '</option>');
                // }
            });
        } else {
            showAlert('载入状态列表失败');
        }

    });
}
$(function () {
    initBtnEvent();
    doSearch();
    cateReg();
    verReg();
    afflReg();
    batchReg();
    modelReg();
    initStatus();
});