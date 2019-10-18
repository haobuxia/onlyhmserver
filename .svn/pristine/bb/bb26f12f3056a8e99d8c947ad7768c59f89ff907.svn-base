<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <script src="/static/js/userUpload.js?v=${version}"></script>
     
<div class="main-content" id="searchSection">
    <div class="panel clearfix" id="searchDiv">
        <div class="panel-heading">
            <h3 class="panel-title">添加apk文件</h3>
        </div>
         <form id="addForm" class="panel-body col-md-6 col-sm-6">
            <div class="input-group">
                <input id="version" type="text" name="version" class="form-control" placeholder="版本号" required value="">
                <span class="input-group-addon">版本号</span>
            </div>
            <div class="input-group">               
                <select id="fileType" class="form-control" >
                    <option value="" disabled>选择文件类型</option>
                    <c:forEach items="${fileTypeList}" var="fileType">
                        <option value="${fileType.key}">${fileType.val}</option>
                    </c:forEach>
                </select>
                 <span class="input-group-addon">选择文件类型</span>
            </div>
           
            <div style="margin-bottom: 8px;">
            <h5>文件描述</h5>
            <textarea id="description" class="form-control" placeholder="版本升级信息" ></textarea>
             </div>  
               
            
            <label class="input-group" for="thisfile">
                <span class="form-control"  id="changefile">点击选择文件</span>
                <input type="file" name="apkfile" placeholder="点击选择文件" class="form-control" style="display: none; " id="thisfile" onchange="changeFile()">
                <span class="input-group-addon">File(apk)</span>
            </label>               
            
           
            <input type="text" id="service_rwh" placeholder="请输入工单号" class="form-control" value="">              
           
            <div class="clearfix" style="margin-top: 8px;">
                <button class="btn btn-default pull-right" type="submit"  id="cancelApkBtn">
                    取消
                </button>
                <button class="btn btn-primary pull-right" type="submit"  id="addApkBtn" style="margin: 0 5px;">
                    确定
                </button>
            </div>              
            
        </form>
    </div>
</div>
    <script>
        var  changeFile = function(){
            $("#changefile").text($("#thisfile").val());
        }
    </script>
    <script src="/static/js/addApkFile.js?v=${version}"></script>
