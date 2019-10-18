package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.TagGroup;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 标签分组信息
 */
@Repository
public interface TagGroupDao {
    void insert(TagGroup tagGroup);

    TagGroup selectById(int id);

    List<TagGroup> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int updateNameById(Map<String, Object> params);

    int deleteById(int id);

}
