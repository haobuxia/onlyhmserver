package com.tianyi.helmet.server.service.kdxf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.msp.cpdb.lfasr.client.LfasrClientImp;
import com.iflytek.msp.cpdb.lfasr.exception.LfasrException;
import com.iflytek.msp.cpdb.lfasr.model.LfasrType;
import com.iflytek.msp.cpdb.lfasr.model.Message;
import com.iflytek.msp.cpdb.lfasr.model.ProgressStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * 科大讯飞 语音文字识别服务
 * Created by liuhanc on 2018/4/3.
 */
@Service
public class KdxfSpeechService {
    private LfasrClientImp lc = null;

    private Logger logger = LoggerFactory.getLogger(KdxfSpeechService.class);

    @PostConstruct
    public void init() {
//        PropertyConfigurator.configure("log4j.properties");
        //默认载入 config.properties 作为配置文件
        try {
            lc = LfasrClientImp.initLfasrClient();
        } catch (LfasrException e) {
            // 初始化异常，解析异常描述信息
            Message initMsg = JSON.parseObject(e.getMessage(), Message.class);
            logger.error("初始化科大讯飞语音转换引擎失败.ecode=" + initMsg.getErr_no() + ",failed=" + initMsg.getFailed());
        }
    }

    /**
     * 识别一个语音文件
     * 支持格式 wav,flac,opus,m4a,mp3
     *
     * @param audioFile
     * @return
     */
    public String asrAudioToText(File audioFile, int startWaitSeconds, int maxWaitSeconds) {
        if (startWaitSeconds <= 0) startWaitSeconds = 5;
        if (maxWaitSeconds <= 0) maxWaitSeconds = 5 * 60;//5分钟

        HashMap<String, String> params = new HashMap<>();
        params.put("has_participle", "false");//是否需要分词，true：需要对结果进行分词处理、false：不需要对结果进行分词处理，可为空。
//        params.put("suid", userId);//客户端用户ID，保留字段，可为空。
        String taskId = null;
        try {
            // 上传音频文件
            Message uploadMsg = lc.lfasrUpload(audioFile.getAbsolutePath(), LfasrType.LFASR_STANDARD_RECORDED_AUDIO, params);
            // 判断返回值
            if (uploadMsg.getOk() == 0) {
                // 创建任务成功
                taskId = uploadMsg.getData();
            } else {
                // 创建任务失败-服务端异常
                logger.error("科大讯飞上传语音文件反馈失败.ecode=" + uploadMsg.getErr_no() + ",failed=" + uploadMsg.getFailed());
                return null;
            }
        } catch (LfasrException e) {
            // 上传异常，解析异常描述信息
            Message uploadMsg = JSON.parseObject(e.getMessage(), Message.class);
            logger.error("科大讯飞上传语音文件反馈失败.ecode=" + uploadMsg.getErr_no() + ",failed=" + uploadMsg.getFailed());
            return null;
        }

        int seconds = startWaitSeconds;
        int waitAllSeconds = 0;
        boolean canGetResult = false;
        while (true) {
            sleep(seconds, taskId);
            waitAllSeconds += seconds;
            boolean over = checkTaskIsOver(taskId);
            if (over) {
                canGetResult = true;
                break;
            }
            if (waitAllSeconds > maxWaitSeconds) {
                //超时
                break;
            }
            seconds = seconds * 2;
        }
        if (!canGetResult) {
            return null;
        }
        return readResult(taskId);
    }

    //线程停顿
    private void sleep(int seconds, String taskId) {
        try {
            // 睡眠1min。另外一个方案是让用户尝试多次获取，第一次假设等1分钟，获取成功后break；失败的话增加到2分钟再获取，获取成功后break；再失败的话加到4分钟；8分钟；……
            Thread.sleep(seconds * 1000);
            logger.debug("waiting " + seconds + " seconds for task " + taskId + "...");
        } catch (InterruptedException e) {
        }
    }

