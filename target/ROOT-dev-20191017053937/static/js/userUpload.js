//文件上传
var uploaderJsInit = false ;
var createFileUploader = function (uploadAction,submitButton,showMsg,reloadData,params,extensions,successCallback,fileInputSelector) {
    if(fileInputSelector == null) fileInputSelector = "input[type='file']";
    console.debug('创建文件上传控件.'+fileInputSelector);
    $(fileInputSelector).ajaxfileupload({
        action: uploadAction,
        valid_extensions : extensions,
        validate_extensions:extensions != null && extensions.length > 0,
        params: params,
        submit_button:submitButton,//配置了此项则在此按钮的click事件触发上传动作
        onComplete: function(response) {
            submitButton.text("确定").prop("disabled",false);
            if(showMsg){
                if(response.success){
                    showAlert("上传成功");
                    if(successCallback){
                        doCallback(successCallback,[response.message]);
                    }
                }else{
                    showAlert(response.message);//显示上传结果
                }
            }
            if(reloadData){
                loadData(1);
            }
        },
        onStart: function() {
            submitButton.text("上传中").prop("disabled",true);
            return true;
        },
        onCancel: function() {
            showAlert('请先选择上传文件');
        }
    });
}

/**
 * 初始化文件上传组件
 * @param uploadAction 上传处理后台url
 * @param showMsg 是否显示上传结果,默认是
 * @param reloadData 是否重新载入列表数据，默认是并调用loadData(1)
 */
var initFileUploader= function (uploadAction,submitBtn,params,showMsg,reloadData,extensions,successCallback,fileInputSelector) {
    if(!uploaderJsInit){
        $.getScript("/static/jquery/jquery.ajaxfileupload.js",function () {
            uploaderJsInit = true;
            createFileUploader(uploadAction,submitBtn,showMsg,reloadData,params||{},extensions||[],successCallback,fileInputSelector);
        });
    }else{
        createFileUploader(uploadAction,submitBtn,showMsg,reloadData,params||{},extensions||[],successCallback,fileInputSelector);
    }
}