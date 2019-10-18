package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.Audio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 音频信息
 *
 */
@Repository
public interface AudioDao {
	void insert(Audio audio);

	Audio selectById(int id);

	List<Audio> listBy(Map<String, Object> params);

	int countBy(Map<String, Object> params);

	int increaseViewCount(int id);

	int deleteById(int id);

}
