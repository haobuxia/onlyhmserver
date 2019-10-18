package com.tianyi.helmet.server.controller.scene;

import com.tianyi.helmet.server.entity.svc.SvcVideo;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 循环机数据上传接口
 * <p>
 * Created by liuhanc on 2018/1/29.
 */
@Controller
@RequestMapping("recirc")
public class RecircDataController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "uploadVideo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<SvcVideo> uploadVideo(@RequestParam String rwh, @RequestParam String videoType, @RequestParam("videofile") MultipartFile file) {

        return null;
    }
}
