package com.tianyi.helmet.server.controller.scene;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.scene.VideoExcelDataPo;
import com.tianyi.helmet.server.service.scene.VideoExcelReader;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 视频excel
 * 根据上传的excel配置来查找视频和对应工况
 *
 * Created by liuhanc on 2018/7/25.
 *
 * wenxinyan 2018-8-16 修改了 upload()
 */
@Controller
@RequestMapping("videoexcel")
public class VideoExcelController {
    private Logger logger = LoggerFactory.getLogger(VideoExcelController.class);

    @Autowired
    private VideoExcelReader videoExcelReader;
    @Autowired
    private RedisMqPublisher redisMqPublisher;

    @RequestMapping(value = "index")
    public String index(Model model){
        return "scene/tsinghua-video/videoExcelUpload";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(@RequestParam("excel") MultipartFile file,Model model) {
        //修改file.getName()为file.getOriginalFilename()才是获取正确的文件名
        //2018-08-16 wxy
        String name = file.getOriginalFilename().toLowerCase();
        if(!name.endsWith(".xls") && !name.endsWith(".xlsx")){
            model.addAttribute("msg","必须是excel文件");
            return "scene/tsinghua-video/uploadResult";
        }

        List<VideoExcelDataPo> list = null;
        try {
            byte[] bytes = file.getBytes();
            list = videoExcelReader.readExcel(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            logger.error("接收上传excel时发生IO异常", e);
            model.addAttribute("msg","接收上传excel时发生IO异常");
            return "scene/tsinghua-video/uploadResult";
        } catch (RuntimeException e) {
            logger.error("解析上传excel时发生异常",e);
            model.addAttribute("msg","解析上传excel时发生异常:" + e.getMessage());
            return "scene/tsinghua-video/uploadResult";
        }

        if(list.size() == 0){
            model.addAttribute("msg","解析上传excel后没有发现要处理的任务数据");
            return "scene/tsinghua-video/uploadResult";
        }

        //任务加入异步队列处理 TsingHuaVideoProcessJob 类负责处理队列
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_Tsinghua_Video, JSON.toJSONString(list));//加入视频处理队列

        model.addAttribute("msg","接收成功，" + list.size() + "个任务处理已加入队列，请稍候到服务器相应目录查看处理结果");
        return "scene/tsinghua-video/uploadResult";
    }
}
