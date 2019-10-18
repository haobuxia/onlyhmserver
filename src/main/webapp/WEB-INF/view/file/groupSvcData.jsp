<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main-content" id="searchSection">
    <div class="panel clearfix">
        <div class="panel-heading" >
            <div class="custom-tabs-line tabs-line-bottom left-aligned">
                <ul class="nav" role="tablist"  id="byTypeButtons">
                    <li class="" data-restype="searchlist"><a href="#tab-bottom-left1" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="searchlist">时间</a></li>
                    <li class="" data-restype="siteid"><a href="#tab-bottom-left2" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="siteid">工地</a></li>
                    <li class="" data-restype="clientid"><a href="#tab-bottom-left3" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="clientid">头盔IMEI</a></li>
                    <li class="" data-restype="neusername"><a href="#tab-bottom-left4" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="neusername">头盔账号</a></li>
                    <li class="" data-restype="machinecode"><a href="#tab-bottom-left5" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="machinecode">车辆机号</a></li>
                    <li class="" data-restype="imei"><a href="#tab-bottom-left6" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="imei">车辆盒子IMEI</a></li>
                    <li class="active" data-restype="svcdata"><a href="#tab-bottom-left7" role="tab" data-toggle="tab" aria-expanded="true" data-groupby="svcdata">服务数据</a></li>
                </ul>
            </div> 
        </div>
        <div class="panel-heading">
                <h3 class="panel-title" id="serTitle">根据服务数据搜索</h3>
        </div>        
   
        <div class="clearfix">
          
                <div class="panel-heading col-md-6" id="searchDiv" >
                    <h4 class="header">视频分类</h4>
                    <div>
                        <div id="tagSection" style="height: auto;max-height:200px;overflow: auto">
                            <c:forEach items="${tagCountList}" var="tripVo">
                                <div class='chip btn btn-primary' data-key="${tripVo.one}"> ${tripVo.three} <span
                                        class="badge"> ${tripVo.two} </span></div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="panel-heading col-md-6" id="keywordSection">
                    <h4 class="header">视频关键词</h4>
                    <div  style="height: auto;max-height:200px;overflow: auto">
                        <c:forEach items="${keywordCountList}" var="tripVo">
                            <div class='chip btn btn-primary' data-key="${tripVo.one}"> ${tripVo.three} <span
                                    class="badge"> ${tripVo.two} </span></div>
                        </c:forEach>
                    </div>
                </div>

        </div>
    </div>
</div>
<div class="divider"></div>
<div class="">
    <div class="panel">
        
        <div class="panel-body" id="contentSection">
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

        
        $("#byTypeButtons a").off('click').click(function () {
            var _btn = $(this);
            var groupBy = _btn.attr("data-groupby");
            if("searchlist" == groupBy){
                doSearch();
            }else{
                loadMainContent("/list/video/"+groupBy);
            }
        });
        $("#byTagButtons a").click(function () {
            var _btn = $(this);
            var tag = _btn.text();
            console.debug("点击了标签:"+tag);
            toResultPage(tag);
        });
    })

</script>
<script src="/static/js/groupSvcData.js?v=${version}"></script>
