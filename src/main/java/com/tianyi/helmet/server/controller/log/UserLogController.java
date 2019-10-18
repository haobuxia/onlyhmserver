package com.tianyi.helmet.server.controller.log;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.service.log.UserLogService;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户日志
 *
 * Created by liuhanc on 2017/12/13.
 */
@Controller
@RequestMapping("userlog")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;

    /**
     * 系统日志
     *
     * @return
     */
    @RequestMapping(value = "list")
    public String list(String keyword, Model model) {
        return list(1, keyword, model);
    }

    /**
     * 系统日志
     * @param page
     * @param keyword
     * @param model
     * @return
     */
    @RequestMapping("list/{page}")
    public String list(@PathVariable Integer page, String keyword, Model model) {
        LoginUserInfo lui = LoginUserHolder.get();
        if(!lui.isAdmin()){
            return "common/403";
        }
        return searchLogList(page,keyword,false,model);
    }

    private String searchLogList(Integer page,String keyword,boolean personLog,Model model){
        PageListVo vo = userLogService.queryList(page,keyword,personLog);
        model.addAttribute("vo", vo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("personLog", personLog);
        return "log/listUserLog";
    }
}
