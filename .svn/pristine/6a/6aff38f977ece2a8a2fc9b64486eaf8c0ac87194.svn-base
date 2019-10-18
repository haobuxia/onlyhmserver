package com.tianyi.helmet.server.controller.helmetinterface;

import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.controller.interceptor.OperaLogHolder;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.file.UploadEntityServiceFactory;
import com.tianyi.helmet.server.service.log.OperaLogService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频功能
 */
@Controller
@RequestMapping("video")
public class VideoController {

    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private OperaLogService operaLogService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo upload(@RequestParam("videofile") MultipartFile file,
                             @RequestParam(value = "createTime") Date createTime, @RequestParam(value = "machineCode", required = false) String machineCode,
                             @RequestParam(value = "orderNo", required = false) String orderNo,
                             @RequestParam(value = "description", required = false) String description, @RequestParam(value = "tag", required = false) String tag,
                             @RequestParam(value = "lon", required = false) Float lon, @RequestParam(value = "lat", required = false) Float lat, @RequestParam(value = "neUserName", required = false) String neUserName) {
        /**
         * update by xiayuan 2018/10/10
         */
        if (OperaLogHolder.get() != null) {
            operaLogService.addNewLog(OperaLogHolder.get(), 0, "调用接口/video/upload成功", 0);
        }

        String helmetImei = HelmetImeiHolder.get();
        int userId = -1;
        if (neUserName != null && neUserName.length() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("neUserHel", neUserName);
            List<User> list = userService.listByNoPage(map);
            if (list.size() == 1) {
                User user = list.get(0);
                userId = user.getId();
            }
        } else if(!StringUtils.isEmpty(helmetImei)) {
            userId = equipmentService.selectByUUID(helmetImei).getUserId();
        }
        logger.info("video upload post.clientId=" + helmetImei + ",description=" + description + ",createTime=" + createTime + ",machineCode=" + machineCode + ",tag=" + tag + ",orderNo=" + orderNo);
        ResponseVo vo = uploadEntityServiceFactory.upload(UploadEntityTypeEnum.video, file, userId, helmetImei, neUserName, createTime, machineCode, description, tag, orderNo, lon, lat, "", "", "");
        return vo;
    }

}
