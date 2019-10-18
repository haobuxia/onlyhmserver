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
        <h3 class="panel-title">神钢建机</h3>
    </div>

    <%--<div class="panel-body col-md-8" id="searchSection">
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

                &lt;%&ndash;<span class="input-group-btn">&ndash;%&gt;
                &lt;%&ndash;<button id="more-button" class="btn btn btn-primary" title="更多筛选">更多筛选</button>&ndash;%&gt;
                &lt;%&ndash;</span>&ndash;%&gt;
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
            &lt;%&ndash;<div class="input-group" id="showBtn">&ndash;%&gt;
            &lt;%&ndash;<span class="input-group-btn"></span>&ndash;%&gt;
            &lt;%&ndash;<span class="input-group-btn"><button class="btn btn btn-primary pull-right" type="submit"&ndash;%&gt;
            &lt;%&ndash;id="search-button"&ndash;%&gt;
            &lt;%&ndash;title="搜索设备">&ndash;%&gt;
            &lt;%&ndash;<i class="fa fa-search"></i>&ndash;%&gt;
            &lt;%&ndash;</button></span>&ndash;%&gt;

            &lt;%&ndash;</div>&ndash;%&gt;
        </div>
    </div>--%>
</div>
</div>
<div class="custom-tabs-line tabs-line-bottom left-aligned" style="margin-bottom:10px;">
    <ul class="nav" role="tablist" id="saleStateList">
        <li class="active" data-statetype="-1"><a href="#tab-bottom-left1" role="tab" data-toggle="tab"
                                                  aria-expanded="true">TYBOX数据列表</a></li>

    </ul>
</div>

<div id="editUserModal" class="modal" style="top:20%">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4>原始数据</h4>
            <p></p>
            <textarea style="min-height:200px;" id="edit_orgname" placeholder="原始数据" class="form-control" readonly value="" />
            <h4 id="editUserModalTitle">解析数据结果</h4>
            <p></p>
            <textarea style="min-height:200px;" id="edit_name" placeholder="解析结果" class="form-control" readonly value="" />
        </div>
    </div>
</div>
<div id="listContent"><!-- 列表数据 -->
    <div class="panel ">
        <div class="panel-body">
            <table class="table table-striped" style="table-layout:fixed;">
                <thead>
                <tr>
                    <th style="width:10%;">序号</th>
                    <th style="width:80%;">原始数据</th>
                    <th style="width:10%;">操作</th>
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
<!-- 分页区域 end-->
<script src="/static/js/tyBoxList.js?v=${version}"></script>
