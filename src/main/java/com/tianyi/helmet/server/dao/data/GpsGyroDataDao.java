package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.GpsGyroData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  车载终端gps陀螺仪数据信息
 *
 */
@Repository
public interface GpsGyroDataDao {

	void insert(GpsGyroData gps);

	GpsGyroData selectById(int id);

	List<GpsGyroData> selectBy(Map<String, Object> paramMap);

	int countBy(Map<String, Object> paramMap);

}
