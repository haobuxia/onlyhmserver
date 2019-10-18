/*
package com.tianyi.helmet.server.controller.client;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.*;
import com.tianyi.helmet.server.entity.data.HelmetGps;
import com.tianyi.helmet.server.entity.data.HelmetSensor;
import com.tianyi.helmet.server.service.client.*;
import com.tianyi.helmet.server.service.data.HelmetDataWebSocketService;
import com.tianyi.helmet.server.service.data.HelmetGpsService;
import com.tianyi.helmet.server.service.data.HelmetSensorService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

*/
/**
 * 头盔操作
 * <p>
 * Created by liuhanc on 2017/10/13.
 *//*

@Controller
@RequestMapping("helmet")
public class HelmetController {
    private Logger logger = LoggerFactory.getLogger(HelmetController.class);

    @Autowired
    private HelmetService helmetService;
    @Autowired
    private HelmetComponent helmetComponent;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private NeteaseUserComponent neteaseUserComponent;
    @Autowired
    private HelmetSensorService helmetSensorService;
    @Autowired
    private HelmetGpsService helmetGpsService;
    @Autowired
    private HelmetDataWebSocketService helmetDataWebSocketService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private TianYuanUserService tianYuanUserService;
    @Autowired
    private TianyiUserService tianyiUserService;


    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    */
/**
     * 设置头盔的客户,登记出厂
     *
     * @param helmetId
     *//*

    @RequestMapping("/setCustomer/{helmetId}")
    @ResponseBody
    public ResponseVo<Integer> setCustomer(@PathVariable Integer helmetId, @RequestParam Integer customerId) {
        Helmet helmet = helmetService.getHelmetById(helmetId);
        if (helmet == null) {
            return ResponseVo.fail("头盔不存在." + helmetId);
        }
        int oldCustomerId = helmet.getCustomerId();
        int saleState = helmet.getSaleState();
        if (saleState != 3) {
            logger.error("头盔不是待发售状态时，更新头盔的客户." + oldCustomerId + "-->" + customerId);
        }
        Customer customer = customerService.selectById(customerId);
        if (customer == null) {
            return ResponseVo.fail("客户不存在." + customerId);
        }

        boolean result = helmetComponent.setHelmetSaleInfo(helmet, customerId);
        return result ? ResponseVo.success() : ResponseVo.fail("设置头盔客户失败");
    }

    */
/**
     * 删除某个头盔
     *
     * @param helmetId
     *//*

    @RequestMapping("/remove/{helmetId}")
    @ResponseBody
    public ResponseVo<Integer> remove(@PathVariable Integer helmetId) {
        Helmet helmet = helmetService.getHelmetById(helmetId);
        if (helmet == null) {
            return ResponseVo.fail("头盔不存在." + helmetId);
        }
        int saleState = helmet.getSaleState();
        if (saleState != 0) {
            return ResponseVo.fail("头盔已经初始化,不可删除");
        }

        int cnt = helmetService.deleteHelmet(helmet);
        return cnt == 1 ? ResponseVo.success(cnt) : ResponseVo.fail("删除头盔失败");
    }

    */
/**
     * 修改保存某个头盔的模型批次
     *
     * @param helmetId
     *//*

    @RequestMapping("/save/{helmetId}")
    @ResponseBody
    public ResponseVo<Integer> save(@PathVariable Integer helmetId, @RequestParam String model, @RequestParam String batch) {
        Helmet helmet = helmetService.getHelmetById(helmetId);
        if (helmet == null) {
            return ResponseVo.fail("头盔不存在." + helmetId);
        }

        helmet.setModel(model);
        helmet.setBatch(batch);
        int cnt = helmetService.updateHelmetById(helmet);
        return ResponseVo.success(cnt);
    }

    */
/**
     * 进入头盔地图定位首页
     *
     * @param model
     * @return
     *//*

    @RequestMapping("/index")
    public String index(Model model) {
        ResponseVo<List<QuadrupleVo<String, String, Boolean, HelmetGps>>> retVo = refreshAll();
        if (!retVo.isSuccess()) {
            return "redirect:/common/content403";
        }

        List<QuadrupleVo<String, String, Boolean, HelmetGps>> qvList = retVo.getData();//4个数据项分别是：clilentId,neUsername,online,gps
//        logger.debug("进入已售头盔地图定位功能,头盔=" + tripleVoList);

        Set<String> clientIdSet = qvList.stream().map(trip -> trip.getOne()).collect(Collectors.toSet());
        String websocketToken = helmetDataWebSocketService.generateToken(clientIdSet);

        model.addAttribute("websocketToken", websocketToken);//所有设备在线状态传送token
        model.addAttribute("clientList", JSON.toJSONString(qvList));
        model.addAttribute("type", "helmet");
        return "helmet/helmet3And1";
    }


    */
