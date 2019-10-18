package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.VideoKeywordDao;
import com.tianyi.helmet.server.entity.file.KeyWord;
import com.tianyi.helmet.server.entity.file.VideoKeyword;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.TripleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 视频关键词服务
 * <p>
 * Created by liuhanc on 2018/5/25.
 */
@Service
public class VideoKeywordService {

    @Autowired
    private VideoKeywordDao videoKeywordDao;
    @Autowired
    private KeyWordService keyWordService;

    @Cacheable(value = CacheKeyConstants.VIDEO_KEYWORDS_BY_VIDEO_ID, key = "#videoId.toString()", unless = "#result == null")
    public List<VideoKeyword> selectByVideoId(int videoId) {
        return videoKeywordDao.selectByVideoId(videoId);
    }

    @CacheEvict(value = CacheKeyConstants.VIDEO_KEYWORDS_BY_VIDEO_ID, key = "#videoKeyword.videoId.toString()")
    public void insert(VideoKeyword videoKeyword) {
        videoKeywordDao.insert(videoKeyword);
    }

    @CacheEvict(value = CacheKeyConstants.VIDEO_KEYWORDS_BY_VIDEO_ID, key = "#videoKeyword.videoId.toString()")
    public void updateKeyWord(VideoKeyword videoKeyword) {
        videoKeywordDao.updateKeyWord(videoKeyword);
    }

    @CacheEvict(value = CacheKeyConstants.VIDEO_KEYWORDS_BY_VIDEO_ID, key = "#videoId.toString()")
    public int deleteByVideoId(int videoId) {
        return videoKeywordDao.deleteByVideoId(videoId);
    }

    @CacheEvict(value = CacheKeyConstants.VIDEO_KEYWORDS_BY_VIDEO_ID, key = "#videoId.toString()")
    public int deleteByVideoIdKeywordId(int videoId, int keywordId) {
        Map<String, Object> map = new HashMap();
        map.put("videoId", videoId);
        map.put("keywordId", keywordId);
        return videoKeywordDao.deleteByVideoIdKeywordId(map);
    }

    public List<VideoKeyword> selectByKeywordId(int videoId, int page, int pageSize) {
        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        map.put("videoId", videoId);
        return videoKeywordDao.selectByKeywordId(map);
    }

    public int countByKeywordId(int keywordId) {
        return videoKeywordDao.countByKeywordId(keywordId);
    }

    /**
     * 按关键词分组查询视频数量、关键词名
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageListVo<TripleVo<Integer, Integer, String>> groupByKeyword(Integer page, Integer pageSize) {
        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        List<DoubleVo<Integer, Integer>> kwIdCntlist = videoKeywordDao.groupByKeyword(map);

        //数据转换
        List<Integer> kwIdList = kwIdCntlist.stream().map(DoubleVo::getKey).distinct().collect(Collectors.toList());
        Map<Integer, String> kwIdNameMap = keyWordService.selectKeyWordList().stream().filter(kw -> kwIdList.contains(kw.getId())).collect(Collectors.toMap(KeyWord::getId, KeyWord::getKeywordName));
        List<TripleVo<Integer, Integer, String>> voList = kwIdCntlist.stream().map(doubleVo -> {
            //关键词id，资源数、关键词名
            return new TripleVo<>(doubleVo.getKey(), doubleVo.getVal(), kwIdNameMap.get(doubleVo.getKey()));
        }).collect(Collectors.toList());

        int total = videoKeywordDao.selectKeywordCount(map);
        PageListVo<TripleVo<Integer, Integer, String>> vo = new PageListVo(page, pageSize);
        vo.setList(voList);
        vo.setTotal(total);
        return vo;
    }

}
