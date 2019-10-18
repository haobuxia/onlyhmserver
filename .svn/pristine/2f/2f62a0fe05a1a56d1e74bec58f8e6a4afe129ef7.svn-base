package com.tianyi.helmet.server.service.power;

import com.tianyi.helmet.server.entity.power.Menu;
import com.tianyi.helmet.server.entity.power.MenuDTO;

import java.util.List;
import java.util.Map;

/**
 * 功能点信息
 * Created by wenxinyan on 2018/10/10.
 */
public interface MenuService {

    List<Menu> listAll();

    Menu selectById(int id);

    List<Menu> listByNoPage(Map<String, Object> param);

    List<MenuDTO> tree();

    List<MenuDTO> roleTree(String userId);
}
