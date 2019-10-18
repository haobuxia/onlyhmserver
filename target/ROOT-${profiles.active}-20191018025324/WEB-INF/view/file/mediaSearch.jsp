<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="vertical-align-wrap" style="position: fixed;background-color: #fff;">
    <div class="vertical-align-middle">
        <div class="auth-box lockscreen clearfix">
            <div class="content">
                <h1 class="sr-only">田一科技</h1>
                <div class="logo text-center">田一科技</div>
                <div class="user text-center">
                    <span class="lnr lnr-inbox" style="font-size: 100px;"></span>
                    <h2 class="name">资料中心</h2>
                </div>
                <div class="custom-tabs-line tabs-line-bottom left-aligned" style="margin-bottom:10px;">
                    <ul class="nav" role="tablist">
                        <li class="active" data-restype="video"><a href="#tab-bottom-left1" role="tab" data-toggle="tab"
                                                                   aria-expanded="true">视频</a></li>
                        <li class="" data-restype="image"><a href="#tab-bottom-left2" role="tab" data-toggle="tab"
                                                             aria-expanded="false">照片</a></li>
                    </ul>
                </div>
                <div class="input-group">
                    <input type="text" id="keyword" class="form-control" placeholder="输入搜索关键字 ...">
                    <span class="input-group-btn"><button type="button" class="btn btn-primary" onclick="doSearch()"><i
                            class="fa fa-search"></i></button></span>
                </div>
                <h5>标签</h5>
                <p class="demo-button" style="max-height: 109px;overflow: auto;" id="byTagButtons">
                    <c:forEach items="${tagList}" var="tag">
                        <button type="button" class="btn btn-sm btn-default" data-id="${tag.id}">${tag.tagName}</button>
                    </c:forEach>
                </p>
                <h5>分类搜索</h5>
                <p class="demo-button" id="byTypeButtons">
                    <button type="button" class="btn btn-xs btn-info" data-groupby="searchlist">时间</button>
                    <button type="button" class="btn btn-xs btn-info" data-groupby="siteid">工地</button>
                    <button type="button" class="btn btn-xs btn-info" data-groupby="clientid">头盔IMEI</button>
                    <button type="button" class="btn btn-xs btn-info" data-groupby="neusername">头盔账号</button>
                    <button type="button" class="btn btn-xs btn-info" data-groupby="machinecode">车辆机号</button>
                    <button type="button" class="btn btn-xs btn-info" data-groupby="imei">车辆盒子IMEI</button>
                    <button type="button" class="btn btn-xs btn-info" data-groupby="svcdata">服务数据</button>
                </p>
            </div>
        </div>
    </div>
</div>
<script>
    function doSearch() {
        var keyword = $.trim($("#keyword").val());
        toResultPage(keyword);
        return false;
    }

    function toResultPage(keyword){
        var resType = $("ul[role=tablist]>li.active").attr("data-restype");
        if (resType == null || resType == "") resType = "video";
        var url = "/media/search/" + resType + "/1?keyword="+keyword;
        loadMainContent(url);
    }

    $(function () {
        $("#byTypeButtons button").off('click').click(function () {
            var _btn = $(this);
            var groupBy = _btn.attr("data-groupby");
            if("searchlist" == groupBy){
                doSearch();
            }else{
                loadMainContent("/list/video/"+groupBy);
            }
        });
        $("#byTagButtons button").off('click').click(function () {
            var _btn = $(this);
            var tag = _btn.text();
            console.debug("点击了标签:"+tag);
            toResultPage(tag);
        });
    })

</script>