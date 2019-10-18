package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import com.tianyi.helmet.server.service.svc.SvcCommRestService;
import com.tianyi.helmet.server.service.svc.SvcIdRestService;
import com.tianyi.helmet.server.service.svc.SvcMainRestService;
import com.tianyi.helmet.server.service.svc.SvcTaskService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanSvcService;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.svc.rest.entity.SvcGzclMain;
import com.tianyi.svc.rest.entity.SvcMsg;
import com.tianyi.svc.rest.entity.SvcTask;
import com.tianyi.svc.sdk.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 天远用户-服务人员task刷新执行
 * <p>
 * Created by liuhanc on 2018/3/5.
 */
@Component
public class TyUserSvcTaskRefreshJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(TyUserSvcTaskRefreshJob.class);

    @Autowired
    private TianYuanUserService tianYuanUserService;
    @Autowired
    private TianYuanSvcService tianYuanSvcService;
    @Autowired
    private SvcTaskService svcTaskService;
    @Autowired
    private SvcMainRestService svcMainRestService;
    @Autowired
    private SvcCommRestService svcCommRestService;
    @Autowired
    private SvcIdRestService svcIdRestService;
    @Autowired
    private JsonRedisTemplate jedisTemplate;


    @Value("${job.tianyuan.svctaskrefresh.minutes}")
    private int refreshMinutes;//上次刷新距今小于这个值时刷新


    public static final String TYUSER_SVCTASK_REFRESHTIME = "tyuser_svctask_refreshtime";
    public static final String TYUSER_SVCMSG_REFRESHTIME = "tyuser_svcmsg_refreshtime";

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("TyUserSvcTaskRefreshJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            Integer userId = Integer.parseInt(body);

            TianYuanUser user = tianYuanUserService.selectById(userId);
            doRefresh(user, true);
        } catch (Exception e) {
            logger.error("处理天远token刷新队列消息异常.", e);
        }
    }

    /**
     * 执行任务和消息的刷新
     *
     * @param user
     * @param forceRefresh
     * @return
     */
    public DoubleVo<int[], int[]> doRefresh(TianYuanUser user, boolean forceRefresh) {
        if (user == null)
            return null;

        int[] taskCount = doRefreshTask(user, forceRefresh);
        int[] msgCount = doRefreshMsg(user, forceRefresh);
        return new DoubleVo<>(taskCount, msgCount);
    }

    /**
     * 刷新任务
     *
     * @param user
     * @param forceRefresh
     * @return
     */
    public int[] doRefreshTask(TianYuanUser user, boolean forceRefresh) {
        long lastRefreshTime = getLastRefreshTime(TYUSER_SVCTASK_REFRESHTIME, user.getId());
        long currentTime = System.currentTimeMillis();
        boolean doRefresh = false;
        if (currentTime - lastRefreshTime >= 1000l * 60 * refreshMinutes || forceRefresh) {
            doRefresh = true;
        }

        if (!doRefresh)
            return new int[]{0, -1};

        return refreshTaskByTime(user, new Date(lastRefreshTime), true);
    }

    /**
     * 刷新消息
     *
     * @param user
     * @param forceRefresh
     * @return
     */
    public int[] doRefreshMsg(TianYuanUser user, boolean forceRefresh) {
        long lastRefreshTime = getLastRefreshTime(TYUSER_SVCMSG_REFRESHTIME, user.getId());
        long currentTime = System.currentTimeMillis();
        boolean doRefresh = false;
        if (currentTime - lastRefreshTime >= 1000l * 60 * refreshMinutes || forceRefresh) {
            doRefresh = true;
        }

        if (!doRefresh)
            return new int[]{0, -1};

        return refreshMsgByTime(user, new Date(lastRefreshTime), true);
    }

    /**
     * 刷新某个时间以来的任务
     *
     * @param user
     * @param sendTime
     * @param saveRefreshTime
     * @return
     */
    public int[] refreshTaskByTime(TianYuanUser user, Date sendTime, boolean saveRefreshTime) {
        /**
         * 刷新task
         * 1、获取上次时间以来的任务
         * 2、对任务进行过滤：已存在的任务进行比对、替换，
         * 3、将任务存入SvcTask，将当前用户和task的关联从存入SvcTaskPg
         * 4、为每个任务初始化各个子模块数据，后续只需更新不需考虑insert事宜；不符合的任务类别暂时忽略
         */
        List<SvcTask> taskList = null;
        long currentTime = System.currentTimeMillis();
        try {
            taskList = tianYuanSvcService.getSvcTask(user.getOprtId(), user.getAccessToken(), sendTime);
            if (saveRefreshTime)
                saveLastRefreshTime(TYUSER_SVCTASK_REFRESHTIME, user.getId(), currentTime);//记录刷新时间
        } catch (Exception e) {
            logger.error("刷新天远用户服务日志任务列表异常.oprtId=" + user.getOprtId(), e);
            return new int[]{0, -1};
        }
//        logger.debug("刷新服务日志任务列表.得到数量:" + taskList.size() + ",开始时间:" + sendTime + ",用户=" + user.getUsername() + "," + user.getOprtName());

        long successCount = taskList.stream().map(task -> {
            task.setOprtId(user.getOprtId());
            task.setOprtName(user.getOprtName());
            try {
                svcTaskService.saveNewTask(task);
                return true;
            } catch (RestException e) {
                logger.error("保存收到的任务信息异常.rwh=" + task.getRwh() + ",oprtId=" + task.getOprtId() + ",uri=" + e.getUri(), e);
                return false;
            }
        }).filter(success -> success).count();
        return new int[]{taskList.size(), (int) successCount};
    }

    /**
     * 刷新某个时间以来的消息
     *
     * @param user
     * @param sendTime
     * @param saveRefreshTime
     * @return
     */
    public int[] refreshMsgByTime(TianYuanUser user, Date sendTime, boolean saveRefreshTime) {
        List<SvcMsg> msgList = null;
        long currentTime = System.currentTimeMillis();
        try {
            msgList = tianYuanSvcService.getSvcMsg(user.getOprtId(), user.getAccessToken(), sendTime);
            if (saveRefreshTime)
                saveLastRefreshTime(TYUSER_SVCMSG_REFRESHTIME, user.getId(), currentTime);//记录刷新时间
        } catch (Exception e) {
            logger.error("刷新天远用户服务日志消息列表异常." + user.getOprtId(), e);
            return new int[]{0, -1};
        }
//        logger.debug("刷新服务日志消息列表.得到数量:" + msgList.size() + ",开始时间:" + sendTime + ",用户=" + user.getUsername() + "," + user.getOprtName());

        long successCount = msgList.stream().map(msg -> {
            try {
                svcIdRestService.addOne(SvcMsg.class, msg);
            } catch (RestException e) {
                logger.error("保存收到的消息信息异常.msgType=" + msg.getMsgType() + ",rcvOprtName=" + msg.getRecOprtName() + ",uri=" + e.getUri(), e);
            }
            return msg;
        }).map(msg -> {
            try {
                consumeSvcMsg(msg, user.getOprtId());
            } catch (Exception e) {
                logger.error("处理服务日志消息时异常.msgContent=" + msg.getMsgContent() + ",oprtId=" + user.getOprtId(), e);
            }
            return msg;
        }).count();
        return new int[]{msgList.size(), (int) successCount};
    }

    protected void updateTaskRwzt(SvcTask task, String newRwzt) {
        if (!newRwzt.equals(task.getRwzt())) {
            task.setRwzt(newRwzt);
            svcMainRestService.updateSvcMainEntity(SvcTask.class,SvcTask.class.getSimpleName(), task);
        }
    }

    /**
     * 消息处理，参照手机端
     * @param svcMsg
     * @param oprtId
     */
    public void consumeSvcMsg(SvcMsg svcMsg, String oprtId) {
        //有可能任务不存在但是有消息
        String msgType = svcMsg.getMsgType();
        if (!"1".equals(msgType)) {
            return;
        }

        String msgContent = svcMsg.getMsgContent();
        String[] t = msgContent.split("#");
        if ((t.length == 3 || t.length == 4) && !"Svc_Sqgd".equals(t[0])) {
            //继续
        } else {
            return;
        }

        String rwh = t[1];
        SvcTask task = svcTaskService.getSvcTask(rwh, oprtId);
        if (task == null)
            return;

        String rwzt = t[2];
        if (!"5".equals(rwzt)) {
            updateTaskRwzt(task, rwzt);
            return;
        }

        //1故障处理///////////////////////////////////////////////////////////////////
        //1、检测+处理（是否修复为是） 填写完成 审核的消失；
        //2、检测+处理（是否修复为否） 填写完成 审核的不消失；
        //3、检测（1234） 填写完成+结果内容（是否完成为是） 审核消失；.用户拒绝修理(价格高) 2. 用户拒绝修理(周期长) 3.用户拖延使用故障品4.用户无时间
        //3、检测（1234） 填写完成+结果内容（是否完成为否） 审核不消失；
        //4、检测（非1234）填写完成 审核不消失；
        String fwlb = task.getFwlb();
        String orderKey = t[0];
        int rwztInt = Integer.parseInt(rwzt);
        if ("故障处理".equals(fwlb) && rwztInt < 5) {
            SvcGzclMain svcGzclMain = (SvcGzclMain) svcMainRestService.getOrderByRwhOprtId(SvcGzclMain.class, SvcGzclMain.class.getSimpleName(), rwh, oprtId);
            //Svc_Gzcl_Main\Svc_TSI\Svc_Claim
            /*
            * 故障处理审核业务逻辑：
            //第一步：日志审核通过，修改状态位为-1，可继续填写上报；
            //第二步：TSI审核通过
                      未进行故障处理+未进行故障处理原因填写为1234+结果内容为完成,修改状态位为5，隐藏该任务单；
                  否则，修改状态位为-1，可继续填写上报；
            //第三步：索赔报告审核通过
                  是否修复为是，修改状态位为5，隐藏该任务单；
                  否则，修改状态位为-1，可继续填写上报；
             *
             * */

            // 故障检测日志--》（未进行故障处理，原因填写为1234+结果内容为完成+日志+TSI审核）
            // 故障检测日志+故障处理日志--》（是否修复为修复+日志+TSI+索赔报告）

            //前提：日志、TSI、索赔报告顺序执行

            //故障处理 日志审核通过，不可以修改手机端，不通过可以修改；TSI审核通过不可修改，TSI审核不过可以修改；索赔审核通过消失，审核不过，可以修改
            //待检查日志通过,可以修改

            //第一步：日志审核通过	格式： Svc_Gzcl_Main#999#5
            if ("Svc_Gzcl_Main".equals(orderKey)) {
                if ("待检测".equals(svcGzclMain.getGzjcWtrhjj())) {
                    updateTaskRwzt(task, "2");
                }
            } else if ("Svc_TSI".equals(orderKey) && t.length == 4) {  //第二步：TSI审核通过	格式： Svc_TSI#999#5#SvcGM_gzjc_wtrhjj:待检测,tSvcGM_gzjc_wjxgzclyy:用户拒绝修理(价格高),SvcCJ_jgnr:完成
                String[] ttt = t[3].split(",");
                if (ttt.length == 4) {
                    String[] ttt1 = ttt[0].split(":");
                    String[] ttt2 = ttt[1].split(":");
                    String[] ttt3 = ttt[2].split(":");
                    String[] ttt4 = ttt[3].split(":");

                    //未进行故障处理+原因填写为1234+结果内容为完成
                    if ("否".equals(ttt4[1])) {
                        if (("用户拒绝修理(价格高)".equals(ttt2[1]) ||
                                "用户拒绝修理(周期长)".equals(ttt2[1]) ||
                                "用户拖延使用故障品".equals(ttt2[1]) ||
                                "用户无时间".equals(ttt2[1])) &&
                                "完成".equals(ttt3[1])) {
                            updateTaskRwzt(task, "5");
                        } else {
                            updateTaskRwzt(task, "2");
                        }
                    }

                    if ("1".equals(svcGzclMain.getGzclTxbz()) && "1".equals(svcGzclMain.getGzjcTxbz()) && "否".equals(svcGzclMain.getGzclSfxf())) {//是否修复
                        updateTaskRwzt(task, "2");
                    }
                }
            } else //第三步：索赔报告审核通过	格式： Svc_Claim#999#5#SvcGM_gzcl_sfxf:是
                if ("Svc_Claim".equals(orderKey) && t.length == 4) {
                    String ttt = t[3];
                    String[] ttt1 = ttt.split(":");
                    if ("是".equals(ttt1[1])) {
                        updateTaskRwzt(task, "5");
                    } else {
                        updateTaskRwzt(task, "-1");
                    }
                }
        }

        //1工厂改装	格式： Svc_Gcgz_Main#999#5#SvcCJ_jgnr:未完成
        if ("工厂改装".equals(fwlb) && rwztInt < 5) {
            //Svc_Gzcl_Main\Svc_Claim
            //日志审核-有效、待检测
            if ("Svc_Gcgz_Main".equals(orderKey)) {
                String ttt = t[3];
                String[] ttt1 = ttt.split(":");
                if ("未完成".equals(ttt1[1])) {
                    updateTaskRwzt(task, "2");
                }
            } else if ("Svc_Claim".equals(orderKey)) {
                updateTaskRwzt(task, "5");
            }
        } else if ("现场调查".equals(fwlb) && rwztInt < 5) { //2现场调查	格式： Svc_Xcdc#999#5#SvcCJ_jgnr:未完成
            //Svc_Xcdc\Svc_TSI
            //日志审核-有效、待检测
            if ("Svc_Xcdc".equals(orderKey)) {
                String ttt = t[3];
                String[] ttt1 = ttt.split(":");
                if ("未完成".equals(ttt1[1])) {
                    updateTaskRwzt(task, "2");
                }
            } else if ("Svc_TSI".equals(orderKey)) {
                updateTaskRwzt(task, "5");
            }
        } else if ("交机服务".equals(fwlb) && rwztInt < 5 && t.length == 4) {//格式： Svc_Jjfw_Main#999#5#SvcCJ_jgnr:完成
            String ttt = t[3];
            String[] ttt1 = ttt.split(":");
            if ("Svc_Jjfw_Main".equals(orderKey) && "完成".equals(ttt1[1])) {
                updateTaskRwzt(task, "5");
            } else {
                updateTaskRwzt(task, "2");
            }
        } else if ("出保服务".equals(fwlb) && rwztInt < 5 && t.length == 4) {
            String ttt = t[3];
            String[] ttt1 = ttt.split(":");
            if ("Svc_Cbfw_Main".equals(orderKey) && "完成".equals(ttt1[1])) {
                updateTaskRwzt(task, "5");
            } else {
                updateTaskRwzt(task, "2");
            }
        } else if ("定期保养".equals(fwlb) && rwztInt < 5 && t.length == 4) {
            String ttt = t[3];
            String[] ttt1 = ttt.split(":");
            if ("Svc_Dqby_Main".equals(orderKey) && "完成".equals(ttt1[1])) {
                updateTaskRwzt(task, "5");
            } else {
                updateTaskRwzt(task, "2");
            }
        } else if ("走访检查".equals(fwlb) && rwztInt < 5 && t.length == 4) {
            String ttt = t[3];
            String[] ttt1 = ttt.split(":");
            if ("Svc_Zfjc_Main".equals(orderKey) && "完成".equals(ttt1[1])) {
                updateTaskRwzt(task, "5");
            } else {
                updateTaskRwzt(task, "2");
            }
        } else if ("零件销售".equals(fwlb) && rwztInt < 5 && t.length == 4) {
            String ttt = t[3];
            String[] ttt1 = ttt.split(":");
            if ("Svc_Ljxs".equals(orderKey) && "完成".equals(ttt1[1])) {
                updateTaskRwzt(task, "5");
            } else {
                updateTaskRwzt(task, "2");
            }
        } else if ("公司改装".equals(fwlb) && rwztInt < 5 && t.length == 4) {
            String ttt = t[3];
            String[] ttt1 = ttt.split(":");
            if ("Svc_Gsgz_Main".equals(orderKey) && "完成".equals(ttt1[1])) {
                updateTaskRwzt(task, "5");
            } else {
                updateTaskRwzt(task, "2");
            }
        } else if ("债权事务".equals(fwlb) && rwztInt < 5 && t.length == 4) {
            String ttt = t[3];
            String[] ttt1 = ttt.split(":");
            if ("Svc_Zqsw".equals(orderKey) && "完成".equals(ttt1[1])) {
                updateTaskRwzt(task, "5");
            } else {
                updateTaskRwzt(task, "2");
            }
        }
    }

    private long getLastRefreshTime(String refreshKey, int userId) {
        Long time = (Long) jedisTemplate.opsForValue().get(refreshKey + ":" + userId);
        return time == null ? System.currentTimeMillis() - 90l * 24 * 60 * 60 * 1000 : time.longValue();//90天以前
    }

    private void saveLastRefreshTime(String refreshKey, int userId, long time) {
        jedisTemplate.opsForValue().set(refreshKey + ":" + userId, time, 100, TimeUnit.DAYS);//时间保存100天
    }
}
