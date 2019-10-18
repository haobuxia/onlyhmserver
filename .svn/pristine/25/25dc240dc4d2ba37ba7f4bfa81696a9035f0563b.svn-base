package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.HelmetUniversalInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/6/13 9:08
 */
@Repository
public interface HelmetUniversalDao {
    void insert(HelmetUniversalInfo helmetUniversalInfo);

    void update(HelmetUniversalInfo helmetUniversalInfo);

    HelmetUniversalInfo selectById(int id);

    HelmetUniversalInfo selectByUuid(String uuid);

    List<HelmetUniversalInfo> listBy(Map<String, Object> params);

    HelmetUniversalInfo selectByUserId(int userId);
}
