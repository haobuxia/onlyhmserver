package com.tianyi.helmet.server.service.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.net.URL;

/**
 * 短信服务
 * Created by liuhanc on 2017/12/8.
 */
@Service
public class ShortMessageService {
    @Autowired
    private ConfigService configService;

    private ShortMessageWebService shortMessageWebService;

    @PostConstruct
    public void initWebService() {
        String address = configService.getMsgUrl();
        URL wsdlUrl = null;
        try {
            wsdlUrl = new URL(address + "?wsdl");
        } catch (Exception e) {
            logger.error("初始化WebService url异常." + address, e);
            return;
        }

        try {
            javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlUrl, new QName(ShortMessageWebService.namespace, ShortMessageWebService.serviceName));
            shortMessageWebService = service.getPort(new QName(ShortMessageWebService.namespace, ShortMessageWebService.portName), ShortMessageWebService.class);
            //不加下面这句会提示连接超时
            ((BindingProvider) shortMessageWebService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private Logger logger = LoggerFactory.getLogger(ShortMessageService.class);

    protected String createRequestJson(String code, String mobile) {
        JSONObject jo = new JSONObject();
        jo.put("UserName", configService.getMsgUser());
        jo.put("UserPwd", configService.getMsgPassword());
        jo.put("SimNo", mobile);
        jo.put("Message", code + "," + configService.getMsgTimeout());
        String postJson = jo.toJSONString();
//        String postJson="{\"UserName\":\""+configService.getMsgUser()+"\",\"UserPwd\":\""+configService.getMsgPassword()+"\",\"SimNo\":\""+mobile+"\",\"Message\":\""+code+","+configService.getMsgTimeout()+"\"}";
//        String postJson="{\"UserName\":\"tykj\",\"UserPwd\":\"tykj20171207\",\"SimNo\":\"17091087800\",\"Message\":\"1234,5\"}";
//        logger.debug("发送短信请求数据："+postJson);
        return postJson;
    }

    protected ResponseVo<String> createResponse(String json) {
        logger.debug("发送短信反馈数据：" + json);
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            /**
             *   常用结果列举：
             1：发送成功
             2：发送失败
             3：账号错误
             4：密码错误
             5：账号失效
             6: 手机号非法
             7：模板不匹配
             8："json对象名称错误"
             9：24小时内短信条数超过上限
             100：服务内部错误
             */
            String respCode = jo.getString("RespCode");
            if ("1".equals(respCode))
                return ResponseVo.success();
            else {
                String respMsg = jo.getString("Message");
                return ResponseVo.fail("code:" + respCode + ",msg:" + respMsg);
            }
        } else {
            return ResponseVo.fail("code:500,msg:短信接口反馈为空");
        }
    }

    /**
     * 发送短信
     *
     * @param code
     * @param mobile
     * @return
     */
    public ResponseVo<String> sendVerifyCode(String code, String mobile) {
        if (configService.getMsgSend() == 1) {
            String strJson = createRequestJson(code, mobile);
            String respJson = shortMessageWebService.SendMessage(strJson);
            return createResponse(respJson);
        } else {
            logger.debug("短信不发送,打印短信码：" + code + ",手机号" + mobile);
            return ResponseVo.success();
        }
    }
}
