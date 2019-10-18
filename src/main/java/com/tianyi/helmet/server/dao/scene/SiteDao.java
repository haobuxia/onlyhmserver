package com.tianyi.helmet.server.dao.scene;

import com.tianyi.helmet.server.entity.scene.Site;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/** 工地
 *
 * Created by liuhanc on 2018/1/16.
 */
@Repository
public interface SiteDao {

    void insert(Site site);

    List<Site> selectBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    Site selectById(int id);

    int updateById(Site site);

    int deleteById(int id);
}
