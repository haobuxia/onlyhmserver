<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title">网易用户</h3>
        </div>
        <div class="panel-body col-md-6"  id="searchSection">
            <div class="input-group" id="searchDiv">
                <input type="text" id="keyword"  placeholder="请输入账号或昵称" class="form-control" value="${keyword}">
                <span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="search-button" title="点击根据用户名搜索">
			                    <i class="fa fa-search"></i>
			                </button></span>
            </div>
        </div>
    </div>
    <div class="panel" id="contentSection">
        <div class="panel-heading">
            <h3 class="panel-title">网易用户列表</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>用户ID</th>
                    <th>用户类型</th>
                    <th>账号</th>
                    <th>注册时间</th>
                    <th>昵称</th>
                    <th>云ID</th>
                    <th>设备ID</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${vo.list}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${user.id}</td>
                        <td>${user.getUserTypeStr()}</td>
                        <td>${user.username}</td>
                        <td>${user.getRegTimeString()}</td>
                        <td>${user.nickName}</td>
                        <td>${user.yunId}</td>
                        <td>${user.userId}</td>
                        <td>

                            <c:choose>
                                <c:when test="${user.userType==1}">
                                    <!-- 头盔app用户 -->
                                    <a onclick="viewPwd('${user.username}',${user.id});" class="label label-success">查看密码</a>
                                    <a onclick="chgPwdForm('${user.username}',${user.id}); " class="label label-success">修改密码</a>
                                </c:when>
                            </c:choose>
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


<div id="chgPwdModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4>修改密码</h4>
            <p></p>
            <div id="editForm">
                <div class="input-group">
                    <input type="hidden" id="useredit_id" readonly>
                    <input type="text" id="useredit_username" placeholder="客户姓名" class="form-control" readonly>
                    <span class="input-group-addon">客户姓名</span>
                </div>
                
                <div class="input-group">
                    <input type="password" id="useredit_password" placeholder="请输入新密码" class="form-control" value="">
                    <span class="input-group-addon">新密码</span>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a href="javascript:void(0)" id="useredit-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

    <script>
        var listNeteaseUserPage = {};
        listNeteaseUserPage.keyword = '${keyword}';
    </script>
    <script src="/static/js/listNeteaseUser.js?v=${version}"></script>
