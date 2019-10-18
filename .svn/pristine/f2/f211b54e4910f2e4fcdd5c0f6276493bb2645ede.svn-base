package com.tianyi.helmet.server.controller.client;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.TianyiContact;
import com.tianyi.helmet.server.entity.client.User;
import com.tianyi.helmet.server.service.client.CompanyService;
import com.tianyi.helmet.server.service.client.TianyiContactService;
import com.tianyi.helmet.server.service.client.UserComponent;
import com.tianyi.helmet.server.service.client.UserService;
import com.tianyi.helmet.server.vo.ResponseVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 田一头盔系统用户联系人
 * <p>
 * Created by liuhanc on 2017/11/2.
 */
@Controller
@RequestMapping("tianyicontact")
public class TianyiContactController {

    private Logger logger = LoggerFactory.getLogger(TianyiContactController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TianyiContactService tianyiContactService;
    @Autowired
    private UserComponent userComponent;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value="list", method = RequestMethod.POST)
    @ApiOperation(value = "获取当前登陆用户联系人列表")
    public String toListPage(Model model) {
        LoginUserInfo lui = LoginUserHolder.get();
        List<TianyiContact> contactList = tianyiContactService.selectByUserId(lui.getId());
        contactList.stream().forEach(contact -> {
            User user = userService.selectById(contact.getContactId());
            userComponent.desensitization(user);
            int companyId = user.getOrganisation();
            user.setDisplayOrg(companyService.selectById(companyId).getCompanyName());
            contact.setContactUser(user);
        });
        model.addAttribute("contactList", contactList);
        return "user/listTianyiContact";
    }

    /**
     * 添加1个联系人
     *
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加联系人")
    public ResponseVo<TianyiContact> addContact(@RequestParam String userRealName, @RequestParam(required = false) String reamark) {
        LoginUserInfo lui = LoginUserHolder.get();
        if (StringUtils.isEmpty(userRealName)) {
            return ResponseVo.fail("联系人姓名不可为空");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", userRealName);
        List<User> users = userService.listByNoPage(map);
        if (users == null) {
            return ResponseVo.fail("不存在的用户不可设置为联系人");
        }

        User user = users.get(0);
        TianyiContact contact = new TianyiContact();
        contact.setUserId(lui.getId());
        contact.setContactId(user.getId());
        contact.setRemark(reamark);
        tianyiContactService.insert(contact);

        userComponent.desensitization(user);
        contact.setContactUser(user);
        return ResponseVo.success(contact);
    }

    /**
     * 移除1个联系人
     *
     * @param contactId
     * @return
     */
    @RequestMapping(value="remove", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "移除联系人")
    public ResponseVo removeContact(@RequestParam int contactId) {
        LoginUserInfo lui = LoginUserHolder.get();
        int cnt = tianyiContactService.deleteByUserIdContactId(lui.getId(), contactId);
        if (cnt > 0)
            return ResponseVo.success("移除成功");
        return ResponseVo.fail("移除失败，不存在的联系人");
    }

}
