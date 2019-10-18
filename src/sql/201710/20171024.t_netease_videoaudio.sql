create table t_netease_videoaudio(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  createTime BIGINT,
  type varchar(20),
  channelId BIGINT,
  accid1 varchar(20),
  accid2 varchar(20),
  caller varchar(20),
  duration INT
);
ALTER TABLE `t_netease_videoaudio` ADD INDEX `idx_netease_av_channelid` (`channelId`) ;
ALTER TABLE `t_netease_videoaudio` ADD INDEX `idx_netease_av_createtime` (`createTime`) ;
ALTER TABLE `t_netease_videoaudio` ADD INDEX `idx_netease_av_type` (`type`) ;