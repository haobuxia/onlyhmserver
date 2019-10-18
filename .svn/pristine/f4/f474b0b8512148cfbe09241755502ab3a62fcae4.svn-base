package com.tianyi.helmet.server.entity.netease;

/**
 * 网易抄送消息中的文件信息
 *
 * Created by liuhanc on 2017/10/17.
 */
public class FileInfo {
    private boolean caller;//是否是此通通话的发起者，若是则为true，若不是则没有此字段，可转为Boolean值
    private long channelid;//通道号，可转为Long值
    private String filename;//文件名，直接存储，混合录制文件filename带有"-mix"标记
    private String md5;//文件的md5值
    private boolean mix;//是否为混合录制文件，true：混合录制文件；false：单人录制文件
    private long size;//文件大小，单位为字符，可转为Long值
    private String type;//文件的类型（扩展名），包括：实时音频录制文件(aac)、白板录制文件(gz)、实时视频录制文件(mp4)、互动直播视频录制文件(flv)
    private Integer vid;//点播文件id，注意白板录制文件(gz)无此字段
    private String url;//文件的下载地址
    private String user;//用户帐号，若该文件为混合录制文件，则该字段为"0"

    public boolean isCaller() {
        return caller;
    }

    public void setCaller(boolean caller) {
        this.caller = caller;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean isMix() {
        return mix;
    }

    public void setMix(boolean mix) {
        this.mix = mix;
    }

    public long getChannelid() {
        return channelid;
    }

    public void setChannelid(long channelid) {
        this.channelid = channelid;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
