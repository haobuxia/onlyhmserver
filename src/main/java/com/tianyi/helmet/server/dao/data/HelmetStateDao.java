package com.tianyi.helmet.server.dao.data;

import com.tianyi.helmet.server.entity.data.HelmetState;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  头盔终端状态信息
 *
 */
@Repository
public interface HelmetStateDao {

	void insert(HelmetState state);

	HelmetState selectById(int id);

	List<HelmetState> selectBy(Map<String, Object> paramMap);

	int countBy(Map<String, Object> paramMap);

}
