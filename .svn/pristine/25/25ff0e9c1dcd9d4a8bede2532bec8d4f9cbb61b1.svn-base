//package com.tianyi.helmet.server.controller.client;
//
//import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
//import com.tianyi.helmet.server.entity.client.LoginUserInfo;
//import com.tianyi.helmet.server.service.client.CustomerComponent;
//import com.tianyi.helmet.server.service.client.CustomerService;
//import com.tianyi.helmet.server.service.client.TianyiUserService;
//import com.tianyi.helmet.server.vo.DoubleVo;
//import com.tianyi.helmet.server.vo.PageListVo;
//import com.tianyi.helmet.server.vo.ResponseVo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * 客户信息
// * Created by liuhanc on 2017/11/29.
// */
//@Controller
//@RequestMapping("customer")
//public class CustomerController {
//    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private CustomerComponent customerComponent;
//    @Autowired
//    private TianyiUserService tianyiUserService;
//
//    /**
//     * 进入列表页面首页
//     *
//     * @return
//     */
//    @RequestMapping(value = "list")
//    public String list(String keyword, Model model) {
//        return list(1, keyword, model);
//    }
//
//    @RequestMapping("list/{page}")
//    public String list(@PathVariable Integer page, String keyword, Model model) {
//        LoginUserInfo lui = LoginUserHolder.get();
//
//        Map<String, Object> map = PageListVo.createParamMap(page);
//        map.put("order", "-1");
//        if (lui.isCustomer()) {
//            map.put("saleUserId", lui.getId());
//        }
//        if (!StringUtils.isEmpty(keyword)) {
//            map.put("keyword", keyword);
//        }
//        List<Customer> list = customerService.selectBy(map);
//        Map<Integer, String> salesIdNameMap = list.stream()
//                .map(Customer::getSaleUserId).distinct()
//                .map(saleUserId -> {
//                    return tianyiUserService.selectById(saleUserId);
//                })
//                .filter(user -> user != null)
//                .collect(Collectors.toMap(tu -> tu.getId(), tu -> tu.getName()));
//        list.forEach(customer -> customer.setSaleUserName(salesIdNameMap.get(customer.getSaleUserId())));
//
//        int count = customerService.countBy(map);
//        PageListVo vo = new PageListVo(page);
//        vo.setList(list);
//        vo.setTotal(count);
//        model.addAttribute("vo", vo);
//        model.addAttribute("keyword", keyword);
//        return "customer/listCustUser";
//    }
//
//    @RequestMapping("listCustomers")
//    @ResponseBody
//    public ResponseVo<List<DoubleVo>> listCustomers(String keyword) {
//        LoginUserInfo lui = LoginUserHolder.get();
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("order", "-1");
//        if (lui.isCustomer()) {
//            map.put("saleUserId", lui.getId());
//        }
//        if (!StringUtils.isEmpty(keyword)) {
//            map.put("keyword", keyword);
//        }
//        List<Customer> list = customerService.selectBy(map);
//        List<DoubleVo> voList = list.stream()
//                .map(cust -> {
//                    return new DoubleVo(cust.getId(), cust.getDisplayName());
//                }).collect(Collectors.toList());
//        return ResponseVo.success(voList);
//    }
//
//    //获得客户信息准备修改
//    @RequestMapping("info/{id}")
//    @ResponseBody
//    public ResponseVo<Customer> info(@PathVariable Integer id) {
//        Customer customer = customerService.selectById(id);
//        LoginUserInfo lui = LoginUserHolder.get();
//        if (lui.isSales()) {
//            if (customer.getSaleUserId() != lui.getId()) {
//                return ResponseVo.fail("无权查看");
//            }
//        }
//        return ResponseVo.success(customer);
//    }
//
//    //修改或新增客户信息
//    @RequestMapping("save")
//    @ResponseBody
//    public ResponseVo<Customer> save(Customer customer) {
//        return customerComponent.saveCustomer(customer);
//    }
//}
