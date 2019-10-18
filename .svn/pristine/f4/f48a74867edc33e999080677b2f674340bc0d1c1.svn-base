package com.tianyi.helmet.server.controller.interceptor;

import com.iflytek.msp.cpdb.lfasr.util.StringUtil;
import com.tianyi.helmet.server.entity.log.OperaLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 操作日志拦截器
 *
 * Created by wenxinyan on 2018/8/24.
 */
@Component
public class OperaLogInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(OperaLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        OperaLog operaLog = new OperaLog();
        operaLog.setCreateTime(LocalDateTime.now());
        operaLog.setClientId("no");
        operaLog.setUUID("fuwuqi-a");
        operaLog.setDeviceType("FWQ");
        operaLog.setLogType("no");
        operaLog.setLogflowId("no");
        operaLog.setOrderNo(null);
        operaLog.setLogDate(LocalDate.now());
        operaLog.setLogTime(LocalDateTime.now());
        operaLog.setLogContent("初始内容");
        operaLog.setLogNature(null);

        String clientId = request.getParameter("neUserName");
        if(StringUtil.isEmpty(clientId)) {
            OperaLogHolder.set(operaLog);
            logger.error("存入操作日志前，没有找到clientId参数.uri=" + request.getRequestURI());
            return true;
        }
        operaLog.setClientId(clientId);

        String logType = request.getParameter("logType");
        if(StringUtil.isEmpty(logType)) {
            OperaLogHolder.set(operaLog);
            logger.error("存入操作日志前，没有找到logType参数.uri=" + request.getRequestURI());
            return true;
        }
        operaLog.setLogType(logType);

        String logflowId = request.getParameter("logflowId");
        if(StringUtil.isEmpty(logflowId)) {
            OperaLogHolder.set(operaLog);
            logger.error("存入操作日志前，没有找到logflowId参数.uri=" + request.getRequestURI());
            return true;
        }
        operaLog.setLogflowId(logflowId);

        OperaLogHolder.set(operaLog);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        OperaLogHolder.remove();
    }
}
