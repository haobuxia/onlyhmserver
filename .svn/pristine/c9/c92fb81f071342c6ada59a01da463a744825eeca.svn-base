package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.dao.file.HelmetShotDao;
import com.tianyi.helmet.server.dao.file.ImageDao;
import com.tianyi.helmet.server.entity.file.*;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 图片服务
 *
 * @author liuhan
 * @since 1.0
 */
@Service
public class ImageService extends HelmetShotService<Image> {
    private Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    private ImageDao imageDao;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private ConfigService configService;
    @Autowired
    private TagResourceService tagResourceService;
    @Autowired
    private KmxService kmxService;


    @Override
    public HelmetShotDao getDao() {
        return imageDao;
    }

    @Override
    public void insert(UploadEntity uploadEntity) {
        imageDao.insert((Image) uploadEntity);
    }

    @Override
    @Transactional
    public void insert(UploadEntity uploadEntity, String tag) {
        Image image = (Image) uploadEntity;
        if (!StringUtils.isEmpty(tag)) { //@todo 二手机用分组ID来区分，该功能暂时还未使用上
            if (ResCategoryContants.RECIRC_JH_CNNAME.equals(tag)) {
                image.setImageType(ImageCategoryEnum.RECIRC_JH.toString().toLowerCase());
            } else if (ResCategoryContants.WHITEBOARD_CNNAME.equals(tag)) {
                image.setImageType(ImageCategoryEnum.WHITEBOARD.toString().toLowerCase());
            }
        }
        insert(image);
        if (!StringUtils.isEmpty(tag)) {
            tagResourceService.insert(UploadEntityTypeEnum.image, image.getId(), tag);
        }
    }

    @Override
    public Image selectById(int id) {
        return imageDao.selectById(id);
    }

    //查询二手机照片
    public PageListVo<Image> listRecircImageList(int page, int pageSize, String machineCode, Date date1, Date date2) {
        Map<String, Object> params = PageListVo.createParamMap(page, pageSize);
        params.put("imageType", ImageCategoryEnum.RECIRC_JH.toString().toLowerCase());//小写 二手机
        params.put("machineCode", machineCode);
        params.put("createTime1", date1);
        params.put("createTime2", date2);
        List<Image> list = this.listBy(params);
        int total = this.countBy(params);
        PageListVo<Image> vo = new PageListVo<>(page, pageSize);
        vo.setList(list);
        vo.setTotal(total);
        return vo;
    }

    public Image getLatestWhiteBoardImage(String neUserName) {
        Map<String, Object> params = PageListVo.createParamMap(1, 1);
        params.put("neUserName", neUserName);
        params.put("imageType", ImageCategoryEnum.WHITEBOARD.toString().toLowerCase());
        List<Image> list = this.listBy(params);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public List<Image> listBy(Map params) {
        return (List<Image>) imageDao.listBy(params);
    }

    @Override
    public int countBy(Map params) {
        return imageDao.countBy(params);
    }


    @Override
    public int increaseViewCount(int id) {
        return imageDao.increaseViewCount(id);
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        tagResourceService.deleteByResTypeResId(UploadEntityTypeEnum.image.getId(), id);
        return imageDao.deleteById(id);
    }
    /**
     * update by xiayuan 2018/10/11
     */
    @Override
    public ResponseVo addNewFile(byte[] bytes, String origName, String suffix, String description, Date createTime,
                                 int userId,String clientId,String neUserName, String src, String machineCode, String tag, String orderNo, Float lon, Float lat, String caller, String called, String source) {
        String[] path = fastDfsClient.uploadFile(bytes, suffix, configService.getFastdfsRetryTimes());
        if (path == null) {
            logger.info("upload post.file save to oss failed.fileName=" + origName);
            return ResponseVo.fail("保存上传文件失败");
        }
        String ossPath = path[0] + "/" + path[1];
        String thumbOssPath = makePicThumbToOss(bytes);

        LocalDateTime createTimeDT = Dates.toLocalDateTime(createTime);
//        Helmet helmet = helmetService.getHelmetByImei(clientId);
//        String userId = null;
//        if (helmet != null) {
//            HelmetBindLog log = helmetBindLogService.getBindUserPhoneByHelmetIdAndTime(helmet.getId(), createTimeDT);
//            if (log != null) userId = log.getUserPhone();
//        }

        Image v = new Image();
        v.setFileName(origName);
        v.setDescription(description);
        v.setUploadTime(LocalDateTime.now());
        v.setCreateTime(createTimeDT);
        v.setClientId(clientId);
        /**
         * udpate by xiayuan 2018/10/11
         */
        v.setNeUserName(neUserName);

        v.setUserId(userId);
        v.setViewCount(0);
        v.setOssPath(ossPath);
        v.setThumbOssPath(thumbOssPath);
        v.setSizeKb(bytes.length / 1024);
        v.setOrderNo(orderNo);

        Float[] geo = kmxService.getHelmetGeo(v.getClientId(), createTime.getTime());
        this.updateLonLat(v.getId(), geo);
        logger.debug("设置图片定位信息.v.id=" + v.getId() + ",geo=" + (geo == null ? null : (geo[0] + "," + geo[1])));

        this.insert(v, tag);
        return ResponseVo.success(v);
    }

}
