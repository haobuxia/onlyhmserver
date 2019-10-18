package com.tianyi.helmet.server.service.data;

import com.tianyi.helmet.server.dao.data.HelmetGpsDao;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 *  头盔定位信息
 *
 *
 * Created by liuhanc on 2018/1/16.
 */
@Service
public class HelmetGeoService {
    @Autowired
    private KmxService kmxService;
    @Autowired
    private HelmetGpsDao helmetGpsDao;

    public Float[] getHelmetGeo(String clientId,LocalDateTime time){
        long longTime = Dates.toDate(time).getTime();
        Float[] geo = kmxService.getHelmetGeo(clientId,longTime);
        if(geo == null){
            Map<String,Object> map = new HashMap(3);
            map.put("clientId",clientId);
            map.put("createTime",time);
            map.put("intervalSeconds",5*60);
            DoubleVo<Float,Float> vo = helmetGpsDao.selectLonLatByClientIdTimeInterval(map);
            if(vo != null){
                geo = new Float[]{vo.getKey(),vo.getVal()};
            }
        }
        return geo;
    }

    public Float[] getHelmetGeo(String clientId,LocalDateTime time1,LocalDateTime time2){
        long longTime1 = Dates.toDate(time1).getTime();
        long longTime2 = Dates.toDate(time2).getTime();
        long middleTime = longTime1 + (longTime2-longTime1)/2;
        Float[] geo = kmxService.getHelmetGeo(clientId,middleTime);
        if(geo == null){
            Map<String,Object> map = new HashMap(3);
            map.put("clientId",clientId);
            map.put("createTime1",time1);
            map.put("createTime2",time2);
            DoubleVo<Float,Float> vo = helmetGpsDao.selectLonLatByClientIdTimeRange(map);
            if(vo != null){
                geo = new Float[]{vo.getKey(),vo.getVal()};
            }
        }
        return geo;
    }
}
