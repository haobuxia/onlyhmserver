package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.TagResourceDao;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.TagResource;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.TripleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 标签资源信息
 */
@Service
public class TagResourceService {
    private Logger logger = LoggerFactory.getLogger(TagResourceService.class);

    @Autowired
    private TagResourceDao tagResourceDao;
    @Autowired
    private TagService tagService;
    @Autowired
    private GetOrCreateComponent getOrCreateComponent;

    public void insert(UploadEntityTypeEnum typeEnum, int resId, String tagName) {
        Tag tag = getOrCreateComponent.getOrCreateByTagName(tagName);
        insert(typeEnum, resId, tag);
    }

    public void insert(UploadEntityTypeEnum typeEnum, int resId, Tag tag) {
        TagResource tagRes = new TagResource();
        tagRes.setTagId(tag.getId());
        tagRes.setResType(typeEnum.getId());
        tagRes.setResId(resId);
        tagRes.setCreateTime(LocalDateTime.now());
        insert(tagRes);
    }

    public void insert(TagResource tagRes) {
        tagResourceDao.insert(tagRes);
    }

    public TagResource selectById(int id) {
        return tagResourceDao.selectById(id);
    }

    /**
     *
     * @param resType  UploadEntityTypeEnum
     * @param resId
     * @return
     */
    public List<TagResource> listByResTypeResId(UploadEntityTypeEnum resType, int resId) {
        Map<String, Object> parms = new HashMap<>(3);
        parms.put("resType", resType.getId());
        parms.put("resId", resId);
        List<TagResource> list = this.listBy(parms);
        return list;
    }

    @Transactional
    public void updateResTags(int resType, int resId, Set<Integer> addTagIdSet, Set<Integer> removeTagIdSet) {
        if (!CollectionUtils.isEmpty(addTagIdSet)) {
            List<TagResource> addList = addTagIdSet.stream().map(tagId -> {
                TagResource tr = new TagResource();
                tr.setCreateTime(LocalDateTime.now());
                tr.setResId(resId);
                tr.setResType(resType);
                tr.setTagId(tagId);
                return tr;
            }).collect(Collectors.toList());
            tagResourceDao.insertTagResources(addList);//@todo 某个资源被设置为二手机标签（二手机功能还未设计启用）了，后续处理
        }
        if (!CollectionUtils.isEmpty(removeTagIdSet)) {
            Map<String, Object> params = new HashMap<>(3);
            params.put("resType", resType);
            params.put("resId", resId);
            removeTagIdSet.stream().forEach(tagId -> {
                params.put("tagId", tagId);
                tagResourceDao.deleteByTagIdResTypeResId(params);//@todo 某个资源被取消掉二手机标签（二手机功能还未设计启用）了，后续处理
            });
        }
    }

    public List<TagResource> listBy(Map<String, Object> params) {
        return tagResourceDao.listBy(params);
    }

    public int countBy(Map<String, Object> params) {
        return tagResourceDao.countBy(params);
    }

    /**
     * 根据某个标签删除其标记的资源信息
     *
     * @param tagId
     * @return
     */
    public int deleteByTagId(int tagId) {
        return tagResourceDao.deleteByTagId(tagId);
    }

    /**
     * 根据某个资源删除其所有标签
     *
     * @param resType
     * @param resId
     * @return
     */
    public int deleteByResTypeResId(int resType, int resId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("resType", resType);
        map.put("resId", resId);
        return tagResourceDao.deleteByResTypeResId(map);
    }

    /**
     * 按标签分组查询视频数量 /标签id，资源数、标签名
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageListVo<TripleVo<Integer, Integer, String>> groupByTag(UploadEntityTypeEnum typeEnum, Integer groupId, Integer page, Integer pageSize) {
        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        map.put("resType", typeEnum.getId());
        map.put("groupId", groupId);
        List<DoubleVo<Integer, Integer>> tagIdResCntlist = tagResourceDao.groupByTag(map);

        //数据转换
        List<Integer> tagIdList = tagIdResCntlist.stream().map(DoubleVo::getKey).distinct().collect(Collectors.toList());
        Map<Integer, String> tagIdNameMap = tagService.selectTagList().stream().filter(tag -> tagIdList.contains(tag.getId())).collect(Collectors.toMap(Tag::getId, Tag::getTagName));
        List<TripleVo<Integer, Integer, String>> voList = tagIdResCntlist.stream().map(doubleVo -> {
            //标签id，资源数、标签名
            return new TripleVo<>(doubleVo.getKey(), doubleVo.getVal(), tagIdNameMap.get(doubleVo.getKey()));
        }).collect(Collectors.toList());

        int total = tagResourceDao.selectTagCount(map);

        PageListVo<TripleVo<Integer, Integer, String>> vo = new PageListVo(page, pageSize);
        vo.setList(voList);
        vo.setTotal(total);
        return vo;
    }

}
