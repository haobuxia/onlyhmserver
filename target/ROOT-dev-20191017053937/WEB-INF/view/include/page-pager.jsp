<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${vo.getPageCount() > 1}">
    <div class="section no-pad-bot" style="margin-left: 50px;">
        <div class="row">
            <ul class="pagination page-${type}">
                <c:if test="${vo.hasPrePage()}">
                    <li class="waves-effect" data-page="1" title="首页"><a href="javascript:void(0)"><i class="material-icons">first_page</i></a></li>
                    <li class="waves-effect" data-page="${vo.page-1}" title="前一页"><a href="javascript:void(0)"><i class="material-icons">chevron_left</i></a></li>
                </c:if>
                <c:forEach var="pageIdx" items="${vo.getPageRange()}">
                    <li class="<c:choose>
                    <c:when test='${pageIdx == vo.page}'>active</c:when>
                    <c:otherwise>waves-effect</c:otherwise></c:choose>
                " data-page="${pageIdx}"><a href="javascript:void(0)">${pageIdx}</a></li>
                </c:forEach>
                <c:if test="${vo.hasNextPage()}">
                    <li class="waves-effect" data-page="${vo.page+1}" title="后一页"><a href="javascript:void(0)"><i class="material-icons">chevron_right</i></a></li>
                    <li class="waves-effect" data-page="${vo.getPageCount()}" title="最后一页"><a href="javascript:void(0)"><i class="material-icons">last_page</i></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</c:if>
<c:if test="${vo.total == 0}">
    <div class="section no-pad-bot">
        <div class="row">
            无数据
        </div>
    </div>
</c:if>
<script>
    var pagePager = {};
    pagePager.page = ${vo.page};
    pagePager.pageSize = ${vo.pageSize};
    pagePager.total = ${vo.total};
    pagePager.totalPage =  ${vo.getPageCount()};
    $(function () {
        var links = $("ul.pagination.page-${type}").find("li[data-page]");
        $.each(links, function (idx,link) {
            $(link).off('click').on('click',function () {
                loadData($(this).attr("data-page"),'${type}');
            });
        });
    });
</script>

