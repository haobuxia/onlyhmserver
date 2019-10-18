create table t_helmet_log(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId varchar(50),
  createTime DATETIME ,
  logType varchar(10),
  logUserId int,
  logContent varchar(2000)
) DEFAULT CHARSET=utf8 ;

ALTER TABLE t_helmet_log ADD INDEX `idx_helmetlog_clientId` (`clientId`) ;
ALTER TABLE t_helmet_log ADD INDEX `idx_helmetlog_logType` (`logType`) ;
ALTER TABLE t_helmet_log ADD INDEX `idx_helmetlog_userid` (`logUserId`) ;
ALTER TABLE t_helmet_log ADD INDEX `idx_helmetlog_createTime` (`createTime`) ;

create table t_user_log(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  userId int,
  createTime DATETIME ,
  logType varchar(10),
  logContent varchar(2000)
) DEFAULT CHARSET=utf8 ;

ALTER TABLE t_user_log ADD INDEX `idx_userlog_userId` (`userId`) ;
ALTER TABLE t_user_log ADD INDEX `idx_userlog_logType` (`logType`) ;
ALTER TABLE t_user_log ADD INDEX `idx_userlog_createTime` (`createTime`) ;

