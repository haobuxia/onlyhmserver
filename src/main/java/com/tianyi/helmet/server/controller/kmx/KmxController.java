package com.tianyi.helmet.server.controller.kmx;

import com.sagittarius.bean.result.AbstractPoint;
import com.tianyi.helmet.server.service.client.TyBoxImeiService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.kmx.DeviceService;
import com.tianyi.helmet.server.service.kmx.DeviceTypeService;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.kmx.SensorSupport;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tsinghua.thss.sdk.bean.common.Device;
import tsinghua.thss.sdk.bean.common.DeviceType;
import tsinghua.thss.sdk.bean.common.Sensor;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.tianyi.helmet.server.service.kmx.SensorSupport.HELMET_DEVICE_IDPREFIX;
import static com.tianyi.helmet.server.service.kmx.SensorSupport.TYBOX_DEVICE_IDPREFIX;

/**
 * kmx数据库管理
 * 1、设备类型
 * 2、设备
 * 3、设备数据查询
 *
 * Created by liuhanc on 2018/1/10.
 */
@Controller
@RequestMapping("kmx")
public class KmxController {
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private KmxService kmxService;
    @Autowired
    private SensorSupport sensorSupport;
    @Autowired
    private TyBoxImeiService imeiService;
    @Autowired
    private EquipmentService equipmentService;

    private Logger logger = LoggerFactory.getLogger(KmxController.class);

    @RequestMapping("listDeviceType")
    public String listDeviceType(Model model){
        List<DeviceType> list = deviceTypeService.getAllDeviceType();
        logger.debug("listDeviceType result list.size="+(list == null ? null:list.size()));
        model.addAttribute("list",list);
        return "kmx/listDeviceType";
    }

    @RequestMapping("listDeviceIntance")
    public String listDeviceIntance(Model model){
        List<Device> list = deviceService.getAllDevice();
        logger.debug("listDevice result list.size="+(list == null ? null:list.size()));
        if(list != null){
            Comparator<Device> comparator = Comparator.comparing(Device::getDeviceTypeId);
            list = list.stream().sorted(comparator).collect(Collectors.toList());
        }
        model.addAttribute("list",list);
        return "kmx/listDeviceIntance";
    }

    @RequestMapping("listData")
    public String listData(){
        return "kmx/listKmxData";
    }

    @RequestMapping("loadDeviceId")
    @ResponseBody
    public ResponseVo loadDeviceId(@RequestParam String deviceType){
        List<String> list = null;
        if("helmet".equals(deviceType)){
            /**
             * update by xiayuan 2018/10/10
             */
            list = equipmentService.getEffectHelmetIdSet().stream().map(hemletId->HELMET_DEVICE_IDPREFIX+hemletId).collect(Collectors.toList());
        }else{
            list = imeiService.getEffectImeiIdSet().stream().map(imei->TYBOX_DEVICE_IDPREFIX+imei).collect(Collectors.toList());
        }
        return ResponseVo.success(list);
    }

    @RequestMapping("loadSensorId")
    @ResponseBody
    public ResponseVo loadSensorId(@RequestParam String deviceType){
        List<String> list = null;
        if("helmet".equals(deviceType)){
            list = sensorSupport.toSensorIdList(sensorSupport.getHelmetSensorList());
        }else{
            list = sensorSupport.toSensorIdList(sensorSupport.getTyBoxSensorList());
        }
        return ResponseVo.success(list);
    }

    @RequestMapping("queryData")
    public String listData(@RequestParam String deviceId, @RequestParam String sensorId, @RequestParam long time1, @RequestParam long time2, Model model){
        List<Sensor> sensorList = null;
        if(deviceId.startsWith(SensorSupport.HELMET_DEVICE_IDPREFIX)){
            sensorList = sensorSupport.getHelmetSensorList();
        }else{
            sensorList = sensorSupport.getTyBoxSensorList();
        }
        PageListVo vo = new PageListVo();
        Sensor sensor = sensorList.stream().filter(s->s.getId().equals(sensorId)).findAny().orElse(null);
        if(sensor == null){
            model.addAttribute("vo",vo);
            return "kmx/listDataContent";
        }

        String valueType = sensor.getValueType().toString().toLowerCase();
        model.addAttribute("dataType",valueType);

        List<? extends AbstractPoint> list = null;
        if("string".equals(valueType)){
            list =  kmxService.queryStringRange(deviceId,sensorId,time1,time2);
        }else if("int".equals(valueType)){
            list =  kmxService.queryIntRange(deviceId,sensorId,time1,time2);
        }else if("float".equals(valueType)){
            list =  kmxService.queryFloatRange(deviceId,sensorId,time1,time2);
        }else if("geo".equals(valueType)){
            list =  kmxService.queryGeoRange(deviceId,sensorId,time1,time2);
        }
        vo.setList(list);
        int listSize = list == null ? 0 : list.size();
        vo.setTotal(listSize);
        vo.setPageSize(listSize);

        logger.debug("kmx数据查询.dataType="+valueType+",结果记录数："+listSize+",deviceId="+deviceId+",sensorId="+sensorId+",time="+time1+",["+new Date(time1)+"]->"+time2+"["+new Date(time2)+"]");
        model.addAttribute("vo",vo);
        return "kmx/listDataContent";
    }

    @RequestMapping("insertData")
    @ResponseBody
    public ResponseVo insertData(@RequestParam String deviceId, @RequestParam String sensorId, @RequestParam long time1, @RequestParam long time2,@RequestParam String value){
        List<Sensor> sensorList = null;
        if(deviceId.startsWith(SensorSupport.HELMET_DEVICE_IDPREFIX)){
            sensorList = sensorSupport.getHelmetSensorList();
        }else{
            sensorList = sensorSupport.getTyBoxSensorList();
        }
        Sensor sensor = sensorList.stream().filter(s->s.getId().equals(sensorId)).findAny().orElse(null);
        if(sensor == null){
           return ResponseVo.fail("传感器不存在");
        }

        String valueType = sensor.getValueType().toString().toLowerCase();
        boolean insertSuccess = false;
        try{
            if("string".equals(valueType)){
                insertSuccess = kmxService.insert(deviceId,sensorId,time1,System.currentTimeMillis(),value);
            }else if("int".equals(valueType)){
                insertSuccess =  kmxService.insert(deviceId,sensorId,time1,System.currentTimeMillis(),Integer.parseInt(value));
            }else if("float".equals(valueType)){
                insertSuccess = kmxService.insert(deviceId,sensorId,time1,System.currentTimeMillis(),Float.parseFloat(value));
            }else if("geo".equals(valueType)){
                String[] arr = value.split(",");
                insertSuccess = kmxService.insertGeo(deviceId,sensorId,time1,System.currentTimeMillis(),new Float[]{Float.parseFloat(arr[0]),Float.parseFloat(arr[1])});
            }else{
                return ResponseVo.fail("传感器类型不支持."+valueType);
            }
        }catch(Exception e){
            //NumberFormatException
            logger.error("插入kmx数据异常.",e);
            return ResponseVo.fail("添加失败."+e.getMessage());
        }

        if(insertSuccess) {
            logger.debug("kmx数据入库.dataType="+valueType+",deviceId="+deviceId+",sensorId="+sensorId+",time="+time1+",["+new Date(time1)+"],value="+value);
            return ResponseVo.success();
        }
        return ResponseVo.fail("添加数据失败");
    }
}
