<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.echart{min-height: 250px;}
	.chartlist>button{margin: 1px;font-size: 12px;padding: 5px 10px;}
</style>
<link rel="stylesheet" href="/static/css/timeLineStyle.css">
<div class="main-content videoPlayPage">
	<div class="panel">

		<div class="panel-heading">
			<h3 class="panel-title">
				<a class="text-muted" href="#" onclick="toQueryPanel()"><span class="lnr lnr-chevron-left"></span>返回</a>
			</h3>
		</div>
		<div class="panel-body clearfix align-mid" id="videoPlaySection" style="background:#2e2e36;">
			<div class="col-md-12" style="position: relative;">
				<c:if test="${entity.hasGpsData == 1}">
					<div style="position: absolute;right: 10px;top: 10px;z-index: 1"><a href="/play/chart/detail?videoId=${entity.id}"><i class="fa fa-bar-chart" ></i>显示数据</a></div>
				</c:if>
				<div id="videoPlayer" >
					<!-- 视频 -->
					<video id="my-video" class="myvideo" poster="${fileServer}${entity.thumbOssPath}"
						   src="${fileServer}<c:choose><c:when test='${entity.trackVideoOssPath == null || entity.trackVideoOssPath == ""}'>${entity.ossPath}</c:when><c:otherwise>${entity.trackVideoOssPath}</c:otherwise></c:choose>"
						   preload="auto" controls="" webkit-playsinline="true" playsinline="true" x-webkit-airplay="allow"
						   x5-video-player-type="h5" x5-video-player-fullscreen="true" x5-video-orientation="portraint">
						<p class="vjs-no-js">
							To view this video please enable JavaScript, and consider upgrading to a web
							browser that
							<a href="http://videojs.com/html5-video-support/" target="_blank">supports
								HTML5 video</a>
						</p>
					</video>
				</div>
				<div class="con-btns">
					<a href="${fileServer}${entity.ossPath}" class="text-muted" target="_blank"><span class="fa fa-download"></span> 下载原视频</a>
					<c:if test="${entity.hasGpsData == 1}">
						<a href="${fileServer}${entity.trackVideoOssPath}" class="text-muted" target="_blank"><span class="fa fa-download"></span> 下载字幕版</a>
						<a href="/track/vtt/${entity.id}" class="text-muted" target="_blank"><span class="fa fa-download"></span> 下载字幕</a>
					</c:if>
					<c:if test="${userInfo.admin}">
						<c:if test="${entity.hasGpsData == 1}">
							<a href="javascript:delTrackVideo()" class="text-muted"><span class="fa fa-trash"></span> 删除字幕版</a>
						</c:if>
						<a href="/recirculate/report/${entity.id}" class="text-muted" target="_blank"><span class="fa fa-file-text-o"></span> 评估报告</a>
					</c:if>

				</div>
				<div id="timeline">
						<ul id="dates">
							<c:forEach var="item" items="${keywordList}" varStatus="status">
								<c:if test="${item.keywordId!=0}">
									<li><a href="#" timeOption="${item.timeseconds}">${item.keywordName}</a></li>
								</c:if>
							</c:forEach>
						</ul>
				</div>
			</div>
		</div>

		<div class="panel-heading align-mid">
			<div class="custom-tabs-line tabs-line-bottom left-aligned">
				<ul class="nav" role="tablist" id="navDetail">
					<li class="active" data-restype="v-detail">
						<a href="#tab-bottom-left1" role="tab" data-toggle="tab" aria-expanded="true" data-groupby="v-detail">视频简介</a>
					</li>
					<li class="" data-restype="v-map">
						<a href="#tab-bottom-left2" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="v-map">坐标地图</a>
					</li>
					<li class="" data-restype="v-video">
						<a href="#tab-bottom-left2" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="v-video">语音备注</a>
					</li>
					<li class="" data-restype="v-keyword">
						<a href="#tab-bottom-left3" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="v-keyword">关键词</a>
					</li>
					<li class="" data-restype="v-data">
						<a href="#tab-bottom-left3" role="tab" data-toggle="tab" aria-expanded="false" data-groupby="v-data">关键词</a>
					</li>
				</ul>
			</div>
		</div>

		<div class="align-mid tablists">
			<!-- 列表项1 视频简介-->
			<div class="clearfix tablist">
				<div class="profile-detail">
					<div class="profile-info col-md-6">
						<h4 class="heading">基础信息</h4>
						<ul class="list-unstyled list-justify">
							<c:if test="${not empty helmet}">
								<li>客户ID <span>${helmet.affiliationId}</span></li>
								<li>头盔ID <span>${helmet.deviceUUID}</span></li>
								<li>头盔账号 <span>${helmet.deviceNumber}</span></li>
							</c:if>
							<c:if test="${not empty entity.machineCode}">
								<li>机号 <span>${entity.machineCode}</span></li>
							</c:if>
							<c:if test="${not empty entity.imei}">
								<li>车载盒子 <span>${entity.imei}</span></li>
							</c:if>
						</ul>
					</div>
					<div class="profile-info col-md-6">
						<h4 class="heading">视频信息</h4>
						<ul class="list-unstyled list-justify">
							<li>视频时长<span>${entity.seconds}秒</span></li>
							<li>文件大小<span>${entity.sizeKb}KB</span></li>
							<li>查看次数<span>${entity.viewCount}</span></li>
							<li>录制时间<span>${entity.getCreateTimeString()}</span></li>
							<%--<c:if test="${!userInfo.demo}">--%>
								<li>保存时间<span>${entity.getUploadTimeString()}</span></li>
							<%--</c:if>--%>
							<c:if test="${entity.lon > 0 and entity.lat > 0}">
								<li>GPS定位<span>${entity.lon},${entity.lat}</span></li>
							</c:if>
							<c:if test="${entity.siteId > 0 }">
								<li>工地名称<span>${entity.site.name}</span></li>
								<li>工地地址<span>${entity.site.address}</span></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			<!-- 列表项2 地图-->
			<div class="clearfix tablist" style="display: none;">
				<div class="container" id="mapContainer" style="height: 300px;"></div>
			</div>

			<!-- 列表项3 语音备注 -->
			<div class="clearfix tablist" style="display: none;">
				<div class="profile-info">
					<h4 class="heading"><i class="fa fa-pencil"></i>视频语音备注</h4>
					<textarea id="videoText" class="form-control" style="height:auto;min-height: 150px;max-height:300px;" data-length="300"></textarea>
				</div>
				<div class="text-center">
					<button id="videoTextSaveBtn" class="btn btn-primary"><i class="fa fa-floppy-o"></i>保存备注</button>
				</div>
			</div>

			<!-- 列表项4 语音关键词识别确认 -->
			<div class="clearfix tablist" style="display: none;">
				<div class="profile-info">
					<table class="table table-striped">
						<thead>
						<tr>
							<th>开始时间（秒）</th>
							<th>原始语音</th>
							<th>识别关键词</th>
						</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="text-center">
					<button id="videoKeywordSaveBtn" class="btn btn-primary"><i class="fa fa-floppy-o"></i>保存</button>
				</div>
			</div>

			<!-- 列表项5 关键字 -->
			<div class="clearfix tablist" style="display: none;">
				<h3 class="panel-title video-name">${entity.id} ${helmet.deviceNumber}</h3>
				<br>
				<h4 class="panel-title">关键词
					<a href="javascript:;" id="keyAdd" class="pull-right" style="padding:3px 5px"><span class=" fa fa-plus-circle" title="添加关键词" style="font-size:20px"></span> 添加关键词 </a>
					<a href="javascript:;" id="keyClose" class="pull-right" style="padding:3px 5px"><span class=" fa fa-window-close-o" title="删除关键词"   style="font-size:20px"></span> 删除关键词 </a>
				</h4>
				<h4 class="panel-title" id="keyWin" style="display: none;margin-top: 10px;">
					<input placeholder="输入关键词后,回车" id="keyword" type="text" class="form-control" data-length="10" style="width:300px">
				</h4>
				<div class="panel-body" id="keywordArea">
					<%--<div class="chip btn btn-primary">--%>
					<%--车辆--%>
					<%--<i class="myClose fa fa-close" data-key="4" title="点击移除此关键词" style="display: none;"></i>--%>
					<%--</div>--%>

				</div>
			</div>
			<!-- 列表项4 数据图表 -->
			<%--<div class="clearfix tablist" style="display: none;">--%>
			<%--<div class="profile-detail">--%>
			<%--<div class="profile-info">--%>
			<%--<h4 class="heading">选择图表</h4>--%>
			<%--<div class="chartlist tabs-line-bottom custom-tabs-line">--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<button class="btn btn-default">R泵泵压Kg/cm2</button>&ndash;%&gt;--%>
			<%--<div class="btn btn-success" id="v-win">视频</div>--%>
			<%--</div>--%>
			<%--</div>--%>
			<%--<div class="profile-info clearfix" id="chartdetail" style="display:flex;">--%>

			<%--</div>--%>
			<%--</div>--%>
			<%--</div>--%>
			<c:if test="${entity.hasGpsData == 1}">
				<!-- ixu 数据图表弹窗  start   -->
				<div class="modal" id="q1" aria-hidden="true" data-backdrop="static">
					<div class="panel panel-body center-block" style="width: 98%;height: 90%;margin-top:2%;position: relative;">
						<i class="fa fa-close" style="position: absolute;right: 5px;top: 5px;font-size: 30px;z-index:9" onclick="fn2()"></i>
						<div class="row" style="height: 30%;">
							<div class="echart chart col-md-4 col-xd-4"></div>
							<div class="echart chart col-md-4 col-xd-4"></div>
							<div class="echart chart col-md-4 col-xd-4"></div>
						</div>
						<div class="row videoPlayer" style="height: 40%;">
							<div class="echart chart col-md-4 col-xd-4"></div>
							<!-- 视频 -->
							<video id="my-video-o" class="myvideo col-md-4 col-xd-4" poster="${fileServer}${entity.thumbOssPath}"
								   src="${fileServer}<c:choose><c:when test='${entity.trackVideoOssPath == null || entity.trackVideoOssPath == ""}'>${entity.ossPath}</c:when><c:otherwise>${entity.trackVideoOssPath}</c:otherwise></c:choose>"
								   preload="auto" controls="" webkit-playsinline="true" playsinline="true" x-webkit-airplay="allow"
								   x5-video-player-type="h5" x5-video-player-fullscreen="true" x5-video-orientation="portraint">
								<p class="vjs-no-js">
									To view this video please enable JavaScript, and consider upgrading to a web
									browser that
									<a href="http://videojs.com/html5-video-support/" target="_blank">supports
										HTML5 video</a>
								</p>
							</video>
							<div class="echart chart col-md-4 col-xd-4"></div>
						</div>
						<div class="row" style="height: 30%;">
							<div class="echart chart col-md-4 col-xd-4"></div>
							<div class="echart chart col-md-4 col-xd-4"></div>
							<div class="echart chart col-md-4 col-xd-4"></div>
						</div>
					</div>
				</div>
				<!-- ixu 数据图表弹窗  end -->
			</c:if>
		</div>
	</div>
