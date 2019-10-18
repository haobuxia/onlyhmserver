videoActionComparePage.actionCodeNameMap = {
    "autoSpeedDown": "自动降速",
    "swingArmRise": "动臂上升",
    "swingArmFall": "动臂下降",
    "dipperDigger": "斗杆挖掘",
    "dipperDigger2": "斗杆挖掘2",
    "dipperUnload": "斗杆卸载",
    "bucketDigger": "铲斗挖掘",
    "bucketUnload": "铲斗卸载"
};

var initCompareCharts = function () {
    var actionNameArray = [];
    $.each(videoActionComparePage.actionCodeNameMap, function (actionCode, actionName) {
        actionNameArray.push(actionName);
    });

    //逐个视频,分析数据
    var videoIdArray = [];
    $.each(videoActionComparePage.videoList, function (idx, video) {
        videoIdArray.push("视频"+video.id);
        appendCompareVideo(video);
        //1 拿到每个视频8个动作发生的时间,按时间先后顺序来排序
        var timeArray = [];
        var codeTimeMap = {};
        var videoAction = video.videoAction;
        $.each(videoActionComparePage.actionCodeNameMap, function (actionCode, actionName) {
            var actionTime = videoAction[actionCode];
            timeArray.push(actionTime);
            codeTimeMap[actionCode] = actionTime;
        });
        timeArray.sort();//按时间排序
        video.timeArray = timeArray;
        video.codeTimeMap = codeTimeMap;
    });

    console.debug("legend videoIdArray :"+videoIdArray);
    //每个指标1个曲线图，每个曲线图多个曲线，每个曲线对应1个视频。每个曲线横轴有8个值，对应8个动作
    $.each(videoActionComparePage.typeNameIdMap, function (typeName, typeId) {//指标
        var chartSeriesArray = [];
        console.debug("构造指标曲线图:"+typeName+",typeId="+typeId);

        $.each(videoActionComparePage.videoList, function (idx, video) {//视频
            var videoId = video.id;
            console.debug("构造指标曲线图:"+typeName+"，处理视频id:"+videoId);
            var timeArray = video.timeArray;
            var codeTimeMap = video.codeTimeMap;
            var typeValListMap = video.typeValListMap;
            if(typeValListMap == null || typeValListMap[typeId] == null){
                //kmx not enabled
                console.warn("action value is null.may be kmx not enabled ?");
                typeValListMap = {};
                typeValListMap[typeId]=[];
                $.each(timeArray,function(idx,time){
                    typeValListMap[typeId].push(0);//数据不存在,补充假数据
                });
            }

            var valList = typeValListMap[typeId];
            var actionValArray = [];
            $.each(videoActionComparePage.actionCodeNameMap, function (actionCode, actionName) {
                var actionTime = codeTimeMap[actionCode];
                var actionIndex = $.inArray(actionTime,timeArray);
                var val = valList[actionIndex];
                console.debug("构造指标曲线图:"+typeName+"，处理视频id:"+videoId+",处理动作:"+actionName+".发生时间="+actionTime+",在8个动作序列中排位:"+actionIndex+",指标值="+val);
                actionValArray.push(val);
            });

            var videoSeries = {
                name: "视频"+videoId,
                type: 'line',
                data: actionValArray
            };
            chartSeriesArray.push(videoSeries);
        });

        var option = createChartOption(typeName, videoIdArray, actionNameArray,chartSeriesArray);
        createChart(typeId,typeName,option);
    });
};

var createChartOption = function (performenceName, videoIdArray, actionNameArray, seriesArray) {
    //http://echarts.baidu.com/examples/editor.html?c=line-stack
    var option = {
        title: {
            text: performenceName
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: videoIdArray
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {readOnly: true},
                magicType: {type: ['line', 'bar']},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: actionNameArray
        },
        yAxis: {
            type: 'value'
        },
        series: seriesArray
    };

    return option;
}

function createChart(chartTypeId,chartTypeName,chartOption){
    var div = $('<div class="col s12 l6 chart" id="' + chartTypeId + '" data-charttype="line" data-chartname="' + chartTypeName + '" style="cursor:pointer;min-height: 250px;" style="min-height: 250px !important;height: auto;"></div>');
    $("#videoActionCompareSection").append(div);
    var myChart = echarts.init(div[0]);
    myChart.setOption(chartOption);
    console.debug("初始化chart完毕." + chartTypeName);
}

var appendCompareVideo = function (video) {
    var tr = $("<tr data-id='" + video.id + "'><td>" + video.id + "</td><td>" + video.neUserName + "</td><td>" + video.createTimeString + "</td><td>"+video.imei+"</td><td>"+(video.machineCode || "")+"</td></tr>");
    var lastTd = $("<td></td>");
    tr.append(lastTd);
    var imgBtn = $('<i class="material-icons" title="查看预览截图" style="cursor:pointer" >image</i>');
    lastTd.append(imgBtn);
    imgBtn.click(function () {
        $("#playImage").attr("src",videoActionComparePage.fileServer+video.thumbOssPath);
        $('#playImageModal').modal('show');
    });
    var videoBtn = $('<i class="material-icons" title="播放视频" style="cursor:pointer" >videocam</i>');
    lastTd.append(videoBtn);
    videoBtn.click(function () {
        $("#playVideo").attr("src",videoActionComparePage.fileServer+video.ossPath);
        $('#playVideoModal').modal('show');
    });
    $("#compareTableBody").append(tr);
}

function closePlayVideo(){
    $("#playVideo")[0].pause();
    $("#playVideo").removeAttr("src");
    $('#playVideoModal').modal('hide');
}

function closePlayImage(){
    $("#playImage").removeAttr("src");
    $('#playImageModal').modal('hide');
}

$(function () {
    initCompareCharts();
    $("#playVideoCloseBtn").off('click').on('click',closePlayVideo);
    $("#playImageCloseBtn").off('click').on('click',closePlayImage);
})