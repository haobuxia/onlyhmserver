package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.HelmetGps;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  头盔终端定位信息
 *
 */
@Repository
public interface HelmetGpsDao {

	void insert(HelmetGps gps);

	HelmetGps selectById(int id);

	List<HelmetGps> selectBy(Map<String, Object> paramMap);

	int countBy(Map<String, Object> paramMap);

	DoubleVo<Float,Float> selectLonLatByClientIdTimeRange(Map<String, Object> paramMap);

	DoubleVo<Float,Float> selectLonLatByClientIdTimeInterval(Map<String, Object> paramMap);
}
