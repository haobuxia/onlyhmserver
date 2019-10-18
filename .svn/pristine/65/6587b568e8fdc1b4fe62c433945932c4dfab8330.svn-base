package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.controller.interceptor.OperaLogHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.Image;
import com.tianyi.helmet.server.entity.file.UploadEntity;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.log.OperaLog;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.log.OperaLogService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 上传服务工厂类
 * <p>
 * Created by liuhanc on 2017/10/26.
 */
@Component
public class UploadEntityServiceFactory {

    private Logger logger = LoggerFactory.getLogger(UploadEntityServiceFactory.class);

    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;

    @Autowired
    private VideoService videoService;
    @Autowired
    private AudioService audioService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private OperaLogService operaLogService;
    @Autowired
    private UserService userService;

    public ResponseVo  upload(UploadEntityTypeEnum type, MultipartFile file, int userId, String clientId, String neUserName, Date createTime, String machineCode, String description, String tag, String orderNo, Float lon, Float lat, String caller, String called, String source) {
        OperaLog operaLog = new OperaLog();
        if(OperaLogHolder.get() != null)
        {
            operaLog = OperaLogHolder.get();
        }
        else
        {
            operaLog.setCreateTime(LocalDateTime.now());
            operaLog.setClientId("no");
            operaLog.setUUID("fuwuqi-a");
            operaLog.setDeviceType("FWQ");
            operaLog.setLogType("no");
            operaLog.setLogflowId("no");
            operaLog.setOrderNo(null);
            operaLog.setLogDate(LocalDate.now());
            operaLog.setLogTime(LocalDateTime.now());
            operaLog.setLogContent("初始内容");
        }

        operaLogService.addNewLog(operaLog, 1, "开始判断上传文件是否为空", 0);
        if (file.isEmpty()) {
            operaLogService.addNewLog(operaLog, 999, "上传文件为空", 0);
            logger.error("上传的文件是空的.type=" + type + ",clientId=" + clientId + ",createTime=" + createTime);
            return ResponseVo.fail("文件是空的");
        }
        operaLogService.addNewLog(operaLog, 2, "上传文件不为空", 0);

        //检查文件类型
        operaLogService.addNewLog(operaLog, 3, "开始检查上传文件类型", 0);
        String[] supportTypes = getSupportedTypes(type);
        String suffix = null;
        if (supportTypes != null && supportTypes.length > 0) {
            String originalFilename = file.getOriginalFilename();
            logger.debug("file.name=" + file.getName() + ",oriname=" + originalFilename);
            int dotIdx = originalFilename.lastIndexOf(".");
            if (dotIdx == -1) {
                operaLogService.addNewLog(operaLog, 999, "文件扩展名为空", 0);
                return ResponseVo.fail("文件扩展名为空");
            }

            String tempSuffix = originalFilename.substring(dotIdx + 1).toLowerCase();
            suffix = tempSuffix;
            if (!Arrays.stream(supportTypes).filter(t -> t.equalsIgnoreCase(tempSuffix)).findAny().isPresent()) {
                operaLogService.addNewLog(operaLog, 999, "上传文件类型不受支持", 0);
                logger.error("upload post.file type not support");
                return ResponseVo.fail("上传文件类型不受支持." + suffix);
            }
        }
        operaLogService.addNewLog(operaLog, 4, "上传文件类型符合要求", 0);

        //获得文件内容
        operaLogService.addNewLog(operaLog, 5, "开始获取文件内容", 0);
        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (Throwable e) {
            operaLogService.addNewLog(operaLog, 999, "获取上传文件内容失败", 0);
            logger.error("upload post.file get bytes failed.type=" + type + ",clientId=" + clientId + ",createTime=" + createTime);
            return ResponseVo.fail("获取上传文件内容失败.");
        }
        operaLogService.addNewLog(operaLog, 6, "获取上传文件内容成功", 0);

        //保存文件
        operaLogService.addNewLog(operaLog, 7, "开始保存上传文件", 0);
        try {
            /**
             * update by xiayuan 2018/10/10
             */
            ResponseVo vo = uploadEntityServiceFactory.getUploadEntityService(type).addNewFile(bytes, file.getOriginalFilename(), suffix, description, createTime,userId, clientId, neUserName,"upload", machineCode, tag, orderNo,lon,lat,caller,called,source);
            operaLogService.addNewLog(operaLog, 999, "保存上传文件结束", 0);
            return vo;
        } catch (Throwable e) {
            operaLogService.addNewLog(operaLog, 999, "保存上传文件失败", 0);
            logger.error("保存上传文件失败.type=" + type + ",clientId=" + clientId + ",origName=" + file.getOriginalFilename(), e);
            return ResponseVo.fail("保存上传文件失败.type=" + type + ",clientId=" + clientId + ",createTime=" + createTime + ",origName=" + file.getOriginalFilename());
        }
    }

