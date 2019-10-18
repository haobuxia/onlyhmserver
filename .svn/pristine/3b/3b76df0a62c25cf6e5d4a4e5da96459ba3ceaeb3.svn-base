package com.tianyi.helmet.server.dao.dictionary;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 版本 字典表
 * Created by xiayuan on 2018/9/30.
 */
@Repository
public interface VersionDao {

    List<Map<Integer, String>> selectVersion();

    List<Map<Integer, String>> selectVersion(@Param("id") Integer id);

    Integer getVersion(@Param("version") String version);

    int addVersion(@Param("version") String version);

}
