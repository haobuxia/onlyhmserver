<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div class="panel clearfix">
    <div class="panel-heading">
        <h3 class="panel-title">区域拍摄统计</h3>
    </div>

    <div class="panel-body col-md-8" id="searchSection">
        <div id="regDept">
            <div class="input-group" id="showBtn">
                <input type="text" id="userName" placeholder="使用人" class="form-control">
                <label for="userName" class="input-group-addon">使用人</label>
                <input type="text" id="startTime" placeholder="请输入开始时间:例如2018-08-08" class="form-control"
                       value="">
                <label for="startTime" class="input-group-addon">开始时间</label>
                <input type="text" id="endTime" placeholder="请输入结束时间:例如2018-08-08" class="form-control"
                       value="" ><%--onchange="dateMinus(this.value);"--%>
                <label for="endTime" class="input-group-addon">结束时间</label>

                <span class="input-group-btn">
                    <button class="btn btn btn-primary" type="submit" id="search-button" title="点击搜索">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </div>
    </div>
</div>
</div>
<div class="custom-tabs-line tabs-line-bottom left-aligned" style="margin-bottom:10px;">
    <ul class="nav" role="tablist" id="saleStateList">
        <li class="active" data-statetype="-1"><a href="#tab-bottom-left1" role="tab" data-toggle="tab"
                                                  aria-expanded="true">统计列表</a></li>

    </ul>
</div>

<div id="listContent"><!-- 列表数据 -->
    <div class="panel ">
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>区域</th>
                    <th>使用人</th>
                    <th>拍摄时长(分钟)</th>
                    <th>拍摄数量</th>
                    <th>工单数量</th>
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

<div id="statisticOneModal" class="modal" style="top:20%">
    <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">个人拍摄时长统计</h4>
            </div>
            <div class="modal-body">
                <div class="panel ">
                    <div class="panel-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>时间</th>
                                <th>拍摄时长(分钟)</th>
                                <th>视频数量</th>
                                <th>工单数量</th>
                            </tr>
                            </thead>
                            <tbody id="deviceListContent1">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<!-- 分页区域 start-->
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
</div>--%>
<script>
    var newPagePager = {};
    $(function () {

    });

</script>
<!-- 分页区域 end-->
<script src="/static/echarts/echarts4.2.min.js"></script>
<script src="/static/js/helmetVideoStatsList.js?v=${version}"></script>
