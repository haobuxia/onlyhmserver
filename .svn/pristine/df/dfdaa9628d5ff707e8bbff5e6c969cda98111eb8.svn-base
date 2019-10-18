package com.tianyi.helmet.server.service.baidu;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * 百度图像识别服务
 * <p>
 * 分类识别，模型训练：http://ai.baidu.com/customize/app/model/
 * <p>
 * Created by liuhanc on 2017/12/25.
 */
@Service
public class ImageClassifyService {
    private AipImageClassify client;
    String appId = "10580405";
    String apiKey = "4zErD8zk8WPMT62VKH9Y4GG3";
    String secretKey = "Y31DY1822Z72G9cXqG1Zhg8h38xkEWQk";

    @PostConstruct
    public void init() {
        client = new AipImageClassify(appId, apiKey, secretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
    }

    public void detectCar(byte[] bytes) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "3");
        JSONObject res = client.carDetect(bytes, options);
        System.out.println("车辆识别结果：" + res.toString(2));
    }

    public void detectLogo(byte[] bytes) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("custom_lib", "true");
        JSONObject res = client.logoDeleteByImage(bytes, options);
        System.out.println("logo识别结果：" + res.toString(2));
    }

}
