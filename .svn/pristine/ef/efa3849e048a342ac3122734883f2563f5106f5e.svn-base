/**
 * Created by wenxinyan on 2018/10/9.
 */
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

var initApi = function () {
    $.get("/api/list",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,a){
                //$("#edit_api").append("<option value='" + a.id + "'>" + a.remark + "(" + a.urlName + ")" + "</option>");
                apiArray[a.id] = a.remark;
            });
        }
    });
}

var initMenu = function () {
    $.get("/menu/list",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,m){
                $("#edit_menu").append("<option value='" + m.id + "'>" + m.menuName + "</option>");
                menuArray[m.id] = m.menuName;
            });
        }
    });
}

var addSubmit = function () {
    var role = {};

    role.roleName = $.trim($("#add_roleName").val());
    role.companyId = $.trim($("#add_organisation").val());
    role.universalId = $.trim($("#add_project").val());
    var postUrl = "/role/add";
    $.post(postUrl,role,function(resp){
        if(resp.success){
            $("#addRoleModal").modal('hide');
            showAlert("保存成功");
            searchData(1);
        } else {
            showAlert(resp.message);
        }
    },"json");
}

var deleteSubmit = function (id) {
    if(confirm("确定要删除这个角色吗？")){
        $.post("/role/delete",{id : id},function(resp){
            if(resp.success){
                showAlert("删除成功");
                searchData(newPagePager.currentPage);
            }
        },"json");
    }
}

var editOpen = function (id,roleName,organisation,project) {
    $("#edit_id").val(id);
    $("#edit_roleName").val(roleName);
    $("#edit_organisation").val(organisation);
    $("#edit_project").val(project);
    //获取用户所有菜单列表
    $.post("/menu/roletree",{userId: userId}, function(resp){
        if(resp.success){
            demo.treeData=resp.data;
        }
    },"json");

    // 获取角色拥有的菜单列表
    $.post("/rolemenu/list",{id:id},function(resp){
        if(resp.success){
            var menuList = new Array();
            $.each(resp.data,function(idx,ml){
                menuList[idx] = ml.menuId;
            });
            $("#edit_menu").selectpicker('val', menuList);
            $('.selectpicker').selectpicker('refresh');

            demo.checkedData=menuList;
        }
    },"json");

    $.post("/roleapi/list",{id:id},function(resp){
        if(resp.success){
            var apiList = new Array();
            $.each(resp.data,function(idx,al){
                apiList[idx] = al.apiId;
            });
            $("#edit_api").selectpicker('val',apiList);
            $('.selectpicker').selectpicker('refresh');
        }
    },"json");

    $("#editModal").modal('show');
}

var editSubmit = function () {
    var id = $("#edit_id").val();
    var roleName = $("#edit_roleName").val();
    var companyId = $.trim($("#edit_organisation").val());
    var universalId = $.trim($("#edit_project").val());
    var url = "/role/update?id=" + id + "&roleName=" + roleName+ "&companyId=" + companyId+ "&universalId=" + universalId;
    // if($("#edit_menu").val() != null){
    //     var menus = $("#edit_menu").val().join(",");
    //     url += "&menus=" + menus;
    // }
    var menus = demo.$refs.tree.getCheckedKeys();
    url += "&menus=" + menus.join(",");
    if($("#edit_api").val() != null){
        var apis = $("#edit_api").val().join(",");
        url += "&apis=" + apis;
    }
    ajaxPost(url,{},function(resp){
        if(resp.success){
            $("#editModal").modal('hide');
            showAlert("保存成功");
            searchData(newPagePager.currentPage);
        }
    },"json");
}

var searchData = function (page) {
    var roleName = $("#roleName").val();
    var companyId = $("#organisation").val();
    var list = $("tbody");

    var url = "/role/list?page=" + page;
    if(roleName != ''){
        url += "&roleName=" + roleName;
    }
    if(companyId != ''){
        url += "&companyId=" + companyId;
    }
    ajaxPost(url,{},function(resp){
        if(resp.success){
            newPagePager.page = resp.data.page;
            newPagePager.pageCount = resp.data.pageCount;
            newPagePager.total = resp.data.total;

            makePage(newPagePager.page, newPagePager.pageCount);

            list.html('');
            $.each(resp.data.list,function(idx,role){
                var api = '';
                var menu1 = '';
                var menu2 ='';
                var menu3 = '';

                $.ajax({
                    type : "post",
                    url : "/rolemenu/menulist",
                    data : {id:role.id,isUserId:false},
                    async : false,
                    dataType : "json",
                    success : function(resp){
                        if(resp.success){
                            $.each(resp.data.menu1,function(idx,ml1){
                                menu1 += ml1.name + "&nbsp;&nbsp;&nbsp;&nbsp;";

                                if(ml1.menuType == 0){
                                    menu2 += ml1.name + " : ";
                                }
                                $.each(resp.data.menu2,function(idx,ml2){
                                    if(ml2.fatherId == ml1.id){
                                        menu2 += ml2.name + "&nbsp;&nbsp;&nbsp;&nbsp;"
                                    }
                                });
                                menu2 += "<br>";
                            });
                            $.each(resp.data.menu3,function(idx,ml3){
                                if(idx == 0){
                                    menu3 += ml3.name;
                                } else {
                                    menu3 += ", " + ml3.name;
                                }
                            });
                        }
                    }
                });

                $.ajax({
                    type : "post",
                    url : "/roleapi/list",
                    data : {id:role.id},
                    async : false,
                    dataType : "json",
                    success : function(resp){
                        if(resp.success){
                            $.each(resp.data,function(idx,al){
                                if(idx == 0){
                                    api += apiArray[al.apiId];
                                } else {
                                    api += ", " + apiArray[al.apiId];
                                }
                            });
                        }
                    }
                });

                var html = '<tr><td>' + (idx + 1 + (12 * (newPagePager.currentPage - 1))) + '</td>'
                    + '<td>' + role.roleName + '</td>'
                    // + '<td>' + api + '</td>'
                    + '<td>' + menu1 + '</td>'
                    + '<td>' + menu2 + '</td>'
                    + '<td>' + menu3 + '</td>'
                    + '<td><a class="label label-info" onclick="editOpen(' + role.id + ',\'' + role.roleName + '\',' + role.companyId + ',' + role.universalId + ');">修改权限</a>'
                    // + '<a class="label label-danger" onclick="deleteSubmit(' + role.id + ');">删除</a>'
                    + '</td></tr>';
                list.append(html);
            });
        }
    },"json");
}

var initBtnEvent = function(){
    $("#search-button").off('click').click(function () {
        searchData(1);
    });

    $("#add-button").off('click').click(function () {
        $("#addRoleModal").modal("show");
    });

    $("#addsubmit-button").off('click').click(function () {
        addSubmit();
    });

    $("#editsubmit-button").off('click').click(function () {
        editSubmit();
    });
}
var initProject = function () {
    $.get("/aihelmet/us/list",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,cpn){
                // $("#organisation").append('<option value="' + cpn.id + '">' + cpn.companyName + '</option>');
                $("#add_project").append('<option value="' + cpn.id + '">' + cpn.project + '</option>');
                $("#edit_project").append('<option value="' + cpn.id + '">' + cpn.project + '</option>');
            });
        }
    });
}
var initCompany = function () {
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
$(function () {
    initBtnEvent();
    initApi();
    initMenu();
    initProject();
    initCompany();
});