package com.tianyi.helmet.server.service.fastdfs;

import org.csource.fastdfs.DownloadCallback;

import java.io.IOException;
import java.io.OutputStream;

/**
 *  fdfs下载流
 *
 *  模仿org.csource.fastdfs.DownloadStream
 *  并进行了修改。
 *
 * Created by liuhanc on 2017/11/21.
 */
public class TianyiDownloadStream implements DownloadCallback {
    private OutputStream out;
    private long currentBytes = 0;

    public TianyiDownloadStream(OutputStream out) {
        super();
        this.out = out;
    }

    /**
     * recv file content callback function, may be called more than once when the file downloaded
     *
     * @param fileSize file size
     * @param data     data buff
     * @param bytes    data bytes
     * @return 0 success, return none zero(errno) if fail
     */
    public int recv(long fileSize, byte[] data, int bytes) {
        try {
            out.write(data, 0, bytes);
        } catch (IOException ex) {
//            ex.printStackTrace(); //此异常堆栈不需打印
            return -1;
        }

        currentBytes += bytes;
        if (this.currentBytes == fileSize) {
            this.currentBytes = 0;
        }

        return 0;
    }
}
