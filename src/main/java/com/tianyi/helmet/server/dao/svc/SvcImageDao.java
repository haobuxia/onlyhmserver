package com.tianyi.helmet.server.dao.svc;

import com.tianyi.helmet.server.entity.svc.SvcImage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/** 服务日志-照片
 *
 * Created by liuhanc on 2018/3/19.
 */
@Repository
public interface SvcImageDao {

    void insert(SvcImage image);

    List<SvcImage> listBy(Map<String, Object> map);

    int countBy(Map<String, Object> map);

    SvcImage selectById(int id);

    int deleteById(int id);

}
