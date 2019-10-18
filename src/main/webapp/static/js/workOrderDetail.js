//获取 工单详情
var getOrderdetail = function (orderNo) {
    ajaxGet("/workordermanage/get/" + orderNo, function (resp) {
        if (resp.success) {
            showOrderdetail(resp.data);
        } else {
            showAlert("获取数据失败,无法修改." + resp.message);
        }
    });
}
//填单 工单详情
var showOrderdetail = function (workOrder) {
    $("#editWorkOrder_id").val(workOrder.id);
    $("#editWorkOrder_orderNo").val(workOrder.orderNo);
    $("#editWorkOrder_orderType").val(workOrder.orderTypeName);
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
    $("#editWorkOrder_tags").val(workOrder.tags)
}
//获取 零件信息
var getPart = function (orderNo) {
    var url = "/workordermanage/" + orderNo + "/part";
    ajaxGet(url, function (resp) {
        if (resp.success) {
            showOrderParts(resp.data);
        } else {
            showAlert("获取零件信息失败,错误代码：" + resp.message);
        }
    });
}
//填单 零件列表
var showOrderParts = function (res) {
    switch (res.codeType) {
        case 1:
            var ctype = '条形码';
            break;
        case 2:
            var ctype = '二维码';
            break;
        default:
            var ctype = '扫码类型未定义';
    }
    if(typeof(res.partNo) == 'undefined'){
        showAlert("该工单号未扫描零件");
        res.partNo = '';
        res.partName = '';
        res.warehouseName = '';
        res.model = '';
        ctype = '';
        res.createTime = '';
    }
    var tr = '<tr>'
        + '<td>' + res.partNo + '</td>'
        + '<td>' + res.partName + '</td>'
        + '<td>' + res.warehouseName + '</td>'
        + '<td>' + res.model + '</td>'
        + '<td>' + ctype + '</td>'
        + '<td>' + res.createTime + '</td>'
        + '</td>'
        + '</tr>';

    $('#parts tbody').html(tr);

    //获取零件列表
    getPartDetail(res.partNo);
    //获取视频列表
    getPartVideos();
}


//获取 零件详情
var getPartDetail = function (partNo) {
    $("div.progress").attr('style','display:block!important');
    console.log(1)
    if(partNo == ''){
        partNo = 'undo'
    }
    var url = "/workordermanage/" + orderNo + "/part/" + partNo + "/";
    ajaxGet(url, function (resp) {
        if (resp.success) {
            showOrderPartdetail(resp.data);
        } else {
            showAlert("获取零件详情失败,错误代码：" + resp.message);
        }
    });
}
//查看 零件详情列表  仓储
var showOrderPartdetail = function (res) {
//零件详情列表
    $("div.progress").attr('style',' ');
    if(res.partInfoList == null){
        showAlert("未通过零件号扫描到零件详情");
    }

        console.log(res.partInfoList);
    var partInfoList = res.partInfoList;
    var $partInfo = $("#partInfo");
    var innerPartInfo = '';
    for (var i = 0; i < partInfoList.length; i++) {
        var _item = partInfoList[i];
        var _inner = '<h3 style="margin-left:10px;">' + _item.vclType + '</h3>'
            + '<ul class="list-unstyled cover_zone clearfix">';
        for (var j = 0; j < _item.partsList.length; j++) {
            var _e = _item.partsList[j];
            _inner += '<li class="col-md-3 col-sm-4 col-xs-6 col-lg-2">'
                + '<div class="panel cover_list btnDiv-video">'
                + '<h4>' + _e.sysOneName + '</h4>'
                + '<h5>' + _e.sysTwoName + '</h5>'
                + '<div class="helmet-video" data-mc="">'
                + '<img src="http://webapp.tymics.cn/MachineTypeImg/'+ _item.vclType+'/' + _e.image + '">'
                + '</div>'
                + '<h5>' + _e.partName + '</h5>'
                + '<h5>￥' + _e.partPrice + '</h5>'
                + '</li>';
        }
        _inner += '</ul>';
        $partInfo.append(_inner);
    }
// 仓储
    var inventoryList = res.inventoryList;
    var $inventory = $('#inventory');
    var $inner = '';
    for (var i = 0; i < inventoryList.length; i++) {
        var $item = inventoryList[i];
        $inner += '<tr>'
            + '<td>' + $item.warehouseName + '</td>'
            + '<td>' + $item.inventoryQty + '</td>'
            + '</tr>';
    }
    $inventory.html($inner);

}

var loadData = function (page) {
    var url = "/workordermanage/" + orderNo +"/video/" + page;
    ajaxGet(url, function (resp) {
        if (resp.success) {
            showOrderVideos(resp.data);
        } else {
            showAlert("获取相关视频失败,错误代码：" + resp.message);
        }
    });
}

//获取视频
/*var getPartVideos = function(partNo){
    var url = "/workordermanage/" + orderNo +"/video/" + partNo;
    ajaxGet(url, function (resp) {
        if (resp.success) {
            showOrderVideos(resp.data);
        } else {
            showAlert("获取零件详情失败,错误代码：" + resp.message);
        }
    });
}
//显示视频列表
var showOrderVideos = function(res){
    var _videos = res.list;
    var _inner = '';
    var _page = res.page;
    var _pagecount = res.pageCount;
    var _pagesize = res.pageSize;
    var _total = res.total;
    var _flag = res.flag;
    for (var i = 0; i < _videos.length; i++) {
        _inner += '<li class="col-md-3 col-sm-4 col-xs-6">'
                + '<div class="panel cover_list btnDiv-video" data-id="'+_videos[i].id+'">'
                + '<h4>'+_videos[i].id+'</h4>'
                + '<div class="helmet-video" data-id="'+_videos[i].id+'" data-mc="">'
                + '<img src="'+_videos[i].thumbOssPath+'" alt="视频" style="cursor:pointer;" onclick="viewMedia(\'video\','+_videos[i].id+')">'
                + '</div>'
                + '<h5>'+_videos[i].createTimeString+'</h5>'
                +'</div></li>'
    }
    $('#orderVideos').html(_inner);
    $('new-pager').attr(_pagecount);
    $('new-pager').attr(_pagesize);
    $('new-pager').attr(_total);
    $('new-pager').attr(_flag);
    $('new-pager').attr(_page);
}*/

$(function () {
    getOrderdetail(orderNo);
    getPart(orderNo);



// tabs切换
    $('body').on('click', '#tabs li', function () {

        $(this).addClass('active').siblings('li').removeClass('active');
        var tab = $(this).data('tab');
        $('#conPanel>div').hide();
        $('#' + tab).show();
    });

});