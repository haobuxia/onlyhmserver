var createHelmetWatchChartIfNeeded = function () {
    if (helmet3And1Page.helmetChart) {
        updateWatchChartData(0,0,0);
        // setTimeout(function(){
        //     helmet3And1Page.helmetChart.resize();
        // },300);
        return;
    }

// echarts start
    helmet3And1Page.helmetChart = echarts.init(document.getElementById('oneHelmetChart'));
    helmet3And1Page.helmetChartOption = {
        backgroundColor: '#1b1b1b',
        tooltip: {
            formatter: "{a} <br/>{c} {b}"
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '脑波专注度',
                type: 'gauge',
                radius : '70%',
                min: 0,
                max: 100,
                splitNumber: 10,
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.09, '#ff4500'], [0.82, 'lime'], [1, '#1e90ff']],
                        width: 3,
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisLabel: {            // 坐标轴小标记
                    show: false,
                    textStyle: {       // 属性lineStyle控制线条样式
                        fontWeight: 'bolder',
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 10,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                splitLine: {           // 分隔线
                    length: 15,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        width: 3,
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                pointer: {           // 分隔线
                    shadowColor: '#fff', //默认透明
                    shadowBlur: 5
                },
                title: {
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder',
                        fontSize: 20,
                        fontStyle: 'italic',
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                detail: {
                    backgroundColor: 'rgba(30,144,255,0.8)',
                    borderWidth: 1,
                    borderColor: '#fff',
                    shadowColor: '#fff', //默认透明
                    shadowBlur: 5,
                    offsetCenter: [0, '50%'],       // x, y，单位px
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder',
                        color: '#fff'
                    }
                },
                data: [{value: 40, name: '脑波专注度'}]
            },
            {
                name: '电量',
                type: 'gauge',
                center: ['20%', '55%'],    // 默认全局居中
                radius: '70%',
                min: 0,
                max: 100,
                // endAngle: 45,
                splitNumber: 10,
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.2, '#ff4500'], [0.82, 'lime'], [1, '#1e90ff']],
                        width: 2,
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisLabel: {            // 坐标轴小标记
                    show: false,
                    textStyle: {       // 属性lineStyle控制线条样式
                        fontWeight: 'bolder',
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 10,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                splitLine: {           // 分隔线
                    length: 15,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        width: 3,
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                pointer: {
                    width: 5,
                    shadowColor: '#fff', //默认透明
                    shadowBlur: 5
                },
                title: {
                    offsetCenter: [0, '-30%'],       // x, y，单位px
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder',
                        fontStyle: 'italic',
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                detail : {
                    backgroundColor: 'rgba(30,144,255,0.8)',
                    borderWidth: 1,
                    borderColor: '#fff',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 5,
                    offsetCenter: [0, '50%'],       // x, y，单位px
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder',
                        color: '#fff'
                    }
                },
                data: [{value: 10, name: '电量'}]
            },
            {
                name: '脑波放松度',
                type: 'gauge',
                center: ['80%', '50%'],    // 默认全局居中
                radius: '70%',
                min: 0,
                max: 100,
                splitNumber: 10,
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.09, '#ff4500'], [0.82, 'lime'], [1, '#1e90ff']],
                        width: 2,
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisLabel: {            // 坐标轴小标记
                    show: false,
                    textStyle: {       // 属性lineStyle控制线条样式
                        fontWeight: 'bolder',
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 10,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                splitLine: {           // 分隔线
                    length: 15,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        width: 3,
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                pointer: {
                    width: 5,
                    shadowColor: '#fff', //默认透明
                    shadowBlur: 5
                },
                title: {
                    offsetCenter: [0, '-30%'],       // x, y，单位px
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder',
                        fontStyle: 'italic',
                        color: '#fff',
                        shadowColor: '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
               detail : {
                    backgroundColor: 'rgba(30,144,255,0.8)',
                    borderWidth: 1,
                    borderColor: '#fff',
                    shadowColor : '#fff', //默认透明
                    shadowBlur: 5,
                    offsetCenter: [0, '50%'],       // x, y，单位px
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder',
                        color: '#fff'
                    }
                },
                data: [{value: 90, name: '脑波放松度'}]
            }
        ]
    };
    helmet3And1Page.helmetChart.setOption(helmet3And1Page.helmetChartOption, true);

    // echart表 随着页面大小改变动态自适应
    window.addEventListener("resize", function () {
        helmet3And1Page.helmetChart.resize();
    });

    helmet3And1Page.helmetWatchChartInited = true;
    updateWatchChartData(0,0,0);
// ECharts end-->
}

function createHelmetHistoryChartIfNeeded() {
    if (helmet3And1Page.historyChart) {
        return;
    }

    helmet3And1Page.sensorDataKeys = ["concert", "relax", "xa", "ya", "za", "xg", "yg", "zg", "battery", "runtime"];
    helmet3And1Page.sensorDataNames = ['专注度', '放松度', '角度x', '角度y', '角度z', '加速度x', '加速度y', '加速度z', '电量', '运行时间'];

    var series = [];
    $.each(helmet3And1Page.sensorDataNames, function (idx, name) {
        var serie = {
            name: name,
            type: 'line',
            data: []
        };
        series.push(serie);
    });

    var option = {
        title: {
            text: '传感器历史数据变化图'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: helmet3And1Page.sensorDataNames,
            selected: {
                '角度x': false,
                '角度y': false,
                '角度z': false,
                '加速度x': false,
                '加速度y': false,
                '加速度z': false,
                '运行时间': false
            }
        },
        grid: {
            left: '1%',
            right: '1%',
            bottom: '4%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            // type: 'time',
            type: 'category',
            splitLine: {
                show: false
            },
            data: []
        },
        yAxis: {
            type: 'value',
            min: 'dataMin',
            max: 'dataMax',
            splitLine: {
                show: false
            }
        },
        series: series
    };

    helmet3And1Page.historyChart = echarts.init($("#historyChart")[0]);
    helmet3And1Page.historyChart.setOption(option);

    window.addEventListener('resize', function () {
        window.setTimeout(function () {
            helmet3And1Page.historyChart.resize();
        }, 300);
    }, false);
}

//更新仪表盘chart的数值
function updateWatchChartData(battery, concert, relax) {
    ajaxGet("/device/helmet/getLatestBattery?clientId="+helmet3And1Page.currentHelmet.clientId, function(resp) {
        if (resp.code==200) {
            battery = resp.data.battery;
            // echart 动态更新数据
            battery = battery ||0;
            concert = concert ||0;
            relax = relax ||0;

            var option = helmet3And1Page.helmetChartOption;
            //专注、电量、放松
            option.series[0].data[0].value = concert.toFixed(2) - 0;
            option.series[1].data[0].value = battery.toFixed(2) - 0;
            option.series[2].data[0].value = relax.toFixed(2) - 0;
            helmet3And1Page.helmetChart.setOption(option, true);
            helmet3And1Page.helmetChart.resize();
        }
    });
}
//更新仪表盘battery chart的数值
function updateWatchChartBatteryData(battery) {
    // echart 动态更新数据
    battery = battery ||0;
    var option = helmet3And1Page.helmetChartOption;
    //专注、电量、放松
    option.series[1].data[0].value = battery.toFixed(2) - 0;
    helmet3And1Page.helmetChart.setOption(option, true);
}
//更新仪表盘battery chart的数值
function updateWatchChartMindWaverData(concert, relax) {
    // echart 动态更新数据
    concert = concert ||0;
    relax = relax ||0;
    var option = helmet3And1Page.helmetChartOption;
    //专注、电量、放松
    option.series[0].data[0].value = concert.toFixed(2) - 0;
    option.series[2].data[0].value = relax.toFixed(2) - 0;
    helmet3And1Page.helmetChart.setOption(option, true);
}
//显示某个头盔的界面
function viewHelmet(linkObj) {
    //清理就得头盔websocket监控
    if(helmet3And1Page.currentHelmet != null){
        console.debug("切换查看头盔,先关闭旧头盔的websocket监控."+JSON.stringify(helmet3And1Page.currentHelmet));
        closeCurrentHelmetWebsocket();
    }else{
        //隐藏所有头盔的地图定位显示区域
        $("#allHelmetMapArea").addClass("hidden");
    }

    //处理当前头盔
    var parentLi = $(linkObj).parents("li[data-clientId]");
    var clientId = parentLi.attr("data-clientId");
    var neUserName = parentLi.attr("data-name");
    var userName = parentLi.attr("data-uname");
    var gpsArr = parentLi.attr("data-gps").split(",");
    helmet3And1Page.currentHelmet = {
        clientId: clientId,
        neUsername: neUserName,
        userName: userName,
        online: isHelmetInOnlineList(clientId),
        gps: {
            lon: gpsArr[0],
            lat: gpsArr[1],
            createTimeString: gpsArr[2]
        }
    };
    //由于天远地图在1个页面只能有1个实例,所以要把全部头盔定位的地图移除
    $("#mapContainer").empty();
    $("#allHelmetMapDiv").empty();
    console.debug("移除全部头盔地图组件完毕.mapContainer.length = "+$("#mapContainer").length);
    //显示当前头盔
    $("#oneHelmetName").text(userName);
    //显示当前头盔数据区域
    $("#oneHelmetInfoArea").removeClass("hidden");

    //启动头盔实时数据websocket
    createCurrentHelmetWebsocket();
    //头盔的仪表chart初始化
    createHelmetWatchChartIfNeeded();
    //显示头盔tab页签数据
    loadCurrentHelmetDefaultTabData();
}

//显示所有头盔的数据以及地图定位
function initAllClientMarkers() {
    var list = helmet3And1Page.clientList;
    if (list == null || list.length == 0) {
        console.debug('没有设备数据');
        $("#onlineCount").text(0);
        $("#offlineCount").text(0);
        $("#onlineHelmets").empty();
        $("#offlineHelmets").empty();
        return;
    }

    $.each(list, function (idx, serverClient) {
        var gps = serverClient.four;
        if (gps == null) {
            gps = {"clientId": serverClient.one, "lon": 0, "lat": 0, createTimeString: '无定位'};
            serverClient.four = gps;
        }

        var helmetClient = {};
        helmetClient.clientId = serverClient.one;
        var names = serverClient.two.split("-");
        if(names.length>1){
            helmetClient.userName = names[0]+"-"+names[1];
        } else {
            helmetClient.userName = names[0];
        }
        if(names.length>2){
            helmetClient.neUsername = names[2];
        }
        helmetClient.online = false;//online 初始全设为离线
        helmetClient.gps = serverClient.four;//gps

        showHelmetInMap(helmetClient);
        createOrUpdateCollectionItem(helmetClient);
    });

    var onlineCount  = $("#onlineCount").text()*1;
    if(onlineCount == 0 ){
        //没有在线设备,则隐藏
        $("#onlineHelmets").parents("div.slimScrollDiv").css("display","none");
        $("#offlineHelmets").parents("div.slimScrollDiv").css("display","block");
    }
}

//显示头盔在地图的定位
function showHelmetInMap(helmetClient) {
    if (helmetClient == null) {
        return;
    }
    var gps = helmetClient.gps;
    var labelContent = (helmetClient.online ? "在线" : "离线") + " " + helmetClient.userName + " " + gps.createTimeString;
    var iconImage = "/static/images/worker/" + (helmetClient.online ? "yellow-m" : "white-m") + ".png";
    setMapPointMarker(gps.lon*1, gps.lat*1, "helmet_" + helmetClient.clientId, labelContent, iconImage, null, false);
}

//判断某个头盔当前是否现在是在在线列表里
function isHelmetInOnlineList(clientId) {
    var li = $("ul.helmet-list li[data-clientid='" + clientId + "']");
    if (li.length == 0) {
        return false;
    }
    var parentUl = li.parent("ul");
    var parentUlId = parentUl.attr("id");
    return "onlineHelmets" == parentUlId;
}

//显示头盔信息在左侧导航条里
function createOrUpdateCollectionItem(helmetClient) {
    var clientId = helmetClient.clientId;
    var online = helmetClient.online;
    var li = $("ul.helmet-list li[data-clientid='" + clientId + "']");
    if (li.length == 0) {
        //新创建的头库项目则初始化最后在线时间
        helmet3And1Page.helmetOnlineTimeMap[clientId] = online ? new Date().getTime() : new Date().getTime() - 60 * 60 * 1000;

        li = createCollectionItem(helmetClient);
        var ulId = online ? "onlineHelmets" : "offlineHelmets";
        $("#" + ulId).append(li);
        updateOnlineItemState(li,online,false,"(在线)");
        updateOnlineCount(online, 1);
    } else {
        helmet3And1Page.helmetOnlineTimeMap[clientId] = new Date().getTime();
        updateOnlineItemState(li,online,true,helmetClient.onlineType);
        //updateOnlineItemState(li,online,true,"(在线)");
    }
}

//在线状态的头盔项目的状态修改
function updateOnlineItemState(li,online,updateCount,onlineType) {
    var alink = li.find("a:first");
    var neUserName = li.attr("data-name");
    var clientId = li.attr("data-clientId");
    if (online){
        alink.removeClass("text-muted");
        var exists = alink.siblings("span.fa-video-camera").length > 0 ;
        if(!exists){
            console.debug("设置头盔在线."+neUserName);
            var stateType = $('<lable id="label-' + clientId + '">' + onlineType + '</lable>');
            // 切换新的音视频平台20190705
            var cameraSpan = $('<span class="fa fa-video-camera pull-right" title="点击发起视频"></span>');
            // cameraSpan.click(function(){
            //     talkToHelmet(neUserName);
            // });
            alink.after(stateType);
            alink.after(cameraSpan);
        }
        else{
            $("#label-"+clientId).html(onlineType);
        }
    }else{
        console.debug("设置头盔下线."+neUserName);
        alink.addClass("text-muted");
        alink.siblings().remove();//移除camera按钮
    }

    var oldOnlineStatus = isHelmetInOnlineList(clientId);
    if (oldOnlineStatus) {
        if (online) {
            //在线状态未变：在线
        } else {
            //在线变离线
            $("#offlineHelmets").append(li);
            if(updateCount){
                updateOnlineCount(false, 1);
                updateOnlineCount(true, -1);
            }
        }
    } else {//以前离线
        if (online) {//离线变在线
            $("#onlineHelmets").append(li);
            if(updateCount) {
                updateOnlineCount(false, -1);
                updateOnlineCount(true, 1);
            }
        } else {
            //在线状态未变：离线
        }
    }
}

function createCollectionItem(helmetClient){
    var clientId = helmetClient.clientId;
    var neUserName = helmetClient.neUsername;
    var userName = helmetClient.userName;
    var online = helmetClient.online;
    var gps = helmetClient.gps;
    var li = $("<li data-gps='" + (gps.lon + ',' + gps.lat + ',' + gps.createTimeString) + "' data-clientId='" + clientId + "' data-name='" + neUserName + "' data-uname='" + userName + "' data-online='" + online + "'>" +
        // "<i alt='Avatar' class='img-circle pull-left avatar fa fa-helmet'></i>" +
        "<p><a onclick='viewHelmet(this)' style='cursor:pointer' title='点击查看设备详情'>" + userName + "</a> "+
        // "<span class='timestamp'>石家庄</span>" +
        // "<i class='lnr lnr-cross' title='去除地图定位'></i>" +
        "</p></li>");
    return li;
}

//更新头盔在线、离线数量
function updateOnlineCount(online, cnt) {
    if (online) {
        var oldVal = $("#onlineCount").text();
        $("#onlineCount").text(oldVal * 1 + cnt);
    } else {
        var oldVal = $("#offlineCount").text();
        $("#offlineCount").text(oldVal * 1 + cnt);
    }
}

function updateCurrentHelmetTime(time){
    $("#oneHelmetTime").text("设备时间:"+time);
}

//为当前查看的头盔创建socket
function createCurrentHelmetWebsocket(){
    var clientId = helmet3And1Page.currentHelmet.clientId;
    ajaxGet("/device/gettoken?clientId="+clientId,function(resp){
       if(resp.success){
           var token = resp.data;
           var oneHelmetWebSocketQueryString = "?token="+token+"&dataType=gps_sensor_heart_battery_mind";
           console.debug("当前头盔websocket的token="+token+",websocket创建url="+oneHelmetWebSocketQueryString);
           helmet3And1Page.one_websocket = new HelmetWebSocket(clientId,function(dataText){
               console.debug("收到当前头盔实时传感器数据："+dataText);
               var dataObj = JSON.parse(dataText);
               updateCurrentHelmetTime(dataObj.createTimeString);
               setCurrentHelmetOnline(true);
               if(dataObj.dataType == 'gps'){
                   var helmetClient = {};
                   helmetClient.clientId = dataObj.clientId;
                   var names = dataObj.neUsername.split("-");
                   if(names.length>1){
                       helmetClient.userName = names[0]+"-"+names[1];
                   } else {
                       helmetClient.userName = names[0];
                   }
                   if(names.length>2){
                       helmetClient.neUsername = names[2];
                   }
                   helmetClient.online = true;//online
                   helmetClient.gps = dataObj;//gps
                   showHelmetInMap(helmetClient);
                   createOrUpdateCollectionItem(helmetClient);
               } else if(dataObj.dataType == 'sensor'){
                   helmetModel.updateHelmetGravity(dataObj.xg,dataObj.yg,dataObj.zg);
               } else if(dataObj.dataType == 'battery'){
                   updateWatchChartBatteryData(dataObj.battery);
               } else if(dataObj.dataType == 'mind') {
                   updateWatchChartMindWaverData(dataObj.concert,dataObj.relax);
               }
           },oneHelmetWebSocketQueryString);

           helmet3And1Page.currentHelmetOnlineInterval = window.setInterval(checkCurrentHelmetOnline,60*1000);//60秒检测一次在线状态
           addMainContentUnloadListener(closeCurrentHelmetWebsocket);
       } else{
           showAlert("获取token失败，暂时无法查看该头盔实时数据");
       }
    });
}

//关闭当前头盔的socket
function closeCurrentHelmetWebsocket(){
    if(helmet3And1Page.one_websocket)
        helmet3And1Page.one_websocket.closeWebSocket();
    window.clearInterval(helmet3And1Page.currentHelmetOnlineInterval);
}

//定时检查列表里所有头盔的在线状态
function checkAllHelmetOnline(){
    $.each(helmet3And1Page.helmetOnlineTimeMap,function(clientId,lastOnlineTime){
        var itemLi = $("li[data-clientId='"+clientId+"']");
        var offlineTime = new Date().getTime() - lastOnlineTime;
        if(offlineTime > 10*1000){
            //120秒未在线了则认为已离线
            if(offlineTime > 120 * 2 * 1000){
                //上一轮检测一经发现离线了,此处就不再设置了
            }else{
                console.debug('列表中的头盔 '+clientId+' 120s未在线了，认为已离线');
                updateOnlineItemState(itemLi,false,true,"(在线)");
            }
        }else{
            //updateOnlineItemState(itemLi,true,true,"(在线)");
        }
    });
}

//检查当前头盔是否还在线
function checkCurrentHelmetOnline() {
    var time = helmet3And1Page.helmetOnlineTimeMap[helmet3And1Page.currentHelmet.clientId];
    var lastOnlineTime = time||new Date().getTime();
    if(new Date().getTime() - lastOnlineTime > 60*1000){
        //60秒未在线了则认为已离线
        console.debug('当前头盔60s未在线了，则认为已离线');
        setCurrentHelmetOnline(false);
    }
}

//设置当前头盔在线或离线状态
function setCurrentHelmetOnline(online){
    if(online){
        helmet3And1Page.helmetOnlineTimeMap[helmet3And1Page.currentHelmet.clientId] = new Date().getTime();
        helmet3And1Page.currentHelmet.online = true;//online
        // updateCurrentHelmetChatButton(true);
    }else{
        helmet3And1Page.currentHelmet.online = false;
        // window.clearInterval(helmet3And1Page.currentHelmetOnlineInterval);//当前头盔下线了，还是要继续监测，因为可能会再次上线
        // updateCurrentHelmetChatButton(false);
    }
    //更新左侧列表里的在线状态。按道理不需要此处更新，allHelmetWebSocket里会自动更新
    // var li = $("li[data-clientId='"+helmet3And1Page.currentHelmet.clientId+"']");
    // updateOnlineItemState(online);
}

//为所有头盔创建socket
function createAllHelmetWebSocket() {
    helmet3And1Page.all_websocket = new HelmetWebSocket("all",function(dataText){
        console.debug(new Date()+"收到全部头盔实时传感器数据："+dataText);
        var dataObj = JSON.parse(dataText);
        // if (dataObj.dataType == 'gps') {
        //     var helmetClient = {};
        //     helmetClient.clientId = dataObj.clientId;
        //     helmetClient.neUsername = dataObj.neUsername;
        //     helmetClient.online = true;//online
        //     helmetClient.gps = dataObj;//gps
        //     createOrUpdateCollectionItem(helmetClient);
        //
        //     if(helmet3And1Page.currentHelmet == null){
        //         //当前还是所有头盔地图,则显示定位
        //         console.debug('当前没有具体头盔定位，则在地图显示所有头盔定位.clientId='+helmetClient.clientId+",neUsername="+helmetClient.neUsername);
        //         showHelmetInMap(helmetClient);
        //     }else{
        //         console.debug('当前已经有具体头盔定位，则不在地图显示所有头盔定位.clientId='+helmetClient.clientId+",neUsername="+helmetClient.neUsername);
        //     }
        // }
        if (dataObj.dataType == 'heart') {
           var helmetClient = {};
           helmetClient.clientId = dataObj.clientId;
           helmetClient.neUsername = dataObj.neUsername;
           helmetClient.online = true;
           helmetClient.onlineType = dataObj.onlineType;
            if (dataObj.onlineType == 0) {
                helmetClient.onlineType = "(在线)";
            }
            else if (dataObj.onlineType == 1) {
                helmetClient.onlineType = "(正在视频通话)";
            }
            else if (dataObj.onlineType == 2) {
                helmetClient.onlineType = "(正在拍报)";
            }
            else if (dataObj.onlineType == 3) {
                helmetClient.onlineType = "(正在查看工单)";
            }
            else if (dataObj.onlineType == 4) {
                helmetClient.onlineType = "(已黑屏待机)";
            }
            else {
                helmetClient.onlineType = "(离线)";
            }
            console.debug(helmetClient.onlineType);

            createOrUpdateCollectionItem(helmetClient);

        }
    },helmet3And1Page.allHelmetWebSocketQueryString);
    helmet3And1Page.allHelmetOnlineInterval = window.setInterval(checkAllHelmetOnline,120*1000);//120秒检测一次在线状态
    addMainContentUnloadListener(closeAllHelmetWebsocket);
}
//关闭当前头盔的socket
function closeAllHelmetWebsocket(){
    if(helmet3And1Page.all_websocket){
        console.debug("关闭所有头盔在线状态的监控websocket.clientIdList="+helmet3And1Page.all_websocket.clientId+",wsUrl="+helmet3And1Page.all_websocket.wsFullUrl);
        helmet3And1Page.all_websocket.closeWebSocket();
    }
    window.clearInterval(helmet3And1Page.allHelmetOnlineInterval);
}

//当前头盔显示在单个头盔的地图上
function showCurrentHelmetInMap() {
    var mapModelArea = $("#nav-tabs-body>div[data-category='map-model']");
    var existMapContainer = $("#mapContainer").length > 0;
    if (!existMapContainer) {
        console.debug("单个头盔地图组件不存在，新建1个.");
        mapModelArea.prepend('<div class="col-md-6 col-xs-6" id="mapContainer" style="min-height: 400px;max-height: 400px;"></div>');
        initializeMap(12, true);
    }else{
        //地图已经初始化过,则可能存在别的头盔的marker
    }
    showHelmetInMap(helmet3And1Page.currentHelmet);
}

//显示当前头盔的3d模型
function showCurrentHelmetModel() {
    helmetModel.setupModel("helmetModelArea", false);
}

//显示当前头盔的聊天界面
// function showCurrentHelmetChat() {
//     if (helmet3And1Page.currentHelmet == null) {
//         return;
//     }
//     updateCurrentHelmetChatButton(helmet3And1Page.currentHelmet.online);
// }

//显示当前头盔的历史传感器数据界面
function showCurrentHelmetHistory() {
    createHelmetHistoryChartIfNeeded();
    createHistorySensorSliderIfNeeded();
    loadHistoryDefaultSensorData();
}

//时间slider编辑器
var slideFromTimestamp = wNumb({
    edit:function (a) {
        var ret = new Date(1*a).Format("yyyy-MM-dd hh:mm");
        return ret;
    }
});

//创建历史数据查询滑动条
function createHistorySensorSliderIfNeeded() {
    if(helmet3And1Page.dateSlider){
        return;
    }

    helmet3And1Page.sliderStep = 24 * 60 * 60 * 1000,// 1天
    helmet3And1Page.slideEndTime = new Date().getTime();
    helmet3And1Page.slideStartTime = helmet3And1Page.slideEndTime - helmet3And1Page.sliderStep * 1;//默认查询1天前到今天

    helmet3And1Page.dateSlider = document.getElementById('sensor-slider');
    noUiSlider.create(helmet3And1Page.dateSlider, {
        range: {//范围
            min: new Date("2017-10-01").getTime(),//@todo
            max: new Date().getTime(),
        },
        tooltips: [slideFromTimestamp, slideFromTimestamp],
        step: helmet3And1Page.sliderStep,
        start: [helmet3And1Page.slideStartTime, helmet3And1Page.slideEndTime],//默认
        format: wNumb({
            decimals: 0
        })
    });

    //滑动事件
    helmet3And1Page.dateSlider.noUiSlider.on('update', function (values, idx) {
        console.debug('开始时间变化：' + idx + ".values = " + values.join(","));
        var reload = false;
        if (values[0] != helmet3And1Page.slideStartTime) {
            helmet3And1Page.slideStartTime = values[0];
            reload = true;
        }
        if (values[1] != helmet3And1Page.slideEndTime) {
            helmet3And1Page.slideEndTime = values[1];
            reload = true;
        }
        if (reload) {
            if (helmet3And1Page.loadSlideDataId > 0) {
                console.debug('上次载入slide数据还在排序.则清理掉。helmet3And1Page.loadSlideDataId = ' + helmet3And1Page.loadSlideDataId);
                window.clearTimeout(helmet3And1Page.loadSlideDataId);
                helmet3And1Page.loadSlideDataId = -1;
                console.debug("清理后.helmet3And1Page.loadSlideDataId=" + helmet3And1Page.loadSlideDataId);
            }

            helmet3And1Page.historyChart.showLoading();
            helmet3And1Page.loadSlideDataId = window.setTimeout('loadHistoryRangeSensorData()', 2000);//延迟2秒加载
            console.debug('加入执行计划:' + helmet3And1Page.loadSlideDataId);
        }
    });
}

//载入头盔历史传感器数据
function loadHistoryDefaultSensorData() {
    helmet3And1Page.slideEndTime = new Date().getTime();
    helmet3And1Page.slideStartTime = helmet3And1Page.slideEndTime - helmet3And1Page.sliderStep * 1;//默认查询1天前到今天
    helmet3And1Page.historyChart.showLoading();
    loadHistoryRangeSensorData();
}

//载入头盔历史传感器数据
function loadHistoryRangeSensorData() {
    // if(true) return;
    console.debug('loadRangeSensorData=' + helmet3And1Page.slideStartTime + "-->" + helmet3And1Page.slideEndTime);
    var url = "/device/refresh/range/" + helmet3And1Page.currentHelmet.clientId;
    $.get(url, {
        date1: helmet3And1Page.slideStartTime,
        date2: helmet3And1Page.slideEndTime
    }, function (resp) {
        if (resp.success) {
            updateHelmetHistoryChart(resp.data);
        } else {
            console.debug('loadHistoryRangeSensorData resp fail.');
            helmet3And1Page.historyChart.hideLoading();
        }
    });
}
//更新头盔历史传感器数据chart
function updateHelmetHistoryChart(sensorList) {
    console.debug('传感器历史数据记录数：' + sensorList.length);
    var seriesDataObject = {};
    $.each(helmet3And1Page.sensorDataKeys, function (idx, key) {
        seriesDataObject[key] = [];
    });
    var xAxisData = [];
    $.each(sensorList, function (idx, sensorData) {
        var time = sensorData.createTimeString;
        xAxisData.push(time);

        $.each(helmet3And1Page.sensorDataKeys, function (idx, key) {
            seriesDataObject[key].push(sensorData[key] || '-');
        });
    });
    var chartSeries = [];
    $.each(helmet3And1Page.sensorDataKeys, function (idx, key) {
        chartSeries.push({
            data: seriesDataObject[key]
        });
    });

    helmet3And1Page.historyChart.setOption({
        xAxis: {
            data: xAxisData
        },
        series: chartSeries
    });
    helmet3And1Page.historyChart.hideLoading();
}

//显示头盔录制的媒体资源
function showCurrentHelmetMedia() {
    console.debug("显示当前头盔的资源");
    var page = 1;
    $.each(["video","image"
        // ,"audio","file"
    ],function(idx,type){
        loadData(page,type);
    });
}

//载入某个类型的媒体资源
function loadData(page,type) {
    var url = "/list/"+type+"/list/"+helmet3And1Page.currentHelmet.clientId+"/"+page;
    loadContent("#"+type+"Section",url);
}

//更新头盔聊天按钮的文字和状态
// function updateCurrentHelmetChatButton(online) {
//     var chatArea = $("#nav-tabs-body>div[data-category='chat']");
//     var chatBtn = chatArea.find("button");
//     var oldStatus = chatBtn.attr("data-online");
//     if (oldStatus == online)
//         return;
//
//     if (online)
//         chatBtn.text("视频聊天").off('click').click(function () {
//             talkToHelmet(helmet3And1Page.currentHelmet.neUsername);
//         });
//     else
//         chatBtn.text("不在线,无法视频").off('click');
// }

//与头盔发起聊天
function talkToHelmet(helmetNeUsername) {
    window.open("/im/index?accid=" + helmetNeUsername, "imChatWindow");
}

//载入当前头盔的默认的页签数据
function loadCurrentHelmetDefaultTabData() {
    // helmet3And1Page.helmetChart.resize();
    var dataCategory = $("#nav-tabs>li.active").attr("data-category");
    loadCurrentHelmetTabData(dataCategory);
}
//载入当前头盔的某个页签数据
function loadCurrentHelmetTabData(dataCategory) {
    console.debug("载入头盔页签数据."+dataCategory);
    $("#nav-tabs-body>div[data-category='"+dataCategory+"']").removeClass("hidden").siblings('div').addClass('hidden');
    if ("map-model" == dataCategory) {
        showCurrentHelmetInMap();
        showCurrentHelmetModel();
    // } else if ("chat" == dataCategory) {
    //     showCurrentHelmetChat();
    } else if ("history" == dataCategory) {
        //载入历史传感器数据
        showCurrentHelmetHistory();
    } else if ("media" == dataCategory) {
        //载入这个头盔拍摄的资料
        showCurrentHelmetMedia();
    } else if ("now" == dataCategory) {
        helmet3And1Page.helmetChart.resize();
    }
}

function doSearchHelmet(){
    var helmetSearchKeyword = $.trim($("#helmetSearchKeyword").val());
    if(helmetSearchKeyword == ""){
        return;
    }

    var found = false;
    var searchHelmetsDiv = $("#searchedHelmets");
    searchHelmetsDiv.empty();
    $.each(helmet3And1Page.clientList, function (idx, serverClient) {
        var neUsername = serverClient.two;
        if(neUsername.indexOf(helmetSearchKeyword) != -1){
            found = true;
            var helmetClient = {};
            helmetClient.clientId = serverClient.one;
            var names = serverClient.two.split("-");
            if(names.length>1){
                helmetClient.userName = names[0]+"-"+names[1];
            } else {
                helmetClient.userName = names[0];
            }
            if(names.length>2){
                helmetClient.neUsername = names[2];
            }
            helmetClient.online = serverClient.three;//online
            helmetClient.gps = serverClient.four;//gps
            var li = createCollectionItem(helmetClient);
            searchHelmetsDiv.append(li);
            updateOnlineItemState(li,helmetClient.online,true);
            $("#helmetSearchCount").text("搜索到 1 个结果");
            return false;
        }
    });

    if(!found){
        $("#helmetSearchCount").text("搜索到 0 个结果");
    }
}


$(function () {
    // 具体头盔各项数据选项卡 start
    $("#nav-tabs>li>a").on("click", function () {
        var _li = $(this).parent("li");
        if (_li.hasClass("active")) {
            //已经是激活状态,则点击不理会
            console.debug('头盔具体页签已经是激活状态，则不再处理事件。');
            return;
        }

        _li.addClass('active').siblings('li').removeClass('active');
        loadCurrentHelmetDefaultTabData();
    });

    //初始化地图
    initializeMap(10, true);
    helmet3And1Page.helmetOnlineTimeMap = {};
    //初始化各个设备的定位
    initAllClientMarkers();
    createAllHelmetWebSocket();
});