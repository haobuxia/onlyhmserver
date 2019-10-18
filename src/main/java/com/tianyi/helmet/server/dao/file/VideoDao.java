package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.TripleVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 视频信息
 */
@Repository
public interface VideoDao extends HelmetShotDao {
    int insert(Video video);

    Video selectById(int id);

    /**
     * 根据头盔自己的imei分组
     * @param params
     * @return
     */
    List<DoubleVo<String, Integer>> groupByHelmetImei(Map<String, Object> params);

    /**
     * 根据头盔的账号分组
     * @param params
     * @return
     */
    List<DoubleVo<String, Long>> groupByNeUsername(Map<String, Object> params);

    /**
     * 根据视频对应头盔连接的天远盒子imei分组
     * @param params
     * @return
     */
    List<DoubleVo<String, Integer>> groupByTyBoxImei(Map<String, Object> params);

    int selectClientCount();

    int selectImeiCount();

    List<TripleVo<Float, Float, Integer>> selectGeoCount();

    int increaseViewCount(int id);

    int deleteById(int id);

    int updateStateById(Map<String, Object> params);

    /**
     * 更新是否有车载盒子数据
     *
     * @param params
     * @return
     */
    int updateHasGpsDataById(Map<String, Object> params);

    /**
     * 更新内嵌字幕版视频路径
     *
     * @param params
     * @return
     */
    int updateTrackVideoOssPathById(Map<String, Object> params);

    int updateById(Video video);

    int updateVideoTypeById(Map<String, Object> params);

    List<Video> listByCreateTime(Map<String, Object> params);

    List<Integer> listDoubles(Map<String, Object> params);
}
