package com.tianyi.helmet.server.service.tianyuan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.util.HttpUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 天远接口基础帮助类
 * 
 * Created by liuhanc on 2018/1/23.
 */
@Service
public class TianYuanApiBasicHelper {
    @Autowired
    private ConfigService configService;

    private Logger logger = LoggerFactory.getLogger(TianYuanApiBasicHelper.class);

    /**
     * 根据账号密码获得token
     *
     * @param userName
     * @param password
     * @return
     */
    public TianYuanUser userPassLogin(String userName, String password) {
        boolean isProd = TianYuanUser.isProdUser(userName);
        String oauthUrl = isProd ? configService.getTianYuanOAuthProdUrl() : configService.getTianYuanOAuthUrl();
        String url = Commons.concatUrl(oauthUrl, "connect/token");
        Map<String, String> map = new HashMap<>(8);
        map.put("client_id", configService.getTianYuanOAuthClientId());
        map.put("client_secret", configService.getTianYuanOAuthClientSecret());
        map.put("scope", configService.getTianYuanOAuthScope());
        map.put("grant_type", "password");
        map.put("loginType", "1");//1=默认，2=手机号登录
        map.put("username", TianYuanUser.getLoginName(userName));
        map.put("password", password);
        // 20180530 张召兵 说：登录时，需要添加一个参数sysID 值为1
        map.put("sysID", "1");

        logger.debug("天远环境用户登录,是否生产环境?=" + isProd + ",请求参数:" + JSON.toJSONString(map)+ ",oauthUrl=" + oauthUrl);

        JSONObject jo = postForm(url, map, null, "json",false);
        if (jo == null) {
            throw new TianYuanException("天远体系用户名密码登录失败");
        }

        TianYuanUser tianYuanUser = toTianYuanUser(jo, userName, password);
        return tianYuanUser;
    }

    /**
     * 刷新token，刷新结果存入参数中
     *
     * @return
     */
    public void refreshAccessToken(TianYuanUser existUser) {
        boolean isProd = existUser.isProdUser();
        String oauthUrl = isProd ? configService.getTianYuanOAuthProdUrl() : configService.getTianYuanOAuthUrl();
        String url = Commons.concatUrl(oauthUrl, "connect/token");
        Map<String, String> map = new HashMap<>(7);
        map.put("client_id", configService.getTianYuanOAuthClientId());
        map.put("client_secret", configService.getTianYuanOAuthClientSecret());

        map.put("grant_type", "refresh_token");
        map.put("refresh_token", existUser.getRefreshToken());

        logger.debug("天远环境用户刷新token,是否生产环境?=" + isProd + ",请求参数:" + JSON.toJSONString(map)+ ",oauthUrl=" + oauthUrl);

        JSONObject jo = postForm(url, map, null, "json",false);
        if (jo == null) {
            throw new TianYuanException("刷新token失败");
        }

        TianYuanUser newTokenUser = toTianYuanUser(jo, null, null);

        existUser.setAccessToken(newTokenUser.getAccessToken());
        existUser.setExpiresIn(newTokenUser.getExpiresIn());
        existUser.setTokenType(newTokenUser.getTokenType());
        existUser.setRefreshToken(newTokenUser.getRefreshToken());
        existUser.setRefreshTime(LocalDateTime.now());
    }

    /**
     * 获得用户信息
     *
     * @param fullToken
     * @return
     */
    public JSONObject getUserInfo(boolean isProd, String fullToken) {
        String oauthUrl = isProd ? configService.getTianYuanOAuthProdUrl() : configService.getTianYuanOAuthUrl();
        String url = Commons.concatUrl(oauthUrl, "v1/UserInfo");

        logger.debug("天远环境用户获取用户信息,是否生产环境?=" + isProd + ",oauthUrl=" + oauthUrl);

        JSONObject jo = getForm(url, fullToken, "json");//[{"Acnt_ID":1000002122.0,"Acnt_Name":"tianyikeji","Oprt_ID":1000002643.0,"Oprt_Name":"tianyikeji","GroupId":"","IsFlagship":false,"ClientId":"rentalweb","Oprt_Mobile":"17091087800","DictOT_ID":1,"DictOT_Name":"管理员","ClientScope":["openid","profile","api","offline_access"],"GrantType":"password","SysId":0.0,"SysIds":[0.0,1.0],"Dept_Info":[{"Dept_ID":10001.0,"Dept_Name":"系统管理","OrgI_ID":1.0,"OrgI_Name":"默认架构"}],"Acnt_Dept_Info":[],"Acnt_Type":1}]
        if (jo == null) {
            throw new TianYuanException("获取当前天远用户信息失败.反馈为空");
        }
        return jo;
    }

