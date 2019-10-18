<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="panel ">
        <%--<div class="panel-body">--%>
            <%--<table class="table table-striped">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th>序号</th>--%>
                    <%--<th>头盔序号</th>--%>
                    <%--<th>硬件识别码</th>--%>
                    <%--<th>型号</th>--%>
                    <%--<th>批次</th>--%>
                    <%--<th>初始化时间</th>--%>
                    <%--<th>状态</th>--%>
                    <%--<c:if test="${saleState==1}">--%>
                        <%--<th>出厂时间</th>--%>
                        <%--<th style="width: 400px;">客户</th>--%>
                    <%--</c:if>--%>
                    <%--<th>网易账号</th>--%>
                    <%--<th>田一用户</th>--%>
                    <%--<th>天远用户</th>--%>
                    <%--<th>操作</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody>--%>
                <%--<c:forEach var="helmet" items="${vo.list}" varStatus="status">--%>
                    <%--<tr>--%>
                        <%--<td>${status.count}</td>--%>
                        <%--<td>${helmet.id}</td>--%>
                        <%--<td>${helmet.imei}</td>--%>
                        <%--<td>${helmet.model}</td>--%>
                        <%--<td>${helmet.batch}</td>--%>
                        <%--<td>${helmet.getActiveTimeStr()}</td>--%>
                        <%--<td>${helmet.getSaleStateStr()}</td>--%>
                        <%--<c:if test="${saleState==1}">--%>
                            <%--<td>${helmet.getFactoryTimeStr()}</td>--%>
                            <%--<td data-customerId="${helmet.affiliationId}">${helmet.affiliationName}</td>--%>
                        <%--</c:if>--%>
                        <%--<td data-neuserId="${helmet.neUserId}">${helmet.neUsername}</td>--%>
                        <%--<td>${helmet.tianyiUsername}</td>--%>
                        <%--<td>${helmet.tianYuanUsername}</td>--%>
                        <%--<td>--%>
                            <%--<a onclick="editHelmet(${helmet.id},'${helmet.model}','${helmet.batch}')"--%>
                               <%--class="label label-success">修改</a>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${helmet.saleState == 0}"><!-- toinit-->--%>
                                    <%--<a onclick="initAccount(${helmet.id},1)" class="label label-warning">初始化</a>--%>
                                    <%--<a onclick="delHelmet(${helmet.id})" class="label label-danger">删除</a>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${helmet.saleState == 1}"><!-- sold -->--%>
                                    <%--<a onclick="initAccount(${helmet.id},0)"--%>
                                       <%--class="label label-success">查看二维码</a>--%>
                                    <%--<a onclick="loadApkUpdateList('${helmet.imei}')"--%>
                                       <%--class="label label-primary">升级记录</a>--%>
                                    <%--<a onclick="openXmlModel('${helmet.neUsername}')"--%>
                                       <%--class="label label-primary">升级文件</a>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${helmet.saleState == 2 || helmet.saleState == 3}"> <!-- tofactory,test -->--%>
                                    <%--<a class="label label-primary" onclick="initAccount(${helmet.id},0)">重新扫码</a>--%>
                                    <%--<a class="label label-info" onclick="regHelmet(${helmet.id})">出厂登记</a>--%>
                                <%--</c:when>--%>
                            <%--</c:choose>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</div>--%>
        <%--<jsp:include page="../include/new-page-pager.jsp"></jsp:include>--%>
    </div>