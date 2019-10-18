package com.tianyi.helmet.server.dao.app;

import com.tianyi.helmet.server.entity.app.ApkFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * apk文件信息
 */
@Repository
public interface ApkFileDao {

    void insert(ApkFile apk);

    ApkFile selectById(int id);

    List<ApkFile> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int updateById(ApkFile apk);

    int deleteById(int id);

    ApkFile getAppLastForceVersion();

    ApkFile getAppLastOptionalVersion();
}
