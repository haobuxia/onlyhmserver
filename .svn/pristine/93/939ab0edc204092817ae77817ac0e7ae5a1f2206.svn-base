<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/static/bootstrap-select/bootstrap-select.min.css">

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title">注册用户</h3>
        </div>
        <div class="panel-body col-md-6" id="searchSection">
            <div class="input-group" id="searchDiv">
                <input type="text" id="keyword" placeholder="请输入账号、姓名、手机号、公司搜索" class="form-control" value="${keyword}">
                <span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="search-button"
                                                      title="点击根据用户名搜索">
			                    <i class="fa fa-search"></i>
			                </button></span>
                <span class="input-group-btn"><button class="btn btn btn-warning" type="submit" id="add-button"
                                                      title="点击添加1个用户">
			                    <i class="fa fa-plus"></i>
			                </button></span>
            </div>
        </div>
    </div>

    <div class="panel" id="contentSection">
        <div class="panel-heading">
            <h3 class="panel-title">注册用户列表</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>ID</th>
                    <th>账号</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>公司</th>
                    <th>部门</th>
                    <th>性别</th>
                    <th>注册时间</th>
                    <%--<th>网易账号</th>--%>
                    <th>佩戴头盔</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${vo.list}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.name}</td>
                        <td>${user.mobile}</td>
                        <td>${user.organisation}</td>
                        <td>${user.department}</td>
                        <td>${user.sex}</td>
                        <td>${user.getRegTimeString()}</td>
                        <%--<td>${user.neUsername}</td>--%>
                        <td>${user.helmet.deviceNumber}</td>
                        <td><a onclick="editUser(${user.id})" class="label label-success">修改</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="../include/new-page-pager.jsp"></jsp:include>
    </div>

</div>
<!-- END MAIN -->
<div class="clearfix"></div>
<!-- END WRAPPER -->


<div id="editUserModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="editUserModalTitle">修改客户信息</h4>
            <p></p>
            <div id="editForm">
                <div class="input-group">
                    <input type="hidden" id="useredit_id" readonly>
                    <input type="text" id="useredit_username" placeholder="登录账号" class="form-control" value="">
                    <span class="input-group-addon">登录账号（不可helmet开头）</span>
                </div>
                <br>
                <div class="input-group">
                    <input type="text" id="useredit_password" placeholder="密码" class="form-control" value=""
                           data-length="15" required maxlength="15">
                    <span class="input-group-addon">账号密码</span>
                </div>
                <div class="input-group">
                    <input type="text" id="useredit_name" placeholder="客户姓名" class="form-control" value="">
                    <span class="input-group-addon">姓名</span>
                </div>
                <div class="input-group">
                    <input type="text" id="useredit_mobile" placeholder="请输入手机号" class="form-control" value="">
                    <span class="input-group-addon">手机号</span>
                </div>

                <div class="input-group">
                    <select class="form-control" id="useredit_userSex">
                        <option value="2" selected>未知</option>
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                    <span class="input-group-addon">性别</span>
                </div>
                <div class="input-group">
                    <select id="useredit_userRole" class="form-control selectpicker" title="请选择角色" multiple >
                        <option value="demo" selected>演示账号</option>
                        <option value="admin">管理员</option>
                        <option value="sales">销售员</option>
                        <option value="customer">客户</option>
                        <option value="driver">机手</option>
                    </select>
                    <span class="input-group-addon">角色</span>
                </div>

                <div class="input-group">
                    <input type="text" id="useredit_company" placeholder="请输入公司名称" class="form-control" value="">
                    <span class="input-group-addon">公司名称</span>
                </div>
                <div class="input-group">
                    <input type="text" id="useredit_dept" placeholder="请输入部门名称" class="form-control" value="">
                    <span class="input-group-addon">部门名称</span>
                </div>
                <div class="input-group">
                    <input type="text" id="useredit_neUsername" placeholder="绑定已存在的账号则输入，否则请置空" class="form-control"
                           value="">
                    <span class="input-group-addon">网易账号</span>
                </div>

            </div>
        </div>
        <div class="modal-footer">
            <a href="javascript:void(0)" id="useredit-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

<script src="/static/bootstrap-select/bootstrap-select.min.js"></script>
<script>
    var listTyUserPage = {};
    listTyUserPage.keyword = '${keyword}';
</script>
<script src="/static/js/listTianyiUser.js?v=${version}"></script>
