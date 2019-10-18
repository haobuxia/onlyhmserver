package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.entity.client.TianyiContact;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.power.Menu;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 用户管理服务
 * Created by wenxinyan on 2018/9/25.
 */
public interface UserService {

    int insert(User user);

    int deleteById(int id);

    int update(User user);

    List<User> listBy(Map<String, Object> map);

    int countBy(Map<String, Object> map);

    User selectById(int id);

    List<User> listByNoPage(Map<String, Object> map);

    List<String> listDept(Map<String, Object> map);

    User selectByAccount(String account);

    User selectByTianyuanAccount(String account);

    boolean checkAccount(String account);

    boolean checkName(String name, int organisation);

    List<Menu> getHelmetMenuByUserId(int id);

    boolean updateNetUser(User user);

    List<User> selectByNoNetUser(Map<String, Object> map);

    List<User> selectByCompany(Map<String, Object> map);
}
