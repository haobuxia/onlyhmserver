<%--
  Created by IntelliJ IDEA.
  User: wenxinyan
  Date: 2018/9/25
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/static/bootstrap-select/bootstrap-select.min.css">
<link rel="stylesheet" href="/static/bootstrap-datetimepicker/bootstrap-datepicker.min.css">

<script src="/static/bootstrap-datetimepicker/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="/static/bootstrap-datetimepicker/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
<style>
    em {
        font-style: normal;
        font-size: 10px;
        color: red;
    }

    .input-group {
        margin-top: 10px;
        margin-bottom: 0px;
    }
</style>

<div class="main-content">
    <div class="panel clearfix" id="searchSection">
        <div class="panel-heading">
            <h3 class="panel-title"></h3>
        </div>

        <div class="panel-body col-md-6" id="searchDiv">
            <div class="input-group">
                <div id="companyDiv">
                    <select class="form-control" id="organisation">
                        <option value="">全部</option>
                    </select>
                </div>
                <label for="organisation" class="input-group-addon">所属单位</label>

                <span class="input-group-btn">
                    <button class="btn btn btn-primary" type="submit" id="search-button" title="点击搜索">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
                <span class="input-group-btn">
                    <button class="btn btn btn-warning" type="button" id="more-button" title="更多筛选">
                        更多筛选
                    </button>
                </span>

                <span class="input-group-btn">
                    <button class="btn btn btn-danger pull-right" type="button" id="add-button" title="新增用户">
                        <i class="fa fa-plus"></i>
                    </button>
                </span>
            </div>

            <div id="show-more" style="display: none">
                <div class="input-group" id="nameDiv">
                    <input type="text" id="name" placeholder="请输入用户名称" class="form-control" />
                    <label for="name" class="input-group-addon" value="">用户名称</label>
                </div>

                <div class="input-group" id="timeDiv1">
                    <input type="text" id="time1" placeholder="请选择起始时间范围" class="form-control" />
                    <label for="time1" class="input-group-addon" value="">注册时间</label>
                </div>

                <div class="input-group" id="timeDiv2">
                    <input type="text" id="time2" placeholder="请选择结束时间范围" class="form-control" />
                    <label for="time2" class="input-group-addon" value="">注册时间</label>
                </div>
            </div>
        </div>
    </div>

    <div class="panel-body" id="contentSection">
        <%--<div class="panel-heading">
            <h3 class="panel-title">用户列表</h3>
        </div>--%>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>账号</th>
                    <th>名称</th>
                    <th>性别</th>
                    <th>生日</th>
                    <th>联系电话</th>
                    <th>所属单位</th>
                    <th>所属部门</th>
                    <th>注册日期</th>
                    <th>角色</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

        <!-- 分页 -->
        <div class="panel-headline" id="new-page-pager">
            <div class="panel-body">
                <p class="demo-button">
                    <span id="pageInfo">共 0 条数据 , 第 0 页 , 共 0 页</span>
                    , 跳转到
                    <input type="text" style="width:50px;" id="toPage"> 页
                    <button type="button" class="btn btn-xs btn-primary" id="toPageButton">GO</button>
                    &nbsp;
                    <!-- 禁用按钮 -->
                    <button type="button" class="btn btn-xs btn-primary" id="prePageButton">上一页</button>
                    &nbsp;
                    <!-- 可用按钮 -->
                    <button type="button" class="btn btn-xs btn-primary" id="nextPageButton">下一页</button>
                </p>
            </div>
        </div>

    </div>
</div>

<!-- END MAIN -->
<div class="clearfix"></div>
<!-- END WRAPPER -->

