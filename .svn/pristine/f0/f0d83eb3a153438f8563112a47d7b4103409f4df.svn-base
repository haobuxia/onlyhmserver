package com.tianyi.helmet.server.service.support;

import com.tianyi.helmet.server.util.CommandExecutor;
import com.tianyi.helmet.server.util.Dates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ffmpeg 音视频操作工具服务
 *
 */
@Service
public class FfmpegService {

    private Logger logger = LoggerFactory.getLogger(FfmpegService.class);
    @Autowired
    private ConfigService configService;

    private boolean isWindows = true;

    @PostConstruct
    private void init() {
        String osName = System.getProperty("os.name");
        logger.debug("系统名称:" + osName);
        if (osName.toLowerCase().startsWith("win")) {
            logger.debug("is windows");
            isWindows = true;
        } else {
            logger.debug("not windows");
            isWindows = false;
        }
    }

    private List<String> executeCommand(String cmd) {
        List<String> msgList = null;
        try {
            if (isWindows) {
//                logger.debug("windows系统即将执行命令:" + cmd);
                msgList = CommandExecutor.executeCommands(cmd);
            } else {
                String[] cmds = CommandExecutor.buildSudoCommands(cmd, configService.getSudoPassword());
//                logger.debug("非windows系统即将执行复合命令:" + Arrays.toString(cmds)+".cmds.length="+cmds.length);
                msgList = CommandExecutor.executeCommands(cmds);
            }
        } catch (Exception e) {
            logger.error("执行命令异常.cmd=" + cmd, e);
        }
        return msgList;
    }

    /**
     * 为视频文件生成截图
     *
     * @param videoFile
     * @param previewImgSaveFile
     */
    public boolean previewImage(File videoFile, File previewImgSaveFile, int previewImgPosSecond) {
        String realthumbnailPath = previewImgSaveFile.getAbsolutePath();
        String realfileoriPath = videoFile.getAbsolutePath();
        String cmd = "";
        cmd += configService.getFfmpegBinDir();
        cmd += "ffmpeg -y -i " + "\"" + realfileoriPath + "\"" +
                " -ss " + previewImgPosSecond + " -s " + configService.getVideoWidth() + "x" + configService.getVideoHeight() + " -f image2 -vframes 1 " + "\"" + realthumbnailPath + "\"";

        logger.debug("previewImage cmd=" + cmd);

        List<String> msgList = executeCommand(cmd);
        return !CollectionUtils.isEmpty(msgList);
    }


    /**
     * 视频转换
     *
     * @param videoFile
     * @param destFile
     * @param waterMarkFile
     */
    public boolean videoTransCode(File videoFile, File destFile, File waterMarkFile) {
        String realfileoriginalPath = videoFile.getAbsolutePath();
        String destFilePath = destFile.getAbsolutePath();
//        int w = 360,h=640;//视频宽高
        String cmd = "";
        cmd += configService.getFfmpegBinDir();
        cmd += "ffmpeg -y ";
        cmd += "-i ";
        cmd += "\"" + realfileoriginalPath + "\" ";
        cmd += "-vcodec libx264 ";
        cmd += "-b:v 500000 ";
        cmd += "-r 25 ";
        cmd += "-acodec libmp3lame ";
        cmd += "-b:a 64000 ";
        cmd += "-ar 22050 ";
        cmd += "-vf ";
        cmd += "\"scale=min(iw*" + configService.getVideoHeight() + "/ih\\," + configService.getVideoWidth() + "):min(" + configService.getVideoHeight() + "\\,ih*" + configService.getVideoWidth() + "/iw)," +
                " pad=" + configService.getVideoWidth() + ":" + configService.getVideoHeight() + ":(" + configService.getVideoWidth() + "-iw)/2:(" + configService.getVideoHeight() + "-ih)/2\"";
//        cmd += "scale=w="+videoWidth+":h="+videoHeight;
//        String keepaspectratio = "true";//保持长宽等比例
//        if (keepaspectratio.equals("true")) {
////            cmd += ":" + "force_original_aspect_ratio=decrease,pad=w="+w+":h="+h+":x=(ow-iw)/2:y=(oh-ih)/2";//在ubuntu上不支持()提示语法错误
//            cmd += ":" + "force_original_aspect_ratio=decrease,pad=w="+videoWidth+":h="+videoWidth;
//        }
        cmd += "[aa]";
//        boolean watermark = false;
//        if (waterMarkFile != null && waterMarkFile.exists()) {//水印
//            watermark = true;
//            String watermarkFile = waterMarkFile.getName();
//            cmd += ";movie=";
//            cmd += watermarkFile;
//            cmd += "[bb];";
//            cmd += "[aa][bb]";
//            cmd += "overlay=x=5:y=5 ";//水印的位置
//        } else {
        cmd += " ";
//        }
        cmd += "\"";
        cmd += destFilePath;
        cmd += "\"";


        logger.debug("videoTransCode cmd=" + cmd);

//        executeCommand(cmd, watermark ? waterMarkFile.getParentFile() : null);
        List<String> msgList = executeCommand(cmd);
        for (String msg : msgList) {
            if (msg != null && (msg.contains("Error") || msg.contains("Failed"))) {
                logger.error("视频转换异常：" + msg);
                return false;
            }
        }
        return true;
//        return !CollectionUtils.isEmpty(msgList);
    }

