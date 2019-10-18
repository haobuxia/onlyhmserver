package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.VideoKeyword;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 视频信息扩展-关键词
 */
@Repository
public interface VideoKeywordDao {
    void insert(VideoKeyword video);

    List<VideoKeyword> selectByVideoId(int videoId);

    List<VideoKeyword> selectByKeywordId(Map<String, Object> map);

    int countByKeywordId(int KeywordId);

    int deleteByVideoId(int videoId);

    int deleteByKeywordId(int KeywordId);

    int deleteByVideoIdKeywordId(Map<String, Object> map);

    List<DoubleVo<Integer,Integer>> groupByKeyword(Map<String, Object> map);

    int selectKeywordCount(Map<String, Object> map);

    int updateKeyWord(VideoKeyword video);
}
