package com.tianyi.helmet.server.service.kmx;

import com.tianyi.helmet.server.service.client.TyBoxImeiService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.support.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsinghua.thss.sdk.bean.common.Device;
import tsinghua.thss.sdk.bean.common.DeviceType;
import tsinghua.thss.sdk.bean.common.Sensor;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * kmx元数据初始化服务
 *
 * Created by liuhanc on 2018/1/3.
 *
 * kmx杜绝删除操作
 * wenxinyan 2018-8-17 修改了init 注释了deleteAllHelmetData，deleteAllTyBoxData
 */
@Service
public class MetaDataInitService {

    @Autowired
    private KmxService kmxService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private TyBoxImeiService imeiService;
    @Autowired
    private SensorSupport sensorSupport;
    @Autowired
    private ConfigService configService;

    private Logger logger = LoggerFactory.getLogger(MetaDataInitService.class);


    /**
     * 设备类型和设备初始化
     */
    @PostConstruct
    public void init() {
        if (!kmxService.isEnabled()) {
            return;
        }
        boolean isNeedReCreate = configService.getKmxReCreate() == 1;
        if(isNeedReCreate){
            logger.debug("kmx数据库需要重建...");
            /*
            * wenxinyan 2018-8-17 注释删除操作
            * */
            //删除旧数据
//            deleteAllHelmetData();
//            deleteAllHelmetData();
//            //删除设备
//            deviceService.deleteAllDeviceAndSensors();
//            deviceTypeService.deleteAllDeviceTypeAndSensors();
        }

        boolean isNeedInit = configService.getKmxInitMeta() == 1;
        if(isNeedInit){
            logger.debug("kmx数据库元数据初始化检查...");

            //初始化设备类型
//            initDeviceTypeHelmet();
//            initDeviceTypeTyBox();

            //初始化设备实例
            logger.debug("初始化设备实例...");
            initAllHelmetDevices();
            initAllTyBoxDevices();
        }
    }

//    private void deleteAllHelmetData(){
//        List<String> helmetSensorIdList = sensorSupport.toSensorIdList(sensorSupport.getHelmetSensorList());
//        helmetService.getEffectHelmetIdSet().stream().forEach(clientId -> {
//            logger.debug("删除头盔所有数据."+clientId);
//            kmxService.deleteDeviceData(SensorSupport.HELMET_DEVICE_IDPREFIX + clientId,helmetSensorIdList);
//        });
//    }
//
//    private void deleteAllTyBoxData(){
//        List<String> tyBoxSensorIdList = sensorSupport.toSensorIdList(sensorSupport.getTyBoxSensorList());
//        imeiService.getEffectImeiIdSet().stream().forEach(imei -> {
//            logger.debug("删除车辆所有数据."+imei);
//            kmxService.deleteDeviceData(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei,tyBoxSensorIdList);
//        });
//    }

    //初始化头盔设备
    private void initDeviceTypeHelmet() {
        DeviceType dt = deviceTypeService.getDeviceType(SensorSupport.HELMET_DEVICETYPE_ID);
        if (dt != null) {
            logger.debug("设备类型:" + SensorSupport.HELMET_DEVICETYPE_ID + "已初始化,不再初始化");
            return;
        }

        logger.debug("初始化设备类型:" + SensorSupport.HELMET_DEVICETYPE_ID + "...");

        List<Sensor> allSensorList = sensorSupport.getHelmetSensorList();
        DeviceType dt1 = deviceTypeService.newDeviceType(SensorSupport.HELMET_DEVICETYPE_ID, "田一智能头盔版本1", allSensorList);
        deviceTypeService.saveDeviceType(dt1);
    }

    //初始化天远盒子设备
    private void initDeviceTypeTyBox() {
        DeviceType dt = deviceTypeService.getDeviceType(SensorSupport.TYBOX_DEVICETYPE_ID);
        if (dt != null) {
            logger.debug("设备类型:" + SensorSupport.TYBOX_DEVICETYPE_ID + "已初始化,不再初始化");
            return;
        }

        logger.debug("初始化设备类型:" + SensorSupport.TYBOX_DEVICETYPE_ID + "...");
        List<Sensor> sensorList = sensorSupport.getTyBoxSensorList();

        DeviceType dt1 = deviceTypeService.newDeviceType(SensorSupport.TYBOX_DEVICETYPE_ID, "天远盒子", sensorList);
        deviceTypeService.saveDeviceType(dt1);
    }

    //初始化所有已有头盔设备
    private void initAllHelmetDevices() {
        /**
         * update by xiayuan 2018/10/10
         */
        equipmentService.getEffectHelmetIdSet().stream().forEach(clientId -> {
            initDeviceHelmetDevice(clientId);
        });
    }

    //初始化所有已有天远盒子
    private void initAllTyBoxDevices() {
        imeiService.getEffectImeiIdSet().stream().forEach(imei -> {
            initDeviceTyBoxDevice(imei);
        });
    }

    /**
     * 初始化某个头盔设备
     *
     * @param helmetClientId
     * @return
     */
    public boolean initDeviceHelmetDevice(String helmetClientId) {
        if (!kmxService.isEnabled()) {
            return false;
        }

        Device device = deviceService.getDevice(SensorSupport.HELMET_DEVICE_IDPREFIX + helmetClientId);
        if (device != null)
            return true;

        List<Sensor> sensorList = sensorSupport.getHelmetSensorList();
        boolean success = deviceService.createDeviceInstance(SensorSupport.HELMET_DEVICETYPE_ID,
                SensorSupport.HELMET_DEVICE_IDPREFIX + helmetClientId, "田一智能头盔:" + helmetClientId,
                sensorList);
        logger.debug("创建头盔设备:" + helmetClientId + ",结果=" + success);
        return success;
    }

    /**
     * 初始化某个天远盒子设备
     *
     * @param imei
     * @return
     */
    public boolean initDeviceTyBoxDevice(String imei) {
        if (!kmxService.isEnabled()) {
            return false;
        }

        Device device = deviceService.getDevice(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei);
        if (device != null)
            return true;

        List<Sensor> sensorList = sensorSupport.getTyBoxSensorList();
        boolean success = deviceService.createDeviceInstance(SensorSupport.TYBOX_DEVICETYPE_ID, SensorSupport.TYBOX_DEVICE_IDPREFIX + imei,
                "车载蓝牙盒子IMEI:" + imei, sensorList);
        logger.debug("创建天远盒子设备:" + imei + ",结果=" + success);
        return success;
    }

    /**
     * 初始化某个天远盒子设备sg
     *
     * @param imei
     * @return
     */
    public boolean initDeviceSgTyBoxDevice(String imei) {
        if (!kmxService.isEnabled()) {
            return false;
        }

        Device device = deviceService.getDevice(SensorSupport.TYBOX_DEVICE_IDPREFIX + imei);
        if (device != null)
            return true;

        List<Sensor> sensorList = sensorSupport.getSgTyBoxSensorList();
        boolean success = deviceService.createDeviceInstance(SensorSupport.TYBOX_DEVICETYPE_ID, SensorSupport.TYBOX_DEVICE_IDPREFIX + imei,
                "车载蓝牙盒子IMEI:" + imei, sensorList);
        logger.debug("创建天远盒子设备:" + imei + ",结果=" + success);
        return success;
    }

}
