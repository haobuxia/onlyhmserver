package com.tianyi.helmet.server.controller.common;

import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.client.TianyiUserRole;
import com.tianyi.helmet.server.entity.log.UserLogTypeEnum;
import com.tianyi.helmet.server.service.client.LoginUserTokenService;
import com.tianyi.helmet.server.service.client.TianyiUserRoleService;
import com.tianyi.helmet.server.service.log.UserLogService;
import com.tianyi.helmet.server.service.support.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页界面
 */
@Controller
@RequestMapping("")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TianyiUserRoleService tianyiUserRoleService;
    @Autowired
    private LoginUserTokenService loginUserTokenService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private UserLogService userLogService;

    @RequestMapping({"", "index"})
    public String index(Model model, HttpServletRequest request, HttpServletResponse resp) {
        LoginUserInfo lui = loginUserTokenService.getCurrentLoginUserInfo(request);
        model.addAttribute("isLogin", lui != null);
        model.addAttribute("userInfo", lui);
        if (lui != null) {
            List<TianyiUserRole> roles = tianyiUserRoleService.selectByUserId(lui.getId());
            model.addAttribute("roles", roles);
        }
        return "index";
    }

    @RequestMapping("indexContent")
    public String indexContent(Model model, HttpServletRequest request) {
        LoginUserInfo lui = loginUserTokenService.getCurrentLoginUserInfo(request);
        model.addAttribute("isLogin", lui != null);
        return "indexContent";
    }


    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse resp) {
        LoginUserInfo lui = loginUserTokenService.getCurrentLoginUserInfo(request);
        if (lui != null) {
            try{
                userLogService.addLog(UserLogTypeEnum.logout,"",lui.getId(),lui.getUserType());
            }catch(Exception e){
                logger.error("记录退出日志异常.username="+lui.getUsername(),e);
            }

            loginUserTokenService.deleteUserToken(lui.getToken());
        }
        cookieService.deleteCookie(resp, LoginUserTokenService.LOGINUSER_TOKEN_NAME);
        return "redirect:index";
    }

}
