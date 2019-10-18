create table t_video_timetag(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    videoId int DEFAULT 0,
    videoName VARCHAR(20),
    time DATETIME
);
CREATE INDEX idx_videotimetag_videoId ON hmserver.t_video_timetag (videoId);
CREATE INDEX idx_videotimetag_videoName ON hmserver.t_video_timetag (videoName);

CREATE INDEX idx_video_videoName ON hmserver.t_video (videoName);
