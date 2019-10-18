package com.tianyi.helmet.server.controller.data;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.UploadEntity;
import com.tianyi.helmet.server.service.data.HelmetOnlineStatusService;
import com.tianyi.helmet.server.service.data.ReferenceValueService;
import com.tianyi.helmet.server.service.file.TagService;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/3/20.
 */
@Controller
@RequestMapping("helmetOnlineStats")
@Api(value = "api", description = "头盔在线时长统计")
public class HelmetOnlineStatusController {
    @Autowired
    private HelmetOnlineStatusService helmetOnlineStatusService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value="statisticList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过条件查询用户头盔在线时长列表")
    public ResponseVo statisticList(@RequestParam String deviceNumber, @RequestParam String userName,
                                    @RequestParam(required = false) String startTime,
                                    @RequestParam(required = false) String endTime)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        if(deviceNumber != null && !"".equalsIgnoreCase(deviceNumber)) {
            params.put("deviceNumber", deviceNumber);
        }
        if(userName != null && !"".equalsIgnoreCase(userName)) {
            params.put("userName", userName);
        }
        if (startTime != null) {
            params.put("startTime", startTime);
        } else {
            return ResponseVo.fail("起始日期不能为空");
        }
        if (endTime != null) {
            params.put("endTime", endTime);
        } else {
            return ResponseVo.fail("截止日期不能为空");
        }
        return ResponseVo.success(helmetOnlineStatusService.getStatisticList(params));
    }

    @RequestMapping(value="statisticOne", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "按天分组统计一个用户头盔在线时长")
    public ResponseVo statisticOne(@RequestParam String clientId, @RequestParam Integer userId,
                                    @RequestParam(required = false) String startTime,
                                    @RequestParam(required = false) String endTime)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        if(clientId != null && !"".equalsIgnoreCase(clientId)) {
            params.put("clientId", clientId);
        }
        if(userId != null) {
            params.put("userId", userId);
        }
        if (startTime != null) {
            params.put("startTime", startTime);
        } else {
            ResponseVo.fail("起始日期不能为空");
        }
        if (endTime != null) {
            params.put("endTime", endTime);
        } else {
            ResponseVo.fail("截止日期不能为空");
        }
        return ResponseVo.success(helmetOnlineStatusService.statisticTimesByDay(params));
    }

    @RequestMapping(value="index", method = RequestMethod.GET)
    @ApiOperation(value = "打开头盔在线时长统计界面")
    public String index(Model model) {
        return "helmet/helmetOnlineStatsList";
    }

    @RequestMapping(value="videoList", method = RequestMethod.GET)
    @ApiOperation(value = "打开区域个人拍摄时长统计界面")
    public String videoList(Model model) {
        return "helmet/helmetVideoStatsList";
    }

    @RequestMapping(value="statisticListByArea", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过条件查询区域用户拍摄时长列表")
    public ResponseVo statisticListByArea(@RequestParam(required = false) String department, @RequestParam String userName,
                                    @RequestParam(required = false) String startTime,
                                    @RequestParam(required = false) String endTime)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        if(department != null && !"".equalsIgnoreCase(department)) {
            params.put("department", department);
        }
        if(userName != null && !"".equalsIgnoreCase(userName)) {
            params.put("userName", userName);
        }
        if (startTime != null) {
            params.put("startTime", startTime);
        } else {
            return ResponseVo.fail("起始日期不能为空");
        }
        if (endTime != null) {
            params.put("endTime", endTime);
        } else {
            return ResponseVo.fail("截止日期不能为空");
        }
        return ResponseVo.success(helmetOnlineStatusService.getStatisticListByArea(params));
    }

    @RequestMapping(value="statisticListByAreaUser", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过条件查询单个用户拍摄时长列表")
    public ResponseVo statisticListByAreaUser(@RequestParam(required = false) String department, @RequestParam Integer userId,
                                          @RequestParam(required = false) String startTime,
                                          @RequestParam(required = false) String endTime)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        if(department != null && !"".equalsIgnoreCase(department)) {
            params.put("department", department);
        }
        if(userId != null) {
            params.put("userId", userId);
        }
        if (startTime != null) {
            params.put("startTime", startTime);
        } else {
            return ResponseVo.fail("起始日期不能为空");
        }
        if (endTime != null) {
            params.put("endTime", endTime);
        } else {
            return ResponseVo.fail("截止日期不能为空");
        }
        return ResponseVo.success(helmetOnlineStatusService.getStatisticListByAreaUser(params));
    }

    @RequestMapping(value="statisticListByTag", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "通过条件查询标签拍摄次数列表")
    public ResponseVo statisticListByTag(@RequestParam(required = false) String department,
                                         @RequestParam(required = false) Integer organisation,
                                         @RequestParam(required = false) String userName,
                                         @RequestParam(required = false) String startTime,
                                         @RequestParam(required = false) String endTime)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        if(department != null && !"".equalsIgnoreCase(department)) {
            params.put("department", department);
        }
        if(organisation != null) {
            params.put("organisation", organisation);
        }
        if(userName != null && !"".equalsIgnoreCase(userName)) {
            params.put("userName", userName);
        }
        if (startTime != null) {
            params.put("startTime", startTime);
        } else {
            return ResponseVo.fail("起始日期不能为空");
        }
        if (endTime != null) {
            params.put("endTime", endTime);
        } else {
            return ResponseVo.fail("截止日期不能为空");
        }
        return ResponseVo.success(helmetOnlineStatusService.getShootNumListByTag(params));
    }

    @RequestMapping(value="videoTagsList", method = RequestMethod.GET)
    @ApiOperation(value = "按照标签统计区域个人拍摄时长")
    public String videoTagsList(Model model) {
        Map<String, Object> param = new HashMap<>();
        param.put("grouId", 1);
        List<Tag> list = tagService.listBy(param);
        model.addAttribute("tags", list);
        model.addAttribute("tagstr", JSON.toJSONString(list));
        return "helmet/helmetVideoTagStatsList";
    }
}
