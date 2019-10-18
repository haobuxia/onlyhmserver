package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.entity.file.KeyWord;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.TagGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *  对象获取或创建组件。针对系统中需唯一存在但又随时可能新创建的数据统一进行创建获取的管理
 *
 *
 * Created by liuhanc on 2018/4/20.
 */
@Component
public class GetOrCreateComponent {
    @Autowired
    private TagService tagService;
    @Autowired
    private ListFilterService listFilterService;
    @Autowired
    private KeyWordService keyWordService;
    @Autowired
    private TagGroupService tagGroupService;

    public Tag getOrCreateByTagName(String tagName) {
        return getOrCreateByTagName(tagName,tagGroupService.getDefaultGroupId());
    }

    public Tag getOrCreateByTagName(String tagName, int groupId) {
        Tag tag1 = listFilterService.selectTagByName(tagName);
        if (tag1 == null) {
            tag1 = new Tag();
            tag1.setCreateTime(LocalDateTime.now());
            tag1.setTagName(tagName);
            tag1.setGroupId(groupId);
            tagService.insert(tag1);
        }
        return tag1;
    }

    public TagGroup getOrCreateByGroupName(String groupName) {
        TagGroup tagGroup1 = listFilterService.selectGroupByName(groupName);
        if (tagGroup1 == null) {
            tagGroup1 = new TagGroup();
            tagGroup1.setCreateTime(LocalDateTime.now());
            tagGroup1.setGroupName(groupName);
            tagGroupService.insert(tagGroup1);
        }
        return tagGroup1;
    }

    public KeyWord getOrCreateByKeywordName(String keywordName) {
        KeyWord kw1 = listFilterService.selectKeyWordByName(keywordName);
        if (kw1 == null) {
            kw1 = new KeyWord();
            kw1.setKeywordName(keywordName);
            keyWordService.insert(kw1);
        }
        return kw1;
    }
}
