function initMapData() {
    //初始化地图
    initializeMap(10);
    //初始化数据
    var lonlat = videoExtPage.lonlat;
    if (lonlat != null && lonlat[0] > 0 && lonlat[1] > 0) {
        console.debug('视频定位信息显示在地图上');
        createMapMarker("video_" + videoExtPage.videoId, null, lonlat[0], lonlat[1], "");
        setMapCenter(lonlat[0], lonlat[1], 12);
    } else {
        console.debug('视频没有定位信息,不显示定位');
    }
}

var initVideoText = function () {
    var extend = videoExtPage.videoExtend;
    var txt = "";
    if(extend == undefined || extend == null){

    } else {
        txt = (extend.audioEditText == undefined || $.trim(extend.audioEditText) == "") ? extend.audioOrigText : extend.audioEditText;
    }
    $('#videoText').val(txt);
    // $('#videoText').trigger('autoresize');
}

var initVideoKeyList = function () {
    var list = $("tbody");
    list.html('');
    $.each(videoExtPage.keywordList, function (idx, kw) {
        var html = '<tr><td>' + kw.timeseconds + '</td>'
            + '<td>' + kw.perspeech + '</td>'
            + '<td><span id="' + kw.id + '">' + (kw.keywordName==undefined?'':kw.keywordName) + '</span></td>'
            + '<td><a class="label label-info" onclick="editOpen(' + kw.id + ',\'' + kw.keywordName + '\',\'' + kw.perspeech  + '\',\'' + kw.keywordId + '\',\'' + kw.videoId + '\');">修改</a></td>'
            + '</tr>';
        list.append(html);

        // addToKeywordArea(kw.keywordName,kw.keywordId,kw.id);
    });

}
var initKeywordSelect= function () {
    $.each(videoExtPage.kwList, function (idx, kw) {
        $("#edit_keyword").append('<option value="' + kw.id + '">' + kw.keywordName + '</option>');
    });
    $("#editsubmit-button").off('click').click(function () {
        editSubmit();
    });
}
var editSubmit = function () {
    var videoKeyWord = {};
    videoKeyWord.id = $.trim($("#edit_id").val());
    videoKeyWord.videoId = $.trim($("#edit_videoId").val());
    videoKeyWord.keywordId = $.trim($("#edit_keyword").val());
    var postUrl = "/videoext/updatekeyword";
    $.post(postUrl,videoKeyWord,function(resp){
        if(resp.success){
            $("#editUserModal").modal('hide');
            showAlert("保存成功");
            $("#"+videoKeyWord.id).html($("#edit_keyword option:selected").text());
        }
    },"json");
}
var editOpen = function (id,keywordName,perspeech,keywordId,videoId) {
    $("#edit_id").val(id);
    $("#edit_videoId").val(videoId);
    $("#edit_perspeech").val(perspeech);
    $("#edit_keyword").val(keywordId);

    $("#editUserModal").modal("show");
}

var initVideoKeyword = function () {
    $.each(videoExtPage.keywordList, function (idx, kw) {
        addToKeywordArea(kw.keywordName,kw.keywordId);
    });
}

var checkAddKeyword = function (kwToAdd) {
    var kwExists = false;
    $.each(videoExtPage.keywordList, function (idx, kw) {
        if (kw.keywordName == kwToAdd) {
            kwExists = true;
            return false;
        }
    });

    return !kwExists;
}

var addToKeywordArea = function (kwStr,kwId) {
    var kwChip = $('<div class="chip btn btn-primary" data-key="'+kwId+'">'+kwStr+'</div>');
    var closeBtn = $('<i class="myClose fa fa-close" title="点击移除此关键词" style="display: none;"></i>');
    kwChip.append(closeBtn);
    $("#keywordArea").append(kwChip);
    closeBtn.click(function(){
        var keywordId = $(this).parents("div.chip").attr("data-key");
        removeVideoKeyword(keywordId);
    });
    if($("#keywordArea i:first").is(":visible")){
        closeBtn.css("display","block");
    }
}

var initVideoExtEvent = function () {
    $("#keyword").off('keydown').keydown(function (event) {
        if (event.keyCode == 13) {
            var txt = $.trim($("#keyword").val());
            if (txt != "") {
                var isNewKw = checkAddKeyword(txt);
                if (isNewKw) {
                    addKeywordToVideo(txt);
                }else{
                    $("#keyword").val("");
                    showAlert("关键词已添加");
                }
            }
        }
    });
    $("#videoTextSaveBtn").off('click').click(function () {
        var txt = $.trim($("#videoText").val());
        saveVideoText(txt);
    });
}

