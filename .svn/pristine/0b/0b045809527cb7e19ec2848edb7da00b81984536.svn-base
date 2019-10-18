package com.tianyi.helmet.server.dao.app;

import com.tianyi.helmet.server.entity.app.ApkUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 头盔apk版本升级信息
 */
@Repository
public interface ApkUpdateDao {

    void insert(ApkUpdate apk);

    ApkUpdate selectById(int id);

    List<ApkUpdate> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int updateById(ApkUpdate apk);

    int deleteById(int id);

    int updateStatusByImeiAndStatus(Map<String, Object> update);
}
