package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.AudioDao;
import com.tianyi.helmet.server.entity.file.Audio;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.entity.file.VideoStateEnum;
import com.tianyi.helmet.server.service.client.HelmetBindLogService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 音频服务
 *
 * @author liuhan
 * @since 1.0
 */
@Service
public class AudioService implements UploadEntityService<Audio> {

    private Logger logger = LoggerFactory.getLogger(AudioService.class);

    @Autowired
    private AudioDao audioDao;
    @Autowired
    private HelmetBindLogService helmetBindLogService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private ConfigService configService;
    @Autowired
    private TagResourceService tagResourceService;

    @Override
    public void insert(Audio audio) {
        audioDao.insert(audio);
    }

    @Override
    @Transactional
    public void insert(Audio audio, String tag) {
        insert(audio);
        if (!StringUtils.isEmpty(tag)) {
            tagResourceService.insert(UploadEntityTypeEnum.audio, audio.getId(), tag);
        }
    }

    @Override
    public Audio selectById(int id) {
        return audioDao.selectById(id);
    }

    @Override
    public List<Audio> listBy(Map<String, Object> params) {
        return audioDao.listBy(params);
    }

    @Override
    public int countBy(Map<String, Object> params) {
        return audioDao.countBy(params);
    }

    @Override
    public int increaseViewCount(int id) {
        return audioDao.increaseViewCount(id);
    }

    @Override
    public int deleteById(int id) {
        return audioDao.deleteById(id);
    }

    /**
     * 添加1个新音频
     * 记录入库
     * update by xiayuan 2018/10/11 增加neUserName
     */
    @Override
    public ResponseVo addNewFile(byte[] bytes, String origName, String suffix, String description, Date createTime,
                                 int userId, String clientId, String neUserName, String src, String machineCode, String tag, String orderNo, Float lon, Float lat, String caller, String called, String source) {
        //save to oss
        String[] path = fastDfsClient.uploadFile(bytes, suffix, configService.getFastdfsRetryTimes());
        if (path == null) {
            logger.info("upload post.file save to oss failed.fileName=" + origName);
            return ResponseVo.fail("保存上传文件失败");
        }

        String ossPath = path[0] + "/" + path[1];

        LocalDateTime createTimeDT = Dates.toLocalDateTime(createTime);
//        Helmet helmet = helmetService.getHelmetByImei(clientId);
//        String userId = null;
//        if (helmet != null) {
//            HelmetBindLog log = helmetBindLogService.getBindUserPhoneByHelmetIdAndTime(helmet.getId(), createTimeDT);
//            if(log != null) userId = log.getUserPhone();
//        }
        //save to db
        logger.info("upload post.file saveToDb ... ");
        Audio v = new Audio();
        v.setFileName(origName);
        v.setDescription(description);
        v.setUploadTime(LocalDateTime.now());
        v.setCreateTime(createTimeDT);
        v.setClientId(clientId);
        /**
         * udpate by xiayuan 2018/10/11
         */
        v.setNeUserName(neUserName);
        v.setUserId(userId);
        v.setViewCount(0);
        v.setOssPath(ossPath);
        v.setSizeKb(bytes.length / 1024);
        v.setState(VideoStateEnum.SUCCESS.getState());
        v.setSrc(src);
        v.setOrderNo(orderNo);
        this.insert(v, tag);
        return ResponseVo.success(v);
    }
}
