drop table t_terminal;
alter table t_video add column osspath VARCHAR (100);
alter table t_audio add column osspath VARCHAR (100);
alter table t_image add column osspath VARCHAR (100);

alter table t_video add column createTime TIMESTAMP ;
alter table t_audio add column createTime TIMESTAMP ;
alter table t_image add column createTime TIMESTAMP ;

ALTER TABLE `t_audio` ADD INDEX `idx_audio_termcode` (`termCode`) ;
ALTER TABLE `t_video` ADD INDEX `idx_video_termcode` (`termCode`) ;
ALTER TABLE `t_image` ADD INDEX `idx_image_termcode` (`termCode`) ;