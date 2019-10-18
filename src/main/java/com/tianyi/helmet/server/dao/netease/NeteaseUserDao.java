package com.tianyi.helmet.server.dao.netease;

import com.tianyi.helmet.server.entity.client.NeteaseUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 网易用户信息
 */
@Repository
public interface NeteaseUserDao {
    void insert(NeteaseUser user);

    NeteaseUser selectById(int id);

    NeteaseUser selectByUsername(String username);

    String selectMaxUsername(String namePrefix);

    NeteaseUser selectByUserId(String deviceId);

    NeteaseUser selectByYunId(String yunId);

    List<NeteaseUser> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    /**
     * create by xiayuan 2018/10/10
     * @param avprovider
     */
    int countNull(String avprovider);

    /**
     * create by xiayuan 2018/10/10
     * @param avprovider
     */
    String selectNoUser(String avprovider);

    int deleteById(int id);

    int updateById(NeteaseUser user);

}
