<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content" id="searchSection">
    <div class="panel clearfix" id="searchDiv">
        <div class="panel-heading">
            <h3 class="panel-title">服务日志</h3>
        </div>
        <div class="panel-body col-md-6 col-sm-6">
            <div style="display:<c:if test="${isTianYuanUser}">none</c:if>">
                <input  type="<c:choose><c:when test='${isTianYuanUser}'>hidden</c:when><c:otherwise>text</c:otherwise></c:choose>" class="form-control" id="service_oprtId" placeholder="请输入服务人员ID或姓名" value="${oprtId}">
            </div>
            <br>
                <select id="service_rwzt" class="form-control" >
                    <option value="" disabled>请选择任务状态</option>
                    <option value="0">待填写</option>
                    <option value="1" selected>已填写未完成</option>
                    <option value="2">填写完成未上传</option>
                    <option value="3">已上传待审核</option>
                    <option value="4">已上传被打回</option>
                    <option value="5">已上传审核通过</option>
                </select>
            <br>
            <div class="input-group">
                <input type="date" id="service_date1" placeholder="请输入开始时间yyyy-MM-dd" class="form-control" value="">
                <span class="input-group-addon">请输入开始时间</span>
            </div>
            
            <div class="input-group">
                <input type="date" id="service_date2" placeholder="请输入结束时间yyyy-MM-dd" class="form-control" value="">
                <span class="input-group-addon">请输入结束时间</span>
            </div>               
            
           
            <input type="text" id="service_rwh" placeholder="请输入工单号" class="form-control" value="">              
            <br>
            <div class="clearfix">
                <button class="btn btn btn-primary pull-right" type="submit" id="search-button" title="点击搜索任务工单">
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
<!-- END MAIN -->



<div id="serviceOrderModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
            <h4 id="serviceOrderTitle"></h4>
            <p>
                <input type="hidden" id="serviceOrderRwh">
                <input type="hidden" id="serviceOrderOprtId">
            </p>
        </div>
        <table class="table project-table">
            <tbody>
                <tr class="card-panel" id="SvcCommYhxx"><td><a href="#">用户信息</a></td><td><i class="fa "></i></td></tr>
                <tr class="card-panel" id="SvcCommSbjbxx"><td><a href="#">设备基本信息</a></td><td><i class="fa "></i></td></tr>
                <tr class="card-panel" id="SvcCommSyqk"><td><a href="#">使用情况</a></td><td><i class="fa "></i></td></tr>
                <tr class="card-panel" id="mainItem"><td><a href="#">服务项目</a></td><td><i class="fa "></i></td></tr>
                <%--<tr class="card-panel" id="SvcCommLj"><td><a href="#">零件</a></td><td><i class="fa "></i></td></tr>--%>
                <tr class="card-panel" id="SvcCommJgnr"><td><a href="#">结果内容</a></td><td><i class="fa"></i></td></tr>
                <tr class="card-panel" id="SvcCommSjlc"><td><a href="#">时间里程</a></td><td><i class="fa"></i></td></tr>
                <%--<tr class="card-panel" id="SvcCommDkfx"><td><a href="#">待扣费项</a></td><td><i class="fa"></i></td></tr>--%>
                <tr class="card-panel" id="SvcCommYhyj"><td><a href="#">用户意见</a></td><td><i class="fa"></i></td></tr>
                <tr class="card-panel" id="SvcCommPic"><td><a href="#">照片信息</a></td><td><i class="fa"></i></td></tr>
                <tr class="card-panel" id="svcResData"><td><a href="#">头盔摄录</a></td><td><i class="fa"></i></td></tr>

            </tbody>
        </table>
        <div class="modal-footer">
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>

<div id="serviceOrderDetailModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div>
             <h4 id="serviceOrderDetailTitle"></h4>
            <p></p>
            <div>
                <table class="table-striped project-table">
                     <thead>
                        <tr>
                            <th>数据项</th>
                            <th>数据值</th>
                        </tr>
                    </thead>
                    <tbody id="serviceOrderDetailTableBody">
                    </tbody>
                </table>
            </div>
                
        </div>
        <div class="modal-footer">
            <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
        </div>
    </div>
</div>


<script>
    var serviceIndexPage = {};
    serviceIndexPage.isTianYuanUser = ${isTianYuanUser};
    serviceIndexPage.taskMetaData = ${taskMetaData};
    serviceIndexPage.orderMapData = {};
</script>
<script src="/static/js/serviceIndex.js?v=${version}"></script>
