package com.tianyi.helmet.server.dao.workorder;

import com.tianyi.helmet.server.entity.workorder.WorkCard;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WorkCardDao {
    int deleteByPrimaryKey(Integer workcardPk);


    int insertSelective(WorkCard record);

    WorkCard selectByPrimaryKey(Integer workcardPk);

    WorkCard selectByWorkCardId(String workId);

    int updateByPrimaryKeySelective(WorkCard record);

    int updateByPrimaryKey(WorkCard record);

    List<WorkCard>  listBy(Map map);
}