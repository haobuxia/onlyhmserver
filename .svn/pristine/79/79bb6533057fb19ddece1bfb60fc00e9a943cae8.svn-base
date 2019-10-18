package com.tianyi.helmet.server.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.helmetinterface.HelmetUserController;
import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
import com.tianyi.helmet.server.entity.client.Company;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.NeteaseUser;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.service.client.*;
import com.tianyi.helmet.server.service.redis.JsonRedisTemplate;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.service.support.CookieService;
import com.tianyi.helmet.server.service.support.VerifyCodeService;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.util.IpUtil;
import com.tianyi.helmet.server.util.image.ValidateCode;
import com.tianyi.helmet.server.vo.AppAccountVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tianyi.helmet.server.service.support.CacheKeyConstants.APP_USER_BY_IMEI;

/**
 *  通用不需登录访问的url
 *
 * Created by liuhanc on 2017/11/24.
 */
@Controller
@RequestMapping("common")
@Api(value = "api", description = "田一后台公共接口")
public class CommonController {
    private Logger logger = LoggerFactory.getLogger(HelmetUserController.class);

    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private TianYuanUserComponent tianYuanUserComponent;
    @Autowired
    private LoginUserTokenService loginUserTokenService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private UserComponent userComponent;
    @Autowired
    private ConfigService configService;
    @Autowired
    private UserService userService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private JsonRedisTemplate jedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("403")
    public String to403() {
//        return "common/403";
        return "redirect:/index";
    }

    @RequestMapping("content403")
    public String toContent403() {
        return "common/content403";
    }

    @RequestMapping("404")
    public String to404() {
        return "common/404";
    }

    @RequestMapping("content404")
    public String toContent404() {
        return "common/content404";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "user/loginForm";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "user/registerForm";
    }


    /**
     * web登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public AppAccountVo login(@RequestParam String username, @RequestParam String password, @RequestParam String loginType, HttpServletRequest req, HttpServletResponse resp) {
        logger.debug("web用户登录:username=" + username + ",loginType=" + loginType);

//        AbstractUserInfo userInfo = null;
        User userInfo = null;
        if ("1".equals(loginType)) {
            //田一用户登录
//            userInfo = tianyiUserService.selectByUsername(username);
            userInfo = userService.selectByAccount(username);
            if (userInfo == null) {
                return new AppAccountVo("600", "用户名错误");
            }
//            if(userInfo.getNeUserWeb() == null){
//                return new AppAccountVo("600", "该用户还在处理中，请稍后再试");
//            }
            if (!password.equals(userInfo.getPassword())) {
                return new AppAccountVo("601", "密码错误");
            }
        }
//        else if ("2".equals(loginType)) {
//            //天远用户登录
//            userInfo = tianYuanUserComponent.userPassLogin(username, password);
//        }
        else {
            return new AppAccountVo("600", "用户名错误");
        }

        LoginUserInfo lui = loginUserTokenService.getCurrentLoginUserInfo(req);
        String clientIp = IpUtil.getRequestIp(req);
        String userAgent = req.getHeader("User-Agent");
        String loginToken = userComponent.userLogonSuccess(userInfo, lui, clientIp, userAgent);

        resp.addHeader(LoginUserTokenService.LOGINUSER_TOKEN_NAME, loginToken);
        resp.addHeader("userId", userInfo.getId()+"");
        cookieService.writeCookie(resp, LoginUserTokenService.LOGINUSER_TOKEN_NAME, loginToken, configService.getUserLogonExpireMinute() * 60);
        cookieService.writeCookie(resp, "userId", userInfo.getId()+"", configService.getUserLogonExpireMinute() * 60);

        return new AppAccountVo("200", "登入成功", null);
    }

    /**
     * 忘记密码
     *
     * @return
     */
    @RequestMapping(value = "forget", method = RequestMethod.GET)
    public String forget(HttpServletRequest req, HttpServletResponse resp) {
        return "user/forgetForm";
    }

    /**
     * 初始化图形验证码生成
     *
     * @return
     */
    @RequestMapping(value = "/captchaGen/{operate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo captchaGen(@PathVariable String operate) throws IOException {
        String token = verifyCodeService.genereateToken(operate);
        return ResponseVo.success(token);
    }

    /**
     * 输出图形验证码
     *
     * @return
     */
    @RequestMapping(value = "/captchaimg/{operate}/{token}", method = RequestMethod.GET)
    public void captchaImg(@PathVariable String operate, @PathVariable String token, HttpServletResponse resp) throws IOException {
        // 设置响应的类型格式为图片格式
        resp.setContentType("image/jpeg");
        //禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        ValidateCode vCode = verifyCodeService.getValidateCode(operate, token);
        if (vCode != null) {
            vCode.write(resp.getOutputStream());
        } else {
            resp.setStatus(404);
        }
    }

    /**
     * 图形验证码验证
     *
     * @return
     */
    @RequestMapping(value = "/captchaVerify/{operate}/{token}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo captchaVerify(@PathVariable String operate, @PathVariable String token, @RequestParam String code) throws IOException {
        return verifyCodeService.verifyCode(operate, token, code);
    }

