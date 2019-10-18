package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.entity.svc.SvcFaultBrief;
import com.tianyi.helmet.server.entity.svc.SvcFaultBriefEnum;
import com.tianyi.helmet.server.entity.svc.po.SvcRestPage;
import com.tianyi.helmet.server.entity.svc.po.SvcSimpleTask;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.DoubleVo;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.svc.rest.entity.SvcTask;
import com.tianyi.svc.rest.entity.base.SvcCommEntity;
import com.tianyi.svc.rest.entity.base.SvcMainEntity;
import com.tianyi.svc.sdk.Executor;
import com.tianyi.svc.sdk.basic.PageList;
import com.tianyi.svc.sdk.basic.RestRequestBuilder;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 服务日志-任务数据服务
 * 
 * Created by liuhanc on 2018/4/2.
 */
@Service
public class SvcTaskService {

    @Autowired
    private SvcFaultBriefService svcFaultBriefService;
    @Autowired
    private SvcMainRestService svcMainRestService;
    @Autowired
    private SvcCommRestService svcCommRestService;
    @Autowired
    private SvcIdRestService svcRestService;
    @Autowired
    private SvcMetaDataHelper svcMetaDataHelper;

    private Logger logger = LoggerFactory.getLogger(SvcTaskService.class);

    /**
     * 将1个新收到的天远任务存入数据库。
     * 包括task、主数据、各个comm数据
     * 如果各项数据已存在则不insert否则insert
     *
     * @param task
     */
    public void saveNewTask(SvcTask task) {
        String rwh = task.getRwh();
        String oprtId = task.getOprtId();

        logger.debug("存储任务.rwh=" + rwh + ",oprtId=" + oprtId);

        //任务对应的各个数据初始化
        DoubleVo<? extends SvcMainEntity, List<? extends SvcCommEntity>> vo = svcMetaDataHelper.createEntitiesByFwlb(task);
        if (vo == null) {
//            logger.debug("任务不需入库.rwh=" + rwh + ",oprtId=" + oprtId + ",fwlb=" + task.getFwlb());
            return;
        }

        //@todo 通过事务一次性入库 或增加个一次性保存接口
        SvcFaultBrief brief = svcFaultBriefService.selectByRwhOprtId(rwh, oprtId);
        if (brief == null) {
            brief = new SvcFaultBrief(rwh, oprtId);
            svcFaultBriefService.insert(brief);
        }

        //任务入库
//        logger.debug("准备保存task=" + JSON.toJSONString(task));
        SvcTask oldTask = getSvcTask(rwh, oprtId);
        if (oldTask == null) {
            task = (SvcTask) svcRestService.addOne(SvcTask.class, task);
        } else {
            //已存在
            logger.debug("获取的任务已存在，则不存储.rwh=" + rwh + ",oprtId=" + oprtId);
        }

        Class cls = svcMetaDataHelper.getMainClassByFwlb(task.getFwlb());
        SvcMainEntity mainEntity = vo.getKey();

        //主数据入库
//        logger.debug("准备保存工单主数据=" + mainEntity.getClass().getName() + ":" + JSON.toJSONString(mainEntity));
        SvcMainEntity oldMainEntity = svcMainRestService.getOrderByRwhOprtId(cls, cls.getSimpleName(), rwh, oprtId);
        if (oldMainEntity == null) {
            oldMainEntity = (SvcMainEntity) svcRestService.addOne(cls, mainEntity);
        }

        String svcId = String.valueOf(oldMainEntity.getPkValue());
        //常规数据入库
        vo.getVal().stream().forEach(commEntity -> {
            commEntity.setSvcId(svcId);
//            logger.debug("准备保存工单Comm数据=" + commEntity.getClass().getName() + ":" + JSON.toJSONString(commEntity));
            SvcCommEntity oldCommEntity = svcCommRestService.getCommDataByRwhSvcId(commEntity.getClass(), commEntity.getClass().getSimpleName(), rwh, svcId);
            if (oldCommEntity == null) {
                svcRestService.addOne(commEntity.getClass(), commEntity);
            }
        });
    }

