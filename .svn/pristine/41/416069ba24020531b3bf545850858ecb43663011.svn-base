package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.HelmetShotDao;
import com.tianyi.helmet.server.entity.file.HelmetShot;
import com.tianyi.helmet.server.entity.file.Image;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.entity.scene.Site;
import com.tianyi.helmet.server.service.data.HelmetGeoService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.scene.SiteService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.RelationUtils;
import com.tianyi.helmet.server.util.image.ImageUtils;
import com.tianyi.helmet.server.vo.GeoDataVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.TripleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 各种上传资源服务接口
 * <p>
 * Created by liuhanc on 2017/10/26.
 */
public abstract class HelmetShotService<T extends HelmetShot> implements UploadEntityService {
    private Logger logger = LoggerFactory.getLogger(HelmetShotService.class);

    @Autowired
    private HelmetGeoService helmetGeoService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private ConfigService configService;

    abstract HelmetShotDao getDao();

    public int clearSiteIdBySiteId(Integer siteId) {
        return getDao().clearSiteId(siteId);
    }

    public int updateSiteIdByGeo(Integer siteId, Float lon, Float lat) {
        Map<String, Object> map = new HashMap();
        map.put("siteId", siteId);
        map.put("lon", lon);
        map.put("lat", lat);
        return getDao().updateSiteIdByGeo(map);
    }

    @Transactional
    public int updateSiteIdByGeo(Integer siteId, List<Float[]> lonLatList) {
        if (CollectionUtils.isEmpty(lonLatList)) {
            return 0;
        }

        int allCount = 0;
        for (Float[] lonlat : lonLatList) {
            allCount += updateSiteIdByGeo(siteId, lonlat[0], lonlat[1]);
        }
        return allCount;
    }

