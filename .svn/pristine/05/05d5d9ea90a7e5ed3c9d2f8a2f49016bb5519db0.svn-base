package com.tianyi.helmet.server.service.tianyuan;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.interceptor.TianyiUserHolder;
import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.service.client.HelmetUniversalService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * //TODO 说明
 *
 * @author zhouwei
 * 2019/1/15 09:35
 * @version 0.1
 **/
@Service
public class TianYuanIntesrvApiHelper {

    @Autowired
    private ConfigService configService;
    @Autowired
    private TianYuanApiBasicHelper basicHelper;
    @Autowired
    private HelmetUniversalService helmetUniversalService;

    private static final String API_URL_PREFIX = "/datasrv/request/HM";

    public JSONObject getJson(String apiUri, JSONObject reqJson) {

        apiUri = Commons.concatUrl(API_URL_PREFIX, apiUri);
        String url = concatIntesrvUrl(apiUri);
        JSONObject retJson = basicHelper.postJson(url, reqJson.toJSONString(),
                configService.getTianYuanIntesrvJwt(), "json");
//        JSONObject retJson = basicHelper.postJson(url, reqJson.toJSONString(),null, "json");
        return retJson;
    }

    public JSONObject getJson(String preUrl, String apiUri, JSONObject reqJson) {
        apiUri = Commons.concatUrl(preUrl, apiUri);
        String url = concatIntesrvUrl(apiUri);
        JSONObject retJson = basicHelper.postJson(url, reqJson.toJSONString(),
                configService.getTianYuanIntesrvJwt(), "json");
//        JSONObject retJson = basicHelper.postJson(url, reqJson.toJSONString(),null, "json");
        return retJson;
    }

    public ResponseVo<Object> call(String preUrl, String apiUri, JSONObject reqJson) {
        JSONObject respJson = getJson(preUrl, apiUri, reqJson);
        if ("true".equals(String.valueOf(respJson.get("success")).toLowerCase())) {
            return ResponseVo.success(respJson.get("data"));
        } else {
            return ResponseVo.fail((String) respJson.get("msg"));
        }
    }

    public ResponseVo<Object> call(String apiUri, JSONObject reqJson, String retKey) {
        JSONObject respJson = getJson(apiUri, reqJson);
        if (isEmpty(retKey)) {
            retKey = "data";
        }
        if ("true".equals(String.valueOf(respJson.get("success")).toLowerCase())) {
            return ResponseVo.success(respJson.get("data"));
        } else {
            return ResponseVo.fail((String) respJson.get("msg"));
        }
    }

    public ResponseVo<Object> call(String apiUri, JSONObject reqJson){
        return call(apiUri, reqJson, null);
    }

    public String concatIntesrvUrl(String apiUri){
        String projectName = getProject();
        if(projectName.contains("小松")) {
            return Commons.concatUrl(configService.getTianYuanIntesrvProdUrl(), apiUri);
        }else if(projectName.contains("邢台")){
            return Commons.concatUrl(configService.getTianYuanIntesrvProdUrl1(), apiUri);
        }else {
            //zhouwei  20190905 如果需要调用智能服务，默认小松服务
            return Commons.concatUrl(configService.getTianYuanIntesrvProdUrl(), apiUri);
        }
//        return null;
    }

    public String concatIntesrvNetUrl(String apiUri){
        String projectName = getProject();
        if(projectName.contains("小松")) {
            return Commons.concatUrl(configService.getTianYuanIntesrvProdUrl(), apiUri);
        }else if(projectName.contains("邢台")){
            return Commons.concatUrl(configService.getTianYuanIntesrvProdNetUrl1(), apiUri);
        }else {
            //zhouwei  20190905 如果需要调用智能服务，默认小松服务
            return Commons.concatUrl(configService.getTianYuanIntesrvProdUrl(), apiUri);
        }
//        return null;
    }

    /**
     * 根据当前用户获取项目信息
     * zhouwei
     * @return 如果没有项目返回空字符
     */
    private String getProject() {
        HelmetUniversalInfo info = helmetUniversalService.selectByUserId(TianyiUserHolder.get().getId());
        if (info != null && !isEmpty(info.getProject())) {
            return info.getProject();
        } else {
            return "";
        }
    }
}