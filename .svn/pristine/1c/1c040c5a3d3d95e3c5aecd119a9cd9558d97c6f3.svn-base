package com.tianyi.helmet.server.service.svc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.service.tianyuan.TianYuanSvcJsonHelper;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.QuadrupleVo;
import com.tianyi.svc.rest.entity.*;
import com.tianyi.svc.rest.entity.base.SvcCommEntity;
import com.tianyi.svc.rest.entity.base.SvcIdEntity;
import com.tianyi.svc.rest.entity.base.SvcMainEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * 服务日志元数据帮助类
 * <p>
 * Created by liuhanc on 2018/3/8.
 */
@Component
public class SvcMetaDataHelper {

    @Autowired
    private TianYuanSvcJsonHelper tianYuanSvcJsonHelper;

    private Logger logger = LoggerFactory.getLogger(SvcMetaDataHelper.class);

    /**
     * 各种服务类别对应的实体类
     */
    private Map<String, Class<? extends SvcMainEntity>> fwlbClassMap = new HashMap<>(10);

    /**
     * 服务的公用数据
     */
    private Map<Class<? extends SvcCommEntity>, DoubleVo<String, String>> commConfigMap = new HashMap<>(8);

    public enum SvcFaultPicMeta {
        JIHAO("SvcGM_gzjc_gzzpjh", "故障照片-机号", SvcGzclMain::setGzjcGzzpjh),
        ZHENGJI("SvcGM_gzjc_gzzpzj", "故障照片-整机", SvcGzclMain::setGzjcGzzpzj),
        JIANCE("SvcGM_gzjc_gzzpgzjc", "故障照片-故障检测", SvcGzclMain::setGzjcGzzpgzjc),
        YUANJING("SvcGM_gzjc_gzzpgzyj", "故障照片-故障远景", SvcGzclMain::setGzjcGzzpgzyj),
        JINJING("SvcGM_gzjc_gzzpgzjj", "故障照片-故障近景", SvcGzclMain::setGzjcGzzpgzjj),
        ZHONGJING("SvcGM_gzjc_gzzpgzzj", "故障照片-故障中景", SvcGzclMain::setGzjcGzzpgzzj);

        String fieldNameEn;
        String fieldNameCn;
        BiConsumer<SvcGzclMain, String> fieldSetter;

        SvcFaultPicMeta(String en, String cn, BiConsumer<SvcGzclMain, String> fieldSetter) {
            this.fieldNameCn = cn;
            this.fieldNameEn = en;
            this.fieldSetter = fieldSetter;
        }

        public static SvcFaultPicMeta getByFieldNameEn(String fieldNameEn) {
            return Arrays.stream(SvcFaultPicMeta.values()).filter(meta -> meta.getEn().equals(fieldNameEn)).findAny().orElse(null);
        }

        public String getCn() {
            return fieldNameCn;
        }

        public String getEn() {
            return fieldNameEn;
        }

        public BiConsumer<SvcGzclMain, String> getFieldSetter() {
            return fieldSetter;
        }
    }

    @PostConstruct
    public void initData() {
        fwlbClassMap.put("故障处理", SvcGzclMain.class);
        fwlbClassMap.put("交机服务", SvcJjfwMain.class);
        fwlbClassMap.put("出保服务", SvcCbfwMain.class);
        fwlbClassMap.put("定期保养", SvcDqbyMain.class);
        fwlbClassMap.put("走访检查", SvcZfjcMain.class);
        fwlbClassMap.put("零件销售", SvcLjxs.class);
        fwlbClassMap.put("债权事务", SvcZqsw.class);
        fwlbClassMap.put("工厂改装", SvcGcgzMain.class);
        fwlbClassMap.put("公司改装", SvcGsgzMain.class);
        fwlbClassMap.put("现场调查", SvcXcdc.class);

        //
        commConfigMap.put(SvcCommYhxx.class, new DoubleVo<>("用户信息", "all"));//用户信息
        commConfigMap.put(SvcCommSbjbxx.class, new DoubleVo<>("基本信息", "all"));//设备基本信息
        commConfigMap.put(SvcCommJgnr.class, new DoubleVo<>("结果内容", "all"));//结果内容
        commConfigMap.put(SvcCommSjlc.class, new DoubleVo<>("时间里程", "all"));//时间里程
        commConfigMap.put(SvcCommSyqk.class, new DoubleVo<>("使用情况", "{\"has\":\"故障处理,现场调查\"}"));//使用情况
//        commConfigMap.put(SvcCommLj.class, new DoubleVo<>("零件", "{\"no\":\"债权事务\"}"));//零件
//        commConfigMap.put(SvcCommDkfx.class, new DoubleVo<>("待扣费项", "{\"no\":\"债权事务,工厂改装\"}"));//待扣费项
        commConfigMap.put(SvcCommYhyj.class, new DoubleVo<>("用户意见", "{\"no\":\"债权事务\"}"));//用户意见
    }

