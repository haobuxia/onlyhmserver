var initStrategyEvent = function () {
    $("#add-button").click(function () {
        $("#editForm input").val("");
        $("#editStrategy_orderNo").val(workorderStrategyPage.orderNo);
        $('#editStrategyModal').modal('show');
    });
    $("#editStrategy-button").click(function () {
        //提交
        submitStrategyEdit();
    });
}

var submitStrategyEdit = function () {
    var strategy = {};
    strategy.orderNo = workorderStrategyPage.orderNo;
    strategy.priority = $.trim($("#editStrategy_priority").val());
    strategy.eventType = $.trim($("#editStrategy_eventType").val());
    strategy.eventVal = $.trim($("#editStrategy_eventVal").val());
    strategy.actionType = $.trim($("#editStrategy_actionType").val());
    strategy.actionVal = $.trim($("#editStrategy_actionVal").val());

    if (strategy.priority == "" ||
        strategy.eventType == "" ||
        // strategy.eventVal == "" ||
        strategy.actionType == ""
    ) {
        showAlert("请把各项数据填写完整");
        return;
    }

    var ret = checkInputStrategy(strategy);
    if(ret != null ){
        showAlert("数据填写有误:"+ret);
        return;
    }

    var url = "/workorderstrategy/add";
    ajaxPost(url, strategy, function (resp) {
        if (resp.success) {
            showAlert("保存成功");
            $('#editStrategyModal').modal('hide');
            loadMainContent("/workorderstrategy/list/"+workorderStrategyPage.orderNo);
        } else {
            showAlert("保存失败." + resp.message);
        }
    })
}


var removeStrategy = function (linkObj) {
    if (workorderStrategyPage.orderState != "INIT") {
        showAlert("工单当前状态不允许工单策略的删除");
        return;
    }

    var tr = $(linkObj).parents("tr[data-id]");
    var id = tr.attr("data-id");
    ajaxPost("/workorderstrategy/delete/" + id, {}, function (resp) {
        if (resp.success) {
            showAlert("删除成功");
            tr.remove();
            // loadMainContent("/workorderstrategy/list/"+workorderStrategyPage.orderNo);
        } else {
            showAlert("删除失败." + resp.message);
        }
    })
}

var checkInputStrategy = function (stg) {
    var evt = stg.eventType;
    var evtVal = stg.eventVal;

    if("TIME_LOOP"==evt){
        evtVal = evtVal.toUpperCase();
        //数字+单位。如3MIN表示3分钟1个循环执行,5HOUR表示5小时循环1次.单位值有:DAY/HOUR/MIN/SEC
        var i1 = evtVal.indexOf("DAY");
        var i2 = evtVal.indexOf("HOUR");
        var i3 = evtVal.indexOf("MIN");
        var i4 = evtVal.indexOf("SEC");

        var found = i1 > 0 || i2 > 0 || i3 >0 || i4 > 0 ;
        if(!found){
            return "触发事件值有误，请填写正确的循环时间数据(数字+单位)";
        }
        var idx = i1 >0 ? i1 :(i2 >0 ? i2 :(i3 > 0 ? i3 : i4));
        var numPart = evtVal.substr(0,idx);
        if(isNaN(numPart) || numPart*1 <= 0){
            return "触发事件值有误，请填写正确的循环数字值";
        }
    }else if("TIME"==evt){
        //TIME对应eventVal的值写法：MM-DD HH:mm或HH:mm，分别表示某月某日某时某分或者某时某分触发。
        if(evtVal.length == 11){
            var M = evtVal.substr(0,2);
            var d = evtVal.substr(3,2);
            var h = evtVal.substr(7,2);
            var m = evtVal.substr(9,2);
            if(M <1 || M > 12){
                return "触发事件值有误，请填写正确的月份";
            }
            if(d <1 || d > 31){
                return "触发事件值有误，请填写正确的日期";
            }
            if(h <1 || h > 24){
                return "触发事件值有误，请填写正确的小时";
            }
            if(m <1 || m > 59){
                return "触发事件值有误，请填写正确的分钟";
            }
        }else if(evtVal.length == 5){
            var h = evtVal.substr(0,2);
            var m = evtVal.substr(3,2);
            if(h <1 || h > 24){
                return "触发事件值有误，请填写正确的小时";
            }
            if(m <1 || m > 59){
                return "触发事件值有误，请填写正确的分钟";
            }
        }else{
            return "触发事件值有误，请填写正确的时间";
        }
    }else if("EVENT"==evt){
        evtVal = evtVal.toUpperCase();
        //EVENT对应eventVal的值写法：KEY1、KEY2、KEY3…表示按了头盔物理按键1,2,3（具体哪个按键是1,2,3或者定义为前后上下中键这种定义可再商量）、SCAN表示扫描了二维码、VIDEO表示拍摄视频结束、IMAGE表示拍摄照片结束、AUDIO表示录音结束、CONNBT表示连接蓝牙成功、DISCONNBT表示断开蓝牙成功
        $("#editStrategy_eventVal").attr("placeholder","请输入物理按键名称或者SCAN/VIDEO/IMAGE/AUDIO/CONNBT/DISCONNBT/CONNWIFI/DISCONNWIFI/CHARGE/DISCHARGE");
        if(evtVal.indexOf("KEY") == 0){
            return null;
        }else if(evtVal == "SCAN" || evtVal == "VIDEO" || evtVal == "IMAGE" || evtVal == "AUDIO" || evtVal == "CONNBT" || evtVal == "DISCONNBT" || evtVal == "CONNWIFI" || evtVal == "DISCONNWIFI" || evtVal == "CHARGE" || evtVal == "DISCHARGE"){
            return null;
        } else{
            return "触发事件值有误，事件名称无法识别";
        }
    }else if("POSITION"==evt){
        //POSITION对应eventVal的值写法：Lng,Lat,R表示经度,纬度,半径(单位米)。头盔定位到达经纬度半径范围内时触发动作执行。或者ORDERPOS这个固定字符串，表示到达工单服务位置半径500米内时触发。
        if("ORDERPOS" == evtVal){
            return null;
        }
        var parts = evtVal.split(",");
        if(parts.length != 3){
            return "触发事件值有误，位置信息书写有误";
        }
        var lng = parts[0];
        var lat = parts[1];
        var r = parts[2];
        if(isNaN(lng) || isNaN(lat) || isNaN(r) ){
            return "触发事件值有误，位置信息经纬度、半径值应该是数字";
        }
        if(lng > 180 || lng < -180){
            return "触发事件值有误，位置信息经度范围超标";
        }
        if(lat > 90 || lat < -90){
            return "触发事件值有误，位置信息纬度范围超标";
        }
    }

    var action = stg.actionType;
    var actionVal = stg.actionVal;
    if("VIDEO"==action || "AUDIO"==action){
        if(isNaN(actionVal)){
            return "执行动作值有误，应数入数字作为时长";
        }
        if(actionVal * 1 < 0){
            return "执行动作值有误，时长必须为正数";
        }
    }else if("LINK"==action){
        actionVal = actionVal.toLowerCase();
        if(actionVal != "" && !(actionVal.indexOf("http://") == 0 || actionVal.indexOf("https://") == 0  )){
            return "执行动作值有误，连接请以http(s)://开头";
        }
    }
}

