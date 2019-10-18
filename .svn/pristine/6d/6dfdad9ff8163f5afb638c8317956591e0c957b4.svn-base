package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.HelmetSensor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  头盔终端传感器信息
 *
 */
@Repository
public interface HelmetSensorDao {

	void insert(HelmetSensor sensor);

	HelmetSensor selectById(int id);

	List<HelmetSensor> selectBy(Map<String,Object> paramMap);

	int countBy(Map<String,Object> paramMap);

	String selectMinCreateTime(String clientId);

	String selectMaxCreateTime(String clientId);
}
