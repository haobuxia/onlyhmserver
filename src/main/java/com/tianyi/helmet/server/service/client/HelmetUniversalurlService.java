package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.entity.client.HelmetUniversalurlInfo;
import com.tianyi.helmet.server.vo.ResponseVo;

import java.util.List;

/**
 * Created by tianxujin on 2019/8/26 16:10
 */
public interface HelmetUniversalurlService {
    /**
     * 获取项目的统一配置URL信息
     * @param uid
     * @return
     */
    List<HelmetUniversalurlInfo> listUniversalurls(int uid, String urltype);

}
