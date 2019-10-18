package com.tianyi.helmet.server.service.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 配置项
 * Created by liuhanc on 2017/12/1.
 */
@Service
public class ConfigService {

    //系统管理员配置
    @Value("${system.env}")
    private String systemEnv;
    @Value("${system.sudo.password}")
    private String sudoPassword;
    @Value("${user.logon.expire.minute:60}")
    private int userLogonExpireMinute;

    //头盔在线状态
    @Value("${helmet.online.intervalSeconds:40}")
    private int helmetOnlineIntervalSeconds;

    //文件服务器配置

    @Value("${fastdfs.enable}")
    private String fastdfsEnable;
    @Value("${fastdfs.server.url}")
    private String fastdfsServerUrl;
    @Value("${fastdfs.pool.min}")
    private int fastdfsPoolMin;
    @Value("${fastdfs.pool.max}")
    private int fastdfsPoolMax;
    @Value("${fastdfs.pool.waitseconds}")
    private int fastdfsPoolWaitSeconds;
    @Value("${fastdfs.retry.times}")
    private int fastdfsRetryTimes;

    //文件上传配置
    @Value("${upload.file.savedir}")
    private String uploadFileSaveDir;
    @Value("${upload.file.filedir}")
    private String uploadFileDir;
    @Value("${upload.file.imagedir}")
    private String uploadImageDir;
    @Value("${upload.file.audiodir}")
    private String uploadAudioDir;
    @Value("${upload.file.videodir}")
    private String uploadVideoDir;
    @Value("${upload.file.azkjdir}")
    private String uploadAzkjDir;
    @Value("${upload.file.azkjserver}")
    private String uploadAzkjServer;

    @Value("${upload.file.supporttypes}")
    private String uploadFileSupportTypes;
    @Value("${upload.image.supporttypes}")
    private String uploadImageSupportTypes;
    @Value("${upload.audio.supporttypes}")
    private String uploadAudioSupportTypes;
    @Value("${upload.video.supporttypes}")
    private String uploadVideoSupportTypes;

    //视频配置
    @Value("${thumbnail.width:260}")
    private int thumbnailWidth;
    @Value("${thumbnail.height:200}")
    private int thumbnailHeight;
    @Value("${video.watermark.filepath}")
    private String videoWaterMarkFilePath;
    @Value("${video.transcode:0}")
    private String videoTranscode;//1转码,0不转码
    @Value("${video.width:303}")
    private String videoWidth;
    @Value("${video.height:227}")
    private String videoHeight;

    //静态资源版本号
    @Value("${resource.static.version.usesystime:0}")
    private String resourceStaticVersionUseSysTime;//是否使用系统时间作为版本号。1是
    @Value("${resource.static.version:'1'}")
    private String resourceStaticVersion;//不使用系统时间时使用的版本号值

    //定时任务配置
    @Value("${job.enablefile:}")
    private String jobEnableFile;
    @Value("${job.enableip:}")
    private String jobEnableIp;

    //mqtt配置
    @Value("${mqtt.jobstart}")
    private int mqttJobStart;
    @Value("${mqtt.backend.clientId:SYSTEM_BACKEND}")
    private String mqttBackendClientId;
    @Value("${mqtt.protocal:tcp://}")
    private String mqttProtocal;
    @Value("${mqtt.server:222.223.231.137}")
    private String mqttServer;
    @Value("${mqtt.port}")
    private int mqttPort;
    @Value("${mqtt.user:}")
    private String mqttUser;
    @Value("${mqtt.password:}")
    private String mqttPassword;

    @Value("${gps.time.adjust:0}")
    @Deprecated //没用
    private int gpsTimeAdjust;

    //网易配置
    @Value("${netease.appkey}")
    private String neteaseAppKey;
    @Value("${netease.appSecret}")
    private String neteaseAppSecret;
    @Value("${netease.usernameprefix}")
    private String neteaseUserNamePrefix;
    @Value("${netease.agnameprefix}")
    private String agnameprefix;
    @Value("${netease.company}")
    private String neteaseCompany;
    @Value("${netease.url}")
    private String neteaseUrl;

    //ffmpeg配置
    @Value("${ffmpeg.path}")
    private String ffmpegBinDir;

