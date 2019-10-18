package com.tianyi.helmet.server.controller.helmetinterface;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.tianyuan.TianYuanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 头盔端天远零配件接口
 * Created by liuhanc on 2018/1/22.
 */
@Controller
@RequestMapping("ty")
public class TyPartController {
    @Autowired
    private TianYuanService tianYuanService;

    private Logger logger = LoggerFactory.getLogger(TyPartController.class);

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping("data/{serviceName}")
    @ResponseBody
    public JSONObject dataService(@PathVariable String serviceName, HttpServletRequest request) {
        try {
            JSONObject jsonObject = tianYuanService.partServiceInvoke(serviceName, request);
            return jsonObject == null ? new JSONObject() : jsonObject;
        } finally {
//            logger.debug("\r\n调用天远接口:serviceName = " + serviceName + ",requestJson = " + requestJson + ",反馈=" + JSON.toJSONString(jsonObject) + "\r\n");
        }
    }

}
