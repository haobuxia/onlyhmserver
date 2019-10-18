package com.tianyi.helmet.server.service.data;

import com.tianyi.helmet.server.dao.data.HelmetChargeDao;
import com.tianyi.helmet.server.dao.data.HelmetOnlineStatusDao;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.data.HelmetCharge;
import com.tianyi.helmet.server.entity.data.HelmetHeartBeat;
import com.tianyi.helmet.server.entity.data.HelmetOnlineStatistics;
import com.tianyi.helmet.server.entity.data.HelmetOnlineStatus;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.Tag;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.TagService;
import com.tianyi.helmet.server.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计头盔在线时长
 * Created by tianxujin on 2019/3/14.
 */
@Service
public class HelmetChargeService {
    @Autowired
    private HelmetChargeDao helmetChargeDao;

    public void insert(HelmetCharge helmetCharge) {
        helmetChargeDao.insert(helmetCharge);
    }

    public int countBy(Map<String, Object> param) {
        return helmetChargeDao.countBy(param);
    }
}
