package com.tianyi.helmet.server.service.scene;

import com.tianyi.helmet.server.dao.scene.WorkOrderStrategyDao;
import com.tianyi.helmet.server.entity.scene.svc.StrategyActionTypeEnum;
import com.tianyi.helmet.server.entity.scene.svc.StrategyEventTypeEnum;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务工单策略
 * <p>
 * Created by liuhanc on 2018/7/1.
 */
@Service
public class WorkOrderStrategyService {
    @Autowired
    private WorkOrderStrategyDao workOrderStrategyDao;

    public List<WorkOrderStrategy> selectByOrderNo(String orderNo) {
        List<WorkOrderStrategy> list = workOrderStrategyDao.selectByOrderNo(orderNo);
        list.stream().forEach(this::fulfillStrategyTypeName);
        return list;
    }

    public void addWorkOrderStrategy(WorkOrderStrategy strategy) {
        workOrderStrategyDao.insert(strategy);
    }

    public WorkOrderStrategy selectById(int id) {
        WorkOrderStrategy strategy = workOrderStrategyDao.selectById(id);
        fulfillStrategyTypeName(strategy);
        return strategy;
    }

    public int deleteByOrderNo(String orderNo) {
        return workOrderStrategyDao.deleteByOrderNo(orderNo);
    }

    public int deleteById(int id) {
        return workOrderStrategyDao.deleteById(id);
    }

    public void fulfillStrategyTypeName(WorkOrderStrategy strategy) {
        strategy.setEnventTypeName(StrategyEventTypeEnum.getTypeCnName(strategy.getEventType()));
        strategy.setActionTypeName(StrategyActionTypeEnum.getTypeCnName(strategy.getActionType()));
    }
}
