package com.tianyi.helmet.server.controller.scene;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.tianyuan.TianYuanException;
import com.tianyi.helmet.server.service.tianyuan.TianYuanService;
import org.apache.commons.io.IOUtils;
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
 * 后台天远零配件接口对接
 * 该接口主要为web端网页调用时使用(非跨域)
 * web js通过ajax调用时，在请求的header里传入头盔的网易账号。后台会对网易账号进行检查处理从而得到网易账号对应头盔绑定的天远账号，从而实现天远零件接口调用
 * Created by liuhanc on 2018/6/22.
 */
@Controller
@RequestMapping("typartdata")
public class TyPartDataController {
    @Autowired
    private TianYuanService tianYuanService;

    private Logger logger = LoggerFactory.getLogger(TyPartDataController.class);

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
