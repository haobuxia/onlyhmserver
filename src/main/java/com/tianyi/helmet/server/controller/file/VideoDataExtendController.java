package com.tianyi.helmet.server.controller.file;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.*;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.*;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.RelationUtils;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务数据视频，视频扩展信息
 * 视频关键词信息
 * <p>
 * Created by liuhanc on 2018/5/25.
 */
@Controller
@RequestMapping("videoext")
public class VideoDataExtendController {

    private static final Logger logger = LoggerFactory.getLogger(VideoDataExtendController.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoKeywordService videoKeywordService;
    @Autowired
    private VideoComponent videoComponent;
    @Autowired
    private ConfigService configService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ListFilterService listFilterService;
    @Autowired
    private TagGroupService tagGroupService;
    @Autowired
    private TagResourceService tagResourceService;
    @Autowired
    private VideoDataExtendService videoDataExtendService;
    @Autowired
    private KeyWordService keyWordService;

    private int pageSize = 8;

    //某个类型某个工地资源
    @RequestMapping("/list/{page}")
    public String listByPageTagKeyword(@PathVariable Integer page, @RequestParam(required = false) Integer tagId, @RequestParam(required = false) Integer keywordId, Model model) {
        Map params = PageListVo.createParamMap(page, pageSize);
        params.put("groupId", tagGroupService.getSvcDataGroupId());
        params.put("tag", tagId);
        params.put("keywordId", keywordId);
        List<Video> videoList = videoService.listBy(params);
        int total = videoService.countBy(params);
        PageListVo vo = new PageListVo(page, pageSize);
        vo.setTotal(total);
        vo.setList(videoList);

        model.addAttribute("type", "video");
        model.addAttribute("vo", vo);
        model.addAttribute("isAdmin", LoginUserHolder.get().isAdmin());
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        model.addAttribute("createLoadDataFunc", false);
        return "file/newListContent";
    }


    /**
     * 播放或查看某个上传的音视图
     *
     * @param videoId
     * @param model
     * @return
     */
    @RequestMapping({"/play/{videoId}"})
    public String play(@PathVariable Integer videoId, Model model) {
        Video video = videoService.selectById(videoId);
        if (video == null)
            return "common/content404";

//        if (!videoComponent.isSvcDataVideo(video)) {
//            return "common/content404";
//        }

        //服务数据的视频,需增加额外数据传给页面
        List<Tag> tagList = listFilterService.selectTagListByGroupId(tagGroupService.getSvcDataGroupId());
        Set<Integer> svcDataTagIdSet = tagList.stream().map(Tag::getId).distinct().collect(Collectors.toSet());
        TagResource matchTagRes = tagResourceService.listByResTypeResId(UploadEntityTypeEnum.video, video.getId()).stream()
                .filter(tagRes -> {
                    return svcDataTagIdSet.contains(tagRes.getTagId());
                }).findAny().orElse(null);

        if (matchTagRes == null || !svcDataTagIdSet.contains(matchTagRes.getTagId())) {
            logger.error("视频类型属于服务数据类,但是视频的标签都不属于服务数据类.id=" + video.getId());
            return "common/content404";
        }

        VideoDataExtend extend = videoDataExtendService.selectByVideoId(video.getId());
        if (extend == null) {
            extend = new VideoDataExtend();
            extend.setVideoId(videoId);
            videoDataExtendService.insert(extend);
        }
/**
 * update by xiayuan 2018/10/10
 */
        EquipmentLedger helmet = equipmentService.selectByUUID(video.getClientId());
        if (helmet != null) {
            model.addAttribute("helmet", helmet);
        }

        int tagId = matchTagRes.getTagId();
        Tag tag = listFilterService.selectTagById(tagId);
        List<VideoKeyword> keywordList = videoKeywordService.selectByVideoId(videoId);
        RelationUtils.fullfilListRelateProperty(keywordList, VideoKeyword::getKeywordId, listFilterService::selectKeyWordListByIdList, KeyWord::getId, KeyWord::getKeywordName, VideoKeyword::setKeywordName);

        videoService.fullfilThumbOssPath(video);
        model.addAttribute("entity", video);

        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        model.addAttribute("videoExtend", JSON.toJSONString(extend));
        model.addAttribute("videoTag", tag);
        model.addAttribute("keywordList", JSON.toJSONString(keywordList));

        return "file/viewKeywordVideo";
    }

    @RequestMapping(value = {"/savetext/{videoId}"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo saveEditText(@PathVariable Integer videoId, @RequestParam String editText) {

        Video video = videoService.selectById(videoId);
        if (video == null)
            return ResponseVo.fail("视频不存在");

//        if (!videoComponent.isSvcDataVideo(video)) {
//            return ResponseVo.fail("视频类型不符,无法设置备注");
//        }

        videoDataExtendService.updateEditTextByVideoId(editText, videoId);
        return ResponseVo.success();
    }

    @RequestMapping(value = {"/addkeyword/{videoId}"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<VideoKeyword> addKeyword(@PathVariable Integer videoId, @RequestParam String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return ResponseVo.fail("关键词不能为空");
        }

        Video video = videoService.selectById(videoId);
        if (video == null)
            return ResponseVo.fail("视频不存在");

//        if (!videoComponent.isSvcDataVideo(video)) {
//            return ResponseVo.fail("视频类型不符,无法设置关键词");
//        }

        return videoComponent.addKeyword(keyword, videoId);
    }

    @RequestMapping(value = {"/removekeyword/{videoId}"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo removeKeyword(@PathVariable Integer videoId, @RequestParam Integer keywordId) {
        int cnt = videoKeywordService.deleteByVideoIdKeywordId(videoId, keywordId);
        if (cnt > 0)
            return ResponseVo.success();
        return ResponseVo.fail("视频或关键词不存在");
    }

    @RequestMapping(value = {"/updatekeyword"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo updateKeyword(VideoKeyword videoKeyword) {
        if(videoKeyword.getId()==0) {
            return ResponseVo.fail("请指定需要更新的ID");
        }
        KeyWord kw = keyWordService.selectById(videoKeyword.getKeywordId());
        if(kw == null){
            videoKeyword.setKeywordName("");
        } else {
            videoKeyword.setKeywordName(kw.getKeywordName());
        }
        videoKeywordService.updateKeyWord(videoKeyword);
        return ResponseVo.success();
    }


}
