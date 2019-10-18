<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main-content" id="searchSection">
    <div class="panel clearfix" id="searchDiv">
        <div class="panel-heading">
            <h3 class="panel-title">二手机</h3>
        </div>
        <div class="panel-body col-md-6 col-sm-6" >
                
                <div class="input-group">
                    <input type="text" id="recirc_machineCode" placeholder="请输入机号" class="form-control">

                    <span class="input-group-addon">请输入机号</span>
                </div>
                <div class="input-group">
                    <input type="date" id="recirc_date1" placeholder="请输入开始时间yyyy-MM-dd" class="form-control" value="">
                    <span class="input-group-addon">请输入开始时间</span>
                </div>
                <div class="input-group">
                    <input type="date" id="recirc_date2" placeholder="请输入结束时间yyyy-MM-dd" class="form-control" value="">
                    <span class="input-group-addon">请输入结束时间</span>
                </div>
                <div class="clearfix">
                    <button class="btn btn btn-primary pull-right" type="submit" id="search-button" title="点击搜索">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
        </div>
        
    </div>
</div>
<div class="section" id="videoContentSection">
</div>
<div class="section" id="imageContentSection">
</div>
<script>
    var recircIndexPage = {};
</script>
<script src="/static/js/recircIndex.js?v=${version}"></script>

