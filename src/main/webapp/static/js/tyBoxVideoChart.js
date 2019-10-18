/**
 *  视频的工况曲线
 */

var chartMap = {};
var gaugeMap = {};
var chartDataMap = {};
var referenceValueList = {};//图表参考值

var playChartData = function () {
    var currTime = videoPage.video.currentTime;//视频当前播放到的时刻,秒
    if (currTime < videoPage.playStartSecond) {
        currTime = videoPage.playStartSecond;
        videoPage.video.currentTime = currTime;
    }
    playByTimeChartData(currTime);
};


//按时间显示chart线图
var playByTimeChartData = function (currTime) {
    // console.debug('playByTimeChartData:'+currTime+",updateChart="+updateChart+",updateCardContent="+updateCardContent);
    var videoCurrentTime = videoPage.videoCreateTime.getTime() + currTime * 1000;
    $.each(chartMap, function (chartId, myChart) {
        var chartFullData = chartDataMap[chartId];
        var filteredData = filterChartData(chartFullData, videoCurrentTime);
        // console.debug('更新chart数据。'+chartId+",时间="+videoCurrentTime+".完整数据量："+chartFullData.length+",过滤后数据量："+filteredData.length);
        myChart.setOption({
            series: [{
                data: filteredData
            }]
        });
    });
    $.each(chartMap, function (chartId, myChart) {
        // chartMap保存echart图表信息
        //$$$$$$$$$ 需要你写代码的地方
        if (chartId == "video_chart_0") {
            var _min = parseInt(referenceValueList.ROTATION.OIL_PRESSURE.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.OIL_PRESSURE.stateUp); //修改此处 为最大值
        } else if (chartId == "video_chart_1") {
            var _min = parseInt(referenceValueList.ROTATION.REVOLUTION_SPEED.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.REVOLUTION_SPEED.stateUp); //修改此处 为最大值
        } else if (chartId == "video_chart_2") {
            var _min = parseInt(referenceValueList.ROTATION.WATER_TEMPERATURE.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.WATER_TEMPERATURE.stateUp); //修改此处 为最大值
        } else if (chartId == "video_chart_3") {
            var _min = parseInt(referenceValueList.ROTATION.PUMP_PRESSURE_R.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.PUMP_PRESSURE_R.stateUp); //修改此处 为最大值
        } else if (chartId == "video_chart_4") {
            var _min = parseInt(referenceValueList.ROTATION.ELECTRIC_CURRENT_R.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.ELECTRIC_CURRENT_R.stateUp); //修改此处 为最大值
        } else if (chartId == "video_chart_5") {
            var _min = parseInt(referenceValueList.ROTATION.PUMP_PRESSURE_F.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.PUMP_PRESSURE_F.stateUp); //修改此处 为最大值
        } else if (chartId == "video_chart_6") {
            var _min = parseInt(referenceValueList.ROTATION.ELECTRIC_CURRENT_F.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.ELECTRIC_CURRENT_F.stateUp); //修改此处 为最大值
        } else if (chartId == "video_chart_7") {
            var _min = parseInt(referenceValueList.ROTATION.FUEL_CONSUMPTION.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.FUEL_CONSUMPTION.stateUp); //修改此处 为最大值
        } else if (chartId == "video_chart_8") {
            var _min = parseInt(referenceValueList.ROTATION.TORQUE_PERCENT.stateLower); //修改此处 为最小值
            var _max = parseInt(referenceValueList.ROTATION.TORQUE_PERCENT.stateUp); //修改此处 为最大值
        }
        myChart.setOption({
            series: [{
                markLine: {
                    lineStyle: {
                        color: '#008B00'
                    },
                    data: [
                        {
                            name: "最小",
                            yAxis: _min
                        },
                        {
                            name: "最大",
                            yAxis: _max
                        }
                    ]
                }
            }]
        });
    });
    $.each(gaugeMap, function (chartId, myChart) {
        if (chartId == "video_chart_0") {
            var chartName = '机油压力';
        } else if (chartId == "video_chart_1") {
            var chartName = '转速rpm';
        } else if (chartId == "video_chart_2") {
            var chartName = '水温℃';
        } else if (chartId == "video_chart_3") {
            var chartName = 'R泵泵压Kg/cm2';
        } else if (chartId == "video_chart_4") {
            var chartName = 'R泵PC-EPC电流mA';
        } else if (chartId == "video_chart_5") {
            var chartName = 'F泵泵压Kg/cm2';
        } else if (chartId == "video_chart_6") {
            var chartName = 'F泵PC-EPC电流mA';
        } else if (chartId == "video_chart_7") {
            var chartName = '燃料瞬间消耗L/H';
        } else if (chartId == "video_chart_8") {
            var chartName = '扭矩百分比%';
        }
        var chartFullData = chartDataMap[chartId];
        var filteredData = filterGaugeData(chartFullData, videoCurrentTime);
        // console.debug('更新chart数据。'+chartId+",时间="+videoCurrentTime+".完整数据量："+chartFullData.length+",过滤后数据量："+filteredData.length);
        myChart.setOption({
            series: [
                {
                    data: [filteredData]
                },
                {
                    detail: {
                        formatter: [
                            '{value|' + filteredData.value + '} ',
                            '{name|' + chartName + '}'
                        ].join('\n')
                    }
                }]

        });
    });

    var show_altitude = ''; //高度
    if (videoPage.imeiData.hasOwnProperty('GPS位置')) { //检测是否包含该属性
        for (var _item in videoPage.imeiData['GPS位置']) {
            if (videoPage.imeiData['GPS位置'][_item].key > videoCurrentTime) {
                continue;
            }

            if (videoPage.imeiData['GPS位置'][_item].val.altPosNeg == 0) {
                show_altitude = videoPage.imeiData['GPS位置'][_item].val.alt + 'm';
            } else {
                show_altitude = '-' + videoPage.imeiData['GPS位置'][_item].val.alt + 'm';
            }

        }
        $('span.altitude').text(show_altitude);

    }

    var show_E = ''; //E模式
    if (videoPage.imeiData.hasOwnProperty('E模式调整')) { //检测是否包含该属性
        for (var _item in videoPage.imeiData['E模式调整']) {
            if (videoPage.imeiData['E模式调整'][_item].key > videoCurrentTime) {
                continue;
            }
            show_E = 'E' + videoPage.imeiData['E模式调整'][_item].val + "模式";
        }
        $('span.eMode').text(show_E);
    }

    var _powerTime = '';//开机时间
    //这里计算下开机时间
    if (videoPage.imeiData.hasOwnProperty('当天钥匙接通时间S')) {//检测是否包含该属性
        for (var _item in videoPage.imeiData['当天钥匙接通时间S']) {
            if (videoPage.imeiData['当天钥匙接通时间S'][_item].key > videoCurrentTime) {
                continue;
            }
            _powerTime = videoPage.imeiData['当天钥匙接通时间S'][_item].val;
        }
        var _hour = Math.floor(parseInt(_powerTime) / 3600);
        var _minute = Math.floor((parseInt(_powerTime) - (_hour * 3600)) / 60);
        var _second = Math.floor(parseInt(_powerTime) - (_hour * 3600) - (_minute * 60));
        $('.powerTime b').text(_hour + ":" + _minute + ":" + _second);
    }

    var show_act = ''; //动作   此处应通过动作动态修改 图表的参考线值
    if (videoPage.imeiData.hasOwnProperty('动作')) { //检测是否包含该属性
        for (var _item in videoPage.imeiData['动作']) {
            if (videoPage.imeiData['动作'][_item].key > videoCurrentTime) {
                continue;
            }
            var show_act_json = videoPage.imeiData['动作'][_item].val;
            if ($.parseJSON(show_act_json).action == 0) {
                show_act = $.parseJSON(show_act_json).actionVal;
            } else {
                show_act = '无动作';
            }
        }

    }

    $('span.act').text(show_act);
}


