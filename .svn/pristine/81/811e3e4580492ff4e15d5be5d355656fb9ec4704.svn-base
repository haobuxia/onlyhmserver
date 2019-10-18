package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.*;
import com.tianyi.helmet.server.entity.device.EquipmentLedger;
import com.tianyi.helmet.server.entity.log.UserLogTypeEnum;
import com.tianyi.helmet.server.service.device.EquipmentService;
import com.tianyi.helmet.server.service.job.MqttBackendSubscribeJob;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.log.UserLogService;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.util.Browser;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.util.MyConstants;
import com.tianyi.helmet.server.vo.AppAccountVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户相关
 * <p>
 * Created by liuhanc on 2017/12/19.
 */
@Component
public class UserComponent {
    private Logger logger = LoggerFactory.getLogger(UserComponent.class);

    @Autowired
    private UserLogService userLogService;
    @Autowired
    private LoginUserTokenService loginUserTokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private TianyiUserRoleService tianyiUserRoleService;
    @Autowired
    private NeteaseUserService neteaseUserService;
    @Autowired
    private NeteaseUserComponent neteaseUserComponent;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RedisMqPublisher redisMqPublisher;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private MqttBackendSubscribeJob mqttBackendSubscribeJob;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 服务日志用户任务刷新
     *
     * @param tianYuanUserId
     */
    public void addToSvcTaskRefreshQueue(int tianYuanUserId) {
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_SvcTask_Refresh, String.valueOf(tianYuanUserId));
    }

    /**
     * 更新用户的手机号
     *
     * @param userId
     * @param oldMobile
     * @param newMobile
     */
    @Transactional
    public void updateUserMobile(int userId, String oldMobile, String newMobile) {
        /**
         * update by xiayuan 2018/10/13
         */

        User user = new User();
        user.setId(userId);
        user.setMobile(newMobile);
        userService.update(user);
        //如果用户是客户，还需要更新客户资料里的手机号
        /**
         * update by xiayuan 2018/10/10
         */
        if (this.isCustomer(userId) && !StringUtils.isEmpty(oldMobile)) {
            Map<String, Object> map = new HashMap<>();
            map.put("contactNumber", oldMobile);
            List<Company> companies = companyService.listByNoPage(map);
            if (companies == null || companies.size() == 0) {
                logger.error("客户更改手机号,但是根据旧手机号找不到客户信息.user.id=" + userId + ",mobile:" + oldMobile + "-->" + newMobile);
            } else {
                /**
                 * update by xiayuan 2018/10/10
                 */
                //个人信息修改，因为是客户，所以同时更改客户信息里的个人手机号
                companies.stream().forEach(company -> {
                    company.setContactNumber(newMobile);
                    companyService.clearByMobileCache(oldMobile);
                    companyService.update(company);
                });
            }
        }
        if (StringUtils.isEmpty(oldMobile)) {
            userLogService.addLog(UserLogTypeEnum.mobile, "用户新增手机号:" + newMobile);
        } else {
            userLogService.addLog(UserLogTypeEnum.mobile, "用户更新手机号:" + oldMobile + "——>" + newMobile);
        }
    }

//    /**
//     * 更新客户信息
//     *
//     * @param oldCompany 用户的旧信息
//     * @param company    用户的新信息
//     * @return
//     */
//    @Transactional
//    public int updateCustomerInfo(Company oldCompany, Company company) {
////        oldCompany.setAddress(company.getAddress());
//        oldCompany.setCompanyName(company.getCompanyName());
//        oldCompany.setDepart(company.getDepart());
//        if (!Objects.equals(oldCompany.getMobile(), company.getMobile())) {
//            //手机号发生变更,则检查该用户是否已注册账号
//            TianyiUser oldMobileUser = tianyiUserService.selectByMobile(oldCompany.getMobile());
//            if (oldMobileUser != null) {
//                //因管理员修改了客户信息导致被动更新手机号
//                tianyiUserService.updateMobileById(company.getMobile(), oldMobileUser.getId());
//                userLogService.addLog(UserLogTypeEnum.mobile, "用户手机号被管理员更新:" + oldCompany.getMobile() + "——>" + company.getMobile());
//            }
//
//            oldCompany.setMobile(company.getMobile());//
//        }
//
//        if (!StringUtils.isEmpty(oldCompany.getMobile())) {
//            customerService.clearByMobileCache(oldCompany.getMobile());
//        }
//
//        if (!StringUtils.isEmpty(company.getMobile())) {
//            customerService.clearByMobileCache(company.getMobile());
//        }
//
//        oldCompany.setCustName(company.getCustName());
//        int cnt = customerService.updateById(oldCompany);
//        return cnt;
//    }

    /**
     * 检查开通用户的网易账号
     *
     * @param tianyiUser
     * @return
     */
