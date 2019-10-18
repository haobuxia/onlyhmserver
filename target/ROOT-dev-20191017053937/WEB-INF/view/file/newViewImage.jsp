<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main-content">
    <div class="container-fluid">
        <div class="panel panel-profile">
            <div class="clearfix">
                <!-- left -->
                <div class="profile-left" style="background-color: #fff;">
                    <div class="profile-info">
                        <a  class="text-muted" href="#" onclick="toQueryPanel()"><span class="lnr lnr-chevron-left"></span> 返回</a>
                    </div>
                    <div class="profile-detail">
                        <div class="profile-info">
                            <h4 class="heading">用户信息</h4>
                            <ul class="list-unstyled list-justify">
                                <li>客户ID <span>${helmet.affiliationId}</span></li>
                                <li>头盔ID <span>${helmet.deviceUUID}</span></li>
                                <li>头盔账号 <span>${helmet.deviceNumber}</span></li>
                                <li>机手ID <span>${entity.userId}</span></li>
                                <li>机号<span>${entity.machineCode}</span></li>
                            </ul>
                        </div>
                        <div class="profile-info">
                            <h4 class="heading">照片信息</h4>
                            <ul class="list-unstyled list-justify">
                                <li>定位<span>(${entity.lon},${entity.lat})</span></li>
                                <li>查看次数<span>${entity.viewCount}</span></li>
                                <li>文件大小<span>${entity.sizeKb}KB</span></li>
                                <li>上传时间<span>${entity.getUploadTimeString()}</span></li>
                                <li>拍摄时间<span>${entity.getCreateTimeString()}</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- right -->
                <div class="profile-right" id="contentArea">

                    <div id="displayDiv" style="min-height: 600px;">
                        <img class="responsive-img" src="${fileServer}${entity.ossPath}" style="max-width: 100%;">
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var toQueryPanel=function () {
        $('#mediaShownModal').modal('hide');
        $('#mediaShownModal').empty();
    }
</script>