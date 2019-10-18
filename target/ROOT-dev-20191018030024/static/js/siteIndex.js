function saveNewSite(e){
    var lngLats = e.GPSLngLat;//数组,[{"GPSLng":114.378015,"GPSLat":38.012406},{"GPSLng":114.374585,"GPSLat":37.995633},{"GPSLng":114.420619,"GPSLat":37.99726},{"GPSLng":114.395868,"GPSLat":38.020786},{"GPSLng":114.380074,"GPSLat":38.008888}]
    var firstLngLat = lngLats[0];
    geoToAddress(firstLngLat.GPSLng,firstLngLat.GPSLat,function (result) {
        console.debug('根据定位解析地址结果:'+result+":"+JSON.stringify(result));
        var geoCode = result.geocodes[0];
        var lonlatArr = [];
        var tyLonLatArray = [];
        $.each(lngLats,function(idx,lonlat){
            lonlatArr.push(lonlat.GPSLng+","+lonlat.GPSLat);
            tyLonLatArray.push(new TYLngLat(lonlat.GPSLng,lonlat.GPSLat));
        });
        console.debug('构造工地边框结束.');
        var site = {};
        site.id=0;
        site.name="未命名工地";
        site.area= e.area/100000;
        site.lonLats= lonlatArr.join(";");
        site.lon = firstLngLat.GPSLng;
        site.lat = firstLngLat.GPSLat;
        site.address= geoCode.description;
        site.province = geoCode.province;
        site.city = geoCode.city;
        site.district = geoCode.district;
        site.township = geoCode.township;
        site.street = geoCode.street;
        console.debug('构造工地数据结束.');
        // site.siteImageLonLats = siteImagesCheck(tyLonLatArray);
        site.siteVideoLonLats = siteVideosCheck(tyLonLatArray);
        console.debug('构造工地图像视频结束.');
        TYDrawStop(false);
        saveSiteData(site,true);
    });
}


function polyDrawOverSave(e){
    console.debug("polyDraw over event:"+e+""+JSON.stringify(e));
    try{
        if(confirm("确定设置此区域为一个新的工地吗？")){
            saveNewSite(e);
        }else{
            TYDrawStop(false);
            removeMarker(e.Id);
        }
        // var lngLats = e.GPSLngLat;//数组,[{"GPSLng":114.378015,"GPSLat":38.012406},{"GPSLng":114.374585,"GPSLat":37.995633},{"GPSLng":114.420619,"GPSLat":37.99726},{"GPSLng":114.395868,"GPSLat":38.020786},{"GPSLng":114.380074,"GPSLat":38.008888}]
        // var firstLngLat = lngLats[0];
        // setMapCenter(firstLngLat.GPSLng,firstLngLat.GPSLat, 12);//定位到地图的中心
    }catch(e){
        console.error("保存工地异常."+e);
    }
}


function showSiteInForm(siteId){
    console.debug('显示工地信息.'+siteId);
    var site = siteIndexPage.siteMap[siteId];
    if(site == null){
        showAlert('工地信息不存在');
        return;
    }

    $("#site_id").val(siteId);
    $("#site_lonLats").val(site.lonLats);
    $("#site_name").val(site.name);
    var firstLonLat = site.lonLats.split(";")[0];
    $("#site_lonlat").val(firstLonLat);
    $("#site_area").val(site.area);
    $("#site_address").val(site.address);
    $("#site_province").val(site.province);
    $("#site_city").val(site.city);
    $("#site_district").val(site.district);
    $("#site_township").val(site.township);
    $("#site_street").val(site.street);

    $('#siteModal').modal('show');
}

function saveSiteFormData() {
    console.debug('保存工地信息');
    var site = {};
    site.id = $("#site_id").val();
    site.lonLats = $("#site_lonLats").val();
    site.name = $.trim($("#site_name").val());
    site.area = $("#site_area").val();
    site.address = $("#site_address").val();
    site.province = $("#site_province").val();
    site.city = $("#site_city").val();
    site.district = $("#site_district").val();
    site.township = $("#site_township").val();
    site.street = $("#site_street").val();
    var lonlat = $("#site_lonlat").val().split(",");
    site.lon = lonlat[0]*1;
    site.lat = lonlat[1]*1;
    saveSiteData(site,false);
}

function saveSiteData(site,openSiteFormWhenSuccess) {
    $.post("/site/saveSite",site,function (resp) {
        if(resp.success){
            showAlert("保存成功");
            site.id = resp.data;
            siteIndexPage.siteMap[site.id] = site;

            //site重新绘制1次,因原版site不支持添加双击事件
            removeSiteInMap(site.id);
            showSiteInMap(site.id);

            if(openSiteFormWhenSuccess){
                showSiteInForm(site.id);
            }else{
                $('#siteModal').modal('hide');
            }
        }else{
            showAlert("保存失败."+resp.message);
        }
    },'json');
}

function delSiteData() {
    if(confirm("确定删除这个工地吗？")){
        var siteId = $("#site_id").val();
        $.post("/site/deleteSite/"+siteId,{},function (resp) {
            if(resp.success){
                showAlert("删除成功.");
                siteIndexPage.siteMap[siteId] = null;
                removeSiteInMap(siteId);
                $('#siteModal').modal('hide');
            }else{
                showAlert("删除失败."+resp.onmessage);
            }
        },'json');
    }
}

function showSiteInMap(siteId){
    var site = siteIndexPage.siteMap[siteId];
    if(site == null){
        cosole.debug("site在缓存中不存在."+siteId);
        return;
    }
    var lonLats = site.lonLats;
    var arr = lonLats.split(";");
    var lnglatArr = [];
    $.each(arr,function(idx2,lonlat){
        var arr2 = lonlat.split(",");
        lnglatArr.push(new TYLngLat(arr2[0]*1, arr2[1]*1));
    });
    addPolygon("site_"+site.id,lnglatArr,site.name,function(){
        showSiteInForm(siteId);
    });
}

