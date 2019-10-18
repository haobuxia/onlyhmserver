package com.tianyi.helmet.server.controller.data;

import com.tianyi.helmet.server.service.data.ReferenceValueService;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenxinyan on 2018/9/8.
 */
@Controller
@RequestMapping("referencevalue")
public class ReferenceValueController {
    @Autowired
    private ReferenceValueService referenceValueService;

    @RequestMapping("getValue")
    @ResponseBody
    public ResponseVo getReferenceValue(@RequestParam String imei)
    {
        return ResponseVo.success(referenceValueService.selectByModel("PC200-8"));
    }
}
