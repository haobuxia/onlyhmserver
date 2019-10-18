package com.tianyi.helmet.server.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.util.Streams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  http调用相关工具
 *
 * Created by liuhanc on 2018/1/24.
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static HttpEntity createJsonEntity(String jsonString, HttpEntityEnclosingRequestBase method) {
        method.addHeader("Content-Type", "application/json");
        if (StringUtils.isEmpty(jsonString)) jsonString = "{}";
        StringEntity entity = new StringEntity(jsonString, ContentType.APPLICATION_JSON);
        method.setEntity(entity);
        return entity;
    }

    public static HttpEntity createJsonEntity(Map<String, String> params, HttpEntityEnclosingRequestBase method) {
        JSONObject jo = new JSONObject();
        params.keySet().stream().forEach(key -> {
            jo.put(key, params.get(key));
        });
        return createJsonEntity(jo.toJSONString(), method);
    }

    public static HttpEntity createUploadFileFormEntity(Map<String, ? extends Object> params,HttpEntityEnclosingRequestBase httpPost, Map<String,File> uploadFiles) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        httpPost.setConfig(requestConfig);

        Charset urf8 = Charset.forName("UTF-8");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        multipartEntityBuilder.setCharset(urf8);
        ContentType fileContType = ContentType.create("application/octet-stream", urf8);
        uploadFiles.keySet().stream().forEach(fileKey->{
            File file = uploadFiles.get(fileKey);
            multipartEntityBuilder.addBinaryBody(fileKey, file, fileContType, file.getName());
            logger.debug("文件上传提交参数:" + fileKey + "=" + file.getAbsolutePath());
        });

        if (params != null) {
            ContentType myContType = ContentType.create("multipart/form-data", urf8);
            params.keySet().stream().forEach(key -> {
                Object val = params.get(key);
                logger.debug("文件上传时提交其他参数:" + key + "=" + val);
                multipartEntityBuilder.addTextBody(key, val == null ? null : val.toString(), myContType);
            });
        }

        HttpEntity httpEntity = multipartEntityBuilder.build();//此时如果输出httpEntity的body内容，可以得到中文的参数,如果不是中文参数则数据到了后台会乱码且签名无法通过.
        httpPost.setEntity(httpEntity);
        return httpEntity;
    }

    public static HttpEntity createUrlEncodedFormEntity(Map<String, ? extends Object> params, HttpEntityEnclosingRequestBase method) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>(params == null ? 0 : params.size());
        method.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        if (params != null) {
            params.keySet().stream().forEach(key -> {
                Object val = params.get(key);
                nvps.add(new BasicNameValuePair(key, val == null ? null : val.toString()));
            });
        }

        try {
            HttpEntity entity = new UrlEncodedFormEntity(nvps, "utf-8");
            method.setEntity(entity);
            return entity;
        } catch (Exception e) {
            logger.error("create UrlEncodedFormEntity exception", e);
        }

        return null;
    }

    public static byte[] executeBinnaryHttp(HttpUriRequest method, boolean checkStatusCode) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        // 执行请求
        HttpResponse response = client.execute(method);
        int code = response.getStatusLine().getStatusCode();
        if (checkStatusCode && code != 200) {
            logger.error("executeHttp response code is not 200 but " + code + ",url:" + method.getURI() + ",method:" + method.getMethod());
            throw new RuntimeException("请求接口反馈状态码不是200而是" + code);
        }

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            byte[]  bytes = EntityUtils.toByteArray(entity);
            return bytes;
        }
        return null;
    }

    public static String executeHttp(HttpUriRequest method, boolean checkStatusCode) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        // 执行请求
        HttpResponse response = client.execute(method);
        int code = response.getStatusLine().getStatusCode();
        if (checkStatusCode && code != 200) {
            logger.error("executeHttp response code is not 200 but " + code + ",url:" + method.getURI() + ",method:" + method.getMethod());
            throw new RuntimeException("请求接口反馈状态码不是200而是" + code);
        }

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String respString = EntityUtils.toString(entity, "utf-8");
            return respString;
        }
        return null;
    }

    public static String executeFileUpload(String actionUrl, Map<String, ? extends Object> params, String fileKey, File file, Map<String, String> headers) {

        try {
            String BOUNDARY = java.util.UUID.randomUUID().toString();
            String PREFIX = "--", LINEND = "\r\n";
            String MULTIPART_FROM_DATA = "multipart/form-data";
            String CHARSET = "UTF-8";

            URL url = new URL(actionUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 打开连接
            // conn.setInstanceFollowRedirects(true);

            conn.setConnectTimeout(1000 * 1000);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Charset", CHARSET); // 设置编码
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

            if (headers != null) {
                headers.keySet().stream().forEach(key -> {
                    conn.setRequestProperty(key, headers.get(key));
                });
            }

            // 首先组拼文本类型的参数
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, ? extends Object> entry : params.entrySet()) {
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
//                sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
//                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }

            DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(sb.toString().getBytes());
            // 发送文件数据
            StringBuilder sb1 = new StringBuilder();
            sb1.append(PREFIX);
            sb1.append(BOUNDARY);
            sb1.append(LINEND);
            sb1.append("Content-Disposition: form-data; name=\"" + fileKey + "\"; filename=\"" + file.getName() + "\"" + LINEND);
            sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
            sb1.append(LINEND);

            outStream.write(sb1.toString().getBytes());
            InputStream is = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }

            is.close();
            outStream.write(LINEND.getBytes());
            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();

            /**
             * 获取响应码 200=成功 当响应成功，获取响应的流
             */
            if (conn.getResponseCode() == 200) {
                InputStream input = conn.getInputStream();
                String msg = Streams.asString(input);
                return msg;
            } else {
                return "resp code :" + conn.getResponseCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error:" + e.getMessage();
        }
    }

}
