package com.tianyi.helmet.server.service.scene;

import com.tianyi.helmet.server.dao.scene.VideoActionDao;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.scene.VideoAction;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 视频动作
 * Created by liuhanc on 2018/6/4.
 */
@Service
public class VideoActionService {
    @Autowired
    private VideoActionDao videoActionDao;
    @Autowired
    private VideoService videoService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private JsonRedisTemplate jedisTemplate;
    @Autowired
    private UserService userService;


    public void insert(VideoAction videoAction) {
        videoActionDao.insert(videoAction);
    }

    public VideoAction selectByVideoId(int videoId) {
        return videoActionDao.selectByVideoId(videoId);
    }

    public int updateByVideoId(VideoAction videoAction) {
        return videoActionDao.updateByVideoId(videoAction);
    }

    public int deleteByVideoId(int videoId) {
        return videoActionDao.deleteByVideoId(videoId);
    }


    public boolean addUserCompareVideo(int userId, int videoId) {
        String key = CacheKeyConstants.VIDEO_COMPARE_BY_USRID + ":" + userId;
        boolean exists = jedisTemplate.opsForSet().isMember(key,videoId);
        if(!exists){
            jedisTemplate.opsForSet().add(key, videoId);
            jedisTemplate.opsForSet().getOperations().expire(key, 30, TimeUnit.MINUTES);
            return true;
        }
        return false;
    }

    public void deleteUserCompareVideo(int userId, int videoId) {
        String key = CacheKeyConstants.VIDEO_COMPARE_BY_USRID + ":" + userId;
        jedisTemplate.opsForSet().remove(key, videoId);
        jedisTemplate.opsForSet().getOperations().expire(key, 30, TimeUnit.MINUTES);
    }

    public void clearUserCompareVideo(int userId) {
        String key = CacheKeyConstants.VIDEO_COMPARE_BY_USRID + ":" + userId;
        jedisTemplate.opsForSet().getOperations().expire(key, 0, TimeUnit.MILLISECONDS);
    }

    public long getUserCompareSize(int userId) {
        String key = CacheKeyConstants.VIDEO_COMPARE_BY_USRID + ":" + userId;
        long size = jedisTemplate.opsForSet().size(key);
        if(size > 0 ){
            jedisTemplate.opsForSet().getOperations().expire(key, 0, TimeUnit.MILLISECONDS);
        }
        return size;
    }

    public List<Video> listUserUserCompareVideo(int userId) {
        String key = CacheKeyConstants.VIDEO_COMPARE_BY_USRID + ":" + userId;
        Set<Object> set = jedisTemplate.opsForSet().members(key);
        if(set.size() > 0 ){
            jedisTemplate.opsForSet().getOperations().expire(key, 0, TimeUnit.MILLISECONDS);
        }

        /**
         * update by xiayuan 2018/10/10
         */
        List<Video> videoList = set.stream().map(obj -> {
            Integer videoId = (Integer) obj;
            Video v = videoService.selectById(videoId);
            String clientId = v.getClientId();
            EquipmentLedger helmet = equipmentService.selectByUUID(clientId);
            User user = userService.selectById(helmet.getUserId());
            v.setNeUserName(helmet == null ? clientId : user.getNeUserHel());// TODO: 2018/10/9 是哪一个网易账号
            return v;
        }).collect(Collectors.toList());
        return videoList;
    }


    /**
     * 判断动作时间是否设置完整了
     *
     * @return
     */
    public boolean isSetComplete(VideoAction va) {
        return
                Commons.isPlusInt(va.getAutoSpeedDown()) &&
                        Commons.isPlusInt(va.getSwingArmRise()) &&
                        Commons.isPlusInt(va.getSwingArmFall()) &&
                        Commons.isPlusInt(va.getDipperDigger()) &&
                        Commons.isPlusInt(va.getDipperDigger2()) &&
                        Commons.isPlusInt(va.getDipperUnload()) &&
                        Commons.isPlusInt(va.getBucketDigger()) &&
                        Commons.isPlusInt(va.getBucketUnload());
    }

    /**
     * 获得动作的时间序列
     *
     * @return
     */
    public List<Long> getTimeList(VideoAction va) {
        LocalDateTime videoTime = va.getVideoTime();
        return Arrays.stream(new Integer[]{va.getAutoSpeedDown(), va.getSwingArmRise(), va.getSwingArmFall(),
                va.getDipperDigger(), va.getDipperDigger2(), va.getDipperUnload(), va.getBucketDigger(),
                va.getBucketUnload()})
                .sorted().map(second -> {
                    long time = Dates.toDate(videoTime.plusSeconds(second)).getTime();
                    return time;
                }).collect(Collectors.toList());
    }

}
