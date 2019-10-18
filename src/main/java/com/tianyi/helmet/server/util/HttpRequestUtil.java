package com.tianyi.helmet.server.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //TODO 说明
 *
 * @author zhouwei
 * 2019/1/18 08:59
 * @version 0.1
 **/
public class HttpRequestUtil {

    public static final String SINGLE = "SINGLE";
    public static final String DATASET = "DATASET";

    public static Map getXFormRequests(HttpServletRequest request) throws IOException {

        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return new HashMap();
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = 0;
            readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        String str = null;
        str = new String(buffer, "UTF-8");

        JSONObject object = JSONObject.parseObject(str);
        Map<String, Object> reqInfo = new HashMap();
        for (String s : object.keySet()) {
            reqInfo.put(s, object.get(s));
        }
        return reqInfo;
    }

    /**
     *
     * zhouwei
     * @param request
     * @return
     * @throws IOException
     */
    public static Map<String, Object> getXFormParameter(HttpServletRequest request) {
        Map<String, String[]> reqTmp = request.getParameterMap();
//        List<Map<String, Object>> reqParameters = new ArrayList<>();
        Map<String, Object> singleReq = new HashMap<>();
        List<Map> listReq = new ArrayList<>();
        for (String s : reqTmp.keySet()) {
            String[] values = reqTmp.get(s);
            if (values.length == 1) {
                singleReq.put(s, values[0]);
            }
            for (int i = 0; i < values.length; i++) {
                if (listReq.size() < (i + 1)) {
                    listReq.add(new HashMap());
                }
                Map keyTmp = listReq.get(i);
                keyTmp.put(s, values[i]);
            }
        }
        Map<String, Object> retRequest = new HashMap<>();
        retRequest.put("SINGLE", singleReq);
        retRequest.put("DATASET", listReq);
        return retRequest;
    }
}
