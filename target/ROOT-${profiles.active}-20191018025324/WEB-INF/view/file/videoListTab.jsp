<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <div class="main-content">
            <div class="panel clearifx">
                <div class="panel-heading">
                    <h3 class="panel-title">视频分类搜索</h3>
                </div>                
                <div class="panel-body" id="btnDiv">
                    <div class="btn btn-primary btn-lg" data-groupby="searchlist">
                        <span class="white-text">按拍摄时间</span>
                    </div>
                    <div class="btn btn-primary btn-lg" data-groupby="siteid">
                        <span class="white-text">按工地</span>
                    </div>
                    <div class="btn btn-primary btn-lg" data-groupby="clientid">
                        <span class="white-text">按头盔IMEI</span>
                    </div>
                    <div class="btn btn-primary btn-lg" data-groupby="neusername">
                        <span class="white-text">按头盔账号</span>
                    </div>
                    <div class="btn btn-primary btn-lg" data-groupby="machinecode">
                        <span class="white-text">按车辆机号</span>
                    </div>
                    <div class="btn btn-primary btn-lg" data-groupby="imei">
                        <span class="white-text">按车辆盒子IMEI</span>
                    </div>
                    <div class="btn btn-primary btn-lg" data-groupby="tag">
                        <span class="white-text">按标签</span>
                    </div>
                    <div class="btn btn-primary btn-lg" data-groupby="svcdata">
                        <span class="white-text">服务数据</span>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(function () {
                $("#btnDiv div[data-groupby]").css("cursor","pointer").click(function () {
                    var groupBy = $(this).attr("data-groupby");
                    loadMainContent("/list/video/"+groupBy);
                });
            })
        </script>