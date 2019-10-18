<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="/static/js/userUpload.js?v=${version}"></script>
        <br><br>
        <div class="section no-pad-bot">
            <div class="container">
                <h3 class="header">常规上传</h3>
                <div class="row">
                    <form id="addForm" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="termCode" type="text" name="termCode" class="validate" placeholder="输入设备编码" required value="helmet1001">
                                <label for="termCode" class="active">设备编码</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="createTime" type="text" name="createTime" class="validate" placeholder="yyyyMMddhhmmss" required>
                                <label for="createTime" class="active">时间戳</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="description" type="text" name="description"placeholder="描述文字" value="pc端网页上传">
                                <label for="description" class="active">文件描述</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="machineCode" type="text" name="machineCode"placeholder="机号" value="">
                                <label for="machineCode" class="active">机号</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="tag" type="text" name="tag"placeholder="标签文字" value="">
                                <label for="tag" class="active">标签</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="file-field input-field col s12">
                                <div class="btn">
                                    <span>File(${supportTypes})</span>
                                    <input id="uploadFile" type="file" name="${inputName}" placeholder="点击选择文件" required>
                                </div>
                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <a class="waves-effect waves-light btn submit" id="addFileBtn">添加</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="divider"></div>

            <div class="container">
                <h3 class="header">大文件分片上传-初始化</h3>
                <div class="row">
                    <form id="bigFileForm" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile_termCode" type="text" name="bigFile_termCode" class="validate" placeholder="输入设备编码" required value="helmet1001">
                                <label for="bigFile_termCode" class="active">设备编码</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile_createTime" type="text" name="bigFile_createTime" class="validate" placeholder="yyyyMMddhhmmss" required>
                                <label for="bigFile_createTime" class="active">时间戳</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile_fileName" type="text" name="bigFile_fileName" class="validate" placeholder="文件原始名称(.mp4结尾)" required>
                                <label for="bigFile_fileName" class="active">文件名</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile_fileId" type="text" name="bigFile_fileId" class="validate" placeholder="大文件ID唯一识别标志" required>
                                <label for="bigFile_fileId" class="active">文件ID</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile_segmentCount" type="number" name="bigFile_segmentCount" class="validate" placeholder="输入大文件将要分片的数量" required>
                                <label for="bigFile_segmentCount" class="active">分片数量</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile_description" type="text" name="bigFile_description"placeholder="描述文字" value="大文件上传初始化">
                                <label for="bigFile_description" class="active">文件描述</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile_machineCode" type="text" name="bigFile_machineCode"placeholder="机号" value="">
                                <label for="bigFile_machineCode" class="active">机号</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile_tag" type="text" name="bigFile_tag" placeholder="标签文字" value="">
                                <label for="bigFile_tag" class="active">标签</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <a class="waves-effect waves-light btn submit" id="bigFile_initBtn">初始化</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="divider"></div>

            <div class="container">
                <h3 class="header">大文件分片上传-分片</h3>
                <div class="row">
                    <form id="bigFileForm2" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile2_fileId" type="text" name="bigFile2_fileId" class="validate" placeholder="大文件ID唯一识别标志" required value="">
                                <label for="bigFile2_fileId" class="active">文件ID</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="bigFile2_segmentCount" type="text" name="bigFile2_segmentCount" class="validate" placeholder="序号从0开始" required>
                                <label for="bigFile2_segmentCount" class="active">分片序号</label>
                            </div>
                        </div>
                        <div class="file-field input-field col s12">
                            <div class="btn">
                                <span>File</span>
                                <input id="uploadSegment" type="file" name="file" placeholder="点击选择文件" required>
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <a class="waves-effect waves-light btn submit" id="bigFile2_addFileBtn">上传</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
<script>
    var addFilePage = {};
    addFilePage.type= '${type}';
    addFilePage.supportTypes= '${supportTypes}';
</script>
    <script src="/static/js/addFile.js?v=${version}"></script>