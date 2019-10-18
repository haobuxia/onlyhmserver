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
                        <th>标签名称</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="tag" items="${vo.list}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${tag.id}</td>
                            <td>${tag.tagName}</td>
                            <td>${tag.getCreateTimeString()}</td>
                            <td>
                                <a onclick="deleteTag(${tag.id},'${tag.tagName}')" class="label label-danger" title="删除该标签">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <jsp:include page="../include/new-page-pager.jsp"></jsp:include>
       
    </div>
</div>

<!-- Modal Structure -->
<div id="tagModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <h4 id="tagModalTitle">新增标签</h4>
        <p></p>
        <div id="editForm">
            <div class="input-group">
                <select id="tagedit_group" class="form-control">
                    <c:forEach var="group" items="${groupList}">
                        <option value="${group.id}" <c:if test="${group.id==1}">selected</c:if>>${group.groupName}</option>
                    </c:forEach>
                </select>
                <label for="tagedit_group" class="input-group-addon">标签分组</label>
            </div>
            <div class="input-group">
                <input type="hidden" id="tagedit_id"  value="" readonly>
                <input type="text" id="tagedit_name" placeholder="标签名称"  class="form-control" value="">
                <label for="tagedit_name" class="input-group-addon">标签名称</label>
            </div>
        </div>
        <div class="modal-footer">
            <a href="javascript:void(0)" id="tagdit-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">取消</a>
        </div>
    </div>
    
</div>

<script>
    var listTagPage = {};
    listTagPage.keyword = '${keyword}';
</script>
<script src="/static/js/listTag.js?v=${version}"></script>
