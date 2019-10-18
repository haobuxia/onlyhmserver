package com.tianyi.helmet.server.controller.admin;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.service.support.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  用户个人控制台
 * <p>
 * Created by liuhanc on 2017/11/30.
 */
@Controller
@RequestMapping("console")
public class ConsoleController {
    @Autowired
    private ConfigService configService;

    @RequestMapping({"", "index"})
    public String index(Model model) {
        LoginUserInfo lui = LoginUserHolder.get();
        model.addAttribute("userInfo", lui);
        model.addAttribute("tianYuanMapApiUrl", configService.getTianYuanMapApiUrl());
        model.addAttribute("tianYuanMapApiKey", configService.getTianYuanMapApiKey());
//        if (lui.isDemo()) {//演示账号，进入新版页面
            return "console/newIndex";
//        }
//        return "console/index";
    }

    @RequestMapping("welcome")
    public String welcome(Model model) {
        return "console/welcome";
    }

}
