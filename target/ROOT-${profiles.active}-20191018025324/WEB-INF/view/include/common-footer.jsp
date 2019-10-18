<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<footer>
    <div class="container">
        <h5>田一科技</h5>
        <p class="text-small">北京田一科技成立于2017年，是工程机械行业信息化创新企业，致力于工业互联网相关产品及系统的设计、建设、合作。</p>           
    </div>
    <div class="footer-copyright">
        <div class="container">      
             © 2017-2018 <a class="text-small" href="/">北京田一科技</a>       
        </div>
    </div>
</footer>

<!--  Scripts-->
<script src="/static/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<c:if test="${isLogin}">
    <script>
        var indexPage = {};
        indexPage.neteaseAppKey='${neteaseAppKey}';
        indexPage.currentUserId='${userInfo.username}';
        indexPage.currentToken='${userInfo.token}';
    </script>
</c:if>
