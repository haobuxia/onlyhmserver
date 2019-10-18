package com.tianyi.helmet.server.service.track;

import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.SgGpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.service.data.TyBoxDataService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字幕服务
 * <p>
 * Created by liuhanc on 2017/11/1.
 */
@Service
public class TrackService {

    private Logger logger = LoggerFactory.getLogger(TrackService.class);

    public static final String VTT_NAME = "vtt";
    public static final String SRT_NAME = "srt";

    @Autowired
    private TyBoxDataService tyBoxDataService;

    @CacheEvict(value = CacheKeyConstants.VIDEO_TRACK_BY_VIDEO_ID, key = "#type+'-'+#id.toString()")
    public void clearTrackCache(String type,int id){
        logger.debug("删除字幕缓存."+type+",id="+id);
    }

    @Cacheable(value = CacheKeyConstants.VIDEO_TRACK_BY_VIDEO_ID, key = "#type+'-'+#v.id.toString()", unless = "#result == null")
    public String make(Video v, String type) {
        logger.debug("开始生成字幕.id=" + v.getId() + ",字幕类型=" + type);
        //车载设备信息
        DoubleVo<String,Map<Integer,List<DoubleVo<Long,Integer>>>> doubleVo = tyBoxDataService.selectGpsDataListByVideo(v);
        Map<Integer, List<DoubleVo<Long, Integer>>> intMapData = doubleVo.getVal();
        if (CollectionUtils.isEmpty(intMapData)) {
            return "NO DATA";
        }

        if(v.getDescription()!= null && v.getDescription().equals("600s")) {// 神钢字幕生成
            List<SgGpsDataTypeItemEnum> typeList = intMapData.keySet().stream().sorted().map(SgGpsDataTypeItemEnum::get).collect(Collectors.toList());
            SgMaker maker = new SgSubtitlesMaker(typeList, v.getCreateTime(), v.getCreateTime().plusSeconds(v.getSeconds()));
            //int型
            intMapData.keySet().stream().forEach(typeId -> {
                SgGpsDataTypeItemEnum itemEnum = SgGpsDataTypeItemEnum.get(typeId);
                List<DoubleVo<Long, Integer>> kvList = intMapData.get(typeId);
                kvList.stream().forEach(kv -> {
                    int origVal = kv.getVal();
                    float ratioVal = new BigDecimal(itemEnum.getRatio() * origVal).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
                    maker.updateValue(typeId, kv.getKey(), String.valueOf(ratioVal));
                });
            });
            maker.make();
            return maker.getResult();
        }

        Map<GpsDataTypeItemEnum, Map<Long, String>> strDataMap = tyBoxDataService.selectActionGyroDataByVideo(v);
        Map<GpsDataTypeItemEnum, Map<Long, String>> workModeDataMap = tyBoxDataService.selectWorkModeDataByVideo(v);

        List<GpsDataTypeItemEnum> typeList = intMapData.keySet().stream().sorted().map(GpsDataTypeItemEnum::get).collect(Collectors.toList());
        strDataMap.keySet().stream().forEach(dataTypeItemEnum->{
            typeList.add(dataTypeItemEnum);
        });
        workModeDataMap.keySet().stream().forEach(dataTypeItemEnum->{
            typeList.add(dataTypeItemEnum);
        });

        Maker maker = null;
        if (VTT_NAME.equals(type)) {
            maker = new WebVttMaker(typeList, v.getCreateTime(), v.getCreateTime().plusSeconds(v.getSeconds()));
        } else {
            maker = new SubtitlesMaker(typeList, v.getCreateTime(), v.getCreateTime().plusSeconds(v.getSeconds()));
        }

        final Maker finalMaker = maker;
        //int型
        intMapData.keySet().stream().forEach(typeId -> {
            GpsDataTypeItemEnum itemEnum = GpsDataTypeItemEnum.get(typeId);
            List<DoubleVo<Long, Integer>> kvList = intMapData.get(typeId);
            kvList.stream().forEach(kv -> {
                int origVal = kv.getVal();
                float ratioVal = new BigDecimal(itemEnum.getRatio() * origVal).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
                finalMaker.updateValue(typeId, kv.getKey(), String.valueOf(ratioVal));
            });
        });

        //字符型
        updateMapValueToMaker(finalMaker,strDataMap);
        //工作模式
        updateMapValueToMaker(finalMaker,workModeDataMap);

        maker.make();
        return maker.getResult();
    }

    private void updateMapValueToMaker(Maker finalMaker,Map<GpsDataTypeItemEnum, Map<Long, String>> dataMap){
        dataMap.keySet().stream().forEach(typeItemEnum -> {
            Map<Long,String> timeData = dataMap.get(typeItemEnum);
            if(!CollectionUtils.isEmpty(timeData)){
                timeData.keySet().stream().forEach(time -> {
                    finalMaker.updateValue(typeItemEnum.getId(), time, timeData.get(time));
                });
            }
        });
    }

}
