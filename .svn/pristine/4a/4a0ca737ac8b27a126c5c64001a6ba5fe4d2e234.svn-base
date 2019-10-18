package com.tianyi.helmet.server.service.app;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.dao.app.ApkUpdateDao;
import com.tianyi.helmet.server.entity.app.ApkFile;
import com.tianyi.helmet.server.entity.app.ApkUpdate;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import com.tianyi.helmet.server.util.RelationUtils;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * apk升级配置服务
 *
 * Created by liuhanc on 2017/12/18.
 */
@Service
public class ApkUpdateService {
    @Autowired
    private ApkUpdateDao apkUpdateDao;
    @Autowired
    private ApkFileService apkFileService;

    @CacheEvict(value = CacheKeyConstants.APKUPDATE_BY_PK, key = "#clientId+'-type-'+#apkFile.fileType")
    public void addNewUpdate(String clientId, ApkFile apkFile) {
        ApkUpdate apk = new ApkUpdate();
        apk.setClientId(clientId);
        apk.setApkId(apkFile.getId());
        apk.setApkFileType(apkFile.getFileType());
        apk.setCreateTime(LocalDateTime.now());
        apk.setCreateUserId(LoginUserHolder.get().getId());
        apkUpdateDao.insert(apk);
    }

    @Cacheable(value = CacheKeyConstants.APKUPDATE_BY_PK, key = "#clientId+'-type-'+#apkFileType", unless = "#result == null")
    public ApkUpdate getLatestByClientIdFileType(String clientId, String apkFileType) {
        Map<String, Object> params = PageListVo.createParamMap(1, 1);
        params.put("clientId", clientId);
        params.put("apkFileType", apkFileType);
        params.put("status", 0);
        List<ApkUpdate> list = listBy(params);
        if (CollectionUtils.isEmpty(list))
            return null;
        return list.get(0);
    }

    public ApkUpdate selectById(int id) {
        return apkUpdateDao.selectById(id);
    }

    @CacheEvict(value = CacheKeyConstants.APKUPDATE_BY_PK, key = "#update.clientId+'-type-'+#update.apkFileType")
    public int deleteById(ApkUpdate update) {
        return apkUpdateDao.deleteById(update.getId());
    }

    @CacheEvict(value = CacheKeyConstants.APKUPDATE_BY_PK, key = "#update.clientId+'-type-'+#update.apkFileType")
    public int updateById(ApkUpdate update) {
        return apkUpdateDao.updateById(update);
    }

    public int updateStatusByImeiAndStatus(Map<String, Object> update) {
        return apkUpdateDao.updateStatusByImeiAndStatus(update);
    }

    public List<ApkUpdate> listBy(Map<String, Object> params) {
        return apkUpdateDao.listBy(params);
    }

    public int countBy(Map<String, Object> params) {
        return apkUpdateDao.countBy(params);
    }

    public PageListVo<ApkUpdate> list(String clientId, Integer page) {
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("clientId", clientId);
        List<ApkUpdate> list = listBy(map);
        int count = countBy(map);
        PageListVo<ApkUpdate> vo = new PageListVo(page, list, count);
        return vo;
    }

    public void fullfilApkFile(List<ApkUpdate> updateList) {
        RelationUtils.fullfilListRelateProperty(updateList, ApkUpdate::getApkId, apkFileService::listByIdList, ApkFile::getId, apkFile -> apkFile, ApkUpdate::setApkFile);
    }
}
