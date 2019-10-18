<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/static/bootstrap-datetimepicker/datetimepicker.css">

<script src="/static/bootstrap-datetimepicker/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="/static/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
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
        <h3 class="panel-title">设备台账</h3>
    </div>

    <div class="panel-body col-md-8" id="searchSection">
        <div id="regDept">
            <div class="input-group" id="showBtn">
                <div id="sl1">
                    <select class="form-control" id="afflSelect" data-init="false">
                        <option value="" selected>全部</option>
                    </select>
                </div>
                <label for="afflSelect" class="input-group-addon">所属单位</label>
                <input type="text" id="deviceNumber" placeholder="设备编号" class="form-control">
                <label for="deviceNumber" class="input-group-addon">设备编号</label>

                <%--<span class="input-group-btn">--%>
                <%--<button id="more-button" class="btn btn btn-primary" title="更多筛选">更多筛选</button>--%>
                <%--</span>--%>
                <span class="input-group-btn">
                    <button class="btn btn btn-primary" type="submit" id="search-button" title="点击搜索">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
                <span class="input-group-btn"></span>
                <span class="input-group-btn">
                    <button class="btn btn btn-primary" type="button" id="more-button" title="更多筛选">
                        更多筛选
                    </button>
                </span>
            </div>
            <div id="show-more" style="display: none">
                <div class="input-group">
                    <input type="text" id="userName" placeholder="持有人" class="form-control">
                    <label for="userName" class="input-group-addon">&nbsp;持&nbsp;有&nbsp;人&nbsp;</label>
                </div>

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
                    <label for="batchSelect"
                           class="input-group-addon">批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次</label>
                </div>
                <div class="input-group">
                    <select class="form-control" id="status" data-init="false">
                        <option value="" selected>全部</option>
                    </select>
                    <label for="status"
                           class="input-group-addon">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
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
            <%--<div class="input-group" id="showBtn">--%>
            <%--<span class="input-group-btn"></span>--%>
            <%--<span class="input-group-btn"><button class="btn btn btn-primary pull-right" type="submit"--%>
            <%--id="search-button"--%>
            <%--title="搜索设备">--%>
            <%--<i class="fa fa-search"></i>--%>
            <%--</button></span>--%>

            <%--</div>--%>
        </div>
    </div>
</div>
</div>
<div id="qrCodeModal" class="modal" style="top:20%">
    <div class="panel panel-body center-block" style="width: 50%;">
        <h4 id="qrCodeModalTitle">初始化账号</h4>
        <div>
            <h4>头盔信息</h4>
            <p></p>
            <div>
                <div class="input-group">
                    <input type="text" id="i_number" placeholder="设备编号" class="form-control" oninput="initQrCode()">
                    <span class="input-group-addon">设备编号</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="i_model" onchange="initQrCode()">
                    </select>
                    <span class="input-group-addon">型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="i_batch" onchange="initQrCode()">
                    </select>
                    <span class="input-group-addon">批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次</span>
                </div>
                <div class="input-group">
                    <select class="form-control" id="i_cameraOrientation" onchange="initQrCode()">
                        <option value="0">竖版</option>
                        <option value="1">横版</option>
                    </select>
                    <span class="input-group-addon">摄像头方向</span>
                </div>
            </div>
        </div>

        <%--<div id="qrForm">--%>
        <%--<h4>持有人信息</h4>--%>
        <%--<p></p>--%>
        <%--<div>--%>
        <%--<div class="input-group">--%>
        <%--<div id="sl2">--%>
        <%--<select id="i_affl1" class="form-control" data-init="false" onchange="deptReg()">--%>
        <%--<option value="" selected>选择单位</option>--%>
        <%--</select>--%>
        <%--</div>--%>
        <%--<span class="input-group-addon">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位</span>--%>
        <%--</div>--%>
        <%--<div class="input-group">--%>
        <%--<select id="i_dept" class="form-control" data-init="false" onchange="userReg()">--%>
        <%--<option value="" selected>部门(选填)</option>--%>
        <%--</select>--%>
        <%--<span class="input-group-addon">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门</span>--%>
        <%--</div>--%>
        <%--<div class="input-group">--%>
        <%--<select id="userSelect1" class="form-control" data-init="false">--%>
        <%--<option value="" selected>选择用户</option>--%>
        <%--</select>--%>
        <%--<span class="input-group-addon">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户</span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
        <span style="font-family:Microsoft YaHei;font-size:18px;">
            <div class="input-field col s12" id="qrCode" align="center" style="position:relative;">
                <img id='qrCodeImage' class="hide"/>
                <div id="warning" style="position:absolute; z-index:2; left:40%; top:40%; font-size: x-large;color: red;
                    background-color: #f2dede;">请填写完整信息</div>
            </div>
        </span>
        <div style="margin-top:30px;margin-bottom: 10px;" align="center">
            <%--<a class="waves-effect waves-light btn btn-primary"><i class="fa fa-print"></i>--%>
            <%--确定</a>--%>
            <a class="waves-effect waves-light btn btn-primary" data-dismiss="modal" aria-label="Close"><i
                    class="fa fa-close"></i>关闭</a>
        </div>
    </div>

