CREATE TABLE hmserver.t_video_action
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    videoId INT,
    tyboxImei VARCHAR(20) NOT NULL,
    videoTime TIMESTAMP NOT NULL,
    autoSpeedDown INT,
    swingArmRise INT,
    swingArmFall INT,
    dipperDigger INT,
    dipperDigger2 INT,
    dipperUnload INT,
    bucketDigger INT,
    bucketUnload INT
);
CREATE UNIQUE INDEX t_video_action_videoId_uindex ON hmserver.t_video_action (videoId);