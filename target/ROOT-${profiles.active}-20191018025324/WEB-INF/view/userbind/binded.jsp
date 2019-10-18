<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <!-- CSS  -->
    <link href="/static/materialize/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="/static/materialize//css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <title>田一智能头盔-用户设置成功</title>

    <script type="text/javascript" src="/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="/static/materialize/js/materialize.min.js"></script>
    <script type="text/javascript" src="/static/js/common.js?v=${version}"></script>

    <script type="text/javascript">

        var doExit = function () {
            if(confirm("确定要退出吗？")){
                var helmetId = $("#helmetId").val();
                $.post("/helmetqr/unbind",{"helmetId":helmetId},function(resp){
                    if(resp.success){
                        showAlert('退出成功');
                        location.href="/helmetqr/code/"+helmetId;
                    }else{
                        showAlert(resp.message);
                    }
                },"json");
            }
            return false;
        }
    </script>

</head>

<body>
    <div class="section no-pad-bot">
        <div class="container">
            <div class="row">
                <div class="col">
                    <span class="black-text">您已绑定了用户</span>
                </div>
            </div>
            <div class="row">
                <div class="col s12 m6 l6">
                    <input type="hidden" name="helmetId" id="helmetId" value="${helmetId}" readonly />
                    <span class="black-text">当前用户：</span>
                </div>
                <div class="col s12 m6 l6">
                    <input type="text" name="userName" id="userName" value="${userName}" readonly disabled/>
                </div>
                <div class="col s12 m6 l6">
                    <span class="black-text">手机号：</span>
                </div>
                <div class="col s12 m6 l6">
                    <input type="number" name="userPhone" id="userPhone" value="${userPhone}"  maxlength="11" readonly disabled/>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <button class="btn waves-effect waves-light" id="bindButton" onclick="return doExit()" style="cursor: pointer;">退出</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>