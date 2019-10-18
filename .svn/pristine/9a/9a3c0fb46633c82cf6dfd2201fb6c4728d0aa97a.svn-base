package com.tianyi.helmet.server.quartz;

import com.tianyi.helmet.server.dao.device.TmnlDao;
import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.device.TmnlInfo;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.service.angelcomm.azkj.AzkjClient;
import com.tianyi.helmet.server.service.angelcomm.azkj.CallRecord;
import com.tianyi.helmet.server.service.client.NeteaseUserService;
import com.tianyi.helmet.server.service.data.HelmetImportService;
import com.tianyi.helmet.server.service.data.HelmetOnlineStatusService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.UploadEntityServiceFactory;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.DownloadURLFile;
import org.apache.http.client.utils.DateUtils;
import org.springframework.mock.web.MockMultipartFile;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/3/15 9:16
 */
@Component("taskJob")
public class QuartzTask {
    @Autowired
    private HelmetOnlineStatusService helmetOnlineStatusService;

    @Autowired
    private HelmetImportService helmetImportService;

    @Autowired
    private VideoService videoService;
    @Autowired
    private AzkjClient azkjClient;
    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private ConfigService configService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private EquipmentService equipmentService;
    /**
     * 每3分钟触发一次（巡查在线头盔列表，超过3分钟没接收到心跳，强制下线）
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void testTask(){
        helmetOnlineStatusService.handleOffline();
        System.out.println("每1分钟的0秒执行一次"+System.currentTimeMillis());
    }

    /**
     * 每10分钟触发一次（导入生产导运营头盔台账数据）
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void product(){
        List<TmnlInfo> list = helmetImportService.listUnImportDevs();
        for(TmnlInfo tmnlInfo : list) {
            int updateStatus = helmetImportService.productStorage(tmnlInfo.getTmnlProductNo(), tmnlInfo.getTmnlID(), tmnlInfo.getTmnlSatelliteNo(), tmnlInfo.getTmnlSatelliteType(), tmnlInfo.getTmnlSoftwareEdition());
            tmnlInfo.setStatus(updateStatus);
            helmetImportService.updateImportStatus(tmnlInfo);
        }
    }

    /**
     * 每10分钟触发一次（去除重复的视频数据）
     */
    @Scheduled(cron = "0 0/20 * * * ?")
    public void doubleCut(){
        List<Integer> list = videoService.listDoubles();
        for(Integer vid : list) {
            videoService.deleteById(vid);
        }
    }

    /**
     * 每天凌晨1点执行一次（下载安正科技呼叫视频到本地）
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void downloadAgkjVideo(){
        // 1、查询昨天的视频呼叫记录
        LocalDateTime yestoday = LocalDateTime.now().minusDays(1);
        //构造时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.of(yestoday.getYear(), yestoday.getMonth(), yestoday.getDayOfMonth(), 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(yestoday.getYear(), yestoday.getMonth(), yestoday.getDayOfMonth(), 23, 59, 59);
        String stime = startTime.format(formatter);
        String etime = endTime.format(formatter);
        /**
         * get begin
         */
        try {
            CallRecord[] callRecords = azkjClient.selectCallRecord("",stime,etime);
            for (CallRecord record : callRecords) {
                // 2、判断数据库是否已经存在
                Map<String, Object> params = new HashMap<>();
                String source = record.getCid();
                params.put("source", source);
                int existNum = videoService.countBy(params);
                if(existNum > 0) {
                    continue;
                }
                // 3、不存在则调用视频上传接口
                //ResponseVo vo = uploadEntityServiceFactory.upload(UploadEntityTypeEnum.video, file, userId, helmetImei, neUserName, createTime, machineCode, description, tag, orderNo, lon, lat);
                // 4、
                System.out.println(record.getWebpath());// Web 访问地址
                System.out.println(record.getSubpath());// 路径
                System.out.println(record.getRecordname());
                String path = configService.getUploadAzkjServer() + record.getWebpath().substring(20) + record.getSubpath()+"/"+record.getRecordname();
                System.out.println(path);
                String saveDir = configService.getUploadFileSaveDir() + File.separator + configService.getUploadAzkjDir() + File.separator;
                String filePath = DownloadURLFile.downloadFromUrl(path, saveDir);
                // 上传这些文件到fastdfs服务器
                File file = new File(filePath);
                FileInputStream fileInputStream = new FileInputStream(file);
                MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                        ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                String caller = record.getCaller();
                String called = record.getCalled();
                int userId = -1;
                String helmetImei = "";
                String neUserName = "";
                NeteaseUser callerUser = neteaseUserService.selectByYunId(caller);
                NeteaseUser calledUser = neteaseUserService.selectByYunId(called);
                if(callerUser!=null) {
                    userId = callerUser.getUserId();
                    List<EquipmentLedger> callerDevices = equipmentService.selectByUserId(callerUser.getUserId());
                    if(callerDevices!=null && callerDevices.size() > 0) {
                        helmetImei = callerDevices.get(0).getDeviceUUID();
                    }
                }
                if(calledUser!=null) {
                    userId = calledUser.getUserId();
                    List<EquipmentLedger> calledDevices = equipmentService.selectByUserId(calledUser.getUserId());
                    if(calledDevices!=null && calledDevices.size() > 0) {
                        helmetImei = calledDevices.get(0).getDeviceUUID();
                    }
                }
                String[] dateFormats = {"yyyyMMddHHmmss"};
                Date createTime = DateUtils.parseDate(record.getCreatetime(), dateFormats);//"20190202112323"
                String description = "安正音视频";
                String machineCode="";
                String tag = "安正音视频";
                String orderNo = null;
                float lon=-1l;
                float lat=-1l;
                ResponseVo vo = uploadEntityServiceFactory.upload(UploadEntityTypeEnum.video, multipartFile, userId, helmetImei, neUserName, createTime, machineCode, description, tag, orderNo, lon, lat, caller, called, source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // get end

    }
}
