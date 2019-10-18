package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.HelmetConfigInfo;
import com.tianyi.helmet.server.entity.client.HelmetUniversalurlInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/6/13 9:08
 */
@Repository
public interface HelmetUniversalurlDao {
    void insert(HelmetUniversalurlInfo helmetUniversalurlInfo);

    void update(HelmetUniversalurlInfo helmetUniversalurlInfo);

    HelmetUniversalurlInfo selectById(int id);

    void deleteById(int id);

    int countBy(Map<String, Object> params);

    List<HelmetUniversalurlInfo> listBy(Map<String, Object> params);

}