    /**
     * 发送注册短信验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "registerSms", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo registerSmsPost(@RequestParam String operate, @RequestParam String token, @RequestParam String mobile) {
        boolean success = verifyCodeService.operateVerified(operate, token);
        if (!success) {
            return ResponseVo.fail("请先验证图形验证码");
        }
        if (!Commons.isMobile(mobile)) {
            return ResponseVo.fail("手机号不合法");
        }
        //检查手机号是否真实存在
        Map<String,Object> map = new HashMap<>();
        map.put("mobile",mobile);
        List<User> users = userService.listByNoPage(map);
        if (users != null) {
            return ResponseVo.fail("此手机号已经注册账号");
        }
        ResponseVo vo = verifyCodeService.sendSmsVerifyCode(mobile);
        return vo;
    }

    /**
     * 发送绑定头盔验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "bindSms", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo bindSmsPost(@RequestParam String operate, @RequestParam String token, @RequestParam String mobile) {
        boolean success = verifyCodeService.operateVerified(operate, token);
        if (!success) {
            return ResponseVo.fail("请先验证图形验证码");
        }
        //@todo手机号校验?
        if (!Commons.isMobile(mobile)) {
            return ResponseVo.fail("手机号不合法");
        }
        ResponseVo vo = verifyCodeService.sendSmsVerifyCode(mobile);
        return vo;
    }

    /**
     * 发送找回密码短信验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "forgetSms", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo forgetSmsPost(@RequestParam String operate, @RequestParam String token, @RequestParam String mobile) {
        boolean success = verifyCodeService.operateVerified(operate, token);
        if (!success) {
            return ResponseVo.fail("请先验证图形验证码");
        }
        //检查手机号是否真实存在
        if (!Commons.isMobile(mobile)) {
            return ResponseVo.fail("手机号不合法");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("mobile",mobile);
        List<User> users = userService.listByNoPage(map);
        if (users == null) {
            return ResponseVo.fail("此手机号并未注册账号");
        }
        ResponseVo vo = verifyCodeService.sendSmsVerifyCode(mobile);
        return vo;
    }

    /**
     * web端注册
     *
     * @return
     */
//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseVo registerPost(@RequestParam String mobile, @RequestParam String code, @RequestParam String password,
//                                   HttpServletRequest req, HttpServletResponse resp) {
//        logger.debug("用户注册:mobile=" + mobile + ",code=" + code + ",password=" + password);
//        boolean success = verifyCodeService.verifySmsCode(mobile, code);
//        if (!success) {
//            return ResponseVo.fail("手机短信验证码错误");
//        }
//        if (!Commons.isMobile(mobile)) {
//            return ResponseVo.fail("手机号不合法");
//        }
//        TianyiUser user = tianyiUserService.selectByMobile(mobile);
//        if (user != null) {
//            return ResponseVo.fail("此手机号已经注册账号");
//        }
//
//        //String userName,String mobile, String password,String name,String company,int userSex,String neUserName,String roleCodes
//        user = userComponent.addNewUser(mobile, mobile, password, mobile, null, 2, mobile, null);//注册用户默认开通网易账号
//        //注册成功，自动登录
//        makeUserLogon(user, req, resp);
//        return ResponseVo.success();
//    }

    /**
     * 找回密码
     *
     * @return
     */
//    @RequestMapping(value = "forget", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseVo forgetPost(@RequestParam String mobile, @RequestParam String code, @RequestParam String password,
//                                 HttpServletRequest req, HttpServletResponse resp) {
//        logger.debug("用户找回密码:mobile=" + mobile + ",code=" + code + ",password=" + password);
//        boolean success = verifyCodeService.verifySmsCode(mobile, code);
//        if (!success) {
//            return ResponseVo.fail("手机短信验证码错误");
//        }
//        if (!Commons.isMobile(mobile)) {
//            return ResponseVo.fail("手机号不合法");
//        }
//        TianyiUser user = tianyiUserService.selectByMobile(mobile);
//        if (user == null) {
//            return ResponseVo.fail("此手机号并未注册账号");
//        }
//
//        userComponent.updateUserPassword(user, password, true);
//
//        //重置密码成功，自动登录
//        makeUserLogon(user, req, resp);
//        return ResponseVo.success();
//    }

    /**
     * web端注册
     *
     * @return
     */
    @RequestMapping(value = "testsign")
    @ResponseBody
    public ResponseVo testSign(HttpServletRequest req, HttpServletResponse resp) {
        logger.debug("testSign");
        return ResponseVo.success();
    }

