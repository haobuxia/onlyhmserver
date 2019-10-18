package com.tianyi.helmet.server.controller.helmetinterface;

import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.service.file.UploadEntityServiceFactory;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片上传
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo upload(@RequestParam("imagefile") MultipartFile file,
                             @RequestParam(value = "createTime") Date createTime, String machineCode, String description, String tag, @RequestParam("neUserName") String neUserName,@RequestParam(value = "orderNo", required = false) String orderNo,HttpServletRequest request) {
        /**
         * update by xiayuan 2018/10/10
         */
        String helmetImei = HelmetImeiHolder.get();
        int userId = request.getIntHeader("userId");

        logger.info("image upload post.clientId=" + helmetImei + ",description=" + description + ",createTime=" + createTime + ",tag=" + tag);
        ResponseVo vo = uploadEntityServiceFactory.upload(UploadEntityTypeEnum.image, file,userId,helmetImei, neUserName, createTime, machineCode, description, tag, orderNo, null, null, "", "", "");
        return vo;
    }

}
