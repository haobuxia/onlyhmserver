package com.tianyi.helmet.server.controller.helmetinterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.interceptor.TianyiUserHolder;
import com.tianyi.helmet.server.dao.workorder.WorkJobDao;
import com.tianyi.helmet.server.entity.client.Company;
import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.entity.client.TianyiContact;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.HelmetGps;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.scene.svc.Weather;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrder;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrderStateEnum;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrderWelcome;
import com.tianyi.helmet.server.entity.workorder.WorkJob;
import com.tianyi.helmet.server.exception.TianyiException;
import com.tianyi.helmet.server.service.baidu.BaiduMapService;
import com.tianyi.helmet.server.service.client.CompanyService;
import com.tianyi.helmet.server.service.client.HelmetUniversalService;
import com.tianyi.helmet.server.service.client.TianyiContactService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.data.HelmetGpsService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.scene.WeatherService;
import com.tianyi.helmet.server.service.scene.WorkOrderService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanIntesrvApiHelper;
import com.tianyi.helmet.server.service.tianyuan.TianyiUipApiHelper;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.ResponseVo;
import com.tianyi.helmet.server.vo.workorder.AppTodoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.util.StringUtils.isEmpty;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 工单接口
 * <p>
 * Created by liuhanc on 2018/6/30.
 */
@Controller
@RequestMapping("workorder")
@Api(value = "api", description = "工单管理")
public class WorkOrderController {

