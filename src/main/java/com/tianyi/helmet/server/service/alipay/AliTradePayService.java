package com.tianyi.helmet.server.service.alipay;

import com.alipay.api.response.AlipayTradePayResponse;
import com.tianyi.helmet.server.dao.pay.AliTradePayDao;
import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.entity.pay.AliTradePay;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import com.tianyi.helmet.server.util.RelationUtils;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 支付宝支付服务
 *
 * Created by liuhanc on 2018/4/20.
 */
@Service
public class AliTradePayService {
    @Autowired
    private AliTradePayDao aliTradePayDao;
    @Autowired
    private TianYuanUserService tianYuanUserService;

    public void insert(AliTradePay msg) {
        aliTradePayDao.insert(msg);
    }

    public AliTradePay selectById(int id) {
        return aliTradePayDao.selectById(id);
    }

    public AliTradePay selectByOutTradeNo(String outTradeNo) {
        return aliTradePayDao.selectByOutTradeNo(outTradeNo);
    }

    public PageListVo<AliTradePay> queryPageList(String oprtId, int page, Date date1, Date date2) {
        Map<String, Object> params = PageListVo.createParamMap(page);
        params.put("tyOprtId", oprtId);
        params.put("gmtPayment1", date1);
        params.put("gmtPayment2", date2);
        List<AliTradePay> list = aliTradePayDao.listBy(params);
        if (!CollectionUtils.isEmpty(list)) {
            fullfilTyOprtName(list);
        }
        int total = aliTradePayDao.countBy(params);

        PageListVo<AliTradePay> vo = new PageListVo(page);
        vo.setList(list);
        vo.setTotal(total);
        return vo;
    }

    public int deleteById(int id) {
        return aliTradePayDao.deleteById(id);
    }

    /**
     * 填充支付记录里的天远用户名
     *
     * @param helmetList
     */
    public void fullfilTyOprtName(List<AliTradePay> helmetList) {
        RelationUtils.fullfilListRelateProperty(helmetList, AliTradePay::getTyOprtId, tianYuanUserService::selectByOprtIdList, TianYuanUser::getOprtId, TianYuanUser::getOprtName, AliTradePay::setTyOprtName);
    }

    //保存
    public AliTradePay saveAlipayTradePayResponse(AlipayTradePayResponse resp, String status, String helmetImei, String tyOprtId, String tyTradeType, String tyTradeNo, String subject) {
        AliTradePay pay = new AliTradePay();
        pay.setStatus(status);
        pay.setSubject(subject);
        pay.setHelmetImei(helmetImei);
        pay.setTyOprtId(tyOprtId);
        pay.setTyTradeType(tyTradeType);
        pay.setTyTradeNo(tyTradeNo);
        pay.setOutTradeNo(resp.getOutTradeNo());
        pay.setTradeNo(resp.getTradeNo());
        pay.setBuyerLogonId(resp.getBuyerLogonId());
        pay.setBuyerUserId(resp.getBuyerUserId());
        String totalAmount = resp.getTotalAmount();
        pay.setTotalAmount(totalAmount == null ? 0.0f : Float.parseFloat(resp.getTotalAmount()));
        pay.setReceiptAmount(Float.parseFloat(resp.getReceiptAmount()));
        pay.setBuyerPayAmount(Float.parseFloat(resp.getBuyerPayAmount()));
        pay.setInvoiceAmount(Float.parseFloat(resp.getInvoiceAmount()));
        pay.setGmtPayment(resp.getGmtPayment()
        );
        this.insert(pay);
        return pay;
    }
}
