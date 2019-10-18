package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.HelmetConfigInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/6/13 9:08
 */
@Repository
public interface HelmetConfigDao {
    void insert(HelmetConfigInfo helmetConfigInfo);

    void update(HelmetConfigInfo helmetConfigInfo);

    HelmetConfigInfo selectById(int id);

    void deleteById(int id);

    int countBy(Map<String, Object> params);

    List<HelmetConfigInfo> listBy(Map<String, Object> params);

}
