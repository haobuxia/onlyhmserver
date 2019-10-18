package com.tianyi.helmet.server.service.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.EncoderUtil;
import com.tianyi.helmet.server.util.HttpUtils;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 百度地图服务
 * <p>
 * Created by liuhanc on 2018/6/30.
 */
@Service
public class BaiduMapService {
    private Logger logger = LoggerFactory.getLogger(BaiduSpeechService.class);
    @Autowired
    private ConfigService configService;

    /**
     * 某个地址对应的经纬度。返回格式为:lat,lng
     * @param address
     * @return
     */
    public String addressToLatLng(String address) {
        try {
            return geocoder("address", address, jo -> {
                //{"location":{"lng":116.40384918664363,"lat":39.915446357113889},"precise":1,"confidence":70,"level":"UNKNOWN"}
                JSONObject location = jo.getJSONObject("location");
                double lng = location.getDouble("lng");
                double lat = location.getDouble("lat");
                return lat + "," + lng;
            });
        } catch (Exception e) {
            logger.error("根据地址查询经纬度异常." + address, e);
            return "地址解析失败";
        }
    }

    public String latLngToAddress(double lat, double lng) {
        try {
            return geocoder("location", lat + "," + lng, jo -> {
                //{"location":{"lng":116.30789999999993,"lat":40.05700006489279},"formatted_address":"北京市海淀区上地十街10号","business":"西二旗,龙泽,回龙观","addressComponent":{"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"北京市","city":"北京市","city_level":2,"district":"海淀区","town":"","adcode":"110108","street":"上地十街","street_number":"10号","direction":"附近","distance":"16"},"pois":[],"roads":[],"poiRegions":[{"direction_desc":"内","name":"百度大厦","tag":"房地产;写字楼","uid":"435d7aea036e54355abbbcc8"}],"sematic_description":"百度大厦内","cityCode":131}
                return jo.getString("formatted_address");
            });
        } catch (Exception e) {
            logger.error("根据经纬度查询地址异常." + lat + "," + lng, e);
            return "地址解析失败";
        }
    }


    private String geocoder(String key, String val, Function<JSONObject, String> function) throws IOException {
        String ak = configService.getBaiduMapAk();
        String sk = configService.getBaiduMapSk();
        String baseUrl = configService.getBaiduMapBaseUrl();

        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put(key, val);
        paramsMap.put("coordtype","wgs84ll");
//        paramsMap.put("extensions_road","true");
//        paramsMap.put("extensions_town","true");
        paramsMap.put("output", "json");//={"status":0,"result":{"location":{"lng":116.30788068028267,"lat":40.05705856845244},"precise":1,"confidence":80,"level":"商务大厦"}}
        paramsMap.put("ak", ak);
        String uri = configService.getBaiduMapGeocoder();
        if (!uri.endsWith("?")) {
            uri += "?";
        }

        // 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；
        // post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。
        // 所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。
        // 以get请求为例：http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
        // 对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak

        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<String, String> pair : paramsMap.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        String paramsStr = queryString.toString();

        String requestString = uri + paramsStr;
        String sn = EncoderUtil.md5(URLEncoder.encode(requestString + sk, "UTF-8"));
        String fullUrl = baseUrl + requestString + "&sn=" + sn;

//        System.out.println("完整请求url=" + fullUrl);
        String jsonString = HttpUtils.executeHttp(new HttpGet(fullUrl), true);
//        System.out.println("反馈=" + jsonString);
        JSONObject jo = JSON.parseObject(jsonString);
        /**
         * 根据经纬度反查地址名称：
         * {"status":0,"result":{"location":{"lng":116.30789999999993,"lat":40.05700006489279},"formatted_address":"北京市海淀区上地十街10号","business":"西二旗,龙泽,回龙观","addressComponent":{"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"北京市","city":"北京市","city_level":2,"district":"海淀区","town":"","adcode":"110108","street":"上地十街","street_number":"10号","direction":"附近","distance":"16"},"pois":[],"roads":[],"poiRegions":[{"direction_desc":"内","name":"百度大厦","tag":"房地产;写字楼","uid":"435d7aea036e54355abbbcc8"}],"sematic_description":"百度大厦内","cityCode":131}}
         *  根据地址名称查询经纬度：
         * {"status":0,"result":{"location":{"lng":116.40384918664363,"lat":39.915446357113889},"precise":1,"confidence":70,"level":"UNKNOWN"}}
         */
        Integer status = jo.getInteger("status");
        if (status != null && status.intValue() == 0) {
            JSONObject result = jo.getJSONObject("result");
            return function.apply(result);
        }
        return "查询失败";
    }

}
