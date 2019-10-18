package com.tianyi.helmet.server.controller.scene;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.svc.SvcResAbstract;
import com.tianyi.helmet.server.entity.svc.po.SvcRestPage;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.svc.SvcCommRestService;
import com.tianyi.helmet.server.service.svc.SvcMetaDataHelper;
import com.tianyi.helmet.server.service.svc.SvcResUploadHelper;
import com.tianyi.helmet.server.service.svc.SvcTaskService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanSvcJsonHelper;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.QuadrupleVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import com.tianyi.svc.rest.entity.SvcCommPic;
import com.tianyi.svc.rest.entity.SvcTask;
import com.tianyi.svc.rest.entity.base.SvcCommEntity;
import com.tianyi.svc.rest.entity.base.SvcIdEntity;
import com.tianyi.svc.rest.entity.base.SvcMainEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台服务日志接口
 * <p>
 * Created by liuhanc on 2018/1/30.
 */
@Controller
@RequestMapping("service")
public class ServiceController {
    @Autowired
    private TianYuanUserService tianYuanUserService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private SvcMetaDataHelper svcMetaDataHelper;
    @Autowired
    private TianYuanSvcJsonHelper tianYuanSvcJsonHelper;
    @Autowired
    private SvcTaskService svcTaskService;
    @Autowired
    private SvcCommRestService svcCommRestService;
    @Autowired
    private SvcResUploadHelper svcResUploadHelper;
    @Autowired
    private ConfigService configService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(ServiceController.class);

    private int pageSize = 8;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    //服务自动化首页
    @RequestMapping("/index")
    public String index(Model model) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        model.addAttribute("isAdmin", userInfo.isAdmin());
        model.addAttribute("isTianYuanUser", userInfo.isTianyuan());
        model.addAttribute("taskMetaData", JSON.toJSONString(svcMetaDataHelper.getTaskMetaData()));
        return "scene/service/serviceIndex";
    }

    //查询任务列表
    @RequestMapping("/queryTaskList/{page}")
    public String queryTaskList(@PathVariable Integer page, @RequestParam(required = false) String oprtId, @RequestParam String rwzt,
                                @RequestParam Date date1, @RequestParam Date date2, Model model, HttpServletRequest req) {
        if (page == null || page <= 0) page = 1;
        if (date2 != null) {
            date2 = new Date(date2.getTime() + 24 * 60 * 60 * 1000);//日期延后一天否则date2对应的当天数据查询不出来
        }

        LoginUserInfo userInfo = LoginUserHolder.get();
        boolean doQuery = true;
        if (userInfo.isTianyuan()) {
            oprtId = (String) req.getAttribute("oprtId");
        } else {
            //管理员
            oprtId = tianYuanUserService.checkOprtNameToOprtId(oprtId);
            if (oprtId == null) doQuery = false;
        }

        PageListVo<SvcTask> vo = null;
        if (doQuery) {
            page = page - 1;//田一的首页是1，天远日志的首页是0
            SvcRestPage page1 = new SvcRestPage(page, pageSize, null);
            vo = svcTaskService.listTask(oprtId, rwzt, date1, date2, page1);
        } else {
            //不必查询数据为空
            vo = new PageListVo(0, pageSize, Collections.emptyList(), 0);
        }
        model.addAttribute("vo", vo);
        return "scene/service/serviceTaskList";
    }

    //获得某个任务详情
    @RequestMapping("/getTaskInfo/{rwh}/{oprtId}")
    @ResponseBody
    public ResponseVo getTaskInfo(@PathVariable("rwh") String rwh, @PathVariable("oprtId") String oprtId, HttpServletRequest req) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo.isTianyuan()) {
            oprtId = (String) req.getAttribute("oprtId");
        }

        DoubleVo<? extends SvcMainEntity, List<? extends SvcCommEntity>> doubleVo = svcTaskService.getSvcPostData(rwh, oprtId);
        if (doubleVo == null) {
            return ResponseVo.fail("数据不存在." + rwh);
        }

        SvcTask task = svcTaskService.getSvcTask(rwh, oprtId);
        SvcMainEntity mainEntity = doubleVo.getKey();
        List<? extends SvcCommEntity> commEntityList = doubleVo.getVal();
        logger.debug("查询工单详情.mainData=" + mainEntity + ",commEntityList=" + commEntityList);
        List<SvcResAbstract> resList = svcResUploadHelper.getSvcResList(rwh, oprtId);
        List<String[]> svcResKvList = resList.stream().map(res -> {
            return new String[]{
                    res.getResType().getCnName(),
                    res.getResInnerTypeName(),
                    res.getResPath()
            };
        }).collect(Collectors.toList());

        String svcId = String.valueOf(mainEntity.getPkValue());
        List<SvcCommPic> picList = svcCommRestService.listSvcCommPicByRwhSvcId(rwh, svcId);
        List<String[]> picKvList = picList.stream().map(pic -> new String[]{
                pic.getFieldNameCn(), pic.getPhotoUrl(), configService.getFastdfsServerUrl() + pic.getPhotoUrl()
        }).collect(Collectors.toList());

        Map<String, List<String[]>> mapData = commEntityList.stream().collect(Collectors.toMap(entity -> entity.getClass().getSimpleName(), entity -> {
            return entityToKvList((SvcIdEntity) entity);
        }));

        mapData.put(SvcTask.class.getSimpleName(), entityToKvList(task));
        mapData.put("mainItem", entityToKvList(mainEntity));
        mapData.put("svcResData", svcResKvList);
        mapData.put("SvcCommPic", picKvList);

        return ResponseVo.success(mapData);
    }

    private List<String[]> entityToKvList(SvcIdEntity entity) {
        List<QuadrupleVo<String, String, Field, String>> fieldList = tianYuanSvcJsonHelper.getClassFieldList(entity.getClass());
        List<String[]> nameValList = fieldList.stream().map(qo -> {
            Object val = null;
            try {
                val = qo.getThree().get(entity);
            } catch (Exception e) {
                logger.error("获取属性值异常.cls=" + entity.getClass().getName() + ",field=" + qo.getOne(), e);
            }
            return new String[]{
                    qo.getOne(), qo.getFour(), val == null ? "" : val.toString()
            };
        }).collect(Collectors.toList());
        return nameValList;
    }


    //进入接口测试
    @RequestMapping("test")
    public String test(Model model) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        /**
         * update by xiayuan 2018/10/10
         * todo 不知道哪里调用这个接口
         */
//        TianYuanUser tianYuanUser = tianYuanUserService.selectById(userInfo.getId());
        User user = userService.selectById(userInfo.getId());
        List<EquipmentLedger> list = equipmentService.selectByUserId(userInfo.getId());
        EquipmentLedger helmet = new EquipmentLedger();
        for (EquipmentLedger e : list) {
            String deviceUUID = (String) redisTemplate.opsForValue().get(user.getNeUserHel());
            if (StringUtils.isNotEmpty(deviceUUID) && deviceUUID.equals(e.getDeviceUUID())) {
                helmet = e;
            }
        }
        if (helmet != null) {
            model.addAttribute("helmetImei", helmet.getDeviceUUID());
        }
        return "scene/service/test";
    }
}
