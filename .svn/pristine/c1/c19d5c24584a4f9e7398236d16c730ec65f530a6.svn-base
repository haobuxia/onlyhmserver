package com.tianyi.helmet.server.service.netease;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.HttpUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * 网易api
 * Created by liuhanc on 2017/10/15.
 */
@Component
public class NeteaseApiHelper {
    private Logger logger = LoggerFactory.getLogger(NeteaseApiHelper.class);
    @Autowired
    private ConfigService configService;

    /**
     * 执行某个url请求
     * @param url
     * @param params
     * @return
     */
    public JSONObject post(String url, Map<String,String> params,boolean paramsFmtToJson){
        HttpPost httpPost = new HttpPost(url);
        String nonce =  "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(configService.getNeteaseAppSecret(), nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", configService.getNeteaseAppKey());
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);

        // 设置请求的参数
        if(paramsFmtToJson){
            HttpUtils.createJsonEntity(params,httpPost);
        }else{
            HttpUtils.createUrlEncodedFormEntity(params,httpPost);
        }

        String json = null;
        try{
            // 打印执行结果
            json = HttpUtils.executeHttp(httpPost,false);
            logger.info("netease post response:"+json);
        }catch(Exception e){
            logger.error("netease post url exception",e);
        }

        if(!StringUtils.isEmpty(json)){
            JSONObject jo = JSON.parseObject(json);
            int code = jo.getInteger("code");
            if(code != 200){
                logger.error("netease post failed."+json);
            }
            return jo;
        }
        return null;
    }

    public void get(String url){
        HttpGet httpGet = new HttpGet(url);
        // 执行请求
        try{
            HttpUtils.executeHttp(httpGet,false);
        }catch(Exception e){
            logger.error("netease get failed.",e);
        }
    }
}
