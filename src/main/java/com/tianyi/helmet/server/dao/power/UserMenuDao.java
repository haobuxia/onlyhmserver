package com.tianyi.helmet.server.dao.power;

import com.tianyi.helmet.server.entity.power.UserMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户个人桌面
 *
 * Created by wenxinyan on 2018/10/24.
 */
@Repository
public interface UserMenuDao {

    int insert(UserMenu userMenu);

    int deleteBy(Map<String, Object> param);

    List<UserMenu> listByNoPage(Map<String, Object> param);

}
