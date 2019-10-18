<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title">我的联系人</h3>
        </div>
        <div class="panel-body col-md-6" id="searchSection">
            <div class="input-group" id="searchDiv">
                <span class="input-group-btn"><button class="btn btn btn-warning" type="submit" id="add-button"
                                                      title="点击添加联系人">
			                    <i class="fa fa-plus"></i>
			                </button></span>
            </div>
        </div>
    </div>

    <div class="panel" id="contentSection">
        <div class="panel-heading">
            <h3 class="panel-title">联系人列表</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>公司</th>
                    <th>部门</th>
                    <th>性别</th>
                    <th>备注</th>
                    <th>添加时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="contactTableBody">
                <c:forEach var="contact" items="${contactList}" varStatus="status">
                    <tr data-contactId="${contact.contactId}">
                        <td>${status.index+1}</td>
                        <td>${contact.contactUser.name}</td>
                        <td>${contact.contactUser.mobile}</td>
                        <td>${contact.contactUser.displayOrg}</td>
                        <td>${contact.contactUser.department}</td>
                        <td>
                            <c:if test="${contact.contactUser.sex == 0}">未知</c:if>
                            <c:if test="${contact.contactUser.sex == 1}">男</c:if>
                            <c:if test="${contact.contactUser.sex == 2}">女</c:if>
                        </td>
                        <td>${contact.remark}</td>
                        <td><fmt:formatDate type="date" value="${contact.addTime}" /></td>
                        <td><a onclick="removeContact(${contact.contactId})" class="label label-success">移除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
<!-- END MAIN -->
<div class="clearfix"></div>
<!-- END WRAPPER -->

<div id="editContactModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="editContactModalTitle">添加联系人</h4>
            <p></p>
            <div id="editForm">
                <div class="input-group">
                    <input type="text" id="editContact_userRealName" placeholder="联系人姓名" class="form-control" value="">
                    <span class="input-group-addon">联系人姓名</span>
                </div>
                <br>
                <div class="input-group">
                    <input type="text" id="editContact_remark" placeholder="联系人备注" class="form-control" value="">
                    <span class="input-group-addon">联系人备注</span>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a href="javascript:void(0)" id="editContact-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

<script src="/static/js/listTianyiContact.js?v=${version}"></script>
