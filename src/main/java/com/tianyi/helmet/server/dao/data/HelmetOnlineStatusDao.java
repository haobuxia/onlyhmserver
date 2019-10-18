package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.HelmetOnlineStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxujin on 2019/3/14.
 */
@Repository
public interface HelmetOnlineStatusDao {

    void insert(HelmetOnlineStatus helmetOnlineStatus);

    List<HelmetOnlineStatus> selectByClientId(Map<String, Object> paramMap);

    void update(HelmetOnlineStatus hs);

    public List<Map<String, Object>> getLoginTimes(Map<String, Object> paramMap);

    public List<Map<String,Object>> getShootTimes(Map<String, Object> params);

    public List<Map<String, Object>> getLoginTimesByDay(Map<String, Object> paramMap);

    public List<Map<String,Object>> getShootTimesByDay(Map<String, Object> params);

    List<Map<String,Object>> getShootTimesByArea(Map<String, Object> params);

    List<Map<String,Object>> getShootTimeListByUser(Map<String, Object> params);

    List<Map<String,Object>> getShootTimeListByUserByDay(Map<String, Object> params);

    List<Map<String,Object>> getShootNumListByTag(Map<String, Object> params);

    List<HelmetOnlineStatus> getOnlineUser();

    void updateByClientId(Map<String, Object> params);

    int countBy(Map<String, Object> params);
}
