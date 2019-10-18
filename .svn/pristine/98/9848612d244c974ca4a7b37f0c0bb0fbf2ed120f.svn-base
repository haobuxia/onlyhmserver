package com.tianyi.helmet.server.controller.file;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.entity.file.UploadEntity;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.entity.scene.Site;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.*;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.TripleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 各种资源列表查询
 * <p>
 * <p>
 * Created by liuhanc on 2017/11/21.
 * <p>
 * wenxinyan 2018-8-16 注释掉了原来的操作日志入口listhelmet_logIndex()
 */
@Controller
@RequestMapping("list")
public class ListController {
    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private VideoService videoService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private TagResourceService tagResourceService;
    @Autowired
    private VideoKeywordService videoKeywordService;
    @Autowired
    private TagGroupService tagGroupService;
    @Autowired
    private ListFilterService listFilterService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    private int pageSize = 8;

    //某个类型资源
    @RequestMapping("/{type}/searchlist")
    public String searchlistByTypePage(@PathVariable String type, Model model) {
        return searchlistByTypePage(type, 1, model);
    }

    //某个类型资源-按时间排序直接查看时
    @RequestMapping("/{type}/searchlist/{page}")
    public String searchlistByTypePage(@PathVariable String type, @PathVariable Integer page, Model model) {
        return listByType(type, null, null, page, model);//进入查询和数据页面
    }

    //某个类型资源-具体头盔设备的全局页面中
    @RequestMapping("/{type}/list/{page}")
    public String listByTypePage(@PathVariable String type, @PathVariable Integer page, Model model) {
        return listByType(type, null, null, page, model);
    }

    //某个类型某个头盔imei拍摄的资源
    @RequestMapping("/{type}/list/{clientId}/{page}")
    public String listByTypePageClientId(@PathVariable String type, @PathVariable String clientId, @PathVariable Integer page, Model model) {
        String webpage = listByType(type, "clientid", clientId, page, model);
        model.addAttribute("isAdmin", false);//不止执行管理员操作
        model.addAttribute("createLoadDataFunc", false);
        return webpage;
    }

    //某个类型某个头盔IMEI资源
    @RequestMapping("/{type}/searchlist/clientid/{clientId}/{page}")
    public String searchlistByTypePageClientId(@PathVariable String type, @PathVariable String clientId, @PathVariable Integer page, Model model) {
        return listByType(type, "clientid", clientId, page, model);
    }

    //某个类型某个头盔账号资源
    @RequestMapping("/{type}/searchlist/neusername/{neUsername}/{page}")
    public String searchlistByTypePageNeUsername(@PathVariable String type, @PathVariable String neUsername, @PathVariable Integer page, Model model) {
        /**
         * update by xiayuan 2018/10/12
         */
        return listByType(type, "neUserName", neUsername, page, model);
    }

    //某个类型某个tybox imei 资源
    @RequestMapping("/{type}/searchlist/imei/{tyboxImei}/{page}")
    public String searchlistByTypePageImei(@PathVariable String type, @PathVariable String tyboxImei, @PathVariable Integer page, Model model) {
        return listByType(type, "imei", tyboxImei, page, model);
    }

    //某个类型某个机号资源
    @RequestMapping("/{type}/searchlist/machinecode/{machineCode}/{page}")
    public String searchlistByTypePageMachineCode(@PathVariable String type, @PathVariable String machineCode, @PathVariable Integer page, Model model) {
        return listByType(type, "machinecode", machineCode, page, model);
    }

    //某个类型某个标签资源
    @RequestMapping("/{type}/searchlist/tag/{tagId}/{page}")
    public String searchlistByTypePageTag(@PathVariable String type, @PathVariable Integer tagId, @PathVariable Integer page, Model model) {
        return listByType(type, "tag", String.valueOf(tagId), page, model);
    }

