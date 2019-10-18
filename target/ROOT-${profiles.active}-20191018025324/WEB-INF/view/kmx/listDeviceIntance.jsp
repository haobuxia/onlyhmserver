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
                            <th>设备ID</th>
                            <th>描述</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="device" items="${list}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${device.deviceTypeId}</td>
                                <td>${device.id}</td>
                                <td>${device.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
            </div>
    </div>
</div>
