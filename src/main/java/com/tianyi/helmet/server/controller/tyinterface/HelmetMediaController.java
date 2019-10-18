package com.tianyi.helmet.server.controller.tyinterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sagittarius.bean.result.StringPoint;
import com.tianyi.helmet.server.controller.interceptor.OperaLogHolder;
import com.tianyi.helmet.server.dao.data.HelmetOnlineStatusDao;
import com.tianyi.helmet.server.dao.file.VideoLocationDao;
import com.tianyi.helmet.server.entity.client.Company;
import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.HelmetOnlineStatus;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.*;
import com.tianyi.helmet.server.entity.scene.svc.ContactState;
import com.tianyi.helmet.server.entity.tianyuan.HelmetVideoInfo;
import com.tianyi.helmet.server.service.client.CompanyService;
import com.tianyi.helmet.server.service.client.HelmetUniversalService;
import com.tianyi.helmet.server.service.client.NeteaseUserService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.data.HelmetOnlineStatusService;
import com.tianyi.helmet.server.service.data.TyBoxDataService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.*;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.job.VideoWorkOrderProcessJob;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.kmx.MetaDataInitService;
import com.tianyi.helmet.server.service.rabbitmq.MQProducer;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanException;
import com.tianyi.helmet.server.service.tianyuan.TianYuanMapApiHelper;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.AppAccountVo;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import com.tianyi.helmet.server.vo.rabbitmq.RabbitMqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.tianyi.helmet.server.service.kmx.SensorSupport.TYBOX_DEVICE_IDPREFIX;

/**
 * 媒体模块，包括图像和视频
 * <p>
 * Created by txj on 2019/03/13.
 */
@Controller
@RequestMapping("helmetmedia")
@Api(value = "api", description = "头盔视频查询管理")
public class HelmetMediaController {

    private static final Logger logger = LoggerFactory.getLogger(HelmetMediaController.class);
    @Autowired
    private RedisMqPublisher redisMqPublisher;
    @Autowired
    private VideoService videoService;
    @Autowired
    private HelmetOnlineStatusDao helmetOnlineStatusDao;
    @Autowired
    private ImageService imageService;

    @Autowired
    private AudioService audioService;

    @Autowired
    private TianYuanMapApiHelper tianYuanMapApiHelper;

    @Autowired
    private VideoKeywordService videoKeywordService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private HelmetOnlineStatusService helmetOnlineStatusService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private VideoWorkOrderProcessJob videoWorkOrderProcessJob;

