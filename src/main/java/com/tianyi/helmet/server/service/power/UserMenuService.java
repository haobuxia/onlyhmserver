package com.tianyi.helmet.server.service.power;

import com.tianyi.helmet.server.entity.power.UserMenu;

import java.util.List;
import java.util.Map;

/**
 * 用户个人桌面
 *
 * Created by wenxinyan on 2018/10/24.
 */
public interface UserMenuService {

    int insert(UserMenu userMenu);

    int deleteBy(int userId, int menuId);

    List<UserMenu> listByNoPage(Map<String, Object> param);

}
