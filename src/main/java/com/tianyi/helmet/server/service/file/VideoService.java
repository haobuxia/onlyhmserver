package com.tianyi.helmet.server.service.file;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.controller.interceptor.OperaLogHolder;
import com.tianyi.helmet.server.dao.file.*;
import com.tianyi.helmet.server.dao.scene.WorkOrderDao;
import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.*;
import com.tianyi.helmet.server.entity.log.OperaLog;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrder;
import com.tianyi.helmet.server.service.client.HelmetUniversalService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.log.OperaLogService;
import com.tianyi.helmet.server.service.rabbitmq.MQProducer;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanException;
import com.tianyi.helmet.server.service.tianyuan.TianYuanMapApiHelper;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import com.tianyi.helmet.server.vo.TripleVo;
import com.tianyi.helmet.server.vo.rabbitmq.RabbitMqVo;
import org.apache.commons.fileupload.util.Streams;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 视频服务
 *
 * @author liuhan
 * @since 1.0
 */
@Service
public class VideoService extends HelmetShotService<Video> {

    @Autowired
    private VideoDao videoDao;
    @Autowired
    private RedisMqPublisher redisMqPublisher;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private ConfigService configService;
    @Autowired
    private TagResourceService tagResourceService;
    @Autowired
    private GetOrCreateComponent getOrCreateComponent;
    @Autowired
    private TagGroupService tagGroupService;
    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private WorkOrderDao workOrderDao;
    @Autowired
    private OperaLogService operaLogService;
    @Autowired
    private VideoOrderDao videoOrderDao;
    @Autowired
    private VideoMessageDao videoMessageDao;
    @Autowired
    private VideoLocationDao videoLocationDao;
    @Autowired
    private MQProducer mqProducer;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private HelmetUniversalService helmetUniversalService;
    @Autowired
    private TianYuanMapApiHelper tianYuanMapApiHelper;

    private Logger logger = LoggerFactory.getLogger(VideoService.class);

