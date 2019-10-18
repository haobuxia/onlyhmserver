package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.Image;
import com.tianyi.helmet.server.vo.TripleVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 图片信息
 */
@Repository
public interface ImageDao extends HelmetShotDao {
    void insert(Image image);

    Image selectById(int id);

    List<TripleVo<Float, Float, Integer>> selectGeoCount();

    int increaseViewCount(int id);

    int deleteById(int id);

    int updateById(Image image);

    int updateImageTypeById(Map<String, Object> params);
}
