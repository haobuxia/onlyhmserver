package com.tianyi.helmet.server.controller.power;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.power.Menu;
import com.tianyi.helmet.server.entity.power.RoleMenu;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.power.MenuService;
import com.tianyi.helmet.server.service.power.RoleMenuService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxinyan on 2018/10/10.
 */
@Controller
@ResponseBody
@RequestMapping("rolemenu")
public class RoleMenuController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("list")
    public ResponseVo list(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", id);

        return ResponseVo.success(roleMenuService.listByNoPage(map));
    }

    @RequestMapping("menulist")
    public ResponseVo menuList(int id, boolean isUserId) {
        List<Map> menu1 = new ArrayList<>();  //存储一级菜单列表
        List<Map> menu2 = new ArrayList<>();  //存储二级菜单列表
        List<Map> menu3 = new ArrayList<>();  //存储三级菜单列表

        String[] roleCodes = isUserId ? userService.selectById(id).getRoleCodes().split(",") : new String[]{id + ""};
        for(int i = 0; i < roleCodes.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", roleCodes[i]);
            List<RoleMenu> roleMenus = this.getWebMenu(roleMenuService.listByNoPage(map), isUserId);
            if(roleMenus != null){
                for(RoleMenu roleMenu : roleMenus){
                    Menu m1 = menuService.selectById(roleMenu.getMenuId());
                    if(m1.getFatherId() != 0){
                        Menu m2 = menuService.selectById(m1.getFatherId());
                        if(m2.getFatherId() != 0){  //有两个父节点是三级菜单
                            Menu m3 = menuService.selectById(m2.getFatherId());
                            menu1 = this.addMenu(menu1, m3);
                            menu2 = this.addMenu(menu2, m2);
                            menu3 = this.addMenu(menu3, m1);
                        } else {  //有一个父节点是二级菜单
                            menu1 = this.addMenu(menu1, m2);
                            menu2 = this.addMenu(menu2, m1);
                        }
                    } else {  //没有父节点是一级菜单
                        menu1 = this.addMenu(menu1, m1);
                    }
                }
            }
        }

        Map<String, List<Map>> result = new HashMap<>();
        result.put("menu1",menu1);
        result.put("menu2",menu2);
        result.put("menu3",menu3);

        return ResponseVo.success(result);
    }

    private List<Map> addMenu(List<Map> menuList, Menu menu) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", menu.getId());
        map.put("name", menu.getMenuName());
        map.put("fatherId", menu.getFatherId());
        map.put("url", menu.getMenuPage());
        map.put("image", menu.getMenuImage());
        map.put("menuType", menu.getMenuType());

        if(!menuList.contains(map)){
            menuList.add(map);
        }

        return menuList;
    }

    private List<RoleMenu> getWebMenu(List<RoleMenu> roleMenus, boolean isUserId) {
        List<RoleMenu> result = new ArrayList<>();

        for(RoleMenu rm : roleMenus){
            if(isUserId){
                if(menuService.selectById(rm.getMenuId()).getMenuType() == 0){
                    result.add(rm);
                }
            } else {
                result.add(rm);
            }
        }

        return result;
    }
}
