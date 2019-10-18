//地图初始化 如果center类型为Point时，zoom必须赋值，范围3-19级，若调用高清底图（针对移动端开发）时，zoom可赋值范围为3-18级。如果center类型为字符串时，比如“北京”，zoom可以忽略，地图将自动根据center适配最佳zoom级别
function initializeMap(mapLevel,maxHeight) {
    if(maxHeight){
        var mainHeight = getElementIntHeight("main");
        if(mainHeight){
            $("#mapContainer").css("height",(mainHeight-50)+"px");
        }
    }
    // 创建地图实例
    try{
        var option = new TYMapOptions({
            contentId: "mapContainer",
            //必填，地图容器
            lnglat: new TYLngLat(114.50, 38.000),//默认石家庄
            //可选 默认ip定位
            mapLevel: mapLevel //可选默认 12
        });
        TYInitialize(option);
    }catch(e){
        showAlert("初始化地图异常."+e);
    }
}

//设置地图的中心点
function setMapCenter(lon, lat, mapLevel) {
    // 初始化地图，设置中心点坐标和地图级别
    TYSetCenter(new TYLngLat(lon * 1, lat * 1));
    TYSetLevel(mapLevel);
}

//添加热点地图
function addHeatmapOverlay(pointsArray) {
    TYClear();
    var option = new TYHeatMapOption({
            heatMapDatas: {
                radius: 25, // 给定半径
                opacity: [0, 0.8], gradient: {
                    0.5: 'blue',
                    0.65: 'rgb(117,211,248)',
                    0.7: 'rgb(0, 255, 0)',
                    0.9: '#ffea00',
                    1.0: 'red'
                },
                max: 100,
                DataSet: pointsArray
            }
        }
    );
    TYHeatMap(option);
}

//添加圆形
function addCircle(circleId, tyLngLat, radius, clickEventCallback) {
    var cirArr = [];
    var data = new TYCircle();
    data.circleId = circleId;
    data.fillColor = "green";
    data.fillOpacity = .6;
    data.lineColor = "blue";
    data.lineOpacity = .8;
    data.lineType = null;
    data.lineWidth = 2;
    data.lnglat = tyLngLat;
    data.radius = radius;
    cirArr.push(data);

    var circle = new TYCircleOption();
    circle.circleData = cirArr;
    circle.circleState.isClearOverlay = true;
    circle.circleState.isSetView = true;
    //实例化事件参数
    if (clickEventCallback != null) {
        console.debug('为圆圈添加双击事件');
        circle.circleState.overlayEvent = createDoubleClickEvent(clickEventCallback);
    }
    TYDrawCircle(circle);
}

//添加多边形
function addPolygon(id, lnglatArr, labelTxt, clickCallback) {
    var data = new TYPolygon();
    data.fillColor = "green";
    data.fillOpacity = 0.7;
    data.isEdit = false;//不可编辑
    data.lineColor = "red";
    data.lineOpacity = 0.8;
    data.lineType = null;
    data.lineWidth = 1;
    data.lnglat = lnglatArr;//TYLngLat数组
    data.polygonId = id;

    var polygon = new TYPolygonOption();
    polygon.polygonData = [data];
    polygon.polygonState.isClearOverlay = false;//
    polygon.polygonState.isSetView = true;
    if (labelTxt) {
        var lonLat = lnglatArr[0];
        setMapPointMarker(lonLat.lng*1, lonLat.lat*1, 'polygonMarker_' + id, labelTxt, 'http://mapapi.tygps.com/Images/logo.png', clickCallback, true);
        var inSite = pointInPolygon(lonLat, lnglatArr);
        console.debug("创建多边形." + lnglatArr + ",标志点:" + lonLat + ".标志点在多边形内吗？" + inSite);
    }

    if (clickCallback != null) {
        console.debug('创建多边形双击事件.');
        polygon.polygonState.overlayEvent = createDoubleClickEvent(clickCallback);
    }

    TYDrawPolygon(polygon);
}

function removePolygon(id) {
    console.debug("删除多边形：" + id);
    removeMarker(id);
    //可能包含了1个point
    console.debug("删除多边形里的点：" + 'polygonMarker_' + id);
    removeMarker('polygonMarker_' + id);
}