    //手机短信发送
    @Value("${msg.url}")
    private String msgUrl;
    @Value("${msg.username}")
    private String msgUser;
    @Value("${msg.password}")
    private String msgPassword;
    @Value("${msg.timeout:5}")
    private int msgTimeout;
    @Value("${msg.send:1}")
    private int msgSend;

    //kmx数据库配置
    @Value("${kmx.enable:0}")
    private int kmxEnable;
    @Value("${kmx.reCreate:0}")
    private int kmxReCreate;
    @Value("${kmx.initMeta:0}")
    private int kmxInitMeta;
    @Value("${kmx.server:}")
    private String kmxServer;
    @Value("${kmx.port:9042}")
    private int kmxPort;
    @Value("${kmx.syncJobStart:0}")
    private int kmxSyncJobStart = 0;

    //天远oauth接口
    @Value("${tianyuan.oauth.url}")
    private String tianYuanOAuthUrl;
    @Value("${tianyuan.oauth.prod.url}")
    private String tianYuanOAuthProdUrl;
    @Value("${tianyuan.oauth.prod.special.loginName}")
    private String tianYuanOAuthProdLoginUser;
    @Value("${tianyuan.oauth.prod.special.password}")
    private String tianYuanOAuthProdLoginPass;

    @Value("${tianyuan.oauth.clientId}")
    private String tianYuanOAuthClientId;
    @Value("${tianyuan.oauth.clientSecret}")
    private String tianYuanOAuthClientSecret;
    @Value("${tianyuan.oauth.scope}")
    private String tianYuanOAuthScope;

    //天远零配件接口url
    @Value("${tianyuan.partservice.url}")
    private String tianYuanPartServiceUrl;
    @Value("${tianyuan.partservice.prod.url}")
    private String tianYuanPartServiceProdUrl;
    @Value("${tianyuan.partservice.prod.serviceNames}")
    private String tianYuanPartServiceProdServiceNames;

    //天远服务日志接口
    @Value("${tianyuan.svcservice.url}")
    private String tianYuanSvcServiceUrl;

    //天远智能服务接口
    @Value("${tianyuan.intesrv.url}")
    private String tianYuanIntesrvUrl;
    @Value("${tianyuan.intesrv.prod.url}")
    private String tianYuanIntesrvProdUrl;


    @Value("${tianyuan.intesrv.prod.net.url}")
    private String tianYuanIntesrvProdNetUrl;
    @Value("${tianyuan.intesrv.prod.url1}")
    private String tianYuanIntesrvProdUrl1;
    @Value("${tianyuan.intesrv.prod.net.url1}")
    private String tianYuanIntesrvProdNetUrl1;
    @Value("${tianyuan.intesrv.loginName}")
    private String tianYuanIntesrvLoginName;
    @Value("${tianyuan.intesrv.password}")
    private String tianYuanIntesrvPassword;
    @Value("${tianyuan.intesrv.jwt}")
    private String tianYuanIntesrvJwt;

    //天远地图接口
    @Value("${tianyuan.mapapi.url}")
    private String tianYuanMapApiUrl;
    @Value("${tianyuan.mapapi.key}")
    private String tianYuanMapApiKey;

    //田一服务日志接口
    @Value("${tianyi.svc.rest.baseUrl}")
    private String tySvcRestBaseUrl;
    @Value("${tianyi.svc.rest.appKey}")
    private String tySvcRestAppKey;
    @Value("${tianyi.svc.rest.appSecret}")
    private String tySvcRestAppSecret;

    //百度
    @Value("${baidu.speech.appid}")
    private String baiduSpeechAppId;
    @Value("${baidu.speech.appkey}")
    private String baiduSpeechAppKey;
    @Value("${baidu.speech.appsecret}")
    private String baiduSpeechAppSecret;

    @Value("${baidu.map.ak}")
    private String baiduMapAk;
    @Value("${baidu.map.sk}")
    private String baiduMapSk;
    @Value("${baidu.map.baseurl}")
    private String baiduMapBaseUrl;
    @Value("${baidu.map.geocoder}")
    private String baiduMapGeocoder;

    //科大讯飞
    @Value("${app_id}")
    private String kdxfAppId;
    @Value("${secret_key}")
    private String kdxfSecretKey;

