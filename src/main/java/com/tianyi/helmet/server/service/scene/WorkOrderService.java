package com.tianyi.helmet.server.service.scene;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.interceptor.TianyiUserHolder;
import com.tianyi.helmet.server.dao.scene.WorkOrderDao;
import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.entity.client.TianyiContact;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.HelmetOnlineStatus;
import com.tianyi.helmet.server.entity.file.TagResource;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrder;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrderStateEnum;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrderStrategy;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrderTypeEnum;
import com.tianyi.helmet.server.exception.TianyiException;
import com.tianyi.helmet.server.service.client.TianYuanUserComponent;
import com.tianyi.helmet.server.service.client.TianyiContactService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.data.HelmetOnlineStatusService;
import com.tianyi.helmet.server.service.file.ListFilterService;
import com.tianyi.helmet.server.service.file.TagResourceService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.mqtt.MqttClientService;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanApiBasicHelper;
import com.tianyi.helmet.server.service.tianyuan.TianYuanIntesrvApiHelper;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.vo.*;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务工单
 * <p>
 * Created by liuhanc on 2018/7/1.
 */
@Service
public class WorkOrderService {
    @Autowired
    private WorkOrderDao workOrderDao;
    @Autowired
    private WorkOrderStrategyService workOrderStrategyService;
    @Autowired
    private JsonRedisTemplate redisTemplate;
    @Autowired
    private TagResourceService tagResourceService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ListFilterService listFilterService;
    @Autowired
    private TianYuanApiBasicHelper tianYuanApiBasicHelper;
    @Autowired
    private ConfigService configService;
    @Autowired
    private TianYuanUserComponent tianYuanUserComponent;
    @Autowired
    private TianYuanIntesrvApiHelper tianYuanIntesrvApiHelper;
    @Autowired
    private MqttClientService mqttClientService;
    @Autowired
    private UserService userService;
    @Autowired
    private TianyiContactService tianyiContactService;
    @Autowired
    private HelmetOnlineStatusService helmetOnlineStatusService;

    private String ORDERNO_SEQ_KEY = "svc_orderno_seq";

    private Logger logger = LoggerFactory.getLogger(WorkOrderService.class);

    @PostConstruct
    public void clearOldOrderNoCache() {
        //把早于今日的hashKey删除避免占用空间  @todo 定期检测自动删除更好
        String today = DateUtils.formatDate(new Date(), "yyyyMMdd");
        Set<Object> hashKeySet = redisTemplate.opsForHash().keys(ORDERNO_SEQ_KEY);
        Object[] earierDateArray = hashKeySet.stream().filter(hashKey -> hashKey.toString().compareTo(today) < 0).collect(Collectors.toList()).toArray();
        if (earierDateArray.length > 0) {
            redisTemplate.opsForHash().delete(ORDERNO_SEQ_KEY, earierDateArray);
        }
    }

    /**
     * 按日期分组，每组按序列递增.
     * 生成的工单号以当前日期开头8位+3位自增序列
     *
     * @return
     */
    private String generateOrderNo() {
        String today = DateUtils.formatDate(new Date(), "yyyyMMdd");
        long seq = redisTemplate.opsForHash().increment(ORDERNO_SEQ_KEY, today, 1l);
        return today + Commons.leftToLength(String.valueOf(seq), 3, '0');
    }

    public void insert(WorkOrder order) {
        String orderNo = generateOrderNo();
        System.out.println("生成的工单号:" + orderNo);
        order.setOrderNo(orderNo);
        order.setInputTime(new Date());
        order.setOrderState(WorkOrderStateEnum.INIT.toString());
        order.setCollaborateCnt(0);
        order.setOrderCnt(0);
        workOrderDao.insert(order);
    }

    public WorkOrder selectByOrderNo(String orderNo, boolean fill) {
        WorkOrder workOrder = workOrderDao.selectByOrderNo(orderNo);
        if (fill) {
            fillWorkOrderTypeStateName(workOrder);
            fillWorkOrderStrategyList(workOrder);
            fillVideoCounts(workOrder);
        }
        return workOrder;
    }

