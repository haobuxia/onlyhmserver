package com.tianyi.helmet.server.service.power.impl;

import com.tianyi.helmet.server.dao.power.RoleMenuDao;
import com.tianyi.helmet.server.entity.power.RoleMenu;
import com.tianyi.helmet.server.service.power.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色功能权限信息
 * Created by wenxinyan on 2018/10/10.
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuDao roleMenuDao;

    public int insert(RoleMenu roleMenu) {
        return roleMenuDao.insert(roleMenu);
    }

    public int deleteById(int id){
        return roleMenuDao.deleteById(id);
    }

    public List<RoleMenu> listAll(){
        return roleMenuDao.listAll();
    }

    public List<RoleMenu> listByNoPage(Map<String, Object> param){
        return roleMenuDao.listByNoPage(param);
    }
}
