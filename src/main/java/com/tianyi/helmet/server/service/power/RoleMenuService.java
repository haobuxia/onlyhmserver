package com.tianyi.helmet.server.service.power;

import com.tianyi.helmet.server.entity.power.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * 角色功能权限信息
 * Created by wenxinyan on 2018/10/10.
 */
public interface RoleMenuService {

    int insert(RoleMenu roleMenu);

    int deleteById(int id);

    List<RoleMenu> listAll();

    List<RoleMenu> listByNoPage(Map<String, Object> param);

}
