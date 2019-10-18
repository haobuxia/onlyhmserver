package com.tianyi.helmet.server.controller.power;

import com.tianyi.helmet.server.entity.power.Menu;
import com.tianyi.helmet.server.entity.power.UserMenu;
import com.tianyi.helmet.server.service.power.MenuService;
import com.tianyi.helmet.server.service.power.UserMenuService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户个人桌面
 *
 * Created by wenxinyan on 2018/10/24.
 */
@RestController
@RequestMapping("usermenu")
public class UserMenuController {
    @Autowired
    private UserMenuService userMenuService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("add")
    public ResponseVo add(UserMenu userMenu) {
        userMenuService.insert(userMenu);

        return ResponseVo.success();
    }

    @RequestMapping("delete")
    public ResponseVo delete(int userId, int menuId) {
        userMenuService.deleteBy(userId, menuId);

        return ResponseVo.success();
    }

    @RequestMapping("list")
    public ResponseVo list(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", id);
        List<UserMenu> userMenus = userMenuService.listByNoPage(map);
        List<Menu> menuList = new ArrayList<>();
        for(UserMenu um : userMenus){
            menuList.add(menuService.selectById(um.getMenuId()));
        }

        return ResponseVo.success(menuList);
    }
}
