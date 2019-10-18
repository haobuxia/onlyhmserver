<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%
    String type = request.getParameter("resType");
    if (type == null) type = "";
%>--%>
<style>
    #editForm .input-group {
        margin: 10px;
    }

    #editForm .form-control[disabled] {
        background-color: #fff;
        cursor: default;
    }

    #conPanel > div {
        display: none
    }

    #tabs li {
        cursor: pointer;
    }
</style>

<div id="editWorkOrderModal" class="panel">
    <div class="panel-body">
        <div>
            <h4 id="editWorkOrderModalTitle">工单详情</h4>
            <p></p>
            <div id="editForm" class="clearfix">
                <div class="col-md-6 col-sm-6">
                    <div class="input-group">
                        <input disabled type="hidden" id="editWorkOrder_id">
                        <input disabled type="text" id="editWorkOrder_orderNo" placeholder="工单单号" class="form-control">
                        <span class="input-group-addon">工单单号</span>
                    </div>
                    <div class="input-group">
                        <!--   <input disabled type="hidden" id="editWorkOrder_userId"> -->
                        <input disabled disabled id="editWorkOrder_orderType" class="form-control">

                        <span class="input-group-addon">工单类型</span>
                    </div>
                    <div class="input-group">
                        <!-- <input disabled type="hidden" id="editWorkOrder_userId"> -->
                        <input disabled type="text" id="editWorkOrder_subject" placeholder="工单主题" class="form-control">
                        <span class="input-group-addon">工单主题</span>
                    </div>
                    <div class="input-group">
                        <input disabled type="hidden" id="editWorkOrder_userId">
                        <input disabled type="text" id="editWorkOrder_userRealName" placeholder="服务人员"
                               class="form-control">
                        <span class="input-group-addon">服务人员</span>
                    </div>
                    <div class="input-group">
                        <input disabled type="text" id="editWorkOrder_customerName" placeholder="客户名称"
                               class="form-control">
                        <span class="input-group-addon">客户名称</span>
                    </div>
                    <div class="input-group">
                        <input disabled type="text" id="editWorkOrder_contactName" placeholder="客户联系人"
                               class="form-control">
                        <span class="input-group-addon">客户联系人</span>
                    </div>
                    <div class="input-group">
                        <input disabled type="text" id="editWorkOrder_contactPhone" placeholder="联系人电话"
                               class="form-control">
                        <span class="input-group-addon">联系人电话</span>
                    </div>
                    <div class="input-group">
                        <input disabled type="text" id="editWorkOrder_planTime" placeholder="排班时间(yyyyMMdd HHmm)"
                               class="form-control">
                        <span class="input-group-addon">排班时间</span>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">

                    <div class="input-group">
                        <input disabled type="text" id="editWorkOrder_brand" placeholder="车辆品牌" class="form-control">
                        <span class="input-group-addon">车辆品牌</span>
                    </div>
                    <div class="input-group">
                        <input disabled type="text" id="editWorkOrder_model" placeholder="车辆型号" class="form-control">
                        <span class="input-group-addon">车辆型号</span>
                    </div>
                    <div class="input-group">
                        <input disabled type="text" id="editWorkOrder_machineCode" placeholder="机号"
                               class="form-control">
                        <span class="input-group-addon">机号</span>
                    </div>


                    <div class="input-group">
                        <input disabled type="hidden" id="editWorkOrder_latLng">
                        <input disabled type="text" id="editWorkOrder_address" placeholder="车辆位置" class="form-control">
                        <span class="input-group-addon">车辆位置</span>
                    </div>
                    <div class="input-group">
                        <input disabled id="editWorkOrder_tags" class="form-control " title="拍摄视频类型">
                        <span class="input-group-addon">拍摄视频类型</span>
                    </div>
                    <div class="input-group">
                        <textarea disabled id="editWorkOrder_remark" placeholder="请输入工单执行注意事项" class="form-control"
                                  style="height: 120px;"></textarea>
                        <span class="input-group-addon">注意事项</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="panel">
    <div class="custom-tabs-line tabs-line-bottom left-aligned panel-heading" style="padding:0 40px;">
        <ul class="nav" role="tablist" id="tabs">
            <li class="active" data-restype="video" data-tab="parts"><a role="tab" data-toggle="tab"
                                                                        aria-expanded="true">扫描零件</a></li>
            <li class="" data-restype="video" data-tab="part"><a role="tab" data-toggle="tab"
                                                                 aria-expanded="true">零件详情</a></li>
            <li class="" data-restype="video" data-tab="inven"><a role="tab" data-toggle="tab" aria-expanded="true">零件库存量</a>
            </li>
            <li class="" data-restype="image" data-tab="videoCon"><a role="tab" data-toggle="tab" aria-expanded="false">相关视频</a>
            </li>
        </ul>
    </div>
    <div id='conPanel'>
        <!-- 零件 -->
        <div class="panel-body" id="parts" style="display: block;">
            <div class="panel-heading">
                <h3 class="panel-title">扫描零件</h3>
            </div>
            <div class="panel-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th>零件号</th>
                        <th>零件名称</th>
                        <th>仓库名称</th>
                        <th>机型</th>
                        <th>扫码类型</th>
                        <th>扫码时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
        <!-- 零件详情 -->
        <div class="panel-body" id="part">
            <div class="panel-heading">
                <h3 class="panel-title">零件详情</h3>
            </div>
            <div class="panel-body">
                <div id="partInfo">


                </div>
            </div>
        </div>
        <!-- 库存量 -->
        <div class="panel-body" id="inven">
            <div class="panel-heading">
                <h3 class="panel-title">零件库存量</h3>
            </div>
            <div class="panel-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th>仓库名称</th>
                        <th>库存量</th>
                    </tr>
                    <tbody id="inventory">

                    </tbody>
                    </thead>
                </table>
            </div>
        </div>
        <!-- 视频列表 -->
        <div class="panel-body" id="videoCon">
            <div class="panel-heading">
                <h3 class="panel-title">相关视频</h3>
            </div>

            <ul class="list-unstyled cover_zone clearfix" id="orderVideos">


            </ul>
            <div class="panel-headline" id="new-page-pager${type}">
                <div class="panel-body">
                    <p class="demo-button">
                        <span id="pageInfo">共 0 条数据 , 第 0 页 , 共 0 页</span>
                        , 跳转到
                        <input type="text" style="width:50px;" id="toPage${type}"> 页
                        <button type="button" class="btn btn-xs btn-primary" id="toPageButton${type}">GO</button>
                        &nbsp;
                        <!-- 禁用按钮 -->
                        <button type="button" class="btn btn-xs btn-primary" id="prePageButton${type}">上一页</button>
                        &nbsp;
                        <!-- 可用按钮 -->
                        <button type="button" class="btn btn-xs btn-primary" id="nextPageButton${type}">下一页</button>
                    </p>
                </div>
            </div>

        </div>
    </div>
    <script>
        var orderNo = ${orderNo};
    </script>
    <script src="/static/js/workOrderDetail.js"></script>
    <script id="new-pager">
        var viewMedia = function (resType,resId) {
            var url = "/"+resType+"/play/"+resId;
            loadMainContent(url);
        }
        var getPartVideos = function(page){
            var url = "/workordermanage/" + orderNo +"/video/" + page;
            ajaxGet(url, function (resp) {
                if (resp.success) {
                    showOrderVideos(resp.data);
                } else {
                    showAlert("获取相关视频失败,错误代码：" + resp.message);
                }
            });
        }
        //显示视频列表
        var showOrderVideos = function(res){
            var _videos = res.list;
            var _inner = '';
            var _page = res.page;
            var _pagecount = res.pageCount;
            var _total = res.total;
            var _pagesize = res.pageSize;

            for (var i = 0; i < _videos.length; i++) {
                _inner += '<li class="col-md-3 col-sm-4 col-xs-6">'
                    + '<div class="panel cover_list btnDiv-video" data-id="'+_videos[i].id+'">'
                    + '<h4>'+_videos[i].id+'</h4>'
                    + '<div class="helmet-video" data-id="'+_videos[i].id+'" data-mc="">'
                    + '<img src="${fileServer}'+_videos[i].thumbOssPath+'" alt="视频" style="cursor:pointer;" onclick="viewMedia(\'video\','+_videos[i].id+')">'
                    + '</div>'
                    + '<h5>'+_videos[i].createTimeString+'</h5>'
                    +'</div></li>'
            }
            $('#orderVideos').html(_inner);
            var newPagePager${type} = {};
            newPagePager${type}.page =_page||1;
            newPagePager${type}.pageCount =_pagecount||1;
            newPagePager${type}.total = _total||0;
            newPagePager${type}.pageSize =_pagesize||8;
            function makePage ${type}(page, pageCount) {
                newPagePager${type}.currentPage = 1;
                if (page > pageCount) {
                    newPagePager${type}.currentPage = pageCount;
                } else {
                    newPagePager${type}.currentPage = page;
                }

                $("#new-page-pager${type}").attr("data-page", newPagePager${type}.currentPage);

                if (newPagePager${type}.currentPage <= 1) {
                    $("#prePageButton${type}").addClass("disabled").prop("disabled", true);
                } else {
                    $("#prePageButton${type}").removeClass("disabled").prop("disabled", false);
                }

                if (newPagePager${type}.currentPage >= pageCount) {
                    $("#nextPageButton${type}").addClass("disabled").prop("disabled", true);
                } else {
                    $("#nextPageButton${type}").removeClass("disabled").prop("disabled", false);
                }

                $("#toPageButton${type}").off('click').click(function () {
                    var toPage = $.trim($("#toPage${type}").val());
                    if (isNaN(toPage)) {
                        return;
                    }
                    loadData(toPage, "${type}");
                });
                $("#prePageButton${type}").off('click').click(function () {
                    loadData(newPagePager${type}.currentPage - 1, "${type}");
                });
                $("#nextPageButton${type}").off('click').click(function () {
                    loadData(newPagePager${type}.currentPage + 1, "${type}");
                });

                $("#pageInfo").text("共 " + newPagePager${type}.total + " 条数据 , 第 " + newPagePager${type}.currentPage + " 页 , 共 " + newPagePager${type}.pageCount + " 页");
                return newPagePager${type}.currentPage;
            }

            $(function () {
                makePage${type}(newPagePager${type}.page, newPagePager${type}.pageCount);
            });
        }
    </script>
</div>

