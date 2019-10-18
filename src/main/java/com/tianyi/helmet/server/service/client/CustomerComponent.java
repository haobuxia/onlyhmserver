//package com.tianyi.helmet.server.service.client;
//
//import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
//import com.tianyi.helmet.server.entity.client.*;
//import com.tianyi.helmet.server.vo.ResponseVo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
///**
// * 客户相关
// *
// * Created by liuhanc on 2017/11/29.
// */
//@Component
//public class CustomerComponent {
//    @Autowired
//    private CompanyService companyService;
//    @Autowired
//    private UserComponent userComponent;
//    @Autowired
//    private TianyiUserService tianyiUserService;
//    @Autowired
//    private TianyiUserRoleService tianyiUserRoleService;
//
//    private static final Logger logger = LoggerFactory.getLogger(CustomerComponent.class);
//
//    @Transactional
//    public ResponseVo saveCustomer(Company company) {
//        LoginUserInfo lui = LoginUserHolder.get();
//        if (company.getId() == 0) {
//            //insert 新增
//            company.setSaleUserId(lui.getId());
//            company.setCreateTime(LocalDateTime.now());
//            //检查手机号重复
//            if (!StringUtils.isEmpty(company.getMobile())) {
//                List<Company> customers = companyService.selectByMobile(customer.getMobile());
//                if (CollectionUtils.isEmpty(customers)) {
//                    //手机号未使用过则存储入库
//                    customerService.insert(customer);
//
//                    //检查客户的手机号和用户信息是否有相同的
//                    TianyiUser tianyiUser = tianyiUserService.selectByMobile(customer.getMobile());
//                    if (tianyiUser != null) {
//                        boolean isCustomer = userComponent.isCustomer(tianyiUser.getId());
//                        if (!isCustomer) {
//                            logger.debug("新添加的客户和系统内注册用户的手机号相同，则设置用户为客户");
//                            TianyiUserRole userRole = new TianyiUserRole(tianyiUser.getId(), RoleEnum.CUSTOMER.getCode());
//                            tianyiUserRoleService.insert(userRole);
//                        }
//                    }
//
//                    return ResponseVo.success();
//                } else {
//                    return ResponseVo.fail("手机号对应客户已存在." + customer.getMobile());
//                }
//            } else {
//                customerService.insert(customer);
//                return ResponseVo.success();
//            }
//        } else {
//            //update
//            Customer oldCustomer = customerService.selectById(customer.getId());
//            if (oldCustomer == null) {
//                return ResponseVo.fail("客户数据已被删除无法修改");
//            } else {
//                if (oldCustomer.getSaleUserId() != lui.getId()) {
//                    logger.info("客户" + customer.getId() + "信息修改，修改人" + lui.getUsername() + "不是客户信息的原始录入人" + oldCustomer.getSaleUserId());
//                    if (!lui.isAdmin() && !lui.isSales()) {
//                        return ResponseVo.fail("你不是该客户的销售代表，不可更改客户信息.");
//                    }
//                }
//                //
//                int cnt = userComponent.updateCustomerInfo(oldCustomer, customer);
//                return cnt == 1 ? ResponseVo.success() : ResponseVo.fail("客户信息不存在");
//            }
//        }
//    }
//}
