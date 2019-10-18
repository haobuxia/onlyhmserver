package com.tianyi.helmet.server.controller.helmetinterface;

import com.tianyi.helmet.server.controller.interceptor.TianyiUserHolder;
import com.tianyi.helmet.server.entity.client.TianyiContact;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.entity.power.Role;
import com.tianyi.helmet.server.entity.scene.svc.ContactState;
import com.tianyi.helmet.server.service.client.TianyiContactService;
import com.tianyi.helmet.server.service.client.UserComponent;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.service.power.RoleService;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 联系人接口
 *
 * Created by liuhanc on 2018/6/30.
 */
@Controller
@RequestMapping("contact")
@Api(value = "api", description = "田一头盔系统用户联系人")
public class ContactController {
    @Autowired
    private TianyiContactService tianyiContactService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserComponent userComponent;
    @Autowired
    private RoleService roleService;

    /**
     * 头盔获得我的联系人姓名、用户名、设备在线状态
     *
     * @return
     */
    @RequestMapping(value = "mycontact", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取当前用户头盔好友列表")
    public ResponseVo<List<ContactState>> contactList() {
        User tianyiUser = TianyiUserHolder.get();
        List<TianyiContact> tyContacts = tianyiContactService.selectByUserId(tianyiUser.getId());

        //zhouwei 20190606 头盔联系人获取所有专家
        Map<String, Object> roleQuery = new HashMap<>();
        roleQuery.put("roleName", "客户工程师");
        Integer roleId = roleService.listBy(roleQuery).stream()
                .map(Role::getId).findFirst().get();

        List<ContactState> expertContacts;
        List<User> expertUsers;
        Map<String, Object> userQuery = new HashMap<>();
        userQuery.put("roleCodes", String.valueOf(roleId));
        expertUsers = userService.listBy(userQuery);
        expertContacts = expertUsers.stream().map(expert -> {
            ContactState cs = new ContactState(expert.getId(), expert.getAccount(), expert.getName(), expert.getNeUserHel(), expert.getNeUserWeb(), expert.getNeUserPhn());
            cs.setHelmetOnline(0);
            cs.setMobileOnline(0);
            cs.setIsExpert(1);
            return cs;
        }).collect(Collectors.toList());

        List<ContactState> contactList = tyContacts.stream()
                .map(contact -> {
                    User user = userService.selectById(contact.getContactId());
                    return user;
                }).filter(user -> user!=null && !user.getRoleCodes().contains(String.valueOf(roleId)))
                .map(user -> {
                    ContactState cs = new ContactState(user.getId(), user.getAccount(), user.getName(), user.getNeUserHel(), user.getNeUserWeb(), user.getNeUserPhn());
                    int[] states = userComponent.onlineState(user);
                    cs.setHelmetOnline(states[0]);
                    cs.setMobileOnline(states[1]);
                    /*zhouwei 20190605 判断是否专家角色
                    cs.setIsExpert(0);
                    for (Role role : roles) {
                        if (Arrays.stream(user.getRoleCodes().split(",")).
                                filter(r -> r.equals(String.valueOf(role.getId()))).findFirst().isPresent()) {
                            cs.setIsExpert(1);
                            break;
                        }
                    }
                    */
                    return cs;
                })
                .collect(Collectors.toList());
        if (contactList == null) {
            contactList = new ArrayList<>();
        }
        contactList.addAll(expertContacts);

        return ResponseVo.success(contactList);
    }


    @RequestMapping(value = "mycontact/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加头盔好友")
    public ResponseVo<List<ContactState>> addContact(@ApiParam(value = "用户ID", name = "userId") @RequestParam Integer userId) {
        User currentUser = TianyiUserHolder.get();

        List<TianyiContact> tyContacts = tianyiContactService.selectByUserId(currentUser.getId());
        for (TianyiContact tyContact : tyContacts) {
            if (tyContact.getContactId() == userId) {
                return ResponseVo.success();
            }
        }
        User userTmp = userService.selectById(userId);
        if (userTmp == null) {
            return ResponseVo.fail("没有该联系人信息");
        }
        TianyiContact toAddContact = new TianyiContact();
        toAddContact.setContactUser(userTmp);
        toAddContact.setAddTime(new Date());
        toAddContact.setUserId(currentUser.getId());
        toAddContact.setContactId(userTmp.getId());
        tianyiContactService.insert(toAddContact);
        return ResponseVo.success();
    }

    @RequestMapping(value = "mycontact/remove", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "移除头盔好友")
    public ResponseVo<List<ContactState>> removeContact(@ApiParam(value = "用户ID", name = "userId") @RequestParam Integer userId) {
        User currentUser = TianyiUserHolder.get();

        List<TianyiContact> tyContacts = tianyiContactService.selectByUserId(currentUser.getId());
        for (TianyiContact tyContact : tyContacts) {
            if (tyContact.getContactId() == Integer.valueOf(userId)) {
                tianyiContactService.deleteByUserIdContactId(currentUser.getId(), userId);
            }
        }
        return ResponseVo.success();
    }

    @RequestMapping(value = "mycontact/department", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取在线用户部门信息")
    public ResponseVo<List<String>> departmentList(){

        User currentUser = TianyiUserHolder.get();
        Map<String, Object> queryInfo = new HashMap<>();
        queryInfo.put("organisation", currentUser.getOrganisation());
        List<String> contactDepartments =
                userService.listDept(queryInfo);
        return ResponseVo.success(contactDepartments);
    }
}
