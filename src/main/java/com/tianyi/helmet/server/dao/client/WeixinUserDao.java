package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.WeixinUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 微信用户信息
 *
 */
@Repository
public interface WeixinUserDao {
	void insert(WeixinUser user);

	WeixinUser selectById(int id);

	WeixinUser selectByWxId(String wxId);

	WeixinUser selectByTianyiUserId(int tianyiUserId);

	List<WeixinUser> listBy(Map<String, Object> params);

	int countBy(Map<String, Object> params);

	int deleteById(int id);

	int updateById(WeixinUser user);

	int updateBindAppUserInfo(Map<String,Object> params);
}
