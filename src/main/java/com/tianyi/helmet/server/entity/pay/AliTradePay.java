package com.tianyi.helmet.server.entity.pay;

import com.tianyi.helmet.server.entity.IdEntity;

import java.util.Date;

/**
 * 支付宝买家付款记录
 * <p>
 * Created by liuhanc on 2018/4/20.
 */
public class AliTradePay extends IdEntity {
    private String outTradeNo;//商家单号，唯一
    private String status;//状态
    private String subject;//主题说明
    private String helmetImei;//头盔唯一识别标志
    private String tyOprtId;//天远用户id
    private String tyTradeType;//天远交易类型
    private String tyTradeNo;//天远交易单号
    private String tradeNo;//支付宝单号
    private String buyerLogonId;//买家登录账号
    private String buyerUserId;//买家用户Id
    private float totalAmount;//订单金额
    private float receiptAmount;//实收金额
    private float buyerPayAmount;//买家付款金额
    private float invoiceAmount;//发票金额
    private Date gmtPayment;//支付时间

    private String tyOprtName;//天远用户名,不存储
    private String helmetNeUserName;//头盔网易账号,不存储

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTyOprtName() {
        return tyOprtName;
    }

    public void setTyOprtName(String tyOprtName) {
        this.tyOprtName = tyOprtName;
    }

    public String getHelmetNeUserName() {
        return helmetNeUserName;
    }

    public void setHelmetNeUserName(String helmetNeUserName) {
        this.helmetNeUserName = helmetNeUserName;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getHelmetImei() {
        return helmetImei;
    }

    public void setHelmetImei(String helmetImei) {
        this.helmetImei = helmetImei;
    }

    public String getTyOprtId() {
        return tyOprtId;
    }

    public void setTyOprtId(String tyOprtId) {
        this.tyOprtId = tyOprtId;
    }

    public String getTyTradeType() {
        return tyTradeType;
    }

    public void setTyTradeType(String tyTradeType) {
        this.tyTradeType = tyTradeType;
    }

    public String getTyTradeNo() {
        return tyTradeNo;
    }

    public void setTyTradeNo(String tyTradeNo) {
        this.tyTradeNo = tyTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(float receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public float getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(float buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public float getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(float invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }
}
