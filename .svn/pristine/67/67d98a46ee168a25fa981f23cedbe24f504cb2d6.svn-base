package com.tianyi.helmet.server.service.log;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.dao.log.HelmetLogDao;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.log.HelmetLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *  头盔日志服务
 *
 * Created by liuhanc on 2017/12/13.
 */
@Service
public class HelmetLogService {
    @Autowired
    HelmetLogDao helmetLogDao;

    public void insert(HelmetLog log){
        helmetLogDao.insert(log);
    }

    public HelmetLog selectById(int id){
        return helmetLogDao.selectById(id);
    }

    public List<HelmetLog> listBy(Map<String, Object> params){
        return helmetLogDao.listBy(params);
    }

    public int countBy(Map<String, Object> params){
        return helmetLogDao.countBy(params);
    }

    public void addLog(String clientId,String logType,String logContent){
        HelmetLog log = new HelmetLog();
        log.setClientId(clientId);
        log.setCreateTime(LocalDateTime.now());
        log.setLogContent(logContent);
        log.setLogType(logType);
        log.setLogUserId(getCurrentUserId());
        this.insert(log);
    }

    private int getCurrentUserId(){
        LoginUserInfo lui = LoginUserHolder.get();
        return lui == null ? -1 : lui.getId();
    }
}
