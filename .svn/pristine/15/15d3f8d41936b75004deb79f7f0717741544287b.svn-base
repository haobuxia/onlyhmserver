package com.tianyi.helmet.server.service.kmx;

import com.sagittarius.bean.common.TimePartition;
import com.sagittarius.bean.common.ValueType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tsinghua.thss.sdk.bean.common.DeviceType;
import tsinghua.thss.sdk.bean.common.Sensor;

import java.util.List;

/**
 * 设备类型定义
 * Created by liuhanc on 2018/1/2.
 *
 * kmx杜绝删除操作
 * wenxinyan 2018-8-17 注释了deleteAllDeviceTypeAndSensors，deleteDeviceType，deleteSensorOfDeviceType
 */
@Service
public class DeviceTypeService {
    @Autowired
    private KmxService kmxService;
    @Autowired
    private SensorSupport sensorSupport;


    private Logger logger = LoggerFactory.getLogger(DeviceTypeService.class);

    public Sensor createSensor(String sensorId, TimePartition timePartition, ValueType valueType, String description) {
        return new Sensor(sensorId, timePartition, valueType, description);
    }

    public DeviceType newDeviceType(String deviceTypeId, String description, List<Sensor> sensorList) {
        DeviceType deviceType = new DeviceType();
        deviceType.setId(deviceTypeId);
        deviceType.setDescription(description);
        deviceType.setSensors(sensorList);
        return deviceType;
    }

    public boolean saveDeviceType(DeviceType deviceType) {
        if (!kmxService.isEnabled())
            return false;
        try {
            kmxService.getWriter().createDeviceType(deviceType);
            return true;
        } catch (Exception e) {
            logger.error("saveDeviceType exception", e);
        }
        return false;
    }

    public DeviceType getDeviceType(String deviceTypeId) {
        if (!kmxService.isEnabled())
            return null;
        try {
            DeviceType typeA = kmxService.getReader().getDeviceType(deviceTypeId);
            return typeA;
        } catch (Exception e) {
            logger.error("saveDeviceType exception.deviceTypeId=" + deviceTypeId, e);
        }
        return null;
    }

    public List<DeviceType> getAllDeviceType() {
        if (!kmxService.isEnabled())
            return null;

        try {
            return kmxService.getReader().getAllDeviceType();
        } catch (Exception e) {
            logger.error("saveDeviceType exception", e);
        }
        return null;
    }

    //删除所有设备类型和设备类型对应的传感器
//    public void deleteAllDeviceTypeAndSensors() {
//        logger.debug("删除所有设备类型和对应的传感器...");
//        List<DeviceType> list = getAllDeviceType();
//        if (CollectionUtils.isEmpty(list)) {
//            return;
//        }
//
//        List<Sensor> helmetSensorList = sensorSupport.getHelmetSensorList();
//        List<Sensor> tyboxSensorList = sensorSupport.getTyBoxSensorList();
//
//        list.stream().forEach(dt -> {
//            String typeId = dt.getId();
//            int result = this.deleteDeviceType(typeId);
//            logger.debug("删除设备类型:"+typeId+",result="+result);
//            if (typeId.equals(SensorSupport.HELMET_DEVICETYPE_ID)) {
//                helmetSensorList.stream().forEach(sensor -> {
//                    int result2 = this.deleteSensorOfDeviceType(typeId, sensor.getId());
//                    logger.debug("删除设备类型对应传感器:"+typeId+",sensorId="+sensor.getId()+",result="+result2);
//                });
//            } else if (typeId.equals(SensorSupport.TYBOX_DEVICETYPE_ID)) {
//                tyboxSensorList.stream().forEach(sensor -> {
//                    int result2 = this.deleteSensorOfDeviceType(typeId, sensor.getId());
//                    logger.debug("删除设备类型对应传感器:"+typeId+",sensorId="+sensor.getId()+",result="+result2);
//                });
//            }
//        });
//    }
//
//    public int deleteDeviceType(String deviceTypeId) {
//        if (!kmxService.isEnabled())
//            return -1;
//        try {
//            return kmxService.getReader().deleteDeviceType(deviceTypeId);
//        } catch (Exception e) {
//            logger.error("deleteDeviceType exception.deviceTypeId=" + deviceTypeId, e);
//        }
//        return -1;
//    }
//
//    public int deleteSensorOfDeviceType(String deviceTypeId, String sensorId) {
//        try {
//            return kmxService.getReader().deleteSensorOfDeviceType(deviceTypeId, sensorId);
//        } catch (Exception e) {
//            logger.error("deleteDeviceTypeSensor exception.deviceTypeId=" + deviceTypeId + ",sensorId=" + sensorId, e);
//        }
//        return -1;
//    }

}
