package com.tianyi.helmet.server.controller.helmetinterface;

import com.tianyi.helmet.server.service.scene.WhiteBoardComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 协作
 *
 * Created by liuhanc on 2018/6/19.
 */
@Controller
@RequestMapping("/collaborate")
public class CollaborateController {
    private Logger logger = LoggerFactory.getLogger(CollaborateController.class);

    @Autowired
    private WhiteBoardComponent whiteBoardComponent;

    //获取某个头盔最近1次上传的白板图片
    @RequestMapping(value = "whiteboard/image/{neUsername}")
    public void writeWhiteBoardImage(@PathVariable String neUsername, HttpServletResponse resp) {
        whiteBoardComponent.writeWhiteBoardImage(neUsername, resp);
    }
}
