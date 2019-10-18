<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/static/bootstrap-select/bootstrap-select.min.css">

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title">工单管理</h3>
        </div>
        <div class="panel-body col-md-6" id="searchSection">
            <div class="input-group" id="searchDiv">
                <input type="text" id="keyword" placeholder="输入关键字搜索工单" class="form-control" value="">
                <span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="search-button"
                                                      title="点击根据关键字搜索">
			                    <i class="fa fa-search"></i>
			                </button></span>
                <span class="input-group-btn"><button class="btn btn btn-warning" type="submit" id="add-button"
                                                      title="点击添加新的工单">
			                    <i class="fa fa-plus"></i>
			                </button></span>
            </div>
        </div>
    </div>

    <div class="panel" id="contentSection">
        <!--   数据列表位置 -->
    </div>

</div>
<!-- END MAIN -->
<div class="clearfix"></div>
<!-- END WRAPPER -->

<div id="editWorkOrderModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="editWorkOrderModalTitle">添加工单</h4>
            <p></p>
            <div id="editForm">
                <div class="input-group">
                    <input type="hidden" id="editWorkOrder_id">
                    <input type="text" id="editWorkOrder_orderNo" placeholder="工单单号" class="form-control" value="" readonly>
                    <span class="input-group-addon">工单单号</span>
                </div>
                <div class="input-group">
                    <%--<input type="hidden" id="editWorkOrder_userId">--%>
                    <select id="editWorkOrder_orderType" class="selectpicker form-control">
                        <c:forEach items="${typeMap}" var="entry">
                            <option value="${entry.key}">${entry.value}</option>
                        </c:forEach>
                    </select>
                    <span class="input-group-addon">工单类型</span>
                </div>
                <div class="input-group">
                    <%--<input type="hidden" id="editWorkOrder_userId">--%>
                    <input type="text" id="editWorkOrder_subject" placeholder="工单主题" class="form-control" value="">
                    <span class="input-group-addon">工单主题</span>
                </div>
                <div class="input-group">
                    <%--<input type="hidden" id="editWorkOrder_userId">--%>
                    <input type="text" id="editWorkOrder_userRealName" placeholder="服务人员" class="form-control" value="">
                    <span class="input-group-addon">服务人员</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editWorkOrder_customerName" placeholder="客户名称" class="form-control" value="">
                    <span class="input-group-addon">客户名称</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editWorkOrder_contactName" placeholder="客户联系人" class="form-control" value="">
                    <span class="input-group-addon">客户联系人</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editWorkOrder_contactPhone" placeholder="联系人电话" class="form-control" value="">
                    <span class="input-group-addon">联系人电话</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editWorkOrder_planTime" placeholder="排班时间(yyyyMMdd HHmm)" class="form-control" value="">
                    <span class="input-group-addon">排班时间(yyyyMMdd HHmm)</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editWorkOrder_brand" placeholder="车辆品牌" class="form-control" value="小松">
                    <span class="input-group-addon">车辆品牌</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editWorkOrder_model" placeholder="车辆型号" class="form-control" value="PC220-8">
                    <span class="input-group-addon">车辆型号</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editWorkOrder_machineCode" placeholder="机号" class="form-control" value="">
                    <span class="input-group-addon">机号</span>
                </div>
                <div class="input-group">
                    <textarea id="editWorkOrder_remark" placeholder="请输入工单执行注意事项" class="form-control" style="height: 120px;">本次作业中您需要注意以下环节拍摄留存。</textarea>
                    <span class="input-group-addon">注意事项</span>
                </div>
                <div class="input-group">
                    <select id="editWorkOrder_tags" class="form-control selectpicker" title="请选择工单要拍摄视频类型" multiple >
                        <c:forEach items="${tagList}" var="tag">
                            <option value="${tag.tagName}">${tag.tagName}</option>
                        </c:forEach>
                    </select>
                    <span class="input-group-addon">拍摄视频类型</span>
                </div>
                <div class="input-group">
                    <input type="hidden" id="editWorkOrder_latLng">
                    <input type="text" id="editWorkOrder_address" placeholder="车辆位置" class="form-control" value="">
                    <span class="input-group-addon">车辆位置</span>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a href="javascript:void(0)" id="editWorkOrder-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>
<script>
    var workorderIndexPage = {};
    workorderIndexPage.keyword="";
</script>

<script src="/static/bootstrap-select/bootstrap-select.min.js"></script>
<script src="/static/js/workOrderIndex.js?v=${version}"></script>
