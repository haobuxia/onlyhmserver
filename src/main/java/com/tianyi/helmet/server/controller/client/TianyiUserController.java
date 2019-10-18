package com.tianyi.helmet.server.controller.client;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.service.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 田一后台用户登陆校验
 * <p>
 * Created by tianxujin on 20190509.
 */
@Controller
@RequestMapping("tianyiuser")
public class TianyiUserController {

    private Logger logger = LoggerFactory.getLogger(TianyiUserController.class);

    /**
     * 校验用户是否有登陆权限
     *
     * @return
     */
    @RequestMapping(value = "islogin")
    @ResponseBody
    public Boolean authorTianyiUserLogin(HttpServletRequest request, HttpServletResponse response) {
        /*LoginUserHolder.remove();
        //获得当前登录用户
        LoginUserInfo lui = loginUserTokenService.getLoginUserInfo(token);
        //检查用户是否登录
        if(lui == null){
            return false;
        }
        LoginUserHolder.set(lui);
//        logger.debug("检查用户权限.username="+lui.getUsername());
        //@todo 检查 lui里的信息和当前信息是否存在不一致,防止cookie伪造.主要是ip,user-agent等检查

        //cookie延期_redis
        loginUserTokenService.recordUserNewOperate(lui);*/
        return true;
    }

}
