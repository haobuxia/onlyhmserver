package com.tianyi.helmet.server.service.power;

import com.tianyi.helmet.server.entity.power.Api;

import java.util.List;
import java.util.Map;

/**
 * 接口信息
 * Created by wenxinyan on 2018/10/10.
 */
public interface ApiService {

    List<Api> listAll();

    Api selectById(int id);

    List<Api> listByNoPage(Map<String, Object> param);

}
