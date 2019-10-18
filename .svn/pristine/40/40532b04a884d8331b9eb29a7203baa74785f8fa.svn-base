package com.tianyi.helmet.server.controller.helmetinterface;

import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.service.client.NeteaseUserComponent;
import com.tianyi.helmet.server.service.client.NeteaseUserService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.vo.AppAccountVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 头盔用户接口
 * <p>
 * Created by liuhanc on 2017/11/2.
 */
@Controller
@RequestMapping("user")
@Api(value = "api", description = "头盔用户接口")
public class HelmetUserController {

    private Logger logger = LoggerFactory.getLogger(HelmetUserController.class);
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private NeteaseUserComponent neteaseUserComponent;

//    /**
//     * 手机端头盔端注册账号。
//     * 未来头盔端不可直接注册账号
//     * 手机端注册账号不可调用此接口。
//     *
//     * @param device_id
//     * @param username
//     * @param password
//     * @return
//     */
//    @Deprecated //此功能已删除
//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    @ResponseBody
//    public AppAccountVo register(@RequestParam String device_id, @RequestParam String username, @RequestParam String password) {
//        logger.debug("用户注册:username=" + username + ",device_id=" + device_id + ",password=" + password);
//        AppAccountVo vo = new AppAccountVo("600", "账号注册功能已不受支持,请按正规渠道申请账号");
//        return vo;
//
////        //@todo 网易用户注册完毕后，还需要注册头盔用户，还需要和头盔绑定
////        try{
////            AppAccountVo vo = neteaseUserService.regNeteaseUser(device_id,username,password,1,true,true);
////            return vo;
////        }catch(Exception e){
////            logger.error("注册用户账号异常.username="+username,e);
////            return new AppAccountVo("601","账号注册失败");
////        }
//    }

    /**
     * 20180629:手机端登录
     * 因头盔账号已在初始化时确定了，所以头盔不再登录。
     * 手机端登录需验证账号，头盔账号不能在手机端登录。
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    @Deprecated //头盔的网易账号在头盔初始化时已反馈给头盔此处不再需要
    public AppAccountVo login(@RequestParam() String username, @RequestParam String password) {
        logger.debug("手机app用户登录:username=" + username + ",password=" + password);
        User userInfo = userService.selectByAccount(username);
        if (userInfo == null) {
            return new AppAccountVo("600", "用户名错误");
        }
        if (!password.equals(userInfo.getPassword())) {
            return new AppAccountVo("601", "密码错误");
        }
        if(StringUtils.isEmpty(userInfo.getNeUserPhn())){
            return new AppAccountVo("602", "该账号未绑定网易账号,无法使用网易功能");
        }

        NeteaseUser neUserInfo = neteaseUserService.selectByUsername(userInfo.getNeUserPhn());
        if (neUserInfo == null) {
            return new AppAccountVo("603", "该账号绑定的网易账号丢失,无法使用网易功能");
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("yun_token", neUserInfo.getYunToken());
        dataMap.put("username", neUserInfo.getUsername());
        /**
         * update by xiayuan 2018/10/10
         */
        return new AppAccountVo("200", "登入成功", dataMap);
    }

    /**
     * 刷新用户token
     *
     * @param netUserName
     * @return
     */
    @RequestMapping(value = "regetYunToken", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "刷新网易用户token")
    public AppAccountVo regetYunToken(@ApiParam(name = "netUserName", value = "网易账号", required = true) @RequestParam String netUserName) {
        logger.debug("用户重新获取token:netUserName=" + netUserName);
        NeteaseUser userInfo = neteaseUserService.selectByYunId(netUserName);
        if (userInfo == null) {
            return new AppAccountVo("600", "网易账号不存在:" + netUserName);
        }
        return neteaseUserComponent.regetNeteaseToken(userInfo);
    }

}
