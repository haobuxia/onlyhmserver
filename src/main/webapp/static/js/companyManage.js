var initProvinceList = function () {
    $.get("/dictionary/province",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,p){
                provinceList[p.id] = p.provincialName;
            });
        }
    });
}

var initCityList = function () {
    $.get("/dictionary/allcity",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,c){
                cityList[c.id] = c.cityName;
            });
        }
    });
}

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

var showMore = function () {
    if($("#show-more").css("display")=='block'){
        $("#contactNumber").val('');
        $("#province").val('');
        $("#city").val('');
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

var initNature = function () {
    $.get("/dictionary/companynature",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,cn){
                $("#add_companyNature").append('<option value="' + cn.id + '">' + cn.natureName + '</option>');
                $("#edit_companyNature").append('<option value="' + cn.id + '">' + cn.natureName + '</option>');
            });
        }
    });
}

var initProvince = function () {
    $.get("/dictionary/province",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,p){
                $("#province").append('<option value="' + p.id + '">' + p.provincialName + '</option>');
                $("#add_province").append('<option value="' + p.id + '">' + p.provincialName + '</option>');
                $("#edit_province").append('<option value="' + p.id + '">' + p.provincialName + '</option>');
            });
        }
    });
}

var changeCity = function () {
    var id = $("#province").val();
    if(id == ''){
        $("#city").html('<option value="">全部</option>');
    } else {
        $.post("/dictionary/city?id="+id,function(resp){
            if(resp.success){
                $("#city").html('<option value="">全部</option>');
                $.each(resp.data,function(idx,c){
                    $("#city").append('<option value="' + c.id + '">' + c.cityName + '</option>');
                })
            }
        });
    }
}

var changeAddCity = function () {
    var id = $("#add_province").val();
    $.post("/dictionary/city?id="+id,function(resp){
        if(resp.success){
            $("#add_city").html('');
            $.each(resp.data,function(idx,c){
                $("#add_city").append('<option value="' + c.id + '">' + c.cityName + '</option>');
            })
        }
    });
}

var changeEditCity = function () {
    var id = $("#edit_province").val();
    $.post("/dictionary/city?id="+id,function(resp){
        if(resp.success){
            $("#edit_city").html('');
            $.each(resp.data,function(idx,c){
                $("#edit_city").append('<option value="' + c.id + '">' + c.cityName + '</option>');
            })
        }
    });
}

var changeEditCity1 = function (city) {
    var id = $("#edit_province").val();
    $.post("/dictionary/city?id="+id,function(resp){
        if(resp.success){
            $("#edit_city").html('');
            $.each(resp.data,function(idx,c){
                $("#edit_city").append('<option value="' + c.id + '">' + c.cityName + '</option>');
            })
            $("#edit_city").val(city);
        }
    });
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
    var birthday = formatToTime("2000-01-01");
    if($("#add_birthday").val() != ''){
        birthday = formatToTime($.trim($("#add_birthday").val()));
    }

    var data = {
        companyName : $.trim($("#add_companyName").val()),
        address : $.trim($("#add_address").val()),
        companyNature : $.trim($("#add_companyNature").val()),
        province : $.trim($("#add_province").val()),
        city : $.trim($("#add_city").val()),
        contact : $.trim($("#add_contact").val()),
        contactNumber : $.trim($("#add_contactNumber").val()),
        account : $.trim($("#add_account").val()),
        password : "123456",
        name : $.trim($("#add_name").val()),
        sex : $.trim($("#add_sex").val()),
        birthday : birthday,
        mobile : $.trim($("#add_mobile").val()),
        department : $.trim($("#add_department").val())
    };

    if(checkNull($("#add_companyName"))&&checkNull($("#add_address"))&&checkNull($("#add_contact"))&&checkMobile($("#add_contactNumber"))&&checkNull($("#add_account"))&&checkNull($("#add_name"))&&checkMobile($("#add_mobile"))){
        var postUrl = "/company/add";
        $.post(postUrl,{data:JSON.stringify(data)},function(resp){
            if(resp.success){
                $("#addCompanyModal").modal('hide');
                $("#add_companyName").val('');
                $("#add_address").val('');
                $("#add_contact").val('');
                $("#add_contactNumber").val('');
                $("#add_account").val('');
                $("#add_name").val('');
                $("#add_sex").val(0);
                $("#add_birthday").val('');
                $("#add_mobile").val('');
                $("#add_department").val('');

                showAlert("保存成功");
                searchData(1);
            } else {
                showAlert(resp.message);
            }
        },"json");
    }
}

