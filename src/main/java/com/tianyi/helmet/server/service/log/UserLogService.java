package com.tianyi.helmet.server.service.log;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.dao.log.UserLogDao;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.log.UserLog;
import com.tianyi.helmet.server.entity.log.UserLogTypeEnum;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *  用户日志服务
 *
 * Created by liuhanc on 2017/12/13.
 */
@Service
public class UserLogService {
    @Autowired
    private UserLogDao userLogDao;
    @Autowired
    private UserService userService;

    public void insert(UserLog log) {
        userLogDao.insert(log);
    }

    public UserLog selectById(int id) {
        return userLogDao.selectById(id);
    }

    public List<UserLog> listBy(Map<String, Object> params) {
        return userLogDao.listBy(params);
    }

    public int countBy(Map<String, Object> params) {
        return userLogDao.countBy(params);
    }

    public void addLog(UserLogTypeEnum logType, String logContent, int userId,String userType) {
        UserLog log = new UserLog();
        log.setCreateTime(LocalDateTime.now());
        log.setLogContent(logContent);
        log.setLogType(logType.getVal());
        log.setUserId(userId);
        log.setUserType(userType);
        this.insert(log);
    }

    public void addLog(UserLogTypeEnum logType, String logContent) {
        LoginUserInfo lui = LoginUserHolder.get();
        if(lui == null){
            //如果是新注册用户则当时尚未登录,lui可能为null
            this.addLog(logType, logContent, -1,"");
        }else{
            this.addLog(logType, logContent, lui.getId(),lui.getUserType());
        }
    }

    public PageListVo queryList(Integer page, String keyword, boolean personalLog) {
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("keyword", keyword);
        if (personalLog) {
            LoginUserInfo lui = LoginUserHolder.get();
            map.put("userId", lui.getId());
            map.put("userType", lui.getUserType());
        }

        List<UserLog> logList = this.listBy(map);
        for(UserLog ul : logList){
            ul.setUserName(userService.selectById(ul.getUserId()).getName());
        }
        //todo 不晓得咋改
//        userService.fullfilUserName(logList, UserLog::getUserId, UserLog::setUserName);
        int count = this.countBy(map);
        PageListVo vo = new PageListVo(page);
        vo.setList(logList);
        vo.setTotal(count);
        return vo;
    }
}