var saveVideoText = function (text) {
    ajaxPost("/videoext/savetext/" + videoExtPage.videoId, {editText: text}, function (resp) {
        if(resp.success){
            showAlert("保存成功");
        }else{
            showAlert("保存失败."+resp.message);
        }
    });
}

var addKeywordToVideo = function (keyword) {
    ajaxPost("/videoext/addkeyword/" + videoExtPage.videoId, {"keyword": keyword}, function (resp) {
        if(resp.success){
            var kw = resp.data;
            addToKeywordArea(kw.keywordName,kw.keywordId);
            videoExtPage.keywordList.push(kw);
            $("#keyword").val("");
            showAlert("添加成功");
        }else{
            showAlert("添加关键词失败."+resp.message);
        }
    });
}

var removeVideoKeyword = function (keywordId) {
    ajaxPost("/videoext/removekeyword/" + videoExtPage.videoId, {"keywordId": keywordId}, function (resp) {
        if(resp.success){
            $("div[data-key="+keywordId+"]").remove();
            $.each(videoExtPage.keywordList,function (idx,kw) {
               if(kw.keywordId == keywordId){
                   //移除数组中这个关键词缓存
                   videoExtPage.keywordList.splice(idx,1);
                   return false;
               }
            });
            showAlert("移除关键词成功");
        }else{
            showAlert("移除关键词失败."+resp.message);
        }
    });
}
var settings = {
    orientation: 'horizontal',
    datesDiv: '#dates',
    datesSelectedClass: 'selected',
    containerDiv: 	'#timeline',
};
var setVideoPlayTime = function (time) {
    if(time!=null && time != undefined){
        $('#my-video')[0].currentTime=time;
    }
}
var initVideoTimeLine = function () {
    if(videoExtPage.keywordList != null && videoExtPage.keywordList.length>0){
        var kwlist = videoExtPage.keywordList.filter(function (kw) {
            return kw.keywordId!=0;
        });
        if(kwlist!=null && kwlist.length>0){
            $(settings.datesDiv+' a').click(function(event){
                event.preventDefault();
                // first vars
                var howManyDates = $(settings.datesDiv+' li').length;
                var widthDate = $(settings.datesDiv+' li').width();
                var widthContainer = $(settings.containerDiv).width();
                var heightContainer = $(settings.containerDiv).height();
                var heightDate = $(settings.datesDiv+' li').height();
                var currentIndex = $(this).parent().prevAll().length;
                // set positions!
                if(settings.orientation == 'horizontal') {
                    $(settings.datesDiv).width(widthDate*howManyDates).css('marginLeft',widthContainer/2-widthDate/2);
                    var defaultPositionDates = parseInt($(settings.datesDiv).css('marginLeft').substring(0,$(settings.datesDiv).css('marginLeft').indexOf('px')));
                } else if(settings.orientation == 'vertical') {
                    $(settings.datesDiv).height(heightDate*howManyDates).css('marginTop',heightContainer/2-heightDate/2);
                    var defaultPositionDates = parseInt($(settings.datesDiv).css('marginTop').substring(0,$(settings.datesDiv).css('marginTop').indexOf('px')));
                }
                // now moving the dates
                $(settings.datesDiv+' a').removeClass(settings.datesSelectedClass);
                $(this).addClass(settings.datesSelectedClass);
                if(settings.orientation == 'horizontal') {
                    $(settings.datesDiv).animate({'marginLeft':defaultPositionDates-(widthDate*currentIndex)},{queue:false, duration:'settings.datesSpeed'});
                } else if(settings.orientation == 'vertical') {
                    $(settings.datesDiv).animate({'marginTop':defaultPositionDates-(heightDate*currentIndex)},{queue:false, duration:'settings.datesSpeed'});
                }
                // var whichIssue = $(this).text();
                var timeLong = $(this).attr("timeOption");
                setVideoPlayTime(timeLong);
            });
        } else {
            $("#timeline").hide();
        }
    }
}
$(function () {
    // initMapData();
    initVideoText();
    initVideoKeyList();
    initKeywordSelect();
    // initVideoKeyword();
    initVideoExtEvent();
    initVideoTimeLine();
});