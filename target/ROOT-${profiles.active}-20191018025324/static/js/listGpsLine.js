var hexToDec = function(hex){
    return parseInt(hex,16);
}

var transcodeType = function (typeHex) {
    var id = hexToDec(typeHex);
    return id+":"+listGpsLinePage.typeMap["t_"+id].key;
}

var transcodeValue = function (typeHex,intVal) {
    var id = hexToDec(typeHex);
    var ratio = listGpsLinePage.typeMap["t_"+id].val;
    var ratioVal = ratio * intVal ;
    if((id == 10 || id == 11) && ratioVal > 1200) ratioVal = 1200;//最大不能超过1200
    return ratioVal.toFixed(3);//没有四舍五入
}

var showDataParts = function (dataParts,lineNo,baseTime) {
    $('#dataPartsTitle').text("原始数据第"+lineNo+"行,时间："+baseTime);
    var tbody = $("#viewDataPartsModal").find("tbody");
    tbody.empty();
    var dataArray = dataParts.split("]");
    $.each(dataArray,function(idx,data){
        //1-[0001, 0000, 0002, 010B
        if(data == "") return true;
        var partArr = data.split("-[");
        var dataArr = partArr[1].split(",");
        var typeId = hexToDec(dataArr[0]);
        var tr = $("<tr></tr>");
        var td0 = $("<td>"+partArr[0]+"</td>");//序号
        var td11 = $("<td class='red lighten-5'>"+dataArr[0]+"</td>");//类型
        var td12 = $("<td class='red lighten-5'>"+transcodeType(dataArr[0])+"</td>");//类型
        var td21 = $("<td class='deep-purple lighten-5'>"+dataArr[1]+"</td>");//时间
        var td22 = $("<td class='deep-purple lighten-5'>"+hexToDec(dataArr[1])+"</td>");//时间
        var td31 = $("<td class='light-blue lighten-5'>"+dataArr[2]+"</td>");//长度
        var td32 = $("<td class='light-blue lighten-5'>"+hexToDec(dataArr[2])+"</td>");//长度
        tr.append(td0);
        tr.append(td11);tr.append(td12);
        tr.append(td21);tr.append(td22);
        tr.append(td31);tr.append(td32);

        if(typeId == 12 || typeId == 15 || typeId == 16){
            //大数据
            var td41 = $("<td colspan='2' class='green lighten-5'>"+dataArr[3]+"</td>");//值
            tr.append(td41);
        }else {
            var td41 = $("<td class='green lighten-5'>" + (dataArr[3]) + "</td>");//值
            var decVal = hexToDec(dataArr[3]);
            var td42 = $("<td class='green lighten-5'>"+transcodeValue(dataArr[0],decVal)+"</td>");//值
            tr.append(td41);tr.append(td42);
        }
        tbody.append(tr);
    });
    $('#viewDataPartsModal').modal('show');
}

var initLinkEvent = function () {
    var links = $("a[data-parts]");
    $.each(links,function(idx,linkObj){
        $(linkObj).off('click').click(function () {
            var dataParts = $(linkObj).attr("data-parts");
            var lineNo = $(linkObj).attr("data-lineNo");
            var baseTime = $(linkObj).attr("data-time");
            // console.debug('dataParts='+dataParts);
            showDataParts(dataParts,lineNo,baseTime);
        })
    });
}

$(function () {
    initLinkEvent();
});