<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .input-group{margin-bottom: 5px;}
</style>
<div class="container">
    <div class="row panel">
        <div class="panel-heading blue white-text">
            <select class="blue" id="tagSelect" style="display:inline-block;width:100px;"></select>
            <a class="green btn btn-primary" onclick="startDrawPolygon()" style="margin-top: -5px;">绘制工地</a>
            <span>点击左键开始绘制，点击右键结束绘制，多边形区域即为工地范围；双击修改工地信息。</span>
        </div>
        <div  id="mapContainer" style="min-height:600px;height: auto;"></div>
        
    </div>
</div>

<!-- Modal Structure -->
<div id="siteModal" class="modal">
    <div class="panel panel-body center-block" style="width: 50%;">
        <div id="editForm">
             <h4>工地信息</h4>
            <div class="input-group">
                <input type="hidden" id="site_id"  value="" readonly>
                <input type="hidden" id="site_lonLats"  value="" readonly>
                <input type="text" id="site_name" placeholder="工地名称"  class="form-control" value="">
                <label for="site_name" class="active input-group-addon">工地名称</label>
            </div>
            <div class="input-group">
                <input type="text" id="site_lonlat" placeholder="工地定位"  class="form-control" value="" readonly>
                <label for="site_lonlat" class="active input-group-addon">工地定位</label>
            </div>
            <div class="input-group">
                <input type="text" id="site_area" placeholder="工地面积(平方公里)"  class="form-control" value="" readonly>
                <label for="site_area" class="active input-group-addon">工地面积(平方公里)</label>
            </div>
            <div class="input-group">
                <input type="text" id="site_address" placeholder="工地地址"  class="form-control" value="">
                <label for="site_address" class="active input-group-addon">工地地址</label>
            </div>
            <div class="input-group">
                <input type="text" id="site_province" placeholder="省份"  class="form-control" value="">
                <label for="site_province" class="active input-group-addon">省份</label>
            </div>
            <div class="input-group">
                <input type="text" id="site_city" placeholder="城市"  class="form-control" value="">
                <label for="site_city" class="active input-group-addon">城市</label>
            </div>
            <div class="input-group">
                <input type="text" id="site_district" placeholder="区域"  class="form-control" value="">
                <label for="site_district" class="active input-group-addon">县</label>
            </div>
            <div class="input-group">
                <input type="text" id="site_township" placeholder="乡镇"  class="form-control" value="">
                <label for="site_township" class="active input-group-addon">乡镇</label>
            </div>
            <div class="input-group">
                <input type="text" id="site_street" placeholder="街道"  class="form-control" value="">
                <label for="site_street" class="active input-group-addon">街道</label>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0)" id="site-save-button" class="btn btn-success">保存</a>
                <a href="javascript:void(0)" id="site-del-button" class="btn btn-danger">删除</a>
                <a href="javascript:void(0)" class="btn" data-dismiss="modal" aria-label="Close">关闭</a>
            </div>
        </div>
    </div>
    
</div>

<div id="playListModal" class="modal modal-fixed-footer">
    <div class="modal-content">
        <h4 id="playListModalTitle">有多个视频</h4>
        <p></p>
        <div class="row center">
            <div class="collection" id="playList">

            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" class="modal-action modal-close waves-effect waves-red btn-flat">关闭</a>
    </div>
</div>

<div id="playVideoModal" class="modal modal-fixed-footer">
    <div class="modal-content">
        <div class="row center">
            <video id="playVideo" controls="controls" autoplay="autoplay" loop="loop">
                您的浏览器不支持 video 标签。
            </video>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" id="playVideoCloseBtn" class="modal-action waves-effect waves-red btn-flat">关闭</a>
    </div>
</div>

<script>
    var siteIndexPage={};
    <%--siteIndexPage.videoCounts = ${videoCounts};--%>
    <%--siteIndexPage.imageCounts = ${imageCounts};--%>
    siteIndexPage.siteMap = ${siteMap};
</script>
<script src="/static/js/siteIndex.js?v=${version}"></script>

