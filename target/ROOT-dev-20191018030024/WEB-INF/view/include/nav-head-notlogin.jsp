<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="masthead">       
    <div class="container" role="navigation">

        <div class="navbar-header">
          <h3  class="masthead-brand"><a id="logo-container"><img src="/static/images/tianyikeji.png" alt="田一科技" style="height: 24px;margin-top: -13px;"></a></h3> </div>
        <div class="collapse navbar-collapse">
            <ul id="nav-pc" class="nav navbar-nav navbar-right masthead-nav">
                <li><a href="javascript:loadLoginForm()">登录•Login</a></li>
            </ul>
        </div>        
       
    </div>
</div>

<script src="/static/js/notlogin.js?v=${version}"></script>