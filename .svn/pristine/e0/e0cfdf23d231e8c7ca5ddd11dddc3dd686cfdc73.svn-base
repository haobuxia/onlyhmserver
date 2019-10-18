<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main-content" id="searchSection">
    <div class="panel clearfix" id="searchDiv">
        <div class="panel-body col-md-6">
            <button class="btn btn-warning" type="submit" id="retry-button" title="全部重试">
                <i class="fa fa-refresh"></i>
                <span>全部重试</span>
            </button>
        </div>
    </div>
</div>

<div class="panel" id="contentSection">
  
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>接收时间</th>
                    <th>消息类型</th>
                    <th>处理状态</th>
                    <th>处理说明</th>
                    <th >消息内容</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="msg" items="${vo.list}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${msg.getTimeStr()}</td>
                        <td>${msg.eventType}</td>
                        <td>${msg.getProcessFlagStr()}</td>
                        <td>${msg.failReason}</td>
                        <td style="max-width:400px;">${msg.fullMsg}</td>
                        <td><a onclick="ignoreFailMsg(${msg.id})" class="label label-warning">忽略</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="../include/new-page-pager.jsp"></jsp:include>
</div>

<script>
    var listNeteaseMsgPage = {};
</script>
<script src="/static/js/listNeteaseMsg.js?v=${version}"></script>
