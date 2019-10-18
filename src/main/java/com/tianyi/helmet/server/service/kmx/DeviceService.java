package com.tianyi.helmet.server.service.kmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tsinghua.thss.sdk.bean.common.Device;
import tsinghua.thss.sdk.bean.common.Sensor;

import java.util.List;

/**
 * 设备定义
 * <p>
 * Created by liuhanc on 2018/1/2.
 *
 * kmx杜绝删除操作
 * wenxinyan 2018-8-17 注释了deleteAllDeviceAndSensors，deleteDevice，deleteSensorOfDevice
 */
@Service
public class DeviceService {
    @Autowired
    private KmxService kmxService;
    @Autowired
    private SensorSupport sensorSupport;

    private Logger logger = LoggerFactory.getLogger(DeviceService.class);

    public boolean createDeviceInstance(String deviceTypeId, String deviceId, String deviceDesc,List<Sensor> deviceSensorList) {
        if (!kmxService.isEnabled())
            return false;

        Device device1 = new Device();
        device1.setDeviceTypeId(deviceTypeId);
        device1.setId(deviceId);
        device1.setDescription(deviceDesc);
        boolean createDeviceSuccess = false;
        try {
            kmxService.getWriter().createDevice(device1);
            createDeviceSuccess = true;
        } catch (Exception e) {
            createDeviceSuccess = false;
            logger.error("createDeviceInstance exception.deviceTypeId=" + deviceTypeId + ",deviceId=" + deviceId, e);
        }

        if(createDeviceSuccess){
//            logger.debug("为设备"+deviceId+"添加传感器...");
            try{
                kmxService.getWriter().addSensors(deviceId,deviceSensorList);
                return true;
            }catch(Exception e){
                logger.error("addSensors exception.deviceTypeId=" + deviceTypeId + ",deviceId=" + deviceId,e);
            }
        }
        return false;
    }

    public Device getDevice(String deviceId) {
        if (!kmxService.isEnabled())
            return null;
        try {
            return kmxService.getReader().getDeviceById(deviceId);
        } catch (Exception e) {
            logger.error("getDeviceById exception.deviceId=" + deviceId, e);
        }
        return null;
    }

    public List<Sensor> getDeviceSensorList(String deviceId) {
        if (!kmxService.isEnabled())
            return null;

        try {
            return kmxService.getReader().getSensorsByDeviceId(deviceId);
        } catch (Exception e) {
            logger.error("getDeviceById exception.deviceId=" + deviceId, e);
        }
        return null;
    }

    public List<Device> getAllDevice() {
        if (!kmxService.isEnabled())
            return null;
        try {
            return kmxService.getReader().getAllDevice();
        } catch (Exception e) {
            logger.error("getAllDevice exception", e);
        }
        return null;
    }

//    public void deleteAllDeviceAndSensors() {
//        logger.debug("删除所有设备和对应的传感器...");
//        List<Device> deviceList = getAllDevice();
//        if (CollectionUtils.isEmpty(deviceList)) {
//            return;
//        }
//
//        List<Sensor> helmetSensorList = sensorSupport.getHelmetSensorList();
//        List<Sensor> tyboxSensorList = sensorSupport.getTyBoxSensorList();
//
//        deviceList.stream().forEach(device -> {
//            int result = this.deleteDevice(device.getId());
//            logger.debug("删除设备:"+device.getId()+",result="+result);
//            String typeId = device.getDeviceTypeId();
//            if (typeId.equals(SensorSupport.HELMET_DEVICETYPE_ID)) {
//                helmetSensorList.stream().forEach(sensor -> {
//                    int result2 = this.deleteSensorOfDevice(device.getId(), sensor.getId());
//                    logger.debug("删除设备对应传感器:"+device.getId()+",sensorId="+sensor.getId()+",result="+result2);
//                });
//            } else if (typeId.equals(SensorSupport.TYBOX_DEVICETYPE_ID)) {
//                tyboxSensorList.stream().forEach(sensor -> {
//                    int result2 = this.deleteSensorOfDevice(device.getId(), sensor.getId());
//                    logger.debug("删除设备对应传感器:"+device.getId()+",sensorId="+sensor.getId()+",result="+result2);
//                });
//            } else {
//                List<Sensor> sensorList = getDeviceSensorList(device.getId());
//                if (!CollectionUtils.isEmpty(sensorList)) {
//                    sensorList.stream().forEach(sensor -> {
//                        int result2 =  this.deleteSensorOfDevice(device.getId(), sensor.getId());
//                        logger.debug("删除设备对应传感器:"+device.getId()+",sensorId="+sensor.getId()+",result="+result2);
//                    });
//                }
//            }
//        });
//    }
//
//    public int deleteDevice(String deviceId) {
//        if (!kmxService.isEnabled())
//            return -1;
//        try {
//            return kmxService.getReader().deleteDevice(deviceId);
//        } catch (Exception e) {
//            logger.error("deleteDevice exception.deviceId=" + deviceId, e);
//        }
//        return -1;
//    }
//
//    public int deleteSensorOfDevice(String deviceId, String sensorId) {
//        if (!kmxService.isEnabled())
//            return -1;
//        try {
//            return kmxService.getReader().deleteSensorOfDevice(deviceId, sensorId);
//        } catch (Exception e) {
//            logger.error("deleteSensorOfDevice exception.deviceId=" + deviceId + ",sensorId=" + sensorId, e);
//        }
//        return -1;
//    }
}
