
var dismissUser = function (id) {
    showAlert('功能待实现');
}


function searchUser() {
    var keyword =  $.trim($("#keyword").val());
    newPagePager.currentPage = 1;
    listTyUserPage.keyword = keyword;
    reloadListData();
}

function reloadListData(){
    loadData(newPagePager.currentPage);
}

function loadData(page) {
    var url = "/tianyiuser/list/"+page;
    if(listTyUserPage.keyword != ''){
        url += "?keyword="+listTyUserPage.keyword;
    }
    loadMainContent(url);
}

//修改用户信息
var editUser = function(userId){
    ajaxGet("/tianyiuser/get/"+userId,function(resp){
        if(resp.success){
            var user = resp.data;
            $("#useredit_id").val(user.id);
            $("#useredit_name").val(user.name);
            $("#useredit_mobile").val(user.mobile);
            $("#useredit_company").val(user.company);
            $("#useredit_dept").val(user.dept);

            $("#useredit_userSex").val(user.userSex);
            // $("#useredit_userRole").val(user.roleCodes.split(","));
            $("#useredit_userRole").selectpicker('val',user.roleCodes.split(","));
            $('.selectpicker').selectpicker('refresh');
            // $('select').material_select();//选择框设置值以后调用使ui更新

            //以下3项不允许修改
            $("#useredit_username").val(user.username).prop("readonly",true).prop("disabled",true);
            $("#useredit_password").val("").prop("readonly",true).prop("disabled",true);
            $("#useredit_neUsername").val(user.neUsername).prop("readonly",true).prop("disabled",true);

            $("#editUserModalTitle").text("修改田一用户");
            $('#editUserModal').modal('show');
        }else{
            showAlert("载入用户信息失败."+resp.message);
        }
    });
}

var initBtnEvent = function () {
    $("#useredit_userRole").selectpicker('val',null);
    $('.selectpicker').selectpicker('refresh');
    // $('select').material_select();
    $('#search-button').off('click').click(function () {
        searchUser();
        return false;
    });
    $("#add-button").off('click').click(function () {
        //载入添加用户model
        $("#editForm input").val("");
        $("#useredit_username").prop("readonly",false).prop("disabled",false);
        $("#useredit_password").prop("readonly",false).prop("disabled",false);
        $("#useredit_neUsername").prop("readonly",false).prop("disabled",false);

        $("#editUserModalTitle").text("新增田一用户");
        $("#editUserModal").modal("show");
        return false;
    });
    //编辑弹框中点击确定
    $("#useredit-button").off('click').click(function () {
        var id = $("#useredit_id").val();
        var tyUser = {};
        tyUser.id = (id == "" ? 0: id);
        tyUser.username = $.trim($("#useredit_username").val());
        tyUser.password = $.trim($("#useredit_password").val());
        tyUser.name = $.trim($("#useredit_name").val());
        tyUser.mobile = $.trim($("#useredit_mobile").val());
        tyUser.userSex = $("#useredit_userSex").val();
        tyUser.company = $.trim($("#useredit_company").val());
        tyUser.dept = $.trim($("#useredit_dept").val());
        tyUser.roleCodes = $("#useredit_userRole").val().join(",");
        tyUser.neUsername = $.trim($("#useredit_neUsername").val());
        if(tyUser.id == 0 && (tyUser.username == "" || tyUser.password == "")){
            console.debug("新注册用户，需要检查用户名密码==>"+tyUser.username+":"+tyUser.password);
            //新添加用户
            showAlert("用户名密码不能为空");
            return;
        }
        if(tyUser.username.toLocaleLowerCase().indexOf("helmet") == 0){
            showAlert("用户名不能以helmet开头");
            return;
        }
        if(tyUser.mobile != "" && !isPhoneAvailable(tyUser.mobile)){
            showAlert("手机号无效");
            return;
        }

        var postUrl = "/tianyiuser/"+(tyUser.id == 0 ? "add" : "save");
        $.post(postUrl,tyUser,function (resp) {
            if(resp.success){
                $('#editUserModal').modal('hide');
                reloadListData();
                showAlert("保存成功");
            }else{
                showAlert("保存失败:"+resp.message);
            }
        },"json")
    });
}

$(function () {
    initBtnEvent();
});