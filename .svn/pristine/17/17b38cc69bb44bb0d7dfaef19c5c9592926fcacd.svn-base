package com.tianyi.helmet.server.dao.log;

import com.tianyi.helmet.server.entity.log.HelmetLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 头盔日志
 * Created by liuhanc on 2017/12/13.
 */
@Repository
public interface HelmetLogDao {
    void insert(HelmetLog log);

    HelmetLog selectById(int id);

    List<HelmetLog> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);
}
