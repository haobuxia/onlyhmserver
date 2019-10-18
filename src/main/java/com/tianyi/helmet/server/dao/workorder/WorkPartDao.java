package com.tianyi.helmet.server.dao.workorder;

import com.tianyi.helmet.server.entity.workorder.WorkCard;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * //TODO 说明
 *
 * @author zhouwei
 * 2019/1/6 15:49
 * @version 0.1
 **/
@Repository
public interface WorkPartDao {
    List<WorkCard> listBy(Map<String, Object> map);

    List<WorkCard> getTodoWorkCardApp(Map<String, Object> map);
}