    /**
     * 获得某个服务人员某个工单的数据
     *
     * @param rwh
     * @param oprtId
     * @return
     */
    public DoubleVo<? extends SvcMainEntity, List<? extends SvcCommEntity>> getSvcPostData(String rwh, String oprtId) {
        SvcTask task = this.getSvcTask(rwh, oprtId);
        if (task == null)
            return null;

        Class<? extends SvcMainEntity> cls = svcMetaDataHelper.getMainClassByFwlb(task.getFwlb());
        SvcMainEntity mainEntity = svcMainRestService.getOrderByRwhOprtId(cls, cls.getSimpleName(), rwh, oprtId);
        Set<Class<? extends SvcCommEntity>> commClsSet = svcMetaDataHelper.getCommDataSet(task.getFwlb());
        String svcId = String.valueOf(mainEntity.getPkValue());
        List<? extends SvcCommEntity> commList = commClsSet.stream().map(commCls -> {
            SvcCommEntity commEntity = svcCommRestService.getCommDataByRwhSvcId(commCls, commCls.getSimpleName(), rwh, svcId);
            return commEntity;
        }).collect(Collectors.toList());

        return new DoubleVo<>(mainEntity, commList);
    }

    /**
     * 获得任务
     *
     * @param rwh
     * @param oprtId
     * @return
     */
    public SvcTask getSvcTask(String rwh, String oprtId) {
        SvcTask task = (SvcTask) svcMainRestService.getOrderByRwhOprtId(SvcTask.class, SvcTask.class.getSimpleName(), rwh, oprtId);
        return task;
    }

    /**
     * 查询某个日期某人的任务数量统计
     * 包括当日、过期、未过期三种
     * 结果以key-val形式返回。其中key值=
     *
     * @param oprtId
     * @param date
     * @return
     */
    public Map<String, Integer> countTask(String oprtId, LocalDate date) {
        String[] queries = new String[]{"Today", "Expired", "NotExpired"};
        String clsSimpleName = SvcTask.class.getSimpleName();
        String apiName = clsSimpleName.substring(0, 1).toLowerCase() + clsSimpleName.substring(1);
        Map<String, Integer> map = Arrays.stream(queries).collect(Collectors.toMap(key -> Commons.lowerFirst(key), key -> {
            int count = 0;
            try {
                HttpRequestBase request = new RestRequestBuilder("/" + apiName + "/search/countTask" + key, "get")
                        .addParam("oprtId", oprtId)
                        .addParam("countDay", Dates.format(date, "yyyy-MM-dd"))
                        .build();
                count = (Integer) Executor.executeHttp(request, Integer.class, Integer.class);
            } catch (Exception e) {
                logger.error("任务数量统计查询异常.countTask" + key, e);
                count = -1;
            }
            return count;
        }));
        return map;
    }

    /**
     * 查询某个任务分类的任务分页信息
     *
     * @param oprtId
     * @param date
     * @param taskType
     * @param page
     * @return
     */
    public PageListVo<SvcSimpleTask> listTask(String oprtId, LocalDate date, String taskType, SvcRestPage page) {
        String clsSimpleName = SvcTask.class.getSimpleName();
        String apiName = clsSimpleName.substring(0, 1).toLowerCase() + clsSimpleName.substring(1);

        HttpRequestBase request = new RestRequestBuilder("/" + apiName + "/search/findTask" + Commons.upperFirst(taskType), "get")
                .addParam("oprtId", oprtId)
                .addParam("zcwcsj", Dates.format(date, "yyyy-MM-dd"))
                .addParam("rwzt", "5")
                .addParam("page", page.getPage())
                .addParam("size", page.getSize())
                .addParam("sort", page.getSort())
                .build();
        PageList<SvcTask> dataList = (PageList<SvcTask>) Executor.executeHttp(request, SvcTask.class, PageList.class);
        List<SvcTask> taskList = dataList.getList();

        PageListVo<SvcSimpleTask> vo = new PageListVo<>(dataList.getNumber() + 1, dataList.getSize());//田一的首页是1，天远日志的首页是0
        vo.setTotal(dataList.getTotalElements());
        vo.setList(taskList.stream().map(task -> {
            String fwlb = task.getFwlb();
            if ("故障处理".equals(fwlb)) {
                //检查一遍
                SvcFaultBrief old = svcFaultBriefService.selectByRwhOprtId(task.getRwh(), oprtId);
                if (old == null) {
                    old = new SvcFaultBrief();
                    old.setRwh(task.getRwh());
                    old.setOprtId(oprtId);
                    svcFaultBriefService.insert(old);
                }
            }
            return new SvcSimpleTask(task.getRwh(), task.getJh(), task.getFwlb(), task.getRwzt());
        }).collect(Collectors.toList()));
        return vo;
    }


