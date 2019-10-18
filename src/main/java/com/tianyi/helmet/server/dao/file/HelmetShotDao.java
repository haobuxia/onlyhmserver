package com.tianyi.helmet.server.dao.file;

/**
 * 头盔拍摄录制信息
 *
 * Created by liuhanc on 2018/1/19.
 */

import com.tianyi.helmet.server.entity.file.HelmetShot;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.GeoDataVo;
import com.tianyi.helmet.server.vo.TripleVo;

import java.util.List;
import java.util.Map;

public interface HelmetShotDao{

    List<? extends HelmetShot> listBy(Map<String,Object> params);

    int countBy(Map<String, Object> params);

    List<DoubleVo<String, Integer>> groupByMachineCode(Map<String, Object> params);

    int selectMachineCodeCount();

    List<DoubleVo<Integer, Integer>> groupBySiteId(Map<String, Object> params);

    /**
     * 列出基于地理位置和标签的资源列表
     * @return
     */
    List<GeoDataVo> selectTagGeoData();

    int selectSiteIdCount();

    int updateMachineCodeById(Map<String, Object> params);

    int updateLonLatById(Map<String, Object> params);

    int updateThumbOssPathById(Map<String, Object> params);

    int updateSiteIdByGeo(Map<String,Object> params);

    List<TripleVo<Float,Float,Integer>> selectGeoCount();

    int clearSiteId(int siteId);
}
