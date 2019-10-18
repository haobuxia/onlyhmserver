package com.tianyi.helmet.server.util;

import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求响应工具
 *
 * Created by liuhanc on 2018/6/19.
 */
public class ReqRespUtils {

    private static Logger logger = LoggerFactory.getLogger(ReqRespUtils.class);

    /**
     * 输出文字
     *
     * @param response
     * @param contentType
     * @param data
     */
    public static void writeToResponse(HttpServletResponse response, String contentType, String data,int statusCode) {
        PrintWriter writer = null;
        response.setStatus(statusCode);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);
        try {
            writer = response.getWriter();
            writer.print(data);
        } catch (IOException e) {
            logger.error("response write error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }


    /**
     * 输出字节数据到浏览器客户端以便用户下载
     *
     * @param response
     * @param bytesData   文件内容字节数组
     * @param contentType 文件类型
     * @param fileName    文件名称
     * @throws IOException
     */
    public static void writeToResponse(HttpServletResponse response, byte[] bytesData, String contentType, String fileName) throws IOException {
        if (bytesData == null) bytesData = new byte[0];
        fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentLength(bytesData.length);

        response.getOutputStream().write(bytesData);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    /**
     * 下载输出fdfs里的文件，一般浏览器会弹框下载
     *
     * @throws IOException
     */
    public static void downloadFdfsFile(HttpServletResponse response, FastDfsClient fastDfsClient, String ossPath, String contentType, String fileName) throws IOException {
        fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        writeFdfsFile(response, fastDfsClient, ossPath, contentType);
    }

    /**
     * 直接输出fdfs里的文件，输出内容而不是弹框下载
     *
     * @throws IOException
     */
    public static void writeFdfsFile(HttpServletResponse response, FastDfsClient fastDfsClient, String ossPath, String contentType) throws IOException {
        response.setContentType(contentType);
        fastDfsClient.downloadFile(ossPath, response.getOutputStream());
    }

    public static boolean isRequestJson(HttpServletRequest req) {
        String acceptHeader = req.getHeader("Accept");
        if (acceptHeader == null) return false;
        if (acceptHeader.contains("text/json") || acceptHeader.contains("application/json") || acceptHeader.contains("text/javascript")) {//application/json, text/javascript, */*; q=0.01
            return true;
        }
        return false;
    }

    public static boolean isAjax(HttpServletRequest req) {
        boolean isAjaxRequest = false;
        String reqWith = req.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(reqWith)) {
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }

    public static boolean isLocalRequest(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (!StringUtils.isEmpty(referer)) {
            String url = request.getRequestURL().toString();
            int idx = url.indexOf(request.getRequestURI());
            String prefix = url.substring(0, idx);
            if (referer.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }


}
