package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.TagDao;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 标签信息
 */
@Service
public class TagService {
    @Autowired
    private TagDao tagDao;
    @Autowired
    private TagResourceService tagResourceService;

    @CacheEvict(value = CacheKeyConstants.TAGS_ALL, key = "'all'")
    public void insert(Tag tag) {
        tagDao.insert(tag);
    }

    /**
     * 获得所有标签信息。id,name,groupId
     *
     * @return
     */
    @Cacheable(value = CacheKeyConstants.TAGS_ALL, key = "'all'", unless = "#result == null")
    public List<Tag> selectTagList() {
        return listBy(null);
    }

    public List<Tag> listBy(Map<String, Object> params) {
        return tagDao.listBy(params);
    }

    public int countBy(Map<String, Object> params) {
        return tagDao.countBy(params);
    }

    public PageListVo<Tag> list(String keyword, int page) {
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("keyword", keyword);
        List<Tag> list = listBy(map);
        int count = countBy(map);
        PageListVo<Tag> vo = new PageListVo(page);
        vo.setList(list);
        vo.setTotal(count);
        return vo;
    }

    @CacheEvict(value = CacheKeyConstants.TAGS_ALL, key = "'all'")
    @Transactional
    public void deleteById(int id) {
        tagDao.deleteById(id);
        tagResourceService.deleteByTagId(id);
    }

    /**
     * 更新标签分组
     *
     * @param groupId
     * @param id
     */
    public void updateGroupIdById(int groupId, int id) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("groupId", groupId);
        map.put("id", id);
        tagDao.updateGroupIdById(map);
    }
}
