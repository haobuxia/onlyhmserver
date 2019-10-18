package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.BigFile;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.file.BigFileService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 基于mq的大文件合并job
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class BigFileMergeJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(BigFileMergeJob.class);

    @Autowired
    VideoService videoService;
    @Autowired
    private BigFileService bigFileService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private ConfigService configService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("BigFileMergeJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            String fileId = body;
            processOneData(fileId);
        } catch (Exception e) {
            logger.error("处理大文件合并队列消息异常.", e);
        }
    }

    /**
     * 1 下载所有分片
     * 2 合并成1个大文件
     * 3 记录大文件信息入库(db,oss)
     * 4 删除缓存
     */
    public void processOneData(String fileId) {
        BigFile bigFile = bigFileService.getBigFile(fileId);
        if (bigFile == null) {
            logger.error("处理大文件合并时找不到大文件信息.fileId=" + fileId);
            return;
        }

        Set<DoubleVo<Integer, String>> set = bigFile.getSegmentFileSet();
        if (CollectionUtils.isEmpty(set) || set.size() != bigFile.getSegmentCount()) {
            logger.error("处理大文件合并时发现文件数量错误.fileId=" + fileId);
            return;
        }

        Comparator<DoubleVo<Integer, String>> comparator = Comparator.comparing(DoubleVo::getKey);
        List<DoubleVo<Integer, String>> list = set.stream().sorted(comparator).collect(Collectors.toList());

        File mergedFile = mergeFile(bigFile.getFileType(), fileId, list);
        if (mergedFile == null) {
            return;
        }
        //合并成功记录到数据库中
        /**
         * update by xiayuan 2018/10/11
         */
        String imei = bigFile.getClientId();
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(imei);
        User user = userService.selectById(equipmentLedger.getUserId());
        ResponseVo vo = videoService.addNewFile(mergedFile, bigFile.getFileName(), bigFile.getFileType(), bigFile.getDescription(), bigFile.getCreateTime(), equipmentLedger.getUserId(),bigFile.getClientId(), user.getNeUserHel(), "upload", bigFile.getMachineCode(), bigFile.getTag(), null, null, null, "", "", "");
        if (vo.isSuccess()) {
            logger.debug("合并文件记录到数据库:" + (vo.isSuccess() ? "成功" : "失败") + ",fileId=" + fileId + ",path=" + mergedFile.getAbsolutePath());
            //删除cache
            bigFileService.removeBigFile(fileId);
        }
    }

    protected File mergeFile(String fileExt, String fileId, List<DoubleVo<Integer, String>> segmentFileList) {
        File mergedFile = videoService.createNewVideoSaveFile(fileExt);
        try {
            FileOutputStream completeFileOS = new FileOutputStream(mergedFile, true);
            for (DoubleVo<Integer, String> vo : segmentFileList) {
                byte[] bytes = fastDfsClient.downloadFile(vo.getVal());
                logger.debug("合并文件,idx=" + vo.getKey() + "," + vo.getVal() + ",下载成功.fileId=" + fileId);
                completeFileOS.write(bytes);
                completeFileOS.flush();
            }
            completeFileOS.close();
        } catch (Exception e) {
            logger.error("合并大文件失败.fileId=" + fileId, e);
            mergedFile.delete();
            return null;
        }
        logger.debug("合并文件成功.fileId=" + fileId + ",大小=" + mergedFile.length() + "," + mergedFile.getAbsolutePath());
        return mergedFile;
    }
}
