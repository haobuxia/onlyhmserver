<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--- 该页面已废弃,视频图片列表页已改版,声音文件类型如何处理没确定 -->
<%--
<div class="container list-data-${type}">
    <div class="row">
        <c:forEach var="v" items="${vo.list}">
            <div class="col s12 m6 l3">
                <c:choose>
                    <c:when test="${type == 'file'}">
                        <div class="card horizontal">
                            <div class="card-image valign-wrapper">
                                <i class="small material-icons">insert_drive_file</i>
                            </div>
                            <div class="card-stacked">
                                <div class="card-content">
                                    <p class="truncate" style="max-width:300px;">${v.id}&nbsp;&nbsp;${v.neUserName}
                                        <br> ${v.getCreateTimeString()}</p>
                                </div>
                                <div class="card-action btnDiv-${type}" data-id="${v.id}">
                                    <a data-href="/${type}/play/${v.id}" class="truncate" title="点击查看文件"
                                       style="max-width:200px;">${v.fileName}</a>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${type == 'audio'}">
                        <div class="card horizontal">
                            <div class="card-image valign-wrapper">
                                <i class="small material-icons">audiotrack</i>
                            </div>
                            <div class="card-stacked">
                                <div class="card-content">
                                    <p class="truncate" style="max-width:300px;">${v.id}&nbsp;&nbsp;${v.neUserName}
                                        <br> ${v.getCreateTimeString()}</p>
                                </div>
                                <div class="card-action btnDiv-${type}" data-id="${v.id}">
                                    <a data-href="/${type}/play/${v.id}" class="truncate" title="点击播放声音"
                                       style="max-width:300px;"><i class="small material-icons">play_arrow</i></a>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${type == 'video'}">
                        <div class="card">
                            <div class="card-image btnDiv-${type}" data-id="${v.id}" data-mc="${v.machineCode}">
                                <a data-href="/${type}/play/${v.id}" title="点击播放视频"
                                   onclick="showAlert('请稍候，载入播放页面中...');">
                                    <c:choose>
                                        <c:when test="${v.seconds == 0}">
                                            <img class="responsive-img" src="/static/images/video-invalid.png"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img class="responsive-img" src="${fileServer}${v.thumbOssPath}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                            <div class="card-content truncate" style="max-width:300px;">
                                <span class="card-title">${v.id}&nbsp;&nbsp;${v.neUserName}</span>
                                <p>${v.getCreateTimeString()}&nbsp;&nbsp;${v.seconds}秒</p>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${type == 'image'}">
                        <div class="card">
                            <div class="card-image btnDiv-${type}" data-id="${v.id}" data-mc="${v.machineCode}">
                                <a data-href="/${type}/play/${v.id}" title="点击查看图片">
                                    <img class="responsive-img" src="${fileServer}${v.thumbOssPath}"
                                         data-src="/${v.ossPath}"/>
                                </a>
                            </div>
                            <div class="card-content truncate" style="max-width:300px;">
                                <span class="card-title">${v.id}&nbsp;&nbsp;${v.neUserName}</span>
                                <p>${v.getCreateTimeString()}</p>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </c:forEach>
    </div>
    <jsp:include page="../include/page-pager.jsp"></jsp:include>
</div>

<script>
    var enableLinkClick = function () {
        var links = $("div.list-data-${type} a[data-href]");
        $.each(links, function (idx, alink) {
            $(alink).css("cursor", "pointer").off('click').click(function () {
                var href = $(this).attr("data-href");
                loadMainContent(href);
            });
        });
    }
    $(function () {
        enableLinkClick();
    });
</script>--%>
