package com.tianyi.helmet.server.controller.file;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.UploadEntity;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.service.file.*;
import com.tianyi.helmet.server.service.job.VideoWorkOrderProcessJob;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 媒体模块，包括图像和视频
 * <p>
 * Created by liuhanc on 2017/11/22.
 */
@Controller
@RequestMapping("media")
public class MediaController {

    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    private TagService tagService;
    @Autowired
    private ListFilterService listFilterService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private ImageService imageService;
    @Autowired
    private VideoService videoService;

    /**
     * 进入媒体中心首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {
        List<Tag> tagList = tagService.selectTagList();
        model.addAttribute("tagList", tagList);
        return "file/mediaSearch";
    }

    /**
     * 进入资源搜索结果页面
     *
     * @return
     */
    @RequestMapping("/search/{resType}/{orderBy}")
    public String enterSearchResultPage(@PathVariable String resType, @PathVariable String orderBy, @RequestParam(required = false) String keyword, @RequestParam(required = false) Integer page, Model model) {
        List<Tag> tagList = tagService.selectTagList();
        model.addAttribute("tagList", tagList);
        model.addAttribute("resType", resType);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("page", page == null ? 1 : page);
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        return "file/mediaList";
    }

    /**
     * 执行搜索，反馈搜索结果
     *
     * @return
     */
    @RequestMapping("/load/{resType}/{orderBy}")
    public String loadSearchResultData(@PathVariable String resType, @PathVariable String orderBy, @RequestParam(required = false) String keyword, @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String deviceNumber,
                                       @RequestParam(required = false) String sceneSelect,
                                       @RequestParam(required = false) String brandSelect,
                                       @RequestParam(required = false) String machineTypeSelect,
                                       @RequestParam(required = false) String machineModelSelect,
                                       @RequestParam(required = false) String machineNumber,
                                       @RequestParam(required = false) String orderNo,
                                       @RequestParam(required = false) String workId,
                                       @RequestParam(required = false) String timeLong,
                                       @RequestParam(required = false) Integer tag,
                                       @RequestParam(required = false) Integer affiliationId,
            Model model) {
        if (page == null || page < 0) page = 1;
        int pageSize = 8;
        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.get(resType);
        PageListVo<UploadEntity> vo = null;
        if (typeEnum == null || !(UploadEntityTypeEnum.video.equals(typeEnum) || UploadEntityTypeEnum.image.equals(typeEnum))) {
            vo = new PageListVo<>(page);
            vo.setTotal(0);
        } else {
            Map<String, Object> params = PageListVo.createParamMap(page, pageSize);
            params.put("orderBy", orderBy);
            if(userName != null && !"".equalsIgnoreCase(userName)){
                params.put("userName", userName);
            }
            if(deviceNumber != null && !"".equalsIgnoreCase(deviceNumber)){
                params.put("deviceNumber", deviceNumber);
            }
            if(sceneSelect != null && !"".equalsIgnoreCase(sceneSelect)){
                params.put("sceneSelect", sceneSelect);
            }
            if(brandSelect != null && !"".equalsIgnoreCase(brandSelect)){
                params.put("brandSelect", brandSelect);
            }
            if(machineTypeSelect != null && !"".equalsIgnoreCase(machineTypeSelect)){
                params.put("machineTypeSelect", machineTypeSelect);
            }
            if(machineModelSelect != null && !"".equalsIgnoreCase(machineModelSelect)){
                params.put("machineModelSelect", machineModelSelect);
            }
            if(machineNumber != null && !"".equalsIgnoreCase(machineNumber)){
                params.put("machineNumber", machineNumber);
            }
            if(orderNo != null && !"".equalsIgnoreCase(orderNo)){
                params.put("orderNo", orderNo);
            }
            if(workId != null && !"".equalsIgnoreCase(workId)){
                params.put("workId", workId);
            }
            if(timeLong != null && !"".equalsIgnoreCase(timeLong.trim())){
                String[] times = timeLong.split("-");
                params.put("timeLongBegin", times[0]);
                params.put("timeLongEnd", times[1]);
            }
            if(tag !=null && tag > 0) {
                params.put("tag", tag);
            }
            if(affiliationId !=null && affiliationId > 0) {
                params.put("affiliationId", affiliationId);
            }
            if (!StringUtils.isEmpty(keyword)) {
                /*Tag tag = listFilterService.selectTagByName(keyword);
                if (tag != null) {
                    params.put("tag", tag.getId());
                    vo = uploadEntityServiceFactory.listData(typeEnum, "params", params, page, pageSize);
                } else {*/
                params.put("keyword1", keyword);
                vo = uploadEntityServiceFactory.listData(typeEnum, "params", params, page, pageSize);
//                }
            } else {
                //没输入标签则查询全部
                vo = uploadEntityServiceFactory.listData(typeEnum, "params", params, page, pageSize);
            }
        }

        model.addAttribute("vo", vo);
        model.addAttribute("type", resType);
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        model.addAttribute("isAdmin", LoginUserHolder.get().isAdmin());
        model.addAttribute("createLoadDataFunc", false);
        return "file/newListContent";
    }


    //更新资源对应机号
    @RequestMapping(value = "video/machinecode/set/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo setVideoMachineCode(@PathVariable Integer id, @RequestParam String machineCode) {
        logger.info("video setMachineCode. id =" + id + ",machineCode=" + machineCode);
        int cnt = videoService.updateMachineCode(id, machineCode);
        return cnt == 1 ? ResponseVo.success() : ResponseVo.fail("数据不存在");
    }

    //更新资源的机号
    @RequestMapping(value = "image/machinecode/set/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo setImageMachineCode(@PathVariable Integer id, @RequestParam String machineCode) {
        logger.info("image setMachineCode. id =" + id + ",machineCode=" + machineCode);
        int cnt = imageService.updateMachineCode(id, machineCode);
        return cnt == 1 ? ResponseVo.success() : ResponseVo.fail("数据不存在");
    }

    //更新资源对应的工地号
    @RequestMapping(value = "video/siteid/set/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo setVideoSiteId(@PathVariable Integer id, @RequestParam String lonlats) {
        logger.info("video setSiteId. id =" + id + ",lonlats=" + lonlats);
        String[] lonLatArray = lonlats.split(";");
        List<Float[]> lonLatList = Arrays.stream(lonLatArray)
                .map(lonLatStr -> {
                    String[] lonLat = lonLatStr.split(",");
                    return new Float[]{Float.parseFloat(lonLat[0]), Float.parseFloat(lonLat[1])};
                }).collect(Collectors.toList());
        int cnt = videoService.updateSiteIdByGeo(id, lonLatList);
        return cnt > 0 ? ResponseVo.success() : ResponseVo.fail("未更新数据");
    }

    //更改资源对应工地号
    @RequestMapping(value = "image/siteid/set/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo setImageSiteId(@PathVariable Integer id, @RequestParam String lonlats) {
        logger.info("image setSiteId. id =" + id + ",lonlats=" + lonlats);
        String[] lonLatArray = lonlats.split(";");
        List<Float[]> lonLatList = Arrays.stream(lonLatArray)
                .map(lonLatStr -> {
                    String[] lonLat = lonLatStr.split(",");
                    return new Float[]{Float.parseFloat(lonLat[0]), Float.parseFloat(lonLat[1])};
                }).collect(Collectors.toList());
        int cnt = imageService.updateSiteIdByGeo(id, lonLatList);
        return cnt > 0 ? ResponseVo.success() : ResponseVo.fail("未更新数据");
    }


}