    public void addToProcessQueue(int id) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_Video_Process, String.valueOf(id));//加入视频处理队列
    }

    public void addToAsrProcessQueue(int id) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_Video_Asr_Process, String.valueOf(id));//加入视频处理队列
    }

    public void addToKeyWordProcessQueue(int id) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_Video_KeyWord_Process, String.valueOf(id));//加入关键词处理队列
    }

    public void addToTimePreviewQueue(int id) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_Video_TimePreview, String.valueOf(id));//加入视频处理队列
    }

    public void addToTrackQueue(int id) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_Video_AddTrack, String.valueOf(id));//加入视频处理队列
    }

    public void addToGpsDataSyncQueue(int id) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_SyncGpsData, String.valueOf(id));//加入视频处理队列
    }

    @Override
    public HelmetShotDao getDao() {
        return videoDao;
    }

    @Override
    public void insert(UploadEntity uploadEntity) {
        logger.debug("插入之前是否存在ID:" + uploadEntity.getId());
        int i = videoDao.insert((Video) uploadEntity);
        logger.debug("是否插入数据:" + i);
        logger.debug("插入之后是否存在ID:" + uploadEntity.getId());
        addToProcessQueue(uploadEntity.getId());
    }

    @Override
    @Transactional
    public void insert(UploadEntity uploadEntity, String tagName) {
        Video video = (Video) uploadEntity;
        insert(video);
        Tag tag = null;
        if (!StringUtils.isEmpty(tagName)) {
            if (tagName.contains(",")) {
                for (String tagName1 : tagName.split(",")) {
                    tag = getOrCreateComponent.getOrCreateByTagName(tagName1);
                    if (tag != null) {
                        tagResourceService.insert(UploadEntityTypeEnum.video, video.getId(), tag);
                    }
                }
            } else {
                tag = getOrCreateComponent.getOrCreateByTagName(tagName);
                if (tag != null) {
                    tagResourceService.insert(UploadEntityTypeEnum.video, video.getId(), tag);
                }
            }
            /* Integer groupId = tag.getGroupId();
           if (groupId != null) {
                if (tagGroupService.getRecIrcDataGroupId() == groupId.intValue()) {
                    video.setVideoType(VideoCategoryEnum.RECIRC.toString().toLowerCase());
                } else if (tagGroupService.getSvcDataGroupId() == groupId.intValue()) {
                    video.setVideoType(VideoCategoryEnum.SVCDATA.toString().toLowerCase());
                }
            }*/
        }
    }

    @Override
    @Cacheable(value = CacheKeyConstants.VIDEO_BY_ID, key = "#id.toString()", unless = "#result == null")
    public Video selectById(int id) {
        return videoDao.selectById(id);
    }

    //查询二手机视频
    public PageListVo<Video> listRecircVideoList(int page, int pageSize, String machineCode, Date date1, Date date2) {
        Map<String, Object> params = PageListVo.createParamMap(page, pageSize);
        params.put("videoType", VideoCategoryEnum.RECIRC.toString().toLowerCase());//小写 二手机
        params.put("machineCode", machineCode);
        params.put("createTime1", date1);
        params.put("createTime2", date2);
        List<Video> list = this.listBy(params);
        int total = this.countBy(params);
        PageListVo<Video> vo = new PageListVo<>(page, pageSize);
        vo.setList(list);
        vo.setTotal(total);
        return vo;
    }

    @Override
    public List<Video> listBy(Map params) {
        return (List<Video>) videoDao.listBy(params);
    }

    public List<Video> listByCreateTime(Map<String, Object> params) {
        return videoDao.listByCreateTime(params);
    }

    @Override
    public int countBy(Map params) {
        return videoDao.countBy(params);
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#id.toString()")
    public int increaseViewCount(int id) {
        return videoDao.increaseViewCount(id);
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#id.toString()")
    @Transactional
    public int deleteById(int id) {
        tagResourceService.deleteByResTypeResId(UploadEntityTypeEnum.video.getId(), id);
        return videoDao.deleteById(id);
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#id.toString()")
    public int updateMachineCode(int id, String machineCode) {
        return super.updateMachineCode(id, machineCode);
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#id.toString()")
    public int updateLonLat(int id, Float[] lonlat) {
        return super.updateLonLat(id, lonlat);
    }

    //更新视频处理状态
    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#id.toString()")
    public int updateState(int id, VideoStateEnum state) {
        Map<String, Object> params = new HashMap();
        params.put("state", state.getState());// not proc
        params.put("id", id);// not proc
        return videoDao.updateStateById(params);
    }

    /**
     * 更新是否有车载盒子数据，以及盒子的imei
     *
     * @return
     */
    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#video.id.toString()")
    public int updateHasGpsDataById(Video video) {
        Map<String, Object> params = new HashMap();
        params.put("hasGpsData", video.getHasGpsData());
        params.put("imei", video.getImei());
        params.put("id", video.getId());
        return videoDao.updateHasGpsDataById(params);
    }

    /**
     * 更新内嵌字幕版视频路径
     *
     * @return
     */
    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#id.toString()")
    public int updateTrackVideoOssPathById(String trackVideoOssPath, int id) {
        Map<String, Object> params = new HashMap();
        params.put("trackVideoOssPath", trackVideoOssPath);
        params.put("id", id);// not proc
        return videoDao.updateTrackVideoOssPathById(params);
    }

    /**
     * 设置视频处理结果
     *
     * @param id
     * @param state
     * @param ossPath
     * @return
     */
    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#id.toString()")
    public int setProcessResult(int id, VideoStateEnum state, String ossPath, Long sizeKb) {
        Video video = new Video();
        video.setOssPath(ossPath);
        if (sizeKb != null)
            video.setSizeKb(sizeKb);
        video.setState(state.getState());
        video.setId(id);
        return videoDao.updateById(video);
    }

    @CacheEvict(value = CacheKeyConstants.VIDEO_BY_ID, key = "#video.id.toString()")
    public int updateById(Video video) {
        return videoDao.updateById(video);
    }

    /**
     * 按头盔imei分组查询视频数量
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageListVo<TripleVo<String, Integer, String>> groupByHelmetImei(Integer page, Integer pageSize) {
        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        List<TripleVo<String, Integer, String>> list = videoDao.groupByHelmetImei(map).stream()
                .map(vo -> {
                    return new TripleVo<>(vo.getKey(), vo.getVal(), vo.getKey());//@todo helmet.clinetId转换成头盔名称
                }).collect(Collectors.toList());
        int total = videoDao.selectClientCount();

        PageListVo vo = new PageListVo(page, pageSize);
        vo.setList(list);
        vo.setTotal(total);
        return vo;
    }

    /**
     * 按头盔账号分组查询视频数量
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageListVo<TripleVo<String, Integer, String>> groupByHelmetAccount(Integer page, Integer pageSize) {
        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        List<TripleVo<String, Integer, String>> list = videoDao.groupByNeUsername(map).stream()
                .map(vo -> {
                    Long val = vo.getVal();
                    return new TripleVo<String, Integer, String>(vo.getKey(), val.intValue(), vo.getKey());
                }).collect(Collectors.toList());
        int total = videoDao.selectClientCount();

        PageListVo vo = new PageListVo(page, pageSize);
        vo.setList(list);
        vo.setTotal(total);
        return vo;
    }

    /**
     * 按imei分组查询视频数量
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageListVo<TripleVo<String, Integer, String>> groupByImei(Integer page, Integer pageSize) {
        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        map.put("groupImei", "1");
        List<TripleVo<String, Integer, String>> list = videoDao.groupByTyBoxImei(map).stream()
                .map(vo -> {
                    String vclName = vo.getKey();
//                    //@todo // imei转成机器名
//                    VclInfo vclInfo = imeiService.getVclInfoByImei(vo.getKey());
//                    if(vclInfo != null) vclName = vclInfo.toString();
                    return new TripleVo<>(vo.getKey(), vo.getVal(), vclName);
                }).collect(Collectors.toList());
        int total = videoDao.selectImeiCount();

        PageListVo vo = new PageListVo(page, pageSize);
        vo.setList(list);
        vo.setTotal(total);
        return vo;
    }

    /**
     * 添加1个新视频
     * 生成预览图片、获取视频时长、记录入库
     * update by xiayuan
     */
    @Override
    public ResponseVo addNewFile(byte[] bytes, String origName, String suffix, String description, Date createTime,
                                 int userId, String imei, String neUserName, String src, String machineCode, String tag, String orderNo, Float lon, Float lat, String caller, String called, String source) {
        logger.debug("有新的视频文件要处理.");
        return addNewFile(bytes, origName, suffix, description, Dates.toLocalDateTime(createTime), userId, imei, neUserName, src, machineCode, tag, orderNo, lon, lat, caller, called, source);
    }

    /**
     * 获得1个视频的存储文件路径
     *
     * @param suffix
     * @return
     */
    public File createNewVideoSaveFile(String suffix) {
        String dateDir = LocalDate.now().toString();
        String uuidName = UUID.randomUUID().toString();
        String videoPath = dateDir + File.separator + uuidName + "." + suffix;

        String videoDir = configService.getUploadFileSaveDir() + configService.getUploadVideoDir();
        // @todo 集群环境文件存储问题
        File videoFile = new File(videoDir + File.separator + videoPath);//服务器本地生成1文件存储视频内容
        if (!videoFile.getParentFile().exists())
            videoFile.getParentFile().mkdirs();
        return videoFile;
    }

    /**
     * 添加1个新视频
     * 生成预览图片、获取视频时长、记录入库
     */
    public ResponseVo addNewFile(Object fileInstanceOrFileBytes, String origName, String suffix, String description, LocalDateTime createTimeDT,
                                 int userId,String imei, String neUserName, String src, String machineCode, String tag, String orderNo, Float lon, Float lat, String caller, String called, String source) {
        OperaLog operaLog = new OperaLog();
        if (OperaLogHolder.get() != null) {
            operaLog = OperaLogHolder.get();
        } else {
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
        operaLogService.addNewLog(operaLog, 8, "开始处理新的视频", 0);

        String path = null;
        long sizeKb = 0;
        if (fileInstanceOrFileBytes instanceof File) {
            File videoFile = (File) fileInstanceOrFileBytes;
            sizeKb = videoFile.length() / 1024;
            path = fastDfsClient.uploadFile(videoFile, suffix);
        } else {
            byte[] bytes = (byte[]) fileInstanceOrFileBytes;
            sizeKb = bytes.length / 1024;
            path = fastDfsClient.uploadFile(bytes, suffix);
        }

        if (path == null) {
            operaLogService.addNewLog(operaLog, 9, "上传至分布式文件系统失败", 0);
            logger.error("video file save to oss failed.origName=" + origName + ",createTime=" + createTimeDT + ",imei=" + imei);
            return ResponseVo.fail("保存上传文件失败.fileName=" + origName);
        }
        operaLogService.addNewLog(operaLog, 9, "上传至分布式文件系统成功", 0);
//        Helmet helmet = helmetService.getHelmetByImei(clientId);
//        String userId = null;
//        if (helmet != null) {
//            HelmetBindLog log = helmetBindLogService.getBindUserPhoneByHelmetIdAndTime(helmet.getId(), createTimeDT);
//            if (log != null) userId = log.getUserPhone();
//        }

        //save to db
        operaLogService.addNewLog(operaLog, 10, "开始将视频信息存入数据库", 0);
        logger.info("video file saveToDb ... ");
        Video v = new Video();
        //20190805头盔端视频源文件有可能同名，现改成唯一文件名
//        origName = "";
        v.setFileName(origName);
        v.setDescription(description);
        v.setUploadTime(LocalDateTime.now());
        v.setCreateTime(createTimeDT);
        v.setClientId(imei);
        /**
         * udpate by xiayuan 2018/10/11
         */
        if(StringUtils.isEmpty(neUserName)){
            neUserName = "未知";
        }
        v.setNeUserName(neUserName);

        v.setUserId(userId);
        v.setViewCount(0);
        v.setOssPath(path);
        //信息后续通过异步队列补充
        v.setPicOssPath(null);
        v.setSeconds(-1);

        v.setSizeKb(sizeKb);
        v.setState(VideoStateEnum.NOT_PROC.getState());
        v.setSrc(src);
        v.setHasGpsData(0);//未知
        v.setImei(null);//未知
        v.setMachineCode(machineCode);
        v.setOrderNo(orderNo);
        if (lon != null && lat != null) {
            v.setLon(lon);
            v.setLat(lat);
        }
        v.setCalled(called);
        v.setCaller(caller);
        v.setSource(source);
//        logger.debug("视频信息：" + JSON.toJSON(v));
        this.insert(v, tag);
        operaLogService.addNewLog(operaLog, 11, "将视频信息存入数据库", 0);

        return ResponseVo.success();
    }

    /**
     * 获得视频对应文件的本地存储
     * 本地优先，oss次之
     *
     * @param v
     * @return
     */
    public File getVideoFile(Video v) {
        File oriVideo = new File(configService.getUploadFileSaveDir(), configService.getUploadVideoDir() + File.separator + v.getFileName());
        if (oriVideo.exists() && oriVideo.length() > 0) {
            return oriVideo;//临时文件存在
        }

        String ossPath = v.getOssPath();
        if (StringUtils.isEmpty(ossPath))
            return null;//不存在的原始文件

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(oriVideo);
            boolean success = fastDfsClient.downloadFile(ossPath, fos);
            if (success && oriVideo.exists()) {
                return oriVideo;//可删除，被替换掉的原始文件
            }
        } catch (Exception e) {
            logger.error("构造文件输出流异常:" + oriVideo.getAbsolutePath(), e);
            oriVideo.delete();
            return null;//不存在的原始文件
        }
        return null;
    }

    public PageListVo<UploadEntity> getVideoListByOrderNo(String orderNo, Integer page) {
        String type = "video";
        if (page == null || page <= 0) page = 1;
        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.valueOf(type);
        String searchKey;
        String searchVal;

        //通过工单号查询机号
        WorkOrder workOrder = workOrderDao.selectByOrderNo(orderNo);
        String machineCode = workOrder.getMachineCode();
        int pageSize = 4;
        //通过工单号获取视频信息
        searchKey = "orderNo";
        searchVal = orderNo;
        PageListVo<UploadEntity> vo1 = uploadEntityServiceFactory.listData(typeEnum, searchKey, searchVal, page, pageSize);

        if (vo1.getList().size() > 0) {
            vo1.setFlag(1);
            return vo1;
        } else {
            searchKey = "machineCode";
            searchVal = machineCode;
            //只查这个机号对应的最近拍摄的4条 pageSize=4
            PageListVo<UploadEntity> vo2 = uploadEntityServiceFactory.listDataByCreateTime(typeEnum, searchKey, searchVal, 1, 4);
            vo2.setFlag(2);
            return vo2;
        }
        /*//并集：
        PageListVo<UploadEntity> vo = new PageListVo<>();
        List<UploadEntity> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for(int i = 0;i<vo1.getList().size();i++){
            int id1 = vo1.getList().get(i).getId();
            map.put(String.valueOf(id1),vo1.getList().get(i));
        }
        for(int j = 0;j<vo2.getList().size();j++){
            int id2 = vo2.getList().get(j).getId();
            if(map.get(id2)==null){
                map.put(String.valueOf(id2),vo2.getList().get(j));
                vo1.setTotal(vo1.getTotal()+1);
            }
        }
        //将map放到vo中返回
        //遍历map
        for(Object u : map.values()){
            list.add((UploadEntity)u);
        }
        vo.setList(list);
        vo.setTotal(vo1.getTotal());//2018/8/17 有问题
        vo.setPageSize(pageSize);
        vo.setPage(page);
        return vo;*/
    }

    @Transactional
    public void insertVideoOrder(VideoOrder videoOrder) {
        int i = videoOrderDao.insert(videoOrder);
    }

    public List<Integer> listDoubles() {
        Map param = new HashMap();
        return videoDao.listDoubles(param);
    }

    public void noticeToThird(Video v) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderId", v.getOrderNo());
        params.put("videoId", v.getId()+"");
        String createTime = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        params.put("createTime", createTime);
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(v.getClientId());
        String deviceNumber = equipmentLedger.getDeviceNumber();
        params.put("deviceNumber", deviceNumber);
        /**
         * 查询视频所管理项目
         */
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalService.selectByUserId(v.getUserId());
        if(helmetUniversalInfo!=null) {
            params.put("project", helmetUniversalInfo.getId());
        }
        VideoMessage videoMessage = new VideoMessage();
        videoMessage.setVideoId(v.getId());
        videoMessage.setOrderId(v.getOrderNo());
        videoMessage.setRoutingKey("TYHelmet.File.Video.Upload");
        videoMessage.setDeviceNumber(deviceNumber);
        videoMessage.setCreateTime(createTime);
        videoMessageDao.insert(videoMessage);
        RabbitMqVo rabbitMqVo = new RabbitMqVo();
        rabbitMqVo.setsTime(new Date().getTime() + "");
        rabbitMqVo.setMessage(params);
        rabbitMqVo.setMessageId(""+videoMessage.getId());
        rabbitMqVo.setRoutingKey("TYHelmet.File.Video.Upload");
        mqProducer.sendDataToQueue("TYHelmet.File.Video.Upload", rabbitMqVo);
    }

    public void saveLocation(Video v) {
        if(v.getLon()!=-1.0 && v.getLat()!= -1.0) {
            try {
                JSONObject jo = tianYuanMapApiHelper.getLocation(v.getLon(), v.getLat(), false);
                if(jo!=null && jo.getBoolean("status")) {
                    JSONArray ja = jo.getJSONArray("descdata");
                    JSONObject ja0 = (JSONObject) ja.get(0);
                    // 地理位置信息
                    VideoLocation videoLocation = JSONObject.toJavaObject(ja0, VideoLocation.class);
                    videoLocation.setVideoId(v.getId());
                    videoLocationDao.insert(videoLocation);
                }
            } catch (Exception e){
                logger.error("保存视频的地理位置信息失败！");
            }
        }
    }
}
