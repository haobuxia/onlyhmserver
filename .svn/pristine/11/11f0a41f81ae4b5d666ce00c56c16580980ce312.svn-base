package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.KeyWord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 关键词信息
 */
@Repository
public interface KeyWordDao {
    void insert(KeyWord keyWord);

    KeyWord selectById(int id);

    KeyWord selectByKeywordName(String keywordName);

    List<KeyWord> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int updateNameById(Map<String, Object> params);

    int deleteById(int id);

}
