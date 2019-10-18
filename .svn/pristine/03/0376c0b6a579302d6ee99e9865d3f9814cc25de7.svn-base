<!-- wenxinyan 2018-8-16 重写了分页部分 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
    <div class="panel clearfix" id="searchSection">
        <div class="panel-heading">
            <h3 class="panel-title"></h3>
        </div>

        <div class="panel-body col-md-6"  id="searchDiv">
            <div class="input-group">
                <select class="form-control" id="deviceType" onchange="devChange();">
                    <c:if test="${deviceType == ''}">
                        <option value="" selected>全部</option>
                        <option value="TK">头盔</option>
                        <option value="FWQ">服务器</option>
                    </c:if>
                    <c:if test="${deviceType == 'TK'}">
                        <option value="">全部</option>
                        <option value="TK" selected>头盔</option>
                        <option value="FWQ">服务器</option>
                    </c:if>
                    <c:if test="${deviceType == 'FWQ'}">
                        <option value="">全部</option>
                        <option value="TK">头盔</option>
                        <option value="FWQ" selected>服务器</option>
                    </c:if>
                </select>
                <label for="deviceType" class="input-group-addon">设备类型</label>
            </div>
            <div class="input-group">
                <select class="form-control" id="logType">
                    <c:if test="${logType == ''}">
                        <option value="" selected>全部</option>
                        <option value="BT">蓝牙</option>
                        <option value="UPLOAD">文件上传</option>
                        <option value="MIND">脑波</option>
                        <option value="VOICE">语音识别</option>
                        <option value="CRASH">异常崩溃</option>
                        <option value="WELCOME">开机欢迎界面</option>
                    </c:if>
                    <c:if test="${logType == 'BT'}">
                        <option value="">全部</option>
                        <option value="BT" selected>蓝牙</option>
                        <option value="UPLOAD">文件上传</option>
                        <option value="MIND">脑波</option>
                        <option value="VOICE">语音识别</option>
                        <option value="CRASH">异常崩溃</option>
                        <option value="WELCOME">开机欢迎界面</option>
                    </c:if>
                    <c:if test="${logType == 'UPLOAD'}">
                        <option value="">全部</option>
                        <option value="BT">蓝牙</option>
                        <option value="UPLOAD" selected>文件上传</option>
                        <option value="MIND">脑波</option>
                        <option value="VOICE">语音识别</option>
                        <option value="CRASH">异常崩溃</option>
                        <option value="WELCOME">开机欢迎界面</option>
                    </c:if>
                    <c:if test="${logType == 'MIND'}">
                        <option value="">全部</option>
                        <option value="BT">蓝牙</option>
                        <option value="UPLOAD">文件上传</option>
                        <option value="MIND" selected>脑波</option>
                        <option value="VOICE">语音识别</option>
                        <option value="CRASH">异常崩溃</option>
                        <option value="WELCOME">开机欢迎界面</option>
                    </c:if>
                    <c:if test="${logType == 'VOICE'}">
                        <option value="">全部</option>
                        <option value="BT">蓝牙</option>
                        <option value="UPLOAD">文件上传</option>
                        <option value="MIND">脑波</option>
                        <option value="VOICE" selected>语音识别</option>
                        <option value="CRASH">异常崩溃</option>
                        <option value="WELCOME">开机欢迎界面</option>
                    </c:if>
                    <c:if test="${logType == 'CRASH'}">
                        <option value="">全部</option>
                        <option value="BT">蓝牙</option>
                        <option value="UPLOAD">文件上传</option>
                        <option value="MIND">脑波</option>
                        <option value="VOICE">语音识别</option>
                        <option value="CRASH" selected>异常崩溃</option>
                        <option value="WELCOME">开机欢迎界面</option>
                    </c:if>
                    <c:if test="${logType == 'WELCOME'}">
                        <option value="">全部</option>
                        <option value="BT">蓝牙</option>
                        <option value="UPLOAD">文件上传</option>
                        <option value="MIND">脑波</option>
                        <option value="VOICE">语音识别</option>
                        <option value="CRASH">异常崩溃</option>
                        <option value="WELCOME" selected>开机欢迎界面</option>
                    </c:if>
                </select>
                <label for="logType" class="input-group-addon">日志类型</label>
            </div>
            <div class="input-group">
                <select class="form-control" id="logNature" onchange="natChange();">
                    <option value="0">流日志</option>
                    <option value="1">异常日志</option>
                </select>
                <label for="logNature" class="input-group-addon">日志性质</label>
            </div>
            <div class="input-group">
                <input type="text" id="keyword" placeholder="请输入设备名称" class="form-control" value="${keyword}" />
                <label for="keyword" class="input-group-addon">设备标识</label>
            </div>
            <div class="input-group">
                <input type="text" id="logflow" placeholder="请输入日志流ID" class="form-control" value="${logflow}" />
                <label for="logflow" class="input-group-addon">日志流ID</label>
            </div>
            <div class="input-group">
                <input type="text" id="time1" placeholder="请输入时间1"  class="form-control" value="">
                <label for="time1" class="input-group-addon">开始时间</label>
            </div>
            <div class="input-group">
                <input type="text" id="time2" placeholder="请输入时间2"  class="form-control" value="">
                <label for="time2" class="input-group-addon">结束时间</label>
            </div>
            <div class="clearfix" style="margin-top: 8px;">
                <button class="btn btn btn-primary pull-right" type="submit" id="search-button" title="点击搜索">
                    <i class="fa fa-search"></i>
                </button>
            </div>
            <%--<div class="input-group">--%>
                <%--<input type="text" id="keywor" placeholder="请输入头盔名称" class="form-control" value="${keyword}">--%>
                <%--<span class="input-group-btn">--%>
                	<%--<button class="btn btn btn-primary" type="submit" id="search-button" title="点击搜索"><i class="fa fa-search"></i></button>--%>
                <%--</span>--%>
            <%--</div>--%>
        </div>
        <!-- 分页 -->
		    <%--<div class="panel-body col-md-3">--%>
				<%--<div class="input-group">--%>
					<%--<span class="input-group-addon">页码：</span>--%>
					<%--<input type="number"  class="form-control" id="page">--%>
					<%--<span class="input-group-btn" id="btn_jump">--%>
	              		<%--<button class="btn btn btn-primary" type="submit" title="跳转"> <i class="fa fa-search"></i> </button>--%>
	              	<%--</span>--%>
	              	<%--<span class="input-group-btn" id="btn_left">--%>
	              		<%--<button class="btn btn btn-primary" type="submit" title="上一页"> <i class="fa fa-arrow-left"></i> </button>--%>
	              	<%--</span>--%>
					<%--<span class="input-group-btn" id="btn_right">--%>
	              		<%--<button class="btn btn btn-primary" type="submit" title="下一页"> <i class="fa fa-arrow-right"></i> </button>--%>
	              	<%--</span>--%>
	            <%--</div>--%>
			<%--</div>--%>
		</div>
    </div>

    <div class="panel-body" id="contentSection">
        <div class="panel-heading">
            <h3 class="panel-title">操作日志<b id="helmetId"></b></h3>
            <h3 class="panel-title" id="errorLog" style="display: none"> </h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>日志流ID</th>
                    <th>日志类别</th>
                    <th>UUID</th>
                    <th>设备类型</th>
                    <th>序号</th>
                    <th>日期</th>
                    <th>时间</th>
                    <th>日志内容</th>
                    <th>日志性质</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="log" items="${vo.list}" varStatus="status">
                        <tr>
                            <td>${log.logflowId}</td>
                            <td>${log.logType}</td>
                            <td>${log.UUID}</td>
                            <td>
                                <c:if test="${log.deviceType == 'TK'}">
                                    <c:out value="头盔" />
                                </c:if>
                                <c:if test="${log.deviceType == 'FWQ'}">
                                    <c:out value="服务器" />
                                </c:if>
                            </td>
                            <td>${log.orderNo}</td>
                            <td>
                                <c:if test="${not empty log.logDate}">
                                    <c:out value="${log.logDate}" />
                                </c:if>
                            </td>
                            <td>${log.getLogTimeString()}</td>
                            <td>${log.logContent}</td>
                            <td>
                                <c:if test="${log.logNature == 0}">
                                    <c:out value="流日志" />
                                </c:if>
                                <c:if test="${log.logNature == 1}">
                                    <c:out value="异常日志" />
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="../include/new-page-pager.jsp"></jsp:include>

    </div>
