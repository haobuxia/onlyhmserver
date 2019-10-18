package com.tianyi.helmet.server.service.fastdfs;

import com.tianyi.helmet.server.service.support.ConfigService;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * fastdfs初始化和常用方法
 * <p>
 * Created by liuhanc on 2017/9/26.
 */
@Component
public class FastDfsClient {
    private Logger logger = LoggerFactory.getLogger(FastDfsClient.class);

    private ConnectionPool connectionPool;
    @Autowired
    private ConfigService configService;

    @PostConstruct
    private void init() {
        if(configService.isFastdfsEnable()){
            connectionPool = new ConnectionPool(configService.getFastdfsPoolMin(), configService.getFastdfsPoolMax(), configService.getFastdfsPoolWaitSeconds(), configService.getSystemEnv());
        }else{
            logger.debug("fastDfs 不启用");
            connectionPool = null;
        }
    }

    private String[] uploadRetry(Exception e, Object filePathOrBytes, String fileExt, int retryTimes, int retryType) {
        if (e instanceof IOException) {
            String err = e.getMessage();
            if ("Broken pipe".equals(err)) {
                //说明是客户端关闭了连接,比如关闭或者刷新了网页
                logger.error("uploadRetry Broken pipe:" + err);
                return null;
            }
            if (retryTimes > 0 && !StringUtils.isEmpty(err) && err.contains("recv package size -1 != 10")) {
                //retry
                logger.info("upload ioexception.retry.retryTimes=" + retryTimes);
                switch (retryType) {
                    case 0://upload file
                        return uploadFile((String) filePathOrBytes, fileExt, retryTimes - 1);
                    case 1://upload file bytes
                        return uploadFile((byte[]) filePathOrBytes, fileExt, retryTimes - 1);
                    case 2://uploadAppendFile
                        return uploadAppendFile((byte[]) filePathOrBytes, fileExt, retryTimes - 1);
                }
            }
        }
        logger.error("uploadFile exception", e);
        return null;
    }

    public String uploadFile(byte[] bytes, String fileExt) {
        String[] path = this.uploadFile(bytes, fileExt, configService.getFastdfsRetryTimes());
        if (path != null) {
            String ossPath = path[0] + "/" + path[1];
            return ossPath;
        }
        return null;
    }

    public String uploadFile(File file, String fileExt) {
        String[] path = this.uploadFile(file.getAbsolutePath(), fileExt, configService.getFastdfsRetryTimes());
        if (path != null) {
            String ossPath = path[0] + "/" + path[1];
            return ossPath;
        }
        return null;
    }

    public String[] uploadFile(byte[] fileBytes, String fileExt, int retryTimes) {
        if(!configService.isFastdfsEnable())
            return null;

        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        try {
            String[] ret = client1.upload_file(fileBytes, fileExt, null);
            return ret;
        } catch (Exception e) {
            return uploadRetry(e, fileBytes, fileExt, retryTimes, 1);
        } finally {
            connectionPool.checkin(trackerServer, logId);
        }
    }

    //上传1个文件
    public String[] uploadFile(String filePath, String fileExt, int retryTimes) {
        if(!configService.isFastdfsEnable())
            return null;

        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        try {
            String[] ret = client1.upload_file(filePath, fileExt, null);
            return ret;
        } catch (Exception e) {
            return uploadRetry(e, filePath, fileExt, retryTimes, 0);
        } finally {
            connectionPool.checkin(trackerServer, logId);
        }
    }

    /**
     * 上传可更新的文件
     *
     * @param fileBytes
     * @param fileExt
     * @return
     */
    public String[] uploadAppendFile(byte[] fileBytes, String fileExt, int retryTimes) {
        if(!configService.isFastdfsEnable())
            return null;

        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        try {
            String[] ret = client1.upload_appender_file(fileBytes, fileExt, null);
            return ret;
        } catch (Exception e) {
            return uploadRetry(e, fileBytes, fileExt, retryTimes, 2);
        } finally {
            connectionPool.checkin(trackerServer, logId);
        }
    }

    /**
     * 更新文件
     *
     * @param groupName
     * @param remoteName
     * @param file_buff
     * @return
     */
    public int appendFile(String groupName, String remoteName, byte[] file_buff, int retryTimes) {
        if(!configService.isFastdfsEnable())
            return -1;

        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        try {
            return client1.append_file(groupName, remoteName, file_buff);//0 success
        } catch (Exception e) {
            if (e instanceof IOException) {
                String err = e.getMessage();
                if ("Broken pipe".equals(err)) {
                    //说明是客户端关闭了连接,比如关闭或者刷新了网页
                    logger.error("appendFile Broken pipe:" + err);
                    return -1;
                }
                if (retryTimes > 0 && !StringUtils.isEmpty(err) && err.contains("recv package size -1 != 10")) {
                    return appendFile(groupName, remoteName, file_buff, retryTimes - 1);
                }
            }
            logger.error("uploadFile exception", e);
            return -1;
        } finally {
            connectionPool.checkin(trackerServer, logId);
        }
    }


