/**
 * 视频的动作指定
 */

//动作配置
videoPage.actionNameCodeMap = {
    "自动降速": "autoSpeedDown",
    "动臂上升": "swingArmRise",
    "动臂下降": "swingArmFall",
    "斗杆挖掘": "dipperDigger",
    "斗杆挖掘2": "dipperDigger2",
    "斗杆卸载": "dipperUnload",
    "铲斗挖掘": "bucketDigger",
    "铲斗卸载": "bucketUnload"
};

//设置当前视频当前的动作
var setVideoAction = function () {
    // $('.tap-target').tapTarget('close');
    videoPage.video.pause();//设置时视频暂停播放
    showActions();
    $("#videoActionModel").modal('show');
}

var saveActions = function () {
    console.debug('保存设置的动作');
    var setComplete = true;
    $.each(videoPage.actionNameCodeMap, function (actionName, actionCode) {
        var actionSeconds = videoPage.actionData[actionCode];
        if (actionSeconds == null || actionSeconds == -1) {
            setComplete = false;
            showAlert("还有动作还未设置." + actionName);
            return false;
        }
    });
    if (setComplete) {
        videoPage.actionData.videoTime = null;
        ajaxPost("/videoaction/save/" + videoPage.videoId, videoPage.actionData, function (resp) {
            if (resp.success) {
                showAlert("保存成功");
                videoPage.actionIsCompleted = true;
                videoPage.actionData = resp.data;
            } else {
                showAlert(resp.message);
            }
        })
    }
}

var loadActions = function () {
    ajaxGet("/videoaction/get/" + videoPage.videoId, function (resp) {
        if (resp.success) {
            videoPage.actionData = resp.data;
            if (videoPage.actionData == null) {
                //初始化1个数据
                videoPage.actionData = {};
                videoPage.actionData.videoId = videoPage.videoId;
                $.each(videoPage.actionNameCodeMap, function (name, code) {
                    videoPage.actionData[code] = -1;
                })
            }
            showActions();
        } else {
            showAlert(resp.message);
        }
    })
}

//显示当前视频还未设置的动作
var showActions = function () {
    if (videoPage.actionData == null) {
        loadActions();
        return;
    }

    $("#setedActionAreas").empty();
    $("#notSetedActionAreas").empty();

    var isComplete = true;
    $.each(videoPage.actionNameCodeMap, function (actionName, actionCode) {
        var actionSeconds = videoPage.actionData[actionCode];
        if (actionSeconds == null || actionSeconds == -1) {
            isComplete = false;
            addNotSettedBtn(actionName, actionCode, actionSeconds);
        } else {
            addSettedBtn(actionName, actionCode, actionSeconds);
        }
    });

    if (isComplete) {
        //都设置了
        videoPage.actionIsCompleted = true;
    }
}

videoPage.settedActionTemplate = '<div data-code="{0}" style="display:inline-block;cursor:pointer;margin:5px;" data-name="{1}" data-seconds="{2}"></div>';
videoPage.settedActionNameTemplate = '<span class="label label-primary" data-name="{0}">{0}</span>';
videoPage.settedSecondsTemplate = '<span class="badge" style="cursor:pointer;margin:0;min-width:1rem;" title="跳至视频">{0}</span>';

var addSettedBtn = function (actionName, actionCode, actionSecond) {
    var btn = $(videoPage.settedActionTemplate.format(actionCode, actionName, actionSecond));
    var nameBtn = $(videoPage.settedActionNameTemplate.format(actionName));
    var secondsBtn = $(videoPage.settedSecondsTemplate.format(actionSecond));
    nameBtn.click(settedBtnClicked);
    secondsBtn.click(function () {
        console.debug('视频调到对应的秒后暂停住,actionSecond=' + actionSecond);
        videoPage.video.currentTime = actionSecond;
        videoPage.video.pause();//设置视频暂停播放
    });
    btn.append(nameBtn);
    btn.append(secondsBtn);
    $("#setedActionAreas").append(btn);
}

videoPage.notSettedActionTemplate = '<div data-code="{0}" style="display:inline-block;cursor:pointer;margin:5px;" data-name="{1}" data-seconds="{2}">{3}</div>';
var addNotSettedBtn = function (actionName, actionCode, actionSecond) {
    var btn = $(videoPage.notSettedActionTemplate.format(actionCode, actionName, actionSecond, actionName));
    btn.click(notSettedBtnClicked);
    $("#notSetedActionAreas").append(btn);
}

