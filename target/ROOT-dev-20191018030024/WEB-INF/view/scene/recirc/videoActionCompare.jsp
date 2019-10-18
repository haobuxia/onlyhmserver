<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="section">
    <div class="row" id="videoActionCompareSection">
    </div>
    <div class="divider" style="margin-top:80px;margin-bottom: 50px;"></div>
    <div class="row">
        <table>
            <thead>
            <tr>
                <th>视频ID</th>
                <th>头盔</th>
                <th>视频时间</th>
                <th>车载盒子</th>
                <th>机号</th>
                <th>查看</th>
            </tr>
            </thead>
            <tbody id="compareTableBody">

            </tbody>
        </table>
    </div>
</div>

<div id="playImageModal" class="modal modal-fixed-footer">
    <div class="modal-content">
        <div class="row center">
            <img class="responsive-img" id="playImage"  src=""/>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" id="playImageCloseBtn" class="modal-action waves-effect waves-red btn-flat">关闭</a>
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

<script type="text/javascript" src="/static/echarts/echarts.min.js"></script>
<script>
    var videoActionComparePage = {};
    videoActionComparePage.fileServer = '${fileServer}';
    videoActionComparePage.typeNameIdMap = ${typeNameIdMap};
    videoActionComparePage.videoList = ${videoList};
</script>
<script src="/static/js/videoActionCompare.js?v=${version}"></script>

