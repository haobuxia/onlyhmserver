package com.tianyi.helmet.server.service.power.impl;

import com.tianyi.helmet.server.dao.power.RoleDao;
import com.tianyi.helmet.server.entity.power.Role;
import com.tianyi.helmet.server.service.power.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色信息
 * Created by wenxinyan on 2018/10/9.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public int insert(Role role) {
        return roleDao.insert(role);
    }

    public int deleteById(int id) {
        return roleDao.deleteById(id);
    }

    public int update(Role role) {
        return roleDao.update(role);
    }

    public List<Role> listBy(Map<String, Object> param) {
        return roleDao.listBy(param);
    }

    public int countBy(Map<String, Object> param) {
        return roleDao.countBy(param);
    }

    public Role selectById(int id) {
        return roleDao.selectById(id);
    }

    public List<Role> listAll() {
        return roleDao.listAll();
    }
}
