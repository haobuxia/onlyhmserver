create table t_svc_video(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rwh VARCHAR(20),
    uploadTime DATETIME,
    machineCode VARCHAR(20),
    clientId VARCHAR(20),
    userId INT ,
    userName VARCHAR(20),
    ossPath VARCHAR(100),
    videoType VARCHAR(20)
);
CREATE INDEX idx_svcvideo_rwh ON hmserver.t_svc_video (rwh);
CREATE INDEX idx_svcvideo_jihao ON hmserver.t_svc_video (machineCode);
CREATE INDEX idx_svcvideo_clientId ON hmserver.t_svc_video (clientId);
CREATE INDEX idx_svcvideo_userid ON hmserver.t_svc_video (userId);
CREATE INDEX idx_svcvideo_videoType ON hmserver.t_svc_video (videoType);

create table t_svc_audio(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rwh VARCHAR(20),
    uploadTime DATETIME,
    machineCode VARCHAR(20),
    clientId VARCHAR(20),
    userId INT ,
    userName VARCHAR(20),
    ossPath VARCHAR(100),
    audioType VARCHAR(20)
);
CREATE INDEX idx_svcaudio_rwh ON hmserver.t_svc_audio (rwh);
CREATE INDEX idx_svcaudio_jihao ON hmserver.t_svc_audio (machineCode);
CREATE INDEX idx_svcaudio_clientId ON hmserver.t_svc_audio (clientId);
CREATE INDEX idx_svcaudio_userid ON hmserver.t_svc_audio (userId);
CREATE INDEX idx_svcaudio_audioType ON hmserver.t_svc_audio (audioType);

create table t_svc_image(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rwh VARCHAR(20),
    uploadTime DATETIME,
    machineCode VARCHAR(20),
    clientId VARCHAR(20),
    userId INT ,
    userName VARCHAR(20),
    ossPath VARCHAR(100),
    imageType VARCHAR(20)
);
CREATE INDEX idx_svcimage_rwh ON hmserver.t_svc_image (rwh);
CREATE INDEX idx_svcimage_jihao ON hmserver.t_svc_image (machineCode);
CREATE INDEX idx_svcimage_clientId ON hmserver.t_svc_image (clientId);
CREATE INDEX idx_svcimage_userid ON hmserver.t_svc_image (userId);
CREATE INDEX idx_svcimage_imageType ON hmserver.t_svc_image (imageType);

