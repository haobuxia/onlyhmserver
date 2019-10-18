<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<div class="panel clearfix">
    <div class="panel-heading">
        <h3 class="panel-title">出库登记</h3>
    </div>
    <div class="panel-body col-md-6" id="searchSection">
        <%--<div class="input-group">--%>
            <%--<input type="text" id="userName" placeholder="持有人" class="form-control">--%>
            <%--<label for="userName" class="input-group-addon">&nbsp;持&nbsp;有&nbsp;人&nbsp;</label>--%>
            <%--<span class="input-group-btn">--%>
                    <%--<button class="btn btn btn-primary" onclick="showMore()" title="更多筛选">更多筛选</button>--%>
                <%--</span>--%>
        <%--</div>--%>
        <div class="input-group">
            <input type="text" id="deviceNumber" placeholder="设备编号" class="form-control">
            <label for="deviceNumber" class="input-group-addon">设备编号</label>
            <span class="input-group-btn">
                    <button class="btn btn btn-primary" onclick="showMore()" title="更多筛选">更多筛选</button>
                </span>
            <span class="input-group-btn"></span>
            <span class="input-group-btn">
                    <button class="btn btn btn-primary" type="submit" id="search-button" title="点击搜索">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
        </div>
        <div id="show-more" style="display:none;">
            <div class="input-group">
                <select class="form-control" id="categorySelect" data-init="false">
                    <option value="" selected>全部</option>
                </select>
                <label for="categorySelect"
                       class="input-group-addon">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类</label>
            </div>
            <div class="input-group">
                <select class="form-control" id="versionSelect" data-init="false">
                    <option value="" selected>全部</option>
                </select>
                <label for="versionSelect" class="input-group-addon">软件版本</label>
            </div>
            <div class="input-group">
                <select class="form-control" id="model" data-init="false">
                    <option value="" selected>全部</option>
                </select>
                <label for="model" class="input-group-addon">型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
            </div>
            <div class="input-group">
                <select class="form-control" id="batchSelect" data-init="false">
                    <option value="" selected>全部</option>
                </select>
                <label for="batchSelect" class="input-group-addon">批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次</label>
            </div>
            <div class="input-group">
                <select class="form-control" id="statusSelect" data-init="false">
                    <option value="" selected>全部</option>
                </select>
                <label for="statusSelect" class="input-group-addon">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
            </div>
            <div class="input-group">
                <input type="text" id="startTime" placeholder="请输入开始时间:例如2018-08-08 15:59:32" class="form-control"
                       value="">
                <label for="startTime" class="input-group-addon">开始时间</label>
            </div>

            <div class="input-group">
                <input type="text" id="endTime" placeholder="请输入结束时间:例如2018-08-08 15:59:32" class="form-control"
                       value="">
                <label for="endTime" class="input-group-addon">结束时间</label>
            </div>

        </div>
        <%--<div class="input-group">--%>
            <%--<span class="input-group-btn"></span>--%>
            <%--<span class="input-group-btn"><button class="btn btn btn-primary pull-right" type="submit"--%>
                                                  <%--id="search-button"--%>
                                                  <%--title="搜索设备">--%>
                <%--<i class="fa fa-search"></i>--%>
                <%--</button></span>--%>
        <%--</div>--%>
    </div>
</div>
<div class="custom-tabs-line tabs-line-bottom left-aligned" style="margin-bottom:10px;">
    <ul class="nav" role="tablist" id="saleStateList">
        <li class="active" data-statetype="-1"><a href="#tab-bottom-left1" role="tab" data-toggle="tab"
                                                  aria-expanded="true">未出库设备列表</a></li>

    </ul>
</div>
<div id="regDeliveryModal" class="modal" style="top:20%">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4>出库登记</h4>
            <p></p>
            <div id="regForm">
                <div class="input-group">
                    <input type="hidden" id="regForm_deliveryUUID">
                    <select class="form-control" id="companySelect" data-init="false">
                        <option value="" selected>全部</option>
                    </select>
                    <label for="categorySelect"
                           class="input-group-addon">接收单位</label>
                    <span class="input-group-btn">
                        <button class="btn btn btn-primary" onclick="newCompany()" title="新增客户">新增客户</button>
                    </span>
                </div>
                <div>
                    <h4>出库设备编号</h4>
                    <div class="input-group">
                        <textarea  id="deviceDeliveryList" rows="" cols="" disabled="disabled" type="text" placeholder="编号" class="form-control" value=""/>
                        <span class="input-group-addon">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>
                    </div>
                </div>


            </div>
        </div>
        <div class="panel-body modal-footer">
            <a class="btn btn-primary" onclick="saveRegDelivery()">确定</a>
            <a class="btn" data-dismiss="modal" aria-label="Close">取消</a>
        </div>
    </div>
