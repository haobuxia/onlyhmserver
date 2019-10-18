<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/common-head.jsp"></jsp:include>
<body>
<c:choose>
	<c:when test="${isLogin}">
		<jsp:include page="../include/nav-head-logined.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="../include/nav-head-notlogin.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
<main>
	<div class="section">
		<!--   Icon Section   -->
		<div class="row">
			<div class="col s12">
				<ul class="collection with-header">
					<li class="collection-header"><h4>发生了错误！可能的原因：</h4></li>
					<li class="collection-item">${msg}</li>
				</ul>
			</div>
			<div class="col s12 center-align">
				<a class="waves-effect waves-light btn-large pulse" href="/index"><i class="material-icons left">cloud</i>进入首页</a>
			</div>
		</div>
	</div>
</main>
</main>
<jsp:include page="../include/common-footer.jsp"></jsp:include>
</body>
</html>


