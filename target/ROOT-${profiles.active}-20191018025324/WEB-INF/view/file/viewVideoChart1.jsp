<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
	.videoPlayPanel .panel{
		margin-bottom: 0px;
		border-radius: 2px;
		color: #333;
		position: relative;
	}
	.videoPlayPanel .videoPlayHead h3{
		padding-left: 20px;
		height: 40px;
		line-height: 40px;
		font-size: 15px;
	}
	.videoPlayPanel .tabsMenu{
		padding: 0 14px;
		font-size: 20px;
		color:#000;
		border-bottom: 1px #ccc solid;
		margin-bottom: 25px;
	}
	.videoPlayPanel .tabsMenu:after{
		content: '';
		display: block;
		clear: both;
	}
	.videoPlayPanel .tabsMenu>li,.videoAndInfo{
		float: left;
		list-style: none;
		padding: 15px 20px;
		margin: 0 10px;
	}
	.videoPlayPanel .videoAndInfo{
		position: absolute;
		left: 10px;
		top: 71px;
		right: 40%;
		bottom: 20px;
	}
	.videoPlayPanel .videoAndInfo{

	}
	.videoPlayPanel .tabsMenu>li.active>a{
		color:#00a2f3;
		font-weight: bold;
	}
	.videoPlayPanel .tabsMenu>li.active{
		box-sizing: border-box;
		border-bottom: 2px #00a2f3 solid;
	}
	.videoPlayPanel .tabsMenu>li>a{
		color:#000;
	}
	.videoPlayPanel .tabItem{
		display: none;
		min-height: 710px;
	}
	.videoPlayPanel .tabItem.shown{
		display: block;
	}
	.videoPlayPanel #videoPlayer video{
		width: 100%;
		min-height: 250px;
		max-height: 500px;
	}
	.videoPlayPanel .videoInfo>*{
		font-size: 14px;
		padding:7px 0;
	}
	.videoPlayPanel .videoInfo{
		padding: 12px 7px;
		border:1px #ddd solid;
	}
	.videoPlayPanel .labelTitle{
		width: 80px;
		text-align: right;
		display: inline-block;
	}
	.videoPlayPanel .meterModel .r{
		border: 1px solid #ccc;
		min-height: 450px;
		padding: 10px;
		background-color: #222939;
	}
	.videoPlayPanel .meterModel .r p{
		text-align: right;
		color: #ccc;
	}
	.videoPlayPanel .meterModel .r .chart,.videoPlayPanel .dataModel .r .chart{
		min-height: 200px;
	}
	.tabItem.posInfo{
		min-height: 710px;
		/*padding-left: 532px;*/
	}
</style>



<div class="container-fluid videoPlayPanel">
	<div class="panel">
		<div class="videoPlayHead">
            <h3 class="panel-title">
				<a class="text-muted" href="#" onclick="toQueryPanel()"><span class="lnr lnr-chevron-left"></span>返回</a>
				<span>资料中心</span>
				>
				<span>${entity.id}</span>
				<span>${helmet.deviceNumber}</span>
            </h3>
        </div>
	</div>
	<div class="panel">
		<ul class="tabsMenu">
			<li class="active" data-tabitem="meterModel"><a href="javascript:;">仪表模式</a></li>
			<li data-tabitem="dataModel"><a href="javascript:fn1()">数据模式</a></li>
			<li data-tabitem="posInfo"><a href="javascript:;">位置信息</a></li>
		</ul>
		<div class="videoAndInfo  col-sm-5 col-md-4 col-lg-4" id="q1" aria-hidden="true" data-backdrop="static">
			<div class="l" id="videoPlaySection">
				<div id="videoPlayer">
	                <!-- 视频 -->
	               <video id="my-video-o" class="myvideo" poster="${fileServer}${entity.thumbOssPath}"
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
	            <dl class="videoInfo">
	            	<dt>
	            		<b class="labelTitle">头盔帐号:</b>
	            		<b class="helmetId">${helmet.deviceNumber}</b>
	            	</dt>
					<dd>
						<span class="labelTitle">录制时间:</span>
						<span class="createTime">${entity.getCreateTimeString()}</span>
					</dd>
					<%--<dd>
						<span class="labelTitle">E模式调整:</span>
						<span class="eMode">E3模式</span>
					</dd>
					<dd>
						<span class="labelTitle">动&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作:</span>
						<span class="act">有动作</span>
					</dd>
					<dd>
						<span class="labelTitle">海拔高度:</span>
						<span class="altitude">0m</span>
					</dd>--%>
	            </dl>
			</div>
		</div>
		<div class="tabItem meterModel container-fluid shown">
			<div class="l col-sm-5 col-md-4 col-lg-4">

			</div>
			<div class="r col-sm-7 col-md-8 col-lg-8">
				<p class="powerTime"> ● 开机时间: <b>00:00:00</b></p>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
				<div class=" gauge chart col-sm-4 col-md-4 col-lg-4"></div>
			</div>
		</div>
		<div class="tabItem dataModel container-fluid" >
			<div class="l col-sm-5 col-md-4 col-lg-4">

			</div>
			<div class="r col-sm-7 col-md-8 col-lg-8">
				<p class="powerTime"> ● 开机时间: <b>00:00:00</b></p>
				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>

				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>

				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>

				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>

				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>
				<div class="echart chart col-md-4 col-xd-4"></div>
			</div>
		</div>

		<div class="tabItem posInfo">
			<div class="l col-sm-5 col-md-4 col-lg-4">
				
			</div>			
			<div class="r col-sm-7 col-md-8 col-lg-8" id="mapContainer" style="height: 400px; background-color:rgb(34,41,57);"></div>
		</div>
	</div>
</div>




<script>
	$('body').on('click','.videoPlayPanel .tabsMenu>li',function(e){
		var item = $(this).data('tabitem');
		if (!item){
			return  alert('点击出错!');
		}
		$(this).addClass('active').siblings('.videoPlayPanel .tabsMenu>li').removeClass('active');
		$('.videoPlayPanel .'+item).show().siblings('.tabItem').hide();

	});

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
</script>

<script type="text/javascript" src="/static/js/viewVideo.js?v=${version}"></script>
<c:if test="${entity.hasGpsData == 1}">
    <script type="text/javascript" src="/static/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="/static/js/tyBoxVideoChart1.js?v=${version}"></script>
    <%--<script type="text/javascript" src="/static/js/videoAction.js?v=${version}"></script>--%>
</c:if>
<c:if test="${entity.hasGpsData != 1}">
    <script>
        $("#navDetail>li:last").hide();//数据图表不支持则删掉
    </script>
</c:if>
<script>
	var videoExtPage = {};
    var toQueryPanel=function () {
        $('#mediaShownModal').modal('hide');
        $('#mediaShownModal').empty();
    }
</script>
<script type="text/javascript" src="/static/js/viewVideoExt.js?v=${version}"></script>
<script type="text/javascript" src="/static/echarts/echarts.min.js"></script>
<script type="text/javascript" src="/static/js/tyBoxVideoChart1.js?v=${version}"></script>
