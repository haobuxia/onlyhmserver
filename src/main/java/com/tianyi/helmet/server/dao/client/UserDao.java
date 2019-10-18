package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.User;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 * Created by wenxinyan on 2018/9/25.
 */
@Repository
public interface UserDao {

    int insert(User user);

    int deleteById(int id);

    int update(User user);

    List<User> listBy(Map<String, Object> param);

    int countBy(Map<String, Object> param);

    User selectById(int id);

    List<User> listByNoPage(Map<String, Object> param);

    List<String> listDept(Map<String, Object> param);

    User selectByAccount(String account);

    User selectByTianyuanAccount(String account);

    List<User> selectByNoNetUser(Map<String, Object> map);

    List<User> selectByCompany(Map<String, Object> map);

    List<User> selectUsersByUid(int uid);
}
