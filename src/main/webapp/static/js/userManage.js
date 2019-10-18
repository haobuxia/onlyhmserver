/**
 * Created by wenxinyan on 2018/9/25.
 */
var formatToTime = function (yyyyMMddHHmmss) {
    yyyyMMddHHmmss = yyyyMMddHHmmss.replaceAll("-","").replaceAll(":","").replaceAll(" ","");
    var d = new Date();
    d.setYear(yyyyMMddHHmmss.substring(0,4)*1);
    d.setMonth(yyyyMMddHHmmss.substring(4,6)*1-1);
    d.setDate(yyyyMMddHHmmss.substring(6,8)*1);
    d.setHours(yyyyMMddHHmmss.substring(8,10)*1);
    d.setMinutes(yyyyMMddHHmmss.substring(10,12)*1);
    d.setSeconds(yyyyMMddHHmmss.substring(12,14)*1);
    d.setMilliseconds(0);
    return d.getTime();
}

var initCompany = function () {
    var flag = false;
    $.each(menuB,function(idx,mb){
        if(mb.name == "单位管理"){
            flag = true;
        }
    });

    $.get("/company/listnopage",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,cpn){
                organisations[cpn.id] = cpn.companyName;
                if(userOrganisation == 1){
                    $("#organisation").append('<option value="' + cpn.id + '">' + cpn.companyName + '</option>');
                    $("#add_organisation").append('<option value="' + cpn.id + '">' + cpn.companyName + '</option>');
                    $("#edit_organisation").append('<option value="' + cpn.id + '">' + cpn.companyName + '</option>');
                } else {
                    if(userOrganisation == cpn.id){
                        $("#companyDiv").html('');
                        $("#companyDiv").append('<input type="text" id="organisation" value="' + cpn.id + '" style="display: none" /><input type="text" value="' + cpn.companyName + '" class="form-control" readonly />');
                        $("#addCompanyDiv").html('');
                        $("#addCompanyDiv").append('<input type="text" id="add_organisation" value="' + cpn.id + '" style="display: none" /><input type="text" value="' + cpn.companyName + '" class="form-control" readonly />');
                        $("#editCompanyDiv").html('');
                        $("#editCompanyDiv").append('<input type="text" id="edit_organisation" value="' + cpn.id + '" style="display: none" /><input type="text" value="' + cpn.companyName + '" class="form-control" readonly />');
                    }
                }
            });

            searchData(1);
        }
    });
}

var initRole = function () {
    var codes = new Array;

    $.get("/role/listall",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,role){
                roleList[role.id] = role.roleName;

                if(userOrganisation == 1){
                    $("#add_roleCodes").append('<option value="' + role.id + '">' + role.roleName + '</option>');
                    $("#edit_roleCodes").append('<option value="' + role.id + '">' + role.roleName + '</option>');
                } else {
                    if(role.companyId == userOrganisation){
                        $("#add_roleCodes").append('<option value="' + role.id + '">' + role.roleName + '</option>');
                        $("#edit_roleCodes").append('<option value="' + role.id + '">' + role.roleName + '</option>');
                    }
                }

                if(role.roleName == "普通用户"){
                    codes[0] = role.id;
                }
            });

            $("#add_roleCodes").selectpicker('val', codes);
            $("#edit_roleCodes").selectpicker('val', null);
            $('.selectpicker').selectpicker('refresh');
        }
    });
}

var showMore = function () {
    if($("#show-more").css("display")=='block'){
        $("#name").val('');
        $("#time1").val('');
        $("#time2").val('');
    }
    $("#show-more").toggle();
}

function makePage(page, pageCount) {
    newPagePager.currentPage = 1;
    if (page > pageCount) {
        newPagePager.currentPage = pageCount;
    } else {
        newPagePager.currentPage = page;
    }

    $("#new-page-pager").attr("data-page", newPagePager.currentPage);

    if (newPagePager.currentPage <= 1) {
        $("#prePageButton").addClass("disabled").prop("disabled", true);
    } else {
        $("#prePageButton").removeClass("disabled").prop("disabled", false);
    }

    if (newPagePager.currentPage >= pageCount) {
        $("#nextPageButton").addClass("disabled").prop("disabled", true);
    } else {
        $("#nextPageButton").removeClass("disabled").prop("disabled", false);
    }

    $("#toPageButton").off('click').click(function () {
        var toPage = $.trim($("#toPage").val());
        if (isNaN(toPage)) {
            return;
        }
        searchData(toPage);
    });
    $("#prePageButton").off('click').click(function () {
        searchData(newPagePager.currentPage - 1);
    });
    $("#nextPageButton").off('click').click(function () {
        searchData(newPagePager.currentPage + 1);
    });

    $("#pageInfo").text("共 "+newPagePager.total+" 条数据 , 第 "+newPagePager.currentPage+" 页 , 共 "+newPagePager.pageCount+" 页");
    return newPagePager.currentPage;
}

