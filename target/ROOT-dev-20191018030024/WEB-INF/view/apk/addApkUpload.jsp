<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


                <div class="panel">

                        <form id="addForm" method="post" class="panel-body col-md-6 col-sm-6">
                            <div class="input-group">
                                    <select  class="form-control" id="clientId">
                                        <option value="" disabled selected>选择头盔</option>
                                        <option value="-1" >全部</option>
                                        <c:forEach items="${helmetList}" var="helmet">
                                            <option value="${helmet.deviceUUID}">${helmet.affiliationName}-${helmet.deviceNumber}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="clientId" class="input-group-addon">选择头盔</label>
                            </div>
                            <div class="input-group">
                                    <select  class="form-control" id="apkId">
                                        <option value="" disabled selected>选择apk</option>
                                        <c:forEach items="${apkList}" var="apk">
                                            <option value="${apk.id}" title="${apk.description}">${apk.getFileTypeName()}-${apk.version}-${apk.fileName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="apkId" class="input-group-addon">选择文件</label>
                            </div>
                             <div class="clearfix" style="margin-top: 8px;">
                                <button class="btn btn-default pull-right" type="submit"  id="cancelApkBtn">
                                    取消
                                </button>
                                <button class="btn btn-primary pull-right" type="submit"  id="addUpdateBtn" style="margin: 0 5px;">
                                    确定
                                </button>
                            </div>   
                        </form>

                </div>
    <script src="/static/js/addApkUpdate.js?v=${version}"></script>