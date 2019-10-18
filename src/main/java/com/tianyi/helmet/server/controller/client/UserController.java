package com.tianyi.helmet.server.controller.client;

import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.service.client.CompanyService;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * Created by wenxinyan on 2018/9/25.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("welcome")
    public String welcome() {
        return "user/userManage";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResponseVo add(@RequestParam Long bd, User user) {
        user.setBirthday(Dates.toLocalDate(new Date(bd)));

        if(userService.checkAccount(user.getAccount())) {
            return ResponseVo.fail("该用户账号已存在!");
        }

        if(userService.checkName(user.getName(), user.getOrganisation())) {
            return ResponseVo.fail("该用户名称已存在!");
        }

        if(user.getRoleCodes() == null) {
            user.setRoleCodes("4");
        }
        userService.insert(user);

        return ResponseVo.success();
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResponseVo delete(@RequestParam int id) {
        userService.deleteById(id);

        return ResponseVo.success();
    }

    @RequestMapping("update")
    @ResponseBody
    public ResponseVo update(@RequestParam Long bd, User user) {
        user.setBirthday(Dates.toLocalDate(new Date(bd)));
        if(user.getRoleCodes() == null){
            user.setRoleCodes("4");
        }
        userService.update(user);

        return ResponseVo.success();
    }

    @RequestMapping("list")
    @ResponseBody
    public ResponseVo list(@RequestParam Integer page, Integer organisation, String name, String time1, String time2) {
        Map<String, Object> map = PageListVo.createParamMap(page);
        map.put("organisation", organisation);
        map.put("name", name);
        map.put("status", 1);
        if(time1 != null) {
            map.put("crossTime1", new Date(Long.parseLong(time1)));
        }
        if(time2 != null) {
            map.put("crossTime2", new Date(Long.parseLong(time2)));
        }
        List<User> userList = userService.listBy(map);
        for(User u : userList){
            u.setDisplayOrg(companyService.selectById(u.getOrganisation()).getCompanyName());
        }
        int count = userService.countBy(map);
        PageListVo vo = new PageListVo(page);
        vo.setList(userList);
        vo.setTotal(count);

        return ResponseVo.success(vo);
    }

    @RequestMapping("listnopage")
    @ResponseBody
    public ResponseVo list(@RequestParam Integer organisation, String department) {
        Map<String, Object> map = new HashMap<>();
        map.put("organisation", organisation);
        map.put("status", 1);
        if(department != null && department != ""){
            map.put("department", department);
        }
        List<User> userList = userService.listByNoPage(map);
        return ResponseVo.success(userList);
    }

    @RequestMapping("listdept")
    @ResponseBody
    public ResponseVo list(@RequestParam Integer organisation) {
        Map<String, Object> map = new HashMap<>();
        map.put("organisation", organisation);
        map.put("status", 1);
        List<String> deptList = userService.listDept(map);
        return ResponseVo.success(deptList);
    }
}
