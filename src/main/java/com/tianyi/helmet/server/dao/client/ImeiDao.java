package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.Imei;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 车辆盒子Imei信息
 *
 */
@Repository
public interface ImeiDao {
	void insert(Imei imei);

	Imei selectById(int id);

	Imei selectByImei(String imei);

	List<Imei> listBy(Map<String, Object> params);

	int countBy(Map<String, Object> params);

	int deleteById(int id);

}
