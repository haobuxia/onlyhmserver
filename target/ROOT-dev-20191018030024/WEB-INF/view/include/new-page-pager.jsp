<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String type = request.getParameter("resType");
if(type == null) type ="";
%>
<!-- 分页区域 start-->
<div class="panel-headline" id="new-page-pager${type}">
    <div class="panel-body">
        <p class="demo-button">
            <span id="pageInfo">共 0 条数据 , 第 0 页 , 共 0 页</span>
            , 跳转到
            <input type="text" style="width:50px;" id="toPage${type}"> 页
            <button type="button" class="btn btn-xs btn-primary" id="toPageButton${type}">GO</button>
            &nbsp;
            <!-- 禁用按钮 -->
            <button type="button" class="btn btn-xs btn-primary" id="prePageButton${type}">上一页</button>
            &nbsp;
            <!-- 可用按钮 -->
            <button type="button" class="btn btn-xs btn-primary" id="nextPageButton${type}">下一页</button>
        </p>
    </div>
</div>
<script>
    var newPagePager${type} = {};
    newPagePager${type}.page = ${vo.page}||1;
    newPagePager${type}.pageCount = ${vo.pageCount}||1;
    newPagePager${type}.total = ${vo.total}||0;
    newPagePager${type}.pageSize = ${vo.pageSize}||8;

    function makePage${type}(page, pageCount) {
        newPagePager${type}.currentPage = 1;
        if (page > pageCount) {
            newPagePager${type}.currentPage = pageCount;
        } else {
            newPagePager${type}.currentPage = page;
        }

        $("#new-page-pager${type}").attr("data-page", newPagePager${type}.currentPage);

        if (newPagePager${type}.currentPage <= 1) {
            $("#prePageButton${type}").addClass("disabled").prop("disabled", true);
        } else {
            $("#prePageButton${type}").removeClass("disabled").prop("disabled", false);
        }

        if (newPagePager${type}.currentPage >= pageCount) {
            $("#nextPageButton${type}").addClass("disabled").prop("disabled", true);
        } else {
            $("#nextPageButton${type}").removeClass("disabled").prop("disabled", false);
        }

        $("#toPageButton${type}").off('click').click(function () {
            var toPage = $.trim($("#toPage${type}").val());
            if (isNaN(toPage)) {
                return;
            }
            loadData(toPage, "${type}");
        });
        $("#prePageButton${type}").off('click').click(function () {
            loadData(newPagePager${type}.currentPage - 1, "${type}");
        });
        $("#nextPageButton${type}").off('click').click(function () {
            loadData(newPagePager${type}.currentPage + 1, "${type}");
        });

        $("#pageInfo").text("共 "+newPagePager${type}.total+" 条数据 , 第 "+newPagePager${type}.currentPage+" 页 , 共 "+newPagePager${type}.pageCount+" 页");
        return newPagePager${type}.currentPage;
    }

    $(function () {
        makePage${type}(newPagePager${type}.page, newPagePager${type}.pageCount);
    });

</script>
<!-- 分页区域 end-->