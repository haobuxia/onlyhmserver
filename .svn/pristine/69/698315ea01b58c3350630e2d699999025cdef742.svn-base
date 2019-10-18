alter table t_image add column userid VARCHAR (20) DEFAULT '';
alter table t_video add column userid VARCHAR (20) DEFAULT '';
alter table t_audio add column userid VARCHAR (20) DEFAULT '';
alter table t_file add column userid VARCHAR (20) DEFAULT '';

ALTER TABLE `t_audio` ADD INDEX `idx_audio_userid` (`userid`) ;
ALTER TABLE `t_video` ADD INDEX `idx_video_userid` (`userid`) ;
ALTER TABLE `t_image` ADD INDEX `idx_image_userid` (`userid`) ;
ALTER TABLE `t_file` ADD INDEX `idx_file_userid` (`userid`) ;