    //天气情况 -心知天气
    @Value("${weather.appid}")
    private String weatherAppId;
    @Value("${weather.secretkey}")
    private String weatherSecretKey;
    @Value("${weather.baseurl}")
    private String weatherBaseUrl;

    //清华相关
    @Value("${tsinghua.video.output.dir}")
    private String tsinghuaVideoOutputDir;
    @Value("${tsinghua.kmx.enable:0}")
    private int tsinghuaLmxEnable;
    @Value("${tsinghua.kmx.server}")
    private String tsinghuaKmxServer;
    @Value("${tsinghua.kmx.port}")
    private int tsinghuaKmxPort;
    //天远地图接口
    @Value("${tianyuan.datacenter.url}")
    private String tianYuanDatacenterApiUrl;
    @Value("${tianyuan.lease.inspect.url}")
    private String tianYuanLeaseInspectApiUrl;

    @Value("${tianyuan.komatsu.inspect.url}")
    private String tianYuanKomatsuInspectApiUrl;

    @Value("${tianyuan.fix.inspect.url}")
    private String tianYuanFixInspectApiUrl;

    //田一接口平台
    @Value("${tianyi.intesrv.url}")
    private String tianyiIntesrvUrl;

    public String getTianyiIntesrvUrl() {
        return tianyiIntesrvUrl;
    }

    public void setTianyiIntesrvUrl(String tianyiIntesrvUrl) {
        this.tianyiIntesrvUrl = tianyiIntesrvUrl;
    }

    public String getUploadAzkjDir() {
        return uploadAzkjDir;
    }

    public String getUploadAzkjServer() {
        return uploadAzkjServer;
    }

    public void setUploadAzkjServer(String uploadAzkjServer) {
        this.uploadAzkjServer = uploadAzkjServer;
    }

    public void setUploadAzkjDir(String uploadAzkjDir) {
        this.uploadAzkjDir = uploadAzkjDir;
    }

    public String getTianYuanFixInspectApiUrl() {
        return tianYuanFixInspectApiUrl;
    }

    public void setTianYuanFixInspectApiUrl(String tianYuanFixInspectApiUrl) {
        this.tianYuanFixInspectApiUrl = tianYuanFixInspectApiUrl;
    }

    public String getTianYuanLeaseInspectApiUrl() {
        return tianYuanLeaseInspectApiUrl;
    }

    public void setTianYuanLeaseInspectApiUrl(String tianYuanLeaseInspectApiUrl) {
        this.tianYuanLeaseInspectApiUrl = tianYuanLeaseInspectApiUrl;
    }

    public String getTianYuanKomatsuInspectApiUrl() {
        return tianYuanKomatsuInspectApiUrl;
    }

    public void setTianYuanKomatsuInspectApiUrl(String tianYuanKomatsuInspectApiUrl) {
        this.tianYuanKomatsuInspectApiUrl = tianYuanKomatsuInspectApiUrl;
    }

    public String getTianYuanDatacenterApiUrl() {
        return tianYuanDatacenterApiUrl;
    }

    public void setTianYuanDatacenterApiUrl(String tianYuanDatacenterApiUrl) {
        this.tianYuanDatacenterApiUrl = tianYuanDatacenterApiUrl;
    }
    //0常州小松工厂1租赁2大修厂3智能服务
    public String getTianYuanInspectApiUrl(Integer project) {
        if(project != null && project.intValue()==0) {
            return tianYuanKomatsuInspectApiUrl;
        } else if(project != null && project.intValue()==1) {
            return tianYuanLeaseInspectApiUrl;
        } else if(project != null && project.intValue()==2) {
            return tianYuanFixInspectApiUrl;
        }
        return "";
    }

    public String getNeteaseUrl() {
        return neteaseUrl;
    }

    public void setNeteaseUrl(String neteaseUrl) {
        this.neteaseUrl = neteaseUrl;
    }

    //智能服务系统相关
    @Value("${ty}")



    public int getTsinghuaLmxEnable() {
        return tsinghuaLmxEnable;
    }

    public void setTsinghuaLmxEnable(int tsinghuaLmxEnable) {
        this.tsinghuaLmxEnable = tsinghuaLmxEnable;
    }

