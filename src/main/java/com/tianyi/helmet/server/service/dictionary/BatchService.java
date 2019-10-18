package com.tianyi.helmet.server.service.dictionary;

import java.util.List;
import java.util.Map;

/**
 * 批次 字典表
 * Created by xiayuan on 2018/10/10.
 */
public interface BatchService {

    List<Map<Integer, String>> selectBatch();

    int insertBatch(String batch);

}
