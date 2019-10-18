package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.entity.file.BigFile;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 大文件服务
 * <p>
 * Created by liuhanc on 2017/12/27.
 */
@Service
public class BigFileService {
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private JsonRedisTemplate jedisTemplate;
    @Autowired
    private RedisMqPublisher redisMqPublisher;


    private Logger logger = LoggerFactory.getLogger(BigFileService.class);

    /**
     * 初始化1个大文件
     *
     * @param file
     */
    public ResponseVo<BigFile> initNewBigFile(BigFile file) {
        BigFile old = getBigFile(file.getFileId());
        if (old == null) {
            String key = CacheKeyConstants.BIGFILE_BY_FILEID + ":" + file.getFileId();
            jedisTemplate.opsForValue().set(key, file, 10, TimeUnit.DAYS);//10天
            return ResponseVo.success();
        } else {
            return ResponseVo.fail("该文件ID已经初始化过.", old);
        }
    }

    //获得大文件
    public BigFile getBigFile(String fileId) {
        String key = CacheKeyConstants.BIGFILE_BY_FILEID + ":" + fileId;
        BigFile bigFile = (BigFile) jedisTemplate.opsForValue().get(key);
        return bigFile;
    }

    //删除大文件缓存
    public void removeBigFile(String fileId){
        String key = CacheKeyConstants.BIGFILE_BY_FILEID + ":" + fileId;
        jedisTemplate.delete(key);
    }

    /**
     * 添加1个分片
     */
    public ResponseVo addFileSegment(String fileId, int fileSeq, byte[] fileBytes) {
        BigFile bigFile = getBigFile(fileId);
        if (bigFile == null) {
            return ResponseVo.fail("文件尚未初始化");
        }
        boolean exists = bigFile.isSegmentExists(fileSeq);
        if(exists){
            return ResponseVo.fail("此分片已存在");
        }

        String ossPath = fastDfsClient.uploadFile(fileBytes, "tmp");
        if (StringUtils.isEmpty(ossPath)) {
            return ResponseVo.fail("文件保存失败,请重试");
        }

        boolean success = bigFile.addSegment(fileSeq, ossPath);
        if(success){
            // 更新一下
            String key = CacheKeyConstants.BIGFILE_BY_FILEID + ":" + fileId;
            jedisTemplate.opsForValue().set(key, bigFile, 10, TimeUnit.DAYS);//10天
        }

        boolean uploadComplete = false;
        if (bigFile.isFileComplete()) {
            uploadComplete = true;
            logger.debug("分片文件都已上传,准备合并.fileId=" + fileId);
            addToMergeQueue(fileId);
        }

        return ResponseVo.success(null, (success ? "添加成功" : "之前已添加本次重复,忽略") +(uploadComplete?"所有分片都已上传":"还有分片待上传"));
    }

    //添加到视频合并队列
    public void addToMergeQueue(String fileId) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_BigFile_Merge, fileId);
    }
}
