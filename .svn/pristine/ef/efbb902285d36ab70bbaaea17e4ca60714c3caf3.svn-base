<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main-content">
    <div class="panel-body" id="contentSection">
            <div class="panel-heading">
                <h3 class="panel-title">KMX</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>传感器</th>
                        <th>传感器时间</th>
                        <th>入库时间</th>
                        <th>数值</th>
                    </tr>
                    </thead>
                    <tbody id="kmxdataList">
                        <c:forEach var="point" items="${vo.list}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${point.metric}</td>
                                <td>${point.primaryTime}</td>
                                <td>${point.secondaryTime}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${dataType == 'geo'}">
                                            (${point.longitude},${point.latitude})
                                        </c:when>
                                        <c:otherwise>
                                            ${point.value}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

    </div>
</div>
<script>
    $(function () {
        var tdlist = $("#kmxdataList td");
        var date =  new Date();
        $.each(tdlist,function(idx,td){
            var left = idx % 10;
            if(left ==2 || left ==3 || left ==7 || left ==8){
                var txt = $.trim($(td).text());
                date.setTime(txt*1);
                var fmt = date.Format("yyyy-MM-dd hh:mm:ss.S");
//                console.debug("时间td:"+idx+",txt="+txt+",date="+date+",fmt="+fmt);
                $(td).text(fmt);
            }
        });
    });
</script>