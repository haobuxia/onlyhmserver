package com.tianyi.helmet.server.partner;

/**
 * 反馈信息状态码枚举
 *
 * @author yinzt
 * @description
 * @date 2016/12/20
 */
public enum HttpCodeEnum {
    OPERTATION_SUCCESS(200,"操作成功"),
    PARAMETER_ERROR(301,"参数错误"),
    SIGNATURE_ERROR(302,"签名错误"),
    NO_AUTH_ERROR(303,"无访问权限"),
    APPKEY_ERROR(304,"appKey错误"),
    DUPLICATE_ERROR(305,"重复添加"),
    INTERNAL_ERROR(500,"内部错误");

    private int code;
    private String message;
    private HttpCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
