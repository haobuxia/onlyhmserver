package com.tianyi.helmet.server.controller.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sagittarius.bean.result.StringPoint;
import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.Company;
import com.tianyi.helmet.server.entity.data.GpsLineData;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.SgGpsDataTypeItemEnum;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.*;
import com.tianyi.helmet.server.entity.scene.Site;
import com.tianyi.helmet.server.service.client.CompanyService;
import com.tianyi.helmet.server.service.data.TyBoxDataLineService;
import com.tianyi.helmet.server.service.data.TyBoxDataService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.file.*;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.scene.SiteService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.util.RelationUtils;
import com.tianyi.helmet.server.util.ReqRespUtils;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.tianyi.helmet.server.service.kmx.SensorSupport.TYBOX_DEVICE_IDPREFIX;

/**
 * 资源播放和查看
 * Created by liuhanc on 2017/11/22.
 */
@Controller
@RequestMapping("")
public class PlayController {

    private static final Logger logger = LoggerFactory.getLogger(PlayController.class);

    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private TyBoxDataService tyBoxDataService;
    @Autowired
    private TyBoxDataLineService tyBoxDataLineService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private SiteService siteService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private VideoDataExtendService videoDataExtendService;
    @Autowired
    private VideoKeywordService videoKeywordService;
    @Autowired
    private KeyWordService keyWordService;
    @Autowired
    private ListFilterService listFilterService;
    @Autowired
    private KmxService kmxService;

    /**
     * 获得资源的查看路径
     *
     * @param type
     * @param resId
     * @return
     */
    @RequestMapping("/{type}/play/{resId}/url")
    @ResponseBody
    public ResponseVo<String> videoPlayUrl(@PathVariable String type, @PathVariable Integer resId) {
        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.valueOf(type);
        UploadEntity entity = uploadEntityServiceFactory.getEntity(typeEnum, resId);
        if (entity == null) {
            return ResponseVo.fail("资源不存在");
        }

        return ResponseVo.success(configService.getFastdfsServerUrl() + entity.getOssPath());
    }

