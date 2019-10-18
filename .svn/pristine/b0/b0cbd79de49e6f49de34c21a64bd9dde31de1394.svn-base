<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel clearfix">
    <div class="panel-heading">
        <h3 class="panel-title">设备历史表</h3>
    </div>
    <div class="panel-body col-md-6" id="searchSection">
        <div class="input-group">
            <div id="sl">
                <select class="form-control" id="afflSelect" data-init="false">
                    <option value="" selected>全部</option>
                </select>
            </div>
            <label for="afflSelect" class="input-group-addon">所属单位</label>
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
        <div id="show-more" style="display: none">
            <div class="input-group">
                <input type="text" id="userName" placeholder="持有人" class="form-control">
                <label for="userName" class="input-group-addon">&nbsp;持&nbsp;有&nbsp;人&nbsp;</label>
            </div>
            <div class="input-group">
                <input type="text" id="deviceNumber" placeholder="设备编号" class="form-control">
                <label for="deviceNumber" class="input-group-addon">设备编号</label>
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
                <%--<input type="text" id="model" placeholder="型号" class="form-control">--%>
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
                <label for="statusSelect"
                       class="input-group-addon">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
            </div>
            <div class="input-group">
                <input type="text" id="startTime" placeholder="请输入开始时间:例如2018-08-08 15:59:32" class="form-control"
                       value="">
                <label for="startTime" class="input-group-addon">持有开始时间</label>
            </div>
            <div class="input-group">
                <input type="text" id="endTime" placeholder="请输入结束时间:例如2018-08-08 15:59:32" class="form-control"
                       value="">
                <label for="endTime" class="input-group-addon">持有结束时间</label>
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
                                                  aria-expanded="true">设备历史列表</a></li>

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
                    <th>持有开始时间</th>
                    <th>持有结束时间</th>
                    <th>所属单位</th>
                    <th>软件版本</th>
                    <th>操作说明</th>
                </tr>
                </thead>
                <tbody id="historyListContent">

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
<script src="/static/jquery/jquery.qrcode.js"></script>
<script src="/static/jquery/jquery.jqprint-0.3.js"></script>
<script src="/static/js/deviceHistoryList.js?v=${version}"></script>
