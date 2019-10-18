package com.tianyi.helmet.server.dao.device;

import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import jnr.ffi.annotations.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EquipmentDao {
    int insert(EquipmentLedger equipmentLedger);

    int insertHistory(EquipmentLedger equipmentLedger);

    int insertReason(Map<String, Object> map);

    int countByHistory(Map<String, Object> map);

    int countBy(Map<String, Object> map);

    List<EquipmentLedger> select(Map<String, Object> map);

    List<EquipmentLedger> selectHistory(Map<String, Object> map);

    int updateHistory(EquipmentLedger equipmentLedger);

    int update(EquipmentLedger equipmentLedger);

    int updateUserId(Map<String, Object> map);

    int updateById(@Param("deviceUUID") String deviceUUID);

    EquipmentLedger getByDeviceUUID(@Param("deviceUUID") String deviceUUID);

    EquipmentLedger getByDeviceNumber(@Param("deviceNumber") String deviceNumber);

    String getBlueBoxMac(@Param("deviceId") Integer deviceId);

}
