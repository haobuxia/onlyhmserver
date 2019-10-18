<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title">客户管理</h3>
        </div>
        <div class="panel-body col-md-6"  id="searchSection">
            <div class="input-group" id="searchDiv">
                <input type="text" id="keyword"  placeholder="请输入客户姓名、手机号或公司名" class="form-control" value="${keyword}">
                <span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="search-button" title="点击根据客户姓名搜索">
			                    <i class="fa fa-search"></i>
			                </button></span>
                <span class="input-group-btn"><button class="btn btn btn-warning" type="submit" id="add-button" title="新增客户">
			                    <i class="fa fa-plus"></i>
			                </button></span>
            </div>
        </div>
    </div>

    <div class="panel" id="contentSection">
        <div class="panel-heading">
            <h3 class="panel-title">待售头盔列表</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>公司</th>
                    <th>部门</th>
                    <th>地址</th>
                    <th>登记时间</th>
                    <th>修改时间</th>
                    <th>销售员</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="customer" items="${vo.list}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${customer.id}</td>
                        <td>${customer.custName}</td>
                        <td>${customer.mobile}</td>
                        <td>${customer.company}</td>
                        <td>${customer.department}</td>
                        <td>${customer.address}</td>
                        <td>${customer.getCreateTimeStr()}</td>
                        <td>${customer.getModifyTimeStr()}</td>
                        <td>${customer.saleUserName}</td>
                        <td><a onclick="chgCustomerForm(${customer.id})" class="label label-success">修改</a></td>
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
</div>
</div>
<!-- END WRAPPER -->


<div id="chgCustomerModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="chgCustomerModalTitle">修改客户信息</h4>
            <p></p>
            <div id="editForm">
                <div class="input-group">
                    <input type="hidden" id="useredit_id" readonly>
                    <input type="text" id="useredit_name" placeholder="客户姓名" class="form-control" value="">
                    <span class="input-group-addon">客户姓名</span>
                </div>
                <div class="input-group">
                    <input type="text" id="useredit_mobile" placeholder="请输入手机号" class="form-control" value="">
                    <span class="input-group-addon">手机号</span>
                </div>
                <div class="input-group">
                    <input type="text" id="useredit_company" placeholder="请输入公司名称" class="form-control" value="">
                    <span class="input-group-addon">公司名称</span>
                </div>
                <div class="input-group">
                    <input type="text" id="useredit_depart" placeholder="请输入部门名称" class="form-control" value="">
                    <span class="input-group-addon">部门名称</span>
                </div>
                <div class="input-group">
                    <input type="text" id="useredit_address" placeholder="请输入公司地址" class="form-control" value="">
                    <span class="input-group-addon">公司地址</span>
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
        var listCustUserPage = {};
        listCustUserPage.keyword = '${keyword}';
    </script>
    <script src="/static/js/listCustUser.js?v=${version}"></script>
