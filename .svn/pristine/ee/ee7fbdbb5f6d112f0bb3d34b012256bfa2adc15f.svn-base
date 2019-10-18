package com.tianyi.helmet.server.controller.netease;

import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.service.client.NeteaseUserService;
import com.tianyi.helmet.server.vo.AppAccountVo;
import com.tianyi.helmet.server.vo.PageListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 网易用户管理接口
 *
 * Created by liuhanc on 2017/10/12.
 */
@Controller
@RequestMapping("neteaseuser")
public class NeteaseUserController {
    private Logger logger = LoggerFactory.getLogger(NeteaseUserController.class);

    @Autowired
    private NeteaseUserService neteaseUserService;

    /**
     * 进入列表页面首页
     * @return
     */
    @RequestMapping(value = "list")
    public String list(String keyword, Model model){
        return list(1,keyword,model);
    }


    @RequestMapping("list/{page}")
    public String list(@PathVariable Integer page, String keyword,Model model){
        if(page == null) page = 1;
        Map<String,Object> map = PageListVo.createParamMap(page);
        if(!StringUtils.isEmpty(keyword)){
            map.put("keyword",keyword);
        }
        List<NeteaseUser> list = neteaseUserService.listBy(map);
        int count = neteaseUserService.countBy(map);
        PageListVo<NeteaseUser> vo = new PageListVo(page);
        vo.setList(list);
        vo.setTotal(count);
        model.addAttribute("vo",vo);
        model.addAttribute("keyword",keyword);
        return "user/listNeteaseUser";
    }


    /**
     * 查看密码
     * @return
     */
    @RequestMapping(value = "viewPassword")
    @ResponseBody
    public AppAccountVo viewPassword(Integer id){
        NeteaseUser userInfo = neteaseUserService.selectById(id);
        if(userInfo == null){
            return new AppAccountVo("600","用户不存在");
        }
        AppAccountVo vo = new AppAccountVo("200", userInfo.getPassword());
        return vo;
    }

    /**
     * 修改网易用户(app用户)的密码
     * @return
     */
    @RequestMapping(value = "changePassword")
    @ResponseBody
    public AppAccountVo changePassword(@RequestParam(required = false) Integer id, @RequestParam(required = false) String username, @RequestParam String password){
        if(id == null || StringUtils.isEmpty(username)){
            return new AppAccountVo("601","参数不足");
        }
        NeteaseUser userInfo = null;
        if(id != null)
            userInfo = neteaseUserService.selectById(id);
        else
            userInfo = neteaseUserService.selectByUsername(username);

        if(userInfo == null){
            return new AppAccountVo("600","用户不存在");
        }
        userInfo.setPassword(password);
        neteaseUserService.updateById(userInfo);
        return new AppAccountVo("200","修改密码成功");
    }

}
