package com.tianyi.helmet.server.service.log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.tianyi.helmet.server.entity.log.OperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyi.helmet.server.dao.log.OperaLogDao;

/**
 *  操作日志服务
 *
 * Created by yujiawei on 2017/12/13.
 */
@Service
public class OperaLogService {
    @Autowired
    OperaLogDao operaLogDao;

    public void insert(OperaLog log){
    	operaLogDao.insert(log);
    }

    public List<OperaLog> listBy(Map<String, Object> params){
        return operaLogDao.listBy(params);
    }
    
    public int countBy(Map<String, Object> params){
        return operaLogDao.countBy(params);
    }

    public void addLog(String clientId,String logType,String logContent){
    	OperaLog log = new OperaLog();
        log.setClientId(clientId);
        log.setCreateTime(LocalDateTime.now());
        log.setLogContent(logContent);
        this.insert(log);
    }

    public void addNewLog(OperaLog operaLog, int orderNo, String logContent, int logNature) {
        if(!operaLog.getLogType().equals("no") || !operaLog.getLogflowId().equals("no"))
        {
            OperaLog log = new OperaLog();
            log.setClientId(operaLog.getClientId());
            log.setCreateTime(LocalDateTime.now());
            log.setUUID(operaLog.getUUID());
            log.setDeviceType(operaLog.getDeviceType());
            log.setLogType(operaLog.getLogType());
            log.setLogflowId(operaLog.getLogflowId());
            log.setOrderNo(new Integer(orderNo));
            log.setLogDate(LocalDate.now());
            log.setLogTime(LocalDateTime.now());
            log.setLogContent(logContent);
            log.setLogNature(new Integer(logNature));
            this.insert(log);
        }
    }

    public void addNewLog(String logflowId, String clientId, String logType, int orderNo, String logContent, int logNature) {
        if(!logflowId.equals("no") || !logType.equals("no")){
            OperaLog log = new OperaLog();
            log.setClientId(clientId);
            log.setCreateTime(LocalDateTime.now());
            log.setUUID("fuwuqi-a");
            log.setDeviceType("FWQ");
            log.setLogType(logType);
            log.setLogflowId(logflowId);
            log.setOrderNo(new Integer(orderNo));
            log.setLogDate(LocalDate.now());
            log.setLogTime(LocalDateTime.now());
            log.setLogContent(logContent);
            log.setLogNature(new Integer(logNature));
            this.insert(log);
        }
    }
}