var notSettedBtnClicked = function () {
    var btn = $(this);
    var actionName = btn.attr("data-name");
    var actionCode = videoPage.actionNameCodeMap[actionName];
    var seconds = Math.round(videoPage.video.currentTime);//四舍五入取整
    videoPage.actionData[actionCode] = seconds;//清空设置的时间
    btn.remove();

    addSettedBtn(actionName, actionCode, seconds);
}

var settedBtnClicked = function () {
    var btn = $(this);
    var actionName = btn.attr("data-name");
    if (confirm("确定删除已设置的动作[" + actionName + "]吗？")) {
        var actionCode = videoPage.actionNameCodeMap[actionName];
        videoPage.actionData[actionCode] = -1;//清空设置的时间
        btn.parent().remove();

        videoPage.actionIsCompleted = false;
        addNotSettedBtn(actionName, actionCode, -1);
    }
}

//获得已经加入到对比中的视频信息
var checkCompareVideo = function () {
    ajaxGet("/videoaction/comparelist", function (resp) {
        if (resp.success) {
            var videoList = resp.data;
            videoPage.compareVideoList = videoList;
            $("#compareTableBody").empty();
            $.each(videoPage.compareVideoList, function (idx, v) {
                appendCompareVideo(v);
            });
        } else {
            console.error("获取对比视频列表异常");
        }
    });
}

var appendCompareVideo = function (video) {
    var tr = $("<tr data-id='" + video.id + "'><td>" + video.id + "</td><td>" + video.neUserName + "</td><td>" + video.createTimeString + "</td></tr>");
    var lastTd = $("<td></td>");
    tr.append(lastTd);
    var delBtn = $('<i class="fa fa-close" title="移除" style="cursor:pointer" ></i>');
    lastTd.append(delBtn);
    delBtn.click(function () {
        removeCompareVideo(video.id);
    });
    $("#compareTableBody").append(tr);
    showHideCompareDataBtn(true);
}

var showHideCompareDataBtn = function (show) {
    if(show) $("#compareDataBtn").removeClass("hide");
    else $("#compareDataBtn").addClass("hide");
}

var showCompareData = function (show) {
    if(show) $("#videoCompareContent").addClass("open");
    else $("#videoCompareContent").removeClass("open");
}

var compareDataIsShow = function () {
    return $("#videoCompareContent").hasClass("open");
}

var addToCompare = function () {
    if (!videoPage.actionIsCompleted) {
        showAlert("视频动作未设置完成或尚未保存，无法添加到对比中");
        return;
    }

    ajaxPost("/videoaction/addtocompare/" + videoPage.videoId, {}, function (resp) {
        if (resp.success) {
            showAlert('加入成功');
            $("#videoActionModel").modal('hide');
            videoPage.compareVideoList.push(videoPage.videoData);
            appendCompareVideo(videoPage.videoData);
            showCompareData(true);//添加成功后将对比列表展示出来
        } else {
            showAlert("加入失败." + resp.message);
        }
    })
}

var removeCompareVideo = function (videoId) {
    if (confirm("确定移除对比视频[" + videoId + "]吗?")) {
        ajaxPost("/videoaction/deletecompare/" + videoPage.videoId, {}, function (resp) {
            if (resp.success) {
                $("#compareTableBody tr[data-id='" + videoId + "']").remove();
                $.each(videoPage.compareVideoList, function (idx, v) {
                    if (v.id == videoId) {
                        videoPage.compareVideoList.splice(idx, 1);
                        console.debug("移除js缓存中的video完毕.idx=" + idx);
                        return false;
                    }
                });
                if (videoPage.compareVideoList.length == 0) {
                    //没有对比视频了，则隐藏对比按钮
                    showHideCompareDataBtn(false);
                    showCompareData(false);
                }
            } else {
                showAlert('移除失败.' + resp.message);
            }
        })
    }
}

var initVideoActionBtnEvent = function () {
    $("#compareDataBtn").off('click').click(function () {
        var show = compareDataIsShow();
        showCompareData(!show);//切换
    });
    $("#hideCompareContentBtn").off('click').click(function () {
        showCompareData(false);
    });
    $("#viewCompareBtn").off('click').click(function () {
        showAlert("请稍候，载入对比页面中...");
        var url = "/videoaction/compare";
        loadMainContent(url);
    });
    $("#clearCompareBtn").off('click').click(function () {
        ajaxPost("/videoaction/clearcompare",{},function (resp) {
            if(resp.success){
                showAlert("清空成功");
                videoPage.compareVideoList=[];
                $("#compareTableBody").empty();
                showHideCompareDataBtn(false);
                showCompareData(false);
            }else{
                showAlert("清空失败."+resp.message);
            }
        })
    });
}

$(function () {
    checkCompareVideo();
    initVideoActionBtnEvent();
});