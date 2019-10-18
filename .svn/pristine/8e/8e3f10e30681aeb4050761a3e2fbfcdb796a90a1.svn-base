create table t_gpsaction(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  imei VARCHAR (20) not null,
  createTime TIMESTAMP not null,
  action INT ,
  actionVal INT,
  walk INT
);
ALTER TABLE `t_gpsaction` ADD INDEX `idx_gpsaction_clientid` (`clientId`) ;
ALTER TABLE `t_gpsaction` ADD INDEX `idx_gpsaction_imei` (`imei`) ;
ALTER TABLE `t_gpsaction` ADD INDEX `idx_gpsaction_createtime` (`createTime`) ;

create table t_gpslocation(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  imei VARCHAR (20) not null,
  createTime DATETIME not null,
  latns INT,
  lat INT,
  lonew INT,
  lon INT,
  speed INT,
  orient INT,
  oldnew INT,
  altPosNeg INT,
  alt INT,
  star INT,
  gpsTime DATETIME
);
ALTER TABLE `t_gpslocation` ADD INDEX `idx_gpsloc_clientid` (`clientId`) ;
ALTER TABLE `t_gpslocation` ADD INDEX `idx_gpsloc_imei` (`imei`) ;
ALTER TABLE `t_gpslocation` ADD INDEX `idx_gpsloc_createtime` (`createTime`) ;
ALTER TABLE t_gpslocation ALTER COLUMN gpsTime SET DEFAULT '2000-01-01 01:01:01';

create table t_gpsgyro(
  id INT not null PRIMARY KEY AUTO_INCREMENT,
  clientId VARCHAR (50) not null,
  imei VARCHAR (20) not null,
  createTime TIMESTAMP not null,
  frontBack INT,
  leftRight INT,
  rotate INT,
  rotateMaxSpeed INT,
  rotateAvgSpeed INT,
  downAcceleration INT,
  upAcceleration INT,
  backTime  INT
);
ALTER TABLE `t_gpsgyro` ADD INDEX `idx_gpsgyro_clientid` (`clientId`) ;
ALTER TABLE `t_gpsgyro` ADD INDEX `idx_gpsgyro_imei` (`imei`) ;
ALTER TABLE `t_gpsgyro` ADD INDEX `idx_gpsgyro_createtime` (`createTime`) ;


