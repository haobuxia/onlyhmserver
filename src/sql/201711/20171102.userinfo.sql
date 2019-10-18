create table t_userinfo(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  username varchar(20) not null,
  password varchar(40) not null,
  yunId VARCHAR(40) ,
  yunToken VARCHAR (40),
  deviceId VARCHAR (40),
  regTime DATETIME
) ;
ALTER TABLE `t_userinfo` ADD INDEX `idx_userinfo_username` (`username`) ;
ALTER TABLE `t_userinfo` ADD INDEX `idx_userinfo_yunId` (`yunId`) ;
ALTER TABLE `t_userinfo` ADD INDEX `idx_userinfo_deviceId` (`deviceId`) ;