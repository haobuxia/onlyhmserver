package com.tianyi.helmet.server.service.kdxf;

import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.EncoderUtil;
import com.tianyi.helmet.server.util.HttpUtils;
import org.apache.commons.fileupload.util.Streams;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 科大讯飞 图片文字识别服务
 * Created by liuhanc on 2018/4/3.
 */
@Service
public class KdxfOcrService {

    private Logger logger = LoggerFactory.getLogger(KdxfOcrService.class);

    private static final String URL_GENERAL = "http://webapi.xfyun.cn/v1/service/v1/ocr/general";

    private static final String URL_HANDWRITING = "http://webapi.xfyun.cn/v1/service/v1/ocr/handwriting";

    //相关参数JSON串经Base64编码后的字符串，见各接口详细说明
    private static final String x_param = EncoderUtil.base64Encode("{\"language\": \"cn|en\",\"location\": \"false\"}".getBytes());

    @Autowired
    private ConfigService configService;

    /**
     * 印刷体识别
     */
    public String general(File image) {
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        try {
            Streams.copy(new FileInputStream(image), byos, false);
        } catch (Exception e) {
            logger.error("读取图片异常", e);
            return null;
        }

        return ocr(byos.toByteArray(), "general");
    }

    /**
     * 印刷体识别
     */
    public String general(byte[] imageBytes) {
        return ocr(imageBytes, "general");
    }

    /**
     * 手写体识别
     */
    public String handwriting(File image) {
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        try {
            Streams.copy(new FileInputStream(image), byos, false);
        } catch (Exception e) {
            logger.error("读取图片异常", e);
            return null;
        }
        return ocr(byos.toByteArray(), "handwriting");
    }

    /**
     * 手写体识别
     */
    public String handwriting(byte[] imageBytes) {
        return ocr(imageBytes, "handwriting");
    }

    protected String ocr(byte[] imageBytes, String ocrType) {
        String url = null;
        if ("general".equalsIgnoreCase(ocrType)) {
            url = URL_GENERAL;
        } else if ("handwriting".equalsIgnoreCase(ocrType)) {
            url = URL_HANDWRITING;
        } else {
            return null;
        }

        HttpPost httpPost = new HttpPost(url);
        setAuthHeader(httpPost);

        //图像数据，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式
        String base64 = EncoderUtil.base64Encode(imageBytes);
        Map<String, Object> params = new HashMap<>(1);
        params.put("image", base64);
        HttpUtils.createUrlEncodedFormEntity(params, httpPost);

        try {
            String resp = HttpUtils.executeHttp(httpPost, true);
            logger.debug("科大讯飞识别图片结果:" + resp);
            return resp;
        } catch (Exception e) {
            logger.error("科大识别图片中印刷文字异常", e);
            return null;
        }
    }

    protected void setAuthHeader(HttpPost httpPost) {
        httpPost.addHeader("X-Appid", configService.getKdxfAppId());//讯飞开放平台注册申请应用的应用ID(appid)
        long now = System.currentTimeMillis() / 1000;
        httpPost.addHeader("X-CurTime", String.valueOf(now));//当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数
        httpPost.addHeader("X-Param", x_param);//相关参数JSON串经Base64编码后的字符串，见各接口详细说明
        httpPost.addHeader("X-CheckSum", calcuteCheckSum(now));//令牌，计算方法：MD5(apiKey + curTime + param)，三个值拼接的字符串，进行MD5哈希计算（32位小写），其中apiKey由讯飞提供，调用方管理。
    }

    protected String calcuteCheckSum(long nowSeconds) {
        //MD5(apiKey+curTime+param);
        //计算方法：MD5(apiKey + curTime + param)，三个值拼接的字符串，进行MD5哈希计算（32位小写），其中apiKey由讯飞提供，调用方管理
        String value = configService.getKdxfSecretKey() + nowSeconds + x_param;
        return EncoderUtil.md5(value);
    }

    public static void main(String[] args) {
        KdxfOcrService ocrService = new KdxfOcrService();
        ConfigService configService = new ConfigService();
        configService.setKdxfAppId("5b231723");
        configService.setKdxfSecretKey("54c4b74486352568cf1d56f3bd4e3f79");
        ocrService.configService = configService;
        File imgFile = new File("C:\\Users\\liuhanc\\Downloads\\铭牌\\上机型下年度\\2a3d99c4cf094bfa9ab69da67cada00e.jpg");
        ocrService.general(imgFile);
//        ocrService.handwriting(imgFile);
    }
}
