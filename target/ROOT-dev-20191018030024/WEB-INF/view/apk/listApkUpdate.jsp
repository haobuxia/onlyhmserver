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
                    <input type="text" id="keyword"  placeholder="请输入头盔设备ID查询头盔升级记录" class="form-control" value="${imei}">
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
                        <th>头盔编号</th>
                        <th>设置时间</th>
                        <th>文件名</th>
                        <th>文件类型</th>
                        <th>版本号</th>
                        <th>文件描述</th>
                        <th>设置人</th>
                        <th>升级状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="apk" items="${vo.list}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${apk.clientNumber}</td>
                            <td>${apk.getCreateTimeString()}</td>
                            <td>${apk.apkFile.fileName}</td>
                            <td>${apk.apkFile.getFileTypeName()}</td>
                            <td>${apk.apkFile.version}</td>
                            <td style="max-width:400px;">${apk.apkFile.description}</td>
                            <td data-userid="${apk.createUserId}">${apk.createUserName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${apk.status==0}">
                                        未升级
                                    </c:when>
                                    <c:when test="${ apk.status==1}">
                                        已跳过
                                    </c:when>
                                    <c:when test="${ apk.status==2}">
                                        已升级
                                    </c:when>
                                    <c:otherwise>
                                        其他
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a onclick="delUpdate(${apk.id})" class="label label-danger" title="删除该升级设置">删除</a>
                                <a href="${fileServer}${apk.apkFile.ossPath}" class="label label-success" target="_blank">下载</a>
                                <a href="/appupdate/${apk.apkFile.fileType}/version.xml?clientId=${apk.clientId}" class="label label-success" target="_blank">升级文件</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
                <jsp:include page="../include/new-page-pager.jsp"></jsp:include>

        </div>

    <script>
        var listApkUpdatePage = {};
        listApkUpdatePage.imei = '${imei}';
    </script>
    <script src="/static/js/listApkUpdate.js?v=${version}"></script>
