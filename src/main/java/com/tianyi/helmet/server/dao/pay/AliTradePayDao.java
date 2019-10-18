package com.tianyi.helmet.server.dao.pay;

import com.tianyi.helmet.server.entity.pay.AliTradePay;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  支付宝支付记录
 *
 * Created by liuhanc on 2018/4/20.
 */
@Repository
public interface AliTradePayDao {
    void insert(AliTradePay msg);

    AliTradePay selectById(int id);

    AliTradePay selectByOutTradeNo(String outTradeNo);

    List<AliTradePay> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int deleteById(int id);
}
