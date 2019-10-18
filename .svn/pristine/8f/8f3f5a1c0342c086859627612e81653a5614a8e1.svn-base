package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 标签信息
 */
@Repository
public interface TagDao {
    void insert(Tag tag);

    Tag selectById(int id);

    Tag selectByTagName(String tagName);

    List<Tag> selectByGroupId(int groupId);

    List<Tag> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int updateGroupIdById(Map<String, Object> params);

    int updateGroupIdByOldGroupId(Map<String, Object> params);

    int updateNameById(Map<String, Object> params);

    int deleteById(int id);

}
