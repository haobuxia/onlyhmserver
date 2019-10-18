<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="section no-pad-bot">
    <div class="container">
        <div class="row">
        <c:if test="${not empty helmet}">
            <div class="col s6 m6 l3"><span class="black-text">客户ID:${helmet.affiliationId}</span></div>
            <div class="col s6 m6 l3"><span class="black-text">头盔ID:${helmet.deviceUUID}</span></div>
            <div class="col s6 m6 l3"><span class="black-text">头盔账号:${helmet.deviceNumber}</span></div>
        </c:if>
            <div class="col s6 m6 l3"><span class="black-text">机手ID:${entity.userId}</span></div>
            <div class="col s6 m6 l3"><span class="black-text">上传时间:${entity.getUploadTimeString()}</span></div>
            <div class="col s6 m6 l3"><span class="black-text">拍摄时间:${entity.getCreateTimeString()}</span></div>
            <div class="col s6 m6 l3"><span class="black-text">文件大小:${entity.sizeKb}KB</span></div>
            <div class="col s6 m6 l3"><span class="black-text">查看次数:${entity.viewCount}</span></div>
            <div class="col s6 m6 l3"><span class="black-text"></span></div>
            <div class="col s6 m6 l3"><span class="black-text">机号:${entity.machineCode}</span></div>
            <div class="col s6 m6 l3"><span class="black-text">定位:(${entity.lon},${entity.lat})</span></div>
            <c:if test="${entity.siteId > 0 }">
                <div class="col s6 m6 l3"><span class="black-text">工地:${entity.site}</span></div>
            </c:if>
        </div>
        <div class="divider"></div>
        <div class="section no-pad-bot">
            <div class="row" style="overflow:hidden" id="contentArea">
                <div class="col s12" id="displayDiv">
                    <img class="responsive-img" src="${fileServer}${entity.ossPath}"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/extif-js/exif.js"></script>
<script type="text/javascript" src="/static/js/viewImage.js?v=${version}"></script>