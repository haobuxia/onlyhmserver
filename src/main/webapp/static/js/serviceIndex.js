var initBtnEvent = function () {
    // $('select').material_select();
    $("#search-button").off('click').click(reloadListData);
    $("#serviceOrderDetailModalCloseBtn").off('click').click(function(){
        $("#serviceOrderDetailModal").modal('hide');
        $("#serviceOrderModal").modal('show');
        $("#serviceOrderDetailTableBody").empty();
    });
}

var initUiData = function () {
    if(serviceIndexPage.isTianYuanUser){//天远用户只能查自己的工单
        $("#service_oprtId").prop("disabled",true).prop("readonly",true);
    }else{//能查所有人的
        $("#service_oprtId").prop("disabled",false).prop("readonly",false);
    }

    var todayTime = new Date().getTime();
    var dayBefore10 = new Date();
    var tomorrow = new Date();
    dayBefore10.setTime(todayTime - 90* 24* 60* 60 * 1000);
    tomorrow.setTime(todayTime + 4* 24* 60* 60 * 1000);

    $("#service_date1").val(dayBefore10.Format("yyyy-MM-dd"));
    $("#service_date2").val(tomorrow.Format("yyyy-MM-dd"));
}

function reloadListData(){
    loadData(1);
}

var loadData = function (page) {
    var oprtId = $.trim($("#service_oprtId").val());
    if(oprtId != ""){
        var rwh = $.trim($("#service_rwh").val());
        if(rwh != ""){
            openTaskModel(rwh,oprtId);
        }else{
            var rwzt = $("#service_rwzt").val();
            var date1 = $("#service_date1").val();
            var date2 = $("#service_date2").val();
            if( rwzt == "" || rwzt == null || date1 == "" || date1 == ""){
                showAlert("请设置各项查询条件");
                return;
            }
            var url = "/service/queryTaskList/"+page+"?rwzt="+rwzt+"&date1="+date1+"&date2="+date2+"&oprtId="+decodeURIComponent(oprtId);
            loadContent("#contentSection",url);
        }
    }else{
        showAlert("请设置各项查询条件");
        return;
    }
}

var initTaskLink = function () {
    var links = $("#taskListBody a[data-rwh]");
    $.each(links,function (idx,link) {
        $(link).off('click').click(function(){
            var rwh = $(this).attr("data-rwh");
            var oprtId = $(this).attr("data-oprtId");
            openTaskModel(rwh,oprtId);
        })
    });
}

var translateRwztText = function () {
    $.each($("#taskListBody  td[data-rwzt]"),function(idx,td){
        var rwzt = $(td).attr("data-rwzt");
        var txt = getRwztCnName(rwzt);
        $(td).text(txt);
    });
}

var getRwztCnName = function (rwzt) {
    var txt = $("#service_rwzt option[value='"+rwzt+"']").text();
    return txt;
}

var openTaskModel = function (rwh,oprtId) {
    $("#serviceOrderRwh").val(rwh);
    $("#serviceOrderOprtId").val(oprtId);
    $("#serviceOrderModal i.fa").html("edit");
    $("#serviceOrderModal div.card-panel").off('click');
    $('#serviceOrderModal').modal('show');

    loadTaskData(rwh,oprtId);
}

var openTaskDetailModel = function (rwh,itemKey,itemName) {
    // showAlert("显示任务:"+rwh+"的数据项:"+itemKey);
    $('#serviceOrderModal').modal('hide');
    var orderMapData = serviceIndexPage.orderMapData[rwh];
    var orderFieldDatas = orderMapData[itemKey];
    var detailTabBody = $("#serviceOrderDetailTableBody");
    detailTabBody.empty();//清空
    if(orderFieldDatas != null){
        if(itemKey == "svcResData" || itemKey == "SvcCommPic") {
            $.each(orderFieldDatas,function(idx,field){
                var valTdVal = "<a href='"+field[2]+"' target='_blank'>"+field[1]+"<a/>";
                detailTabBody.append("<tr><td>"+field[0]+"</td><td>"+valTdVal+"</td></tr>");
            });
        }else{
            $.each(orderFieldDatas,function(idx,field){
                var valTdVal = field[2];
                detailTabBody.append("<tr><td data-field='"+field[0]+"'>"+field[1]+"</td><td>"+valTdVal+"</td></tr>");
            });
        }
    }

    $("#serviceOrderDetailTitle").text(itemName);
    $("#serviceOrderDetailModal").modal('show');
}

