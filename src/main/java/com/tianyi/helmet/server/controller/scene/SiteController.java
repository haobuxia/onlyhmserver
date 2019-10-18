package com.tianyi.helmet.server.controller.scene;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.entity.scene.Site;
import com.tianyi.helmet.server.service.file.ImageService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.scene.SiteService;
import com.tianyi.helmet.server.vo.GeoDataVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 *  工地接口
 *
 * Created by liuhanc on 2018/1/17.
 */
@Controller
@RequestMapping("site")
public class SiteController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SiteService siteService;

    private Logger logger = LoggerFactory.getLogger(SiteController.class);

    //获得工地上的视频和定位数据
    //以定位为key，以GeoDataVo的列表为value的map数据，表示某个定位位置的多个视频及标签名数据
    @RequestMapping("/videoGeoData")
    @ResponseBody
    public ResponseVo loadVideoGeoData() {
        Map<String, List<GeoDataVo>> mapListData = videoService.selectTagGeoMapData();
        return ResponseVo.success(mapListData);
    }

    @RequestMapping("/index")
    public String index(Model model) {
//        List<TripleVo<Float, Float, Integer>> videoCounts = videoService.selectGeoCount();
//        List<TripleVo<Float, Float, Integer>> imageCounts = imageService.selectGeoCount();
        //列出所有工地
        Map<Integer, Site> siteMap = siteService.listBy(null).stream().collect(Collectors.toMap(site -> site.getId(), site -> site));
//
//        model.addAttribute("videoCounts", JSON.toJSONString(videoCounts));
//        model.addAttribute("imageCounts", JSON.toJSONString(imageCounts));
        model.addAttribute("siteMap", JSON.toJSONString(siteMap));
        return "scene/siteIndex";
    }

    @RequestMapping("/saveSite")
    @ResponseBody
    public ResponseVo saveSite(Site site) {
        int id = site.getId();
        if (id == 0) {
            //insert
            logger.debug("新增工地信息."+site.getName()+",video定位:"+site.getSiteVideoLonLats());
            siteService.insert(site);
            return ResponseVo.success(site.getId());
        } else {
            //update
            logger.debug("更新工地信息."+site.getName()+",video定位:"+site.getSiteVideoLonLats());
            Site oldSite = siteService.selectById(id);
            if (oldSite == null) {
                return ResponseVo.fail("工地不存在");
            }
            siteService.updateById(site);
            return ResponseVo.success(site.getId());
        }
    }

    @RequestMapping("/deleteSite/{siteId}")
    @ResponseBody
    public ResponseVo deleteSite(@PathVariable Integer siteId) {
        Site oldSite = siteService.selectById(siteId);
        if (oldSite == null) {
            return ResponseVo.fail("工地不存在");
        }
        siteService.deleteById(siteId);
        return ResponseVo.success();
    }

}