    public String getTsinghuaKmxServer() {
        return tsinghuaKmxServer;
    }

    public void setTsinghuaKmxServer(String tsinghuaKmxServer) {
        this.tsinghuaKmxServer = tsinghuaKmxServer;
    }

    public int getTsinghuaKmxPort() {
        return tsinghuaKmxPort;
    }

    public void setTsinghuaKmxPort(int tsinghuaKmxPort) {
        this.tsinghuaKmxPort = tsinghuaKmxPort;
    }

    public String getTsinghuaVideoOutputDir() {
        return tsinghuaVideoOutputDir;
    }

    public void setTsinghuaVideoOutputDir(String tsinghuaVideoOutputDir) {
        this.tsinghuaVideoOutputDir = tsinghuaVideoOutputDir;
    }

    public String getWeatherAppId() {
        return weatherAppId;
    }

    public void setWeatherAppId(String weatherAppId) {
        this.weatherAppId = weatherAppId;
    }

    public String getWeatherSecretKey() {
        return weatherSecretKey;
    }

    public void setWeatherSecretKey(String weatherSecretKey) {
        this.weatherSecretKey = weatherSecretKey;
    }

    public String getWeatherBaseUrl() {
        return weatherBaseUrl;
    }

    public void setWeatherBaseUrl(String weatherBaseUrl) {
        this.weatherBaseUrl = weatherBaseUrl;
    }

    public String getBaiduMapAk() {
        return baiduMapAk;
    }

    public void setBaiduMapAk(String baiduMapAk) {
        this.baiduMapAk = baiduMapAk;
    }

    public String getBaiduMapSk() {
        return baiduMapSk;
    }

    public void setBaiduMapSk(String baiduMapSk) {
        this.baiduMapSk = baiduMapSk;
    }

    public String getBaiduMapBaseUrl() {
        return baiduMapBaseUrl;
    }

    public void setBaiduMapBaseUrl(String baiduMapBaseUrl) {
        this.baiduMapBaseUrl = baiduMapBaseUrl;
    }

    public String getBaiduMapGeocoder() {
        return baiduMapGeocoder;
    }

    public void setBaiduMapGeocoder(String baiduMapGeocoder) {
        this.baiduMapGeocoder = baiduMapGeocoder;
    }

    public String getFastdfsEnable() {
        return fastdfsEnable;
    }

    public String getTianYuanOAuthProdLoginUser() {
        return tianYuanOAuthProdLoginUser;
    }

    public String getKdxfAppId() {
        return kdxfAppId;
    }

    public String getKdxfSecretKey() {
        return kdxfSecretKey;
    }

    public String getBaiduSpeechAppId() {
        return baiduSpeechAppId;
    }

    public String getBaiduSpeechAppKey() {
        return baiduSpeechAppKey;
    }

    public String getBaiduSpeechAppSecret() {
        return baiduSpeechAppSecret;
    }

    public String getTySvcRestBaseUrl() {
        return tySvcRestBaseUrl;
    }

    public String getTySvcRestAppKey() {
        return tySvcRestAppKey;
    }

    public String getTySvcRestAppSecret() {
        return tySvcRestAppSecret;
    }

    public String getTianYuanMapApiUrl() {
        return tianYuanMapApiUrl;
    }

    public String getTianYuanMapApiKey() {
        return tianYuanMapApiKey;
    }

    public String getTianYuanPartServiceUrl() {
        return tianYuanPartServiceUrl;
    }

    public String getTianYuanPartServiceProdUrl() {
        return tianYuanPartServiceProdUrl;
    }

    public String[] getTianYuanPartServiceProdServiceNames() {
        return tianYuanPartServiceProdServiceNames.split(",");
    }

    public String getTianYuanOAuthUrl() {
        return tianYuanOAuthUrl;
    }

    public String getTianYuanOAuthProdUrl() {
        return tianYuanOAuthProdUrl;
    }

    public String getTianYuanOAuthProdLoginPass() {
        return tianYuanOAuthProdLoginPass;
    }

    public String getTianYuanOAuthClientId() {
        return tianYuanOAuthClientId;
    }

    public String getTianYuanOAuthClientSecret() {
        return tianYuanOAuthClientSecret;
    }

