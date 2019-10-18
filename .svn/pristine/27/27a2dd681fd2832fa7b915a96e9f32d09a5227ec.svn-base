package com.tianyi.helmet.server.controller.power;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.power.Api;
import com.tianyi.helmet.server.entity.power.Role;
import com.tianyi.helmet.server.entity.power.RoleApi;
import com.tianyi.helmet.server.entity.power.RoleMenu;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.power.*;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 角色信息
 * Created by wenxinyan on 2018/10/9.
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleApiService roleApiService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMenuService userMenuService;

    @RequestMapping("welcome")
    public String welcome() {
        return "power/roleManage";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResponseVo add(Role role) {
        roleService.insert(role);

        return ResponseVo.success();
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResponseVo delete(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", id);
        List<RoleMenu> roleMenus = roleMenuService.listByNoPage(map);
        List<RoleApi> roleApis = roleApiService.listByNoPage(map);

        for(RoleMenu rm : roleMenus){
            roleMenuService.deleteById(rm.getId());
        }

        for(RoleApi ra : roleApis){
            roleApiService.deleteById(ra);
        }

        this.updateUser(id + "");

        roleService.deleteById(id);

        return ResponseVo.success();
    }

    @RequestMapping("update")
    @ResponseBody
    public ResponseVo update(int id, String roleName, String menus, String apis, Integer companyId, Integer universalId) {
        Role role = new Role();
        role.setId(id);
        role.setRoleName(roleName);
        role.setCompanyId(companyId);
        role.setUniversalId(universalId);
        roleService.update(role);

        this.updateMenu(id, menus);

        this.updateApi(id, menus);

        return ResponseVo.success();
    }

    @RequestMapping("list")
    @ResponseBody
    public ResponseVo list(@RequestParam Integer page, String roleName, Integer companyId) {
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("roleName",roleName);
        map.put("companyId",companyId);
        List<Role> roleList = roleService.listBy(map);
        int count = roleService.countBy(map);
        PageListVo vo = new PageListVo(page);
        vo.setList(roleList);
        vo.setTotal(count);

        return ResponseVo.success(vo);
    }

    @RequestMapping("listall")
    @ResponseBody
    public ResponseVo list() {
        return ResponseVo.success(roleService.listAll());
    }

    private void updateUser(String roleCodes) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleCodes", roleCodes);
        List<User> users = userService.listByNoPage(map);
        for(User u : users){
            if(u.getRoleCodes().equals(roleCodes)){
                u.setRoleCodes("4");
            }

            if(u.getRoleCodes().startsWith(roleCodes+",")){
                u.setRoleCodes(u.getRoleCodes().replace(roleCodes+",", ""));
            }

            if(u.getRoleCodes().endsWith(","+roleCodes)){
                u.setRoleCodes(u.getRoleCodes().replace(","+roleCodes, ""));
            }

            if(u.getRoleCodes().contains(","+roleCodes+",")){
                u.setRoleCodes(u.getRoleCodes().replace(roleCodes+",", ""));
            }

            userService.update(u);
        }
    }

    private void updateMenu(int roleId, String menus) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        List<RoleMenu> oldList = roleMenuService.listByNoPage(map);
        List<String> addMenu = new ArrayList<>();

        if(menus != null && menus != ""){
             List<String> newList = Arrays.asList(menus.split(","));

             for(RoleMenu rm : oldList){
                 addMenu.add(rm.getMenuId() + "");
                 if(!newList.contains(rm.getMenuId() + "")){
                     roleMenuService.deleteById(rm.getId());
                     this.updateUserMenu(roleId + "", rm.getMenuId());
                 }
             }

             for(String str : newList){
                 if(!addMenu.contains(str)){
                     RoleMenu roleMenu = new RoleMenu();
                     roleMenu.setRoleId(roleId);
                     roleMenu.setMenuId(Integer.parseInt(str));
                     roleMenuService.insert(roleMenu);
                 }
             }
        } else {
            for(RoleMenu rm : oldList){
                roleMenuService.deleteById(rm.getId());
                this.updateUserMenu(roleId + "", rm.getMenuId());
            }
        }
    }

    private void updateApi(int roleId, String menus) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        List<RoleApi> oldList = roleApiService.listByNoPage(map);
        List<String> addApi = new ArrayList<>();

        if(menus != null && menus != ""){
            List<String> menuList = Arrays.asList(menus.split(","));
            List<String> newList = new ArrayList<>();
            for(String str : menuList){
                Map<String, Object> apiMap = new HashMap<>();
                apiMap.put("menuId", Integer.parseInt(str));
                List<Api> apis = apiService.listByNoPage(apiMap);
                for(Api a : apis){
                    newList.add(a.getId() + "");
                }
            }

            for(RoleApi ra : oldList){
                addApi.add(ra.getApiId() + "");
                if(!newList.contains(ra.getApiId() + "")){
                    roleApiService.deleteById(ra);
                }
            }

            for(String str : newList){
                if(!addApi.contains(str)){
                    RoleApi roleApi = new RoleApi();
                    roleApi.setRoleId(roleId);
                    roleApi.setApiId(Integer.parseInt(str));
                    roleApiService.insert(roleApi);
                }
            }
        } else {
            for(RoleApi ra : oldList){
                roleApiService.deleteById(ra);
            }
        }
    }

    private void updateUserMenu(String roleCodes, int menuId) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleCodes", roleCodes);
        List<User> userList = new ArrayList<>();

        for(User u : userService.listByNoPage(map)){
            if(u.getRoleCodes().equals(roleCodes) || u.getRoleCodes().startsWith(roleCodes+",") || u.getRoleCodes().endsWith(","+roleCodes) || u.getRoleCodes().contains(","+roleCodes+",")){
                userList.add(u);
            }
        }

        if(userList.size()>0){
            for(User user : userList){
                userMenuService.deleteBy(user.getId(), menuId);
            }
        }
    }
}
