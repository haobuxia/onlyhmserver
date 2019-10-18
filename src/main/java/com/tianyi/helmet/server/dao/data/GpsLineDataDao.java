package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.GpsLineData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  车载终端gps每行数据基本信息
 *
 */
@Repository
public interface GpsLineDataDao {

	void insert(GpsLineData gps);

	GpsLineData selectById(int id);

	List<GpsLineData> selectBy(Map<String, Object> paramMap);

	int countBy(Map<String, Object> paramMap);

	List<String> getImeiList();
}
