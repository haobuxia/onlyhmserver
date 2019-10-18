package com.tianyi.helmet.server.service.tianyuan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.HttpUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 天远地图api
 * <p>
 * Created by liuhanc on 2018/1/23.
 */
@Service
public class TianYuanMapApiHelper {
    @Autowired
    private ConfigService configService;
    @Autowired
    private JsonRedisTemplate jedisTemplate;

    private Logger logger = LoggerFactory.getLogger(TianYuanMapApiHelper.class);

    public JSONObject getPointInCircle(float lon, float lat, float radius) {
        String center = lon + "," + lat;
        String url = configService.getTianYuanMapApiUrl() + "GetPointInCircle?radius=" + radius + "&key=" + configService.getTianYuanMapApiKey() + "&center=" + center;
        return getJson(url);
    }

    /**
     * 获取地址位置描述
     * @param lon
     * @param lat
     * @param batch 是否批量查询
     * @return
     */
    public JSONObject getLocation(float lon, float lat, boolean batch) {
        String center = lon + "," + lat;
        batch = batch || false;
        String url = configService.getTianYuanMapApiUrl() + "TYReGeoCoder?batch=" + batch + "&location=" + center + "&key=" + configService.getTianYuanMapApiKey() ;
        return getJson(url);
    }


    /**
     * 执行某个url请求
     *
     * @param url
     * @return
     */
    public JSONObject getJson(String url) {
        HttpPost httpGet = new HttpPost(url);
        String json = null;
        try {
            json = HttpUtils.executeHttp(httpGet,false);
//            logger.info("tianyuan mapapi get response:" + json+",url="+url);
        } catch (Exception e) {
            logger.error("tianyuan mapapi exception.url=" + url, e);
            throw new TianYuanException("调用天远地图接口失败.url=" + url, e);
        }

        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = null;
            try{
                jo = JSON.parseObject(json);
            }catch(Exception e){
                throw new TianYuanException("调用天远地图接口失败.url=" + url+",resp="+json);
            }

            /**
             * 0：参数异常
             1：成功
             2：成功，但结果集为0
             3：key无效
             4：异常报错
             */
            String status = jo.getString("status");
            if ("true".equals(status)) {
                return jo;
            }
            throw new TianYuanException("调用天远地图接口失败.status=" + status + ",msg=" + jo.getString("msg") + ",url=" + url);
        }
        return null;
    }

}
