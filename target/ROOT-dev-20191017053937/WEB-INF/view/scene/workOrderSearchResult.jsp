<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel-heading">
    <h3 class="panel-title">工单列表</h3>
</div>
<div class="panel-body">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>序号</th>
            <th>工单号</th>
            <th>服务人员</th>
            <th>工单类型</th>
            <th>工单主题</th>
            <th>客户</th>
            <th>联系人</th>
            <th>车辆机型</th>
            <th>车辆机号</th>
            <th style="width: 400px;">备注</th>
            <th>标签</th>
            <th>工单状态</th>
            <th>排班时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="contactTableBody">
        <c:forEach var="workorder" items="${vo.list}" varStatus="status">
            <tr data-id="${workorder.id}" data-orderNo="${workorder.orderNo}" data-orderState="${workorder.orderState}">
                <td>${status.index+1}</td>
                <td>${workorder.orderNo}</td>
                <td data-userId="{workorder.userId}">${workorder.userRealName}</td>
                <th>${workorder.orderTypeName}</th>
                <td>${workorder.subject}</td>
                <td>${workorder.customerName}</td>
                <td>${workorder.contactName}</td>
                <td>${workorder.model}</td>
                <td>${workorder.machineCode}</td>
                <td>${workorder.remark}</td>
                <td>${workorder.tags}</td>
                <td>${workorder.orderStateName}</td>
                <td><fmt:formatDate type="both" value="${workorder.planTime}" /></td>
                <td><a onclick="removeWorkOrder(this)" class="label label-danger">移除</a><a onclick="editWorkOrder(this)" class="label label-info">修改</a><a onclick="editStrategy(this)" class="label label-info">策略</a><a onclick="detailsWorkOrder(this)" class="label label-success">详情</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../include/new-page-pager.jsp"></jsp:include>