var isHasDataItem = function (dataItem,rwlb) {
    var meta = serviceIndexPage.taskMetaData[dataItem];
    console.debug("判断是否在meta中.dataItem="+dataItem+",rwlb="+rwlb+",meta="+meta);
    var hasItem = meta.key;//true,false
    var fwlbString = meta.val;
    if(hasItem){
        var show = false;
        $.each(fwlbString.split(","),function (idx,fwlb) {
            if(rwlb == fwlb){
                show = true;
                return false;//break
            }
        });
        return show;
    }else{
        var show = true;
        $.each(fwlbString.split(","),function (idx,fwlb) {
            if(rwlb == fwlb){
                show = false;
                return false;//break
            }
        });
        return show;
    }
}

var loadTaskData = function (rwh,oprtId) {
    ajaxGet("/service/getTaskInfo/"+rwh+"/"+oprtId,function (resp) {
        if(resp.success){
            var orderMapData = resp.data;
            serviceIndexPage.orderMapData[rwh] = orderMapData;
            showTaskModel(rwh);
        }else{
            showAlert(resp.message);
        }
    });
}

var showTaskModel = function (rwh) {
    var panels = $("#serviceOrderModal div.card-panel");
    var orderMapData = serviceIndexPage.orderMapData[rwh];
    var taskFieldDatas = orderMapData["SvcTask"];
    if(taskFieldDatas == null){
        showAlert("任务信息不存在,无法查看");
        return;
    }

    var fwlb = getOrderFieldVal(taskFieldDatas,"fwlb");
    var rwzt = getOrderFieldVal(taskFieldDatas,"rwzt");
    console.debug("showTaskModel:rwh="+rwh+",fwlb="+fwlb+",rwzt="+rwzt);
    var rwztTxt = getRwztCnName(rwzt);
    $("#serviceOrderTitle").text(fwlb+"-"+rwh+"-"+rwztTxt);
    $("#mainItemName").text(fwlb);
    taskTabEnabled("SvcCommSyqk",isHasDataItem("Syqk",fwlb));//使用情况
    taskTabEnabled("SvcCommYhyj",isHasDataItem("Yhyj",fwlb));//用户意见

    // taskTabEnabled("SvcCommLj",isHasDataItem("Lj",fwlb));//零件
    // taskTabEnabled("SvcCommDkfx",isHasDataItem("Dkfx",fwlb));//待扣费项

    $.each(panels,function(idx,panel){
        var panelId = $(panel).attr("id");
        var orderFieldDatas = orderMapData[panelId];
        if(orderFieldDatas == null){
            console.warn("没有数据."+panelId);
            $(this).find("i.fa").addClass('fa-times');
        }else{
            var txbz = getOrderFieldVal(orderFieldDatas,"txbz","gzjcTxbz","gzclTxbz");//填写标志
            // console.debug(panelId+"的填写标志="+txbz);
            if(txbz == "1" || txbz == "11" || txbz == null){
                $(this).find("i.fa").removeClass('fa-pencil').addClass('fa-check-circle');
                $(this).attr("title","已填写");
            }else{
                $(this).find("i.fa").removeClass('fa-check-circle').addClass('fa-pencil');
                $(this).attr("title","未填写");
            }
        }
        $(this).off('click').css("cursor","pointer").click(function(){
            var panelName = $(this).find("span").text();
            openTaskDetailModel(rwh,panelId,panelName);
        });
    });
}

var getOrderFieldVal = function(orderFieldDatas,fieldName,fieldName2,fieldName3){
    var val = null;
    var val2 = null, val3 = null;
    $.each(orderFieldDatas,function(idx,field){
        if(field[0] == fieldName ){
            val = field[2];
            return false;
        }
        else if(fieldName2 != null && field[0] == fieldName2){
            val2 = field[2];
        }
        else if(fieldName3 != null && field[0] == fieldName3){
            val3 = field[2];
        }
    });
    if(val == null && val2 != null && val3 != null) return val2 + val3;
    return val;
}

var taskTabEnabled = function (taskTabId,enabled) {
    if(enabled){
        $("#"+taskTabId).removeClass("hide").css("cusror","pointer").find("i.material-icons").html("edit");
    }else{
        $("#"+taskTabId).addClass("hide").css("cursor","default").find("i.material-icons").html("clear");
    }
}

$(function () {
    initBtnEvent();
    initUiData();
})