function initMapData() {
    //初始化地图
    initializeMap(10);
    //初始化数据
    var lonlat = videoPage.lonlat;
    if (lonlat != null && lonlat[0] > 0 && lonlat[1] > 0) {
        console.debug('视频定位信息显示在地图上');
        createMapMarker("video_" + videoPage.videoId, null, lonlat[0], lonlat[1], "");
        setMapCenter(lonlat[0], lonlat[1], 12);
    } else {
        console.debug('视频没有定位信息,不显示定位');
    }
}

var initVideoPlayer = function (videoPlayerId) {
    videoPage.video = $(videoPlayerId)[0];
}

var delTrackVideo = function () {
    if (confirm("确定要删除视频的字幕版吗？再次查看本视频时会自动重新生成字幕版视频.")) {
        ajaxPost("/track/delVideo/" + videoPage.videoId, {}, function (resp) {
            if (resp.success) {
                showAlert("删除成功.页面即将刷新...");
                loadMainContent("/video/play/" + videoPage.videoId);
            } else {
                showAlert(resp.message);
            }
        });
    }
}


$(function () {
    initVideoPlayer('#my-video-o');
    initMapData();
    // requestFullScreen();
});
