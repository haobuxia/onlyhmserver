package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.entity.file.KeyWord;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.TagGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  列表过滤服务
 *  主要针对内存中的列表进行过滤，以便充分利用 spirng cache的特性（aop）
 *
 * Created by liuhanc on 2018/6/21.
 */
@Service
public class ListFilterService {
    @Autowired
    private KeyWordService keyWordService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagGroupService tagGroupService;


    public Tag selectTagById(int id) {
        return tagService.selectTagList().stream().filter(tag -> tag.getId() == id).findAny().orElse(null);
    }

    public Tag selectTagByName(String tagName) {
        return tagService.selectTagList().stream().filter(tag -> tag.getTagName().equals(tagName)).findAny().orElse(null);
    }

    public List<Tag> selectTagListByGroupId(int groupId) {
        return tagService.selectTagList().stream().filter(tag -> tag.getGroupId() != null && groupId == tag.getGroupId()).collect(Collectors.toList());
    }


    public KeyWord selectKeyWordById(int id) {
        return keyWordService.selectKeyWordList().stream().filter(keyWord -> keyWord.getId() == id).findAny().orElse(null);
    }

    public KeyWord selectKeyWordByName(String keywordName) {
        return keyWordService.selectKeyWordList().stream().filter(keyWord -> keyWord.getKeywordName().equals(keywordName)).findAny().orElse(null);
    }

    public List<KeyWord> selectKeyWordListByIdList(List<Integer> idList) {
        return keyWordService.selectKeyWordList().stream().filter(keyWord -> idList.contains(keyWord.getId())).collect(Collectors.toList());
    }

    public TagGroup selectGroupById(int id) {
        return tagGroupService.selectTagGroupList().stream().filter(group -> group.getId() == id).findAny().orElse(null);
    }

    public TagGroup selectGroupByName(String groupName) {
        return tagGroupService.selectTagGroupList().stream().filter(group -> group.getGroupName().equals(groupName)).findAny().orElse(null);
    }

}