    /**
     * 视频对应的chart工况数据
     *
     * @return
     */
    @RequestMapping({"/video/play/chartdata/{videoId}"})
    @ResponseBody
    public ResponseVo<DoubleVo<List<String>, Map<String, List<DoubleVo<Long, Object>>>>> videoChartData(@PathVariable Integer videoId) throws ParseException {
        UploadEntity entity = uploadEntityServiceFactory.getEntity(UploadEntityTypeEnum.video, videoId);
        if (entity == null)
            return ResponseVo.fail("视频不存在");

        Video video = (Video) entity;
        //string类型
        DoubleVo<String, Map<Integer, List<DoubleVo<Long, String>>>> doubleVo1 = tyBoxDataService.selectGpsDataListByVideo1(video);
        //int类型
        DoubleVo<String, Map<Integer, List<DoubleVo<Long, Integer>>>> doubleVo = tyBoxDataService.selectGpsDataListByVideo(video);
        Map<Integer, List<DoubleVo<Long, Integer>>> typeMapData = doubleVo.getVal();
        Map<Integer, List<DoubleVo<Long, String>>> typeMapData1 = doubleVo1.getVal();

        Map<String, List<DoubleVo<Long, Object>>> nameMapData = typeMapData.keySet().stream().map(typeId -> {
            GpsDataTypeItemEnum typeItemEnum = GpsDataTypeItemEnum.get(typeId);
            return typeItemEnum;
        }).filter(typeItemEnum -> typeItemEnum != null)
                .collect(Collectors.toMap(
                        typeEnum -> typeEnum.getName(),
                        typeEnum -> {
                            List<DoubleVo<Long, Integer>> list = typeMapData.get(typeEnum.getId());
                            List<DoubleVo<Long, Object>> list2 = list.stream().map(LongIntKv -> {
                                int origVal = LongIntKv.getVal();
                                Float ratioVal = new BigDecimal(typeEnum.getRatio() * origVal).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
                                return new DoubleVo<Long, Object>(LongIntKv.getKey(), new Float(ratioVal));
                            }).collect(Collectors.toList());
                            return list2;
                        }
                ));
        Map<String, List<DoubleVo<Long, Object>>> nameMapData1 = typeMapData1.keySet().stream().map(typeId -> {
            GpsDataTypeItemEnum typeItemEnum = GpsDataTypeItemEnum.get(typeId);
            return typeItemEnum;
        }).filter(typeItemEnum -> typeItemEnum != null)
                .collect(Collectors.toMap(
                        typeEnum -> typeEnum.getName(),
                        typeEnum -> {
                            List<DoubleVo<Long, String>> list = typeMapData1.get(typeEnum.getId());
                            List<DoubleVo<Long, Object>> list2 = list.stream().map(LongIntKv -> {
//                                int origVal = Integer.valueOf(LongIntKv.getVal());
//                                Float ratioVal = new BigDecimal(typeEnum.getRatio() * origVal).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
                                return new DoubleVo<Long, Object>(LongIntKv.getKey(), LongIntKv.getVal());
                            }).collect(Collectors.toList());
                            return list2;
                        }
                ));
        List<String> webChartsNameList = nameMapData.keySet().stream().sorted().collect(Collectors.toList());
        List<String> webChartsNameList2 = nameMapData1.keySet().stream().sorted().collect(Collectors.toList());
        webChartsNameList.addAll(webChartsNameList2);
        //动作数据输出到前端。因清华大数据需要，在时间有限的情况下此处粗糙处理输出以便清华能拿到

        Map<GpsDataTypeItemEnum, Map<Long, String>> gyroAndActionData = tyBoxDataService.selectActionGyroDataByVideo(video);
        List<DoubleVo<Long, Object>> gyroList;
        if(gyroAndActionData != null){
            Map<Long, String> gyroDataMap = gyroAndActionData.get(GpsDataTypeItemEnum.GYRO);
            gyroList = gyroDataMap.keySet().stream().sorted().map(time -> new DoubleVo<Long, Object>(time, gyroDataMap.get(time))).collect(Collectors.toList());
        }else{
            gyroList = new ArrayList<>();
        }
        Map<Long, String> actionDataMap = tyBoxDataService.selectActionDataByVideo(video);
        List<DoubleVo<Long, Object>> actionList = actionDataMap.keySet().stream().sorted().map(time -> new DoubleVo<Long, Object>(time, actionDataMap.get(time))).collect(Collectors.toList());
        if(actionList!=null){
            actionList = actionResover(actionList);
        }
        long time1 = Dates.toDate(video.getCreateTime()).getTime();
        long time2 = time1 + video.getSeconds() * 1000;
        List<StringPoint> gpsDataList = kmxService.queryStringRange(TYBOX_DEVICE_IDPREFIX + video.getImei(), "GPS_LOCATION", time1, time2);
        List<DoubleVo<Long,Object>> gpsList;
        if(gpsDataList!=null){
            gpsList = gpsResover(gpsDataList);
        }else{
            gpsList = new ArrayList<>();
        }
        nameMapData.put(GpsDataTypeItemEnum.ACTION.getName(), actionList);
        nameMapData.put(GpsDataTypeItemEnum.GYRO.getName(), gyroList);
        nameMapData.put(GpsDataTypeItemEnum.GPS_LOCATION.getName(), gpsList);
        /*if(gpsLocationData != null){
            for(DoubleVo d : gpsList){
                d.setVal(gpsLocationData);
            }
        }*/
//        nameMapData.put(GpsDataTypeItemEnum.GPS_LOCATION.getName(),gpsList);

        logger.debug("视频播放.v.id=" + videoId + ".得到视频对应传感器数据:tybox imei=" + doubleVo.getKey() + ",分类详情:" + JSON.toJSONString(webChartsNameList));

        return ResponseVo.success(new DoubleVo<>(webChartsNameList, nameMapData));
    }

