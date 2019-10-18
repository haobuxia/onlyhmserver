package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.svc.SvcImage;
import com.tianyi.helmet.server.entity.svc.SvcImageTypeEnum;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.svc.SvcImageService;
import com.tianyi.helmet.server.service.svc.SvcMetaDataHelper;
import com.tianyi.helmet.server.service.svc.SvcResUploadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 解析上传的照片
 * <p>
 * Created by liuhanc on 2018/4/2.
 */
@Component
public class SvcImageParseJob implements MessageListener {
    @Autowired
    private SvcImageService svcImageService;
    @Autowired
    private SvcResUploadHelper svcResUploadHelper;
    @Autowired
    private FastDfsClient fastDfsClient;

    private Logger logger = LoggerFactory.getLogger(SvcImageParseJob.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("SvcImageParseJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            Integer id = Integer.parseInt(body);
            SvcImage image = svcImageService.selectById(id);
            doParse(image, false);//不强制刷新
        } catch (Exception e) {
            logger.error("处理服务日志照片解析队列消息异常.", e);
        }
    }


    private void deleteSvcRes(SvcImage v) {
        String imageType = v.getImageType();
        if (SvcImageTypeEnum.jihao.toString().equals(imageType)) {
            //删除生成的图片
            svcResUploadHelper.deleteSvcPics(v);
        }

        //删除数据
        svcImageService.deleteById(v.getId());

        //删除文件
        String ossPath = v.getOssPath();
        if (org.springframework.util.StringUtils.isEmpty(ossPath)) {
            return;
        }
        fastDfsClient.deleteFile(ossPath);
    }

    public void doParse(SvcImage image, boolean force) {
        if (image == null) {
            logger.debug("图片信息不存在，不处理");
            return;
        }

        //之前上传的同类型视频要删除掉
        List<SvcImage> resList = svcImageService.selectByRwhTypeOprtId(image.getRwh(), image.getImageType(), image.getOprtId());
        if (!CollectionUtils.isEmpty(resList)) {
            resList.stream().filter(tempImage -> tempImage.getId() != image.getId()).forEach(tempImage -> {
                //其他同类型的资源要删除
                //删除记录、删除图片、删除生成的图片
                boolean success = false;
                try {
                    deleteSvcRes(tempImage);
                    success = true;
                } catch (Exception e) {
                    success = false;
                    logger.error("删除之前上传的同类型图片.id=" + tempImage.getId() + ",rwh=" + image.getRwh() + ",imageType=" + image.getImageType() + ",oprtId=" + image.getOprtId() + ",异常", e);
                } finally {
                    logger.debug("删除之前上传的同类型图片.id=" + tempImage.getId() + ",rwh=" + image.getRwh() + ",imageType=" + image.getImageType() + ",oprtId=" + image.getOprtId() + ",结果=" + (success ? "成功" : "失败"));
                }
            });
        }

        SvcMetaDataHelper.SvcFaultPicMeta jhMeta = SvcMetaDataHelper.SvcFaultPicMeta.JIHAO;
        if (!jhMeta.name().equalsIgnoreCase(image.getImageType())) {
            logger.debug("图片信息类型不需处理.imageType = " + image.getImageType() + ",image.id=" + image.getId());
            return;
        }

        //生成服务日志图片记录并上传图片
        try {
            svcResUploadHelper.createSvcCommPic(image.getRwh(), image.getSvcId(), jhMeta, image.getOssPath(), "");
        } catch (Exception e) {
            logger.error("处理上传的服务日志照片异常.image.id=" + image.getId() + ",rwh=" + image.getRwh() + ",svcId=" + image.getSvcId() + ",oprtId=" + image.getOprtId(), e);
        }
    }
}
