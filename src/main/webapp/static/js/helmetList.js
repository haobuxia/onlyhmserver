var editHelmet = function (id, model, batch) {
    $("#helmetedit_id").val(id);
    $("#helmetedit_model").val(model);
    $("#helmetedit_batch").val(batch);
    $("#editHelmetModal").modal('show');
}

var saveHelmet = function () {
    var id = $("#helmetedit_id").val();
    var model = $("#helmetedit_model").val();
    var batch = $("#helmetedit_batch").val();

    ajaxPost("/helmet/save/" + id, {"model": model, "batch": batch}, function (resp) {
        if (resp.success) {
            showAlert("修改成功");
            reloadListData();
            $("#editHelmetModal").modal("hide");
        } else {
            showAlert("修改失败." + resp.message);
        }
    })
}

var delHelmet = function (id) {
    if (confirm("确定删除这个头盔数据吗?")) {
        $.post("/helmet/remove/" + id, function (resp) {
            if (resp.success) {
                reloadListData();
            } else {
                showAlert(resp.message);
            }
        });
    }
}

var loadApkUpdateList = function (imei) {
    loadMainContent("/apk/update/list/1?imei=" + imei);
}

var openXmlModel = function (neUserName) {
    $('#updateXmlModalTitle').text("升级更新文件(" + neUserName + ")");
    var items = $("#updateXmlModal a.collection-item");
    $.each(items, function (idx, item) {
        var type = $(item).attr("data-type");
        var url = "/appupdate/" + type + "/version.xml?clientId=" + neUserName;
        $(item).attr("href", url);
    });
    $('#updateXmlModal').modal('show');
}

//打开二维码
var initAccount = function (id, isInit) {
    var url = window.location.protocol + "//" + "111.11.4.69" + "/helmetqr/code/" + id;
    $("#qrcode_id").val(id);
    $("#qrCodeImage").addClass("hide");
    $('#qrCode canvas').remove();
    $('#qrCode').qrcode(url);
    var canvas = $('#qrCode canvas');
    var dataUrl = canvas[0].toDataURL();

    $("#qrCodeImage").attr("src", dataUrl).removeClass("hide");
    canvas.remove();

    if (isInit == 1) {
        $("#qrCodeModalTitle").text("初始化账号");
        $("#qrCodeModalBtn").text("手工初始化");
    } else {
        $("#qrCodeModalTitle").text("重新扫码");
        $("#qrCodeModalBtn").text("重新初始化");
    }
    $('#qrCodeModal').modal('show');
}

var regHelmet = function (id) {
    $("#regForm_helmetId").val(id);
    var selectObj = $("#customerSelect");
    var init = selectObj.attr("data-init");
    if (init == "false") {
        $.post("/customer/listCustomers", {}, function (resp) {
            if (resp.success) {
                $.each(resp.data, function (idx, cust) {
                    selectObj.append('<option value="' + cust.key + '">' + cust.val + '</option>');
                });
                // $('select').material_select();//下拉条控件初始化
                selectObj.attr("data-init", "true");
                $('#regHelmetModal').modal('show');
            } else {
                showAlert('载入我的客户失败');
            }
        });
    } else {
        $('#regHelmetModal').modal('show');
    }
}

var saveRegHelmet = function () {
    var helmetId = $("#regForm_helmetId").val();
    var customerId = $("#customerSelect option:selected").val();
    if (customerId == "") {
        return;
    }
    $.post("/helmet/setCustomer/" + helmetId, {customerId: customerId}, function (resp) {
        if (resp.success) {
            showAlert('设置成功');
            reloadListData();
        } else {
            showAlert(resp.message);
        }
    });
    $('#regHelmetModal').modal('hide');
}

var manualInit = function () {
    var id = $("#qrcode_id").val();
    var imei = $.trim($("#qrcode_imei").val());
    if (imei == "") {
        showAlert('请输入imei');
        return;
    }
    $.post("/helmetqr/code/" + id, {"imei": imei}, function (resp) {
        if (resp.code == "200") {
            var data = resp.data;
            alert("手工初始化成功.分配给头盔[" + imei + "]的账号是:" + data.username + "，密码是:" + data.yun_token + "\n请牢记此账号密码在头盔app上登录.");
            $('#qrCodeModal').modal('hide');
            reloadListData();
        } else {
            showAlert(resp.msg);
        }
    }, "json");
}

var printQrCode = function () {
    $('#qrCode').jqprint({
        debug: false,
        importCSS: true,
        printContainer: true,
        operaSupport: false
    })
}

function reloadListData() {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/helmet/list/" + page + "?saleState=" + helmetListPage.saleState + "&keyword=" + helmetListPage.keyword;
    loadContent("#listContent", url, function (html) {
        $("#keyword").val(helmetListPage.keyword != "" ? helmetListPage.keyword : "");
    });
}

var doSearch = function () {
    var keyword = $("#keyword").val();
    var state = $("#saleStateList>li.active").attr("data-statetype");
    helmetListPage.keyword = keyword;
    helmetListPage.saleState = state;
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

    //扩充账号
    $("#add-button").off('click').click(function () {
        var keyword = $("#keyword").val();
        var count = parseInt(keyword);
        if (!$.isNumeric(count) || count <= 0) {
            showAlert('请输入大于0的数字');
            return;
        }
        $("#add_count").val(count);

        if (confirm("确定要扩充头盔账号吗，数量" + count + "个？")) {
            $.post("/helmet/addAccount", {count: count}, function (resp) {
                if (resp.success) {
                    showAlert('扩充成功.数量:' + resp.data);
                    loadData(1);
                } else {
                    showAlert('扩充失败.' + resp.message);
                }
            }, "json");
        }
    });
}

$(function () {
    initBtnEvent();
    doSearch();
});