imeiMapPage.dataType = {};
imeiMapPage.dataType.year="年";
imeiMapPage.dataType.month="月";
imeiMapPage.dataType.day="日";
imeiMapPage.dataType.hour="时";
imeiMapPage.dataType.minute="分";
imeiMapPage.dataType.second="秒";
imeiMapPage.dataType.sequence=['year','month','day','hour','minute','second'];

function initMapData() {
    //初始化地图
    initializeMap(12);
    //初始化数据
    initSubListData('year');
}

function initSubListData(dataType) {
    var typeName = imeiMapPage.dataType[dataType];
    var listDataArea = $("a[data-key='"+dataType+"']");
    listDataArea.empty();
    //显示日期数据
    $.each(imeiMapPage.data.key,function(idx,data){
        var html='<a href="javascript:void(0)" class="blue-text" data-val="'+data+'">'+data+typeName+'</a>';
        listDataArea.append(html);
    });
    if(imeiMapPage.data.key.length > 0){
        listDataArea.removeClass("hide");
    }
    enableDataTypeEvent(dataType);
    showHeatPoints();
}

function showHeatPoints() {
    //显示热力图点
    var points=[];
    $.each(imeiMapPage.data.val,function (idx,location) {
        points.push({"lng":location.one,"lat":location.two,"count":location.three});
    });
    console.debug('开始显示热力图.数量:'+points.length);
    addHeatmapOverlay(points);
}

function enableDataTypeEvent(dataType) {
    var subLinks =  $("a[data-key='"+dataType+"']").find("a");
    subLinks.off('click').click(function () {
        var val = $(this).attr("data-val");
        $(this).addClass("orange");
        var parent = $(this).parent("a");
        parent.attr("data-val",val);
        hideOrRemoveSubTypeData(dataType,val);
        var subDataType = parent.attr("data-subkey");
        if(subDataType){
            loadSubListData(subDataType);
        }
    });
}

function hideOrRemoveSubTypeData(dataType,dataTypeVal){
    $("a[data-key='"+dataType+"']").children(":not(a[data-val='"+dataTypeVal+"'])").remove();
    var foundCurrent = false;
    $.each(imeiMapPage.dataType.sequence,function(idx,seq){
        if(seq == dataType){
            foundCurrent = true;
        }else{
            if(foundCurrent){
                $("a[data-key='"+seq+"']").addClass("hide").empty();
            }
        }
    });
}

function getListDataParams(dataType) {
    var params={};
    $.each(imeiMapPage.dataType.sequence,function(idx,seq){
        if(seq == dataType){
            return false;
        }
        params[seq] = $("a[data-key='"+seq+"']").attr("data-val");
    });
    return params;
}

function loadSubListData(dataType){
    var params = getListDataParams(dataType);
    // console.debug('loadSubListData='+params+",params="+JSON.stringify(params));
    $.post("/imei/data/"+imeiMapPage.clientId,params,function(resp){
        if(resp.success){
            imeiMapPage.data = resp.data;
            initSubListData(dataType);
        }else{
            console.error("载入数据失败."+resp.message);
        }
    },"json");
}

$(function () {
    initMapData();
});
