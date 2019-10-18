package com.tianyi.helmet.server.dao.workorder;

import com.tianyi.helmet.server.entity.workorder.WorkJob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkJobDao {
    int deleteByPrimaryKey(Integer jobPk);

    int insert(WorkJob record);

    int insertSelective(WorkJob record);

    WorkJobDao selectByPrimaryKey(Integer jobPk);

    int updateByPrimaryKeySelective(WorkJob record);

    int updateByPrimaryKey(WorkJob record);

    List<WorkJob> selectByWorkOid(String workCardId);
}