    public List<WorkOrder> selectNotEndWorkOrderListByUserId(int userId, boolean fill) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("orderStateNot", WorkOrderStateEnum.END.toString());//状态不等于
        List<WorkOrder> orderList = workOrderDao.selectBy(map);
        if (fill) {
            orderList.stream().forEach(order -> {
                fillWorkOrderTypeStateName(order);
                fillWorkOrderStrategyList(order);
                fillVideoCounts(order);
            });
        }
        return orderList;
    }

    public PageListVo<WorkOrder> search(String keyword, int page) {
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("keyword", keyword);
        List<WorkOrder> orderList = workOrderDao.selectBy(map);
        orderList.stream().forEach(this::fillWorkOrderTypeStateName);
        int total = workOrderDao.countBy(map);
        PageListVo vo = new PageListVo(page);
        vo.setTotal(total);
        vo.setList(orderList);
        return vo;
    }

    public void fillWorkOrderStrategyList(WorkOrder order) {
        List<WorkOrderStrategy> strategyList = workOrderStrategyService.selectByOrderNo(order.getOrderNo());
        order.setStrategyList(strategyList);
    }

    public void fillWorkOrderTypeStateName(WorkOrder order) {
        order.setOrderTypeName(WorkOrderTypeEnum.getTypeName(order.getOrderType()));
        order.setOrderStateName(WorkOrderStateEnum.getStateName(order.getOrderState()));
    }

    public void fillVideoCounts(WorkOrder order) {
        String tags = order.getTags();
        if (tags == null || StringUtils.isEmpty(tags.trim())) {
            return;
        }

        //统计当前工单所有视频对应的标签数量。虽然1个视频可以有多个标签，但是头盔录制上传阶段最多1个标签
        List<Video> videoList = (List<Video>) videoService.listByOrderNo(order.getOrderNo());
        Map<Integer, Long> tagIdCountMap = videoList.stream().map(video -> {
            List<TagResource> resourceList = tagResourceService.listByResTypeResId(UploadEntityTypeEnum.video, video.getId());
            return resourceList.stream().map(res -> res.getTagId()).collect(Collectors.toList());
        }).flatMap(List::stream).collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        String videoCounts = Arrays.stream(tags.split(","))
                .map(tagName -> {
                    return StringUtils.isEmpty(tagName) ? null : listFilterService.selectTagByName(tagName);
                })
                .map(tag -> {
                    if (tag == null)
                        return "0";
                    int tagId = tag.getId();
                    Long videoCount = tagIdCountMap.get(tagId);
                    return videoCount == null ? String.valueOf(0) : String.valueOf(videoCount);
                }).collect(Collectors.joining(","));

        logger.debug("获取工单标签的视频数量:orderNo=" + order.getOrderNo() + ",tags=" + order.getTags() + ",videoCounts=" + videoCounts + ".此工单当前视频总数=" + videoList.size());
        order.setVideoCounts(videoCounts);
    }

    public int updateOrderStateToIngByOrderNo(String orderNo) {
        return workOrderDao.updateOrderStateToIngByOrderNo(orderNo);
    }

    public int updateOrderStateToEndByOrderNo(String orderNo) {
        return workOrderDao.updateOrderStateToEndByOrderNo(orderNo);
    }

    public int updateById(WorkOrder order) {
        return workOrderDao.updateById(order);
    }

    public int increaseCollaborateCntByOrderNo(String orderNo) {
        return workOrderDao.increaseCollaborateCntByOrderNo(orderNo);
    }

    public int increaseOrderCntByOrderNo(String orderNo) {
        return workOrderDao.increaseOrderCntByOrderNo(orderNo);
    }

    @Transactional
    public int deleteByOrderNo(String orderNo) {
        workOrderStrategyService.deleteByOrderNo(orderNo);
        return workOrderDao.deleteByOrderNo(orderNo);
    }

    @Transactional
    public Map<String, Object> findPartByOrderNo(String orderNo) {
        //通过工单号查询零件信息
        Map<String,Object> map = workOrderDao.findPartByOrderNo(orderNo);
        //通过工单号查询机型
        WorkOrder workOrder = workOrderDao.selectByOrderNo(orderNo);
        String model = workOrder.getModel();
        if(map == null){
            map = new HashMap<>();
        }
        map.put("model", model);
        return map;
    }


    @Transactional
    public ResponseVo<ResultVo> findPartInfoByOrderNo(String orderNo,String partNo) {
        logger.debug("findPartInfoByOrderNo方法调用");
        WorkOrder workOrder = workOrderDao.selectByOrderNo(orderNo);
        String model = workOrder.getModel();
        //调用天远接口
        String partNoString = "{\"partNo\":\""+partNo+"\"}";
        logger.debug("partNo:"+partNo);
        JSONObject partNoJson = JSONObject.parseObject(partNoString);
        String requestJson = partNoJson.toString();
        logger.debug("requestJson:"+requestJson);
        String authorization = null;

        String serviceName = "GetAllPartInfo";
        //开发测试环境需要的代码
//        authorization = TianYuanUserHolder.getFullToken();
//        String partServieUrl = "http://tyapitest.tygps.com/v1/ZK/";
        //生产环境需要的代码
        TianYuanUser tianYuanUser = tianYuanUserComponent.getSpecialUser();
        authorization = tianYuanUser.getFullToken();
        String partServieUrl = configService.getTianYuanPartServiceProdUrl();


        String url = Commons.concatUrl(partServieUrl, serviceName);

        logger.debug("开始，请求天远地址url："+url);
        JSONObject resultJson = tianYuanApiBasicHelper.postJson(url, requestJson, authorization, "json");

        logger.debug("调用天远接口结束");
        List<JSONObject> list = (List) resultJson.get("data");

        ResultVo resultVo = new ResultVo();
        List<PartInfoList> list2 = new ArrayList<>();
        List<InventoryList> list3 = new ArrayList<>();
        JSONObject o = list.get(0);
        JSONArray partInfoList1 = o.getJSONArray("partInfoList");
        JSONArray inventoryList1 = o.getJSONArray("inventoryList");
        for(int i = 0;i<partInfoList1.size();i++){
            //创建要封装的对象
            PartInfoList partInfoList = new PartInfoList();
            List<PartsList> list1 = new ArrayList<>();
            JSONObject jsonPart = (JSONObject)partInfoList1.get(i);

            //PartInfoList对象的封装
            String vclType = jsonPart.get("vclType").toString();
            if(!model.equals(vclType)){
                continue;
            }
            partInfoList.setVclType(vclType);
            JSONArray partsList1 = jsonPart.getJSONArray("partsList");

            //PartsList对象的封装
            for(int j = 0;j<partsList1.size();j++){

                PartsList partsList = new PartsList();
                JSONObject jsonObject = (JSONObject)partsList1.get(j);
                String sysOneName = jsonObject.get("sysOneName").toString();
                String sysTwoName = jsonObject.get("sysTwoName").toString();
                String image = jsonObject.get("image").toString();
                String partName = jsonObject.get("partName").toString();
                String partPrice = jsonObject.get("partPrice").toString();
                partsList.setImage(image);
                partsList.setSysOneName(sysOneName);
                partsList.setPartName(partName);
                partsList.setPartPrice(partPrice);
                partsList.setSysTwoName(sysTwoName);
                list1.add(partsList);
            }
            partInfoList.setPartsList(list1);
            list2.add(partInfoList);
            break;
        }
        //InventoryList对象的封装
        for(int i = 0;i<inventoryList1.size();i++){
            InventoryList inventoryList = new InventoryList();
            JSONObject jsonObject = (JSONObject)inventoryList1.get(i);
            String warehouseName = jsonObject.get("warehouseName").toString();
            String inventoryQty = jsonObject.get("inventoryQty").toString();
            inventoryList.setInventoryQty(inventoryQty);
            inventoryList.setWarehouseName(warehouseName);
            list3.add(inventoryList);

        }
        resultVo.setInventoryList(list3);
        resultVo.setPartInfoList(list2);
        logger.debug("方法调用结束");
        if(resultVo.getPartInfoList().size()==0){
            return ResponseVo.success(new ResultVo());
        }
        return ResponseVo.success(resultVo);
    }

    @Transactional(rollbackFor = {TianyiException.class, Exception.class})
    public ResponseVo updateWorkStatus(String workId, String workStatus, String userAccount, String pauseReason, String dispatchId, int userId){
        //调用智能服务数据接口更新工作卡状态
        JSONObject reqJson = new JSONObject();
        reqJson.put("workStatus", workStatus);
        reqJson.put("workOid", workId);
        reqJson.put("pauseReason", pauseReason);
        //zhouwei 201904240745 增加派工ID字段
        reqJson.put("dispatchOid", dispatchId);
        //获取里程信息用
        reqJson.put("userAccount", userAccount);
        JSONObject retJson = tianYuanIntesrvApiHelper.getJson("WorkStatusUpdate", reqJson);
        if ("true".equals(String.valueOf(retJson.get("success")).toLowerCase())) {
            //zhouwei 20190910 取消头盔手机关联
//            JSONObject mqttJson = new JSONObject();
//            mqttJson.put("status", workStatus);
//            mqttJson.put("dispatchId", dispatchId);
//            MqttMessage message = new MqttMessage();
//            message.setQos(1);
//            message.setRetained(true);
//            message.setPayload(mqttJson.toJSONString().getBytes());
//            //工单状态用田一用户id做主题，消息中待派工OID
////            mqttClientService.publishMessage(MqttConfig.TOPIC_HM_WORKORDER + workId, message);
//            mqttClientService.publishMessage(MqttConfig.TOPIC_HM_WORKORDER + userId, message);
            return ResponseVo.success();
        } else {
            throw new TianyiException((String) retJson.get("msg"));
        }
    }

    public List listTianyiLinkMan(String department,String mobile, String name) {

        /*
        1、获取当前用户
        4、获取头盔好友列表
        2、根据当前用户部门获取部门下其他用户，如果是管理员，获取所有用户
        3、遍历用户
            3.1、获取网易账号
            3.2、获取在线状态
            3.3、判断头盔好友
         */
        User currentUser = TianyiUserHolder.get();

        Map<String, Object> queryInfo = new HashMap<>();
        queryInfo.put("department", department);
        queryInfo.put("organisation", currentUser.getOrganisation());
        queryInfo.put("mobile", mobile);
        queryInfo.put("name", name);
        List<User> users = userService.listByNoPage(queryInfo);
        List<TianyiContact> friends = tianyiContactService.selectByUserId(currentUser.getId());
        Set<Integer> friendIds = new HashSet<>();
        for (TianyiContact friend : friends) {
            friendIds.add(friend.getContactId());
        }
        List<HelmetOnlineStatus> onlineUser = helmetOnlineStatusService.getOnlineUser();
        List<Map<String, Object>> respUsers = new ArrayList();
        for (User user : users) {
            Map userTmp = new HashMap();
            userTmp.put("helmetFriend", friendIds.contains(user.getId())?"true":"false");
            userTmp.put("serviceSector", user.getDepartment());

            //zhouwei 201904231649 从在线状态表中获取在线离线状态
            userTmp.put("onLineStatus", "离线");
            for (HelmetOnlineStatus onlineTmp : onlineUser) {
                if (user.getId() == onlineTmp.getUserId()) {
                    userTmp.put("onLineStatus", "在线");
                    break;
                }
            }

            userTmp.put("contactName", user.getName());
            userTmp.put("tyUserId", user.getId());
            userTmp.put("linkManPhone", user.getMobile());
            userTmp.put("companyName", "天远");
            userTmp.put("deptName", user.getDepartment());
            userTmp.put("helmetAccount", user.getNeUserHel());
            userTmp.put("webAccount", user.getNeUserWeb());
            userTmp.put("phoneAccount", user.getNeUserPhn());
            respUsers.add(userTmp);
        }
        return respUsers;
    }
}
