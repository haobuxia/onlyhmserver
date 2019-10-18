var reloadListData = function () {
    loadData(1);
}

var loadData = function (page) {
    var url = "/workordermanage/search?keyword=" + workorderIndexPage.keyword + "&page=" + page;
    loadContent("#contentSection", url);
}

var initWorkOrderEvent = function () {
    $("#editWorkOrder_tags").selectpicker('val',null);
    $('.selectpicker').selectpicker('refresh');

    $("#search-button").click(function () {
        workorderIndexPage.keyword = $.trim($("#keyword").val());
        loadData(1);
    });

    $("#add-button").click(function () {
        $("#editWorkOrder_planTime").val(new Date().Format("yyyyMMdd hhmm"));
        $("#editWorkOrderModalTitle").text("新增工单");
        $('#editWorkOrderModal').modal('show');
    });

    $("#editWorkOrder-button").click(function () {
        //提交
        submitWorkOrderEdit();
    });
}

var submitWorkOrderEdit = function () {
    var workOrder = {};
    workOrder.id = $.trim($("#editWorkOrder_id").val());
    workOrder.orderNo = $.trim($("#editWorkOrder_orderNo").val());
    workOrder.userId = $.trim($("#editWorkOrder_userId").val());

    workOrder.orderType = $.trim($("#editWorkOrder_orderType").val());
    workOrder.subject = $.trim($("#editWorkOrder_subject").val());
    workOrder.userRealName = $.trim($("#editWorkOrder_userRealName").val());
    workOrder.customerName = $.trim($("#editWorkOrder_customerName").val());
    workOrder.contactName = $.trim($("#editWorkOrder_contactName").val());
    workOrder.contactPhone = $.trim($("#editWorkOrder_contactPhone").val());
    workOrder.planTime = $.trim($("#editWorkOrder_planTime").val());
    workOrder.brand = $.trim($("#editWorkOrder_brand").val());
    workOrder.model = $.trim($("#editWorkOrder_model").val());
    workOrder.machineCode = $.trim($("#editWorkOrder_machineCode").val());
    workOrder.address = $.trim($("#editWorkOrder_address").val());
    workOrder.latLng = $.trim($("#editWorkOrder_latLng").val());
    workOrder.remark = $.trim($("#editWorkOrder_remark").val());
    workOrder.tags = $.trim($("#editWorkOrder_tags").val());
    if(workOrder.id == "") workOrder.id="0";

    if(workOrder.userRealName == "" ||
        workOrder.customerName == "" ||
        workOrder.contactName == "" ||
        workOrder.contactPhone == "" ||
        workOrder.planTime == "" ||
        workOrder.brand == "" ||
        workOrder.model == "" ||
        workOrder.machineCode == "" ||
        workOrder.address == ""
    ){
        showAlert("请把各项数据填写完整");
        return;
    }

    var url = "/workordermanage/" + (workOrder.id == "0" ? "add" : "save");
    ajaxPost(url, workOrder, function (resp) {
        if (resp.success) {
            showAlert("保存成功");
            $('#editWorkOrderModal').modal('hide');
            reloadListData();
        } else {
            showAlert("保存失败." + resp.message);
        }
    })
}

var removeWorkOrder = function (linkObj) {
    var tr = $(linkObj).parents("tr[data-orderNo]");
    var orderNo = tr.attr("data-orderNo");
    var orderState = tr.attr("data-orderState");
    if (orderState != "INIT") {
        showAlert("该工单不可删除");
        return;
    }

    if(confirm("确定要删除这个工单吗？")){
        ajaxPost("/workordermanage/delete/" + orderNo, {}, function (resp) {
            if (resp.success) {
                showAlert("删除成功");
                reloadListData();
            } else {
                showAlert("删除失败." + resp.message);
            }
        })
    }
}

var editWorkOrder = function (linkObj) {
    var tr = $(linkObj).parents("tr[data-orderNo]");
    var orderNo = tr.attr("data-orderNo");
    var orderState = tr.attr("data-orderState");
    if (orderState != "INIT") {
        showAlert("该工单不可修改");
        return;
    }

    ajaxGet("/workordermanage/get/" + orderNo, function (resp) {
        if (resp.success) {
            openWorkOrderEditModel(resp.data);
        } else {
            showAlert("获取数据失败,无法修改." + resp.message);
        }
    });
}
var detailsWorkOrder = function (linkObj) {
    var tr = $(linkObj).parents("tr[data-orderNo]");
    console.log(tr);
    var orderNo = tr.attr("data-orderNo");
    console.log(orderNo);
    /*var orderState = tr.attr("data-orderState");
    if (orderState != "INIT") {
        showAlert("该工单不可修改");
        return;
    }*/
    loadMainContent("/workordermanage/"+orderNo+"/detail");
}
var editStrategy = function (linkObj) {
    var tr = $(linkObj).parents("tr[data-orderNo]");
    var orderNo = tr.attr("data-orderNo");
    loadMainContent("/workorderstrategy/list/"+orderNo);
}

var openWorkOrderEditModel = function (workOrder) {
    $("#editWorkOrder_id").val(workOrder.id);
    $("#editWorkOrder_orderNo").val(workOrder.orderNo);
    $("#editWorkOrder_orderType>option[value="+workOrder.orderType+"]").prop("selected","true").siblings("option").prop("selected",false);
    $("#editWorkOrder_subject").val(workOrder.subject);
    $("#editWorkOrder_userId").val(workOrder.userId);
    $("#editWorkOrder_userRealName").val(workOrder.userRealName);
    $("#editWorkOrder_customerName").val(workOrder.customerName);
    $("#editWorkOrder_contactName").val(workOrder.contactName);
    $("#editWorkOrder_contactPhone").val(workOrder.contactPhone);
    var planTime = new Date(workOrder.planTime);
    $("#editWorkOrder_planTime").val(planTime.Format("yyyyMMdd hhmm"));
    $("#editWorkOrder_brand").val(workOrder.brand);
    $("#editWorkOrder_model").val(workOrder.model);
    $("#editWorkOrder_machineCode").val(workOrder.machineCode);
    $("#editWorkOrder_address").val(workOrder.address);
    $("#editWorkOrder_latLng").val(workOrder.latLng);
    $("#editWorkOrder_remark").val(workOrder.remark);

    if(workOrder.tags != undefined){
        $("#editWorkOrder_tags").selectpicker('val',workOrder.tags.split(","));
        $('.selectpicker').selectpicker('refresh');
    }

    $("#editWorkOrderModalTitle").text("修改工单");
    $('#editWorkOrderModal').modal('show');
}

$(function () {
    initWorkOrderEvent();
    reloadListData();
})