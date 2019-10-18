package com.tianyi.helmet.server.service.data;

import com.tianyi.helmet.server.dao.data.GpsLineDataDao;
import com.tianyi.helmet.server.entity.data.GpsLineData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 天远盒子行数据
 * Created by liuhanc on 2017/12/14.
 */
@Service
public class TyBoxDataLineService {
    @Autowired
    private GpsLineDataDao gpsLineDataDao;


    public List<GpsLineData> selectByFileId(int fileId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fileId",fileId);
        return gpsLineDataDao.selectBy(paramMap);
    }

}
