package com.tianyi.helmet.server.controller.admin;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.app.ApkFile;
import com.tianyi.helmet.server.entity.app.ApkFileTypeEnum;
import com.tianyi.helmet.server.entity.app.ApkUpdate;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.exception.TransException;
import com.tianyi.helmet.server.service.app.ApkFileService;
import com.tianyi.helmet.server.service.app.ApkUpdateService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.dictionary.VersionService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * apk 文件管理和升级
 * <p>
 * Created by liuhanc on 2017/12/18.
 */
@Controller
@RequestMapping("apk")
public class ApkController {
    private Logger logger = LoggerFactory.getLogger(ApkController.class);

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ApkFileService apkFileService;
    @Autowired
    private ApkUpdateService apkUpdateService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private VersionService versionService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.GET)
    public String fileUploadGet(Model model) {
        List<DoubleVo> fileTypeList = Arrays.stream(ApkFileTypeEnum.values()).map(
                typeEnum -> new DoubleVo<>(typeEnum.getCode(), typeEnum.getName())
        ).collect(Collectors.toList());
        model.addAttribute("fileTypeList", fileTypeList);
        return "apk/addApkFile";
    }

    /**
     * @return
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo fileUploadPost(@RequestParam("apkfile") MultipartFile file, @RequestParam String version, @RequestParam String fileType, @RequestParam String description) {
        String fileName = file.getOriginalFilename();
        int dotIdx = fileName.lastIndexOf(".");
        if (dotIdx == -1) {
            return ResponseVo.fail("文件扩展名为空");
        }

        String fileExt = fileName.substring(dotIdx + 1).toLowerCase();
        if (!"apk".equalsIgnoreCase(fileExt)) {
            return ResponseVo.fail("文件扩展名不是.apk");
        }

        if (ApkFileTypeEnum.valueOf(fileType) == null) {
            return ResponseVo.fail("文件类型不受支持." + fileType);
        }

        //获得文件内容
        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (Throwable e) {
            logger.error("upload apk file get bytes failed.", e);
            return ResponseVo.fail("获取上传文件内容失败.");
        }

        String[] path = fastDfsClient.uploadFile(bytes, fileExt, configService.getFastdfsRetryTimes());
        if (path == null) {
            logger.info("upload post.file save to oss failed.fileName=" + fileName);
            return ResponseVo.fail("保存上传文件失败");
        }

        String ossPath = path[0] + "/" + path[1];
        ApkFile apk = new ApkFile();
        apk.setDescription(description);
        apk.setFileName(fileName);
        apk.setFileType(fileType);
        apk.setOssPath(ossPath);
        apk.setUploadTime(LocalDateTime.now());
        apk.setUploadUserId(LoginUserHolder.get().getId());
        apk.setVersion(version);
        apkFileService.insert(apk);

        if (versionService.getVersion(version) == null) {
            versionService.addVersion(version);
        }

        if (ApkFileTypeEnum.phone.getCode().equals(apk.getFileType())) {
            redisTemplate.delete(CacheKeyConstants.APPVERSION_FORCE+":\"getAppLastForceVersion\"");
            redisTemplate.delete(CacheKeyConstants.APPVERSION_OPTIONAL+":\"getAppLastOptionalVersion\"");
        }
        return ResponseVo.success();
    }

    /**
     * 进入添加升级文件配置界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/set", method = RequestMethod.GET)
    public String updateGet(Model model) {
        List<ApkFile> apkList = apkFileService.listBy(PageListVo.createParamMap(1, 20));//最新20个
        /**
         * update by xiayuan 2018/10/10
         */
        List<EquipmentLedger> helmetList = equipmentService.selectByType(true);
        helmetList = equipmentService.fullfilCustomerNeUser(helmetList);
        model.addAttribute("apkList", apkList);
        model.addAttribute("helmetList", helmetList);
        return "apk/addApkUpload";
    }

    /**
     * @return
     */
    @RequestMapping(value = "/update/set", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo updatePost(@RequestParam String imei, @RequestParam Integer apkId) {
        ApkFile apkFile = apkFileService.selectById(apkId);
        if (apkFile == null) {
            return ResponseVo.fail("apk文件不存在");
        }

        if ("-1".equals(imei)) {
            this.addAllUpdate(apkFile);
        } else {
            /**
             * update by xiayuan 2018/10/11
             */
            EquipmentLedger helmet = equipmentService.selectByUUID(imei);
            if (helmet == null) {
                return ResponseVo.fail("设备不存在:" + imei);
            }
            apkUpdateService.addNewUpdate(imei, apkFile);
        }

        return ResponseVo.success();
    }

    @Transactional
    void addAllUpdate(ApkFile apkFile) {
        //全部都升级
        /**
         * update by xiayuan 2018/10/10
         */
        List<EquipmentLedger> helmetList = equipmentService.selectByType(true);
        helmetList.stream().forEach(helmet -> {
            apkUpdateService.addNewUpdate(helmet.getDeviceUUID(), apkFile);
        });
    }

    /**
     * @return
     */
    @RequestMapping(value = "/file/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo deleteFile(@PathVariable Integer id) throws TransException {
        if (id == null)
            return ResponseVo.fail("数据不存在");

        ApkFile file = apkFileService.selectById(id);
        if (file == null)
            return ResponseVo.fail("数据不存在");

        Map<String, Object> map = new HashMap<>(1);
        map.put("apkId", id);
        int cnt = apkUpdateService.countBy(map);
        if (cnt > 0) {
            return ResponseVo.fail("使用该文件的头盔有" + cnt + "个,不可删除");
        }

        boolean success = apkFileService.delete(file);
        return success ? ResponseVo.success() : ResponseVo.fail("删除数据失败." + cnt);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/update/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo deleteUpdate(@PathVariable Integer id) {
        if (id == null)
            return ResponseVo.fail("数据不存在");

        ApkUpdate update = apkUpdateService.selectById(id);
        if (update != null) {
            apkUpdateService.deleteById(update);
            return ResponseVo.success();
        }
        return ResponseVo.fail("删除失败，数据不存在.");
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/file/list")
    public String fileList(Model model) {
        return fileList(1, null, model);
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/file/list/{page}")
    public String fileList(@PathVariable Integer page, String keyword, Model model) {
        PageListVo<ApkFile> vo = apkFileService.list(keyword, page);
//        userService.fullfilUserName(vo.getList(), ApkFile::getUploadUserId, ApkFile::setUploadUserName);
        for(ApkFile apkFile : vo.getList()){
            apkFile.setUploadUserName(userService.selectById(apkFile.getUploadUserId()).getName());
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("vo", vo);
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        return "apk/listApkFile";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/update/list")
    public String updateList(Model model) {
        return updateList(1, null, model);
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/update/list/{page}")
    public String updateList(@PathVariable Integer page, String imei, Model model) {
        PageListVo<ApkUpdate> vo = apkUpdateService.list(imei, page);
//        userService.fullfilUserName(vo.getList(), ApkUpdate::getCreateUserId, ApkUpdate::setCreateUserName);
        for(ApkUpdate apkUpdate : vo.getList()){
            apkUpdate.setCreateUserName(userService.selectById(apkUpdate.getCreateUserId()).getName());
            apkUpdate.setClientNumber(equipmentService.selectByUUID(apkUpdate.getClientId()).getDeviceNumber());
        }
        apkUpdateService.fullfilApkFile(vo.getList());
        model.addAttribute("imei", imei);
        model.addAttribute("vo", vo);
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        return "apk/listApkUpdate";
    }

}