    public String getTianYuanOAuthScope() {
        return tianYuanOAuthScope;
    }

    public String getNeteaseUserNamePrefix(String avprovider) {
        if("angelcomm".equals(avprovider)) {
            return agnameprefix;
        } else if("netease".equals(avprovider)) {
            return neteaseUserNamePrefix;
        }
        return "helmet";
    }

    public int getKmxSyncJobStart() {
        return kmxSyncJobStart;
    }

    public int getKmxEnable() {
        return kmxEnable;
    }

    public int getKmxReCreate() {
        return kmxReCreate;
    }

    public int getKmxInitMeta() {
        return kmxInitMeta;
    }

    public String getKmxServer() {
        return kmxServer;
    }

    public int getKmxPort() {
        return kmxPort;
    }

    public int getMsgSend() {
        return msgSend;
    }

    public String getSudoPassword() {
        return sudoPassword;
    }

    public int getUserLogonExpireMinute() {
        return userLogonExpireMinute;
    }

    public int getHelmetOnlineIntervalSeconds() {
        return helmetOnlineIntervalSeconds;
    }

    public String getSystemEnv() {
        return systemEnv;
    }

    public boolean isFastdfsEnable() {
        return "1".equals(fastdfsEnable);
    }

    public String getFastdfsServerUrl() {
        return fastdfsServerUrl;
    }

    public int getFastdfsPoolMin() {
        return fastdfsPoolMin;
    }

    public int getFastdfsPoolMax() {
        return fastdfsPoolMax;
    }

    public int getFastdfsPoolWaitSeconds() {
        return fastdfsPoolWaitSeconds;
    }

    public int getFastdfsRetryTimes() {
        return fastdfsRetryTimes;
    }

    public String getUploadFileSaveDir() {
        return uploadFileSaveDir;
    }

    public String getUploadFileDir() {
        return uploadFileDir;
    }

    public String getUploadImageDir() {
        return uploadImageDir;
    }

    public String getUploadAudioDir() {
        return uploadAudioDir;
    }

    public String getUploadVideoDir() {
        return uploadVideoDir;
    }

    public String getUploadFileSupportTypes() {
        return uploadFileSupportTypes;
    }

    public String getUploadImageSupportTypes() {
        return uploadImageSupportTypes;
    }

    public String getUploadAudioSupportTypes() {
        return uploadAudioSupportTypes;
    }

