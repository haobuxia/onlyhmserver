package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 天远用户token定时刷新的检查任务
 * <p>
 * Created by liuhanc on 2018/3/5.
 */
@Component
public class TyUserTokenRefreshCheckJob {
    private Logger logger = LoggerFactory.getLogger(TyUserTokenRefreshCheckJob.class);

    @Autowired
    private TianYuanUserService tianYuanUserService;
    @Autowired
    private RedisMqPublisher redisMqPublisher;

    @Value("${job.tianyuan.tokenrefresh.start:1}")
    private String startJob;
    @Value("${job.tianyuan.tokenrefresh.minutes}")
    private int refreshMinutes;//剩余寿命小于这个值时刷新token


    public void doRefresh() {
        if(!"1".equalsIgnoreCase(startJob)){
            logger.info("天远用户token刷新不启动");
            return;
        }

        logger.info("天远用户token刷新开始.");
        try {
            refresh();
        } catch (Throwable e) {
            logger.error("天远用户token刷新异常", e);
        }
    }

    public void addToRefreshQueue(int tianYuanUserId) {
        try{
            redisMqPublisher.sendMessage(ChannelNameConstants.channelName_TyUserToekn_Refresh, String.valueOf(tianYuanUserId));
        }catch(Exception e){
            logger.error("天远用户token刷新加入队列异常.user.id="+tianYuanUserId,e);
        }
    }

    private void refresh() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime refreshTime = now.minusSeconds(refreshMinutes);//比当前时间小xx分钟的时间作为刷新时间参数
        Map<String, Object> map = new HashMap<>();
        map.put("refreshTime2", refreshTime);
        List<TianYuanUser> userList = tianYuanUserService.listBy(map);
        logger.debug("需要刷新token的用户数量:" + userList.size());

        userList.stream().map(TianYuanUser::getId).forEach(this::addToRefreshQueue);
    }

}
