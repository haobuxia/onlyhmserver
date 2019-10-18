package com.tianyi.helmet.server.service.netease;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.dao.netease.NeteaseMessageDao;
import com.tianyi.helmet.server.dao.netease.NeteaseVideoAudioDao;
import com.tianyi.helmet.server.entity.netease.NeteaseMessage;
import com.tianyi.helmet.server.entity.netease.NeteaseVideoAudio;
import com.tianyi.helmet.server.service.job.RedisMqPublisher;
import com.tianyi.helmet.server.service.support.ChannelNameConstants;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网易消息服务
 *
 * Created by liuhanc on 2017/10/16.
 */
@Service
public class NeteaseMessageService {
    @Autowired
    private NeteaseMessageDao neteaseMessageDao;
    @Autowired
    private NeteaseVideoAudioDao neteaseVideoAudioDao;
    @Autowired
    private RedisMqPublisher redisMqPublisher;

    public void insert(NeteaseMessage msg){
        if("5".equals(msg.getEventType())){
            //视频音频时长信息
            JSONObject jo = JSON.parseObject(msg.getFullMsg());
            NeteaseVideoAudio nva = new NeteaseVideoAudio();
            String members = jo.getString("members");
            JSONArray ja = JSON.parseArray(members);
            if(ja != null && ja.size() > 0 ){
                JSONObject jo1 = ja.getJSONObject(0);
                nva.setAccid1(jo1.getString("accid"));
                nva.setDuration(jo.getInteger("duration"));
                if(ja.size()>1){
                    JSONObject jo2 = ja.getJSONObject(1);
                    nva.setAccid2(jo2.getString("accid"));
                }

                Boolean caller = jo.getBoolean("caller");
                if(caller != null && caller.booleanValue()){
                    nva.setCaller(nva.getAccid1());
                }else{
                    nva.setCaller(nva.getAccid2());
                }
            }

            String type = jo.getString("type");
            nva.setChannelId(jo.getLong("channelId"));
            nva.setCreateTime(jo.getLong("createtime"));
            nva.setType(type);

            neteaseVideoAudioDao.insert(nva);
        }
        neteaseMessageDao.insert(msg);

        if("6".equals(msg.getEventType())){
            addToDownloadQueue(msg.getId());
        }
    }

    public void addToDownloadQueue(int msgRecId){
        redisMqPublisher.sendMessage(ChannelNameConstants.channelName_NeteaseVideo_Download,String.valueOf(msgRecId));
    }

    public NeteaseMessage selectById(int id){
        return neteaseMessageDao.selectById(id);
    }

    public int updateMessageProcessFlag(int id,String processFlag,String failReason){
        if(failReason != null && failReason.length() > 50){
            failReason = failReason.substring(0,50);
        }
        Map<String,Object> map = new HashMap<>(3);
        map.put("id",id);
        map.put("processFlag",processFlag);
        map.put("failReason",failReason);
        return neteaseMessageDao.updateMessageProcessFlag(map);
    }

    public PageListVo<NeteaseMessage> selectBy(Map<String,Object> params,int page,int pageSize,boolean withCount){
        params.put("start",(page-1)*pageSize);
        params.put("length",pageSize);
        PageListVo<NeteaseMessage> vo = new PageListVo<NeteaseMessage>();
        vo.setPage(page);
        vo.setPageSize(pageSize);
        if(withCount){
            int count = neteaseMessageDao.countBy(params);
            vo.setTotal(count);
            if(count > 0 ){
                List<NeteaseMessage> list = neteaseMessageDao.selectBy(params);
                vo.setList(list);
            }
        }else{
            List<NeteaseMessage> list = neteaseMessageDao.selectBy(params);
            vo.setList(list);
        }
        return vo;
    }

}
