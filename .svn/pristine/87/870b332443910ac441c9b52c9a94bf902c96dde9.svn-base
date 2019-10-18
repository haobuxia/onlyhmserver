package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.service.support.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

/**
 * 上传文件临时目录清理任务
 * <p>
 * Created by liuhanc on 2018/3/5.
 */
@Component
public class UploadDirClearJob {
    private Logger logger = LoggerFactory.getLogger(UploadDirClearJob.class);

    @Value("${upload.file.dir.clear:1}")
    private String doClear;
    @Autowired
    private ConfigService configService;
    private static final long one_day_millies = 24l * 60 * 60 * 1000;

    public void doClear() {
        if (!"1".equalsIgnoreCase(doClear)) {
            logger.info("上传文件临时目录文件清理不启动");
            return;
        }

        logger.info("上传文件临时目录文件清理开始.");
        try {
            clear();
        } catch (Throwable e) {
            logger.error("上传文件临时目录文件清理异常", e);
        }
    }


    private void clear() {
        String dirName = configService.getUploadFileSaveDir();
        String videoDir = configService.getUploadVideoDir();
        String audioDir = configService.getUploadAudioDir();
        String fileDir = configService.getUploadFileDir();
        String imageDir = configService.getUploadImageDir();

        clearDirectDir(new File(dirName, videoDir));
        clearDirectDir(new File(dirName, audioDir));
        clearDirectDir(new File(dirName, fileDir));
        clearDirectDir(new File(dirName, imageDir));
    }

    private void clearDirectDir(File dir) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("没有文件需要清除." + dir.getAbsolutePath());
            return;
        }
        Arrays.stream(files).forEach(file -> {
            try {
                clearFile(file);
            } catch (Exception e) {
                logger.error("清理临时文件异常." + file.getAbsolutePath(), e);
            }
        });
    }

    private void clearFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                Arrays.stream(files).forEach(subfile -> {
                    try {
                        clearFile(subfile);
                    } catch (Exception e) {
                        logger.error("清理临时文件异常." + subfile.getAbsolutePath(), e);
                    }
                });
                files = file.listFiles();
            }
            if (files == null || files.length == 0) {
                System.out.println("删除目录." + file.getAbsolutePath());
                file.delete();
            }
        } else {
            long modifyTime = file.lastModified();
            if (System.currentTimeMillis() - modifyTime > one_day_millies && !file.getAbsolutePath().contains("thumbnail")) {
                System.out.println("删除文件." + file.getAbsolutePath());
                file.delete();
            }
        }
    }
}
