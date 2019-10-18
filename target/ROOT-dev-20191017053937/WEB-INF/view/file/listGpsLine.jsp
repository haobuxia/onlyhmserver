<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="section" id="contentSection">
    <div class="container list-data">
        <div class="row">
            <div class="col s12">
                <table class="striped">
                    <thead>
                    <tr>
                        <th>行号</th>
                        <th>IMEI</th>
                        <th>字节长度</th>
                        <th>信息生成时间</th>
                        <th>数据条数</th>
                        <th>数据内容</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="gpsLine" items="${list}">
                        <tr>
                            <td>${gpsLine.lineNo}</td>
                            <td>${gpsLine.imei}</td>
                            <td>${gpsLine.byteLength}</td>
                            <td>${gpsLine.getBaseTimeString()}</td>
                            <td>${gpsLine.dataCount}</td>
                            <td><a data-parts="${gpsLine.dataParts}" data-lineNo="${gpsLine.lineNo}" data-time="${gpsLine.getBaseTimeString()}"class="blue-text" style="cursor: pointer;">查看</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Modal Structure -->
<div id="viewDataPartsModal" class="modal modal-fixed-footer">
    <div class="modal-content">
        <h4 id="dataPartsTitle">原始数据</h4>
        <div class="row center">
            <div class="col s12">
                <table class="striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th class="red lighten-5">数据类型</th>
                        <th class="red lighten-5">类型解析</th>
                        <th class="deep-purple lighten-5">相对时间</th>
                        <th class="deep-purple lighten-5">时间解析</th>
                        <th class="light-blue lighten-5">数据长度</th>
                        <th class="light-blue lighten-5">长度解析</th>
                        <th class="green lighten-5">数据值</th>
                        <th class="green lighten-5">值解析</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a href="javascript:void(0)" id="dataparts-button" class="modal-action modal-close btn waves-effect waves-green">关闭</a>
    </div>
</div>

<script>
    var listGpsLinePage = {};
    listGpsLinePage.typeMap = ${typeMap};
</script>
<script src="/static/js/listGpsLine.js?v=${version}"></script>
