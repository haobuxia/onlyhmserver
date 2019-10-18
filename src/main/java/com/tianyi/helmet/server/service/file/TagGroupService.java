package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.TagDao;
import com.tianyi.helmet.server.dao.file.TagGroupDao;
import com.tianyi.helmet.server.entity.file.TagGroup;
import com.tianyi.helmet.server.exception.TianyiException;
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

/**
 * 标签分组信息
 */
@Service
public class TagGroupService {

    @Autowired
    private TagGroupDao tagGroupDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private ListFilterService listFilterService;

    public int getDefaultGroupId() {
        return 1;
    }

    public int getSvcDataGroupId() {
        return 2;
    }

    public int getRecIrcDataGroupId() {
        return 3;
    }


    @CacheEvict(value = CacheKeyConstants.TAG_GROUPS_ALL, key = "'all'")
    public void insert(TagGroup group) {
        tagGroupDao.insert(group);
    }

    /**
     * 获得所有标签信息。id,name,groupId
     *
     * @return
     */
    @Cacheable(value = CacheKeyConstants.TAG_GROUPS_ALL, key = "'all'", unless = "#result == null")
    public List<TagGroup> selectTagGroupList() {
        return listBy(null);
    }

    public List<TagGroup> listBy(Map<String, Object> params) {
        return tagGroupDao.listBy(params);
    }

    public int countBy(Map<String, Object> params) {
        return tagGroupDao.countBy(params);
    }

    public PageListVo<TagGroup> list(String keyword, int page) {
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("keyword", keyword);
        List<TagGroup> list = listBy(map);
        int count = countBy(map);
        PageListVo<TagGroup> vo = new PageListVo(page);
        vo.setList(list);
        vo.setTotal(count);
        return vo;
    }

    @CacheEvict(value = CacheKeyConstants.TAG_GROUPS_ALL, key = "'all'")
    @Transactional
    public void deleteById(int id) {
        TagGroup group = listFilterService.selectGroupById(id);
        if (group != null) {
            if (group.getId() == this.getDefaultGroupId()) {
                throw new TianyiException("标签默认分组不可删除");
            }

            tagGroupDao.deleteById(id);
            Map<String, Object> map = new HashMap<>(2);
            map.put("groupId", this.getDefaultGroupId());
            map.put("oldGroupId", group.getId());
            tagDao.updateGroupIdByOldGroupId(map);
        }
    }

}
