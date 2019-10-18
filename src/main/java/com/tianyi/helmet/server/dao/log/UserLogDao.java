package com.tianyi.helmet.server.dao.log;

import com.tianyi.helmet.server.entity.log.UserLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  用户日志
 * Created by liuhanc on 2017/12/13.
 */
@Repository
public interface UserLogDao {
    void insert(UserLog log);

    UserLog selectById(int id);

    List<UserLog> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);
}