    //检查转写是否完成
    protected boolean checkTaskIsOver(String taskId) {
        try {
            // 获取处理进度
            Message progressMsg = lc.lfasrGetProgress(taskId);

            // 如果返回状态不等于0，则任务失败
            if (progressMsg.getOk() != 0) {
                logger.error("科大讯飞查询语音转换结果失败.ecode=" + progressMsg.getErr_no() + ",failed=" + progressMsg.getFailed() + ",taskId=" + taskId);
                // 服务端处理异常-服务端内部有重试机制（不排查极端无法恢复的任务）
                // 客户端可根据实际情况选择：
                // 1. 客户端循环重试获取进度
                // 2. 退出程序，反馈问题
                return false;
            } else {
                ProgressStatus progressStatus = JSON.parseObject(progressMsg.getData(), ProgressStatus.class);
                if (progressStatus.getStatus() == 9) {
                    // 处理完成
                    return true;
                } else {
                    // 未处理完成
                    return false;
                }
            }
        } catch (LfasrException e) {
            // 获取进度异常处理，根据返回信息排查问题后，再次进行获取
            Message progressMsg = JSON.parseObject(e.getMessage(), Message.class);
            logger.error("科大讯飞查询语音转换结果失败.ecode=" + progressMsg.getErr_no() + ",failed=" + progressMsg.getFailed() + ",taskId=" + taskId);
            return false;
        }
    }

    //获得转写结果
    private String readResult(String taskId) {
        // 获取任务结果
        try {
            Message resultMsg = lc.lfasrGetResult(taskId);
            // 如果返回状态等于0，则任务处理成功
            if (resultMsg.getOk() == 0) {
                // 打印转写结果
                String json = resultMsg.getData();
                logger.debug("科大讯飞语音转换结果:" + json);
                JSONArray jsonArray = JSON.parseArray(json);
                int size = jsonArray.size();
                if (size > 0) {
                    StringBuffer sb = new StringBuffer();
                    String finalText = "";
                    for (int i = 0; i < size; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String text = jsonObject.getString("onebest");
                        String bg = jsonObject.getString("bg");
                        String ed = jsonObject.getString("ed");
                        sb.append(timeFormat(Long.parseLong(bg)));
                        sb.append("-");
                        sb.append(timeFormat(Long.parseLong(ed)));
                        sb.append(" ");
                        sb.append(text);
                        sb.append("\r\n");
                    }
                    finalText = sb.toString();
                    return finalText;
                } else {
                    logger.error("科大讯飞查询语音转换结果，未识别出结果.taskId=" + taskId);
                    return null;
                }
            } else {
                // 转写失败，根据失败信息进行处理
                logger.error("科大讯飞查询语音转换结果失败.ecode=" + resultMsg.getErr_no() + ",failed=" + resultMsg.getFailed() + ",taskId=" + taskId);
            }
        } catch (LfasrException e) {
            // 获取结果异常处理，解析异常描述信息
            Message resultMsg = JSON.parseObject(e.getMessage(), Message.class);
            logger.error("科大讯飞查询语音转换结果失败.ecode=" + resultMsg.getErr_no() + ",failed=" + resultMsg.getFailed() + ",taskId=" + taskId);
        }
        return null;
    }

    public String timeFormat(long time) {
        long hours = time / (1000 * 60 * 60);
        long minutes = (time - hours * (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (time - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
        String diffTime = "";
        if (minutes < 10) {
            diffTime = hours + ":0" + minutes;
        } else {
            diffTime = hours + ":" + minutes;
        }
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    public static void main(String[] args) {
        KdxfSpeechService speechService = new KdxfSpeechService();
        speechService.init();
        String fileName = "1.mp3";
        String text = speechService.asrAudioToText(new File("E:\\tianxujin\\vidiotest\\" + fileName), 20, 10 * 60);
        File outFile = new File("E:\\tianxujin\\vidiotest\\k1.text");
        try {
            if(!outFile.exists()){
                outFile.createNewFile();
            }
            FileWriter fw = new FileWriter(outFile);
            BufferedWriter bw= new BufferedWriter(fw);;
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("识别结果：" + text);
    }
}
