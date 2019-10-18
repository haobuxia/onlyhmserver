package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.controller.interceptor.OperaLogHolder;
import com.tianyi.helmet.server.dao.file.FileDao;
import com.tianyi.helmet.server.entity.client.HelmetBindLog;
import com.tianyi.helmet.server.entity.file.File;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.entity.log.OperaLog;
import com.tianyi.helmet.server.service.client.HelmetBindLogService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.log.OperaLogService;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 上传文件服务
 *
 * @author liuhan
 * @since 1.0
 */
@Service
public class FileService implements UploadEntityService<File> {
    private Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileDao fileDao;
    @Autowired
    private HelmetBindLogService helmetBindLogService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private ConfigService configService;
    @Autowired
    private TagResourceService tagResourceService;
    @Autowired
    private RedisMqPublisher redisMqPublisher;
    @Autowired
    private OperaLogService operaLogService;


    @Override
    public void insert(File file) {
        fileDao.insert(file);
    }


    @Override
    @Transactional
    public void insert(File file, String tag) {
        insert(file);
        if (!StringUtils.isEmpty(tag)) {
            tagResourceService.insert(UploadEntityTypeEnum.file, file.getId(), tag);
        }
    }


    @Override
    public File selectById(int id) {
        return fileDao.selectById(id);
    }

    @Override
    public int deleteById(int id) {
        return fileDao.deleteById(id);
    }

    @Override
    public List<File> listBy(Map<String, Object> params) {
        return fileDao.listBy(params);
    }

    @Override
    public int countBy(Map<String, Object> params) {
        return fileDao.countBy(params);
    }

    @Override
    public int increaseViewCount(int id) {
        return fileDao.increaseViewCount(id);
    }

    /**
     * 保存1个文件
     *
     * @param bytes
     * @param origName
     * @param suffix
     * @param description
     * @param createTime
     * @param clientId
     * @param src
     * @param neUserName
     * @return
     */
    @Override
    public ResponseVo addNewFile(byte[] bytes, String origName, String suffix, String description, Date createTime,
                                 int userId,String clientId,String neUserName, String src, String machineCode, String tag, String orderNo, Float lon, Float lat, String caller, String called, String source) {
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
        operaLogService.addNewLog(operaLog, 8, "开始处理新的文件", 0);

        String[] path = fastDfsClient.uploadFile(bytes, suffix, configService.getFastdfsRetryTimes());
        if (path == null) {
            operaLogService.addNewLog(operaLog, 9, "上传至分布式文件系统失败", 0);
            logger.error("upload post.file save to oss failed.origName=" + origName + ",createTime=" + createTime + ",clientId=" + clientId);
            return ResponseVo.fail("保存上传文件失败.fileName=" + origName);
        }
        operaLogService.addNewLog(operaLog, 9, "上传至分布式文件系统成功", 0);
        String ossPath = path[0] + "/" + path[1];
        LocalDateTime createTimeDT = Dates.toLocalDateTime(createTime);
//        Helmet helmet = helmetService.getHelmetByImei(clientId);
//        String userId = null;
//        if (helmet != null) {
//            HelmetBindLog log = helmetBindLogService.getBindUserPhoneByHelmetIdAndTime(helmet.getId(), createTimeDT);
//            if (log != null) userId = log.getUserPhone();
//        }

        operaLogService.addNewLog(operaLog, 10, "开始将文件信息存入数据库", 0);
        File file = new File();
        file.setUploadTime(LocalDateTime.now());
        file.setCreateTime(createTimeDT);
        file.setClientId(clientId);
        /**
         * udpate by xiayuan 2018/10/11
         */
        file.setNeUserName(neUserName);
        file.setUserId(userId);
        file.setFileName(origName);
        file.setDescription(description);
        file.setOssPath(ossPath);
        file.setViewCount(0);
        file.setSizeKb(bytes.length / 1024);
        file.setFileType(suffix);
        this.insert(file, tag);
        operaLogService.addNewLog(operaLog, 11, "将文件信息存入数据库结束", 0);

        if ("gps".equalsIgnoreCase(suffix) || "timetag".equalsIgnoreCase(suffix)) {
            addToProcessQueue(file.getId());
            operaLogService.addNewLog(operaLog, 12, "文件类型为gps或者timetag，将文件加入处理队列", 0);
        }

        return ResponseVo.success(file);
    }

    public void addToProcessQueue(int id) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_File_Process, String.valueOf(id)+","+OperaLogHolder.getLogflowId()+","+OperaLogHolder.getClientId()+","+OperaLogHolder.get().getLogType());//加入文件处理队列
    }

}
