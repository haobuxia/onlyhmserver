var addApkFilePage={};
addApkFilePage.getUploadParams = {
    version:function(){
        return $.trim($("#version").val());
    },
    fileType:function () {
        return $("#fileType").val();
    },
    description:function(){
        return $.trim($('#description').val());
    }
}

var toApkFileList = function () {
    loadMainContent("/apk/file/list");
}

var initBtnEvent = function () {
    //文件上传组件
    initFileUploader('/apk/file/upload', $('#addApkBtn'),addApkFilePage.getUploadParams,true,false,["apk"],toApkFileList);

    // $('select').material_select();

    $("#cancelApkBtn").click(toApkFileList);
}

$(function () {
    initBtnEvent();
});