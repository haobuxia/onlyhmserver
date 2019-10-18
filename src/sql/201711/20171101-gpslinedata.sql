create table t_gpsline(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  fileId INT not null,
  lineNo INT not null,
  imei VARCHAR (20) not null,
  byteLength INT ,
  baseTime DateTime ,
  dataCount INT ,
  dataIds text,
  dataParts text
);
ALTER TABLE `t_gpsline` ADD INDEX `idx_gpsline_fileId` (`fileId`) ;
ALTER TABLE `t_gpsline` ADD INDEX `idx_gpsline_imei` (`imei`) ;
ALTER TABLE `t_gpsline` ADD INDEX `idx_gpsline_baseTime` (`baseTime`) ;