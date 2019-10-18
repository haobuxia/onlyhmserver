package com.tianyi.helmet.server.controller.file;

import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.TagGroup;
import com.tianyi.helmet.server.service.file.GetOrCreateComponent;
import com.tianyi.helmet.server.service.file.TagGroupService;
import com.tianyi.helmet.server.service.file.TagService;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  标签相关
 *
 * Created by liuhanc on 2017/12/28.
 */
@Controller
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private TagGroupService tagGroupService;
    @Autowired
    private GetOrCreateComponent getOrCreateComponent;

    /**
     * @param model
     * @return
     */
    @RequestMapping("/grouplist")
    public String toGroupListPage(Model model) {
        return toGroupListPage(1, null, model);
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping("/grouplist/{page}")
    public String toGroupListPage(@PathVariable Integer page, String keyword, Model model) {
        PageListVo<TagGroup> vo = tagGroupService.list(keyword, page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("vo", vo);
        return "file/listTagGroup";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String toListPage(Model model) {
        return toListPage(1, null, model);
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/list/{page}")
    public String toListPage(@PathVariable Integer page, String keyword, Model model) {
        PageListVo<Tag> vo = tagService.list(keyword, page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("vo", vo);
        List<TagGroup> groupList = tagGroupService.selectTagGroupList();
        model.addAttribute("groupList", groupList);

        return "file/listTag";
    }

    /**
     * @return
     */
    @RequestMapping("/tags")
    @ResponseBody
    public ResponseVo<List<DoubleVo<Integer, String>>> tagNames() {
        List<DoubleVo<Integer, String>> tagList = tagService.selectTagList().stream().map(tag -> new DoubleVo<>(tag.getId(), tag.getTagName())).collect(Collectors.toList());
        return ResponseVo.success(tagList);
    }

    /**
     * @return
     */
    @RequestMapping("/tagsbygroup")
    @ResponseBody
    public ResponseVo<List<Tag>> tagsByGroup(@RequestParam Integer groupId) {
        Map<String, Object> params = new HashMap<>();
        if(groupId != null) {
            params.put("groupId", groupId);
        }
        List<Tag> tagList = tagService.listBy(params);
        return ResponseVo.success(tagList);
    }

    /**
     * @return
     */
    @RequestMapping("/taggroups")
    @ResponseBody
    public ResponseVo<List<DoubleVo<Integer, String>>> tagGroupNames() {
        List<DoubleVo<Integer, String>> tagList = tagGroupService.selectTagGroupList().stream().map(tag -> new DoubleVo<>(tag.getId(), tag.getGroupName())).collect(Collectors.toList());
        return ResponseVo.success(tagList);
    }

    /**
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResponseVo delete(@PathVariable Integer id) {
        tagService.deleteById(id);
        return ResponseVo.success();
    }

    /**
     * @return
     */
    @RequestMapping("/deletegroup/{id}")
    @ResponseBody
    public ResponseVo deleteGroup(@PathVariable Integer id) {
        tagGroupService.deleteById(id);
        return ResponseVo.success();
    }

    /**
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseVo add(@RequestParam String tagName, @RequestParam Integer groupId) {
        Tag tag = getOrCreateComponent.getOrCreateByTagName(tagName, groupId);
        return ResponseVo.success(tag);
    }

    /**
     * @return
     */
    @RequestMapping("/addgroup")
    @ResponseBody
    public ResponseVo addGroup(@RequestParam String groupName) {
        TagGroup tagGroup = getOrCreateComponent.getOrCreateByGroupName(groupName);
        return ResponseVo.success(tagGroup);
    }

}
