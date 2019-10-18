package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.VideoTimeTag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  视频时间标志信息
 *
 */
@Repository
public interface VideoTimeTagDao {

	void batchInsert(List<VideoTimeTag> videoTimeTagList);

	void insert(VideoTimeTag videoTimeTag);

	VideoTimeTag selectById(int id);

	int deleteById(int id);

	List<VideoTimeTag> selectByVideoId(int videoId);

	List<VideoTimeTag> selectByVideoName(String videoName);

	List<VideoTimeTag> listBy(Map<String, Object> map);

	int countBy(Map<String, Object> map);

	int updateVideoIdByName(Map<String,Object> map);
}
