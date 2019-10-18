<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/static/assets/css/helmet3and1.css">
<link href="/static/nouislider/nouislider.min.css" rel="stylesheet">

<div class="main-content">
    <!-- 设备列表 start -->
    <div class="col-md-3 col-sm-4" id="clinetListContainer">
        <!-- 搜索设备 -->

        <div class="panel panel-scrolling touKuiList">

            <div class="input-group">
                <input class="form-control" type="text" placeholder="设备名..." id="helmetSearchKeyword">
                <span class="input-group-btn"><button class="btn btn-primary" type="button" onclick="doSearchHelmet()">搜索</button></span>
            </div>
            <div class="panel-heading">
                <span id="helmetSearchCount">搜索到 0 个结果</span>
                <div class="right">
                    <button type="button" class="btn-toggle-collapse"><i
                            class="lnr lnr-chevron-up  lnr-chevron-down"></i></button>
                    <!-- <button type="button" class="btn-remove"><i class="lnr lnr-cross" title="去除地图定位"></i></button> -->
                </div>
            </div>
            <div class="slimScrollDiv" style="position: relative; overflow-y:scroll; width: auto; height: 430px; display: none;">
                <div class="panel-body">
                    <ul class="list-unstyled activity-list" id="searchedHelmets">
                        <%--<li>--%>
                        <%--<img src="assets/img/user1.png" alt="Avatar" class="img-circle pull-left avatar">--%>
                        <%--<p><a href="#" class="text-muted">设备名Michael</a> <span class="timestamp">20 分钟 前 北京中关村</span>--%>
                        <%--</p>--%>
                        <%--<i class="lnr lnr-cross" title="去除地图定位"></i>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                        <%--<img src="assets/img/user1.png" alt="Avatar" class="img-circle pull-left avatar">--%>
                        <%--<p><a href="#" class="text-muted">设备名Michael</a> <span class="timestamp">20 分钟 前 北京中关村</span>--%>
                        <%--</p>--%>
                        <%--<i class="lnr lnr-cross" title="去除地图定位"></i>--%>
                        <%--</li>--%>
                    </ul>
                    <%--<button type="button" class="btn btn-primary btn-bottom center-block">加载更多</button>--%>
                </div>
            </div>
        </div>
        <!-- 在线列表 -->
        <div class="panel panel-scrolling touKuiList">
            <div class="panel-heading">
                <h3 class="panel-title">在线设备 <span id="onlineCount"> 0 </span>个</h3>
                <div class="right">
                    <button type="button" class="btn-toggle-collapse"><i
                            class="lnr lnr-chevron-up  lnr-chevron-down"></i></button>
                    <!-- <button type="button" class="btn-remove"><i class="lnr lnr-cross" title="去除地图定位"></i></button> -->
                </div>
            </div>
            <div class="slimScrollDiv" style="position: relative; overflow-y:scroll; width: auto; height: 430px;">
                <div class="panel-body">
                    <ul class="list-unstyled activity-list helmet-list" id="onlineHelmets">
                        <%--<li>--%>
                        <%--<img src="/static/assets/img/user1.png" alt="Avatar" class="img-circle pull-left avatar">--%>
                        <%--<p><a href="#" class="">设备名Michael</a> <span class="timestamp">北京</span></p>--%>
                        <%--<i class="lnr lnr-cross" title="去除地图定位"></i>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                        <%--<img src="/static/assets/img/user2.png" alt="Avatar" class="img-circle pull-left avatar">--%>
                        <%--<p><a href="#" class="">设备名Daniel</a> <span class="timestamp">北京</span></p>--%>
                        <%--<i class="lnr lnr-cross" title="去除地图定位"></i>--%>
                        <%--</li>--%>
                    </ul>
                    <%--<button type="button" class="btn btn-primary btn-bottom center-block">加载更多</button>--%>
                </div>
            </div>
        </div>
        <!-- END 在线列表 -->
        <!-- 离线列表 -->
        <div class="panel panel-scrolling touKuiList">
            <div class="panel-heading">
                <h3 class="panel-title">离线设备 <span id="offlineCount"> 0 </span>个</h3>
                <div class="right">
                    <button type="button" class="btn-toggle-collapse"><i
                            class="lnr lnr-chevron-up lnr-chevron-down"></i></button>
                    <!-- <button type="button" class="btn-remove"><i class="lnr lnr-cross" title="去除地图定位"></i></button> -->
                </div>
            </div>
            <div class="slimScrollDiv" style="position: relative; overflow-y:scroll; width: auto; height: 430px; display: none;">
                <div class="panel-body">
                    <ul class="list-unstyled activity-list helmet-list" id="offlineHelmets">
                        <%--<li>--%>
                        <%--<img src="/static/assets/img/user1.png" alt="Avatar" class="img-circle pull-left avatar">--%>
                        <%--<p><a href="#" class="text-muted">设备名Michael</a> <span class="timestamp">已运行 20 分钟 前 北京</span>--%>
                        <%--</p>--%>
                        <%--<i class="lnr lnr-cross" title="去除地图定位"></i>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                        <%--<img src="/static/assets/img/user2.png" alt="Avatar" class="img-circle pull-left avatar">--%>
                        <%--<p><a href="#" class="text-muted">设备名Daniel</a> <span class="timestamp">昨天 北京</span></p>--%>
                        <%--<i class="lnr lnr-cross" title="去除地图定位"></i>--%>
                        <%--</li>--%>
                    </ul>
                    <%--<button type="button" class="btn btn-primary btn-bottom center-block">加载更多</button>--%>
                </div>
            </div>
        </div>
        <!-- END 离线列表 -->
    </div>
    <!-- 设备列表 end -->

    <!-- 所有设备地图 start -->
    <div class="col-md-9 col-sm-8 " id="allHelmetMapArea">
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title">所有设备定位</h3>
            </div>
            <div class="panel-body" id="allHelmetMapDiv">
                <div id="mapContainer" style="min-height: 600px;max-height: 600px;"></div>
            </div>
        </div>
    </div>
    <!-- 所有设备地图 start -->

    <!-- 个人 strat-->
    <div class="col-md-9 col-sm-8 hidden" id="oneHelmetInfoArea">
        

        <div class="panel">
            <ul class="nav nav-tabs" id="nav-tabs">
                <li role="presentation" class="active" data-category="now"><a href="#">实时数据</a></li>
                <li role="presentation" data-category="map-model"><a href="#">个人定位</a></li>
                <!-- <li role="presentation" data-category="chat"><a href="#">视频聊天</a></li> -->
                <li role="presentation" data-category="history"><a href="#">历史数据</a></li>
                <li role="presentation" data-category="media"><a href="#">相关资料</a></li>
            </ul>

            <div class="panel-body" id="nav-tabs-body">
                <!-- 选项卡内容 -->
                <!-- 实时数据 -->
                <div class="col-md-12 col-xs-12" data-category="now">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <!-- 实时数据 ( --> <b id="oneHelmetName"><!-- 设备名9527 --></b> <!-- ) -->
                            <span class="right text-primary" id="oneHelmetTime"><!-- 设备时间:2018-04-21 14:39:10 --></span>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-12 col-xs-12" id="oneHelmetChart" style="height: 250px;">
                        </div>
                    </div>
                </div>                
                <!-- 个人定位 包含 个人定位地图 #mapContainer 和 头盔 #helmetModel -->
                <div class="col-md-12 col-xs-12 hidden" data-category="map-model">
                    <div class="col-md-6 col-xs-6"  id="helmetModelArea" style="height:400px"></div>
                </div>
                <!-- 视频聊天 -->
               <!--  <div class="col-md-12 col-xs-12 hidden" data-category="chat">
                    <button class="btn btn-primary" type="button" data-online="false">不在线,无法视频</button>
                </div> -->
                <!-- 传感器历史数据  #sensorChart -->
                <div class="col-md-12 col-xs-12 hidden" data-category="history">
                    <div class="col-md-12 col-xs-12"  id="historyChart" style="height:350px"></div>
                    <div class="col-md-12 col-xs-12"  id="sensor-slider" style="height:20px" title="拖动时间条选择载入时间范围"></div>
                </div>
                <!-- 相关资料  视频 照片音频... -->
                <div class="col-md-12 col-xs-12 hidden" data-category="media">
                    <div class="col-md-12 col-xs-12" id="videoSection"></div>
                    <div class="col-md-12 col-xs-12" id="imageSection"></div>
                    <div class="col-md-12 col-xs-12" id="audioSection"></div>
                    <div class="col-md-12 col-xs-12" id="fileSection"></div>
                </div>
            </div>
        </div>
    </div>
    <!-- 个人 end -->