</div>

<script>
    var helmet_log = {};
    helmet_log.keyword = '${keyword}';
    helmet_log.logflow = '${logflow}';
</script>
<script src="/static/js/OperaLog.js?v=${version}"></script>

<%--<script>--%>
    <%--var helmet_log = {};--%>
     <%--var helmetId ; //头盔id--%>
     <%--var page=1;--%>
     <%--var count;--%>
     <%--$(function(){--%>
	 <%--//查询--%>
     	<%--$('#search-button').click(function(event) {--%>
     		<%--$('#page').val('1');--%>
     		<%--page = 1;--%>
     		<%--serLog(1);--%>
     	<%--});--%>
	 <%--// 跳转--%>
	 	<%--$('#btn_jump').click(function(event) {--%>
     		<%--page = $('#page').val();--%>
     		<%--if(!page||page<=0){--%>
     			<%--alert('请输入正确的页码!');--%>
     			<%--return;--%>
     		<%--}--%>
     		<%--serLog(page);--%>
     	<%--});--%>
	 <%--//下一页--%>
	 	<%--$('#btn_right').click(function(event) {--%>
	 		<%--if (page==count) {--%>
	 			<%--alert('已经最后一页了!');--%>
	 			<%--return;--%>
	 		<%--}--%>
     		<%--$('#page').val(''+(++page));--%>
     		<%--if(!page||page<=0){--%>
     			<%--alert('请输入正确的页码!');--%>
     			<%--return;--%>
     		<%--}--%>
     		<%--serLog(page);--%>
     	<%--});--%>
	 <%--// 上一页--%>
	 	<%--$('#btn_left').click(function(event) {--%>
	 		<%--if (page==1) {--%>
	 			<%--alert('已经第一页了!');--%>
	 			<%--return;--%>
	 		<%--}--%>
     		<%--if(!page||page<=0){--%>
     			<%--alert('请输入正确的页码!');--%>
     			<%--return;--%>
     		<%--}--%>
     		<%--$('#page').val(''+(--page));--%>
     		<%--serLog(page);--%>
     	<%--});--%>
	 <%--});--%>

     <%--function serLog(page){--%>
	 	<%--helmetId = $("#keyword").val();--%>

	 	<%--if (helmetId===undefined||helmetId.length==0) {--%>
	 		<%--alert('请输入头盔号进行查询!');--%>
	 		<%--return;--%>
	 	<%--}--%>

	 	<%--$.ajax({--%>
	 		<%--url: '/operalog/list',--%>
	 		<%--type: 'post',--%>
	 		<%--dataType: 'json',--%>
	 		<%--data: {--%>
	 			<%--clientId: helmetId,--%>
	 			<%--page : page--%>
	 		<%--},--%>
	 		<%--success:function(res) {--%>
	 			<%--$('#errorLog').hide();--%>
	 			<%--count = res.count;--%>
	 			<%--if (res.data.length == 0) {--%>
	 				<%--$('tbody').html('无');--%>
	 				<%--return;--%>
	 			<%--}--%>

	 			<%--var tbody = '';--%>
	 			<%--for (var i = 0; i < res.data.length; i++) {--%>
	 				<%--tbody+='<tr><td>'+res.data[i].id+'</td>';--%>
	 				<%--tbody+='<td>'+res.data[i].createTimeString+'</td>';--%>
	 				<%--tbody+='<td>'+res.data[i].logContent+'</td></tr>';--%>
	 			<%--}--%>

	 			<%--$('tbody').html(tbody);--%>

	 		<%--},--%>
	 		<%--error:function(err){--%>
	 			<%--console.error(err);--%>
	 			<%--$('#errorLog').text(err).show();--%>
	 		<%--}--%>
	 	<%--})--%>
     <%--}--%>





<%--</script>--%>
