package com.tianyi.helmet.server.dao.dictionary;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 型号 字典表
 * Created by xiayuan on 2018/10/10.
 */
@Repository
public interface ModelDao {
    List<Map<Integer, String>> selectModel();
    List<Map<Integer, String>> selectModel(@Param("id") Integer id);

    int insertModel(String model);

}
