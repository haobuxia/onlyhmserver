
function startDrawCircle() {
    initDrawer(TYEnumOverLay.TY_CIRCLE,circleDrawOver);
}

function circleDrawOver(e) {
    console.debug("圆圈绘制完毕."+JSON.stringify(e));
    TYDrawStop(false);

    var lonlat = e.GPSLngLat;//{"GPSLng":114.401367,"GPSLat":37.993729}
    var radius = e.radius;//米
    var circleId = e.id;

    removeMarker(circleId);//先删除再重绘制，以便添加双击事件

    addCircle("circle_"+circleId,new TYLngLat(lonlat.GPSLng,lonlat.GPSLat),radius,function () {
        //删除圆圈并清空车辆定位信息
        console.debug("双击圆圈，则删除圆圈");
        removeMarker("circle_"+circleId);
        var pointInCircleArray = imeiIndexPage.circlePointsMap[circleId];
        console.debug("双击圆圈后删除圆圈内的车辆信息.数量="+pointInCircleArray.length);
        imeiIndexPage.circlePointsMap[circleId]=[];//清空
        $.each(pointInCircleArray,function (idx,point) {
            var jh = point.value;
            removeMarker("jh_"+jh);
        });
    });

    console.debug("调用getPointInCircle得到圆圈内的车辆定位...");
    getPointInCircle(lonlat.GPSLng,lonlat.GPSLat,radius,function (resp) {
        // console.debug("getPointInCircle 结果="+resp);
        var status = resp.data.status;
        if(status == "1"){
            var pointResult = resp.data.result;
            //var pointCount = pointResult.pointCount;
            var pointInCircleArray = pointResult.PointInCircleArray;
            imeiIndexPage.circlePointsMap[circleId] = pointInCircleArray;
            $.each(pointInCircleArray,function (idx,point) {
                //{"lnglat":[114.394199,38.255833],"id":734,"value":"DBH1140"}
                var lnglat = point.lnglat;
                // var id = point.id;
                var jh = point.value;
                createMapMarker("jh_"+jh,null,lnglat[0],lnglat[1],jh);
            });
        }else{
            showAlert("此范围内没有车辆");
        }
    });
}

function initMapData() {
    //初始化地图
    $("#mapContainer").css("min-height",$("main").css("height"));
    initializeMap(8);
}

$(function () {
    initMapData();
});