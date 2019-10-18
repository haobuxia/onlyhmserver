<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="mediaShownModal" class="modal" style="top:5%;"></div>
<!-- MAIN -->
<div>
    <!-- MAIN CONTENT -->
    <div class="main-content">
        <div class="container-fluid">
            <div class="panel panel-headline">
                <div class="panel-heading">
                    <h3 class="panel-title">资料中心</h3>
                </div>
                <!-- 搜索区域 start-->
                <div class="panel-body">
                    <div class="custom-tabs-line tabs-line-bottom left-aligned">
                        <ul class="nav" role="tablist" id="resTypeList">
                            <li class="active" data-restype="video"><a href="#tab-bottom-left1" role="tab" data-toggle="tab" aria-expanded="true">视频</a></li>
                            <li class="" data-restype="image"><a href="#tab-bottom-left2" role="tab" data-toggle="tab" aria-expanded="false">照片</a></li>
                        </ul>
                    </div>
                    <br>
                    <div class="input-group col-md-10 col-sm-12">
                        <input type="text" id="userName" placeholder="用户名称" class="form-control">
                        <label for="userName" class="input-group-addon">用户</label>
                        <input type="text" id="deviceNumber" placeholder="头盔编号" class="form-control">
                        <label for="deviceNumber" class="input-group-addon">头盔号</label>
                        <input type="text" id="machineNumber" placeholder="机号" class="form-control">
                        <label for="machineNumber" class="input-group-addon">机号</label>
                        <input type="text" id="orderNo" placeholder="工单号" class="form-control">
                        <label for="orderNo" class="input-group-addon">工单号</label>
                        <input type="text" id="keyword" placeholder="关键字" class="form-control">
                        <label for="keyword" class="input-group-addon">关键字</label>
                    </div>
                    <div class="input-group col-md-10 col-sm-12">
                        <select class="form-control" id="brandSelect" data-init="false"<%-- onchange="changeMachineType();"--%>>
                            <option value="" selected>全部</option>
                        </select>
                        <label for="brandSelect"
                               class="input-group-addon">品牌</label>
                        <select class="form-control" id="machineTypeSelect" data-init="false"<%-- onchange="changeMachineModel();"--%>>
                            <option value="" selected>全部</option>
                        </select>
                        <label for="machineTypeSelect"
                               class="input-group-addon">机&nbsp;&nbsp;&nbsp;种</label>
                        <select class="form-control" id="machineModelSelect" data-init="false">
                            <option value="" selected>全部</option>
                        </select>
                        <label for="machineModelSelect"
                               class="input-group-addon">机型</label>
                        <select class="form-control" id="sceneSelect" data-init="false">
                            <option value="" selected>全部</option>
                            <option value="保内保养">保内保养</option>
                            <option value="保外保养">保外保养</option>
                        </select>
                        <label for="sceneSelect" class="input-group-addon">场&nbsp;&nbsp;&nbsp;景</label>
                        <select class="form-control" id="tagSelect" data-init="false">
                            <option value="" selected>全部</option>
                            <c:forEach items="${tagList}" var="tag">
                                <option value="${tag.id}">${tag.tagName}</option>
                            </c:forEach>
                        </select>
                        <label for="tagSelect"
                               class="input-group-addon">标&nbsp;&nbsp;&nbsp;签</label>
                    </div>
                    <div class="btn-group">
                        <button style="margin-right: 5px;" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span  id="orderByBtn">时间降序</span> <span class="caret"></span></button>
                        <ul class="dropdown-menu" id="orderByList">
                            <li><a href="#">时间降序</a></li>
                            <li><a href="#">时间升序</a></li>
                        </ul>
                        <input type="text" id="orderBy" value="1" style="display: none;">
                    </div>
                    <div class="btn-group" id="byTagButtons">
                        <button type="button" class="btn btn-default" name="0-10">10秒以下</button>
                        <button type="button" class="btn btn-default" name="10-30">10~30秒</button>
                        <button type="button" class="btn btn-default" name="30-60">30~60秒</button>
                        <button type="button" class="btn btn-default" name="60-300">1~5分钟</button>
                        <button type="button" class="btn btn-default" name="300-600">5~10分钟</button>
                        <button type="button" class="btn btn-default" name="600-1200">10~20分钟</button>
                        <button type="button" class="btn btn-default" name="1200-12000">20分钟</button>
                    </div>
                    <div class="btn-group">
                        <button style="margin-left: 5px;" class="btn btn-primary" type="button" onclick="doSearch()"> 搜索 </button>
                    </div>
                </div>
                <%--<div class="panel-body">
                    <p class="subtitle">点击功能快捷搜索</p>
                    <p class="demo-button" id="byTypeButtons">
                        <button type="button" class="btn btn-xs btn-primary" data-groupby="searchlist">时间</button>
                        <button type="button" class="btn btn-xs btn-primary" data-groupby="siteid">工地</button>
                        <button type="button" class="btn btn-xs btn-primary" data-groupby="clientid">头盔IMEI</button>
                        <button type="button" class="btn btn-xs btn-primary" data-groupby="neusername">头盔账号</button>
                        <button type="button" class="btn btn-xs btn-primary" data-groupby="machinecode">车辆机号</button>
                        <button type="button" class="btn btn-xs btn-primary" data-groupby="imei">车辆盒子IMEI</button>
                        <button type="button" class="btn btn-xs btn-primary" data-groupby="svcdata">服务数据</button>
                    </p>
                    <p class="subtitle">点击标签快捷搜索</p>
                    <p class="demo-button" style="max-height: 100px; overflow: auto;" id="byTagButtons">
                        <c:forEach items="${tagList}" var="tag">
                            <button type="button" class="btn btn-xs btn-default"
                                    data-id="${tag.id}">${tag.tagName}</button>
                        </c:forEach>
                    </p>
                </div>--%>
            </div>
            <!-- 结果区域 start -->
            <div id="mediaResult" class="col-md-12 col-xs-12"></div>
        </div>
    </div>
    <!-- END MAIN CONTENT -->
