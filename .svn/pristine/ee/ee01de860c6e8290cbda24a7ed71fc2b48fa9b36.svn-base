package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.GpsLocationData;
import com.tianyi.helmet.server.vo.TripleVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  车载终端gps定位数据信息
 *
 */
@Repository
public interface GpsLocationDataDao {

	void insert(GpsLocationData gps);

	GpsLocationData selectById(int id);

	List<GpsLocationData> selectBy(Map<String, Object> paramMap);

	int countBy(Map<String, Object> paramMap);

	List<Integer> selectDateDataList(Map<String, Object> paramMap);

	List<TripleVo<Integer,Integer,Integer>> selectLocationList(Map<String, Object> paramMap);
}
