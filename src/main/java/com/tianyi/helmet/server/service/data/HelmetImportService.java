package com.tianyi.helmet.server.service.data;

import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.websocket.sender.MessageSender;
import com.tianyi.helmet.server.dao.device.TmnlDao;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.device.TmnlInfo;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.dictionary.VersionService;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.vo.AppAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 头盔生产导运营数据导入接口
 * Created by tianxujin on 2019/6/4 14:06
 */
@Service
public class HelmetImportService {
    @Autowired
    private VersionService versionService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private TmnlDao tmnlDao;

    public int productStorage(String deviceUUID,String deviceNumber,String model,
                                        String batch,String version) {
        int returnStatus = 1;
        EquipmentLedger equipmentLedger = new EquipmentLedger();
        EquipmentLedger equip = equipmentService.selectByUUID(deviceUUID);
        if (equip != null) {
            equipmentLedger = equip;
        } else {
            equipmentLedger.setAffiliationId(1);
        }
        if (versionService.getVersion(version) == null) {
            versionService.addVersion(version);
        }
        int versionId = versionService.getVersion(version);
        equipmentLedger.setVersionId(versionId);
        equipmentLedger.setCategoryId(1);
        equipmentLedger.setDeviceNumber(deviceNumber);
        equipmentLedger.setModel(model);//TYH-IM-1A
        equipmentLedger.setBatch(batch);
        equipmentLedger.setDeviceUUID(deviceUUID);
        equipmentLedger.setRemark(MyConstants.DEVICE_CHANGE_ADD);
        if (equip != null) {
            returnStatus = 3;// 返修更新头盔
            int rs = equipmentService.update(equipmentLedger);
            if (!(rs > 0)) {
                returnStatus = -1;// 入库失败
            }
        } else {
            int rs = equipmentService.insert(equipmentLedger);
            if(rs == MyConstants.DEVICE_FIND_DUPLICATE) {
                returnStatus = 2;// 入库重复
            }
            if (!(rs > 0)) {
                returnStatus = -1;// 入库失败
            }
        }
//        EquipmentLedger equip1 = equipmentService.selectByDeviceNumber(deviceNumber);
//        if (equip1 != null) {
//            returnStatus = 3; //"设备编号已存在."
//        }
        return returnStatus;// 成功
    }

    public List<TmnlInfo> listUnImportDevs() {
        Map<String, Object> params = new HashMap<>();
        params.put("status", 0);
        List<TmnlInfo> list = tmnlDao.select(params);
        return list;
    }
    @Transactional
    public void updateImportStatus(TmnlInfo tmnlInfo) {
        tmnlDao.update(tmnlInfo);
    }
}
