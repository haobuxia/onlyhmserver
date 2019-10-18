//package com.tianyi.helmet.server.service.client;
//
//import com.tianyi.helmet.server.dao.client.CustomerDao;
//import com.tianyi.helmet.server.service.support.CacheKeyConstants;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 客户服务
// *
// * Created by liuhanc on 2017/11/29.
// */
//@Service
//public class CustomerService {
//    @Autowired
//    private CustomerDao customerDao;
//
//    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
//
//
//    public void insert(Customer customer) {
//        //@todo 如果有手机号，则自动注册账号并发送短信给对应手机号
//        //@todo 如果手机号对应账号存在，则自动设置账号的角色为客户
//        customerDao.insert(customer);
//    }
//
//    @Cacheable(value = CacheKeyConstants.CUSTOMER_BY_ID, key = "#id.toString()", unless = "#result == null")
//    public Customer selectById(int id) {
//        return customerDao.selectById(id);
//    }
//
//    //1个手机号应对应唯一客户
//    @Cacheable(value = CacheKeyConstants.CUSTOMER_BY_MOBILE, key = "#mobile", unless = "#result == null")
//    public List<Customer> selectByMobile(String mobile) {
//        List<Customer> customers = customerDao.selectByMobile(mobile);
//        return customers;
//    }
//
//    @CacheEvict(value = CacheKeyConstants.CUSTOMER_BY_ID, key = "#customer.id.toString()")
//    public int updateById(Customer customer) {
//        //@todo 如果有手机号，则自动注册账号并发送短信给对应手机号
//        //@todo 如果手机号对应账号存在，则自动设置账号的角色为客户
//        //@todo 如果修改了手机号,则手机号缓存要清理
//        customer.setLastModifyTime(LocalDateTime.now());
//        return customerDao.updateById(customer);
//    }
//
//    @CacheEvict(value = CacheKeyConstants.CUSTOMER_BY_MOBILE, key = "#mobile")
//    public void clearByMobileCache(String mobile){
//    }
//    public List<Customer> selectBy(Map<String, Object> params) {
//        return customerDao.selectBy(params);
//    }
//
//    public List<Customer> selectByIdList(List<Integer> idList) {
//        Map<String, Object> mm = new HashMap();
//        mm.put("idList", idList);
//        return selectBy(mm);
//    }
//
//    public int countBy(Map<String, Object> params) {
//        return customerDao.countBy(params);
//    }
//
//
//}