var editOpen = function (id,companyName,address,companyNature,province,city,contact,contactNumber) {
    $("#edit_id").val(id);
    $("#edit_companyName").val(companyName);
    $("#edit_address").val(address);
    $("#edit_companyNature").val(companyNature);
    $("#edit_province").val(province);
    changeEditCity1(city);
    $("#edit_contact").val(contact);
    $("#edit_contactNumber").val(contactNumber);

    $("#editCompanyModal").modal("show");
}

var editSubmit = function () {
    var company = {};
    company.id = $.trim($("#edit_id").val());
    company.companyName = $.trim($("#edit_companyName").val());
    company.address = $.trim($("#edit_address").val());
    company.companyNature = $.trim($("#edit_companyNature").val());
    company.province = $.trim($("#edit_province").val());
    company.city = $.trim($("#edit_city").val());
    company.contact = $.trim($("#edit_contact").val());
    company.contactNumber = $.trim($("#edit_contactNumber").val());

    if(checkNull($("#edit_address"))&&checkNull($("#edit_contact"))&&checkMobile($("#edit_contactNumber"))){
        var postUrl = "/company/update";
        $.post(postUrl,company,function(resp){
            if(resp.success){
                $("#editCompanyModal").modal('hide');
                showAlert("保存成功");
                searchData(newPagePager.currentPage);
            }
        },"json");
    }
}

var deleteSubmit = function (id) {
    if(confirm("确定要删除这个单位吗？")){
        $.post("/company/delete",{id : id},function(resp){
            if(resp.success){
                showAlert("删除成功");
                searchData(newPagePager.currentPage);
            }
        },"json");
    }
}

var searchData = function (page) {
    var companyName = $("#companyName").val();
    var contact = $("#contact").val();
    var contactNumber = $("#contactNumber").val();
    var province = $("#province").val();
    var city = $("#city").val();
    var list = $("tbody");

    var url = "/company/list?page=" + page;
    if(companyName != ''){
        url += "&companyName=" + companyName;
    }
    if(contact != ''){
        url += "&contact=" + contact;
    }
    if(contactNumber != ''){
        url += "&contactNumber=" + contactNumber;
    }
    if(province != ''){
        url += "&province=" + province;
    }
    if(city != ''){
        url += "&city=" + city;
    }

    ajaxPost(url,{},function(resp){
        if(resp.success){
            newPagePager.page = resp.data.page;
            newPagePager.pageCount = resp.data.pageCount;
            newPagePager.total = resp.data.total;

            makePage(newPagePager.page, newPagePager.pageCount);

            list.html('');
            $.each(resp.data.list,function(idx,company){
                var id = company.id;

                var html = '<tr><td>' + (idx + 1 + (12 * (newPagePager.currentPage - 1))) + '</td>'
                    + '<td>' + company.companyName + '</td>'
                    + '<td>' + company.address + '</td>'
                    + '<td>' + company.displayNature + '</td>'
                    + '<td>' + company.displayProvince + '</td>'
                    + '<td>' + company.displayCity + '</td>'
                    + '<td>' + company.contact + '</td>'
                    + '<td>' + company.contactNumber + '</td>'
                    +'<td><a class="label label-info" onclick="editOpen(' + id + ',\'' + company.companyName + '\',\'' + company.address + '\',' + company.companyNature + ',' + company.province + ',' + company.city + ',\'' + company.contact + '\',\'' + company.contactNumber + '\')">修改</a>'
                    +'<a class="label label-danger" onclick="deleteSubmit(' + id + ');">删除</a></td></tr>';
                list.append(html);
            });
        }
    },"json");
}

var initBtnEvent = function(){
    $("#search-button").off('click').click(function () {
        searchData(1);
    });

    $("#more-button").off('click').click(function () {
        showMore();
    });

    $("#add-button").off('click').click(function () {
        $("#addCompanyModal").modal("show");
        changeAddCity();
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
}

$(function () {
    initBtnEvent();
    initProvince();
    initNature();
    initProvinceList();
    initCityList();
    initDatePicker();
    searchData(1);
});