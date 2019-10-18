package com.tianyi.helmet.server.controller.signature;


import com.tianyi.helmet.server.partner.HttpCodeEnum;

/**
 * 签名校验异常
 *
 */
public class SignatureException extends RuntimeException {
    private HttpCodeEnum httpCodeEnum;
    private String message;

    public SignatureException() {
    }

    public SignatureException(String message) {
        super(message);
    }

    public SignatureException(HttpCodeEnum httpCodeEnum, String message) {
        this.httpCodeEnum = httpCodeEnum;
        this.message = message;
    }

    public SignatureException(Throwable e) {
        super(e);
    }

    public SignatureException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpCodeEnum getHttpCodeEnum() {
        return httpCodeEnum;
    }

    public void setHttpCodeEnum(HttpCodeEnum httpCodeEnum) {
        this.httpCodeEnum = httpCodeEnum;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
