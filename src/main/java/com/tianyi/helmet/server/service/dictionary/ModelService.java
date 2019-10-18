package com.tianyi.helmet.server.service.dictionary;

import java.util.List;
import java.util.Map;

/**
 * 型号 字典表
 * Created by xiayuan on 2018/10/20.
 */
public interface ModelService {

    List<Map<Integer, String>> selectModel();

    int insertModel(String model);

}
