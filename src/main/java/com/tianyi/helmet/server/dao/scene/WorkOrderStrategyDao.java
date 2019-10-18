package com.tianyi.helmet.server.dao.scene;

import com.tianyi.helmet.server.entity.scene.svc.WorkOrderStrategy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 服务工单的策略。头盔根据这些策略自动执行一些操作
 * Created by liuhanc on 2018/7/1.
 */
@Repository
public interface WorkOrderStrategyDao {

    void insert(WorkOrderStrategy order);

    WorkOrderStrategy selectById(int id);

    List<WorkOrderStrategy> selectByOrderNo(String orderNo);

    int deleteByOrderNo(String orderNo);

    int deleteById(int id);
}
