
addFilePage.getUploadParams = {
    termCode:function () {
        return $("#termCode").val();
    },
    createTime:function () {
        return $("#createTime").val();
    },
    description:function () {
        return $("#description").val();
    },
    machineCode:function () {
        return $("#machineCode").val();
    },
    tag:function () {
        return $("#tag").val();
    }
};
addFilePage.getUploadSegmentParams = {
    fileId:function () {
        return $("#bigFile2_fileId").val();
    },
    segmentCount:function () {
        return $("#bigFile2_segmentCount").val();
    }
};
var toFileList = function () {
    loadMainContent("/list/"+addFilePage.type+"/searchlist");
}

var segmentUploadSuccess = function (msg) {
    showAlert('分片上传成功:'+msg);
}

var initCreateTime = function () {
    var createTime = $("#createTime");
    if(createTime.length > 0 ){
        var fmt = createTime.attr("placeholder");
        createTime.val(new Date().Format(fmt));
    }
    createTime = $("#bigFile_createTime");
    if(createTime.length > 0 ){
        var fmt = createTime.attr("placeholder");
        createTime.val(new Date().Format(fmt));
    }
}

var initBtnEvent = function () {
    initCreateTime();

    //文件上传组件
    var supportTypeArr = [];
    if(addFilePage.supportTypes.indexOf("[")==0){
        var arr = addFilePage.supportTypes.substring(1,addFilePage.supportTypes.length-1).split(",");
        for(i =0 ;i<arr.lengthl;i++){
            supportTypeArr.push($.trim(arr[i]));
        }
        console.debug('supportTypeArr='+supportTypeArr);
    }

    initFileUploader("/"+addFilePage.type+'/upload', $('#addFileBtn'),addFilePage.getUploadParams,true,false,supportTypeArr,toFileList,'#uploadFile');

    initFileUploader("/file/upload/bigfile/segment", $('#bigFile2_addFileBtn'),addFilePage.getUploadSegmentParams,true,false,[],segmentUploadSuccess,'#uploadSegment');

    $("#bigFile_initBtn").click(function () {
        $.post("/file/upload/bigfile/init",{
            termCode:$("#bigFile_termCode").val(),
            createTime:$("#bigFile_createTime").val(),
            fileName:$("#bigFile_fileName").val(),
            fileId:$("#bigFile_fileId").val(),
            segmentCount:$("#bigFile_segmentCount").val(),
            description:$("#bigFile_description").val(),
            machineCode:$("#bigFile_machineCode").val(),
            tag:$("#bigFile_tag").val(),
        },function (resp) {
            if(resp.success){
                showAlert('初始化成功,请上传分片');
            }else{
                showAlert(resp.message);
            }
        },"json");
    });
}

$(function () {
    Materialize.updateTextFields();
    initBtnEvent();
});