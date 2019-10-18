<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
    <div class="panel clearfix">
        <div class="panel-heading">
            <h3 class="panel-title">excel上传</h3>
        </div>
        <div class="panel-body col-md-6" id="searchSection">
            <a href="/static/template/videoexcel.template.xlsx" target="_blank">下载excel模板</a>
            <form action="/videoexcel/upload" method="post" enctype="multipart/form-data">
                <div class="input-group" id="searchDiv">
                    <input type="file" name="excel" placeholder="选择视频excel" class="form-control" value="">
                    <span class="input-group-btn"><button class="btn btn btn-primary" type="submit"
                                                          title="点击上传">
                                    <i class="fa fa-upload"></i>
                                </button></span>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- END MAIN -->
<div class="clearfix"></div>
<!-- END WRAPPER -->