<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
        
        <div class="panel clearfix">
            <div class="panel-heading">
                <h3 class="panel-title"></h3>
            </div>
            
            <div class="panel-body col-md-6"  id="searchSection">
                <div class="input-group" id="searchDiv">
                    <input type="text" id="keyword"  placeholder="请输入文件名或描述文字搜索" class="form-control" value="${keyword}">
                    <span class="input-group-btn"><button class="btn btn btn-primary" type="submit" id="search-button" title="点击搜索">
                                    <i class="fa fa-search"></i>
                                </button></span>
                    <span class="input-group-btn"><button class="btn btn btn-warning" type="submit" id="add-button" title="新增">
                                <i class="fa fa-plus"></i>
                            </button></span>
                </div>
            </div>
        </div>

        <div class="panel" id="contentSection">
            <div class="panel-heading">
                <h3 class="panel-title">APK发布</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>文件名</th>
                        <th>类型</th>
                        <th>版本号</th>
                        <th>上传时间</th>
                        <th>上传人</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="apk" items="${vo.list}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${apk.fileName}</td>
                            <td>${apk.fileTypeName}</td>
                            <td>${apk.version}</td>
                            <td>${apk.getUploadTimeString()}</td>
                            <td data-userid="${apk.uploadUserId}">${apk.uploadUserName}</td>
                            <td style="max-width: 400px;">${apk.description}</td>
                            <td style="min-width: 100px;padding-left: 10px;">
                                <a onclick="deleteFile(${apk.id})" class="label label-danger" title="删除该文件">删除</a>
                                <a href="${fileServer}${apk.ossPath}" class="label label-success" target="_blank">下载</a>
                                <!--
                                <a href="javascript:loadUpdateList(${apk.id})">推送记录</a> -->
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

                <jsp:include page="../include/new-page-pager.jsp"></jsp:include>
        </div>




</div>
    <script>
        var listApkFilePage = {};
        listApkFilePage.keyword = '${keyword}';
    </script>
    <script src="/static/js/listApkFile.js?v=${version}"></script>
