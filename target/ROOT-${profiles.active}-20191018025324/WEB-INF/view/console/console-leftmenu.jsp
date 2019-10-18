<!-- wenxinyan 2018-8-16 在系统管理菜单中添加操作日志入口 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
    <div class="sidebar-scroll">
        <nav>
            <ul class="nav collapsible">
                <li><a href="/console/index" class=""><i class="lnr lnr-home"></i> <span>管理首页</span></a></li>

                <c:if test="${!userInfo.tianyuan}">
                    <!-- 资料中心 start -->
                    <li>
                        <a href="#menu2" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>资料中心</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="menu2" class="collapse ">
                            <ul class="nav">
                                <li><a data-href="/media/index">搜索首页</a></li>
                                <li><a data-href="/list/video/neusername">分类搜索页</a></li>
                            </ul>
                        </div>
                    </li>
                    <!-- 视频照片 end -->
                </c:if>
                <%--<c:if test="${userInfo.admin || userInfo.sales || userInfo.demo}">
                    <!-- 头盔设备  start-->
                    <li>
                        <a href="#menu1" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>头盔设备</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="menu1" class="collapse ">
                            <ul class="nav">

                                <c:if test="${userInfo.admin || userInfo.sales}">
                                    <li><a data-href="/helmet/tosalelist">待售头盔</a></li>
                                    <li><a data-href="/helmet/saledlist">已售头盔</a></li>
                                </c:if>
                                <c:if test="${userInfo.admin}">
                                    <li><a data-href="/imei/index">车辆管理</a></li>
                                </c:if>
                            </ul>
                        </div>
                    </li>
                    <!-- 头盔设备  end-->
                </c:if>--%>

                <c:if test="${userInfo.admin || userInfo.tianyuan || userInfo.demo}">
                    <!-- 智能场景 start -->
                    <li>
                        <a href="#menu3" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>智能场景</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="menu3" class="collapse ">
                            <ul class="nav">
                                <c:if test="${userInfo.admin || userInfo.demo}">
                                    <li><a data-href="/site/index">发现工地</a></li>
                                    <li><a data-href="/recirculate/index">二手机</a></li>
                                </c:if>
                                <li><a data-href="/service/index">服务日志</a></li>
                                <li><a data-href="/alipayquery/index">支付收款</a></li>
                            </ul>
                        </div>
                    </li>
                    <!-- 智能场景 end -->
                </c:if>


                <c:if test="${userInfo.admin || userInfo.sales}">
                    <!-- 用户管理 start -->
                    <li>
                        <a href="#menu4" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>用户管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="menu4" class="collapse ">
                            <ul class="nav">
                                <li><a data-href="/company/welcome">客户管理</a></li>
                                <c:if test="${userInfo.admin}">
                                    <li><a data-href="/neteaseuser/list">网易用户</a></li>
                                    <li><a data-href="/tianyiuser/list">注册用户</a></li>
                                </c:if>
                                <li><a data-href="/user/welcome">用户管理</a></li>
                            </ul>
                        </div>
                    </li>
                    <!-- 用户管理 end -->
                </c:if>


                <c:if test="${userInfo.admin}">
                    <!-- 更多选项  start -->
                    <li>
                        <a href="#menu5" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>app管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="menu5" class="collapse ">
                            <ul class="nav">
                                <li><a data-href="/apk/file/list">apk发布</a></li>
                                <li><a data-href="/apk/update/list">apk升级</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#menu6" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>系统管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="menu6" class="collapse ">
                            <ul class="nav">
                                <li><a data-href="/tag/grouplist">标签分组管理</a></li>
                                <li><a data-href="/tag/list">标签管理</a></li>
                                <li><a data-href="/neteasevideo/faillist">网易失败视频</a></li>
                                <li><a data-href="/helmetlog/list">头盔日志</a></li>
                                <li><a data-href="/userlog/list">用户日志</a></li>
                                <li><a data-href="/operalog/list">操作日志</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#menu7" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>KMX管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="menu7" class="collapse ">
                            <ul class="nav">
                                <li><a data-href="/kmx/listDeviceType">设备类型</a></li>
                                <li><a data-href="/kmx/listDeviceIntance">设备</a></li>
                                <li><a data-href="/kmx/listData">数据查询</a></li>
                            </ul>
                        </div>
                    </li>
                    <!-- 更多选项  end -->
                </c:if>


                <c:if test="${userInfo.tianyuan}">
                    <!-- 天远帐号 start -->
                    <li>
                        <a href="#menu8" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>天远帐号</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="menu8" class="collapse ">
                            <ul class="nav">
                                <li><a data-href="/personal/qrcode">二维码</a></li>
                                <li><a data-href="/service/test">接口测试</a></li>
                            </ul>
                        </div>
                    </li>
                    <!-- 天远帐号  end -->
                </c:if>


                <c:if test="${userInfo.admin || userInfo.sales || userInfo.demo}">
                <!-- 设备管理  start-->
                <li>
                    <a href="#menu9" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>设备管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                    <div id="menu9" class="collapse ">
                        <ul class="nav">
                            <li><a data-href="/device/index">设备地图</a></li>
                            <li><a style="cursor:pointer;" data-href="/device/ledger/list1">设备台账</a></li>
                            <c:if test="${userInfo.admin || userInfo.sales}">
                                <li><a style="cursor:pointer;" data-href="/device/ledger/list2">出库登记</a></li>
                            </c:if>
                            <li><a style="cursor:pointer;" data-href="/device/ledger/list3">设备历史记录查询</a></li>
                            <c:if test="${userInfo.admin}">
                                <li><a data-href="/imei/index">车辆管理</a></li>
                            </c:if>
                        </ul>
                    </div>
                    <!-- 设备管理  end-->
                    </c:if>


            </ul>
        </nav>
    </div>
</div>
<!-- END LEFT SIDEBAR -->

<script>
    $(function () {
        $.each($("ul.collapsible li a[data-href]"), function (idx, alink) {
            var obj = $(alink);
            var href = obj.attr("data-href");
            if (href != '#') {
                obj.css("cursor", "pointer").off('click').on('click', function () {
                    loadMainContent(href);
                });
            }
        });
    })

</script>