    /**
     * 获得上传文件表单名
     *
     * @param type
     * @return
     */
    public String getUploadFileInputName(String type) {
        if ("video".equals(type)) {
            return "videofile";
        } else if ("image".equals(type)) {
            return "imagefile";
        } else if ("audio".equals(type)) {
            return "audiofile";
        } else if ("file".equals(type)) {
            return "file";
        } else {
            return null;
        }
    }

    /**
     * 获得上传文件支持的类型
     *
     * @param type
     * @return
     */
    public String[] getSupportedTypes(UploadEntityTypeEnum type) {
        String types = null;
        switch (type) {
            case video:
                types = configService.getUploadVideoSupportTypes();
                break;
            case image:
                types = configService.getUploadImageSupportTypes();
                break;
            case audio:
                types = configService.getUploadAudioSupportTypes();
                break;
            case file:
                types = configService.getUploadFileSupportTypes();
                break;
            default:
                types = null;
                break;
        }
        return types.split(",");
    }

    /**
     * 获得某个保存到本地目录的文件
     *
     * @param type
     * @param filePath
     * @return
     */
    public java.io.File getSavedFile(UploadEntityTypeEnum type, String filePath) {
        String subDir = null;
        switch (type) {
            case video:
                subDir = configService.getUploadVideoDir();
                break;
            case image:
                subDir = configService.getUploadImageDir();
                break;
            case audio:
                subDir = configService.getUploadAudioDir();
                break;
            case file:
                subDir = configService.getUploadFileDir();
                break;
            default:
                subDir = null;
                break;
        }
        if (subDir == null) return null;
        java.io.File file = new java.io.File(configService.getUploadFileSaveDir() + subDir + java.io.File.separator + filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    /**
     * 获得类型对应service实例
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T extends UploadEntity> UploadEntityService getUploadEntityService(UploadEntityTypeEnum type) {
        switch (type) {
            case video:
                return videoService;
            case image:
                return imageService;
            case audio:
                return audioService;
            case file:
                return fileService;
            default:
                return null;
        }
    }

    public PageListVo<UploadEntity> listData(UploadEntityTypeEnum type, String searchKey, Object searchVal, int page, int pageSize) {
        PageListVo<UploadEntity> vo = new PageListVo<>();
        UploadEntityService service = uploadEntityServiceFactory.getUploadEntityService(type);

        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        if (!StringUtils.isEmpty(searchKey)) {
            if(searchVal instanceof Map){
                Map<String,String> maps = (Map<String,String>)searchVal;
//                map.put("keyword", maps.get("keyword"));
//                map.put("orderBy", maps.get("orderBy"));
//                map.put("tag",maps.get("tag"));
                for(String key : maps.keySet()){
                    map.put(key, maps.get(key));
                }
            }else{
                map.put(searchKey,searchVal);
            }

        }
        //鉴权
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo == null || userInfo.isAdmin() || userInfo.isDemo()) {
        } else {
            if (userInfo.isCustomer()) {
                map.put("affiliationId", userInfo.getId());
            } else if (userInfo.isSales()) {
                map.put("salerId", userInfo.getId());
            } else if (userInfo.isDriver()) {
                map.put("userId", userInfo.getId());
            }
        }

        List<UploadEntity> list = service.listBy(map);
        for(UploadEntity uploadEntity:list){
            Integer userId = uploadEntity.getUserId();
            if(userId == -1 || userId == 0){
                uploadEntity.setAccount("未知用户");
            }else{
                String account = userService.selectById(userId).getName();
                uploadEntity.setAccount(account);
            }
        }
        int count = service.countBy(map);
        vo.setTotal(count);
        vo.setList(list);

       /* if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(entity -> {
                *//**
                 * update by xiayuan 2018/10/10
                 *//*
                String clientId = entity.getClientId();
                EquipmentLedger helmet = equipmentService.selectByUUID(clientId);
                User user = userService.selectById(helmet.getUserId());
                entity.setNeUserName(helmet == null ? clientId : user.getNeUserHel());
            });
        }*/

        if (UploadEntityTypeEnum.video.equals(type)) {
            vo.getList().stream().forEach(entity -> {
                Video video = (Video) entity;
//        补充经纬度
                videoService.fullfilLonlat(video);
                if (StringUtils.isEmpty(video.getPicOssPath())) {
                    //预览图丢失，则生成
                    videoService.addToTimePreviewQueue(video.getId());
                }
                //预览图缩略图丢失，则生成
                videoService.fullfilThumbOssPath(video);
            });
        } else if (UploadEntityTypeEnum.image.equals(type)) {
            vo.getList().stream().forEach(entity -> {
                Image image = (Image) entity;
//        补充经纬度
                imageService.fullfilLonlat(image);
                //预览图缩略图丢失，则生成
                imageService.fullfilThumbOssPath(image);
            });
        }

        vo.setPage(page);
        vo.setPageSize(pageSize);
        return vo;
    }

    public PageListVo<UploadEntity> listDataByCreateTime(UploadEntityTypeEnum type, String searchKey, Object searchVal, int page, int pageSize) {
        PageListVo<UploadEntity> vo = new PageListVo<>();

        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        if (!StringUtils.isEmpty(searchKey)) {
            map.put(searchKey, searchVal);
        }
        //鉴权
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo == null || userInfo.isAdmin() || userInfo.isDemo()) {
        } else {
            if (userInfo.isCustomer()) {
                map.put("affiliationId", userInfo.getId());
            } else if (userInfo.isSales()) {
                map.put("salerId", userInfo.getId());
            } else if (userInfo.isDriver()) {
                map.put("userId", userInfo.getId());
            }
        }

        List<Video> list = videoService.listByCreateTime(map);
        //测试可用
        List<UploadEntity> list1 = new ArrayList<>();
        for (UploadEntity u : list) {
            list1.add(u);
        }
        vo.setTotal(list1.size());
        vo.setList(list1);

        if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(entity -> {
                /**
                 * update by xiayuan 2018/10/10
                 */
                String clientId = entity.getClientId();
                EquipmentLedger helmet = equipmentService.selectByUUID(clientId);
                User user = userService.selectById(helmet.getUserId());
                entity.setNeUserName(helmet == null ? clientId : user.getNeUserHel());
            });
        }

