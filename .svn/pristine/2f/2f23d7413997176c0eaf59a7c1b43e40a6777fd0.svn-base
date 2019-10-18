function searchUser() {
    var keyword =  $.trim($("#keyword").val());
    newPagePager.currentPage = 1;
    listNeteaseUserPage.keyword = keyword;
    reloadListData();
}

function reloadListData(){
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/neteaseuser/list/"+page;
    if(listNeteaseUserPage.keyword != ''){
        url += "?keyword="+listNeteaseUserPage.keyword;
    }
    loadMainContent(url);
}

var viewPwd = function (username,id) {
    $.post("/neteaseuser/viewPassword",{"id":id},function (resp) {
        if(resp.code=="200"){
            showAlert(username+"的密码:"+resp.msg);
        }else{
            showAlert(resp.message);
        }
    },"json")
};

var chgPwdForm = function (username,id) {
    $("#useredit_id").val(id);
    $("#useredit_username").val(username);
    $('#chgPwdModal').modal('show');
}

var initBtnEvent = function () {
    $("#useredit-button").off('click').click(function () {
        var id =  $("#useredit_id").val();
        var username = $("#useredit_username").val();
        var password = $("#useredit_password").val();
        $.post("/neteaseuser/changePassword",{id:id,username:username,password:password},function (resp) {
            if(resp.code == "200"){
                $('#editUserModal').modal('hide');
                reloadListData();
                showAlert("修改密码成功");
            }else{
                showAlert("修改密码失败:"+resp.msg);
            }
        },"json")
    });

    $("#search-button").off('click').click(function () {
        searchUser();
        return false;
    });
}

$(function () {
    initBtnEvent();
});