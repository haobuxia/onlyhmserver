package com.tianyi.helmet.server.controller.power;

import com.tianyi.helmet.server.service.power.MenuService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenxinyan on 2018/10/10.
 */
@Controller
@ResponseBody
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("list")
    public ResponseVo list() {
        return ResponseVo.success(menuService.listAll());
    }

    @RequestMapping("tree")
    public ResponseVo tree() {
        return ResponseVo.success(menuService.tree());
    }

    @RequestMapping("roletree")
    public ResponseVo roleTree(@RequestParam String userId) {
        return ResponseVo.success(menuService.roleTree(userId));
    }
}
