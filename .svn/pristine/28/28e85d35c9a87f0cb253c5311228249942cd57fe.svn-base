package com.tianyi.helmet.server.controller.client;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.tianyuan.TianYuanMapApiHelper;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  车辆天远盒子imei相关
 *
 * Created by liuhanc on 2017/11/7.
 */
@Controller
@RequestMapping("imei")
public class ImeiController {
    @Autowired
    private TianYuanMapApiHelper tianYuanMapApiHelper;

    private Logger logger = LoggerFactory.getLogger(ImeiController.class);

    @RequestMapping("/getPointInCircle")
    @ResponseBody
    public ResponseVo getPointInCircle(float lon, float lat, float radius){
        JSONObject jo = tianYuanMapApiHelper.getPointInCircle(lon,lat,radius);
        return ResponseVo.success(jo);
    }

//    @RequestMapping("/map/{imei}")
//    public String showInMap(@PathVariable String imei, Model model) {
//        boolean exist = imeiService.exist(imei);
//        if (!exist) {
//            return "common/content404";
//        }
//
//        DoubleVo<List, List> dataVo = getImeiData(imei, null, null, null, null);
//        model.addAttribute("imei", imei);
//        model.addAttribute("data", JSON.toJSONString(dataVo));
//        model.addAttribute("type", "imei");
//        return "imei/imeiInMap";
//    }

    @RequestMapping("/index")
    public String index(Model model) {
//        List<DoubleVo<String, GpsLocationData>> imeiList = imeiService.listBy()
//                .stream()
//                .distinct()
//                .sorted()
//                .map(imei -> {
//                    GpsLocationData loc = tyBoxDataService.getLatestGpsLocation(imei);
//                    return new DoubleVo<String, GpsLocationData>(imei, loc);
//                })
//                .collect(Collectors.toList());
//        model.addAttribute("imeiList", JSON.toJSONString(imeiList));
        model.addAttribute("type", "imei");
        return "imei/imeiIndex";
    }

//    @RequestMapping("/data/{imei}")
//    @ResponseBody
//    public ResponseVo<DoubleVo> data(@PathVariable String imei, Integer year, Integer month, Integer day, Integer hour) {
//        DoubleVo<List, List> doublelVo = getImeiData(imei, year, month, day, hour);
//        ResponseVo<DoubleVo> vo = ResponseVo.success(doublelVo);
//        return vo;
//    }

//
//    //获得车辆某个时间段的传感器数据
//    private DoubleVo<List, List> getImeiData(String imei, Integer year, Integer month, Integer day, Integer hour) {
//        String dataType = null;
//        if (year == null) {
//            dataType = "year";
//        } else if (month == null) {
//            dataType = "month";
//        } else if (day == null) {
//            dataType = "day";
//        } else if (hour == null) {
//            dataType = "hour";
//        } else {
//            dataType = "minute";
//        }
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("imei", imei);
//        paramMap.put("hasData", 1);
//        paramMap.put("dataType", dataType);
//        paramMap.put("year", year);
//        paramMap.put("month", month);
//        paramMap.put("day", day);
//        paramMap.put("hour", hour);
//        List<Integer> dateDataLists = hour != null ? Collections.emptyList() : tyBoxDataService.selectDateDataList(paramMap);
//        List<TripleVo<Integer, Integer, Integer>> locList = tyBoxDataService.selectLocationList(paramMap);
//
//        DoubleVo<List, List> doublelVo = new DoubleVo(dateDataLists, locList);
//        return doublelVo;
//    }
}
