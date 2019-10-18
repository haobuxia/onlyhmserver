package com.tianyi.helmet.server.controller.file;

import com.tianyi.helmet.server.entity.file.Video;
import com.tianyi.helmet.server.service.data.TyBoxDataService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.file.VideoService;
import com.tianyi.helmet.server.service.track.TrackService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.commons.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

/**
 * 字幕控制器
 *
 * Created by liuhanc on 2017/11/22.
 */
@Controller
@RequestMapping("track")
public class TrackController {

    private static final Logger logger = LoggerFactory.getLogger(TrackController.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private TyBoxDataService tyBoxDataService;
    @Autowired
    private FastDfsClient fastDfsClient;


    /**
     * 生成输出vtt字幕
     *
     * @param videoId
     * @param resp
     */
    @RequestMapping({"/{trackType}/{videoId}"})
    @ResponseBody
    public ResponseVo makeTrack(@PathVariable String trackType, @PathVariable Integer videoId, HttpServletResponse resp) throws Exception {
        Video entity = videoService.selectById(videoId);
        if (entity == null)
            return ResponseVo.fail("视频不存在:" + videoId);

        long seconds = entity.getSeconds();
        if (seconds <= 0) {
            return ResponseVo.fail("视频有问题,时长错误:" + seconds);
        }

        String str = trackService.make(entity, trackType);
        resp.setContentType("text/" + trackType + ";charset=utf-8");
        Streams.copy(new ByteArrayInputStream(str.getBytes()), resp.getOutputStream(), true);
        return null;
    }

    /**
     * 删除字幕版视频
     *
     * @param id
     * @param resp
     */
    @RequestMapping({"/delVideo/{id}"})
    @ResponseBody
    public ResponseVo delTrackVideo(@PathVariable Integer id, HttpServletResponse resp) throws Exception {
        Video entity = videoService.selectById(id);
        if (entity == null)
            return ResponseVo.fail("视频不存在:" + id);
        String ossPath = entity.getTrackVideoOssPath();
        if(StringUtils.isEmpty(ossPath)){
            return ResponseVo.fail("字幕版视频不存在:" + id);
        }

        int cnt = videoService.updateTrackVideoOssPathById(null,id);
        //删除字幕数据缓存
        tyBoxDataService.clearVideoGpsDataCache(id);
        //删除字幕缓存
        trackService.clearTrackCache(TrackService.SRT_NAME,id);
        trackService.clearTrackCache(TrackService.VTT_NAME,id);

        if(cnt == 1){
            boolean success = fastDfsClient.deleteFile(ossPath);
            return ResponseVo.success("视频文件删除结果:"+(success ? "成功" : "失败"));
        }
        return ResponseVo.fail("清除字幕版视频信息失败");
    }

}
