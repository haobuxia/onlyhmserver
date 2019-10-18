package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.svc.rest.entity.SvcCommPic;
import com.tianyi.svc.rest.entity.base.SvcCommEntity;
import com.tianyi.svc.sdk.Executor;
import com.tianyi.svc.sdk.ExecutorFactory;
import com.tianyi.svc.sdk.basic.RestRequestBuilder;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务日志接口--服务日志公用数据
 * <p>
 * Created by liuhanc on 2018/3/8.
 */
@Service
public class SvcCommRestService<T extends SvcCommEntity> {
    @Autowired
    private SvcMainRestService svcMainRestService;
    @Autowired
    private SvcIdRestService svcIdRestService;
    @Autowired
    private SvcTaskService svcTaskService;
    @Autowired
    private SvcMetaDataHelper svcMetaDataHelper;

    private Logger logger = LoggerFactory.getLogger(SvcCommRestService.class);

    /**
     * 更新数据
     *
     * @param entityClass
     * @param t
     * @return
     */
    @CacheEvict(value = CacheKeyConstants.SVC_ORDER_COMM_BY_ID, key = "#clsSimpleName+'-'+#t.rwh+'-'+#t.svcId")
    public T updateSvcCommEntity(Class<T> entityClass, String clsSimpleName, T t) {
        Executor executor = ExecutorFactory.getExecutor(entityClass);
        return (T) executor.notNullUpdateById(t);
    }

    /**
     * 根据任务号和主数据Id得到对应的某个comm类型数据
     *
     * @param tClass
     * @param rwh
     * @return
     */
    @Cacheable(value = CacheKeyConstants.SVC_ORDER_COMM_BY_ID, key = "#clsSimpleName+'-'+#rwh+'-'+#mainDataSvcId", unless = "#result == null")
    public T getCommDataByRwhSvcId(Class<T> tClass, String clsSimpleName, String rwh, String mainDataSvcId) {
//        Table table = tClass.getAnnotation(Table.class);
//        String apiName = table.name().toLowerCase();
        String apiName = clsSimpleName.substring(0, 1).toLowerCase() + clsSimpleName.substring(1);
        HttpRequestBase request = new RestRequestBuilder("/" + apiName + "/search/getByRwhAndSvcId", "get")
                .addParam("rwh", rwh)
                .addParam("svcId", mainDataSvcId)
                .build();
        return (T) svcIdRestService.selectOne(request, tClass);
    }

    /**
     * 根据任务号和svcId查询任务对应所有图片
     *
     * @param rwh
     * @param mainDataSvcId
     * @return
     */
    public List<T> listSvcCommPicByRwhSvcId(String rwh, String mainDataSvcId) {
        Class tClass = SvcCommPic.class;
        String clsSimpleName = tClass.getSimpleName();
//        Table table = (Table)tClass.getAnnotation(Table.class);
//        String apiName = table.name().toLowerCase();
        String apiName = clsSimpleName.substring(0, 1).toLowerCase() + clsSimpleName.substring(1);
        HttpRequestBase request = new RestRequestBuilder("/" + apiName + "/search/findByRwhAndSvcId", "get")
                .addParam("rwh", rwh)
                .addParam("svcId", mainDataSvcId)
                .build();
        return (List<T>) svcIdRestService.selectPageList(request, tClass);
    }

}
