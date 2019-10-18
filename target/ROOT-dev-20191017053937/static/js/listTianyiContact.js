var initBtnEvent = function () {
    $("#add-button").off('click').click(function () {
        //载入添加联系人model
        $("#editForm input").val("");
        $("#editContactModal").modal("show");
        return false;
    });
    //编辑弹框中点击确定
    $("#editContact-button").off('click').click(function () {
        var userRealName = $("#editContact_userRealName").val();
        if(userRealName == ""){
            showAlert("请输入要添加为联系人的用户");
            return;
        }
        var remark = $("#editContact_remark").val();

        $.post("/tianyicontact/add", {userRealName: userRealName, remark: remark}, function (resp) {
            if (resp.success) {
                $("#editContact_contactId").val("");
                $("#editContact_remark").val("");
                $('#editContactModal').modal('hide');
                showAlert("保存成功");
                setTimeout(function () {
                    loadMainContent("/tianyicontact/list");
                },300);
            } else {
                showAlert("保存失败:请输入正确的联系人姓名" );
            }
        }, "json")
    });
}

var removeContact = function (contactId) {
    if (confirm("确定移除这个联系人吗？")) {
        ajaxPost("/tianyicontact/remove", {contactId: contactId}, function (resp) {
            if (resp.success) {
                $("tr[data-contactId=" + contactId + "]").remove();
                showAlert("移除成功");
            } else {
                showAlert("移除失败");
            }
        })
    }
}

$(function () {
    initBtnEvent();
});