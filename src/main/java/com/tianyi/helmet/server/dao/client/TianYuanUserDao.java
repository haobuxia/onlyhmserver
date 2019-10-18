package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.TianYuanUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 天远用户信息
 */
@Repository
public interface TianYuanUserDao {
    void insert(TianYuanUser user);

    TianYuanUser selectById(int id);

    TianYuanUser selectByUsername(String username);

    TianYuanUser selectByOprtId(String oprtId);

    List<TianYuanUser> selectByOprtIdList(List<String> oprtIdList);

    TianYuanUser selectByOprtName(String oprtName);

    List<TianYuanUser> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int deleteById(int id);

    int updateById(TianYuanUser user);

}
