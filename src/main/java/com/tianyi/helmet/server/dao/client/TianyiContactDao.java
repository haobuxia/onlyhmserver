package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.TianyiContact;
import com.tianyi.helmet.server.entity.client.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 田一用户的联系人信息
 *
 */
@Repository
public interface TianyiContactDao {
	void insert(TianyiContact contact);

	List<User> listBy(Map<String, Object> params);

	int countBy(Map<String, Object> params);

	TianyiContact selectById(int id);

	List<TianyiContact> selectByUserId(int userId);

	TianyiContact selectByUserIdContactId(Map<String,Integer> map);

	int deleteById(int id);

	int deleteByUserId(int userId);

	int deleteByUserIdContactId(Map<String,Integer> map);
}
