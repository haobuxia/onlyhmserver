package com.tianyi.helmet.server.service.scene;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.file.Image;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.file.ImageService;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.util.ReqRespUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 白板相关
 * Created by liuhanc on 2018/6/29.
 */
@Component
public class WhiteBoardComponent {
    private Logger logger = LoggerFactory.getLogger(WhiteBoardComponent.class);

    @Autowired
    private ImageService imageService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    public void writeWhiteBoardImage(String neUserName, HttpServletResponse resp) {
        /**
         * update by xiayuan 2018/10/10
         */
//        EquipmentLedger helmet = null;
//        Map<String,Object> map = new HashMap<>();
//        map.put("neUserHel",neUsername);
//        List<User> users = userService.listByNoPage(map);
//        User user = users.get(0);
//        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
//        for (EquipmentLedger e : list) {
//            String state = (String) redisTemplate.opsForValue().get(e.getDeviceUUID());
//            if (MyConstants.DEVICE_STATE_ON.equals(state)) {
//                helmet = e;
//            }
//        }
//        String helmetImei = helmet.getDeviceUUID();
//        if (StringUtils.isEmpty(helmetImei)) {
//            //对应的头盔不存在
//            ReqRespUtils.writeToResponse(resp, "text/json; charset=utf-8", "url对应头盔不存在." + neUsername, 403);
//            return;
//        }

        //因为web端也可以上传白板图片了，而在当前数据库表中图片来源标志是clientId(头盔硬件码)，所以实际做法中web端上传的白板图片对应的clientId设置为web用户的neUsername了
        //所以此处根据头盔imei查询一次，根据neUsername查询一次，再比较二者最新的作为最终的白板图片。 @toto 需优化
        Image helmetImage = imageService.getLatestWhiteBoardImage(neUserName);
        Image webImage = imageService.getLatestWhiteBoardImage(neUserName);

        if (helmetImage == null && webImage == null) {
            ReqRespUtils.writeToResponse(resp, "text/json; charset=utf-8", "找不到白板图片." + neUserName, 403);
            return;
        }

        Image finalImage = null;
        if(helmetImage != null && webImage != null){
            //都有，则检查哪个最新
            LocalDateTime helmetTm = helmetImage.getCreateTime();
            LocalDateTime webTm = webImage.getCreateTime();
            if(helmetTm.isAfter(webTm)){
                finalImage = helmetImage;
            }else{
                finalImage = webImage;
            }
        }else{
            finalImage = helmetImage != null ? helmetImage : webImage;
        }

        String ossPath = finalImage.getOssPath();
        if (StringUtils.isEmpty(ossPath)) {
            ReqRespUtils.writeToResponse(resp, "text/json; charset=utf-8", "白板图片丢失." + neUserName, 404);
            return;
        }

        // 设置响应的类型格式为图片格式
        try {
            ReqRespUtils.writeFdfsFile(resp, fastDfsClient, ossPath, "image/jpeg");
        } catch (Exception e) {
            logger.error("输出白板图片异常.neUsername=" + neUserName, e);
        }
    }
}
