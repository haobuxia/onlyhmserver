-- drop table t_netease_msg;
create table t_netease_msg(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  time TIMESTAMP not null,
  processFlag VARCHAR(1) not null DEFAULT '0',
  eventType VARCHAR(5),
  convType VARCHAR(20),
  toAccount VARCHAR(20),
  fromAccount VARCHAR (20),
  msgTimestamp VARCHAR (20),
  msgType VARCHAR (30),
  msgIdClient VARCHAR (40),
  msgIdServer VARCHAR (20),
  fullMsg VARCHAR (2000)
) DEFAULT CHARSET=utf8 ;
ALTER TABLE `t_netease_msg` ADD INDEX `idx_netease_msg_timestamp` (`msgTimestamp`) ;
ALTER TABLE `t_netease_msg` ADD INDEX `idx_netease_msg_procflag` (`processFlag`) ;
ALTER TABLE `t_netease_msg` ADD INDEX `idx_netease_msg_eventtype` (`eventType`) ;
ALTER TABLE `t_netease_msg` ADD INDEX `idx_netease_msg_from` (`fromAccount`) ;
ALTER TABLE `t_netease_msg` ADD INDEX `idx_netease_msg_to` (`toAccount`) ;