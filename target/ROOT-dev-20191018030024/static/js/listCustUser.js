function searchUser() {
    var keyword =  $.trim($("#keyword").val());
    newPagePager.currentPage = 1;
    listCustUserPage.keyword = keyword;
    reloadListData();
}

function reloadListData(){
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {
    var url = "/company/list/"+page;
    if(listCustUserPage.keyword != ''){
        url += "?keyword="+listCustUserPage.keyword;
    }
    loadMainContent(url);
}

var chgCustomerForm = function (id) {
    $.post("/customer/info/"+id,{},function (resp) {
        if(resp.success){
            var customer = resp.data;
            $("#chgCustomerModalTitle").text("修改客户信息");
            $("#useredit_id").val(id);
            $("#useredit_name").val(customer.custName);
            $("#useredit_mobile").val(customer.mobile);
            $("#useredit_company").val(customer.company);
            $("#useredit_depart").val(customer.depart);
            $("#useredit_address").val(customer.address);

            $('#chgCustomerModal').modal('show');
        }else{
            showAlert(resp.message);
        }
    },"json")
}

var initBtnEvent = function () {
    $("#useredit-button").off('click').click(function () {
        var customer = {};
        customer.id = $("#useredit_id").val();
        customer.custName = $("#useredit_name").val();
        customer.mobile = $("#useredit_mobile").val();
        customer.company = $("#useredit_company").val();
        customer.depart = $("#useredit_depart").val();
        customer.address = $("#useredit_address").val();
        if(customer.mobile != "" && ! isPhoneAvailable(customer.mobile)){
            showAlert("手机号无效");
            return;
        }
        $.post("/customer/save",customer,function (resp) {
            if(resp.success){
                $('#chgCustomerModal').modal('hide');
                reloadListData();
                // alert("保存成功");
            }else{
                showAlert("保存失败:"+resp.message);
            }
        },"json")
    });

    $("#search-button").off('click').click(function () {
        searchUser();
        return false;
    });

    $("#add-button").off('click').click(function () {
        $("#chgCustomerModalTitle").text("添加新的客户");
        $("#useredit_id").val("0");
        $("#useredit_name").val("");
        $("#useredit_mobile").val("");
        $("#useredit_company").val("");
        $("#useredit_depart").val("");
        $("#useredit_address").val("");

        $('#chgCustomerModal').modal('show');
        return false;
    });
}

$(function () {
    initBtnEvent();
});