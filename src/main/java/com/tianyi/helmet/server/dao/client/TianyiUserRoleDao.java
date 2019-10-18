package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.TianyiUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 田一用户角色信息
 *
 */
@Repository
public interface TianyiUserRoleDao {
	void insert(TianyiUserRole userRole);

	TianyiUserRole selectById(int id);

	List<TianyiUserRole> selectByUserId(int userId);

	List<TianyiUserRole> selectByRoleCode(String roleCode);

	int deleteById(int id);

	int deleteByUserId(int userId);

	int deleteByUserIdRoleCode(Map<String,Object> params);
}
