<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="section no-pad-bot" id="searchSection">
    <div class="container">
        <table class="responsive-table" id="orderDetail">
            <tr>
                <td>工单号</td>
                <td data-key="orderno"></td>
                <td>创建时间</td>
                <td data-key="createtime"></td>
                <td>发起方</td>
                <td data-key="createtype"></td>
                <td>工单类别</td>
                <td data-key="ordertype"></td>
            </tr>
            <tr>
                <td>设备号</td>
                <td data-key="vcl_no"></td>
                <td>品牌</td>
                <td data-key="vcl_brand"></td>
                <td>型号</td>
                <td data-key="vcl_type"></td>
                <td>设备位置</td>
                <td data-key="vcl_des"></td>
            </tr>
            <tr>
                <td>报修人</td>
                <td data-key="complainperson"></td>
                <td>客户</td>
                <td data-key="clnt_name"></td>
                <td>客户手机</td>
                <td data-key="clnt_mobile"></td>
                <td>地址</td>
                <td data-key="clnt_adrs"></td>
            </tr>
            <tr>
                <td>服务人</td>
                <td data-key="svcperson"></td>
                <td>小时数</td>
                <td data-key="vcl_worktime"></td>
                <td>工单状态</td>
                <td data-key="status"></td>
                <td>问题描述</td>
                <td data-key="fault_des"></td>
            </tr>
            <tr>
                <td>开始时间</td>
                <td data-key="startTime"></td>
                <td>开始位置</td>
                <td data-key="startLonLat"></td>
                <td>结束时间</td>
                <td data-key="endTime"></td>
                <td>结束位置</td>
                <td data-key="endLonLat"></td>
            </tr>
            <tr>
                <td>服务开始时间</td>
                <td data-key="serviceStartTime"></td>
                <td>服务开始位置</td>
                <td data-key="serviceStartLonLat"></td>
                <td>服务结束时间</td>
                <td data-key="serviceEndTime"></td>
                <td>服务结束位置</td>
                <td data-key="serviceEndLonLat"></td>
            </tr>
            <tr>
                <td>工单结果</td>
                <td data-key="orderResult"></td>
                <td>工单未完原因</td>
                <td data-key="orderNotReason"></td>
                <td>用户评价</td>
                <td data-key="clientResult"></td>
                <td>服务内容</td>
                <td data-key="serviceDesc"></td>
            </tr>
            <tr>
                <td>服务里程</td>
                <td data-key="miles"></td>
                <td>设备工时</td>
                <td data-key="workHour"></td>
                <td>是否支援</td>
                <td data-key="supportType"></td>
                <td>是否有偿</td>
                <td data-key="isCharge"></td>
            </tr>
            <tr>
                <td>零件费应收</td>
                <td data-key="partFee1"></td>
                <td>零件费实收</td>
                <td data-key="partFee2"></td>
                <td>修理工时费应收</td>
                <td data-key="repairFee1"></td>
                <td>修理工时费实收</td>
                <td data-key="repairFee2"></td>
            </tr>
            <tr>
                <td>诊断工时费应收</td>
                <td data-key="diagnosisFee1"></td>
                <td>诊断工时费实收</td>
                <td data-key="diagnosisFee1"></td>
                <td>视频数量</td>
                <td data-key="videoCount"></td>
                <td>照片数量</td>
                <td data-key="imageCount"></td>
            </tr>
        </table>
    </div>
    <div class="divider"></div>
    <div class="container">
        <div class="row" id="imageResContent">
        </div>
    </div>
    <div class="divider"></div>
    <div class="container">
        <div class="row" id="videoResContent">
        </div>
    </div>
</div>

<div id="playVideoModal" class="modal modal-fixed-footer">
    <div class="modal-content">
        <div class="row center">
            <video id="playVideo" controls="controls" autoplay="autoplay" loop="loop">
                您的浏览器不支持 video 标签。
            </video>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" id="playVideoCloseBtn" class="modal-action waves-effect waves-red btn-flat">关闭</a>
    </div>
</div>
<div id="playImageModal" class="modal modal-fixed-footer">
    <div class="modal-content">
        <div class="row center">
            <image id="playImage">
            </image>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" id="playImageCloseBtn" class="modal-action waves-effect waves-red btn-flat">关闭</a>
    </div>
</div>

<script>
    var serviceOrderPage = {};
    serviceOrderPage.orderDetail = ${orderDetail};
    serviceOrderPage.orderForm = ${orderForm};
    serviceOrderPage.resList = ${resList};
</script>
<script src="/static/js/serviceOrder.js?v=${version}"></script>