    public Map<String, DoubleVo<Boolean, String>> getTaskMetaData() {
        Map<String, DoubleVo<Boolean, String>> map = new HashMap<>(4);
        map.put("Syqk", getCommFwlb(commConfigMap.get(SvcCommSyqk.class).getVal()));
//        map.put("Lj", getCommFwlb(commConfigMap.get(SvcCommLj.class).getVal()));
//        map.put("Dkfx", getCommFwlb(commConfigMap.get(SvcCommDkfx.class).getVal()));
        map.put("Yhyj", getCommFwlb(commConfigMap.get(SvcCommYhyj.class).getVal()));
        return map;
    }

    private DoubleVo<Boolean, String> getCommFwlb(String fwlbJsonString) {
        JSONObject jsonObject = JSON.parseObject(fwlbJsonString);
        if (jsonObject.containsKey("has")) {
            String has = jsonObject.getString("has");
            return new DoubleVo(Boolean.TRUE, has);
        } else {
            //no
            String no = jsonObject.getString("no");
            return new DoubleVo(Boolean.FALSE, no);
        }
    }


    /**
     * 创建某个服务类别对应的主数据
     *
     * @param cls
     * @param task
     * @return
     */
    public SvcMainEntity createSvcMainEntity(Class<? extends SvcMainEntity> cls, SvcTask task) {
        SvcMainEntity t = null;
        try {
            t = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        fillEmptyData(t);

        t.setOprtId(task.getOprtId());
        t.setOprtName(task.getOprtName());
        t.setRwh(task.getRwh());
        t.setVclNo(task.getJh());//机号
        t.setAuditStatus("未审核");
        t.setSendTime(task.getSendTime());
        t.setScbz("0");
        t.setTxbz("0");

        //@todo 其他服务类别进行处理
        if ("故障处理".equals(task.getFwlb())) {
            //故障处理
            SvcGzclMain gzcl = (SvcGzclMain) t;
            gzcl.setZcwcrq(task.getZcwcsj());//最迟完成时间

            gzcl.setSfys("否");
            gzcl.setYsgsf(0.0);
            gzcl.setSftj("否");
            gzcl.setGznr("");
            gzcl.setGzjcWtrhjj("");//问题如何解决，解决方式
            gzcl.setGzjcSfjxgzcl("否");//是否进行故障处理
            gzcl.setGzclSfxf("否");//是否修复
            gzcl.setGzfsrq(task.getGzfsrq());//故障发生日期
            gzcl.setGzclBy1("机器");//故障处理部位一级.测试系统需填，正式的不填
            gzcl.setGzjcBw1("机器");//下拉 28
            gzcl.setGzjcBw2("机器");//下拉 1
            gzcl.setGzjcBw3("机器");//下拉 28

            /**
             * 液压系统 //下拉 59
             * 液压系统
             机械故障
             电器、信息故障
             发动机故障
             */
            gzcl.setGzjcBy2("无");//测试系统需填，正式的不填
            /**
             * 机械故障 //下拉 60
             * 主泵
             主阀
             液压油箱
             油缸
             */
            gzcl.setGzjcBy3("无"); //测试系统需填，正式的不填

        }
        return t;
    }


    /**
     * 创建某个comm数据实例
     *
     * @param cls
     * @param task
     * @param svcId
     * @return
     */
    public SvcCommEntity createSvcCommEntity(Class<? extends SvcCommEntity> cls, SvcTask task, String svcId) {
        SvcCommEntity r = null;
        try {
            r = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        fillEmptyData(r);

        r.setRwh(task.getRwh());
        r.setSvcId(svcId);
        r.setSendTime(task.getSendTime());
        r.setScbz("0");
        r.setTxbz("0");

        String clsName = cls.getSimpleName();
        //用户信息和基本信息可以沿用一部分数据
        if (clsName.equals(SvcCommYhxx.class.getSimpleName())) {
            //用户信息
            SvcCommYhxx yhxx = (SvcCommYhxx) r;
            yhxx.setLxr(task.getLxr());
            yhxx.setLxfs(task.getLxfs());
            yhxx.setYhmc(task.getYhmc());
            yhxx.setTxbz("1");//用户信息默认已填写 因头盔端不对用户信息进行任何修改
        } else if (clsName.equals(SvcCommSbjbxx.class.getSimpleName())) {
            //设备基本信息
            SvcCommSbjbxx jbxx = (SvcCommSbjbxx) r;
            jbxx.setJqszsheng(task.getJqszsheng());
            jbxx.setJqszshi(task.getJqszshi());
            jbxx.setJqszx(task.getJqszx());
            jbxx.setJqszz(task.getJqszz());
            jbxx.setJqszc(task.getJqszc());//机器所在村
            jbxx.setFwrq(task.getFwrq());//服务日期
            jbxx.setGzxs(String.valueOf(task.getGzxs()));
            jbxx.setSfazpsq(task.getSfazpsq());
            jbxx.setPsqjxs(task.getPsqjxs());
            jbxx.setPsqpp(task.getPsqpp());
            jbxx.setPsqxh(task.getPsqxh());
        } else if (clsName.equals(SvcCommSyqk.class.getSimpleName())) {
            //使用情况
            SvcCommSyqk syqk = (SvcCommSyqk) r;
            syqk.setDmtj("易碎岩石");//地面条件默认第1个
            syqk.setFjj("无");//附加件
            syqk.setGztj("挖掘");//工作条件默认值
            syqk.setGzzl("采石场");//工作种类 默认第1个
            syqk.setHj("");//环境  温度和海拔，需要调用网络接口。并且和下拉条对应
            syqk.setRy("0#");
            syqk.setGz("无");
        } else if (clsName.equals(SvcCommSjlc.class.getSimpleName())) {
            SvcCommSjlc sjlc = (SvcCommSjlc) r;
            sjlc.setCfsj("00:00");
            sjlc.setFhsj("23:59");
            sjlc.setLc("0");
            sjlc.setTld("无");//停留点 必须写，但是头盔不支持
        } else if (clsName.equals(SvcCommYhyj.class.getSimpleName())) {
            SvcCommYhyj yhyj = (SvcCommYhyj) r;
            yhyj.setMycd("");
            yhyj.setBy1("成功预约");//与客户沟通结果,头盔不支持，默认
        } else if (clsName.equals(SvcCommJgnr.class.getSimpleName())) {
            SvcCommJgnr jgnr = (SvcCommJgnr) r;
            jgnr.setWwcyy("未填写");
            jgnr.setJgnr("未填写");
        }
        return r;
    }


    public <T extends SvcIdEntity> void fillEmptyData(T t) {
        Class cls = t.getClass();
        List<QuadrupleVo<String, String, Field, String>> list = tianYuanSvcJsonHelper.getClassFieldList(cls);
        if (list == null || list.size() == 0) {
            logger.error("对生成的svc对象填充空数据时对象属性查找失败.class=" + cls.getName());
            return;
        }

        list.stream().forEach(qo -> {
            //fieldName, jsonKey, field, ignore
            Field field = qo.getThree();
            Class type = field.getType();

            try {
                if (String.class.equals(type)) {
                    field.set(t, "");
                } else if (Integer.class.equals(type)) {
                    field.set(t, 0);
                } else if (Long.class.equals(type)) {
                    field.set(t, 0l);
                } else if (Double.class.equals(type)) {
                    field.set(t, 0.0d);
                } else if (Float.class.equals(type)) {
                    field.set(t, 0.0f);
                } else if (Date.class.equals(type)) {
                    field.set(t, new Date());
                }
            } catch (Exception e) {
                logger.error("填充数据异常.class=" + cls.getName() + ",field=" + field.getName() + ",type=" + type.getName(), e);
            }
        });
    }

    /**
     * 根据任务工单数据创建工单对应的主数据和其他comm数据实例
     *
     * @param task
     * @return
     */
    public DoubleVo<? extends SvcMainEntity, List<? extends SvcCommEntity>> createEntitiesByFwlb(SvcTask task) {
        String fwlb = task.getFwlb();
        if (!"故障处理".equals(fwlb)) {
            return null;
        }

        Class<? extends SvcMainEntity> mainEntityClass = getMainClassByFwlb(fwlb);
        Set<Class<? extends SvcCommEntity>> clsSet = getCommDataSet(fwlb);

        SvcMainEntity mainEntity = createSvcMainEntity(mainEntityClass, task);
        String svcId = null;
        List commEntityList = clsSet.stream().map(cls -> {
            return createSvcCommEntity(cls, task, svcId);
        }).collect(Collectors.toList());
        DoubleVo<? extends SvcMainEntity, List<? extends SvcCommEntity>> vo = new DoubleVo<>(mainEntity, commEntityList);
        return vo;
    }

    //获得服务日志常规数据
    public Set<Class<? extends SvcCommEntity>> getCommDataSet(String fwlb) {
        Set<Class<? extends SvcCommEntity>> set = commConfigMap.keySet().stream().filter(cls -> {
            String configJson = commConfigMap.get(cls).getVal();
            if ("all".equals(configJson))
                return true;
            DoubleVo<Boolean, String> vo = getCommFwlb(configJson);
            boolean persent = Arrays.stream(vo.getVal().split(",")).filter(lb -> lb.equals(fwlb)).findAny().isPresent();
            return vo.getKey() ? persent : !persent;
        }).collect(Collectors.toSet());
        return set;
    }

    //获得comm数据的中文描述名
    public String geteCommDataName(Class<? extends SvcCommEntity> cls) {
        return commConfigMap.get(cls).getKey();
    }

    public Class<? extends SvcMainEntity> getMainClassByFwlb(String fwlbName) {
        return fwlbClassMap.get(fwlbName);
    }

}