var checkMobile = function (cm) {
    var check = true;

    var a = /^[1][3,4,5,7,8][0-9]{9}$/;
    if(!a.test($(cm).val())){
        $(cm).css("border-color","red");
        $("." + $(cm).attr('id') + "_error").text("输入的号码格式有误").show();
        check = false;
    } else {
        checkFocus(cm);
    }

    return check;
}

var checkNull = function (cn) {
    var check = true;

    if($(cn).val() == ''){
        $(cn).css("border-color","red");
        $("." + $(cn).attr('id') + "_error").text("必填项输入不可为空").show();
        check = false;
    } else {
        checkFocus(cn);
    }

    return check;
}

var checkFocus = function (cf) {
    $(cf).css("border-color","");
    $("." + $(cf).attr('id') + "_error").hide();
}

var addSubmit = function () {
    var user = {};
    user.account = $.trim($("#add_account").val());
    user.password = 123456;
    user.name = $.trim($("#add_name").val());
    user.sex = $.trim($("#add_sex").val());
    if($("#add_birthday").val() == ''){
        var bd = formatToTime("2000-01-01");
    } else {
        var bd = formatToTime($.trim($("#add_birthday").val()));
    }

    user.mobile = $.trim($("#add_mobile").val());
    user.organisation = $.trim($("#add_organisation").val());
    user.department = $.trim($("#add_department").val());
    user.roleCodes = $("#add_roleCodes").val().join(",");
    user.tianyuanAccount = $.trim($("#add_tianyuanAccount").val());

    if(checkNull($("#add_account"))&&checkNull($("#add_name"))&&checkMobile($("#add_mobile"))){
        var postUrl = "/user/add?bd=" + bd;
        $.post(postUrl,user,function(resp){
            if(resp.success){
                $("#addUserModal").modal('hide');
                $("#add_account").val('');
                $("#add_name").val('');
                $("#add_sex").val(0);
                $("#add_birthday").val('');
                $("#add_mobile").val('');
                $("#add_department").val('');
                var codes = new Array;
                codes[0] = 4;
                $("#add_roleCodes").selectpicker('val', codes);
                $('.selectpicker').selectpicker('refresh');

                showAlert("保存成功");
                searchData(1);
            } else {
                showAlert(resp.message);
            }
        },"json");
    }
}

var editOpen = function (id,account,password,name,sex,birthday,mobile,organisation,department,roleCodes,tianyuanAccount) {
    $("#edit_id").val(id);
    $("#edit_account").val(account);
    $("#edit_password").val(password);
    $("#edit_name").val(name);
    $("#edit_sex").val(sex);
    $("#edit_birthday").val(birthday);
    $("#edit_mobile").val(mobile);
    $("#edit_organisation").val(organisation);
    $("#edit_department").val(department);
    $("#edit_tianyuanAccount").val(tianyuanAccount);
    $("#edit_roleCodes").selectpicker('val', roleCodes.split(","));
    $('.selectpicker').selectpicker('refresh');

    $("#editUserModal").modal("show");
}

var editSubmit = function () {
    var user = {};
    user.id = $.trim($("#edit_id").val());
    // user.account = $.trim($("#edit_account").val());
    user.password = $.trim($("#edit_password").val());
    user.name = $.trim($("#edit_name").val());
    user.sex = $.trim($("#edit_sex").val());
    if($("#edit_birthday").val() == ''){
        var bd = formatToTime("2000-01-01");
    } else {
        var bd = formatToTime($.trim($("#edit_birthday").val()));
    }
    user.mobile = $.trim($("#edit_mobile").val());
    user.organisation = $.trim($("#edit_organisation").val());
    user.department = $.trim($("#edit_department").val());
    user.roleCodes = $("#edit_roleCodes").val().join(",");
    user.tianyuanAccount = $.trim($("#edit_tianyuanAccount").val());

    if(checkNull($("#edit_password"))&&checkMobile($("#edit_mobile"))){
        var postUrl = "/user/update?bd=" + bd;
        $.post(postUrl,user,function(resp){
            if(resp.success){
                $("#editUserModal").modal('hide');
                showAlert("保存成功");
                searchData(newPagePager.currentPage);
            }
        },"json");
    }
}

