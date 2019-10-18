<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title"></h3>
        </div>
        
        <div class="panel-body col-md-6"  id="searchSection">
            <div class="input-group" id="searchDiv">
                <input type="text" id="keyword"  placeholder="请输入标签名称" class="form-control" value="${keyword}">
                <span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="search-button" title="点击搜索">
                                <i class="fa fa-search"></i>
                            </button></span>
                <span class="input-group-btn"><button class="btn btn btn-warning" type="submit" id="add-button" title="新增">
                            <i class="fa fa-plus"></i>
                        </button></span>
            </div>
        </div>
    </div>


<div class="panel-body" id="contentSection">
            <div class="panel-heading">
                <h3 class="panel-title">标签管理</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>ID</th>
                        <th>标签组名称</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                <c:forEach var="tagGroup" items="${vo.list}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${tagGroup.id}</td>
                        <td>${tagGroup.groupName}</td>
                        <td>${tagGroup.getCreateTimeString()}</td>
                        <td>
                            <a onclick="deleteTagGroup(${tagGroup.id},'${tagGroup.groupName}')" title="删除该组 " class="label label-danger">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <jsp:include page="../include/new-page-pager.jsp"></jsp:include>
    </div>
</div>

<script>
    var listTagGroupPage = {};
    listTagGroupPage.keyword = '${keyword}';
</script>
<script src="/static/js/listTagGroup.js?v=${version}"></script>
