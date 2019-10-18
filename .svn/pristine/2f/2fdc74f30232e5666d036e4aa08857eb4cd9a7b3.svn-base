package com.tianyi.helmet.server.dao.log;

import com.tianyi.helmet.server.entity.log.OperaLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 * Created by yujiawei on 2018/7/27.
 */
@Repository
public interface OperaLogDao {
    
	void insert(OperaLog log);

    List<OperaLog> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);
}
