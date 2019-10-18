<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title> PC200-8检查表</title>
    <script src="/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="/static/jquery/jquery.jqprint-0.3.js"></script>
    <link href="/static/css/recirculateReport.css" type="text/css" rel="stylesheet" media="screen,projection,print"/>
</head>
<body style="text-align: center;">
<div class="reportDiv">

    <div class="headDiv">
        <div style="margin-bottom: 30px;width: 100%;">
            <div style="width: 120px;">
                <img src="/static/images/komatsu-100x25.png" style="width:100px;height:25px;"/>
            </div>
            <div style="left: 140px;
                    position: absolute;
                    font-size: 16px;">
                <h2 style="margin-top: 0;">PC200-8液压挖机性能检査表（接收）</h2>
            </div>
            <div style="position: absolute;
                     right: 60px;padding-top: 30px;">
                <h5 style="    margin-top: 0;">小松（常州）机械更新制造有限公司</h5>
            </div>
        </div>
    </div>

    <div class="topDiv">
        <table width="100%" border="1" cellspacing="0">
            <tr>
                <td style="width:20%">机种・机型</td>
                <td style="width:40%" data-key="machineType" noHighLight></td>
                <td style="width: 20%">作业号码</td>
                <td data-key="reportNo" noHighLight></td>
            </tr>
            <tr>
                <td>机号</td>
                <td data-key="machineCode" noHighLight></td>
                <td>检査实施日期</td>
                <td data-key="reportDate" noHighLight></td>
            </tr>
            <tr>
                <td>发动机型号</td>
                <td data-key="engine" noHighLight></td>
                <td>检査担当者姓名</td>
                <td data-key="createUser" noHighLight></td>
            </tr>
        </table>
    </div>

    <div class="contentDiv">
        <table width="100%" border="1" cellspacing="0">
            <tr style="text-align: center">
                <td colspan="2" rowspan="2">项目</td>
                <td rowspan="2">测试条件</td>
                <td rowspan="2">单位</td>
                <td colspan="2">允许极限值</td>
                <td colspan="3">测量结果</td>
            </tr>
            <tr>
                <td colspan="2" nowrap="nowrap">PC200、PC200LC-8</td>
                <td style="width:70px;">第一回次</td>
                <td style="width:70px;">第二回次</td>
                <td style="width:70px;">第三回次</td>
            </tr>
            <tr data-condition="1920±100">
                <td rowspan="3" style="width:80px;">发动机转速</td>
                <td>２泵溢流时</td>
                <td>・液压油温：４５～５５℃ 、发动机以高怠速运转<br>
                    ・发动机水温：在工作范围内<br>
                    ・２泵溢流： 斗杆溢流时<br>
                </td>
                <td rowspan="3" style="text-align: center">rpm</td>
                <td colspan="2">1920±100</td>
                <td data-key="speed_overflow_1"></td>
                <td data-key="speed_overflow_2"></td>
                <td data-key="speed_overflow_3"></td>
            </tr>
            <tr data-condition="1950±100">
                <td>2泵溢流+功率增强</td>
                <td>・斗杆溢流+触式加力开关在ON位置<br>
                    ・液压油温、发动机水温在工作范围内<br>
                    ・发动机高怠速运转
                </td>
                <td colspan="2">1950±100</td>
                <td data-key="speed_increase_1"></td>
                <td data-key="speed_increase_2"></td>
                <td data-key="speed_increase_3"></td>
            </tr>
            <tr data-condition="1400±100">
                <td>自动降速操作时的转速</td>
                <td>・发动机以高怠速运转 、自动降速开关打开<br>
                    ・所有操纵杆在中位状态
                </td>
                <td colspan="2">1400±100</td>
                <td data-key="speed_decrease_1"></td>
                <td data-key="speed_decrease_2"></td>
                <td data-key="speed_decrease_3"></td>
            </tr>
            <tr data-condition="340-375">
                <td rowspan="9">油压（泵压）</td>
                <td>动臂</td>
                <td rowspan="7">
                    ・液压油温：４５～５５℃<br>
                    ・发动机以高怠速运转仅让测量回路溢流<br>
                    ・工作模式: P模式<br>
                    ・泵输出压力<br>
                    ・对于行走,测量一侧溢流时的油压<br>
                    ・所有操纵杆在中位状态
                </td>
                <td rowspan="9" style="text-align: center">Kg/ｃｍ2</td>
                <td rowspan="3" colspan="2">340-375</td>
                <td data-key="pressure_arm_1"></td>
                <td data-key="pressure_arm_2"></td>
                <td data-key="pressure_arm_3"></td>
            </tr>
            <tr data-condition="340-375">
                <td>斗杆</td>
                <td data-key="pressure_dipper_1"></td>
                <td data-key="pressure_dipper_2"></td>
                <td data-key="pressure_dipper_3"></td>
            </tr>
            <tr data-condition="340-375">
                <td>铲斗</td>
                <td data-key="pressure_bucket_1"></td>
                <td data-key="pressure_bucket_2"></td>
                <td data-key="pressure_bucket_3"></td>
            </tr>
            <tr data-condition="290-330">
                <td>回转</td>
                <td colspan="2">290-330</td>
                <td data-key="pressure_back_1"></td>
                <td data-key="pressure_back_2"></td>
                <td data-key="pressure_back_3"></td>
            </tr>
            <tr data-condition="375-410">
                <td rowspan="2">行走</td>
                <td rowspan="2" colspan="2">375-410</td>
                <td data-key="pressure_walk_11"></td>
                <td data-key="pressure_walk_12"></td>
                <td data-key="pressure_walk_13"></td>
            </tr>
            <tr data-condition="375-410">
                <td data-key="pressure_walk_21"></td>
                <td data-key="pressure_walk_22"></td>
                <td data-key="pressure_walk_23"></td>
            </tr>
            <tr data-condition="28-35">
                <td>控制阀（卸荷阀）</td>
                <td colspan="2">28-35</td>
                <td data-key="pressure_valve_1"></td>
                <td data-key="pressure_valve_2"></td>
                <td data-key="pressure_valve_3"></td>
            </tr>
            <tr data-condition="36±10">
                <td rowspan="2">LS差压</td>
                <td rowspan="2"> ・油温：４５～５５℃<br>
                    ・发动机高怠速运转<br>
                    ・工作模式: P模式<br>
                    ・行走速度: 高速
                </td>
                <td colspan="2">36±10</td>
                <td data-key="pressure_differ_11"></td>
                <td data-key="pressure_differ_12"></td>
                <td data-key="pressure_differ_13"></td>
            </tr>
            <tr data-condition="18±1">
                <td colspan="2">18±1</td>
                <td data-key="pressure_differ_21"></td>
                <td data-key="pressure_differ_22"></td>
                <td data-key="pressure_differ_23"></td>
            </tr>
            <tr data-condition="最大130">
                <td rowspan="4">回转</td>
                <td>回转制动角度</td>
                <td>
                    ・作业时姿势：空负荷・最大有效范围<br>
                    ・发动机以高怠速运转 、 工作模式：P模式<br>
                    ・液压油温：４５～５５℃<br>
                    ・1次回转后停止时旋转轴承偏移量
                </td>
                <td style="text-align: center">度</td>
                <td colspan="2">最大130</td>
                <td data-key="back_degree_1"></td>
                <td data-key="back_degree_2"></td>
                <td data-key="back_degree_3"></td>
            </tr>
            <tr data-condition="最大3.7">
                <td rowspan="2">回转起動所要時間</td>
                <td rowspan="2">
                    ・作业时姿势：空负荷・最大有效范围<br>
                    ・发动机以高怠速运转<br>
                    ・液压油温：４５～５５℃<br>
                    ・工作模式:P模式<br>
                    ・从起动位置通过９０度・１８０度回转时所需要的時間
                </td>
                <td rowspan="2" style="text-align: center">秒</td>
                <td colspan="2">最大3.7</td>
                <td data-key="back_starttime_11"></td>
                <td data-key="back_starttime_12"></td>
                <td data-key="back_starttime_13"></td>
            </tr>
            <tr data-condition="最大5.5">
                <td colspan="2">最大5.5</td>
                <td data-key="back_starttime_21"></td>
                <td data-key="back_starttime_22"></td>
                <td data-key="back_starttime_23"></td>
            </tr>
            <tr data-condition="最大30">
                <td>回转所需要的时间</td>
                <td>
                    ・作业时姿势：空负荷・最大有效范围<br>
                    ・发动机以高怠速运转<br>
                    ・液压油温：４５～５５℃<br>
                    ・工作模式: P模式<br>
                    ・从第1次回转后到第５次回转时所需要的时间
                </td>
                <td style="text-align: center">秒</td>
                <td colspan="2">最大30</td>
                <td data-key="back_alltime_1"></td>
                <td data-key="back_alltime_2"></td>
                <td data-key="back_alltime_3"></td>
            </tr>
            <tr data-condition="46.2-60.4">
                <td rowspan="7">行走</td>
                <td rowspan="6">行走速度</td>
                <td rowspan="6">
                    ・发动机高速空转<br>
                    ・液压油温：４５～５５℃<br>
                    ・P模式时<br>
                    ・分別每支起一侧的履帯，旋转一圈后测量空转5圈所需要的时间
                </td>
                <td rowspan="6" style="text-align: center">秒</td>
                <td rowspan="2" style="width:100px;text-align: center;">46.2-60.4</td>
                <td>R</td>
                <td data-key="walkspeed_r_11"></td>
                <td data-key="walkspeed_r_11"></td>
                <td data-key="walkspeed_r_13"></td>
            </tr>
            <tr data-condition="46.2-60.4">
                <td>L</td>
                <td data-key="walkspeed_l_11"></td>
                <td data-key="walkspeed_l_12"></td>
                <td data-key="walkspeed_l_13"></td>
            </tr>
            <tr data-condition="33.7-45.3">
                <td rowspan="2" style="text-align: center;">33.7-45.3</td>
                <td>R</td>
                <td data-key="walkspeed_r_21"></td>
                <td data-key="walkspeed_r_22"></td>
                <td data-key="walkspeed_r_23"></td>
            </tr>
            <tr data-condition="33.7-45.3">
                <td>L</td>
                <td data-key="walkspeed_l_21"></td>
                <td data-key="walkspeed_l_22"></td>
                <td data-key="walkspeed_l_23"></td>
            </tr>
            <tr data-condition="25.2-29.8">
                <td rowspan="2" style="text-align: center;">25.2-29.8</td>
                <td>R</td>
                <td data-key="walkspeed_r_31"></td>
                <td data-key="walkspeed_r_32"></td>
                <td data-key="walkspeed_r_33"></td>
            </tr>
            <tr data-condition="25.2-29.8">
                <td>L</td>
                <td data-key="walkspeed_l_31"></td>
                <td data-key="walkspeed_l_32"></td>
                <td data-key="walkspeed_l_33"></td>
            </tr>
            <tr data-condition="??">
                <td>行走跑偏</td>
                <td>
                    ・发动机以高怠速运转<br>
                    ・液压油温：４５～５５℃<br>
                    ・工作模式: P模式<br>
                    ・在平地上至少跑１０ｍ以上后,再测量跑２０ｍ时所需要的时间<br>
                    ・在水平硬地面上进行测量<br>
                    *测量A尺寸
                </td>
                <td style="text-align: center;">mm</td>
                <td colspan="2" colspan="2">
                    <img src="/static/images/report-chart.png" style="width:100%;"/>
                </td>
                <td data-key="walkoff_1"></td>
                <td data-key="walkoff_2"></td>
                <td data-key="walkoff_3"></td>
            </tr>
            <tr data-condition="最大900">
                <td rowspan="4">工作装置自然降下量</td>
                <td>工作装置整体部分</td>
                <td rowspan="4">
                    ・作业时姿势：最大有效范围<br>
                    ・负荷：铲斗前端额定荷重(0.8m³,1440Kg )<br>
                    ・水平、平地上、操纵杆在中位状态、发动机熄火<br>
                    ・液压油温：４５～５５℃<br>
                    ・调整后开始<br>
                    ・每隔５分钟测量一次,根据15分钟的结果进行判断
                </td>
                <td rowspan="4" style="text-align: center">mm</td>
                <td colspan="2">铲斗齿前端降下量<br>
                    最大900
                </td>
                <td data-key="decline_device_1"></td>
                <td data-key="decline_device_2"></td>
                <td data-key="decline_device_3"></td>
            </tr>
            <tr data-condition="最大27">
                <td>动臂油缸</td>
                <td colspan="2">油缸缩回量<br>
                    最大27
                </td>
                <td data-key="decline_arm_1"></td>
                <td data-key="decline_arm_2"></td>
                <td data-key="decline_arm_3"></td>
            </tr>
            <tr data-condition="最大240">
                <td>斗杆油缸</td>
                <td colspan="2">油缸伸出量<br>
                    最大240
                </td>
                <td data-key="decline_dipper_1"></td>
                <td data-key="decline_dipper_2"></td>
                <td data-key="decline_dipper_3"></td>
            </tr>
            <tr data-condition="最大58">
                <td>铲斗油缸</td>
                <td colspan="2">油缸缩回量<br>
                    最大58
                </td>
                <td data-key="decline_bucket_1"></td>
                <td data-key="decline_bucket_2"></td>
                <td data-key="decline_bucket_3"></td>
            </tr>
            <tr data-condition="最大4.7">
                <td rowspan="6">工作装置速度</td>
                <td rowspan="2">动臂速度</td>
                <td rowspan="2">
                    ・最大有效范围姿势<br>
                    ・铲斗触地＜－＞油缸完全伸出量<br>
                    ・发动机以高怠速运转<br>
                    ・液压油温：４５～５５℃<br>
                    ・工作模式: P模式
                </td>
                <td rowspan="9" style="text-align: center;">秒</td>
                <td colspan="2">最大4.7</td>
                <td data-key="speed_arm_11"></td>
                <td data-key="speed_arm_12"></td>
                <td data-key="speed_arm_13"></td>
            </tr>
            <tr data-condition="最大3.7">
                <td colspan="2">最大3.7</td>
                <td data-key="speed_arm_21"></td>
                <td data-key="speed_arm_22"></td>
                <td data-key="speed_arm_23"></td>
            </tr>
            <tr data-condition="最大4.5">
                <td rowspan="2">斗杆速度</td>
                <td rowspan="2">
                    ・斗杆直角位置<br>
                    ・油缸完全缩回＜－＞油缸完全伸出量<br>
                    ・发动机高速空转<br>
                    ・液压油温：４５～５５℃<br>
                    ・工作模式: P模式
                </td>
                <td colspan="2">最大4.5</td>
                <td data-key="speed_dipper_11"></td>
                <td data-key="speed_dipper_12"></td>
                <td data-key="speed_dipper_13"></td>
            </tr>
            <tr data-condition="最大3.5">
                <td colspan="2">最大3.5</td>
                <td data-key="speed_dipper_21"></td>
                <td data-key="speed_dipper_22"></td>
                <td data-key="speed_dipper_22"></td>
            </tr>
            <tr data-condition="最大3.3">
                <td rowspan="2">铲斗速度</td>
                <td rowspan="2">
                    ・最大有效范围姿势<br>
                    ・油缸完全缩回＜－＞完全伸出<br>
                    ・发动机以高怠速运转<br>
                    ・液压油温：４５～５５℃<br>
                    ・工作模式:P模式
                </td>
                <td colspan="2">最大3.3</td>
                <td data-key="speed_bucket_11"></td>
                <td data-key="speed_bucket_12"></td>
                <td data-key="speed_bucket_13"></td>
            </tr>
            <tr data-condition="最大2.7">
                <td colspan="2">最大2.7</td>
                <td data-key="speed_bucket_21"></td>
                <td data-key="speed_bucket_22"></td>
                <td data-key="speed_bucket_23"></td>
            </tr>
            <tr data-condition="最大1.2">
                <td rowspan="3">时滞</td>
                <td>动臂</td>
                <td>
                    ・从最大有效范围向上抬起的姿势在地面上让铲斗触地,由此车体提高的时间<br>
                    ・发动机低怠速<br>
                    ・液压油温：４５～５５℃
                </td>
                <td colspan="2">最大1.2</td>
                <td data-key="timelag_arm_1"></td>
                <td data-key="timelag_arm_2"></td>
                <td data-key="timelag_arm_3"></td>
            </tr>
            <tr data-condition="最大2.8">
                <td>斗杆</td>
                <td>
                    ・用最大有效范围姿势将斗杆完全缩回，斗杆瞬间停止时的停止時間<br>
                    ・发动机低怠速<br>
                    ・液压油温：４５～５５℃
                </td>
                <td colspan="2">最大2.8</td>
                <td data-key="timelag_dipper_1"></td>
                <td data-key="timelag_dipper_2"></td>
                <td data-key="timelag_dipper_3"></td>
            </tr>
            <tr data-condition="最大3.6">
                <td>铲斗</td>
                <td>
                    ・用最大有效范围姿势将铲斗完全缩回，斗杆瞬间停止时的停止時間<br>
                    ・发动机低怠速<br>
                    ・液压油温：４５～５５℃
                </td>
                <td colspan="2">最大3.6</td>
                <td data-key="timelag_bucket_1"></td>
                <td data-key="timelag_bucket_2"></td>
                <td data-key="timelag_bucket_3"></td>
            </tr>
        </table>
        <div style="margin-left:90px;text-align: left;">
            <span>※“Mi”模式仅适用于多功能监控器规格机器</span>
        </div>
    </div>
