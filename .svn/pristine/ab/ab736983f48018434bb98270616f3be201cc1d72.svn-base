package com.tianyi.helmet.server.service.alipay;

import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.TradeStatus;
import com.alipay.demo.trade.model.builder.AlipayTradeCancelRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.tianyi.helmet.server.service.support.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 支付宝接口服务
 *
 * Created by liuhanc on 2018/2/24.
 */
@Service
public class AlipayService {

    private Logger logger = LoggerFactory.getLogger(AlipayService.class);
    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    private static AlipayTradeWithHBServiceImpl tradeWithHBService;

    @Autowired
    private ConfigService configService;
    @Autowired
    private AliTradePayService aliTradePayService;

    @PostConstruct
    private void init() {
        String profile = configService.getSystemEnv();
        String propertyFile = profile + "/alipay.properties";
        InputStream is = AlipayService.class.getClassLoader().getResourceAsStream(propertyFile);
        if (is == null) {
            logger.error("配置文件读取失败.请确定文件是否存在:" + propertyFile);
            logger.error("配置文件读取失败.请确定文件是否存在:" + propertyFile);
            logger.error("配置文件读取失败.请确定文件是否存在:" + propertyFile);
            return;
        }
        Configs.init(is);

//         支付宝当面付2.0服务（集成了交易保障接口逻辑）
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();
    }

    /**
     * 当面付
     * https://docs.open.alipay.com/api_1/alipay.trade.pay
     *
     * @param authCode    客户条形码的值
     * @param totalAmount 订单总金额
     * @param subject     订单标题简要文字说明
     * @param description 订单详情描述文字
     * @param tyOprtId    商户操作员编号
     * @param helmetImei  商户机具终端编号
     */
    public AlipayF2FPayResult tradePay(String authCode, String totalAmount, String payType, String subject, String description, String tyOprtId, String helmetImei) {
        String outTradeNo = "HELMET_PAY_" + helmetImei + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());//生成1个订单号,在当前app下唯一
//        ExtendParams extendParams = new ExtendParams();//业务扩展参数
//        extendParams.setSysServiceProviderId(providerId);//系统商编号 该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID 貌似是第三方提供商用得上

        // 支付超时，线下扫码交易定义为5分钟.条码支付应该用不上
//        String timeoutExpress = "5d";

        // 创建条码支付请求builder，设置请求参数
        AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
                .setOutTradeNo(outTradeNo).setAuthCode(authCode).setSubject(subject)
                .setTotalAmount(totalAmount).setUndiscountableAmount("0.00")
//                .setGoodsDetailList(goodsDetailList) // 商品列表
//                .setExtendParams(extendParams)
                .setBody(subject+"@"+payType+"@"+description)
//                .setStoreId(storeId) //商户门店编号
//                .setSellerId(sellerId) //如果该值为空，则默认为商户签约账号对应的支付宝用户ID
// .               setBuyerId(??) //买家的支付宝用户id，如果为空，会从传入了码值信息中获取买家ID
                .setOperatorId(tyOprtId)//商户操作员编号
                .setTerminalId(helmetImei)//商户机具终端编号
//                .setTimeoutExpress(timeoutExpress)
                ;
        // 调用tradePay方法获取当面付应答
        AlipayF2FPayResult result = tradeWithHBService.tradePay(builder);
        TradeStatus status = result.getTradeStatus();
        AlipayTradePayResponse response = result.getResponse();

        try{
            aliTradePayService.saveAlipayTradePayResponse(response, status.toString(), helmetImei, tyOprtId, payType, description, subject);
        }catch(Exception e){
            logger.error("存储支付结果信息异常",e);
        }

        return result;
    }

    /**
     * 退款
     * https://docs.open.alipay.com/api_1/alipay.trade.refund/
     *
     * @param outTradeNo   退款对应的订单号
     * @param outRequestNo 退款请求标志。如果1个订单号多次退款，则这多次退款的请求标志应相同
     * @param refundAmount 退款金额
     * @param refundReason 退款原因
     * @param operatorId   商户操作员编号
     * @param terminalId   商户机具终端编号
     * @return
     */
    public AlipayF2FRefundResult tradeRefund(String outTradeNo, String outRequestNo, String refundAmount, String refundReason, String operatorId, String terminalId) {
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
                .setOutTradeNo(outTradeNo).setRefundAmount(refundAmount).setRefundReason(refundReason)
//                .setTradeNo(??) //不针对某个订单的退款则传入此信息
                .setOutRequestNo(outRequestNo)
                .setOperatorId(operatorId)
                .setTerminalId(terminalId)
//                .setStoreId(storeId)
                ;
        AlipayF2FRefundResult result = tradeWithHBService.tradeRefund(builder);
        return result;
    }

    /**
     * 撤销交易。用于在交易失败和超时时使用。
     * <p>
     * 对比交易关闭，关闭主要用于交易长时间未付款时使用。
     *
     * @param outTradeNo
     * @return
     */
    public AlipayTradeCancelResponse tradeCancel(String outTradeNo) {
        AlipayTradeCancelRequestBuilder builder = new AlipayTradeCancelRequestBuilder()
                .setOutTradeNo(outTradeNo);
        return tradeWithHBService.tradeCancel(builder);
    }

    /**
     * 查询交易结果
     *
     * @param outTradeNo
     * @return
     */
    public AlipayF2FQueryResult queryTradeResult(String outTradeNo) {
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
//                .setTradeNo()
                .setOutTradeNo(outTradeNo);
        return tradeWithHBService.queryTradeResult(builder);
    }

    /**
     * 查询退款结果
     *
     * @param outTradeNo
     * @return
     */
    public AlipayF2FQueryResult queryRefundResult(String outTradeNo) {
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
//                .setTradeNo()
                .setOutTradeNo(outTradeNo);
        return tradeWithHBService.queryTradeResult(builder);
    }

}