function removeSiteInMap(siteId){
    removePolygon("site_"+siteId);
}

function initSiteData(){
    console.debug("初始化工地信息.");
    $.each(siteIndexPage.siteMap,function (siteId,site) {
        showSiteInMap(siteId);
    });
}

function siteImagesCheck(siteLonLatArray){
    var imgInSiteArray=[];
    $.each(siteIndexPage.imageCounts,function (idx,img) {
        var point = new TYLngLat(img.one,img.two);
        var inSite = pointInPolygon(point,siteLonLatArray);
        if(inSite){
            imgInSiteArray.push(img.one+","+img.two);
        }
    });
    console.debug("在工地中的图片有："+imgInSiteArray.length);
    return imgInSiteArray.join(";");
}

function siteVideosCheck(siteLonLatArray){
    var videoInSiteArray=[];
    $.each(siteIndexPage.geoData,function (geo,dataList) {
        var geoArray = geo.split(",");
        var point = new TYLngLat(geoArray[0]*1,geoArray[1]*1);
        var inSite = pointInPolygon(point,siteLonLatArray);
        if(inSite){
            videoInSiteArray.push(geo);
        }
    });
    console.debug("在工地中的视频，数量有："+videoInSiteArray.length+"个.定位数据="+videoInSiteArray);
    return videoInSiteArray.join(";");
}

function startDrawPolygon(){
    initDrawer(TYEnumOverLay.TY_POLYGON,polyDrawOverSave);
}

function initVideoImageGeoData() {
    console.debug("初始化基于地理位置的视频图片信息.");
    //载入所有带地理位置的视频信息及其标签
    $.get("/site/videoGeoData",{},function (resp) {
        if(resp.success){
            siteIndexPage.geoData = resp.data;
            showGeoData();
        }else{
            showAlert("载入数据失败."+resp.message);
        }
    });
}

function showTagsInSelect(){
    var selectObj = $("#tagSelect");
    selectObj.empty();
    selectObj.append('<option value="">选择标签</option>');
    $.each(siteIndexPage.tagArray,function(idx,tagName){
        selectObj.append('<option value="'+tagName+'">'+tagName+'</option>');
    });
    // //下拉条控件初始化
    loadTagRes();
}

function loadTagRes() {
    var tagName = $("#tagSelect").val();
    TYClear();

    var geoIdx = 0;
    $.each(siteIndexPage.geoData,function (geo,geoDataList) {
        var geoVideoIds = {};
        $.each(geoDataList,function (idx,geoData) {
            if(tagName == ""){
                geoVideoIds[geoData.resId]=1;//去重
            }else{
                if(geoData.tagName == tagName){
                    geoVideoIds[geoData.resId]=1;
                }
            }
        });

        if(! $.isEmptyObject(geoVideoIds)){
            geoIdx ++ ;
            var geoArr = geo.split(",");
            var videoIdArray = [];
            for (var videoId in geoVideoIds){
                videoIdArray.push(videoId);
            }
            //lng,lat,markerId,labelContent,iconUrl,clickUrlOrFunc,setCenter
            setMapPointMarker(geoArr[0]*1,geoArr[1]*1,"video_"+geoIdx,videoIdArray.length+"个视频",null,function(){
                // showAlert("播放视频.数据:"+(videoIdArray.join(",")));
                if(videoIdArray.length > 1 ){
                    showVideoInPlayList(videoIdArray);
                }else{
                    playVideo(videoIdArray[0]);
                }
            });
        }
    });
    TYSetLevel(10);
}

function showVideoInPlayList(videoIdArray) {
    $("#playList").empty();
    $.each(videoIdArray,function(idx,videoId){
        $("#playList").append('<a href="javascript:playVideo('+videoId+')" class="collection-item">'+videoId+'</a>');
    });
    $("#playListModal").modal('show');
}

function playVideo(id){
    $.get("/video/play/"+id+"/url",{},function(resp){
        if(resp.success){
            $("#playVideo").attr("src",resp.data);
            $('#playVideoModal').modal('show');
        }else{
           showAlert("获取视频地址失败."+resp.message);
        }
    },"json");
}

function showGeoData() {
    siteIndexPage.geoData = siteIndexPage.geoData ||{};
    siteIndexPage.tags = {};
    $.each(siteIndexPage.geoData,function (key,dataList) {
        $.each(dataList,function(idx,data){
            if(data.tagName == null) data.tagName = "无标签";
            siteIndexPage.tags[data.tagName]=1;
        });
    });
    siteIndexPage.tagArray = [];
    $.each(siteIndexPage.tags,function (tagName,val) {
        siteIndexPage.tagArray.push(tagName);
    })
    siteIndexPage.tagArray.sort();

    showTagsInSelect();
}

function closePlayVideo(){
    $("#playVideo")[0].pause();
    $("#playVideo").removeAttr("src");
    $('#playVideoModal').modal('hide');
}

function initMapData(){
    //初始化地图
    $("#mapContainer").css("min-height",$("main").css("height"));

    initializeMap(10);

    initVideoImageGeoData();

    $("#site-save-button").off('click').on('click',saveSiteFormData);
    $("#site-del-button").off('click').on('click',delSiteData);
    $("#tagSelect").off('change').on('change',loadTagRes);
    $("#playVideoCloseBtn").off('click').on('click',closePlayVideo);

    window.setTimeout('initSiteData()',500);
}

$(function () {
    initMapData();
});