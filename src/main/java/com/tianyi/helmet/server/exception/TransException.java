package com.tianyi.helmet.server.exception;

/**
 * 事务异常
 * Created by liuhanc on 2017/12/18.
 */
public class TransException extends Exception {
    public TransException(String msg){
        super(msg);
    }

    public TransException(String msg,Throwable t){
        super(msg,t);
    }
}
