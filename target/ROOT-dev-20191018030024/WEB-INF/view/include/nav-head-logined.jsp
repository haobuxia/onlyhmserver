<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="masthead">       
    <div class="container" role="navigation">

        <div class="navbar-header">
          <h3  class="masthead-brand"><a id="logo-container"><img src="/static/images/tianyikeji.png" alt="田一科技" style="height: 24px;margin-top: -13px;"></a></h3>
        </div>
        <div class="collapse navbar-collapse">
            <ul id="nav-pc" class="nav navbar-nav navbar-right masthead-nav">
                <li><a href="/console/index" title="管理首页">${userInfo.username}</a></li>
                <li><a class="fa fa-power-off" title="注销" data-toggle="modal" data-target="#modal_logout"><!-- 退出 --></a></li>
               <!--  <li><a href="/console/index" title="管理">管理</a></li> -->
            </ul>
        </div>        
       
    </div>
</div>


<!-- 模态框（Modal）  注销登录 -->
<div class="modal fade" id="modal_logout" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" style="color: #000;font-size: 20px;">
                你要注销登录吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <a href="/logout" class="btn btn-warning">
                    注销
                </a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- END WRAPPER -->