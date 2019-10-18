package com.tianyi.helmet.server.controller.scene;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.file.Image;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.scene.RecirculateReport;
import com.tianyi.helmet.server.service.file.ImageService;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 循环机接口
 * <p>
 * Created by liuhanc on 2018/1/29.
 */
@Controller
@RequestMapping("recirculate")
public class RecirculateController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ConfigService configService;


    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping("index")
    public String index(Model model) {
        return "scene/recirc/recircIndex";
    }

    //列表查询
    @RequestMapping("videolist/{page}")
    public String videoList(@PathVariable Integer page, @RequestParam(required = false) String machineCode, @RequestParam(required = false) Date date1, @RequestParam(required = false) Date date2, Model model) {
        if (page == null || page <= 0) page = 1;
        PageListVo<Video> vo = videoService.listRecircVideoList(page, 8, machineCode, date1, date2);
        model.addAttribute("vo", vo);
        model.addAttribute("type", "video");
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        model.addAttribute("isAdmin", LoginUserHolder.get().isAdmin());
        model.addAttribute("createLoadDataFunc", true);
        return "file/newListContent";
    }

    //列表查询
    @RequestMapping("imagelist/{page}")
    public String imageList(@PathVariable Integer page, @RequestParam(required = false) String machineCode, @RequestParam(required = false) Date date1, @RequestParam(required = false) Date date2, Model model) {
        if (page == null || page <= 0) page = 1;
        PageListVo<Image> vo = imageService.listRecircImageList(page, 8, machineCode, date1, date2);
        model.addAttribute("vo", vo);
        model.addAttribute("type", "image");
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        model.addAttribute("isAdmin", LoginUserHolder.get().isAdmin());
        model.addAttribute("createLoadDataFunc", true);
        return "file/newListContent";
    }

    @RequestMapping("report/{videoId}")
    public String report(@PathVariable Integer videoId, Model model) {
        Video video = videoService.selectById(videoId);
        if (video == null || video.getIntHasGpsData() != 1) {
            return "redirect:/common/403";
        }

//        String imei = video.getImei();
//        VclInfo vclInfo = imeiService.getVclInfoByImei(imei);
        RecirculateReport report = new RecirculateReport();
        model.addAttribute("report", JSON.toJSONString(report));
//        model.addAttribute("vclInfo", JSON.toJSONString(vclInfo));
        return "scene/recirc/recirculateReport";
    }
}
