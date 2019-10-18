var loadDeviceId = function (deviceType) {
    $.get("/kmx/loadDeviceId",{deviceType:deviceType},function (resp) {
        if(resp.success){
            var list = resp.data;
            $("#deviceId").empty();
            $.each(list,function (idx,deviceId) {
                $("#deviceId").append("<option value='"+deviceId+"'>"+deviceId+"</option>");
            });
            // $('select').material_select();
        }else{
            showAlert("载入设备列表失败."+resp.message);
        }
    },'json');
}
var loadSensorId = function (deviceType) {
    $.get("/kmx/loadSensorId",{deviceType:deviceType},function (resp) {
        if(resp.success){
            var list = resp.data;
            $("#sensorId").empty();
            $.each(list,function (idx,sensorId) {
                $("#sensorId").append("<option value='"+sensorId+"'>"+sensorId+"</option>");
            });
            // $('select').material_select();
        }else{
            showAlert("载入传感器列表失败."+resp.message);
        }
    },'json');
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

var searchData = function () {
    var deviceType = $("#deviceType").val();
    var deviceId = $("#deviceId").val();
    var sensorId = $("#sensorId").val();
    var time1 = formatToTime($("#time1").val());
    var time2 = formatToTime($("#time2").val());

    var url = "/kmx/queryData?deviceId="+deviceId+"&sensorId="+sensorId+"&time1="+time1+"&time2="+time2;
    loadContent("#contentSection",url);
}

var insertData = function () {
    var val = prompt("请输入要添加的数据值");
    if(val == undefined || val == ""){
        showAlert("未输入数据,则不保存");
        return;
    }

    var deviceType = $("#deviceType").val();
    var deviceId = $("#deviceId").val();
    var sensorId = $("#sensorId").val();
    var time1 = formatToTime($("#time1").val());
    var time2 = formatToTime($("#time2").val());

    $.post("/kmx/insertData",{
        deviceId:deviceId,
        sensorId:sensorId,
        time1:time1,
        time2:time2,
        value:val
    },function (resp) {
        if(resp.success){
            showAlert('添加成功');
        }else{
            showALert(resp.message);
        }
    },'json');
}

var initBtnEvent = function () {
    // $('select').material_select();
    $("#deviceType").on('change',function () {
        initDeviceSensorList();
    });
    $("#search-button").off('click').click(function () {
        searchData();
    });

    $("#add-button").off('click').click(function () {
        insertData();
    });
}

var initDeviceSensorList = function () {
    var val = $("#deviceType").val();
    console.debug("deviceType = "+val);
    loadDeviceId(val);
    loadSensorId(val);
}

var initUiData = function () {
    var todayTime = new Date().getTime();
    var time1 = new Date();
    var time2 = new Date();
    time1.setTime(todayTime - 1 * 24 * 60 * 60 * 1000);
    time2.setTime(todayTime + 1 * 60 * 60 * 1000);

    $("#time1").val(time1.Format("yyyy-MM-dd hh:mm:ss"));
    $("#time2").val(time2.Format("yyyy-MM-dd hh:mm:ss"));
}

$(function () {
    initBtnEvent();
    initDeviceSensorList();
    initUiData();
});