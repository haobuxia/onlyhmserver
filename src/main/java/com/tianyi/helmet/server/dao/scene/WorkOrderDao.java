package com.tianyi.helmet.server.dao.scene;

import com.tianyi.helmet.server.entity.scene.svc.WorkOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 服务工单
 * Created by liuhanc on 2018/7/1.
 */
@Repository
public interface WorkOrderDao {

    void insert(WorkOrder order);

    WorkOrder selectById(int id);

    WorkOrder selectByOrderNo(String orderNo);

    List<WorkOrder> selectBy(Map<String, Object> map);

    int countBy(Map<String, Object> map);

    int updateOrderStateToIngByOrderNo(String orderNo);

    int updateOrderStateToEndByOrderNo(String orderNo);

    int updateById(WorkOrder order);

    int increaseCollaborateCntByOrderNo(String orderNo);

    int increaseOrderCntByOrderNo(String orderNo);

    int deleteByOrderNo(String orderNo);

    /**
     * 新增工单号零件信息关联表
     */
    int insertRel(Map<String ,String> map);
    /**
     * 通过单号查找零件信息
     */
    Map<String,Object> findPartByOrderNo(String orderNo);


//    List<Map<String, Object>> findPartInfoByOrderNo(String orderNo);


}
