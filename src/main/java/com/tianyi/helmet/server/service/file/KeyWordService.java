package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.KeyWordDao;
import com.tianyi.helmet.server.entity.file.KeyWord;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 关键词信息
 */
@Service
public class KeyWordService {
    @Autowired
    private KeyWordDao keyWordDao;

    /**
     * 获得关键词列表
     *
     * @return
     */
    @Cacheable(value = CacheKeyConstants.KEYWORDS_ALL, key = "'all'", unless = "#result == null")
    public List<KeyWord> selectKeyWordList() {
        return keyWordDao.listBy(null);
    }

    @CacheEvict(value = CacheKeyConstants.KEYWORDS_ALL, key = "'all'")
    public void insert(KeyWord tag) {
        keyWordDao.insert(tag);
    }

    public KeyWord selectById(int keyWordId) {
        return keyWordDao.selectById(keyWordId);
    }
}
