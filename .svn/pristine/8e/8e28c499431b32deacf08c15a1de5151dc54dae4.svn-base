package com.tianyi.helmet.server.service.job;


import com.tianyi.helmet.server.entity.data.AbstractGpsData;
import com.tianyi.helmet.server.entity.data.GpsLineData;
import com.tianyi.helmet.server.entity.data.TyBoxLineData;
import com.tianyi.helmet.server.entity.file.File;
import com.tianyi.helmet.server.service.client.TyBoxImeiService;
import com.tianyi.helmet.server.service.data.TyBoxDataService;
import com.tianyi.helmet.server.service.fastdfs.FastDfsClient;
import com.tianyi.helmet.server.service.file.FileService;
import com.tianyi.helmet.server.service.kmx.KmxService;
import com.tianyi.helmet.server.service.log.OperaLogService;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.MainDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 基于mq的 文件处理工作
 */
@Component //在applicationContext.xml中配置了此类接收某个pattern的订阅
public class NewFileProcessJob implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(NewFileProcessJob.class);

    @Autowired
    private FileService fileService;
    @Autowired
    private FastDfsClient fastDfsClient;
    @Autowired
    private ConfigService configService;
    @Autowired
    private KmxService kmxService;
    @Autowired
    private TyBoxDataService tyBoxDataService;
    @Autowired
    private TyBoxImeiService imeiService;
    @Autowired
    private OperaLogService operaLogService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String patternStr = new String(pattern);
            String body = new String(message.getBody());
            logger.debug("NewFileProcessJob 收到消息:patternStr=" + patternStr + ",channel=" + new String(message.getChannel()) + ",body=" + body);
            String id = body.split(",")[0];
            String logflowId = body.split(",")[1];
            String clientId = body.split(",")[2];
            String logType = body.split(",")[3];
            operaLogService.addNewLog(logflowId, clientId, logType, 13, "收到处理文件类型为gps或者timetag的消息", 0);
            Integer fileId = Integer.parseInt(id);
            File v = fileService.selectById(fileId);
            processOneData(v, logflowId, clientId, logType);
        } catch (Exception e) {
            logger.error("处理视频队列消息异常.", e);
        }
    }

    /**
     * 处理某个文件
     *
     * @param v
     */
    public void processOneData(File v, String logflowId, String clientId, String logType) {
        if (v == null)
            return;

        //operaLogService.addNewLog(logflowId, clientId, logType, 14, "开始处理文件类型为gps或者timetag的文件", 0);
        byte[] bytes = fastDfsClient.downloadFile(v.getOssPath());
        //operaLogService.addNewLog(logflowId, clientId, logType, 15, "下载文件类型为gps或者timetag的文件完成", 0);
        String origName = v.getFileName().toLowerCase();
        String description = v.getDescription();
        String content = new String(bytes).trim();
        String[] lines = content.split("\n");
        logger.debug("异步处理文件 " + origName + ",行数=" + lines.length);
        if("600s".equals(description)){
            operaLogService.addNewLog(logflowId, clientId, logType, 14, "文件类型为gps，开始处理神钢传感器数据", 0);
            processShgtFile(lines, v.getId(), v.getClientId());
            operaLogService.addNewLog(logflowId, clientId, logType, 15, "处理神钢传感器数据完成", 0);
        } else if("100d".equals(description)){
            operaLogService.addNewLog(logflowId, clientId, logType, 14, "文件类型为gps，开始处理神钢传感器数据", 0);
            processDgpsFile(lines, v.getId(), v.getClientId());
            operaLogService.addNewLog(logflowId, clientId, logType, 15, "处理神钢传感器数据完成", 0);
        } else if (origName.endsWith(".gps")) {
            operaLogService.addNewLog(logflowId, clientId, logType, 14, "文件类型为gps，开始处理车辆蓝牙传感器数据", 0);
            processGpsFile(lines, v.getId(), v.getClientId());
            operaLogService.addNewLog(logflowId, clientId, logType, 15, "处理车辆蓝牙传感器数据完成", 0);
        } else if (origName.endsWith(".timetag")) {
            operaLogService.addNewLog(logflowId, clientId, logType, 14, "文件类型为timetag，开始处理视频多个时间标签", 0);
            processTimetagFile(lines, v.getId(), v.getClientId());
            operaLogService.addNewLog(logflowId, clientId, logType, 15, "处理视频多个时间标签完成", 0);
        }
    }

    private void processDgpsFile(String[] lines, int fileId, String clientId) {
        //车载gps数据文件,内容是十六进制的字符串
        //文件内容如何组织：文本文件，每行1条gps数据
        String thisFileImei = null;
        int lineNo = 0;
        for (String line : lines) {
            line = line.trim();
            if (StringUtils.isEmpty(line)) {
                continue;
            } else {
                lineNo++;
            }
            try {
                MainDetailVo<GpsLineData, AbstractGpsData> mdVo = tyBoxDataService.saveDgpsData(line, clientId, fileId, lineNo, true);
                if (StringUtils.isEmpty(thisFileImei) && mdVo.getMain() != null) {
                    thisFileImei = mdVo.getMain().getImei();
                }
            } catch (Exception e) {
                logger.error("解析gps数据并存储入库异常.fileId=" + fileId + ".数据=" + line, e);
            }
        }

        if (!StringUtils.isEmpty(thisFileImei)) {
            //存下这个文件对应的Imei
            imeiService.addImei(thisFileImei,"100d");
        }
    }


    //处理车辆蓝牙传感器数据
    private void processGpsFile(String[] lines, int fileId, String clientId) {
        //车载gps数据文件,内容是十六进制的字符串
        //文件内容如何组织：文本文件，每行1条gps数据
        String thisFileImei = null;
        int lineNo = 0;
        for (String line : lines) {
            line = line.trim();
            if (StringUtils.isEmpty(line)) {
                continue;
            } else {
                lineNo++;
            }

            try {
                MainDetailVo<GpsLineData, AbstractGpsData> mdVo = tyBoxDataService.saveGpsData(line, clientId, fileId, lineNo, true);
                if (StringUtils.isEmpty(thisFileImei) && mdVo.getMain() != null) {
                    thisFileImei = mdVo.getMain().getImei();
                }
            } catch (Exception e) {
                logger.error("解析gps数据并存储入库异常.fileId=" + fileId + ".数据=" + line, e);
            }
        }

        if (!StringUtils.isEmpty(thisFileImei)) {
            //存下这个文件对应的Imei
            imeiService.addImei(thisFileImei,"100b");
        }
    }

    //处理视频的多个时间标签
    private List<LocalDateTime> processTimetagFile(String[] lines, int fileId, String clientId) {
        logger.debug("收到视频时间标签数据.fileId=" + fileId + ",clientId=" + clientId + ",数据量=" + lines.length);
        List<LocalDateTime> dateList = Arrays.stream(lines).filter(line -> line.trim().length() > 0)
                .map(line -> Dates.parse(line, "yyyyMMddHHmmss"))
                .filter(date -> date != null)
                .map(date -> Dates.toLocalDateTime(date))
                .sorted()
                .collect(Collectors.toList());
        return dateList;
    }

    //处理神户钢铁传感器数据
    public void processShgtFile(String[] lines, int fileId, String clientId) {
        //神户钢铁数据文件,内容是十六进制的字符串
        //文件内容如何组织：文本文件，每行1条神户钢铁数据
        String thisFileImei = null;
        int lineNo = 0;
        for (String line : lines) {
            line = line.trim();
            if (StringUtils.isEmpty(line)) {
                continue;
            } else {
                lineNo++;
            }
            System.out.println("正在解析行数："+lineNo);
            try {
                 MainDetailVo<TyBoxLineData, AbstractGpsData> mdVo = tyBoxDataService.saveTyBoxLineData(line, clientId, fileId, lineNo, true);
                if (StringUtils.isEmpty(thisFileImei) && mdVo.getMain() != null) {
                    thisFileImei = mdVo.getMain().getImei();
                }
            } catch (Exception e) {
                logger.error("解析神户钢铁数据并存储入库异常.fileId=" + fileId + ".数据=" + line, e);
            }
        }

        if (!StringUtils.isEmpty(thisFileImei)) {
            //存下这个文件对应的Imei
            imeiService.addImei(thisFileImei,"600s");
        }
    }

}
