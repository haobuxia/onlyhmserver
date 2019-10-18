package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.dao.client.TianyiContactDao;
import com.tianyi.helmet.server.entity.client.TianyiContact;
import com.tianyi.helmet.server.service.support.CacheKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 田一用户的联系人信息服务
 * <p>
 */
@Service
public class TianyiContactService {
    @Autowired
    TianyiContactDao tianyiContactDao;

    @CacheEvict(value = CacheKeyConstants.CONTACTS_BY_USERID, key = "#contact.userId.toString()")
    public void insert(TianyiContact contact) {
        contact.setAddTime(new Date());
        tianyiContactDao.insert(contact);
    }

    @Cacheable(value = CacheKeyConstants.CONTACTS_BY_USERID, key = "#userId.toString()", unless = "#result == null")
    public List<TianyiContact> selectByUserId(int userId) {
        return tianyiContactDao.selectByUserId(userId);
    }

    /**
     * 没有配置联系人的，对应的默认联系人列表
     *
     * @return
     */
    @Cacheable(value = CacheKeyConstants.CONTACTS_DEFAULT, key = "'tianyi'", unless = "#result == null")
    public List<TianyiContact> getDefaultContactList() {
        return tianyiContactDao.selectByUserId(-1);
    }

    @CacheEvict(value = CacheKeyConstants.CONTACTS_BY_USERID, key = "#userId.toString()")
    public int deleteByUserIdContactId(int userId, int contactId) {
        Map<String, Integer> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("contactId", contactId);
        return tianyiContactDao.deleteByUserIdContactId(map);
    }

}
