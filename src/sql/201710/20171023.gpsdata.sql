create table t_gpsdata(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  imei VARCHAR (20) not null,
  createTime TIMESTAMP not null,
  dataType INT not null ,
  val INT
);
ALTER TABLE `t_gpsdata` ADD INDEX `idx_gpsdata_clientid` (`clientId`) ;
ALTER TABLE `t_gpsdata` ADD INDEX `idx_gpsdata_createtime` (`createTime`) ;
ALTER TABLE `t_gpsdata` ADD INDEX `idx_gpsdata_imei` (`imei`) ;
ALTER TABLE `t_gpsdata` ADD INDEX `idx_gpsdata_datatype` (`dataType`) ;