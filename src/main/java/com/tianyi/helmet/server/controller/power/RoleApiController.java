package com.tianyi.helmet.server.controller.power;

import com.tianyi.helmet.server.service.power.RoleApiService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenxinyan on 2018/10/10.
 */
@Controller
@ResponseBody
@RequestMapping("roleapi")
public class RoleApiController {
    @Autowired
    private RoleApiService roleApiService;

    @RequestMapping("list")
    public ResponseVo list(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", id);

        return ResponseVo.success(roleApiService.listByNoPage(map));
    }
}
