<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<div class="panel clearfix">--%>
    <%--<div class="panel-heading">--%>
        <%--<h3 class="panel-title">头盔列表</h3>--%>
    <%--</div>--%>
    <%--<div class="panel-body col-md-6" id="searchSection">--%>
        <%--<div class="input-group" id="searchDiv">--%>
            <%--<input type="text" id="keyword" placeholder="请输入自动扩充头盔账号的数量或者网易账号、硬件识别号来搜索头盔信息" class="form-control">--%>
            <%--<span class="input-group-btn"><button class="btn btn btn-default" type="submit" id="search-button"--%>
                                                  <%--title="搜索头盔">--%>
                                <%--<i class="fa fa-search"></i>--%>
                            <%--</button></span>--%>
            <%--<span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="add-button"--%>
                                                  <%--title="扩充头盔账号">--%>
                                <%--<i class="fa fa-plus"></i>--%>
                            <%--</button></span>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div class="custom-tabs-line tabs-line-bottom left-aligned" style="margin-bottom:10px;">--%>
    <%--<ul class="nav" role="tablist" id="saleStateList">--%>
        <%--<li class="active" data-statetype="-1"><a href="#tab-bottom-left1" role="tab" data-toggle="tab"--%>
                                                   <%--aria-expanded="true">待售</a></li>--%>
        <%--<li class="" data-statetype="1"><a href="#tab-bottom-left2" role="tab" data-toggle="tab"--%>
                                             <%--aria-expanded="false">已售</a></li>--%>
    <%--</ul>--%>
<%--</div>--%>

<%--<div id="listContent"><!-- 列表数据 -->--%>
<%--</div>--%>
<%--<!-- END MAIN -->--%>
<%--<div class="clearfix"></div>--%>
<%--<!-- END WRAPPER -->--%>

<%--<div id="qrCodeModal" class="modal">--%>
    <%--<div class="panel panel-body center-block" style="width: 50%;">--%>
        <%--<h4 id="qrCodeModalTitle">初始化账号</h4>--%>
        <%--<p>1、头盔扫描下方二维码，解析得到网址后，通过post方式访问该网址，请求header中需传入appKey,signature,imei参数；<br>--%>
            <%--2、后台接收到头盔端请求，将header里的imei和该二维码建立关联，并反馈给头盔登录使用的app登录账号和密码；<br>--%>
            <%--3、头盔app收到反馈后，使用该账号自动登录头盔app，头盔app不可退出登录或更换账号登录；<br>--%>
            <%--<!----%>
            <%--4、同时打印下方二维码，粘贴到头盔内侧，方面后续机手机扫码；<br>--%>
            <%--5、机手拿到头盔后，通过微信/手机app扫描头盔内侧二维码；<br>--%>
            <%--6、微信/手机app打开网址，建立微信与头盔的关联关系； -->--%>
        <%--</p>--%>
        <%--<div class="text-center" id="qrForm">--%>
            <%--<div class="input-field col s12" id="qrCode">--%>
                <%--<img id='qrCodeImage' class="hide"/>--%>
            <%--</div>--%>
            <%--<div style="margin-top:30px;margin-bottom: 10px;">--%>
                <%--<a class="waves-effect waves-light btn btn-primary" onclick="printQrCode()"><i class="fa fa-print"></i>--%>
                    <%--打印二维码</a>--%>
                <%--<a class="waves-effect waves-light btn btn-primary" data-dismiss="modal" aria-label="Close"><i--%>
                        <%--class="fa fa-close"></i> 关闭</a>--%>
            <%--</div>--%>
            <%--&lt;%&ndash;<div style="margin-top:30px;margin-bottom: 10px;">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="text" id="qrcode_imei" placeholder="请输入头盔IMEI" class="validate" value="">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="hidden" id="qrcode_id"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a class="waves-effect waves-light btn btn-primary" onclick="manualInit()" id="qrCodeModalBtn">手工初始化</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="regHelmetModal" class="modal">--%>
    <%--<div class="panel panel-body center-block" style="width: 50%;">--%>
        <%--<div>--%>
            <%--<h4>出厂登记</h4>--%>
            <%--<p></p>--%>
            <%--<div id="regForm">--%>
                <%--<input type="hidden" id="regForm_helmetId"/>--%>
                <%--<div>--%>
                    <%--<select class="form-control" id="customerSelect" data-init="false">--%>
                        <%--<option value="" disabled selected>选择客户</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="panel-body modal-footer">--%>
            <%--<a class="btn btn-primary" onclick="saveRegHelmet()">确定</a>--%>
            <%--<a class="btn" data-dismiss="modal" aria-label="Close">取消</a>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="updateXmlModal" class="modal">--%>
    <%--<div class="panel panel-body center-block" style="width: 50%;">--%>
        <%--<div>--%>
            <%--<h4 id="updateXmlModalTitle">升级更新文件</h4>--%>
            <%--<p></p>--%>
            <%--<div>--%>
                <%--<a target="_blank" class="list-group-item" data-type="phone">手机</a>--%>
                <%--<a target="_blank" class="list-group-item" data-type="phone_accessible">手机无障碍</a>--%>
                <%--<a target="_blank" class="list-group-item" data-type="helmet">头盔</a>--%>
                <%--<a target="_blank" class="list-group-item" data-type="helmet_accessible">头盔无障碍</a>--%>
                <%--<a target="_blank" class="list-group-item" data-type="helmet_sensor">头盔传感器</a>--%>

            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="modal-footer">--%>
            <%--<a class="btn" data-dismiss="modal" aria-label="Close">关闭</a>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="editHelmetModal" class="modal">--%>
    <%--<div class="panel panel-body center-block" style="width: 50%;">--%>
        <%--<div>--%>
            <%--<h4 id="editHelmetModalTitle">修改头盔属性</h4>--%>
            <%--<p></p>--%>
            <%--<div>--%>
                <%--<div class="input-group">--%>
                    <%--<input type="hidden" id="helmetedit_id">--%>
                    <%--<input type="text" id="helmetedit_model" placeholder="请输入模型" class="form-control" value="">--%>
                    <%--<span class="input-group-addon">型号</span>--%>
                <%--</div>--%>
                <%----%>
                <%--<div class="input-group">--%>
                    <%--<input type="text" id="helmetedit_batch" placeholder="请输入批次" class="form-control" value="">--%>
                    <%--<span class="input-group-addon">批次</span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="modal-footer">--%>
            <%--<a onclick="saveHelmet()" class="btn btn-primary">保存</a>--%>
            <%--<a class="btn" data-dismiss="modal" aria-label="Close">关闭</a>--%>
        <%--</div>--%>
    <%--</div>--%>

<%--</div>--%>

<%--<script>--%>
    <%--var helmetListPage = {};--%>
<%--</script>--%>
<%--<script src="/static/jquery/jquery.qrcode.js"></script>--%>
<%--<script src="/static/jquery/jquery.jqprint-0.3.js"></script>--%>
<%--<script src="/static/js/helmetList.js?v=${version}"></script>--%>