    //某个类型某个工地资源
    @RequestMapping("/{type}/searchlist/siteid/{siteId}/{page}")
    public String searchlistByTypePageSiteId(@PathVariable String type, @PathVariable Integer siteId, @PathVariable Integer page, Model model) {
        return listByType(type, "siteid", String.valueOf(siteId), page, model);
    }

    //视频查看首页
    @RequestMapping("/video")
    public String listVideoIndex(Model model) {
        return "file/videoListTab";
    }

//    //操作日志首页
//    @RequestMapping("/helmet_log")
//    public String listhelmet_logIndex(Model model) { return "file/helmet_log"; }

    /**
     * 按头盔imei分组查看视频
     *
     * @param model
     * @return
     */
    @RequestMapping("/video/clientid")
    public String listVideoByClientId(Model model) {
        return listVideoByClientId(1, model);
    }

    //按头盔id分组查看视频
    @RequestMapping("/video/clientid/{page}")
    public String listVideoByClientId(@PathVariable Integer page, Model model) {
        PageListVo<TripleVo<String, Integer, String>> vo = videoService.groupByHelmetImei(page, pageSize);
        return toGroupVideoList(vo, "clientid", model);
    }

    /**
     * 按头盔账号分组查看视频
     *
     * @param model
     * @return
     */
    @RequestMapping("/video/neusername")
    public String listVideoByNeUsername(Model model) {
        return listVideoByNeUsername(1, model);
    }

    //按头盔账号分组查看视频
    @RequestMapping("/video/neusername/{page}")
    public String listVideoByNeUsername(@PathVariable Integer page, Model model) {
        PageListVo<TripleVo<String, Integer, String>> vo = videoService.groupByHelmetAccount(page, pageSize);
        return toGroupVideoList(vo, "neusername", model);
    }

    /**
     * 按车辆盒子imei分组视频
     *
     * @param model
     * @return
     */
    @RequestMapping("/video/imei")
    public String listVideoByTyBoxImei(Model model) {
        return listVideoByTyBoxImei(1, model);
    }

    //按车辆TyBox imei分组视频
    @RequestMapping("/video/imei/{page}")
    public String listVideoByTyBoxImei(@PathVariable Integer page, Model model) {
        PageListVo<TripleVo<String, Integer, String>> vo = videoService.groupByImei(page, pageSize);
        return toGroupVideoList(vo, "imei", model);
    }

    /**
     * 按机号分组视频
     *
     * @param model
     * @return
     */
    @RequestMapping("/video/machinecode")
    public String listVideoByMachineCode(Model model) {
        return listVideoByMachineCode(1, model);
    }

    // 按机号分组视频
    @RequestMapping("/video/machinecode/{page}")
    public String listVideoByMachineCode(@PathVariable Integer page, Model model) {
        PageListVo<TripleVo<String, Integer, String>> vo = videoService.groupByMachineCode(page, pageSize);
        return toGroupVideoList(vo, "machinecode", model);
    }

    /**
     * 服务数据类型的视频查看
     * 根据视频标签分组统计、根据视频关键词分组统计
     *
     * @param model
     * @return
     */
    @RequestMapping("/video/svcdata")
    public String listVideoBySvcData(Model model) {
        Integer groupId = tagGroupService.getSvcDataGroupId();
        //标签id，资源数、标签名
        PageListVo<TripleVo<Integer, Integer, String>> tagVo = tagResourceService.groupByTag(UploadEntityTypeEnum.video, groupId, 1, Integer.MAX_VALUE);

        List<TripleVo<Integer, Integer, String>> tagTripleList = tagVo.getList();
        Map<Integer, Integer> tagIdResCountMap = tagTripleList.stream().collect(Collectors.toMap(TripleVo::getOne, TripleVo::getTwo));

        //显示服务数据的所有标签
        List<Tag> tagList = listFilterService.selectTagListByGroupId(groupId);
        tagTripleList = tagList.stream().map(tag -> {
            return new TripleVo<>(tag.getId(), tagIdResCountMap.getOrDefault(tag.getId(), 0), tag.getTagName());
        }).collect(Collectors.toList());


        PageListVo<TripleVo<Integer, Integer, String>> keywordVo = videoKeywordService.groupByKeyword(1, 30);

        model.addAttribute("tagCountList", tagTripleList);
        model.addAttribute("keywordCountList", keywordVo.getList());

        return "file/groupSvcData";
    }

