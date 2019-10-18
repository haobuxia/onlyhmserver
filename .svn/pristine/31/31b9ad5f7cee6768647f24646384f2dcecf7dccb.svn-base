package com.tianyi.helmet.server.service.job;


import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.kmx.PointWithVclid;
import com.tianyi.helmet.server.service.kmx.TsingHuaKmxService;
import com.tianyi.helmet.server.service.scene.VideoExcelDataPo;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.util.MyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 清华视频处理工作
 *
 * wenxinyan 2018-8-16 修改了processOneData()
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class TsingHuaVideoProcessJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(TsingHuaVideoProcessJob.class);

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private TsingHuaKmxService tsingHuaKmxService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    /*
        处理队列消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("TsingHuaVideoProcessJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            List<VideoExcelDataPo> dataList = JSON.parseArray(body, VideoExcelDataPo.class);
            processData(dataList);
        } catch (Exception e) {
            logger.error("处理excel配置的视频工作队列消息异常.", e);
        }
    }

    /**
     * 处理视频任务列表
     */
    public void processData(List<VideoExcelDataPo> dataList) {
        logger.debug("开始执行清华需要的视频工作。共有任务数量:" + dataList.size());
        dataList.stream().forEach(po -> {
            try {
                processOneData(po);
            } catch (Exception e) {
                logger.error("执行清华需要的视频工作异常:" + po.getNeUsername() + "," + po.getJh(), e);
            }
        });
    }

    //处理一条视频和json数据输出需求
    public void processOneData(VideoExcelDataPo po) {
        String neUserName = po.getNeUsername();
/**
 * update by xiayuan 2018/10/10
 */
//        EquipmentLedger helmet = null;
//        Map<String,Object> map = new HashMap<>();
//        map.put("neUserHel",neUsername);
//        List<User> users = userService.listByNoPage(map);
//        User user = users.get(0);
//        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
//        for (EquipmentLedger e : list) {
//            String state = (String) redisTemplate.opsForValue().get(e.getDeviceUUID());
//            if (MyConstants.DEVICE_STATE_ON.equals(state)) {
//                helmet = e;
//            }
//        }
//        String helmetImei = helmet.getDeviceUUID();
//        if (StringUtils.isEmpty(helmetImei)) {
//            logger.error("执行清华需要的视频工作时，发现网易账号对应头盔并不存在." + neUsername);
//            return;
//        }

//        String tyboxNo = po.getTyboxNo();
        String jh = po.getJh();
        Date date1 = po.getBeginTime();
        Date date2 = po.getEndTime();

        //修改传递参数createTime为crossTime
        //2018-08-16 wxy
        Map<String, Object> params = new HashMap(3);
        params.put("neUserName", neUserName);
        params.put("createTimeCross", "true");
        params.put("crossTime1", date1);
        params.put("crossTime2", date2);
        System.out.println(params.get("clientId"));
        System.out.println(params.get("crossTime1"));
        System.out.println(params.get("crossTime2"));
        List<Video> videoList = videoService.listBy(params);
        int size = videoList.size();
        logger.debug("查询得到符合条件的视频数:" + size);
        if (size == 0) {
            //视频没找到,则根据po中配置的时间来查询kmx
            //1 查询kmx数据
            long time1 = po.getBeginTime().getTime();
            long time2 = po.getEndTime().getTime();

            Map<String, List<PointWithVclid>> kmxData = null;
            try {
                kmxData = tsingHuaKmxService.getRange(jh, time1, time2);
            } catch (Exception e) {
                logger.error("根据excel中配置时间查询工况数据异常.jh=" + jh, e);
                kmxData = Collections.emptyMap();
            }

            //2 保存视频和kmx文件到指定目录
            try {
                String fileName = saveKmxData(jh, kmxData, po.getBeginTime(), po.getEndTime());
                logger.debug("保存视频到设定目录成功:" + fileName);
            } catch (Exception e) {
                logger.error("保存工况到物理磁盘目录时异常.jh=" + jh, e);
            }

        } else {
            //有视频，则以视频时间来查询kmx
            videoList.stream().forEach(video -> {
                //1 查询kmx数据
                long time1 = Dates.toDate(video.getCreateTime()).getTime();
                long time2 = Dates.toDate(video.getCreateTime().plusSeconds(video.getSeconds())).getTime();

                Map<String, List<PointWithVclid>> kmxData = null;
                try {
                    kmxData = tsingHuaKmxService.getRange(jh, time1, time2);
                } catch (Exception e) {
                    logger.error("查询视频工况数据异常.v.id=" + video.getId() + ",jh=" + jh, e);
                    kmxData = Collections.emptyMap();
                }

                //2 保存视频和kmx文件到指定目录
                try {
                    String fileName = saveVideoAndKmxData(jh, video, kmxData);
                    logger.debug("保存视频到设定目录成功:" + fileName);
                } catch (Exception e) {
                    logger.error("保存视频和工况到物理磁盘目录时异常.v.id=" + video.getId() + ",jh=" + jh, e);
                }
            });
        }
    }

    //保存kmx数据
    protected String saveKmxData(String jh, Map<String, List<PointWithVclid>> kmxData, Date beginTime, Date endTime) throws FileNotFoundException {
        String dir = configService.getTsinghuaVideoOutputDir();
        File file = new File(dir);
        file.mkdirs();

        String fileName = jh + "-" + Dates.format(beginTime, "yyyyMMddHHmmss") + "-" + Dates.format(endTime, "yyyyMMddHHmmss");
        //文件命名规则为TYBOX号+起止时间
        File kmxFile = new File(file, fileName + ".json");
        try {
            org.apache.commons.io.IOUtils.write(JSON.toJSONString(kmxData, false), new FileOutputStream(kmxFile));
        } catch (Exception e) {
            logger.error("输出kmx工况数据到目录异常.jh=" + jh + ",path=" + kmxFile.getAbsolutePath(), e);
        }
        return fileName;
    }


    //保存视频原始文件到配置目录并重命名为标准格式
    protected String saveVideoAndKmxData(String jh, Video video, Map<String, List<PointWithVclid>> kmxData) throws FileNotFoundException {
        String dir = configService.getTsinghuaVideoOutputDir();
        File file = new File(dir);
        file.mkdirs();

        LocalDateTime createTime = video.getCreateTime();
        LocalDateTime endTime = createTime.plusSeconds(video.getSeconds());
        String fileName = jh + "-" + Dates.format(Dates.toDate(createTime), "yyyyMMddHHmmss") + "-" + Dates.format(Dates.toDate(endTime), "yyyyMMddHHmmss");
        //文件命名规则为TYBOX号+起止时间
        File videoFile = new File(file, fileName + ".mp4");
        File kmxFile = new File(file, fileName + ".json");
        try {
            org.apache.commons.io.IOUtils.write(JSON.toJSONString(kmxData, false), new FileOutputStream(kmxFile));
        } catch (Exception e) {
            logger.error("输出kmx工况数据到目录异常.jh=" + jh + ",path=" + kmxFile.getAbsolutePath(), e);
        }
        try {
            fastDfsClient.downloadFile(video.getOssPath(), new FileOutputStream(videoFile));
        } catch (Exception e) {
            logger.error("输出视频文件到目录异常.jh=" + jh + ",path=" + videoFile.getAbsolutePath() + ",v.id=" + video.getId(), e);
        }
        return fileName;
    }

}