/**
     * 生成websocket通讯的token
     *
     * @param clientId
     * @return
     *//*

    @RequestMapping("/gettoken")
    @ResponseBody
    public ResponseVo<String> generateWebsocketToken(@RequestParam(required = false) String clientId) {
        Set<String> clientIdSet = null;
        if (StringUtils.isEmpty(clientId)) {
            //所有设备
            ResponseVo<List<QuadrupleVo<String, String, Boolean, HelmetGps>>> retVo = refreshAll();
            List<QuadrupleVo<String, String, Boolean, HelmetGps>> qvList = retVo.getData();//4个数据项分别是：clilentId,neUsername,online,gps
            clientIdSet = qvList.stream().map(trip -> trip.getOne()).collect(Collectors.toSet());
        } else {
            clientIdSet = new HashSet<>(Arrays.asList(clientId));
        }
        String websocketToken = helmetDataWebSocketService.generateToken(clientIdSet);
        return ResponseVo.success(websocketToken);
    }

    //刷新获取某个头盔的某个时间段内的传感器数据
    @RequestMapping("/refresh/range/{clientId}")
    @ResponseBody
    public ResponseVo<List<HelmetSensor>> refreshRange(@PathVariable String clientId, Long date1, Long date2) {
        ResponseVo checkVo = checkHelmetViewRight(clientId);
        if (checkVo != null)
            return checkVo;

        if (date1 == null)
            date1 = Dates.toDate(LocalDateTime.now().minusDays(1)).getTime();
        if (date2 != null)
            date2 = Dates.toDate(LocalDateTime.now()).getTime();

        List<HelmetSensor> sensorList = helmetSensorService.selectByClientId(clientId, date1, date2);
        logger.debug(clientId + " 头盔传感器数据：" + (sensorList == null ? 0 : sensorList.size()));
        return ResponseVo.success(sensorList);
    }

    */
/**
     * 刷新获取某个头盔的所有传感器最新数据
     *
     * @param clientId
     * @return
     *//*

    @RequestMapping("/refresh/{clientId}")
    @ResponseBody
    public ResponseVo<TripleVo> refresh(@PathVariable String clientId) {
        ResponseVo checkVo = checkHelmetViewRight(clientId);
        if (checkVo != null)
            return checkVo;

        HelmetGps gps = helmetGpsService.getLatest(clientId);
        HelmetSensor sensor = helmetSensorService.getLatest(clientId);
        Boolean isOnline = helmetService.isOnLine(clientId);

        TripleVo<Boolean, HelmetGps, HelmetSensor> vo = new TripleVo(isOnline, gps, sensor);
        ResponseVo<TripleVo> retVo = ResponseVo.success(vo);
        return retVo;
    }

    */
/**
     * 刷新所有头盔的定位传感器数据
     * 三个数据项分别是：clilentId,online,gps
     *
     * @return
     *//*

    @RequestMapping("/refreshall")
    @ResponseBody
    public ResponseVo<List<QuadrupleVo<String, String, Boolean, HelmetGps>>> refreshAll() {
        LoginUserInfo userInfo = LoginUserHolder.get();
        List<Helmet> helmetList = null;
        if (userInfo.isAdmin() || userInfo.isDemo()) {
            helmetList = helmetService.selectByType(true);
        } else if (userInfo.isCustomer()) {
            //自己购买的
            helmetList = helmetService.selectByCustomerId(userInfo.getId());
        } else if (userInfo.isSales()) {
            //自己销售的
            helmetList = helmetService.selectBySalerId(userInfo.getId());
        } else {
            //机手看不到
            return ResponseVo.fail("您无权查看头盔数据");
        }

        List<QuadrupleVo<String, String, Boolean, HelmetGps>> clientList = helmetList
                .stream()
                .map(helmet -> {
                    HelmetGps gps = helmetGpsService.getLatest(helmet.getImei());
                    QuadrupleVo<String, String, Boolean, HelmetGps> qv = new QuadrupleVo(helmet.getImei(), helmet.getNeUsername(), helmetService.isOnLine(helmet.getImei()), gps);
                    return qv;
                })
                .collect(Collectors.toList());
        ResponseVo<List<QuadrupleVo<String, String, Boolean, HelmetGps>>> retVo = ResponseVo.success(clientList);
        return retVo;
    }

    */