    @Autowired
    private MQProducer mqProducer;
    @Autowired
    private MetaDataInitService metaDataInitService;
    @Autowired
    private HelmetUniversalService helmetUniversalService;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private VideoLocationDao videoLocationDao;
    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private TyBoxDataService tyBoxDataService;
    @Autowired
    private KmxService kmxService;
    /**
     * 获取工单关联视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取视频信息")
    @RequestMapping(value = "/video/getById", method = RequestMethod.POST)
    public ResponseVo<HelmetVideoInfo> loadVideoData(@ApiParam(name = "vid", value = "视频ID", required = true) @RequestParam(required = true) String vid) {
        if(vid == null || "".equalsIgnoreCase(vid)) {
            return ResponseVo.fail("参数vid不能为空");
        }
        Video v = videoService.selectById(Integer.parseInt(vid));
        if(v==null) {
            return ResponseVo.fail("视频vid不存在");
        }
        String fastdfsServerUrl = configService.getFastdfsServerUrl();
        HelmetVideoInfo hv = new HelmetVideoInfo();
        hv.setId(v.getId());
        hv.setOssPath(fastdfsServerUrl + v.getOssPath());
        hv.setSeconds(v.getSeconds());
        hv.setSizeKb(v.getSizeKb());
        hv.setThumbOssPath(fastdfsServerUrl + v.getThumbOssPath());
        hv.setCreateTime(v.getCreateTime());
        hv.setOrderId(v.getOrderNo());
        if(v.getLon()!=-1.0 && v.getLat()!= -1.0) {
            String location = "";
            try {
                JSONObject jo = tianYuanMapApiHelper.getLocation(v.getLon(), v.getLat(), false);
                if(jo!=null) {
                    JSONArray ja = jo.getJSONArray("descdata");
                    JSONObject ja0 = (JSONObject) ja.get(0);
                    location = ja0.getString("description");
                }
            } catch (TianYuanException e){
            }
            hv.setLocation(location);
        }
        List<VideoKeyword> kl=videoKeywordService.selectByVideoId(v.getId());
        hv.setKeywords(kl);
        return ResponseVo.success(hv);
    }

    /**
     * 获取工单关联视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取工单关联视频列表")
    @RequestMapping(value = "/video/list", method = RequestMethod.POST)
    public ResponseVo<List<HelmetVideoInfo>> loadSearchResultData(@ApiParam(name = "orderNo", value = "工单号", required = true) @RequestParam(required = true) String orderNo,
                                                                  @ApiParam(name = "tyaccount", value = "天远账号", required = false) @RequestParam(required = false) String tyaccount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderBy", 2);
        if(orderNo != null && !"".equalsIgnoreCase(orderNo)) {
            params.put("orderNo", orderNo);
        }
        if(tyaccount != null && !"".equalsIgnoreCase(tyaccount)) {
            params.put("tyaccount", tyaccount);
        }
        List<Video> list = videoService.listBy(params);
        List<HelmetVideoInfo> hlist = new ArrayList<HelmetVideoInfo>();
        String fastdfsServerUrl = configService.getFastdfsServerUrl();
        for (Video v: list) {
            HelmetVideoInfo hv = getHelmetVideoInfo(fastdfsServerUrl, v);
            hlist.add(hv);
        }
        return ResponseVo.success(hlist);
    }

    private HelmetVideoInfo getHelmetVideoInfo(String fastdfsServerUrl, Video v) {
        HelmetVideoInfo hv = new HelmetVideoInfo();
        hv.setId(v.getId());
        hv.setOssPath(fastdfsServerUrl + v.getOssPath());
        hv.setSeconds(v.getSeconds());
        hv.setSizeKb(v.getSizeKb());
        hv.setThumbOssPath(fastdfsServerUrl + v.getThumbOssPath());
        hv.setCreateTime(v.getCreateTime());
        hv.setLon(v.getLon());
        hv.setLat(v.getLat());
        if(v.getLon()!=-1.0 && v.getLat()!= -1.0) {
            String location = "";
            try {
                JSONObject jo = tianYuanMapApiHelper.getLocation(v.getLon(), v.getLat(), false);
                if(jo!=null) {
                    JSONArray ja = jo.getJSONArray("descdata");
                    JSONObject ja0 = (JSONObject) ja.get(0);
                    location = ja0.getString("description");
                }
            } catch (TianYuanException e){

            }
            hv.setLocation(location);
        }
        List<VideoKeyword> kl=videoKeywordService.selectByVideoId(v.getId());
        hv.setKeywords(kl);
        User user = userService.selectById(v.getUserId());
        if(user!=null) {
            hv.setTyaccount(user.getTianyuanAccount());
        }
        if(!StringUtils.isEmpty(v.getCalled()) || !StringUtils.isEmpty(v.getCaller())) {
            hv.setAzvideo(1);
        }
        return hv;
    }

    /**
     * 获取工单关联视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取昨天上传的视频列表")
    @RequestMapping(value = "/video/yestoday", method = RequestMethod.POST)
    public ResponseVo<List<HelmetVideoInfo>> loadYestodayVideo() {
        Map<String, Object> params = new HashMap<String, Object>();
        LocalDateTime yestoday = LocalDateTime.now().minusDays(1);
        //构造时间
        LocalDateTime startTime = LocalDateTime.of(yestoday.getYear(), yestoday.getMonth(), yestoday.getDayOfMonth(), 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(yestoday.getYear(), yestoday.getMonth(), yestoday.getDayOfMonth(), 23, 59, 59);

        params.put("uploadTime1", startTime);
        params.put("uploadTime2", endTime);
        List<Video> list = videoService.listBy(params);
        List<HelmetVideoInfo> hlist = new ArrayList<HelmetVideoInfo>();
        String fastdfsServerUrl = configService.getFastdfsServerUrl();
        for (Video v: list) {
            HelmetVideoInfo hv = new HelmetVideoInfo();
            hv.setId(v.getId());
            hv.setOssPath(fastdfsServerUrl + v.getOssPath());
            hv.setSeconds(v.getSeconds());
            hv.setSizeKb(v.getSizeKb());
            hv.setThumbOssPath(fastdfsServerUrl + v.getThumbOssPath());
            hv.setCreateTime(v.getCreateTime());
            hv.setLocation(String.valueOf(v.getId())+"_"+v.getCreateTimeString());
            if(v.getUserId() != -1){
                User user = userService.selectById(v.getUserId());
                hv.setLocation(hv.getLocation()+"_"+user.getName());
            }
            hlist.add(hv);
        }
        return ResponseVo.success(hlist);
    }
    /**
     * 获取工单关联视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取时间段上传的视频列表")
    @RequestMapping(value = "/video/section", method = RequestMethod.POST)
    public ResponseVo<List<HelmetVideoInfo>> loadSectionVideo(@RequestParam String time1, String time2) {
        Map<String, Object> params = new HashMap<String, Object>();
        LocalDate yestoday = LocalDate.now();
        LocalDate yestoday1 = LocalDate.now();
        if(time1 != null) {
            yestoday = Dates.toLocalDate(new Date(Long.parseLong(time1)));
        }
        if(time2 != null) {
            yestoday1 = Dates.toLocalDate(new Date(Long.parseLong(time2)));
        }
        //构造时间
        LocalDateTime startTime = LocalDateTime.of(yestoday.getYear(), yestoday.getMonth(), yestoday.getDayOfMonth(), 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(yestoday1.getYear(), yestoday1.getMonth(), yestoday1.getDayOfMonth(), 23, 59, 59);

        params.put("uploadTime1", startTime);
        params.put("uploadTime2", endTime);
        List<Video> list = videoService.listBy(params);
        List<HelmetVideoInfo> hlist = new ArrayList<HelmetVideoInfo>();
        String fastdfsServerUrl = configService.getFastdfsServerUrl();
        for (Video v: list) {
            HelmetVideoInfo hv = new HelmetVideoInfo();
            hv.setId(v.getId());
            hv.setOssPath(fastdfsServerUrl + v.getOssPath());
            hv.setSeconds(v.getSeconds());
            hv.setSizeKb(v.getSizeKb());
            hv.setThumbOssPath(fastdfsServerUrl + v.getThumbOssPath());
            hv.setCreateTime(v.getCreateTime());
            hv.setLocation(String.valueOf(v.getId())+"_"+v.getCreateTimeString());
            if(v.getUserId() != -1){
                User user = userService.selectById(v.getUserId());
                hv.setLocation(hv.getLocation()+"_"+user.getName());
            }
            hlist.add(hv);
        }
        return ResponseVo.success(hlist);
    }
    /**
     * 获取时间段内某个头盔录制的没有关联工单的视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取时间段内某个头盔录制的没有关联工单的视频列表")
    @RequestMapping(value = "/video/noorder", method = RequestMethod.POST)
    public ResponseVo<List<HelmetVideoInfo>> loadSectionVideo(@ApiParam(name = "time1", value = "开始时间戳", required = true) @RequestParam(required = true) String time1,
                                                              @ApiParam(name = "time2", value = "结束时间戳", required = true) @RequestParam(required = true) String time2,
                                                              @ApiParam(name = "deviceNo", value = "头盔编号", required = true) @RequestParam(required = true) String deviceNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        if(time1 != null) {
            startTime = Dates.toLocalDateTime(new Date(Long.parseLong(time1)));
        }
        if(time2 != null) {
            endTime = Dates.toLocalDateTime(new Date(Long.parseLong(time2)));
        }
        //构造时间
        params.put("createTime1", startTime);
        params.put("createTime2", endTime);
        params.put("deviceNo", deviceNo);
        List<Video> list = videoService.listBy(params);
        List<HelmetVideoInfo> hlist = new ArrayList<HelmetVideoInfo>();
        String fastdfsServerUrl = configService.getFastdfsServerUrl();
        for (Video v: list) {
            HelmetVideoInfo hv = new HelmetVideoInfo();
            hv.setId(v.getId());
            hv.setOssPath(fastdfsServerUrl + v.getOssPath());
            hv.setSeconds(v.getSeconds());
            hv.setSizeKb(v.getSizeKb());
            hv.setThumbOssPath(fastdfsServerUrl + v.getThumbOssPath());
            hv.setCreateTime(v.getCreateTime());
            if(v.getLon()!=-1.0 && v.getLat()!= -1.0) {
                String location = "";
                try {
                    JSONObject jo = tianYuanMapApiHelper.getLocation(v.getLon(), v.getLat(), false);
                    if(jo!=null) {
                        JSONArray ja = jo.getJSONArray("descdata");
                        JSONObject ja0 = (JSONObject) ja.get(0);
                        location = ja0.getString("description");
                    }
                } catch (TianYuanException e){

                }
                hv.setLocation(location);
            }
            hlist.add(hv);
        }
        return ResponseVo.success(hlist);
    }

    /**
     * 获取天远账号关联的网易云账号信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取天远账号关联的网易云账号信息")
    @RequestMapping(value = "/get/netuser", method = RequestMethod.POST)
    public ResponseVo<Map<String, String>> netuser(@ApiParam(name = "tyuserNo", value = "天远账号", required = true) @RequestParam(required = true) String tyuserNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        User user = userService.selectByTianyuanAccount(tyuserNo);
        Map<String, String> map = new HashMap<>();
        if(user!=null) {
            String neUserWeb = user.getNeUserWeb();
            NeteaseUser neteaseUser = neteaseUserService.selectByUsername(neUserWeb);
            map.put("yunId", neteaseUser.getYunId());
            map.put("yunToken", neteaseUser.getYunToken());
            map.put("userName", user.getName());
        }
        return ResponseVo.success(map);
    }

    /**
     * 获取用户的网易云账号信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取用户的网易云账号信息")
    @RequestMapping(value = "/get/aguser", method = RequestMethod.POST)
    public ResponseVo<Map<String, String>> aguser(@ApiParam(name = "loginUserAccount", value = "用户账号", required = true) @RequestParam(required = true) String loginUserAccount,@ApiParam(name = "loginUserPwd", value = "用户密码", required = true) @RequestParam(required = true) String loginUserPwd) {
        Map<String, Object> params = new HashMap<String, Object>();
        User user = userService.selectByAccount(loginUserAccount);
        Map<String, String> map = new HashMap<>();
        if(user!=null) {
            if (!loginUserPwd.equals(user.getPassword())) {
                return ResponseVo.fail("601", "密码错误");
            }
            String neUserWeb = user.getNeUserWeb();
            NeteaseUser neteaseUser = neteaseUserService.selectByUsername(neUserWeb);
            map.put("yunId", neteaseUser.getYunId());
            map.put("yunToken", neteaseUser.getYunToken());
            map.put("userName", user.getName());
        }
        return ResponseVo.success(map);
    }

    /**
     * 获取在线用户网易云账号信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取在线用户网易云账号信息")
    @RequestMapping(value = "/get/online/netuser", method = RequestMethod.POST)
    public ResponseVo<List<Map<String, String>>> netuser() {
        Map<String, Object> params = new HashMap<String, Object>();
        List<HelmetOnlineStatus> onlineUser = helmetOnlineStatusService.getOnlineUser();
        List<Map<String, String>> result = new ArrayList<>();
        for(HelmetOnlineStatus onlineStatus: onlineUser){
            Map<String, String> map = new HashMap<>();
            EquipmentLedger equipmentLedger = equipmentService.selectByUUID(onlineStatus.getClientId());
            if(equipmentLedger == null) {// redis包含系统中未知头盔信息
                continue;
            }
            map.put("helmetNo", equipmentLedger.getDeviceNumber());
            if(onlineStatus.getUserId() != null){
                int userId = onlineStatus.getUserId().intValue();
                User user = userService.selectById(userId);
                if(user != null) {
                    map.put("tyaccount", user.getTianyuanAccount());
                }
            } else {
                map.put("tyaccount", null);
            }
            map.put("userName", onlineStatus.getUserName());
            map.put("netuserNo", onlineStatus.getNeUsername());//头盔网易云账号

            Object gpsObj = redisTemplate.opsForValue().get(CacheKeyConstants.HELMET_GPS_NEWEST_BY_IMEI + ":" + onlineStatus.getClientId());
            if(gpsObj != null) {
                JSONObject jsonObject = JSONObject.parseObject(gpsObj.toString());
                if(jsonObject!=null) {
                    map.put("lat", jsonObject.getString("lat"));
                    map.put("lon", jsonObject.getString("lon"));
                }
            }

            result.add(map);
        }
        return ResponseVo.success(result);
    }

    /**
     * 获取工单关联视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "根据服务人员账号、时间范围、工单号获取视频列表并更新工单号")
    @RequestMapping(value = "/video/linkvideo", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public ResponseVo linkVideo(@ApiParam(name = "workOrderNo", value = "智能服务工单号", required = true) @RequestParam(required = true) String workOrderNo,
                                                             @ApiParam(name = "tyaccountId", value = "智能服务用户账号ID", required = true) @RequestParam(required = true) String tyaccountId,
                                                             @ApiParam(name = "time1", value = "开始时间戳（毫秒）", required = true) @RequestParam(required = true) String time1,
                                                             @ApiParam(name = "time2", value = "结束时间戳（毫秒）", required = true) @RequestParam(required = true) String time2) {
        Map<String, Object> params = new HashMap<String, Object>();
        LocalDateTime yestoday = LocalDateTime.now();
        LocalDateTime yestoday1 = LocalDateTime.now();
        if(time1 != null) {
            yestoday = Dates.toLocalDateTime(new Date(Long.parseLong(time1)));
        }
        if(time2 != null) {
            yestoday1 = Dates.toLocalDateTime(new Date(Long.parseLong(time2)));
        }
        //构造时间
        LocalDateTime startTime = yestoday;
        LocalDateTime endTime = yestoday1;
        params.put("createTime1", startTime);
        params.put("createTime2", endTime);
//        params.put("tyaccount", tyaccountId);
        List<Video> list = videoService.listBy(params);
        User tyUser = userService.selectByTianyuanAccount(tyaccountId);
        if(tyUser==null) {
            return ResponseVo.fail("用户不存在:"+tyaccountId);
        }
        String neuserHel = tyUser.getNeUserHel();
        String neuserWeb = tyUser.getNeUserWeb();
        String neuserPhn = tyUser.getNeUserPhn();
        if(neuserHel == null || neuserPhn == null || neuserWeb==null) {
            return ResponseVo.fail("用户没有绑定安正用户:"+tyaccountId);
        }
        for (Video v: list) {
            /**
             * 1、过滤视频工单不为空的数据
             * 2、更新工单号进视频列表
             *
             */
            if(v.getOrderNo() != null && !v.getOrderNo().equals("")){
                continue;
            }
            if(v.getCalled()!=null && v.getCaller()!=null) {
                if(neuserHel.equals(v.getCaller()) || neuserWeb.equals(v.getCaller()) || neuserPhn.equals(v.getCaller())
                        || neuserHel.equals(v.getCalled()) || neuserWeb.equals(v.getCalled()) || neuserPhn.equals(v.getCalled())) {
                    v.setOrderNo(workOrderNo);
                    videoService.updateById(v);
                    //zhouwei 20190530 补充视频工单信息
                    videoWorkOrderProcessJob.doProcessVideoAsr(v);
                }
            }
        }
        return ResponseVo.success();
    }

    /**
     * 获取工单关联视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "根据经纬度、时间范围、人员、工单号获取视频列表并更新工单号")
    @RequestMapping(value = "/video/linkorder", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public ResponseVo<List<HelmetVideoInfo>> loadLinkedVideo(@ApiParam(name = "workOrderNo", value = "智能服务工单号", required = true) @RequestParam(required = true) String workOrderNo,
                                                             @ApiParam(name = "tyaccountId", value = "智能服务用户账号ID", required = true) @RequestParam(required = true) String tyaccountId,
                                                             @ApiParam(name = "lonlatStr", value = "经纬度", required = true) @RequestParam(required = true) String lonlatStr,
                                                             @ApiParam(name = "time1", value = "开始时间戳（毫秒）", required = true) @RequestParam(required = true) String time1,
                                                             @ApiParam(name = "time2", value = "结束时间戳（毫秒）", required = true) @RequestParam(required = true) String time2) {

        JSONArray lonlat = (JSONArray) JSONArray.parse(lonlatStr);
        Map<String, Object> params = new HashMap<String, Object>();
        LocalDateTime yestoday = LocalDateTime.now();
        LocalDateTime yestoday1 = LocalDateTime.now();
        if(time1 != null) {
            yestoday = Dates.toLocalDateTime(new Date(Long.parseLong(time1)));
        }
        if(time2 != null) {
            yestoday1 = Dates.toLocalDateTime(new Date(Long.parseLong(time2)));
        }
        //构造时间
        LocalDateTime startTime = yestoday;
        LocalDateTime endTime = yestoday1;

        params.put("createTime1", startTime);
        params.put("createTime2", endTime);
        params.put("tyaccount", tyaccountId);

        List<Video> list = videoService.listBy(params);
        List<HelmetVideoInfo> hlist = new ArrayList<HelmetVideoInfo>();
        String fastdfsServerUrl = configService.getFastdfsServerUrl();
        for (Video v: list) {
            HelmetVideoInfo hv = new HelmetVideoInfo();
            hv.setId(v.getId());
            hv.setOssPath(fastdfsServerUrl + v.getOssPath());
            hv.setSeconds(v.getSeconds());
            hv.setSizeKb(v.getSizeKb());
            hv.setThumbOssPath(fastdfsServerUrl + v.getThumbOssPath());
            hv.setCreateTime(v.getCreateTime());
            hv.setLocation(String.valueOf(v.getId())+"_"+v.getCreateTimeString());
            if(v.getUserId() != -1){
                User user = userService.selectById(v.getUserId());
                hv.setLocation(hv.getLocation()+"_"+user.getName());
            }
            /**
             * 1、过滤视频地理位置在范围内
             *    工单不为空的数据
             * 2、更新工单号进视频列表
             *
             */
            if(v.getOrderNo() != null && !v.getOrderNo().equals("")){
                continue;
            }
            for(Object llTmp : lonlat){
                JSONObject ll = (JSONObject) llTmp;
                double lon = ((BigDecimal) ll.get("lon")).doubleValue();
                double lat =  ((BigDecimal) ll.get("lat")).doubleValue();
                double distence = 0;
                if(v.getLon()!=-1 && v.getLat() != -1){
                    distence =getDistance(lon,lat,v.getLon(),v.getLat());
                } else {
                    continue;
                }
                if(distence < 2000){
                    v.setOrderNo(workOrderNo);
                    videoService.updateById(v);
                    hlist.add(hv);
                    //zhouwei 20190530 补充视频工单信息
                    videoWorkOrderProcessJob.doProcessVideoAsr(v);
                }
            }

        }
        return ResponseVo.success(hlist);
    }

    private double EARTH_RADIUS = 6378.137;

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }

    /**
     * 获取工单关联视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取工单关联资料列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseVo<Map<String, Object>> loadResultData(@ApiParam(name = "orderNo", value = "工单号", required = true) @RequestParam(required = true) String orderNo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderBy", 2);
        if(orderNo != null && !"".equalsIgnoreCase(orderNo)) {
            params.put("orderNo", orderNo);
        }
        Map<String, Object> result = new HashMap<>();
        String fastdfsServerUrl = configService.getFastdfsServerUrl();
        Map<String, Object> vparams = new HashMap<String, Object>();
        vparams.put("orderBy", 2);
        vparams.put("workId", orderNo);
        List<Video> list = videoService.listBy(vparams);
        List<Map<String, Object>> vlist = new ArrayList<>();
        for (Video v: list) {
            Map<String, Object> hv = new HashMap<>();
            hv.put("id",v.getId());
            hv.put("ossPath", fastdfsServerUrl + v.getOssPath());
            hv.put("thumbOssPath", fastdfsServerUrl + v.getThumbOssPath());
            hv.put("createTime" ,v.getCreateTime());
            hv.put("seconds",v.getSeconds());
            hv.put("sizeKb", v.getSizeKb());
            if(v.getLon()!=-1.0 && v.getLat()!= -1.0) {
                String location = "";
                try {
                    JSONObject jo = tianYuanMapApiHelper.getLocation(v.getLon(), v.getLat(), false);
                    if(jo!=null) {
                        JSONArray ja = jo.getJSONArray("descdata");
                        JSONObject ja0 = (JSONObject) ja.get(0);
                        location = ja0.getString("description");
                    }
                } catch (TianYuanException e){

                }
                hv.put("location", location);
            }
            vlist.add(hv);
        }
        result.put("videoList", vlist);

        List<Image> list1 = imageService.listBy(params);
        List<Map<String, Object>> ilist = new ArrayList<>();
        for (Image v: list1) {
            Map<String, Object> hv = new HashMap<>();
            hv.put("id",v.getId());
            hv.put("ossPath", fastdfsServerUrl + v.getOssPath());
            hv.put("thumbOssPath", fastdfsServerUrl + v.getThumbOssPath());
            hv.put("createTime" ,v.getCreateTime());
            if(v.getLon()!=-1.0 && v.getLat()!= -1.0) {
                String location = "";
                try {
                    JSONObject jo = tianYuanMapApiHelper.getLocation(v.getLon(), v.getLat(), false);
                    if(jo!=null) {
                        JSONArray ja = jo.getJSONArray("descdata");
                        JSONObject ja0 = (JSONObject) ja.get(0);
                        location = ja0.getString("description");
                    }
                } catch (TianYuanException e){

                }
                hv.put("location", location);
            }
            ilist.add(hv);
        }
        result.put("imageList", ilist);

        List<Audio> list2 = audioService.listBy(params);
        List<Map<String, Object>> alist = new ArrayList<>();
        for (Audio v: list2) {
            Map<String, Object> hv = new HashMap<>();
            hv.put("id",v.getId());
            hv.put("ossPath", fastdfsServerUrl + v.getOssPath());
            hv.put("createTime" ,v.getCreateTime());
            alist.add(hv);
        }
        result.put("audioList", alist);

        return ResponseVo.success(result);
    }

    /**
     * 获取在线用户网易云账号信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "更新用户音视频账号信息")
    @RequestMapping(value = "/update/aguser", method = RequestMethod.POST)
    public ResponseVo updateAguser() {
        Map<String, Object> params = new HashMap<String, Object>();
        List<User> userList = userService.selectByNoNetUser(params);
        for(User user: userList){
            userService.updateNetUser(user);
        }
        return ResponseVo.success("更新成功!");
    }

    @RequestMapping(value = "mydevice", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据天远账号获取头盔编号")
    public ResponseVo<Map<String, Object>> mydevice(@ApiParam(value = "天远账号", name = "tianyuanAccount") @RequestParam String tianyuanAccount){
        User user = userService.selectByTianyuanAccount(tianyuanAccount);
        if(user == null) {
            return ResponseVo.fail("天远账号没有绑定用户："+tianyuanAccount);
        }
        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
        Map<String, Object> queryInfo = new HashMap<>();
        if(list!=null && list.size() > 0) {
            queryInfo.put("deviceNumber", list.get(0).getDeviceNumber());
        } else {
            return ResponseVo.fail("天远账号没有绑定头盔："+tianyuanAccount);
        }
        return ResponseVo.success(queryInfo);
    }

    @RequestMapping(value = "myAccount", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据头盔用户ID获取天远账号")
    public ResponseVo<Map<String, Object>> myAccount(@ApiParam(value = "头盔用户ID", name = "userId") @RequestParam String userId){
        User user = userService.selectById(Integer.parseInt(userId));
        if(user == null) {
            return ResponseVo.fail("天远账号没有绑定用户："+userId);
        }
        Map<String, Object> queryInfo = new HashMap<>();
        if(user!=null && !StringUtils.isEmpty(user.getTianyuanAccount())) {
            queryInfo.put("account", user.getTianyuanAccount());
        } else {
            return ResponseVo.fail("天远账号没有头盔用户："+ userId);
        }
        return ResponseVo.success(queryInfo);
    }

    /**
     * 获取视频列表
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取视频列表")
    @RequestMapping(value = "/video/listby", method = RequestMethod.POST)
    public ResponseVo<List<HelmetVideoInfo>> loadVideos(@RequestBody Map<String, Object> params) {
        if(params == null || params.keySet().size()<=0) {
            return ResponseVo.fail("参数不能为空");
        }
        List<Video> list = videoService.listBy(params);
        List<HelmetVideoInfo> hlist = new ArrayList<HelmetVideoInfo>();
        String fastdfsServerUrl = configService.getFastdfsServerUrl();
        for (Video v: list) {
            HelmetVideoInfo hv = new HelmetVideoInfo();
            hv.setId(v.getId());
            hv.setOssPath(fastdfsServerUrl + v.getOssPath());
            hv.setSeconds(v.getSeconds());
            hv.setSizeKb(v.getSizeKb());
            hv.setThumbOssPath(fastdfsServerUrl + v.getThumbOssPath());
            hv.setCreateTime(v.getCreateTime());
            hv.setLocation(String.valueOf(v.getId())+"_"+v.getCreateTimeString());
            if(v.getUserId() != -1){
                User user = userService.selectById(v.getUserId());
                hv.setLocation(hv.getLocation()+"_"+user.getName());
            }
            hlist.add(hv);
        }
        return ResponseVo.success(hlist);
    }

    /**
     * 获取在线用户网易云账号信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "rabbitmq测试消息发送")
    @RequestMapping(value = "/rabbitmq/test", method = RequestMethod.POST)
    public ResponseVo testRabbitmq(@ApiParam(value = "视频ID", name = "videoId") @RequestParam Integer videoId) {
        Video v = videoService.selectById(videoId);
        videoService.noticeToThird(v);
        return ResponseVo.success("测试成功!");
    }

    /**
     * 获取在线用户网易云账号信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "redismq测试消息发送toProcessFile，[417,20190923185503.gps,200070,UPLOAD];toNeUserCreate,netease")
    @RequestMapping(value = "/redismq/test", method = RequestMethod.POST)
    public void testRedismq(@ApiParam(value = "验证码", name = "captcha") @RequestParam String captcha,
                            @ApiParam(value = "文件上传解析redisKey", name = "redisKey") @RequestParam String redisKey,
                            @ApiParam(value = "文件上传解析boxbody", name = "redisBody") @RequestParam String redisBody) {
        //417,20190923185503.gps,200070,UPLOAD
        if(StringUtils.isEmpty(captcha) || !"tianyikeji".equals(captcha)) {
            return;
        }
        redisMqPublisher.sendMessage(redisKey, redisBody);//加入文件处理队列
    }

    /**
     * 初始化头盔kmx
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "初始化头盔kmx")
    @RequestMapping(value = "/kmxinitdevice/test", method = RequestMethod.POST)
    public void initKmxDevice(@ApiParam(value = "头盔IMEI", name = "deviceId") @RequestParam String deviceId) {
        if(StringUtils.isEmpty(deviceId)) {
            return;
        }
        metaDataInitService.initDeviceHelmetDevice(deviceId);
    }

    /**
     * 初始化视频地理位置信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "初始化视频地理位置信息")
    @RequestMapping(value = "/video/initlocation", method = RequestMethod.POST)
    public void initVideoLocation(@ApiParam(value = "验证码", name = "captcha") @RequestParam String captcha) {
        if(StringUtils.isEmpty(captcha) || !"tianyikeji".equals(captcha)) {
            return;
        }
        Map<String, Object> param = new HashMap<>();
        List<Video> list = videoService.listBy(param);
        for(Video v : list) {
            videoService.saveLocation(v);
        }
    }

    /**
     * 获取在线用户网易云账号信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "更新网易用户音视频账号信息")
    @RequestMapping(value = "/update/neteaseuser", method = RequestMethod.POST)
    public ResponseVo updateNeteaseuser(@ApiParam(value = "验证码", name = "captcha") @RequestParam String captcha) {
        if(StringUtils.isEmpty(captcha) || !"tianyikeji".equals(captcha)) {
            return ResponseVo.fail("验证码不正确");
        }
        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("company", "netease");
        List<User> userList = userService.selectByCompany(params);
        for(User user: userList){
            if(user.getNeUserHel() !=null && user.getNeUserHel().startsWith("hel")) {
                continue;
            }
            userService.updateNetUser(user);
        }
        return ResponseVo.success("更新成功!");
    }

    /**
     * 测试本地头盔端service方法
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "测试本地头盔端service方法")
    @RequestMapping(value = "/helmet/test", method = RequestMethod.POST)
    public ResponseVo testHelmet(@ApiParam(value = "验证码", name = "captcha") @RequestParam String captcha,
                                 @ApiParam(value = "uuid", name = "uuid") @RequestParam String uuid) {
        if(StringUtils.isEmpty(captcha) || !"tianyikeji".equals(captcha)) {
            return ResponseVo.fail("验证码不正确");
        }
        return helmetUniversalService.listContact(uuid);
    }

    /**
     * 【运营可视化】查询视频信息接口
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "【运营可视化】查询视频信息接口")
    @RequestMapping(value = "/videoSearch", method = RequestMethod.POST)
    public ResponseVo videoSearch(@ApiParam(value = "视频ID", name = "videoId") @RequestParam String videoId) {
        if(StringUtils.isEmpty(videoId)) {
            return ResponseVo.fail("视频ID不能为空");
        }
        Video video = videoService.selectById(Integer.parseInt(videoId));
        Map result = new HashMap();
        result.put("videoId", videoId);
        result.put("createTime", video.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        result.put("seconds", video.getSeconds());
        result.put("lon", video.getLon());
        result.put("lat", video.getLat());
        // location
        Map param = new HashMap();
        param.put("videoId", videoId);
        List<VideoLocation> list = videoLocationDao.listBy(param);
        if(list.size() > 0)
        result.put("location", list.get(0).getDescription());
        EquipmentLedger equipmentLedger = equipmentService.selectByUUID(video.getClientId());
        if(equipmentLedger != null) {
            result.put("deviceNumber", equipmentLedger.getDeviceNumber());
        }
        // userName
        User user = userService.selectById(video.getUserId());
        if(user != null) {
            result.put("userName", user.getName());
        }
        Company company = companyService.selectById(user.getOrganisation());
        if(company != null) {
            result.put("companyName", company.getCompanyName());
        }
        HelmetUniversalInfo helmetUniversalInfo = helmetUniversalService.selectByUserId(video.getUserId());
        if(helmetUniversalInfo!=null) {
            result.put("project", helmetUniversalInfo.getProject());
        }

        return ResponseVo.success(result);
    }

    /**
     * 【运营可视化】查询头盔台账信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "【运营可视化】查询头盔登录时长信息")
    @RequestMapping(value = "/deviceInfo", method = RequestMethod.POST)
    public ResponseVo deviceInfo(@ApiParam(value = "开始时间", name = "startTime") @RequestParam String startTime,
                                 @ApiParam(value = "截止时间", name = "endTime") @RequestParam String endTime) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(startTime)) {
            params.put("startTime", startTime);
        } else {
            return ResponseVo.fail("起始日期不能为空");
        }
        if (!StringUtils.isEmpty(endTime)) {
            params.put("endTime", endTime);
        } else {
            return ResponseVo.fail("截止日期不能为空");
        }
        List<Map<String, Object>> list = helmetOnlineStatusDao.getLoginTimesByDay(params);
        List<Map<String, Object>> resultList = new ArrayList();
        for (Map<String, Object> baseMap : list) {
            Map result = new HashMap();
            User user = userService.selectById(Integer.parseInt(baseMap.get("userId").toString()));
            if(user!=null){
                result.put("userName", user.getName());
                result.put("area", user.getDepartment());
                Company company = companyService.selectById(user.getOrganisation());
                if(company != null) {
                    result.put("companyName", company.getCompanyName());
                }
            } else {
                result.put("userName", "");
                result.put("area", "");
                result.put("companyName", "");
            }
            EquipmentLedger equipmentLedger=equipmentService.selectByUUID(baseMap.get("clientId").toString());
            if(equipmentLedger != null) {
                result.put("deviceNumber", equipmentLedger.getDeviceNumber());
            } else {
                result.put("deviceNumber", "");
            }
            result.put("loginDate", baseMap.get("loginDate"));
            result.put("bootTime", baseMap.get("loginSeconds"));
            resultList.add(result);
        }
        return ResponseVo.success(resultList);
    }

    /**
     * 【运营可视化】查询头盔台账信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "【运营可视化】查询头盔使用人变更记录")
    @RequestMapping(value = "/deviceUserChange", method = RequestMethod.POST)
    public ResponseVo deviceUserChange(@ApiParam(value = "开始时间", name = "startTime") @RequestParam String startTime,
                                 @ApiParam(value = "截止时间", name = "endTime") @RequestParam String endTime) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(startTime)) {
            params.put("stime", startTime);
        } else {
            return ResponseVo.fail("起始日期不能为空");
        }
        if (!StringUtils.isEmpty(endTime)) {
            params.put("etime", endTime);
        } else {
            return ResponseVo.fail("截止日期不能为空");
        }
        List<EquipmentLedger> list = equipmentService.selectHistory(params);
        List<Map<String, Object>> resultList = new ArrayList();
        for (EquipmentLedger equipmentLedger : list) {
            Map result = new HashMap();
            User user = userService.selectById(equipmentLedger.getUserId());
            if(user!=null){
                result.put("userName", user.getName());
                result.put("area", user.getDepartment());
                Company company = companyService.selectById(user.getOrganisation());
                if(company != null) {
                    result.put("companyName", company.getCompanyName());
                }
            } else {
                result.put("userName", "");
                result.put("area", "");
                result.put("companyName", "");
            }
            result.put("deviceNumber", equipmentLedger.getDeviceNumber());
            result.put("startTime", equipmentLedger.getStartTime());
            result.put("endTime", equipmentLedger.getEndTime());
            resultList.add(result);
        }
        return ResponseVo.success(resultList);
    }
    @RequestMapping({"/playvideo/chartdata/{videoId}"})
    @ResponseBody
    public ResponseVo<DoubleVo<List<String>, Map<String, List<DoubleVo<Long, Object>>>>> videoChartData(@PathVariable Integer videoId) throws ParseException {
        UploadEntity entity = uploadEntityServiceFactory.getEntity(UploadEntityTypeEnum.video, videoId);
        if (entity == null)
            return ResponseVo.fail("视频不存在");

        Video video = (Video) entity;
        //string类型
        DoubleVo<String, Map<Integer, List<DoubleVo<Long, String>>>> doubleVo1 = tyBoxDataService.selectGpsDataListByVideo1(video);
        //int类型
        DoubleVo<String, Map<Integer, List<DoubleVo<Long, Integer>>>> doubleVo = tyBoxDataService.selectGpsDataListByVideo(video);
        Map<Integer, List<DoubleVo<Long, Integer>>> typeMapData = doubleVo.getVal();
        Map<Integer, List<DoubleVo<Long, String>>> typeMapData1 = doubleVo1.getVal();

        Map<String, List<DoubleVo<Long, Object>>> nameMapData = typeMapData.keySet().stream().map(typeId -> {
            GpsDataTypeItemEnum typeItemEnum = GpsDataTypeItemEnum.get(typeId);
            return typeItemEnum;
        }).filter(typeItemEnum -> typeItemEnum != null)
                .collect(Collectors.toMap(
                        typeEnum -> typeEnum.getName(),
                        typeEnum -> {
                            List<DoubleVo<Long, Integer>> list = typeMapData.get(typeEnum.getId());
                            List<DoubleVo<Long, Object>> list2 = list.stream().map(LongIntKv -> {
                                int origVal = LongIntKv.getVal();
                                Float ratioVal = new BigDecimal(typeEnum.getRatio() * origVal).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
                                return new DoubleVo<Long, Object>(LongIntKv.getKey(), new Float(ratioVal));
                            }).collect(Collectors.toList());
                            return list2;
                        }
                ));
        Map<String, List<DoubleVo<Long, Object>>> nameMapData1 = typeMapData1.keySet().stream().map(typeId -> {
            GpsDataTypeItemEnum typeItemEnum = GpsDataTypeItemEnum.get(typeId);
            return typeItemEnum;
        }).filter(typeItemEnum -> typeItemEnum != null)
                .collect(Collectors.toMap(
                        typeEnum -> typeEnum.getName(),
                        typeEnum -> {
                            List<DoubleVo<Long, String>> list = typeMapData1.get(typeEnum.getId());
                            List<DoubleVo<Long, Object>> list2 = list.stream().map(LongIntKv -> {
//                                int origVal = Integer.valueOf(LongIntKv.getVal());
//                                Float ratioVal = new BigDecimal(typeEnum.getRatio() * origVal).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
                                return new DoubleVo<Long, Object>(LongIntKv.getKey(), LongIntKv.getVal());
                            }).collect(Collectors.toList());
                            return list2;
                        }
                ));
        List<String> webChartsNameList = nameMapData.keySet().stream().sorted().collect(Collectors.toList());
        List<String> webChartsNameList2 = nameMapData1.keySet().stream().sorted().collect(Collectors.toList());
        webChartsNameList.addAll(webChartsNameList2);
        //动作数据输出到前端。因清华大数据需要，在时间有限的情况下此处粗糙处理输出以便清华能拿到

        Map<GpsDataTypeItemEnum, Map<Long, String>> gyroAndActionData = tyBoxDataService.selectActionGyroDataByVideo(video);
        List<DoubleVo<Long, Object>> gyroList;
        if(gyroAndActionData != null){
            Map<Long, String> gyroDataMap = gyroAndActionData.get(GpsDataTypeItemEnum.GYRO);
            gyroList = gyroDataMap.keySet().stream().sorted().map(time -> new DoubleVo<Long, Object>(time, gyroDataMap.get(time))).collect(Collectors.toList());
        }else{
            gyroList = new ArrayList<>();
        }
        Map<Long, String> actionDataMap = tyBoxDataService.selectActionDataByVideo(video);
        List<DoubleVo<Long, Object>> actionList = actionDataMap.keySet().stream().sorted().map(time -> new DoubleVo<Long, Object>(time, actionDataMap.get(time))).collect(Collectors.toList());
        if(actionList!=null){
            actionList = actionResover(actionList);
        }
        long time1 = Dates.toDate(video.getCreateTime()).getTime();
        long time2 = time1 + video.getSeconds() * 1000;
        List<StringPoint> gpsDataList = kmxService.queryStringRange(TYBOX_DEVICE_IDPREFIX + video.getImei(), "GPS_LOCATION", time1, time2);
        List<DoubleVo<Long,Object>> gpsList;
        if(gpsDataList!=null){
            gpsList = gpsResover(gpsDataList);
        }else{
            gpsList = new ArrayList<>();
        }
        nameMapData.put(GpsDataTypeItemEnum.ACTION.getName(), actionList);
        nameMapData.put(GpsDataTypeItemEnum.GYRO.getName(), gyroList);
        nameMapData.put(GpsDataTypeItemEnum.GPS_LOCATION.getName(), gpsList);
        /*if(gpsLocationData != null){
            for(DoubleVo d : gpsList){
                d.setVal(gpsLocationData);
            }
        }*/