<div id="addUserModal" class="modal" style="top:20%">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="addUserModalTitle">添加用户信息</h4>
            <p></p>
            <div id="addForm">
                <div class="input-group">
                    <input type="text" id="add_account" placeholder="登录账号" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)" />
                    <span class="input-group-addon">登录账号*</span>
                </div>
                <div><em class="add_account_error"></em></div>
                <div class="input-group">
                    <input type="text" id="add_name" placeholder="用户姓名" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)" />
                    <span class="input-group-addon">用户姓名*</span>
                </div>
                <div><em class="add_name_error"></em></div>
                <div class="input-group">
                    <select class="form-control" id="add_sex">
                        <option value="0" selected>未知</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                    <span class="input-group-addon">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_birthday" placeholder="生日(格式:1990-01-01,默认:1990-01-01)" class="form-control" value="1990-01-01" />
                    <span class="input-group-addon">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_mobile" placeholder="电话" class="form-control" value="" onblur="checkMobile(this);" onfocus="checkFocus(this);" />
                    <span class="input-group-addon">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话*</span>
                </div>
                <div><em class="add_mobile_error"></em></div>
                <div class="input-group">
                    <div id="addCompanyDiv">
                        <select class="form-control" id="add_organisation">
                        </select>
                    </div>
                    <span class="input-group-addon">所属公司*</span>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" id="add_department" placeholder="所属部门" value="" />
                    <span class="input-group-addon">所属部门&nbsp;</span>
                </div>
                <div class="input-group">
                    <select id="add_roleCodes" class="form-control selectpicker" title="请选择角色" multiple >
                    </select>
                    <span class="input-group-addon">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色*</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_tianyuanAccount" placeholder="" class="form-control" value="" />
                    <span class="input-group-addon">天远账号 </span>
                </div>
            </div>
        </div>

        <div class="modal-footer">
            <a href="javascript:void(0)" id="addsubmit-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

<div id="editUserModal" class="modal" style="top:20%">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="editUserModalTitle">修改用户信息</h4>
            <p></p>
            <div id="editForm">
                <div class="input-group" style="display: none">
                    <input type="text" id="edit_id" placeholder="登录ID" class="form-control" value="" readonly />
                </div>
                <div class="input-group">
                    <input type="text" id="edit_account" placeholder="登录账号" class="form-control" value="" disabled />
                    <span class="input-group-addon">登录账号*</span>
                </div>
                <div class="input-group">
                    <input type="text" id="edit_password" placeholder="账号密码" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)" />
                    <span class="input-group-addon">账号密码*</span>
                </div>
                <div><em class="edit_password_error"></em></div>
                <div class="input-group">
                    <input type="text" id="edit_name" placeholder="用户姓名" class="form-control" value="" />
                    <span class="input-group-addon">用户姓名*</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="edit_sex">
                        <option value="0">未知</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                    <span class="input-group-addon">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;</span>
                </div>
                <div class="input-group">
                    <input type="text" id="edit_birthday" placeholder="生日(格式:2000-01-01,默认:2000-01-01)" class="form-control" value="" />
                    <span class="input-group-addon">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;</span>
                </div>
                <div class="input-group">
                    <input type="text" id="edit_mobile" placeholder="电话" class="form-control" value="" onblur="checkMobile(this);" onfocus="checkFocus(this);" />
                    <span class="input-group-addon">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话*</span>
                </div>
                <div><em class="edit_mobile_error"></em></div>
                <div class="input-group">
                    <div id="editCompanyDiv">
                        <select class="form-control" id="edit_organisation" >
                        </select>
                    </div>
                    <span class="input-group-addon">所属公司&nbsp;</span>
                </div>
                <div class="input-group">
                    <input type="text" class="form-control" id="edit_department" placeholder="所属部门" value="" />
                    <span class="input-group-addon">所属部门&nbsp;</span>
                </div>
                <div class="input-group">
                    <select id="edit_roleCodes" class="form-control selectpicker" title="请选择角色" multiple >
                    </select>
                    <span class="input-group-addon">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色*</span>
                </div>
                <div class="input-group">
                    <input type="text" id="edit_tianyuanAccount" placeholder="" class="form-control" value="" />
                    <span class="input-group-addon">天远账号 </span>
                </div>
            </div>
        </div>

        <div class="modal-footer">
            <a href="javascript:void(0)" id="editsubmit-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

<script src="/static/bootstrap-select/bootstrap-select.min.js"></script>
<script>
    var userList = {};
    var newPagePager = {};
    var organisations = new Array;
    var roleList = new Array;
</script>
<script src="/static/js/userManage.js?v=${version}"></script>