//    public String checkNeteaseUser(TianyiUser tianyiUser) {
//        boolean needCheckNeUser = true;
//
//        String neUserToken = null;
//        String mobile = tianyiUser.getMobile();
//        if (!StringUtils.isEmpty(mobile) && !needCheckNeUser) {
//            List<Customer> custs = customerService.selectByMobile(mobile);
//            if (custs != null && custs.size() > 0) {
//                //说明是客户。客户需要有网易账号
//                needCheckNeUser = true;
//            }
//        }
//
//        if (!needCheckNeUser) {
//            return null;
//        }
//
//        String neUserName = tianyiUser.getNeUsername();
//        boolean needRegNeUser = false;
//        //网易用户
//        if (StringUtils.isEmpty(neUserName)) {
//            neUserName = tianyiUser.getUsername();
//            needRegNeUser = true;
//        } else {
//            NeteaseUser neUser = neteaseUserService.selectByUsername(neUserName);
//            if (neUser == null) {
//                needRegNeUser = true;
//            } else {
//                neUserToken = neUser.getYunToken();
//            }
//        }
//
//        if (needRegNeUser) {
//            AppAccountVo vo = neteaseUserComponent.regNeteaseUser(neUserName, neUserName, tianyiUser.getPassword(), 2, false);
//            tianyiUser.setNeUsername(neUserName);
//            tianyiUserService.updateById(tianyiUser);
////            mqttBackendSubscribeJob.addNewMobileTopic(neUserName); //@todo 手机端报文规范还未确定,topic命名方式未定
//
//            if ("200".equals(vo.getCode())) {
//                //success
//                neUserToken = (String) (vo.getData().get("yun_token"));
//            }
//        }
//
//        return neUserToken;
//    }

    /**
     * 用户，登录成功后，需生成一些信息
     */
    public String userLogonSuccess(User userInfo, LoginUserInfo lui, String clientIp, String userAgent) {
        if (lui != null) {
            //已经登录的用户再次登录.
            String oldUserName = lui.getUsername();
            if (oldUserName.equalsIgnoreCase(userInfo.getAccount())) {
                //还是老用户
                userLogService.addLog(UserLogTypeEnum.logon, "用户成功登录", userInfo.getId(), "tianyi");
                return lui.getToken();
            } else {
                //更换了用户,则把旧的token删掉
                loginUserTokenService.deleteUserToken(lui.getToken());
            }
        } else {
            //新登录
        }

        LoginUserHolder.remove();
        //生成新用户token
        lui = new LoginUserInfo();
        lui.setId(userInfo.getId());
        lui.setUsername(userInfo.getAccount());
        lui.setLoginTime(System.currentTimeMillis());
        lui.setLastOperateTime(lui.getLoginTime());
        lui.setClientIp(clientIp);
        lui.setClientTypeMap(Browser.versions(userAgent));
//        if (userInfo instanceof TianyiUser) {
        lui.setUserType("tianyi");
//            lui.setAdmin(this.isAdmin(userInfo.getId()));
//            lui.setSales(this.isSales(userInfo.getId()));
//            lui.setCustomer(this.isCustomer(userInfo.getId()));
//            lui.setDriver(this.isDriver(userInfo.getId()));
//            lui.setDemo(this.isDemo(userInfo.getId()));
        lui.setAdmin(true);
        lui.setSales(true);
        lui.setCustomer(true);
        lui.setDriver(true);
        lui.setDemo(true);
        lui.setTianyuan(false);
        //判断是否需要自动生成网易账号
//            String neUserToken = checkNeteaseUser(userInfo);
        if (userInfo.getNeUserWeb() != null) {
            lui.setYunToken(neteaseUserService.selectByUsername(userInfo.getNeUserWeb()).getYunToken());
        }
        lui.setOrganisation(userInfo.getOrganisation());
//        }
//        else if (userInfo instanceof TianYuanUser) {
//            lui.setUserType("tianyuan");
//            lui.setTianyuan(true);
//            //天远用户登录后加入服务日志任务刷新队列
//            TianYuanUser tyUser = (TianYuanUser) userInfo;
//            if (!tyUser.isProdUser()) {
//                addToSvcTaskRefreshQueue(tyUser.getId());
//            }
//        } else {
//            lui.setUserType("unknown");
//        }

        LoginUserHolder.set(lui);
        String loginToken = loginUserTokenService.createUserToken(lui);
        userLogService.addLog(UserLogTypeEnum.logon, "用户成功登录", lui.getId(), lui.getUserType());

        //记录日志
        return loginToken;
    }


    @Transactional
    public int updateUserPassword(User user, String password, boolean findPwd) {
        user.setPassword(password);
        int cnt = userService.update(user);
        userLogService.addLog(UserLogTypeEnum.password, (findPwd ? "找回" : "修改") + "密码", user.getId(), "tianyi");
        return cnt;
    }

    /**
     * 用户自行修改个人信息
     *
     * @return
     */
    public int updateUserSimpleInfo(User user, String name, Integer userSex, String company) {
        userLogService.addLog(UserLogTypeEnum.edit, "用户修改信息,用户名：" + user.getAccount() + ",姓名：" + user.getName() + "-->" + name);

        user.setName(name);
        if (userSex != null)
            user.setSex(userSex);
        if (!StringUtils.isEmpty(company))
            user.setOrganisation(1);//todo  需要修改   现在前台传来的是公司名称，以后需要选择公司id
        int cnt = userService.update(user);

        return cnt;
    }


