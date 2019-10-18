package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanApiBasicHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 天远用户token刷新执行
 * <p>
 * Created by liuhanc on 2018/3/5.
 */
@Component
public class TyUserTokenRefreshJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(TyUserTokenRefreshJob.class);

    @Autowired
    private TianYuanUserService tianYuanUserService;
    @Autowired
    private TianYuanApiBasicHelper tianYuanApiBasicHelper;

    @Value("${job.tianyuan.tokenrefresh.minutes}")
    private int refreshMinutes;//剩余寿命小于这个值时刷新token

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("TyUserTokenRefreshJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            Integer userId = Integer.parseInt(body);
            TianYuanUser user = tianYuanUserService.selectById(userId);
            doRefresh(user, false);//不强制刷新
        } catch (Exception e) {
            logger.error("处理天远token刷新队列消息异常.", e);
        }
    }

    public void doRefresh(TianYuanUser user, boolean force) {
        if (user == null)
            return;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minRefreshTime = now.minusSeconds(refreshMinutes);//比当前时间小xx分钟的时间作为刷新时间参数
        LocalDateTime lastRefreshTime = user.getRefreshTime();

        if (lastRefreshTime.isAfter(minRefreshTime)) {
            if (!force) {
                //比最早刷新时间还晚，表名已经刷新了则不再刷新
                logger.debug("天远用户token的刷新时间还不到,此处忽略." + user.getUsername() + ",上次刷新时间:" + user.getRefreshTime());
                return;
            }
        }

        try {
            tianYuanApiBasicHelper.refreshAccessToken(user);
            logger.info("刷新天远用户token完毕.username=" + user.getUsername());
        } catch (Exception e) {
            logger.error("刷新天远用户token异常.id=" + user.getId() + ",username=" + user.getUsername(), e);
            return;
        }

        tianYuanUserService.updateById(user);
        tianYuanUserService.clearByUserNameCache(user.getUsername());
    }
}