</div>
<div id="addCompanyModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="addCompanyModalTitle">添加单位信息</h4>
            <p></p>
            <div id="addForm1">
                <div class="input-group">
                    <input type="text" id="add_companyName" placeholder="单位名称" class="form-control"
                           value="" onblur="checkNull(this)" onfocus="checkFocus(this)"/>
                    <span class="input-group-addon">单位名称*</span>
                </div>
                <div><em class="add_companyName_error"></em></div>
                <div class="input-group">
                    <input type="text" id="add_address" placeholder="详细地址" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)"/>
                    <span class="input-group-addon">详细地址*</span>
                </div>
                <div><em class="add_address_error"></em></div>
                <div class="input-group">
                    <select class="form-control" id="add_companyNature">
                        <%--<option value="1">民营</option>--%>
                        <%--<option value="2">国营</option>--%>
                    </select>
                    <span class="input-group-addon">单位性质</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="add_province" onchange="changeAddCity();">
                    </select>
                    <span class="input-group-addon">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="add_city">
                    </select>
                    <span class="input-group-addon">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_contact" placeholder="联系人" class="form-control"
                           value="" onblur="checkNull(this)" onfocus="checkFocus(this)"/>
                    <span class="input-group-addon">联&nbsp;&nbsp;系&nbsp;&nbsp;人*</span>
                </div>
                <div><em class="add_contact_error"></em></div>
                <div class="input-group">
                    <input type="text" id="add_contactNumber" placeholder="联系电话" class="form-control"
                           value="" onblur="checkMobile(this)" onfocus="checkFocus(this)"/>
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
                    <input type="text" id="add_account" placeholder="登录账号" class="form-control"
                           value="" onblur="checkNull(this)" onfocus="checkFocus(this)"/>
                    <span class="input-group-addon">登录账号*</span>
                </div>
                <div><em class="add_account_error"></em></div>
                <div class="input-group">
                    <input type="text" id="add_name" placeholder="用户姓名" class="form-control" value="" onblur="checkNull(this)" onfocus="checkFocus(this)"/>
                    <span class="input-group-addon">用户姓名*</span>
                </div>
                <div><em class="add_name_error"></em></div>
                <div class="input-group" style="display: none">
                    <input type="text" id="add_password" placeholder="默认123456" class="form-control"
                           value="123456" />
                    <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="add_sex">
                        <option value="0" selected>未知</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                    <span class="input-group-addon">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_birthday" placeholder="生日(格式:2000-01-01,默认:2000-01-01)"
                           class="form-control" value=""/>
                    <span class="input-group-addon">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</span>
                </div>
                <div class="input-group">
                    <input type="text" id="add_mobile" placeholder="电话" class="form-control" value="" onblur="checkMobile(this)" onfocus="checkFocus(this)"/>
                    <span class="input-group-addon">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话*</span>
                </div>
                <div><em class="add_mobile_error"></em></div>
                <%--<div class="input-group">
                    <input type="text" id="add_home" placeholder="籍贯" class="form-control" value=""/>
                    <span class="input-group-addon">籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯</span>
                </div>--%>
                <div class="input-group">
                    <input type="text" class="form-control" id="add_department" placeholder="所属部门"
                           value=""/>
                    <span class="input-group-addon">所属部门</span>
                </div>
            </div>
        </div>

        <div class="modal-footer">
            <a onclick="saveCompany()" id="addsubmit-button" class="btn btn-primary">确定</a>
            <a class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>
<div id="listContent"><!-- 列表数据 -->
    <div class="panel ">
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>选择</th>
                    <th>序号</th>
                    <th>设备编号</th>
                    <th>品类</th>
                    <th>型号</th>
                    <th>批次</th>
                    <th>入库时间</th>
                    <th>状态</th>
                    <th>持有人</th>
                    <th>所属单位</th>
                    <th>软件版本</th>
                </tr>
                </thead>
                <tbody id="deliveryListContent">

                </tbody>
            </table>
        </div>
        <div id="deliveryButton" align="center">

        </div>

    </div>
</div>
<!-- END MAIN -->
<div class="clearfix"></div>
<!-- END WRAPPER -->
<!-- 分页区域 start-->
<div class="panel-headline" id="new-page-pager$">
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
<script>
    var newPagePager = {};
</script>
<script src="/static/jquery/jquery.qrcode.js"></script>
<script src="/static/jquery/jquery.jqprint-0.3.js"></script>
<script src="/static/js/deliveryList.js?v=${version}"></script>
