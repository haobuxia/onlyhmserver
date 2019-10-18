package com.tianyi.helmet.server.controller.signature;

import com.tianyi.helmet.server.util.EncoderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 签名请求相关，根据HttpRequest生成签名
 *
 * @author yinzt
 * @description
 * @date 2016/12/20
 */
public class SignatureRequest {
    private String appKey;
    private String signature;
    private SortedMap<String, String[]> parameters;

    private Logger logger = LoggerFactory.getLogger(SignatureRequest.class);

    public SignatureRequest() {
        this.parameters = new TreeMap<>();
    }

    public SignatureRequest(HttpServletRequest request) {
        Map<String, String[]> source = request.getParameterMap();
        this.parameters = new TreeMap(source);
        this.appKey = request.getHeader("appKey");
        this.signature = request.getHeader("signature");
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public SortedMap<String, String[]> getParameters() {
        return parameters;
    }

    public void setParameters(SortedMap<String, String> parameters) {
        Map<String, String[]> tempMap = parameters.keySet().stream().collect(
                Collectors.toMap(
                        key -> key,
                        key -> {
                            return new String[]{parameters.get(key)};
                        }));
        this.parameters = new TreeMap<>(tempMap);
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSignature() {
        return signature;
    }

    public String[] calcuteSignature(String appSecret) {
        StringBuilder sBuilder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : this.parameters.entrySet()) {
            String key = entry.getKey();
            String[] mulValue = entry.getValue();
            if (mulValue == null || mulValue.length == 0) {
                continue;
            }
            sBuilder.append(key + "=" + mulValue[0] + "&");
        }
        sBuilder.append("appSecret=" + appSecret);
        String signSrcString = sBuilder.toString();
        String sign = EncoderUtil.sha1(signSrcString);
        logger.debug("请求签名计算,字符串:"+signSrcString+",签名结果:"+sign);
        return new String[]{signSrcString, sign};
    }


    public boolean sign(String appSecret) {
        String[] sign = calcuteSignature(appSecret);
        boolean success = signature.equals(sign[1]);
        if (!success) {
            logger.debug("签名校验不通过.计算得到=" + sign[1] + ",传入=" + signature + ",签名字符串=" + sign[0]);
        }
        return success;
    }
}
