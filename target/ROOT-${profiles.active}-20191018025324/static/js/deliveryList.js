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
        $("#statusSelect").val('');
        $("#deviceNumber").val('');
    }
    $("#show-more").toggle();
}
var regDelivery = function (deviceUUIDs) {
    $("#regForm_deliveryUUID").val(deviceUUIDs);
    initCompany();
    $('#regDeliveryModal').modal('show');
}

var saveRegDelivery = function () {
    var deviceUUIDs = $("#regForm_deliveryUUID").val();
    var affiliationId = $("#companySelect option:selected").val();
    console.log(affiliationId + "....................")
    console.log(deviceUUIDs + "....................")
    if (affiliationId == "") {
        return;
    }
    $.post("/device/ledger/delivery", {deviceUUIDs: deviceUUIDs, affiliationId: affiliationId}, function (resp) {
        if (resp.success) {
            $('#regDeliveryModal').modal('hide');
            showAlert('设置成功');
            reloadListData();
        } else {
            showAlert(resp.message);
        }
    });

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
    var url = "/device/ledger/get/keyword";
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
        showAlert("此状态不可选！");
        return;
    } else if (status == '注销') {
        status = 6;
    }
    if (newPagePager.startTime != '') {
        if (newPagePager.startTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").length == 8) {
            newPagePager.startTime += ' 00:00:00';
        }
    }
    if (newPagePager.endTime != '') {
        if (newPagePager.endTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").length == 8) {
            newPagePager.endTime += ' 23:59:59';
        }
    }
    console.log("status" + status);
    ajaxPost(url,
        {
            page: page,
            affiliationId: 1,
            userName: newPagePager.userName,
            categoryId: newPagePager.categoryId,
            versionId: newPagePager.versionId,
            model: newPagePager.model,
            batch: newPagePager.batch,
            startTime: newPagePager.startTime,
            endTime: newPagePager.endTime,
            deviceNumber: newPagePager.deviceNumber,
            status: status,
            flag: 2
        }
        , function (resp) {
            if (resp.success) {
                var list = resp.data.list;
                newPagePager.page = resp.data.currentPage || 1;
                newPagePager.pageCount = resp.data.pageTotal || 1;
                newPagePager.total = resp.data.count || 0;
                newPagePager.pageSize = resp.data.pageSize || 10;
                var _inner = '';
                var _deliveryButton = $("#deliveryButton");

                var deliveryListContent = $("#deliveryListContent");
                console.log("list length::::" + list.length);
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
                    } else if (_item.status == 6) {
                        _item.status = '注销';
                    }
                    if (_item.createTime == null) {
                        _item.createTime = '';
                    }
                    _inner += "<tr>" +
                        '<td>' + '<input type="checkbox" id="checks" name="checkbox" value="' + _item.deviceUUID + '"/>' + '</td>' +
                        '<td>' + (i + 1) + '</td>' +
                        '<td>' + _item.deviceNumber + '</td>' +
                        '<td>' + _item.category + '</td>' +
                        '<td>' + _item.model + '</td>' +
                        '<td>' + _item.batch + '</td>' +
                        '<td>' + _item.createTime + '</td>' +
                        '<td>' + _item.status + '</td>' +
                        '<td>' + _item.userName + '</td>' +
                        '<td>' + _item.affiliationName + '</td>' +
                        '<td>' + _item.version + '</td>' +
                        '</tr>';

                }
                var _html = '<span class="input-group-btn" style="">\n' +
                    '<button class="btn btn btn-primary" onclick="checks()" title="出库登记">出库登记</button>\n' +
                    '</span>';

                deliveryListContent.html("");
                deliveryListContent.append(_inner);
                _deliveryButton.html("");
                _deliveryButton.append(_html);

            } else {
                showAlert(resp.message);
            }
            makePage(newPagePager.page, newPagePager.pageCount);
        });

}

function checks() {
    var test = $("input[name='checkbox']:checked");
    var checkBoxValue = "";
    var _content = $("#deviceDeliveryList");
    var _inner = '';
    var j = 1;
    if (test.val() == undefined) {
        showAlert("请选择需要出库的设备！");
        return;
    }
    test.each(function () {
        checkBoxValue += $(this).val() + ",";
        var deviceUUID = $(this).val();
        ajaxPost("/device/ledger/list",
            {
                deviceUUID: deviceUUID
            }
            , function (resp) {
                if (resp.success) {
                    var _item = resp.data;
                    j++;
                    _inner += _item.deviceNumber + '  ';
                    if (j % 5 == 0) {
                        _inner += '\n';
                    }
                    _content.val(_inner);
                    _content.html("");
                } else {
                    showAlert(resp.message);
                }
            }
        )

    })


    checkBoxValue = checkBoxValue.substring(0, checkBoxValue.length - 1);

    console.log("checkBoxValue--------------------3" + checkBoxValue)
    regDelivery(checkBoxValue);
};


