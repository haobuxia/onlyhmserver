var addApkUpdatePage={};
addApkUpdatePage.addBtnClick = function () {
    var imei = $("#clientId").val();
    var apkId = $("#apkId").val();
    if(imei == "" || apkId == "" ){
        return;
    }

    if(confirm("确定要设置升级吗？")){
        $.post("/apk/update/set",{imei:imei,apkId:apkId},function (resp) {
            if(resp.success){
                showAlert('设置成功');
                loadMainContent('/apk/update/list')
            }else{
                showAlert(resp.message);
            }
        });
    }
}
addApkUpdatePage.initEvent = function () {
    $("#addUpdateBtn").off('click').click(addApkUpdatePage.addBtnClick);
    $("#cancelApkBtn").click(function () {
        history.back();
    });
    // $('select').material_select();
}
$(function () {
    addApkUpdatePage.initEvent();
});