    /**
     * 按标签分组视频
     *
     * @param model
     * @return
     */
    @RequestMapping("/video/tag")
    public String listVideoByTag(Model model) {
        return listVideoByTag(1, model);
    }

    // 按标签分组视频
    @RequestMapping("/video/tag/{page}")
    public String listVideoByTag(@PathVariable Integer page, Model model) {
        //标签id，资源数、标签名
        PageListVo<TripleVo<Integer, Integer, String>> vo = tagResourceService.groupByTag(UploadEntityTypeEnum.video, null, page, pageSize);
        return toGroupVideoList(vo, "tag", model);
    }

    /**
     * 按工地分组视频
     *
     * @param model
     * @return
     */
    @RequestMapping("/video/siteid")
    public String listVideoBySiteId(Model model) {
        return listVideoBySiteId(1, model);
    }

    // 按工地分组视频
    @RequestMapping("/video/siteid/{page}")
    public String listVideoBySiteId(@PathVariable Integer page, Model model) {
        PageListVo<TripleVo<String, Integer, Site>> vo = videoService.groupBySiteId(page, pageSize);
        return toGroupVideoList(vo, "siteid", model);
    }

    /**
     * 根据某个参数查询数据列表.
     *
     * @param type
     * @param searchKey
     * @param searchVal
     * @param page
     * @param model
     * @return
     */
    protected String listByType(String type, String searchKey, String searchVal, Integer page, Model model) {
        if (page == null || page <= 0) page = 1;
        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.valueOf(type);
        PageListVo<UploadEntity> vo = uploadEntityServiceFactory.listData(typeEnum, searchKey, searchVal, page, pageSize);
        if (!StringUtils.isEmpty(searchKey)) {
            model.addAttribute(searchKey, searchVal);
        }

        model.addAttribute("type", type);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("searchVal", searchVal);
        model.addAttribute("vo", vo);
        model.addAttribute("isAdmin", LoginUserHolder.get().isAdmin());
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        model.addAttribute("createLoadDataFunc", true);
//        return "file/listFile";
        return "file/newListContent";
    }

    protected String toGroupVideoList(PageListVo vo, String groupBy, Model model) {
        model.addAttribute("vo", vo);
        model.addAttribute("groupBy", groupBy);
        String placeHolder = "", btnTitle = "";
        if ("clientid".equalsIgnoreCase(groupBy)) {
            placeHolder = "请输入头盔设备IMEI";
            btnTitle = "根据头盔设备IMEI搜索";
        }
        if ("neusername".equalsIgnoreCase(groupBy)) {
            placeHolder = "请输入头盔账号";
            btnTitle = "根据头盔账号搜索";
        } else if ("imei".equalsIgnoreCase(groupBy)) {
            placeHolder = "请输入车载盒子IMEI";
            btnTitle = "根据车载盒子IMEI搜索";
        } else if ("machinecode".equalsIgnoreCase(groupBy)) {
            placeHolder = "请输入机号";
            btnTitle = "根据机号搜索";
        } else if ("tag".equalsIgnoreCase(groupBy)) {
            placeHolder = "请输入标签名";
            btnTitle = "根据标签搜索";
        } else if ("siteid".equalsIgnoreCase(groupBy)) {
            placeHolder = "请输入工地名称";
            btnTitle = "根据工地搜索";
        }
        model.addAttribute("searchPlaceHolder", placeHolder);
        model.addAttribute("searchBtnTitle", btnTitle);
        return "file/groupVideoList";
    }
}