function createDoubleClickEvent(dbClickCallback) {
    var event = new TYOverlayEvent();
    event.mouseEvent = TYEnumEvent.TY_DOUBLECLICK;
    event.mouseFunc = dbClickCallback;
    return event;
}

function createClickEvent(dbClickCallback) {
    var event = new TYOverlayEvent();
    event.mouseEvent = TYEnumEvent.TY_CLICK;
    event.mouseFunc = dbClickCallback;
    return event;
}

function removeMarker(markerId) {
    TYRemove(markerId);
}


//图形绘制工具
function initDrawer(overlayType, drawOverFunc) {
    var draw = new TYOverlayOption();
    draw.isClearOverlay = false;
    draw.isShowMath = false;//不显示绘制图形的面积，且图形还可以删掉
    draw.overlayType = overlayType;//多边形 TYEnumOverLay.TY_RECTANGLE 矩形;TYEnumOverLay.TY_CIRCLE 圆形
    draw.callfunc = drawOverFunc;
    TYDrawOverlay(draw);
}

function getPointInCircle(lon, lat, radius, callback) {
    var url = "/imei/getPointInCircle?radius=" + radius + "&lon=" + lon + "&lat=" + lat;
    console.debug("查询范围内车辆信息.url=" + url);
    ajaxGet(url, function (resp) {
        doCallback(callback, [resp]);
    });
}
//在地图中创建marker
function createMapMarker(markId, iconImgUrl, lng, lat, labelContent, markerClickUrlOrCallbackFunc) {
    var data = [];
    var markerConfig = {
        pointId: markId,
        label: new TYLabel({
            content: "<span style='color:red;'>" + labelContent + "</span>",
            offset: new TYPixel(25, 15)
        }),
        lnglat: new TYLngLat(lng, lat),
    };
    if (iconImgUrl) {
        markerConfig.icon = new TYIcon({//点图标对象
            url: iconImgUrl,//点图标地址
            size: new TYSize(31, 31)//图标可视区域
        });
    }
    var marker = new TYMarker(markerConfig);
    data.push(marker);

    var pointobj = new TYPointOption();
    pointobj.pointData = data;
    pointobj.pointState.isClearOverlay = false;//不删除之前的
    pointobj.pointState.isSetView = true;
    // 实例化事件参数
    if (markerClickUrlOrCallbackFunc != undefined) {
        var event = new TYOverlayEvent();
        event.mouseEvent = TYEnumEvent.TY_CLICK;
        event.mouseFunc = function (e) {
            if ($.isFunction(markerClickUrlOrCallbackFunc)) {
                doCallback(markerClickUrlOrCallbackFunc, [markId])
            } else {
                loadMainContent(markerClickUrlOrCallbackFunc);
            }
        };
        pointobj.pointState.overlayEvent = event;
    }
    // 绘制函数
    TYDrawPoint(pointobj);
    return marker;
}

//更新或创建marker
function setMapPointMarker(lng, lat, markerId, labelContent, iconUrl, clickUrlOrFunc, setCenter) {
    // 删除旧的点位(如果存在)
    removeMarker(markerId);
    //创建点位
    if (lng == 0 && lat == 0) { //有问题的定位
        return;
    }

    var oldLevel = _map.getZoom();
    createMapMarker(markerId, iconUrl, lng, lat, labelContent, clickUrlOrFunc);
    if (setCenter) {
        setMapCenter(lng, lat, oldLevel);//定位到地图的中心
        //console.debug("设置marker在地图中心.地图level="+oldLevel+",设置完毕后获取level="+_map.getZoom());
    }

}

function geoToAddress(lon, lat, callbackFunc) {
    var arr = [new TYLngLat(lon, lat)];
    var geos = new TYGeoCoderOptions();//逆地理编码参数实例化
    geos.callback = callbackFunc;//转换后的回调函数
    geos.location = arr;// arr;//需要转换的经纬度数组
    geos.type = TYEnumGeocoder.TY_NEAR_DESC;//转换类型，“附近类型”描述
    TYGeoCoder(geos);
}

function pointInPolygon(lnglatPoint, lnglatArr) {
    var sp = new TYSpasisOptions();
    sp.point = lnglatPoint;//new TYLngLat(114.90, 38.2);
    sp.polyGon = lnglatArr;
    sp.sisType = TYEnumSpAsis.P_PGON;
    var pointIn = TYSpAsis(sp);
    return pointIn;
}