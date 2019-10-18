package com.tianyi.helmet.server.controller.file;

import com.tianyi.helmet.server.entity.file.Image;
import com.tianyi.helmet.server.entity.file.UploadEntity;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.file.UploadEntityService;
import com.tianyi.helmet.server.service.file.UploadEntityServiceFactory;
import com.tianyi.helmet.server.service.file.VideoComponent;
import com.tianyi.helmet.server.service.file.VideoDataExtendService;
import com.tianyi.helmet.server.service.scene.VideoActionService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.commons.fileupload.util.Streams;
import org.csource.fastdfs.StorageClient1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 *  上传文件管理
 *
 * Created by liuhanc on 2017/10/9.
 */
@Controller
@RequestMapping("/uploadfile")
public class UploadFileController {

    private Logger logger = LoggerFactory.getLogger(UploadFileController.class);
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private VideoComponent videoComponent;
    @Autowired
    private VideoDataExtendService videoDataExtendService;
    @Autowired
    private VideoActionService videoActionService;

    /**
     * 进入文件上传页面
     * @param type
     * @param model
     * @return
     */
    @RequestMapping(value = "add/{type}", method = RequestMethod.GET)
    public String upload(@PathVariable String type, Model model) {
        logger.debug("FileController upload get.enter upload form page");
        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.valueOf(type);
        model.addAttribute("supportTypes", Arrays.toString(uploadEntityServiceFactory.getSupportedTypes(typeEnum)));
        model.addAttribute("inputName", uploadEntityServiceFactory.getUploadFileInputName(type));
        model.addAttribute("type", type);

        return "file/addFile";
    }

//    /**
//     * 输出video的预览图片以及image的缩略图
//     *
//     * @param url
//     * @param resp
//     * @throws Exception
//     */
//    @RequestMapping(value = "/thumbnail/{type}", method = RequestMethod.GET)
//    @ResponseBody
//    public Object thumbnail(@PathVariable String type, String url, HttpServletResponse resp) throws Exception {
//        if (StringUtils.isEmpty(url)) {
//            return ResponseVo.fail("url is illegal,can not output");
//        }
//
//        //是否已经有缩略图,没有则生成缩略图
//        String thumbnailUrl = "thumbnail" + File.separator + url;
//        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.valueOf(type);
//
//        File thumbnailFile = uploadEntityServiceFactory.getSavedFile(typeEnum, thumbnailUrl);
//        if (thumbnailFile != null && thumbnailFile.exists() && thumbnailFile.isFile()) {
//            //输出给客户
//            Streams.copy(new FileInputStream(thumbnailFile), resp.getOutputStream(), true);
//            return null;
//        }
//
////        logger.debug("缩略图文件不存在，进行生成."+thumbnailFile.getAbsolutePath());
//        File imageFile = uploadEntityServiceFactory.getSavedFile(typeEnum, url);
//        InputStream is = null;
//        if (imageFile != null && imageFile.exists() && imageFile.isFile()) {
////            logger.debug("原始文件存在，进行生成."+imageFile.getAbsolutePath());
//            is = new FileInputStream(imageFile);
//        } else {
////            logger.debug("原始文件不存在，从oss获取。路径."+imageFile.getAbsolutePath());
//            ByteArrayOutputStream byos = new ByteArrayOutputStream();
//            boolean success = fastDfsClient.downloadFile(url, byos);
//            if (!success) {
//                return ResponseVo.fail("output oss image file failed." + url);
//            }
//            is = new ByteArrayInputStream(byos.toByteArray());
//        }
//
//        ByteArrayOutputStream byos = new ByteArrayOutputStream();
//        ImageUtils.fromSteram(is)        //设置原图片
//                //.width(200)			//设置生成图片的宽度，高度将以原图片的高度等比例伸缩
//                //.height(200)			//设置生成图片的高度，宽度将以原图片的宽度等比例伸缩
//                //.scale(1)				//设置生成图片的伸缩比例
//                .size(configService.getThumbnailWidth(), configService.getThumbnailHeight())        //设置生成图片的宽度和高度
//                .keepRatio(true)
////                .rotate(34)				//设置原图片的旋转角度
////                .watermark(watermark)	//设置水印
//                //.watermarkArray(list)	//设置多个水印
//                .bgcolor(Color.YELLOW)    //设置背景颜色，如果为null，表示不添加背景颜色，如果图片为png，为透明颜色
//                .quality(0.6f)            //设置压缩比例，默认为0.75
//                .toOutput(byos, "jpg");    //生成图片的路径
//
//        //输出到缩略图文件
//        thumbnailFile.getParentFile().mkdirs();
//        Streams.copy(new ByteArrayInputStream(byos.toByteArray()), new FileOutputStream(thumbnailFile), true);
//        //输出给客户
//        Streams.copy(new FileInputStream(thumbnailFile), resp.getOutputStream(), true);
//
//        return null;
//    }

//    /**
//     * 输出oss的某个文件
//     *
//     * @param url
//     * @param resp
//     * @throws Exception
//     */
//    @RequestMapping(value = "/output", method = RequestMethod.GET)
//    @ResponseBody
//    public Object output(String url, HttpServletResponse resp) throws Exception {
//        //oss文件
//        int slashIdx = url.indexOf(StorageClient1.SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR);
//        if (slashIdx <= 0) {
//            logger.error("url is illegal,can not output.");
//            return ResponseVo.fail("url is illegal,can not output");
//        }
//        try {
//            fastDfsClient.downloadFile(url, resp.getOutputStream());
//        } catch (Exception e) {
//            logger.error("输出oss文件异常.file=" + url, e);
//        }
//        return null;
//    }
//
//    /**
//     * 输出oss的某个文件
//     *
//     * @param url
//     * @param resp
//     * @throws Exception
//     */
//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    @ResponseBody
//    public Object download(String url, HttpServletResponse resp) throws Exception {
//        //oss文件
//        int slashIdx = url.indexOf(StorageClient1.SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR);
//        if (slashIdx <= 0) {
//            logger.error("url is illegal,can not output.");
//            return "文件不存在";
//        }
//
//
//        int dotIdx = url.lastIndexOf(".");
//        String ext = "";
//        if (dotIdx > 0) {
//            ext = url.substring(dotIdx);
//        }
//
//        String fileName = new String(("工况信息" + ext).getBytes("GBK"), "iso-8859-1");
//        resp.setContentType("text/plain;charset=utf-8");
//        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//
//        try {
//            fastDfsClient.downloadFile(url, resp.getOutputStream());
//        } catch (Exception e) {
//            logger.error("输出oss文件异常.file=" + url, e);
//        }
//        return null;
//    }

//    /**
//     * 输出本地某类型的某个文件
//     *
//     * @param type
//     * @param url
//     * @param resp
//     * @throws Exception
//     */
//    @RequestMapping(value = "/output/{type}", method = RequestMethod.GET)
//    @ResponseBody
//    public Object output(@PathVariable String type, String url, HttpServletResponse resp) throws Exception {
//        if (StringUtils.isEmpty(url)) {
//            logger.error("url is empty,can not output.");
//            return ResponseVo.fail("url is empty,can not output");
//        }
//
//        //本地文件
//        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.valueOf(type);
//        File file = uploadEntityServiceFactory.getSavedFile(typeEnum, url);
//        if (file != null && !file.exists()) {
//            logger.error("file is not exist,can not output.");
//            return ResponseVo.fail("file is not exist,can not output");
//        }
//
//        Streams.copy(new FileInputStream(file), resp.getOutputStream(), true);
//        return null;
//    }