var eventTypeChanged = function () {
    var evt = $("#editStrategy_eventType").val();
    if("TIME_LOOP"==evt){
        //数字+单位。如3MIN表示3分钟1个循环执行,5HOUR表示5小时循环1次.单位值有:DAY/HOUR/MIN/SEC
        $("#editStrategy_eventVal").attr("placeholder","请输入数字+单位（DAY/HOUR/MIN/SEC）");
    }else if("TIME"==evt){
        //TIME对应eventVal的值写法：MM-DD HH:mm或HH:mm，分别表示某月某日某时某分或者某时某分触发。
        $("#editStrategy_eventVal").attr("placeholder","请输入时间MM-DD HH:mm或HH:mm");
    }else if("EVENT"==evt){
        //EVENT对应eventVal的值写法：KEY1、KEY2、KEY3…表示按了头盔物理按键1,2,3（具体哪个按键是1,2,3或者定义为前后上下中键这种定义可再商量）、SCAN表示扫描了二维码、VIDEO表示拍摄视频结束、IMAGE表示拍摄照片结束、AUDIO表示录音结束、CONNBT表示连接蓝牙成功、DISCONNBT表示断开蓝牙成功
        $("#editStrategy_eventVal").attr("placeholder","请输入物理按键名称或者SCAN/VDEO/IMAGE/AUDIO/CONNBT/DISCONNBT");
    }else if("POSITION"==evt){
        //POSITION对应eventVal的值写法：Lng,Lat,R表示经度,纬度,半径(单位米)。头盔定位到达经纬度半径范围内时触发动作执行。或者ORDERPOS这个固定字符串，表示到达工单服务位置半径500米内时触发。
        $("#editStrategy_eventVal").attr("placeholder","请输入Lng,Lat,R值或'ORDERPOS'表示工单所在位置");
    }
}

var actionTypeChanged = function () {
    var action = $("#editStrategy_actionType").val();
    if("VIDEO"==action || "AUDIO"==action){
        $("#editStrategy_actionVal").attr("placeholder","请输入录制时长(单位秒)").prop("readonly",false);
    }else if("LINK"==action){
        $("#editStrategy_actionVal").attr("placeholder","请输入网址").prop("readonly",false);
    } else {
        $("#editStrategy_actionVal").prop("readonly",true);
    }
}

$(function () {
    initStrategyEvent();
    eventTypeChanged();
    actionTypeChanged();
})