    public boolean videoAddSubtitlies(File videoFile, File subtitlesFile, File destFile) {
        if (destFile.exists()) destFile.delete();
        //ffmpeg -i input.mkv -vf subtitles=subtitles.srt output.mkv
        String video_path = videoFile.getAbsolutePath();
        String subtitles_path = subtitlesFile.getAbsolutePath();
        if (isWindows) {
            //字幕文件的路径需要进行转换
            subtitles_path = subtitles_path.replaceAll("\\\\", "\\\\\\\\").replace(":", "\\:");
        }
        String cmd = "";
        cmd += configService.getFfmpegBinDir();
        cmd += "ffmpeg -i ";
        cmd += "\"" + video_path + "\" ";
        cmd += " -vf \"subtitles='" + subtitles_path + "'\"";
        if (!isWindows) {
            cmd += " -strict -2 ";//似乎acc库没有编译进去,不加此属性会导致失败
        }
        cmd += " \"" + destFile.getAbsolutePath() + "\"";

        logger.debug("videoAddSubtitlies cmd =" + cmd);

        List<String> msgList = executeCommand(cmd);
        if (msgList == null) {
            return false;
        } else {
            boolean existFail = msgList.stream().filter(line -> line.indexOf("Conversion failed!") == 0).findAny().isPresent();
            if (existFail) {
                logger.error("合并字幕失败.");
                msgList.stream().forEach(logger::error);
            }

            if (destFile.exists() && destFile.length() > 0)
                return true;
            return false;
        }
    }

    /**
     * 获取视频时间
     *
     * @param videoFile
     * @return
     */
    public int getVideoTime(File videoFile) {
        String video_path = videoFile.getAbsolutePath();

        String cmd = "";
        cmd += configService.getFfmpegBinDir();
        cmd += "ffmpeg -i ";
        cmd += "\"" + video_path + "\" ";

        logger.debug("getVideoTime cmd=" + cmd);
        List<String> msgList = executeCommand(cmd);
        return readVideoTime(msgList);
    }

    /**
     * 音频转换
     * <p>
     * 16000,  "pcm_s16le", "wav" mp3转换成这种wav后百度可以识别出来
     *
     * @param srcFile
     * @param destFile -b <比特率> 指定压缩比特率，似乎ffmpeg是自动VBR的，指定了就大概是平均比特率，比如768，1500这样的就是原来默认项目中有的 1500（视频数据流量，用-b xxxx的指令则使用固定码率，数字随便改，1500以上没效果；还可以用动态码率如：-qscale 4和-qscale 6，4的质量比6高）
     * @param ac       频道声道 1,2 设定声道数，1就是单声道，2就是立体声，转换单声道的TVrip可以用1（节省一半容量），高品质的DVDrip就可以用2
     * @param acodec   音频编码 "aac", "pcm_u8","pcm_s16le","pcm_s16be","pcm_u16le","pcm_u16be"
     * @param fmt
     * @return
     */
    public boolean audioTranscode(String srcFile, String destFile, int ac, String acodec, String fmt) {
        File audioFileFile = new File(destFile);
        audioFileFile.delete();

        String cmd = "";
        cmd += configService.getFfmpegBinDir();

//        CHANNELS ：值得选项为1和2
//        PCMFORMAT ：值得选项为pcm_u8，pcm_s16le ，pcm_s16be，pcm_u16le，pcm_u16be
//        FREQUENCY ：8000，11025 ，22050，44100

        cmd += "ffmpeg -y -i \"" + srcFile + "\"  -ac " + ac + " -ar 16000 -acodec " + acodec + " -f " + fmt + " \"" + destFile + "\"";
        logger.debug("audioTranscode cmd=" + cmd);

        List<String> msgList = executeCommand(cmd);
        for (String msg : msgList) {
            if (msg != null && (msg.contains("Error") || msg.contains("Failed") || msg.contains("Invalid"))) {
                logger.error("音频转换异常：" + msg);
                return false;
            }
        }
        return true;
    }

