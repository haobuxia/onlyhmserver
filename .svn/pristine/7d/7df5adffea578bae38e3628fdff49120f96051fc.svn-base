package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.dao.client.ImeiDao;
import com.tianyi.helmet.server.entity.client.Imei;
import com.tianyi.helmet.server.entity.tianyuan.VclInfo;
import com.tianyi.helmet.server.service.file.FileService;
import com.tianyi.helmet.server.service.kmx.MetaDataInitService;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.service.tianyuan.TianYuanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 天远盒子 imei
 * Created by liuhanc on 2017/11/7.
 */
@Service
public class TyBoxImeiService {

    @Autowired
    private ImeiDao imeiDao;
    @Autowired
    private RedisTemplate jedisTemplate;
    @Autowired
    private MetaDataInitService metaDataInitService;
    @Autowired
    private TianYuanService tianYuanService;

    private Logger logger = LoggerFactory.getLogger(FileService.class);

    @PostConstruct
    public void initImeiSet() {
        listBy();
    }

    public void addImei(String imei,String type) {
        boolean exist = exist(imei);
        if (!exist) {
            jedisTemplate.opsForSet().add(CacheKeyConstants.TYBOX_IMEI_SET_ALL, imei);
            effectImeiIdSet.add(imei);
            //内存中不存在的则入库.
            Imei ii = new Imei();
            ii.setImei(imei);
            ii.setCreateTime(LocalDateTime.now());
            try {
                imeiDao.insert(ii);
            } catch (Exception e) {
                logger.warn("存储车辆imei入库失败." + imei + ".msg=" + e.getMessage());
            }
            if(type!=null && type.equals("600s")) {
                metaDataInitService.initDeviceSgTyBoxDevice(imei);
            } else {
                metaDataInitService.initDeviceTyBoxDevice(imei);
            }
        }
    }

    public Set<String> listBy() {
        Set<String> set = jedisTemplate.opsForSet().members(CacheKeyConstants.TYBOX_IMEI_SET_ALL);
        if (set == null || set.size() == 0) {
            set = imeiDao.listBy(null).stream().map(Imei::getImei).collect(Collectors.toSet());
            if (set.size() > 0)
                jedisTemplate.opsForSet().add(CacheKeyConstants.TYBOX_IMEI_SET_ALL, set.toArray());
        }
        return set;
    }

    public boolean exist(String imei) {
        return jedisTemplate.opsForSet().isMember(CacheKeyConstants.TYBOX_IMEI_SET_ALL, imei);
    }


    public Set<String> getEffectImeiIdSet() {
        return listBy().stream().collect(Collectors.toSet());
    }

    @Cacheable(value = CacheKeyConstants.VCLINFO_BY_TYBOX_IMEI, key = "#imei", unless = "#result == null")
    public VclInfo getVclInfoByImei(String imei) {
        VclInfo info = tianYuanService.getVclInfoByImei(imei);
        return info;
    }


    private Set<String> effectImeiIdSet = null;
    private static final LocalDateTime effectTime = LocalDateTime.of(2017, 10, 1, 0, 0, 0);

    public boolean isEffectImei(String imei) {
        if (effectImeiIdSet == null) {
            effectImeiIdSet = this.getEffectImeiIdSet();
        }
        return effectImeiIdSet.contains(imei);
    }

}
