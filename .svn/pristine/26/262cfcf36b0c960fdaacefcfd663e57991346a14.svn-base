package com.tianyi.helmet.server.controller.scene;

import com.tianyi.helmet.server.entity.scene.svc.*;
import com.tianyi.helmet.server.service.scene.WorkOrderService;
import com.tianyi.helmet.server.service.scene.WorkOrderStrategyService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 工单策略管理
 * Created by liuhanc on 2018/7/1.
 */
@Controller
@RequestMapping("workorderstrategy")
public class WorkOrderStrategyController {

    @Autowired
    private WorkOrderStrategyService workOrderStrategyService;
    @Autowired
    private WorkOrderService workOrderService;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMdd HHmm");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping("list/{orderNo}")
    public String listPage(@PathVariable String orderNo, Model model) {
        WorkOrder order = workOrderService.selectByOrderNo(orderNo,false);
        if(order != null){
            workOrderService.fillWorkOrderTypeStateName(order);
        }

        List<WorkOrderStrategy> strategyList = workOrderStrategyService.selectByOrderNo(orderNo);

        Map<String, String> eventTypeMap = Arrays.stream(StrategyEventTypeEnum.values()).collect(Collectors.toMap(type -> type.toString(), type -> type.getCnName()));
        model.addAttribute("eventTypeMap", eventTypeMap);
        Map<String, String> actionTypeMap = Arrays.stream(StrategyActionTypeEnum.values()).collect(Collectors.toMap(type -> type.toString(), type -> type.getCnName()));
        model.addAttribute("actionTypeMap", actionTypeMap);

        model.addAttribute("workOrder", order);
        model.addAttribute("strategyList", strategyList);
        return "scene/workOrderStrategyList";
    }

    @RequestMapping("get/{id}")
    @ResponseBody
    public ResponseVo<WorkOrderStrategy> getStrategy(@PathVariable int id) {
        WorkOrderStrategy strategy = workOrderStrategyService.selectById(id);
        return ResponseVo.success(strategy);
    }

    @RequestMapping("add")
    @ResponseBody
    public ResponseVo<WorkOrderStrategy> addStrategy(WorkOrderStrategy strategy) {
        WorkOrder order = workOrderService.selectByOrderNo(strategy.getOrderNo(),false);
        if (order == null) {
            return ResponseVo.fail("工单不存在." + strategy.getOrderNo());
        }
        if (!WorkOrderStateEnum.INIT.toString().equals(order.getOrderState())) {
            return ResponseVo.fail("工单" + WorkOrderStateEnum.getStateName(order.getOrderState()) + ",不可添加策略." + strategy.getOrderNo());
        }

        workOrderStrategyService.addWorkOrderStrategy(strategy);
        return ResponseVo.success(strategy);
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public ResponseVo deleteStrategy(@PathVariable int id) {
        WorkOrderStrategy strategy = workOrderStrategyService.selectById(id);
        if (strategy == null) {
            return ResponseVo.fail("策略不存在,不可删除.");
        }
        WorkOrder order = workOrderService.selectByOrderNo(strategy.getOrderNo(),false);
        if (order != null && !WorkOrderStateEnum.INIT.toString().equals(order.getOrderState())) {
            return ResponseVo.fail("工单" + WorkOrderStateEnum.getStateName(order.getOrderState()) + ",不可删除策略." + strategy.getOrderNo());
        }

        workOrderStrategyService.deleteById(id);
        return ResponseVo.success();
    }

    @RequestMapping("clear/{orderNo}")
    @ResponseBody
    public ResponseVo clearStrategy(@PathVariable String orderNo) {
        WorkOrder order = workOrderService.selectByOrderNo(orderNo,false);
        if (order != null && !WorkOrderStateEnum.INIT.toString().equals(order.getOrderState())) {
            return ResponseVo.fail("工单" + WorkOrderStateEnum.getStateName(order.getOrderState()) + ",不可删除策略." + orderNo);
        }

        int cnt = workOrderStrategyService.deleteByOrderNo(orderNo);
        return ResponseVo.success(cnt);
    }

}