</div>
<!-- END MAIN -->
<div class="clearfix"></div>

<script>
    var _keyword = "${keyword}";
    var _orderBy = "${orderBy}";
    var _resType = "${resType}";
    var _page = ${page};
    var newPagePager = {};
    function initUIForm() {
        $("#keyword").val(_keyword);
        var otxt = $("#orderByList>li").eq(_orderBy-1).text();
        $("#orderByBtn").text(otxt);

        $("#resTypeList>li").removeClass("active");
        $("#resTypeList>li[data-restype='"+_resType+"']").addClass("active");

        $(".page-title").text(_resType=="video"?"视频":"图片");
    }

    function initEvent() {
        $("#byTypeButtons button").off('click').click(function () {
            var _btn = $(this);
            var groupBy = _btn.attr("data-groupby");
            if("searchlist" == groupBy){
                doSearch();
            }else{
                loadMainContent("/list/video/"+groupBy);
            }
        });
        /*$("#byTagButtons button").off('click').click(function () {
            var _btn = $(this);
            var tag = _btn.text();
            console.log(1212,_btn.attr("name"));
            $("#keyword").val(tag);
            doSearch();
        });*/
        $("#byTagButtons button").off('click').click(function () {
            var _btn = $(this);
            $("#byTagButtons").children().removeClass("active");
            _btn.addClass("active");
            var timeLong = _btn.attr("name");
            newPagePager.timeLong=timeLong;
            doSearch();
        });
        // 搜索框 时间排序 下拉框
        $("#orderByList>li").click(function(event) {
            var oi = $(this).index()+1;
            $("#orderBy").val(oi);
            $("#orderByBtn").text($(this).text());
            doSearch();
        });

        $("#resTypeList>li").click(function(event) {
            //此事件触发时，active的还没变，此时执行搜索会导致搜索旧的tab数据
            //所以延迟一下再执行
            setTimeout(doSearch,300);
        });
    }
    function doSearch1() {
        newPagePager.timeLong=null;
        doSearch();
    }
    function doSearch() {
        _page=1;
        var keyword = $.trim($("#keyword").val());
        var orderBy = $("#orderBy").val();
        var resType = $("#resTypeList>li.active").attr("data-restype");
        _keyword = keyword;
        _orderBy = orderBy;
        _resType = resType;

        newPagePager.userName=$("#userName").val();
        newPagePager.deviceNumber=$("#deviceNumber").val();
        newPagePager.sceneSelect=$("#sceneSelect").val();
        newPagePager.brandSelect=$("#brandSelect").val();
        newPagePager.machineTypeSelect=$("#machineTypeSelect").val();
        newPagePager.machineModelSelect=$("#machineModelSelect").val();
        newPagePager.tag=$("#tagSelect").val();
        newPagePager.machineNumber=$("#machineNumber").val();
        newPagePager.orderNo=$("#orderNo").val();

        loadData(_page,_resType);
        return false;
    }

    function reloadListData(){
        loadData(1,_resType);
    }
    //删除用重载页面
    function reloadListDataForDel($p){
        loadData($p,_resType);
    }
    function loadData(page,type) {
        _page = page;
        var url = "/media/load/" + type + "/" + _orderBy + "?keyword=" + _keyword + "&page=" + _page;
        url=url+"&userName="+newPagePager.userName;
        url=url+"&deviceNumber="+newPagePager.deviceNumber;
        url=url+"&sceneSelect="+newPagePager.sceneSelect;
        url=url+"&brandSelect="+newPagePager.brandSelect;
        url=url+"&machineTypeSelect="+newPagePager.machineTypeSelect;
        url=url+"&machineModelSelect="+newPagePager.machineModelSelect;
        url=url+"&tag="+newPagePager.tag;
        url=url+"&machineNumber="+newPagePager.machineNumber;
        url=url+"&workId="+newPagePager.orderNo;
        if(newPagePager.timeLong!=null && newPagePager.timeLong != undefined)
        url=url+"&timeLong="+newPagePager.timeLong;
        if(userOrganisation!=1){// 添加只显示本单位头盔视频权限管理
            url=url+"&affiliationId="+userOrganisation;
        }
        console.debug("载入媒体资源数据.url="+url);
        loadContent("#mediaResult",url);
    }
    var initBrand = function () {
        $.post("/dictionary/brand",function(resp){
            if(resp.success && resp.data!=null){
                $.each(resp.data,function(idx,c){
                    $("#brandSelect").append('<option value="' + c.id + '">' + c.name + '</option>');
                })
            }
        });
    }
    var changeMachineType = function () {
        var id = $("#brandSelect").val();
        if(id == ''){
            $("#machineTypeSelect").html('<option value="">全部</option>');
            $("#machineModelSelect").html('<option value="">全部</option>');
        } else {
            $.post("/dictionary/listMachineTypeByBrand?id="+id,function(resp){
                if(resp.success && resp.data!=null){
                    $("#machineTypeSelect").html('<option value="">全部</option>');
                    $("#machineModelSelect").html('<option value="">全部</option>');
                    $.each(resp.data,function(idx,c){
                        $("#machineTypeSelect").append('<option value="' + c.name + '">' + c.name + '</option>');
                    })
                }
            });
        }
    }
    var changeMachineModel = function () {
        var id = $("#machineTypeSelect").val();
        if(id == ''){
            $("#machineModelSelect").html('<option value="">全部</option>');
        } else {
            $.post("/dictionary/listMachineModelByMachineType?id="+id,function(resp){
                if(resp.success && resp.data!=null){
                    $("#machineModelSelect").html('<option value="">全部</option>');
                    $.each(resp.data,function(idx,c){
                        $("#machineModelSelect").append('<option value="' + c.id + '">' + c.name + '</option>');
                    })
                }
            });
        }
    }
    var initMachineParams = function() {
        $.post("/dictionary/listMachineTypeByBrand?id=",function(resp){
            if(resp.success && resp.data!=null){
                $.each(resp.data,function(idx,c){
                    $("#machineTypeSelect").append('<option value="' + c.name + '">' + c.name + '</option>');
                })
            }
        });
        $.post("/dictionary/listMachineModelByMachineType?id=",function(resp){
            if(resp.success && resp.data!=null){
                $.each(resp.data,function(idx,c){
                    $("#machineModelSelect").append('<option value="' + c.name + '">' + c.name + '</option>');
                })
            }
        });
    }
    $(function () {
        initUIForm();
        initEvent();
        initBrand();
        initMachineParams();
        doSearch();
    })
</script>
