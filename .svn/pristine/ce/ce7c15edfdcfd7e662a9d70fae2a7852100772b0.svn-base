package com.tianyi.helmet.server.service.power.impl;

import com.tianyi.helmet.server.dao.power.MenuDao;
import com.tianyi.helmet.server.entity.power.Menu;
import com.tianyi.helmet.server.entity.power.MenuDTO;
import com.tianyi.helmet.server.service.power.MenuService;
import com.tianyi.helmet.server.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能点信息
 * Created by wenxinyan on 2018/10/10.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    public List<Menu> listAll(){
        return menuDao.listAll();
    }

    public Menu selectById(int id){
        return menuDao.selectById(id);
    }

    public List<Menu> listByNoPage(Map<String, Object> param){
        return menuDao.listByNoPage(param);
    }

    @Override
    public List<MenuDTO> tree() {
        Map<String, Object> param = new HashMap<>();
        List<MenuDTO> menus = menuDao.treeList(param);

        return TreeUtil.buildByRecursive(menus, 0);
    }

    @Override
    public List<MenuDTO> roleTree(String userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        List<MenuDTO> menus = menuDao.roleTreeList(param);
        List<MenuDTO> tmpmenus = new ArrayList<>();
        Map<String, Object> tmpparam = new HashMap<>();
        for(MenuDTO mdto : menus) {
            if(mdto.getFatherId() != 0){
                if(!find(mdto.getFatherId(),menus,tmpmenus)) {
                    tmpparam.put("id", mdto.getFatherId());
                    List<MenuDTO> tempm = menuDao.treeList(tmpparam);
                    tmpmenus.add(tempm.get(0));
                }
            }
        }
        menus.addAll(tmpmenus);
        return TreeUtil.buildByRecursive(menus, 0);
    }

    private boolean find(int fatherId, List<MenuDTO> menus, List<MenuDTO> tmpmenus) {
        for(MenuDTO mdto : menus) {
            if(fatherId == mdto.getId()) {
                return true;
            }
        }
        for(MenuDTO mdto : tmpmenus) {
            if(fatherId == mdto.getId()) {
                return true;
            }
        }
        return false;
    }
}
