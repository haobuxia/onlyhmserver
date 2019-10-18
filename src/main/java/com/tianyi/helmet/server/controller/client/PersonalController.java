package com.tianyi.helmet.server.controller.client;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.service.client.NeteaseUserService;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import com.tianyi.helmet.server.service.client.UserComponent;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.log.UserLogService;
import com.tianyi.helmet.server.service.support.VerifyCodeService;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心
 * Created by liuhanc on 2017/11/29.
 */
@Controller
@RequestMapping("personal")
@Api(value = "api", description = "田一用户中心")
public class PersonalController {
    private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);
    @Autowired
    private TianYuanUserService tianYuanUserService;
    @Autowired
    private UserComponent userComponent;
    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 进入个人中心首页
     *
     * @return
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (!userInfo.isTianyuan()) {
            User user = userService.selectById(userInfo.getId());
            model.addAttribute("user", user);
        }
//        else {
//            TianYuanUser tianYuanUser = tianYuanUserService.selectById(userInfo.getId());
//            model.addAttribute("tianYuanUser", tianYuanUser);
//        }
        model.addAttribute("userInfo", userInfo);
        return "user/personIndex";
    }

    //进入二维码账号绑定页面.头盔扫描页面内的二维码后,设置头盔使用当前\账号
    @RequestMapping(value = "qrcode", method = RequestMethod.GET)
    public String qrcode(Model model) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        /**
         * update by xiayuan 2018/10/9
         */
        List<EquipmentLedger> list = equipmentService.selectByUserId(userInfo.getId());
        model.addAttribute("userId", userInfo.getId());//用户Id
        model.addAttribute("time", System.currentTimeMillis());//当前时间
        model.addAttribute("list", list);//当前时间
        model.addAttribute("token", userInfo.getToken());//当前时间
        return "user/tyUserQrcode";
    }


    /**
     * 个人信息
     *
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo info() {
        LoginUserInfo userInfo = LoginUserHolder.get();
        User user = userService.selectById(userInfo.getId());
        user.setPassword(null);
        return ResponseVo.success(userInfo);
    }

    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更新用户密码")
    public ResponseVo updatePwd(@ApiParam(required = true, value="旧密码") String oldpwd,
                                @ApiParam(required = true, value="新密码") String pwd) {
        if (StringUtils.isEmpty(pwd)) {
            return ResponseVo.fail("新密码不能为空");
        }
        if (pwd.length() > 15) {
            return ResponseVo.fail("密码长度不能超过15位");
        }
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo.isTianyuan()) {
            return ResponseVo.fail("天远用户无法更改个人资料");
        }

        User user = userService.selectById(userInfo.getId());
        if (user == null) {
            return ResponseVo.fail("用户信息丢失");
        }
        if (!user.getPassword().equals(oldpwd)) {
            return ResponseVo.fail("旧的密码错误");
        }
        int cnt = userComponent.updateUserPassword(user, pwd, false);
        return cnt == 1 ? ResponseVo.success() : ResponseVo.fail("修改密码失败");
    }

    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo updateInfo(@RequestParam String name, Integer userSex, String company) {
        if (StringUtils.isEmpty(name)) {
            return ResponseVo.fail("姓名不能为空");
        }
        if (name.length() > 5) {
            return ResponseVo.fail("姓名长度不能超过5位");
        }

        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo.isTianyuan()) {
            return ResponseVo.fail("天远用户无法更改个人资料");
        }

        User user = userService.selectById(userInfo.getId());
        if (user == null) {
            return ResponseVo.fail("用户信息丢失");
        }

        int cnt = userComponent.updateUserSimpleInfo(user, name, userSex, company);
        return cnt == 1 ? ResponseVo.success() : ResponseVo.fail("修改个人信息失败");
    }

    @RequestMapping(value = "changeMobileSms", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo changeMobileSms(@RequestParam String operate, @RequestParam String token, @RequestParam String mobile) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo.isTianyuan()) {
            return ResponseVo.fail("天远用户无法更改个人资料");
        }

        boolean success = verifyCodeService.operateVerified(operate, token);
        if (!success) {
            return ResponseVo.fail("请先验证图形验证码");
        }
        if (!Commons.isMobile(mobile)) {
            return ResponseVo.fail("手机号不合法");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        List<User> users = userService.listByNoPage(map);
        if (users != null) {
            return ResponseVo.fail("此手机号已经注册了账号,不可修改您的账号对应手机为这个手机号");
        }
        ResponseVo vo = verifyCodeService.sendSmsVerifyCode(mobile);
        return vo;
    }

    @RequestMapping(value = "changeMobile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo changeMobile(@RequestParam String password, @RequestParam String mobile, @RequestParam String code) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo.isTianyuan()) {
            return ResponseVo.fail("天远用户无法更改个人资料");
        }

        User user = userService.selectById(userInfo.getId());
        if (user == null) {
            return ResponseVo.fail("用户信息丢失");
        }
        if (!password.equals(user.getPassword())) {
            return ResponseVo.fail("密码错误");
        }
        if (!Commons.isMobile(mobile)) {
            return ResponseVo.fail("手机号不合法");
        }
        boolean success = verifyCodeService.verifySmsCode(mobile, code);
        if (!success) {
            return ResponseVo.fail("短信验证码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        List<User> mobileUsers = userService.listByNoPage(map);
        if (mobileUsers != null) {
            return ResponseVo.fail("手机号" + mobile + "已经注册了账号，不可修改您的手机为这个手机号");
        }

        userComponent.updateUserMobile(user.getId(), user.getMobile(), mobile);

        return ResponseVo.success();
    }

    /**
     * 进入列表页面首页
     *
     * @return
     */
    @RequestMapping(value = "loglist")
    public String loglist(String keyword, Model model) {
        return loglist(1, keyword, model);
    }

    @RequestMapping("loglist/{page}")
    public String loglist(@PathVariable Integer page, String keyword, Model model) {
        PageListVo vo = userLogService.queryList(page, keyword, true);
        model.addAttribute("vo", vo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("personLog", true);
        return "log/listUserLog";
    }

}
