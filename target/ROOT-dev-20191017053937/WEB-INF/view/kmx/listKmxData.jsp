<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main-content">
    <div class="panel clearfix" id="searchSection">
            <div class="panel-body col-md-6" id="searchDiv">
                <div class="input-group">
                    <select  class="form-control" id="deviceType">
                        <option value="helmet" selected>头盔</option>
                        <option value="tybox">盒子</option>
                    </select>
                    <label for="deviceType" class="input-group-addon">设备类型</label>
                </div>
                <div class="input-group">
                    <select class="form-control" id="deviceId">
                    </select>
                    <label for="deviceId" class="input-group-addon">选择设备</label>
                </div>
                <div class="input-group">
                    <select class="form-control" id="sensorId">
                    </select>
                    <label for="sensorId" class="input-group-addon">选择传感器</label>
                </div>
                <div class="input-group">
                    <input type="text" id="time1" placeholder="请输入时间1"  class="form-control" value="">
                    <label for="time1" class="input-group-addon">开始时间</label>
                </div>
                <div class="input-group">
                    <input type="text" id="time2" placeholder="请输入时间2"  class="form-control" value="">
                    <label for="time2" class="input-group-addon">结束时间</label>
                </div>
                <div class="clearfix" style="margin-top: 8px;">
                    <button class="btn btn btn-warning pull-right" type="submit" id="add-button" title="添加">
                        <i class="fa fa-plus"></i>
                    </button>
                    <button class="btn btn btn-primary pull-right" type="submit" id="search-button" title="点击搜索">
                    <i class="fa fa-search"></i>
                </button>
                    
                </div>
            </div>
    </div>

    <div class="divider"></div>
    <div class="section" id="contentSection">
    </div>
</div>
<script>
    var listKmxDataPage = {};
</script>
<script src="/static/js/listKmxData.js?v=${version}"></script>