var doSearch = function () {
    // var regDept_affiliationId = $("#regDept_affiliationId").val();
    // var userName = $("#userName").val();  //10/26 去掉持有人筛选，换为设备编号筛选
    var categoryId = $("#categorySelect").val();
    var model = $("#model").val();
    var batch = $("#batchSelect option:selected").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var versionId = $("#versionSelect").val();
    var status = $("#statusSelect").val();
    var deviceNumber = $("#deviceNumber").val();
    // newPagePager.regDept_affiliationId = regDept_affiliationId;
    // newPagePager.userName = userName;
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
    $("#add-button").off('click').click(function () {
        $("#addCompanyModal").modal("show");
        changeAddCity();
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

function newCompany() {
    $("#add_companyName").val('');
    $("#add_contact").val('');
    $("#add_contactNumber").val('');
    $("#add_address").val('');
    $("#add_account").val('');
    $("#add_password").val('');
    $("#add_name").val('');
    $("#add_birthday").val('');
    $("#add_mobile").val('');
    // $("#add_home").val('');
    $("#add_department").val('');

    $("#addCompanyModal").modal("show");


}

var checkMobile = function (cm) {
    var check = true;

    var a = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!a.test($(cm).val())) {
        $(cm).css("border-color", "red");
        $("." + $(cm).attr('id') + "_error").text("输入的号码格式有误").show();
        check = false;
    } else {
        checkFocus(cm);
    }

    return check;
}

var checkNull = function (cn) {
    var check = true;

    if ($(cn).val() == '') {
        $(cn).css("border-color", "red");
        $("." + $(cn).attr('id') + "_error").text("必填项输入不可为空").show();
        check = false;
    } else {
        checkFocus(cn);
    }

    return check;
}

var checkFocus = function (cf) {
    $(cf).css("border-color", "");
    $("." + $(cf).attr('id') + "_error").hide();
}

function saveCompany() {
    var birthday = formatToTime("2000-01-01");
    if ($("#add_birthday").val() != '') {
        birthday = formatToTime($.trim($("#add_birthday").val()));
    }

    var data = {
        companyName: $.trim($("#add_companyName").val()),
        companyNature: $.trim($("#add_companyNature").val()),
        address: $.trim($("#add_address").val()),
        province: $.trim($("#add_province").val()),
        city: $.trim($("#add_city").val()),
        contact: $.trim($("#add_contact").val()),
        contactNumber: $.trim($("#add_contactNumber").val()),
        account: $.trim($("#add_account").val()),
        password: $.trim($("#add_password").val()),
        name: $.trim($("#add_name").val()),
        sex: $.trim($("#add_sex").val()),
        birthday: birthday,
        mobile: $.trim($("#add_mobile").val()),
        department: $.trim($("#add_department").val())
    };
    if (checkNull($("#add_companyName")) && checkNull($("#add_address")) && checkNull($("#add_contact")) && checkMobile($("#add_contactNumber")) && checkNull($("#add_account")) && checkNull($("#add_name")) && checkMobile($("#add_mobile"))) {
        var postUrl = "/company/add";
        $.post(postUrl, {data: JSON.stringify(data)}, function (resp) {
            if (resp.success) {
                initCompany();
                $("#addCompanyModal").modal('hide');
                showAlert("保存成功");
                reloadListData();
            } else {
                showAlert(resp.message);
            }
        }, "json");
    }
}

var formatToTime = function (yyyyMMddHHmmss) {
    yyyyMMddHHmmss = yyyyMMddHHmmss.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
    var d = new Date();
    d.setYear(yyyyMMddHHmmss.substring(0, 4) * 1);
    d.setMonth(yyyyMMddHHmmss.substring(4, 6) * 1 - 1);
    d.setDate(yyyyMMddHHmmss.substring(6, 8) * 1);
    d.setHours(yyyyMMddHHmmss.substring(8, 10) * 1);
    d.setMinutes(yyyyMMddHHmmss.substring(10, 12) * 1);
    d.setSeconds(yyyyMMddHHmmss.substring(12, 14) * 1);
    d.setMilliseconds(0);
    return d.getTime();
}

var changeAddCity = function () {
    var id = $("#add_province").val();
    $.post("/dictionary/city?id=" + id, function (resp) {
        $("#add_city").html("");
        if (resp.success) {
            $.each(resp.data, function (idx, c) {
                $("#add_city").append('<option value="' + c.id + '">' + c.cityName + '</option>');
            })
        }
    });
}
var initProvince = function () {
    $.get("/dictionary/province", function (resp) {
        if (resp.success) {
            $.each(resp.data, function (idx, p) {
                $("#add_province").append('<option value="' + p.id + '">' + p.provincialName + '</option>');
            });

            changeAddCity();
        }
    });
}


var initNature = function () {
    $.get("/dictionary/companynature", function (resp) {
        if (resp.success) {
            $.each(resp.data, function (idx, cn) {
                $("#add_companyNature").append('<option value="' + cn.id + '">' + cn.natureName + '</option>');
            });
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

function initCompany() {
    var selectObj = $("#companySelect");
    $.post("/company/listnopage", {}, function (resp) {
        if (resp.success) {
            selectObj.html('');
            $.each(resp.data, function (idx, cust) {
                selectObj.append('<option value="' + cust.id + '">' + cust.companyName + '</option>');
            });
            // $('select').material_select();//下拉条控件初始化
        } else {
            showAlert('载入单位列表失败');
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
                if (sta.status != '删除') {
                    selectObj.append('<option value="' + sta.status + '">' + sta.status + '</option>');
                }
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
    initProvince();
    initNature();
    batchReg();
    modelReg();
    initCompany();
    initStatus();
});