    private Logger logger = LoggerFactory.getLogger(WorkOrderController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HelmetGpsService helmetGpsService;
    @Autowired
    private BaiduMapService baiduMapService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private WorkJobDao workJobDao;
    @Autowired
    private TianYuanIntesrvApiHelper tianYuanIntesrvApiHelper;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TianyiContactService tianyiContactService;
    @Autowired
    private HelmetUniversalService helmetUniversalService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private TianyiUipApiHelper tianyiUipApiHelper;

    private final static List<String> MILEAGE_STATUS_LIST = new ArrayList<>();
    private final static List<String> WORK_STATUS_TODO_NEXT = new ArrayList<>();
    private final static List<String> WORK_STATUS_ING_NEXT = new ArrayList<>();
    private final static List<String> WORK_STATUS_TOEND_NEXT = new ArrayList<>();
    private final static String MAX_TIME = "253402271999000";//9999-12-31 23:59:59

    static {
        MILEAGE_STATUS_LIST.add("departure");
        MILEAGE_STATUS_LIST.add("arrival");
        MILEAGE_STATUS_LIST.add("leave");

        WORK_STATUS_TODO_NEXT.add("进行中");
        WORK_STATUS_TODO_NEXT.add("待审核");

        WORK_STATUS_ING_NEXT.add("待结束");

        WORK_STATUS_TOEND_NEXT.add("已完成");
    }



    /**
     * 头盔获取工单列表
     *
     * @return
     */
    @PostMapping("hm/work/list")
    @ResponseBody
    @ApiOperation(value = "头盔获取工单列表")
    public ResponseVo listWorkCard() {
        //zhouwei 20190603 调用新的头盔获取工单接口
//        JSONObject retInfo = tianYuanIntesrvApiHelper.getJson("WorkList", dataReqJson);
        //zhouwei 20190810 根据用户判断应该调用哪个服务
        HelmetUniversalInfo universalInfo = helmetUniversalService.selectByUserId(TianyiUserHolder.get().getId());
        if (universalInfo == null || isEmpty(universalInfo.getProject())) {
            return ResponseVo.fail("未配置项目信息");
        }
        if(universalInfo.getId() == 5) {// 天远租赁
            String helmetImei = HelmetImeiHolder.get();
            EquipmentLedger equipmentLedger = equipmentService.selectByUUID(helmetImei);
            if(equipmentLedger != null) {
                JSONObject jsonObject = tianyiUipApiHelper.getWorkOrderList(equipmentLedger.getDeviceNumber());
                return jsonObject.toJavaObject(ResponseVo.class);
            } else {
                return ResponseVo.fail("头盔没有入库："+helmetImei);
            }
        } else if (universalInfo.getProject().startsWith("智能服务")) {// 智能服务项目
            JSONObject dataReqJson = getTtoWorkTimes();
            dataReqJson.put("useraccount", getTianyuanAccount());
            JSONObject retInfo = tianYuanIntesrvApiHelper.getJson("WorkListNew", dataReqJson);
            if ("true".equals(String.valueOf(retInfo.get("success")).toLowerCase())) {
                return ResponseVo.success(retInfo.get("data"));
            } else {
                return ResponseVo.fail((String) retInfo.get("msg"));
            }
        } else {
            return ResponseVo.fail("不支持" + universalInfo.getProject() + "项目");
        }
    }

    /**
     * 手机端-获取工单列表
     *
     * @return
     */
    @RequestMapping(value = "work/list/app", method = POST)
    @ResponseBody
    @ApiOperation(value = "手机获取工单列表")
    public ResponseVo listWorkCardApp(@ApiParam(value = "查询日期", name = "qrycal",
            required = true) @RequestParam String qrycal) {
        /*
            2、调用智能服务数据接口
         */

        //1、获取工作卡索引信息
        //组装数据接口需要数据
        //获取今、明、之后三个时间段的工单oid
        JSONObject dataReqJson = getTtoWorkTimes();
        dataReqJson.put("userAccount", getTianyuanAccount());
        dataReqJson.put("qrycal", qrycal);

        //调用智能服务数据接口
        JSONObject retInfo = tianYuanIntesrvApiHelper.getJson("ListWorkApp", dataReqJson);
        if ("true".equals(String.valueOf(retInfo.get("success")).toLowerCase())) {
            JSONObject retData = (JSONObject) retInfo.get("data");
            JSONObject retToAppJson = new JSONObject();
            retToAppJson.put("workList", retData.get("workList"));
            retToAppJson.put("todayCount", String.valueOf(((JSONArray) retData.get("todayworkoids")).size() - 1));
            retToAppJson.put("tomorrowCount", String.valueOf(((JSONArray) retData.get("tomorrowworkoids")).size() - 1));
            retToAppJson.put("otherCount", String.valueOf(((JSONArray) retData.get("otherworkoids")).size() - 1));
            //缓存工单是否需要附加信息，如果需要，在结束工单时拦截

            return ResponseVo.success(retToAppJson);
        } else {
            return ResponseVo.fail((String) retInfo.get("msg"));
        }
    }

    private String getTianyuanAccount() {

        String tianyuanAccount = TianyiUserHolder.get().getTianyuanAccount();
        if (isEmpty(tianyuanAccount)) {
            logger.error("该用户未绑定天远账号");
            throw new RuntimeException("该用户未绑定天远账号");
        }
        return tianyuanAccount;
    }

    /**
     * 获取today,tomorrow,other三个时间段的工作卡OID
     * zhouwei
     *
     * @return
     */
    private JSONObject getTtoWorkTimes() {
        //获取当天时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date today = calendar.getTime();
        String todayTime = String.valueOf(today.getTime());
        //计算一天后日期
        calendar.add(Calendar.DATE, 1);
        String tomorrowTime = String.valueOf(calendar.getTimeInMillis());
        //计算2天后日期
        calendar.add(Calendar.DATE, 1);
        String otherTime = String.valueOf(calendar.getTimeInMillis());

        JSONObject dataReqJson = new JSONObject();
        dataReqJson.put("todayTime", todayTime);
        dataReqJson.put("tomorrowTime", tomorrowTime);
        dataReqJson.put("otherTime", otherTime);

        return dataReqJson;
    }

    /**
     * 获取工单工作卡零件
     *
     * @param workId niusen
     * @return
     */
    @RequestMapping("/work/{workId}/refpart/list")
    @ResponseBody
    public ResponseVo listWorkPart(@PathVariable String workId) {
        JSONObject dataReqJson = new JSONObject();
        dataReqJson.put("tyworkoid", workId);
        //调用智能服务数据接口
        JSONObject retInfo = tianYuanIntesrvApiHelper.getJson("WorkNecessaryParts", dataReqJson);
        if ("true".equals(String.valueOf(retInfo.get("success")).toLowerCase())) {
            return ResponseVo.success(retInfo.get("data"));
        } else {
            return ResponseVo.fail((String) retInfo.get("msg"));
        }
    }

    /**
     * 欢迎页
     * 反馈位置地址、日期、时间、
     * 头盔佩戴者姓名、工单数
     *
     * @return
     */
    @RequestMapping("welcome")
    @ResponseBody
    public ResponseVo<WorkOrderWelcome> welcome() {
        User user = TianyiUserHolder.get();
        String imei = HelmetImeiHolder.get();

        WorkOrderWelcome welcome = new WorkOrderWelcome();
        welcome.setServerTime(Dates.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        welcome.setUserId(user.getId());
        welcome.setUserRealName(user.getName());

        List<WorkOrder> workOrderList = workOrderService.selectNotEndWorkOrderListByUserId(user.getId(), true);
        welcome.setWorkOrderList(workOrderList);

        //地址定位
        /**
         * update by xiayuan 2018/10/9
         */
//        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
//        EquipmentLedger helmet = new EquipmentLedger();
//        for (EquipmentLedger e : list) {
//            String state = (String) redisTemplate.opsForValue().get(e.getDeviceUUID());
//            if (MyConstants.DEVICE_STATE_ON.equals(state)) {
//                helmet = e;
//            }
//        }
        HelmetGps gps = helmetGpsService.getLatest(imei);

        if (gps != null) {
            //实时地址
            String address = baiduMapService.latLngToAddress(gps.getLat(), gps.getLon());
            welcome.setAddress(address);

            //实时天气
            Weather weather = weatherService.getWeather(gps.getLat(), gps.getLon());
            welcome.setWeather(weather);
        } else {
            welcome.setAddress("石家庄市裕华区黄河大道225号");
            Weather weather = weatherService.getWeather(114.5, 38);//石家庄默认gps
            welcome.setWeather(weather);
        }

        return ResponseVo.success(welcome);
    }

    /**
     * @return
     */
    @RequestMapping("getWorkOrder")
    @ResponseBody
    public ResponseVo<WorkOrder> getWorkOrder(@RequestParam String orderNo) {
        User user = TianyiUserHolder.get();
        WorkOrder workOrder = workOrderService.selectByOrderNo(orderNo, false);
        if (workOrder.getUserId() != user.getId()) {
            return ResponseVo.fail("工单未指派给你，无法获取");
        }

        //填充数据
        workOrderService.fillWorkOrderTypeStateName(workOrder);
        workOrderService.fillWorkOrderStrategyList(workOrder);
        workOrderService.fillVideoCounts(workOrder);

        return ResponseVo.success(workOrder);
    }


    /**
     * @return
     */
    @RequestMapping("start")
    @ResponseBody
    public ResponseVo startWorkOrder(@RequestParam String orderNo) {
        User user = TianyiUserHolder.get();
        WorkOrder workOrder = workOrderService.selectByOrderNo(orderNo, false);
        if (workOrder.getUserId() != user.getId()) {
            return ResponseVo.fail("工单未指派给你，无法开始");
        }

        String oldState = workOrder.getOrderState();
        if (!WorkOrderStateEnum.INIT.toString().equals(oldState)) {
            return ResponseVo.fail("工单当前状态是" + WorkOrderStateEnum.valueOf(oldState).getCnName() + ",无法开始");
        }

        workOrderService.updateOrderStateToIngByOrderNo(orderNo);

        Map<String, String> retData = new HashMap<>();
        retData.put("orderState", WorkOrderStateEnum.ING.toString());
        retData.put("orderStateName", WorkOrderStateEnum.ING.getCnName());
        return ResponseVo.success(retData);
    }

    /**
     * @return
     */
    @RequestMapping("end")
    @ResponseBody
    public ResponseVo endWorkOrder(@RequestParam String orderNo) {
        User user = TianyiUserHolder.get();
        WorkOrder workOrder = workOrderService.selectByOrderNo(orderNo, false);
        if (workOrder.getUserId() != user.getId()) {
            return ResponseVo.fail("工单未指派给你，无法结束");
        }
        String oldState = workOrder.getOrderState();
        if (!WorkOrderStateEnum.ING.toString().equals(oldState)) {
            return ResponseVo.fail("工单当前状态是" + WorkOrderStateEnum.valueOf(oldState).getCnName() + ",无法结束");
        }

        workOrderService.updateOrderStateToEndByOrderNo(orderNo);

        Map<String, String> retData = new HashMap<>();
        retData.put("orderState", WorkOrderStateEnum.END.toString());
        retData.put("orderStateName", WorkOrderStateEnum.END.getCnName());
        return ResponseVo.success(retData);
    }

    /**
     * @return
     */
    @RequestMapping("addCollaborate")
    @ResponseBody
    public ResponseVo addCollaborate(@RequestParam String orderNo) {
        User user = TianyiUserHolder.get();
        WorkOrder workOrder = workOrderService.selectByOrderNo(orderNo, false);
        if (workOrder.getUserId() != user.getId()) {
            return ResponseVo.fail("工单未指派给你，无法修改");
        }
        String oldState = workOrder.getOrderState();
        if (!WorkOrderStateEnum.ING.toString().equals(oldState)) {
            return ResponseVo.fail("工单当前状态是" + WorkOrderStateEnum.valueOf(oldState).getCnName() + ",无法添加协助次数");
        }

        workOrderService.increaseCollaborateCntByOrderNo(orderNo);
        workOrder = workOrderService.selectByOrderNo(orderNo, false);
        return ResponseVo.success(workOrder.getCollaborateCnt());
    }

    /**
     * 工单详情-任务列表
     *
     * @return niusen
     */
    @RequestMapping("/work/{workId}/todo/list")
    @ResponseBody
    public ResponseVo<List<AppTodoVo>> workJobdetails(@PathVariable String workId) {
        List<AppTodoVo> todos = new ArrayList<>();

        AppTodoVo todoVo = new AppTodoVo();

        todoVo = new AppTodoVo();
        todoVo.setTitle("现场服务");
        todoVo.setPosition("石家庄开发区黄河大道225号");
        todoVo.setDescribe("携带零件并前往冀东水泥的车辆地点进行现场服务工作");
        todos.add(todoVo);

        return ResponseVo.success(todos);
    }

    /**
     * 工单作业卡新增
     *
     * @param reqJobs
     * @return niusen
     */
    @RequestMapping("jobs/add")
    @ResponseBody
    public ResponseVo addWorkJob(@RequestBody List<Map<String, Object>> reqJobs) {
        ResponseVo<Map<String, String>> responseVo;
        if (reqJobs.size() < 1) {
            return ResponseVo.fail("新增失败");
        } else {
            for (Map<String, Object> job : reqJobs) {
                WorkJob wj = new WorkJob();
                wj.setPltOid(String.valueOf(job.get("plt_oid")));
                wj.setPltTyWorkOid(String.valueOf(job.get("plt_tyworkoid")));
                wj.setPltJobName(String.valueOf(job.get("plt_jobname")));
                wj.setPltIsCustomerJob(Boolean.valueOf(String.valueOf(job.get("plt_iscustomerjob"))));
                wj.setPltJobStatus(String.valueOf(job.get("plt_jobstatus")));
                Integer startTime = 0;
                Integer finishTime = 0;
                Integer seq = 0;
                try {
                    String intObj = String.valueOf(job.get("plt_jobstarttime"));
                    startTime = Integer.valueOf(intObj);
                    intObj = String.valueOf(job.get("plt_jobfinishtime"));
                    finishTime = Integer.valueOf(intObj);
                    intObj = String.valueOf(job.get("plt_sequence"));
                    seq = Integer.valueOf(intObj);
                } catch (NumberFormatException e) {
                } finally {
                    wj.setPltJobStartTime(startTime);
                    wj.setPltJobFinishTime(finishTime);
                    wj.setPltSequence(seq);
                }
                wj.setPltJobVideos(String.valueOf(job.get("plt_jobvideos")));
                wj.setPltJobDetails(String.valueOf(job.get("plt_jobdetails")));
                wj.setPltTyExpertWorkOid(String.valueOf(job.get("plt_tyexpertworkoid")));
                wj.setPltJobSummary(String.valueOf(job.get("plt_jobsummary")));
                wj.setPltTyExpertJobOid(String.valueOf(job.get("plt_tyexpertjoboid")));
                int r = workJobDao.insert(wj);
                if (r != 1) {
                    System.out.println(wj.getPltOid() + "失败");
                }
            }
            return ResponseVo.success();
        }
    }

    /**
     * 调整作业卡信息接口
     * 2019-1-18
     * created by updateWorkStatus
     *
     * @param workId 工作卡ID
     * @return
     */
    @PostMapping("work/{workId}/job/update")
    @ResponseBody
    @ApiOperation("更新作业步骤")
    public ResponseVo jobAlter(@ApiParam(value = "工作卡OID", required = true)@PathVariable String workId,
                               @ApiParam(value = "步骤列表", required = true)@RequestParam String workJobs) {
        JSONArray workJobsJson = JSON.parseArray(workJobs);

        JSONObject reqJson;
        if (workJobsJson.size() < 1) {
            return ResponseVo.fail("数据为空");
        } else {
            for (Object o : workJobsJson) {
                JSONObject map = (JSONObject) o;
                map.put("workId", workId);
            }
            Map<String, Object> infoTmp = new HashMap<>();
            infoTmp.put("paramInfo", workJobsJson);
            reqJson = JSONObject.parseObject(JSON.toJSONString(infoTmp));
        }
        //调用智能服务数据接口
        JSONObject retInfo = tianYuanIntesrvApiHelper.getJson("WorkJobUpdate", reqJson);
        if ("true".equals(String.valueOf(retInfo.get("success")).toLowerCase())) {
            return ResponseVo.success();
        } else {
            return ResponseVo.fail("DWF数据返回错误");
        }
    }

    /**
     * 获取在线用户联系人
     *
     * @return niusen
     */
    @RequestMapping(value = "/work/linkManOnline", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取联系人列表")
    public ResponseVo getLinkManOnLine(@ApiParam(value = "部门名称") @RequestParam( required = false) String department,
                                       @ApiParam(value = "用户名称") @RequestParam( required = false) String name,
                                       @ApiParam(value = "手机号") @RequestParam(required = false) String mobile) {

        List<Map> response = workOrderService.listTianyiLinkMan(department, mobile, name);
        return ResponseVo.success(response);
    }

    /**
     * 手机端根据工作卡oid获取作业和步骤信息
     * updateWorkStatus
     *
     * @param workId 工作卡oid
     * @return
     */
    @RequestMapping(value = "/work/{workId}/jobandstep", method = POST)
    @ResponseBody
    @ApiOperation(value = "根据工作卡OID查询作业和步骤")
    public ResponseVo getJobAndStepByWorkOid(@ApiParam(value = "工作卡OID", name = "workId",
            required = true) @PathVariable String workId) {

        //调用智能服务数据接口获取作业卡和步骤
        JSONObject reqJson = new JSONObject();
        reqJson.put("tyworkoid", workId);

        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("ListJobAndJobStepsAPP", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {

            //数据返回成功，遍历步骤是否需要打标，组装作业卡打标标志
            JSONObject retData = (JSONObject) retJson.get("data");
            JSONArray jobs = (JSONArray) retData.get("workJobs");
            for (Object jTmp : jobs) {
                JSONObject job = (JSONObject) jTmp;
                JSONArray steps = (JSONArray) job.get("jobSteps");
                String jobIsMark = "false";
                for (Object sTmp : steps) {
                    JSONObject step = (JSONObject) sTmp;
                    if ("true".equalsIgnoreCase(String.valueOf(step.get("isMark")))) {
                        jobIsMark = "true";
                        break;
                    }
                }
                job.put("isMark", jobIsMark);
            }
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }


    /**
     * 获取工作卡里程信息
     * updateWorkStatus
     *
     * @param customerId 客户oid
     * @return
     */
    @RequestMapping(value = "app/mileage/{customerId}/get", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取工作卡里程信息")
    public ResponseVo getWorkMileageStatus(@ApiParam(value = "客户OID", required = true) @PathVariable String customerId) {
        if (isEmpty(customerId)) {
            logger.error("客户ID不能为空");
            return ResponseVo.fail("客户ID不能为空");
        }

        JSONObject retJson = getWorkMileageStatusInfoByCustomer(customerId);
        if (retJson == null) {
            return ResponseVo.fail("获取里程信息失败");
        } else {
            return ResponseVo.success(retJson);
        }
    }


    /**
     * 手机出发到达接口
     * updateWorkStatus
     *
     * @param type 出发或到达类型
     * @return
     */
    @RequestMapping(value = "app/mileage/{type}", method = POST)
    @ResponseBody
    @ApiOperation("手机出发到达离开")
    public ResponseVo workMileageProcess(@ApiParam(value = "类型", required = true) @PathVariable String type,
                                         @ApiParam(value = "客户OID", required = true) @RequestParam String customerId,
                                         @ApiParam(value = "地理位置", required = true) @RequestParam String coordinate) {
        if (isEmpty(customerId)) {
            logger.error("客户ID不能为空");
            return ResponseVo.fail("客户ID不能为空");
        }
        if (isEmpty(type)
                || !MILEAGE_STATUS_LIST.contains(type)) {
            logger.error("里程状态非法");
            return ResponseVo.fail("里程状态非法");
        }

        JSONObject reqJson = new JSONObject();
        reqJson.put("customerOid", customerId);
        reqJson.put("mileageType", type);
        reqJson.put("coordinate", coordinate);
        reqJson.put("userAccount", getTianyuanAccount());
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("WorkMileageUpdate", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }

    private JSONObject getWorkMileageStatusInfoByCustomer(String customerId) {
        return getWorkMileageStatusInfo(customerId);
    }

    /**
     * 调用智能服务获取里程信息
     * updateWorkStatus
     *
     * @param customerId 客户OID
     * @return
     */
    private JSONObject getWorkMileageStatusInfo(String customerId) {
        //调用智能服务数据接口获取里程信息
        JSONObject reqJson = new JSONObject();
        reqJson.put("customerOid", customerId);
        reqJson.put("userAccount", getTianyuanAccount());
        JSONObject retJson;
        if (isEmpty(customerId)) {
            retJson = tianYuanIntesrvApiHelper.getJson("WorkMileageInfo", reqJson);
        } else {
            //根据客户号查询里程状态
            retJson = tianYuanIntesrvApiHelper.getJson("WorkMileageInfoByCustomer", reqJson);
        }
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
        /*
        返回状态处理
        如果没有记录，返回出发
        返回已出发，返回到达
        返回已到达，返回离开
         */
            JSONObject retData = (JSONObject) retJson.get("data");

            if (retData == null || retData.size() == 0) {
                retData = new JSONObject();
                retData.put("mileageStatus", "出发");
                return retData;
            } else {
                return retData;
            }
        } else {
            logger.error((String) retJson.get("msg"));
            return null;
        }
    }

    /**
     * 根据工作卡OID修改工作卡状态
     * updateWorkStatus
     *
     * @param workId 工作卡OID
     * @return
     */
    @RequestMapping(value = "work/{workId}/status/update", method = POST)
    @ResponseBody
    @ApiOperation(value = "更新工作卡状态")
    public ResponseVo updateWorkStatus(@ApiParam(value = "工作卡OID", required = true) @PathVariable String workId,
                                       @ApiParam(value = "工作卡状态", required = true) @RequestParam String workStatus,
        @ApiParam(value = "暂停原因") @RequestParam(required = false) String pauseReason,
        @ApiParam(value = "派工OID",required = true) @RequestParam String dispatchId) {

        HelmetUniversalInfo universalInfo = helmetUniversalService.selectByUserId(TianyiUserHolder.get().getId());
        if (universalInfo == null || isEmpty(universalInfo.getProject())) {
            return ResponseVo.fail("未配置项目信息");
        }
        if(universalInfo.getId() == 1) {// 天远租赁
            String helmetImei = HelmetImeiHolder.get();
            EquipmentLedger equipmentLedger = equipmentService.selectByUUID(helmetImei);
            if(equipmentLedger != null) {
                JSONObject jsonObject = tianyiUipApiHelper.updateStatus(equipmentLedger.getDeviceNumber(),workId,workStatus);
                return jsonObject.toJavaObject(ResponseVo.class);
            } else {
                return ResponseVo.fail("头盔没有入库："+helmetImei);
            }
        } else if (universalInfo.getProject().startsWith("智能服务")) {// 智能服务项目
            try {
                return workOrderService.updateWorkStatus(workId, workStatus, getTianyuanAccount(), pauseReason, dispatchId, TianyiUserHolder.get().getId());
            } catch (TianyiException e) {
                logger.error(e.getMessage());
                return ResponseVo.fail(e.getMessage());
            }
        } else {
            return ResponseVo.fail("不支持" + universalInfo.getProject() + "项目");
        }
    }

    /**
     * 根据工作卡OID修改计划服务派工时间
     *
     * @param workId 工作卡OID
     * @return
     */
    @RequestMapping(value = "work/{workId}/dispatchtime/update", method = POST)
    @ResponseBody
    @ApiOperation("修改计划服务时间")
    public ResponseVo updateDispatchTime(@ApiParam(value = "工单OID", required = true) @PathVariable String workId,
                                         @ApiParam(value = "派工OID", required = true) @RequestParam String dispatchId,
                                         @ApiParam(value = "计划服务时间", required = true) @RequestParam String workPlanTime) {

        if (StringUtils.isEmpty(workPlanTime)) {
            logger.error("派工时间不能为空");
            return ResponseVo.fail("派工时间不能为空");
        }

        JSONObject reqJson = new JSONObject();
        reqJson.put("srvPlanTime", workPlanTime);
        reqJson.put("workOid", workId);
        reqJson.put("userAccount", getTianyuanAccount());
        reqJson.put("dispatchOid", dispatchId);
        return tianYuanIntesrvApiHelper.call("WorkDispatchTimeUpdate", reqJson);
    }

    @RequestMapping(value = "/work/linkManInfo", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取工单联系人")
    public ResponseVo getWorkLinkMan() {

        /*
        1、获取未完成工作卡索引信息
        2、获取联系人信息
        3、查询头盔后台联系人相关信息
         */

        //调用智能服务获取工单相关联系人
        JSONObject reqJson = new JSONObject();
        reqJson.put("useraccount", getTianyuanAccount());
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("WorkLinkMan", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")))) {
            //组装田一用户信息
            JSONObject retData = (JSONObject) retJson.get("data");
            addTianyiUserInfo((JSONArray) retData.get("WorkInfo"));
            return ResponseVo.success(retData.get("WorkInfo"));
        } else {
            logger.error("获取联系人信息失败:" + retJson.get("msg"));
            return ResponseVo.fail("获取联系人信息失败");
        }

    }

    private void addTianyiUserInfo(JSONArray workInfo) {

        /*
        根据tyuserid查询田一用户
         */
        for (Object tmp : workInfo) {
            JSONObject workJson = (JSONObject) tmp;
            JSONArray linkMen = (JSONArray) workJson.get("Contact");
            for (Object tmp1 : linkMen) {
                JSONObject linkMan = (JSONObject) tmp1;
                String tyUserId = (String) linkMan.get("contactName");
//                String tyUserId = (String) linkMan.get("contactId");
                if (StringUtils.isEmpty(tyUserId)) {
                    continue;
                }
                User user = getTianyiUserByTyId(tyUserId);
                if (user == null) {
                    linkMan.put("tyUserId", "");
                    linkMan.put("helmetFriend", "false");
                    linkMan.put("tianYiUser", "false");
                }else{
                    List<TianyiContact> contacts = tianyiContactService.selectByUserId(TianyiUserHolder.get().getId());
                    linkMan.put("tyUserId", String.valueOf(user.getId()));
                    linkMan.put("helmetFriend", "false");
                    for (TianyiContact contact : contacts) {
                        if (contact.getUserId() == user.getId()) {
                            linkMan.put("helmetFriend", "true");
                            break;
                        }
                    }
                    linkMan.put("tianYiUser", "true");
                }
            }
        }

    }

    private User getTianyiUserByTyId(String tyUserId) {
        return null;
    }

    /**
     * app查询历史工单列表
     * niusen
     *
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "work/history", method = POST)
    @ResponseBody
    @ApiOperation(value = "app查询历史工单列表")
    public ResponseVo ListWorkOrder(@RequestParam(required = false) String deviceId,
                                    @RequestParam(required = false) String startTime,
                                    @RequestParam(required = false) String finishTime) {
        //调用智能服务数据接口更新工作卡状态
        JSONObject reqJson = new JSONObject();
        reqJson.put("useraccount", getTianyuanAccount());
        if (isEmpty(startTime)) startTime = "0";
        if (isEmpty(finishTime)) finishTime = "0";
        if (isEmpty(deviceId)) deviceId = "";
        if (startTime.length() > 10) {
            startTime = startTime.substring(0, 10);
        }
        reqJson.put("starttime", startTime);
        if (finishTime.length() > 10) {
            finishTime = finishTime.substring(0, 10);
        }
        reqJson.put("finishtime", finishTime);
        reqJson.put("deviceoid", deviceId);
        JSONObject retJson;
        if (StringUtils.isNotEmpty(deviceId)) {
            reqJson.put("flag", "device");
        } else {
            reqJson.put("flag", "time");
            if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(finishTime)) {
                return ResponseVo.fail("请输入查询日期");
            }
        }
        retJson = tianYuanIntesrvApiHelper.getJson("ListWorkOrderHistory", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }

    }

    @RequestMapping(value = "app/mileage/get", method = POST)
    @ResponseBody
    @ApiOperation("查看服务人员里程和工单状态")
    public ResponseVo workAndMileageStatus(@ApiParam(value = "派工OID")@RequestParam(required = false) String dispatchId) {

        //获取登陆用户OAuth账号
        String tianyuanAccount;
        try {
            tianyuanAccount = getTianyuanAccount();
        } catch (RuntimeException e) {
            return ResponseVo.fail(e.getMessage());
        }

        //请求智能服务数据接口
        JSONObject reqJson = new JSONObject();
        reqJson.put("tianyuanAccount", tianyuanAccount);
        reqJson.put("dispatchoid", String.valueOf(dispatchId));
        return tianYuanIntesrvApiHelper.call("WorkAndMileageStatus", reqJson);
    }

    /**
     * 获取人力支援列表
     * niusen
     *
     * @param workId 工单OID
     * @return
     */
    @RequestMapping(value = "work/{workId}/assist/people/list", method = POST)
    @ResponseBody
    @ApiOperation("获取人力支援列表")
    public ResponseVo listAssistPeople(@ApiParam(value = "工单OID", required = true)@PathVariable String workId) {
        //请求智能服务数据接口
        JSONObject reqJson = new JSONObject();
        reqJson.put("workId", workId);
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("ListAssistPeople", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }

    }

    /**
     * 获取零件支援列表
     * niusen
     *
     * @param workId 工作卡OID
     * @return
     */
    @RequestMapping(value = "work/{workId}/assist/part/list", method = POST)
    @ResponseBody
    @ApiOperation("查询零件支援列表")
    public ResponseVo listAssistPart(@ApiParam(value = "工作卡OID", required = true)@PathVariable String workId) {
        //请求智能服务数据接口
        JSONObject reqJson = new JSONObject();
        reqJson.put("workId", workId);
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("ListAssistPart", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }

    /**
     * 申请人力支援
     * niusen
     *
     * @param workId
     * @return
     */
    @RequestMapping(value = "work/{workId}/assist/people", method = POST)
    @ResponseBody
    @ApiOperation("手机人力支援申请")
    public ResponseVo listPeople(@ApiParam(value = "工作卡OID", required = true) @PathVariable String workId,
                                 @ApiParam(value = "支援人数", required = true)@RequestParam String assistPeopleNum,
                                 @ApiParam(value = "备注")@RequestParam(required = false) String assistRemark,
                                 @ApiParam(value = "支援时间", required = true)@RequestParam String assistPlanTime,
                                 @ApiParam(value = "支援地点", required = true)@RequestParam String assistCoordinate,
                                 @ApiParam(value = "支援地点", required = true)@RequestParam String assistAddress) {
        //请求智能服务数据接口
        JSONObject reqJson = new JSONObject();
        reqJson.put("workId", workId);
        reqJson.put("userAccount", getTianyuanAccount());
        reqJson.put("assistPeopleNum", assistPeopleNum);
        reqJson.put("assistAddress", assistAddress);
        reqJson.put("assistRemark", assistRemark);
        reqJson.put("assistPlanTime", assistPlanTime);
        reqJson.put("assistCoordinate", assistCoordinate);
        reqJson.put("assistType", "人力支援");
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("WorkAssist", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success();
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }

    /**
     * 申请零件支援
     * niusen
     *
     * @param workId
     * @return
     */
    @RequestMapping(value = "work/{workId}/assist/part", method = POST)
    @ResponseBody
    @ApiOperation(value = "申请零件支援")
    public ResponseVo listParts(@ApiParam(value = "工作卡OID", required = true) @PathVariable String workId,
            @ApiParam(value = "零件列表Json", required = true, defaultValue = "{\"assistParts\":[{\"partName\":\"零件名称\"," +
                    "\"partNum\":\"零件数量\",\"partId\":\"零件编号\"}]}") @RequestParam String assistParts, @ApiParam(value =
            "是否需要人力支援", required = true)@RequestParam String needPerson) {
        //请求智能服务数据接口
        JSONObject reqJson = new JSONObject();
        JSONArray jsonArray = JSONArray.parseArray(assistParts);
        List<Object> objList = jsonArray.subList(0, jsonArray.size());
        reqJson.put("workId", workId);
        reqJson.put("userAccount", getTianyuanAccount());
        reqJson.put("assistType", "零件支援");
        reqJson.put("assistParts", objList);
        reqJson.put("needPerson", needPerson);

        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("WorkAssist", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success();
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }

    /**
     * 获取机型列表
     * niusen
     *
     * @return
     */
    @RequestMapping("app/modeler/list")
    @ResponseBody
    public ResponseVo listDeviceModel() {
        //请求智能服务数据接口
        JSONObject reqJson = new JSONObject();
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("ListDeviceModel", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }

    /**
     * 获取添加零件列表
     * niusen
     *
     * @return
     */
    @RequestMapping("app/assistparts/get")
    @ResponseBody
    public ResponseVo listAddAssistParts(@RequestParam String deviceModel, @RequestParam String partName) {
        //请求智能服务数据接口
        JSONObject reqJson = new JSONObject();
        reqJson.put("deviceModel", deviceModel);
        reqJson.put("partName", partName);
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("ListAddAssistParts", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }

    /**
     * 提取零件列表
     * zhouwei
     *
     * @param workId 工作卡OID
     * @return
     */
    @RequestMapping("work/{workId}/pickpart")
    @ResponseBody
    public ResponseVo workPickPart(@PathVariable String workId) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("workoid", workId);
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("WorkPartPick", reqJson);

        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }


    /**
     * 头盔结束工单
     *
     * @param workId 工作卡OID
     * @return
     */
    @RequestMapping(value = "work/hm/{workId}/status/update", method = POST)
    @ResponseBody
    @ApiOperation("头盔更新工单状态")
    public ResponseVo hmEndWork(@ApiParam(value = "工作卡OID", required = true) @PathVariable String workId,
                                @ApiParam(value = "工作卡状态", required = true) @RequestParam String workStatus,
                                @ApiParam(value = "派工OID",required = true) @RequestParam String dispatchId) {
        HelmetUniversalInfo universalInfo = helmetUniversalService.selectByUserId(TianyiUserHolder.get().getId());
        if (universalInfo == null || isEmpty(universalInfo.getProject())) {
            return ResponseVo.fail("未配置项目信息");
        }
        if(universalInfo.getId() == 1) {// 天远租赁
            String helmetImei = HelmetImeiHolder.get();
            EquipmentLedger equipmentLedger = equipmentService.selectByUUID(helmetImei);
            if(equipmentLedger != null) {
                JSONObject jsonObject = tianyiUipApiHelper.updateStatus(equipmentLedger.getDeviceNumber(),workId,workStatus);
                return jsonObject.toJavaObject(ResponseVo.class);
            } else {
                return ResponseVo.fail("头盔没有入库："+helmetImei);
            }
        } else if (universalInfo.getProject().startsWith("智能服务")) {// 智能服务项目
            try {
                //TODO 解决头盔20190611单独上线的问题，屏蔽头盔主动开始结束工单功能，等手机版本上线后再放开
                return workOrderService.updateWorkStatus(workId, workStatus, getTianyuanAccount(), null, dispatchId, TianyiUserHolder.get().getId());
//            return ResponseVo.success();
            } catch (TianyiException e) {
                logger.error(e.getMessage());
                return ResponseVo.fail(e.getMessage());
            }
        } else {
            return ResponseVo.fail("不支持" + universalInfo.getProject() + "项目");
        }
    }

    @RequestMapping(value = "work/{workId}/part/update", method = POST)
    @ResponseBody
    @ApiOperation("手机调整零件")
    public ResponseVo alterPart(@ApiParam(value = "工作卡OID", required = true) @PathVariable String workId,
                                @ApiParam(value = "零件列表", required = true) @RequestParam String parts,
                                @ApiParam(value = "出库单OID", required = true) @RequestParam String deliveryId) {

        JSONArray partsJson = JSONArray.parseArray(parts);
        if (partsJson.size() == 0) {
            logger.error("调整零件为空");
            return ResponseVo.fail("请上送调整的零件");
        }

        JSONObject sendJson = new JSONObject();
        sendJson.put("parts", partsJson);
        sendJson.put("workoid", workId);
        sendJson.put("deliveryinfooid", deliveryId);

        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("WorkPartUpdate", sendJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            return ResponseVo.success(retJson.get("data"));
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }

    /**
     * 查询手机退库零件列表
     *
     * @param workId
     * @return
     */
    @RequestMapping(value = "/work/{workId}/part/return/list", method = POST)
    @ResponseBody
    @ApiOperation(value = "查询零件退库申请列表")
    public ResponseVo backPartList(@ApiParam(value = "工作卡OID", name = "workId", required = true) @PathVariable String workId) {
        JSONObject sendJson = new JSONObject();
        sendJson.put("tyworkoid", workId);

        return tianYuanIntesrvApiHelper.call("ListPartsBacks", sendJson);
    }

    /**
     * 手机退库零件提交
     *
     * @param workId
     * @return
     */
    @RequestMapping(value = "work/{workId}/part/return", method = POST)
    @ResponseBody
    @ApiOperation(value = "申请零件退库")
    public ResponseVo backPartSubmit(@ApiParam(value = "工作卡OID", name = "workId", required = true) @PathVariable String workId,
                                     @ApiParam(value = "申请零件列表", name = "parts", defaultValue =
                                             "[{\"partId\":\"零件编号\",\"partNum\":\"退库数量\"}," +
                                             "{\"partId\":\"零件编号\",\"partNum\":\"退库数量\"}]",
                                             required = true) @RequestParam String parts,
                                     @ApiParam(value = "出库单OID",required = true)@RequestParam String partDeliveryId){
        JSONObject sendJson = new JSONObject();
        JSONArray objArray = JSONArray.parseArray(parts);
        sendJson.put("data", objArray);
        sendJson.put("tyworkoid", workId);
        sendJson.put("commitPersonAccount", getTianyuanAccount());
        sendJson.put("partdeliveryinfooid", partDeliveryId);
        return tianYuanIntesrvApiHelper.call("AddPartsBack", sendJson);
    }

    @RequestMapping(value = "work/{workId}/mileage/list", method = POST)
    @ResponseBody
    @ApiOperation(value = "根据工作卡OID查询里程列表")
    public ResponseVo getMileageInfoByWork(@ApiParam(name = "workId", value = "工作卡OID", required = true) @PathVariable String workId) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("workoid", workId);
        reqJson.put("useraccount", getTianyuanAccount());
        return tianYuanIntesrvApiHelper.call("ListWorkMileageInfo", reqJson);
    }

    @RequestMapping(value = "work/{workId}/detail", method = POST)
    @ResponseBody
    @ApiOperation(value = "根据工作卡OID查询工作卡详情")
    public ResponseVo getWorkDetail(@ApiParam(name = "workId", value = "工作卡OID", required = true) @PathVariable String workId) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("workId", workId);
        return tianYuanIntesrvApiHelper.call("GetWorkDetail", reqJson);
    }

    @RequestMapping(value = "work/{workId}/add/device", method = POST)
    @ResponseBody
    @ApiOperation(value = "更新工作卡机号")
    public ResponseVo updateWorkDevice(@ApiParam(name = "workId", value = "工作卡OID", required = true) @PathVariable String workId,
                                       @ApiParam(name = "deviceId", value = "车辆编号", required = true) @RequestParam String deviceId) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("workId", workId);
        reqJson.put("deviceId", deviceId);

        return tianYuanIntesrvApiHelper.call("UpdateWorkDevice", reqJson);
    }


    @RequestMapping(value = "work/{workId}/add/result", method = POST)
    @ResponseBody
    @ApiOperation(value = "补全工作卡信息")
    public ResponseVo fillWorkDetail(@ApiParam(name = "workId", value = "工作卡OID", required = true) @PathVariable String workId,
    @ApiParam(name="additional", value = "补全信息Json字符", required = true) @RequestParam String additional) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("workOid", workId);
        JSONObject additionalJson = JSONObject.parseObject(additional);
        reqJson.put("workDetail", additionalJson);

        return tianYuanIntesrvApiHelper.call("FillWorkDetail", reqJson);
    }

    @RequestMapping(value = "work/{workId}/part/delivery", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取所有零件出库单")
    public ResponseVo getWorkDelivery(@ApiParam(name = "workId", value = "工作卡OID", required = true) @PathVariable String workId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("workoid", workId);

        return tianYuanIntesrvApiHelper.call("ListWorkDelivery", reqJson);
    }

    @RequestMapping(value = "work/assistantList", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取客户助理列表")
    public ResponseVo getAssistUserList(@ApiParam("用户名") @RequestParam(required = false) String name,
                                        @ApiParam("手机号") @RequestParam(required = false) String mobile) {
        return getIntesrvUserList("客户助理", name, mobile);

    }

    @RequestMapping(value = "work/engineerList", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取客户工程师列表")
    public ResponseVo getEngineerUserList(@ApiParam("用户名") @RequestParam(required = false) String name,
                                          @ApiParam("手机号") @RequestParam(required = false) String mobile) {
        return getIntesrvUserList("客户工程师", name, mobile);
    }

    /**
     * 根据类型从智能服务系统获取用户列表
     * zhouwei
     * @param userType 用户类别：客户助理，客户工程师
     * @return
     */
    private ResponseVo getIntesrvUserList(String userType, String name, String mobile) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("usertype", userType);
        reqJson.put("name", name == null ? "" : name);
        reqJson.put("mobile", mobile == null ? "" : mobile);
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("ListInteSrvUserList", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            JSONArray responseUsers = additionalTianyiUserInfo(retJson);
            return ResponseVo.success(responseUsers);
        } else {
            return ResponseVo.fail((String) retJson.get("msg"));
        }
    }

    /**
     * 补全用户信息田一部分
     * zhouwei
     * @param retJson
     * @return
     */
    private JSONArray additionalTianyiUserInfo(JSONObject retJson) {
        JSONArray responseUsers = new JSONArray();
            /*
                获取网易账号
                判断是否田一用户
                获取公司和部门信息
                检查是否好友
             */
        JSONArray users = (JSONArray) retJson.get("data");
        for (Object tmp : users) {
            JSONObject retUserInfo = new JSONObject();
            JSONObject user = (JSONObject) tmp;
            User tianyiUser = userService.selectByAccount((String) user.get("account"));
            retUserInfo.put("contactName", user.get("contactName"));
            retUserInfo.put("linkManPhone", user.get("linkManPhone"));
            retUserInfo.put("deptName", user.get("deptName"));

            if(tianyiUser==null){
                //不是田一用户
                retUserInfo.put("companyName", "天远科技");
            }else{
                //是田一用户
                retUserInfo.put("helmetAccount", tianyiUser.getNeUserHel());
                retUserInfo.put("webAccount", tianyiUser.getNeUserWeb());
                retUserInfo.put("phoneAccount", tianyiUser.getNeUserPhn());
                retUserInfo.put("tyUserId", String.valueOf(tianyiUser.getId()));
                retUserInfo.put("deptName", tianyiUser.getDepartment());
                Company company = companyService.selectById(tianyiUser.getOrganisation());
                retUserInfo.put("companyName", company==null?null:company.getCompanyName());
            }
            responseUsers.add(retUserInfo);
        }
        return responseUsers;
    }

    @RequestMapping(value = "dict/{type}", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取智能服务数据字典")
    public ResponseVo getSysDict(@ApiParam(value = "字典类型", required = true)@PathVariable String type) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("type", type);
        return tianYuanIntesrvApiHelper.call("ListSysDict", reqJson);
    }

    @RequestMapping(value = "work/{workId}/part/list", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取工单所有零件")
    public ResponseVo getWorkParts(@ApiParam(value = "工作卡OID", required = true)@PathVariable String workId) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("workoid", workId);
        return tianYuanIntesrvApiHelper.call("ListWorkParts", reqJson);
    }

    @RequestMapping(value = "work/{workId}/part/pickcmd", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取服务人员取货码")
    public ResponseVo getPickCmd(@ApiParam(value = "工作卡OID", required = true)@PathVariable String workId,
                                 @ApiParam(value = "出库单OID", required = true)@RequestParam String deliveryId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("workId", workId);
        reqJson.put("deliveryId", deliveryId);
        reqJson.put("userAccount", getTianyuanAccount());
        return tianYuanIntesrvApiHelper.call("GetPickCmd", reqJson);
    }

    @RequestMapping(value = "work/{workId}/additional", method = POST)
    @ResponseBody
    @ApiOperation(value = "查询工单附加信息")
    public ResponseVo getPickCmd(@ApiParam(value = "工作卡OID", required = true)@PathVariable String workId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("workoid", workId);
        return tianYuanIntesrvApiHelper.call("GetWorkAdditional", reqJson);
    }


    @RequestMapping(value = "assist/{assistId}/person", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取人力支援详细信息")
    public ResponseVo getPeopleAssistInfo(@ApiParam(value = "人力支援申请OID", required = true)@PathVariable String assistId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("assistoid", assistId);
        return tianYuanIntesrvApiHelper.call("GetPeopleAssistInfo", reqJson);
    }

    @RequestMapping(value = "work/{workId}/partdelivery/list", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取待退库零件列表")
    public ResponseVo getToReturnPartList(@ApiParam(value = "工作卡OID", required = true)@PathVariable String workId,
                                       @ApiParam(value = "出库单OID", required = true)@RequestParam String partDeliveryId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("workoid", workId);
        reqJson.put("partdeliveryinfooid", partDeliveryId);
        return tianYuanIntesrvApiHelper.call("GetToReturnPartList", reqJson);
    }

    @RequestMapping(value = "work/{workNum}/summary", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取工单简要信息")
    public ResponseVo getWorkSummary(@ApiParam(value = "工单编号", required = true)@PathVariable String workNum){
        JSONObject reqJson = new JSONObject();
        reqJson.put("workordernum", workNum);
        return tianYuanIntesrvApiHelper.call("GetWorkSummary", reqJson);
    }

    @RequestMapping(value = "work/{workId}/allmileage", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取服务人员里程信息")
    public ResponseVo getWorkAllMileages(@ApiParam(value = "工单ID", required = true)@PathVariable String workId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("workoid", workId);
        return tianYuanIntesrvApiHelper.call("GetWorkMileages", reqJson);
    }

    @RequestMapping(value = "work/{workId}/files", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取工单附件")
    public ResponseVo getWorkFiles(@ApiParam(value = "工单ID", required = true)@PathVariable String workId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("workoid", workId);
        JSONObject retInfo = tianYuanIntesrvApiHelper.getJson("GetWorkFiles", reqJson);
        if ("true".equals(String.valueOf(retInfo.get("success")).toLowerCase())) {
            JSONObject retData = (JSONObject) retInfo.get("data");
            //拼接智能服务url
            retData.put("attachmentUrl", tianYuanIntesrvApiHelper.concatIntesrvNetUrl((String) retData.get("attachmentUrl")));
            return ResponseVo.success(retInfo.get("data"));
        } else {
            return ResponseVo.fail((String) retInfo.get("msg"));
        }
    }


    @RequestMapping(value = "locker/{deliveryId}/pickup/open", method = POST)
    @ResponseBody
    @ApiOperation(value = "开柜-取货")
    public ResponseVo lockerOpenPickup(@ApiParam(value = "出库单ID", required = true)@PathVariable String deliveryId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("deliveryId", deliveryId);
        reqJson.put("userAccount", getTianyuanAccount());
        return tianYuanIntesrvApiHelper.call("OpenLockerPick", reqJson);
    }

    @RequestMapping(value = "locker/prepare/list", method = POST)
    @ResponseBody
    @ApiOperation(value = "获取备货列表")
    public ResponseVo listPrepareDelivery(){
        JSONObject reqJson = new JSONObject();
        reqJson.put("userAccount", getTianyuanAccount());
        return tianYuanIntesrvApiHelper.call("ListPrepareDelivery", reqJson);
    }

    @RequestMapping(value = "locker/{deliveryId}/prepare/open", method = POST)
    @ResponseBody
    @ApiOperation(value = "开柜-备货")
    public ResponseVo lockerOpenPrepare(@ApiParam(value = "出库单ID", required = true)@PathVariable String deliveryId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("deliveryId", deliveryId);
        reqJson.put("userAccount", getTianyuanAccount());
        return tianYuanIntesrvApiHelper.call("OpenLockerPrepare", reqJson);
    }

    @RequestMapping(value = "locker/{deliveryId}/open", method = POST)
    @ResponseBody
    @ApiOperation(value = "强制开柜")
    public ResponseVo lockerOpenForce(@ApiParam(value = "出库单ID", required = true)@PathVariable String deliveryId,
                                      @ApiParam(value = "箱号", required = true)@RequestParam String boxNo){
        JSONObject reqJson = new JSONObject();
        reqJson.put("deliveryId", deliveryId);
        reqJson.put("userAccount", getTianyuanAccount());
        reqJson.put("boxNo", boxNo);
        return tianYuanIntesrvApiHelper.call("OpenLockerForce", reqJson);
    }

    @RequestMapping(value = "locker/{deliveryId}/prepared", method = POST)
    @ResponseBody
    @ApiOperation(value = "完成备货")
    public ResponseVo finishPrepare(@ApiParam(value = "出库单ID", required = true)@PathVariable String deliveryId){
        JSONObject reqJson = new JSONObject();
        reqJson.put("deliveryId", deliveryId);
        reqJson.put("userAccount", getTianyuanAccount());
        return tianYuanIntesrvApiHelper.call("FinishPrepare", reqJson);
    }
}