</div>
<div id="editUserModal" class="modal" style="top:60%">
	<div class="panel panel-body center-block" style="width: 40%;">
		<div>
			<h4 id="editUserModalTitle">修改识别关键词结果信息</h4>
			<p></p>
			<div id="editForm">
				<div class="input-group" style="display: none">
					<input type="text" id="edit_id" placeholder="结果ID" class="form-control" value="" readonly />
					<input type="text" id="edit_videoId" placeholder="视频ID" class="form-control" value="" readonly />
				</div>
				<div class="input-group">
					<input type="text" id="edit_perspeech" placeholder="原始语音" class="form-control" value="" disabled />
					<span class="input-group-addon">原始语音*</span>
				</div>
				<div class="input-group">
					<div id="editCompanyDiv">
						<select class="form-control" id="edit_keyword" readonly="false">
							<option value="0" selected>--</option>
						</select>
					</div>
					<span class="input-group-addon">识别关键词</span>
				</div>
			</div>
		</div>

		<div class="modal-footer">
			<a href="javascript:void(0)" id="editsubmit-button" class="btn btn-primary">确定</a>
			<a href="#" onclick="toQueryPanel1()" class="btn btn-danger" role="button" aria-label="Close">关闭</a>
		</div>
	</div>
</div>
<script>
    var videoPage = {};
    videoPage.videoId = ${entity.id};
    videoPage.lonlat = [${entity.lon}, ${entity.lat}];
    videoPage.videoCreateTime = new Date('${entity.createTime}');
    videoPage.videoData = {
        id:${entity.id},
        imei: '${entity.clientId}',
        neUsername: '${entity.neUserName}',
        createTimeString: '${entity.getCreateTimeString()}'
    };

    var videoExtPage = {};
    videoExtPage.videoId = ${entity.id};
    videoExtPage.lonlat = [${entity.lon},${entity.lat}];
    videoExtPage.videoExtend = ${videoExtend};
    videoExtPage.keywordList = ${keywordListStr};
    videoExtPage.kwList = ${kwListStr};

    //添加关键词 窗口切换
    $("#keyAdd").click(function(){
        $("#keyWin").slideToggle();
    });
    // 删除关键词  按钮显示切换
    $("#keyClose").click(function(event) {
        $("#keywordArea i").fadeToggle();
    });
    // tabs 切换
    $("#navDetail>li").on('click',function(){
        var i = $(this).index();
        $(".tablists>.tablist").hide().eq(i).show();
    });

    //    $(".navbar").hide();
    $(".main").css("padding-top","0");
    $(".tablist:eq(3) .profile-detail .profile-info:eq(0)").hide();
    //    $('body').append('<span style="font-size:20px;position:fixed;right:10px;top:10px;" onclick="showNav()"><i class="fa fa-caret-square-o-down"></i>出现导航栏</span>');

    //    function showNav(){
    //        $(".navbar").show();
    //        $(".main").css("padding-top","80px");
    //    }
    var toQueryPanel=function () {
        $('#mediaShownModal').modal('hide');
        $('#mediaShownModal').empty();
    }
    var toQueryPanel1=function () {
        $('#editUserModal').modal('hide');
//        $('#editUserModal').empty();
    }
    $('#editUserModal').on("hidden.bs.modal",function(){
        $(document.body).addClass("modal-open");
    });
</script>

<script type="text/javascript" src="/static/js/viewVideo.js?v=${version}"></script>
<c:if test="${entity.hasGpsData == 1}">
	<script type="text/javascript" src="/static/echarts/echarts.min.js"></script>
	<script type="text/javascript" src="/static/js/tyBoxVideoChart.js?v=${version}"></script>
	<%--<script type="text/javascript" src="/static/js/videoAction.js?v=${version}"></script>--%>
</c:if>
<c:if test="${entity.hasGpsData != 1}">
	<script>
        $("#navDetail>li:last").hide();//数据图表不支持则删掉
	</script>
</c:if>
<script type="text/javascript" src="/static/js/viewVideoExt.js?v=${version}"></script>
