package com.tianyi.helmet.server.controller.scene;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.UploadEntity;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrder;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrderTypeEnum;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.file.ListFilterService;
import com.tianyi.helmet.server.service.file.TagGroupService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.scene.WorkOrderService;
import com.tianyi.helmet.server.service.scene.WorkOrderStrategyService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanService;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import com.tianyi.helmet.server.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工单管理
 * Created by liuhanc on 2018/7/1.
 */
@Controller
@RequestMapping("workordermanage")
public class WorkOrderManageController {

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private WorkOrderStrategyService workOrderStrategyService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagGroupService tagGroupService;
    @Autowired
    private ListFilterService listFilterService;
    @Autowired
    private TianYuanService tianYuanService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ConfigService configService;

    private Logger logger = LoggerFactory.getLogger(WorkOrderManageController.class);

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMdd HHmm");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping("index")
    public String toListPage(Model model) {
        Map<String, String> typeMap = Arrays.stream(WorkOrderTypeEnum.values()).collect(Collectors.toMap(type -> type.toString(), type -> type.getCnName()));
        List<Tag> tagList = listFilterService.selectTagListByGroupId(tagGroupService.getSvcDataGroupId());
        //工单分类
        model.addAttribute("typeMap", typeMap);
        model.addAttribute("tagList", tagList);
        return "scene/workOrderIndex";
    }

    @RequestMapping("search")
    public String loadPage(Integer page, String keyword, Model model) {
        if (page == null || page.intValue() <= 0) page = 1;
        PageListVo<WorkOrder> vo = workOrderService.search(keyword, page);
        model.addAttribute("vo", vo);
        model.addAttribute("keyword", keyword);
        return "scene/workOrderSearchResult";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResponseVo<WorkOrder> addWorkOrder(WorkOrder order) {
        if (order.getPlanTime() == null) {
            return ResponseVo.fail("没有指定排工时间");
        }

        String userRealName = order.getUserRealName();
        Map<String,Object> map = new HashMap<>();
        map.put("name",userRealName);
        List<User> users = userService.listByNoPage(map);
        User user = users.get(0);
        if (user == null) {
            return ResponseVo.fail("派工的服务人员不存在." + userRealName);
        }

        order.setUserId(user.getId());
        workOrderService.insert(order);
        return ResponseVo.success(order);
    }

    /**
     * 根据工单号获取工单详情
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("get/{orderNo}")
    @ResponseBody
    public ResponseVo<WorkOrder> getWorkOrder(@PathVariable String orderNo) {
        WorkOrder order = workOrderService.selectByOrderNo(orderNo, true);
        return ResponseVo.success(order);
    }


    @RequestMapping("save")
    @ResponseBody
    public ResponseVo<WorkOrder> saveWorkOrder(WorkOrder order) {
        if (order.getId() == 0) {
            return ResponseVo.fail("工单保存请求有误");
        }

        String userRealName = order.getUserRealName();
        Map<String,Object> map = new HashMap<>();
        map.put("name",userRealName);
        List<User> users = userService.listByNoPage(map);
        User user = users.get(0);
        if (user == null) {
            return ResponseVo.fail("派工的服务人员不存在." + userRealName);
        }

        order.setUserId(user.getId());
        workOrderService.updateById(order);
        return ResponseVo.success(order);
    }

    @RequestMapping("delete/{orderNo}")
    @ResponseBody
    public ResponseVo deleteWorkOrder(@PathVariable String orderNo) {
        workOrderService.deleteByOrderNo(orderNo);
        return ResponseVo.success();
    }

    /**
     * 头盔扫码 服务端调用天远零件询价接口
     *
     * @param orderNo
     * @param request
     * @return
     */
    @RequestMapping("data/{orderNo}")
    @ResponseBody
        public ResponseVo dataService2(@PathVariable String orderNo, HttpServletRequest request) {
        try {
            logger.debug("扫码接口调用");
            String serviceName = "GetAllPartInfo";
            ResponseVo<Map<String ,Object>> responseVo = tianYuanService.partServiceInvokeTY(serviceName, request, orderNo);
            return responseVo;


        } finally {
//            logger.debug("\r\n调用天远接口:serviceName = " + serviceName + ",requestJson = " + requestJson + ",反馈=" + JSON.toJSONString(jsonObject) + "\r\n");
        }
    }

    /**
     * 根据工单号获取零件信息
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("/{orderNo}/part")
    @ResponseBody
    public ResponseVo<Map<String, Object>> getPart(@PathVariable String orderNo) {
        logger.debug("根据工单号获取零件信息接口调用");
        if (StringUtils.isEmpty(orderNo)||"undefined".equals(orderNo)) {
            return ResponseVo.fail("参数传递错误");
        }
        Map<String, Object> resultMap = workOrderService.findPartByOrderNo(orderNo);
        return ResponseVo.success(resultMap);
    }

    /**
     * 根据工单号获取零件信息
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("/{orderNo}/part/{partNo}/")
    @ResponseBody
    public ResponseVo<ResultVo> getPartInfo(@PathVariable String orderNo,@PathVariable String partNo) {
        logger.debug("根据工单号获取零件信息接口调用");
        if (StringUtils.isAnyEmpty(orderNo,partNo)||"undefined".equals(orderNo)||"undefined".equals(partNo)) {
            return ResponseVo.fail("参数传递错误");
        }
        //约定好
        if("undo".equals(partNo)){
            ResultVo resultVo = new ResultVo();
            return ResponseVo.success(resultVo);
        }
        ResponseVo<ResultVo> result = workOrderService.findPartInfoByOrderNo(orderNo,partNo);
        return result;
    }

    /**
     * 根据工单号获取视频列表
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("/{orderNo}/video/{page}")
    @ResponseBody
    public ResponseVo getVideoListByOrderNo(@PathVariable String orderNo, @PathVariable String page) {
        logger.debug("根据工单号获取视频列表接口调用");
        Integer pages = 1;
        if(!page.matches("[0-9]")){
            pages = 1;
        }else{
            pages = Integer.valueOf(page);
        }
        PageListVo<UploadEntity> vo = videoService.getVideoListByOrderNo(orderNo,pages);
        return ResponseVo.success(vo);
    }

    @RequestMapping("/{orderNo}/detail")
    public String workOrderDetails(Model model, @PathVariable String orderNo) {
        model.addAttribute("orderNo",orderNo);
        model.addAttribute("fileServer",configService.getFastdfsServerUrl());
        return "scene/workOrderDetail";
    }
}
