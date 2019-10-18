package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.HelmetHeartBeat;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wenxinyan on 2018/9/13.
 */
@Repository
public interface HelmetHeartBeatDao {

    void insert(HelmetHeartBeat helmetHeartBeat);

    //List<HelmetHeartBeat> selectBy(Map<String, Object> paramMap);

    //int countBy(Map<String, Object> paramMap);

}