    @RequestMapping(value = "app/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "手机APP登陆接口")
    public ResponseVo appLogin(@ApiParam(value = "登陆账号", name = "name", required = true) @RequestParam String name,
                               @ApiParam(value = "登陆密码", name = "password", required = true) @RequestParam String password) {

        /*
          1、校验田一用户密码
          2、获取网易用户
          3、缓存imei+田一用户={天远账号，天远账号ID}
         */

        User userInfo = userService.selectByAccount(name);
        if (userInfo == null) {
            return ResponseVo.fail("用户名错误");
        }
        if (!password.equals(userInfo.getPassword())) {
            return ResponseVo.fail("密码错误");
        }

        Company company = companyService.selectById(userInfo.getOrganisation());

        NeteaseUser neUserInfo = neteaseUserService.selectByUsername(userInfo.getNeUserPhn());
        JSONObject appUserJson = (JSONObject) JSONObject.toJSON(userInfo);
        appUserJson.put("neUserName", userInfo.getNeUserPhn());
        appUserJson.put("yun_token", neUserInfo.getYunToken());
        appUserJson.put("userId", userInfo.getId());
        appUserJson.put("imei", HelmetImeiHolder.get());
        appUserJson.put("company", neUserInfo.getCompany());

        if (StringUtils.isEmpty(HelmetImeiHolder.get())) {
            return ResponseVo.fail("imei非法");
        }

        Map<String, Object> appLoginInfo = (Map<String, Object>) redisTemplate.opsForHash().get(APP_USER_BY_IMEI, HelmetImeiHolder.get());
        if (appLoginInfo == null) {
            appLoginInfo = new HashMap<>();
            appLoginInfo.put("neUserName", userInfo.getNeUserPhn());
            appLoginInfo.put("yun_token", neUserInfo.getYunToken());
            appLoginInfo.put("userId", userInfo.getId());
            appLoginInfo.put("imei", HelmetImeiHolder.get());
            appLoginInfo.put("company", neUserInfo.getCompany());
            redisTemplate.opsForHash().put(APP_USER_BY_IMEI, HelmetImeiHolder.get(), appLoginInfo);
        } else {
            if (!Integer.valueOf(userInfo.getId()).equals(appLoginInfo.get("userId"))) {
                //更新登陆信息
                appLoginInfo.put("userId", userInfo.getId());
                appLoginInfo.put("neUserName", userInfo.getNeUserPhn());
                appLoginInfo.put("yun_token", neUserInfo.getYunToken());
                appLoginInfo.put("company", neUserInfo.getCompany());
                redisTemplate.opsForHash().put(APP_USER_BY_IMEI, HelmetImeiHolder.get(), appLoginInfo);
            }
        }
        //删除其他用户的登陆信息
        String otherImei = null;
        for (Cursor it = redisTemplate.opsForHash().scan(APP_USER_BY_IMEI, ScanOptions.NONE); it.hasNext(); ) {
            Map.Entry<Object, Map<String, Object>> entry = (Map.Entry<Object, Map<String, Object>>) it.next();
            if (!HelmetImeiHolder.get().equals(entry.getValue().get("imei"))) {
                if (Integer.valueOf(userInfo.getId()).equals(entry.getValue().get("userId"))) {
                    if (logger.isDebugEnabled())
                        logger.debug("有重复的手机登账号" + entry.getValue().get("userId") + ",imei:" + entry.getValue().get("imei"));
                    otherImei = (String) entry.getValue().get("imei");
                    break;
                }
            }
        }
        if (otherImei != null) {
            redisTemplate.opsForHash().delete(APP_USER_BY_IMEI, otherImei);
        }

        Map retInfo = new HashMap();
        retInfo.put("neUserName", appUserJson.get("neUserName"));
        retInfo.put("yun_token", appUserJson.get("yun_token"));
        retInfo.put("company", appUserJson.get("company"));
        retInfo.put("userName", appUserJson.get("name"));
        retInfo.put("userId", String.valueOf(appUserJson.get("id")));
        retInfo.put("department", userInfo.getDepartment());
        retInfo.put("companyName", company.getCompanyName());
        retInfo.put("mobile", appUserJson.get("mobile"));
        String sex = String.valueOf(appUserJson.get("sex"));
        if (sex != null) {
            sex = "1".equals(sex) ? "男" : "女";
        }
        retInfo.put("sex", sex);
        return ResponseVo.success(retInfo);
    }

    /**
     * 设置用户自动登录
     *
     * @param tianyiUser
     * @param req
     * @param resp
     */
//    protected void makeUserLogon(TianyiUser tianyiUser, HttpServletRequest req, HttpServletResponse resp) {
//        LoginUserInfo lui = loginUserTokenService.getCurrentLoginUserInfo(req);
//        String clientIp = IpUtil.getRequestIp(req);
//        String userAgent = req.getHeader("User-Agent");
//        String loginToken = userComponent.userLogonSuccess(tianyiUser, lui, clientIp, userAgent);
//
//        resp.addHeader(LoginUserTokenService.LOGINUSER_TOKEN_NAME, loginToken);
//        cookieService.writeCookie(resp, LoginUserTokenService.LOGINUSER_TOKEN_NAME, loginToken, configService.getUserLogonExpireMinute() * 60);
//    }

}