    public String getUploadVideoSupportTypes() {
        return uploadVideoSupportTypes;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public String getVideoWaterMarkFilePath() {
        return videoWaterMarkFilePath;
    }

    public String getVideoTranscode() {
        return videoTranscode;
    }

    public String getVideoWidth() {
        return videoWidth;
    }

    public String getVideoHeight() {
        return videoHeight;
    }

    public String getResourceStaticVersionUseSysTime() {
        return resourceStaticVersionUseSysTime;
    }

    public String getResourceStaticVersion() {
        return resourceStaticVersion;
    }

    public String getJobEnableFile() {
        return jobEnableFile;
    }

    public String getJobEnableIp() {
        return jobEnableIp;
    }

    public int getMqttJobStart() {
        return mqttJobStart;
    }

    public String getMqttBackendClientId() {
        return mqttBackendClientId;
    }

    public String getMqttProtocal() {
        return mqttProtocal;
    }

    public String getMqttServer() {
        return mqttServer;
    }

    public int getMqttPort() {
        return mqttPort;
    }

    public String getMqttUser() {
        return mqttUser;
    }

    public String getMqttPassword() {
        return mqttPassword;
    }

    public String getNeteaseAppKey() {
        return neteaseAppKey;
    }

    public String getNeteaseAppSecret() {
        return neteaseAppSecret;
    }

    public String getFfmpegBinDir() {
        return ffmpegBinDir;
    }

    public int getGpsTimeAdjust() {
        return gpsTimeAdjust;
    }

    public String getMsgUrl() {
        return msgUrl;
    }

    public String getMsgUser() {
        return msgUser;
    }

    public String getMsgPassword() {
        return msgPassword;
    }

    public int getMsgTimeout() {
        return msgTimeout;
    }

    public String getTianYuanSvcServiceUrl() {
        return tianYuanSvcServiceUrl;
    }

    public void setSystemEnv(String systemEnv) {
        this.systemEnv = systemEnv;
    }

    public void setSudoPassword(String sudoPassword) {
        this.sudoPassword = sudoPassword;
    }

    public void setUserLogonExpireMinute(int userLogonExpireMinute) {
        this.userLogonExpireMinute = userLogonExpireMinute;
    }

    public void setHelmetOnlineIntervalSeconds(int helmetOnlineIntervalSeconds) {
        this.helmetOnlineIntervalSeconds = helmetOnlineIntervalSeconds;
    }

    public void setFastdfsEnable(String fastdfsEnable) {
        this.fastdfsEnable = fastdfsEnable;
    }

    public void setFastdfsServerUrl(String fastdfsServerUrl) {
        this.fastdfsServerUrl = fastdfsServerUrl;
    }

    public void setFastdfsPoolMin(int fastdfsPoolMin) {
        this.fastdfsPoolMin = fastdfsPoolMin;
    }

    public void setFastdfsPoolMax(int fastdfsPoolMax) {
        this.fastdfsPoolMax = fastdfsPoolMax;
    }

    public void setFastdfsPoolWaitSeconds(int fastdfsPoolWaitSeconds) {
        this.fastdfsPoolWaitSeconds = fastdfsPoolWaitSeconds;
    }

    public void setFastdfsRetryTimes(int fastdfsRetryTimes) {
        this.fastdfsRetryTimes = fastdfsRetryTimes;
    }

    public void setUploadFileSaveDir(String uploadFileSaveDir) {
        this.uploadFileSaveDir = uploadFileSaveDir;
    }

    public void setUploadFileDir(String uploadFileDir) {
        this.uploadFileDir = uploadFileDir;
    }

    public void setUploadImageDir(String uploadImageDir) {
        this.uploadImageDir = uploadImageDir;
    }

    public void setUploadAudioDir(String uploadAudioDir) {
        this.uploadAudioDir = uploadAudioDir;
    }

    public void setUploadVideoDir(String uploadVideoDir) {
        this.uploadVideoDir = uploadVideoDir;
    }

    public void setUploadFileSupportTypes(String uploadFileSupportTypes) {
        this.uploadFileSupportTypes = uploadFileSupportTypes;
    }

    public void setUploadImageSupportTypes(String uploadImageSupportTypes) {
        this.uploadImageSupportTypes = uploadImageSupportTypes;
    }

    public void setUploadAudioSupportTypes(String uploadAudioSupportTypes) {
        this.uploadAudioSupportTypes = uploadAudioSupportTypes;
    }

    public void setUploadVideoSupportTypes(String uploadVideoSupportTypes) {
        this.uploadVideoSupportTypes = uploadVideoSupportTypes;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public void setVideoWaterMarkFilePath(String videoWaterMarkFilePath) {
        this.videoWaterMarkFilePath = videoWaterMarkFilePath;
    }

    public void setVideoTranscode(String videoTranscode) {
        this.videoTranscode = videoTranscode;
    }

    public void setVideoWidth(String videoWidth) {
        this.videoWidth = videoWidth;
    }

    public void setVideoHeight(String videoHeight) {
        this.videoHeight = videoHeight;
    }

    public void setResourceStaticVersionUseSysTime(String resourceStaticVersionUseSysTime) {
        this.resourceStaticVersionUseSysTime = resourceStaticVersionUseSysTime;
    }

    public void setResourceStaticVersion(String resourceStaticVersion) {
        this.resourceStaticVersion = resourceStaticVersion;
    }

    public void setJobEnableFile(String jobEnableFile) {
        this.jobEnableFile = jobEnableFile;
    }

    public void setJobEnableIp(String jobEnableIp) {
        this.jobEnableIp = jobEnableIp;
    }

    public void setMqttJobStart(int mqttJobStart) {
        this.mqttJobStart = mqttJobStart;
    }

    public void setMqttBackendClientId(String mqttBackendClientId) {
        this.mqttBackendClientId = mqttBackendClientId;
    }

    public void setMqttProtocal(String mqttProtocal) {
        this.mqttProtocal = mqttProtocal;
    }

    public void setMqttServer(String mqttServer) {
        this.mqttServer = mqttServer;
    }

    public void setMqttPort(int mqttPort) {
        this.mqttPort = mqttPort;
    }

    public void setMqttUser(String mqttUser) {
        this.mqttUser = mqttUser;
    }

    public void setMqttPassword(String mqttPassword) {
        this.mqttPassword = mqttPassword;
    }

    public void setGpsTimeAdjust(int gpsTimeAdjust) {
        this.gpsTimeAdjust = gpsTimeAdjust;
    }

    public void setNeteaseAppKey(String neteaseAppKey) {
        this.neteaseAppKey = neteaseAppKey;
    }

    public void setNeteaseAppSecret(String neteaseAppSecret) {
        this.neteaseAppSecret = neteaseAppSecret;
    }

    public void setNeteaseUserNamePrefix(String neteaseUserNamePrefix) {
        this.neteaseUserNamePrefix = neteaseUserNamePrefix;
    }

    public void setFfmpegBinDir(String ffmpegBinDir) {
        this.ffmpegBinDir = ffmpegBinDir;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public void setMsgUser(String msgUser) {
        this.msgUser = msgUser;
    }

    public void setMsgPassword(String msgPassword) {
        this.msgPassword = msgPassword;
    }

    public void setMsgTimeout(int msgTimeout) {
        this.msgTimeout = msgTimeout;
    }

    public void setMsgSend(int msgSend) {
        this.msgSend = msgSend;
    }

    public void setKmxEnable(int kmxEnable) {
        this.kmxEnable = kmxEnable;
    }

    public void setKmxReCreate(int kmxReCreate) {
        this.kmxReCreate = kmxReCreate;
    }

    public void setKmxInitMeta(int kmxInitMeta) {
        this.kmxInitMeta = kmxInitMeta;
    }

    public void setKmxServer(String kmxServer) {
        this.kmxServer = kmxServer;
    }

    public void setKmxPort(int kmxPort) {
        this.kmxPort = kmxPort;
    }

    public void setKmxSyncJobStart(int kmxSyncJobStart) {
        this.kmxSyncJobStart = kmxSyncJobStart;
    }

    public void setTianYuanOAuthUrl(String tianYuanOAuthUrl) {
        this.tianYuanOAuthUrl = tianYuanOAuthUrl;
    }

    public void setTianYuanOAuthProdUrl(String tianYuanOAuthProdUrl) {
        this.tianYuanOAuthProdUrl = tianYuanOAuthProdUrl;
    }

    public void setTianYuanOAuthProdLoginUser(String tianYuanOAuthProdLoginUser) {
        this.tianYuanOAuthProdLoginUser = tianYuanOAuthProdLoginUser;
    }

    public void setTianYuanOAuthProdLoginPass(String tianYuanOAuthProdLoginPass) {
        this.tianYuanOAuthProdLoginPass = tianYuanOAuthProdLoginPass;
    }

    public void setTianYuanOAuthClientId(String tianYuanOAuthClientId) {
        this.tianYuanOAuthClientId = tianYuanOAuthClientId;
    }

    public void setTianYuanOAuthClientSecret(String tianYuanOAuthClientSecret) {
        this.tianYuanOAuthClientSecret = tianYuanOAuthClientSecret;
    }

    public void setTianYuanOAuthScope(String tianYuanOAuthScope) {
        this.tianYuanOAuthScope = tianYuanOAuthScope;
    }

    public void setTianYuanPartServiceUrl(String tianYuanPartServiceUrl) {
        this.tianYuanPartServiceUrl = tianYuanPartServiceUrl;
    }

    public void setTianYuanPartServiceProdUrl(String tianYuanPartServiceProdUrl) {
        this.tianYuanPartServiceProdUrl = tianYuanPartServiceProdUrl;
    }

    public void setTianYuanPartServiceProdServiceNames(String tianYuanPartServiceProdServiceNames) {
        this.tianYuanPartServiceProdServiceNames = tianYuanPartServiceProdServiceNames;
    }

    public void setTianYuanSvcServiceUrl(String tianYuanSvcServiceUrl) {
        this.tianYuanSvcServiceUrl = tianYuanSvcServiceUrl;
    }

    public void setTianYuanMapApiUrl(String tianYuanMapApiUrl) {
        this.tianYuanMapApiUrl = tianYuanMapApiUrl;
    }

    public void setTianYuanMapApiKey(String tianYuanMapApiKey) {
        this.tianYuanMapApiKey = tianYuanMapApiKey;
    }

    public void setTySvcRestBaseUrl(String tySvcRestBaseUrl) {
        this.tySvcRestBaseUrl = tySvcRestBaseUrl;
    }

    public void setTySvcRestAppKey(String tySvcRestAppKey) {
        this.tySvcRestAppKey = tySvcRestAppKey;
    }

    public void setTySvcRestAppSecret(String tySvcRestAppSecret) {
        this.tySvcRestAppSecret = tySvcRestAppSecret;
    }

    public void setBaiduSpeechAppId(String baiduSpeechAppId) {
        this.baiduSpeechAppId = baiduSpeechAppId;
    }

    public void setBaiduSpeechAppKey(String baiduSpeechAppKey) {
        this.baiduSpeechAppKey = baiduSpeechAppKey;
    }

    public void setBaiduSpeechAppSecret(String baiduSpeechAppSecret) {
        this.baiduSpeechAppSecret = baiduSpeechAppSecret;
    }

    public void setKdxfAppId(String kdxfAppId) {
        this.kdxfAppId = kdxfAppId;
    }

    public void setKdxfSecretKey(String kdxfSecretKey) {
        this.kdxfSecretKey = kdxfSecretKey;
    }

    public String getTianYuanIntesrvUrl() {
        return tianYuanIntesrvUrl;
    }

    public void setTianYuanIntesrvUrl(String tianYuanIntesrvUrl) {
        this.tianYuanIntesrvUrl = tianYuanIntesrvUrl;
    }

    public String getTianYuanIntesrvProdUrl() {
        return tianYuanIntesrvProdUrl;
    }

    public void setTianYuanIntesrvProdUrl(String tianYuanIntesrvProdUrl) {
        this.tianYuanIntesrvProdUrl = tianYuanIntesrvProdUrl;
    }

    public String getTianYuanIntesrvLoginName() {
        return tianYuanIntesrvLoginName;
    }

    public void setTianYuanIntesrvLoginName(String tianYuanIntesrvLoginName) {
        this.tianYuanIntesrvLoginName = tianYuanIntesrvLoginName;
    }

    public String getTianYuanIntesrvPassword() {
        return tianYuanIntesrvPassword;
    }

    public void setTianYuanIntesrvPassword(String tianYuanIntesrvPassword) {
        this.tianYuanIntesrvPassword = tianYuanIntesrvPassword;
    }

    public String getTianYuanIntesrvJwt() {
        return tianYuanIntesrvJwt;
    }

    public void setTianYuanIntesrvJwt(String tianYuanIntesrvJwt) {
        this.tianYuanIntesrvJwt = tianYuanIntesrvJwt;
    }

    public String getTianYuanIntesrvProdNetUrl() {
        return tianYuanIntesrvProdNetUrl;
    }

    public void setTianYuanIntesrvProdNetUrl(String tianYuanIntesrvProdNetUrl) {
        this.tianYuanIntesrvProdNetUrl = tianYuanIntesrvProdNetUrl;
    }

    public String getNeteaseCompany() {
        return neteaseCompany;
    }

    public void setNeteaseCompany(String neteaseCompany) {
        this.neteaseCompany = neteaseCompany;
    }
    public String getTianYuanIntesrvProdUrl1() {
        return tianYuanIntesrvProdUrl1;
    }

    public void setTianYuanIntesrvProdUrl1(String tianYuanIntesrvProdUrl1) {
        this.tianYuanIntesrvProdUrl1 = tianYuanIntesrvProdUrl1;
    }

    public String getTianYuanIntesrvProdNetUrl1() {
        return tianYuanIntesrvProdNetUrl1;
    }

    public void setTianYuanIntesrvProdNetUrl1(String tianYuanIntesrvProdNetUrl1) {
        this.tianYuanIntesrvProdNetUrl1 = tianYuanIntesrvProdNetUrl1;
    }

    public String getAgnameprefix() {
        return agnameprefix;
    }

    public void setAgnameprefix(String agnameprefix) {
        this.agnameprefix = agnameprefix;
    }
}
