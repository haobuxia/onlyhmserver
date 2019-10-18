package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import com.tianyi.helmet.server.vo.ResponseVo;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/6/12 16:10
 */
public interface HelmetConfigService {
    /**
     * 获取项目的统一配置信息
     * @param uid
     * @return
     */
    ResponseVo listConfigs(int uid);

}
