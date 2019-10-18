package com.tianyi.helmet.server.controller.power;

import com.tianyi.helmet.server.service.power.ApiService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenxinyan on 2018/10/10.
 */
@Controller
@ResponseBody
@RequestMapping("api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @RequestMapping("list")
    public ResponseVo list(){
        return ResponseVo.success(apiService.listAll());
    }
}
