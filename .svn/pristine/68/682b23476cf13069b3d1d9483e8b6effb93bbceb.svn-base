package com.tianyi.helmet.server.dao.svc;

import com.tianyi.helmet.server.entity.svc.SvcFaultBrief;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/** 服务日志-故障简要信息
 *
 * Created by liuhanc on 2018/4/2.
 */
@Repository
public interface SvcFaultBriefDao {

    void insert(SvcFaultBrief brief);

    List<SvcFaultBrief> listBy(Map<String, Object> map);

    int countBy(Map<String, Object> map);

    SvcFaultBrief selectById(int id);

    int deleteById(int id);

    int updateByRwhOprtId(SvcFaultBrief brief);

}