    public String[] audioSplitToMp3(String audioFile, String destFileDir, int perAudioSeconds, int audioSeconds) {
        int maxCount = audioSeconds / perAudioSeconds + (audioSeconds % perAudioSeconds > 0 ? 1 : 0);
        String uuid = UUID.randomUUID().toString();
        String[] files = new String[maxCount];
        boolean success = true;
        
        for (int i = 0; i < maxCount; i++) {
            String startTime = Dates.formatSeconds(i * perAudioSeconds);
            String destFile = destFileDir + File.separator + uuid + "." + i + ".mp3";
            files[i] = destFile;

            String cmd = "";
            cmd += configService.getFfmpegBinDir();
            cmd += "ffmpeg -y -i \"" + audioFile + "\"  -ss " + startTime + " -t " + perAudioSeconds + " -acodec copy \"" + destFile + "\"";
            logger.debug("audioSplit " + i + " cmd=" + cmd);
            List<String> msgList = executeCommand(cmd);
            for (String msg : msgList) {
                if (msg != null && (msg.contains("Error") || msg.contains("Failed") || msg.contains("Invalid"))) {
                    logger.error("音频切割异常：" + msg);
                    success = false;
                    break;
                }
            }
        }

        if (success) {
            return files;
        }

        return new String[0];
    }

    /**
     * 从视频中提取声音
     *
     * @param videoFile
     * @param audioFile
     * @return
     */
    public boolean extractMp3FromVideo(String videoFile, String audioFile) {
        File audioFileFile = new File(audioFile);
        audioFileFile.delete();

        String cmd = "";
        cmd += configService.getFfmpegBinDir();
        cmd += "ffmpeg -y -i \"" + videoFile + "\" -f mp3  -vn \"" + audioFile + "\"";
        logger.debug("extractMp3FromVideo cmd=" + cmd);

        List<String> msgList = executeCommand(cmd);
        for (String msg : msgList) {
            if (msg != null && (msg.contains("Error") || msg.contains("Failed"))) {
                logger.error("音频提取异常：" + msg);
                return false;
            }
        }
        return true;
    }

    /**
     * 将声音合并到1个无声视频中
     *
     * @param videoFile
     * @param audioFile
     * @param outputVideoFile
     * @return
     */
    public boolean mergeVidioAudio(String videoFile,String audioFile,String outputVideoFile){
        String cmd = "";
        cmd += configService.getFfmpegBinDir();
        cmd += "ffmpeg -i \"" + audioFile + "\" -i \""+videoFile+"  \" -strict -2 "  + outputVideoFile + "\"";
        logger.debug("mergeVidioAudio cmd=" + cmd);

        List<String> msgList = executeCommand(cmd);
        for (String msg : msgList) {
            if (msg != null && (msg.contains("Error") || msg.contains("Failed"))) {
                logger.error("音视频合并异常：" + msg);
                return false;
            }
        }
        return true;
    }

    /**
     * 从cmd执行结果中解析视频的时长
     *
     * @param cmdRespLines
     * @return N秒
     */
    private int readVideoTime(List<String> cmdRespLines) {
        String errResps = cmdRespLines.stream().collect(Collectors.joining());
        //从视频信息中解析时长
        String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
        Pattern pattern = Pattern.compile(regexDuration);
        Matcher m = pattern.matcher(errResps);
        if (m.find()) {
            int min = 0;
            String strs[] = m.group(1).split(":");
            if (strs[0].compareTo("0") > 0) {
                min += Integer.valueOf(strs[0]) * 60 * 60;//秒
            }
            if (strs[1].compareTo("0") > 0) {
                min += Integer.valueOf(strs[1]) * 60;
            }
            if (strs[2].compareTo("0") > 0) {
                min += Math.round(Float.valueOf(strs[2]));
            }
            return min;
        }
        return 0;
    }

}
