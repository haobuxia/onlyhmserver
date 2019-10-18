<%--
  Created by IntelliJ IDEA.
  User: wenxinyan
  Date: 2018/10/13
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
    <div class="sidebar-scroll">
        <nav>
            <ul id="menuList" class="nav collapsible">
                <li><a href="/console/index" class=""><i class="lnr lnr-home"></i> <span>管理首页</span></a></li>

                <input id="userId" type="text" value="${userInfo.id}" readonly style="display: none" />
            </ul>
        </nav>
    </div>
</div>
<!-- END LEFT SIDEBAR -->

<script>
    var menuA = new Array;
    var menuB = new Array;
    var menuC = new Array;

    $(function () {
        $.post("/rolemenu/menulist",{id:$("#userId").val(),isUserId:true},function(resp){
            if(resp.success){
                menuA = resp.data.menu1;
                menuB = resp.data.menu2;
                menuC = resp.data.menu3;

                $.each(resp.data.menu1,function(idx,m1){
                    var strmenu = 'menu' + idx;
                    var html = '<li><a href="#' + strmenu + '" data-toggle="collapse" class="collapsed"><i class="'+m1.image+'"></i><span>' + m1.name + '</span><i class="icon-submenu lnr lnr-chevron-left"></i></a>'
                        + '<div id="' + strmenu + '" class="collapse ">'
                        + '<ul class="nav">';
                    $.each(resp.data.menu2,function(idx,m2){
                        if(m2.url != null){
                            if(m2.fatherId == m1.id){
                                html += '<li><a data-href="' + m2.url + '"  class="'+m2.image+'"> ' + m2.name + '</a></li>';
                            }
                        } else {

                        }
                    });
                    html += '</ul></div></li>';
                    $("#menuList").append(html);
                });

                $.each($("ul.collapsible li a[data-href]"), function (idx, alink) {
                    var obj = $(alink);
                    var href = obj.attr("data-href");
                    if (href != '#') {
                        obj.css("cursor", "pointer").off('click').on('click', function () {
                            loadMainContent(href);
                        });
                    }
                });
            }
        },"json");
    })
</script>