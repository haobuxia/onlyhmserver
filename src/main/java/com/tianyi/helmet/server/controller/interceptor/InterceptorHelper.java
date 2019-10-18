package com.tianyi.helmet.server.controller.interceptor;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.util.ReqRespUtils;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web controller拦截器帮助工具
 * Created by liuhanc on 2017/12/5.
 */
@Component
public class InterceptorHelper {
    private Logger logger = LoggerFactory.getLogger(InterceptorHelper.class);

    /**
     * 反馈没有权限
     *
     * @param request
     * @param response
     * @param reasonMsg
     * @throws IOException
     */
    public void writeNoAccessResponse(HttpServletRequest request, HttpServletResponse response, String reasonMsg) throws IOException {
        if (ReqRespUtils.isAjax(request)) {
            //ajax
            if (ReqRespUtils.isRequestJson(request)) {
                ResponseVo vo = ResponseVo.fail(reasonMsg);
                ReqRespUtils.writeToResponse(response, "text/json; charset=utf-8", JSON.toJSONString(vo),403);
            } else {
                response.sendRedirect("/common/content403");
            }
        } else {
            response.sendRedirect("/common/403");
        }
    }
}
