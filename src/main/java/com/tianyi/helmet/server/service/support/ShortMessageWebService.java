package com.tianyi.helmet.server.service.support;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 发送短信webservice
 *
 * Created by liuhanc on 2017/12/15.
 */
@WebService(targetNamespace =  "http://tempuri.org/")
public interface ShortMessageWebService {

    String namespace = "http://tempuri.org/";
    String serviceName="QmyService";
    String portName="BasicHttpBinding_IQmyService";

    /**
     * 发送短信
     * @param param
     * @return
     */
    @WebMethod(action = namespace+"IQmyService/SendMessage")
    @WebResult(name = "SendMessageResult", targetNamespace = namespace)
    public String SendMessage(@WebParam(name = "strJson", targetNamespace = namespace) String param);
}
