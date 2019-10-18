package com.tianyi.helmet.server.controller.scene;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.scene.VideoAction;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.scene.VideoActionService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 视频动作
 * <p>
 * Created by liuhanc on 2018/6/5.
 */
@RequestMapping("videoaction")
@Controller
public class VideoActionController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoActionService videoActionService;
    @Autowired
    private KmxService kmxService;
    @Autowired
    private ConfigService configService;

    @RequestMapping("/get/{videoId}")
    @ResponseBody
    public ResponseVo<VideoAction> getVideoAction(@PathVariable int videoId) {
        VideoAction va = videoActionService.selectByVideoId(videoId);
        return ResponseVo.success(va);
    }

    @RequestMapping("/save/{videoId}")
    @ResponseBody
    public ResponseVo<VideoAction> saveVideoAction(@PathVariable int videoId, VideoAction videoAction) {
        if (!videoActionService.isSetComplete(videoAction)) {
            return ResponseVo.fail("动作设置不完整，无法保存.");
        }

        Video video = videoService.selectById(videoId);
        if (video == null || video.getIntHasGpsData() != 1) {
            return ResponseVo.fail("该视频不支持动作设置.");
        }

        videoAction.setVideoId(videoId);
        videoAction.setTyboxImei(video.getImei());
        videoAction.setVideoTime(video.getCreateTime());

        VideoAction oldVa = videoActionService.selectByVideoId(videoId);
        if (oldVa == null) {
            videoActionService.insert(videoAction);
        } else {
            videoActionService.updateByVideoId(videoAction);
        }

        return ResponseVo.success(videoAction);
    }


    @RequestMapping("/addtocompare/{videoId}")
    @ResponseBody
    public ResponseVo addToCompare(@PathVariable int videoId) {
        LoginUserInfo userinfo = LoginUserHolder.get();
        boolean success = videoActionService.addUserCompareVideo(userinfo.getId(), videoId);
        if(success)
            return ResponseVo.success();
        return ResponseVo.fail("可能已经添加了");
    }

    @RequestMapping("/deletecompare/{videoId}")
    @ResponseBody
    public ResponseVo deleteCompare(@PathVariable int videoId) {
        LoginUserInfo userinfo = LoginUserHolder.get();
        videoActionService.deleteUserCompareVideo(userinfo.getId(), videoId);
        return ResponseVo.success();
    }


    @RequestMapping("/clearcompare")
    @ResponseBody
    public ResponseVo clearCompare() {
        LoginUserInfo userinfo = LoginUserHolder.get();
        videoActionService.clearUserCompareVideo(userinfo.getId());
        return ResponseVo.success();
    }

    @RequestMapping("/comparelist")
    @ResponseBody
    public ResponseVo<List<Video>> compareList() {
        LoginUserInfo userinfo = LoginUserHolder.get();
        List<Video> videoList = videoActionService.listUserUserCompareVideo(userinfo.getId());
        return ResponseVo.success(videoList);
    }

    //进入视频动作对比展示页面
    @RequestMapping("/compare")
    public String toComparePage(Model model) {
        LoginUserInfo userinfo = LoginUserHolder.get();
        List<Video> videoList = videoActionService.listUserUserCompareVideo(userinfo.getId());
        videoList.stream().forEach(video->{
            VideoAction va = videoActionService.selectByVideoId(video.getId());
            video.setVideoAction(va);

            List<Long> timeList = videoActionService.getTimeList(va);
            Map<Integer, List<Integer>> typeValListMap = kmxService.queryGpsIntDataByTimeList(video.getImei(), timeList);
            video.setTypeValListMap(typeValListMap);
        });

        Map<String, Integer> typeNameIdMap = GpsDataTypeItemEnum.getCompareDataTypeList().stream()
                .collect(Collectors.toMap(typeEnum -> typeEnum.getName(), typeEnum -> typeEnum.getId()));
        model.addAttribute("videoList", JSON.toJSONString(videoList));
        model.addAttribute("typeNameIdMap", JSON.toJSONString(typeNameIdMap));
        model.addAttribute("fileServer",configService.getFastdfsServerUrl());
        return "scene/recirc/videoActionCompare";
    }
}