    /**
     * 视频对应的chart工况数据
     *
     * @return
     */
    @RequestMapping({"/video/play/chartdata1/{videoId}"})
    @ResponseBody
    public ResponseVo<DoubleVo<List<String>, Map<String, List<DoubleVo<Long, Object>>>>> videoChartData1(@PathVariable Integer videoId) throws ParseException {
        UploadEntity entity = uploadEntityServiceFactory.getEntity(UploadEntityTypeEnum.video, videoId);
        if (entity == null)
            return ResponseVo.fail("视频不存在");

        Video video = (Video) entity;
          //int类型
        DoubleVo<String, Map<Integer, List<DoubleVo<Long, Integer>>>> doubleVo = tyBoxDataService.selectGpsDataListByVideo(video);
        Map<Integer, List<DoubleVo<Long, Integer>>> typeMapData = doubleVo.getVal();

        Map<String, List<DoubleVo<Long, Object>>> nameMapData = typeMapData.keySet().stream().map(typeId -> {
            SgGpsDataTypeItemEnum typeItemEnum = SgGpsDataTypeItemEnum.get(typeId);
            return typeItemEnum;
        }).filter(typeItemEnum -> typeItemEnum != null)
                .collect(Collectors.toMap(
                        typeEnum -> typeEnum.getName(),
                        typeEnum -> {
                            List<DoubleVo<Long, Integer>> list = typeMapData.get(typeEnum.getId());
                            List<DoubleVo<Long, Object>> list2 = list.stream().map(LongIntKv -> {
                                int origVal = LongIntKv.getVal();
                                Float ratioVal = new BigDecimal(typeEnum.getRatio() * origVal).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
                                return new DoubleVo<Long, Object>(LongIntKv.getKey(), new Float(ratioVal));
                            }).collect(Collectors.toList());
                            return list2;
                        }
                ));

        List<String> webChartsNameList = nameMapData.keySet().stream().sorted().collect(Collectors.toList());
        //动作数据输出到前端。因清华大数据需要，在时间有限的情况下此处粗糙处理输出以便清华能拿到

//        long time1 = Dates.toDate(video.getCreateTime()).getTime();
//        long time2 = time1 + video.getSeconds() * 1000;
//        List<StringPoint> gpsDataList = kmxService.queryStringRange(TYBOX_DEVICE_IDPREFIX + video.getImei(), "GPS_LOCATION", time1, time2);
//        List<DoubleVo<Long,Object>> gpsList;
//        if(gpsDataList!=null){
//            gpsList = gpsResover(gpsDataList);
//        }else{
//            gpsList = new ArrayList<>();
//        }
//        nameMapData.put(GpsDataTypeItemEnum.ACTION.getName(), actionList);
//        nameMapData.put(GpsDataTypeItemEnum.GYRO.getName(), gyroList);
//        nameMapData.put(GpsDataTypeItemEnum.GPS_LOCATION.getName(), gpsList);
        /*if(gpsLocationData != null){
            for(DoubleVo d : gpsList){
                d.setVal(gpsLocationData);
            }
        }*/
//        nameMapData.put(GpsDataTypeItemEnum.GPS_LOCATION.getName(),gpsList);

        logger.debug("视频播放.v.id=" + videoId + ".得到视频对应传感器数据:tybox imei=" + doubleVo.getKey() + ",分类详情:" + JSON.toJSONString(webChartsNameList));

        return ResponseVo.success(new DoubleVo<>(webChartsNameList, nameMapData));
    }
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String[] txt2String(File file){
        StringBuilder result = new StringBuilder();
        List<String> list = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//                result.append(System.lineSeparator()+s);
                list.add(s);
            }

            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        String[] array = new String[list.size()];
        String[] s=list.toArray(array);
        return s;
    }
    /**
     * 视频的播放
     *
     * @return
     */
    @RequestMapping({"/video/play/{videoId}"})
    public String videoPlay(@PathVariable Integer videoId, Model model) {
        UploadEntity entity = uploadEntityServiceFactory.getEntity(UploadEntityTypeEnum.video, videoId);
        if (entity == null)
            return "common/content404";

        Video video = (Video) entity;
        videoService.fullfilLonlat(video);
        videoService.fullfilThumbOssPath(video);

//        if (videoComponent.isSvcDataVideo(video)) {
//            //服务数据类的视频采用另外的界面播放
//            return "redirect:/videoext/play/" + videoId;
//        }

        ///** 注销将工况数据写入视频文件中20190908
        //查询工况数据
//        if (video.getHasGpsData() == null || video.getHasGpsData() == 1) {
        DoubleVo<String, Map<Integer, List<DoubleVo<Long, Integer>>>> doubleVo = tyBoxDataService.selectGpsDataListByVideo(video);
        String imei = doubleVo.getKey();
        Map<Integer, List<DoubleVo<Long, Integer>>> typeMapData = doubleVo.getVal();

        int hasGpsData = typeMapData.size() > 0 ? 1 : 0;
        if (hasGpsData != video.getIntHasGpsData() || !Objects.equals(video.getImei(), imei)) {
            video.setHasGpsData(hasGpsData);
            video.setImei(imei);
            videoService.updateHasGpsDataById(video);
        }

        if (1 == video.getHasGpsData()) {
            if (StringUtils.isEmpty(video.getTrackVideoOssPath())) {
                //有字幕数据但是没有字幕视频则补充生成
                //logger.info("视频有传感器数据但是没有字幕版，则加入生成队列.v.id=" + videoId);
                //videoService.addToTrackQueue(videoId);
            } else if (!StringUtils.isEmpty(video.getTrackVideoOssPath()) && typeMapData.size() == 0) {
                //有字幕视频了,但mysql历史数据没有导入到kmx中
                //logger.info("视频已经存在字幕版，但是kmx中没有原始数据，则加入同步队列.v.id=" + videoId);
                //videoService.addToGpsDataSyncQueue(videoId);
            }

            //视频有蓝牙盒子数据，则可能有时间标签信息，载入  @todo 此功能似乎头盔端还未上线
//                tagList = videoTimeTagService.selectByVideoName(video.getFileName());
//                if (tagList != null && tagList.size() > 0) {
//                    VideoTimeTag tag = tagList.get(0);
//                    if (tag.getVideoId() == null || tag.getVideoId().intValue() != video.getId()) {
//                        videoTimeTagService.updateVideoIdByName(video.getId(), video.getFileName());
//                    }
//                }
        }
        //注销将工况数据写入视频文件中20190908end*/
//        }

        //视频语音识别
        VideoDataExtend extend = videoDataExtendService.selectByVideoId(videoId);
        if (extend == null) {
            extend = new VideoDataExtend();
            extend.setVideoId(videoId);
            videoDataExtendService.insert(extend);
            //任务加入到语音识别队列中
            videoService.addToAsrProcessQueue(videoId);
        }

        //关键词设置
        List<VideoKeyword> keywordList = videoKeywordService.selectByVideoId(videoId);
        RelationUtils.fullfilListRelateProperty(keywordList, VideoKeyword::getKeywordId, listFilterService::selectKeyWordListByIdList, KeyWord::getId, KeyWord::getKeywordName, VideoKeyword::setKeywordName);

        //填充头盔信息到model属性中
        fillHelmetInfoToModel(entity.getClientId(), model);

        List<KeyWord> kwList = keyWordService.selectKeyWordList();
        model.addAttribute("userInfo", LoginUserHolder.get());
//        model.addAttribute("timeTagListData", JSON.toJSONString(tagList));
        model.addAttribute("type", UploadEntityTypeEnum.video.toString());
        model.addAttribute("entity", entity);
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        model.addAttribute("videoExtend", JSON.toJSONString(extend));
        model.addAttribute("keywordList", keywordList);
        model.addAttribute("keywordListStr", JSON.toJSONString(keywordList));
        model.addAttribute("kwListStr", JSON.toJSONString(kwList));
        if(1 == video.getHasGpsData() && "600s".equals(video.getDescription())) {
            return "file/viewVideoChart1"; // 600s查看界面
        } else if (1 == video.getHasGpsData()) {
            return "file/viewVideoChart";
        } else {
            return "file/viewVideo";
        }
//        if (1 == video.getHasGpsData()) {
//            return "file/viewTyBoxVideo";
//        }else{
//            return "file/viewPlainVideo";
//        }
    }


    /**
     * 图片的查看
     *
     * @return
     */
    @RequestMapping({"/image/play/{imgId}"})
    public String playImage(@PathVariable Integer imgId, Model model) {
        UploadEntity entity = uploadEntityServiceFactory.getEntity(UploadEntityTypeEnum.image, imgId);
        if (entity == null)
            return "common/content404";

        Image img = (Image) entity;
        imageService.fullfilLonlat(img);
        imageService.fullfilThumbOssPath(img);

        //图片所在工地
        if (img.getSiteId() != null && img.getSiteId().intValue() > 0) {
            Site site = siteService.selectById(img.getSiteId().intValue());
            img.setSite(site);
        }

        fillHelmetInfoToModel(entity.getClientId(), model);
        model.addAttribute("type", UploadEntityTypeEnum.image.toString());
        model.addAttribute("entity", entity);
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
//        return "file/viewImage";
        return "file/newViewImage";
    }

    /**
     * 音频和文件的查看
     *
     * @param type
     * @param resId
     * @param model
     * @return
     */
    @RequestMapping({"/{type}/play/{resId}"})
    public String play(@PathVariable String type, @PathVariable Integer resId, Model model) {
        UploadEntityTypeEnum typeEnum = UploadEntityTypeEnum.valueOf(type);
        UploadEntity entity = uploadEntityServiceFactory.getEntity(typeEnum, resId);
        if (entity == null)
            return "common/content404";

        fillHelmetInfoToModel(entity.getClientId(), model);
        model.addAttribute("fileServer", configService.getFastdfsServerUrl());
        model.addAttribute("type", type);
        model.addAttribute("entity", entity);
        return "file/viewFile";
    }

    /**
     * 将资源对应的头盔信息补充到Model中以便网页显示
     *
     * @param uploadEntityClientId
     * @param model
     */
    private void fillHelmetInfoToModel(String uploadEntityClientId, Model model) {
        /**
         * update by xiayuan 2018/10/10
         */
        if (!"_backend_".equals(uploadEntityClientId)) {
            EquipmentLedger helmet = equipmentService.selectByUUID(uploadEntityClientId);
            if (helmet != null) {
                if (helmet.getAffiliationId() > 0) {
                    Company company = companyService.selectById(helmet.getAffiliationId());
                    helmet.setAffiliationName(company.getCompanyName());
                }
                model.addAttribute("helmet", helmet);
            }
        }
    }

    /**
     * 查看gps文件的具体信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping({"/file/play/detail/{id}"})
    public String viewFileList(@PathVariable Integer id, HttpServletResponse resp, Model model) throws IOException {
        List<GpsLineData> gpsLineDataList = tyBoxDataLineService.selectByFileId(id);
        if (CollectionUtils.isEmpty(gpsLineDataList)) {
            //直接下载
            UploadEntity entity = uploadEntityServiceFactory.getEntity(UploadEntityTypeEnum.file, id);
            if (entity == null)
                return "common/content404";
            ReqRespUtils.downloadFdfsFile(resp, fastDfsClient, entity.getOssPath(), "text/plain;charset=utf-8", entity.getFileName());
            return null;
        } else {
            //列表展示
            Map<String, DoubleVo> typeMap = Arrays.stream(GpsDataTypeItemEnum.values()).collect(Collectors.toMap(typeEnum -> {
                return "t_" + typeEnum.getId();
            }, typeEnum -> {
                return new DoubleVo<>(typeEnum.getName(), typeEnum.getRatio());
            }));
            model.addAttribute("list", gpsLineDataList);
            model.addAttribute("typeMap", JSON.toJSONString(typeMap));
            return "file/listGpsLine";
        }
    }

    private List<DoubleVo<Long, Object>> actionResover(List<DoubleVo<Long, Object>> actionList) {
        List<DoubleVo<Long, Object>> resultList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("动臂上升");
        list.add("动臂下降");
        list.add("斗杆回收");
        list.add("斗杆伸出");
        list.add("铲斗挖掘");
        list.add("铲斗卸载");
        list.add("回转");
        list.add("行走");
        for (DoubleVo d : actionList) {
            JSONObject json = JSONObject.parseObject((String) d.getVal());
            String actionValString = (String) json.get("actionVal");
            logger.debug("before:"+actionValString);
            actionValString = new StringBuffer(actionValString).reverse().toString();
            logger.debug("after:"+actionValString);
            Integer action = (Integer) json.get("action");
            if(action == 1){
                resultList.add(d);
                continue;
            }
            String actionVal = "";
            for (int i = 0; i < actionValString.length(); i++) {
                if ('1' == actionValString.charAt(i)) {
                    actionVal += list.get(i);
                    System.out.println("动作：：：："+actionVal);
                }
            }
            Integer walk = (Integer) json.get("walk");
            if (0 == walk) {
                actionVal = actionVal + ",同时动作";
            }
            d.setVal(((String) d.getVal()).replace(actionValString, actionVal));
            resultList.add(d);
        }
        return resultList;
    }

    private List<DoubleVo<Long,Object>> gpsResover(List<StringPoint> gpsDataList) throws ParseException {
        List<DoubleVo<Long, Object>> gpsList = new ArrayList<>();
        for (StringPoint s : gpsDataList) {
            String jsonString = s.getValue();
            JSONObject json = JSONObject.parseObject(jsonString);
            String timeString = (String) json.get("gpsTime");
            timeString = timeString.replace("T", " ");
            if (timeString.length() < 19) {
                timeString = timeString + ":00";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(timeString);
            Long time = date.getTime();
            DoubleVo<Long, Object> d = new DoubleVo<>();
            d.setKey(time);
            d.setVal(json);
            gpsList.add(d);
        }
        return gpsList;
    }

}