    /**
     * 删除某类型的某个文件
     *
     * @param type
     * @param id
     * @return
     */
    @RequestMapping("delete/{type}/{id}")
    @ResponseBody
    public ResponseVo<String> delete(@PathVariable String type, @PathVariable Integer id) {
        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.valueOf(type);
        UploadEntityService service = uploadEntityServiceFactory.getUploadEntityService(typeEnum);
        UploadEntity entity = service.selectById(id);
        if (entity == null)
            return ResponseVo.fail("文件不存在");

        //删除db记录
        service.deleteById(id);

        //删除oss存储
        String ossPath = entity.getOssPath();
        if (!StringUtils.isEmpty(ossPath)) {
            fastDfsClient.deleteFile(ossPath);
            if (entity instanceof Image) {
                //删除缩略图
                File thumbFile = uploadEntityServiceFactory.getSavedFile(typeEnum, "thumbnail" + File.separator + ossPath);
                if (thumbFile != null && thumbFile.exists())
                    thumbFile.delete();
            }
        }

        //视频、图片还有本地存储
        if (entity instanceof Image) {
            //删除图片的本地存储和图片的缩略图
            Image im = (Image) entity;
            String thumbPath = im.getThumbOssPath();
            if (!StringUtils.isEmpty(thumbPath)) {
                fastDfsClient.deleteFile(thumbPath);
            }
        } else if (entity instanceof Video) {
            //删除视频的截图图片和图片的缩略图、字幕版视频
            Video vd = (Video) entity;
            String thumbPath = vd.getThumbOssPath();
            if (!StringUtils.isEmpty(thumbPath)) {
                fastDfsClient.deleteFile(thumbPath);
            }

            String picOssPath = vd.getPicOssPath();
            if (!StringUtils.isEmpty(picOssPath)) {
                fastDfsClient.deleteFile(picOssPath);
            }

            String trackOssPath = vd.getTrackVideoOssPath();
            if (!StringUtils.isEmpty(trackOssPath)) {
                fastDfsClient.deleteFile(trackOssPath);
            }

            //服务体系视频
//            if (videoComponent.isSvcDataVideo(vd)) {
                videoDataExtendService.deleteByVideoId(vd.getId());
//            }

            videoActionService.deleteByVideoId(id);
        }

        return ResponseVo.success("删除成功");
    }
}