</div>
<div class="custom-tabs-line tabs-line-bottom left-aligned" style="margin-bottom:10px;">
    <ul class="nav" role="tablist" id="saleStateList">
        <li class="active" data-statetype="-1"><a href="#tab-bottom-left1" role="tab" data-toggle="tab"
                                                  aria-expanded="true">设备列表</a></li>

    </ul>
</div>

<div id="listContent"><!-- 列表数据 -->
    <div class="panel ">
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>设备编号</th>
                    <th>品类</th>
                    <th>型号</th>
                    <th>批次</th>
                    <th>入库时间</th>
                    <th>状态</th>
                    <th>持有人</th>
                    <th>使用人</th>
                    <th>所属单位</th>
                    <th>软件版本</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="deviceListContent">

                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- END MAIN -->
<div class="clearfix"></div>
<!-- END WRAPPER -->


<div id="regDeviceModal" class="modal" style="top:20%">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <div align="center">
                <h4>变更使用人</h4>
            </div>
            <h4>基本信息</h4>
            <div id="userContent">

            </div>
            <h4>新持有人信息</h4>
            <p></p>
            <div id="regForm">
                <input type="hidden" id="regForm_deviceUUID">
                <input type="hidden" id="dept"/>
                <input type="hidden" id="company"/>
                <div>
                    <div id="sl3">
                        <select class="form-control" id="companySelect" data-init="false" onchange="changeUser();">

                        </select>
                    </div>
                    <select class="form-control" id="userSelect" data-init="false">
                        <option value="" disabled selected>选择用户</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="panel-body modal-footer">
            <a class="btn btn-primary" onclick="saveReg()">确定</a>
            <a class="btn" data-dismiss="modal" aria-label="Close">取消</a>
        </div>
    </div>
</div>

<div id="editDeviceModal" class="modal" style="top:20%">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="editDeviceModalTitle1">修改设备信息</h4>
            <p></p>
            <div id="regForm1">
                <div class="input-group">
                    <input type="text" disabled="disabled" id="edit_deviceNumber" placeholder="编号"
                           class="form-control">
                    <label for="edit_deviceNumber" class="input-group-addon">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                </div>
                <div class="input-group">
                    <input type="text" disabled="disabled" id="edit_categorySelect" placeholder="品类"
                           class="form-control">
                    <label for="edit_categorySelect"
                           class="input-group-addon">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类</label>
                </div>
                <div class="input-group">
                    <select class="form-control" id="edit_model" data-init="false" disabled="disabled">
                    </select>
                    <%--<input type="text" id="edit_model" placeholder="型号" class="form-control">--%>
                    <label for="edit_model"
                           class="input-group-addon">型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                </div>
                <div class="input-group">
                    <select class="form-control" id="edit_batchSelect" data-init="false" disabled="disabled">
                    </select>
                    <label for="edit_batchSelect"
                           class="input-group-addon">批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次</label>
                </div>
                <div class="input-group">
                    <input type="text" disabled="disabled" id="edit_versionSelect" placeholder="软件版本"
                           class="form-control">
                    <label for="edit_versionSelect" class="input-group-addon">软件版本</label>
                </div>
                <div class="input-group">
                    <input type="text" disabled="disabled" id="edit_affiliationName" placeholder="持有单位" class="form-control">
                    <label for="edit_affiliationName" class="input-group-addon">持有单位</label>
                </div>
                <div class="input-group">
                    <input type="text" disabled="disabled" id="edit_userName" placeholder="持有用户" class="form-control">
                    <label for="edit_userName" class="input-group-addon">持有用户</label>
                </div>
                <%--<h4 id="editDeviceModalTitle2">修改设备状态</h4>
                <p></p>--%>
                <input type="hidden" id="deviceEdit_deviceUUID"/>
                <div class="input-group">
                    <input style="display: none" id="oldStatus"/>
                    <select class="form-control" id="statusSelect"></select>
                    <label for="edit_batchSelect"
                           class="input-group-addon">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
                </div>
                <div id="delDeviceModal" class="panel panel-body center-block" style="width:100%;display: none">
                    <div>
                        <h4 id="delDeviceModalTitle">请输入删除原因</h4>
                        <p></p>
                        <div>
                            <div class="input-group">
                                <span class="input-group-addon">原因：</span>
                                <input type="text" id="delDevice_reason" placeholder="原因" class="form-control" value="">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a onclick="delDevice()" class="btn btn-primary">确定</a>
                        <a class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
                    </div>
                </div>
            </div>
        </div>
        <div id="saveModal" class="panel-body modal-footer">
            <a class="btn btn-primary" onclick="saveRegDevice()">确定</a>
            <a class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>


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
    $(function () {
        if (userOrganisation == 1) {
            $("#showBtn").append('<span class="input-group-btn"></span>' +
                '<span class="input-group-btn"><button id="add-button" class="btn btn btn-primary pull-right"\n' +
                '                                                      type="submit"\n' +
                '                                                      title="扫码入库">扫码入库</button></span>');
        }
    });

</script>
<!-- 分页区域 end-->
<script src="/static/jquery/jquery.qrcode.js"></script>
<script src="/static/jquery/jquery.jqprint-0.3.js"></script>
<script src="/static/js/deviceList.js?v=${version}"></script>
