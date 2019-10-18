<!-- wenxinyan 2018-8-16 在tianyi用户主功能区添加操作日志入口 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- MAIN CONTENT -->
<div>
    <div class="main-content">
        <div class="container-fluid">
            <!-- <h3 class="page-title">尊敬的 
                    ${userInfo.username} 
                , 你好！
            </h3> -->
            <div class="panel panel-headline demo-icons">
                <div class="panel-heading baseCplor">
                   <!--  <h3 class="panel-title">功能区</h3>
                    <p class="subtitle">点击图标使用功能</p> -->
                </div>
                <div class="panel-body baseCplor">
                    <ul class="list-unstyled row" id="addMenuUl">
                        <%--<c:choose>--%>
                            <%--<c:when test="${userInfo.userType == 'tianyi'}">--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/media/index">--%>
                                        <%--<span class="lnr lnr-inbox"></span>--%>
                                        <%--<span class="cssclass">资料中心</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/device/index">--%>
                                        <%--<span class="fa fa-map-marker"></span>--%>
                                        <%--<span class="cssclass">设备地图</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/imei/index">--%>
                                        <%--<span class="fa fa-truck"></span> --%>
                                        <%--<span class="cssclass">车辆管理</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a href='#/site/index'>--%>
                                        <%--<span class="fa fa-building-o"></span> --%>
                                        <%--<span class="cssclass">发现工地</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--&lt;%&ndash;<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a href=""></a>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<a class="item_url" href="#/recirculate/index" class="lnr lnr-lighter"></a> <a&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;class="item_url" href="#/recirculate/index" class="cssclass">二手机</a>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/tianyicontact/list">--%>
                                        <%--<span class="fa fa-phone"></span>--%>
                                        <%--<span class="cssclass">我的联系人</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/service/index">--%>
                                        <%--<span class="fa fa-pencil-square-o"></span> --%>
                                        <%--<span class="cssclass">服务日志</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/alipayquery/index">--%>
                                        <%--<span class="fa fa-jpy"></span> --%>
                                        <%--<span class="cssclass">支付收款</span> --%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/list/video/svcdata">--%>
                                        <%--<span class="fa fa-bar-chart-o"></span> --%>
                                        <%--<span class="cssclass">服务数据</span> --%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<c:if test="${userInfo.admin}">--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/helmet/tolist">--%>
                                            <%--<span class="fa fa-list-ol"></span> --%>
                                            <%--<span class="cssclass">头盔列表</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/company/welcome">--%>
                                            <%--<span class="fa fa-users"></span> --%>
                                            <%--<span class="cssclass">客户管理</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/tianyiuser/list">--%>
                                            <%--<span class="fa fa-user-plus"></span> --%>
                                            <%--<span class="cssclass">注册用户</span> --%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/neteaseuser/list">--%>
                                            <%--<span class="fa fa-user"></span>--%>
                                            <%--<span><p style="font-size: 12px;">netease</p></span>--%>
                                            <%--<span class="cssclass">网易用户</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/apk/file/list">--%>
                                            <%--<span class="fa fa-upload"></span> --%>
                                            <%--<span class="cssclass">apk发布</span> --%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/apk/update/list">--%>
                                            <%--<span class="fa fa-refresh"></span> --%>
                                            <%--<span class="cssclass">apk升级</span> --%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/tag/grouplist">--%>
                                            <%--<span class="fa fa-tags"></span> --%>
                                            <%--<span class="cssclass">标签分组管理</span> --%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/tag/list">--%>
                                            <%--<span class="fa fa-tag"></span> --%>
                                            <%--<span class="cssclass">标签管理</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/workordermanage/index">--%>
                                            <%--<span class="fa fa-file-text-o"></span>--%>
                                            <%--<span class="cssclass">工单管理</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/videoexcel/index">--%>
                                            <%--<span class="fa fa-file-text-o"></span>--%>
                                            <%--<span class="cssclass">清华视频</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/neteasevideo/faillist">--%>
                                            <%--<span class="fa fa-video-camera" style="position: relative;">--%>
                                                <%--<i class="fa-ban text-danger" style="position: absolute;top: 13px;left:7px;font-style: normal;font-size: 25px;"></i>--%>
                                            <%--</span> --%>
                                            <%--<span><p style="font-size: 12px;">netease</p></span>--%>
                                            <%--<span class="cssclass">网易失败视频</span> --%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/helmetlog/list">--%>
                                            <%--<span class="fa fa-pencil-square-o"></span> --%>
                                            <%--<span><p style="font-size: 12px;">helmet</p></span>--%>
                                            <%--<span class="cssclass">头盔日志</span> --%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/userlog/list">--%>
                                            <%--<span class="fa fa-pencil-square-o" style="position: relative;">--%>
                                                <%--<i class="fa-user" style="position: absolute;top: 10px;left:7px;font-style: normal;font-size: 20px;"></i>--%>
                                            <%--</span> --%>
                                            <%--<span class="cssclass">用户日志</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/operalog/list">--%>
                                            <%--<span class="fa fa-pencil-square-o" style="position: relative;">--%>
                                                <%--<i class="fa-user" style="position: absolute;top: 10px;left:7px;font-style: normal;font-size: 20px;"></i>--%>
                                            <%--</span>--%>
                                            <%--<span class="cssclass">操作日志</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#/kmx/listData">--%>
                                            <%--<span class="fa fa-search"></span> --%>
                                            <%--<span><p style="font-size: 12px;">KMX</p></span>--%>
                                            <%--<span class="cssclass">KMX数据查询</span>--%>
                                        <%--</a>--%>
                                    <%--</li>--%>
                                    <%--&lt;%&ndash;<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                        <%--<a class="item_url" href="#">--%>
                                            <%--<span class="fa fa-search"></span>--%>
                                            <%--<span><p style="font-size: 12px;"></p></span>--%>
                                            <%--<span class="cssclass">工单详情</span>--%>
                                        <%--</a>--%>
                                    <%--</li>&ndash;%&gt;--%>
                                <%--</c:if>--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${userInfo.tianyuan}">--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/personal/qrcode">--%>
                                        <%--<span class="fa fa-barcode"></span>--%>
                                        <%--<span><p style="font-size: 12px">二维码</p></span>--%>
                                        <%--<span class="cssclass">账号二维码</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/service/index">--%>
                                        <%--<span class="fa fa-pencil-square-o"></span> --%>
                                        <%--<span class="cssclass">服务日志</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/alipayquery/index">--%>
                                        <%--<span class="fa fa-jpy"></span> --%>
                                        <%--<span class="cssclass">支付收款</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                                    <%--<a class="item_url" href="#/service/test">--%>
                                        <%--<span class="fa fa-cogs"></span> --%>
                                        <%--<span class="cssclass">接口测试</span>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>

                    </ul>
                </div>
            </div>
            <%--<div class="panel panel-headline demo-icons">--%>
                <%--<div class="panel-heading">--%>
                    <%--<h3 class="panel-title">新增功能</h3>--%>
                    <%--<p class="subtitle">点击图标添加功能</p>--%>
                <%--</div>--%>
                <%--<div class="panel-body">--%>
                    <%--<ul class="list-unstyled row">--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-home"></span> <span class="cssclass">fa fa-home</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-download"></span> <span class="cssclass">fa fa-download</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-upload"></span> <span class="cssclass">fa fa-upload</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-shopping-cart"></span> <span class="cssclass">fa fa-shopping-cart</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-calendar"></span> <span class="cssclass">fa fa-calendar</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-map"></span> <span class="cssclass">fa fa-map</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-map"></span> <span class="cssclass">fa fa-location</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-credit-card"></span> <span class="cssclass">fa fa-credit-card</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-database"></span> <span class="cssclass">fa fa-database</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-bell"></span> <span class="cssclass">fa fa-bell</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-envelope"></span> <span class="cssclass">fa fa-envelope</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-thumbs-up"></span> <span class="cssclass">fa fa-thumbs-up</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-line-chart"></span> <span class="cssclass">fa fa-line-chart</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-map-marker"></span> <span class="cssclass">fa fa-map-marker</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-paper-plane-o"></span> <span class="cssclass">fa fa-paper-plane-o</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-search"></span> <span class="cssclass">fa fa-search</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-shopping-bag"></span> <span class="cssclass">fa fa-shopping-bag</span>--%>
                        <%--</li>--%>
                        <%--<li class="col-md-2 col-sm-4 col-xs-6">--%>
                            <%--<span class="fa fa-random"></span> <span class="cssclass">fa fa-random</span>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
    </div>
</div>
<!-- END MAIN CONTENT -->

<script>
    $(function(){
        $.post("/rolemenu/menulist",{id:$("#userId").val(),isUserId:true},function(resp){
            if(resp.success){
                $.each(resp.data.menu2,function(idx,m2){
                    if(m2.url != ''){
                        $("#addMenuUl").append('<li class="col-md-2 col-sm-4 col-xs-6">' +
                            '<a class="item_url" href="#' + m2.url + '">' +
                            '<span class="' + m2.image + '"></span>' +
                            '<span class="cssclass">' + m2.name + '</span></a></li>');
                    }
                });
            }
        },"json");
    });
</script>