package com.tianyi.helmet.server.controller.helmetinterface;

/**
 * 支付宝收付款
 * Created by liuhanc on 2018/2/24.
 */

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.demo.trade.model.TradeStatus;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.interceptor.TianYuanUserHolder;
import com.tianyi.helmet.server.service.alipay.AlipayService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 支付宝支付接口
 */
@Controller
@RequestMapping("alipay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    private Logger logger = LoggerFactory.getLogger(AlipayController.class);

    //payType 支付类型.根据收款情况不同填写。"partSellOrder":零配件销售订单,"partBuyOrder":零配件采购订单
    //description 表示天远单号
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo pay(@RequestParam String authCode, @RequestParam float amount, @RequestParam String payType, @RequestParam String subject, @RequestParam String description) {
        String helmetImei = HelmetImeiHolder.get();
        String tyOprtId = TianYuanUserHolder.getTianYuanOprtId();

        //演示需要，将金额设定为0.01元
        amount = 0.01f;
        AlipayF2FPayResult result = null;

        try {
            result = alipayService.tradePay(authCode, String.valueOf(amount), payType, subject, description, tyOprtId, helmetImei);
        } catch (Exception e) {
            return ResponseVo.fail(e.getMessage());
        }

        TradeStatus status = result.getTradeStatus();
        AlipayTradePayResponse response = result.getResponse();
        switch (status) {
            case SUCCESS: {
                return ResponseVo.success(response.getOutTradeNo());
            }
            case FAILED:
                if (response == null)
                    return ResponseVo.fail("支付宝支付失败.");
                return ResponseVo.fail(response.getSubMsg());
            case UNKNOWN: {
                if (response == null)
                    return ResponseVo.fail("支付状态未知.");
                return ResponseVo.fail("支付状态未知." + response.getSubMsg());
            }
            default:
                return ResponseVo.fail("不支持的交易状态，交易返回异常");
        }
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo query(@RequestParam String outTradeNo) {
        try {
            AlipayF2FQueryResult result = alipayService.queryTradeResult(outTradeNo);
            TradeStatus status = result.getTradeStatus();
            switch (status) {
                case SUCCESS: {
                    return ResponseVo.success(result.getResponse());
                }
                case FAILED:
                    return ResponseVo.fail("查询收款信息失败", result.getResponse());
                default:
                    return ResponseVo.fail("查询收款信息异常", result.getResponse());
            }
        } catch (Exception e) {
            return ResponseVo.fail(e.getMessage());
        }
    }

//    @RequestMapping(value = "refund", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseVo refund(@RequestParam String outTradeNo, @RequestParam float amount, @RequestParam String reason) {
//        String clientId = HelmetImeiHolder.get();
//        String token = TianYuanAuthorizationHolder.get();
//        DoubleVo<Long, String> vo = tianYuanService.getTianYuanUser(token);
//        Long operatorId = vo.getKey();
//
//        try {
//            AlipayF2FRefundResult result = alipayService.tradeRefund(outTradeNo, outTradeNo, String.valueOf(amount), reason, String.valueOf(operatorId), clientId);
//            TradeStatus status = result.getTradeStatus();
//            switch (status) {
//                case SUCCESS: {
//                    return ResponseVo.success(result.getResponse());
//                }
//                case FAILED:
//                    return ResponseVo.fail("退款失败", result.getResponse());
//                default:
//                    return ResponseVo.fail("退款异常", result.getResponse());
//            }
//        } catch (Exception e) {
//            return ResponseVo.fail(e.getMessage());
//        }
//    }

}
