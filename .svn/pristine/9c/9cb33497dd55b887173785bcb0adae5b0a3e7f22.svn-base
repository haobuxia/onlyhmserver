<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title"></h3>
        </div>
        
        <div class="panel-body col-md-6"  id="searchSection">
            <div class="input-group" id="searchDiv">
                <input type="text" id="keyword"  placeholder="搜索暂时不支持" class="form-control" value="${keyword}">
                <span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="search-button" title="点击搜索">
                                <i class="fa fa-search"></i>
                            </button></span>
            </div>
        </div>
    </div>

    <div class="panel-body" id="contentSection">
            <div class="panel-heading">
                <h3 class="panel-title">用户日志</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>用户</th>
                        <th>时间</th>
                        <th>日志类型</th>
                        <th>日志内容</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="log" items="${vo.list}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td data-userId="${log.userId}">${log.userName}</td>
                                    <td>${log.getCreateTimeString()}</td>
                                    <td>${log.getLogTypeName()}</td>
                                    <td>${log.logContent}</td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <jsp:include page="../include/new-page-pager.jsp"></jsp:include>
       
    </div>
</div>

    <script>
        var listUserlogPage = {};
        listUserlogPage.keyword = '${keyword}';
        listUserlogPage.personal = ${personLog};
    </script>
    <script src="/static/js/listUserlog.js?v=${version}"></script>
