create table t_helmetstate(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId varchar(50),
  createTime DATETIME,
  onlineState int,
  netType int
);
ALTER TABLE `t_helmetstate` ADD INDEX `idx_helmetstate_clientId` (`clientId`) ;
