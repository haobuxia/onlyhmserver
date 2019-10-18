package com.tianyi.helmet.server.controller.scene;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.pay.AliTradePay;
import com.tianyi.helmet.server.service.alipay.AliTradePayService;
import com.tianyi.helmet.server.service.client.TianYuanUserService;
import com.tianyi.helmet.server.vo.PageListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * 支付宝支付记录查询
 * Created by liuhanc on 2018/4/20.
 */
@Controller
@RequestMapping("alipayquery")
public class AliPayQueryController {

    @Autowired
    private TianYuanUserService tianYuanUserService;
    @Autowired
    private AliTradePayService aliTradePayService;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    //阿里支付查询首页
    @RequestMapping("/index")
    public String index(Model model) {
        LoginUserInfo userInfo = LoginUserHolder.get();
        model.addAttribute("isAdmin", userInfo.isAdmin());
        model.addAttribute("isTianYuanUser", userInfo.isTianyuan());
        return "scene/aliquery/alipayQueryIndex";
    }

    //查询支付数据列表
    @RequestMapping("/queryList/{page}")
    public String queryList(@PathVariable Integer page, @RequestParam(required = false) String oprtId,
                                @RequestParam Date date1, @RequestParam Date date2, Model model, HttpServletRequest req) {
        if(page == null || page <= 0 ) page = 1;
        if(date2 != null){
            date2 = new Date(date2.getTime()+24*60*60*1000);//日期延后一天否则date2对应的当天数据查询不出来
        }

        LoginUserInfo userInfo = LoginUserHolder.get();
        boolean doQuery = true;
        if (userInfo.isTianyuan()) {
            oprtId = (String) req.getAttribute("oprtId");
        } else if(!StringUtils.isEmpty(oprtId)){
            //管理员或demo
            oprtId = tianYuanUserService.checkOprtNameToOprtId(oprtId);
        }else{
            oprtId = null;
        }

        PageListVo<AliTradePay> vo = null;
        if (doQuery) {
            vo = aliTradePayService.queryPageList(oprtId, page, date1, date2);
        } else {
            //不必查询数据为空
            vo = new PageListVo(0, 12, Collections.emptyList(), 0);
        }
        model.addAttribute("vo", vo);
        return "scene/aliquery/aliPayQueryList";
    }
}
