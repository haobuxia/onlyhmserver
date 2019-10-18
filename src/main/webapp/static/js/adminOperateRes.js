listContentPage.userIconBtnHtml = '<div class="cover_control">'+
                                        ' <span class="fa fa-bars"></span>'+
                                        ' <div>'+
                                        '   <a class="fa fa-trash"></a>'+
                                        '   <a class="fa fa-bookmark"></a>'+
                                        '   <a class="fa fa-truck"></a>'+
                                        '  </div>'+
                                        '</div>';

listContentPage.tagHtml = '<span class="badge" data-id="{0}">{1}</span>';

var enableAdminOperate = function (type) {
    $.each($("div.helmet-"+type),function(idx,div){
        $(div).append(listContentPage.userIconBtnHtml);
    });
    $.each($("div.helmet-"+type+" a.fa-trash"),function(idx,a){
        $(a).off('click').click(function () {
            var dataId = $(a).parents("div.helmet-"+type).attr("data-id");
            confirmDel(type,dataId);
            return false;
        });
    });
    $.each($("div.helmet-"+type+" a.fa-bookmark"),function(idx,a){
        $(a).off('click').click(function () {
            // $("div.btnDiv-"+type+" a.tagBtn").not($(a)).closeFAB();//关闭其他按钮的弹出浮动
            var dataId = $(a).parents("div.helmet-"+type).attr("data-id");
            setTag(type,dataId);
            return false;
        });
    });
    $.each($("div.helmet-"+type+" a.fa-truck"),function(idx,a){//机号
        $(a).off('click').click(function () {
            // $("div.btnDiv-"+type+" a.tagBtn").not($(a)).closeFAB();//关闭其他按钮的弹出浮动
            var btnDiv = $(a).parents("div.helmet-"+type);
            var dataId = btnDiv.attr("data-id");
            var mcCode = btnDiv.attr("data-mc");
            setMachineCode(type,dataId,mcCode);
            return false;
        });
    });
}

var confirmDel = function (type,id) {
    if(confirm("确定删除吗？")){
        $.post("/uploadfile/delete/"+type+"/"+id,{},function (resp) {
            if(resp.success){
                reloadListDataForDel(newPagePagervideo.currentPage);
            }else{
                showAlert(resp.message);
            }
        },"json");
    }
};

var createTagModel = function (callback) {
    if($("#tagModel").length > 0 ){
        doCallback(callback);
        return;
    }

    var html =
        '<div id="tagModel" class="modal">'+
            '<div class="panel panel-body center-block" style="width: 80%;">'+
                '<h4 id="tagModelTitle">设置标签</h4>'+
                '<div class="panel-body"><div id="tagAreas"></div></div>'+            
            '   <div class="modal-footer">'+
            '       <a href="javascript:saveTag()" class="btn btn-success">确定</a>'+
            '        </div>'+
            '   </div>'+
            '</div>'+
     '</div>';
    $("main").append(html);
    initAllTags(callback);
}

var createMachineCodeModel = function (callback) {
    if($("#mcModel").length > 0 ){
        doCallback(callback);
        return;
    }

    var html =
        '<div id="mcModel" class="modal">'+
        '   <div class="panel panel-body center-block" style="width: 500px;">'+
        '       <h4 id="mcModelTitle">设置机号</h4>'+
        '       <div class="panel-body">' +
        '           <div class="input-field">'+
        '                   <label for="mcedit_machineCode" class="active">机号</label>'+
        '                   <input type="text" id="mcedit_machineCode" placeholder="机号(勿输中文)"  class="form-control" value="">'+
        '           </div>' +
        '       </div>'+
        '       <div class="modal-footer">'+
        '          <a href="javascript:saveMachineCode()" class="btn btn-success">确定</a>'+
        '       </div>'+        
        '   </div>'+
        
        '</div>';
    $("main").append(html);
    doCallback(callback);
}

var saveTag = function () {
    var choosedTag = [];
    $.each($("#tagAreas span.teal"),function(idx,span){
        choosedTag.push($(span).attr("data-id"));
    });
    if(choosedTag.length == 0 ){
        $('#tagModel').modal('hide');
        return;
    }
    var type = $('#tagModel').attr("data-restype");
    var resId = $('#tagModel').attr("data-resid");
    $.post("/tagres/save/"+type+"/"+resId,{tagIds:choosedTag.join(",")},function(resp){
        if(resp.success){
            showAlert("设置标签成功");
            $('#tagModel').modal('hide');
            // $('.fixed-action-btn').closeFAB();
        }else{
            showAlert(resp.message);
        }
    },"json");
}

var saveMachineCode = function () {
    var machineCode = $.trim($("#mcedit_machineCode").val());
    if(encodeURI(machineCode).indexOf( "%u" ) >= 0 || (/ /g).test(machineCode)){
        showALert('机号不能包含中文和空格');
        return ;
    }

    var resId = $('#mcModel').attr("data-resid");
    if(confirm("确定设置资源"+resId+"对应的机号为"+machineCode+"吗?")){
        var type = $('#mcModel').attr("data-restype");
        $.post("/media/"+type+"/machinecode/set/"+resId,{"machineCode":machineCode},function (resp) {
            if(resp.success){
                showAlert("设置成功");
                var btnDiv = $("div.btnDiv-"+type+"[data-id='"+resId+"']");
                btnDiv.attr("data-mc",machineCode);

                $('#mcModel').modal('hide');
                // $('.fixed-action-btn').closeFAB();
            }else{
                showAlert(resp.message);
            }
        });
    }
}

//载入所有可用标签
var initAllTags = function (callback) {
    $.get("/tag/tags",{},function (resp) {
        if(resp.success){
            var tagAreas = $("#tagAreas");
            tagAreas.empty();
            var tagList = resp.data;
            $.each(tagList,function(idx,tag){
                tagAreas.append(listContentPage.tagHtml.format(tag.key,tag.val));
            });
            $("#tagAreas span").css("cursor","pointer").click(function(){
                var span =$(this);
                if(span.hasClass("teal")){
                    span.removeClass("teal");
                }else{
                    span.addClass("teal");
                }
            });
            doCallback(callback);
        }else{
            showAlert('载入标签失败.'+resp.message);
        }
    },"json");
}

var showResTags = function (type,id) {
    $.get("/tagres/load/"+type+"/"+id,{},function (resp) {
        if(resp.success){
            var tagIdList = resp.data;
            $("#tagAreas span").removeClass("teal");//清除掉已有标记
            $('#tagModel').attr("data-restype",type).attr("data-resid",id).modal('show');
            $('#tagModelTitle').text("设置标签["+id+"]");
            $.each(tagIdList,function (idx,tagId) {
                $("#tagAreas span[data-id='"+tagId+"']").addClass("teal");
            });
        }else{
            showAlert('载入已打标签失败.'+resp.message);
        }
    },"json");
}

var showResMachineCode = function (type,id,mcCode) {
    $("#mcedit_machineCode").val(mcCode);//old
    $('#mcModel').attr("data-restype",type).attr("data-resid",id).modal('show');
    $('#mcModelTitle').text("设置机号["+id+"]");
}

var setTag = function (type,id) {
    createTagModel(function () {
        showResTags(type,id);
    });
}

var setMachineCode = function (type,id,mcCode) {
    createMachineCodeModel(function () {
        showResMachineCode(type,id,mcCode);
    });
}

$(function () {
    enableAdminOperate(listContentPage.type);
});