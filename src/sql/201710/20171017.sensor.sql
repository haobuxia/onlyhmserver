create table t_sensor(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  createTime DATETIME not null,
  lat FLOAT,
  lon FLOAT ,
  concert INT,
  relax INT,
  xa INT,
  ya INT,
  za INT,
  xg INT,
  yg INT,
  zg INT,
  batty INT,
  runtime BIGINT
) DEFAULT CHARSET=utf8 ;
ALTER TABLE `t_sensor` ADD INDEX `idx_sensor_clientid` (`clientId`) ;
ALTER TABLE `t_sensor` ADD INDEX `idx_sensor_createtime` (`createTime`) ;
