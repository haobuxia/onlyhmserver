<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="section">
        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12">
                <h4 id="qrCodeModalTitle">用户账号绑定头盔</h4>
                <p>1、头盔扫描下方二维码，解析得到网址后，通过post方式访问该网址，请求header中需传入appKey,signature,imei参数；<br>
                    2、后台接收到头盔端请求，将header里的imei和该二维码建立关联，并反馈给头盔对应用户账号绑定成功；<br>
                    3、头盔app收到反馈后，后续请求后台接口时只需在header里传入imei给后台，后台会自动匹配对应的用户账号；<br>
                    4、一个用户账号可以绑定多个头盔；<br><br>
                </p>
                <div class="row center" id="qrForm">
                    <div class="input-field col s12 m4 l4" id="qrCode">
                        <img id='qrCodeImage' class="hide"/>
                    </div>
                    <!-- todo 已改为可以绑定多个 -->
                    <div class="col s4">
                        <br>
                        <span>当前账号已绑定头盔:</span>
                        <c:forEach items="${list}" var="keyword" varStatus="id">
                            ${keyword.deviceNumber}&nbsp;&nbsp;
                        </c:forEach>
                    </div>
                    <div class="col s12 m4 l4"></div>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var tyUserQrcodePage={};
    tyUserQrcodePage.time = "${time}";
    tyUserQrcodePage.userId = "${userId}";
    tyUserQrcodePage.token = "${token}";
</script>
<script src="/static/jquery/jquery.qrcode.js"></script>
<script src="/static/js/tyUserQrcode.js"></script>