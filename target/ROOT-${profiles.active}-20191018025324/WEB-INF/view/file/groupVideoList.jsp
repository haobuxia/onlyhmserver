<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main-content">
        <div class="panel clearfix" >
            <div class="panel-heading">
                <div class="custom-tabs-line tabs-line-bottom left-aligned">
                    <ul class="nav" role="tablist"  id="byTypeButtons">
                        <li class="active" data-restype="searchlist"><a href="#tab-bottom-left1" role="tab" data-toggle="tab" aria-expanded="true" data-groupby="searchlist">时间</a></li>
                        <li class="" data-restype="siteid"><a href="#tab-bottom-left2" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="siteid">工地</a></li>
                        <li class="" data-restype="clientid"><a href="#tab-bottom-left3" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="clientid">头盔IMEI</a></li>
                        <li class="" data-restype="neusername"><a href="#tab-bottom-left4" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="neusername">头盔账号</a></li>
                        <li class="" data-restype="machinecode"><a href="#tab-bottom-left5" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="machinecode">车辆机号</a></li>
                        <li class="" data-restype="imei"><a href="#tab-bottom-left6" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="imei">车辆盒子IMEI</a></li>
                        <li class="" data-restype="svcdata"><a href="#tab-bottom-left7" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="svcdata">服务数据</a></li>
                    </ul>
                </div>
            </div>
             <div class="panel-heading">
                <h3 class="panel-title" id="serTitle">${searchBtnTitle}</h3>
            </div>
            <div class="panel-body col-md-6" id="searchSection">
                <div class="row center" id="searchDiv">
                    <div class="input-group">
                        <input type="text" id="oneKey" placeholder="${searchPlaceHolder}"  class="form-control" value="">
                        <span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="search-button" title="${searchBtnTitle}">
                                    <i class="fa fa-search"></i>
                        </button></span>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel clearfix" id="videoCountSection">
            <div class="panel-body">
                <div class="collection">
                    <c:forEach items="${vo.list}" var="tripVo">
                    <p>                        
                        <a href="javascript:void(0)" class="text-muted" data-key="${tripVo.one}"> 
                            <span class="badge"> ${tripVo.two} </span> 
                            ${tripVo.three}  
                        </a>
                    </p>
                    </c:forEach>
                </div>
                <jsp:include page="../include/new-page-pager.jsp"></jsp:include>
            </div>
        </div>
</div>
    <script>
        var listGroupVideoPage = {};
        listGroupVideoPage.groupBy = '${groupBy}';
    </script>
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

    function changeTab(){
        var serTitle = $("#serTitle").text();
        var li = $("#byTypeButtons>li");
        if (serTitle.search("工地") != -1){
            li.removeClass('active').attr("aria-expanded","false");
            li.eq(1).addClass('active').attr("aria-expanded","true");
        }else if(serTitle.search("头盔设备IMEI") != -1){
            li.removeClass('active').attr("aria-expanded","false");
            li.eq(2).addClass('active').attr("aria-expanded","true");            
        }
        else if(serTitle.search("头盔账号") != -1){
            li.removeClass('active').attr("aria-expanded","false");
            li.eq(3).addClass('active').attr("aria-expanded","true");             
        }
        else if(serTitle.search("根据机号") != -1){
            li.removeClass('active').attr("aria-expanded","false");
            li.eq(4).addClass('active').attr("aria-expanded","true");             
        }
        else if(serTitle.search("盒子IMEI") != -1){
            li.removeClass('active').attr("aria-expanded","false");
            li.eq(5).addClass('active').attr("aria-expanded","true");             
        }
        else if(serTitle.search("服务数据") != -1){
            li.removeClass('active').attr("aria-expanded","false");
            li.eq(6).addClass('active').attr("aria-expanded","true");             
        }
        

    }
    $(function () {

        changeTab();

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
    <script src="/static/js/groupVideoList.js?v=${version}"></script>
