package com.tianyi.helmet.server.service.file;

import com.tianyi.helmet.server.entity.file.UploadEntity;
import com.tianyi.helmet.server.vo.ResponseVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 各种上传资源服务接口
 * <p>
 * Created by liuhanc on 2017/10/26.
 */
public interface UploadEntityService<T extends UploadEntity> {

    void insert(T t);

    void insert(T t, String tag);

    T selectById(int id);

    List<T> listBy(Map<String, Object> params);

    int countBy(Map<String, Object> params);

    int increaseViewCount(int id);

    int deleteById(int id);

    /**
     * update by xiayuan 2018/10/10
     */
    ResponseVo<T> addNewFile(byte[] bytes, String fileName, String suffix, String description, Date createTime,int userId, String clientId,String neUserName, String src, String machineCode, String tag, String orderNo, Float lon, Float lat, String caller, String called, String source);

}
