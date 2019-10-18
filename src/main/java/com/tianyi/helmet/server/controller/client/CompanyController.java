package com.tianyi.helmet.server.controller.client;

import com.alibaba.fastjson.JSON;
import com.tianyi.helmet.server.entity.client.Company;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.service.client.CompanyService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.dictionary.CityService;
import com.tianyi.helmet.server.service.dictionary.CompanyNatureSerivce;
import com.tianyi.helmet.server.service.dictionary.ProvinceService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单位管理
 * Created by wenxinyan on 2018/9/30.
 */
@Controller
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyNatureSerivce companyNatureSerivce;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;

    @RequestMapping("welcome")
    public String welcome() {
        return "user/companyManage";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResponseVo add(@RequestParam String data) {
        Map<String, Object> map = (Map<String, Object>) JSON.parse(data);
        User user = this.getUser(map);
        user.setRoleCodes("6");  //默认为单位管理员

        Company company = this.getCompany(map);

        if(userService.checkAccount(user.getAccount()))
        {
            return ResponseVo.fail("该用户账号已存在!");
        }

        companyService.insert(company);
        user.setOrganisation(company.getId());
        userService.insert(user);

        return ResponseVo.success();
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResponseVo delete(@RequestParam int id) {
        companyService.deleteById(id);

        Map<String, Object> map = new HashMap<>();
        map.put("organisation", id);
        map.put("status", 1);
        List<User> users = userService.listByNoPage(map);
        for(User u : users){
            userService.deleteById(u.getId());
        }

        return ResponseVo.success();
    }

    @RequestMapping("update")
    @ResponseBody
    public ResponseVo update(Company company) {
        companyService.update(company);

        return ResponseVo.success();
    }

    @RequestMapping("list")
    @ResponseBody
    public ResponseVo list(@RequestParam Integer page, String companyName, String contact, String contactNumber, Integer province, Integer city){
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("companyName", companyName);
        map.put("contact", contact);
        map.put("contactNumber", contactNumber);
        map.put("province", province);
        map.put("city", city);
        map.put("status", 1);
        List<Company> companyList = companyService.listBy(map);
        for(Company c : companyList){
            c.setDisplayNature(companyNatureSerivce.selectById(c.getCompanyNature()).getNatureName());
            c.setDisplayProvince(provinceService.selectById(c.getProvince()).getProvincialName());
            c.setDisplayCity(cityService.selectById(c.getCity()).getCityName());
        }
        int count = companyService.countBy(map);
        PageListVo vo = new PageListVo(page);
        vo.setList(companyList);
        vo.setTotal(count);

        return ResponseVo.success(vo);
    }

    @RequestMapping("listnopage")
    @ResponseBody
    public ResponseVo list(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        List<Company> companyList = companyService.listByNoPage(map);
        System.out.println(companyList);
        return ResponseVo.success(companyList);
    }

    private User getUser(Map<String, Object> map) {
        User user = new User();

        user.setAccount((String)map.get("account"));
        user.setPassword((String)map.get("password"));
        user.setName((String)map.get("name"));
        user.setSex(Integer.parseInt((String)map.get("sex")));
        user.setBirthday(Dates.toLocalDate(new Date((Long)map.get("birthday"))));
        user.setMobile((String)map.get("mobile"));
        user.setDepartment((String)map.get("department"));

        return user;
    }

    private Company getCompany(Map<String, Object> map) {
        Company company = new Company();

        company.setCompanyName((String)map.get("companyName"));
        company.setAddress((String)map.get("address"));
        company.setCompanyNature(Integer.parseInt((String)map.get("companyNature")));
        company.setProvince(Integer.parseInt((String)map.get("province")));
        company.setCity(Integer.parseInt((String)map.get("city")));
        company.setContact((String)map.get("contact"));
        company.setContactNumber((String)map.get("contactNumber"));

        return company;
    }
}
