package com.tianyi.helmet.server.controller.helmetinterface;

import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.interceptor.OperaLogHolder;
import com.tianyi.helmet.server.entity.file.BigFile;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.service.file.BigFileService;
import com.tianyi.helmet.server.service.file.UploadEntityServiceFactory;
import com.tianyi.helmet.server.service.log.OperaLogService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 文件上传
 * Created by liuhanc on 2017/10/9.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private BigFileService bigFileService;
    @Autowired
    private OperaLogService operaLogService;


    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> upload(@RequestParam("file") MultipartFile file,
                                     @RequestParam(value = "createTime") Date createTime, String machineCode, String description, String tag, @RequestParam("neUserName") String neUserName, HttpServletRequest request) {
        /**
         * update by xiayuan 2018/10/10
         */
        if (OperaLogHolder.get() != null) {
            operaLogService.addNewLog(OperaLogHolder.get(), 0, "调用接口/file/upload成功", 0);
        }

        String helmetImei = HelmetImeiHolder.get();
        int userId = request.getIntHeader("userId");
        logger.info("file upload post.helmetImei=" + helmetImei + ",description=" + description + ",createTime=" + createTime);
        ResponseVo vo = uploadEntityServiceFactory.upload(UploadEntityTypeEnum.file, file, userId, helmetImei, neUserName, createTime, machineCode, description, tag, null, null, null, "", "", "");
        return vo;
    }

    /**
     * 大文件上传初始化
     *
     * @return
     */
    @RequestMapping(value = "/upload/bigfile/init", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> bigFileUploadInit(@RequestParam(value = "createTime") Date createTime,
                                                @RequestParam String fileName, @RequestParam String fileId, @RequestParam int segmentCount,
                                                String machineCode, String description, String tag) {
        String helmetImei = HelmetImeiHolder.get();

        logger.info("file upload bigfile init.clientId=" + helmetImei + ",fileId=" + fileId + ",createTime=" + createTime + ",machineCode=" + machineCode + ",tag=" + tag);
        int dotIdx = fileName.lastIndexOf(".");
        String suffix = fileName.substring(dotIdx + 1).toLowerCase();
        if (StringUtils.isEmpty(suffix)) {
            return ResponseVo.fail("文件扩展名为空");
        }
        if (!"mp4".equalsIgnoreCase(suffix)) {
            return ResponseVo.fail("只支持mp4文件");
        }

        BigFile file = new BigFile();
        file.setCreateTime(Dates.toLocalDateTime(createTime));
        file.setUploadTime(LocalDateTime.now());
        file.setClientId(helmetImei);
        file.setFileName(fileName);
        file.setDescription(description);
        file.setTag(tag);
        file.setFileType(suffix);
        file.setSegmentCount(segmentCount);
        file.setMachineCode(machineCode);
        file.setFileId(fileId);

        bigFileService.initNewBigFile(file);

        return ResponseVo.success();
    }

    /**
     * 大文件上传分片
     *
     * @param fileId
     * @param segmentCount
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload/bigfile/segment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> bigFileUploadSegment(@RequestParam String fileId, @RequestParam int segmentCount,
                                                   @RequestParam("file") MultipartFile file) {
        logger.info("file upload bigfile segment.fileId=" + fileId + ",segmentCount=" + segmentCount);
        try {
            ResponseVo vo = bigFileService.addFileSegment(fileId, segmentCount, file.getBytes());
            return vo;
        } catch (Exception e) {
            logger.error("保存分片文件异常", e);
            return ResponseVo.fail("失败;" + e.getMessage());
        }
    }

}
