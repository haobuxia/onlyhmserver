package com.tianyi.helmet.server.controller.client;

import com.tianyi.helmet.server.entity.app.ApkFile;
import com.tianyi.helmet.server.entity.app.ApkFileTypeEnum;
import com.tianyi.helmet.server.entity.app.ApkUpdate;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.app.ApkFileService;
import com.tianyi.helmet.server.service.app.ApkUpdateService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.dictionary.VersionService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 头盔获取升级信息
 *
 * <p>
 * Created by liuhanc on 2017/11/2.
 */
@Controller
@RequestMapping("appupdate")
@Api(value = "api", description = "头盔app升级管理")
public class AppUpdateController {

    private Logger logger = LoggerFactory.getLogger(AppUpdateController.class);
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ApkUpdateService apkUpdateService;
    @Autowired
    private ApkFileService apkFileService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private VersionService versionService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/{apkFileType}/version.xml")
    /**
     * 2018/10/9 @RequestParam(name = "clientId") String neUserName 改成deviceUUID
     * update by xiayuan
      */
    public void version(@PathVariable String apkFileType, @RequestParam(name = "clientId") String deviceUUID, HttpServletResponse resp) throws IOException {
        ApkFileTypeEnum typeEnum = ApkFileTypeEnum.valueOf(apkFileType);
        if (typeEnum == null) {
            resp.getWriter().write("无法识别的类型:" + apkFileType);
            return;
        }
        if (StringUtils.isEmpty(deviceUUID)) {
            resp.getWriter().write("设备ID不能为空:" + deviceUUID);
            return;
        }
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(deviceUUID);
        if (equipmentLedger == null) {
            resp.getWriter().write("设备不存在:" + deviceUUID);
            return;
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml; charset=utf-8");
        ApkUpdate apkUpdate = apkUpdateService.getLatestByClientIdFileType(deviceUUID, typeEnum.getCode());
        StringBuffer sb = new StringBuffer();
        sb.append("<update>\n");
        sb.append(createVersionXmlContent(apkUpdate));
        sb.append("</update>");
        resp.getWriter().write(sb.toString());
    }

    @RequestMapping(value = "/{apkFileType}/notice", method = RequestMethod.POST)
    /**
     * 2019/03/26
     * @RequestParam(name = "clientId") String
     *
     * created by tianxujin
     */
    @ApiOperation(value = "更新设备版本号信息")
    @Transactional
    @ResponseBody
    public ResponseVo notice(@PathVariable String apkFileType, @RequestParam(name = "clientId") String deviceUUID, @RequestParam(name = "version") String version) {
        ApkFileTypeEnum typeEnum = ApkFileTypeEnum.valueOf(apkFileType);
        ResponseVo vo = null;
        if (typeEnum == null) {
            vo = ResponseVo.fail("无法识别的类型:" + apkFileType);
            return vo;
        }
        if (StringUtils.isEmpty(deviceUUID)) {
            vo = ResponseVo.fail("设备ID不能为空:" + deviceUUID);
            return vo;
        }
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(deviceUUID);
        if (equipmentLedger == null) {
            vo = ResponseVo.fail("设备不存在:" + deviceUUID);
            return vo;
        }
        int versionId = versionService.getVersion(version);
        equipmentLedger.setVersionId(versionId);
        equipmentService.update(equipmentLedger);

        ApkUpdate apkUpdate = apkUpdateService.getLatestByClientIdFileType(deviceUUID, typeEnum.getCode());
        apkUpdate.setStatus(2);// 已升级
        apkUpdate.setUpdateTime(LocalDateTime.now());
        apkUpdateService.updateById(apkUpdate);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("clientId", deviceUUID);
        params.put("ustatus", 1);
        params.put("status", 0);
        apkUpdateService.updateStatusByImeiAndStatus(params);
        vo = ResponseVo.success("更新成功！");
        return vo;
    }

    private String createVersionXmlContent(ApkUpdate apkUpdate) {
        StringBuffer sb = new StringBuffer();
        if (apkUpdate == null) {
            sb.append("        <errorCode>400</errorCode>\n");
            sb.append("        <errorMsg>未配置升级设置</errorMsg>\n");
        } else {
            ApkFile apkFile = apkFileService.selectById(apkUpdate.getApkId());
            if (apkFile == null) {
                sb.append("        <errorCode>404</errorCode>\n");
                sb.append("        <errorMsg>升级设置对应文件信息丢失</errorMsg>\n");
            } else {
                sb.append(
                        "        <version>" + apkFile.getVersion() + "</version>\n" +
                                "        <name>" + apkFile.getFileName() + "</name>\n" +
                                "        <url>" + configService.getFastdfsServerUrl() + apkFile.getOssPath() + "</url>\n");
            }
        }
        return sb.toString();
    }
    /**
     * 手机获取当前版本号
     * zhouwei
     * @param version 当前手机版本
     * @returnle
     */
    @PostMapping("phone/lastversion")
    @ApiOperation("查询手机最新版本")
    @ResponseBody
    public ResponseVo getAppVersion(@ApiParam(value = "APP当前版本号", required = true) @RequestParam String version) {
        logger.info("获取手机版本信息,当前手机版本:" + version);
        //校验手机版本，格式01.00.01
        StringBuffer sb = new StringBuffer();
        for (String s : version.split("\\.")) {
            while (s.length() < 2) {
                s="0"+s;
            }
            sb.append(s.substring(0, 2)+".");
        }
        version = sb.substring(0, sb.length() - 1);
        if (logger.isDebugEnabled()) {
            logger.debug("整理后的版本号:" + version);
        }

        Map<String, Object> respInfo = new HashMap<>();
        ApkFile forceApkFile = apkFileService.getAppLastForceVersion();
        if (forceApkFile != null) {
            Map<String, Object> forceVersionInfo = buildVersionInfo(forceApkFile);
            if(newVersion(version, forceApkFile.getVersion())) {
                respInfo.put("force", forceVersionInfo);
            }
        }
        ApkFile optionalApkFile = apkFileService.getAppLastOptionalVersion();
        if (optionalApkFile != null) {
            Map<String, Object> optionalVersionInfo = buildVersionInfo(optionalApkFile);
            if(newVersion(version, optionalApkFile.getVersion())) {
                respInfo.put("optional", optionalVersionInfo);
            }
        }
        return ResponseVo.success(respInfo);
    }

    private Map<String, Object> buildVersionInfo(ApkFile apkFile) {
        Map<String, Object> versionInfo = new HashMap<>();
        versionInfo.put("path", apkFile.getOssPath());
        versionInfo.put("version", apkFile.getVersion());
        versionInfo.put("fileName", apkFile.getFileName());
        versionInfo.put("desc", apkFile.getDescription());
        return versionInfo;
    }

    private boolean newVersion(String currentVersion, String lastVersion) {
        if(Integer.valueOf(lastVersion.replace(".","")) >
                Integer.valueOf(currentVersion.replace(".", ""))){
            return true;
        }
        return false;
    }
}
