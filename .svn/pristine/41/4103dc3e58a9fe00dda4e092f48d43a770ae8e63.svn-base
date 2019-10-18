<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main-content">
    <div class="panel" id="contentSection">
            <div class="panel-heading">
                <h3 class="panel-title"></h3>
            </div>
            <div class="panel-body">
                
                    <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>设备类型ID</th>
                        <th>描述</th>
                        <th>传感器</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="deviceType" items="${list}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${deviceType.id}</td>
                            <td>${deviceType.description}</td>
                            <td>
                                <table class="striped">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>描述</th>
                                        <th>时间分区</th>
                                        <th>数据类型</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="sensor" items="${deviceType.sensors}">
                                        <tr>
                                            <th>${sensor.id}</th>
                                            <th>${sensor.description}</th>
                                            <th>${sensor.timePartition}</th>
                                            <th>${sensor.valueType}</th>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

        </div>
    </div>
</div>
