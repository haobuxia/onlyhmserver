package com.tianyi.helmet.server.service.svc;

import com.tianyi.helmet.server.entity.svc.po.SvcRestPage;
import com.tianyi.svc.rest.entity.SvcCommPic;
import com.tianyi.svc.rest.entity.base.SvcIdEntity;
import com.tianyi.svc.sdk.Executor;
import com.tianyi.svc.sdk.ExecutorFactory;
import com.tianyi.svc.sdk.basic.PageList;
import com.tianyi.svc.sdk.basic.RestRequestBuilder;
import com.tianyi.svc.sdk.exception.RestException;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 服务日志接口-普通数据接口
 * <p>
 * Created by liuhanc on 2018/3/8.
 */
@Service
public class SvcIdRestService<T extends SvcIdEntity> {

    private Logger logger = LoggerFactory.getLogger(SvcIdRestService.class);

    /**
     * 分页查询
     *
     * @param entityClass
     * @param page
     * @return
     */
    public PageList<T> listPage(Class<T> entityClass, SvcRestPage page) {
        Executor executor = ExecutorFactory.getExecutor(entityClass);
        return (PageList<T>) executor.findAll(page.getPage(), page.getSize(), page.getSort());
    }

    /**
     * 添加1个
     *
     * @param entityClass
     * @param t
     * @return
     */
    public T addOne(Class<T> entityClass, T t) {
        Executor executor = ExecutorFactory.getExecutor(entityClass);
        return (T) executor.insert(t);
    }

    public boolean removeOne(Class<T> entityClass, Serializable ID) {
        Executor executor = ExecutorFactory.getExecutor(entityClass);
        return executor.deleteById(ID);
    }

    /**
     * 删除某个图片
     *
     * @param rwh
     * @param svcId
     * @param fieldNameEn
     * @return
     */
    public boolean deleteSvcCommPic(String rwh, String svcId, String fieldNameEn) {
        String uri = "svcCommPic/search/findByRwhAndSvcIdAndFieldNameEn";
        HttpRequestBase request = new RestRequestBuilder(uri, "get")
                .addParam("rwh", rwh)
                .addParam("svcId", svcId)
                .addParam("fieldNameEn", fieldNameEn)
                .build();
        List<SvcCommPic> picList = Executor.executeHttpNoPageList(request, SvcCommPic.class);
        Class picClass = SvcCommPic.class;
        if (!CollectionUtils.isEmpty(picList)) {
            long cnt = picList.stream().mapToInt(pic -> {
                boolean success = this.removeOne(picClass, pic.getId());
                return success ? 1 : 0;
            }).filter(result -> result == 1).count();
            logger.debug("删除图片.rwh=" + rwh + ",svcId=" + svcId + ",fieldName=" + fieldNameEn + ",cnt=" + cnt);
            return cnt > 0;
        }
        return false;
    }

    /**
     * 更新
     *
     * @param entityClass
     * @param t
     * @return
     */
    public T updatSvcIdEntity(Class<T> entityClass, T t) {
        Executor executor = ExecutorFactory.getExecutor(entityClass);
        return (T) executor.notNullUpdateById(t);
    }

    public T selectOne(HttpRequestBase request, Class<T> tClass) {
        try {
            return (T) Executor.executeHttpWithEntity(request, tClass);
        } catch (RestException e) {
            String msg = e.getMessage();
            if (!StringUtils.isEmpty(msg) && msg.contains("code=[404]")) {
                //数据不存在时
                return null;
            }
            throw e;
        }
    }

    public List<T> selectPageList(HttpRequestBase request, Class<T> tClass) {
        try {
            return (List<T>) Executor.executeHttpNoPageList(request, tClass);
        } catch (RestException e) {
            String msg = e.getMessage();
            if (!StringUtils.isEmpty(msg) && msg.contains("code=[404]")) {
                //数据不存在时
                return null;
            }
            throw e;
        }
    }

}
