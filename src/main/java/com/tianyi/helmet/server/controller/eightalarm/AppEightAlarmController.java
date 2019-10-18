package com.tianyi.helmet.server.controller.eightalarm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.tianyuan.TianYuanIntesrvApiHelper;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * //TODO 说明
 *
 * @author zhouwei
 * 2019-06-21 09:58
 * @version 0.1
 **/
@Api(value = "api", description = "八级警报")
@Controller
@RequestMapping("eightalarm")
@ResponseBody
public class AppEightAlarmController {
    @Autowired
    private TianYuanIntesrvApiHelper tianYuanIntesrvApiHelper;

    @RequestMapping(value = "getAlarmDetail", method = RequestMethod.POST)
    @ApiOperation("警报详情")
    public ResponseVo getAlarmDetail(@ApiParam(value = "工单OID", required = true) @RequestParam String workOid) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("workOid", workOid);
        return tianYuanIntesrvApiHelper.call("ty/alarm", "getAlarmDetail", reqJson);
    }

    @RequestMapping(value = "getTravel", method = RequestMethod.POST)
    @ApiOperation(value = "获取轨迹")
    public ResponseVo getTravel(@ApiParam(value = "里程OID", required = true) @RequestParam String mileageOid) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("mileageOid", mileageOid);
        return tianYuanIntesrvApiHelper.call("ty/alarm", "getTravel", reqJson);
    }

    @RequestMapping(value = "getCallLog", method = RequestMethod.POST)
    @ApiOperation(value = "获取轨迹")
    public ResponseVo getCallLog(@ApiParam(value = "音频路径", required = true) @RequestParam String voicePath) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("voicePath", voicePath);
        return tianYuanIntesrvApiHelper.call("ty/alarm", "getCallLog", reqJson);
    }

    @RequestMapping(value = "alarmWithRegion", method = RequestMethod.POST)
    @ApiOperation(value = "警报停机地域维度(地图)")
    public ResponseVo alarmWithRegion(@ApiParam(value = "开始时间", required = true) @RequestParam String startTime,
                                      @ApiParam(value = "结束时间", required = true) @RequestParam String endTime,
                                      @ApiParam(value = "工作小时", required = true) @RequestParam String workHours,
                                      @ApiParam(value = "是否停机", required = true) @RequestParam String isShutDown) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("startTime", startTime);
        reqJson.put("endTime", endTime);
        JSONArray hours = JSONArray.parseArray(workHours);
        reqJson.put("workHours", hours);
        reqJson.put("isShutDown", isShutDown);
        return tianYuanIntesrvApiHelper.call("ty/alarm", "alarmWithRegion", reqJson);
    }


    @RequestMapping(value = "alarmWithCustomer", method = RequestMethod.POST)
    @ApiOperation(value = "警报停机客户维度")
    public ResponseVo alarmWithCustomer(@ApiParam(value = "开始时间", required = true) @RequestParam String startTime,
                                      @ApiParam(value = "结束时间", required = true) @RequestParam String endTime,
                                      @ApiParam(value = "工作小时", required = true) @RequestParam String workHours,
                                        @ApiParam(value = "设备类型", required = true) @RequestParam String deviceType,
                                        @ApiParam(value = "服务地区", required = true) @RequestParam String srvArea,
                                        @ApiParam(value = "客户名称", required = true) @RequestParam String customerName) {

        JSONObject reqJson = new JSONObject();
        reqJson.put("startTime", startTime);
        reqJson.put("endTime", endTime);
        JSONArray hours = JSONArray.parseArray(workHours);
        reqJson.put("workHours", hours);
        JSONArray deviceTypes = JSONArray.parseArray(deviceType);
        if(deviceTypes.size()==0){
            deviceTypes.add("");
        }
        reqJson.put("deviceType", deviceTypes);
        reqJson.put("srvArea", srvArea);
        reqJson.put("customerName", customerName);
        return tianYuanIntesrvApiHelper.call("ty/alarm", "alarmWithCustomer", reqJson);
    }
}