var filterChartData = function (chartFullData, videoCurrentTime) {
    var filteredData = [];
    $.each(chartFullData, function (idx, data) {
        if (data.name <= videoCurrentTime) {
            filteredData.push(data);
        }
    });
    return filteredData;
}
var filterGaugeData = function (chartFullData, videoCurrentTime) {
    var filteredData = [];
    $.each(chartFullData, function (idx, data) {
        if (data.name <= videoCurrentTime) {
            var _data = {
                // name: data.name,
                value: data.value[1]
            }
            filteredData = _data;
        }
    });
    return filteredData;
}
var chartColorMap = {
    "泵压": "#ef5350",
    "电流": "#64b5f6",
    "扭矩": "#ef5350"
};

var getChartColor = function (chartName) {
    var lineColor = null;
    $.each(chartColorMap, function (key, color) {
        if (chartName.indexOf(key) != -1) {
            lineColor = color;
            return false;
        }
    });
    return lineColor || "64b5f6";
}

//初始化工况chart
var initChartsIfNeeded = function () {
    if (videoPage.gpsTypeNameList.length > 0 && Object.keys(chartMap).length > 0) {
        //有数据且chart也存在则不再初始化
        return;
    }

    // var chartDetailArea = $("#chartdetail");
    var minGpsTime = 0;
    var charts = $("div.echart");

    $.each(videoPage.gpsChartDataList, function (chartIdx, chartName) {
        if (chartIdx == 0) {
            var _max = 1;
        } else if (chartIdx == 1) {
            var _max = 2300;
        } else if (chartIdx == 2) {
            var _max = 120;
        } else if (chartIdx == 3) {
            var _max = 400;
        } else if (chartIdx == 4) {
            var _max = 1200;
        } else if (chartIdx == 5) {
            var _max = 400;
        } else if (chartIdx == 6) {
            var _max = 1200;
        } else if (chartIdx == 7) {
            var _max = 100;
        } else if (chartIdx == 8) {
            var _max = 100;
        }

        if (charts.length <= chartIdx) {
            console.info("视频指标数超过设计的数量,该标志忽略不显示.chartName=" + chartName + ",设计数量=" + charts.length + ",当前指标idx=" + chartIdx);
            return false;
        }

        var dataList = videoPage.gpsData[chartName];
        var categoryData = [];
        if (dataList != null && dataList.length > 0) {
            $.each(dataList, function (idx, data) {
                var ctime = data.key;
                if (minGpsTime == 0) minGpsTime = ctime;
                if (minGpsTime > ctime) minGpsTime = ctime;
                var val = data.val;
                var oneData = {
                    name: ctime,
                    value: [
                        ctime,
                        val
                    ]
                };
                categoryData.push(oneData);
            });
        }
        var chartId = 'video_chart_' + chartIdx;
        chartDataMap[chartId] = categoryData;

        var myChart = charts[chartIdx];
        var chartDiv = $(myChart);
        chartDiv.attr("id", chartId).attr("data-charttype", "line").attr("data-chartname", chartName);
        var lineColor = getChartColor(chartName);
        createChart(chartId, chartName, categoryData, lineColor, 0, _max);
    });

    //添加resize事件
    window.addEventListener('resize', function () {
        window.setTimeout(function () {
            $.each(chartMap, function (chartId, myChart) {
                myChart.resize();
            });
        }, 300);
    }, false);


    videoPage.gpsMinTime = minGpsTime;
    console.debug("gps数据处理完毕，minGpsTime=" + videoPage.gpsMinTime + ",createTime=" + videoPage.videoCreateTime.getTime());
    if (videoPage.gpsMinTime > videoPage.videoCreateTime.getTime()) {
        console.debug("蓝牙盒子数据的时间比时间录制时间晚，定位视频开始播放时间为蓝牙数据最小时间.");
        var second = (videoPage.gpsMinTime - videoPage.videoCreateTime.getTime()) / 1000;
        videoPage.playStartSecond = second;
        videoPage.video.currentTime = videoPage.playStartSecond;
        console.debug("定位视频播放起始秒数:" + videoPage.playStartSecond);
    }
};
var initGaugeIfNeeded = function () {
    if (videoPage.gpsTypeNameList.length > 0 && Object.keys(chartMap).length > 0) {
        //有数据且chart也存在则不再初始化
        return;
    }

    // var chartDetailArea = $("#chartdetail");
    var minGpsTime = 0;
    // var charts = $("div.echart");
    var gauge = $('.gauge');
    $.each(videoPage.gpsChartDataList, function (chartIdx, chartName) {
        // if (charts.length <= chartIdx) {
        //     console.info("视频指标数超过设计的数量,该标志忽略不显示.chartName=" + chartName + ",设计数量=" + charts.length + ",当前指标idx=" + chartIdx);
        //     return false;
        // }
        // if (chartIdx == 0) {
        //     var _max = 1;//需要改
        // } else if (chartIdx == 1) {
        //     var _max = 2500;
        // } else if (chartIdx == 2) {
        //     var _max = 2000;
        // } else if (chartIdx == 3) {
        //     var _max = 120;
        // } else if (chartIdx == 4) {
        //     var _max = 400;
        // } else if (chartIdx == 5) {
        //     var _max = 400;
        // } else if (chartIdx == 6) {
        //     var _max = 100;//需要改
        // } else if (chartIdx == 7) {
        //     var _max = 2000;
        // } else if (chartIdx == 8) {
        //     var _max = 150;
        // }
        if (chartIdx == 0) {
            var _max = 1;//需要改
        } else if (chartIdx == 1) {
            var _max = 2500;
        } else if (chartIdx == 2) {
            var _max = 120;
        } else if (chartIdx == 3) {
            var _max = 400;
        } else if (chartIdx == 4) {
            var _max = 2000;
        } else if (chartIdx == 5) {
            var _max = 400;
        } else if (chartIdx == 6) {
            var _max = 2000;//需要改
        } else if (chartIdx == 7) {
            var _max = 100;
        } else if (chartIdx == 8) {
            var _max = 150;
        }
        switch (chartName) {
            case '燃料瞬间消耗L/H':
                break;
            case "转速rpm":
                break;
            case "扭矩百分比%":
                break;
            case "F泵泵压Kg/cm2":
                break;
            case "F泵PC-EPC电流mA":
                break;
            case "R泵泵压Kg/cm2":
                break;
            case "R泵PC-EPC电流mA":
                break;
            case "机油压力":
                break;
            case "水温℃":
                break;
            default:
                return;
        }
        var dataList = videoPage.gpsData[chartName];
        var categoryData = [];
        if (dataList != null && dataList.length > 0) {
            $.each(dataList, function (idx, data) {
                var ctime = data.key;
                if (minGpsTime == 0) minGpsTime = ctime;
                if (minGpsTime > ctime) minGpsTime = ctime;
                var val = data.val;
                var oneData = {
                    name: ctime,
                    value: [
                        ctime,
                        val
                    ]
                };
                categoryData.push(oneData);
            });
        }
        var chartId = 'video_chart_' + chartIdx;
        chartDataMap[chartId] = categoryData;
        var gaugeDiv = gauge[chartIdx];
        $(gaugeDiv).attr("id", 'g' + chartId).attr("data-charttype", "gauge").attr("data-chartname", chartName);
        var lineColor = getChartColor(chartName);
        createGaugeChart(chartId, chartName, categoryData, lineColor, 0, _max);
    });

    //添加resize事件
    window.addEventListener('resize', function () {
        window.setTimeout(function () {
            $.each(chartMap, function (chartId, myChart) {
                myChart.resize();
            });
        }, 300);
    }, false);


    videoPage.gpsMinTime = minGpsTime;
    console.debug("gps数据处理完毕，minGpsTime=" + videoPage.gpsMinTime + ",createTime=" + videoPage.videoCreateTime.getTime());
    if (videoPage.gpsMinTime > videoPage.videoCreateTime.getTime()) {
        console.debug("蓝牙盒子数据的时间比时间录制时间晚，定位视频开始播放时间为蓝牙数据最小时间.");
        var second = (videoPage.gpsMinTime - videoPage.videoCreateTime.getTime()) / 1000;
        videoPage.playStartSecond = second;
        videoPage.video.currentTime = videoPage.playStartSecond;
        console.debug("定位视频播放起始秒数:" + videoPage.playStartSecond);
    }
};
//创建1个chart
var createGaugeChart = function (chartId, chartName, chartFullData, lineColor, rotateY, max) {
    console.log(chartId + chartName + "############" + max);
    switch (chartName) {
        case '燃料瞬间消耗L/H':
            break;
        case "转速rpm":
            break;
        case "扭矩百分比%":
            break;
        case "F泵泵压Kg/cm2":
            break;
        case "F泵PC-EPC电流mA":
            break;
        case "R泵泵压Kg/cm2":
            break;
        case "R泵PC-EPC电流mA":
            break;
        case "机油压力":
            break;
        case "水温℃":
            break;
        default:
            break;
    }
    var chartDiv = $("#g" + chartId)[0];
    var myChart = echarts.init(chartDiv);
    gaugeMap[chartId] = myChart;

    var highlight = '#03b7c9';//这里是外侧圆环的颜色
    var pos = ["50%", "60%"];//设置圈的圆心在中间
    var option = {
        backgroundColor: '#222939',//背景色
        series: [
            {
                name: '仪表盘',
                textStyle: {
                    color: '#fff'
                },
                radio: '75%',
                type: 'gauge',
                center: pos,
                detail: {show: 0},
                min: 0,
                max: max,
                startAngle: 225,
                endAngle: -45,
                splitNumber: 10, //分割几块
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: [[0.15, 'lime'], [0.8, '#1e90ff'], [1, '#ff4500']],
                        width: 2,
                        shadowBlur: 0
                        /*color: [
                            [1, highlight]
                        ]*/
                    }
                },
                axisTick: {
                    show: true,
                    lineStyle: {
                        color: highlight,
                        width: 1
                    },
                    length: -5,
                    splitNumber: 10
                },
                splitLine: {
                    show: true,
                    length: -14,
                    lineStyle: {
                        color: highlight,
                    }
                },
                axisLabel: {
                    distance: -20,
                    textStyle: {
                        color: highlight,
                        fontSize: '14',
                        fontWeight: 'bold'
                    }
                },
                // pointer: {
                //     show: true,
                //     length: '105%'
                // },
                detail: {
                    show: 0
                },
                data: [{value: 0, name: ''}]
            },
            // 内侧指针、数值显示
            {
                name: 'circleInside',
                type: 'gauge',
                center: pos,
                radius: '65%',//调整内圈的大小
                startAngle: 225,
                endAngle: -45,
                min: 0,
                max: 3,
                axisLine: {
                    show: true,
                    lineStyle: {
                        width: 16,
                        color: [
                            [1, 'rgba(255,255,255,.1)']
                        ]
                    }
                },
                axisTick: {
                    show: 0,
                },
                splitLine: {
                    show: 0,
                },
                axisLabel: {
                    show: 0
                },
                pointer: {
                    show: true,
                    length: '105%'
                },
                detail: {
                    show: true,
                    offsetCenter: [0, '100%'],
                    textStyle: {
                        fontSize: 16,
                        color: '#fff'
                    },
                    formatter: [
                        '{value} ' + ('这里放显示的值的变量' || ''),
                        '{name|' + chartName + '}'
                    ].join('\n'),
                    rich: {
                        name: {
                            fontSize: 12,
                            lineHeight: 20,
                            color: '#ddd'
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: highlight,
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
    console.debug("初始化chart完毕." + chartId);
}
var createChart = function (chartId, chartName, chartFullData, lineColor, rotateY, chartMax) {
    var chartDiv = $("#" + chartId)[0];
    var myChart = echarts.init(chartDiv);
    chartMap[chartId] = myChart;
    console.debug("创建chart.chartId" + chartId + ",chart=" + myChart);
    var optionChartData = [];

    var option = {
        title: {
            text: chartName,
            x: 'center',
            textStyle: {
                fontSize: '12',
                height: '16'
            },
            itemGap: 0,
            padding: 0,

        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                var date = new Date(params.name);
                return date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "." + date.getMilliseconds() + ",数值：" + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        grid: {//直角坐标系控制
            x: 50,
            x2: 50,
            y: 20,
        },
        xAxis: {
            type: 'time',
            splitNumber: 2,
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            min: 0,
            max: chartMax,
            splitLine: {
                show: false
            },
            axisLabel: {
                margin: 5,
                interval: 0,//横轴信息全部显示
                rotate: rotateY || 0,//-30度角倾斜显示
                formatter: function (value, index) {
                    if (value >= 10000 && value < 10000000) {
                        var left = value % 10000;
                        return (value / 10000).toFixed(1) + "";
                    } else if (value >= 10000000) {
                        var left = value % 10000;
                        return (value / 10000000).toFixed(1) + "";
                    }
                    return value;
                }
            },
        },
        series: [{
            name: '传感器数据',
            type: 'line',
            showSymbol: true,
            symbol: 'circle',     //设定为实心点
            symbolSize: 3,   //设定实心点的大小
            hoverAnimation: true,
            itemStyle: {
                normal: {
                    color: lineColor,//'#000000',
                    lineStyle: {
                        color: lineColor
                    }
                }
            },
            data: optionChartData
        }]
    };
    myChart.setOption(option);
    console.debug("初始化chart完毕." + chartId);
};

//切换折线图中数据的展示:折线图、点图
// var changeChartType = function (myChart) {
//     var _myChart = $(myChart);
//     var oldType = _myChart.attr("data-charttype");
//     var chartName = _myChart.attr("data-chartname");
//     var newType = oldType == "line" ? "scatter" : "line";
//     _myChart.attr("data-charttype", newType);
//
//     var chartId = _myChart.attr("id");
//     var chartFullData = chartDataMap[chartId];
//     var currTime = videoPage.video.currentTime;
//     var videoCurrentTime = videoPage.videoCreateTime.getTime() + currTime * 1000;
//     var filteredData = filterChartData(chartFullData, videoCurrentTime);
//     var chartColor = getChartColor(chartName);
//
//     var myChartInstance = chartMap[chartId];
//     myChartInstance.setOption({
//         series: [{
//             type: newType,
//             symbolSize: 3,   //设定实心点的大小
//             data: filteredData,
//             itemStyle: {
//                 normal: {
//                     color: chartColor,//'#000000',
//                     lineStyle: {
//                         color: chartColor
//                     }
//                 }
//             },
//         }]
//     });
// }

//异步载入工况chart数据
var loadChartData = function () {
    showAlert("正在载入视频工况信息...");
    ajaxGet("/video/play/chartdata/" + videoPage.videoId, function (resp) {
        if (resp.success) {
            var doubleVo = resp.data;
            // videoPage.gpsTypeNameList = doubleVo.key;
            // videoPage.gpsData = doubleVo.val;
            setDataForVideo(doubleVo);
            initGaugeIfNeeded();
            console.debug("载入视频工况成功.");
            videoPage.video.play();
        } else {
            showAlert("载入车辆工况数据失败." + resp.message);
        }
    });
}
var setDataForVideo = function (doubleVo) {
    // videoPage.gpsTypeNameList = doubleVo.key;
    // videoPage.gpsData = doubleVo.val;
    videoPage.gpsData = {};
    videoPage.imeiData = {};
    videoPage.gpsChartDataList = [];
    videoPage.gpsChartDataList.push("机油压力");
    videoPage.gpsChartDataList.push("转速rpm");
    videoPage.gpsChartDataList.push("水温℃");
    videoPage.gpsChartDataList.push("R泵泵压Kg/cm2");
    videoPage.gpsChartDataList.push("R泵PC-EPC电流mA");
    videoPage.gpsChartDataList.push("F泵泵压Kg/cm2");
    videoPage.gpsChartDataList.push("F泵PC-EPC电流mA");
    videoPage.gpsChartDataList.push("燃料瞬间消耗L/H");
    videoPage.gpsChartDataList.push("扭矩百分比%");
    for (var chartName in doubleVo.val) {
        switch (chartName) {
            case '燃料瞬间消耗L/H':
                ;
            case "转速rpm":
                ;
            case "扭矩百分比%":
                ;
            case "F泵泵压Kg/cm2":
                ;
            case "F泵PC-EPC电流mA":
                ;
            case "R泵泵压Kg/cm2":
                ;
            case "R泵PC-EPC电流mA":
                ;
            case "机油压力":
                ;
            case "水温℃":
                videoPage.gpsData[chartName] = doubleVo.val[chartName];

                // for (var i = 0; i < doubleVo.key.length; i++) {
                //     if (doubleVo.key[i] == chartName) {
                //         videoPage.gpsChartDataList.push(doubleVo.key[i]);
                //         break;
                //     }
                // }

                break;
            default:
                videoPage.imeiData[chartName] = doubleVo.val[chartName];
        }
    }
    videoPage.gpsTypeNameList = doubleVo.key;
    // videoPage.gpsData = doubleVo.val;
    console.debug("载入视频工况成功.");
    // videoPage.video.play();
}

//视频播放暂停拖动事件
var removeVideoEvent = function () {
    var _video = $(videoPage.video);
    _video.off('play');
    _video.off('pause');
    _video.off('timeupdate');
    _video.off('ended');
}

var addVideoEvent = function () {
    var _video = $(videoPage.video);
    _video.off('play').on('play', playChartData);
    _video.off('pause').on('pause', playChartData);
    _video.off('timeupdate').on('timeupdate', playChartData);
    _video.off('ended').on('ended', playChartData);
}

//视频基本信息和视频chart信息tab页签切换事件
// var initVideoChartTabEvent = function () {
// videoPage.chartOrder = 0;
//
// $("#navDetail>li").on('click',function(){
//     var i = $(this).index();
//     if(i == 3){//最后一个页签
//         //chart页签，则初始化chart
//         initChartsIfNeeded();
//     }
// });

// $("#navtabs>li").on('click',function(){
//     var i = $(this).index();
//     $(".profile-left>.profile-detail").eq(i).show().siblings('.profile-detail').hide();
//     if(i == 1){
//         //chart页签，则初始化chart
//         initChartsIfNeeded();
//     }
// });
// }

//ixu   数据图表弹窗 控制 fn1 出现  fn2 消失  你可以自己替换在你需要的位置
function fn1() {//打开
    var playerTime = videoPage.video.currentTime;
    videoPage.video.pause();

    // $("#q1").modal("show");
    initChartsIfNeeded();
    initVideoPlayer("#my-video-o");//重新初始化播放器
    addVideoEvent();//图表对应播放器上重新绑定事件
    //播放器时间定位到前1个播放器的播放位置
    videoPage.video.currentTime = playerTime;
    videoPage.video.play();
}

function fn2() {//关闭
    // $("#q1").modal("hide");
    videoPage.video.pause();
    var playerTime = videoPage.video.currentTime;
    //旧的播放器上的事件移除
    removeVideoEvent();
    initVideoPlayer("#my-video-o");//重新初始化播放器
    addVideoEvent();//图表对应播放器上重新绑定事件
    //播放器时间定位到前1个播放器的播放位置
    videoPage.video.currentTime = playerTime;
    videoPage.video.play();
}

//加载图表参考值
var loadReferenceValue = function () {
    ajaxPost('/referencevalue/getValue', {imei: videoPage.videoData.imei}, function (res) {
        if (!res.success) {
            showAlert('获取图表参考值失败！');
            return;
        }
        referenceValueList = res.data.param;
    })
}

$(function () {
    loadReferenceValue();
    addVideoEvent();
    loadChartData();

});