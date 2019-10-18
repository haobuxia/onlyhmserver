<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title">工单策略  单号:${workOrder.orderNo}，类型:${workOrder.orderTypeName}，状态:${workOrder.orderStateName}</h3>
        </div>
        <c:if test="${workOrder.orderState == 'INIT'}">
            <div class="panel-body col-md-6" id="searchSection">
                <div class="input-group" id="searchDiv">
                        <span class="input-group-btn"><button class="btn btn btn-warning" type="submit" id="add-button"
                                                              title="点击添加新的策略">
                                        <i class="fa fa-plus"></i>
                                    </button></span>
                </div>
            </div>
        </c:if>
    </div>
    <div class="panel">
        <div class="panel-heading">
            <h3 class="panel-title">工单策略列表</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>优先级</th>
                    <th>触发事件类型</th>
                    <th>触发事件值</th>
                    <th>动作事件类型</th>
                    <th>动作事件值</th>
                    <c:if test="${workOrder.orderState == 'INIT'}">
                        <th>操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody id="contactTableBody">
                <c:forEach var="strategy" items="${strategyList}" varStatus="status">
                    <tr data-id="${strategy.id}">
                        <td>${status.index+1}</td>
                        <td>${strategy.priority}</td>
                        <th>${strategy.enventTypeName}</th>
                        <td>${strategy.eventVal}</td>
                        <td>${strategy.actionTypeName}</td>
                        <td>${strategy.actionVal}</td>
                        <c:if test="${workOrder.orderState == 'INIT'}">
                            <td><a onclick="removeStrategy(this)" class="label label-success">移除</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<div id="editStrategyModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="editStrategyModalTitle">添加策略</h4>
            <p></p>
            <div id="editForm">
                <div class="input-group">
                    <input type="text" id="editStrategy_orderNo" placeholder="工单单号" class="form-control" value="${workOrder.orderNo}"
                           readonly>
                    <span class="input-group-addon">工单单号</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editStrategy_priority" placeholder="优先级(整数)" class="form-control" value="">
                    <span class="input-group-addon">优先级</span>
                </div>
                <div class="input-group">
                    <select id="editStrategy_eventType" onchange="eventTypeChanged()" class="selectpicker">
                        <c:forEach items="${eventTypeMap}" var="entry">
                            <option value="${entry.key}">${entry.value}</option>
                        </c:forEach>
                    </select>
                    <span class="input-group-addon">事件类型</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editStrategy_eventVal" placeholder="事件值" class="form-control" value="">
                    <span class="input-group-addon">事件值</span>
                </div>
                <div class="input-group">
                    <select class="selectpicker" id="editStrategy_actionType" onchange="actionTypeChanged()">
                        <c:forEach items="${actionTypeMap}" var="entry">
                            <option value="${entry.key}">${entry.value}</option>
                        </c:forEach>
                    </select>
                    <span class="input-group-addon">动作类型</span>
                </div>
                <div class="input-group">
                    <input type="text" id="editStrategy_actionVal" placeholder="动作值" class="form-control" value="">
                    <span class="input-group-addon">动作值</span>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a href="javascript:void(0)" id="editStrategy-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

<script>
    var workorderStrategyPage = {};
    workorderStrategyPage.orderNo = "${workOrder.orderNo}";
    workorderStrategyPage.orderState = "${workOrder.orderState}";
</script>
<script src="/static/js/workOrderStrategy.js?v=${version}"></script>