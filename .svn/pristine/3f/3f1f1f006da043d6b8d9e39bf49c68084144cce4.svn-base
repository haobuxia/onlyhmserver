package com.tianyi.helmet.server.controller.client;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.file.Image;
import com.tianyi.helmet.server.entity.file.UploadEntityTypeEnum;
import com.tianyi.helmet.server.service.client.NeteaseUserService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.file.UploadEntityServiceFactory;
import com.tianyi.helmet.server.service.scene.WhiteBoardComponent;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.support.CookieService;
import com.tianyi.helmet.server.util.EncoderUtil;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 打开im。网页视频聊天
 * <p>
 * Created by liuhanc on 2017/11/27.
 */
@Controller
@RequestMapping("im")
public class ImController {
    private Logger logger = LoggerFactory.getLogger(ImController.class);
    @Autowired
    private CookieService cookieService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private WhiteBoardComponent whiteBoardComponent;
    @Autowired
    private UploadEntityServiceFactory uploadEntityServiceFactory;
    @Autowired
    private UserService userService;

    @RequestMapping({"", "index"})
    public String index(String accid, HttpServletRequest request, HttpServletResponse resp) {
        LoginUserInfo lui = LoginUserHolder.get();
        String yunToken = lui.getYunToken();
        if (yunToken == null) {
            logger.error("用户无权进行网易im对话.username=" + lui.getUsername());
            cookieService.deleteCookie(resp, "uid");
            cookieService.deleteCookie(resp, "sdktoken");
            return "redirect:/common/403";
        } else {
            int userLogonExpireMinute = configService.getUserLogonExpireMinute();

            /**
             * update by xiayuan 2018/10/13
             */
            User user = userService.selectById(lui.getId());
            if (user == null) {
                return "redirect:/common/403";
            }
            if (StringUtils.isEmpty(user.getNeUserWeb())) {
                return "redirect:/common/403";
            }
//            NeteaseUser neteaseUser = neteaseUserService.selectByUserId(String.valueOf(user.getId()));
            NeteaseUser neteaseUser = neteaseUserService.selectByUsername(user.getNeUserWeb());
            cookieService.writeCookie(resp, "uid", neteaseUser.getUsername(), userLogonExpireMinute * 60);
            cookieService.writeCookie(resp, "sdktoken", lui.getYunToken(), userLogonExpireMinute * 60);
            return "redirect:/webdemo/im/main.html?accid=" + accid;
        }
    }


    //获取某个头盔最近1次上传的白板图片
    @RequestMapping(value = "whiteboard/image/{neUsername}")
    public void writeWhiteBoardImage(@PathVariable String neUsername, HttpServletResponse resp) {
        whiteBoardComponent.writeWhiteBoardImage(neUsername, resp);
    }


    @RequestMapping(value = "whiteboard/image/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo upload(@RequestParam("imagefile") String fileBase64) {
        if (StringUtils.isEmpty(fileBase64)) {
            return ResponseVo.fail("请求参数imagefile不能为空");
        }
        fileBase64 = fileBase64.trim();
        String start = "data:image/jpeg;base64,";
        if (!fileBase64.startsWith(start)) {
            return ResponseVo.fail("请求参数imagefile不是以data:image/jpeg;base64,开头");
        }
        fileBase64 = fileBase64.substring(start.length());//去掉头部
        byte[] fileBytes = EncoderUtil.base64Decode(fileBase64);

        LoginUserInfo userInfo = LoginUserHolder.get();
        /**
         * update by xiayuan 2018/10/10
         */
        User user = userService.selectById(userInfo.getId());
        String neUsername = user.getNeUserWeb();
        logger.info("网页端白板图片上传.对应网易账号:" + neUsername + ",当前登录田一用户:" + neUsername + ".文件大小=" + fileBytes.length);

        try {
            //图片在当前数据库中对应头盔是以头盔唯一硬件码来识别的，但是web端上传的图片，并没有唯一识别码。所以暂时用neUsername作为clientId传入。
            ResponseVo vo = uploadEntityServiceFactory.getUploadEntityService(UploadEntityTypeEnum.image).addNewFile(fileBytes, neUsername + "-" + System.currentTimeMillis() + ".jpg", "jpg", "网页端白板截图上传", new Date(), userInfo.getId(),"",neUsername, "upload", null, "白板", null, null, null, "", "", "");
            if (vo.isSuccess()) {
                Image image = (Image) vo.getData();
                logger.debug("网页端白板图片上传成功.大小=" + image.getSizeKb() + ",存储路径=" + image.getOssPath() + ",neUsername=" + neUsername);
            } else {
                logger.error("网页端白板图片上传失败." + vo.getMessage() + ",neUsername=" + neUsername);
            }
            return vo;
        } catch (Throwable e) {
            logger.error("保存网页端上传白板文件失败.neUsername=" + neUsername, e);
            return ResponseVo.fail("保存上传文件失败.neUsername=" + neUsername + ".err=" + e.getMessage());
        }
    }

}
