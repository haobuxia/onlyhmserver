CREATE TABLE hmserver.t_video_keyword
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    videoId INT,
    keywordId INT
);
CREATE INDEX idx_video_keyword_vdId ON hmserver.t_video_keyword (videoId);
CREATE INDEX idx_video_keyword_kwId ON hmserver.t_video_keyword (keywordId);

ALTER TABLE hmserver.t_video_dataext DROP keywords;