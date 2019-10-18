package com.tianyi.helmet.server.service.power.impl;

import com.tianyi.helmet.server.dao.power.UserMenuDao;
import com.tianyi.helmet.server.entity.power.UserMenu;
import com.tianyi.helmet.server.service.power.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户个人桌面
 *
 * Created by wenxinyan on 2018/10/24.
 */
@Service
public class UserMenuServiceImpl implements UserMenuService {
    @Autowired
    private UserMenuDao userMenuDao;

    public int insert(UserMenu userMenu) {
        return userMenuDao.insert(userMenu);
    }

    public int deleteBy(int userId, int menuId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("menuId", menuId);

        return userMenuDao.deleteBy(map);
    }

    public List<UserMenu> listByNoPage(Map<String, Object> param) {
        return userMenuDao.listByNoPage(param);
    }
}
