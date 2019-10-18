package com.tianyi.helmet.server.service.angelcomm.azkj;

import com.tianyi.helmet.server.service.angelcomm.azkj.client.AzkjStub;
import com.tianyi.helmet.server.service.angelcomm.azkj.holders.ArrayOfCallRecordHolder;
import com.tianyi.helmet.server.service.support.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.rpc.holders.IntHolder;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * Created by tianxujin on 2019/6/29 14:32
 */
@Component
public class AzkjClient {

    private Logger logger = LoggerFactory.getLogger(AzkjClient.class);

    private AzkjStub stub;

    @Autowired
    private ConfigService configService;
    @PostConstruct
    private void init() {
        String wsdlUrl = configService.getNeteaseUrl() + "/?wsdl";
        try {
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();

            // 生成的客户端代码中有XxxStub类，可如下方式使用
            stub = new AzkjStub(new URL(wsdlUrl), service);
            // 有些webservice需要登录，登陆后才能进行一些操作，这个需要设置如下两个参数：
            // 超时时间
            stub.setTimeout(1000 * 60 * 20);
            // 次数设置true，登录后才能保持登录状态，否则第二次调用ws方法时仍然会提示未登录。
            stub.setMaintainSession(true);
            // 调用ws的方法
//            EmployeeInfo[] es =  stub.getEmployeeListAll();
//            for(EmployeeInfo e : es) {
//                System.out.println(e.getName());
//            }
        } catch (Exception e) {
            logger.error("安正科技创初始化失败："+e.getMessage());
            e.printStackTrace();
        }
    }

    public String registerUser(String azkjUser) {
        String token="123456";
        try {
            int result = stub.createEmployeeInfo(azkjUser, azkjUser,2,token, 6,0.0,0.0,1,1,"");
            if(result != 0) {
                token="";
            }
        } catch (RemoteException e) {
            logger.error("安正科技创建用户失败："+e.getMessage());
            e.printStackTrace();
            token="";
        }
        return token;// 未知错误
    }

    public CallRecord[] selectCallRecord (String userAccount, String begintime, String endtime) {
        CallRecord[] callRecords = {};
        try {
            IntHolder intParam = new IntHolder(1000);
            ArrayOfCallRecordHolder recordHolder = new ArrayOfCallRecordHolder();
            stub.selectCallRecord(0,1000,begintime,endtime,-1,-1,userAccount,-1,-1,intParam,recordHolder);
            callRecords = recordHolder.value;
            for (CallRecord record : callRecords) {
//                System.out.println(record.getWebpath());// Web 访问地址
//                System.out.println(record.getSubpath());// 路径
//                System.out.println(record.getRecordname());
                String path = record.getWebpath().substring(0, 20) + ":58081" + record.getWebpath().substring(20) + record.getSubpath()+"/"+record.getRecordname();
                System.out.println(path);
                record.setRemark(path); // 全路径暂时放置到备注字段
            }
        } catch (RemoteException e) {
            logger.error("获取安正科技音视频通话记录失败："+e.getMessage());
            e.printStackTrace();
        }
        return callRecords;
    }

}