    private TianYuanUser toTianYuanUser(JSONObject jo, String username, String password) {
        TianYuanUser tianYuanUser = new TianYuanUser();
        tianYuanUser.setAccessToken(jo.getString("access_token"));
        tianYuanUser.setExpiresIn(jo.getLong("expires_in"));
        tianYuanUser.setTokenType(jo.getString("token_type"));//Bearer
        tianYuanUser.setRefreshToken(jo.getString("refresh_token"));
        tianYuanUser.setLoginType("1");
        tianYuanUser.setUsername(username);
        tianYuanUser.setPassword(password);

        return tianYuanUser;
    }

    /**
     * 执行某个url请求
     *
     * @param url
     * @return
     */
    public JSONObject getForm(String url, String authorization, String respFormat) {
        HttpGet get = new HttpGet(url);
        // 设置请求的header
        if (!StringUtils.isEmpty(authorization)) {
            get.addHeader("Authorization", authorization);
        }

        String respTxt = null;
        try {
            respTxt = HttpUtils.executeHttp(get, true);
            logger.info("tianyuan get response:[" + respTxt + "].url=" + url);
        } catch (Exception e) {
            throw new TianYuanException("调用天远接口失败.url=" + url, e);
        }
        return parseResp(respTxt, respFormat, url);
    }

    /**
     * 执行某个url请求
     *
     * @param url
     * @param params
     * @return
     */
    public JSONObject postForm(String url, Map<String, ? extends Object> params, String authorization, String respFormat,boolean checkStatus) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        if (!StringUtils.isEmpty(authorization)) {
            httpPost.addHeader("Authorization", authorization);
        }

        // 设置请求的参数
        HttpUtils.createUrlEncodedFormEntity(params, httpPost);

        String respTxt = null;
        try {
            respTxt = HttpUtils.executeHttp(httpPost, checkStatus);
            logger.debug("天远提交form请求.url=" + url + ",反馈=" + respTxt);
        } catch (Exception e) {
            throw new TianYuanException("调用天远接口失败.url=" + url, e);
        }

        return parseResp(respTxt, respFormat, url);
    }

    /**
     * 执行某个url请求上传文件
     *
     * @param url
     * @param params
     * @return
     */
    public JSONObject postUploadForm(String url, Map<String, ? extends Object> params, String fileKey, File file, String authorization, String respFormat) {

        Map<String, String> headers = new HashMap<>();
        // 设置请求的header
        if (!StringUtils.isEmpty(authorization)) {
            headers.put("Authorization", authorization);
        }

        String respTxt = null;
        try {
            respTxt = HttpUtils.executeFileUpload(url, params, fileKey, file, headers);
            logger.debug("天远提交file form请求.url=" + url + ",反馈=" + respTxt);
        } catch (Exception e) {
            throw new TianYuanException("调用天远文件上传接口失败.url=" + url, e);
        }

        return parseResp(respTxt, respFormat, url);
    }

    /**
     * 执行某个url请求
     *
     * @param url
     * @param requestJson
     * @return
     */
    public JSONObject postJson(String url, String requestJson, String authorization, String respFormat) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        if (!StringUtils.isEmpty(authorization)) {
            httpPost.addHeader("Authorization", authorization);
        }

        // 设置请求的参数
        HttpUtils.createJsonEntity(requestJson, httpPost);

        String respTxt = null;
        try {
            respTxt = HttpUtils.executeHttp(httpPost, false);
            logger.debug("天远提交json请求.url=" + url + ",反馈=" + respTxt);
        } catch (Exception e) {
            throw new TianYuanException("调用天远接口失败." + e.getMessage(), e);
        } finally {
        }

        return parseResp(respTxt, respFormat, url);
    }

    protected JSONObject parseResp(String respTxt, String respFormat, String url) {
        if (StringUtils.isEmpty(respTxt)) {
            return null;
        }

        if ("json".equals(respFormat)) {
            JSONObject jo = null;
            try {
                jo = JSON.parseObject(respTxt);
            } catch (Exception e) {
                logger.error("解析天远接口反馈的json数据异常", e);
                return null;
            }

            String error = jo.getString("error");
            if (!StringUtils.isEmpty(error)) {
                throw new TianYuanException("调用天远接口失败.url=" + url + ".error=" + error);
            }
            return jo;
        } else if ("xml".equals(respFormat)) {
            //
            String startKeyString = "<string xmlns=\"http://tempuri.org/\">";
            int pos1 = respTxt.indexOf(startKeyString);
            int pos2 = respTxt.lastIndexOf("</string>");
            if (pos1 != -1 && pos2 != -1) {
                String jsonTxt = respTxt.substring(pos1 + startKeyString.length(), pos2).trim();
                JSONObject jo = null;
                try {
                    jo = JSON.parseObject(jsonTxt);
                } catch (Exception e) {
                    logger.error("解析天远接口反馈xml内容中的json时异常", e);
                    return null;
                }
                return jo;
            } else {
                throw new TianYuanException("调用天远接口失败.url=" + url + ".反馈字符串格式无法解析");
            }
        }
        return null;
    }

}