</div>
<button onclick="printReport()" style="position: absolute;left: 200px;">打印</button>

<script>
    var reportData = ${report};
    function printReport() {
        $('.reportDiv').jqprint({
            debug: false,
            importCSS: true,
            printContainer: false,
            operaSupport: false
        })
    }

    var dataHeighLight = function(td){
        var key = td.attr("data-key");
        var val = reportData[key];
        td.text(val);

        var noHighLight = td.attr("noHighLight");
        if(noHighLight != undefined){
            return;
        }

        //高亮检测
        var conditionTr = td.parent("tr");
        if(conditionTr.length != 1){
          console.error("没找到条件项目."+key);
            return;
        }

        var txt = $.trim(conditionTr.attr("data-condition"));
        var min=0,max=0;
        if(txt.indexOf("±") != -1){
            var idx = txt.indexOf("±");
            var middle = txt.substring(0,idx)*1;
            var bd = txt.substring(idx+1)*1;
            min = middle - bd;
            max = middle +bd;
        }else if(txt.indexOf("-") != -1){
            var idx = txt.indexOf("-");
            min = txt.substring(0,idx)*1;
            max = txt.substring(idx+1)*1;
        }else if(txt.indexOf('最大') != -1){
            var idx = txt.indexOf("最大");
            min = 0;
            max = txt.substring(idx+2)*1;
        }else{
            console.warn("条件无法识别."+key);
            min = 0;
            max = 100;
        }
        if(val >= min && val <= max){
            td.removeClass("highlight");
        }else{
            //超过范围,高亮
            td.addClass("highlight");
        }
    }

    var initData = function(){
        var tds = $("td[data-key]");
        $.each(tds,function (idx,td) {
            var _td = $(td);
            _td.css("text-align","center");
            dataHeighLight(_td);
        });
    }
    $(function(){
        initData();
    });
</script>
</body>
</html>