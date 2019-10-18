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
            <div class="col s6 m6 l3"><span class="black-text">文件名:${entity.fileName}</span></div>
        </div>
        <div class="divider"></div>
        <div class="section no-pad-bot">
            <div class="row" style="overflow:hidden" id="contentArea">
                <div class="col s12" id="displayDiv">
                    <c:choose>
                        <c:when test="${type=='audio'}">
                            <div class="card blue-grey darken-1">
                                <div class="card-content white-text">
                                    <span class="card-title">音频播放</span>
                                    <p>
                                        <audio src="${fileServer}${entity.ossPath}" controls="controls" preload="preload">
                                            您的浏览器不支持 audio 标签。
                                        </audio>
                                    </p>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:viewFileDetail()">查看文件内容</a>
                            <a href="javascript:downloadFile()">下载文件</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var viewFilePage = {};
    viewFilePage.type = '${type}';
    viewFilePage.id = ${entity.id};
    viewFilePage.ossPath = '${fileServer}${entity.ossPath}';
</script>
<script type="text/javascript" src="/static/js/viewFile.js?v=${version}"></script>