        if (UploadEntityTypeEnum.video.equals(type)) {
            vo.getList().stream().forEach(entity -> {
                Video video = (Video) entity;
//        补充经纬度
                videoService.fullfilLonlat(video);
                if (StringUtils.isEmpty(video.getPicOssPath())) {
                    //预览图丢失，则生成
                    videoService.addToTimePreviewQueue(video.getId());
                }
                //预览图缩略图丢失，则生成
                videoService.fullfilThumbOssPath(video);
            });
        } else if (UploadEntityTypeEnum.image.equals(type)) {
            vo.getList().stream().forEach(entity -> {
                Image image = (Image) entity;
//        补充经纬度
                imageService.fullfilLonlat(image);
                //预览图缩略图丢失，则生成
                imageService.fullfilThumbOssPath(image);
            });
        }

        vo.setPage(page);
        vo.setPageSize(pageSize);
        return vo;
    }


    /**
     * 获得某个上传数据类型的数据实体
     *
     * @param type
     * @param id
     * @return
     */
    public UploadEntity getEntity(UploadEntityTypeEnum type, int id) {
        UploadEntityService service = uploadEntityServiceFactory.getUploadEntityService(type);
        UploadEntity entity = service.selectById(id);
        if (entity == null)
            return null;

        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo.isAdmin() || userInfo.isDemo()) {
        } else {
            if (userInfo.isCustomer() || userInfo.isSales()) {
                EquipmentLedger helmet = equipmentService.selectByUUID(entity.getClientId());
                if (helmet == null) {
                    return null;
                }
                if (userInfo.isCustomer() && userInfo.getId() != helmet.getAffiliationId())
                    return null;
                /**
                 * update by xiayuan 2018/10/10
                 */
                /*if (userInfo.isSales() && userInfo.getId() != helmet.getSalerId())
                    return null;*/   // TODO: 2018/10/9 销售人员是干嘛的
            } else if (userInfo.isDriver()) {
                int userId = entity.getUserId();
                if (!String.valueOf(userInfo.getId()).equals(userId)) {
                    //不是自己录制的无权查看
                    return null;
                }
            }
        }

        //增加view次数
        service.increaseViewCount(entity.getId());
        entity.setViewCount(entity.getViewCount() + 1);
        return entity;
    }

}
