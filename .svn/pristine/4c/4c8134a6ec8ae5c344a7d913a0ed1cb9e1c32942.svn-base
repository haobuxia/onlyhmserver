package com.tianyi.helmet.server.controller;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.tianyi.helmet.server.controller.signature.SignatureException;
import com.tianyi.helmet.server.exception.TianyiException;
import com.tianyi.helmet.server.exception.TransException;
import com.tianyi.helmet.server.service.fastdfs.FdfsException;
import com.tianyi.helmet.server.service.tianyuan.TianYuanException;
import com.tianyi.helmet.server.util.ClientIpUtils;
import com.tianyi.helmet.server.util.ReqRespUtils;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * Created by liuhanc on 2017/10/30.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Object handleRuntimeException(HttpServletRequest req, HttpServletResponse res,
                                         RuntimeException ex) throws IOException {
        printRequestAndStack(req, ex, "运行时异常(返回信息)");
        return ResponseVo.fail(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Object handleIllegalArgumentException(HttpServletRequest req, HttpServletResponse res, IllegalArgumentException ex) throws IOException {
        printRequestAndStack(req, ex, "参数错误");
        return createFailResponse(req, res, "参数错误");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Object handleMethodArgumentTypeMismatchException(HttpServletRequest req, HttpServletResponse res, MethodArgumentTypeMismatchException ex) throws IOException {
        printRequestAndStack(req, ex, "参数类型不匹配:" + ex.getName());
        return createFailResponse(req, res, "参数类型不匹配:" + ex.getName());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object handleBindException(HttpServletRequest req, HttpServletResponse res, BindException ex) throws IOException {
        String[] fields = ex.getSuppressedFields();
        String fieldsString = Arrays.stream(fields).collect(Collectors.joining(","));
        printRequest(req, ex, "传入参数值绑定异常");
        return createFailResponse(req, res, "参数值错误:" + fieldsString);
    }


    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public Object handleMultipartException(HttpServletRequest req, HttpServletResponse res, MultipartException ex) throws IOException {
        return createFailResponse(req, res, "文件上传请求有误:" + ex.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Object handleNullPointerException(HttpServletRequest req, HttpServletResponse res, NullPointerException ex) throws IOException {
        printRequestAndStack(req, ex, "空数据错误");
        return createFailResponse(req, res, "空数据错误");
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Object handleMissingServletRequestParameterException(HttpServletRequest req, HttpServletResponse res, MissingServletRequestParameterException ex) throws IOException {
        printRequest(req, ex, "请求参数不足:" + ex.getParameterName());
        return createFailResponse(req, res, "请求参数不足:" + ex.getParameterName());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Object handleHttpRequestMethodNotSupportedException(HttpServletRequest req, HttpServletResponse res, HttpRequestMethodNotSupportedException ex) throws IOException {
        return createFailResponse(req, res, "不支持的请求方法:" + ex.getMethod());
    }

    @ExceptionHandler(value = RedisConnectionFailureException.class)
    @ResponseBody
    public Object handleRedisConnectionFailureException(HttpServletRequest req, HttpServletResponse res, RedisConnectionFailureException ex) throws IOException {
        return createFailResponse(req, res, "缓存异常");
    }

    @ExceptionHandler(value = SignatureException.class)
    @ResponseBody
    public Object handleSignatureException(HttpServletRequest req, HttpServletResponse res, SignatureException ex) throws IOException {
        return createFailResponse(req, res, "接口签名异常." + ex.getMessage());
    }

    @ExceptionHandler(value = TianYuanException.class)
    @ResponseBody
    public Object handleTianYuanException(HttpServletRequest req, HttpServletResponse res, TianYuanException ex) throws IOException {
        logger.error("发生天远服务异常.uri=" + req.getRequestURI() + ",method=" + req.getMethod() + ",imei=" + req.getHeader("imei") + ".err msg=" + ex.getMessage());
        return createFailResponse(req, res, "天远服务异常." + ex.getMessage());
    }

    @ExceptionHandler(value = TianyiException.class)
    @ResponseBody
    public Object handleTianyiException(HttpServletRequest req, HttpServletResponse res, TianyiException ex) throws IOException {
        logger.error("发生田一内部异常.uri=" + req.getRequestURI() + ",method=" + req.getMethod() + ",imei=" + req.getHeader("imei") + ".err msg=" + ex.getMessage());
        return createFailResponse(req, res, "服务异常." + ex.getMessage());
    }

    /**
     * spring 嵌套事务回滚异常
     *
     * @param req
     * @param res
     * @param ex
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = UnexpectedRollbackException.class)
    @ResponseBody
    public Object handleUnexpectedRollbackException(HttpServletRequest req, HttpServletResponse res, UnexpectedRollbackException ex) throws IOException {
        printRequest(req, ex, "事务操作失败");
        return createFailResponse(req, res, "事务操作失败");
    }


    @ExceptionHandler(value = MySQLSyntaxErrorException.class)
    @ResponseBody
    public Object handleMySQLSyntaxErrorException(HttpServletRequest req, HttpServletResponse res, MySQLSyntaxErrorException ex) throws IOException {
        printRequest(req, ex, "数据库语法错误");
        return createFailResponse(req, res, "数据库错误");
    }

    @ExceptionHandler(value = FdfsException.class)
    @ResponseBody
    public Object handleFdfsException(HttpServletRequest req, HttpServletResponse res, FdfsException ex) throws IOException {
        printRequest(req, ex, "文件存储发生意外");
        return createFailResponse(req, res, "文件存储发生意外");
    }

    @ExceptionHandler(value = TransException.class)
    @ResponseBody
    public Object handleTransException(HttpServletRequest req, HttpServletResponse res, TransException ex) throws IOException {
        printRequest(req, ex, "事务操作发生意外");
        return createFailResponse(req, res, "事务操作发生意外");
    }


    @ExceptionHandler(value = ClassCastException.class)
    @ResponseBody
    public Object handleClassCastException(HttpServletRequest req, HttpServletResponse res, ClassCastException ex) throws IOException {
        printRequestAndStack(req, ex, "程序错误");
        return createFailResponse(req, res, ex.getMessage());
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    @ResponseBody
    public Object handleIndexOutOfBoundsException(HttpServletRequest req, HttpServletResponse res, IndexOutOfBoundsException ex) throws IOException {
        printRequestAndStack(req, ex, "程序错误数组越界");
        return createFailResponse(req, res, "程序错误数组越界.index=" + ex.getMessage());
    }

    @ExceptionHandler(value = FileUploadException.class)
    @ResponseBody
    public Object handleFileUploadException(HttpServletRequest req, HttpServletResponse res, FileUploadException ex) throws IOException {
        return createFailResponse(req, res, "文件上传请求有误." + ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleException(HttpServletRequest req, HttpServletResponse res, Exception ex) throws IOException {
        printRequestAndStack(req, ex, "未预料的异常");
        return createFailResponse(req, res, ex.getMessage());//@todo 根据请求是否ajax返回不同的content
    }

    /**
     * 创建反馈
     *
     * @return
     */
    private Object createFailResponse(HttpServletRequest req, HttpServletResponse resp, String msg) {
        if (ReqRespUtils.isRequestJson(req)) {
            return ResponseVo.fail(msg);
        } else {
            ReqRespUtils.writeToResponse(resp, "text/plain;charset=utf-8", msg,200);
            return null;
        }
    }

    /**
     * 在日志里打印请求信息
     *
     * @param req
     * @param ex
     */
    private void printRequest(HttpServletRequest req, Exception ex, String whatMatter) {
        StringBuffer msg = new StringBuffer("\r\n===========begin================\r\n");
        msg.append(whatMatter).append("\n");
        msg.append(ex.getClass().getName()).append(":").append(ex.getMessage()).append("\r\n");
        msg.append("request method:").append(req.getMethod()).append("\t client ip:").append(ClientIpUtils.getIpAddress(req)).append("\r\n");
        msg.append("request url:").append(req.getRequestURL()).append("\r\n");
        if ("post".equalsIgnoreCase(req.getMethod())) {
            Map<String, String[]> map = req.getParameterMap();
            if (map != null)
                msg.append("post data:").append(JSON.toJSON(map)).append("\r\n");
        }
        msg.append("\n===========end================");
        logger.error(msg.toString());
    }

    /**
     * 在日志里打印请求信息和异常堆栈
     *
     * @param req
     * @param ex
     */
    private void printRequestAndStack(HttpServletRequest req, Exception ex, String whatMatter) {
        printRequest(req, ex, whatMatter);
        logger.error(whatMatter + " 异常堆栈信息:", ex);
    }


}
