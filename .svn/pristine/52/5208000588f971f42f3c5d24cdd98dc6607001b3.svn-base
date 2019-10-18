package com.tianyi.helmet.server.service.netease;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 网易云信接口服务
 *
 * Created by liuhanc on 2017/10/15.
 */
@Service
public class NeteaseApiService {
    private Logger logger = LoggerFactory.getLogger(NeteaseApiService.class);

    @Autowired
    private NeteaseApiHelper neteaseApiHelper;

    /**
     * http://dev.netease.im/docs/product/IM%E5%8D%B3%E6%97%B6%E9%80%9A%E8%AE%AF/%E6%9C%8D%E5%8A%A1%E7%AB%AFAPI%E6%96%87%E6%A1%A3?#历史记录
     *
     *查询存储在网易云通信服务器中的单人聊天历史消息，只能查询在保存时间范围内的消息
     跟据时间段查询点对点消息，每次最多返回100条；
     不提供分页支持，第三方需要跟据时间段来查询。

     * from	String	是	发送者accid
      to	String	是	接收者accid
     begintime	String	是	开始时间，ms
     endtime	String	是	截止时间，ms
     limit	int	是	本次查询的消息条数上限(最多100条),小于等于0，或者大于100，会提示参数错误
     reverse	int	否	1按时间正序排列，2按时间降序排列。其它返回参数414错误.默认是按降序排列
     */
    public void querySessionMsg(String from, String to, String begintime, String endtime, int limit, int reverse, Consumer<JSONObject> msgConsumer){
        String url="https://api.netease.im/nimserver/history/querySessionMsg.action";
        Map<String,String> params = new HashMap<>();
        params.put("from",from);
        params.put("to",to);
        params.put("begintime",begintime);
        params.put("endtime",endtime);
        params.put("limit",""+limit);
        params.put("reverse",""+reverse);
        JSONObject jo = neteaseApiHelper.post(url,params,false);
        if(jo == null)
            return;
        JSONArray array = jo.getJSONArray("msgs");
        int dataSize = array.size();
        for(int i=0;i<dataSize;i++){
            JSONObject oneMsg = array.getJSONObject(i);
            try{
                msgConsumer.accept(oneMsg);
            }catch(Exception e){
                logger.error("querySessionMsg result process exception",e);
            }
        }
    }

    /**
     * 删除视频
     * http://dev.netease.im/docs/product/%E7%82%B9%E6%92%AD/%E6%9C%8D%E5%8A%A1%E7%AB%AFAPI%E6%96%87%E6%A1%A3?pos=toc-4-4
     * 其中5.5节删除视频
     *
     * @param vid
     * @return
     */
    public boolean deleteVideo(int vid){
        String url = "https://vcloud.163.com/app/vod/video/videoDelete";
        Map<String,String> params = new HashMap<>();
        params.put("vid",""+vid);
        JSONObject jo = neteaseApiHelper.post(url,params,true);
        if(jo == null)
            return false;

        int code = jo.getInteger("code");
        String msg = jo.getString("msg");
        if( code == 200 ){
            return true;
        }
        logger.error("删除视频失败.vid="+vid+",code="+code+",msg="+msg);
        return false;
    }

    /**
     * 下载某个文件并输出到out中
     * @param urlStr
     * @param out
     */
    public boolean fileDownload(String urlStr,OutputStream out){
        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为30秒
            conn.setConnectTimeout(30*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            Streams.copy(inputStream,out,true);
            if(inputStream!=null){
                inputStream.close();
            }
            return true;
        }catch(Exception e){
            logger.error("fileDownload exception.url="+urlStr,e);
        }
        return false;
    }

    public String userCreate(String accid,String name){
        String url = "https://api.netease.im/nimserver/user/create.action";
        Map<String,String> params = new HashMap<>();
        params.put("accid",accid);
        params.put("name",name);
        JSONObject jo = neteaseApiHelper.post(url,params,false);
        if(jo == null){
            logger.error("云信账号创建失败.请求无反馈");
            return null;
        }

        int code = jo.getInteger("code");
        String desc = jo.getString("desc");
        if( code == 200 ){
            JSONObject info = jo.getJSONObject("info");
            String token = info.getString("token");
            return token;
        }else{
            //{"desc":"already register","code":414}
            if(code == 414 && "already register".equals(desc)){
                logger.info("网易反馈用户已经在云信注册过,则刷新token并返回."+accid);
                return userRefreshToken(accid);
            }
            logger.error("注册用户失败.code:"+code+",desc:"+desc);
            return null;
        }
    }


    public String userRefreshToken(String accid){
        String url = "https://api.netease.im/nimserver/user/refreshToken.action";
        Map<String,String> params = new HashMap<>();
        params.put("accid",accid);

        JSONObject jo = neteaseApiHelper.post(url,params,false);
        if(jo == null){
            logger.error("云信账号token刷新失败.请求无反馈");
            return null;
        }

        int code = jo.getInteger("code");
        String msg = jo.getString("msg");
        if( code == 200 ){
            JSONObject info = jo.getJSONObject("info");
            String token = info.getString("token");
            return token;
        }else{
            logger.error("云信账号token刷新失败."+msg);
            return null;
        }
    }
}
