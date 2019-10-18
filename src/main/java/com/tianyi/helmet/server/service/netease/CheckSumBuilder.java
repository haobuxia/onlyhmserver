package com.tianyi.helmet.server.service.netease;

import com.tianyi.helmet.server.util.EncoderUtil;

/**
 * 请求数据签名校验
 *
 */
public class CheckSumBuilder {
    // 计算并获取CheckSum
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return EncoderUtil.sha1(appSecret + nonce + curTime);
    }

    // 计算并获取md5值
    public static String getMD5(String requestBody) {
        return EncoderUtil.md5(requestBody);
    }

}