/**
     * 扩充网易用户账号
     *
     * @return
     *//*

    @RequestMapping("/addAccount")
    @ResponseBody
    public ResponseVo addAccount(@RequestParam int count) {
        if (count <= 0)
            return ResponseVo.fail("数量必须是正数");
        String prefix = configService.getNeteaseUserNamePrefix();
        String maxUserName = neteaseUserService.selectMaxUserName(prefix);
        int minCount = 0010;
        if (maxUserName != null) {
            minCount = Integer.parseInt(maxUserName.substring(prefix.length())) + 1;
        }
        int successCount = 0;
        for (int i = 0; i < count; i++) {
            String userCounter = String.valueOf(minCount + i);
            String username = prefix + Strings.padStart(userCounter, 4, '0');
            //1头盔app用户;2 web/手机app用户 3管理员 4未设置用户类型
            AppAccountVo vo = neteaseUserComponent.regNeteaseUser(username, username, username, 1, true);
            if ("200".equals(vo.getCode()))
                successCount++;
            logger.debug("扩充头盔账号.code=" + vo.getCode() + ",username=" + username);
        }
        return ResponseVo.success(successCount);
    }


    */
/**
     * 进入已售头盔信息列表页面
     *
     * @param model
     * @return
     *//*

    @RequestMapping("/tolist")
    public String toHelletList(Model model) {
        return "helmet/helmetList";
    }


    */
/**
     * 查询头盔列表具体数据列表
     *
     * @param page
     * @param saleState
     * @param model
     * @return
     *//*

    @RequestMapping("/list/{page}")
    public String queryBySaleState(@PathVariable Integer page, Integer saleState, String keyword,Model model) {
        if (page == null) page = 1;
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("keyword",keyword);
        if (saleState != null) {
            if (saleState >= 0) {
                map.put("saleState", saleState);
            } else {
                map.put("notSaleState", -saleState);
            }
        }
        LoginUserInfo userInfo = LoginUserHolder.get();
        if (userInfo.isCustomer()) {
            map.put("customerId", userInfo.getId());
        }

        List<Helmet> helmetList = helmetService.selectBy(map);

        List<Integer> customerIdList = helmetList.stream().map(Helmet::getCustomerId).distinct().filter(id -> id > 0).collect(Collectors.toList());
        if (customerIdList.size() > 0) {
            Map<String, Object> params = new HashMap(1);
            params.put("idList", customerIdList);
            Map<Integer, String> custMap = customerService.selectBy(params).stream().collect(Collectors.toMap(cust -> cust.getId(), cust -> cust.getDisplayName()));
            for (Helmet helmet : helmetList) {
                helmet.setCustomerName(custMap.get(helmet.getCustomerId()));
            }
        }
        helmetList.stream().forEach(helmet->{
            if(helmet.getTianYuanUserId() != null && helmet.getTianYuanUserId() > 0 ){
                TianYuanUser tianYuanUser = tianYuanUserService.selectById(helmet.getTianYuanUserId());
                helmet.setTianYuanUsername(tianYuanUser == null ? "" : (tianYuanUser.getUsername()+" "+tianYuanUser.getOprtName()));
            }
            if(helmet.getTianyiUserId() != null && helmet.getTianyiUserId() > 0 ){
                TianyiUser tianyiUser = tianyiUserService.selectById(helmet.getTianyiUserId());
                helmet.setTianyiUsername(tianyiUser == null ? "" : (tianyiUser.getDisplayName()));
            }
        });
        int count = helmetService.countBy(map);
        PageListVo vo = new PageListVo(page);
        vo.setList(helmetList);
        vo.setTotal(count);
        model.addAttribute("vo", vo);
        return "helmet/helmetListContent";
    }

    */
/**
     * 检查当前用户对某个头盔的查看权限
     *
     * @param clientId
     * @return
     *//*

    private ResponseVo checkHelmetViewRight(String clientId) {
        Helmet helmet = helmetService.getHelmetByImei(clientId);
        if (helmet == null) {
            return ResponseVo.fail("头盔不存在");
        }

        LoginUserInfo userInfo = LoginUserHolder.get();
        if (!userInfo.isAdmin() && !userInfo.isDemo()) {
            if (userInfo.isTianyuan())
                return ResponseVo.fail("天远账号无权查看");
            if (userInfo.isSales() && helmet.getSalerId() != userInfo.getId()) {
                logger.error("当前用户是销售员，查看的不是自己销售的头盔，不允许." + clientId);
                return ResponseVo.fail("销售员无权查看");
            } else if (userInfo.isCustomer() && helmet.getCustomerId() != userInfo.getId()) {
                logger.error("当前用户是客户，查看的不是自己购买的头盔，不允许." + clientId);
                return ResponseVo.fail("客户无权查看");
            }
        }
        return null;
    }
}
*/
