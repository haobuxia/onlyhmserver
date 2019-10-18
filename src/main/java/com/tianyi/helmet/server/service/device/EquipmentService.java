package com.tianyi.helmet.server.service.device;

import com.tianyi.helmet.server.entity.device.EquipmentLedger;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EquipmentService {

    int insert(EquipmentLedger equipmentLedger);

    List<EquipmentLedger> select(Map<String, Object> map);

    List<EquipmentLedger> selectHistory(Map<String, Object> map);

    List<EquipmentLedger> selectAll(Map<String, Object> map);

    List<EquipmentLedger> selectAllHistory(Map<String, Object> map);

    int countBy(Map<String, Object> map);

    int countByHistory(Map<String, Object> map);

    int update(EquipmentLedger equipmentLedger);

    int updateUserId(Map<String, Object> map);

    int deleteById(String deviceUUID, String reason);

    boolean isOnLine(String deviceUUID);

    List<EquipmentLedger> selectByType(Boolean isActive);

    List<EquipmentLedger> fullfilCustomerNeUser(List<EquipmentLedger> list);

    List<EquipmentLedger> selectByUserId(int userId);

    EquipmentLedger selectByUUID(String deviceUUID);

    EquipmentLedger selectByDeviceNumber(String deviceNumber);

    void cacheGpsState(String imei, boolean stateOk);

    boolean isGpsStateOk(String imei);

    void cacheWillTime(String imei);

    Set<String> getEffectHelmetIdSet();

    int insertHistory(EquipmentLedger equipmentLedger);

    int updateHistory(EquipmentLedger equipmentLedger);

    int unbinded(EquipmentLedger device);

    String selectBlueBoxMac(int id);
}