    public int updateMachineCode(int id, String machineCode) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        map.put("machineCode", machineCode);
        return getDao().updateMachineCodeById(map);
    }

    public List<TripleVo<Float, Float, Integer>> selectGeoCount() {
        return getDao().selectGeoCount();
    }

    public List<? extends HelmetShot> selectAllGeoData() {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("hasGeo", "1");
        params.put("hasTag", "1");
        return getDao().listBy(params);
    }

    //以定位为key，以GeoDataVo的列表为value的map数据，表示某个定位位置的多个视频及标签名数据
    public Map<String, List<GeoDataVo>> selectTagGeoMapData() {
        return getDao().selectTagGeoData().stream().collect(Collectors.groupingBy(vo -> vo.getLon() + "," + vo.getLat(), Collectors.toList()));
    }

    /**
     * 根据工单号查询工单对应的资源
     *
     * @param orderNo
     * @return
     */
    public List<?> listByOrderNo(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        List<?> list = getDao().listBy(map);
        return list;
    }

    /**
     * 按机号分组查询数据和数量
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageListVo<TripleVo<String, Integer, String>> groupByMachineCode(Integer page, Integer pageSize) {
        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        map.put("groupMachineCode", "1");
        List<TripleVo<String, Integer, String>> list = getDao().groupByMachineCode(map).stream()
                .map(vo -> {
                    return new TripleVo<>(vo.getKey(), vo.getVal(), vo.getKey());//@todo 机号转成机器名
                }).collect(Collectors.toList());
        int total = getDao().selectMachineCodeCount();

        PageListVo vo = new PageListVo(page, pageSize);
        vo.setList(list);
        vo.setTotal(total);
        return vo;
    }

    /**
     * 按工地分组查询数据和数量
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageListVo<TripleVo<String, Integer, Site>> groupBySiteId(Integer page, Integer pageSize) {
        Map<String, Object> map = PageListVo.createParamMap(page, pageSize);
        map.put("groupSiteId", "1");
        List<TripleVo<Integer, Integer, Site>> siteStatList = getDao().groupBySiteId(map).stream()
                .map(vo -> {
                    return new TripleVo<Integer, Integer, Site>(vo.getKey(), vo.getVal());
                }).collect(Collectors.toList());
        fullfilSiteName(siteStatList);//工地号转成工地

        int total = getDao().selectSiteIdCount();
        PageListVo vo = new PageListVo(page, pageSize);
        vo.setList(siteStatList);
        vo.setTotal(total);
        return vo;
    }

    /**
     * 更新资源发生的定位位置
     *
     * @param id
     * @param lonlat
     * @return
     */
    public int updateLonLat(int id, Float[] lonlat) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("id", id);
        map.put("lon", lonlat == null ? -1f : (lonlat[0] == null ? 0 : lonlat[0]));
        map.put("lat", lonlat == null ? -1f : (lonlat[1] == null ? 0 : lonlat[1]));
        return getDao().updateLonLatById(map);
    }

    public int updateThumbOssPath(int id, String thumbOssPath) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        map.put("thumbOssPath", thumbOssPath);
        return getDao().updateThumbOssPathById(map);
    }


    //填充按工地统计信息
    protected void fullfilSiteName(List<TripleVo<Integer, Integer, Site>> tripleVoList) {
        RelationUtils.fullfilListRelateProperty(tripleVoList, TripleVo::getOne, siteService::listByIdList, Site::getId, site -> site, TripleVo::setThree);
    }

    /**
     * 填充资源的定位位置
     *
     * @param t
     */
    public void fullfilLonlat(T t) {
        if (!needFulfillLonLat(t)) {
            return;
        }

        LocalDateTime queryTime = null;
        if (t instanceof Video) {
            Video v = (Video) t;
            queryTime = v.getCreateTime().plusSeconds(v.getSeconds() / 2);
        } else if (t instanceof Image) {
            queryTime = t.getCreateTime();
        }

        if (queryTime != null) {
            Float[] geo = helmetGeoService.getHelmetGeo(t.getClientId(), queryTime);
            this.updateLonLat(t.getId(), geo);
            if (geo == null) {
                t.setLon(-1f);
                t.setLat(-1f);
            } else {
                t.setLon(geo[0]);
                t.setLat(geo[1]);
            }
            logger.debug("补全资源定位信息.v.id=" + t.getId() + ",geo=" + (geo == null ? null : (geo[0] + "," + geo[1])));
        }
    }


    /**
     * 填充资源的缩略图信息，如不存在则生成并存入oss
     *
     * @param t
     */
    public void fullfilThumbOssPath(T t) {
        if (!StringUtils.isEmpty(t.getThumbOssPath())) {
            return;
        }

        String picPath = null;
        String src = null;
        if (t instanceof Video) {
            Video v = (Video) t;
            picPath = v.getPicOssPath();
            src = v.getSrc();
        } else if (t instanceof Image) {
            Image i = (Image) t;
            picPath = i.getOssPath();
        }

        if (picPath == null) {
            if("netease".equals(src)){
                t.setThumbOssPath("/static/images/nepic.jpg");
            }else{
                t.setThumbOssPath("/static/images/img-lost.png");
            }
            return;
        }

        byte[] picContent = fastDfsClient.downloadFile(picPath);
        if (picContent == null) {
            if("netease".equals(src)){
                t.setThumbOssPath("/static/images/nepic.jpg");
            }else{
                t.setThumbOssPath("/static/images/img-lost.png");
            }
            logger.error("生成缩略图时下载原图失败.path=" + picPath + ",id=" + t.getId() + ",type=" + t.getType());
            return;
        }

        String thumbOssPath = makePicThumbToOss(picContent);
        t.setThumbOssPath(thumbOssPath);
        this.updateThumbOssPath(t.getId(), thumbOssPath);
    }

    /**
     * 根据图片原始内容生成缩略图并存入oss服务器，反馈oss存储路径
     *
     * @param picContent
     * @return
     */
    public String makePicThumbToOss(byte[] picContent) {
        InputStream is = new ByteArrayInputStream(picContent);
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        ImageUtils.fromSteram(is)        //设置原图片
                //.width(200)			//设置生成图片的宽度，高度将以原图片的高度等比例伸缩
                //.height(200)			//设置生成图片的高度，宽度将以原图片的宽度等比例伸缩
                //.scale(1)				//设置生成图片的伸缩比例
                .size(configService.getThumbnailWidth(), configService.getThumbnailHeight())        //设置生成图片的宽度和高度
                .keepRatio(true)
//                .rotate(34)				//设置原图片的旋转角度
//                .watermark(watermark)	//设置水印
                //.watermarkArray(list)	//设置多个水印
                .bgcolor(Color.YELLOW)    //设置背景颜色，如果为null，表示不添加背景颜色，如果图片为png，为透明颜色
                .quality(0.6f)            //设置压缩比例，默认为0.75
                .toOutput(byos, "jpg");    //生成图片的路径
        String thumbOssPath = fastDfsClient.uploadFile(byos.toByteArray(), "jpg");
        return thumbOssPath;
    }


    /**
     * 判断资源是否需要填充定位信息
     *
     * @param shot
     * @return
     */
    public boolean needFulfillLonLat(HelmetShot shot) {
        float lon = shot.getLon();
        float lat = shot.getLat();
        if (lon == 0f && lat == 0f) {
            return true;
        }
        return false;
    }
}
