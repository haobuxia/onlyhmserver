package com.tianyi.helmet.server.service.scene;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.scene.svc.Weather;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.HttpUtils;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 天气服务
 * <p>
 * Created by liuhanc on 2018/6/30.
 */
@Service
public class WeatherService {
    private Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private ConfigService configService;


    //https://api.seniverse.com/v3/weather/now.json?key=gbsoda3ogispc5pr&location=beijing&language=zh-Hans&unit=c
    public Weather getWeather(double lat, double lng) {
        //经纬度 例如：location=39.93:116.40（格式是 纬度:经度，英文冒号分隔）
        String url = configService.getWeatherBaseUrl() + "weather/now.json?key=" + configService.getWeatherSecretKey() + "&location=" + lat + ":" + lng + "&language=zh-Hans&unit=c";
        String respJson = null;
        try {
            respJson = HttpUtils.executeHttp(new HttpGet(url), true);
            /**
             * {"results":[{"location":{"id":"WX4FBXXFKE4F","name":"北京","country":"CN","path":"北京,北京,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"},"now":{"text":"晴","code":"1","temperature":"24"},"last_update":"2018-06-30T22:20:00+08:00"}]}
             */
//            System.out.println("天气接口反馈=" + respJson);
        } catch (Exception e) {
            logger.error("查询实况天气异常.lat=" + lat + ",lng=" + lng, e);
            return null;
        }

        JSONObject jo = JSON.parseObject(respJson);
        JSONArray results = jo.getJSONArray("results");
        if (results.size() > 0) {
            JSONObject result = results.getJSONObject(0);
            JSONObject now = result.getJSONObject("now");
            if (now != null) {
                Weather weather = new Weather();
                weather.setText(now.getString("text"));
                weather.setCode(now.getString("code"));
                weather.setTemperature(now.getString("temperature"));
                return weather;
            }
        }
        return null;
    }
}
