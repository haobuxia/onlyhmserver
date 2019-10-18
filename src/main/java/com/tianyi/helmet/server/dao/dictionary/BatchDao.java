package com.tianyi.helmet.server.dao.dictionary;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 批次 字典表
 * Created by xiayuan on 2018/10/10.
 */
@Repository
public interface BatchDao {
    List<Map<Integer, String>> selectBatch();
    List<Map<Integer, String>> selectBatch(@Param("id") Integer id);

    int insertBatch(String batch);

}
