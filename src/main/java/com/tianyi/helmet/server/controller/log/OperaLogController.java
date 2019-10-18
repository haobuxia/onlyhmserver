package com.tianyi.helmet.server.controller.log;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.log.OperaLog;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.log.OperaLogService;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作日志
 * <p>
 * Created by yujiawei on 2017/12/13.
 * <p>
 * wenxinyan 2018-8-16 重写了方法添加了分页功能
 */
@Controller
@RequestMapping("operalog")
public class OperaLogController {

    @Autowired
    private OperaLogService operaLogService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

//    @RequestMapping("list")
//    public Map<String, Object> list( Integer page, String clientId) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        Integer pageSize = 100;
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("clientId", clientId);
//        map.put("start", page-1);
//        map.put("length", pageSize);
//        List<OperaLog> logList = helmetOperaLogService.listBy(map);
//        int count = helmetOperaLogService.countBy(map);
//        result.put("clientId", clientId);
//        result.put("page", page);
//        result.put("data", logList);
//        result.put("count", count);
//        return result;
//    }


    @RequestMapping(value = "list")
    public String List(String keyword, String logflow, Model model) {
        return List(1, "", "", "0", "", "", keyword, logflow, model);
    }

    @RequestMapping("list/{page}")
    public String List(@PathVariable Integer page, String deviceType, String logType, String logNature, String time1, String time2, String keyword, String logflow, Model model) {
        Map<String, Object> map = PageListVo.createParamMap(page, 50);
        //map.put("clientId", keyword);
        if (deviceType != null && logType != null && logNature != null && time1 != null && time2 != null) noflow:{
            if (logflow != null && !logflow.equals("")) {
                map.put("logflowId", logflow);
                map.put("logNature", 0);
                break noflow;
            }

            if (deviceType.equals("TK")) {
                /**
                 * update by xiayuan 2018/10/10
                 * todo 通过网易账号查，但是显示没有
                 */
                map.put("clientId", keyword);
            } else if (deviceType.equals("FWQ")) {
                map.put("UUID", keyword);
            }
            map.put("deviceType", deviceType);
            map.put("logType", logType);
            map.put("logNature", Integer.parseInt(logNature));
            if (time1 != "") {
                map.put("crossTime1", new Date(Long.parseLong(time1)));
            }
            if (time2 != "") {
                map.put("crossTime2", new Date(Long.parseLong(time2)));
            }
        }
        else {
            map.put("logNature", 0);
        }
        List<OperaLog> logList = operaLogService.listBy(map);
        int count = operaLogService.countBy(map);
        PageListVo vo = new PageListVo(page, 50);
        vo.setList(logList);
        vo.setTotal(count);
        model.addAttribute("vo", vo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("logflow", logflow);
        model.addAttribute("deviceType", deviceType);
        model.addAttribute("logType", logType);

        return "file/operaLog";
    }

}
