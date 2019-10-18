package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.TagResource;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 标签资源信息
 */
@Repository
public interface TagResourceDao {
    void insert(TagResource tagResource);

    void insertTagResources(List<TagResource> list);

    TagResource selectById(int id);

    List<TagResource> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int deleteById(int id);

    int deleteByTagId(int tagId);

    int deleteByResTypeResId(Map<String, Object> params);

    int deleteByTagIdResTypeResId(Map<String, Object> params);

    List<DoubleVo<Integer,Integer>> groupByTag(Map<String, Object> params);

    int selectTagCount(Map<String, Object> params);

}
