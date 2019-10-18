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
    <jsp:include page="content403.jsp"/>
</main>
</main>
<jsp:include page="../include/common-footer.jsp"></jsp:include>
</body>
</html>


