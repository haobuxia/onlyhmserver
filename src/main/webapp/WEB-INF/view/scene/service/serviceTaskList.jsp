<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>任务号</th>
                    <th>时间</th>
                    <th>机号</th>
                    <th>服务类别</th>
                    <th>服务内容</th>
                    <th>用户名称</th>
                    <th>联系人</th>
                    <th>工单状态</th>
                    <th>服务人员</th>
                    <th>查看</th>
                </tr>
                </thead>
                <tbody id="taskListBody">
                <c:forEach var="task" items="${vo.list}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${task.rwh}</td>
                        <td><fmt:formatDate type="date" value="${task.sendTime}" /></td>
                        <td>${task.jh}</td>
                        <td>${task.fwlb}</td>
                        <td>${task.fwnr}</td>
                        <td>${task.yhmc}</td>
                        <td>${task.lxr}</td>
                        <td data-rwzt="${task.rwzt}">${task.rwzt}</td>
                        <td>${task.oprtName}</td>
                        <td><a class="label label-success"  data-rwh="${task.rwh}" data-oprtId="${task.oprtId}" data-rwlb="${task.fwlb}" class="label label-success">查看</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
    <jsp:include page="../../include/new-page-pager.jsp"></jsp:include>
<script src="/static/js/serviceTaskList.js?v=${version}"></script>


