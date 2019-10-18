package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.svc.rest.entity.base.SvcMainEntity;
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

/**
 * 服务日志接口-服务日志主数据
 * <p>
 * Created by liuhanc on 2018/3/8.
 */
@Service
public class SvcMainRestService<T extends SvcMainEntity> {
    @Autowired
    private SvcIdRestService svcIdRestService;

    private Logger logger = LoggerFactory.getLogger(SvcMainRestService.class);


    @CacheEvict(value = CacheKeyConstants.SVC_ORDER_MAIN_BY_ID, key = "#simpleName+'-'+#t.rwh+'-'+#t.oprtId")
    public T updateSvcMainEntity(Class<T> entityClass, String simpleName, T t) {
        Executor executor = ExecutorFactory.getExecutor(entityClass);
        return (T) executor.notNullUpdateById(t);
    }

    /**
     * 根据任务号和用户id查询某个工单实体数据
     *
     * @param tClass
     * @param rwh
     * @return
     */
    @Cacheable(value = CacheKeyConstants.SVC_ORDER_MAIN_BY_ID, key = "#clsSimpleName+'-'+#rwh+'-'+#oprtId", unless = "#result == null")
    public T getOrderByRwhOprtId(Class<T> tClass, String clsSimpleName, String rwh, String oprtId) {
//        Table table = tClass.getAnnotation(Table.class);
//        String apiName = table.name().toLowerCase();
        String apiName = clsSimpleName.substring(0, 1).toLowerCase() + clsSimpleName.substring(1);
        HttpRequestBase request = new RestRequestBuilder("/" + apiName + "/search/getByRwhAndOprtId", "get")
                .addParam("rwh", rwh)
                .addParam("oprtId", oprtId)
                .build();

        return (T) svcIdRestService.selectOne(request, tClass);
    }

}
