package com.tianyi.helmet.server.controller.file;

import com.tianyi.helmet.server.entity.file.TagResource;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.service.file.TagResourceService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  资源标签
 *
 * Created by liuhanc on 2017/12/28.
 */
@Controller
@RequestMapping("tagres")
public class TagResController {

    @Autowired
    private TagResourceService tagResourceService;

    /**
     *  载入某类下的某个资源的所有标签id信息
     * @return
     */
    @RequestMapping("/load/{resType}/{resId}")
    @ResponseBody
    public ResponseVo<List<Integer>> load(@PathVariable String resType, @PathVariable Integer resId) {
        List<Integer> list = tagResourceService.listByResTypeResId(UploadEntityTypeEnum.valueOf(resType), resId).stream().map(TagResource::getTagId).collect(Collectors.toList());
        return ResponseVo.success(list);
    }

    /**
     * @return
     */
    @RequestMapping("/save/{resType}/{resId}")
    @ResponseBody
    public ResponseVo save(@PathVariable String resType, @PathVariable Integer resId, @RequestParam(required = false) String tagIds) {
        if(tagIds == null) tagIds="";
        UploadEntityTypeEnum resTypeEnum = UploadEntityTypeEnum.valueOf(resType);
        List<TagResource> oldList = tagResourceService.listByResTypeResId(resTypeEnum, resId);
        Set<Integer> oldTagSet = oldList.stream().map(TagResource::getTagId).distinct().collect(Collectors.toSet());
        Set<Integer> tagIdSet = Arrays.stream(tagIds.split(",")).map(Integer::valueOf).collect(Collectors.toSet());

        Set<Integer> newAddTagSet = tagIdSet.stream().filter(tagId -> !oldTagSet.contains(tagId)).collect(Collectors.toSet());//传入有旧的没有，则是要增加的
        Set<Integer> newDelTagSet = oldTagSet.stream().filter(tagId -> !tagIdSet.contains(tagId)).collect(Collectors.toSet());//旧的有传入没有，则是要去掉的
        tagResourceService.updateResTags(resTypeEnum.getId(), resId, newAddTagSet, newDelTagSet);
        return ResponseVo.success();
    }

}