//    /**
//     * 新注册1个用户
//     * 注册普通账号，如果是客户还自动注册网易账号
//     *
//     * @param mobile
//     * @param password
//     */
//    @Transactional
//    public User addNewUser(String userName, String mobile, String password, String name, String company, int userSex, String neUserName, String roleCodes) {
//        User user = new User();
//        user.setName(userName);
//        user.setPassword(password);
//        user.setMobile(mobile);
//        user.setName(name);
//        user.setOrganisation(company);
//        user.setSex(userSex);
//        user.set(neUserName);
//        user.setRegTime(LocalDateTime.now());
//
//        boolean isCustomer = false;
//        // 根据手机号判断是客户就自动给它注册网易账号，感觉比较危险，暂时屏蔽掉
////        if (!StringUtils.isEmpty(mobile)) {
////            List<Customer> custs = customerService.selectByMobile(mobile);
////            if (custs != null && custs.size() > 0) {
////                isCustomer = true;
////                //说明是客户。客户需要有网易账号
////                needRegNeteaseUser = true;
////            }
////        }
//
//        //添加网易账号
//            //String deviceId, String userName, String password,int userType,boolean createHelmet,boolean activeHelmet
//            AppAccountVo vo = neteaseUserComponent.regNeteaseUser(user.getId(), neUserName, password, 2);
////            if ("200".equals(vo.getCode())) {
////                mqttBackendSubscribeJob.addNewMobileTopic(neUserName);//@todo 手机端报文规范还未确定,topic命名方式未定
////            }
//
//        //添加用户
//        tianyiUserService.insert(user);
//
//        //添加角色
//        List<TianyiUserRole> userRoles = null;
//        if (!StringUtils.isEmpty(roleCodes)) {
//            userRoles = Arrays.stream(roleCodes.split(",")).map(code -> code.trim()).filter(code -> code.length() > 0)
//                    .map(code -> RoleEnum.valueOf(code.toUpperCase())).filter(roleEnum -> roleEnum != null).map(role -> {
//                        return new TianyiUserRole(user.getId(), role.getCode());
//                    }).collect(Collectors.toList());
//        } else {
//            //如果没有传入角色，则角色设置默认都是机手
//            userRoles = new ArrayList<>(1);
//            TianyiUserRole tur = new TianyiUserRole(user.getId(), RoleEnum.DRIVER.getCode());
//            userRoles.add(tur);
//
//            if (isCustomer) {
//                //客户
//                TianyiUserRole tur2 = new TianyiUserRole(user.getId(), RoleEnum.CUSTOMER.getCode());
//                userRoles.add(tur2);
//            }
//        }
//        userRoles.stream().forEach(userRole -> tianyiUserRoleService.insert(userRole));
//
//        userLogService.addLog(UserLogTypeEnum.register, "用户注册，账号:" + userName, user.getId(), "tianyi");
//        return user;
//    }


    /**
     * 管理员修改用户及角色
     */
