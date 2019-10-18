package com.tianyi.helmet.server.controller.helmetinterface;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.interceptor.TianYuanUserHolder;
import com.tianyi.helmet.server.entity.svc.*;
import com.tianyi.helmet.server.entity.svc.po.*;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.svc.*;
import com.tianyi.helmet.server.service.tianyuan.TianYuanSvcService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import com.tianyi.svc.rest.entity.*;
import com.tianyi.svc.rest.entity.base.SvcCommEntity;
import com.tianyi.svc.rest.entity.base.SvcMainEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 头盔端服务日志接口
 * <p>
 * Created by liuhanc on 2018/3/16.
 */
@Controller
@RequestMapping("svc")
public class SvcDataController {
    @Autowired
    private SvcMainRestService svcMainRestService;
    @Autowired
    private SvcCommRestService svcCommRestService;
    @Autowired
    private SvcResUploadHelper svcResUploadHelper;
    @Autowired
    private TianYuanSvcService tianYuanSvcService;
    @Autowired
    private SvcTaskService svcTaskService;
    @Autowired
    private SvcIdRestService svcIdRestService;
    @Autowired
    private SvcMetaDataHelper svcMetaDataHelper;
    @Autowired
    private KmxService kmxService;
    @Autowired
    private SvcFaultBriefService svcFaultBriefService;

    private Logger logger = LoggerFactory.getLogger(SvcDataController.class);

    //获得我的任务分类和数量
    @RequestMapping(value = "taskCount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<Map<String, Integer>> taskCount() {
        String operatorId = TianYuanUserHolder.getTianYuanOprtId();
        LocalDate today = LocalDate.now();
        Map<String, Integer> map = svcTaskService.countTask(operatorId, today);
        return ResponseVo.success(map);
    }

    //获得某分类任务列表
    @RequestMapping(value = "taskList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<PageListVo<SvcSimpleTask>> taskList(@RequestParam String taskType, @RequestParam Integer page, @RequestParam Integer pageSize) {
        String operatorId = TianYuanUserHolder.getTianYuanOprtId();
        LocalDate today = LocalDate.now();
        SvcRestPage page1 = new SvcRestPage(page - 1, pageSize, null);//rest接口分页中page从0开始计数
        PageListVo<SvcSimpleTask> taskListVo = svcTaskService.listTask(operatorId, today, taskType, page1);
        return ResponseVo.success(taskListVo);
    }

    //任务客户信息
    @RequestMapping(value = "taskCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<SvcCustomer> taskCustomer(@RequestParam String rwh) {
        SvcCommYhxx yhxx = (SvcCommYhxx) this.getCommDataByCurrentUserAndRwh(SvcCommYhxx.class, rwh);
        if (yhxx == null) {
            return ResponseVo.fail("用户数据不存在." + rwh);
        }
        SvcCommSbjbxx jbxx = (SvcCommSbjbxx) this.getCommDataByCurrentUserAndRwh(SvcCommSbjbxx.class, rwh);
        if (jbxx == null) {
            return ResponseVo.fail("设备基本信息数据不存在." + rwh);
        }

        SvcCustomer customer = new SvcCustomer(rwh, yhxx.getYhmc(), yhxx.getLxr(), yhxx.getLxfs(), jbxx.getJqszsheng() + "" + jbxx.getJqszshi());
        return ResponseVo.success(customer);
    }

