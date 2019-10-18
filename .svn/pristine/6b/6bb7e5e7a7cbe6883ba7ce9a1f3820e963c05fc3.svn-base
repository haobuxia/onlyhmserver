<%--
  Created by IntelliJ IDEA.
  User: wenxinyan
  Date: 2018/9/30
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <h3 class="panel-title">单位管理</h3>
        </div>

        <div class="panel-body col-md-8" id="searchDiv">
            <div class="input-group">

                <input type="text" id="companyName" class="form-control" placeholder="请输入单位名称" value="" />
                <label for="companyName" class="input-group-addon">单位名称</label>

                <input type="text" id="contact" class="form-control" placeholder="请输入联系人" value="" />
                <label for="contact" class="input-group-addon">联系人</label>
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
                    <button class="btn btn btn-danger pull-right" type="button" id="add-button" title="新增单位">
                        <i class="fa fa-plus"></i>
                    </button>
                </span>
            </div>

            <div id="show-more" style="display: none">
                <div class="input-group">

                    <input type="text" id="contactNumber" placeholder="请输入联系电话" class="form-control" />
                    <label for="contactNumber" class="input-group-addon">联系电话</label>
                </div>

                <div class="input-group">

                    <select class="form-control" id="province" onchange="changeCity();">
                        <option value="">全部</option>
                    </select>
                    <label for="province" class="input-group-addon">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</label>
                </div>

                <div class="input-group">

                    <select class="form-control" id="city">
                        <option value="">全部</option>
                    </select>
                    <label for="city" class="input-group-addon">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</label>
                </div>
            </div>
        </div>
    </div>

    <div class="panel-body" id="contentSection">
        <%--<div class="panel-heading">
            <h3 class="panel-title">单位列表</h3>
        </div>--%>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>单位名称</th>
                    <th>详细地址</th>
                    <th>单位性质</th>
                    <th>省份</th>
                    <th>城市</th>
                    <th>联系人</th>
                    <th>联系电话</th>
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

<div id="addCompanyModal" class="modal" style="top:100px">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="addCompanyModalTitle">新增单位信息</h4>
            <p></p>
            <div id="addForm1">
                <div class="input-group">
                    <input type="text" id="add_companyName" placeholder="单位名称" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)" />
                    <span class="input-group-addon">单位名称*</span>
                </div>
                <div><em class="add_companyName_error"></em></div>
                <div class="input-group">
                    <input type="text" id="add_address" placeholder="详细地址" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)" />
                    <span class="input-group-addon">详细地址*</span>
                </div>
                <div><em class="add_address_error"></em></div>
                <div class="input-group">
                    <select class="form-control" id="add_companyNature">
                    </select>
                    <span class="input-group-addon">单位性质&nbsp;</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="add_province" onchange="changeAddCity();">
                    </select>
                    <span class="input-group-addon">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份&nbsp;</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="add_city">
                    </select>
                    <span class="input-group-addon">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市&nbsp;</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_contact" placeholder="联系人" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)" />
                    <span class="input-group-addon">联&nbsp;系&nbsp;&nbsp;人*</span>
                </div>
                <div><em class="add_contact_error"></em></div>
                <div class="input-group">
                    <input type="text" id="add_contactNumber" placeholder="联系电话" class="form-control" value="" onblur="checkMobile(this);" onfocus="checkFocus(this);" />
                    <span class="input-group-addon">联系电话*</span>
                </div>
                <div><em class="add_contactNumber_error"></em></div>
            </div>
        </div>

        <div>
            <h4 id="addUserModalTitle">单位管理员</h4>
            <p></p>
            <div id="addForm2">
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
                    <span class="input-group-addon">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_birthday" placeholder="生日(格式:1990-01-01,默认:1990-01-01)" class="form-control" value="1990-01-01" />
                    <span class="input-group-addon">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_mobile" placeholder="电话" class="form-control" value="" onblur="checkMobile(this);" onfocus="checkFocus(this);" />
                    <span class="input-group-addon">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话*</span>
                </div>
                <div><em class="add_mobile_error"></em></div>
                <div class="input-group">
                    <input type="text" class="form-control" id="add_department" placeholder="所属部门" value="" />
                    <span class="input-group-addon">所属部门&nbsp;</span>
                </div>
            </div>
        </div>

        <div class="modal-footer">
            <a href="javascript:void(0)" id="addsubmit-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

<div id="editCompanyModal" class="modal" style="top:100px">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="editCompanyModalTitle">修改单位信息</h4>
            <p></p>
            <div id="editForm">
                <div class="input-group" style="display: none">
                    <input type="text" id="edit_id" placeholder="单位ID" class="form-control" value="" readonly />
                </div>
                <div class="input-group">
                    <input type="text" id="edit_companyName" placeholder="单位名称" class="form-control" value="" />
                    <span class="input-group-addon">单位名称*</span>
                </div>
                <div class="input-group">
                    <input type="text" id="edit_address" placeholder="详细地址" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)" />
                    <span class="input-group-addon">详细地址*</span>
                </div>
                <div><em class="edit_address_error"></em></div>
                <div class="input-group">
                    <select class="form-control" id="edit_companyNature">
                    </select>
                    <span class="input-group-addon">单位性质&nbsp;</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="edit_province" onchange="changeEditCity()">
                    </select>
                    <span class="input-group-addon">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份&nbsp;</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="edit_city">
                    </select>
                    <span class="input-group-addon">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市&nbsp;</span>
                </div>
                <div class="input-group">
                    <input type="text" id="edit_contact" placeholder="联系人" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)" />
                    <span class="input-group-addon">联&nbsp;系&nbsp;&nbsp;人*</span>
                </div>
                <div><em class="edit_contact_error"></em></div>
                <div class="input-group">
                    <input type="text" id="edit_contactNumber" placeholder="联系电话" class="form-control" value="" onblur="checkMobile(this);" onfocus="checkFocus(this);" />
                    <span class="input-group-addon">联系电话*</span>
                </div>
                <div><em class="edit_contactNumber_error"></em></div>
            </div>
        </div>

        <div class="modal-footer">
            <a href="javascript:void(0)" id="editsubmit-button" class="btn btn-primary">确定</a>
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

<script>
    var companyList = {};
    var newPagePager = {};
    var provinceList = new Array();
    var cityList = new Array();
</script>
<script src="/static/js/companyManage.js?v=${version}"></script>
