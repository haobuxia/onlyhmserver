CREATE TABLE hmserver.t_video_dataext
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    videoId INT NOT NULL,
    audioOssPath VARCHAR(100),
    audioOrigText VARCHAR(500),
    audioEditText VARCHAR(500),
    keywords VARCHAR(200)
);
CREATE UNIQUE INDEX idx_video_ext_videoid ON hmserver.t_video_dataext (videoId);
CREATE INDEX idx_video_ext_keyword ON hmserver.t_video_dataext (keywords);
ALTER TABLE `t_video_dataext`
MODIFY COLUMN `audioOrigText`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `audioOssPath`,
MODIFY COLUMN `audioEditText`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `audioOrigText`,
MODIFY COLUMN `keywords`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `audioEditText`;


CREATE TABLE hmserver.t_keyword
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    keywordName VARCHAR(20) NOT NULL
);
CREATE UNIQUE INDEX t_keyword_id_uindex ON hmserver.t_keyword (id);
CREATE UNIQUE INDEX t_keyword_keywordName_uindex ON hmserver.t_keyword (keywordName);
ALTER TABLE `t_keyword`
MODIFY COLUMN `keywordName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `id`;