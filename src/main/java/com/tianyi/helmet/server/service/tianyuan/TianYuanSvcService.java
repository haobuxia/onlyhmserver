package com.tianyi.helmet.server.service.tianyuan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.svc.SvcResTypeEnum;
import com.tianyi.helmet.server.service.svc.SvcIdRestService;
import com.tianyi.helmet.server.service.svc.SvcMetaDataHelper;
import com.tianyi.helmet.server.service.svc.SvcResUploadHelper;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.ResponseVo;
import com.tianyi.svc.rest.entity.*;
import com.tianyi.svc.rest.entity.base.SvcCommEntity;
import com.tianyi.svc.rest.entity.base.SvcMainEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 天远服务日志接口
 * <p>
 * Created by liuhanc on 2018/3/27.
 */
@Service
public class TianYuanSvcService {

    @Autowired
    private TianYuanService tianYuanService;
    @Autowired
    private TianYuanSvcJsonHelper tianYuanSvcJsonHelper;
    @Autowired
    private SvcIdRestService svcIdRestService;
    @Autowired
    private SvcResUploadHelper svcResUploadHelper;

    private static final Date Max_Date = Dates.toDate(LocalDate.of(2030, 12, 31));

    private Logger logger = LoggerFactory.getLogger(TianYuanSvcService.class);

