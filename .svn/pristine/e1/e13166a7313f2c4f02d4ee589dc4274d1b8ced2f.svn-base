package com.tianyi.helmet.server.controller.admin;

import com.tianyi.helmet.server.controller.interceptor.LoginUserHolder;
import com.tianyi.helmet.server.entity.client.LoginUserInfo;
import com.tianyi.helmet.server.entity.netease.NeteaseMessage;
import com.tianyi.helmet.server.service.netease.NeteaseMessageService;
import com.tianyi.helmet.server.vo.PageListVo;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tianyi.helmet.server.entity.netease.NeteaseMessage.*;

/**
 * 网易视频
 * Created by liuhanc on 2017/12/8.
 */
@Controller
@RequestMapping("neteasevideo")
public class NeteaseVideoController {
    @Autowired
    private NeteaseMessageService neteaseMessageService;
    private int defaultPageSize = PageListVo.DEFAULT_PAGE_SIZE;
    /**
     * 网易视频处理失败的记录
     * @return
     */
    @RequestMapping(value="faillist")
    public String neteaseFailList(Model model){
        return neteaseFailList(1,model);
    }

    /**
     * 网易视频处理失败的记录
     * @return
     */
    @RequestMapping(value="/faillist/{page}")
    public String neteaseFailList(@PathVariable Integer page,Model model){
        if(page == null) page = 1 ;
        Map<String,Object> map = new HashMap<>();
        map.put("eventType",6);//音视频
        map.put("processFlagList", Arrays.asList(ProcessFlag_FAIL,ProcessFlag_BREAK));
        PageListVo<NeteaseMessage> vo = neteaseMessageService.selectBy(map,page,defaultPageSize,true);
        model.addAttribute("vo",vo);
        return "file/neteaseMsgList";
    }

    /**
     * 网易视频处理失败的记录重新再处理一次
     * @return
     */
    @RequestMapping(value="failedRetry")
    @ResponseBody
    public ResponseVo failedRetry(Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("eventType",6);//音视频
        map.put("processFlagList", Arrays.asList(ProcessFlag_FAIL,ProcessFlag_BREAK));
        PageListVo<NeteaseMessage> vo = neteaseMessageService.selectBy(map,1,defaultPageSize,false);
        List<NeteaseMessage> msgList = vo.getList();
        if(msgList.size() > 0){
            msgList.forEach(msg->{
                neteaseMessageService.updateMessageProcessFlag(msg.getId(),ProcessFlag_Not,"失败重试");
                neteaseMessageService.addToDownloadQueue(msg.getId());
            });
            return ResponseVo.success(msgList.size());
        }
        return ResponseVo.fail("没有失败数据需要处理");
    }

    /**
     * 网易视频处理失败的标记为忽略
     * @return
     */
    @RequestMapping(value="/ignore/{id}")
    @ResponseBody
    public ResponseVo ignoreFailMsg(@PathVariable Integer id){
        LoginUserInfo userInfo = LoginUserHolder.get();
        int cnt = neteaseMessageService.updateMessageProcessFlag(id,ProcessFlag_IGNORE,"失败后人工确认忽略.by:"+userInfo.getId()+","+userInfo.getUsername());
        return cnt == 1 ? ResponseVo.success():ResponseVo.fail("标记为忽略失败");
    }
}