//    @Transactional
//    public boolean updateTianyiUserAndRole(TianyiUser user) {
//        //如果该修改了角色
//        if (!StringUtils.isEmpty(user.getRoleCodes())) {
//            tianyiUserRoleService.deleteByUserId(user.getId());
//            List<TianyiUserRole> userRoles = Arrays.stream(user.getRoleCodes().split(",")).map(code -> code.trim()).filter(code -> code.length() > 0)
//                    .map(code -> RoleEnum.valueOf(code.toUpperCase())).filter(roleEnum -> roleEnum != null).map(role -> {
//                        return new TianyiUserRole(user.getId(), role.getCode());
//                    }).collect(Collectors.toList());
//            userRoles.stream().forEach(userRole -> tianyiUserRoleService.insert(userRole));
//        }
//        //以下几个属性不能随意修改，故置空不修改
//        user.setUsername(null);
//        user.setNeUsername(null);
//        user.setUsername(null);
//        user.setRegTime(null);
//        user.setBindWxTime(null);
//        int cnt = tianyiUserService.updateById(user);
//        userLogService.addLog(UserLogTypeEnum.edit, "用户修改信息,用户名：" + user.getUsername());
//        return cnt == 1;
//    }
    public boolean isAdmin(int userId) {
        List<TianyiUserRole> roles = tianyiUserRoleService.selectByUserId(userId);
        return roles.stream().filter(ur -> ur.getRoleCode().equals(RoleEnum.ADMIN.getCode())).findAny().isPresent();
    }

    public boolean isCustomer(int userId) {
        List<TianyiUserRole> roles = tianyiUserRoleService.selectByUserId(userId);
        return roles.stream().filter(ur -> ur.getRoleCode().equals(RoleEnum.CUSTOMER.getCode())).findAny().isPresent();
    }

    public boolean isSales(int userId) {
        List<TianyiUserRole> roles = tianyiUserRoleService.selectByUserId(userId);
        return roles.stream().filter(ur -> ur.getRoleCode().equals(RoleEnum.SALES.getCode())).findAny().isPresent();
    }

    public boolean isDriver(int userId) {
        List<TianyiUserRole> roles = tianyiUserRoleService.selectByUserId(userId);
        return roles.stream().filter(ur -> ur.getRoleCode().equals(RoleEnum.DRIVER.getCode())).findAny().isPresent();
    }

    public boolean isDemo(int userId) {
        List<TianyiUserRole> roles = tianyiUserRoleService.selectByUserId(userId);
        return roles.stream().filter(ur -> ur.getRoleCode().equals(RoleEnum.DEMO.getCode())).findAny().isPresent();
    }

    /**
     * 用户信息脱敏
     *
     * @param user
     */
    public void desensitization(User user) {
        user.setPassword(null);
        user.setMobile(Commons.maskMobile(user.getMobile()));
    }

    /**
     * 获得用户在各种设备端的在线状态
     *
     * @param user
     * @return 分别是头盔、手机
     */
    public int[] onlineState(User user) {
        /**
         * update by xiayuan 2018/10/9
         *
         * todo 仍然有问题，需要看联系人如何修改  手机如何处理，，目前还有问题
         */
        List<EquipmentLedger> list = equipmentService.selectByUserId(user.getId());
        String imei = "";
        String mImei = "";
        for (EquipmentLedger e : list) {
            String deviceUUID = (String) redisTemplate.opsForValue().get(user.getNeUserHel());
            String mobileUUID = (String) redisTemplate.opsForValue().get(user.getNeUserPhn());
            if (StringUtils.isNotEmpty(deviceUUID) && deviceUUID.equals(e.getDeviceUUID())) {
                imei = deviceUUID;
            }
            if (StringUtils.isNotEmpty(mobileUUID) && mobileUUID.equals(e.getDeviceUUID())) {
                mImei = deviceUUID;
            }
        }

        boolean helmetOnLine = equipmentService.isOnLine(imei);
        boolean mobileOnLine = equipmentService.isOnLine(mImei);
        return new int[]{helmetOnLine ? 1 : 0, mobileOnLine ? 1 : 0};
    }

}
