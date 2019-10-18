package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.GpsData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  车载终端gps数据信息
 *
 */
@Repository
public interface GpsDataDao {

	void insert(GpsData gps);

	GpsData selectById(int id);

	List<GpsData> selectBy(Map<String, Object> paramMap);

	int countBy(Map<String, Object> paramMap);

	String selectImeiByClientIdTimeRange(Map<String, Object> paramMap);
}