</div>
<!-- END MAIN -->
<div class="clearfix"></div>

<script>
    //列表切换
        var affectedElement = $('.panel-body');

        $('.panel .btn-toggle-collapse').click(function(e) {
                e.preventDefault();
                // if has scroll
                if( $(this).parents('.panel').find('.slimScrollDiv').length > 0 ) {
                    affectedElement = $('.slimScrollDiv');
                }
                $('.panel .btn-toggle-collapse').not(this).parents('.panel').find(affectedElement).hide();
                $(this).parents('.panel').find(affectedElement).slideToggle(300);
                $(this).find('i.lnr-chevron-up').toggleClass('lnr-chevron-down');
            });
</script>
<%--<script src="http://apps.bdimg.com/libs/echarts/2.1.9/source/echarts-all.js"></script>--%>
<script src="/static/echarts/echarts.min.js"></script>
<script src="/static/nouislider/nouislider.min.js"></script>
<script src="/static/nouislider/wNumb.js"></script>

<script>
    var helmet3And1Page = {};
    helmet3And1Page.clientList =${clientList};
    helmet3And1Page.allHelmetWebSocketQueryString = "?token=${websocketToken}&dataType=gps_heart";
</script>
<script src="/static/js/websocket.js?v=${version}"></script>
<script src="/static/threejs/three.min.js"></script>
<script src="/static/threejs/STLLoader.js"></script>
<script src="/static/threejs/OrbitControls.js"></script>
<script src="/static/js/helmetModel.js?v=${version}"></script>
<script src="/static/js/helmet3and1.js"></script>
