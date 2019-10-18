package com.tianyi.helmet.server.controller.log;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.log.HelmetLog;
import com.tianyi.helmet.server.entity.log.UserLog;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.log.HelmetLogService;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 *  头盔日志
 *
 * Created by liuhanc on 2017/12/13.
 */
@Controller
@RequestMapping("helmetlog")
public class HelmetLogController {

    @Autowired
    private HelmetLogService helmetLogService;
    @Autowired
    private UserService userService;

    /**
     * 进入列表页面首页
     *
     * @return
     */
    @RequestMapping(value = "list")
    public String list(String keyword, Model model) {
        return list(1, keyword, model);
    }

    @RequestMapping("list/{page}")
    public String list(@PathVariable Integer page, String keyword, Model model) {
        LoginUserInfo lui = LoginUserHolder.get();
        if(!lui.isAdmin()){
            return "common/403";
        }

        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("keyword", keyword);
        List<HelmetLog> logList = helmetLogService.listBy(map);
        for(HelmetLog helmetLog : logList){
            helmetLog.setLogUserName(userService.selectById(helmetLog.getLogUserId()).getName());
        }
        int count = helmetLogService.countBy(map);
        PageListVo vo = new PageListVo(page);
        vo.setList(logList);
        vo.setTotal(count);
        model.addAttribute("vo", vo);
        model.addAttribute("keyword", keyword);
        return "log/listHelmetLog";
    }
}
