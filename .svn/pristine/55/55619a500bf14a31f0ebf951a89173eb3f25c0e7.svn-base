package com.tianyi.helmet.server.service.tianyuan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.client.HelmetUniversalurlInfo;
import com.tianyi.helmet.server.service.client.HelmetUniversalurlService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.HttpUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 天远小松工厂api
 * <p>
 * Created by tianxujin on 2019/6/13.
 */
@Service
public class TianYuanKomatsuApiHelper {
    @Autowired
    private ConfigService configService;

    @Autowired
    private HelmetUniversalurlService helmetUniversalurlService;

    private Logger logger = LoggerFactory.getLogger(TianYuanKomatsuApiHelper.class);

    public JSONObject getPendingCars(String userId, Integer project, String deviceNumber) {
        String urltype = "cars";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("deviceNumber", deviceNumber);
        return getJsonObject(project, urltype, params);
    }

    private JSONObject getJsonObject(Integer project, String urltype, Map<String, Object> params) {
        List<HelmetUniversalurlInfo> urls = helmetUniversalurlService.listUniversalurls(project, urltype);
        if(urls != null && urls.size() == 1) {
            String url = urls.get(0).getUrl();
            if(params.size() > 0) {
                url = url + "?";
            }
            int count = 0;
            for(String key : params.keySet()) {
                if(count>0) {
                    url = url + "&" + key + "=" + params.get(key);
                } else {
                    url = url + key + "=" + params.get(key);
                }
                count++;
            }
            return getJson(url);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", new ArrayList<Map<String, Object>>());
        return jsonObject;
    }

    public JSONObject getPendingWorks(String userId, String cid, Integer project) {
        String urltype = "works";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("cid", cid);
        return getJsonObject(project, urltype, params);
    }

    /**
     * 执行某个url请求
     *
     * @param url
     * @return
     */
    public JSONObject getJson(String url) {
        HttpPost httpGet = new HttpPost(url);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(50000)
                .setConnectTimeout(50000)
                .setConnectionRequestTimeout(50000)
                .build();
        httpGet.setConfig(defaultRequestConfig);
        String json = null;
        try {
            json = HttpUtils.executeHttp(httpGet,false);
//            logger.info("tianyuan mapapi get response:" + json+",url="+url);
        } catch (Exception e) {
            logger.error("tianyuan Komatsuapi exception.url=" + url, e);
            throw new TianYuanException("调用天远小松工厂项目接口失败.url=" + url, e);
        }

        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = null;
            try{
                jo = JSON.parseObject(json);
            }catch(Exception e){
                throw new TianYuanException("调用天远小松工厂项目接口失败.url=" + url+",resp="+json);
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
            throw new TianYuanException("调用天远小松工厂项目接口失败.status=" + status + ",msg=" + jo.getString("msg") + ",url=" + url);
        }
        return null;
    }

    public JSONObject startOrder(String userId, String id, Integer project, String isQuick) {
        String urltype = "startwork";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("wid", id);
        params.put("isQuick", isQuick);
        return getJsonObject(project, urltype, params);
    }

    public JSONObject endOrder(String userId, String id, Integer project, String isQuick) {
        String urltype = "endwork";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("wid", id);
        params.put("isQuick", isQuick);
        return getJsonObject(project, urltype, params);
    }

    public JSONObject startTask(String userId, String orderNo, String taskid, Integer project, String deviceNumber) {
        String urltype = "starttask";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("orderNo", orderNo);
        params.put("taskid", taskid);
        params.put("deviceNumber", deviceNumber);
        return getJsonObject(project, urltype, params);
    }

    public JSONObject endTask(String userId, String orderNo, String taskid, String pass, String remark, Integer project) {
        String urltype = "endtask";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("orderNo", orderNo);
        params.put("taskid", taskid);
        params.put("pass", pass);
        params.put("remark", remark);
        return getJsonObject(project, urltype, params);
    }

    public JSONObject startCar(String userId, String id, Integer project, String isQuick, String deviceNumber) {
        String urltype = "startcar";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("wid", id);
        params.put("isQuick", isQuick);
        params.put("deviceNumber", deviceNumber);
        return getJsonObject(project, urltype, params);
    }

    public JSONObject endCar(String userId, String id, Integer project, String isQuick) {
        String urltype = "endcar";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("wid", id);
        params.put("isQuick", isQuick);
        return getJsonObject(project, urltype, params);
    }

    public JSONObject listContact(String tianyuanAccount, int project, String deviceNum) {
        String urltype = "contact";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", tianyuanAccount);
        params.put("deviceNumber", deviceNum);
        return getJsonObject(project, urltype, params);
    }
}