//        nameMapData.put(GpsDataTypeItemEnum.GPS_LOCATION.getName(),gpsList);

        logger.debug("视频播放.v.id=" + videoId + ".得到视频对应传感器数据:tybox imei=" + doubleVo.getKey() + ",分类详情:" + JSON.toJSONString(webChartsNameList));

        return ResponseVo.success(new DoubleVo<>(webChartsNameList, nameMapData));
    }
    private List<DoubleVo<Long, Object>> actionResover(List<DoubleVo<Long, Object>> actionList) {
        List<DoubleVo<Long, Object>> resultList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("动臂上升");
        list.add("动臂下降");
        list.add("斗杆回收");
        list.add("斗杆伸出");
        list.add("铲斗挖掘");
        list.add("铲斗卸载");
        list.add("回转");
        list.add("行走");
        for (DoubleVo d : actionList) {
            JSONObject json = JSONObject.parseObject((String) d.getVal());
            String actionValString = (String) json.get("actionVal");
            logger.debug("before:"+actionValString);
            actionValString = new StringBuffer(actionValString).reverse().toString();
            logger.debug("after:"+actionValString);
            Integer action = (Integer) json.get("action");
            if(action == 1){
                resultList.add(d);
                continue;
            }
            String actionVal = "";
            for (int i = 0; i < actionValString.length(); i++) {
                if ('1' == actionValString.charAt(i)) {
                    actionVal += list.get(i);
                    System.out.println("动作：：：："+actionVal);
                }
            }
            Integer walk = (Integer) json.get("walk");
            if (0 == walk) {
                actionVal = actionVal + ",同时动作";
            }
            d.setVal(((String) d.getVal()).replace(actionValString, actionVal));
            resultList.add(d);
        }
        return resultList;
    }
    private List<DoubleVo<Long,Object>> gpsResover(List<StringPoint> gpsDataList) throws ParseException {
        List<DoubleVo<Long, Object>> gpsList = new ArrayList<>();
        for (StringPoint s : gpsDataList) {
            String jsonString = s.getValue();
            JSONObject json = JSONObject.parseObject(jsonString);
            String timeString = (String) json.get("gpsTime");
            timeString = timeString.replace("T", " ");
            if (timeString.length() < 19) {
                timeString = timeString + ":00";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(timeString);
            Long time = date.getTime();
            DoubleVo<Long, Object> d = new DoubleVo<>();
            d.setKey(time);
            d.setVal(json);
            gpsList.add(d);
        }
        return gpsList;
    }

}
