package com.tianyi.helmet.server.service.scene;

import com.tianyi.helmet.server.dao.scene.SiteDao;
import com.tianyi.helmet.server.entity.scene.Site;
import com.tianyi.helmet.server.service.file.ImageService;
import com.tianyi.helmet.server.service.file.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  工地服务
 *
 * Created by liuhanc on 2018/1/16.
 */
@Service
public class SiteService {

    @Autowired
    private SiteDao siteDao;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ImageService imageService;

    private Logger logger = LoggerFactory.getLogger(SiteService.class);

    @Transactional
    public void insert(Site site) {
        site.setCreateTime(LocalDateTime.now());
        siteDao.insert(site);

        String imgLonLtas = site.getSiteImageLonLats();
        String videoLonLats = site.getSiteVideoLonLats();
        if(!StringUtils.isEmpty(imgLonLtas)){
            List<Float[]> lonLats = Arrays.stream(imgLonLtas.split(";")).map(lonLat->{
                String[] ll = lonLat.split(",");
                return new Float[]{Float.parseFloat(ll[0]),Float.parseFloat(ll[1])};
            }).collect(Collectors.toList());
            int cnt = imageService.updateSiteIdByGeo(site.getId(),lonLats);
            logger.debug("新建工地成功."+site.getId()+","+site.getName()+".工地图片数量:"+cnt);
        }
        if(!StringUtils.isEmpty(videoLonLats)){
            List<Float[]> lonLats = Arrays.stream(videoLonLats.split(";")).map(lonLat->{
                String[] ll = lonLat.split(",");
                return new Float[]{Float.parseFloat(ll[0]),Float.parseFloat(ll[1])};
            }).collect(Collectors.toList());
            int cnt = videoService.updateSiteIdByGeo(site.getId(),lonLats);
            logger.debug("新建工地成功."+site.getId()+","+site.getName()+".确定的视频数量:"+cnt+",传入视频数量:"+lonLats.size());
        }
    }

    public List<Site> listBy(Map params) {
        return siteDao.selectBy(params);
    }

    public List<Site> listByIdList(List<Integer> idList){
        Map<String,Object> parms = new HashMap<>(1);
        parms.put("idList",idList);
        return listBy(parms);
    }

    public int countBy(Map params) {
        return siteDao.countBy(params);
    }

    public Site selectById(int id){
        return siteDao.selectById(id);
    }

    @Transactional
    public int deleteById(int id){
        int cnt = siteDao.deleteById(id);
        if(cnt > 0 ){
            //删除视频和图片标记为这个工地的
            int cnt1 = videoService.clearSiteIdBySiteId(id);
            int cnt2 = imageService.clearSiteIdBySiteId(id);
            logger.debug("删除工地完毕.site.id="+id+",被清除此工地的视频数:"+cnt1+",图片数:"+cnt2);
        }
        return cnt;
    }

    public int updateById(Site site) {
        return siteDao.updateById(site);
    }
}