    //拉取任务
    public List<SvcTask> getSvcTask(String oprtId, String accessToken, Date sendTime) {
        Map<String, String> params = new HashMap(4);
        params.put("AccountID", oprtId);
        params.put("TokenSign", accessToken);
        params.put("Version", "1");
        params.put("SendTime", Dates.format(sendTime, "yyyy-MM-dd'T'HH:mm:ss.SSS"));
        JSONObject jsonObject = tianYuanService.svcServiceInvoke("GetSvcTask", params);
        if (jsonObject != null) {
            int code = jsonObject.getInteger("code");
            if(code != 0){
                throw new TianYuanException("调用天远接口失败。error="+jsonObject.getString("comment"));
            }

            JSONArray taskArray = jsonObject.getJSONArray("SvcTask");
//            JSONArray yhxxArray = jsonObject.getJSONArray("SvcTaskYhxx");//@todo 作用是什么? 天远没答复
            int size = taskArray.size();
            List<SvcTask> taskList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                JSONObject jsonObject1 = taskArray.getJSONObject(i);
                try {
                    SvcTask task = new SvcTask();
                    tianYuanSvcJsonHelper.parse(jsonObject1, task);
                    if (StringUtils.isEmpty(task.getStatus()))
                        task.setStatus("未派工");
                    if (task.getExp() == null) {
                        task.setExp(Max_Date);
                    }
                    taskList.add(task);
                } catch (Exception e) {
                    logger.error("解析天远服务日志任务数据异常", e);
                }
            }
            return taskList;
        }
        return Collections.emptyList();
    }

    //拉取消息
    public List<SvcMsg> getSvcMsg(String oprtId, String accessToken, Date sendTime) {
        Map<String, String> params = new HashMap(4);
        params.put("AccountID", oprtId);
        params.put("TokenSign", accessToken);
        params.put("Version", "1");
        params.put("SendTime", Dates.format(sendTime, "yyyy-MM-dd'T'HH:mm:ss.SSS"));
        JSONObject jsonObject = tianYuanService.svcServiceInvoke("GetSvcMsg", params);
        if (jsonObject != null) {
            int code = jsonObject.getInteger("code");
            if(code != 0){
                throw new TianYuanException("调用天远接口失败。error="+jsonObject.getString("comment"));
            }

            JSONArray array = jsonObject.getJSONArray("SvcMsg");
            int size = array.size();
            List<SvcMsg> taskList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                JSONObject jsonObject1 = array.getJSONObject(i);
                try {
                    SvcMsg msg = new SvcMsg();
                    tianYuanSvcJsonHelper.parse(jsonObject1, msg);
                    taskList.add(msg);
                } catch (Exception e) {
                    logger.error("解析天远服务日志消息数据异常", e);
                }
            }
            return taskList;
        }
        return Collections.emptyList();
    }

    //    上传照片
    public ResponseVo postPhoto(File imgFile, String rwh, String svcId, boolean deleteOrigFile) {
        Map<String, Object> params = new HashMap();
        params.put("file", imgFile);
        params.put("rwh", rwh);
        params.put("photoName", imgFile.getName());

        JSONObject jsonObject = tianYuanService.svcServiceInvoke("PostPhoto", params);
        logger.debug("上传图片 rwh="+rwh+",svcId="+svcId+"结果反馈:" + jsonObject.toJSONString());
        ResponseVo vo = parsePostResponse(jsonObject,1);//成功是1
        if (vo.isSuccess()) {
            if (deleteOrigFile)
                imgFile.delete();
        } else {
            logger.error("上传图片到天远服务器失败.rwh=" + rwh + ",svcId=" + svcId + ",file=" + imgFile.getAbsolutePath() + ",error=" + vo.getMessage());
        }
        return vo;
    }

    //提交故障处理
    public <T extends SvcMainEntity, R extends SvcCommEntity> ResponseVo postGzcl(String oprtId, String accessToken, T mainEntity, List<R> commDataList, List<SvcCommPic> picList) {
        JSONObject jo = new JSONObject();
        jo.put("AccountID", oprtId);
        jo.put("TokenSign", accessToken);
        jo.put("Version", "1");
        jo.put("code", "0");
        jo.put("comment", "田一头盔填报");

        //主数据
        String gzclMainJson = tianYuanSvcJsonHelper.toJson(mainEntity);//序列化成json,但是key用列名而不是属性名
        JSONObject gzclMainJsonObject = JSON.parseObject(gzclMainJson);
        jo.put(SvcGzclMain.class.getSimpleName(), Arrays.asList(gzclMainJsonObject));

        //附加其他单个数据
        if (commDataList != null && commDataList.size() > 0) {
            for (R commData1 : commDataList) {
                String commJson = tianYuanSvcJsonHelper.toJson(commData1);
                JSONObject commJsonObject = JSON.parseObject(commJson);
                jo.put(commData1.getClass().getSimpleName(), Arrays.asList(commJsonObject));
            }
        }

        //抵扣费项
        jo.put(SvcCommDkfx.class.getSimpleName(), Collections.emptyList());//空数据
        //故障处理外协费用
        jo.put(SvcGzclWxfy.class.getSimpleName(), Collections.emptyList());//空数据

        //附加多个图片数据,先检查是否都上传完毕了
        if (picList != null && picList.size() > 0) {
            List<JSONObject> picJsonList = picList.stream().map(pic -> {
                String picJson = tianYuanSvcJsonHelper.toJson(pic);
                JSONObject commJsonObject = JSON.parseObject(picJson);
                return commJsonObject;
            }).collect(Collectors.toList());
            jo.put(SvcCommPic.class.getSimpleName(), picJsonList);
        }

        //最终提交的json
        String finalJson = jo.toJSONString();
        logger.debug("故障处理提交,json=" + finalJson);
        Map<String,String> param = new HashMap<>(1);
        param.put("para",finalJson);
        JSONObject jsonObject = tianYuanService.svcServiceInvoke("PostGzcl", param);
        return parsePostResponse(jsonObject,0);//成功是0
    }

    //检查服务日志照片是否已上传，如未上传则执行上传.返回true表示上传成功
    public boolean checkUploadCommPic(SvcMainEntity mainEntity, SvcCommPic pic) {
        String scbz = pic.getScbz();
        if ("2".equals(scbz)) { //已上传
            return true;
        }

        String ossPath = pic.getPhotoUrl();
        if (StringUtils.isEmpty(ossPath))
            return false;

        File imgFile = svcResUploadHelper.downloadFdfsFile(ossPath, SvcResTypeEnum.image);
        try {
            ResponseVo vo = this.postPhoto(imgFile, pic.getRwh(), pic.getSvcId(), true);
            if (vo.isSuccess()) {
                //pic标志
                pic.setScbz("2");//0未上传 1正在上传 2已上传
                pic.setPhotoName(imgFile.getName());
                svcIdRestService.updatSvcIdEntity(SvcCommPic.class, pic);

                //主数据的照片上传信息
                SvcMetaDataHelper.SvcFaultPicMeta meta = SvcMetaDataHelper.SvcFaultPicMeta.getByFieldNameEn(pic.getFieldNameEn());
                if (meta != null) {
                    svcResUploadHelper.updateSvcPhotoUploadInfo(mainEntity, meta);
                }

                return true;
            } else {
                logger.error("上传工单照片失败.rwh=" + pic.getRwh() + ",svcId=" + pic.getSvcId() + ",error=" + vo.getMessage());
                return false;
            }
        } catch (Exception e) {
            logger.error("上传工单照片异常.rwh=" + pic.getRwh() + ",svcId=" + pic.getSvcId(), e);
            return false;
        }
    }

    protected ResponseVo parsePostResponse(JSONObject jsonObject,int successCode) {
        if (jsonObject == null) {
            return ResponseVo.fail("接口无反馈");
        }
        int code = jsonObject.getInteger("code");
        /**
         * 成功：1
         失败：0
         */
        if (code == successCode) {
            //成功
            return ResponseVo.success();
        } else {
            return ResponseVo.fail(jsonObject.getString("comment"));
        }
    }

}