    //日志提交
    @RequestMapping(value = "taskSubmit", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo taskSubmit(@RequestParam String rwh) {
        String oprtId = TianYuanUserHolder.getTianYuanOprtId();
        SvcFaultBrief brief = svcFaultBriefService.selectByRwhOprtId(rwh, oprtId);
        String lostItems = svcFaultBriefService.getLostSvcFaultBriefEnum(brief);
        if (!StringUtils.isEmpty(lostItems)) {
            return ResponseVo.fail("数据填写不完备，缺少:" + lostItems);
        }

        SvcTask task = svcTaskService.getSvcTask(rwh, oprtId);
        String rwzt = task.getRwzt();//0待填写，1已填写未完成,2填写完成未上传,3已上传待审核,4已上传被打回,5已上传审核通过

        if ("3".equals(rwzt) || "5".equals(rwzt)) {
            return ResponseVo.fail("任务已提交，不要重复提交." + rwh);
        }

        //设置当前时间为服务结束时间
        SvcCommSjlc sjlc = (SvcCommSjlc) this.getCommDataByCurrentUserAndRwh(SvcCommSjlc.class, rwh);
        if (sjlc == null) {
            return ResponseVo.fail("时间里程数据不存在." + rwh);
        }

        sjlc.setJsfwsj(Dates.format(new Date(), "HH:mm"));
        ResponseVo vo = saveCommEntity(SvcCommSjlc.class, sjlc);
        if (!vo.isSuccess()) {
            return vo;
        }

        //提交:将任务主数据、comm数据组合后调用天远接口
        DoubleVo<? extends SvcMainEntity, List<? extends SvcCommEntity>> doubleVo = svcTaskService.getSvcPostData(rwh, oprtId);
        if (doubleVo == null) {
            return ResponseVo.fail("获取提交数据失败." + rwh);
        }

        SvcMainEntity mainEntity = doubleVo.getKey();
        List<? extends SvcCommEntity> commEntityList = doubleVo.getVal();

        //pic数据
        String svcId = String.valueOf(mainEntity.getPkValue());
        List<SvcCommPic> picList = svcCommRestService.listSvcCommPicByRwhSvcId(rwh, svcId);

        //检查图片是否都上传完毕了，如果未上传完毕，则不能提交
        String notUploadPics = picList.stream().filter(pic -> {
            tianYuanSvcService.checkUploadCommPic(mainEntity, pic);
            return !"2".equals(pic.getScbz()); //0未上传 1正在上传 2已上传
        }).map(pic -> pic.getFieldNameCn()).collect(Collectors.joining("、"));

        if (!StringUtils.isEmpty(notUploadPics)) {
            logger.debug("工单提交时,部分图片上传失败,不能提交,失败照片:" + notUploadPics + ",rwh=" + rwh + ",oprtId=" + oprtId);
            return ResponseVo.fail("部分图片上传失败,不能提交,失败照片:" + notUploadPics);
        }

        vo = tianYuanSvcService.postGzcl(oprtId, TianYuanUserHolder.getAccessToken(), mainEntity, commEntityList, picList);
        if (vo.isSuccess()) {
            //需要更新数据状态
            if (task != null && !"3".equals(task.getRwzt())) {
                task.setRwzt("3");//已上传待审核
                svcMainRestService.updateSvcMainEntity(SvcTask.class, SvcTask.class.getSimpleName(), task);
            }

            mainEntity.setScbz("2");//已上传
            svcMainRestService.updateSvcMainEntity(mainEntity.getClass(), mainEntity.getClass().getSimpleName(), mainEntity);

            commEntityList.stream().forEach(commEntity -> {
                commEntity.setScbz("2");//已上传
                svcCommRestService.updateSvcCommEntity(commEntity.getClass(), commEntity.getClass().getSimpleName(), commEntity);
            });

            picList.stream().forEach(pic -> {
                pic.setScbz("2");//已上传
                svcIdRestService.updatSvcIdEntity(SvcCommPic.class, pic);
            });
        }
        return vo;
    }

    //时间里程信息
    @RequestMapping(value = "taskTimeMile", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<SvcTimeMile> taskTimeMileGet(@RequestParam String rwh) {
        SvcCommSjlc sjlc = (SvcCommSjlc) this.getCommDataByCurrentUserAndRwh(SvcCommSjlc.class, rwh);
        if (sjlc == null) {
            return ResponseVo.fail("数据不存在." + rwh);
        }

        SvcTimeMile mile = new SvcTimeMile(rwh, sjlc.getCfsj(), sjlc.getFhsj(), sjlc.getLc());
        return ResponseVo.success(mile);
    }

    //时间里程信息
    @RequestMapping(value = "taskTimeMile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<SvcTimeMile> taskTimeMileSet(SvcTimeMile svcTimeMile) {
        String rwh = svcTimeMile.getRwh();
        SvcCommSjlc sjlc = (SvcCommSjlc) this.getCommDataByCurrentUserAndRwh(SvcCommSjlc.class, rwh);
        if (sjlc == null) {
            return ResponseVo.fail("数据不存在." + rwh);
        }

        if (StringUtils.isEmpty(sjlc.getKsfwsj())) {
            sjlc.setKsfwsj(Dates.format(new Date(), "HH:mm"));
        }
        sjlc.setCfsj(svcTimeMile.getSetoutTime());
        sjlc.setFhsj(svcTimeMile.getReturnTime());
        sjlc.setLc(svcTimeMile.getMile());
        sjlc.setTxbz("1");

        ResponseVo vo = saveCommEntity(SvcCommSjlc.class, sjlc);
        if (vo.isSuccess()) {
            String oprtId = TianYuanUserHolder.getTianYuanOprtId();
            svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, SvcFaultBriefEnum.timeMile, "1");
        }
        return vo;
    }

    //获得使用情况
    @RequestMapping(value = "taskUsage", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<SvcUsage> taskUsageGet(@RequestParam String rwh) {
        SvcCommSyqk syqk = (SvcCommSyqk) this.getCommDataByCurrentUserAndRwh(SvcCommSyqk.class, rwh);
        if (syqk == null) {
            return ResponseVo.fail("使用情况数据不存在." + rwh);
        }
        SvcCommSbjbxx jbxx = (SvcCommSbjbxx) this.getCommDataByCurrentUserAndRwh(SvcCommSbjbxx.class, rwh);
        if (jbxx == null) {
            return ResponseVo.fail("设备基本信息不存在." + rwh);
        }

        SvcUsage usage = new SvcUsage();
        usage.setRwh(rwh);
        usage.setGongkuang(syqk.getGztj());//工作条件
        usage.setRanyou(syqk.getRy());//
        usage.setGaizhuang(syqk.getGz());//
        usage.setHour(jbxx.getGzxs());//工作小时
        ResponseVo vo = ResponseVo.success(usage);
        return vo;
    }

    //设置使用情况
    @RequestMapping(value = "taskUsage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> taskUsageSet(SvcUsage svcUsage) {
        String rwh = svcUsage.getRwh();
        SvcCommSyqk syqk = (SvcCommSyqk) this.getCommDataByCurrentUserAndRwh(SvcCommSyqk.class, rwh);
        if (syqk == null) {
            return ResponseVo.fail("使用情况数据不存在." + rwh);
        }
        SvcCommSbjbxx jbxx = (SvcCommSbjbxx) this.getCommDataByCurrentUserAndRwh(SvcCommSbjbxx.class, rwh);
        if (jbxx == null) {
            return ResponseVo.fail("设备基本信息不存在." + rwh);
        }

        syqk.setGztj(svcUsage.getGongkuang());
        syqk.setRy(svcUsage.getRanyou());
        syqk.setGz(svcUsage.getGaizhuang());
        /**
         * 0(32)~40 (105)/1500（5000）及以下
         -1(31)~-20(-4)/1500（5000）及以下
         41（106）及以上/1500（5000）及以下
         -21(-5)~-40(-40) / 1500(5000)及以下
         -21(-5)~-40(-40) / 1500(5000)及以下
         41(106)及以上 / 1501(5001)~2300(7500)
         0（32)~40（105） / 1501(5001)~2300(7500)
         -1（31)~-20（-4） / 1501(5001)~2300(7500)
         -21(-5)~-40(-40) / 1501(5001)~2300(7500)
         -41(-41)及以下 / 1501(5001)~2300(7500)
         41(106)及以上 / 2301（7501）及以上
         0（32)~40（105） / 2301（7501）及以上
         -1（31)~-20（-4） / 2301（7501）及以上
         -21(-5)~-40(-40) / 2301（7501）及以上
         -41(-41)及以下 / 2301（7501）及以上
         */
        syqk.setHj("0(32)~40 (105)/1500（5000）及以下");//@todo 环境:根据当前定位信息调用天气预报接口得到温度,调用海拔接口得到海拔
        syqk.setTxbz("1");

        jbxx.setGzxs(svcUsage.getHour());
        jbxx.setFwrq(new Date());//服务日期
        jbxx.setTxbz("1");

        ResponseVo vo = saveCommEntity(SvcCommSyqk.class, syqk);
        if (vo.isSuccess()) {
            vo = saveCommEntity(SvcCommSbjbxx.class, jbxx);
        }
        if (vo.isSuccess()) {
            String oprtId = TianYuanUserHolder.getTianYuanOprtId();
            svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, SvcFaultBriefEnum.usageStat, "1");
        }
        return vo;
    }

    //获得故障基本信息
    @RequestMapping(value = "taskFault", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<SvcFault> taskFaultGet(@RequestParam String rwh) {
        SvcGzclMain gzcl = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
        if (gzcl == null) {
            return ResponseVo.fail("故障数据不存在." + rwh);
        }

        SvcFault fault = new SvcFault();
        fault.setRwh(rwh);
        fault.setYouchang("是".equals(gzcl.getSfys()));
        fault.setGongshifei(gzcl.getYsgsf());
        fault.setTingji("是".equals(gzcl.getSftj()));
        fault.setFaultContent(gzcl.getGznr());//故障内容

//        SvcResAbstract audio = svcResUploadHelper.getSvcRes(rwh, "audio", SvcAudio.AudioType.faultContent.name(), TianYuanUserHolder.getTianYuanOprtId());
//        fault.setAudioPath(audio == null ? null : audio.getResPath());
        return ResponseVo.success(fault);
    }

    //设置故障基本信息
    @RequestMapping(value = "taskFault", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> taskFaultSet(SvcFault fault) {
        String rwh = fault.getRwh();
        SvcGzclMain gzcl = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
        if (gzcl == null) {
            return ResponseVo.fail("故障数据不存在." + rwh);
        }

        gzcl.setSfys(fault.isYouchang() ? "是" : "否");
        gzcl.setYsgsf(fault.getGongshifei());
        gzcl.setSftj(fault.isTingji() ? "是" : "否");
        gzcl.setGznr(fault.getFaultContent());//故障内容
        gzcl.setGzjcGzyy(fault.getFaultContent());//检测过程及故障原因
        gzcl.setGzjcBy8(fault.getFaultContent());//服务内容
        gzcl.setGzjcTxbz("1");
        ResponseVo vo = saveMainEntity(SvcGzclMain.class, gzcl);
        if (vo.isSuccess()) {
            String oprtId = TianYuanUserHolder.getTianYuanOprtId();
            svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, SvcFaultBriefEnum.fault, "1");
        }
        return vo;
    }

    //故障检查解决方式
    @RequestMapping(value = "taskFaultResolveMethod", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<String> taskFaultResolveMethodGet(@RequestParam String rwh) {
        SvcGzclMain gzcl = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
        if (gzcl == null) {
            return ResponseVo.fail("故障数据不存在." + rwh);
        }

        return ResponseVo.success(gzcl.getGzjcWtrhjj());// 问题如何解决
    }

    //故障检查解决方式
    @RequestMapping(value = "taskFaultResolveMethod", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> taskFaultResolveMethodSet(@RequestParam String rwh, @RequestParam String resolveMethod) {
        SvcGzclMain gzcl = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
        if (gzcl == null) {
            return ResponseVo.fail("故障数据不存在." + rwh);
        }

        gzcl.setGzjcWtrhjj(resolveMethod);//问题如何解决
        gzcl.setGzjcTxbz("1");
        ResponseVo vo = saveMainEntity(SvcGzclMain.class, gzcl);
        if (vo.isSuccess()) {
            String oprtId = TianYuanUserHolder.getTianYuanOprtId();
            svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, SvcFaultBriefEnum.faultResove, "1");
        }
        return vo;
    }

    //故障是否处理
    @RequestMapping(value = "taskFaultHandled", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<SvcFaultHandle> taskFaultHandledGet(@RequestParam String rwh) {
        SvcGzclMain gzcl = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
        if (gzcl == null) {
            return ResponseVo.fail("故障数据不存在." + rwh);
        }

        boolean yesNo = "是".equals(gzcl.getGzjcSfjxgzcl());//是否进行故障处理
        if (yesNo) {
            return ResponseVo.success(SvcFaultHandle.handled(rwh));
        } else {
//            SvcResAbstract audio = svcResUploadHelper.getSvcRes(rwh, "audio", SvcAudioTypeEnum.notHandleReason.getCode(), TianYuanUserHolder.getTianYuanOprtId());
            return ResponseVo.success(SvcFaultHandle.notHandled(rwh, gzcl.getGzclWxfyy()));
        }
    }

    //故障是否处理
    @RequestMapping(value = "taskFaultHandled", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> taskFaultHandledSet(@RequestParam String rwh, @RequestParam boolean handled, @RequestParam(required = false) String notHandleReason) {
        SvcGzclMain gzcl = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
        if (gzcl == null) {
            return ResponseVo.fail("故障数据不存在." + rwh);
        }

        SvcCommJgnr jgnr = (SvcCommJgnr) this.getCommDataByCurrentUserAndRwh(SvcCommJgnr.class, rwh);
        if (jgnr == null) {
            return ResponseVo.fail("结果内容数据不存在." + rwh);
        }

        if (notHandleReason == null) notHandleReason = "";
        if (notHandleReason.length() > 25) {//天远数据库50个长度25个汉字
            //长度超过50则截断
            notHandleReason = notHandleReason.substring(0, 20) + "...";
        }

        gzcl.setGzjcSfjxgzcl(handled ? "是" : "否");
        gzcl.setGzclWxfyy(handled ? "" : notHandleReason);//未处理原因,数据库长度50
        gzcl.setGzjcWjxgzclyy(handled ? "" : notHandleReason);//未进行故障处理原因
        gzcl.setGzclTxbz("1");

        jgnr.setJgnr(handled ? "已完成" : "未完成");
        jgnr.setWwcyy(handled ? "" : notHandleReason);//未完成原因,数据库长度50
        jgnr.setTxbz("1");

        ResponseVo vo = saveMainEntity(SvcGzclMain.class, gzcl);
        if (vo.isSuccess()) {
            vo = saveCommEntity(SvcCommJgnr.class, jgnr);
        }
        if (vo.isSuccess()) {
            String oprtId = TianYuanUserHolder.getTianYuanOprtId();
            svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, SvcFaultBriefEnum.faultHandle, "1");
        }
        return vo;
    }

    //故障是否修复
    @RequestMapping(value = "taskFaultRepaired", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<SvcFaultRepair> taskFaultRepairedGet(@RequestParam String rwh) {
        SvcGzclMain gzcl = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
        if (gzcl == null) {
            return ResponseVo.fail("故障数据不存在." + rwh);
        }

        boolean syxf = "是".equals(gzcl.getGzclSfxf());
        if (syxf) {
            SvcResAbstract video = svcResUploadHelper.getLatestSvcRes(rwh, SvcResTypeEnum.video, SvcVideoTypeEnum.faultRepaird.toString(), TianYuanUserHolder.getTianYuanOprtId());
            return ResponseVo.success(SvcFaultRepair.repaired(rwh, video == null ? null : video.getResPath()));
        } else {
            return ResponseVo.success(SvcFaultRepair.notRepaired(rwh));//未修复
        }
    }

    //故障是否修复
    @RequestMapping(value = "taskFaultRepaired", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> taskFaultRepairedSet(@RequestParam String rwh, @RequestParam boolean repaired) {
        SvcGzclMain gzcl = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
        if (gzcl == null) {
            return ResponseVo.fail("故障数据不存在." + rwh);
        }

        gzcl.setGzclSfxf(repaired ? "是" : "否");
        gzcl.setGzclTxbz("1");
        ResponseVo vo = saveMainEntity(SvcGzclMain.class, gzcl);
        if (vo.isSuccess()) {
            String oprtId = TianYuanUserHolder.getTianYuanOprtId();
            svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, SvcFaultBriefEnum.faultRepair, "1");
        }
        return vo;
    }

    //用户意见建议
    @RequestMapping(value = "taskUserOpinion", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<SvcUserOpinion> taskUserOpinionGet(@RequestParam String rwh) {
        SvcCommYhyj yhyj = (SvcCommYhyj) this.getCommDataByCurrentUserAndRwh(SvcCommYhyj.class, rwh);
        if (yhyj == null) {
            return ResponseVo.fail("用户意见数据不存在." + rwh);
        }

        SvcUserOpinion opinion = new SvcUserOpinion();
        opinion.setRwh(rwh);
        opinion.setSatisfy(yhyj.getMycd());//满意程度
//        SvcResAbstract audio = svcResUploadHelper.getSvcRes(rwh, "audio", SvcAudioTypeEnum.userOpinion.getCode(), TianYuanUserHolder.getTianYuanOprtId());
//        opinion.setOpinionAudioPath(audio == null ? null : audio.getResPath());
        opinion.setUserOpinion(yhyj.getJyhyj());
        return ResponseVo.success(opinion);
    }

    //用户意见建议
    @RequestMapping(value = "taskUserOpinion", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> taskUserOpinionSet(@RequestParam String rwh, @RequestParam String satisfy, @RequestParam String userOpinion) {
        SvcCommYhyj yhyj = (SvcCommYhyj) this.getCommDataByCurrentUserAndRwh(SvcCommYhyj.class, rwh);
        if (yhyj == null) {
            return ResponseVo.fail("用户意见数据不存在." + rwh);
        }

        if (userOpinion.length() > 25) {//天远数据库50个长度25个汉字
            //长度超过50则截断
            userOpinion = userOpinion.substring(0, 20) + "...";
        }

        yhyj.setMycd(satisfy);
        yhyj.setJyhyj(userOpinion);//用户建议和意见
        yhyj.setTxbz("1");
        ResponseVo vo = saveCommEntity(SvcCommYhyj.class, yhyj);
        if (vo.isSuccess()) {
            String oprtId = TianYuanUserHolder.getTianYuanOprtId();
            svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, SvcFaultBriefEnum.opinion, "1");
        }
        return vo;
    }

    @RequestMapping(value = "uploadVideo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<SvcVideo> uploadVideo(@RequestParam String rwh, @RequestParam String videoType, @RequestParam("videofile") MultipartFile file) {
        ResponseVo vo = triggerServiceStartTime(rwh);
        if (!vo.isSuccess()) {
            return vo;
        }

        String oprtId = TianYuanUserHolder.getTianYuanOprtId();
        SvcMainEntity mainEntity = this.getMainDataByRwhOprtId(rwh, oprtId);
        if (mainEntity == null) {
            return ResponseVo.fail("工单主数据不存在." + rwh);
        }

        //videoType site：工地，digger：车辆，faultCheck：故障前检查，faultRepaird：故障修复
        vo = svcResUploadHelper.uploadVideo(rwh, String.valueOf(mainEntity.getPkValue()), videoType, file);
        if (vo.isSuccess()) {
            SvcFaultBriefEnum briefEnum = null;
            if (SvcVideoTypeEnum.site.toString().equals(videoType)) {
                briefEnum = SvcFaultBriefEnum.siteVideo;
            } else if (SvcVideoTypeEnum.digger.toString().equals(videoType)) {
                briefEnum = SvcFaultBriefEnum.diggerVideo;
            } else if (SvcVideoTypeEnum.faultCheck.toString().equals(videoType)) {
                briefEnum = SvcFaultBriefEnum.faultCheckVideo;
            } else if (SvcVideoTypeEnum.faultRepaird.toString().equals(videoType)) {
                briefEnum = SvcFaultBriefEnum.faultRepairVideo;
            }

            if (briefEnum != null) {
                svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, briefEnum, "1");
            }
        }
        return vo;
    }

    @RequestMapping(value = "uploadAudio", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<SvcAudio> uploadAudio(@RequestParam String rwh, @RequestParam String audioType, @RequestParam("audiofile") MultipartFile file) {
        ResponseVo vo = triggerServiceStartTime(rwh);
        if (!vo.isSuccess()) {
            return vo;
        }

        String oprtId = TianYuanUserHolder.getTianYuanOprtId();
        SvcMainEntity mainEntity = this.getMainDataByRwhOprtId(rwh, oprtId);
        if (mainEntity == null) {
            return ResponseVo.fail("工单主数据不存在." + rwh);
        }

        //audioType faultContent:故障内容,notHandleReason:故障未处理原因,userOpinion 用户意见建议
        vo = svcResUploadHelper.uploadAudio(rwh, String.valueOf(mainEntity.getPkValue()), audioType, file);
        if (vo.isSuccess()) {
            SvcFaultBriefEnum briefEnum = null;
            if (SvcAudioTypeEnum.userOpinion.toString().equals(audioType)) {
                briefEnum = SvcFaultBriefEnum.opinion;
            } else if (SvcAudioTypeEnum.notHandleReason.toString().equals(audioType)) {
                briefEnum = SvcFaultBriefEnum.faultHandle;
            } else if (SvcAudioTypeEnum.faultContent.toString().equals(audioType)) {
                briefEnum = SvcFaultBriefEnum.fault;
            }

            if (briefEnum != null) {
                svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, briefEnum, "1");
            }
        }
        return vo;
    }

    @RequestMapping(value = "uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<SvcImage> uploadImage(@RequestParam String rwh, @RequestParam String imageType, @RequestParam("imagefile") MultipartFile file) {
        ResponseVo vo = triggerServiceStartTime(rwh);
        if (!vo.isSuccess()) {
            return vo;
        }

        String oprtId = TianYuanUserHolder.getTianYuanOprtId();
        SvcMainEntity mainEntity = this.getMainDataByRwhOprtId(rwh, oprtId);
        if (mainEntity == null) {
            return ResponseVo.fail("工单主数据不存在." + rwh);
        }

        //imageType  jihao:机号照片
        vo = svcResUploadHelper.uploadImage(rwh, String.valueOf(mainEntity.getPkValue()), imageType, file);
        if (vo.isSuccess()) {
            SvcFaultBriefEnum briefEnum = null;
            if (SvcImageTypeEnum.jihao.toString().equals(imageType)) {
                briefEnum = SvcFaultBriefEnum.jhPic;
            }
            if (briefEnum != null) {
                svcTaskService.updateSvcFaultBriefByRwhOprtId(rwh, oprtId, briefEnum, "1");
            }
        }
        return vo;
    }

    private <T extends SvcMainEntity> ResponseVo saveMainEntity(Class<T> entityClass, T entity) {
        ResponseVo vo = triggerServiceStartTime(entity.getRwh());
        if (!vo.isSuccess()) {
            return vo;
        }
        try {
            svcMainRestService.updateSvcMainEntity(entityClass, entityClass.getSimpleName(), entity);
            return ResponseVo.success();
        } catch (Exception e) {
            logger.error("更新信息异常." + entityClass.getSimpleName() + ",rwh=" + entity.getRwh() + ",oprtId=" + entity.getOprtId() + ",entity=" + JSON.toJSONString(entity), e);
            return ResponseVo.fail("保存失败." + e.getMessage());
        }
    }

    private <T extends SvcCommEntity> ResponseVo saveCommEntity(Class<T> entityClass, T entity) {
        if (!entityClass.getSimpleName().equals(SvcCommSjlc.class.getSimpleName())) {
            ResponseVo vo = triggerServiceStartTime(entity.getRwh());
            if (!vo.isSuccess()) {
                return vo;
            }
        }

        try {
            svcCommRestService.updateSvcCommEntity(entityClass, entityClass.getSimpleName(), entity);
            return ResponseVo.success();
        } catch (Exception e) {
            logger.error("更新信息异常." + entityClass.getSimpleName() + ",rwh=" + entity.getRwh() + ",svcId=" + entity.getSvcId() + ",entity=" + JSON.toJSONString(entity), e);
            return ResponseVo.fail("保存失败." + e.getMessage());
        }
    }

    //记录服务开始时间=当日首次填写日志的时间
    private ResponseVo triggerServiceStartTime(String rwh) {
        SvcCommSjlc sjlc = (SvcCommSjlc) this.getCommDataByCurrentUserAndRwh(SvcCommSjlc.class, rwh);
        if (sjlc == null) {
            return ResponseVo.fail("时间里程数据不存在." + rwh);
        }

        if (StringUtils.isEmpty(sjlc.getKsfwsj())) {
            sjlc.setKsfwsj(Dates.format(new Date(), "HH:mm"));
            ResponseVo vo = saveCommEntity(SvcCommSjlc.class, sjlc);
            //服务开始时的定位
            try {
                triggerGzclGpsInfo(rwh);
            } catch (Exception e) {
                logger.error("设置故障处理的定位信息异常", e);
            }
            return vo;
        }

        return ResponseVo.success();
    }

    //设置故障处理的定位信息
    private void triggerGzclGpsInfo(String rwh) {
        String operatorId = TianYuanUserHolder.getTianYuanOprtId();
        SvcTask task = svcTaskService.getSvcTask(rwh, operatorId);
        if ("故障处理".equals(task.getFwlb())) {
            Float[] lonLat = kmxService.getHelmetGeo(HelmetImeiHolder.get(), System.currentTimeMillis());
            if (lonLat != null) {
                SvcGzclMain gzclMain = this.getSvcGzclMainByCurrentUserAndRwh(rwh);
                gzclMain.setVclGpsLon(String.valueOf(lonLat[0]));
                gzclMain.setVclGpsLa(String.valueOf(lonLat[1]));
                svcMainRestService.updateSvcMainEntity(SvcGzclMain.class, SvcGzclMain.class.getSimpleName(), gzclMain);
            }
        }
    }

    //根据任务号得到当前用户对应的某个comm类型数据
    public SvcCommEntity getCommDataByCurrentUserAndRwh(Class<? extends SvcCommEntity> tClass, String rwh) {
        String oprtId = TianYuanUserHolder.getTianYuanOprtId();
        SvcTask task = svcTaskService.getSvcTask(rwh, oprtId);
        if (task != null) {
            Class<? extends SvcMainEntity> mainCls = svcMetaDataHelper.getMainClassByFwlb(task.getFwlb());
            if (mainCls != null) {
//                logger.debug("查询工单的" + tClass.getSimpleName() + "数据，对应主数据类为:" + mainCls.getSimpleName() + ",rwh=" + rwh + ",oprtId=" + oprtId);
                SvcMainEntity mainEntity = svcMainRestService.getOrderByRwhOprtId(mainCls, mainCls.getSimpleName(), rwh, oprtId);
                if (mainEntity != null) {
                    return svcCommRestService.getCommDataByRwhSvcId(tClass, tClass.getSimpleName(), rwh, String.valueOf(mainEntity.getPkValue()));
                }
            }
        }
        return null;
    }


    /**
     * 获得工单对应的主数据
     *
     * @param rwh
     * @param oprtId
     * @return
     */
    public SvcMainEntity getMainDataByRwhOprtId(String rwh, String oprtId) {
        SvcTask task = svcTaskService.getSvcTask(rwh, oprtId);
        if (task == null)
            return null;

        Class cls = svcMetaDataHelper.getMainClassByFwlb(task.getFwlb());
        SvcMainEntity mainEntity = svcMainRestService.getOrderByRwhOprtId(cls, cls.getSimpleName(), rwh, oprtId);
        return mainEntity;
    }

    /**
     * 获得当前用户的工单数据
     *
     * @param rwh
     * @return
     */
    public SvcGzclMain getSvcGzclMainByCurrentUserAndRwh(String rwh) {
        String oprtId = TianYuanUserHolder.getTianYuanOprtId();
        Class tClass = SvcGzclMain.class;
        return (SvcGzclMain) svcMainRestService.getOrderByRwhOprtId(tClass, tClass.getSimpleName(), rwh, oprtId);
    }

}
