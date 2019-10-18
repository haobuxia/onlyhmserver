package com.tianyi.helmet.server.service.tianyuan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.HttpUtils;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 田一接口平台统一api
 * <p>
 * Created by tianxujin on 2019/8/16.
 */
@Service
public class TianyiUipApiHelper {
    @Autowired
    private ConfigService configService;

    private Logger logger = LoggerFactory.getLogger(TianyiUipApiHelper.class);

    /**
     * 获取作业卡列表
     * @param helmetNo 头盔编号
     * @return
     */
    public JSONObject getWorkOrderList(String helmetNo) {
        String url = configService.getTianyiIntesrvUrl() + "/interface/workorder/getWorkOrderList";
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("helmetNo", helmetNo);
        return getJson(url, requestParams);
    }

    /**
     * 执行某个url请求
     * @param url
     * @return
     */
    public JSONObject getJson(String url, Map<String, Object> requestParams) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(50000)
                .setConnectTimeout(50000)
                .setConnectionRequestTimeout(50000)
                .build();
        httpPost.setConfig(defaultRequestConfig);
        httpPost.setHeader("Content-Type","application/json;charset=utf-8");
        StringEntity postingString = new StringEntity(JSON.toJSONString(requestParams),"utf-8");
        httpPost.setEntity(postingString);
        String json = null;
        try {
            json = HttpUtils.executeHttp(httpPost,false);
        } catch (Exception e) {
            logger.error("tianyi uip exception.url=" + url, e);
            throw new TianYuanException("调用田一接口平台失败.url=" + url, e);
        }
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = null;
            try{
                jo = JSON.parseObject(json);
            }catch(Exception e){
                throw new TianYuanException("调用田一接口平台失败.url=" + url+",resp="+json);
            }

            /**
             * 0：参数异常
             1：成功
             2：成功，但结果集为0
             3：key无效
             4：异常报错
             */
            String status = jo.getString("success");
            if ("true".equals(status)) {
                return jo;
            }
            throw new TianYuanException("调用田一接口平台失败.status=" + status + ",msg=" + jo.getString("msg") + ",url=" + url);
        }
        return null;
    }

    public JSONObject updateStatus(String deviceNumber, String workId, String workStatus) {
        String url = configService.getTianyiIntesrvUrl() + "/interface/workorder/updateStatus";
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("helmetNo", deviceNumber);
        requestParams.put("workStatus", workStatus);
        Map<String, Object> params = new HashMap<>();
        params.put("data", requestParams);
        params.put("workCardNum", workId);
        return getJson(url, params);
    }
}
