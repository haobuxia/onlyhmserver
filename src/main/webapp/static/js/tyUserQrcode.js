var genQrCode = function () {
    var time = tyUserQrcodePage.time;
    var userId = tyUserQrcodePage.userId;
    var token = tyUserQrcodePage.token;
    var url = window.location.protocol + "//" + window.location.host + "/serviceqr/code/" + token + "?time="+time+"&userId="+userId;
    $("#qrcode_id").val(token);
    $("#qrCodeImage").addClass("hide");
    $('#qrCode canvas').remove();
    $('#qrCode').qrcode(url);
    var canvas = $('#qrCode canvas');
    var dataUrl = canvas[0].toDataURL();
    $("#qrCodeImage").attr("src", dataUrl).removeClass("hide");
    canvas.remove();
}

var manualInit = function () {
    var id = $("#qrcode_id").val();
    var imei = $.trim($("#qrcode_imei").val());
    if (imei == "") {
        showAlert('请输入imei');
        return;
    }
    ajaxHeaderPost("/serviceqr/code/" + id, {"imei": imei}, {"imei": imei}, function (resp) {
        //{"code":"200","msg":"头盔绑定天远用户成功","data":{"username":"gaoxuzhao"}}
        if (resp.code == "200") {
            var data = resp.data;
            if (data != null && data.username != null) {
                showAlert("操作成功.设置到头盔:" + imei + "的账号是:" + data.username);
            } else {
                showAlert("解除账号头盔绑定成功");
            }
            setTimeout(function () {
                loadMainContent("/personal/qrcode");
            }, 1000);
        } else {
            showAlert(resp.msg);
        }
    });
}

$(function () {
    genQrCode();
});