var deleteSubmit = function (id) {
    if(confirm("确定要删除这个用户吗？")){
        $.post("/user/delete",{id : id},function(resp){
            if(resp.success){
                showAlert("删除成功");
                searchData(newPagePager.currentPage);
            }
        },"json");
    }
}

var searchData = function (page) {
    var page = page;
    var organisation = $("#organisation").val();
    var name = $("#name").val();
    var time1 = $("#time1").val();
    var time2 = $("#time2").val();
    var list = $("tbody");

    var url = "/user/list?page=" + page;
    if(organisation != ''){
        url += "&organisation=" + organisation;
    }
    if(name != ''){
        url += "&name=" + name;
    }
    if(time1 != ''){
        url += "&time1=" + formatToTime(time1).toString();
    }
    if(time2 != ''){
        if(time2.replaceAll("-","").replaceAll(":","").replaceAll(" ","").length == 8){
            time2 += "235959";
        }
        url += "&time2=" + formatToTime(time2).toString();
    }

    ajaxPost(url,{},function(resp){
        if(resp.success){
            newPagePager.page = resp.data.page;
            newPagePager.pageCount = resp.data.pageCount;
            newPagePager.total = resp.data.total;

            makePage(newPagePager.page, newPagePager.pageCount);

            list.html('');
            $.each(resp.data.list,function(idx,u){
                var id = u.id;
                var account = u.account;
                var password = u.password;
                var name = u.name;
                var sex = '未知';
                if(u.sex == 1) {
                    sex = '男';
                } else if(u.sex == 2) {
                    sex = '女';
                }
                var birthday = u.birthday.year + '-' + u.birthday.monthValue + '-' + u.birthday.dayOfMonth;
                var mobile = u.mobile;
                var department = u.department;
                var createTime = u.createTime.year + '-' + u.createTime.monthValue + '-' + u.createTime.dayOfMonth + ' ' + u.createTime.hour + ':' + u.createTime.minute + ':' + u.createTime.second;
                var roleCodes = '';
                $.each(u.roleCodes.split(","),function(idx,code){
                    roleCodes += roleList[code] + "&nbsp;&nbsp;&nbsp;&nbsp;";
                });

                var html = '<tr><td>' + (idx + 1 + (12 * (newPagePager.currentPage - 1))) + '</td>'
                    + '<td>' + u.account + '</td>'
                    + '<td>' + u.name + '</td>'
                    + '<td>' + sex + '</td>'
                    + '<td>' + u.birthday.year + '-' + u.birthday.monthValue + '-' + u.birthday.dayOfMonth + '</td>'
                    + '<td>' + u.mobile + '</td>'
                    + '<td>' + u.displayOrg + '</td>'
                    + '<td>' + u.department + '</td>'
                    + '<td>' + u.createTime.year + '-' + u.createTime.monthValue + '-' + u.createTime.dayOfMonth + ' ' + u.createTime.hour + ':' + u.createTime.minute + ':' + u.createTime.second + '</td>'
                    + '<td>' + roleCodes + '</td>'
                    + '<td><a class="label label-info" onclick="editOpen(' + id + ',\'' + account + '\',\'' + password + '\',\'' + name + '\',' + u.sex + ',\'' + birthday + '\',\'' + mobile + '\',' + u.organisation + ',\'' + department + '\',\'' + u.roleCodes + '\',\''+u.tianyuanAccount+'\');">修改</a>'
                    // + '<td><a class="label label-info" onclick="editOpen(' + id + ',\'' + account + '\',\'' + password + '\',\'' + name + '\',' + u.sex + ',\'' + birthday + '\',\'' + mobile + '\',' + u.organisation + ',\'' + department + '\',\'' + u.roleCodes + '\');">修改</a>'
                    + '<a class="label label-danger" onclick="deleteSubmit(' + id + ');">删除</a></td></tr>';
                list.append(html);
            });
        }
    });
}

var initBtnEvent = function(){
    $("#search-button").off('click').click(function () {
        searchData(1);
    });

    $("#more-button").off('click').click(function () {
        showMore();
    });

    $("#add-button").off('click').click(function () {
        $("#addUserModal").modal("show");
    });

    $("#addsubmit-button").off('click').click(function () {
        addSubmit();
    });

    $("#editsubmit-button").off('click').click(function () {
        editSubmit();
    });
}
var initDatePicker = function(){
    $("#add_birthday").datepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
    $("#edit_birthday").datepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
    $("#time1").datepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
    $("#time2").datepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
}
$(function () {
    initBtnEvent();
    initCompany();
    initRole();
    initDatePicker();
});