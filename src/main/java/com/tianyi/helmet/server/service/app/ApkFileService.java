package com.tianyi.helmet.server.service.app;

import com.tianyi.helmet.server.dao.app.ApkFileDao;
import com.tianyi.helmet.server.entity.app.ApkFile;
import com.tianyi.helmet.server.exception.TransException;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * apk文件服务
 *
 * Created by liuhanc on 2017/12/18.
 */
@Service
public class ApkFileService {
    @Autowired
    ApkFileDao apkFileDao;
    @Autowired
    private FastDfsClient fastDfsClient;

    public void insert(ApkFile apk) {
        apkFileDao.insert(apk);
    }

    public ApkFile selectById(int id) {
        return apkFileDao.selectById(id);
    }

    public List<ApkFile> listBy(Map<String, Object> params) {
        return apkFileDao.listBy(params);
    }

    public int countBy(Map<String, Object> params) {
        return apkFileDao.countBy(params);
    }

    public int updateById(ApkFile apk) {
        return apkFileDao.updateById(apk);
    }

    public List<ApkFile> listByIdList(List<Integer> userIdList) {
        Map<String, Object> params = new HashMap(1);
        params.put("idList", userIdList);
        List<ApkFile> list = apkFileDao.listBy(params);
        return list;
    }

    public PageListVo<ApkFile> list(String keyword, Integer page) {
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("keyword", keyword);
        List<ApkFile> list = listBy(map);
        int count = countBy(map);
        PageListVo<ApkFile> vo = new PageListVo(page, list, count);
        return vo;
    }

    @Transactional
    public boolean delete(ApkFile file) throws TransException {
        String ossPath = file.getOssPath();
        int cnt = apkFileDao.deleteById(file.getId());
        if (cnt == 1) {
            boolean success = fastDfsClient.deleteFile(ossPath);
            if (!success) {
                throw new TransException("删除文件失败");
            }
            return success;
        }
        return false;
    }

    @Cacheable(value = CacheKeyConstants.APPVERSION_FORCE, key = "#root.methodName")
    public ApkFile getAppLastForceVersion() {
        return apkFileDao.getAppLastForceVersion();
    }

    @Cacheable(value = CacheKeyConstants.APPVERSION_OPTIONAL, key="#root.methodName")
    public ApkFile getAppLastOptionalVersion() {
        return apkFileDao.getAppLastOptionalVersion();
    }
}
