package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.dao.client.HelmetBindLogDao;
import com.tianyi.helmet.server.entity.client.HelmetBindLog;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 头盔绑定日志
 *
 * Created by liuhanc on 2017/10/12.
 */
@Service
public class HelmetBindLogService {

    @Autowired
    private HelmetBindLogDao clientBindLogDao;

    public void addClientBindLog(int helmetId, String userPhone, String userName){
        //绑定日志
        HelmetBindLog clientBindLog = new HelmetBindLog();
        clientBindLog.setBindTime(LocalDateTime.now());
        clientBindLog.setHelmetId(helmetId);
        clientBindLog.setUserName(userName);
        clientBindLog.setUserPhone(userPhone);
        this.addClientBindLog(clientBindLog);
    }

    @Cacheable(value=CacheKeyConstants.HELMET_BIND_USER_BY_ID,key="#bindLog.helmetId.toString()", unless = "#result == null")
    public HelmetBindLog addClientBindLog(HelmetBindLog bindLog){
        clientBindLogDao.insert(bindLog);
        return bindLog;
    }

    public PageListVo<HelmetBindLog> selectByHelmetId(int helmetId, int page, int pageSize, boolean needTotalCount){
        Map<String,Object> params = new HashMap<>();
        params.put("helmetId",helmetId);
        return selectBy(params,page,pageSize,needTotalCount);
    }

    public PageListVo<HelmetBindLog> selectBy(Map<String,Object> params, int page, int pageSize, boolean needTotalCount){
        params.put("start",( page -1) * pageSize );
        params.put("length",pageSize);
        List<HelmetBindLog> list =  clientBindLogDao.selectBy(params);

        int count = list.size();
        if(needTotalCount)
            count = clientBindLogDao.countBy(params);

        PageListVo<HelmetBindLog> vo = new PageListVo();
        vo.setList(list);
        vo.setPage(page);
        vo.setPageSize(pageSize);
        vo.setTotal(count);
        return vo;
    }

    @Cacheable(value= CacheKeyConstants.HELMET_BIND_USER_BY_ID,key="#helmetId.toString()", unless = "#result == null")
    public HelmetBindLog getLatestBindUser(int helmetId){
        HelmetBindLog log = getBindUserPhoneByHelmetIdAndTime(helmetId,LocalDateTime.now());
        return log;
    }

    /**
     * 获得某个设备某个时刻的使用用户
     *
     * @param helmetId
     * @param time
     * @return
     */
    public HelmetBindLog getBindUserPhoneByHelmetIdAndTime(int helmetId, LocalDateTime time){
        Map<String,Object> params = new HashMap<>();
        params.put("helmetId",helmetId);
        params.put("bindTime2",time);
        params.put("bind","1");//绑定日志
        PageListVo<HelmetBindLog> vo = selectBy(params,1,1,false);
        List<HelmetBindLog> list =  vo.getList();
        if(list.size() > 0){
            HelmetBindLog log = list.get(0);
            return log;
        }
        return null;
    }
}