    /**
     * 查询任务列表
     *
     * @param oprtId 任务接收用户Id
     * @param rwzt   0待填写
     *               1已填写未完成
     *               2填写完成未上传
     *               3已上传待审核
     *               4已上传被打回
     *               5已上传审核通过
     * @return
     */
    public PageListVo<SvcTask> listTask(String oprtId, String rwzt, Date time1, Date time2, SvcRestPage page) {
        String clsSimpleName = SvcTask.class.getSimpleName();
        String apiName = clsSimpleName.substring(0, 1).toLowerCase() + clsSimpleName.substring(1);

        HttpRequestBase request = new RestRequestBuilder("/" + apiName + "/search/findByOprtIdAndRwztAndSendTimeBetween", "get")
                .addParam("oprtId", oprtId)
                .addParam("rwzt", rwzt)
                .addParam("time1", Dates.format(time1, "yyyy-MM-dd"))
                .addParam("time2", Dates.format(time2, "yyyy-MM-dd"))
                .addParam("page", page.getPage())
                .addParam("size", page.getSize())
                .addParam("sort", page.getSort())
                .build();
        PageList<SvcTask> dataList = (PageList<SvcTask>) Executor.executeHttp(request, SvcTask.class, PageList.class);
        PageListVo<SvcTask> vo = new PageListVo<SvcTask>(dataList.getNumber() + 1, dataList.getSize());//田一的首页是1，天远日志的首页是0
        vo.setTotal(dataList.getTotalElements());
        vo.setList(dataList.getList());
        return vo;
    }

    /**
     * 更新故障处理工单各个数据项的填写状态
     *
     * @param rwh
     * @param oprtId
     * @param briefEnum
     * @param zt
     * @return
     */
    public boolean updateSvcFaultBriefByRwhOprtId(String rwh, String oprtId, SvcFaultBriefEnum briefEnum, String zt) {
        SvcFaultBrief brief = new SvcFaultBrief();
        brief.setRwh(rwh);
        brief.setOprtId(oprtId);
        DoubleVo<BiConsumer<SvcFaultBrief, String>, Function<SvcFaultBrief, String>> doubleVo = svcFaultBriefService.getBriefConfig(briefEnum);
        doubleVo.getKey().accept(brief, zt);
        logger.debug("保存服务日志填写状态.update." + briefEnum + ",rwh=" + rwh + ",oprtId=" + oprtId);
        boolean success = svcFaultBriefService.updateByRwhOprtId(brief);
        if (success) {
            //检查是否全部填写完毕，如果是，则更新task.rwzt的值
            brief = svcFaultBriefService.selectByRwhOprtId(rwh, oprtId);
            boolean complete = svcFaultBriefService.isComplete(brief);
            if (complete) {
                SvcTask svcTask = this.getSvcTask(rwh, oprtId);
                String oldRwzt = svcTask.getRwzt();
                if (svcTask != null && !"2".equals(oldRwzt)) {
                    //2 表示填写完成待上传
                    svcTask.setRwzt("2");////0待填写，1已填写未完成,2填写完成未上传,3已上传待审核,4已上传被打回,5已上传审核通过
                    svcMainRestService.updateSvcMainEntity(SvcTask.class, SvcTask.class.getSimpleName(), svcTask);
                    logger.debug("工单填写完毕，更新task的任务状态,rwh=" + rwh + ",oprtId=" + oprtId + ".rwzt:" + oldRwzt + "-->2");
                }
            }
        }
        return success;
    }


}
