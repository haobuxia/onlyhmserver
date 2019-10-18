package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.controller.signature.SignatureException;
import com.tianyi.helmet.server.controller.signature.SignatureRequest;
import com.tianyi.helmet.server.exception.TianyiException;
import com.tianyi.helmet.server.partner.HttpCodeEnum;
import com.tianyi.helmet.server.partner.Partner;
import com.tianyi.helmet.server.partner.PartnerConfig;
import com.tianyi.helmet.server.util.ReqRespUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 合作伙伴与本系统接口交互时的签名校验拦截器
 */
@Component
public class PartnerSignatureInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    @Autowired
    private PartnerConfig partnerConfig;

    private Logger logger = LoggerFactory.getLogger(PartnerSignatureInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HelmetImeiHolder.remove();
        boolean isLocal = ReqRespUtils.isLocalRequest(request);
        if (!isLocal) {
            signatureCheck(request);
        }

        imeiRecord(request, isLocal);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HelmetImeiHolder.remove();
    }

    /**
     * 只需将Ime值进行记录即可。不需要检查imei的合法性。
     *
     * @param request
     * @param isLocal
     * @throws TianyiException
     */
    public void imeiRecord(HttpServletRequest request, boolean isLocal) throws TianyiException {
        String imei = request.getHeader("imei");//头盔唯一标识
        logger.debug("记录当前请求对应的头盔imei:" + imei + ",uri=" + request.getRequestURI() + ",method=" + request.getMethod());
        HelmetImeiHolder.set(imei);
    }

    public void signatureCheck(HttpServletRequest request) throws SignatureException {
        String appKey = request.getHeader("appKey");
        String signature = request.getHeader("signature");
        if (StringUtils.isEmpty(appKey)) {
            throw new SignatureException(HttpCodeEnum.APPKEY_ERROR, "appKey不能为空");
        }
        if (StringUtils.isEmpty(signature)) {
            throw new SignatureException(HttpCodeEnum.SIGNATURE_ERROR, "签名signature不能为空");
        }

        Partner partner = partnerConfig.getPartner(appKey);
        if (partner == null) {
            throw new SignatureException(HttpCodeEnum.APPKEY_ERROR, "appKey错误");
        }
        SignatureRequest signatureRequest = new SignatureRequest(request);
        if (!signatureRequest.sign(partner.getAppSecret())) {
            throw new SignatureException(HttpCodeEnum.SIGNATURE_ERROR, "签名校验失败");
//        }else{
//            logger.debug("请求签名校验通过."+request.getRequestURI()+",appKey="+appKey+",signature="+signature);
        }

    }
}
