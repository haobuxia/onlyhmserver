package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.dao.svc.SvcFaultBriefDao;
import com.tianyi.helmet.server.entity.svc.SvcFaultBrief;
import com.tianyi.helmet.server.entity.svc.SvcFaultBriefEnum;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.vo.DoubleVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 服务日志各项数据填报信息服务
 * <p>
 * Created by liuhanc on 2018/3/19.
 */
@Service
public class SvcFaultBriefService {
    @Autowired
    private SvcFaultBriefDao svcFaultBriefDao;

    private Logger logger = LoggerFactory.getLogger(SvcFaultBriefService.class);

    public void insert(SvcFaultBrief breif) {
        svcFaultBriefDao.insert(breif);
    }

    private Map<SvcFaultBriefEnum, DoubleVo<BiConsumer<SvcFaultBrief, String>, Function<SvcFaultBrief, String>>> briefMap = new HashMap<>();

    @PostConstruct
    private void initBriefMap() {
        briefMap.put(SvcFaultBriefEnum.siteVideo, new DoubleVo<>(SvcFaultBrief::setSiteVideo, SvcFaultBrief::getSiteVideo));
        briefMap.put(SvcFaultBriefEnum.diggerVideo, new DoubleVo<>(SvcFaultBrief::setDiggerVideo, SvcFaultBrief::getDiggerVideo));
        briefMap.put(SvcFaultBriefEnum.faultCheckVideo, new DoubleVo<>(SvcFaultBrief::setFaultCheckVideo, SvcFaultBrief::getFaultCheckVideo));
        briefMap.put(SvcFaultBriefEnum.faultRepairVideo, new DoubleVo<>(SvcFaultBrief::setFaultRepairVideo, SvcFaultBrief::getFaultCheckVideo));
        briefMap.put(SvcFaultBriefEnum.jhPic, new DoubleVo<>(SvcFaultBrief::setJhPic, SvcFaultBrief::getJhPic));
        briefMap.put(SvcFaultBriefEnum.timeMile, new DoubleVo<>(SvcFaultBrief::setTimeMile, SvcFaultBrief::getTimeMile));
        briefMap.put(SvcFaultBriefEnum.usageStat, new DoubleVo<>(SvcFaultBrief::setUsageStat, SvcFaultBrief::getUsageStat));
        briefMap.put(SvcFaultBriefEnum.fault, new DoubleVo<>(SvcFaultBrief::setFault, SvcFaultBrief::getFault));
        briefMap.put(SvcFaultBriefEnum.faultResove, new DoubleVo<>(SvcFaultBrief::setFaultResove, SvcFaultBrief::getFaultResove));
        briefMap.put(SvcFaultBriefEnum.faultHandle, new DoubleVo<>(SvcFaultBrief::setFaultHandle, SvcFaultBrief::getFaultHandle));
        briefMap.put(SvcFaultBriefEnum.faultRepair, new DoubleVo<>(SvcFaultBrief::setFaultRepair, SvcFaultBrief::getFaultRepair));
        briefMap.put(SvcFaultBriefEnum.opinion, new DoubleVo<>(SvcFaultBrief::setOpinion, SvcFaultBrief::getOpinion));
    }

    public DoubleVo<BiConsumer<SvcFaultBrief, String>, Function<SvcFaultBrief, String>> getBriefConfig(SvcFaultBriefEnum briefEnum) {
        return briefMap.get(briefEnum);
    }

    @Cacheable(value = CacheKeyConstants.SVC_FAULT_BRIEF_BY_PK, key = "#rwh+'-'+#oprtId", unless = "#result == null")
    public SvcFaultBrief selectByRwhOprtId(String rwh, String oprtId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("rwh", rwh);
        map.put("oprtId", oprtId);
        List<SvcFaultBrief> list = svcFaultBriefDao.listBy(map);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    /**
     * 更新某个状态位
     */
    @CacheEvict(value = CacheKeyConstants.SVC_FAULT_BRIEF_BY_PK, key = "#brief.rwh+'-'+#brief.oprtId")
    public boolean updateByRwhOprtId(SvcFaultBrief brief) {
        int cnt = svcFaultBriefDao.updateByRwhOprtId(brief);
        boolean success = cnt > 0;
        return success;
    }

    //判断1个工单是否填写完毕
    public boolean isComplete(SvcFaultBrief brief) {
        String lostItems = getLostSvcFaultBriefEnum(brief);
        return StringUtils.isEmpty(lostItems);
    }

    //获得1个工单未填写完毕的额项目名，以、分隔
    public String getLostSvcFaultBriefEnum(SvcFaultBrief brief) {
        String lostItems = Arrays.stream(SvcFaultBriefEnum.values()).filter(typeEnum -> {
            DoubleVo<BiConsumer<SvcFaultBrief, String>, Function<SvcFaultBrief, String>> doubleVo = briefMap.get(typeEnum);
            String zt = doubleVo.getVal().apply(brief);
            return !"1".equals(zt);
        }).map(typeEnum -> typeEnum.getCnName()).collect(Collectors.joining("、"));
        return lostItems;
    }

}
