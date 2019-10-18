package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.GpsLineData;
import com.tianyi.helmet.server.entity.data.TyBoxLineData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  车载终端tybox每行数据基本信息
 *
 */
@Repository
public interface TyBoxLineDataDao {

	void insert(TyBoxLineData lineData);

	TyBoxLineData selectById(int id);

	List<TyBoxLineData> selectBy(Map<String, Object> paramMap);

	int countBy(Map<String, Object> paramMap);

}
