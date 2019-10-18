function createHelmetHistoryChartIfNeeded(list) {
    var days = [];
    $.each(list, function (idx, dayData) {
        var serie = {
            loginLongStr: dayData.loginLongStr,
            开机时长: Math.ceil(dayData.loginSeconds/60),
            拍摄时长: Math.ceil(dayData.shootSeconds/60)
        };
        days.push(serie);
    });

    var option = {
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {},
        grid: {
            left: '10%',
            bottom:'25%'
        },
        dataset: {
            dimensions: ['loginLongStr', '开机时长', '拍摄时长'],
            source: days
        },
        xAxis: {
            type: 'category',
            axisLabel: {
                interval:0,
                rotate:40
            }
        },
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [
            {type: 'bar'},
            {type: 'bar'}
        ]
    };

    newPagePager.historyChart = echarts.init($("#videoChart")[0]);
    newPagePager.historyChart.setOption(option, true);

    window.addEventListener('resize', function () {
        window.setTimeout(function () {
            newPagePager.historyChart.resize();
        }, 300);
    }, false);
}
function crtTimeFtt(val) {
    if (val != null) {
        return val.year + '-' + (val.monthValue) + '-' + val.dayOfMonth;
    }
}
var statisticOne = function (userName, userId, department, loginSeconds, shootSeconds, loginNum) {
    $("#videoLabel").val("区域："+department+"  使用人："+userName+"  拍摄时长："+Math.ceil(loginSeconds/60)+"分钟  视频数："+loginNum);

    var url = "/helmetOnlineStats/statisticListByAreaUser";
    ajaxPost(url,
        {
            startTime: newPagePager.startTime,
            endTime: newPagePager.endTime,
            userId: userId
        },
        function (resp) {
            if (resp.success) {
                var list = resp.data;
                var _inner = '';
                var deviceListContent = $("#deviceListContent1");
                for (var i = 0; i < list.length; i++) {
                    var _item = list[i];
                    _inner += "<tr>" +
                        '<td>' + _item.userName + '</td>' +
                        '<td>' + crtTimeFtt(_item.loginDate) + '</td>' +
                        '<td>' + Math.ceil(_item.loginSeconds/60) + '</td>' +
                        '<td>' + (_item.loginNum==null?0:_item.loginNum) + '</td>' +
                        '<td>' + (_item.workNum==null?0:_item.workNum) + '</td>' +
                        '</tr>';

                }
                deviceListContent.html("");
                deviceListContent.append(_inner);
                $('#statisticOneModal').modal('show');
            } else {
                showAlert(resp.message);
            }
        });
}

function reloadListData() {
    loadData(newPagePager.currentPage);
}

var loadData = function (page) {

    var url = "/helmetOnlineStats/statisticListByTag";

    if (newPagePager.startTime != '') {
        if (newPagePager.startTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").length == 8) {
            newPagePager.startTime += ' 00:00:00';
        }
    }
    if (newPagePager.endTime != '') {
        if (newPagePager.endTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").length == 8) {
            newPagePager.endTime += ' 23:59:59';
        }
    }
    ajaxPost(url,
        {
            page: page,
            userName: newPagePager.userName,
            startTime: newPagePager.startTime,
            endTime: newPagePager.endTime,
            department: newPagePager.department,
            organisation: newPagePager.organisation
        },
        function (resp) {
            if (resp.success) {
                var list = resp.data;
                // newPagePager.page = resp.data.currentPage || 1;
                // newPagePager.pageCount = resp.data.pageTotal || 1;
                // newPagePager.total = resp.data.count || 0;
                // newPagePager.pageSize = resp.data.pageSize || 10;
                var _inner = '';
                var deviceListContent = $("#deviceListContent");
                for (var i = 0; i < list.length; i++) {
                    var _item = list[i];
                    _inner += "<tr>" +
                        '<td>' + (i + 1) + '</td>' +
                        '<td>' + _item.department + '</td>' +
                        '<td>' + _item.userName + '</td>';
                        for(var j=0;j<tagsList.length;j++){
                            var itemKey="tag_"+tagsList[j].id;
                            _inner += '<td>' + (_item[itemKey]==null?0:_item[itemKey]) + '</td>';
                        }
                    _inner += '<td>' + (_item.totalNum==null?0:_item.totalNum) + '</td></tr>';

                }
                deviceListContent.html("");
                deviceListContent.append(_inner);


            } else {
                showAlert(resp.message);
            }

            // makePage(newPagePager.page, newPagePager.pageCount);
        });

}

var doSearch = function () {
    var userName = $("#userName").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var department = $("#department").val();
    var organisation = $("#organisation").val();

    newPagePager.userName = userName;
    newPagePager.startTime = startTime;
    newPagePager.endTime = endTime;
    newPagePager.department = department;
    newPagePager.organisation = organisation;
    if(startTime==null || startTime==''){
        showAlert("请输入开始时间");
        return;
    }
    // if(dateMinus(endTime)){
        loadData(1);
    // }
}

var initBtnEvent = function () {
    //搜
    $("#search-button").off('click').click(doSearch);
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
        loadData(toPage, "");
    });
    $("#prePageButton").off('click').click(function () {
        loadData(newPagePager.currentPage - 1, "");
    });
    $("#nextPageButton").off('click').click(function () {
        loadData(newPagePager.currentPage + 1, "");
    });

    $("#pageInfo").text("共 " + newPagePager.total + " 条数据 , 第 " + newPagePager.currentPage + " 页 , 共 " + newPagePager.pageCount + " 页");
    return newPagePager.currentPage;
}
var dateMinus = function(endTime){
    var begindate = $("#startTime").val();
    if(begindate==null){
        // showAlert("请输入开始时间");
        return false;
    }
    begindate = new Date(Date.parse(begindate.replace(/-/g, "/"))); //将开始时间由字符串格式转换为日期格式
    endTime = new Date(Date.parse(endTime.replace(/-/g, "/"))); //将开始时间由字符串格式转换为日期格式
    var startDate = begindate.getTime(); //将开始日期转换成毫秒
    var endDate = endTime.getTime(); //将结束日期转换成毫秒
    var day = parseInt((endDate-startDate)/1000/3600/24); //结束日期减去开始日期后转换成天数
    if(day > 30){
        showAlert("开始时间和结束时间不能间隔一个月以上。");
        return false;
    }
    return true;
}
var initDatePicker = function(){
    $("#startTime").datepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
    $("#endTime").datepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
}
var initCompany = function () {
    $.get("/company/listnopage",function(resp){
        if(resp.success){
            $.each(resp.data,function(idx,cpn){
                if(userOrganisation == 1){
                    $("#organisation").append('<option value="' + cpn.id + '">' + cpn.companyName + '</option>');
                } else {
                    if(userOrganisation == cpn.id){
                        $("#companyDiv").html('');
                        $("#companyDiv").append('<input type="text" id="organisation" value="' + cpn.id + '" style="display: none" /><input type="text" value="' + cpn.companyName + '" class="form-control" readonly />');
                    }
                }
            });
        }
    });
}
$(function () {
    initBtnEvent();
    // doSearch();
    initDatePicker();
    initCompany();
});