    /**
     * 下载文件
     *
     * @param ossPath
     * @return
     */
    public byte[] downloadFile(String ossPath) {
        if (StringUtils.isEmpty(ossPath)) return new byte[0];
        int slashIdx = ossPath.indexOf("/");
        return downloadFile(ossPath.substring(0, slashIdx), ossPath.substring(slashIdx + 1), configService.getFastdfsRetryTimes());
    }

    public boolean downloadFile(String ossPath, OutputStream os) {
        if (StringUtils.isEmpty(ossPath)) return false;
        int slashIdx = ossPath.indexOf("/");
        return downloadFile(ossPath.substring(0, slashIdx), ossPath.substring(slashIdx + 1), os, configService.getFastdfsRetryTimes());
    }

    /**
     * 下载文件
     *
     * @param groupName
     * @param remoteName
     * @return
     */
    public byte[] downloadFile(String groupName, String remoteName, int retryTimes) {
        if(!configService.isFastdfsEnable())
            return null;

        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        try {
            byte[] b = client1.download_file(groupName, remoteName);
            return b;
        } catch (Exception e) {
            if (e instanceof IOException) {
                String err = e.getMessage();
                if ("Broken pipe".equals(err)) {
                    //说明是客户端关闭了连接,比如关闭或者刷新了网页
                    logger.error("downloadFile Broken pipe:" + err);
                    return null;
                }
                if (retryTimes > 0 && !StringUtils.isEmpty(err) && err.contains("recv package size -1 != 10")) {
                    return downloadFile(groupName, remoteName, retryTimes - 1);
                }
            }

            logger.error("downloadFile exception", e);
            return null;
        } finally {
            connectionPool.checkin(trackerServer, logId);
        }
    }

    /**
     * 直接输出文件到流
     *
     * @param groupName
     * @param remoteName
     * @param os
     * @param retryTimes
     */
    public boolean downloadFile(String groupName, String remoteName, OutputStream os, int retryTimes) {
        if(!configService.isFastdfsEnable())
            return false;

        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        DownloadCallback ds = new TianyiDownloadStream(os);
        try {
            int errNo = client1.download_file(groupName, remoteName, ds);
            if (errNo != 0) {
                logger.error("fdfs outputFile 下载文件错误.errNo:" + errNo);
                return false;
            }
            return true;
        } catch (Exception e) {
            if (e instanceof IOException) {
                String err = e.getMessage();
                if ("Broken pipe".equals(err)) {
                    //说明是客户端关闭了连接,比如关闭或者刷新了网页
                    logger.error("outputFile Broken pipe:" + err);
                    return false;
                }
                if (retryTimes > 0 && !StringUtils.isEmpty(err) && err.contains("recv package size -1 != 10")) {
                    return downloadFile(groupName, remoteName, os, retryTimes - 1);
                }
            }
            logger.error("outputFile exception" + e.getMessage());
            return false;
        } finally {
            connectionPool.checkin(trackerServer, logId);
        }
    }


    /**
     * 生成文件访问token
     *
     * @param group_name
     * @param remoteName
     * @param ts
     * @return
     */
    public String getFileToken(String group_name, String remoteName, int ts) {
        if(!configService.isFastdfsEnable())
            return null;

        String file_id = group_name + StorageClient1.SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR + remoteName;
        try {
            String token = ProtoCommon.getToken(file_id, ts, ClientGlobal.g_secret_key);
            return token;
        } catch (Exception e) {
            logger.error("getToken exception", e);
        }
        return "error";
    }

    public boolean deleteFile(String ossPath) {
        return deleteFile(ossPath, configService.getFastdfsRetryTimes());
    }

    public boolean deleteFile(String ossPath, int retryTimes) {
        if (StringUtils.isEmpty(ossPath)) return false;
        int slashIdx = ossPath.indexOf("/");
        return deleteFile(ossPath.substring(0, slashIdx), ossPath.substring(slashIdx + 1), retryTimes);
    }

    public boolean deleteFile(String group_name, String remoteName, int retryTimes) {
        if(!configService.isFastdfsEnable())
            return false;

        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        try {
            int ret = client1.delete_file(group_name, remoteName);
            return ret == 0;
        } catch (Exception e) {
            if (e instanceof IOException) {
                String err = e.getMessage();
                if ("Broken pipe".equals(err)) {
                    //说明是客户端关闭了连接,比如关闭或者刷新了网页
                    logger.error("deleteFile Broken pipe:" + err);
                    return false;
                }
                if (retryTimes > 0 && !StringUtils.isEmpty(err) && err.contains("recv package size -1 != 10")) {
                    return deleteFile(group_name, remoteName, retryTimes - 1);
                }
            }
            logger.error("deleteFile exception." + group_name + "," + remoteName, e);
        } finally {
            connectionPool.checkin(trackerServer, logId);
        }
        return false;
    }
}
