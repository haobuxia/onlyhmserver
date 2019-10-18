package com.tianyi.helmet.server.controller.interceptor;

import com.tianyi.helmet.server.service.job.TyUserSvcTaskRefreshJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务日志任务和消息刷新拦截器
 * <p>
 * Created by liuhanc on 2018/4/3.
 */
@Component
public class SvcTaskMsgRefreshInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {

    @Autowired
    private TyUserSvcTaskRefreshJob tyUserSvcTaskRefreshJob;

    private Logger logger = LoggerFactory.getLogger(SvcTaskMsgRefreshInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //每次调用接口前，执行1次刷新检测。是否真实刷新，需看情况
        doRefresh();
        return true;
    }

    protected void doRefresh() {
        try {
            tyUserSvcTaskRefreshJob.doRefresh(TianYuanUserHolder.get(), false);//择机刷新
        } catch (Exception e) {
            logger.error("刷新用户的服务日志任务异常", e);
        }
    }
}
