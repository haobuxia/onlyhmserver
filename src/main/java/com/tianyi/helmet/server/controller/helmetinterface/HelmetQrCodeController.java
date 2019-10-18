//package com.tianyi.helmet.server.controller.helmetinterface;
//
//import com.tianyi.helmet.server.controller.interceptor.HelmetImeiHolder;
//import com.tianyi.helmet.server.entity.client.Helmet;
//import com.tianyi.helmet.server.entity.client.NeteaseUser;
//import com.tianyi.helmet.server.service.client.HelmetComponent;
//import com.tianyi.helmet.server.service.client.HelmetService;
//import com.tianyi.helmet.server.service.client.NeteaseUserService;
//import com.tianyi.helmet.server.vo.AppAccountVo;
//import com.tianyi.helmet.server.vo.ResponseVo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.ServletRequestDataBinder;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 头盔二维码相关接口
// * <p>
// * <p>
// * Created by liuhanc on 2017/10/13.
// */
//@Controller
//@RequestMapping("helmetqr")
//public class HelmetQrCodeController {
//    private Logger logger = LoggerFactory.getLogger(HelmetQrCodeController.class);
//
//    @Autowired
//    private HelmetService helmetService;
//    @Autowired
//    private HelmetComponent helmetComponent;
//    @Autowired
//    private NeteaseUserService neteaseUserService;
//
//    @InitBinder
//    protected void initBinder(ServletRequestDataBinder binder) throws Exception {
//        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        CustomDateEditor editor = new CustomDateEditor(df, false);
//        binder.registerCustomEditor(Date.class, editor);
//    }
//
//    /**
//     * 头盔app扫码后发送绑定请求（头盔绑定后可出厂发售）
//     * 头盔初始化
//     *
//     * @param helmetId
//     * @return
//     */
//    @RequestMapping(value = "/code/{helmetId}", method = RequestMethod.POST)
//    @Transactional
//    @ResponseBody
//    public AppAccountVo helmetQrCodePost(@PathVariable("helmetId") Integer helmetId) {
//        String imei = HelmetImeiHolder.get();
//
//        //管理员和销售才能干绑定的事,但是这是头盔扫码,所以由外部拦截器来检查权限
//        Helmet helmetById = helmetService.getHelmetById(helmetId);
//        if (helmetById == null) {
//            return new AppAccountVo("600", "请求无效URL");
//        }
//
//        boolean needBind = false;
//        String oldImei = helmetById.getImei();
//        if (!StringUtils.isEmpty(oldImei)) {
//            if (oldImei.equals(imei)) {
//                //头盔重复扫码
//                logger.debug("头盔重新扫描自己的二维码.helmetId = " + helmetId + ",imei = " + oldImei+",网易用户id = "+helmetById.getNeUserId());
//                needBind = false;
//            } else {
//                return new AppAccountVo("600", "头盔账号已被使用." + oldImei);
//            }
//        } else {
//            Helmet helmetByImei = helmetService.getHelmetByImei(imei);
//            if (helmetByImei != null) {
//                return new AppAccountVo("600", "当前头盔已绑定有账号." + helmetByImei.getImei());
//            }
//            needBind = true;
//        }
//
//        Integer neUserId = helmetById.getNeUserId();
//        if (neUserId == null) {
//            return new AppAccountVo("600", "头盔数据有问题（未绑定网易账号）,无法使用.");
//        }
//        NeteaseUser neUser = neteaseUserService.selectById(neUserId);
//        if (neUser == null) {
//            return new AppAccountVo("600", "头盔数据有问题（绑定网易账号不存在）,无法使用.");
//        }
//
//        //绑定
//        if (needBind) {
//            helmetById.setImei(imei);
//            ResponseVo vo = helmetComponent.initHelmetClientId(helmetById, neUser);
//            if (!vo.isSuccess()) {
//                return new AppAccountVo("600", "设置硬件识别码失败");
//            }
//        }
//
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        dataMap.put("device_id", neUser.getDeviceId());
//        dataMap.put("username", neUser.getUsername());
//        dataMap.put("yun_token", neUser.getYunToken());
//        return new AppAccountVo("200", "头盔账号绑定成功", dataMap);
//    }
//
////
////    /**
////     * 微信 扫描头盔二维码,进入绑定页面
////     *
////     * @param helmetId
////     * @param model
////     * @param request
////     * @param response
////     * @return
////     */
////    @RequestMapping(value = "/code/{helmetId}", method = RequestMethod.GET)
////    @Transactional
////    public String helmetQrCodeGet(@PathVariable("helmetId") Integer helmetId, Model model, HttpServletRequest request, HttpServletResponse response) {
////        //1 检查clientId是否有效
////        Helmet helmet = helmetService.getHelmetById(helmetId);
////        if (helmet == null) {
////            model.addAttribute("message", "无效的头盔二维码." + helmetId);
////            return "common/error";
////        }
////        model.addAttribute("helmetId", helmetId);
////
////        //2 检查是否已绑定了账号
////        //2.1 检查是否有cookie，如果有cookie则自动绑定当前cookie的用户，之前绑定的人如果是当前人则什么都不做否则自动剔除绑定关系
////        Cookie userphoneCk = cookieService.readCookie(request, "userPhone");
////        Cookie usernameCk = cookieService.readCookie(request, "userName");
////        if (userphoneCk != null && usernameCk != null) {
////            userphoneCk.setMaxAge(Integer.MAX_VALUE);
////            usernameCk.setMaxAge(Integer.MAX_VALUE);
////            cookieService.writeCookie(response, userphoneCk);
////            cookieService.writeCookie(response, usernameCk);
////
////            String userName = EncoderUtil.urlDecode(usernameCk.getValue(), "GBK");
////            model.addAttribute("userPhone", userphoneCk.getValue());
////            model.addAttribute("userName", userName);
////
////            HelmetBindLog log = helmetBindLogService.getLatestBindUser(helmetId);
////            if (log != null && !Objects.equals(log.getUserPhone(), userphoneCk.getValue())) {
////                helmetBindLogService.addClientBindLog(helmet.getId(), userphoneCk.getValue(), userName);
////            }
////
////            //进入已绑定成功界面
////            return "userbind/binded";
////        }
////
////        //2.2 如果没有cookie，则进入绑定界面
////        return "userbind/bind";
////    }
////
////    /**
////     * 绑定传入的用户
////     *
////     * @param helmetId
////     */
////    @RequestMapping("/binduser")
////    @ResponseBody
////    public ResponseVo<Integer> binduser(@RequestParam Integer helmetId, @RequestParam String userPhone, @RequestParam String userName, @RequestParam String msgCode, HttpServletResponse response) {
////        //检查短信
////        boolean success = verifyCodeService.verifySmsCode(userPhone, msgCode);
////        if (!success) {
////            return ResponseVo.fail("手机短信验证码错误");
////        }
////
////        //1 检查clientId是否有效
////        Helmet helmet = helmetService.getHelmetById(helmetId);
////        if (helmet == null) {
////            return ResponseVo.fail("无效的头盔二维码." + helmetId);
////        }
////        logger.debug("binduser.phone = " + userPhone + ",name=" + userName);
////        //检查当前已绑定用户是否就是要绑定的用户，避免重复绑定
////        HelmetBindLog log = helmetBindLogService.getLatestBindUser(helmetId);
////        if (log != null && !Objects.equals(log.getUserPhone(), userPhone)) {
////            helmetBindLogService.addClientBindLog(helmet.getId(), userPhone, userName);
////        }
////
////        cookieService.writeCookie(response, "userPhone", userPhone, Integer.MAX_VALUE);
////        cookieService.writeCookie(response, "userName", EncoderUtil.urlEncode(userName, "GBK"), Integer.MAX_VALUE);
////        return ResponseVo.success(helmetId);
////    }
////
////    /**
////     * 解除绑定的用户
////     *
////     * @param helmetId
////     */
////    @RequestMapping("/unbind")
////    @ResponseBody
////    public ResponseVo<Integer> unbind(@RequestParam Integer helmetId, HttpServletResponse response) {
////        Helmet helmet = helmetService.getHelmetById(helmetId);
////        if (helmet == null) {
////            return ResponseVo.fail("无效的头盔二维码." + helmetId);
////        }
////
////        helmetBindLogService.addClientBindLog(helmet.getId(), null, null);//解除绑定
////
////        cookieService.deleteCookie(response, "userPhone");
////        cookieService.deleteCookie(response, "userName");
////
////        return ResponseVo.success(helmetId);
////    }
//
//
//}
