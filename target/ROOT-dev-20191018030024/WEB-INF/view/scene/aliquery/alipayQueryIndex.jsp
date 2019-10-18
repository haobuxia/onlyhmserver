<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main-content" id="searchSection">
    <div class="panel clearfix" id="searchDiv">
        <div class="panel-heading">
            <h3 class="panel-title">支付收款</h3>
        </div>
        <div class="panel-body col-md-6 col-sm-6">
            <div style="display:<c:if test="${isTianYuanUser}">none</c:if>">
                <input  type="<c:choose><c:when test='${isTianYuanUser}'>hidden</c:when><c:otherwise>text</c:otherwise></c:choose>" class="form-control" id="alipay_oprtId" placeholder="请输入服务人员ID或姓名" value="${oprtId}">
            </div>
            <br>
            <div class="input-group">
                <input type="date" id="alipay_date1" placeholder="请输入开始时间yyyy-MM-dd" class="form-control" value="">
                <span class="input-group-addon">请输入开始时间</span>
            </div>
           
            <div class="input-group">
                <input type="date" id="alipay_date2" placeholder="请输入结束时间yyyy-MM-dd" class="form-control" value="">
                <span class="input-group-addon">请输入结束时间</span>
            </div>               
           
           
            <input type="text" id="alipay_rwh" placeholder="请输入工单号" class="form-control" value="">              
            <br>
            <div class="clearfix">
                <button class="btn btn btn-primary pull-right" type="submit" id="search-button" title="点击搜索">
                    <i class="fa fa-search"></i>
                </button>
            </div>              
            
        </div>
    </div>
     <div class="panel" id="contentSection">
        <div class="panel-heading">
            <h3 class="panel-title"></h3>
        </div>
        <div class="panel-body" id="contentSection">
            
        </div>
    </div>
</div>










<script>
    var queryIndexPage = {};
    queryIndexPage.isTianYuanUser = ${isTianYuanUser};
</script>
<script src="/static/js/queryIndex.js?v=${version}"></script>